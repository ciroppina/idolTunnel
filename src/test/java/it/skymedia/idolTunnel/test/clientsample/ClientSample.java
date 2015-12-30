package it.skymedia.idolTunnel.test.clientsample;

import it.skymedia.idolTunnel.jaxws.client.Hit;
import it.skymedia.idolTunnel.jaxws.client.IdolOEMConnection;
import it.skymedia.idolTunnel.jaxws.client.IdolOEMConnection_Service;

import java.util.HashMap;
import java.util.List;

/**
 * DEMO Java App to show how to use idoltunnel JAXB-CXF SOAP WebService
 *  A simple maim method uses Java Map(s) to send action to a Autonomy IDOL 10 Server, behind the web service
 *  <br/>
 *  Notice: this App is provided with a Java implementation of the web-service client (WAR webapp)
 *  and a wsdl descriptor located in the WebContent/wsdl/ folder
 *  
 * @author Administrator
 *
 */
public class ClientSample {

	/**
	 * WebService-Client App launchable main
	 * <br/>
	 * Lot of action calls to an listening HP IDOL Server
	 */
	public static void main(String[] args) {
		
		//test port1
		IdolOEMConnection_Service service1 = actionsToSOAPPort1();

		//test port2
		actionsToSOAPPort2(service1);
        
		//build port ic
		IdolOEMConnection ic = actionsToSOAPPortIc(service1);
		
		//actions to port ic
		anotherGroupOfActionToSOAPPortIc(ic);

		//action with spellCheck
		aCallToSpellCheckFunction(ic);
		
		//action with print=indexText (only one content String)
		ic = new IdolOEMConnection_Service().getIdolOEMConnectionPort();
		aQueryWithPrintIndexTextFunction(ic);
		
        System.out.println("***********************");
        System.out.println("Call Over!");
        /**
         * console output ends here
         */
	}

	/**
	 * an ACTION=QUERY adding param print=indexText, that returns a String containing 
	 * all the text content of all indexed fields, of a <hit>
	 * <br/>
	 * Notice that the the indexed text is contained in the <autn:content> tag, without
	 *  further nested tags inside
	 *  
	 * @return a String (the text content)
	 * 
	 * @param ic
	 */
	public static void aQueryWithPrintIndexTextFunction(IdolOEMConnection ic) {
		HashMap<String, String> pList;
		String autnresponse;
		
		//new Objects-based method call
		pList = new HashMap<String, String>();
		pList.put("action", "query");
		pList.put("text", "imputato indagato latitante");
		pList.put("anylanguage", "true");
		pList.put("print", "indexText");
		pList.put("maxresults", "10");
		pList.put("outputencoding", "utf8");
		
		autnresponse = ic.autnResponseAsString(pList, "xml");
		List<Hit> listOfHits = ic.getQueryHitsNoDocumentMap(autnresponse);
		String indexedContent = ic.getHitIndexedContent(listOfHits.get(0).getDreFields());
		System.out.println("Server said: Indexed Content: \n" + indexedContent );
	}
	
	/**
	 * an ACTION=QUERY adding text with uncorrect words and spellCheck=true
	 * <br/>
	 * Notice that the the getSpellCheckFields web-method accepts the XML-Only autnresponse
	 * and returns a Java ArrayList< String >, where:
	 * - the 1st List item is String of corrected italian words (autn:spelling)
	 * - the 2nd List item is the newly-corrected query, for next query purpose (autn:spellingQuery)
	 * 
	 * @param ic
	 */
	public static void aCallToSpellCheckFunction(IdolOEMConnection ic) {
		HashMap<String, String> pList;
		String autnresponse;

		//new Objects-based method call
		pList = new HashMap<String, String>();
		pList.put("action", "query");
		pList.put("text", "latitnte ingadato impuatto");
		pList.put("anylanguage", "true");
		pList.put("spellcheck", "true");
		pList.put("print", "noresults");
		pList.put("outputencoding", "utf8");
		
		autnresponse = ic.autnResponseAsString(pList, "xml");
		List<String> list = ic.getSpellCheckFields(autnresponse);
		System.out.println("Server said: spellCheck fields: \n" 
			+ list.get(0)+"\n"
			+ list.get(1));
	}
	
