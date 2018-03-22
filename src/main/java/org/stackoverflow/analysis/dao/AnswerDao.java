package org.stackoverflow.analysis.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.stackoverflow.analysis.datasource.CassandraDataSource;
import org.stackoverflow.analysis.model.AnswerModel;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;

public class AnswerDao {
	
	public static final AnswerDao ANSWER_DAO = new AnswerDao();
	
	public static AnswerDao getInstance() {
		return ANSWER_DAO;
	}
	
	public boolean add(List<AnswerModel> answerModels) {
		Session session = CassandraDataSource.getConnection();
		PreparedStatement insertStatement = session.prepare(
				"insert into answer"
				+ " (tags, owner, can_flag, comment_count, down_vote_count, up_vote_count, is_accepted, score, last_activity_date, " + 
				"creation_date,answer_id, question_id, title, body, last_edit_date)" + 
				" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
					);
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
		return true;
		
	}
	
	public static void main(String[] args) {
		List<String> tags = new ArrayList<>();
		tags.add("Java");
		tags.add("Spark");
		Map<String, String> owner = new HashMap<>();
		owner.put("text", "t");
		owner.put("ttt", "ttt");
		List<AnswerModel> models = new ArrayList<>();
		AnswerModel answerModel = new AnswerModel();
		answerModel.setAnswer_id(1111);
		answerModel.setBody("test");
		answerModel.setCan_flag(true);
		answerModel.setComment_count(1);
		answerModel.setCreation_date(1219709813);
		answerModel.setDown_vote_count(1);
		answerModel.setIs_accepted(true);
		answerModel.setLast_activity_date(1219709813);
		answerModel.setOwner(owner);
		answerModel.setQuestion_id(1);
		answerModel.setScore(1);
		answerModel.setTags(tags);
		answerModel.setTitle("some");
		answerModel.setUp_vote_count(1);
		models.add(answerModel);
		AnswerDao.getInstance().add(models);
	}

}
