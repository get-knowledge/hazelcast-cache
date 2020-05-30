package com.gobeyond.hazelcast.cache.service;

import java.util.List;

import com.gobeyond.hazelcast.cache.model.Student;

public interface StudentService {

	List<Student> findAll();

	Student findByStudentNumber(long studentNumber);

	Student findByEmail(String email);

	List<Student> findAllByOrderByGpa();

	void saveOrUpdateStudent(Student student);

	void deleteStudent(String id);

	List<Student> findAllByOrderByGpaDesc();

}
