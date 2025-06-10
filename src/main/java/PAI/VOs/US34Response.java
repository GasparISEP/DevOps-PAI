package PAI.VOs;

import java.util.List;

public record US34Response (
        ProgrammeEditionEnrolmentID progEditionEnrolment,
        List<CourseEditionEnrolmentID> listCourseEditionEnrolment) {}
