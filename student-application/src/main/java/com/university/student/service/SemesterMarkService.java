package com.university.student.service;

import com.university.student.exception.IdAlreadyPresentException;
import com.university.student.exception.StudentNotFoundException;
import com.university.student.model.SemesterMark;
import java.util.List;
import java.util.Map;

public interface SemesterMarkService {

    /**
     * Retrieves a list of all available semesters.
     *
     * @return a {@link List} of {@link String} objects representing all semesters
     */
    public List<String> getAllSemester();

    /**
     * Adds marks for a student identified by the given ID.
     *
     * @param id the unique identifier of the student
     * @param marks a {@link List} of {@link SemesterMark} objects containing the marks to be added
     * @return a {@link String} indicating the outcome or success message of the add operation
     * @throws StudentNotFoundException if no student exists with the given ID
     * @throws IdAlreadyPresentException if marks for the specified semester already exist for the student
     */
    public String addMark(String id, List<SemesterMark> marks) throws StudentNotFoundException, IdAlreadyPresentException;

    /**
     * Retrieves the marks for a student identified by the given ID.
     *
     * @param id the unique identifier of the student
     * @return a {@link List} of {@link SemesterMark} objects containing the student's marks
     * @throws StudentNotFoundException if no student exists with the given ID
     */
    public List<SemesterMark> getMark(String id) throws StudentNotFoundException;

    /**
     * Retrieves the marks for a specific semester of a student identified by the given ID.
     *
     * @param id the unique identifier of the student
     * @param semester the specific semester for which marks are to be retrieved
     * @return the {@link SemesterMark} object containing the student's marks for the specified semester
     * @throws StudentNotFoundException if no student exists with the given ID
     */
    public SemesterMark getMarkForSemester(String id, String semester) throws StudentNotFoundException;

    /**
     * Retrieves the marks for all semesters of a student identified by the given ID.
     *
     * @param id the unique identifier of the student
     * @return a {@link Map} where the key is the semester (as a {@link String}) and the value is a {@link List} of {@link SemesterMark} objects containing the student's marks for that semester
     * @throws StudentNotFoundException if no student exists with the given ID
     */
    public Map<String, List<SemesterMark>> getMarkForSemester(String id) throws StudentNotFoundException;

}
