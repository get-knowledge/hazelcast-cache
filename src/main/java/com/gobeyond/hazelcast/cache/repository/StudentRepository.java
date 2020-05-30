package com.gobeyond.hazelcast.cache.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gobeyond.hazelcast.cache.model.Student;

public interface StudentRepository extends MongoRepository<Student, String> {

	Student findByStudentNumber(long studentNumber);

	Student findByEmail(String email);

	List<Student> findAllByOrderByGpa();

	List<Student> findAllByOrderByGpaDesc();

}
