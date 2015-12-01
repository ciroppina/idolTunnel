
package it.skymedia.idolTunnel.jaxws.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.skymedia.idolTunnel.jaxws.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GrlResponse_QNAME = new QName("http://idolTunnel.skymedia.it/", "grlResponse");
    private final static QName _Getstatus_QNAME = new QName("http://idolTunnel.skymedia.it/", "getstatus");
    private final static QName _GetstatusResponse_QNAME = new QName("http://idolTunnel.skymedia.it/", "getstatusResponse");
    private final static QName _Getversion_QNAME = new QName("http://idolTunnel.skymedia.it/", "getversion");
    private final static QName _AciRequestResponse_QNAME = new QName("http://idolTunnel.skymedia.it/", "aciRequestResponse");
    private final static QName _AciRequest_QNAME = new QName("http://idolTunnel.skymedia.it/", "aciRequest");
    private final static QName _Getlicenseinfo_QNAME = new QName("http://idolTunnel.skymedia.it/", "getlicenseinfo");
    private final static QName _GetversionResponse_QNAME = new QName("http://idolTunnel.skymedia.it/", "getversionResponse");
    private final static QName _Grl_QNAME = new QName("http://idolTunnel.skymedia.it/", "grl");
    private final static QName _GetlicenseinfoResponse_QNAME = new QName("http://idolTunnel.skymedia.it/", "getlicenseinfoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.skymedia.idolTunnel.jaxws.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AciRequest }
     * 
     */
    public AciRequest createAciRequest() {
        return new AciRequest();
    }

    /**
     * Create an instance of {@link AciRequest.Arg0 }
     * 
     */
    public AciRequest.Arg0 createAciRequestArg0() {
        return new AciRequest.Arg0();
    }

    /**
     * Create an instance of {@link Getversion }
     * 
     */
    public Getversion createGetversion() {
        return new Getversion();
    }

    /**
     * Create an instance of {@link AciRequestResponse }
     * 
     */
    public AciRequestResponse createAciRequestResponse() {
        return new AciRequestResponse();
    }

    /**
     * Create an instance of {@link GetversionResponse }
     * 
     */
    public GetversionResponse createGetversionResponse() {
        return new GetversionResponse();
    }

    /**
     * Create an instance of {@link Getlicenseinfo }
     * 
     */
    public Getlicenseinfo createGetlicenseinfo() {
        return new Getlicenseinfo();
    }

    /**
     * Create an instance of {@link Grl }
     * 
     */
    public Grl createGrl() {
        return new Grl();
    }

    /**
     * Create an instance of {@link GetlicenseinfoResponse }
     * 
     */
    public GetlicenseinfoResponse createGetlicenseinfoResponse() {
        return new GetlicenseinfoResponse();
    }

    /**
     * Create an instance of {@link GrlResponse }
     * 
     */
    public GrlResponse createGrlResponse() {
        return new GrlResponse();
    }

    /**
     * Create an instance of {@link Getstatus }
     * 
     */
    public Getstatus createGetstatus() {
        return new Getstatus();
    }

    /**
     * Create an instance of {@link GetstatusResponse }
     * 
     */
    public GetstatusResponse createGetstatusResponse() {
        return new GetstatusResponse();
    }

    /**
     * Create an instance of {@link AciRequest.Arg0 .Entry }
     * 
     */
    public AciRequest.Arg0 .Entry createAciRequestArg0Entry() {
        return new AciRequest.Arg0 .Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GrlResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://idolTunnel.skymedia.it/", name = "grlResponse")
    public JAXBElement<GrlResponse> createGrlResponse(GrlResponse value) {
        return new JAXBElement<GrlResponse>(_GrlResponse_QNAME, GrlResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Getstatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://idolTunnel.skymedia.it/", name = "getstatus")
    public JAXBElement<Getstatus> createGetstatus(Getstatus value) {
        return new JAXBElement<Getstatus>(_Getstatus_QNAME, Getstatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetstatusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://idolTunnel.skymedia.it/", name = "getstatusResponse")
    public JAXBElement<GetstatusResponse> createGetstatusResponse(GetstatusResponse value) {
        return new JAXBElement<GetstatusResponse>(_GetstatusResponse_QNAME, GetstatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Getversion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://idolTunnel.skymedia.it/", name = "getversion")
    public JAXBElement<Getversion> createGetversion(Getversion value) {
        return new JAXBElement<Getversion>(_Getversion_QNAME, Getversion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AciRequestResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://idolTunnel.skymedia.it/", name = "aciRequestResponse")
    public JAXBElement<AciRequestResponse> createAciRequestResponse(AciRequestResponse value) {
        return new JAXBElement<AciRequestResponse>(_AciRequestResponse_QNAME, AciRequestResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AciRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://idolTunnel.skymedia.it/", name = "aciRequest")
    public JAXBElement<AciRequest> createAciRequest(AciRequest value) {
        return new JAXBElement<AciRequest>(_AciRequest_QNAME, AciRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Getlicenseinfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://idolTunnel.skymedia.it/", name = "getlicenseinfo")
    public JAXBElement<Getlicenseinfo> createGetlicenseinfo(Getlicenseinfo value) {
        return new JAXBElement<Getlicenseinfo>(_Getlicenseinfo_QNAME, Getlicenseinfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetversionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://idolTunnel.skymedia.it/", name = "getversionResponse")
    public JAXBElement<GetversionResponse> createGetversionResponse(GetversionResponse value) {
        return new JAXBElement<GetversionResponse>(_GetversionResponse_QNAME, GetversionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Grl }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://idolTunnel.skymedia.it/", name = "grl")
    public JAXBElement<Grl> createGrl(Grl value) {
        return new JAXBElement<Grl>(_Grl_QNAME, Grl.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetlicenseinfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://idolTunnel.skymedia.it/", name = "getlicenseinfoResponse")
    public JAXBElement<GetlicenseinfoResponse> createGetlicenseinfoResponse(GetlicenseinfoResponse value) {
        return new JAXBElement<GetlicenseinfoResponse>(_GetlicenseinfoResponse_QNAME, GetlicenseinfoResponse.class, null, value);
    }

}
