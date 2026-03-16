package com.example.stipendije.service;

import com.example.stipendije.dto.PeriodRequestDTO;
import com.example.stipendije.model.entity.ApplicationPeriod;
import com.example.stipendije.repository.ApplicationPeriodRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class ApplicationPeriodService {

    private final ApplicationPeriodRepository repository;

    public ApplicationPeriodService(ApplicationPeriodRepository repository) {
        this.repository = repository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ApplicationPeriod createOrUpdate(PeriodRequestDTO dto)  {

        ApplicationPeriod period = repository.findByAcademicYearAndActiveTrue(dto.getAcademicYear())
                .orElse(new ApplicationPeriod());

        period.setAcademicYear(dto.getAcademicYear());
        period.setStartDate(dto.getStartDate());
        period.setEndDate(dto.getEndDate());
        period.setActive(true);

        return repository.save(period);
    }
}
