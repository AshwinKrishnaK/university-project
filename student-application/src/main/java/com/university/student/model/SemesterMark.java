package com.university.student.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(schema = "mark")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SemesterMark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String semester;
    private Float mark;
    private String subject;
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = true)
    private Student student;
}
