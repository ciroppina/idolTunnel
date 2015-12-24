##########################################################
#                                                        #
#            HP Autonomy Idol Server                     #
#            JAVA ACI APIs Web-Service                   #
#            for OEM Licensed servers                    #
#                                                        #
#            by ciroBorrelli                             #
#            Skymedia Srl, Carsoli AQ IT                 #
#            @CopyRight 2015 - 2020                      #
#                                                        #
##########################################################

This is an Eclipse Maven artifacted (maven jee7) project
useful as a tunnel to send ACI actions to HP IDOL Server
aciport [default 9000] over the http/https protocol

The configuration properties are contained in the:
- idol_config/idolTunnel.properties file
that should be put under the standard JVM user.dir path,
ie: [jboss_install_dir/bin/applconf]/idol_config
ie: [apache_tomacat_dir/bin/applconf]/idol_config
ie: [myeclipse_workspace/myproject]/idol_config

The config properties file contains:
- the encryption key for OEM communications with
  IDOL Server licensedwith an OEM licensekey.dat
- the host IP
- the ACI port
- a flag indicating if the IDOL Server is OEM'ed

The project deploys a war application named:
- idolTunnel.war
with all deopendencies included in the WEB-INF/lib/

The project contains a SOAP Java WS web-service whose
deployed WSDL has the following url format:
- http://[localhost]:[8080]/geagaci/acitunnel/IdolOEMConnection/IdolOEMConnection?wsdl

Also, the project contains a JUnit test class for all the
web-service public methods:
- it.skymedia.idolTunnel.test.IdolOEMConnectionTest.java

and a sample of a ws-client that tests all the exposed
operations:
- it.skymedia.idolTunnel.test.ws.clientsample.ClientSample.java

The main @WebMethod (SOAP Operation) has the signature:
/**
 * accepts every ACI action and the return format: XML or ByteArray
 * format MUST be: "xml" OR "json" (json meaning every else 
 * byte format), it means the desired response format
 * pList is a NahMap of IDOL ACI-acceptable parameter/value
 * @return a String containing the XML/Json/Text response
 */
@WebMethod(operationName="aciRequest")
public String aciRequest(Map<String, String> pList, String format);

The folder mvn-provided/* contains a couple of jars
and their relative mvn-install Windows commands, in 
order to install such jars in the .m2 (maven 3) repo
