package aiss.model.repository;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "size"
})

public class MP3 {
	
	@JsonIgnore
	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("size")
	private Integer size;
	
	public MP3(String name, Integer size) {
		super();
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.size = size;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@JsonProperty("name")
	public String getName() {
		return name;
	}
	
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty("size")
	public Integer getSize() {
		return size;
	}
	
	@JsonProperty("size")
	public void setSize(Integer size) {
		this.size = size;
	}
	

}
