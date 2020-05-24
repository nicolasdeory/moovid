package aiss.model.repository;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
    "name",
    "size"
})

public class FiltroMP3 {

	@JsonProperty("name")
	private String name;
	@JsonProperty("size")
	private Size size;
	
	@JsonProperty("name")
	public String getName() {
		return name;
	}
	
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty("size")
	public Size getSize() {
		return size;
	}
	
	@JsonProperty("size")
	public void setSize(Size size) {
		this.size = size;
	}
	
	
	
}
