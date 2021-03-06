package it.skymedia.idolTunnel.jaxws.client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.13
 * 2016-01-18T18:43:23.419+01:00
 * Generated source version: 2.7.13
 * 
 */
@WebServiceClient(name = "IdolOEMConnection", 
                  wsdlLocation = "http://localhost:8080/geagaci/acitunnel/IdolOEMConnection/IdolOEMConnection?wsdl",
                  targetNamespace = "http://idolTunnel.skymedia.it/") 
public class IdolOEMConnection_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://idolTunnel.skymedia.it/", "IdolOEMConnection");
    public final static QName IdolOEMConnectionPort = new QName("http://idolTunnel.skymedia.it/", "IdolOEMConnectionPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/geagaci/acitunnel/IdolOEMConnection/IdolOEMConnection?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(IdolOEMConnection_Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://localhost:8080/geagaci/acitunnel/IdolOEMConnection/IdolOEMConnection?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public IdolOEMConnection_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public IdolOEMConnection_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public IdolOEMConnection_Service() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public IdolOEMConnection_Service(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public IdolOEMConnection_Service(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public IdolOEMConnection_Service(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns IdolOEMConnection
     */
    @WebEndpoint(name = "IdolOEMConnectionPort")
    public IdolOEMConnection getIdolOEMConnectionPort() {
        return super.getPort(IdolOEMConnectionPort, IdolOEMConnection.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IdolOEMConnection
     */
    @WebEndpoint(name = "IdolOEMConnectionPort")
    public IdolOEMConnection getIdolOEMConnectionPort(WebServiceFeature... features) {
        return super.getPort(IdolOEMConnectionPort, IdolOEMConnection.class, features);
    }

}
