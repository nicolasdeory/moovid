package aiss.controller.spotify;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SpotifyGetSongsController extends HttpServlet{

	private static final Logger log = Logger.getLogger(SpotifyGetSongsController.class.getName());
	private static final long serialVersionUID = 1L;
	 public SpotifyGetSongsController() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			/*String artists = request.getParameter("artists");
			String danceability = request.getParameter("danceability");
			String energy = request.getParameter("energy");
			String instrumentalness = request.getParameter("instrumentalness");
			String tempo = request.getParameter("tempo");
			String valence = request.getParameter("valence");
			List<Artist> id = SpotifyResource.getArtistIds(artists);
			String json_song = SpotifyResource.getRecommendations(id, danceability,
					energy, instrumentalness, tempo, valence);
			List<Song> songs = SpotifyResource.getSongsFromJson(json_song);
			request.setAttribute("songs", songs.toString());
			log.log(Level.INFO, "Forwarding recommended songs: " + songs);
			request.getRequestDispatcher("/.jsp").forward(request, response);*/
			
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}
}
