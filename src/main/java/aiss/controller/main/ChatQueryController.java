package aiss.controller.main;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.restlet.data.CharacterSet;
import org.restlet.ext.jackson.JacksonRepresentation;

import aiss.controller.demo.SpotifyDemo;
import aiss.conversation.Context;
import aiss.conversation.ChatResponseFactory;
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
		System.out.println("got intent " + intent.toString());
		Context sessionContext = (Context)request.getSession().getAttribute("context");
		// Discard context if it's too old (more than 10 minutes)
		if (sessionContext != null &&
				LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) - sessionContext.getTimestamp() > 60*10)
			sessionContext = null;
		
		sessionContext = sessionContext == null ? new Context() : sessionContext;
		String accessToken = "";
		String logParam = request.getParameter("login");
		//System.out.println("log param = " + logParam);
		if (logParam != null && logParam.equals("1"))
		{
			accessToken = (String) request.getSession().getAttribute("GooglePhotos-token");
			
		}
		else
		{
			accessToken = null;
		}
		
		sessionContext.setAccessToken(accessToken);
		sessionContext.setLoggedIn(accessToken != null && !"".equals(accessToken));
		System.out.println(intent);
		ChatQueryResponse chatResponse = ChatResponseFactory.fromIntent(intent, sessionContext);
		chatResponse.setQuery(query);
		sessionContext = chatResponse.getContext();
		request.getSession().setAttribute("context", sessionContext);
		
		// Generate chat response
		// TODO: sesion context attach is logged in if cookie is present
		JacksonRepresentation<ChatQueryResponse> chatqRepr = new JacksonRepresentation<ChatQueryResponse>(chatResponse);
		chatqRepr.setCharacterSet(CharacterSet.UTF_8);
		String chatJson = chatqRepr.getText();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		System.out.println(chatJson);
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
