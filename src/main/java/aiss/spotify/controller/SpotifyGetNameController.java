package aiss.spotify.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.restlet.resource.ClientResource;

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
			List<String> id = new ArrayList<String>();
			for (String artist : artist_split) {
				String uri_artist = SpotifyResource.getArtistURI(artist);
				ClientResource cr_ar = new ClientResource(uri_artist);
				String json_artist = cr_ar.get(String.class);
				id.add(SpotifyResource.getArtistId(json_artist));
			}
			
			String uri_search = SpotifyResource.getRecommendationsURI(id, danceability, energy, tempo, valence);
			ClientResource cr_sr = new ClientResource(uri_search);
			String json_song = cr_sr.get(String.class);
			String song = SpotifyResource.getSearch(json_song);
			request.setAttribute("song", song);
			request.getRequestDispatcher("/.jsp").forward(request, response);
			
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}
}
