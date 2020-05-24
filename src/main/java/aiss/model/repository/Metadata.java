package aiss.model.repository;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "albumName",
    "author",
    "URLCoverArt"
})

public class Metadata {
	
	@JsonProperty("name")
	private String name;
	@JsonProperty("albumName")
	private String albumName;
	@JsonProperty("author")
	private String author;
	@JsonProperty("URLCoverArt")
	private String URLCoverArt;
	
	@JsonProperty("name")
	public String getName() {
		return name;
	}
	
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty("albumName")
	public String getAlbumName() {
		return albumName;
	}
	
	@JsonProperty("albumName")
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	
	@JsonProperty("author")
	public String getAuthor() {
		return author;
	}
	
	@JsonProperty("author")
	public void setAuthor(String author) {
		this.author = author;
	}
	
	@JsonProperty("URLCoverArt")
	public String getURLCoverArt() {
		return URLCoverArt;
	}
	
	@JsonProperty("URLCoverArt")
	public void setURLCoverArt(String uRLCoverArt) {
		URLCoverArt = uRLCoverArt;
	}

	@Override
	public String toString() {
		return name + "," + albumName + "," + author;
	}
	
	
	
	


}
