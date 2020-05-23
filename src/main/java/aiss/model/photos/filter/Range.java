
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
    "startDate",
    "endDate"
})
public class Range {

    @Override
	public String toString() {
		return "Range [startDate=" + startDate + ", endDate=" + endDate + "]";
	}

	@JsonProperty("startDate")
    private StartDate startDate;
    @JsonProperty("endDate")
    private EndDate endDate;

    
    
    public Range(StartDate startDate, EndDate endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}

	@JsonProperty("startDate")
    public StartDate getStartDate() {
        return startDate;
    }

    @JsonProperty("startDate")
    public void setStartDate(StartDate startDate) {
        this.startDate = startDate;
    }

    @JsonProperty("endDate")
    public EndDate getEndDate() {
        return endDate;
    }

    @JsonProperty("endDate")
    public void setEndDate(EndDate endDate) {
        this.endDate = endDate;
    }

}
