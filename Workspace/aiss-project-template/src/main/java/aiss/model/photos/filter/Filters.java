package aiss.model.photos.filter;

public interface Filters {
	
	public Boolean getIncludedArchivedMedia();
	public void setIncludedArchivedMedia(Boolean includeArchivedMedia);
	public Boolean getExcludeNonAppCreatedData();
	public void setExcludeNonAppCreatedData(Boolean excludeNonAppCreatedData);
	
}
