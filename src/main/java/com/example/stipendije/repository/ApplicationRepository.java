package com.example.stipendije.repository;

import com.example.stipendije.model.entity.Application;
import com.example.stipendije.model.entity.Student;
import com.example.stipendije.model.enums.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findByAcademicYearAndStatus(String academicYear, ApplicationStatus status);

    List<Application> findByAcademicYear(String academicYear);

    boolean existsByStudentAndAcademicYear(Student student, String academicYear);

    boolean existsByAcademicYearAndStatus(String academicYear, ApplicationStatus status);
}
