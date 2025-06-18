package PAI.assembler.courseInStudyPlan;

import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanServiceDTO;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CourseInStudyPlanBusinessAssemblerImpl implements ICourseInStudyPlanBusinessAssembler {

    public CourseInStudyPlanServiceDTO toDTO(CourseInStudyPlan courseInStudyPlan) {
        Objects.requireNonNull(courseInStudyPlan, "Entity cannot be null");

        return new CourseInStudyPlanServiceDTO(
                courseInStudyPlan.getSemester().toInt(),
                courseInStudyPlan.getCurricularYear().toInt(),
                courseInStudyPlan.getCourseID().getAcronym().getAcronym(),
                courseInStudyPlan.getCourseID().getName().getName(),
                courseInStudyPlan.getStudyplanID().getProgrammeID().getAcronym().getAcronym(),
                courseInStudyPlan.getStudyplanID().getDate().toString(),
                courseInStudyPlan.getDurationOfCourse().toInt(),
                courseInStudyPlan.getQuantityOfCreditsEcts().toDouble(),
                courseInStudyPlan.getGeneratedID().getId()
        );
    }
}
