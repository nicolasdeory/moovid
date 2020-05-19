package aiss.conversation;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ChatResponseSupplier {
	
	private static final Logger log = Logger.getLogger(ChatResponseSupplier.class.getName());
	private static HashMap<String, NodeList> localization;
	
	private static Random random;
	
	public static String[] getLocalizedResponse(String key)
	{
		if (localization == null)
		{
			try {
				init();
			} catch (XPathExpressionException | ParserConfigurationException e) {
				log.log(Level.SEVERE, "Error parsing chat response XML");
			}
		}
			
		
		// Get phraselist
		NodeList phraselist = localization.get(key);
		
		// Pick random phrase
		Node rsp = phraselist.item(random.nextInt(phraselist.getLength()));
		
		// Get the separate messages and store them in a list
		List<String> textNodes = new ArrayList<String>();
		
		NodeList rspNodes = rsp.getChildNodes();
		for(int j = 0; j < rspNodes.getLength(); j++)
		{
			Node subRsp = rspNodes.item(j);
			if (!subRsp.getNodeName().equals("ret"))
			{
				textNodes.add(subRsp.getTextContent().trim());
			}
		}

		// Return the messages as an array
		return textNodes.toArray(new String[0]);
		
		
	}
	
	private static void init() throws ParserConfigurationException, XPathExpressionException
	{
		random = new Random();
		
		Document doc = loadDocument();
    	
    	// Initialize dictionary
    	localization = new HashMap<>();
    
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression expr = xpath.compile("//phraselist");
		NodeList ns = (NodeList)expr.evaluate(doc, XPathConstants.NODESET);
		List<String> phraseLists = new ArrayList<String>();
		for(int i = 0; i<ns.getLength();i++)
		{
			phraseLists.add(ns.item(i).getAttributes().getNamedItem("name").getTextContent());
		}
		
		for(String phrList : phraseLists)
		{
			XPathExpression expr2 = xpath.compile("//phraselist[@name='" + phrList + "']/response");
			NodeList responses = (NodeList)expr2.evaluate(doc, XPathConstants.NODESET);
			localization.put(phrList, responses);
		}
	}
	
	public static Document loadDocument()
	{
		String path = "ChatResponses.xml";
		InputStream is = ChatResponseSupplier.class.getClassLoader().getResourceAsStream(path);
    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    	DocumentBuilder dBuilder;
    	Document doc;
    	try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(new InputSource(new InputStreamReader(is, "UTF-8")));			
		} catch (SAXException | IOException e) {
			log.log(Level.SEVERE, "Error parsing ChatResponses XML file");
			e.printStackTrace();
			return null;
		} catch (ParserConfigurationException e) {
			log.log(Level.SEVERE, "Error parsing ChatResponses XML file");
			e.printStackTrace();
			return null;
		}

    	// Normalize document removing extra whitespace
    	doc.getDocumentElement().normalize();
    	return doc;
	}
	
}
