package com.library.person;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.ListIterator;

public class StudentManager {
	ObjectOutputStream oos_student = null;
	ObjectInputStream ooi_student = null;
	File student_file = null;
	ArrayList<Student> student_list = null;

	@SuppressWarnings("unchecked")
	public StudentManager() {
		student_file = new File("Student.dat");
		student_list = new ArrayList<Student>();
		if (student_file.exists()) {
			try {
				ooi_student = new ObjectInputStream(new FileInputStream(student_file));
				student_list = (ArrayList<Student>) ooi_student.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}

		}
	}

	public void addAStudent(Student student) {
		student_list.add(student);
	}

	public Student get(int rollNo) {
		for (Student student : student_list) {
			if (student.getRollNo() == rollNo) {
				return student;
			}

		}
		return null;
	}

	public boolean updateStudent(int update_rollNo, String name, String emailId, String phoneNumber, String address,
			String dob, int std, String divison) {

		ListIterator<Student> iterator = student_list.listIterator();
		while (iterator.hasNext()) {
			Student student = iterator.next();
			if (student.getRollNo() == update_rollNo) {
				student.setAddress(address);
				student.setName(name);
				student.setEmailId(emailId);

				student.setDivison(divison);
				student.setDob(dob);
				student.setPhoneNumber(phoneNumber);
				student.setStd(std);
				return true;

			}
		}
		return false;

	}

	public void viewAllStudents() {
		for (Student student : student_list) {
			System.out.println(student);
		}
	}

	public boolean deleteStudent(int delete_rollNo) {
		ListIterator<Student> student_ite = (ListIterator<Student>) student_list.listIterator();
		while (student_ite.hasNext()) {
			Student student = student_ite.next();
			if (student.getRollNo() == delete_rollNo) {
				student_list.remove(student);
				return true;
			}
		}
		return false;
	}

	public void writeToFile() {
		try {
			oos_student = new ObjectOutputStream(new FileOutputStream(student_file));
			oos_student.writeObject(student_list);
		} catch (IOException io) {
			io.printStackTrace();
		}
	}
}
