package com.gobeyond.hazelcast.cache.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gobeyond.hazelcast.cache.model.Student;
import com.gobeyond.hazelcast.cache.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/students")
@Slf4j
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping(value = "/")
	public List<Student> getAllStudents() {

		log.info("::: List Of All Students Inside Controller :::");
		return studentService.findAll();
	}

	@GetMapping(value = "/studentNumber/{studentNumber}")
	public Student getStudentByStudentNumber(@PathVariable("studentNumber") Long studentNumber) {

		log.info("::: Find By Student Number Inside Controller :::" + studentNumber);

		return studentService.findByStudentNumber(studentNumber);
	}

	@GetMapping(value = "/email/{email}")
	public Student getStudentByEmail(@PathVariable("email") String email) {
		
		log.info("::: Find By Student Email Inside Controller ::: " + email);

		return studentService.findByEmail(email);
	}

	@GetMapping(value = "/orderByGpa")
	public List<Student> findAllByOrderByGpaDesc() {
		return studentService.findAllByOrderByGpaDesc();
	}

	@PostMapping(value = "/")
	public ResponseEntity<?> saveOrUpdateStudent(@RequestBody Student student) {
		studentService.saveOrUpdateStudent(student);
		return new ResponseEntity("Student added successfully", HttpStatus.OK);
	}

	@DeleteMapping(value = "/{studentNumber}")
	public void deleteStudent(@PathVariable Long studentNumber) {
		studentService.deleteStudent(studentService.findByStudentNumber(studentNumber).getId());
	}

}
