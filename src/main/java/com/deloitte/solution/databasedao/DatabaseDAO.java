package com.deloitte.solution.databasedao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.deloitte.solution.databasevo.DataBaseVO;


public class DatabaseDAO {
	
	@Autowired
	private SqlSessionTemplate template;
	
	private String nameSpace = "com.deloitte.solution.mapper.DataBaseMapper";
	
	
	public List<DataBaseVO> selectData() {
		return template.selectList(nameSpace+".getDataBase");
	}
}
