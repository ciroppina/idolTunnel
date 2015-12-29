package it.skymedia.idolTunnel.jaxws.client;

import java.util.HashMap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.13
 * 2015-12-29T14:14:50.685+01:00
 * Generated source version: 2.7.13
 * 
 */
@WebService(targetNamespace = "http://idolTunnel.skymedia.it/", name = "IdolOEMConnection")
@XmlSeeAlso({ObjectFactory.class})
public interface IdolOEMConnection {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "aciRequest", targetNamespace = "http://idolTunnel.skymedia.it/", className = "it.skymedia.idolTunnel.jaxws.client.AciRequest")
    @WebMethod
    @ResponseWrapper(localName = "aciRequestResponse", targetNamespace = "http://idolTunnel.skymedia.it/", className = "it.skymedia.idolTunnel.jaxws.client.AciRequestResponse")
    public java.lang.String aciRequest(
        @WebParam(name = "arg0", targetNamespace = "")
        HashMap<String, String> arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "grl", targetNamespace = "http://idolTunnel.skymedia.it/", className = "it.skymedia.idolTunnel.jaxws.client.Grl")
    @WebMethod
    @ResponseWrapper(localName = "grlResponse", targetNamespace = "http://idolTunnel.skymedia.it/", className = "it.skymedia.idolTunnel.jaxws.client.GrlResponse")
    public java.lang.String grl(
        @WebParam(name = "arg0", targetNamespace = "")
        long arg0
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getHitIndexedContent", targetNamespace = "http://idolTunnel.skymedia.it/", className = "it.skymedia.idolTunnel.jaxws.client.GetHitIndexedContent")
    @WebMethod
    @ResponseWrapper(localName = "getHitIndexedContentResponse", targetNamespace = "http://idolTunnel.skymedia.it/", className = "it.skymedia.idolTunnel.jaxws.client.GetHitIndexedContentResponse")
    public java.lang.String getHitIndexedContent(
        @WebParam(name = "arg0", targetNamespace = "")
        HashMap<String, String> arg0
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getQueryResponse", targetNamespace = "http://idolTunnel.skymedia.it/", className = "it.skymedia.idolTunnel.jaxws.client.GetQueryResponse")
    @WebMethod
    @ResponseWrapper(localName = "getQueryResponseResponse", targetNamespace = "http://idolTunnel.skymedia.it/", className = "it.skymedia.idolTunnel.jaxws.client.GetQueryResponseResponse")
    public java.lang.String getQueryResponse(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getstatus", targetNamespace = "http://idolTunnel.skymedia.it/", className = "it.skymedia.idolTunnel.jaxws.client.Getstatus")
    @WebMethod
    @ResponseWrapper(localName = "getstatusResponse", targetNamespace = "http://idolTunnel.skymedia.it/", className = "it.skymedia.idolTunnel.jaxws.client.GetstatusResponse")
    public java.lang.String getstatus();

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getQueryHitsNoDocumentMap", targetNamespace = "http://idolTunnel.skymedia.it/", className = "it.skymedia.idolTunnel.jaxws.client.GetQueryHitsNoDocumentMap")
    @WebMethod
    @ResponseWrapper(localName = "getQueryHitsNoDocumentMapResponse", targetNamespace = "http://idolTunnel.skymedia.it/", className = "it.skymedia.idolTunnel.jaxws.client.GetQueryHitsNoDocumentMapResponse")
    public java.util.List<it.skymedia.idolTunnel.jaxws.client.Hit> getQueryHitsNoDocumentMap(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getversion", targetNamespace = "http://idolTunnel.skymedia.it/", className = "it.skymedia.idolTunnel.jaxws.client.Getversion")
    @WebMethod
    @ResponseWrapper(localName = "getversionResponse", targetNamespace = "http://idolTunnel.skymedia.it/", className = "it.skymedia.idolTunnel.jaxws.client.GetversionResponse")
    public java.lang.String getversion();

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getlicenseinfo", targetNamespace = "http://idolTunnel.skymedia.it/", className = "it.skymedia.idolTunnel.jaxws.client.Getlicenseinfo")
    @WebMethod
    @ResponseWrapper(localName = "getlicenseinfoResponse", targetNamespace = "http://idolTunnel.skymedia.it/", className = "it.skymedia.idolTunnel.jaxws.client.GetlicenseinfoResponse")
    public java.lang.String getlicenseinfo(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getSpellCheckFields", targetNamespace = "http://idolTunnel.skymedia.it/", className = "it.skymedia.idolTunnel.jaxws.client.GetSpellCheckFields")
    @WebMethod
    @ResponseWrapper(localName = "getSpellCheckFieldsResponse", targetNamespace = "http://idolTunnel.skymedia.it/", className = "it.skymedia.idolTunnel.jaxws.client.GetSpellCheckFieldsResponse")
    public java.util.List<java.lang.String> getSpellCheckFields(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getQueryHitsMap", targetNamespace = "http://idolTunnel.skymedia.it/", className = "it.skymedia.idolTunnel.jaxws.client.GetQueryHitsMap")
    @WebMethod
    @ResponseWrapper(localName = "getQueryHitsMapResponse", targetNamespace = "http://idolTunnel.skymedia.it/", className = "it.skymedia.idolTunnel.jaxws.client.GetQueryHitsMapResponse")
    public java.util.List<it.skymedia.idolTunnel.jaxws.client.Hit> getQueryHitsMap(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );
}
