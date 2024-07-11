package com.university.student.service;

import com.university.student.exception.StudentNotFoundException;
import com.university.student.model.Student;
import com.university.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

import static com.university.student.constant.StudentConstant.*;

public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student getStudentDetail(String id) throws StudentNotFoundException {
        Optional<Student> student = studentRepository.findById(id);
        if(!student.isPresent())
            throw new StudentNotFoundException(STUDENT_IS_NOT_FOUND);
        return student.get();
    }

    @Transactional
    public String saveStudent(Student student){
        studentRepository.save(student);
        return SAVED;
    }

    @Transactional
    public String updateStudent(Student student) throws StudentNotFoundException {
        if(isStudentPresent(student.getId()))
            throw new StudentNotFoundException(STUDENT_IS_NOT_FOUND);
        studentRepository.save(student);
        return UPDATED;
    }

    @Transactional
    public String deleteStudent(String id) throws StudentNotFoundException{
       if (isStudentPresent(id))
           throw new StudentNotFoundException(STUDENT_IS_NOT_FOUND);
       studentRepository.deleteById(id);
       return DELETED;
    }
    

    private boolean isStudentPresent(String id){
        Optional<Student> studentOp = studentRepository.findById(id);
        return !studentOp.isPresent();
    }


}
