package aiss.controller.main;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.restlet.ext.jackson.JacksonRepresentation;

import aiss.controller.demo.SpotifyDemo;
import aiss.conversation.Context;
import aiss.conversation.IntentHandlerFactory;
import aiss.conversation.intenthandler.IntentHandler;
import aiss.model.luis.classes.Intent;
import aiss.resources.luis.LuisResource;

/**
 * Servlet implementation class ChatQueryController
 */
public class ChatQueryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatQueryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = request.getParameter("query");
		Intent intent = LuisResource.getIntentFromQuery(query);
		Context sessionContext = (Context)request.getSession().getAttribute("context");
		IntentHandler handler = IntentHandlerFactory.fromIntent(intent, sessionContext);
		// Generate chat response
		ChatQueryResponse chatResponse = handler.generateResponse();
		String chatJson = new JacksonRepresentation<ChatQueryResponse>(chatResponse).getText();
		response.setContentType("application/json");
		// Send chat query response
		response.getWriter().append(chatJson);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
