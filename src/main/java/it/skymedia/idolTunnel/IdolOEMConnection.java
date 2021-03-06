package it.skymedia.idolTunnel;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.autonomy.aci.client.services.AciConstants;
import com.autonomy.aci.client.services.AciService;
import com.autonomy.aci.client.services.impl.AciServiceImpl;
import com.autonomy.aci.client.services.impl.ByteArrayProcessor;
import com.autonomy.aci.client.services.impl.DocumentProcessor;
import com.autonomy.aci.client.transport.AciParameter;
import com.autonomy.aci.client.transport.AciServerDetails;
import com.autonomy.aci.client.transport.impl.AciHttpClientImpl;
import com.autonomy.aci.client.transport.impl.BteaEncryptionCodec;
import com.autonomy.aci.client.util.AciParameters;

/**
 * An extremely basic OEM-key cyphered tunnel, useful
 * to connect to IDOL Server, by using Java ACI NG APIs.
 */
@WebService(serviceName="IdolOEMConnection")
@Stateless
@Startup
public class IdolOEMConnection {
	
	/**
	 * relative path to the config properties file
	 */
	private static String SYSTEM_PROPERTY_CONFIG_PATH = "idol.config_dir";
	/**
	 * the properties file containing configuration parameters
	 */
	private static String DEFAULT_CONFIG_PATH = "idol_config";
	private static String PROPERTY_FILENAME ="idolTunnel.properties";
	/**
	 * properties means configuration paramenters
	 */
	private static Properties properties;
	private static String CONFIG_BASE; 
	private static final Logger log = Logger.getLogger( IdolOEMConnection.class.getName() );
	private static String encryptionKey = "";

	/**
	 * Initialization of static properties
	 */
	static {
		//debug: firstly prints some jvm system properties
		Properties p = System.getProperties();
		log.info("\tuser.dir =" + p.get("user.dir"));
//		Enumeration keys = p.keys();
//		while (keys.hasMoreElements()) {
//		  String key = (String)keys.nextElement();
//		  String value = (String)p.get(key);
//		  System.out.println(key + ": " + value);
//		}
		
		try {
			String configFilePath = System.getProperty(SYSTEM_PROPERTY_CONFIG_PATH);
			if (configFilePath == null || configFilePath.isEmpty()) configFilePath = DEFAULT_CONFIG_PATH;
			
			/*La path del file di properties dell'appplicazione da adesso è nella java.user.dir */
			CONFIG_BASE = System.getProperty("user.dir") + File.separator + configFilePath + File.separator + PROPERTY_FILENAME;
			log.info("\tCONFIG_BASE is: " + CONFIG_BASE);
			
			properties = new Properties();
			//properties.load(getClass().getResourceAsStream("/config.properties"));
			properties.load(getPathAsStreamFrom(CONFIG_BASE));
		} catch (IllegalArgumentException iae) {
			System.out.println(iae.getLocalizedMessage());
			System.out.println("IAE: Occorre specificare la variabile di sistema: " + SYSTEM_PROPERTY_CONFIG_PATH + ", che deve contenere la config_path dell'applicazione");
		} catch (NullPointerException npe) {
			System.out.println(npe.getLocalizedMessage());
			System.out.println("NPE: Occorre specificare la variabile di sistema: " + SYSTEM_PROPERTY_CONFIG_PATH + ", che deve contenere la config_path dell'applicazione");
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println("IOE: Occorre specificare la variabile di sistema: " + SYSTEM_PROPERTY_CONFIG_PATH + ", che deve contenere la config_path dell'applicazione");
		}
	};

	/**
	 * Ritorna un FileInputStream relativo al file di configurazione dell'applicazione
	 * @param eConfPath
	 * @return an InputStream representing the reader from a filesystem config file
	 */
	private static FileInputStream getPathAsStreamFrom(String eConfPath) {
		if(eConfPath.isEmpty()) {
			return null;
		}
		try {
			FileInputStream fis = new FileInputStream(new File(eConfPath));
			return fis;
		} catch(Exception e) {
			return null;
		}
	}
	
    /**
     * aciService means the connection to Autonomy IDOL Server
     */
	private AciService aciService = null;

