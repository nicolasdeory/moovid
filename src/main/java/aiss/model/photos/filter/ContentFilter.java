
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
    "includedContentCategories",
    "excludedContentCategories"
})
public class ContentFilter {

    @JsonProperty("includedContentCategories")
    private List<ContentCategory> includedContentCategories = null;
    @JsonProperty("excludedContentCategories")
    private List<ContentCategory> excludedContentCategories = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    
    
    public ContentFilter(List<ContentCategory> includedContentCategories,
			List<ContentCategory> excludedContentCategories) {
		super();
		this.includedContentCategories = includedContentCategories;
		this.excludedContentCategories = excludedContentCategories;
	}

	@JsonProperty("includedContentCategories")
    public List<ContentCategory> getIncludedContentCategories() {
        return includedContentCategories;
    }

    @JsonProperty("includedContentCategories")
    public void setIncludedContentCategories(List<ContentCategory> includedContentCategories) {
        this.includedContentCategories = includedContentCategories;
    }

    @JsonProperty("excludedContentCategories")
    public List<ContentCategory> getExcludedContentCategories() {
        return excludedContentCategories;
    }

    @JsonProperty("excludedContentCategories")
    public void setExcludedContentCategories(List<ContentCategory> excludedContentCategories) {
        this.excludedContentCategories = excludedContentCategories;
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
