package com.college.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.college.entity.Student;
import com.college.service.StudentService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@RestController
public class CollegeController {
	
	@Autowired
	private StudentService serviceRepo;

	@GetMapping("/helloworld")
	public String HelloWorld() {
		return "Hello World";
	}
	
//	Saving new students.	
	@PostMapping("/savestudent")
	public ResponseEntity<Student> saveStudent(@RequestBody Student student) throws Exception {
//		To check duplication of id.
		int tempId = student.getId();
		Boolean idPresent =  serviceRepo.isPresent(tempId);
//		System.out.println(idPresent);
		if(idPresent == true) {
			throw new Exception("Id is already present");
		}
		serviceRepo.saveStudent(student);
		return new ResponseEntity<Student> (student,HttpStatus.OK);
	}
	
//	Getting values of a student.
	@GetMapping("/allStudents")
	public ResponseEntity<List<Student>> getAll(){
		return new ResponseEntity<>(serviceRepo.getAllStudents(),HttpStatus.OK);
		
	}
	
//	Getting details of a single student
	@GetMapping(value="/getStudent/{id}", produces = "application/json")
	public Student getOneStudent(@PathVariable int id) {
		Student stud = serviceRepo.getStudentByID(id);
		System.out.println(stud);
		return stud;
	}
	
//	Deleting A student by id.
	@DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id) throws Exception{
		Boolean idPresent =  serviceRepo.isPresent(id);
		if (idPresent== false) {
			throw new Exception("given id is not present");
		}
        serviceRepo.deleteStudent(id);
        return "Deleted";
    }
	
//	Updating a student.
	@PutMapping("/update/{id}")
	public Student updateuser(@RequestBody Student student,@PathVariable("id")int id) {
		student.setId(id);
		return serviceRepo.saveStudent(student);
	}
	
//	@RequestMapping(value="/get/{id}",method=post)
//	public Student selectSingleStudent(@PathVariable int){
//		
//	}
}
