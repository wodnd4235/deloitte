package com.deloitte.solution.databasemapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.deloitte.solution.databasevo.DataBaseVO;

@Repository
@Mapper
public interface DatabaseMapper {
	List<DataBaseVO> getDataBase() throws Exception;
}
