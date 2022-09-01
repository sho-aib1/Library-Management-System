package com.library.mainClass;

import java.util.Scanner;

import com.library.ExceptionClasses.BookNotFoundException;
import com.library.ExceptionClasses.StudentNotFoundException;
import com.library.book.Book;
import com.library.book.BookManager;
import com.library.person.Student;
import com.library.person.StudentManager;
import com.library.transaction.BookTransactionManager;

public class MainApp {
	static Scanner sc = new Scanner(System.in);
	static StudentManager sm = new StudentManager();
	static BookManager bm = new BookManager();
	static BookTransactionManager btm = new BookTransactionManager();

	public static void main(String[] args) {

		int choice;

		do {
			System.out.println(
					"--------------------------Library Management System--------------------------------------");
			System.out.println("Enter 1 for Student");
			System.out.println("Enter 2 for Librarian");
			System.out.println("Enter 3 for Exit");
			System.out.println("------------------------------------------------------------------------------------");

			choice = sc.nextInt();

			if (choice == 1) // for student related
			{
				System.out.println("Enter your Roll No");
				int rollNo = sc.nextInt();
				try {
					Student s = sm.get(rollNo);
					if (s == null) {
						throw new StudentNotFoundException();
					}
					int stud_choice;
					do {
						System.out.println("----------------------------------------------------------");
						System.out.println("Enter 1 to view All Books");
						System.out.println("Enter 2 to search book by ISBN");
						System.out.println("Enter 3 to list Books by Subject");
						System.out.println("Enter 4 to Issue a Book");
						System.out.println("Enter 5 to Return a Book");
						System.out.println("Enter 99 to Exit");
						System.out.println("----------------------------------------------------------");

						stud_choice = sc.nextInt();
						studentTask(stud_choice, rollNo);
					} while (stud_choice != 99);

				} catch (StudentNotFoundException e) {
					System.out.println(e);
				}

			}

			else if (choice == 2) {
				int lib_choice;
				do {
					System.out.println("----------------------------------------------------------");

					System.out.println("Enter 11 to View All Students");
					System.out.println("Enter 12 to Print a Student by Roll No");
					System.out.println("Enter 13 to Register a Student");
					System.out.println("Enter 14 to Update a Student");
					System.out.println("Enter 15 to Delete a Student");
					System.out.println("----------------------------------------------------------");
					System.out.println("Enter 21 to View All Books");
					System.out.println("Enter 22 to Print a Book by ISBN");
					System.out.println("Enter 23 to Add a new Book");
					System.out.println("Enter 24 to Updata a Book");
					System.out.println("Enter 25 to Delete a Book");
					System.out.println("----------------------------------------------------------");
					System.out.println("Enter 31 to view All Transaction");
					System.out.println("Enter 99 to Exit");
					System.out.println("----------------------------------------------------------");
					lib_choice = sc.nextInt();
					librarianTask(lib_choice);
				} while (lib_choice != 99);

			}

		} while (choice != 3);
		sm.writeToFile();
		bm.writeToFile();
		btm.writeToFile();

		System.out.println("Thank you for Using our Service and Have a Good Day !");
		System.out.println("----------------------------------------------------------");

	}

