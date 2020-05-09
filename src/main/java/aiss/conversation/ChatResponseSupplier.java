package aiss.conversation;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import aiss.resources.spotify.SpotifyResource;

public class ChatResponseSupplier {
	
	private static final Logger log = Logger.getLogger(ChatResponseSupplier.class.getName());
	private static HashMap<String, NodeList> localization;
	
	private static Random random;
	
	public static String[] getLocalizedResponse(String key)
	{
		if (localization == null)
			init();
		
		// Get phraselist
		NodeList phraselist = localization.get(key);
		
		// Pick random phrase
		Node phrase = phraselist.item(random.nextInt(phraselist.getLength()));
		
		// Get node content including <ret> tags
		String content = phrase.toString();
		
		// Get the separate messages
		String[] messages = content.replace("\n", "").split("<ret>");
		
		return messages;
		
		
	}
	
	private static void init()
	{
		random = new Random();
		
		String path = "webapp/chat/ChatResponses.xml";
		InputStream is = ChatResponseSupplier.class.getClassLoader().getResourceAsStream(path);
    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    	DocumentBuilder dBuilder;
    	Document doc;
    	try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(is);			
		} catch (SAXException | IOException e) {
			log.log(Level.SEVERE, "Error parsing ChatResponses XML file");
			e.printStackTrace();
			return;
		} catch (ParserConfigurationException e) {
			log.log(Level.SEVERE, "Error parsing ChatResponses XML file");
			e.printStackTrace();
			return;
		}

    	// Normalize document removing extra whitespace
    	doc.getDocumentElement().normalize();
    	
    	NodeList phraseLists = doc.getElementsByTagName("phraselist");
    	for (int i = 0; i < phraseLists.getLength(); i++) {
			Node node = phraseLists.item(i);
			String phraseListName = node.getAttributes().getNamedItem("name").getNodeValue();
			localization.put(phraseListName, node.getChildNodes());
		}
	}
	
}
