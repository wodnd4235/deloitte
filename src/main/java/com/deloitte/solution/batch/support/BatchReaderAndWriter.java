package com.deloitte.solution.batch.support;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.Marshaller;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.deloitte.solution.batch.support.adapters.DateAdapter;
import com.deloitte.solution.batch.support.writer.StaxEventItemWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BatchReaderAndWriter extends BatchSqlSessionFactory {

	public <T> MyBatisCursorItemReader<T> newMyBatisCursorItemReader(SqlSessionFactory sqlSessionFactory,
			String queryId, SqlParameters parameters) {

		MyBatisCursorItemReader<T> reader = new MyBatisCursorItemReader<T>();
		reader.setSqlSessionFactory(sqlSessionFactory);

		if (!StringUtils.isBlank(queryId)) {
			reader.setQueryId(queryId);
		}

		if (parameters != null) {
			reader.setParameterValues(parameters.setParameters(getJobParameters(), new HashMap<String, Object>()));
		}

		return reader;
	}

	public <T> MyBatisCursorItemReader<T> newMyBatisCursorItemReader(String queryId, SqlParameters parameters) {
		return newMyBatisCursorItemReader(this.sqlSessionFactory, queryId, parameters);
	}

	public <T> MyBatisCursorItemReader<T> newMyBatisCursorItemReader(String queryId) {
		return newMyBatisCursorItemReader(this.sqlSessionFactory, queryId, null);
	}

	public <T> MyBatisCursorItemReader<T> newMyBatisCursorItemReader() {
		return newMyBatisCursorItemReader(this.sqlSessionFactory, null, null);
	}

	public <T> MyBatisPagingItemReader<T> newMyBatisPagingItemReader(SqlSessionFactory argSqlSessionFactory,
			String queryId, int pageSize, SqlParameters parameters) {

		log.debug("## sqlSessionFactory {}", argSqlSessionFactory.getConfiguration());

		MyBatisPagingItemReader<T> reader = new MyBatisPagingItemReader<T>();
		reader.setSqlSessionFactory(argSqlSessionFactory);

		if (!StringUtils.isBlank(queryId)) {
			reader.setQueryId(queryId);
		}

		if (pageSize > 0) {
			reader.setPageSize(pageSize);
		}

		if (parameters != null) {
			reader.setParameterValues(parameters.setParameters(getJobParameters(), new HashMap<String, Object>()));
		}

		try {
			reader.afterPropertiesSet();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return reader;
	}

	public <T> MyBatisPagingItemReader<T> newMyBatisPagingItemReader(SqlSessionFactory sqlSessionFactory,
			String queryId, int pageSize) {
		return newMyBatisPagingItemReader(sqlSessionFactory, queryId, pageSize, null);
	}

	public <T> MyBatisPagingItemReader<T> newMyBatisPagingItemReader(String queryId, int pageSize,
			SqlParameters parameters) {
		return newMyBatisPagingItemReader(this.sqlSessionFactory, queryId, pageSize, parameters);
	}

	public <T> MyBatisPagingItemReader<T> newMyBatisPagingItemReader(String queryId, int pageSize) {
		return newMyBatisPagingItemReader(this.sqlSessionFactory, queryId, pageSize, null);
	}

	public <T> MyBatisPagingItemReader<T> newMyBatisPagingItemReader() {
		return newMyBatisPagingItemReader(this.sqlSessionFactory, null, 0);
	}

	public <T> MyBatisPagingItemReader<T> newMyBatisPagingItemReader(SqlSessionFactory sqlSessionFactory) {

		MyBatisPagingItemReader<T> reader = new MyBatisPagingItemReader<T>();
		reader.setSqlSessionFactory(sqlSessionFactory);

		try {
			reader.afterPropertiesSet();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return reader;
	}

	public <T> MyBatisBatchItemWriter<T> newMyBatisBatchItemWriter(SqlSessionFactory sqlSessionFactory,
			String statementId) {
		MyBatisBatchItemWriter<T> writer = new MyBatisBatchItemWriter<T>();
		writer.setSqlSessionFactory(sqlSessionFactory);

		if (!StringUtils.isBlank(statementId)) {
			writer.setStatementId(statementId);
		}

		writer.setAssertUpdates(false);

		return writer;
	}

	public <T> MyBatisBatchItemWriter<T> newMyBatisBatchItemWriter(String statementId) {
		return newMyBatisBatchItemWriter(this.sqlSessionFactory, statementId);
	}

	public <T> MyBatisBatchItemWriter<T> newMyBatisBatchItemWriter() {
		return newMyBatisBatchItemWriter(this.sqlSessionFactory, null);
	}

	public <T> StaxEventItemReader<T> newXmlItemReader(String path, String rootElementName, Class<?>... clazz) {

		StaxEventItemReader<T> reader = new StaxEventItemReader<>();
		reader.setResource(new FileSystemResource(path));
		reader.setFragmentRootElementName(rootElementName);
		reader.setStrict(false);

		Jaxb2Marshaller studentMarshaller = new Jaxb2Marshaller();
		studentMarshaller.setClassesToBeBound(clazz);

		try {
			studentMarshaller.afterPropertiesSet();
			reader.setUnmarshaller(studentMarshaller);
			reader.afterPropertiesSet();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return reader;
	}

	public <T> StaxEventItemReader<T> newXmlDataItemReader(String xmlData, String rootElementName, Class<?>... clazz) {

		Resource resource = new ByteArrayResource(xmlData.getBytes());
		StaxEventItemReader<T> reader = new StaxEventItemReader<>();
		reader.setResource(resource);
		reader.setFragmentRootElementName(rootElementName);
		reader.setStrict(false);

		Jaxb2Marshaller studentMarshaller = new Jaxb2Marshaller();
		studentMarshaller.setClassesToBeBound(clazz);

		try {
			studentMarshaller.afterPropertiesSet();
			reader.setUnmarshaller(studentMarshaller);
			reader.afterPropertiesSet();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return reader;
	}

	public <T> StaxEventItemWriter<T> newXmlItemWriter(String path, String rootElementName, String dateFieldformat,
			Class<?>... clazz) {

		StaxEventItemWriter<T> writer = new StaxEventItemWriter<>();
		writer.setResource(new FileSystemResource(path));
		writer.setRootTagName(rootElementName);

		Map<String, Object> properties = new HashMap<>();
		properties.put(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		Jaxb2Marshaller studentMarshaller = new Jaxb2Marshaller();
		studentMarshaller.setClassesToBeBound(clazz);
		studentMarshaller.setMarshallerProperties(properties);

		if (!StringUtils.isBlank(dateFieldformat)) {
			studentMarshaller.setAdapters(new DateAdapter(dateFieldformat));
		}

		try {
			writer.setMarshaller(studentMarshaller);
			writer.afterPropertiesSet();
			studentMarshaller.afterPropertiesSet();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return writer;
	}

	public <T> StaxEventItemWriter<T> newXmlItemWriter(String path, String rootElementName, Class<?>... clazz) {
		return newXmlItemWriter(path, rootElementName, null, clazz);
	}

	public <T> StaxEventItemWriter<T> newXmlItemWriter(String path, Class<?>... clazz) {
		return newXmlItemWriter(path, "", clazz);
	}

}
