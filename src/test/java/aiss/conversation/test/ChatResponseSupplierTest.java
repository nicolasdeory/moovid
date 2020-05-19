package aiss.conversation.test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import aiss.conversation.ChatResponseSupplier;

public class ChatResponseSupplierTest {
	
	private static Logger log = Logger.getLogger(ChatResponseSupplierTest.class.getName());
	
	@Test
	public void testParseXMLDoc() throws Exception{
		Document doc = ChatResponseSupplier.loadDocument();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
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
			System.out.println(phrList);
			XPathExpression expr2 = xpath.compile("//phraselist[@name='" + phrList + "']/response");
			NodeList responses = (NodeList)expr2.evaluate(doc, XPathConstants.NODESET);
			for(int i = 0; i<responses.getLength();i++)
			{
				Node rsp = responses.item(i);
				NodeList rspNodes = rsp.getChildNodes();
				for(int j = 0; j < rspNodes.getLength(); j++)
				{
					Node subRsp = rspNodes.item(j);
					if (subRsp.getNodeName().equals("ret"))
					{
						System.out.println("--ret--");
					}
					else
					{
						System.out.println(subRsp.getTextContent().trim());
					}
					
				}
				System.out.println("=====");
			}
		}
		
		
//    	for (int i = 0; i < phraseLists.getLength(); i++) {
//			Node node = phraseLists.item(i);
//			String phraseListName = node.getAttributes().getNamedItem("name").getNodeValue();
//			System.out.println(node.getChildNodes().toString());
//			System.out.println(node.getChildNodes().item(0).getTextContent());
//		}
	}
	
	@Test
	public void testGetRandomMessage() throws Exception {
		String[] randomResp = ChatResponseSupplier.getLocalizedResponse("goodbye-after-montage");
		System.out.println(String.join("--", randomResp));
	}
	
}
