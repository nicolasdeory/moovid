package aiss.model.repository;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "value"
})

public class Size {
	
	@JsonProperty("type")
	private String type;
	@JsonProperty("value")
	private Integer value;
	
	@JsonProperty("type")
	public String getType() {
		return type;
	}
	
	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}
	
	@JsonProperty("value")
	public Integer getValue() {
		return value;
	}
	
	@JsonProperty("value")
	public void setValue(Integer value) {
		this.value = value;
	}
	
	
	
}
