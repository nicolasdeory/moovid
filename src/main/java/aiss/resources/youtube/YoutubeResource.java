package aiss.resources.youtube;


import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.restlet.resource.ClientResource;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kiulian.downloader.YoutubeDownloader;
import com.github.kiulian.downloader.YoutubeException;
import com.github.kiulian.downloader.model.Extension;
import com.github.kiulian.downloader.model.YoutubeVideo;
import com.github.kiulian.downloader.model.formats.AudioFormat;

import aiss.resources.spotify.SpotifyResource;
public class YoutubeResource {
	
	private static final Logger log = Logger.getLogger(YoutubeResource.class.getName());
	private static final String PATH = "./music";
	//private static final String PATH = ""
	
	private static String getIdFromJson(String json) {
		JsonNode query = null;
		String id = null;
		try {
			query = new ObjectMapper().readTree(json);
			//log.info(json);
			Iterator<JsonNode> itemNodes = query.get("items").elements();
			while (itemNodes.hasNext()) // Iterate and skip channel results. Take the first video result
			{
				JsonNode itemNode = itemNodes.next();
				if (itemNode.get("id").get("kind").textValue().equals("youtube#video"))
				{
					id = itemNode.get("id").get("videoId").textValue();
					break;
				} else 
				{
					log.info("Found non-video result, skipping");
				}
			}
		} catch (IOException e) {
			log.log(Level.WARNING, "Error parsing Youtube query JSON file");
			return null;
		} catch (NullPointerException e)
		{
			log.log(Level.WARNING, "Error parsing Youtube query JSON file (nullpointer)");
			return null;
		}
		return id;
	}
	
	public static String getVideoId(String query) {
		String path = "./keys/YoutubeKey.txt";
    	String key = null;
		InputStream is = SpotifyResource.class.getClassLoader().getResourceAsStream(path);
		try {
		      key = IOUtils.toString(is, "UTF-8");
		} catch (IOException e1) {
			log.log(Level.INFO, "Couldnt find SpotifyKey.txt");
		}
		
		String uri = "https://www.googleapis.com/youtube/v3/search?part=snippet&q=";
		uri += query + "&key=" + key;
		log.log(Level.FINE, "Searching videos at endpoint: " + uri);
		ClientResource cr = new ClientResource(uri);
		String json = cr.get(String.class);
		return getIdFromJson(json);
	}
	
	public static String getAudioStreamUrl(String id) {
		return downloadVideo(id, 1);
	}
	
	private static String downloadVideo(String id, int retry) {
		if (retry == 4) {
			log.log(Level.WARNING, "Couldnt download video with id (" + id + "): Reached maximum number of attempts");
			return null;
		}
		YoutubeDownloader downloader = new YoutubeDownloader(); 
		YoutubeVideo video = null;
		
		try {
			video = downloader.getVideo(id);
		} catch (YoutubeException | IOException e1) {
			log.log(Level.WARNING, "Error at video download with id (" + id + "): " + e1.getMessage());
			log.log(Level.INFO, "Failed download attempt (" + retry + "/3)");
			return downloadVideo(id, retry + 1);
		}
		List<AudioFormat> audios = video.findAudioWithExtension(Extension.WEBM);
		if (audios.isEmpty()) {
			log.log(Level.WARNING, "Error at video download with id (" + id + "): No audio files with .webm extension found");
			log.log(Level.INFO, "Failed download attempt (" + retry + "/3)");
			return downloadVideo(id, retry + 1);
		}
			
		log.log(Level.FINE, "Number of available audio downloads: " + audios.size());
		AudioFormat audioFormat;
		Comparator<AudioFormat> cmp = new Comparator<AudioFormat>() {
			@Override
			public int compare(AudioFormat x, AudioFormat y) {
				return x.bitrate().compareTo(y.bitrate());
			}
		};
		Predicate<AudioFormat> pred = new Predicate<AudioFormat>() {
			@Override
			public boolean test(AudioFormat x) {
				return x.bitrate() >= 160000;
			}
		};
		//Comparator zz = (ss,y)->ss.toString().compareTo(y.toString());
		if (audios.stream().filter(pred).findFirst().isPresent())
			audioFormat = audios.stream().filter(pred).min(cmp).get();
		else
			audioFormat = audios.stream().max(cmp).get();
		log.log(Level.FINE, "Chosen audio download with bitrate of: " + audioFormat.bitrate());
		
		return audioFormat.url(); // just pass the track url
		/*String musicPath = PATH;
		File outputDir = new File(PATH);
		File file = null;
		try {
			System.out.println("Check out this url : " + audioFormat.url());
			file = new File(musicPath + "/hola.webm");
			URL obj = new URL(audioFormat.url());
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setConnectTimeout(50000);
			con.setReadTimeout(50000);
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			int responseCode = con.getResponseCode();
			System.out.println("GET Response Code :: " + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
		        FileOutputStream fis = new FileOutputStream(file);
		        byte[] buffer = new byte[4096];
		        int count = 0;
		        while ((count = bis.read(buffer, 0, 4096)) != -1) {
		            fis.write(buffer, 0, count);
		        }
		        fis.close();
		        bis.close();
			} else {
				System.out.println("GET request not worked");
			}
		} catch (IOException e) {
			log.log(Level.WARNING, "Error at file creation from video (" + video.details().title() + "): " + e.getMessage());
			log.log(Level.INFO, "Failed download attempt (" + retry + "/3)");
			return downloadVideo(id, retry++);
		}
		log.log(Level.INFO, "Succesfully downloaded song at " + musicPath + " : " + video.details().title());
		return file.getName();*/
	}
	
}
