package net.etfbl.muzickagroznica.service;

import net.etfbl.muzickagroznica.model.dao.GenreDao;
import net.etfbl.muzickagroznica.model.entities.Genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContentService {

	@Autowired
	GenreDao genreDao;
	
	public ContentService() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	public Genre addGenre(String name){
		Genre genre = new Genre(name);
		genreDao.persist(genre);
		return genre;
		
	}
	
	@Transactional
	public List<Genre> findAllGenres(){
		return genreDao.findAll();
	}
	
}
