package com.collegeportal.service.impl;

import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.collegeportal.dao.StudentDao;
import com.collegeportal.entity.Student;
import com.collegeportal.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentDao studentdao;

	@Transactional
	public Student saveStudent(Student student) {
		return studentdao.saveStudent(student);
	}

	public Student getOneStudentbyID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<Student> getStudentByName(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
