package com.capgemini.service;

import java.math.BigDecimal;

import com.capgemini.beans.Customer;
import com.capgemini.exception.DuplicatePhoneNo;
import com.capgemini.exception.PhoneNoDoesNotExist;

public interface WalletService
{
	public Customer createAccount(String name,String mobileNumber,BigDecimal amount) throws DuplicatePhoneNo, PhoneNoDoesNotExist;
	public Customer showBalance(String mobileNumber) throws PhoneNoDoesNotExist;
	public Customer fundTransfer(String sourceMobileNumber,String targetMobileNumber,BigDecimal amount) throws PhoneNoDoesNotExist;
	public Customer depositAmount(String mobileNumber,BigDecimal amount) throws PhoneNoDoesNotExist;
	public Customer withdrawAmount(String mobileNumber,BigDecimal amount) throws PhoneNoDoesNotExist;
}
