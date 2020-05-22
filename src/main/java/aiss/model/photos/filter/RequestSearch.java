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

	public RequestSearch(Filters filters) {
		super();
		this.filters = filters;
	}
	
	@JsonProperty("filters")
	public Filters getFilters() {
		return filters;
	}

	
	@JsonProperty("filters")
	public void setFilters(Filters filters) {
		this.filters = filters;
	}

	@Override
	public String toString() {
		return "RequestSearch [filters=" + filters + "]";
	}
	
	

}
