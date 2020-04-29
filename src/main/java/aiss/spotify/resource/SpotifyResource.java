package aiss.spotify.resource;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SpotifyResource {

	private static String getRecommendationsURI(List<String> artistsID, Integer limit, Double danceability, Double energy,
			Double instrumentalness, Double tempo, Double valence) {
		String uri = "https://api.spotify.com/v1/recommendations?";
		uri += "limit=" + limit;
		uri += "&seed_artists=";
		for (String id : artistsID)
			uri += id + ",";
		uri = uri.substring(0, uri.length() - 1);
		uri += "&target_danceability=" + danceability;
		if (energy != -1.0)
			uri += "&target_energy=" + energy;
		if (instrumentalness != -1.0)
			uri += "&target_instrumentalness=" + instrumentalness;
		if (tempo != -1.0)
			uri += "&target_tempo=";
		if (valence != -1.0)
			uri += "&target_valence=" + valence; 
		return uri;
	}
	
	private static String getArtistURI(String artist) {
		String uri = "https://api.spotify.com/v1/search?q=";
		uri += artist + "&type=artist";
		return uri;
	}
	
	private static String getSearch(String json) {
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
	
	private static String getArtistId(String json){
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