	/**
	 * Constructor: initialization of non-static properties, 
	 * then testing comnection and some service actions to an HP IDOL Server
	 */
	public IdolOEMConnection() {
        final String host = StringUtils.defaultIfEmpty(properties.getProperty("host"), "localhost");
        final int aciPort = NumberUtils.toInt(properties.getProperty("aciPort"), 9000);
        //final int indexPort = NumberUtils.toInt(properties.getProperty("indexPort"), 9001);
        
        try {
			AciServerDetails sd = new AciServerDetails(host, aciPort); 
	        if (Boolean.parseBoolean((String) properties.get("isOEMLicensed"))) {
	            encryptionKey = (String) properties.get("encryptionKey");
				sd.setEncryptionCodec(new BteaEncryptionCodec(encryptionKey) );
	        }

			// Construct the AciService to use...
            aciService = new AciServiceImpl(
            	new AciHttpClientImpl(new DefaultHttpClient()), sd );

            /* TEST ACI CONNECTION */
    		System.out.println("You have successfully communicated to an " + getversion() );

            /* try action=GetLicenseInfo */
    		System.out.println("\n\t*** License Info ***\n" + getlicenseinfo("json") );

    		// try action=GetStatus...
            System.out.println("\n\t*** GetStatus of the server ***\n" + getstatus());

            /* try action=GetRequestsLog */
    		System.out.println("\n\t*** GRL ***\n" + grl(50L) );
        }
        catch(Exception e) {
            log.severe(e.getLocalizedMessage() + "\n");
            e.printStackTrace();
        }
    }

	/**
	 * requests the action=grl for the more recent 'tail' requests
	 * 
	 * @return: String - an HTML log page
	 */
	@WebMethod(operationName = "grl")
	public String grl(long tail) {
		AciParameters parms = new AciParameters();
		parms.add(new AciParameter("action", "grl") );
		parms.add(new AciParameter("openLinks", "on") );
		parms.add(new AciParameter("tail", tail) );
		//response = this.aciService.executeAction(parms, new DocumentProcessor());

		// executing action...
		String autnResponse = jsonResponse(this.aciService.executeAction(parms, new ByteArrayProcessor()) ); 
		return autnResponse;
	}

	/**
	 * executes the IDOL Server GetVersion action 
	 * @return a String containing the Json compact response
	 * 
	 * @throws XPathExpressionException
	 */
	@WebMethod(operationName = "getversion")
	public String getversion() {
		// We'll issue a GetVersion action and use the supplied DOM Document processor to process the response...
		Document response = this.aciService.executeAction(new AciParameters(AciConstants.ACTION_GET_VERSION), new DocumentProcessor());
		// Use XPath to pull out the value of the field the contains the type of the ACI server...
		try {
			final XPath xpath = XPathFactory.newInstance().newXPath();
			return (String) xpath.evaluate("/autnresponse/responsedata/producttypecsv", response, XPathConstants.STRING)
				 + ", version: "
				 + (String) xpath.evaluate("/autnresponse/responsedata/version", response, XPathConstants.STRING)
				 + " - "
				 + (String) xpath.evaluate("/autnresponse/responsedata/aciversion", response, XPathConstants.STRING);
		} catch (XPathExpressionException xpe) {
			xpe.printStackTrace();
			return "IdolOEMTunnel - getversion: Error Occurred - not a valid XML autnresponse";
		} catch (Exception e) {
			e.printStackTrace();
			return "IdolOEMTunnel - getversion: unexpected Error Occurred";
		}
	}

	/**
	 * executes the IDOL Server GetLicenseInfo action 
	 * <br/>
	 * accepts the response format in: XML or Json
	 * @return a String containing the XML/Json compact response
	 * 
	 * @throws XPathExpressionException
	 */
	@WebMethod(operationName="getlicenseinfo")
	public String getlicenseinfo(String format) {
		// We'll issue a GetStatus action and use the supplied DOM/Json Document processor to process the response...
		AciParameters parms = new AciParameters(AciConstants.ACTION_GET_LICENSE_INFO);
		parms.add(new AciParameter(AciConstants.PARAM_RESPONSE_FORMAT, format) );
		//response = this.aciService.executeAction(parms, new DocumentProcessor());

		try {
			// executing action...
			String autnResponse = format.toLowerCase().equals("xml") ? 
				xmlResponse(this.aciService.executeAction(parms, new DocumentProcessor()) ): // XML
				jsonResponse(this.aciService.executeAction(parms, new ByteArrayProcessor()) ); // Json
			return autnResponse;
		} catch (Exception e) {
			e.printStackTrace();
			return "IdolOEMTunnel - getlicenseinfo: unexpected Error Occurred";
		}
	}

