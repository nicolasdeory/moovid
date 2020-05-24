package aiss.model.spotify;

public class Song {

	private String artist;
	private String name;
	private Double bpm;
	
	public Song(String artist, String name, Double bpm) {
		super();
		this.artist = artist;
		this.name = name;
		this.bpm = bpm;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Double getBpm() {
		return bpm;
	}
	public void setBpm(Double bpm) {
		this.bpm = bpm;
	}
	public String toString() {
		return this.artist + " - " + this.name + " (" + this.bpm + " bpm)";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artist == null) ? 0 : artist.hashCode());
		result = prime * result + ((bpm == null) ? 0 : bpm.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Song other = (Song) obj;
		if (artist == null) {
			if (other.artist != null)
				return false;
		} else if (!artist.equals(other.artist))
			return false;
		if (bpm == null) {
			if (other.bpm != null)
				return false;
		} else if (!bpm.equals(other.bpm))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
