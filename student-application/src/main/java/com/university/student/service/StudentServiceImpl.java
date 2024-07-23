package com.university.student.service;

import com.university.student.annotation.LogAspect;
import com.university.student.exception.IdAlreadyPresentException;
import com.university.student.exception.StudentNotFoundException;
import com.university.student.model.SemesterMark;
import com.university.student.model.Student;
import com.university.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.university.student.constant.StudentConstant.*;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student getStudentDetail(String id) throws StudentNotFoundException {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isEmpty())
            throw new StudentNotFoundException(STUDENT_IS_NOT_FOUND);
        return student.get();
    }

    public List<Student> getAllStudentsDetails(){
        return studentRepository.findAll();
    }

    @Transactional
    public String saveStudent(Student student) throws IdAlreadyPresentException {
        if (!isStudentPresent(student.getId()))
            throw new IdAlreadyPresentException("Id is already present");
        studentRepository.save(student);
        return SAVED;
    }

    @LogAspect
    @Transactional
    public String saveStudent(List<Student> students) {
        studentRepository.saveAll(students);
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

    @Transactional
    public String saveMark(String id, List<SemesterMark> marks) throws StudentNotFoundException, IdAlreadyPresentException {
        Student student = getStudentDetail(id);
        student.setSemesterMarks(marks);
        updateStudent(student);
        return SAVED;
    }

    public List<SemesterMark> getMark(String id) throws StudentNotFoundException {
        return getStudentDetail(id).getSemesterMarks();
    }
    

    private boolean isStudentPresent(String id){
        Optional<Student> studentOp = studentRepository.findById(id);
        return studentOp.isEmpty();
    }


}
