package com.example.stipendije.repository;

import com.example.stipendije.model.entity.ApplicationPeriod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationPeriodRepository extends JpaRepository<ApplicationPeriod, Long> {

    Optional<ApplicationPeriod> findByAcademicYearAndActiveTrue(String academicYear);
}

