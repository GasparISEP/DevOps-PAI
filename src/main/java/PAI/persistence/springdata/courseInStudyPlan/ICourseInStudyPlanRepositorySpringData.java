package PAI.persistence.springdata.courseInStudyPlan;

import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanDataModel;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;
import PAI.persistence.datamodel.studyPlan.StudyPlanDataModel;
import PAI.persistence.datamodel.studyPlan.StudyPlanIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICourseInStudyPlanRepositorySpringData extends JpaRepository<CourseInStudyPlanDataModel, CourseInStudyPlanIDDataModel> {


    @Query("""
    SELECT SUM(
        CASE WHEN c.durationOfCourse = 1 AND c.semester = :semester THEN c.quantityOfCreditsEcts
            WHEN c.durationOfCourse = 2 AND c.semester = 1 THEN c.quantityOfCreditsEcts / 2.0
            ELSE 0
        END
    )
    FROM CourseInStudyPlanDataModel c
    WHERE c.courseInStudyPlanID = :studyPlanID
    AND c.curricularYear = :curricularYear
    """)
    Double sumCombinedCredits(
            @Param("studyPlanID") StudyPlanIDDataModel studyPlanID,
            @Param("curricularYear") int curricularYear,
            @Param("semester") int semester
    );

}