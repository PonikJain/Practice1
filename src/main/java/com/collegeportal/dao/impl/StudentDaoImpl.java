package com.collegeportal.dao.impl;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.collegeportal.dao.StudentDao;
import com.collegeportal.entity.Student;

@Repository
public class StudentDaoImpl implements StudentDao{
	Logger logger = LogManager.getLogger(StudentDaoImpl.class);
	
	@PersistenceContext
    private EntityManager entityManager;

	public Student saveStudent(Student student) {
		logger.info("DAO layer called ");
		return entityManager.merge(student);
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
