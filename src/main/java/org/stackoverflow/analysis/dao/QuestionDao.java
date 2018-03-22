package org.stackoverflow.analysis.dao;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stackoverflow.analysis.datasource.CassandraDataSource;
import org.stackoverflow.analysis.model.QuestionModel;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;

public class QuestionDao {
	
	public static final QuestionDao QUESTION_DAO = new QuestionDao();
	
	transient Logger log = LoggerFactory.getLogger(this.getClass());
	
	public static QuestionDao getInstance() {
		return QUESTION_DAO;
	}
	
	public boolean add(List<QuestionModel> questions) {
		Session session = null;
		try {
			session = CassandraDataSource.getConnection();
			PreparedStatement insertStatement = session.prepare(
					"insert into question("
					+ "tags,owner,can_close,can_flag,comment_count,delete_vote_count,reopen_vote_count,close_vote_count,is_answered,"
					+ "view_count,favorite_count,down_vote_count,up_vote_count,accepted_answer_id,answer_count,score,last_activity_date,"
					+ "creation_date,question_id,share_link,link,title,body,last_edit_date, closed_reason, closed_date) "
					+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
						);
			log.info("Insert query prepared is :: {}",insertStatement.getQueryString());
			for(QuestionModel question : questions) {
				session.execute(
					insertStatement.bind(
							question.getTags(),
							question.getOwner(),
							question.isCan_close(),
							question.isCan_flag(),
							(short)question.getComment_count(),
							(short)question.getDelete_vote_count(),
							(short)question.getReopen_vote_count(),
							(short)question.getClose_vote_count(),
							question.isIs_answered(),
							(short)question.getView_count(),
							(short)question.getFavorite_count(),
							(short)question.getDown_vote_count(),
							(short)question.getUp_vote_count(),
							question.getAccepted_answer_id(),
							(short)question.getAnswer_count(),
							(short)question.getScore(),
							new Date(question.getLast_activity_date() * 1000),
							new Date(question.getCreation_date() * 1000),
							question.getQuestion_id(),
							question.getShare_link(),
							question.getLink(),
							question.getTitle(),
							question.getBody(),
							new Date(question.getLast_edit_date() * 1000),
							question.getClosed_reason(),
							new Date(question.getClosed_date() * 1000)
							)
					);
			}
		}
		catch(Exception e) {
			log.error("Exception occured while inserting questions ",e);
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
