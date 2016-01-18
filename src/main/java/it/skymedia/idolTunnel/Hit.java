package it.skymedia.idolTunnel;

import java.io.Serializable;
import java.util.HashMap;

public class Hit implements Serializable {
	
	private static final long serialVersionUID = -7051798146659018277L;
	
	/**
	 * the inner Map<String key, String value>, to be hidden to jaxb source generators
	 * 
	 * It maps all the xml-autnresponse tags children of a <autn:hit>
	 */
	private HashMap<String, String> dreFields;

	/**
	 * Getter method that returns a copy of the inner private Map
	 * 
	 * @return: a Map of dreFileds
	 */
	public HashMap<String, String> getDreFields() {
		return dreFields;
	}

	public void setDreFields(HashMap<String, String> dreFields) {
		this.dreFields = dreFields;
	}
	
	private String DREREFERENCE = "";
	private String UUID = "";
	private String DREDATE = "";
	private String TIAP_CLASSEID = "";
	private String TIAP_CLASSE = "";
	private String TIAP_FASCICOLOID = "";
	private String TIAP_DOCUMENTOID = "";
	private String PAGECOUNT = "";
	private String DREDBNAME = "";
	private String TIAP_FASE = "";
	private String TIAP_DATA_NOTIFICA = "";
	private String INDEXEDCONTENT;
	private String SECTION;
	
	public String getSECTION() {
		return SECTION;
	}

	public void setSECTION(String sECTION) {
		SECTION = sECTION;
	}

	public void setDREREFERENCE(String dREREFERENCE) {
		DREREFERENCE = dREREFERENCE;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public void setDREDATE(String dREDATE) {
		DREDATE = dREDATE;
	}

	public void setTIAP_CLASSEID(String tIAP_CLASSEID) {
		TIAP_CLASSEID = tIAP_CLASSEID;
	}

	public void setTIAP_CLASSE(String tIAP_CLASSE) {
		TIAP_CLASSE = tIAP_CLASSE;
	}

	public void setTIAP_FASCICOLOID(String tIAP_FASCICOLO) {
		TIAP_FASCICOLOID = tIAP_FASCICOLO;
	}

	public void setPAGECOUNT(String pAGECOUNT) {
		PAGECOUNT = pAGECOUNT;
	}

	public void setDREDBNAME(String dREDBNAME) {
		DREDBNAME = dREDBNAME;
	}

	public void setTIAP_FASE(String tIAP_FASE) {
		TIAP_FASE = tIAP_FASE;
	}

	public void setTIAP_DATA_NOTIFICA(String tIAP_DATA_NOTIFICA) {
		TIAP_DATA_NOTIFICA = tIAP_DATA_NOTIFICA;
	}

	public void setINDEXEDCONTENT(String iNDEXEDCONTENT) {
		INDEXEDCONTENT = iNDEXEDCONTENT;
	}

	public String getINDEXEDCONTENT() {
		return INDEXEDCONTENT;
	}

	public String getDREREFERENCE() {
		return DREREFERENCE;
	}

	public String getUUID() {
		return UUID;
	}

	public String getDREDATE() {
		return DREDATE;
	}

	public String getTIAP_CLASSEID() {
		return TIAP_CLASSEID;
	}

	public String getTIAP_CLASSE() {
		return TIAP_CLASSE;
	}

	public String getTIAP_FASCICOLOID() {
		return TIAP_FASCICOLOID;
	}

	public String getPAGECOUNT() {
		return PAGECOUNT;
	}

	public String getDREDBNAME() {
		return DREDBNAME;
	}

	public String getTIAP_FASE() {
		return TIAP_FASE;
	}

	public String getTIAP_DATA_NOTIFICA() {
		return TIAP_DATA_NOTIFICA;
	}

	public String getTIAP_DOCUMENTOID() {
		return TIAP_DOCUMENTOID;
	}

	public void setTIAP_DOCUMENTOID(String tIAP_DOCUMENTOID) {
		TIAP_DOCUMENTOID = tIAP_DOCUMENTOID;
	}

	/**
	 * Constructor
	 * @param map: a java.util.HashMap<String key, String value>
	 */
	public Hit(HashMap<String, String> map) {
		this.dreFields = map;
		//INIT FIELDS
		this.INDEXEDCONTENT = map.get("autn:content") == null ? "" : map.get("autn:content");
		this.DREDATE = map.get("DREDATE") == null ? "" : map.get("DREDATE");
		this.UUID = map.get("UUID") == null ? "" : map.get("UUID");
		this.DREREFERENCE = map.get("DREREFERENCE") == null ? "" : map.get("DREREFERENCE");
		this.TIAP_CLASSEID = map.get("TIAP_CLASSEID") == null ? "" : map.get("TIAP_CLASSEID");
		this.TIAP_CLASSE = map.get("TIAP_CLASSE") == null ? "" : map.get("TIAP_CLASSE");
		this.DREDBNAME = map.get("DREDBNAME") == null ? "" : map.get("DREDBNAME");
		this.TIAP_FASCICOLOID = map.get("TIAP_FASCICOLOID") == null ? "" : map.get("TIAP_FASCICOLOID");
		this.PAGECOUNT = map.get("PAGECOUNT") == null ? "" : map.get("PAGECOUNT");
		this.TIAP_FASE = map.get("TIAP_FASE") == null ? "" : map.get("TIAP_FASE");
		this.TIAP_DOCUMENTOID = map.get("TIAP_DOCUMENTOID") == null ? "" : map.get("TIAP_DOCUMENTOID");
		this.TIAP_DATA_NOTIFICA = map.get("TIAP_DATA_NOTIFICA") == null ? "" : map.get("TIAP_DATA_NOTIFICA");
		this.SECTION = map.get("autn:section") == null ? "" : map.get("autn:section");
	}
	
	/**
	 * Constructor
	 * initializes the inner java.util.HashMap<String key, String value>
	 */
	public Hit() {
		new Hit( new HashMap<String, String>() );
	}

}
