package com.deloitte.solution.cotroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.deloitte.solution.dto.PopbillDto;
import com.deloitte.solution.service.PopbillService;
import com.deloitte.solution.support.PopbillProperties;
import com.popbill.api.AccountCheckInfo;
import com.popbill.api.AccountCheckService;
import com.popbill.api.EasyFinBankService;
import com.popbill.api.JoinForm;
import com.popbill.api.PopbillException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@Controller
public class PopbillController {

	@Autowired
	EasyFinBankService easyFinBankService;
	@Autowired
	AccountCheckService accountCheckService;
	@Autowired
	PopbillService baseServiceImpl;
	@Autowired(required=false)
	PopbillProperties popbillProperties;

	@RequestMapping(value = "/requestJob", method = RequestMethod.GET)
	public ModelAndView requestJob(HttpSession session, @ModelAttribute PopbillDto m) throws Exception {
		ModelAndView modelAndView = new ModelAndView();

		// 팝빌 회원 사업자번호
		String testCorpNum = "1168122521";

		// 기관코드
		String BankCode = "0003";

		// 계좌번호
		String AccountNumber = "50502532704011";

		// 시작일자, 날짜형식(yyyyMMdd)
		String SDate = "20220915";

		// 종료일자, 닐짜형식(yyyyMMdd)
		String EDate = "20211110";

		try {
			/*
			 * 계좌 거래내역 수집을 요청합니다. - 검색기간은 현재일 기준 90일 이내로만 요청할 수 있습니다. - 수집 요청후 반환받은
			 * 작업아이디(JobID)의 유효시간은 1시간 입니다.
			 */
			String jobID = easyFinBankService.requestJob(testCorpNum, BankCode, AccountNumber, SDate, EDate);

			modelAndView.addObject("Result", jobID);

		} catch (PopbillException pe) {
			// 예외 발생 시, pe.getCode() 로 오류 코드를 확인하고, pe.getMessage()로 오류 메시지를 확인합니다.
			System.out.println("오류 코드" + pe.getCode());
			System.out.println("오류 메시지" + pe.getMessage());
			modelAndView.addObject("Result", "오류 코드와 메시지를 확인하세요.");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/checkAccountInfo", method = RequestMethod.GET)
	public String checkAccountInfo(HttpSession session, Model m) {
		ModelAndView modelAndView = new ModelAndView();

		/*
		 * 1건의 예금주성명을 조회합니다. -
		 * https://docs.popbill.com/accountcheck/java/api#CheckAccountInfo
		 */

		// [필수] 팝빌회원 사업자번호 (하이픈 '-' 제외 10 자리)
		String MemberCorpNum = "1168122521";

		/*
		 * [필수] 기관코드 - https://docs.popbill.com/accountcheck/?lang=java#BankCodeList
		 */
		String BankCode = "0003";

		// [필수] 계좌번호 (하이픈 '-' 제외 8자리 이상 14자리 이하)
		String AccountNumber = "50502532704011";

		try {
			AccountCheckInfo accountInfo = accountCheckService.CheckAccountInfo(MemberCorpNum, BankCode, AccountNumber);
			m.addAttribute("AccountInfo", accountInfo);
		} catch (PopbillException e) {
			System.out.println("오류 코드" + e.getCode());
			System.out.println("오류 메시지" + e.getMessage());
		}

		// log.debug();
		return "popbill/checkAccountInfo";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String joinMember() {
		JoinForm form = new JoinForm();
		try {
			form.setID("test1234");
			form.setPWD("test1234!");
			form.setLinkID(popbillProperties.getLinkId());
			baseServiceImpl.joinMember(form);
		} catch (PopbillException e) {
			// TODO Auto-generated catch block
			System.out.println("오류 코드" + e.getCode());
			System.out.println("오류 메시지" + e.getMessage());
			e.printStackTrace();
		}
		return "popbill/join";
	}
}
