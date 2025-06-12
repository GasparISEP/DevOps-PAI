package PAI.dto.ProgrammeAndCourses;

import PAI.dto.courseEditionEnrolment.CourseEditionEnrolmentDto;

import java.util.List;

public record StudentEnrolmentResultDto(
        ProgrammeEditionEnrolmentDTO programmeEditionEnrolment,
        List<CourseEditionEnrolmentDto> courseEditionEnrolments
) {
}
