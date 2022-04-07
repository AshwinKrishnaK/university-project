package com.college.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.college.entity.Student;
import com.college.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepo;
	
	public Student saveStudent(Student student) {
		return studentRepo.save(student);
	}
	
	public List<Student> getAllStudents() {
		return studentRepo.findAll();
	}
	
	public Student getStudentByID(int id) {
		return studentRepo.getById(id);
	}
	
	public int deleteStudent(int id) {
		studentRepo.deleteById(id);
		return id;
	}
	
	public boolean isPresent(int id) {
		return studentRepo.existsById(id);
	}
	



}