	/**
	 * another ACTION=QUERY to show how to get the Java List of <AUTNRESPONSE>...<autn:hits> 
	 * from the SOAP port ic
	 * <br/>
	 * Notice that the the getQueryHitsMap web-method accepts the XML-Only autnresponse
	 * and returns a Java ArrayList< Hit >, where:
	 * - every List item is a <autn:hit>, that contains
	 * - one Java Map of IDOL FIELDS (kay=tagName, value=tagText)
	 * 
	 * @param ic
	 */
	public static void anotherGroupOfActionToSOAPPortIc(IdolOEMConnection ic) {
		HashMap<String, String> pList;
		String autnresponse;

		//new Objects-based method call
		pList = new HashMap<String, String>();
		pList.put("action", "query");
		pList.put("text", "*");
		pList.put("anylanguage", "true");
		pList.put("maxresults", "25");
		pList.put("databasematch", "");
		pList.put("combine", "simple");
		pList.put("outputencoding", "utf8");
		pList.put("totalResults", "true");
		
		autnresponse = ic.autnResponseAsString(pList, "xml");
		List<Hit> listHits = ic.getQueryHitsMap(autnresponse);
		System.out.println("Server said: size should be 25: " + listHits.size() );
	}

	/**
     * now preparing to send further ACTION=QUERY to get an AUTNRESPONSE to be parsed
	 * <br/>
	 * THIS method gets the AUTNRESPONSE from the SOAP port ic
	 * <br/>
	 * Notice that the the getQueryResponse web-method accepts the XML-Only autnresponse
	 * and returns a String such as: FAILED, SUCCESS, ERROR
	 *
	 * @param service1
	 * @return a JaxWS IdolOEMConnection
	 */
	public static IdolOEMConnection actionsToSOAPPortIc(
			IdolOEMConnection_Service service1) {
		HashMap<String, String> pList;

		//new Objects-based method call
        pList = new HashMap<String, String>();
		pList.put("action", "query");
		pList.put("text", "*");
		pList.put("anylanguage", "true");
		pList.put("maxresults", "25");
		pList.put("databasematch", "");
		pList.put("combine", "simple");
		pList.put("outputencoding", "utf8");
		pList.put("totalResults", "true");
		
		IdolOEMConnection ic = service1.getIdolOEMConnectionPort();
		/**
		 */
		String autnresponse = ic.autnResponseAsString(pList, "xml");
		System.out.println("Server said: " + ic.getQueryResponse(autnresponse) );
		return ic;
	}

	/**
     * prepares to query a ACTION=CategorySuggestFromText to IDOL 10 Server
     * several actions to test multiple queries to the SOAP port2
     * <br/>
     * Notice that the aciRequest web-method accepts a return format, default is "xml"
     */
	public static void actionsToSOAPPort2(IdolOEMConnection_Service service1) {
		HashMap<String, String> pList;
		
        pList = new HashMap<String, String>();
		pList.put("action", "categorySuggestFromText");
		pList.put("queryText", "Tribunale di Nola - Informativa di reato");
		pList.put("anylanguage", "true");
		pList.put("outputencoding", "utf8");

        System.out.println("Create Web Service...");
        IdolOEMConnection port2 = service1.getIdolOEMConnectionPort();
        System.out.println("Call Web Service Operation...");

        System.out.println("Server said: " + port2.autnResponseAsString(pList, "xml"));

        System.out.println("Server said: " + port2.getstatus());
        System.out.println("Server said: " + port2.grl(50L));
        System.out.println("Server said: " + port2.getversion());
        System.out.println("Server said: " + port2.getlicenseinfo("xml"));
	}

	/**
	 * prepares the map of http params for a IDOL ACTION=QUERY, maresults 25
	 * response will return in JSon format
	 * <br/>
     * using the JAXB-CXF generated ws stub: IdolOEMConnection_Service
     * <br/>
	 * then, sending the ACTION and getting a JSON formatted response
	 * <br/>
	 * sending other ACTIONS to the SOAP port1, to test multiple types
     * <br/>Notice that ACTION GETLICENSEINFO accepts both "xml" or "json" return formats
     * <br/>Notice that ACTION GRL accepts the Long number of last-logs to be returned
	 */
	public static IdolOEMConnection_Service actionsToSOAPPort1() {
		
		HashMap<String, String> pList = new HashMap<String, String>();
		pList.put("action", "query");
		pList.put("text", "*");
		pList.put("anylanguage", "true");
		pList.put("maxresults", "25");
		pList.put("databasematch", "");
		pList.put("combine", "simple");
		pList.put("outputencoding", "utf8");
		pList.put("totalResults", "true");
		
		/**
		 * console output starts here
		 */
        System.out.println("***********************");
        System.out.println("Create Web Service Client...");

        IdolOEMConnection_Service service1 = new IdolOEMConnection_Service();
        System.out.println("Create Web Service...");
        IdolOEMConnection port1 = service1.getIdolOEMConnectionPort();
        System.out.println("Call Web Service Operation...");
       
        System.out.println("Server said: " + port1.autnResponseAsString(pList, "json"));

        System.out.println("Server said: " + port1.getstatus());
        System.out.println("Server said: " + port1.grl(10L));
        System.out.println("Server said: " + port1.getversion());
        System.out.println("Server said: " + port1.getlicenseinfo("json"));
		return service1;
	}
}
