package aiss.model.photos.filter;

public class FeatureFilter{
	
	private Feature[] includedFeatures;
	
	public FeatureFilter(Feature[] includedFeatures) {
		super();
		this.includedFeatures = includedFeatures;
	}
	public Feature[] getIncludedFeatures() {
		return includedFeatures;
	}
	public void setIncludedFeatures(Feature[] includedFeatures) {
		this.includedFeatures = includedFeatures;
	}
}