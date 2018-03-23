package org.stackoverflow.analysis.dao;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stackoverflow.analysis.datasource.CassandraDataSource;
import org.stackoverflow.analysis.model.AnswerModel;
import org.stackoverflow.analysis.model.SOFMetaModel;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;

public class MetaDao {
	
	public static final MetaDao META_DAO = new MetaDao();
	
	transient Logger log = LoggerFactory.getLogger(this.getClass());
	
	public static MetaDao getInstance() {
		return META_DAO;
	}
	
	public boolean add(List<SOFMetaModel> metaModels) throws Exception{
		Session session = null;
		try {
			session = CassandraDataSource.getConnection();
			PreparedStatement insertStatement = session.prepare(
					"insert into sof_meta"
					+ " (meta_type, creation_time, page_number, batch_size, message_time)" + 
					" values (?,?,?,?,?)"
						);
			log.info("Insert query prepared is :: {}",insertStatement.getQueryString());
			for(SOFMetaModel metaModel : metaModels) {
				session.execute(
					insertStatement.bind(
							metaModel.getMeta_type(),
							new Date(metaModel.getCreation_time()),
							(short)metaModel.getPage_number(),
							(short)metaModel.getBatch_size(),
							new Date(metaModel.getMessage_time())
							)
					);
			}
		}
		catch(Exception e) {
			log.error("Exception occured while inserting meta ",e);
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
