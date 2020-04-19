package aiss.model.resources.photos;

import java.util.Arrays;
import java.util.Collection;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import aiss.model.photos.filter.Filters;
import aiss.model.photos.mediaitem.MediaItem;

//Va static pq si no tendria que crearse un objeto MediaItemResource. Preguntar duda al doño (pq va estatico?)

public class MediaItemResource {

	private static String uri = "https://photoslibrary.googleapis.com/v1/mediaItems";
	
	public static MediaItem getMediaItem(String mediaItemId){
		ClientResource cr = null;
		MediaItem list = null;
		try {
			cr = new ClientResource(uri + "/" + mediaItemId);
			list = cr.get(MediaItem.class);
		} catch (ResourceException re){
			System.out.println("Error when retrieving the media item: " + cr.getResponse().getStatus());
		}
		return list;
	}
	
	public static Collection<MediaItem> searchMediaItem(Filters[] filters){
		ClientResource cr = null;
		MediaItem [] list = null;
		try {
			cr = new ClientResource(uri + ":search");
			cr.setEntityBuffering(true);
			list = cr.post(filters, MediaItem[].class);
		} catch (ResourceException re){
			System.out.println("Error when retrieving the collections");
		}
		return Arrays.asList(list);
	}
	
	public static MediaItem createMediaItem(MediaItem MI){
		ClientResource cr = null;
		MediaItem resultMediaItem = null;
		try {
			cr = new ClientResource(uri + ":batchCreate");
			cr.setEntityBuffering(true);
			resultMediaItem = cr.post(MI, MediaItem.class);
		} catch (ResourceException re){
			System.out.println("Error when creating the MediaItem");
		}
		return resultMediaItem;
	}
	
}