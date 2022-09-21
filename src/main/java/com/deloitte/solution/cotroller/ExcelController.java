package com.deloitte.solution.cotroller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.deloitte.solution.service.ExcelService;
import com.deloitte.solution.support.BasicResponse;

/**
  * @FileName : ExcelController.java
  * @Project : Deloitte
  * @Date : 2022. 9. 21. 
  * @작성자 : daeyi
  * @변경이력 :
  * @description : excel 다운로드 및 업로드
  */
@Controller
public class ExcelController {
	
	@Autowired
	ExcelService excelService;
	
	/**
	  * @MethodName : excelMain
	  * @작성일 : 2022. 9. 21.
	  * @작성자 : daeyi
	  * @변경이력 : 
	  * @description : 엑셀 다운로드 화면 이동
	  * @return
	  */
	@RequestMapping("/excel")
	public String excelMain() {
		return "excel/download";
	}
	
	/**
	  * @MethodName : excelDownload
	  * @작성일 : 2022. 9. 21.
	  * @작성자 : daeyi
	  * @변경이력 : 
	  * @description : 엑셀 다운로드 처리
	  * @param response
	  * @throws IOException
	  */
	@RequestMapping("/excel/download")
    public void excelDownload(HttpServletResponse response) throws IOException {
//        Workbook wb = new HSSFWorkbook();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("첫번째 시트");
        Row row = null;
        Cell cell = null;
        int rowNum = 0;
//
//        // Header
        row = sheet.createRow(rowNum++);
        cell = row.createCell(0);
        cell.setCellValue("번호");
        cell = row.createCell(1);
        cell.setCellValue("이름");
        cell = row.createCell(2);
        cell.setCellValue("제목");
//
//        // Body
//        for (int i=0; i<3; i++) {
//            row = sheet.createRow(rowNum++);
//            cell = row.createCell(0);
//            cell.setCellValue(i);
//            cell = row.createCell(1);
//            cell.setCellValue(i+"_name");
//            cell = row.createCell(2);
//            cell.setCellValue(i+"_title");
//        }

        // 컨텐츠 타입과 파일명 지정
        response.setContentType("ms-vnd/excel");
//        response.setHeader("Content-Disposition", "attachment;filename=example.xls");
        response.setHeader("Content-Disposition", "attachment;filename=example.xlsx");

        // Excel File Output
        workbook.write(response.getOutputStream());
        workbook.close();
    }
	
	/**
	  * @MethodName : addExcel
	  * @작성일 : 2022. 9. 21.
	  * @작성자 : daeyi
	  * @변경이력 : 
	  * @description : 엑셀 업로드 처리
	  * @param request
	  * @param response
	  * @param file
	  * @return
	  */
	@RequestMapping(value = "/addExcel", method = RequestMethod.POST)
	public ResponseEntity<? extends BasicResponse> addExcel(HttpServletRequest request,
			HttpServletResponse response, MultipartFile file) {
		return ResponseEntity.ok().body(excelService.addExcel(file));
	};
}
