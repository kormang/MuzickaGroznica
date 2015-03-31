package net.etfbl.muzickagroznica.model.dao;

import net.etfbl.muzickagroznica.model.entities.Comment;

import java.util.List;

public interface CommentDao {
	
	void persist(Comment comment);
	Comment merge(Comment comment);
	void delete(Comment comment);
	Comment findById(int id);
	List<Comment> findByExample(Comment comment);
	List<Comment> findForMusicContent(Integer musicContentId);
	
}
