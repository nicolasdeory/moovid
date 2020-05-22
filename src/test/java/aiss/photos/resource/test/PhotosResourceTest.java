package aiss.photos.resource.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import aiss.model.photos.filter.Date;
import aiss.model.photos.mediaitem.MediaItem;
import aiss.model.photos.mediaitem.MediaItems;

public class PhotosResourceTest {
	
	private static Logger log = Logger.getLogger(PhotosResourceTest.class.getName());

	//Entra un String con una o varias fechas, se pasa a una lista de fechas
	public void ParseoFechasTest() throws Exception{
        String fechasstr = "14-11-2010/26-09-2013/07-03-2009";
        List<Date> fechas = ParseoFechas(fechasstr);
		assertTrue("No se ha parseado la primera fecha correctamente",fechas.get(0).getDay()==14);
		assertTrue("No se ha parseado la segunda fecha correctamente",fechas.get(1).getMonth()==9);
		assertTrue("No se ha parseado la tercera fecha correctamente",fechas.get(2).getYear()==2009);
	}
	
	//Parsea una única fecha
	public void ParseoFechaTest() throws Exception{ 
		String fechastr = "20-03-2015";
		Date fecha = ParseoFecha(fechastr);
		assertTrue("No se ha parseado la fecha correctamente", fecha.getDay()==20 && fecha.getMonth()==3 && fecha.getYear()==2015);
	}
	
	//Esta funcion parsea un string a una Lista de contenidos (tambien en String), en esta prueba le pasamos una lista de contenidos vacia
	public void ParseoContenidosVacioTest() throws Exception{ 
		String contenidovacio = "";
		List<String> cont = ParseoContenidos(contenidovacio);
		assertFalse("La lista que se ha creado tiene elementos invalidos", cont.get(0).length()>2);
	}
	
	public void ParseoContenidosTest() throws Exception{
		String contenidosstr = "selfies-landscapes-documents";
		List<String> contenidos = ParseoContenidos(contenidosstr);
		assertEquals(contenidos.get(0),"selfies");
		assertEquals(contenidos.get(1),"landscapes");
		assertEquals(contenidos.get(2),"documents");
	}
	
	
	
	
	
	
	
	//FUNCIONES COPIADAS DEL CONTROLADOR, NO SON ACCESIBLES
	
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
    
	
}
