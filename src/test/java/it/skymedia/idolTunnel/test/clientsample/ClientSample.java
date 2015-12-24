package it.skymedia.idolTunnel.test.clientsample;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.skymedia.idolTunnel.jaxws.client.*;

public class ClientSample {

	public static void main(String[] args) {
		Map<String, String> pList = new HashMap<String, String>();
		pList.put("action", "query");
		pList.put("text", "*");
		pList.put("anylanguage", "true");
		pList.put("maxresults", "25");
		pList.put("databasematch", "");
		pList.put("combine", "simple");
		pList.put("outputencoding", "utf8");
		pList.put("totalResults", "true");
		
	        System.out.println("***********************");
	        System.out.println("Create Web Service Client...");
	        IdolOEMConnection_Service service1 = new IdolOEMConnection_Service();
	        System.out.println("Create Web Service...");
	        IdolOEMConnection port1 = service1.getIdolOEMConnectionPort();
	        System.out.println("Call Web Service Operation...");
	        System.out.println("Server said: " + port1.aciRequest(pList, "json"));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getstatus());
	        System.out.println("Server said: " + port1.grl(10L));
	        System.out.println("Server said: " + port1.getversion());
	        System.out.println("Server said: " + port1.getlicenseinfo("json"));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        pList = new HashMap<String, String>();
			pList.put("action", "categorySuggestFromText");
			pList.put("queryText", "Tribunale di Nola - Informativa di reato");
			pList.put("anylanguage", "true");
			pList.put("outputencoding", "utf8");

	        System.out.println("Create Web Service...");
	        IdolOEMConnection port2 = service1.getIdolOEMConnectionPort();
	        System.out.println("Call Web Service Operation...");
	        System.out.println("Server said: " + port2.aciRequest(pList, "xml"));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getstatus());
	        System.out.println("Server said: " + port2.grl(50L));
	        System.out.println("Server said: " + port2.getversion());
	        System.out.println("Server said: " + port2.getlicenseinfo("xml"));
	        //Please input the parameters instead of 'null' for the upper method!
	        
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
			String autnresponse = ic.aciRequest(pList, "xml");
			System.out.println("Server said: " + ic.getQueryResponse(autnresponse) );
			
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
			
			autnresponse = ic.aciRequest(pList, "xml");
			List<HashMap<String, String>> listHits = ic.getQueryHitsMap(autnresponse);
			System.out.println("Server said: size should be 25: " + listHits.size() );
	
	        System.out.println("***********************");
	        System.out.println("Call Over!");
	}
}
