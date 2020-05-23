package aiss.montage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.google.appengine.api.ThreadManager;

import aiss.model.luis.classes.MusicIntent;
import aiss.model.luis.enumerates.MontageTheme;
import aiss.model.photos.mediaitem.MediaItem;
import aiss.model.photos.mediaitem.MediaItems;
import aiss.model.spotify.Artist;
import aiss.model.spotify.Song;
import aiss.resources.photos.MediaItemResource;
import aiss.resources.spotify.SpotifyResource;
import aiss.resources.youtube.YoutubeResource;

public class JobManager {

	private static Queue<MontageJob> queue;
	
	private static Map<String, MontageResult> results;
	
	private static Random rand;
	
	public static void initialize() 
	{
		results = new HashMap<String, MontageResult>();
		queue = new LinkedList<>();
		rand = new Random();
		Thread thread = ThreadManager.createBackgroundThread(new Runnable() {
		    public void run() {
		        queueLoop();
		    }
		});
		thread.start();
	}
	
	private static void queueLoop()
	{
		while (true)
		{
			if (queue.isEmpty())
			{
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) { }
				continue;
			}
			
			MontageJob currentJob = queue.poll();
			LocalDate start = currentJob.getStart();
			LocalDate end = currentJob.getEnd();
			List<MontageTheme> themes = currentJob.getThemes();
			List<String> themeStrings = new ArrayList<String>();
			for(MontageTheme t : themes)
			{
				themeStrings.add(t.toString());
			}
			
			if (start == null && end == null)
			{
				// Last two weeks
				end = LocalDate.now();
				start = end.minusWeeks(2);
			} else if (end == null)
			{
				// Photos to the same day
				end = start;
			} else if (start == null)
			{
				// Photos to the same day
				start = end;
			}
			
			// Montage theme never empty
			
			// Init montageresult
			
			
			// Retrieve photos
			MediaItemResource mr = new MediaItemResource(currentJob.getPhotosToken());
			MediaItems mis = mr.searchMediaItem(new ArrayList<LocalDate>(), start, end, themeStrings, new ArrayList<String>());
			System.out.println("media items " + mis);
			List<String> urls = getDownloadUrls(mis);
			
			// Music
			
			MusicIntent music = currentJob.getMusicDesciption();
			List<Artist> authorList = new ArrayList<Artist>();
			for(String a : music.getAuthor())
			{
				authorList.add(SpotifyResource.getArtistIds(a).get(0)); // Gets the most probable artist result
			}
			System.out.println("author list: " + authorList);
			String songJson;
			if (music == null || authorList.size() == 0)
			{
				// TODO: Default song...
				songJson = SpotifyResource.getRecommendations(new ArrayList<Artist>(), null, 
						null, null, null, null, null);
			}
			else
			{
				// TODO: IMPROVE, it doesn't work well. We've gotta pass at least one genre or artist or track
				songJson = SpotifyResource.getRecommendations(authorList, music.getGenre(), 
						music.getDanceable().toString(), music.getEnergy().toString(), music.getAcoustic().toString(),
						music.getTempo().toString(), music.getMood().toString());
				
			}
			List<Song> songs = SpotifyResource.getSongsFromJson(songJson);
			System.out.println("Found songs " + songs);
			//Song randomSong = songs.get(rand.nextInt(songs.size()));
			Song randomSong = songs.get(0); // Get first one
			String songName = randomSong.getName();
			String songAuthor = randomSong.getArtist();
			System.out.println("Picked song " + songAuthor + "--" + songName);
			
			// Get song from Youtube
			String videoId = YoutubeResource.getVideoId(songAuthor + " " + songName);
			System.out.println("video id is " + videoId);
			String videoName = YoutubeResource.downloadVideo(videoId); // TODO: Pass video url instead of file...it's slow AF
			
			// Finish up
			System.out.println("photo urls are " + urls);
			MontageResult montageResult = new MontageResult(urls, videoName);
			results.put(currentJob.getUuid().toString(), montageResult);
			
		}
	}
	
	public static MontageResult queryResult(String uuid)
	{
		if (results == null)
			initialize();
		
		if (results.containsKey(uuid))
		{
			return results.get(uuid);
		}
		else
		{
			return null;
		}
	}
	
	public static List<String> getDownloadUrls(MediaItems mitms){
		List<String> ls = new ArrayList<String>();
		if(mitms==null) return ls;
		for(MediaItem mi: mitms.getMediaItems()) {
			String baseUrl = mi.getBaseUrl();
			String urlFinal = baseUrl + "=w1020-h720-d";
			ls.add(urlFinal);
		}
		return ls;
	}
	
	public static void enqueueJob(MontageJob job)
	{
		if (queue == null)
			initialize();
		
		queue.add(job);
		results.put(job.getUuid().toString(), new MontageResult());
	}
	
	
	
}
