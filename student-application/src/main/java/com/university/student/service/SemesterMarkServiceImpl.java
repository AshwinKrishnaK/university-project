package com.university.student.service;

import com.university.student.exception.IdAlreadyPresentException;
import com.university.student.exception.StudentNotFoundException;
import com.university.student.model.SemesterMark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.university.student.constant.StudentConstant.SAVED;

@Service
public class SemesterMarkServiceImpl implements SemesterMarkService{

    @Autowired
    private StudentServiceImpl studentService;

    public List<String> getAllSemester(){
        return Arrays.asList("S1","S2","S3","S4","S5","S6","S7","S8");
    }

    public String addMark(String id, List<SemesterMark> marks) throws StudentNotFoundException, IdAlreadyPresentException {
        var student = studentService.getStudentDetail(id);
        List<SemesterMark> markList = student.getSemesterMarks();
        for (SemesterMark semesterMark: marks){
            semesterMark.setStudent(student);
        }
        markList.addAll(marks);
        studentService.saveMark(id,markList);
        return SAVED;
    }

    public List<SemesterMark> getMark(String id) throws StudentNotFoundException {
        return studentService.getMark(id);
    }

    public SemesterMark getMarkForSemester(String id, String semester) throws StudentNotFoundException {
        Optional<SemesterMark> semesterMark = getMark(id).stream().filter(mark -> mark.getSemester().equals(semester)).findFirst();
        if (semesterMark.isEmpty()){
            throw new RuntimeException("SemesterMark for this is not present");
        }
        return semesterMark.get();
    }

    public Map<String, List<SemesterMark>> getMarkForSemester(String id) throws StudentNotFoundException {
        return getMark(id).stream().collect(Collectors.groupingBy(SemesterMark::getSemester));
    }
}
