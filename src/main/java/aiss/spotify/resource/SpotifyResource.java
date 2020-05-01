package aiss.spotify.resource;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SpotifyResource {

	public static String getRecommendationsURI(List<String> artistsID, String danceability, String energy,
			String tempo, String valence) {
		String uri = "https://api.spotify.com/v1/recommendations?";
		uri += "limit=1";
		uri += "&seed_artists=";
		for (String id : artistsID)
			uri += id + ",";
		uri = uri.substring(0, uri.length() - 1);
		if (danceability.equals("true"))
		uri += "&target_danceability=0.9";
		if (!energy.equals("none"))
			if (energy.equals("highenergy"))
				uri += "&target_energy=0.8";
			else
				uri += "&target_energy=0.2";
		if (!tempo.equals("none"))
			if (tempo.equals("fastpaced"))
				uri += "&target_tempo=0.8";
			else
				uri += "&target_tempo=0.2";
		if (!valence.equals("none"))
			if (valence.equals("happy"))
				uri += "&target_valence=0.8";
			else
				uri += "&target_valence=0.2";
		return uri;
	}
	
	public static String getArtistURI(String artist) {
		String uri = "https://api.spotify.com/v1/search?q=";
		uri += artist + "&type=artist";
		return uri;
	}
	
	public static String getSearch(String json) {
		JsonNode query = null;
		try {
			query = new ObjectMapper().readTree(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonNode cancion = query.get("tracks").elements().next();
		String res = cancion.get("artists").elements().next().get("name").textValue();
		res += "%20" + cancion.get("name").textValue();
		return res;
	}
	
	public static String getArtistId(String json){
		JsonNode query = null;
		try {
			query = new ObjectMapper().readTree(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return query.get("artists").get("items").elements().next().get("id").textValue();
	}
}
