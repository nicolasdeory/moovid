package aiss.model.photos.filter;

public class Filters {
	
	private DateFilter dateFilter;
	private ContentFilter contentFilter;
	private MediaTypeFilter mediaTypeFilter;
	private FeatureFilter featureFilter;
	private Boolean includeArchivedMedia;
	private Boolean excludeNonAppCreatedData;
	
	public DateFilter getDateFilter() {
		return dateFilter;
	}
	public void setDateFilter(DateFilter dateFilter) {
		this.dateFilter = dateFilter;
	}
	public ContentFilter getContentFilter() {
		return contentFilter;
	}
	public void setContentFilter(ContentFilter contentFilter) {
		this.contentFilter = contentFilter;
	}
	public MediaTypeFilter getMediaTypeFilter() {
		return mediaTypeFilter;
	}
	public void setMediaTypeFilter(MediaTypeFilter mediaTypeFilter) {
		this.mediaTypeFilter = mediaTypeFilter;
	}
	public FeatureFilter getFeatureFilter() {
		return featureFilter;
	}
	public void setFeatureFilter(FeatureFilter featureFilter) {
		this.featureFilter = featureFilter;
	}
	public Boolean getIncludeArchivedMedia() {
		return includeArchivedMedia;
	}
	public void setIncludeArchivedMedia(Boolean includeArchivedMedia) {
		this.includeArchivedMedia = includeArchivedMedia;
	}
	public Boolean getExcludeNonAppCreatedData() {
		return excludeNonAppCreatedData;
	}
	public void setExcludeNonAppCreatedData(Boolean excludeNonAppCreatedData) {
		this.excludeNonAppCreatedData = excludeNonAppCreatedData;
	}
	
	
	
}
