package PAI.dto.ProgrammeAndCourses;

import PAI.dto.course.CourseIDDTO;

import java.util.List;

public record StudentProgrammeEnrolmentRequestDto(
            int studentId,
            String programmeAcronym,
            String schoolYearId,
            List<CourseIDDTO> courseIds
) {}