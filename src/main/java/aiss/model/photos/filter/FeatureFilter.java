package aiss.model.photos.filter;

public class FeatureFilter implements Filters{
	
	private Feature[] includedFeatures;
	private Boolean includedArchivedMedia;
	private Boolean excludeNonAppCreatedData;
	
	public Feature[] getIncludedFeatures() {
		return includedFeatures;
	}
	public void setIncludedFeatures(Feature[] includedFeatures) {
		this.includedFeatures = includedFeatures;
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