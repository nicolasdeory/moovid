package aiss.controller.photos;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.photos.mediaitem.MediaItems;
import aiss.resources.photos.MediaItemResource;

public class GooglePhotosMediaItemListController extends HttpServlet{
	
	private static final Logger log = Logger.getLogger(GooglePhotosMediaItemGetController.class.getName());

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    	String accessToken = (String) req.getSession().getAttribute("GooglePhotos-token");
    	log.info("token is: " + accessToken);
    	if (accessToken != null && !"".equals(accessToken)) {
    		MediaItemResource miResource = new MediaItemResource(accessToken);
    		MediaItems MIs = miResource.getMediaItems();
            if(MIs!=null) {
                log.info("Files obtained");
                req.setAttribute("cajademo", MIs);
                req.getRequestDispatcher("/demo.jsp").forward(req, resp);
            }else {
                log.info("Files could not be found!");
                req.getRequestDispatcher("/AuthController/GooglePhotos").forward(req, resp);
            }
                
        } else {
            log.info("Trying to access Google Photos without an access token, redirecting to OAuth servlet");
            req.getRequestDispatcher("/AuthController/GooglePhotos").forward(req, resp);
        }
   }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doGet(req, resp);
    }
}