	private static void librarianTask(int lib_choice) {
		if (lib_choice == 11) // view all Students
		{
			System.out.println("----------------------------------------------------------");

			System.out.println("All the Students Records are");
			sm.viewAllStudents();
		} else if (lib_choice == 12)// print student by RollNO
		{
			System.out.println("Enter RollNo of a Student");
			int rollno = sc.nextInt();
			Student student = sm.get(rollno);
			if (student == null) {
				System.out.println("----------------------------------------------------------");

				System.out.println("No Records Matches this Rollno");
				System.out.println("----------------------------------------------------------");

			} else {
				System.out.println(student);
			}
		} else if (lib_choice == 13)// add a Student
		{

			System.out.println("----------------------------------------------------------");

			System.out.println("Enter Student Details to add");
			int rollno, standard;
			String email, name, address, phno, dob, division;
			sc.nextLine();
			System.out.println("Name: ");
			name = sc.nextLine();
			System.out.println("Email: ");
			email = sc.nextLine();

			System.out.println("Phone Number: ");
			phno = sc.nextLine();
			System.out.println("Address");
			address = sc.nextLine();
			System.out.println("Date of Birth");
			dob = sc.nextLine();
			System.out.println("Roll no");
			rollno = sc.nextInt();
			System.out.println("Student Standard");
			standard = sc.nextInt();
			sc.nextLine();
			System.out.println("Division");
			division = sc.nextLine();

			Student student = new Student(name, email, phno, address, dob, rollno, standard, division);
			sm.addAStudent(student);
		} else if (lib_choice == 14)// update a Student
		{

			System.out.println("----------------------------------------------------------");

			System.out.println("Enter a Rollno of a Student to Modify the Record ");
			int modify_rollno = sc.nextInt();
			Student student = sm.get(modify_rollno);
			try {
				if (student == null) {
					throw new StudentNotFoundException();
				}
				System.out.println("----------------------------------------------------------");
				System.out.println("Enter Student Details to add");
				int standard;
				String email, name, address, phno, dob, division;
				sc.nextLine();
				System.out.println("Enter Name: ");
				name = sc.nextLine();
				System.out.println("Email: ");
				email = sc.nextLine();
				System.out.print("Phone Number: ");
				phno = sc.nextLine();
				System.out.println("Address");
				address = sc.nextLine();
				System.out.println("Date of Birth");
				dob = sc.nextLine();
				System.out.println("Student Standard");
				standard = sc.nextInt();
				sc.nextLine();
				System.out.println("Division");
				division = sc.nextLine();
				if (sm.updateStudent(modify_rollno, name, email, phno, address, dob, standard, division)) {
					System.out.println("Details of Student with RollNo " + modify_rollno + " Update Successfully");

				}
			} catch (StudentNotFoundException e) {
				System.out.println(e);
			}
		} else if (lib_choice == 15) // delete a Student
		{
			System.out.println("----------------------------------------------------------");
			System.out.println("Enter The Roll Number to Delete the Record");
			int delete_rollNo;
			delete_rollNo = sc.nextInt();
			if (sm.deleteStudent(delete_rollNo)) {
				System.out.println("----------------------------------------------------------");
				System.out.println("Student Record is Removed");
				System.out.println("----------------------------------------------------------");
			} else {
				System.out.println("----------------------------------------------------------");
				System.out.println("No Record with Given Roll Number Exists");
				System.out.println("----------------------------------------------------------");
			}

		} else if (lib_choice == 21) // view all Books
		{
			System.out.println("All the Book Available in The Library are: ");
			bm.viewAllBooks();

		} else if (lib_choice == 22) // print book by isbn
		{
			System.out.println("Enter a Book ISBN ");
			int isbn = sc.nextInt();
			Book book = bm.searchBookByIsbn(isbn);
			if (book == null) {
				System.out.println("----------------------------------------------------------");
				System.out.println("No Book Matches with this ISBN");
				System.out.println("----------------------------------------------------------");
			} else {
				System.out.println(book);
			}
		} else if (lib_choice == 23)// add a book
		{
			System.out.println("Enter Book Details to Add");
			int isbn, available_Quantity, edition;
			String title, publisher, author, subject;
			System.out.println("ISBN");
			isbn = sc.nextInt();
			sc.nextLine();
			System.out.println("Title");
			title = sc.nextLine();
			System.out.println("Author");
			author = sc.nextLine();
			System.out.println("Edition");
			edition = sc.nextInt();
			sc.nextLine();
			System.out.println("Subject");
			subject = sc.nextLine();
			System.out.println("Available quantity");
			available_Quantity = sc.nextInt();
			sc.nextLine();
			System.out.println("Publisher");
			publisher = sc.nextLine();
			Book book = new Book(isbn, title, author, publisher, edition, subject, available_Quantity);
			bm.addBook(book);
			System.out.println("----------------------------------------------------------");
			System.out.println("Book Added SuccesFully");
		}

		else if (lib_choice == 24)// update a book
		{
			System.out.println("Enter a Book ISBN which you want to Modify");
			int update_isbn = sc.nextInt();
			Book book = bm.searchBookByIsbn(update_isbn);
			try {
				if (book == null) {
					throw new BookNotFoundException();
				}
				System.out.println("----------------------------------------------------------");
				System.out.println("Enter Book Details to Modify");
				int available_Quantity, edition;
				String title, publisher, author, subject;
				sc.nextLine();
				System.out.println("Title");
				title = sc.nextLine();
				System.out.println("Author");
				author = sc.nextLine();
				System.out.println("Edition");
				edition = sc.nextInt();
				sc.nextLine();
				System.out.println("Subject");
				subject = sc.nextLine();
				System.out.println("Available quantity");
				available_Quantity = sc.nextInt();
				sc.nextLine();
				System.out.println("Publisher");
				publisher = sc.nextLine();
				if (bm.updateBook(update_isbn, title, author, publisher, edition, subject, available_Quantity)) {
					System.out.println("----------------------------------------------------------");

					System.out.println("Book Details with ISBN " + update_isbn + " Modify Successfull");
					System.out.println("----------------------------------------------------------");
				}
			} catch (BookNotFoundException e) {
				System.out.println(e);
			}

		} else if (lib_choice == 25) {
			System.out.println("Enter a Book ISBN to Delete a Book");
			int del = sc.nextInt();
			Book book = bm.searchBookByIsbn(del);
			if (bm.deleteBook(del)) {
				System.out.println("----------------------------------------------------------");
				System.out.println("Delete Successfully");
				System.out.println("----------------------------------------------------------");
			} else {
				System.out.println("----------------------------------------------------------");
				System.out.println("Book Not Exists");
				System.out.println("----------------------------------------------------------");
			}
		} else if (lib_choice == 31) {
			System.out.println("All the Transaction are");
			btm.showAll();
		}

	}

