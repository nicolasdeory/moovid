package aiss.spotify.resource;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import aiss.spotify.model.Artist;
import aiss.spotify.model.Song;

public class SpotifyResource {
	
	public static Song getSong(String json) {
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
	
	public static Artist getArtist(String json){
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
}
