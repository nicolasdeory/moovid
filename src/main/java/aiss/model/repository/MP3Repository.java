package aiss.model.repository;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MP3Repository {
	
	Map<UUID, File> MP3Map;
	private static MP3Repository instance=null;
	private int index=0;			// Index to create playlists and songs' identifiers.
	
	
	public static MP3Repository getInstance() {
		
		if (instance==null) {
			instance = new MP3Repository();
			instance.init();
		}
		
		return instance;
	}
	
	public void init() {
		
		MP3Map = new HashMap<String,MP3>();
		
		
	}
	
	@Override
	public void addMP3(MP3 p) {
		String id = new UUID().toString();	
		p.setId(id);
		MP3Map.put(id,p);
	}

	@Override
	public MP3 getMP3(String id) {
		return MP3Map.get(id);
	}
	
	@Override
	public void removeMP3(String id) {	
		MP3Map.remove(id);
	}
	
}
