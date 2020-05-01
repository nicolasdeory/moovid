package aiss.model.photos.mediaitem;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MediaItems implements Serializable{
	private List<MediaItem> items = null;
	private String nextPageToken;
	
	public List<MediaItem> getItems() {
		return items;
	}
	public void setItems(List<MediaItem> items) {
		this.items = items;
	}
	public String getNextPageToken() {
		return nextPageToken;
	}
	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}
	
	@Override
	public String toString() {
		return "MediaItems [items=" + items + ", nextPageToken=" + nextPageToken + "]";
	}
	
	
	
	
}
