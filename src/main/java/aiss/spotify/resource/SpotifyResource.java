package aiss.spotify.resource;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import aiss.spotify.model.AccessToken;
import aiss.spotify.model.Artist;
import aiss.spotify.model.Auth;
import aiss.spotify.model.Song;

public class SpotifyResource {
	
	private static AccessToken token;
	
	public static boolean isAuthorized() {
		if (token == null)
			return false;
		else {
			long time = token.getCreationTimestamp().until(LocalTime.now(), ChronoUnit.SECONDS);
			return time < token.getExpiresIn();
		}
	}
	
	public static void authorize(){
       ClientResource cr1 = null;
        try {
            cr1 = new ClientResource("https://accounts.spotify.com/api/token");
            Auth auth = new Auth("client_credentials");
            ChallengeResponse chres = new ChallengeResponse(new ChallengeScheme("", ""));
            chres.setRawValue("e3e7195d0d294ee18a9154414f0cd36e:"
            		+ "f3c5a5f240724e53b682b04aeca8a87a");
            Form form = new Form();
            form.set("grant_type", "client_credentials");
            Representation repr = form.getWebRepresentation();
            cr1.getRequest().setChallengeResponse(chres);
            //cr1.addQueryParameter("grant_type", "client_credentials");
            //cr1.getRequest().getHeaders().add("Authorization", );
            cr1.setEntityBuffering(true);
            AccessToken uploadToken = cr1.post(repr, AccessToken.class);
            uploadToken.setCreationTimestamp(LocalDateTime.now());
        } catch (ResourceException re){
        	//TODO: logger
            System.out.println("Error authorizing Spotify");
        }
    }
	
	public static Song getSongFromJson(String json) {
		JsonNode query = null;
		try {
			query = new ObjectMapper().readTree(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonNode cancion = query.get("tracks").elements().next();
		String artist = cancion.get("artists").elements().next().get("name").textValue();
		String name = cancion.get("name").textValue();
		return new Song(artist, name);
	}
	
	public static Artist getArtistFromJson(String json){
		JsonNode query = null;
		try {
			query = new ObjectMapper().readTree(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Artist (query.get("artists").get("items").elements()
				.next().get("id").textValue());
	}
	
	public static List<Artist> testGetArtist() {
		String artists = "zedd,savant";
		String[] artist_split = artists.split(",");
		List<Artist> id = new ArrayList<Artist>();
		for (String artist : artist_split) {
			String uri_artist = "https://api.spotify.com/v1/search?q=";
			uri_artist += artist + "&type=artist";
			ClientResource cr_ar = new ClientResource(uri_artist);
			ChallengeResponse chres = new ChallengeResponse(new ChallengeScheme("", ""));
            chres.setRawValue(token.getAccessToken());
            cr_ar.getRequest().setChallengeResponse(chres);
			//cr_ar.getRequest().getHeaders().add("Authorization", token.getAccessToken());
			String json_artist = cr_ar.get(String.class);
			id.add(SpotifyResource.getArtistFromJson(json_artist));
		}
		return id;
	}
}
