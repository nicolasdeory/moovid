package aiss.spotify.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SpotifyGetNameController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	 public SpotifyGetNameController() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			// TODO: Read request parameters
			String artists = request.getParameter("artists");
			String danceability = request.getParameter("danceability");
			String energy = request.getParameter("energy");
			String instrumentalness = request.getParameter("instrumentalness");
			String tempo = request.getParameter("tempo");
			String valence = request.getParameter("valence");
			
			// TODO: Create contact in the repository
			ContactRepository contacts = ContactRepository.getInstance();
			contacts.addContact(name, phone);

			// TODO: Forward to contact list view
			request.setAttribute("message", "Contact created successfully");
			request.setAttribute("contacts", contacts);
			request.getRequestDispatcher("/contactListView.jsp").forward(request, response);
			
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}
}
