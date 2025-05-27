package PAI.assembler.courseInStudyPlan;

import PAI.VOs.*;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanCommand;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanRequestDTO;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanResponseDTO;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CourseInStudyPlanAssemblerImpl implements ICourseInStudyPlanAssembler {

    public CourseInStudyPlanCommand toCommand(CourseInStudyPlanRequestDTO request) throws Exception {
        Objects.requireNonNull(request, "Request DTO cannot be null");

        return new CourseInStudyPlanCommand(
                new Semester(request.semester()),
                new CurricularYear(request.curricularYear()),
                new Acronym(request.courseAcronym()),
                new Name(request.courseName()),
                new Acronym(request.programmeAcronym()),
                new NameWithNumbersAndSpecialChars(request.programmeName()),
                new PAI.VOs.Date(request.studyPlanDate()),
                new PAI.VOs.DurationCourseInCurricularYear(request.duration()),
                new CourseQuantityCreditsEcts(request.credits())
        );
    }

    public CourseInStudyPlanResponseDTO toDTO(CourseInStudyPlan courseInStudyPlan) {
        Objects.requireNonNull(courseInStudyPlan, "Entity cannot be null");

        return new CourseInStudyPlanResponseDTO(
                courseInStudyPlan.getSemester().toInt(),
                courseInStudyPlan.getCurricularYear().toInt(),
                courseInStudyPlan.getCourseID().getAcronym().getAcronym(),
                courseInStudyPlan.getCourseID().getName().getName(),
                courseInStudyPlan.getStudyplanID().getProgrammeID().getAcronym().getAcronym(),
                courseInStudyPlan.getStudyplanID().getProgrammeID().getName().getnameWithNumbersAndSpecialChars(),
                courseInStudyPlan.getStudyplanID().getDate().toString(),
                courseInStudyPlan.getDurationOfCourse().toInt(),
                courseInStudyPlan.getQuantityOfCreditsEcts().toDouble(),
                courseInStudyPlan.getCourseID(),
                courseInStudyPlan.getStudyplanID()
        );
    }
}
