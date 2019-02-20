package com.capgemini.exception;

@SuppressWarnings("serial")
public class PhoneNoDoesNotExist extends Exception
{
	@Override
	public String toString()
	{
		return "Phone Number does not exist";
	}
}
