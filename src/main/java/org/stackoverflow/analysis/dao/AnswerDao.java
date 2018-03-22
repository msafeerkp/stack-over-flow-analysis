package org.stackoverflow.analysis.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.spi.LoggerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stackoverflow.analysis.datasource.CassandraDataSource;
import org.stackoverflow.analysis.model.AnswerModel;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;

public class AnswerDao {
	
	public static final AnswerDao ANSWER_DAO = new AnswerDao();
	
	transient Logger log = LoggerFactory.getLogger(this.getClass());
	
	public static AnswerDao getInstance() {
		return ANSWER_DAO;
	}
	
	public boolean add(List<AnswerModel> answerModels) throws Exception{
		Session session = null;
		try {
			session = CassandraDataSource.getConnection();
			PreparedStatement insertStatement = session.prepare(
					"insert into answer"
					+ " (tags, owner, can_flag, comment_count, down_vote_count, up_vote_count, is_accepted, score, last_activity_date, " + 
					"creation_date,answer_id, question_id, title, body, last_edit_date)" + 
					" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
						);
			log.info("Insert query prepared is :: {}",insertStatement.getQueryString());
			for(AnswerModel answerModel : answerModels) {
				session.execute(
					insertStatement.bind(
							answerModel.getTags(),
							answerModel.getOwner(),
							answerModel.isCan_flag(),
							(short)answerModel.getComment_count(),
							(short)answerModel.getDown_vote_count(),
							(short)answerModel.getUp_vote_count(),
							answerModel.isIs_accepted(),
							(short)answerModel.getScore(),
							new Date(answerModel.getLast_activity_date() * 1000),
							new Date(answerModel.getCreation_date() * 1000),
							answerModel.getAnswer_id(),
							answerModel.getQuestion_id(),
							answerModel.getTitle(),
							answerModel.getBody(),
							new Date(answerModel.getLast_edit_date() * 1000)
							)
					);
			}
		}
		catch(Exception e) {
			log.error("Exception occured while inserting answers ",e);
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
