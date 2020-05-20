
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

    @JsonProperty("includedFeatures")
    private List<Feature> includedFeatures = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    
    
    public FeatureFilter(List<Feature> includedFeatures) {
		super();
		this.includedFeatures = includedFeatures;
	}

	@JsonProperty("includedFeatures")
    public List<Feature> getIncludedFeatures() {
        return includedFeatures;
    }

    @JsonProperty("includedFeatures")
    public void setIncludedFeatures(List<Feature> includedFeatures) {
        this.includedFeatures = includedFeatures;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
