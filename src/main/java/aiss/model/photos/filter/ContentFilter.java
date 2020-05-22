
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

    @Override
	public String toString() {
		return "ContentFilter [includedContentCategories=" + includedContentCategories + ", excludedContentCategories="
				+ excludedContentCategories + "]";
	}

	@JsonProperty("includedContentCategories")
    private List<String> includedContentCategories = null;
    @JsonProperty("excludedContentCategories")
    private List<String> excludedContentCategories = null;

    
    
    public ContentFilter(List<String> includedContentCategories,
			List<String> excludedContentCategories) {
		super();
		this.includedContentCategories = includedContentCategories;
		this.excludedContentCategories = excludedContentCategories;
	}

	@JsonProperty("includedContentCategories")
    public List<String> getIncludedContentCategories() {
        return includedContentCategories;
    }

    @JsonProperty("includedContentCategories")
    public void setIncludedContentCategories(List<String> includedContentCategories) {
        this.includedContentCategories = includedContentCategories;
    }

    @JsonProperty("excludedContentCategories")
    public List<String> getExcludedContentCategories() {
        return excludedContentCategories;
    }

    @JsonProperty("excludedContentCategories")
    public void setExcludedContentCategories(List<String> excludedContentCategories) {
        this.excludedContentCategories = excludedContentCategories;
    }

}
