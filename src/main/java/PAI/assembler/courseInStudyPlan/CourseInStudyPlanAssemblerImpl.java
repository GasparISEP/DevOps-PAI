package PAI.assembler.courseInStudyPlan;

import PAI.VOs.*;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.teacher.Teacher;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanCommand;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanRequestDTO;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanResponseDTO;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanServiceDTO;
import PAI.dto.teacher.TeacherDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

    public CourseInStudyPlanResponseDTO toDTO(CourseInStudyPlanServiceDTO courseInStudyPlanServiceDTO) {
        Objects.requireNonNull(courseInStudyPlanServiceDTO, "Service DTO cannot be null");

        return new CourseInStudyPlanResponseDTO(
                courseInStudyPlanServiceDTO.semester(),
                courseInStudyPlanServiceDTO.curricularYear(),
                courseInStudyPlanServiceDTO.courseAcronym(),
                courseInStudyPlanServiceDTO.courseName(),
                courseInStudyPlanServiceDTO.programmeAcronym(),
                courseInStudyPlanServiceDTO.studyPlanDate(),
                courseInStudyPlanServiceDTO.duration(),
                courseInStudyPlanServiceDTO.credits(),
                courseInStudyPlanServiceDTO.generatedID(),
                courseInStudyPlanServiceDTO.programmeAcronym()
        );
    }

    @Override
    public CourseInStudyPlanResponseDTO toDTOfromDomain(CourseInStudyPlan course) {
        Objects.requireNonNull(course, "CourseInStudyPlan entity cannot be null");

        return new CourseInStudyPlanResponseDTO(
                course.getSemester().toInt(),
                course.getCurricularYear().toInt(),
                course.getCourseID().getCourseAcronymValue(),
                course.getCourseID().getCourseNameValue(),
                course.getStudyplanID().getProgrammeID().getProgrammeAcronym(),
                course.getStudyplanID().getDate().getLocalDate().toString(),
                course.getDurationOfCourse().getDuration(),
                course.getQuantityOfCreditsEcts().toDouble(),
                course.getGeneratedID().getId(),
                course.getProgrammeID().getAcronym().getAcronym()
        );
    }

    @Override
    public Iterable<CourseInStudyPlanResponseDTO> toDTOsfromDomains(Iterable <CourseInStudyPlan> courseInStudyPlans) {
        if(courseInStudyPlans == null) {
            return Collections.emptyList();
        }
        List<CourseInStudyPlanResponseDTO> listDTO = new ArrayList<>();
        for (CourseInStudyPlan course : courseInStudyPlans) {
            CourseInStudyPlanResponseDTO responseDTO = toDTOfromDomain(course);
            listDTO.add(responseDTO);
        }
        return listDTO;
    }
}
