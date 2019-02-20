package com.capgemini.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.capgemini.beans.Customer;
import com.capgemini.exception.DuplicatePhoneNo;
import com.capgemini.exception.PhoneNoDoesNotExist;

public class WalletRepoImpl implements WalletRepo
{
	@Override
	public boolean save(Customer customer) throws DuplicatePhoneNo
	{	
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Empl");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		if (entitymanager.find(Customer.class, customer.getMobileNumber()) == null)
		{
			entitymanager.persist(customer);
			entitymanager.getTransaction().commit();
			entitymanager.close();
			emfactory.close();
			return true;
		}
		else
			return false;
	}

	@Override
	public Customer findOne(String mobilenumber) throws PhoneNoDoesNotExist
	{
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Empl");
		EntityManager entitymanager = emfactory.createEntityManager();
		Customer cust = entitymanager.find(Customer.class, mobilenumber);
		emfactory.close();
		entitymanager.close();
		if (cust != null)
			return cust;
		else
			throw new PhoneNoDoesNotExist();
	}
	
	@Override
	public Customer updateBal(Customer customer) throws PhoneNoDoesNotExist
	{
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Empl");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Customer cust = entitymanager.find(Customer.class, customer.getMobileNumber());
		cust.setWallet(customer.getWallet());
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
		return cust;
	}
}
