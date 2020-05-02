package aiss.controller.photos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.photos.filter.ContentCategory;
import aiss.model.photos.filter.ContentFilter;
import aiss.model.photos.filter.Date;
import aiss.model.photos.filter.DateFilter;
import aiss.model.photos.filter.DateRange;
import aiss.model.photos.filter.Feature;
import aiss.model.photos.filter.FeatureFilter;
import aiss.model.photos.filter.Filters;
import aiss.model.photos.filter.MediaType;
import aiss.model.photos.filter.MediaTypeFilter;
import aiss.model.photos.mediaitem.MediaItem;
import aiss.model.resources.photos.MediaItemResource;

public class GooglePhotosMediaItemSearchController extends HttpServlet{
	
	private static final Logger log = Logger.getLogger(GooglePhotosMediaItemSearchController.class.getName());

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    	String fltsstr = req.getParameter("filters");
        Filters flts = generadorDeFiltro(fltsstr);
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
            req.getRequestDispatcher("/pruebagoogle").forward(req, resp); //la url puede modificarse
        }
    }

	private Filters generadorDeFiltro(String fltsstr) {
		// Separacion de parametros necesarios
		String[] parametrosFilters = fltsstr.split(",");
		String[] parametrosDateFilter = parametrosFilters[0].split(";");
		String[] parametrosContentFilter = parametrosFilters[1].split(";");
		String parametrosMediaTypeFilter = parametrosFilters[2].trim();
		String parametrosFeatureFilter = parametrosFilters[3].trim();
		String includeArchivedMediastr = parametrosFilters[4].trim();
		String excludeNonAppCreatedDatastr = parametrosFilters[5].trim();
		//Creacion de DateFilter (consta de 2 parametros, dates y ranges)
		String[] datesstr = parametrosDateFilter[0].split(":")[1].split("|");
		String[] dateRangesstr = parametrosDateFilter[1].split(":")[1].split("|");
		List<Date> datesList = new ArrayList<Date>();
		List<DateRange> dateRangesList= new ArrayList<DateRange>();
		for(String datestr:datesstr) {
			String splits[] = datestr.split(".");
			Integer day = Integer.valueOf(splits[0].trim());
			Integer month = Integer.valueOf(splits[1].trim());
			Integer year = Integer.valueOf(splits[2].trim());
			Date date = new Date(year,month,day);
			datesList.add(date);
		}
		for(String dateRangestr:dateRangesstr) {
			String[] dates = dateRangestr.split("-");
			String[] date1str = dates[0].trim().split(".");
			String[] date2str = dates[1].trim().split(".");
			Integer day1 = Integer.valueOf(date1str[0].trim());
			Integer month1 = Integer.valueOf(date1str[1].trim());
			Integer year1 = Integer.valueOf(date1str[2].trim());
			Integer day2 = Integer.valueOf(date2str[0].trim());
			Integer month2 = Integer.valueOf(date2str[1].trim());
			Integer year2 = Integer.valueOf(date2str[2].trim());
			Date date1 = new Date(year1,month1,day1);
			Date date2 = new Date(year2,month2,day2);
			DateRange dateRange = new DateRange(date1,date2);
			dateRangesList.add(dateRange);
		}
		Date[] dates = new Date[datesList.size()];
		DateRange[] dateRanges = new DateRange[dateRangesList.size()];
		datesList.toArray(dates);
		dateRangesList.toArray(dateRanges);
		DateFilter dateFilter = new DateFilter(dates,dateRanges);
		
		//Creacion de ContentFilter (consta de 2 parametros, included y excluded)
		String includedstr = parametrosContentFilter[0].split(":")[1].trim();
		String excludedstr = parametrosContentFilter[1].split(":")[1].trim();
		List<ContentCategory> includedList = new ArrayList<ContentCategory>();
		List<ContentCategory> excludedList = new ArrayList<ContentCategory>();
		if(includedstr.contains("none")) includedList.add(ContentCategory.NONE);
		if(includedstr.contains("landscapes")) includedList.add(ContentCategory.LANDSCAPES);
		if(includedstr.contains("receipts")) includedList.add(ContentCategory.RECEIPTS);
		if(includedstr.contains("cityscapes")) includedList.add(ContentCategory.CITYSCAPES);
		if(includedstr.contains("landmarks")) includedList.add(ContentCategory.LANDMARKS);
		if(includedstr.contains("selfies")) includedList.add(ContentCategory.SELFIES);
		if(includedstr.contains("people")) includedList.add(ContentCategory.PEOPLE);
		if(includedstr.contains("pets")) includedList.add(ContentCategory.PETS);
		if(includedstr.contains("weddings")) includedList.add(ContentCategory.WEDDINGS);
		if(includedstr.contains("birthdays")) includedList.add(ContentCategory.BIRTHDAYS);
		if(includedstr.contains("documents")) includedList.add(ContentCategory.DOCUMENTS);
		if(includedstr.contains("travel")) includedList.add(ContentCategory.TRAVEL);
		if(includedstr.contains("animals")) includedList.add(ContentCategory.ANIMALS);
		if(includedstr.contains("food")) includedList.add(ContentCategory.FOOD);
		if(includedstr.contains("sport")) includedList.add(ContentCategory.SPORT);
		if(includedstr.contains("night")) includedList.add(ContentCategory.NIGHT);
		if(includedstr.contains("performances")) includedList.add(ContentCategory.PERFORMANCES);
		if(includedstr.contains("whiteboards")) includedList.add(ContentCategory.WHITEBOARDS);
		if(includedstr.contains("screenshots")) includedList.add(ContentCategory.SCREENSHOTS);
		if(includedstr.contains("utility")) includedList.add(ContentCategory.UTILITY);
		if(includedstr.contains("arts")) includedList.add(ContentCategory.ARTS);
		if(includedstr.contains("crafts")) includedList.add(ContentCategory.CRAFTS);
		if(includedstr.contains("fashion")) includedList.add(ContentCategory.FASHION);
		if(includedstr.contains("houses")) includedList.add(ContentCategory.HOUSES);
		if(includedstr.contains("gardens")) includedList.add(ContentCategory.GARDENS);
		if(includedstr.contains("flowers")) includedList.add(ContentCategory.FLOWERS);
		if(includedstr.contains("holidays")) includedList.add(ContentCategory.HOLIDAYS);
		if(excludedstr.contains("none")) excludedList.add(ContentCategory.NONE);
		if(excludedstr.contains("landscapes")) excludedList.add(ContentCategory.LANDSCAPES);
		if(excludedstr.contains("receipts")) excludedList.add(ContentCategory.RECEIPTS);
		if(excludedstr.contains("cityscapes")) excludedList.add(ContentCategory.CITYSCAPES);
		if(excludedstr.contains("landmarks")) excludedList.add(ContentCategory.LANDMARKS);
		if(excludedstr.contains("selfies")) excludedList.add(ContentCategory.SELFIES);
		if(excludedstr.contains("people")) excludedList.add(ContentCategory.PEOPLE);
		if(excludedstr.contains("pets")) excludedList.add(ContentCategory.PETS);
		if(excludedstr.contains("weddings")) excludedList.add(ContentCategory.WEDDINGS);
		if(excludedstr.contains("birthdays")) excludedList.add(ContentCategory.BIRTHDAYS);
		if(excludedstr.contains("documents")) excludedList.add(ContentCategory.DOCUMENTS);
		if(excludedstr.contains("travel")) excludedList.add(ContentCategory.TRAVEL);
		if(excludedstr.contains("animals")) excludedList.add(ContentCategory.ANIMALS);
		if(excludedstr.contains("food")) excludedList.add(ContentCategory.FOOD);
		if(excludedstr.contains("sport")) excludedList.add(ContentCategory.SPORT);
		if(excludedstr.contains("night")) excludedList.add(ContentCategory.NIGHT);
		if(excludedstr.contains("performances")) excludedList.add(ContentCategory.PERFORMANCES);
		if(excludedstr.contains("whiteboards")) excludedList.add(ContentCategory.WHITEBOARDS);
		if(excludedstr.contains("screenshots")) excludedList.add(ContentCategory.SCREENSHOTS);
		if(excludedstr.contains("utility")) excludedList.add(ContentCategory.UTILITY);
		if(excludedstr.contains("arts")) excludedList.add(ContentCategory.ARTS);
		if(excludedstr.contains("crafts")) excludedList.add(ContentCategory.CRAFTS);
		if(excludedstr.contains("fashion")) excludedList.add(ContentCategory.FASHION);
		if(excludedstr.contains("houses")) excludedList.add(ContentCategory.HOUSES);
		if(excludedstr.contains("gardens")) excludedList.add(ContentCategory.GARDENS);
		if(excludedstr.contains("flowers")) excludedList.add(ContentCategory.FLOWERS);
		if(excludedstr.contains("holidays")) excludedList.add(ContentCategory.HOLIDAYS);
		ContentCategory[] included = new ContentCategory[includedList.size()];
		ContentCategory[] excluded = new ContentCategory[excludedList.size()];
		includedList.toArray(included);
		excludedList.toArray(excluded);
		ContentFilter contentFilter = new ContentFilter(included,excluded);
		
		//Creacion de MediaTypeFilter (solo 1 parametro, mediatypes)
		String elementos_mediatypes = parametrosMediaTypeFilter.split(":")[1].trim();
		List<MediaType> mediatypesList = new ArrayList<MediaType>();
		if(elementos_mediatypes.contains("allmedia")) mediatypesList.add(MediaType.ALL_MEDIA);
		if(elementos_mediatypes.contains("video")) mediatypesList.add(MediaType.VIDEO);
		if(elementos_mediatypes.contains("photo")) mediatypesList.add(MediaType.ALL_MEDIA);
		MediaType[] mediatypes = new MediaType[mediatypesList.size()];
		mediatypesList.toArray(mediatypes);
		MediaTypeFilter mediaTypeFilter = new MediaTypeFilter(mediatypes);
		
		//Creacion de FeatureFilter (solo 1 parametro, features)
		String elementos_features = parametrosFeatureFilter.split(":")[1].trim();
		List<Feature> featuresList = new ArrayList<Feature>();
		if(elementos_features.contains("none")) featuresList.add(Feature.NONE);
		if(elementos_features.contains("favorites")) featuresList.add(Feature.FAVORITES);
		Feature[] features = new Feature[featuresList.size()];
		featuresList.toArray(features);
		FeatureFilter featureFilter = new FeatureFilter(features);
		
		//Creacion de includeArchivedMedia 
		Boolean includeArchivedMedia = false;
		if(includeArchivedMediastr.equals("true")) includeArchivedMedia = true;
		
		//Creacion de excludeNonAppCreatedData
		Boolean excludeNonAppCreatedData = false;
		if(excludeNonAppCreatedDatastr.equals("true")) excludeNonAppCreatedData = true;
		
		//Creacion de Filters
		Filters filter = new Filters(dateFilter,contentFilter,mediaTypeFilter,featureFilter,
				includeArchivedMedia,excludeNonAppCreatedData);
		return filter;
	}
    
    
}
