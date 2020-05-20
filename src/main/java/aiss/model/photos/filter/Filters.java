
package aiss.model.photos.filter;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "contentFilter",
    "dateFilter",
    "featureFilter",
    "mediaTypeFilter",
    "excludeNonAppCreatedData",
    "includeArchivedMedia"
})
public class Filters {

    @JsonProperty("contentFilter")
    private ContentFilter contentFilter;
    @JsonProperty("dateFilter")
    private DateFilter dateFilter;
    @JsonProperty("featureFilter")
    private FeatureFilter featureFilter;
    @JsonProperty("mediaTypeFilter")
    private MediaTypeFilter mediaTypeFilter;
    @JsonProperty("excludeNonAppCreatedData")
    private Boolean excludeNonAppCreatedData;
    @JsonProperty("includeArchivedMedia")
    private Boolean includeArchivedMedia;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    
    
    public Filters(ContentFilter contentFilter, DateFilter dateFilter, FeatureFilter featureFilter,
			MediaTypeFilter mediaTypeFilter, Boolean excludeNonAppCreatedData, Boolean includeArchivedMedia) {
		super();
		this.contentFilter = contentFilter;
		this.dateFilter = dateFilter;
		this.featureFilter = featureFilter;
		this.mediaTypeFilter = mediaTypeFilter;
		this.excludeNonAppCreatedData = excludeNonAppCreatedData;
		this.includeArchivedMedia = includeArchivedMedia;
	}

	@JsonProperty("contentFilter")
    public ContentFilter getContentFilter() {
        return contentFilter;
    }

    @JsonProperty("contentFilter")
    public void setContentFilter(ContentFilter contentFilter) {
        this.contentFilter = contentFilter;
    }

    @JsonProperty("dateFilter")
    public DateFilter getDateFilter() {
        return dateFilter;
    }

    @JsonProperty("dateFilter")
    public void setDateFilter(DateFilter dateFilter) {
        this.dateFilter = dateFilter;
    }

    @JsonProperty("featureFilter")
    public FeatureFilter getFeatureFilter() {
        return featureFilter;
    }

    @JsonProperty("featureFilter")
    public void setFeatureFilter(FeatureFilter featureFilter) {
        this.featureFilter = featureFilter;
    }

    @JsonProperty("mediaTypeFilter")
    public MediaTypeFilter getMediaTypeFilter() {
        return mediaTypeFilter;
    }

    @JsonProperty("mediaTypeFilter")
    public void setMediaTypeFilter(MediaTypeFilter mediaTypeFilter) {
        this.mediaTypeFilter = mediaTypeFilter;
    }

    @JsonProperty("excludeNonAppCreatedData")
    public Boolean getExcludeNonAppCreatedData() {
        return excludeNonAppCreatedData;
    }

    @JsonProperty("excludeNonAppCreatedData")
    public void setExcludeNonAppCreatedData(Boolean excludeNonAppCreatedData) {
        this.excludeNonAppCreatedData = excludeNonAppCreatedData;
    }

    @JsonProperty("includeArchivedMedia")
    public Boolean getIncludeArchivedMedia() {
        return includeArchivedMedia;
    }

    @JsonProperty("includeArchivedMedia")
    public void setIncludeArchivedMedia(Boolean includeArchivedMedia) {
        this.includeArchivedMedia = includeArchivedMedia;
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
