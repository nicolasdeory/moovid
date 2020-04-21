package aiss.luis.resources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import aiss.luis.model.classes.Intent;
import aiss.luis.model.classes.MontageCreateIntent;
import aiss.luis.model.classes.MontageThemeIntent;
import aiss.luis.model.classes.MusicIntent;
import aiss.luis.model.classes.SpecificThemeIntent;
import aiss.luis.model.enumerates.IntentType;

public class LuisResource {

	public static Intent retrieveIntent(String json) throws IOException {
		JsonNode productNode = new ObjectMapper().readTree(json);
		IntentType tipo = IntentType.valueOf
				(productNode.get("prediction").get("topIntent").textValue());
		switch(tipo) {
		case CreateMontage:
			MontageCreateIntent mcr = new MontageCreateIntent();
			return mcr;
		case MontageThemeIntent:
			MontageThemeIntent mth = new MontageThemeIntent();
			return mth;
		case MusicDescription:
			MusicIntent mus = new MusicIntent();
			return mus;
		case SpecificTheme:
			SpecificThemeIntent sth = new SpecificThemeIntent();
			return sth;
		default:
			Intent in = new Intent();
			return in;
		}
		
		
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
