package aiss.spotify.model;

public class Artist {

	private String id;

	public Artist(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Artist [id=" + id + "]";
	}
	
	
}
