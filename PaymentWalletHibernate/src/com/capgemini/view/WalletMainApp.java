package com.capgemini.view;

import java.math.BigDecimal;
import java.util.Scanner;

import com.capgemini.exception.DuplicatePhoneNo;
import com.capgemini.exception.PhoneNoDoesNotExist;
import com.capgemini.service.WalletService;
import com.capgemini.service.WalletServiceImpl;

public class WalletMainApp {

	public static void main(String[] args) throws DuplicatePhoneNo,PhoneNoDoesNotExist
	{		
		WalletService walletService = new WalletServiceImpl();
		
		int choice;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String name;
		String mobile;
		BigDecimal amt;
		while(true)
		{
			System.out.println("1. Create account\n2. Show balance\n3. Withdraw amount\n4. Deposit amount\n5. Fund transfer\n6. Exit");
			choice = sc.nextInt();
			switch(choice)
			{
			case 1: 
					System.out.println("Enter name, mobile number and amount");
					name = sc.next();
					mobile = sc.next();
					amt = sc.nextBigDecimal();
					try
					{
						walletService.createAccount(name, mobile, amt);
					}
					catch(DuplicatePhoneNo e)
					{
						System.out.println("Duplicate Phone Numbers");
					}
					break;
			case 2: 
					System.out.println("Enter mobile number");
					mobile = sc.next();
					sc.nextLine();
					try
					{
						System.out.println(walletService.showBalance(mobile).getWallet().getBalance());
					}
					catch(PhoneNoDoesNotExist e)
					{
						System.out.println("Phone Number does not exist");
					}
					break;
			case 3: 
					System.out.println("Enter mobile number and amount\n");
					mobile = sc.next();
					amt = sc.nextBigDecimal();
					try
					{
						walletService.withdrawAmount(mobile, amt);
					}
					catch(PhoneNoDoesNotExist e)
					{
						System.out.println("Phone Number does not exist");
					}
					break;
			case 4:
					System.out.println("Enter mobile number and amount\n");
					mobile = sc.next();
					amt = sc.nextBigDecimal();
					try
					{
						walletService.depositAmount(mobile, amt);
					}
					catch(PhoneNoDoesNotExist e)
					{
						System.out.println("Phone Number does not exist");
					}
					break;
			case 5: 
					String tr;
					System.out.println("Enter first mobile number and second mobile number and amount to be transferred\n");
					mobile = sc.next();
					tr = sc.next();
					amt = sc.nextBigDecimal();
					try
					{
						walletService.fundTransfer(mobile, tr, amt);
					}
					catch(PhoneNoDoesNotExist e)
					{
						System.out.println("Phone Number does not exist");
					}
					break;
			default: System.exit(0);
			}
		}
	}
}
