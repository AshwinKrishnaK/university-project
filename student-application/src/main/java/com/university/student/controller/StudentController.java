package com.university.student.controller;

import com.university.student.exception.IdAlreadyPresentException;
import com.university.student.exception.StudentNotFoundException;
import com.university.student.model.Response;
import com.university.student.model.Student;
import com.university.student.service.StudentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.university.student.constant.StudentConstant.SUCCESS;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @PostMapping("/")
    public ResponseEntity<Response> saveStudent(@Valid @RequestBody Student student) throws IdAlreadyPresentException {
        return ResponseEntity.ok(new Response(studentService.saveStudent(student),SUCCESS));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getStudentById(@PathVariable("id") String id) throws StudentNotFoundException {
        return ResponseEntity.ok(new Response(studentService.getStudentDetail(id),SUCCESS));
    }

    @GetMapping("/")
    public ResponseEntity<Response> getAllStudent(){
        return ResponseEntity.ok(new Response(studentService.getAllStudentsDetails(),SUCCESS));
    }

}
