package aiss.model.photos.filter;

public class ContentFilter implements Filters{

	private ContentCategory[] includedContentCategories;
	private ContentCategory[] excludedContentCategories;
	private Boolean includedArchivedMedia;
	private Boolean excludeNonAppCreatedData;
	
	public ContentCategory[] getIncludedContentCategories() {
		return includedContentCategories;
	}
	public void setIncludedContentCategories(ContentCategory[] includedContentCategories) {
		this.includedContentCategories = includedContentCategories;
	}
	public ContentCategory[] getExcludedContentCategories() {
		return excludedContentCategories;
	}
	public void setExcludedContentCategories(ContentCategory[] excludedContentCategories) {
		this.excludedContentCategories = excludedContentCategories;
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