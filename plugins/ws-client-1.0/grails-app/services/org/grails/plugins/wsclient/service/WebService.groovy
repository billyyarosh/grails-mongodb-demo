package org.grails.plugins.wsclient.service

import groovyx.net.ws.WSClient
import groovyx.net.ws.cxf.SoapVersion

/**
 * A Webservice client using the cxf-framework dynamic client factory.
 * @author Mihai CAZACU (cazacugmihai [at] gmail [dot] com)
 */
class WebService {

    boolean transactional = false

    /**
     * @param wsdlLocation The url of the wsdl-file
     */
    def getClient(String wsdlLocation) {
        def proxy = new WSClient(wsdlLocation, this.class.classLoader)
        proxy.initialize()
        proxy
    }

    /**
     * @param wsdlLocation The url of the wsdl-file
     * @param soapVersion The preferred SOAP version
     */
    def getClient(String wsdlLocation, SoapVersion soapVersion) {
        def proxy = new WSClient(wsdlLocation, this.class.classLoader, soapVersion)
        proxy.initialize()
        proxy
    }

}