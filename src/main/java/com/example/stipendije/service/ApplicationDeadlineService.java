package com.example.stipendije.service;

import com.example.stipendije.model.entity.ApplicationPeriod;
import com.example.stipendije.repository.ApplicationPeriodRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ApplicationDeadlineService {

    private final ApplicationPeriodRepository repository;

    public ApplicationDeadlineService(ApplicationPeriodRepository repository) {
        this.repository = repository;
    }

    public void checkDeadline(String academicYear) {
        ApplicationPeriod period = repository
                .findByAcademicYearAndActiveTrue(academicYear)
                .orElseThrow(() ->
                        new IllegalStateException("No active application period"));

        LocalDate today = LocalDate.now();

        if (today.isBefore(period.getStartDate())) {
            throw new IllegalStateException("The competition for this year is not yet open!");
        }

        if(today.isAfter(period.getEndDate())) {
            throw new IllegalStateException("Application deadline has expired!");
        }
    }
}
