package com.example.stipendije.service;

import com.example.stipendije.dto.ApplicationRankingDTO;
import com.example.stipendije.exception.ResourceNotFoundException;
import com.example.stipendije.model.entity.Application;
import com.example.stipendije.repository.ApplicationRepository;
import com.example.stipendije.service.ranking.ApplicationRankingComparator;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationRankingService {

    private final ApplicationRepository applicationRepository;

    public ApplicationRankingService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<ApplicationRankingDTO> getRankingForYear(String academicYear) {

        List<Application> applications = applicationRepository.findByAcademicYear(academicYear);

        if (applications.isEmpty()) {
            throw new ResourceNotFoundException(
                    "Ranking not available for academic year " + academicYear);
        }

        applications.sort(new ApplicationRankingComparator());

        List<ApplicationRankingDTO> ranking = new ArrayList<>();

        int rank = 1;
        for (Application app: applications) {
            ranking.add(new ApplicationRankingDTO(
                    app.getId(),
                    app.getStudent().getFirstName(),
                    app.getStudent().getLastName(),
                    app.getTotalPoints(),
                    rank++,
                    app.getStatus()
            ));
        }
        return ranking;
    }
}
