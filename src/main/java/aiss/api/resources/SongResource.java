package aiss.api.resources;

import javax.ws.rs.Path;

@Path("/songs")
public class SongResource {
	
	public static SongResource _instance=null;
	
	public static SongResource getInstance()
	{
		if(_instance==null)
			_instance=new SongResource();
		return _instance; 
	}


}
