package org.stackoverflow.analysis.dao;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stackoverflow.analysis.datasource.CassandraDataSource;
import org.stackoverflow.analysis.model.CommentModel;
import org.stackoverflow.analysis.model.QuestionModel;
import org.stackoverflow.analysis.model.TagModel;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;

public class CommentDao {
	
	public static final CommentDao COMMENT_DAO = new CommentDao();
	
	transient Logger log = LoggerFactory.getLogger(this.getClass());
	
	public static CommentDao getInstance() {
		return COMMENT_DAO;
	}
	
	public boolean add(List<CommentModel> comments) {
		Session session = null;
		try {
			session = CassandraDataSource.getConnection();
			PreparedStatement insertStatement = session.prepare(
					"insert into comment(owner,can_flag,edited,score,post_type,creation_date,post_id,comment_id,link,body) "
					+ "values (?,?,?,?,?,?,?,?,?,?)"
						);
			log.info("Insert query prepared is :: {}",insertStatement.getQueryString());
			for(CommentModel comment : comments) {
				session.execute(
					insertStatement.bind(
							comment.getOwner(),
							comment.isCan_flag(),
							comment.isEdited(),
							(short)comment.getScore(),
							comment.getPost_type(),
							new Date(comment.getCreation_date() * 1000),
							comment.getPost_id(),
							comment.getComment_id(),
							comment.getLink(),
							comment.getBody()
							)
					);
			}
		}
		catch(Exception e) {
			log.error("Exception occured while inserting commens ",e);
			throw e;
		}
		finally {
			if(session != null) {
				log.info("Closing the cassandra session.");
				session.close();
			}
		}
		return true;
		
	}

}
