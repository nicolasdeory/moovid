package aiss.model.photos.filter;

public class ContentFilter{

	private ContentCategory[] includedContentCategories;
	private ContentCategory[] excludedContentCategories;
	
	public ContentFilter(ContentCategory[] includedContentCategories, ContentCategory[] excludedContentCategories) {
		super();
		this.includedContentCategories = includedContentCategories;
		this.excludedContentCategories = excludedContentCategories;
	}
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
}