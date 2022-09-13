package com.deloitte.solution.batch.support;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class BatchSqlSessionFactory extends BatchJobConfiguration{

	@Resource(name = "sqlSessionFactory")
	protected SqlSessionFactory sqlSessionFactory;
	
	@Autowired(required = false)
	@Qualifier("sqlSessionFactoryErp")
	protected SqlSessionFactory sqlSessionFactoryErp;

	
	/**
	 * mybatis를 이용하여 DB에서 데이터 호출 시 사용.
	 * master sqlSessionFactory 
	 * 
	 * @return
	 */
	public SqlSessionFactory getSqlSessionFactoryMaster() {
		return sqlSessionFactory;
	}
	
	
	/**
	 * mybatis를 이용하여 ERP DB에서 데이터 호출 시 사용.
	 * master sqlSessionFactoryErp 
	 * 
	 * @return
	 */
	public SqlSessionFactory getSqlSessionFactoryErp() {
		return sqlSessionFactoryErp;
	}
	
	
    
}
