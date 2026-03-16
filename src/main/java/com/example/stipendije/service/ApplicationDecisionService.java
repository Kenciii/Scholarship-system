package com.example.stipendije.service;

import com.example.stipendije.exception.ResourceNotFoundException;
import com.example.stipendije.model.entity.Application;
import com.example.stipendije.model.enums.ApplicationStatus;
import com.example.stipendije.repository.ApplicationRepository;
import com.example.stipendije.service.ranking.ApplicationRankingComparator;
import jakarta.transaction.Transactional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationDecisionService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationRankingComparator comparator;

    public ApplicationDecisionService(ApplicationRepository applicationRepository,
                                      ApplicationRankingComparator comparator) {
        this.applicationRepository = applicationRepository;
        this.comparator = comparator;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public void decideScholarships(String academicYear, int numberOfScholarships) {

        boolean alreadyDecided = applicationRepository
                .existsByAcademicYearAndStatus(academicYear, ApplicationStatus.APPROVED);

        if(alreadyDecided) {
            throw new IllegalStateException("Decisions for the academic year " + academicYear + " have already been made!");
        }

        List<Application> applications = applicationRepository.findByAcademicYearAndStatus
                (academicYear, ApplicationStatus.PENDING);

        if (applications.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No applications found for academic year " + academicYear
            );
        }

        applications.sort(comparator);

        for (int i=0; i < applications.size(); i++) {
            Application app = applications.get(i);

            if(i < numberOfScholarships) {
                app.setStatus(ApplicationStatus.APPROVED);
            }else {
                app.setStatus(ApplicationStatus.REJECTED);
            }
        }
        applicationRepository.saveAll(applications);
    }
}
