
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
    "mediaTypes"
})
public class MediaTypeFilter {

    @Override
	public String toString() {
		return "MediaTypeFilter [mediaTypes=" + mediaTypes + "]";
	}

	@JsonProperty("mediaTypes")
    private List<String> mediaTypes = null;

    
    
    public MediaTypeFilter(List<String> mediaTypes) {
		super();
		this.mediaTypes = mediaTypes;
	}

	@JsonProperty("mediaTypes")
    public List<String> getMediaTypes() {
        return mediaTypes;
    }

    @JsonProperty("mediaTypes")
    public void setMediaTypes(List<String> mediaTypes) {
        this.mediaTypes = mediaTypes;
    }

}
