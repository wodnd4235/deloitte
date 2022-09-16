package com.deloitte.solution.dto;

import lombok.Data;

@Data
public class PopbillDto {
	// 팝빌 회원 사업자번호
	private String testCorpNum;
	// 기관코드
	private String bankCode;
	// 계좌번호
	private String accountNumber;
	// 시작일자, 날짜형식(yyyyMMdd)
	private String sDate;
	// 종료일자, 닐짜형식(yyyyMMdd)
	private String eDate;
}
