package it.skymedia.idolTunnel.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.skymedia.idolTunnel.IdolOEMConnection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class IdolOEMConnectionTest {
	
	static IdolOEMConnection ic; 
	@Before
	public void init() {
		ic = new IdolOEMConnection();
	}

	@After
	public void finish() {
		ic = null;
		System.gc();
	}
	
	@Test
	public void testAciRequest() throws Exception {
		Map<String, String> pList = new HashMap<String, String>();
		pList.put("action", "query");
		pList.put("text", "*");
		pList.put("anylanguage", "true");
		pList.put("maxresults", "25");
		pList.put("databasematch", "");
		pList.put("combine", "simple");
		pList.put("outputencoding", "utf8");
		pList.put("totalResults", "true");
		
		String expected = "{\"$\":\"SUCCESS\"}";
		
		String autnresponse = ic.aciRequest(pList, "json");
		assertTrue("should be in Json!", 
				   autnresponse.contains(expected));
		
		autnresponse = ic.aciRequest(pList, "xml");
		assertFalse("should not be in Json, but in XML!", 
			        autnresponse.contains(expected));
		//fail("Not yet implemented");
	}
	
//	@Test
//	public void testAutnResponseToDocument() throws Exception {
//		
//		Map<String, String> pList = new HashMap<String, String>();
//		pList.put("action", "query");
//		pList.put("text", "*");
//		pList.put("anylanguage", "true");
//		pList.put("maxresults", "25");
//		pList.put("databasematch", "");
//		pList.put("combine", "simple");
//		pList.put("outputencoding", "utf8");
//		pList.put("totalResults", "true");
//		
//		String expectedFirstNode = "autnresponse";
//		String expectedFirstNodeAttr = "http://schemas.autonomy.com/aci/";
//		String autnresponse = ic.aciRequest(pList, "xml");
//		Document documentResult = ic.responseToXMLDocument(autnresponse);
//		
//		assertTrue("Document shold start with <autnresponse> node!", documentResult.getFirstChild().getNodeName().equals(expectedFirstNode));
//		assertTrue("Document first node should contains attributes(1)!", documentResult.getFirstChild().hasAttributes());
//		assertTrue("Document <autnresponse> Attribute should be : http://schemas.autonomy.com/aci/ !", ((Element)documentResult.getFirstChild()).getAttribute("xmlns:autn").equals(expectedFirstNodeAttr));
//
//	}
	
	@Test
	public void testResponseNodeFromDocument() throws Exception {
		
		Map<String, String> pList = new HashMap<String, String>();
		pList.put("action", "query");
		pList.put("text", "*");
		pList.put("anylanguage", "true");
		pList.put("maxresults", "25");
		pList.put("databasematch", "");
		pList.put("combine", "simple");
		pList.put("outputencoding", "utf8");
		pList.put("totalResults", "true");
		
		String expected = "SUCCESS";
		String autnresponse = ic.aciRequest(pList, "xml");
		//Document documentResult = ic.responseToXMLDocument(autnresponse);
		String result = ic.getQueryResponse(autnresponse);
		
		assertTrue("RESPONSE-NODE should be SUCCESS!", result.equals(expected));
	}
	
	@Test
	public void testQueryHitsFromDocument() throws Exception {
		
		Map<String, String> pList = new HashMap<String, String>();
		pList.put("action", "query");
		pList.put("text", "*");
		pList.put("anylanguage", "true");
		pList.put("maxresults", "25");
		pList.put("databasematch", "");
		pList.put("combine", "simple");
		pList.put("outputencoding", "utf8");
		pList.put("totalResults", "true");
		
		int size = 25;
		String autnresponse = ic.aciRequest(pList, "xml");
		//Document documentResult = ic.responseToXMLDocument(autnresponse);
		ArrayList<HashMap<String, String>> listHits = ic.getQueryHitsMap(autnresponse);
		
		assertTrue("Result List Size should be 25!", listHits.size()== size);
		
	}
	

}
