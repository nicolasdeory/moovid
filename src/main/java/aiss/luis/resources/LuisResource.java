package aiss.luis.resources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import aiss.luis.model.classes.Intent;
import aiss.luis.model.classes.MontageCreateIntent;
import aiss.luis.model.classes.MontageThemeIntent;
import aiss.luis.model.classes.MusicIntent;
import aiss.luis.model.classes.SpecificThemeIntent;
import aiss.luis.model.enumerates.IntentType;
import aiss.luis.model.enumerates.MontageTheme;

public class LuisResource {

	
	public static Intent retrieveIntent(String json) throws IOException {
		JsonNode query = new ObjectMapper().readTree(json);
		IntentType tipo = IntentType.valueOf
				(query.get("prediction").get("topIntent").textValue());
		JsonNode nodo_themes = query.get("prediction").get("entities").get("MontageTheme");
		List<MontageTheme> ls = retrieveThemes(nodo_themes , new ArrayList<MontageTheme>());
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
			Intent in = new Intent(tipo);
			return in;
		}
	}
	
	private static boolean isTheme(String cad) {
		boolean res = true;
		try {          
	         MontageTheme.valueOf(cad);
	      } catch (IllegalArgumentException e) {
	        res = false;
	      }

	      return res;
	   }
	
	private static List<MontageTheme> retrieveThemes(JsonNode nodo, List<MontageTheme> ls) {
		Iterator<JsonNode> itnode = nodo.elements();
		while(itnode.hasNext()) {
			Iterator<Map.Entry<String,JsonNode>> itentry = itnode.next().fields();
			while(itentry.hasNext()) {
				Map.Entry<String,JsonNode> entrada = itentry.next();
				if (isTheme(entrada.getKey()))
					ls.add(MontageTheme.valueOf(entrada.getKey()));
				else
					ls = retrieveThemes(entrada.getValue(), ls);
			}
		}
		
		
		return ls;
	}
	/*
	public static void main(String[] args) {
		 String content = "";
		    try
		    {
		        content = new String ( Files.readAllBytes( Paths.get("C:/Users/anton/Desktop/file.txt") ) );
		        JsonNode productNode = new ObjectMapper().readTree(content);
		        JsonNode array = productNode.get("prediction").get("entities").get("MontageTheme");
		        System.out.println(retrieveThemes(array, new ArrayList<MontageTheme>()));
		        
		       
		        
		    } 
		    catch (IOException e) 
		    {
		        e.printStackTrace();
		    }
		    
		}
	*/
}
