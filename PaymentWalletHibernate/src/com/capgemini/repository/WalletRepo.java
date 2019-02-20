package com.capgemini.repository;

import com.capgemini.beans.Customer;
import com.capgemini.exception.DuplicatePhoneNo;
import com.capgemini.exception.PhoneNoDoesNotExist;

public interface WalletRepo {
	
	public boolean save(Customer customer) throws DuplicatePhoneNo;
	public Customer findOne(String mobilenumber) throws PhoneNoDoesNotExist;
	public Customer updateBal(Customer customer) throws PhoneNoDoesNotExist;

}
