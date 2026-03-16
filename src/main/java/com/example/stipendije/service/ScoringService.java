package com.example.stipendije.service;

import com.example.stipendije.exception.AppModNotAllowedException;
import com.example.stipendije.model.entity.Application;
import com.example.stipendije.model.entity.Student;
import com.example.stipendije.model.enums.AchievementType;
import com.example.stipendije.model.enums.ApplicationStatus;
import com.example.stipendije.model.enums.LocationType;
import com.example.stipendije.model.enums.SocialStatus;
import org.springframework.stereotype.Service;

@Service
public class ScoringService {

    public double calculateTotalPoints(Application application) {
        Student student = application.getStudent();

        if (application.getStatus() != ApplicationStatus.PENDING) {
            throw new AppModNotAllowedException("Application can no longer be modified");
        }

        double points = 0;
        points += scoreGpa(student.getGpa());
        points += scoreIncome(student.getIncomePerMember());
        points += scoreSocialStatus(student.getSocialStatus());
        points += scoreLocation(student.getLocationType());
        points += scoreAchievement(student.getAchievementType());

        return points;
    }

    private double scoreGpa(double gpa) {
        if (gpa >= 9.5) return 40;
        if (gpa >= 8.5) return 30;
        if (gpa >= 7.5) return 20;
        if (gpa >= 6.5) return 10;
        return 5;
    }

    private double scoreIncome(double income) {
        if (income <= 500) return 30;
        if (income <= 800) return 20;
        if (income <= 1000) return 10;
        return 5;
    }

    private double scoreSocialStatus(SocialStatus status) {
        return switch (status) {
            case ORPHAN -> 20;
            case SINGLE_PARENT -> 15;
            case UNEMPLOYED_PARENTS -> 10;
            case NORMAL -> 5;
        };
    }

    private double scoreLocation(LocationType locationType) {
        return switch (locationType) {
            case RURAL -> 10;
            case URBAN -> 5;
        };
    }

    private double scoreAchievement(AchievementType achievementType) {
        return switch (achievementType) {
            case INTERNATIONAL -> 30;
            case NATIONAL -> 20;
            case REGIONAL -> 10;
            case UNIVERSITY -> 5;
        };
    }
}
