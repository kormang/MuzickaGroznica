package artistsws.ws;

public class ArtistsWS {

	public ArtistsWS() {
		// TODO Auto-generated constructor stub
	}

	public void addArtist(String artist){
		//Artist artistEntity = new Artist(artist);
		//artistDao.persist(artistEntity);
		System.err.println("Service method call");
	}

	public String[] findAllArtists(){
	//	List<Artist> arts = artistDao.findAll();
	//	String[] ret = new String[arts.size()];
	//	int i = 0;
	//	for(Artist a : arts){
	//		ret[i++] = a.getName();
	//	}
	//	return ret;
		System.err.println("Service method call 2");
		return new String[] {"dsc"};
	}
	
}
