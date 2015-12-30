
package it.skymedia.idolTunnel.jaxws.client;

import java.util.HashMap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per hit complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="hit">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DREDATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DREDBNAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DREREFERENCE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dreFields">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="INDEXEDCONTENT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PAGECOUNT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SECTION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TIAP_CLASSE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TIAP_CLASSEID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TIAP_DATA_NOTIFICA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TIAP_FASCICOLO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TIAP_FASE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TIAP_RG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UUID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "hit", propOrder = {
    "dredate",
    "dredbname",
    "drereference",
    "dreFields",
    "indexedcontent",
    "pagecount",
    "section",
    "tiapclasse",
    "tiapclasseid",
    "tiapdatanotifica",
    "tiapfascicolo",
    "tiapfase",
    "tiaprg",
    "uuid"
})
public class Hit {

    @XmlElement(name = "DREDATE")
    protected String dredate;
    @XmlElement(name = "DREDBNAME")
    protected String dredbname;
    @XmlElement(name = "DREREFERENCE")
    protected String drereference;
    @XmlElement(required = true)
    protected HashMap<String, String > dreFields;
    @XmlElement(name = "INDEXEDCONTENT")
    protected String indexedcontent;
    @XmlElement(name = "PAGECOUNT")
    protected String pagecount;
    @XmlElement(name = "SECTION")
    protected String section;
    @XmlElement(name = "TIAP_CLASSE")
    protected String tiapclasse;
    @XmlElement(name = "TIAP_CLASSEID")
    protected String tiapclasseid;
    @XmlElement(name = "TIAP_DATA_NOTIFICA")
    protected String tiapdatanotifica;
    @XmlElement(name = "TIAP_FASCICOLO")
    protected String tiapfascicolo;
    @XmlElement(name = "TIAP_FASE")
    protected String tiapfase;
    @XmlElement(name = "TIAP_RG")
    protected String tiaprg;
    @XmlElement(name = "UUID")
    protected String uuid;

    /**
     * Recupera il valore della proprieta dredate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDREDATE() {
        return dredate;
    }

    /**
     * Imposta il valore della proprieta dredate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDREDATE(String value) {
        this.dredate = value;
    }

    /**
     * Recupera il valore della proprieta dredbname.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDREDBNAME() {
        return dredbname;
    }

    /**
     * Imposta il valore della proprieta dredbname.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDREDBNAME(String value) {
        this.dredbname = value;
    }

    /**
     * Recupera il valore della proprieta drereference.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDREREFERENCE() {
        return drereference;
    }

    /**
     * Imposta il valore della proprieta drereference.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDREREFERENCE(String value) {
        this.drereference = value;
    }

    /**
     * Recupera il valore della proprieta dreFields.
     * 
     * @return
     *     possible object is
     *     {@link HashMap<String, String > }
     *     
     */
    public HashMap<String, String > getDreFields() {
        return dreFields;
    }

    /**
     * Imposta il valore della proprieta dreFields.
     * 
     * @param value
     *     allowed object is
     *     {@link HashMap<String, String > }
     *     
     */
    public void setDreFields(HashMap<String, String > value) {
        this.dreFields = value;
    }

    /**
     * Recupera il valore della proprieta indexedcontent.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINDEXEDCONTENT() {
        return indexedcontent;
    }

    /**
     * Imposta il valore della proprieta indexedcontent.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINDEXEDCONTENT(String value) {
        this.indexedcontent = value;
    }

    /**
     * Recupera il valore della proprieta pagecount.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPAGECOUNT() {
        return pagecount;
    }

    /**
     * Imposta il valore della proprieta pagecount.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPAGECOUNT(String value) {
        this.pagecount = value;
    }

    /**
     * Recupera il valore della proprieta section.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSECTION() {
        return section;
    }

    /**
     * Imposta il valore della proprieta section.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSECTION(String value) {
        this.section = value;
    }

    /**
     * Recupera il valore della proprieta tiapclasse.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTIAPCLASSE() {
        return tiapclasse;
    }

    /**
     * Imposta il valore della proprieta tiapclasse.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTIAPCLASSE(String value) {
        this.tiapclasse = value;
    }

    /**
     * Recupera il valore della proprieta tiapclasseid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTIAPCLASSEID() {
        return tiapclasseid;
    }

    /**
     * Imposta il valore della proprieta tiapclasseid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTIAPCLASSEID(String value) {
        this.tiapclasseid = value;
    }

    /**
     * Recupera il valore della proprieta tiapdatanotifica.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTIAPDATANOTIFICA() {
        return tiapdatanotifica;
    }

    /**
     * Imposta il valore della proprieta tiapdatanotifica.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTIAPDATANOTIFICA(String value) {
        this.tiapdatanotifica = value;
    }

    /**
     * Recupera il valore della proprieta tiapfascicolo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTIAPFASCICOLO() {
        return tiapfascicolo;
    }

    /**
     * Imposta il valore della proprieta tiapfascicolo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTIAPFASCICOLO(String value) {
        this.tiapfascicolo = value;
    }

    /**
     * Recupera il valore della proprieta tiapfase.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTIAPFASE() {
        return tiapfase;
    }

    /**
     * Imposta il valore della proprieta tiapfase.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTIAPFASE(String value) {
        this.tiapfase = value;
    }

    /**
     * Recupera il valore della proprieta tiaprg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTIAPRG() {
        return tiaprg;
    }

    /**
     * Imposta il valore della proprieta tiaprg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTIAPRG(String value) {
        this.tiaprg = value;
    }

    /**
     * Recupera il valore della proprieta uuid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUUID() {
        return uuid;
    }

    /**
     * Imposta il valore della proprieta uuid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUUID(String value) {
        this.uuid = value;
    }

}
