package com.capgemini.exception;

@SuppressWarnings("serial")
public class DuplicatePhoneNo extends Exception {

	@Override
	public String toString()
	{
		return "Duplicate Phone Numbers";
	}

}
