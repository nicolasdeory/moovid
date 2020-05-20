package aiss.controller.main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.photos.mediaitem.MediaItem;
import aiss.resources.photos.MediaItemResource;

public class MainServlet extends HttpServlet { // UNUSED
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(MainServlet.class.getName());
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		 req.getRequestDispatcher("mashup.html").forward(req, resp);
    }
	
	
}
