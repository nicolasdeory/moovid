package aiss.model.resources.photos;

import java.util.Arrays;
import java.util.Collection;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.photos.album.Album;
import aiss.model.photos.mediaitem.MediaItem;

public class AlbumResource {

	private String uri = "https://photoslibrary.googleapis.com/v1/albums";
	
	public Collection<Album> getAll(){
		ClientResource cr = null;
		Album [] lists = null;
		try {
			cr = new ClientResource(uri);
			lists = cr.get(Album[].class);
		} catch (ResourceException re){
			System.out.println("Error when retrieving the collections");
		}
		return Arrays.asList(lists);
	}
	
	public Album getAlbum(String albumId){
		ClientResource cr = null;
		Album list = null;
		try {
			cr = new ClientResource(uri + "/" + albumId);
			list = cr.get(Album.class);
		} catch (ResourceException re){
			System.out.println("Error when retrieving the album: " + cr.getResponse().getStatus());
		}
		return list;
	}
	
	public Album addAlbum(Album al) {
		ClientResource cr = null;
		Album resultAlbum = null;
		try {
			cr = new ClientResource(uri);
			cr.setEntityBuffering(true);
			resultAlbum = cr.post(al,Album.class);
		} catch (ResourceException re){
			System.out.println("Error when adding the album: " + cr.getResponse().getStatus());
		}
		return resultAlbum;
	}
}