package org.stackoverflow.analysis.dao;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stackoverflow.analysis.datasource.CassandraDataSource;
import org.stackoverflow.analysis.model.QuestionModel;
import org.stackoverflow.analysis.model.TagModel;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;

public class TagDao {
	
	public static final TagDao TAG_DAO = new TagDao();
	
	transient Logger log = LoggerFactory.getLogger(this.getClass());
	
	public static TagDao getInstance() {
		return TAG_DAO;
	}
	
	public boolean add(List<TagModel> tags) {
		Session session = null;
		try {
			session = CassandraDataSource.getConnection();
			PreparedStatement insertStatement = session.prepare(
					"insert into tag "
					+ "(synonyms ,last_activity_date ,has_synonyms ,is_moderator_only ,is_required ,count ,name) "
					+ "values (?,?,?,?,?,?,?)"
						);
			log.info("Insert query prepared is :: {}",insertStatement.getQueryString());
			for(TagModel tag : tags) {
				session.execute(
					insertStatement.bind(
							tag.getSynonyms(),
							new Date(tag.getLast_activity_date() * 1000),
							tag.isHas_synonyms(),
							tag.isIs_moderator_only(),
							tag.isIs_required(),
							tag.getCount(),
							tag.getName()
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