	/**
	 * executes the IDOL Server GetStatus action 
	 * <br/>
	 * accepts the response format in: XML or Json
	 * 
	 * @return a String containing the XML/Json compact response
	 */
	@WebMethod(operationName="getstatus")
	public String getstatus() {
		// We'll issue a GetStatus action and use the supplied DOM/Json Document processor to process the response...
		AciParameters parms = new AciParameters(AciConstants.ACTION_GET_STATUS);
		parms.add(new AciParameter(AciConstants.PARAM_TEMPLATE, "GetStatusForm") );
		parms.add(new AciParameter(AciConstants.PARAM_FORCE_TEMPLATE_REFRESH, "true") );
		//parms.add(new AciParameter(AciConstants.PARAM_RESPONSE_FORMAT, format) );

		// executing action...
		String autnResponse = jsonResponse(this.aciService.executeAction(parms, new ByteArrayProcessor()) );
		return autnResponse;
	}
	
	/**
	 * accepts every ACI action and the return format: XML or Json
	 * 
	 * @return a List<Hit> containing one or more Hit objects (autn:hit)
	 */
	@WebMethod(operationName="autnResponseAsList")
	public ArrayList<Hit> autnResponseAsList(Map<String, String> pList, String format) {
		String autnresposne = autnResponseAsString(pList, format);
		ArrayList<Hit> result = getQueryHitsMap(autnresposne);
		return result;
	}
	
	/**
	 * accepts every ACI action and the return format: XML or Json
	 * 
	 * @return a String containing the XML/Json compact response
	 */
	@WebMethod(operationName="autnResponseAsString")
	public String autnResponseAsString(Map<String, String> pList, String format) {
		if (! isActionGood(pList)) return "Badly Formatted Request";
		
		AciParameters parms = new AciParameters();
		for (String key : pList.keySet()) {
			parms.add(new AciParameter(key, pList.get(key)));
		}
		parms.add(new AciParameter(AciConstants.PARAM_RESPONSE_FORMAT, format) );

		try {
			if (format == null || format.length() < 3) format = "xml";
			// executing action...
			String autnResponse = format.toLowerCase().equals("xml") ? 
				xmlResponse(this.aciService.executeAction(parms, new DocumentProcessor()) ): // XML
				jsonResponse(this.aciService.executeAction(parms, new ByteArrayProcessor()) ); // Json
			return new String(autnResponse);	
		} catch (Exception e) {
			e.printStackTrace();
			return "IdolOEMTunnel - aciRequest: unexpected Error Occurred - maybe Badly formatted ACI action params?";
		}
	}

	
	/**
	 * Method that catches response node from xml autnresponse
	 * 
	 * @return a XML Document
	 */
	@WebMethod(operationName="getQueryResponse")
	public String getQueryResponse(String xml){
		String result = "";
		Document document = getDocumentFrom(xml);
		
		NodeList response = document.getElementsByTagName("response");
		Node resp = response.item(0);
		
		result = resp.getFirstChild().getNodeValue();
		return result ;
	}
	
	/**
	 * Method that returns  spellcheck nodes from the xml autnresponse
	 * <br/>
	 * Notice that the proper action should have: spellCheck=true 
	 * and print=noresults params
	 * 
	 * @return an String[], represented by a List<String> in the WSDL
	 */
	@WebMethod(operationName="getSpellCheck")
	public String[] getSpellCheck(Map<String, String> action) {
		String autnresponse = this.autnResponseAsString(action, "xml");
		return getSpellCheckFields(autnresponse);
	}
	
