package aiss.resources.photos;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.photos.filter.ContentCategory;
import aiss.model.photos.filter.ContentFilter;
import aiss.model.photos.filter.Date;
import aiss.model.photos.filter.DateFilter;
import aiss.model.photos.filter.EndDate;
import aiss.model.photos.filter.Feature;
import aiss.model.photos.filter.FeatureFilter;
import aiss.model.photos.filter.Filters;
import aiss.model.photos.filter.MediaType;
import aiss.model.photos.filter.MediaTypeFilter;
import aiss.model.photos.filter.Range;
import aiss.model.photos.filter.StartDate;
import aiss.model.photos.mediaitem.MediaItem;
import aiss.model.photos.mediaitem.MediaItems;
import aiss.model.photos.mediaitem.NewMediaItem;
import aiss.model.photos.mediaitem.NewMediaItemResult;
import aiss.model.photos.mediaitem.SimpleMediaItem;


public class MediaItemResource {
	
	private static final Logger log = Logger.getLogger(MediaItemResource.class.getName());

	private final String access_token;
	private static String uri = "https://photoslibrary.googleapis.com/v1/mediaItems";
	
	public MediaItemResource(String access_token) {
		super();
		this.access_token = access_token;
	}
	
	public MediaItems getMediaItems() {
        ClientResource cr = null;
        MediaItems mediaItems = null;
        try {
            cr = new ClientResource(uri + "?access_token=" + access_token);
            String result = cr.get(String.class);
            mediaItems = cr.get(MediaItems.class);

        } catch (ResourceException re) {
            System.out.println("Error when retrieving all files: " + cr.getResponse().getStatus());
        }

        return mediaItems;

    }
	
	public String getMediaItemsString() {
        ClientResource cr = null;
        MediaItems mediaItems = null;
        String result = null;
        try {
            cr = new ClientResource(uri + "?access_token=" + access_token);
            result = cr.get(String.class);
            mediaItems = cr.get(MediaItems.class);

        } catch (ResourceException re) {
            System.out.println("Error when retrieving all files: " + cr.getResponse().getStatus());
        }

        return result;

    }

	public  MediaItem getMediaItem(String mediaItemId){
		ClientResource cr = null;
		MediaItem list = null;
		try {
			cr = new ClientResource(uri + "/" + mediaItemId + "?access_token=" + access_token);
			list = cr.get(MediaItem.class);
		} catch (ResourceException re){
			System.out.println("Error when retrieving the media item: " + cr.getResponse().getStatus());
		}
		return list;
	}

	
	//CAMBAIR ESTO PARA QUE ENTREN DOS DATES Y LA LISTA DE MONTAGETHEME
	public  MediaItems searchMediaItem(Date inicio, Date fin, List<String> temas){
		ClientResource cr = null;
		MediaItems list = null;
		try {
			String filtrostr = "";
			String DateFilter = "dates: ;ranges:" + inicio.getDay() + "/" + inicio.getMonth() + "/" + inicio.getYear() +
					"-" + fin.getDay() + "/" + fin.getMonth() + "/" + fin.getYear() + ",";
			String ContentFilter = "included:";
			if(temas.equals(null) || temas.size()==0) {
				ContentFilter= ContentFilter + "NONE-LANDSCAPES-RECEIPTS-CITYSCAPES-LANDMARKS-SELFIES-PEOPLE-PETS-WEDDINGS-"
						+ "BIRTHDAYS-DOCUMENTS-TRAVEL-ANIMALS-FOOD-SPORT-NIGHT-PERFORMANCES-WHITEBOARDS-SCREENSHOTS"
						+ "-UTILITY-ARTS-CRAFTS-FASHION-HOUSES-GARDENS-FLOWERS-HOLIDAYS-";
			}else {
				for(String s : temas) {
					ContentFilter = ContentFilter + s + "-";
				}
			}
			ContentFilter = ContentFilter.substring(0, ContentFilter.length()-1);
			ContentFilter = ContentFilter + ";excluded: ,";
			String MediaTypeFilter = "mediatypes:PHOTO,";
			String FeatureFilter = "features:NONE-FAVORITES,";
			filtrostr = DateFilter + ContentFilter + MediaTypeFilter + FeatureFilter + "true,false";
			Filters filters = generadorDeFiltro(filtrostr);
			System.out.println("INFORMACION: El filtro que se forma es: " + filters);
			cr = new ClientResource(uri + ":search" + "?access_token=" + access_token);
			list = cr.post(filters, MediaItems.class);
			System.out.println("INFORMACION: La lista devuelta es: " + list );
		} catch (ResourceException re){
			System.out.println("INFORMACION: Error when retrieving the collections: " + re);
		}
		return list;
	}
	
	public  NewMediaItemResult createMediaItem(InputStream ficheromedia, String nombreMontaje){
		//2 pasos, primero subir el archivo a Google Servers, segundo colocarlo en Google Photos
		ClientResource cr1 = null;
		ClientResource cr2 = null;
		NewMediaItemResult resultMediaItem = null;
		try {
			//Subir el archivo a Google Servers (Se obtiene uploadToken)
			cr1 = new ClientResource("https://photoslibrary.googleapis.com/v1/uploads" + "?access_token=" + access_token);
			cr1.setEntityBuffering(true);
			String uploadToken = cr1.post(ficheromedia,String.class);
			//Meter el archivo en Google Photos
			cr2 = new ClientResource(uri + ":batchCreate" + "?access_token=" + access_token);
			cr2.setEntityBuffering(true);
			SimpleMediaItem SMI = new SimpleMediaItem(uploadToken,nombreMontaje);
			NewMediaItem NMI = new NewMediaItem(SMI);
			resultMediaItem = cr2.post(NMI, NewMediaItemResult.class);
		} catch (ResourceException re){
			System.out.println("Error when creating the MediaItem");
		}
		return resultMediaItem;
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
				String splits[] = datestr.split(".");
				Integer day = Integer.valueOf(splits[0].trim());
				Integer month = Integer.valueOf(splits[1].trim());
				Integer year = Integer.valueOf(splits[2].trim());
				Date date = new Date(year,month,day);
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
				StartDate date1 = new StartDate(year1,month1,day1);
				EndDate date2 = new EndDate(year2,month2,day2);
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
				includedList.add(s);
			}
		}
		if(!excludedstr.contains("-") && excludedstr.length()>1) excludedList.add(excludedstr);
		else {
			String[] splits = excludedstr.split("-");
			for(String s:splits) {
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