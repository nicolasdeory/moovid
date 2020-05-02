package aiss.resources.photos;

import java.util.Arrays;
import java.util.Collection;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.photos.album.Album;
import aiss.model.photos.mediaitem.MediaItem;

public class AlbumResource {

	private final String access_token;
	private String uri = "https://photoslibrary.googleapis.com/v1/albums";

	public AlbumResource(String access_token) {
		super();
		this.access_token = access_token;
	}

	public Collection<Album> getAll(){
		ClientResource cr = null;
		Album [] lists = null;
		try {
			cr = new ClientResource(uri + "?access_token=" + access_token);
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
			cr = new ClientResource(uri + "/" + albumId + "?access_token=" + access_token);
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
			cr = new ClientResource(uri + "?access_token=" + access_token);
			cr.setEntityBuffering(true);
			resultAlbum = cr.post(al,Album.class);
		} catch (ResourceException re){
			System.out.println("Error when adding the album: " + cr.getResponse().getStatus());
		}
		return resultAlbum;
	}
}