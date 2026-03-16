package com.example.stipendije.service;

import com.example.stipendije.model.entity.Application;
import com.example.stipendije.model.enums.ApplicationStatus;
import com.example.stipendije.repository.ApplicationRepository;
import com.example.stipendije.service.ranking.ApplicationRankingComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApplicationDecisionServiceTest {

    private ApplicationDecisionService decisionService;
    private ApplicationRepository applicationRepository = mock(ApplicationRepository.class);
    private ApplicationRankingComparator comparator = new ApplicationRankingComparator();

    @BeforeEach
    void setUp() {
        decisionService = new ApplicationDecisionService(applicationRepository, comparator);
    }

    @Test
    void shouldThrowExceptionIfDecisionsAlreadyMade() {
        when(applicationRepository.existsByAcademicYearAndStatus("2025-2026", ApplicationStatus.APPROVED))
                .thenReturn(true);

        assertThrows(IllegalStateException.class, () -> {
            decisionService.decideScholarships("2025-2026", 1);
        });
    }

    @Test
    void shouldCorrectlyAssignStatusBasedOnLimit() {
        Application app1 = new Application(); app1.setTotalPoints(90.0);
        Application app2 = new Application(); app2.setTotalPoints(80.0);
        List<Application> apps = new ArrayList<>(List.of(app1, app2));

        when(applicationRepository.existsByAcademicYearAndStatus(anyString(), any())).thenReturn(false);
        when(applicationRepository.findByAcademicYearAndStatus(anyString(), any())).thenReturn(apps);

        decisionService.decideScholarships("2025-2026", 1);

        assertEquals(ApplicationStatus.APPROVED, app1.getStatus());
        assertEquals(ApplicationStatus.REJECTED, app2.getStatus());

        verify(applicationRepository).saveAll(any());
    }

    @Test
    void shouldHandleMoreScholarshipsThanApplications() {
        Application app1 = new Application(); app1.setTotalPoints(90.0);
        Application app2 = new Application(); app2.setTotalPoints(80.0);
        List<Application> apps = new ArrayList<>(List.of(app1, app2));

        when(applicationRepository.findByAcademicYearAndStatus(anyString(), any())).thenReturn(apps);

        decisionService.decideScholarships("2025-2026", 5);

        assertEquals(ApplicationStatus.APPROVED, app1.getStatus());
        assertEquals(ApplicationStatus.APPROVED, app2.getStatus());
    }
}
