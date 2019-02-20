package com.capgemini.service;

import java.math.BigDecimal;

import com.capgemini.beans.Customer;
import com.capgemini.beans.Wallet;
import com.capgemini.exception.DuplicatePhoneNo;
import com.capgemini.exception.PhoneNoDoesNotExist;
import com.capgemini.repository.WalletRepo;
import com.capgemini.repository.WalletRepoImpl;

public class WalletServiceImpl implements WalletService
{	
	WalletRepo walletRepo = new WalletRepoImpl();	

	@Override
	public Customer createAccount(String name, String mobileNumber, BigDecimal amount) throws DuplicatePhoneNo, PhoneNoDoesNotExist
	{		
		Customer customer=new Customer(name, mobileNumber, new Wallet(amount));
		
		if(walletRepo.save(customer))
			return customer;
		else
			throw new DuplicatePhoneNo();
	}

	@Override
	public Customer showBalance(String mobileNumber) throws PhoneNoDoesNotExist
	{
			return walletRepo.findOne(mobileNumber);
	}

	@Override
	public Customer fundTransfer(String sourceMobileNumber, String targetMobileNumber, BigDecimal amount) throws PhoneNoDoesNotExist
	{
		Customer c;
		if(walletRepo.findOne(sourceMobileNumber)==null || walletRepo.findOne(targetMobileNumber)==null)
			throw new PhoneNoDoesNotExist();
		else if(sourceMobileNumber.equals(targetMobileNumber))
		{
			System.out.println("Source and target mobile numbers can't be same");
			return null;
		}
		else
		{
			this.depositAmount(targetMobileNumber, amount);
			c = this.withdrawAmount(sourceMobileNumber, amount);
			return walletRepo.updateBal(c);
		}
	}

	@Override
	public Customer depositAmount(String mobileNumber, BigDecimal amount) throws PhoneNoDoesNotExist
	{
		if(walletRepo.findOne(mobileNumber)==null)
			throw new PhoneNoDoesNotExist();
		
		Customer customer=walletRepo.findOne(mobileNumber);
		Wallet wallet=customer.getWallet();
		wallet.setBalance(wallet.getBalance().add(amount));
		customer.setWallet(wallet);
		return walletRepo.updateBal(customer);
	}

	@Override
	public Customer withdrawAmount(String mobileNumber, BigDecimal amount) throws PhoneNoDoesNotExist
	{		
		if(walletRepo.findOne(mobileNumber)==null)
			throw new PhoneNoDoesNotExist();
		
		Customer customer=walletRepo.findOne(mobileNumber);
		Wallet wallet=customer.getWallet();
		wallet.setBalance(wallet.getBalance().subtract(amount));
		customer.setWallet(wallet);
		return walletRepo.updateBal(customer);
	}
}
