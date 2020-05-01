package aiss.spotify.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.restlet.resource.ClientResource;

import aiss.spotify.model.Artist;
import aiss.spotify.model.Song;
import aiss.spotify.resource.SpotifyResource;


public class SpotifyGetNameController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	 public SpotifyGetNameController() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			String artists = request.getParameter("artists");
			String danceability = request.getParameter("danceability");
			String energy = request.getParameter("energy");
			String tempo = request.getParameter("tempo");
			String valence = request.getParameter("valence");
			String[] artist_split = artists.split(",");
			List<Artist> id = new ArrayList<Artist>();
			for (String artist : artist_split) {
				String uri_artist = "https://api.spotify.com/v1/search?q=";
				uri_artist += artist + "&type=artist";
				ClientResource cr_ar = new ClientResource(uri_artist);
				String json_artist = cr_ar.get(String.class);
				id.add(SpotifyResource.getArtistFromJson(json_artist));
			}
			String uri_search = "https://api.spotify.com/v1/recommendations?";
			uri_search += "limit=1";
			uri_search += "&seed_artists=";
			for (Artist artist : id)
				uri_search += artist.getId() + ",";
			uri_search = uri_search.substring(0, uri_search.length() - 1);
			//TODO: dejarlo en el resource como antes
			if (danceability.equals("true"))
				uri_search += "&target_danceability=0.9";
			if (energy.equals("highenergy"))
				uri_search += "&target_energy=0.8";
			if (energy.equals("lowenergy"))
				uri_search += "&target_energy=0.2";
			if (tempo.equals("fastpaced"))
				uri_search += "&target_tempo=0.8";
			if (tempo.equals("slowpaced"))
				uri_search += "&target_tempo=0.2";
			if (valence.equals("happy"))
				uri_search += "&target_valence=0.8";
			if (valence.equals("sad"))
				uri_search += "&target_valence=0.2";
			ClientResource cr_sr = new ClientResource(uri_search);
			String json_song = cr_sr.get(String.class);
			Song song = SpotifyResource.getSongFromJson(json_song);
			request.setAttribute("song", song.toString());
			request.getRequestDispatcher("/.jsp").forward(request, response);
			
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}
}
