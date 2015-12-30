
package it.skymedia.idolTunnel.jaxws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per grl complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="grl">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "grl", propOrder = {
    "arg0"
})
public class Grl {

    protected long arg0;

    /**
     * Recupera il valore della proprieta arg0.
     * 
     */
    public long getArg0() {
        return arg0;
    }

    /**
     * Imposta il valore della proprieta arg0.
     * 
     */
    public void setArg0(long value) {
        this.arg0 = value;
    }

}
