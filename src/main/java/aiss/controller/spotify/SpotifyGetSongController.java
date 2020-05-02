package aiss.controller.spotify;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.restlet.resource.ClientResource;

import aiss.model.spotify.Artist;
import aiss.model.spotify.Song;
import aiss.resources.spotify.SpotifyResource;


public class SpotifyGetSongController extends HttpServlet{

	private static final Logger log = Logger.getLogger(SpotifyGetSongController.class.getName());
	private static final long serialVersionUID = 1L;
	 public SpotifyGetSongController() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			String artists = request.getParameter("artists");
			String danceability = request.getParameter("danceability");
			String energy = request.getParameter("energy");
			String tempo = request.getParameter("tempo");
			String valence = request.getParameter("valence");
			List<Artist> id = SpotifyResource.getArtistIds(artists);
			String json_song = SpotifyResource.getRecommendations(id, danceability,
					energy, tempo, valence);
			Song song = SpotifyResource.getSongFromJson(json_song);
			request.setAttribute("song", song.toString());
			log.log(Level.INFO, "Forwarding recommended song: " + song);
			request.getRequestDispatcher("/.jsp").forward(request, response);
			
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}
}
