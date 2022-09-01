package com.library.transaction;

import java.io.Serializable;

public class BookTransaction implements Serializable {
	private int isbn;
	private int rollNO;
	private String issueDate;
	private String returnDate;

	@Override
	public String toString() {
		return "BookTransaction [isbn=" + isbn + ", rollNO=" + rollNO + ", issueDate=" + issueDate + ", ReturnDate="
				+ returnDate + "]";
	}

	public BookTransaction() {
		super();
	}

	public BookTransaction(int isbn, int rollNO, String issueDate, String returnDate) {
		super();
		this.isbn = isbn;
		this.rollNO = rollNO;
		this.issueDate = issueDate;
		this.returnDate = returnDate;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public int getRollNO() {
		return rollNO;
	}

	public void setRollNO(int rollNO) {
		this.rollNO = rollNO;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
}
