package com.example.stipendije.controller;

import com.example.stipendije.model.entity.Application;
import com.example.stipendije.model.entity.Student;
import com.example.stipendije.model.entity.User;
import com.example.stipendije.model.enums.ApplicationStatus;
import com.example.stipendije.model.enums.LocationType;
import com.example.stipendije.model.enums.SocialStatus;
import com.example.stipendije.model.enums.AchievementType;
import com.example.stipendije.model.enums.Role;
import com.example.stipendije.repository.ApplicationRepository;
import com.example.stipendije.repository.StudentRepository;
import com.example.stipendije.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ApplicationDecisionControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        applicationRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "STUDENT")
    void shouldDenyAccessForNonAdmin() throws Exception {
        mockMvc.perform(post("/api/scholarships/decide")
                        .param("academicYear", "2025-2026")
                        .param("limit", "1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldAllowAccessForAdmin() throws Exception {
        User u = new User();
        u.setEmail("student.user@test.com");
        u.setPassword("password");
        u.setRole(Role.STUDENT);
        u.setEnabled(true);
        userRepository.save(u);

        Student s = new Student();
        s.setUser(u);
        s.setFirstName("Test");
        s.setLastName("Student");
        s.setGpa(9.0);
        s.setEmail("test@student.com");
        s.setIncomePerMember(500.0);
        s.setAchievementType(AchievementType.NATIONAL);
        s.setLocationType(LocationType.URBAN);
        s.setSocialStatus(SocialStatus.NORMAL);
        studentRepository.save(s);

        Application app = new Application();
        app.setStudent(s);
        app.setAcademicYear("2025-2026");
        app.setTotalPoints(95.0);
        app.setStatus(ApplicationStatus.PENDING);

        app.setCreatedAt(LocalDateTime.now());

        applicationRepository.save(app);

        mockMvc.perform(post("/api/scholarships/decide")
                        .param("academicYear", "2025-2026")
                        .param("limit", "1")
                        .with(csrf()))
                .andExpect(status().isOk());
    }
}
