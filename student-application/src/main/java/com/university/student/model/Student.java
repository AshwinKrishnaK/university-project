package com.university.student.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(schema = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @NotBlank(message = "Id cannot be blank")
    private String id;

    @Size(message = "Minimum 3 letters required",min = 3)
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Past(message = "Date of birth cannot be empty")
    private LocalDate dateOfBirth;

    @Pattern(regexp = "^[6-9]\\d{9}$",message = "Phone number is incorrect")
    @NotBlank(message = "phone number cannot be empty")
    private String phoneNumber;

    @NotBlank(message = "email cannot be empty")
    @Email
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SemesterMark> semesterMarks;

}
