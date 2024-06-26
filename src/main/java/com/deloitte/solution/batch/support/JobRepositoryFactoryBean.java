/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.deloitte.solution.batch.support;

import static org.springframework.batch.support.DatabaseType.SYBASE;

import java.lang.reflect.Field;
import java.sql.Types;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.repository.ExecutionContextSerializer;
import org.springframework.batch.core.repository.dao.AbstractJdbcBatchMetadataDao;
import org.springframework.batch.core.repository.dao.ExecutionContextDao;
import org.springframework.batch.core.repository.dao.Jackson2ExecutionContextStringSerializer;
import org.springframework.batch.core.repository.dao.JobExecutionDao;
import org.springframework.batch.core.repository.dao.JobInstanceDao;
import org.springframework.batch.core.repository.dao.StepExecutionDao;
import org.springframework.batch.core.repository.support.AbstractJobRepositoryFactoryBean;
import org.springframework.batch.core.repository.support.SimpleJobRepository;
import org.springframework.batch.item.database.support.DataFieldMaxValueIncrementerFactory;
import org.springframework.batch.item.database.support.DefaultDataFieldMaxValueIncrementerFactory;
import org.springframework.batch.support.DatabaseType;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.deloitte.solution.batch.support.dao.JdbcExecutionContextDao;
import com.deloitte.solution.batch.support.dao.JdbcJobExecutionDao;
import com.deloitte.solution.batch.support.dao.JdbcJobInstanceDao;
import com.deloitte.solution.batch.support.dao.JdbcStepExecutionDao;

/**
 * A {@link FactoryBean} that automates the creation of a
 * {@link SimpleJobRepository} using JDBC DAO implementations which persist
 * batch metadata in database. Requires the user to describe what kind of
 * database they are using.
 *
 * @author Ben Hale
 * @author Lucas Ward
 * @author Dave Syer
 * @author Michael Minella
 */
public class JobRepositoryFactoryBean extends AbstractJobRepositoryFactoryBean implements InitializingBean {

	protected static final Log logger = LogFactory.getLog(JobRepositoryFactoryBean.class);

	private DataSource dataSource;

	private JdbcOperations jdbcOperations;

	private String databaseType;

	private String tablePrefix = AbstractJdbcBatchMetadataDao.DEFAULT_TABLE_PREFIX;

	private DataFieldMaxValueIncrementerFactory incrementerFactory;

	private int maxVarCharLength = AbstractJdbcBatchMetadataDao.DEFAULT_EXIT_MESSAGE_LENGTH;

	private LobHandler lobHandler;

	private ExecutionContextSerializer serializer;

	private Integer lobType;

	/**
	 * @param type a value from the {@link java.sql.Types} class to indicate the type to use for a CLOB
	 */
	public void setClobType(int type) {
		this.lobType = type;
	}

	/**
	 * A custom implementation of the {@link ExecutionContextSerializer}.
	 * The default, if not injected, is the {@link Jackson2ExecutionContextStringSerializer}.
	 *
	 * @param serializer used to serialize/deserialize {@link org.springframework.batch.item.ExecutionContext}
	 * @see ExecutionContextSerializer
	 */
	public void setSerializer(ExecutionContextSerializer serializer) {
		this.serializer = serializer;
	}

	/**
	 * A special handler for large objects. The default is usually fine, except
	 * for some (usually older) versions of Oracle. The default is determined
	 * from the data base type.
	 *
	 * @param lobHandler the {@link LobHandler} to set
	 *
	 * @see LobHandler
	 */
	public void setLobHandler(LobHandler lobHandler) {
		this.lobHandler = lobHandler;
	}

	/**
	 * Public setter for the length of long string columns in database. Do not
	 * set this if you haven't modified the schema. Note this value will be used
	 * for the exit message in both {@link JdbcJobExecutionDao} and
	 * {@link JdbcStepExecutionDao} and also the short version of the execution
	 * context in {@link JdbcExecutionContextDao} . For databases with
	 * multi-byte character sets this number can be smaller (by up to a factor
	 * of 2 for 2-byte characters) than the declaration of the column length in
	 * the DDL for the tables.
	 *
	 * @param maxVarCharLength the exitMessageLength to set
	 */
	public void setMaxVarCharLength(int maxVarCharLength) {
		this.maxVarCharLength = maxVarCharLength;
	}

	/**
	 * Public setter for the {@link DataSource}.
	 * @param dataSource a {@link DataSource}
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	/**
	 * Public setter for the {@link JdbcOperations}. If this property is not set explicitly,
	 * a new {@link JdbcTemplate} will be created for the configured DataSource by default.
	 * @param jdbcOperations a {@link JdbcOperations}
	 */
	public void setJdbcOperations(JdbcOperations jdbcOperations) {
		this.jdbcOperations = jdbcOperations;
	}

	/**
	 * Sets the database type.
	 * @param dbType as specified by
	 * {@link DefaultDataFieldMaxValueIncrementerFactory}
	 */
	public void setDatabaseType(String dbType) {
		this.databaseType = dbType;
	}

