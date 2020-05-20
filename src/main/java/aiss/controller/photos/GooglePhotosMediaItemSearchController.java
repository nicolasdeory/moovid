package aiss.controller.photos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.photos.filter.ContentCategory;
import aiss.model.photos.filter.Date;
import aiss.model.photos.mediaitem.MediaItems;
import aiss.resources.photos.MediaItemResource;

public class GooglePhotosMediaItemSearchController extends HttpServlet{
	
	private static final Logger log = Logger.getLogger(GooglePhotosMediaItemSearchController.class.getName());

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    	String iniciostr = req.getParameter("inicio");
    	String finstr = req.getParameter("fin");
    	String contentsstr = req.getParameter("contents");
    	Date inicio = ParseoFecha(iniciostr);
    	Date fin = ParseoFecha(finstr);
    	List<String> contenidos = ParseoContenidos(contentsstr);
        if (inicio != null && fin != null && contenidos != null) {
            String accessToken = (String) req.getSession().getAttribute("GooglePhotos-token");
            if (accessToken != null && !"".equals(accessToken)) {
                MediaItemResource miResource = new MediaItemResource(accessToken);
                MediaItems MIs = miResource.searchMediaItem(inicio,fin,contenidos);
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
            req.getRequestDispatcher("/pruebagoogle").forward(req, resp); //la url puede modificarse
        }
    }

    private static Date ParseoFecha(String fechaStr) {
    	String[] splits = fechaStr.split("-");
    	Date res = new Date(Integer.valueOf(splits[0]),Integer.valueOf(splits[1]),Integer.valueOf(splits[2]));
    	return res;
    }
    
    private static List<String> ParseoContenidos(String contenidosStr){
    	List<String> res = new ArrayList<String>();
    	String[] splits = contenidosStr.split("-");
    	for(String s: splits) {
    		res.add(s);
    	}
    	return res;
    }
    
    
}
