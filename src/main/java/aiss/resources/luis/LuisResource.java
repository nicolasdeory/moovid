package aiss.resources.luis;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import aiss.model.luis.classes.Intent;
import aiss.model.luis.classes.MontageCreateIntent;
import aiss.model.luis.classes.MontageThemeIntent;
import aiss.model.luis.classes.MusicIntent;
import aiss.model.luis.classes.SpecificThemeIntent;
import aiss.model.luis.enumerates.IntentType;
import aiss.model.luis.enumerates.MontageTheme;
import aiss.model.luis.enumerates.MusicEnergy;
import aiss.model.luis.enumerates.MusicMood;
import aiss.model.luis.enumerates.MusicTempo;
import aiss.model.luis.enumerates.Sentiment;

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
			List<LocalDate> ls_date = retrieveDateRange(entities);
			MontageCreateIntent mcr = new MontageCreateIntent(sent, ls, ls_date.get(0), ls_date.get(1));
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
	
	private static List<LocalDate> retrieveDateRange(JsonNode nodo) {
		List<LocalDate> res = new ArrayList<LocalDate>();
		String start = "";
		String end = "";
		if (nodo.has("datetimeV2") || (nodo.has("DateRange") && !nodo.get("DateRange").elements().next().isNull())) {
			String tipo = nodo.findValue("type").textValue();
			JsonNode resolution = nodo.findValue("resolution");
			
			if (tipo.equals("daterange")) {
				start = resolution.elements().next().get("start").textValue();
				end = resolution.elements().next().get("end").textValue();
				
			}
			
			else if (tipo.equals("date")) {
				start = resolution.elements().next().get("value").textValue();
				end = resolution.elements().next().get("value").textValue();
			}
			
			else {
				end = LocalDate.now().toString();
				start = LocalDate.now().minusWeeks(2).toString();
			}
		}
		
		else {
			end = LocalDate.now().toString();
			start = LocalDate.now().minusWeeks(2).toString();
		}
		res.add(LocalDate.parse(start));
		res.add(LocalDate.parse(end));
		return res;
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
		        System.out.println("test");
		        
		    } 
		    catch (IOException e) 
		    {
		        e.printStackTrace();
		    }
		    
		}
	*/
}
