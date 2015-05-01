package artistsws.ws;

public class ArtistsWSProxy implements artistsws.ws.ArtistsWS {
  private String _endpoint = null;
  private artistsws.ws.ArtistsWS artistsWS = null;
  
  public ArtistsWSProxy() {
    _initArtistsWSProxy();
  }
  
  public ArtistsWSProxy(String endpoint) {
    _endpoint = endpoint;
    _initArtistsWSProxy();
  }
  
  private void _initArtistsWSProxy() {
    try {
      artistsWS = (new artistsws.ws.ArtistsWSServiceLocator()).getArtistsWS();
      if (artistsWS != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)artistsWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)artistsWS)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (artistsWS != null)
      ((javax.xml.rpc.Stub)artistsWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public artistsws.ws.ArtistsWS getArtistsWS() {
    if (artistsWS == null)
      _initArtistsWSProxy();
    return artistsWS;
  }
  
  public boolean addArtist(java.lang.String artist) throws java.rmi.RemoteException{
    if (artistsWS == null)
      _initArtistsWSProxy();
    return artistsWS.addArtist(artist);
  }
  
  public java.lang.String[] findAllArtists() throws java.rmi.RemoteException{
    if (artistsWS == null)
      _initArtistsWSProxy();
    return artistsWS.findAllArtists();
  }
  
  
}