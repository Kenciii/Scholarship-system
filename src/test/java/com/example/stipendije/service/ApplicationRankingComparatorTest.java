package com.example.stipendije.service;

import com.example.stipendije.model.entity.Application;
import com.example.stipendije.model.entity.Student;
import com.example.stipendije.service.ranking.ApplicationRankingComparator;
import org.junit.jupiter.api.Test;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;

public class ApplicationRankingComparatorTest {

    private final ApplicationRankingComparator comparator = new ApplicationRankingComparator();

    @Test
    void shouldRankHigherGpaFirstWhenPointsAreEqual() {
        Application app1 = new Application();
        app1.setTotalPoints(50.0);
        Student s1 = new Student(); s1.setGpa(8.0);
        app1.setStudent(s1);

        Application app2 = new Application();
        app2.setTotalPoints(50.0);
        Student s2 = new Student(); s2.setGpa(9.0);
        app2.setStudent(s2);

        int result = comparator.compare(app1, app2);
        assertTrue(result > 0, "The student with the higher average should be ahead");
    }
}
