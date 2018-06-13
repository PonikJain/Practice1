package com.collegeportal.service;

import java.util.Set;

import com.collegeportal.entity.Student;

public interface StudentService {

	public Student saveStudent(Student student);

	public Student getOneStudentbyID(int id);

	public Set<Student> getStudentByName(int id);
}
