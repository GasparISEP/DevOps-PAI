package PAI.assembler.courseInStudyPlan;

import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanServiceDTO;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CourseInStudyPlanServiceAssemblerImpl implements ICourseInStudyPlanServiceAssembler {

    @Override
    public CourseInStudyPlanServiceDTO toServiceDTO(CourseInStudyPlan course) {
        Objects.requireNonNull(course, "CourseInStudyPlan entity cannot be null");

        return new CourseInStudyPlanServiceDTO(
                course.getSemester().toInt(),
                course.getCurricularYear().toInt(),
                course.getCourseID().getCourseAcronymValue(),
                course.getCourseID().getCourseNameValue(),
                course.getStudyplanID().getProgrammeID().getProgrammeAcronym(),
                course.getStudyplanID().getDate().getLocalDate().toString(),
                course.getDurationOfCourse().getDuration(),
                course.getQuantityOfCreditsEcts().toDouble(),
                course.getCourseID(),
                course.getStudyplanID()
        );
    }
}
