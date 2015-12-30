package it.skymedia.idolTunnel.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.skymedia.idolTunnel.Hit;
import it.skymedia.idolTunnel.IdolOEMConnection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This @Test class is not part of the WS-Client, it doesnt access the endpoint
 * <br/>This is a test class which directly uses IdolOEMConnection class methods
 * <br/>This is intended for JUnit and Maven tests
 * <br/>
 * THIS ALWAYS SHOULD RETURN ALL-GREEN SIGNALS
 */
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
	public void testAutnResponseAsString() throws Exception {
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
		
		String autnresponse = ic.autnResponseAsString(pList, "json");
		assertTrue("should be in Json!", 
				   autnresponse.contains(expected));
		
		autnresponse = ic.autnResponseAsString(pList, "xml");
		assertFalse("should not be in Json, but in XML!", 
			        autnresponse.contains(expected));
		//fail("Not yet implemented");
	}
	
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
		String autnresponse = ic.autnResponseAsString(pList, "xml");
		//Document documentResult = ic.responseToXMLDocument(autnresponse);
		String result = ic.getQueryResponse(autnresponse);
		
		assertTrue("RESPONSE-NODE should be SUCCESS!", result.equals(expected));
	}
	
	@Test
	public void testAutnResponseAsList() throws Exception {
		
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
		ArrayList<Hit> listOfHits = ic.autnResponseAsList(pList, "xml");
		
		assertTrue("Result List Size should be 25!", listOfHits.size()== size);
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
		String autnresponse = ic.autnResponseAsString(pList, "xml");
		//Document documentResult = ic.responseToXMLDocument(autnresponse);
		ArrayList<Hit> listHits = ic.getQueryHitsMap(autnresponse);
		
		assertTrue("Result List Size should be 25!", listHits.size()== size);
		
	}
	
	@Test
	public void testGetSpellCheckFieldsFromDocument() throws Exception {
		
		Map<String, String> pList = new HashMap<String, String>();
		pList.put("action", "query");
		pList.put("text", "latitnte indagto ompuatto");
		pList.put("anylanguage", "true");
		pList.put("print", "noresults");
		pList.put("spellcheck", "true");
		pList.put("SpellCheckAlphaNumeric", "false");
		pList.put("outputencoding", "utf8");
		
		String autnresponse = ic.autnResponseAsString(pList, "xml");
		//Document documentResult = ic.responseToXMLDocument(autnresponse);
		String[] spellCheckFields = ic.getSpellCheckFields(autnresponse);
		
		assertTrue("Result should contains two not empty fields", 
				spellCheckFields.length > 1 
				&& spellCheckFields[1].length() > 0
				&& spellCheckFields[0].length() > 0 
		);
		
		//debug;
		System.out.println("\n\tSPELLCHECK FIELDS :\n" + 
				spellCheckFields[0] + "\n" + spellCheckFields[1]
		);
	}
	
	@Test
	public void testGetSpellCheckFieldsFromEmptyQuery() throws Exception {
		
		Map<String, String> pList = new HashMap<String, String>();
		pList.put("action", "query");
		pList.put("text", "*");
		pList.put("anylanguage", "true");
		pList.put("print", "noresults");
		pList.put("spellcheck", "true");
		pList.put("SpellCheckAlphaNumeric", "false");
		pList.put("outputencoding", "utf8");
		
		String autnresponse = ic.autnResponseAsString(pList, "xml");
		//Document documentResult = ic.responseToXMLDocument(autnresponse);
		String[] spellCheckFields = ic.getSpellCheckFields(autnresponse);
		
		assertTrue("Result should contains two not empty fields", 
				spellCheckFields.length > 1 
				&& spellCheckFields[1].length() < 1
				&& spellCheckFields[0].length() > 0 
		);
		
		//debug;
		System.out.println("\n\tSPELLCHECK FIELDS :\n" + 
				spellCheckFields[0] + "\n" + spellCheckFields[1]
		);
	}
	
	@Test
	public void testGetSpellCheck() throws Exception {
		
		Map<String, String> pList = new HashMap<String, String>();
		pList.put("action", "query");
		pList.put("text", "latitnte indagto ompuatto");
		pList.put("anylanguage", "true");
		pList.put("print", "noresults");
		pList.put("spellcheck", "true");
		pList.put("SpellCheckAlphaNumeric", "false");
		pList.put("outputencoding", "utf8");
		
		String[] spellCheckFields = ic.getSpellCheck(pList);
		
		assertTrue("Result should contains two not empty fields", 
				spellCheckFields.length > 1 
				&& spellCheckFields[1].length() > 0
				&& spellCheckFields[0].length() > 0 
		);
		
		//debug;
		System.out.println("\n\tSPELLCHECK FIELDS :\n" + 
				spellCheckFields[0] + "\n" + spellCheckFields[1]
		);
	}
	
	@Test
	public void testGetHitIndexedContent() throws Exception {
		
		Map<String, String> pList = new HashMap<String, String>();
		pList.put("action", "query");
		pList.put("text", "*");
		pList.put("maxresults", "10");
		pList.put("anylanguage", "true");
		pList.put("print", "indexText");
		pList.put("outputencoding", "utf8");
		
		String autnresponse = ic.autnResponseAsString(pList, "xml");
		ArrayList<Hit> hits = ic.getQueryHitsNoDocumentMap(autnresponse);
		
		assertTrue("Result should contains the autn:content not-empty field", 
				hits.size() <= 10 
				&& ic.getHitIndexedContent(hits.get(0).getDreFields()).length() > 0
		);
		
		//debug;
		System.out.println("\n\tINDEXED CONTENT:\n" + 
				ic.getHitIndexedContent(hits.get(0).getDreFields())
		);
	}
}
