package aiss.montage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
	
	private static Map<String, MontageJobResult> results;
	private static Map<String, MontageJob> jobs;
	
	private static Random rand;
	
	private static Set<String> basicRecommendationsYtIdCache = new HashSet<String>();

	private static final Logger log = Logger.getLogger(JobManager.class.getName());
	
	public static void initialize() 
	{
		results = new HashMap<String, MontageJobResult>();
		queue = new LinkedList<>();
		jobs = new HashMap<String, MontageJob>();
		rand = new Random();
	}
	
	private static void processJob(MontageJob job)
	{
		LocalDate start = job.getStart();
		LocalDate end = job.getEnd();
		List<MontageTheme> themes = job.getThemes();
		List<String> themeStrings = new ArrayList<String>();
		if (themes != null) // HACK: When you do I want a montage -> no -> yes -> no, themes go null
		{
			for(MontageTheme t : themes)
			{
				themeStrings.add(t.toString());
			}
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
				MediaItemResource mr = new MediaItemResource(job.getPhotosToken());
				List<String> photoUrls = mr.searchMediaItem(new ArrayList<LocalDate>(), start, end, themeStrings, new ArrayList<String>());
				log.info("media urls retrieved. size " + photoUrls.size());
				
		// Music
		
				MusicIntent music = job.getMusicDesciption();
				
				String songJson = null;
				boolean usingBasicRecommendations = false;
				if (music == null)
				{
					// TODO: Default song...
					// Pick genre from a random list (edm, reggaeton, pop)
					// Keep attributes that the user might have specified
					songJson = SpotifyResource.getBasicRecommendations();
					usingBasicRecommendations = true;
				}
				else if (music.getVideoId() == null)
				{
					List<String> musicIntentAuthorList = music.getAuthor();
					List<Artist> authorList = new ArrayList<Artist>();
					if (musicIntentAuthorList != null)
					{
						for(String a : music.getAuthor())
						{
							authorList.add(SpotifyResource.getArtistIds(a).get(0)); // Gets the most probable artist result
						}
					}
					log.info("author list: " + authorList);
					if (authorList.size() == 0)
					{
						log.info("author list and had 0 elements");
						if (music.getGenre().size() == 0)
						{
							log.warning("author list and genres had 0 elements, resorting to basic recommendations");
							songJson = SpotifyResource.getBasicRecommendations();
							usingBasicRecommendations = true;
						}
						else
						{
							songJson = SpotifyResource.getRecommendations(authorList, music.getGenre(), 
									music.getDanceable().toString(), music.getEnergy().toString(), music.getAcoustic().toString(),
									music.getTempo().toString(), music.getMood().toString());
						}
						
					}
					else
					{
						songJson = SpotifyResource.getRecommendations(authorList, music.getGenre(), 
								music.getDanceable().toString(), music.getEnergy().toString(), music.getAcoustic().toString(),
								music.getTempo().toString(), music.getMood().toString());
					}
				}
				
				
				
		
		// Get Youtube audio stream link
		
		List<String> youtubeIdUrls = new ArrayList<String>();
		
		if (music == null || music.getVideoId() == null)
		{
			List<Song> songs = SpotifyResource.getSongsFromJson(songJson);
			log.info("Found songs " + songs);
			//Song randomSong = songs.get(rand.nextInt(songs.size()));
			List<Song> threeSongs = songs.subList(0, 1); // Only 1 song to prevent rate limiting. It may be not robust enough
			for(Song s : threeSongs)
			{
				String songName = s.getName();
				String songAuthor = s.getArtist();
				log.info("Picked song " + songAuthor + "--" + songName);
			}
			
			// TODO: random chance to find new recommendations, else cache has always only length 3
			System.out.println("basicRecommendations : " + usingBasicRecommendations);
			System.out.println("cache " + basicRecommendationsYtIdCache.toString());
			if (usingBasicRecommendations && basicRecommendationsYtIdCache.size() > 2) 
			{
				List<String> basicRecommendationsYtIdCacheList = basicRecommendationsYtIdCache.stream().collect(Collectors.toList());
				//String randVideoId = basicRecommendationsYtIdCacheList.get(rand.nextInt(basicRecommendationsYtIdCacheList.size()));
				for(int i = 0; i < 3; i++)
				{
					String randVideoId = basicRecommendationsYtIdCacheList.get(i);
					if (randVideoId != null)
					{
						log.info("got video from basic recommendations cache. Id is " + randVideoId);
						youtubeIdUrls.add(randVideoId);
						//String audioStreamUrl = YoutubeResource.getAudioStreamUrl(randVideoId);
						//if (audioStreamUrl != null && audioStreamUrl.length() > 0)
						//	audioStreamUrls.add(audioStreamUrl);
					}
				}
			}
			else
			{
				for (Song s : threeSongs)
				{
					String songName = s.getName();
					String songAuthor = s.getArtist();
					// Get song from Youtube
					String videoId = YoutubeResource.getVideoId(songAuthor + " " + songName);
					if (usingBasicRecommendations)
					{
						log.info("added video id " + videoId + " to cache");
						basicRecommendationsYtIdCache.add(videoId);
					}
						
					if (videoId != null)
					{
						log.info("video id is " + videoId);
						youtubeIdUrls.add(videoId);
						//String audioStreamUrl = YoutubeResource.getAudioStreamUrl(videoId);
						//if (audioStreamUrl != null && audioStreamUrl.length() > 0)
						//	audioStreamUrls.add(audioStreamUrl);
					}
				}
			}
		} else 
		{
			log.info("User specified custom video id, returning that");
			youtubeIdUrls.add(music.getVideoId());
		}
		
		if (youtubeIdUrls.size() == 0)
		{
			log.severe("CRITICAL: No youtube video ids were found!! Returning empty list");	
		}
		
		if (photoUrls.size() == 0)
		{
			log.warning("WARNING: No photos were found. Either user has an invalid token, or specified very narrow filters");	
		}
		
		
		// Finish up
		//log.info("photo urls are " + urls);
		MontageJobResult montageResult = new MontageJobResult(photoUrls, youtubeIdUrls);
		results.put(job.getUuid().toString(), montageResult);
	}
	
	
	public static MontageJobResult queryResult(String uuid)
	{
		if (results == null)
			initialize();
		
		if (results.containsKey(uuid) && !results.get(uuid).isPending())
		{
			return results.get(uuid);
		}
		else
		{
			if (jobs.containsKey(uuid))
			{
				processJob(jobs.get(uuid));
				return results.get(uuid);
			} else
			{
				return null;
			}
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
		if (jobs == null)
			initialize();
		
		jobs.put(job.getUuid().toString(), job);
		results.put(job.getUuid().toString(), new MontageJobResult());
	}}
