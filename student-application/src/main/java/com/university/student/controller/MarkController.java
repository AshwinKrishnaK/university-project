package com.university.student.controller;

import com.university.student.exception.IdAlreadyPresentException;
import com.university.student.exception.StudentNotFoundException;
import com.university.student.model.Response;
import com.university.student.model.SemesterMark;
import com.university.student.service.SemesterMarkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.university.student.constant.StudentConstant.SUCCESS;

@RestController
@RequestMapping("/mark")
public class MarkController {

    @Autowired
    private SemesterMarkServiceImpl semesterMarkService;

    @GetMapping("/semesters")
    public ResponseEntity<Response> getAllSemester(){
        return ResponseEntity.ok(new Response(semesterMarkService.getAllSemester(),SUCCESS));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getMark(@PathVariable("id") String id) throws StudentNotFoundException {
        return ResponseEntity.ok(new Response(semesterMarkService.getMark(id),SUCCESS));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Response> saveMark(@PathVariable("id") String id,
                                             @RequestBody List<SemesterMark> semMark) throws StudentNotFoundException, IdAlreadyPresentException {
        return ResponseEntity.ok(new Response(semesterMarkService.addMark(id, semMark),SUCCESS));
    }

    @GetMapping("/semester/{id}")
    public ResponseEntity<Response> getMarkByASemester(@PathVariable("id") String id,
                                             @RequestParam(value = "semester") String semester) throws StudentNotFoundException, IdAlreadyPresentException {
        return ResponseEntity.ok(new Response(semesterMarkService.getMarkForSemester(id, semester),SUCCESS));
    }

    @GetMapping("/semesters/{id}")
    public ResponseEntity<Response> getMarkByASemester(@PathVariable("id") String id) throws StudentNotFoundException, IdAlreadyPresentException {
        return ResponseEntity.ok(new Response(semesterMarkService.getMarkForSemester(id),SUCCESS));
    }
}
