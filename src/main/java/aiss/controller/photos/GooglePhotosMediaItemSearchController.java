package aiss.controller.photos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.photos.filter.Date;
import aiss.model.photos.mediaitem.MediaItem;
import aiss.model.photos.mediaitem.MediaItems;
import aiss.resources.photos.MediaItemResource;

public class GooglePhotosMediaItemSearchController extends HttpServlet{
	
	private static final Logger log = Logger.getLogger(GooglePhotosMediaItemSearchController.class.getName());

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    	String fechasstr = req.getParameter("fechas");
    	String iniciostr = req.getParameter("inicio");
    	String finstr = req.getParameter("fin");
    	String contentsstr = req.getParameter("contents");
    	String excluidosstr = req.getParameter("excluded");
    	List<Date> fechas = ParseoFechas(fechasstr);
    	Date inicio = ParseoFecha(iniciostr);
    	Date fin = ParseoFecha(finstr);
    	List<String> contenidos = ParseoContenidos(contentsstr);
    	List<String> excluidos = ParseoContenidos(excluidosstr);
        if (inicio != null && fin != null && contenidos != null) {
            String accessToken = (String) req.getSession().getAttribute("GooglePhotos-token");
            if (accessToken != null && !"".equals(accessToken)) {
                MediaItemResource miResource = new MediaItemResource(accessToken);
                MediaItems MIs = miResource.searchMediaItem(fechas,inicio,fin,contenidos,excluidos);
                List<String> listaUrls = obtenerURLSDeBajada(MIs);
                if(MIs!=null) {
                	log.info("Files according to filters obtained");
                    req.setAttribute("MediaItems", listaUrls);
                    req.getRequestDispatcher("/demo.jsp").forward(req, resp);
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

    public static List<Date> ParseoFechas(String fechasstr) {
		// TODO Auto-generated method stub
    	List<Date> res = new ArrayList<Date>();
    	if(fechasstr.length()<3) return res;
		String[] diferentesFechas = fechasstr.split("/");
		for(String s:diferentesFechas) {
			String[] splits = s.split("-");
			Date fecha = new Date(Integer.valueOf(splits[0]),Integer.valueOf(splits[1]),Integer.valueOf(splits[2]));
			res.add(fecha);
		}
		return res;
	}

    public static Date ParseoFecha(String fechaStr) {
		Date res = new Date (0,0,0);
		if(fechaStr.length()<3) return res;
    	String[] splits = fechaStr.split("-");
    	res = new Date(Integer.valueOf(splits[0]),Integer.valueOf(splits[1]),Integer.valueOf(splits[2]));
    	return res;
    }
    
    public static List<String> ParseoContenidos(String contenidosStr){
    	List<String> res = new ArrayList<String>();
    	String[] splits = contenidosStr.split("-");
    	for(String s: splits) {
    		res.add(s);
    	}
    	return res;
    }
    
    public List<String> obtenerURLSDeBajada(MediaItems MIs){
		List<String> ls = new ArrayList<String>();
		if(MIs==null) return ls;
		for(MediaItem mi: MIs.getMediaItems()) {
			String baseUrl = mi.getBaseUrl();
			String urlFinal = baseUrl + "=w1020-h720-d";
			ls.add(urlFinal);
		}
		return ls;
	}
    
    
    
    
}
