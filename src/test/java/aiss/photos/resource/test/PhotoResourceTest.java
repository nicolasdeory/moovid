package aiss.photos.resource.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.junit.Test;

import aiss.model.photos.filter.ContentFilter;
import aiss.model.photos.filter.Date;
import aiss.model.photos.filter.DateFilter;
import aiss.model.photos.filter.EndDate;
import aiss.model.photos.filter.FeatureFilter;
import aiss.model.photos.filter.Filters;
import aiss.model.photos.filter.MediaTypeFilter;
import aiss.model.photos.filter.Range;
import aiss.model.photos.filter.StartDate;

public class PhotoResourceTest {
	
	//Pasa los parametros fechas y contenidos a un String que representa un filtro
	@Test
	public void GeneradorDeStringFiltroTest() throws Exception{
		String fechasstr = "20-01-2010";
		String iniciostr = "04-06-2011";
		String finstr = "06-08-2011";
		String contenidosstr = "selfies-documents";
		String excluidosstr = "animals-food";
		List<Date> fechas = ParseoFechas(fechasstr);
		Date inicio = ParseoFecha(iniciostr);
		Date fin = ParseoFecha(finstr);
		List<String> contenidos = ParseoContenidos(contenidosstr);
		List<String> excluidos = ParseoContenidos(excluidosstr);
		String fltsstr = generadorDeStringFiltro(fechas,inicio,fin,contenidos,excluidos);
		assertEquals(fltsstr,"dates:20/1/2010;ranges:4/6/2011-6/8/2011,included:selfies-documents;excluded:animals-food,mediatypes:PHOTO,features:NONE,true,false");
	}
	
	//Recoge el filtro en formato String y lo pasa al tipo Filtro
	@Test
	public void GeneradorDeFiltro() throws Exception{
		String fechasstr = "30-04-2017";
		String iniciostr = "14-01-2018";
		String finstr = "25-06-2018";
		String contenidosstr = "landscapes-cityscapes";
		String excluidosstr = "animals-food";
		List<Date> fechas = ParseoFechas(fechasstr);
		Date inicio = ParseoFecha(iniciostr);
		Date fin = ParseoFecha(finstr);
		List<String> contenidos = ParseoContenidos(contenidosstr);
		List<String> excluidos = ParseoContenidos(excluidosstr);
		String fltsstr = generadorDeStringFiltro(fechas,inicio,fin,contenidos,excluidos);
		Filters f = generadorDeFiltro(fltsstr);
		assertTrue("Fecha mal parseada",f.getDateFilter().getDates().get(0).getDay()==30);
		assertTrue("Rango mal parseado",f.getDateFilter().getRanges().get(0).getStartDate().getMonth()==1 && f.getDateFilter().getRanges().get(0).getEndDate().getYear()==2018);
		assertTrue("Included mal parseado", f.getContentFilter().getIncludedContentCategories().get(0).equals("landscapes"));
		assertTrue("Excluded mal parseado", f.getContentFilter().getExcludedContentCategories().get(1).equals("food"));
		assertTrue("Filtro mal generado",f.getMediaTypeFilter().getMediaTypes().get(0).equals("PHOTO") && f.getFeatureFilter().getIncludedFeatures().get(0).equals("NONE"));
	}
	