	private static void studentTask(int stud_choice, int rollNo) {
		if (stud_choice == 1) {
			System.out.println("All the Books Records are ");
			bm.viewAllBooks();

		} else if (stud_choice == 2) {
			System.out.println("Enter Book ISBN number");
			int isbn = sc.nextInt();
			Book book = bm.searchBookByIsbn(isbn);
			if (book == null) {
				System.out.println("No book with ISBN " + isbn + " exists in our Library");
			} else {
				System.out.println(book);
			}
		} else if (stud_choice == 3) {
			System.out.println("Enter the Subject to search");
			sc.nextLine();
			String sub = sc.nextLine();
			bm.listBooksBySubjects(sub);

		}

		else if (stud_choice == 4) {
			System.out.println("Enter a ISBN to Issue a Book");
			int isbn = sc.nextInt();
			Book book = bm.searchBookByIsbn(isbn);
			try {
				if (book == null) {
					throw new BookNotFoundException();
				}

				if (book.getAvailable_quantity() > 0) {
					if (btm.issueBook(rollNo, isbn)) {
						book.setAvailable_quantity(book.getAvailable_quantity() - 1);
						System.out.println("Book has been Issued");
					}
				} else {
					System.out.println("You already have 3 Books");
				}

			} catch (BookNotFoundException e) {
				System.out.println(e);
			}

		}

		else if (stud_choice == 5) {
			System.out.println("Enter a Book ISBN to Return a Book");
			int isbn = sc.nextInt();
			Book book = bm.searchBookByIsbn(isbn);

			if (book != null) {
				if (btm.returnBook(rollNo, isbn)) {
					book.setAvailable_quantity(book.getAvailable_quantity() + 1);
					System.out.println("Thank you for Returning a Book");
				} else {
					System.out.println("Could not Return a book");
				}
			} else {
				System.out.println("Book with this ISBN does not Exists");
			}

		}

	}

}
