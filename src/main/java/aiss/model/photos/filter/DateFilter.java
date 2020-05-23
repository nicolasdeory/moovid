
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
    "dates",
    "ranges"
})
public class DateFilter {

    @JsonProperty("dates")
    private List<Date> dates = null;
    @JsonProperty("ranges")
    private List<Range> ranges = null;

    
    
    public DateFilter(List<Date> dates, List<Range> ranges) {
		super();
		this.dates = dates;
		this.ranges = ranges;
	}

	@JsonProperty("dates")
    public List<Date> getDates() {
        return dates;
    }

    @JsonProperty("dates")
    public void setDates(List<Date> dates) {
        this.dates = dates;
    }

    @JsonProperty("ranges")
    public List<Range> getRanges() {
        return ranges;
    }

    @JsonProperty("ranges")
    public void setRanges(List<Range> ranges) {
        this.ranges = ranges;
    }

	@Override
	public String toString() {
		return "DateFilter [dates=" + dates + ", ranges=" + ranges + "]";
	}
    
    


}
