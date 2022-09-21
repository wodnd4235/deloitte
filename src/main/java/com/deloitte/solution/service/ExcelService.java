package com.deloitte.solution.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.deloitte.solution.constant.CodeConst;
import com.deloitte.solution.support.BasicResponse;
import com.deloitte.solution.support.CommonResponse;
import com.deloitte.solution.support.ErrorResponse;
import com.deloitte.solution.util.ExcelUtil;


/**
  * @FileName : ExcelService.java
  * @Project : Deloitte
  * @Date : 2022. 9. 21. 
  * @작성자 : daeyi
  * @변경이력 :
  * @description : excel 다운로드 및 업로드
  */
@Service
public class ExcelService {
	@Autowired
	ExcelUtil excelUtil;
	
	/**
	  * @MethodName : addExcel
	  * @작성일 : 2022. 9. 21.
	  * @작성자 : daeyi
	  * @변경이력 : 
	  * @description :
	  * @param file
	  * @return
	  */
	public BasicResponse addExcel(MultipartFile file) {
		
		// 파일 존재하지 않는 경우
		if (file.isEmpty()) {
			return new ErrorResponse("Excel 파일을 선택해주세요.");
		}

		// 확장자 유효성 검사 -> 엑셀파일만 가능
		//1안: tring ext = fileUtil.getExtension(file.getOriginalFilename());
		//2안
		String contentType = file.getContentType();

		//1안: if (!ext.equals("xlsx") && !ext.equals("xls")) {
		//2안
		if(!contentType.equals(CodeConst.EXCEL_TYPE_XLSX)) {
			new ErrorResponse("Excel 파일을 선택해주세요.");
		}

//		List<UserVO> listUser = new ArrayList<UserVO>();
//
//		// 엑셀의 셀데이터를 가져와서 VO에 담기
//		List<Map<String, Object>> listMap = excelUtil.getListData(file, 1, 3);
//
//		for (Map<String, Object> map : listMap) {
//			UserVO userInfo = new UserVO();
//		
//			// 각 셀의 데이터를 VO에 set한다.
//			userInfo.setUserId(map.get("1").toString());
//			userInfo.setPassword(map.get("2").toString());
//			userInfo.setUserName(map.get("3").toString());
//
//			listUser.add(userInfo);
//		}
//
//		// 리스트에 담은 VO를 DB에 저장
//		for (UserVO oneUser : listUser){
//			userMapper.insertUser(oneUser);
//		}
		return new CommonResponse("성공");
	}
}
