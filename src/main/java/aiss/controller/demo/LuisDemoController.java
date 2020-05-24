package aiss.controller.demo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.restlet.ext.jackson.JacksonRepresentation;

import aiss.model.luis.classes.Intent;
import aiss.resources.luis.LuisResource;

/**
 * Servlet implementation class LUISDemoController
 */
public class LuisDemoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LuisDemoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Intent in = LuisResource.getIntentFromQuery("Hazme%20un%20montage%20"
				+ "con%20fotos%20de%20gatos");
		String json = new JacksonRepresentation<Intent>(in).getText();
		response.setContentType("application/json");
		response.getWriter().append(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}