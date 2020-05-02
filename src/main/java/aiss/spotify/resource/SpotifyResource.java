package aiss.spotify.resource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Form;
import org.restlet.data.Method;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import aiss.spotify.model.AccessToken;
import aiss.spotify.model.Artist;
import aiss.spotify.model.Song;

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
        	//String keys = Files.readString(Paths.get("/aiss-project-template/keys/SpotifyKey.txt"));
        	String keys = "e3e7195d0d294ee18a9154414f0cd36e:f3c5a5f240724e53b682b04aeca8a87a";
        	String[] split = keys.split(":");
            cr1 = new ClientResource("https://accounts.spotify.com/api/token");
            Form form = new Form();
            form.set("grant_type", "client_credentials");
            Representation repr = form.getWebRepresentation();
            ChallengeResponse chres = new ChallengeResponse(
            				ChallengeScheme.HTTP_BASIC,split[0], split[1]);
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
        	log.log(Level.WARNING, "Error authorizing Spotify");
        }
    }
	
	public static Song getSongFromJson(String json) {
		JsonNode query = null;
		try {
			query = new ObjectMapper().readTree(json);
			JsonNode cancion = query.get("tracks").elements().next();
			String artist = cancion.get("artists").elements().next().get("name").textValue();
			String name = cancion.get("name").textValue();
			name.replaceAll("%20", " ");
			Song res = new Song(artist, name);
			log.log(Level.FINEST, "Successfully obtained Song object from JSON: " + res);
			return res;
		} catch (IOException e) {
			log.log(Level.WARNING, "Error parsing Spotify Recommendations JSON file");
			return null;
		}
	}
	
	public static Artist getArtistFromJson(String json){
		JsonNode query = null;
		try {
			query = new ObjectMapper().readTree(json);
			Artist res = new Artist (query.get("artists").get("items").elements()
					.next().get("id").textValue());
			log.log(Level.FINEST, "Successfully obtained Artist object from JSON: " + res);
			return res;
		} catch (IOException e) {
			log.log(Level.WARNING, "Error parsing Spotify Artist Search JSON file");
			return null;
		}
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
			id.add(SpotifyResource.getArtistFromJson(json_artist));
		}
		return id;
	}

	public static String getRecommendations(List<Artist> id, String danceability,
			String energy, String tempo, String valence) {
		String uri = "https://api.spotify.com/v1/recommendations?";
		uri += "limit=1";
		uri += "&seed_artists=";
		for (Artist artist : id)
			uri += artist.getId() + ",";
		uri = uri.substring(0, uri.length() - 1);
		if (danceability.equals("true"))
			uri += "&target_danceability=0.9";
		if (energy.equals("highenergy"))
			uri += "&target_energy=0.8";
		if (energy.equals("lowenergy"))
			uri += "&target_energy=0.2";
		if (tempo.equals("fastpaced"))
			uri += "&target_tempo=0.8";
		if (tempo.equals("slowpaced"))
			uri += "&target_tempo=0.2";
		if (valence.equals("happy"))
			uri += "&target_valence=0.8";
		if (valence.equals("sad"))
			uri += "&target_valence=0.2";
		if (!isAuthorized()) authorize();
		log.log(Level.FINE, "Searching recommendations at endpoint: " + uri);
		ClientResource cr_sr = new ClientResource(uri);
		ChallengeResponse chres = new ChallengeResponse(
				ChallengeScheme.HTTP_OAUTH_BEARER);
		chres.setRawValue(token.getAccessToken());
		cr_sr.getRequest().setChallengeResponse(chres);
		return cr_sr.get(String.class);
	}
}
