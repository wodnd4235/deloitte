package com.deloitte.solution.service;

import org.springframework.stereotype.Service;

import com.popbill.api.ChargeInfo;
import com.popbill.api.ContactInfo;
import com.popbill.api.CorpInfo;
import com.popbill.api.EasyFinBankService;
import com.popbill.api.FlatRateState;
import com.popbill.api.JoinForm;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;
import com.popbill.api.easyfin.EasyFinBankAccount;
import com.popbill.api.easyfin.EasyFinBankAccountForm;
import com.popbill.api.easyfin.EasyFinBankJobState;
import com.popbill.api.easyfin.EasyFinBankSearchResult;
import com.popbill.api.easyfin.EasyFinBankSummary;

@Service
public class CustomEasyFinBankService implements EasyFinBankService {

	@Override
	public double getBalance(String CorpNum) throws PopbillException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getPartnerBalance(String CorpNum) throws PopbillException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Response joinMember(JoinForm joinInfo) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPopbillURL(String CorpNum, String TOGO) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPopbillURL(String CorpNum, String UserID, String TOGO) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPartnerURL(String CorpNum, String TOGO) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response checkIsMember(String CorpNum, String LinkID) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContactInfo getContactInfo(String CorpNum, String contactID) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContactInfo getContactInfo(String CorpNum, String contactID, String UserID) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContactInfo[] listContact(String CorpNum) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContactInfo[] listContact(String CorpNum, String UserID) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateContact(String CorpNum, ContactInfo contactInfo, String UserID) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response registContact(String CorpNum, ContactInfo contactInfo) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response registContact(String CorpNum, ContactInfo contactInfo, String UserID) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response checkID(String id) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CorpInfo getCorpInfo(String CorpNum) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CorpInfo getCorpInfo(String CorpNum, String UserID) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateCorpInfo(String CorpNum, CorpInfo corpInfo) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateCorpInfo(String CorpNum, CorpInfo corpInfo, String UserID) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAccessURL(String CorpNum, String UserID) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getChargeURL(String CorpNum, String UserID) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPaymentURL(String CorpNum, String UserID) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUseHistoryURL(String CorpNum, String UserID) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBankAccountMgtURL(String CorpNum) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBankAccountMgtURL(String CorpNum, String UserID) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response registBankAccount(String CorpNum, EasyFinBankAccountForm form) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response registBankAccount(String CorpNum, EasyFinBankAccountForm form, String UserID)
			throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateBankAccount(String CorpNum, EasyFinBankAccountForm form) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateBankAccount(String CorpNum, EasyFinBankAccountForm form, String UserID)
			throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response closeBankAccount(String CorpNum, String BankCode, String AccountNumber, String CloseType)
			throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response closeBankAccount(String CorpNum, String BankCode, String AccountNumber, String CloseType,
			String UserID) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response revokeCloseBankAccount(String CorpNum, String BankCode, String AccountNumber)
			throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response revokeCloseBankAccount(String CorpNum, String BankCode, String AccountNumber, String UserID)
			throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteBankAccount(String CorpNum, String BankCode, String AccountNumber) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteBankAccount(String CorpNum, String BankCode, String AccountNumber, String UserID)
			throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EasyFinBankAccount getBankAccountInfo(String CorpNum, String BankCode, String AccountNumber)
			throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EasyFinBankAccount getBankAccountInfo(String CorpNum, String BankCode, String AccountNumber, String UserID)
			throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EasyFinBankAccount[] listBankAccount(String CorpNum) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EasyFinBankAccount[] listBankAccount(String CorpNum, String UserID) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String requestJob(String CorpNum, String BankCode, String AccountNumber, String SDate, String EDate)
			throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String requestJob(String CorpNum, String BankCode, String AccountNumber, String SDate, String EDate,
			String UserID) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EasyFinBankJobState getJobState(String CorpNum, String JobID) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EasyFinBankJobState getJobState(String CorpNum, String JobID, String UserID) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EasyFinBankJobState[] listActiveJob(String CorpNum) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EasyFinBankJobState[] listActiveJob(String CorpNum, String UserID) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EasyFinBankSearchResult search(String CorpNum, String JobID, String[] TradeType, String SearchString,
			Integer Page, Integer PerPage, String Order) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EasyFinBankSearchResult search(String CorpNum, String JobID, String[] TradeType, String SearchString,
			Integer Page, Integer PerPage, String Order, String UserID) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EasyFinBankSummary summary(String CorpNum, String JobID, String[] TradeType, String SearchString)
			throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EasyFinBankSummary summary(String CorpNum, String JobID, String[] TradeType, String SearchString,
			String UserID) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response saveMemo(String CorpNum, String TID, String Memo) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response saveMemo(String CorpNum, String TID, String Memo, String UserID) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FlatRateState getFlatRateState(String CorpNum, String BankCode, String AccountNumber)
			throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FlatRateState getFlatRateState(String CorpNum, String BankCode, String AccountNumber, String UserID)
			throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFlatRatePopUpURL(String CorpNum) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFlatRatePopUpURL(String CorpNum, String UserID) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChargeInfo getChargeInfo(String CorpNum) throws PopbillException {
		// TODO Auto-generated method stub
		return null;
	}

}
