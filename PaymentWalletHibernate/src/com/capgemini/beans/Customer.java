package com.capgemini.beans;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customer_table")
public class Customer {
	private String name;
	@Id
	private String mobileNumber;
	@Embedded
	private Wallet wallet;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public Wallet getWallet() {
		return wallet;
	}
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	public Customer() {
		super();
	}
	public Customer(String name, String mobileNumber, Wallet wallet) {
		super();
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.wallet = wallet;
	}
	@Override
	public String toString() {
		return "Customer [name=" + name + ", mobileNumber=" + mobileNumber + ", wallet=" + wallet + "]";
	}
}
