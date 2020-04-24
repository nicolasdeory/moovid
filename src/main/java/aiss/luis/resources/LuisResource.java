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
import aiss.luis.model.enumerates.MusicEnergy;
import aiss.luis.model.enumerates.MusicMood;
import aiss.luis.model.enumerates.MusicTempo;
import aiss.luis.model.enumerates.Sentiment;

public class LuisResource {

	
	public static Intent retrieveIntent(String json) throws IOException {
		JsonNode query = new ObjectMapper().readTree(json);
		JsonNode entities = query.get("prediction").get("entities");
		IntentType tipo = IntentType.valueOf
				(query.get("prediction").get("topIntent").textValue());
		JsonNode nodo_themes = entities.get("MontageTheme");
		List<MontageTheme> ls = retrieveThemes(nodo_themes , new ArrayList<MontageTheme>());
		Sentiment sent = Sentiment.valueOf(query.get("prediction").get("sentiment").get("label").textValue());
		switch(tipo) {
		case CreateMontage:
			MontageCreateIntent mcr = new MontageCreateIntent();
			return mcr;
		case MontageThemeIntent:
			MontageThemeIntent mth = new MontageThemeIntent(sent, ls);
			return mth;
		case MusicDescription:
			MusicTempo tempo = MusicTempo.none;
			if (entities.has("MusicTempo"))
				tempo = MusicTempo.valueOf(entities.get("MusicTempo").elements().next().fieldNames().next());
			
			MusicEnergy energy = MusicEnergy.none;
			if (entities.has("MusicEnergy"))
				energy = MusicEnergy.valueOf(entities.get("MusicEnergy").elements().next().fieldNames().next());
			
			MusicMood mood = MusicMood.none;
			if (entities.has("MusicMood"))
				mood = MusicMood.valueOf(entities.get("MusicMood").elements().next().fieldNames().next());
			
			Boolean danceable = false;
			if (entities.has("MusicDanceable"))
				danceable = true;
			
			List<String> ls_author = new ArrayList<String>();
			Iterator<JsonNode> it_author = entities.get("MusicAuthor").elements();
			while(it_author.hasNext()) {
				ls_author.add(it_author.next().textValue());
			}
			
			MusicIntent mus = new MusicIntent(sent, ls_author, mood, tempo, energy, danceable);
			return mus;
		case SpecificTheme:
			SpecificThemeIntent sth = new SpecificThemeIntent(sent, ls);
			return sth;
		default:
			Intent in = new Intent(tipo, sent);
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
		if (nodo == null)
			return ls;
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
		        JsonNode query = new ObjectMapper().readTree(content);
		        JsonNode entities = query.get("prediction").get("entities");
		        Intent in = retrieveIntent(content);
		        System.out.println("ekere");
		        
		    } 
		    catch (IOException e) 
		    {
		        e.printStackTrace();
		    }
		    
		}
	*/
}
