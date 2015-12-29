package it.skymedia.idolTunnel;

import java.io.Serializable;
import java.util.HashMap;

public class Hit implements Serializable {
	
	private static final long serialVersionUID = -7051798146659018277L;
	
	/**
	 * the inner Map<String key, String value>, to be hidden to jaxb source generators
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

	/**
	 * Constructor
	 * @param map: a java.util.HashMap<String key, String value>
	 */
	public Hit(HashMap<String, String> map) {
		this.dreFields = map;
	}
	/**
	 * Constructor
	 * initializes the inner java.util.HashMap<String key, String value>
	 */
	public Hit() {
		new Hit( new HashMap<String, String>() );
	}

}
