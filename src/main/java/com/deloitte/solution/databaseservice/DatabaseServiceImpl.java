package com.deloitte.solution.databaseservice;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.deloitte.solution.databasedao.DatabaseDAO;
import com.deloitte.solution.databasevo.DataBaseVO;

@Service("databaseService")
public class DatabaseServiceImpl implements DatabaseService {
	
	@Resource(name="databaseDAO")
	private DatabaseDAO databaseDAO;
	
	@Override
	public List<DataBaseVO> getDatabaseList(){
		return databaseDAO.selectData();
	}
}
