package aiss.resources.luis;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.restlet.resource.ClientResource;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import aiss.model.luis.classes.Intent;
import aiss.model.luis.classes.MontageCreateIntent;
import aiss.model.luis.classes.MontageThemeIntent;
import aiss.model.luis.classes.MusicIntent;
import aiss.model.luis.classes.SpecificThemeIntent;
import aiss.model.luis.enumerates.IntentType;
import aiss.model.luis.enumerates.MontageTheme;
import aiss.model.luis.enumerates.MusicAcoustic;
import aiss.model.luis.enumerates.MusicDanceable;
import aiss.model.luis.enumerates.MusicEnergy;
import aiss.model.luis.enumerates.MusicMood;
import aiss.model.luis.enumerates.MusicTempo;
import aiss.model.luis.enumerates.Sentiment;
import aiss.resources.spotify.SpotifyResource;
import jdk.internal.jline.internal.Log;

public class LuisResource {

	private static final Logger log = Logger.getLogger(LuisResource.class.getName());
	
	public static Intent getIntentFromQuery(String query) throws IOException
	{
		return getIntentFromJson(getQueryPrediction(query));
	}
	
	public static String getQueryPrediction(String message) {
		/*String uri = "https://westus.api.cognitive.microsoft.com/"
				+ "luis/prediction/v3.0/apps/b9e1fc9e-e095-4050-8786-ca9d2c7034de/"
				+ "slots/staging/predict?subscription-key=8e8e367a952f4a15aff9a3d36a272063&verbose=false"
				+ "&show-all-intents=true&log=true&query=\"";
		uri += message + "\"";*/
		String uri = "https://westus.api.cognitive.microsoft.com/luis/prediction/v3.0/apps/b9e1fc9e-e095-4050-8786-ca9d2c7034de/slots/staging/predict?subscription-key=8e8e367a952f4a15aff9a3d36a272063&verbose=false&show-all-intents=true&log=true&query=";
		uri += message;
		ClientResource cr = new ClientResource(uri);
		return cr.get(String.class);
	}
	
	public static Intent getIntentFromJson(String json) throws IOException {
		JsonNode query = new ObjectMapper().readTree(json);
		JsonNode entities = query.get("prediction").get("entities");
		String s = query.get("prediction").get("topIntent").textValue();
		IntentType tipo = IntentType.valueOf(s);
		JsonNode nodo_themes = entities.get("MontageTheme");
		List<MontageTheme> ls = retrieveThemes(nodo_themes , new ArrayList<MontageTheme>());
		// Sentiment is unused anyway
		Sentiment sent = null;
		//String sentimentString = query.get("prediction").get("sentiment").get("label").textValue();
		//Sentiment sent = Sentiment.valueOf(sentimentString);
		
		Intent intn;
		switch(tipo) { // TODO: Add remaning intents
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
			
			MusicDanceable danceable = MusicDanceable.none;
			if (entities.has("MusicDanceable"))
				danceable = MusicDanceable.yes;
			
			MusicAcoustic acoustic = MusicAcoustic.none;
			if (entities.has("MusicAcoustic"))
				acoustic = MusicAcoustic.yes;
			
			List<String> ls_author = new ArrayList<String>();
			if (entities.get("MusicAuthor") != null)
			{
				Iterator<JsonNode> it_author = entities.get("MusicAuthor").elements();
				while(it_author.hasNext())
					ls_author.add(it_author.next().textValue());
			}
			
			List<String> ls_genre = new ArrayList<String>();
			if (entities.get("MusicGenre") != null)
			{
				Iterator<JsonNode> it_genre = entities.get("MusicGenre").elements();
				while(it_genre.hasNext()) {
					String genre = it_genre.next().textValue();
					ls_genre.add(parseGenre(genre));
				}
			}
			
			MusicIntent mus = new MusicIntent(sent, ls_author, ls_genre, mood,
					tempo, energy, danceable, acoustic);
			return mus;
		case SpecificTheme:
			SpecificThemeIntent sth = new SpecificThemeIntent(sent, ls);
			return sth;
		case CommunicationCancel:
			intn = new Intent(tipo);
			return intn;
		case CommunicationConfirm:
			intn = new Intent(tipo);
			return intn;
		case DecideForMe:
			intn = new Intent(tipo);
			return intn;
		case Greeting:
			intn = new Intent(tipo);
			return intn;
		case Thanks:
			intn = new Intent(tipo);
			return intn;
		case HelpIntent:
			intn = new Intent(tipo);
			return intn;
		case Insult:
			intn = new Intent(tipo);
			return intn;
		case No:
			intn = new Intent(tipo);
			return intn;
		case None:
			intn = new Intent(tipo);
			return intn;
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
				end = start;
			}
			
			else if (nodo.has("other-day")){
				end = LocalDate.now().toString();
				start = LocalDate.now().minusWeeks(2).toString();
			}
		}
		if(!end.equals("") && !start.equals("")) {
			res.add(LocalDate.parse(start));
			res.add(LocalDate.parse(end));
		}
		else {
			res.add(null);
			res.add(null);
		}
		
		return res;
	}
	
	private static String parseGenre(String genre) {
		//TODO: JSON with genre map
		return genre;
	}
}
