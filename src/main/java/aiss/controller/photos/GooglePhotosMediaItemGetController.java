package aiss.controller.photos;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.photos.mediaitem.MediaItem;
import aiss.model.resources.photos.MediaItemResource;

public class GooglePhotosMediaItemGetController extends HttpServlet{
	
	private static final Logger log = Logger.getLogger(GooglePhotosMediaItemGetController.class.getName());

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String id = req.getParameter("mediaItemId");
        if (id != null && !"".equals(id)) {
            String accessToken = (String) req.getSession().getAttribute("GooglePhotos-token");
            if (accessToken != null && !"".equals(accessToken)) {
                MediaItemResource miResource = new MediaItemResource(accessToken);
                MediaItem MI = miResource.getMediaItem(id);
                if(MI!=null) {
                	log.info("File with id '" + id + "' obtained");
                    req.setAttribute("MediaItem", MI);
                }else {
                	log.info("File " + id + " could not be found!");
                	req.getRequestDispatcher("/AuthController/GooglePhotos").forward(req, resp);
                }
                
            } else {
                log.info("Trying to access Google Photos without an access token, redirecting to OAuth servlet");
                req.getRequestDispatcher("/AuthController/GooglePhotos").forward(req, resp);
            }
        } else {
            log.warning("Invalid id for obtain!");
            req.getRequestDispatcher("error.html").forward(req, resp); //la url puede modificarse
        }
    }
}
