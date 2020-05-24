package aiss.model.repository;

import java.util.UUID;


public class MP3 {
	
	private String id;
	private Metadata metadata;
	
	public MP3(Metadata metadata) {
		super();
		this.id = UUID.randomUUID().toString();
		this.metadata = metadata;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Metadata getMetadata() {
		return metadata;
	}
	
	public void setName(Metadata metadata) {
		this.metadata = metadata;
	}
	

}
