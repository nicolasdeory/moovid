package aiss.model.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MP3Repository {
	
	Map<String, MP3> MP3Map;
	private static MP3Repository instance=null;
	
	
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
	
	public void addMP3(MP3 p) {
		String id = UUID.randomUUID().toString();	
		p.setId(id);
		MP3Map.put(id,p);
	}

	public MP3 getMP3(String id) {
		return MP3Map.get(id);
	}
	
	public void removeMP3(String id) {	
		MP3Map.remove(id);
	}
	
	public List<MP3> listMP3(){
		List<MP3> ls = new ArrayList<MP3>();
		for(String id:MP3Map.keySet()) {
			ls.add(MP3Map.get(id));
		}
		return ls;
	}
	
}
