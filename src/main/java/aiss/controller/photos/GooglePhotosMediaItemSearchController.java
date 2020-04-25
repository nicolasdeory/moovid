package aiss.controller.photos;

import java.io.IOException;
import java.util.Collection;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.photos.mediaitem.MediaItem;
import aiss.model.resources.photos.MediaItemResource;
import aiss.model.photos.filter.*;

public class GooglePhotosMediaItemSearchController extends HttpServlet{
	
	private static final Logger log = Logger.getLogger(GooglePhotosMediaItemGetController.class.getName());

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Filters[] flts = req.getParameter("filters");
        if (flts != null && !"".equals(flts)) {
            String accessToken = (String) req.getSession().getAttribute("GooglePhotos-token");
            if (accessToken != null && !"".equals(accessToken)) {
                MediaItemResource miResource = new MediaItemResource(accessToken);
                Collection<MediaItem> MIs = miResource.searchMediaItem(flts);
                if(MIs!=null) {
                	log.info("Files according to filters obtained");
                    req.setAttribute("MediaItems", MIs);
                }else {
                	log.info("No files could be found with those filters");
                	req.getRequestDispatcher("/AuthController/GooglePhotos").forward(req, resp);
                }
                
            } else {
                log.info("Trying to access Google Photos without an access token, redirecting to OAuth servlet");
                req.getRequestDispatcher("/AuthController/GooglePhotos").forward(req, resp);
            }
        } else {
            log.warning("Invalid filters!");
            req.getRequestDispatcher("/googlePhotosFileList").forward(req, resp);
        }
    }
}
