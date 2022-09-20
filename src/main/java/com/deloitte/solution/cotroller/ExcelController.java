package com.deloitte.solution.cotroller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExcelController {
	
	@RequestMapping("/excel")
	public String excelMain() {
		return "excel/download";
	}
	
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
}
