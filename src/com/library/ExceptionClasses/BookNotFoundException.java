package com.library.ExceptionClasses;

public class BookNotFoundException extends Exception {
	public BookNotFoundException() {

	}

	@Override
	public String toString() {
		return "BookNotFoundException is generated";
	}

}
