/**
 * ArtistsWS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package artistsws.ws;

public interface ArtistsWS extends java.rmi.Remote {
    public void addArtist(java.lang.String artist) throws java.rmi.RemoteException;
    public java.lang.String[] findAllArtists() throws java.rmi.RemoteException;
}
