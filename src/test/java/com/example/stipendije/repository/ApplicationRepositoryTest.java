package com.example.stipendije.repository;

import com.example.stipendije.model.entity.Application;
import com.example.stipendije.model.entity.Student;
import com.example.stipendije.model.entity.User;
import com.example.stipendije.model.enums.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ApplicationRepositoryTest {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void shouldFindApplicationsByYearAndStatus() {
        User user = new User();
        user.setEmail("student@test.com");
        user.setRole(Role.STUDENT);
        user.setEnabled(true);
        user = userRepository.save(user);

        Student student = new Student();
        student.setUser(user);
        student.setFirstName("Name");
        student.setLastName("Last Name");
        student.setEmail("student@test.com");
        student.setGpa(9.0);
        student.setIncomePerMember(500.0);
        student.setAchievementType(AchievementType.UNIVERSITY);
        student.setLocationType(LocationType.URBAN);
        student.setSocialStatus(SocialStatus.NORMAL);
        student = studentRepository.save(student);

        Application app = new Application();
        app.setAcademicYear("2025-2026");
        app.setStatus(ApplicationStatus.PENDING);
        app.setTotalPoints(90.0);
        app.setCreatedAt(LocalDateTime.now());
        app.setStudent(student);

        applicationRepository.save(app);

        List<Application> found = applicationRepository.findByAcademicYearAndStatus("2025-2026", ApplicationStatus.PENDING);

        assertFalse(found.isEmpty());
        assertEquals("2025-2026", found.get(0).getAcademicYear());
    }
}
