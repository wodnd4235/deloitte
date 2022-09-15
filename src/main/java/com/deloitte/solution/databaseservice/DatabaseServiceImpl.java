package com.deloitte.solution.databaseservice;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.deloitte.solution.databasemapper.DatabaseMapper;
import com.deloitte.solution.databasevo.DataBaseVO;

@Service
public class DatabaseServiceImpl implements DatabaseService {
	
	@Autowired
	private DatabaseMapper databaseMapper;
	
	@Override
	public List<DataBaseVO> getDatabaseList() throws Exception{
		return databaseMapper.getDataBase();
	}
}