	/**
	 * Sets the table prefix for all the batch meta-data tables.
	 * @param tablePrefix prefix prepended to batch meta-data tables
	 */
	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix = tablePrefix;
	}

	public void setIncrementerFactory(DataFieldMaxValueIncrementerFactory incrementerFactory) {
		this.incrementerFactory = incrementerFactory;
	}

	@Override
	public void afterPropertiesSet() throws Exception {

		Assert.notNull(dataSource, "DataSource must not be null.");

		//다른 프로세스에서 같은 테이블에 일기/쓰기 할때 deadlock 에러가 발생한다. ISOLATION 레빌을 READ_COMMIT으로 바꿔준다. 
		super.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
		if (jdbcOperations == null) {
			jdbcOperations = new JdbcTemplate(dataSource);	
		}		

		if (incrementerFactory == null) {
			incrementerFactory = new DefaultDataFieldMaxValueIncrementerFactory(dataSource);
		}

		if (databaseType == null) {
			databaseType = DatabaseType.fromMetaData(dataSource).name();
			logger.info("No database type set, using meta data indicating: " + databaseType);
		}

		if (lobHandler == null && databaseType.equalsIgnoreCase(DatabaseType.ORACLE.toString())) {
			lobHandler = new DefaultLobHandler();
		}

		if(serializer == null) {
			Jackson2ExecutionContextStringSerializer defaultSerializer = new Jackson2ExecutionContextStringSerializer();

			serializer = defaultSerializer;
		}

		Assert.isTrue(incrementerFactory.isSupportedIncrementerType(databaseType), "'" + databaseType
				+ "' is an unsupported database type.  The supported database types are "
				+ StringUtils.arrayToCommaDelimitedString(incrementerFactory.getSupportedIncrementerTypes()));

		if(lobType != null) {
			Assert.isTrue(isValidTypes(lobType), "lobType must be a value from the java.sql.Types class");
		}

		super.afterPropertiesSet();
	}

	@Override
	protected JobInstanceDao createJobInstanceDao() throws Exception {
		JdbcJobInstanceDao dao = new JdbcJobInstanceDao();
		dao.setJdbcTemplate(jdbcOperations);
		dao.setJobIncrementer(incrementerFactory.getIncrementer(databaseType, tablePrefix + "JOB_SEQ"));
		dao.setTablePrefix(tablePrefix);
		dao.afterPropertiesSet();
		return dao;
	}

	@Override
	protected JobExecutionDao createJobExecutionDao() throws Exception {
		JdbcJobExecutionDao dao = new JdbcJobExecutionDao();
		dao.setJdbcTemplate(jdbcOperations);
		dao.setJobExecutionIncrementer(incrementerFactory.getIncrementer(databaseType, tablePrefix
				+ "JOB_EXECUTION_SEQ"));
		dao.setTablePrefix(tablePrefix);
		dao.setClobTypeToUse(determineClobTypeToUse(this.databaseType));
		dao.setExitMessageLength(maxVarCharLength);
		dao.afterPropertiesSet();
		return dao;
	}

	@Override
	protected StepExecutionDao createStepExecutionDao() throws Exception {
		JdbcStepExecutionDao dao = new JdbcStepExecutionDao();
		dao.setJdbcTemplate(jdbcOperations);
		dao.setStepExecutionIncrementer(incrementerFactory.getIncrementer(databaseType, tablePrefix
				+ "STEP_EXECUTION_SEQ"));
		dao.setTablePrefix(tablePrefix);
		dao.setClobTypeToUse(determineClobTypeToUse(this.databaseType));
		dao.setExitMessageLength(maxVarCharLength);
		dao.afterPropertiesSet();
		return dao;
	}

	@Override
	protected ExecutionContextDao createExecutionContextDao() throws Exception {
		JdbcExecutionContextDao dao = new JdbcExecutionContextDao();
		dao.setJdbcTemplate(jdbcOperations);
		dao.setTablePrefix(tablePrefix);
		dao.setClobTypeToUse(determineClobTypeToUse(this.databaseType));
		dao.setSerializer(serializer);

		if (lobHandler != null) {
			dao.setLobHandler(lobHandler);
		}

		dao.afterPropertiesSet();
		// Assume the same length.
		dao.setShortContextLength(maxVarCharLength);
		return dao;
	}

	private int determineClobTypeToUse(String databaseType) throws Exception {
		if(lobType != null) {
			return lobType;
		} else {
			if (SYBASE == DatabaseType.valueOf(databaseType.toUpperCase())) {
				return Types.LONGVARCHAR;
			}
			else {
				return Types.CLOB;
			}
		}
	}

	private boolean isValidTypes(int value) throws Exception {
		boolean result = false;

		for (Field field : Types.class.getFields()) {
			int curValue = field.getInt(null);
			if(curValue == value) {
				result = true;
				break;
			}
		}

		return result;
	}

}
