package com.collegeportal.dao;

import java.util.Set;

import com.collegeportal.entity.Student;

public interface StudentDao{

	public Student saveStudent(Student student);
	
	public Student getOneStudentbyID(int id);
	
	public Set<Student> getStudentByName(int id);
}