	/**
	 * Method that extracts spellcheck nodes from the xml autnresponse
	 * 
	 * @return an String[], represented by a List<String> in the WSDL
	 */
	@WebMethod(operationName="getSpellCheckFields")
	public String[] getSpellCheckFields(String xml){
		String[] result = {"spellcheck returned no corrected terms", ""};
		Document document = getDocumentFrom(xml);
		
		NodeList corrections = document.getElementsByTagName("autn:spelling");
		if (corrections == null || corrections.item(0) == null) return result;
		NodeList correctedQuery = document.getElementsByTagName("autn:spellingquery");
		if (correctedQuery == null || correctedQuery.item(0) == null) return result;
		
		result[0] = "forse intedevi: " + corrections.item(0).getFirstChild().getNodeValue();
		result[1] = "cerca invece: " + correctedQuery.item(0).getFirstChild().getNodeValue();
		return result;
	}
	
	/**
	 * Method that extracts spellcheck nodes from the xml autnresponse
	 * 
	 * @return an String[], represented by a List<String> in the WSDL
	 */
	@WebMethod(operationName="getHitIndexedContent")
	public String getHitIndexedContent(Map<String, String> hit){
		String result = "no content indexed";
		if (hit == null || hit.isEmpty() || hit.size() < 1) return result;
		
		if (hit.get("autn:content") != null)
			result = hit.get("autn:content") ;

		return result ;
	}
	
