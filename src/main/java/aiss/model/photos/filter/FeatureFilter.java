
package aiss.model.photos.filter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "includedFeatures"
})
public class FeatureFilter {

    @Override
	public String toString() {
		return "FeatureFilter [includedFeatures=" + includedFeatures + "]";
	}

	@JsonProperty("includedFeatures")
    private List<String> includedFeatures = null;

    
    
    public FeatureFilter(List<String> includedFeatures) {
		super();
		this.includedFeatures = includedFeatures;
	}

	@JsonProperty("includedFeatures")
    public List<String> getIncludedFeatures() {
        return includedFeatures;
    }

    @JsonProperty("includedFeatures")
    public void setIncludedFeatures(List<String> includedFeatures) {
        this.includedFeatures = includedFeatures;
    }


}
