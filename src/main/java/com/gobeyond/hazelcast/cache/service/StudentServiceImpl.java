package com.gobeyond.hazelcast.cache.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.gobeyond.hazelcast.cache.model.Student;
import com.gobeyond.hazelcast.cache.repository.StudentRepository;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private HazelcastInstance instance;

	@Override
	public List<Student> findAll() {
		log.info("::: List Of All Students :::");
		return studentRepository.findAll();
	}

	@Override
	@Cacheable(value = "stucache", key = "#studentNumber", unless = "#result == null")
	public Student findByStudentNumber(long studentNumber) {

		log.info("::: Find By Student Number :::" + studentNumber);

		Student stuInfo = studentRepository.findByStudentNumber(studentNumber);

		IMap<Long, Student> cacheStudentMap = instance.getMap("stucache");

		cacheStudentMap.put(studentNumber, stuInfo);

		return cacheStudentMap.get(studentNumber);
	}

	@Override
	@CachePut(value = "stucache", key = "#email", unless = "#result == null")
	public Student findByEmail(String email) {

		log.info("::: Find By Student Email ::: " + email);

		Student stuInfo = studentRepository.findByEmail(email);

		IMap<String, Student> cacheStudentMap = instance.getMap("stucache");

		cacheStudentMap.put(email, stuInfo);

		return cacheStudentMap.get(email);
	}

	@Override
	public List<Student> findAllByOrderByGpa() {

		log.info("::: List Of All Studnets By Order :::");
		return studentRepository.findAllByOrderByGpa();
	}

	@Override
	public void saveOrUpdateStudent(Student student) {

		log.info("::: Save and update students details ::: " + student);
		studentRepository.save(student);
	}

	@Override
	public void deleteStudent(String id) {
		log.info("::: Dlete the studnet by id ::: " + id);
		studentRepository.deleteById(id);
	}

	@Override
	public List<Student> findAllByOrderByGpaDesc() {

		log.info("::: List Of All Studnets By Desc Order :::");
		return studentRepository.findAllByOrderByGpaDesc();
	}
}
