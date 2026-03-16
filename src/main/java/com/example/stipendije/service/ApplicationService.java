package com.example.stipendije.service;

import com.example.stipendije.exception.ApplicationAlreadyExistsException;
import com.example.stipendije.model.entity.Application;
import com.example.stipendije.model.entity.Student;
import com.example.stipendije.model.entity.User;
import com.example.stipendije.model.enums.ApplicationStatus;
import com.example.stipendije.repository.ApplicationRepository;
import com.example.stipendije.security.SecurityUtils;
import jakarta.transaction.Transactional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ScoringService scoringService;
    private final SecurityUtils securityUtils;
    private final ApplicationDeadlineService applicationDeadlineService;

    public ApplicationService(ApplicationRepository applicationRepository,
                              ScoringService scoringService,
                              SecurityUtils securityUtils,
                              ApplicationDeadlineService applicationDeadlineService) {         // check this
        this.applicationRepository = applicationRepository;
        this.scoringService = scoringService;
        this.securityUtils = securityUtils;
        this.applicationDeadlineService = applicationDeadlineService;      // check this
    }

    @PreAuthorize("hasRole('STUDENT')")
    public Application createApplication (String academicYear) {

        applicationDeadlineService.checkDeadline(academicYear);        // check this

        User currentUser = securityUtils.getCurrentUser();
        Student student = currentUser.getStudent();

        if (applicationRepository.existsByStudentAndAcademicYear(student, academicYear)) {
            throw new ApplicationAlreadyExistsException
                    ("Application already exists for this academic year");
        }

        Application application = new Application(student, academicYear);
        application.setStatus(ApplicationStatus.PENDING);

        double points = scoringService.calculateTotalPoints(application);
        application.setTotalPoints(points);

        return applicationRepository.save(application);
    }
}
