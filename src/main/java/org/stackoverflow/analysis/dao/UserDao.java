package org.stackoverflow.analysis.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stackoverflow.analysis.datasource.CassandraDataSource;
import org.stackoverflow.analysis.model.AnswerModel;
import org.stackoverflow.analysis.model.UserModel;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;

public class UserDao {
	
	public static final UserDao USER_DAO = new UserDao();
	
	transient Logger log = LoggerFactory.getLogger(this.getClass());
	
	public static UserDao getInstance() {
		return USER_DAO;
	}
	
	public boolean add(List<UserModel> users) {
		
		Session session = null;
		try {
			session = CassandraDataSource.getConnection();
			PreparedStatement insertStatement = session.prepare(
					"insert into user (badge_counts,view_count,down_vote_count,up_vote_count,answer_count,question_count,account_id,"
					+ "is_employee,last_modified_date,last_access_date,reputation_change_year,reputation_change_quarter,"
					+ "reputation_change_month,reputation_change_week,reputation_change_day,reputation,creation_date,user_type,"
					+ "user_id,about_me,location,website_url,link,profile_image,display_name,age,accept_rate) "
					+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
						);
			log.info("Insert query prepared is :: {}",insertStatement.getQueryString());
			for(UserModel user : users) {
				session.execute(
					insertStatement.bind(
							user.getBadge_counts(),
							(short)user.getView_count(),
							(short)user.getDown_vote_count(),
							(short)user.getUp_vote_count(),
							(short)user.getAnswer_count(),
							(short)user.getQuestion_count(),
							user.getAccount_id(),
							user.isIs_employee(),
							new Date(user.getLast_modified_date() * 1000),
							new Date(user.getLast_access_date() * 1000),
							(short)user.getReputation_change_year(),
							(short)user.getReputation_change_quarter(),
							(short)user.getReputation_change_month(),
							(short)user.getReputation_change_week(),
							(short)user.getReputation_change_day(),
							(short)user.getReputation(),
							new Date(user.getCreation_date() * 1000),
							user.getUser_type(),
							user.getUser_id(),
							user.getAbout_me(),
							user.getLocation(),
							user.getWebsite_url(),
							user.getLink(),
							user.getProfile_image(),
							user.getDisplay_name(),
							(short)user.getAge(),
							(short)user.getAccept_rate()
							)
					);
			}
		}
		catch(Exception e) {
			log.error("Exception occured while inserting tags ",e);
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
