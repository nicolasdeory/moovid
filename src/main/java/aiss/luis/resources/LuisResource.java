package aiss.luis.resources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import aiss.luis.model.Intent;
import aiss.luis.model.IntentType;

public class LuisResource {

	public static Intent retrieveIntent(String json) throws IOException {
		JsonNode productNode = new ObjectMapper().readTree(json);
		Intent in = new Intent();
		in.setTopIntent(IntentType.valueOf
				(productNode.get("prediction").get("topIntent").textValue()));
		return in;
		
	}
	
	public static void main(String[] args) {
	 String content = "";
	    try
	    {
	        content = new String ( Files.readAllBytes( Paths.get("C:/Users/anton/Desktop/file.txt") ) );
	        Intent in = retrieveIntent(content);
	        System.out.println(in);
	    } 
	    catch (IOException e) 
	    {
	        e.printStackTrace();
	    }
	}
}
