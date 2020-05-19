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
    	List<ContentCategory> contenidos = ParseoContenidos(contentsstr);
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

    private Date ParseoFecha(String fechaStr) {
    	String[] splits = fechaStr.split("-");
    	Date res = new Date(Integer.valueOf(splits[0]),Integer.valueOf(splits[1]),Integer.valueOf(splits[2]));
    	return res;
    }
    
    private List<ContentCategory> ParseoContenidos(String contenidosStr){
    	List<ContentCategory> res = new ArrayList<ContentCategory>();
    	if(contenidosStr.contains("none")) res.add(ContentCategory.NONE);
		if(contenidosStr.contains("landscapes")) res.add(ContentCategory.LANDSCAPES);
		if(contenidosStr.contains("receipts")) res.add(ContentCategory.RECEIPTS);
		if(contenidosStr.contains("cityscapes")) res.add(ContentCategory.CITYSCAPES);
		if(contenidosStr.contains("landmarks")) res.add(ContentCategory.LANDMARKS);
		if(contenidosStr.contains("selfies")) res.add(ContentCategory.SELFIES);
		if(contenidosStr.contains("people")) res.add(ContentCategory.PEOPLE);
		if(contenidosStr.contains("pets")) res.add(ContentCategory.PETS);
		if(contenidosStr.contains("weddings")) res.add(ContentCategory.WEDDINGS);
		if(contenidosStr.contains("birthdays")) res.add(ContentCategory.BIRTHDAYS);
		if(contenidosStr.contains("documents")) res.add(ContentCategory.DOCUMENTS);
		if(contenidosStr.contains("travel")) res.add(ContentCategory.TRAVEL);
		if(contenidosStr.contains("animals")) res.add(ContentCategory.ANIMALS);
		if(contenidosStr.contains("food")) res.add(ContentCategory.FOOD);
		if(contenidosStr.contains("sport")) res.add(ContentCategory.SPORT);
		if(contenidosStr.contains("night")) res.add(ContentCategory.NIGHT);
		if(contenidosStr.contains("performances")) res.add(ContentCategory.PERFORMANCES);
		if(contenidosStr.contains("whiteboards")) res.add(ContentCategory.WHITEBOARDS);
		if(contenidosStr.contains("screenshots")) res.add(ContentCategory.SCREENSHOTS);
		if(contenidosStr.contains("utility")) res.add(ContentCategory.UTILITY);
		if(contenidosStr.contains("arts")) res.add(ContentCategory.ARTS);
		if(contenidosStr.contains("crafts")) res.add(ContentCategory.CRAFTS);
		if(contenidosStr.contains("fashion")) res.add(ContentCategory.FASHION);
		if(contenidosStr.contains("houses")) res.add(ContentCategory.HOUSES);
		if(contenidosStr.contains("gardens")) res.add(ContentCategory.GARDENS);
		if(contenidosStr.contains("flowers")) res.add(ContentCategory.FLOWERS);
		if(contenidosStr.contains("holidays")) res.add(ContentCategory.HOLIDAYS);
    	return res;
    }
    
    
}
