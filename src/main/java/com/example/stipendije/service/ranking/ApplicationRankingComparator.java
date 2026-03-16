package com.example.stipendije.service.ranking;

import com.example.stipendije.model.entity.Application;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class ApplicationRankingComparator implements Comparator<Application> {

    @Override
    public int compare(Application a1, Application a2) {

        int result = Double.compare(
                a2.getTotalPoints(),
                a1.getTotalPoints()
        );

        if (result != 0) return result;

        result = Double.compare(
                a2.getStudent().getGpa(),
                a1.getStudent().getGpa()
        );

        if (result != 0) return result;

        result = Double.compare(
                a1.getStudent().getIncomePerMember(),
                a2.getStudent().getIncomePerMember()
        );

        if (result != 0) return result;

        return a1.getCreatedAt()
                .compareTo(a2.getCreatedAt());
    }
    /*
    return Comparator.comparing(Application::getTotalPoints).reversed()
            .thenComparing(app -> app.getStudent().getGpa(), Comparator.reverseOrder())
            .thenComparing(app -> app.getStudent().getIncomePerMember())
            .thenComparing(Application::getCreatedAt())
            .compare(a1, a2);
     */
}
