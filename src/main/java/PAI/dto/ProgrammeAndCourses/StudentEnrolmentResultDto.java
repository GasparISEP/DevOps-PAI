package PAI.dto.ProgrammeAndCourses;

import PAI.dto.courseEditionEnrolment.CourseEditionEnrolmentDto;

import java.util.List;
import java.util.UUID;

public record StudentEnrolmentResultDto(
        ProgrammeEditionEnrolmentDTO programmeEditionEnrolment,
        List<CourseEditionEnrolmentDto> courseEditionEnrolments

) {
}
