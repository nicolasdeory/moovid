package aiss.model.photos.mediaitem;

public class NewMediaItem {
	private String description;
	private SimpleMediaItem simpleMediaItem;
	
	public NewMediaItem(SimpleMediaItem simpleMediaItem) {
		super();
		this.simpleMediaItem = simpleMediaItem;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public SimpleMediaItem getSimpleMediaItem() {
		return simpleMediaItem;
	}
	public void setSimpleMediaItem(SimpleMediaItem simpleMediaItem) {
		this.simpleMediaItem = simpleMediaItem;
	}
	
	
	
}
