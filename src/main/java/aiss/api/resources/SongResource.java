package aiss.api.resources;

import javax.ws.rs.Path;

@Path("/song")
public class SongResource {
	
	public static SongResource _instance=null;
	
	public static SongResource getInstance()
	{
		if(_instance==null)
			_instance=new SongResource();
		return _instance; 
	}
	
	// PUT - subir un mp3 /song/uploadmp3
	// GET - mp3 song id /song/getmp3
	// POST - search mp3 song /song/searchmp3
	// DELETE - borrar mp3 con id /song/deletemp3

}
