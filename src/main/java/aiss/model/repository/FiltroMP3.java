package aiss.model.repository;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"name",
    "albumname",
    "author",
    "urlcoverart"
})

public class FiltroMP3 {

	@JsonProperty("name")
	private String name;
	@JsonProperty("albumname")
	private String albumname;
	@JsonProperty("author")
	private String author;
	@JsonProperty("urlcoverart")
	private String urlcoverart;
	
	@JsonProperty("name")
	public String getName() {
		return name;
	}
	
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("albumname")
	public String getalbumname() {
		return albumname;
	}

	@JsonProperty("albumname")
	public void setalbumname(String albumname) {
		this.albumname = albumname;
	}

	@JsonProperty("author")
	public String getAuthor() {
		return author;
	}

	@JsonProperty("author")
	public void setAuthor(String author) {
		this.author = author;
	}

	@JsonProperty("urlcoverart")
	public String getUrlcoverart() {
		return urlcoverart;
	}

	@JsonProperty("urlcoverart")
	public void setUrlcoverart(String urlcoverart) {
		this.urlcoverart = urlcoverart;
	}
	
	
	
	
	
}
