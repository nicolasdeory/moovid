package aiss.model.resources.photos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.photos.filter.Filters;
import aiss.model.photos.mediaitem.MediaItem;
import aiss.model.photos.mediaitem.NewMediaItem;
import aiss.model.photos.mediaitem.NewMediaItemResult;
import aiss.model.photos.mediaitem.SimpleMediaItem;

//Va static pq si no tendria que crearse un objeto MediaItemResource. Preguntar duda al doño (pq va estatico?)

public class MediaItemResource {

	private final String access_token;
	private static String uri = "https://photoslibrary.googleapis.com/v1/mediaItems";
	
	public MediaItemResource(String access_token) {
		super();
		this.access_token = access_token;
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

	public  Collection<MediaItem> searchMediaItem(Filters filters){
		ClientResource cr = null;
		MediaItem [] list = null;
		try {
			cr = new ClientResource(uri + ":search" + "?access_token=" + access_token);
			cr.setEntityBuffering(true);
			list = cr.post(filters, MediaItem[].class);
		} catch (ResourceException re){
			System.out.println("Error when retrieving the collections");
		}
		return Arrays.asList(list);
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
	
}