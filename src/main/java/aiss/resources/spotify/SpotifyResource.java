package aiss.resources.spotify;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Form;
import org.restlet.data.Method;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import aiss.model.spotify.AccessToken;
import aiss.model.spotify.Artist;
import aiss.model.spotify.Song;

public class SpotifyResource {
	
	private static AccessToken token;
	private static final Logger log = Logger.getLogger(SpotifyResource.class.getName());
	
	public static boolean isAuthorized() {
		if (token == null) {
			log.log(Level.FINER, "Cannot find an Spotify access token, a new one must be requested");
			return false;
		}
		else {
			long time = token.getCreationTimestamp().until(LocalDateTime.now(), ChronoUnit.SECONDS);
			if (time < token.getExpiresIn()) {
				log.log(Level.FINER, "Spotify access token("+ token.getAccessToken()
					+ ") still usable" );
				return true;
			}
				
			else {
				log.log(Level.FINER, "Spotify access token("+ token.getAccessToken()
					+ ") timed out, a new one must be requested");
				return false;
			}
		}
	}
	
	public static void authorize(){
       ClientResource cr1 = null;
        try {
        	String path = "./keys/SpotifyKey.txt";
        	String keys = null;
    		InputStream is = SpotifyResource.class.getClassLoader().getResourceAsStream(path);
    		try {
			      keys = IOUtils.toString(is, "UTF-8");
			} catch (IOException e1) {
				log.log(Level.INFO, "Couldnt find SpotifyKey.txt");
			}
        	String[] split = keys.split(":");
            cr1 = new ClientResource("https://accounts.spotify.com/api/token");
            Form form = new Form();
            form.set("grant_type", "client_credentials");
            Representation repr = form.getWebRepresentation();
            ChallengeResponse chres = new ChallengeResponse(
            				ChallengeScheme.HTTP_BASIC,split[0].trim(), split[1].trim());
            cr1.getRequest().setChallengeResponse(chres);
            cr1.setEntityBuffering(true);
            cr1.setMethod(Method.POST);
            cr1.getRequest().setEntity(repr);
            cr1.getRequestEntity();
            token = cr1.post(repr, AccessToken.class);
            token.setCreationTimestamp(LocalDateTime.now());
            log.log(Level.INFO, "Successfully obtained Spotify access token: "
            		+ token.getAccessToken());
        } catch (ResourceException re){
        	log.log(Level.WARNING, "Error authorizing Spotify " + re.getMessage());
        }
    }
	
	public static List<Song> getSongsFromJson(String json) {
		JsonNode query = null;
		List<Song> result = new ArrayList<Song>();;
		try {
			query = new ObjectMapper().readTree(json);
			Iterator<JsonNode> canciones = query.get("tracks").elements();
			while (canciones.hasNext()) {
				JsonNode cancion = canciones.next();
				String artist = cancion.get("artists").elements().next().get("name").textValue();
				String name = cancion.get("name").textValue();
				name.replaceAll("%20", " ");
				String id = cancion.get("id").textValue();
				Double bpm = getBPM(id);
				Song s = new Song(artist, name, bpm);
				result.add(s);
				log.log(Level.INFO, "Successfully obtained Song object from JSON: " + s);
			}
			
		} catch (IOException e) {
			log.log(Level.WARNING, "Error parsing Spotify Recommendations JSON file: " + e.getMessage());
		}
		return result;
	}
	
	public static Artist getArtistFromJson(String json){
		JsonNode query = null;
		try {
			query = new ObjectMapper().readTree(json);
			Artist res = new Artist (query.get("artists").get("items").elements()
					.next().get("id").textValue());
			log.log(Level.INFO, "Successfully obtained Artist object from JSON: " + res);
			return res;
		} catch (IOException e) {
			log.log(Level.WARNING, "Error parsing Spotify Artist Search JSON file: " + e.getMessage());
			return null;
		}
	}
	
	public static Double getBPMFromJson(String json) {
		JsonNode query = null;
		try {
			query = new ObjectMapper().readTree(json);
			Double res = query.get("tempo").asDouble();
			log.log(Level.INFO, "Successfully obtained song BPM from JSON: " + res);
			return res;
		} catch (IOException e) {
			log.log(Level.WARNING, "Error parsing Audio Features JSON file: " + e.getMessage());
			return null;
		}
	}
	