	/**
	 * Method that returns a List<Map> where every map contains hit's fields 
	 *  and the autn:content text, BUT it do not contains <DOCUMENT> structure !
	 *  <br/>
	 *  Notice that: this method M^UST be invoked only for a print=indexText query !
	 *  
	 * @return ArrayList<Hit> list of Hit, each containing a Map (of dreFields)
	 * 
	 */
	@WebMethod(operationName="getQueryHitsNoDocumentMap")
	public ArrayList<Hit> getQueryHitsNoDocumentMap(String xml){
		ArrayList<Hit> result = new ArrayList<Hit>();
		Document document = getDocumentFrom(xml);

		NodeList temp = null;
		temp = document.getElementsByTagName("response");
		String response = (temp.getLength() > 0) ? document.getElementsByTagName("response").item(0).getTextContent() : "FAILURE";
		temp = document.getElementsByTagName("autn:numhits");
		String numHits = (temp.getLength() > 0) ? document.getElementsByTagName("autn:numhits").item(0).getTextContent() : "0";
		temp = document.getElementsByTagName("autn:totalhits");
		String totalHits = (temp.getLength() > 0) ? document.getElementsByTagName("autn:totalhits").item(0).getTextContent() : "0";

		NodeList hits = document.getElementsByTagName("autn:hit");

		for(int i=0; i<hits.getLength(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			Node nodo = hits.item(i);
			NodeList hitChilds = nodo.getChildNodes();
			
			for(int j=0;j<hitChilds.getLength(); j++){
				Node n = hitChilds.item(j);
				if(n.getNodeType() == Node.ELEMENT_NODE) {
					Element e2 = (Element) n;
					if(!e2.getNodeName().equals("autn:content")){
						String value = "";
						if(map.containsKey(e2.getNodeName())){
							value = map.get(e2.getNodeName()) + "," + e2.getTextContent();
							map.put(e2.getNodeName(), value);
						}
						else{
							map.put(e2.getNodeName(), e2.getTextContent());
						}
						
					} else {
						if(e2.getNodeType() == Node.ELEMENT_NODE) {
							String nodeValue = e2.getFirstChild().getTextContent();
							//Element el = (Element) content;
							String value= "";
							if(map.containsKey(e2.getNodeName())){
								value = map.get(e2.getNodeName()) + "," + nodeValue;
								map.put(e2.getNodeName(), value);
							}
							else{
								map.put(e2.getNodeName(), nodeValue);
							}
						}
					}
				}
			}
			Hit hit=new Hit(map);
			hit.getDreFields().put("response", response);
			hit.getDreFields().put("numhits", numHits);
			hit.getDreFields().put("totalhits", totalHits);
			
			result.add(hit);
		}
		return result;
	}
	
	/**
	 * Method return List<Map> when every map contains hit fields 
	 * 
	 * @return ArrayList<Hit> list of Hit, each containing a Map (of dreFileds)
	 * 
	 */
	@WebMethod(operationName="getQueryHitsMap")
	public ArrayList<Hit> getQueryHitsMap(String xml){
		ArrayList<Hit> result = new ArrayList<Hit>();
		Document document = getDocumentFrom(xml);

		NodeList temp = null;
		temp = document.getElementsByTagName("response");
		String response = (temp.getLength() > 0) ? document.getElementsByTagName("response").item(0).getTextContent() : "FAILURE";
		temp = document.getElementsByTagName("autn:numhits");
		String numHits = (temp.getLength() > 0) ? document.getElementsByTagName("autn:numhits").item(0).getTextContent() : "0";
		temp = document.getElementsByTagName("autn:totalhits");
		String totalHits = (temp.getLength() > 0) ? document.getElementsByTagName("autn:totalhits").item(0).getTextContent() : "0";

		NodeList hits = document.getElementsByTagName("autn:hit");

		for(int i=0; i<hits.getLength(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			Node nodo = hits.item(i);
			NodeList hitChilds = nodo.getChildNodes();
			
			for(int j=0;j<hitChilds.getLength(); j++){
				Node n = hitChilds.item(j);
				if(n.getNodeType() == Node.ELEMENT_NODE) {
					Element e2 = (Element) n;
					if(!e2.getNodeName().equals("autn:content")){
						String value = "";
						if(map.containsKey(e2.getNodeName())){
							value = map.get(e2.getNodeName()) + "," + e2.getTextContent();
							map.put(e2.getNodeName(), value);
						}
						else{
							map.put(e2.getNodeName(), e2.getTextContent());
						}
						
					} else {
						NodeList content = e2.getElementsByTagName("DOCUMENT").item(0).getChildNodes();
					
						for(int z=0; z<content.getLength();z++){
							Node d = content.item(z);
							if(d.getNodeType() == Node.ELEMENT_NODE) {
								Element el = (Element) d;
								String value= "";
								if(map.containsKey(el.getNodeName())){
									value = map.get(el.getNodeName()) + "," + el.getTextContent();
									map.put(el.getNodeName(), value);
								}
								else{
									map.put(el.getNodeName(), el.getTextContent());
								}
							}
						}
					}
				}
			}
			Hit hit=new Hit(map);
			hit.getDreFields().put("response", response);
			hit.getDreFields().put("numhits", numHits);
			hit.getDreFields().put("totalhits", totalHits);
			
			result.add(hit);
		}
		return result;
	}
	
	private Document getDocumentFrom(String xml){
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance(); 
		DocumentBuilder builder = null;
		Document document = null;
		try {
			InputStream is = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
			builder = documentFactory.newDocumentBuilder();
			document = builder.parse(is);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return document;
	}
	
	
	/**
	 * checks if action parameter exists
	 */
	private boolean isActionGood(Map<String, String> pList) {
		String[] cases = {
			"action",
			"a",
			"Action",
			"ACTION"
		};
		for (String key : cases) {
			if (pList.containsKey(key)) return true;
		}
		return false;
	}

	/**
	 * @param autnresponse
	 * @return the ACI autnresponse in Json compact format or 
	 * any other byte-array format
	 */
	private String jsonResponse(byte[] autnresponse) {
		String result="";
		result = new String(autnresponse);
		return result;
	}

	/**
	 * @param response
	 * @return the ACI aurtnresponse in XML compact format
	 */
	private String xmlResponse(Document response) {	
		String result ="";
		StreamResult xmlOutput = new StreamResult(new StringWriter());
		Transformer transformer;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.transform(new DOMSource(response.getDocumentElement()), xmlOutput);
			result = xmlOutput.getWriter().toString();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
			return "Error occurred parsing XML-formatted autnresponse - Badly formatted request";
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
			return "Error occurred parsing XML-formatted autnresponse - Badly formatted request";
		} catch (TransformerException e) {
			e.printStackTrace();
			return "Error occurred parsing XML-formatted autnresponse - Badly formatted request";
		}
		return result;
	}

	/**
	 * a main useful application for debug
	 * @param args
	 * @throws IOException
	 */
    public static void main(final String[] args) throws IOException {
    	new IdolOEMConnection();
    }

}

