
package aiss.model.photos.mediaitem;

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
    "mediaItems",
    "nextPageToken"
})
public class MediaItems {

    @JsonProperty("mediaItems")
    private List<MediaItem> mediaItems = null;
    @JsonProperty("nextPageToken")
    private String nextPageToken;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("mediaItems")
    public List<MediaItem> getMediaItems() {
        return mediaItems;
    }

    @JsonProperty("mediaItems")
    public void setMediaItems(List<MediaItem> mediaItems) {
        this.mediaItems = mediaItems;
    }

    @JsonProperty("nextPageToken")
    public String getNextPageToken() {
        return nextPageToken;
    }

    @JsonProperty("nextPageToken")
    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

	@Override
	public String toString() {
		return "MediaItems [mediaItems=" + mediaItems + ", nextPageToken=" + nextPageToken + ", additionalProperties="
				+ additionalProperties + "]";
	}
    
    

}
