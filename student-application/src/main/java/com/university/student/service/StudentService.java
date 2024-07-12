package com.university.student.service;

import com.university.student.exception.IdAlreadyPresentException;
import com.university.student.exception.StudentNotFoundException;
import com.university.student.model.SemesterMark;
import com.university.student.model.Student;

import java.util.List;

/**
 * @author ashwin
 * */
public interface StudentService {
    /**
     * Retrieves the details of a student based on the provided student ID.
     *
     * @param id the unique identifier of the student
     * @return the {@link Student} object containing the student's details
     * @throws StudentNotFoundException if no student exists with the given ID
     */
    public Student getStudentDetail(String id) throws StudentNotFoundException;

    /**
     * Retrieves a list of all student details.
     *
     * @return a {@link List} of {@link Student} objects containing details of all students
     */
    public List<Student> getAllStudentsDetails();

    /**
     * Saves the details of a student.
     *
     * @param student the {@link Student} object containing the student's details to be saved
     * @return a {@link String} indicating the outcome or success message of the save operation
     * @throws IdAlreadyPresentException if a student with the same ID already exists
     */
    public String saveStudent(Student student) throws IdAlreadyPresentException;

    /**
     * Updates the details of an existing student.
     *
     * @param student the {@link Student} object containing the updated details of the student
     * @return a {@link String} indicating the outcome or success message of the update operation
     * @throws StudentNotFoundException if no student exists with the given ID
     */
    public String updateStudent(Student student) throws StudentNotFoundException;

    /**
     * Deletes the student identified by the given ID.
     *
     * @param id the unique identifier of the student to be deleted
     * @return a {@link String} indicating the outcome or success message of the delete operation
     * @throws StudentNotFoundException if no student exists with the given ID
     */
    public String deleteStudent(String id) throws StudentNotFoundException;

    /**
     * Saves the marks for a student identified by the given ID.
     *
     * @param id the unique identifier of the student
     * @param marks a {@link List} of {@link SemesterMark} objects containing the marks to be saved
     * @return a {@link String} indicating the outcome or success message of the save operation
     * @throws StudentNotFoundException if no student exists with the given ID
     * @throws IdAlreadyPresentException if marks for the specified semester already exist for the student
     */
    public String saveMark(String id, List<SemesterMark> marks) throws StudentNotFoundException, IdAlreadyPresentException;

    /**
     * Retrieves the marks for a student identified by the given ID.
     *
     * @param id the unique identifier of the student
     * @return a {@link List} of {@link SemesterMark} objects containing the student's marks
     * @throws StudentNotFoundException if no student exists with the given ID
     */
    public List<SemesterMark> getMark(String id) throws StudentNotFoundException;

}
