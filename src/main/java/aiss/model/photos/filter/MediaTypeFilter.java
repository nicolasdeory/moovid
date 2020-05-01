package aiss.model.photos.filter;

public class MediaTypeFilter{
	
	private MediaType[] mediaTypes;
	
	public MediaTypeFilter(MediaType[] mediaTypes) {
		super();
		this.mediaTypes = mediaTypes;
	}
	public MediaType[] getMediaTypes() {
		return mediaTypes;
	}
	public void setMediaTypes(MediaType[] mediaTypes) {
		this.mediaTypes = mediaTypes;
	}
	

}
