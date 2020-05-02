package aiss.controller.demo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.restlet.ext.jackson.JacksonRepresentation;

import aiss.spotify.model.Artist;
import aiss.spotify.resource.SpotifyResource;

/**
 * Servlet implementation class SpotifyDemoController
 */
public class SpotifyDemoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SpotifyDemoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!SpotifyResource.isAuthorized())
			SpotifyResource.authorize();
		List<Artist> art = SpotifyResource.getArtistIds("Zedd,Savant");
		String json = new JacksonRepresentation<List<Artist>>(art).getText();
		response.setContentType("application/json");
		response.getWriter().append(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