	public void GeneradorDeFiltroVacio() throws Exception{
        String fechasstr = "";
        String iniciostr = "";
        String finstr = "";
        String contenidosstr = "";
        String excluidosstr = "";
        List<Date> fechas = ParseoFechas(fechasstr);
        Date inicio = ParseoFecha(iniciostr);
        Date fin = ParseoFecha(finstr);
        List<String> contenidos = ParseoContenidos(contenidosstr);
        List<String> excluidos = ParseoContenidos(excluidosstr);
        String fltsstr = generadorDeStringFiltro(fechas,inicio,fin,contenidos,excluidos);
        Filters f = generadorDeFiltro(fltsstr);
        assertTrue("Filtro mal generado",f.getDateFilter().getDates().size()==0 && f.getDateFilter().getRanges().size()==0 && f.getContentFilter().getIncludedContentCategories().size()==0);
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
    
    //PARSEO DE ELEMENTOS A UN STRING REPRESENTANDO EL FILTRO, EXTRACTO DE SEAERCHMEDIAITEMS
    
    private static String generadorDeStringFiltro(List<Date> fechas, Date inicio, Date fin, List<String> temas, List<String> excluidos) {
		// TODO Auto-generated method stub
    	String filtrostr = "";
    	String DateFilter = "dates:";
    	if(fechas.size()==0) DateFilter = DateFilter + "  ";
		for(Date d : fechas) {
			DateFilter = DateFilter + d.getDay() + "/" + d.getMonth() + "/" + d.getYear() + "|";
		}
		DateFilter = DateFilter.substring(0, DateFilter.length()-1);
		if(inicio.getYear()==0) {
			DateFilter = DateFilter + ";ranges: ,";
		}else {
			DateFilter = DateFilter + ";ranges:" + inicio.getDay() + "/" + inicio.getMonth() + "/" + inicio.getYear() +
				"-" + fin.getDay() + "/" + fin.getMonth() + "/" + fin.getYear() + ",";
		}
		String ContentFilter = "included:";
		for(String s : temas) {
				if(s.length()<3) {
					ContentFilter = ContentFilter + "  ";
				}else {
					ContentFilter = ContentFilter + s + "-";
				}
		}
		ContentFilter = ContentFilter.substring(0, ContentFilter.length()-1);
		ContentFilter = ContentFilter + ";excluded:";
		for(String s : excluidos) {
			if(s.length()<3) {
				ContentFilter = ContentFilter + "  ";
			}else {
				ContentFilter = ContentFilter + s + "-";
			}
		}
		ContentFilter = ContentFilter.substring(0, ContentFilter.length()-1);
		ContentFilter = ContentFilter + ",";
		String MediaTypeFilter = "mediatypes:PHOTO,";
		String FeatureFilter = "features:NONE,";
		filtrostr = DateFilter + ContentFilter + MediaTypeFilter + FeatureFilter + "true,false";
		return filtrostr;
	}
    
    public static Filters generadorDeFiltro(String fltsstr) {
		// Separacion de parametros necesarios
		String[] parametrosFilters = fltsstr.split(",");
		String[] parametrosDateFilter = parametrosFilters[0].split(";");
		String[] parametrosContentFilter = parametrosFilters[1].split(";");
		String parametrosMediaTypeFilter = parametrosFilters[2].trim();
		String parametrosFeatureFilter = parametrosFilters[3].trim();
		String includeArchivedMediastr = parametrosFilters[4].trim();
		String excludeNonAppCreatedDatastr = parametrosFilters[5].trim();
		//Creacion de DateFilter (consta de 2 parametros, dates y ranges)
		String datess = parametrosDateFilter[0].split(":")[1];
		String[] datesstr = {""};
		if(!datess.equals(" ") && !datess.contains("|")) datesstr[0] = datess;
		else if(!datess.equals(" ")) datesstr = datess.split("|");		
		String dateRangess = parametrosDateFilter[1].split(":")[1];
		String[] dateRangesstr = {""};
		if(!dateRangess.equals(" ") && !dateRangess.contains("|")) dateRangesstr[0] = dateRangess;
		else if(!dateRangess.equals(" ")) dateRangesstr = dateRangess.split("|");

		List<Date> datesList = new ArrayList<Date>();
		List<Range> dateRangesList= new ArrayList<Range>();
		if(!datess.equals(" ")) {
			for(String datestr:datesstr) {
				String splits[] = datestr.split("/");
				Integer day = Integer.valueOf(splits[0].trim());
				Integer month = Integer.valueOf(splits[1].trim());
				Integer year = Integer.valueOf(splits[2].trim());
				Date date = new Date(day,month,year);
				datesList.add(date);
			}
		}
		if(!dateRangess.equals(" ")) {
		for(String dateRangestr:dateRangesstr) {
				String[] dates = dateRangestr.split("-");
				String[] date1splits = dates[0].split("/");
				String[] date2str = dates[1].split("/");
				Integer day1 = Integer.valueOf(date1splits[0].trim());
				Integer month1 = Integer.valueOf(date1splits[1].trim());
				Integer year1 = Integer.valueOf(date1splits[2].trim());
				Integer day2 = Integer.valueOf(date2str[0].trim());
				Integer month2 = Integer.valueOf(date2str[1].trim());
				Integer year2 = Integer.valueOf(date2str[2].trim());
				StartDate date1 = new StartDate(day1,month1,year1);
				EndDate date2 = new EndDate(day2,month2,year2);
				Range dateRange = new Range(date1,date2);
				dateRangesList.add(dateRange);
			}
		}
		DateFilter dateFilter = new DateFilter(datesList,dateRangesList);
		
		//Creacion de ContentFilter (consta de 2 parametros, included y excluded)
		String includedstr = parametrosContentFilter[0].split(":")[1].trim();
		String excludedstr = parametrosContentFilter[1].split(":")[1].trim();
		List<String> includedList = new ArrayList<String>();
		List<String> excludedList = new ArrayList<String>();
		if(!includedstr.contains("-") && includedstr.length()>1) includedList.add(includedstr);
		else {
			String[] splits = includedstr.split("-");
			for(String s:splits) {
				if(s.equals(" ") || s.equals("")) continue;
				includedList.add(s);
			}
		}
		if(!excludedstr.contains("-") && excludedstr.length()>1) excludedList.add(excludedstr);
		else {
			String[] splits = excludedstr.split("-");
			for(String s:splits) {
				if(s.equals(" ") || s.equals("")) continue;
				excludedList.add(s);
			}
		}
		ContentFilter contentFilter = new ContentFilter(includedList,excludedList);
		
		//Creacion de MediaTypeFilter (solo 1 parametro, mediatypes)
		String elementos_mediatypes = parametrosMediaTypeFilter.split(":")[1].trim();
		List<String> mediatypesList = new ArrayList<String>();
		if(elementos_mediatypes.contains("ALLMEDIA")) mediatypesList.add("ALLMEDIA");
		else if(elementos_mediatypes.contains("VIDEO")) mediatypesList.add("VIDEO");
		else if(elementos_mediatypes.contains("PHOTO")) mediatypesList.add("PHOTO");
		MediaTypeFilter mediaTypeFilter = new MediaTypeFilter(mediatypesList);
		
		//Creacion de FeatureFilter (solo 1 parametro, features)
		String elementos_features = parametrosFeatureFilter.split(":")[1];
		List<String> featuresList = new ArrayList<String>();
		if(elementos_features.contains("NONE")) featuresList.add("NONE");
		if(elementos_features.contains("FAVORITES")) featuresList.add("FAVORITES");
		FeatureFilter featureFilter = new FeatureFilter(featuresList);
		
		//Creacion de includeArchivedMedia 
		Boolean includeArchivedMedia = false;
		if(includeArchivedMediastr.equals("true")) includeArchivedMedia = true;
		
		//Creacion de excludeNonAppCreatedData
		Boolean excludeNonAppCreatedData = false;
		if(excludeNonAppCreatedDatastr.equals("true")) excludeNonAppCreatedData = true;
		
		//Creacion de Filters
		Filters filter = new Filters(contentFilter,dateFilter,featureFilter,mediaTypeFilter,
				excludeNonAppCreatedData,includeArchivedMedia);
		return filter;
	}

}