	public static String getRecommendations(List<Artist> artists, List<String> genres, String danceability,
			String energy, String instrumentalness, String tempo, String valence) {
		String uri = "https://api.spotify.com/v1/recommendations?";
		uri += "limit=10&market=ES";
		for (Artist artist : artists) {
			if (artists.indexOf(artist)!=0)
				uri += "%2C";
			else
				uri += "&seed_artists=";
			uri += artist.getId();
		}
		for (String genre : genres) {
			if (genres.indexOf(genre) != 0)
				uri += "%2C";
			else
				uri += "&seed_genres=";
			uri += genre;
		}
		if (danceability.equals("true"))
			uri += "&min_danceability=0.5";
		if (energy.equals("highenergy"))
			uri += "&min_energy=0.6";
		if (energy.equals("lowenergy"))
			uri += "&max_energy=0.4";
		if (instrumentalness.equals("lyrics"))
			uri += "&target_instrumentalness=0.0";
		if (instrumentalness.equals("instrumental"))
			uri += "&min_instrumentalness=0.2";
		if (tempo.equals("fastpaced"))
			uri += "&min_tempo=110.0";
		if (tempo.equals("slowpaced"))
			uri += "&max_tempo=120.0";
		if (valence.equals("happy"))
			uri += "&min_valence=0.5";
		if (valence.equals("sad"))
			uri += "&max_valence=0.5";
		if (!isAuthorized()) authorize();
		log.log(Level.INFO, "Searching recommendations at endpoint: " + uri);
		ClientResource cr_sr = new ClientResource(uri);
		ChallengeResponse chres = new ChallengeResponse(
				ChallengeScheme.HTTP_OAUTH_BEARER);
		chres.setRawValue(token.getAccessToken());
		cr_sr.getRequest().setChallengeResponse(chres);
		return cr_sr.get(String.class);
	}
	
	public static String getBasicRecommendations() {
		String uri = "https://api.spotify.com/v1/recommendations?";
		uri += "limit=10&market=ES";
		uri += "&seed_genres=edm%2Creggaeton%2Cpop&min_danceability=0.5";
		if (!isAuthorized()) authorize();
		log.log(Level.INFO, "Searching basic recommendations at endpoint: " + uri);
		ClientResource cr_sr = new ClientResource(uri);
		ChallengeResponse chres = new ChallengeResponse(
				ChallengeScheme.HTTP_OAUTH_BEARER);
		chres.setRawValue(token.getAccessToken());
		cr_sr.getRequest().setChallengeResponse(chres);
		return cr_sr.get(String.class);
	}
	
	public static List<Artist> getArtistIds(String artists) {
		if (!isAuthorized()) authorize();
		String[] artist_split = artists.split(",");
		List<Artist> id = new ArrayList<Artist>();
		for (String artist : artist_split) {
			String uri = "https://api.spotify.com/v1/search?q=";
			uri += artist.trim() + "&type=artist";
			log.log(Level.FINE, "Searching artist at endpoint: " + uri);
			ClientResource cr_ar = new ClientResource(uri);
			ChallengeResponse chres = new ChallengeResponse(
    				ChallengeScheme.HTTP_OAUTH_BEARER);
			chres.setRawValue(token.getAccessToken());
			cr_ar.getRequest().setChallengeResponse(chres);
			String json_artist = cr_ar.get(String.class);
			id.add(getArtistFromJson(json_artist));
		}
		return id;
	}

	public static Double getBPM(String id) {
		String uri = "https://api.spotify.com/v1/audio-features/";
		uri += id;
		log.log(Level.FINE, "Searching song data at endpoint: " + uri);
		ClientResource cr_af = new ClientResource(uri);
		ChallengeResponse chres = new ChallengeResponse(
				ChallengeScheme.HTTP_OAUTH_BEARER);
		chres.setRawValue(token.getAccessToken());
		cr_af.getRequest().setChallengeResponse(chres);
		String json_song_data = cr_af.get(String.class);
		return getBPMFromJson(json_song_data);
	}
}
