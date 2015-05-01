/**
 * ArtistsWSServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package artistsws.ws;

public class ArtistsWSServiceLocator extends org.apache.axis.client.Service implements artistsws.ws.ArtistsWSService {

    public ArtistsWSServiceLocator() {
    }


    public ArtistsWSServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ArtistsWSServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ArtistsWS
    private java.lang.String ArtistsWS_address = "http://www.muzickagroznica.com/ArtistsWS/services/ArtistsWS";

    public java.lang.String getArtistsWSAddress() {
        return ArtistsWS_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ArtistsWSWSDDServiceName = "ArtistsWS";

    public java.lang.String getArtistsWSWSDDServiceName() {
        return ArtistsWSWSDDServiceName;
    }

    public void setArtistsWSWSDDServiceName(java.lang.String name) {
        ArtistsWSWSDDServiceName = name;
    }

    public artistsws.ws.ArtistsWS getArtistsWS() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ArtistsWS_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getArtistsWS(endpoint);
    }

    public artistsws.ws.ArtistsWS getArtistsWS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            artistsws.ws.ArtistsWSSoapBindingStub _stub = new artistsws.ws.ArtistsWSSoapBindingStub(portAddress, this);
            _stub.setPortName(getArtistsWSWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setArtistsWSEndpointAddress(java.lang.String address) {
        ArtistsWS_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (artistsws.ws.ArtistsWS.class.isAssignableFrom(serviceEndpointInterface)) {
                artistsws.ws.ArtistsWSSoapBindingStub _stub = new artistsws.ws.ArtistsWSSoapBindingStub(new java.net.URL(ArtistsWS_address), this);
                _stub.setPortName(getArtistsWSWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ArtistsWS".equals(inputPortName)) {
            return getArtistsWS();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws.artistsws", "ArtistsWSService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ws.artistsws", "ArtistsWS"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ArtistsWS".equals(portName)) {
            setArtistsWSEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
