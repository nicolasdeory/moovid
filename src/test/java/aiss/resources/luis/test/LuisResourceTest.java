package aiss.resources.luis.test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

import aiss.model.luis.classes.Intent;
import aiss.model.luis.classes.MontageCreateIntent;
import aiss.model.luis.classes.MusicIntent;
import aiss.model.luis.enumerates.IntentType;
import aiss.model.luis.enumerates.MontageTheme;
import aiss.model.luis.enumerates.MusicAcoustic;
import aiss.model.luis.enumerates.MusicDanceable;
import aiss.model.luis.enumerates.MusicEnergy;
import aiss.model.luis.enumerates.MusicMood;
import aiss.model.luis.enumerates.MusicTempo;
import aiss.model.luis.enumerates.Sentiment;
import aiss.resources.luis.LuisResource;

public class LuisResourceTest {

	private static Logger log = Logger.getLogger(LuisResourceTest.class.getName());

	@Test
	public void getIntentFromQueryTest() throws Exception{
		
		String query1 = "Hola,%20que%20tal";
		String query2 = "Quiero%20un%20montaje%20de%20animales%20con%20fotos"
				+ "%20de%20las%20ultimas%20tres%20semanas";
		String query3 = "Una%20cancion%20potente%20de%20Metallica";
		
		Intent int1 = LuisResource.getIntentFromQuery(query1);
		Intent int2 = LuisResource.getIntentFromQuery(query2);
		Intent int3 = LuisResource.getIntentFromQuery(query3);
		
		Intent test1 = new Intent(IntentType.Greeting);
		List<MontageTheme> themes = new ArrayList<MontageTheme>();
		themes.add(MontageTheme.animals);
		LocalDate end = LocalDate.now();
		LocalDate start = end.minusWeeks(3);
		Intent test2 = new MontageCreateIntent(Sentiment.neutral, themes, start, end);
		
		List<String> autores = new ArrayList<String>();
		autores.add("Metallica");
		List<String> generos = new ArrayList<String>();
		Intent test3 = new MusicIntent(Sentiment.neutral, autores, generos,
				MusicMood.none, MusicTempo.none, MusicEnergy.highenergy,
				MusicDanceable.none, MusicAcoustic.none);
		
		log.log(Level.INFO, "Results at LuisResourceTest.getIntentFromQueryTest(): ");
		log.log(Level.INFO, "Expected result: (" + test1 + ")\nActual result: (" + int1 + ")");
		log.log(Level.INFO, "Expected result: (" + test2 + ")\nActual result: (" + int2 + ")");
		log.log(Level.INFO, "Expected result: (" + test3 + ")\nActual result: (" + int3 + ")");
		assertEquals(int1, test1);
		assertEquals(int2, test2);
		assertEquals(int3, test3);
	}
}
