package aiss.controller.photos;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.photos.mediaitem.NewMediaItemResult;
import aiss.resources.photos.MediaItemResource;

public class GooglePhotosMediaItemCreateController extends HttpServlet{

	private static final Logger log = Logger.getLogger(GooglePhotosMediaItemCreateController.class.getName());

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String MBDString = req.getParameter("media-binary-data");
        InputStream mediaBinaryData = new ByteArrayInputStream(MBDString.getBytes());
        if (mediaBinaryData != null && !"".equals(mediaBinaryData)) {
            String accessToken = (String) req.getSession().getAttribute("GooglePhotos-token");
            if (accessToken != null && !"".equals(accessToken)) {
                MediaItemResource miResource = new MediaItemResource(accessToken);
                NewMediaItemResult MIResult = miResource.createMediaItem(mediaBinaryData, "montajeMoovid");
                if(MIResult!=null) {
                	log.info("File created and uploaded succesfully");
                    req.setAttribute("MediaItems", MIResult);
                }else {
                	log.info("File was not created");
                	req.getRequestDispatcher("/AuthController/GooglePhotos").forward(req, resp);
                }
                
            } else {
                log.info("Trying to access Google Photos without an access token, redirecting to OAuth servlet");
                req.getRequestDispatcher("/AuthController/GooglePhotos").forward(req, resp);
            }
        } else {
            log.warning("No file to upload");
            req.getRequestDispatcher("/pruebagoogle").forward(req, resp); //la url puede modificarse
        }
    }
    
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doGet(req, resp);
	}
}
