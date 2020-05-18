package aiss.controller.main;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
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
		String query = request.getParameter("q");
		Intent intent = LuisResource.getIntentFromQuery(query);
		Context sessionContext = (Context)request.getSession().getAttribute("context");
		// Discard context if it's too old (more than 10 minutes)
		if (sessionContext != null &&
				LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) - sessionContext.getTimestamp() > 60*10)
			sessionContext = null;
		
		sessionContext = sessionContext == null ? new Context() : sessionContext;
		ChatQueryResponse chatResponse = IntentHandlerFactory.fromIntent(intent, sessionContext);
		sessionContext = chatResponse.getContext();
		request.getSession().setAttribute("context", sessionContext);
		// Generate chat response
		// TODO: sesion context attach is logged in if cookie is present
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
