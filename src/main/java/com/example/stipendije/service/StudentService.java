package com.example.stipendije.service;

import com.example.stipendije.dto.StudentRequestDTO;
import com.example.stipendije.model.entity.Student;
import com.example.stipendije.exception.DuplicateEmailException;
import com.example.stipendije.model.entity.User;
import com.example.stipendije.repository.StudentRepository;
import com.example.stipendije.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    public StudentService(StudentRepository studentRepository, UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    @PreAuthorize("hasRole('STUDENT')")
    public Student createStudent(StudentRequestDTO dto, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("The user was not found in the database"));

        if(studentRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateEmailException("Email" + (dto.getEmail()) + " is already taken!");
        }

        Student student = new Student();
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setGpa(dto.getGpa());
        student.setIncomePerMember(dto.getIncomePerMember());
        student.setSocialStatus(dto.getSocialStatus());
        student.setLocationType(dto.getLocationType());
        student.setAchievementType(dto.getAchievementType());

        student.setUser(user);

        return studentRepository.save(student);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
