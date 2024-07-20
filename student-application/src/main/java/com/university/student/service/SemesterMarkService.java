package com.university.student.service;

import com.university.student.exception.IdAlreadyPresentException;
import com.university.student.exception.StudentNotFoundException;
import com.university.student.model.SemesterMark;
import java.util.List;
import java.util.Map;

public interface SemesterMarkService {

    public List<String> getAllSemester();

    public String addMark(String id, List<SemesterMark> marks) throws StudentNotFoundException, IdAlreadyPresentException;

    public List<SemesterMark> getMark(String id) throws StudentNotFoundException;

    public SemesterMark getMarkForSemester(String id, String semester) throws StudentNotFoundException;

    public Map<String, List<SemesterMark>> getMarkForSemester(String id) throws StudentNotFoundException;

}
