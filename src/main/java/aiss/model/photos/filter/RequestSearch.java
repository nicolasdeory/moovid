package aiss.model.photos.filter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "filters"
})

public class RequestSearch {
	
	@JsonProperty("filters")
	private Filters filters;
	@JsonProperty("pageToken")
	private String pageToken;

	public RequestSearch(Filters filters, String pageToken) {
		super();
		this.filters = filters;
		this.pageToken = pageToken;
	}
	
	@JsonProperty("filters")
	public Filters getFilters() {
		return filters;
	}

	
	@JsonProperty("filters")
	public void setFilters(Filters filters) {
		this.filters = filters;
	}

	
	
	public String getPageToken() {
		return pageToken;
	}

	public void setPageToken(String pageToken) {
		this.pageToken = pageToken;
	}

	@Override
	public String toString() {
		return "RequestSearch [filters=" + filters + "pageToken" + pageToken + "]";
	}
	
	

}
