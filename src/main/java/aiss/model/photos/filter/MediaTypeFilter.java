package aiss.model.photos.filter;

public class MediaTypeFilter implements Filters{
	
	private MediaType[] mediaTypes;
	private Boolean includedArchivedMedia;
	private Boolean excludeNonAppCreatedData;
	
	public MediaType[] getMediaTypes() {
		return mediaTypes;
	}
	public void setMediaTypes(MediaType[] mediaTypes) {
		this.mediaTypes = mediaTypes;
	}
	public Boolean getIncludedArchivedMedia() {
		return includedArchivedMedia;
	}
	public void setIncludedArchivedMedia(Boolean includedArchivedMedia) {
		this.includedArchivedMedia = includedArchivedMedia;
	}
	public Boolean getExcludeNonAppCreatedData() {
		return excludeNonAppCreatedData;
	}
	public void setExcludeNonAppCreatedData(Boolean excludeNonAppCreatedData) {
		this.excludeNonAppCreatedData = excludeNonAppCreatedData;
	}
	

}
