package com.example.stipendije.controller;

import com.example.stipendije.dto.StudentRequestDTO;
import com.example.stipendije.model.entity.Student;
import com.example.stipendije.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Students", description = "Operations related to student profiles and data")
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(
            summary = "Create student profile",
            description = "Associates personal and academic data with the currently authenticated user. " +
                    "This must be done before applying for scholarships."
    )
    @ApiResponse(responseCode = "201", description = "Student profile successfully created")
    @ApiResponse(responseCode = "400", description = "Invalid input data or profile already exists")
    @PostMapping
    public ResponseEntity<Student> createStudent(
            @Valid @RequestBody StudentRequestDTO dto,
            Authentication authentication) {

        Student savedStudent = studentService.createStudent(dto, authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    @Operation(
            summary = "Get all students",
            description = "Returns a list of all registered students. Typically used by administrators."
    )
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }
}