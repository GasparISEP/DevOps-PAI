package PAI.VOs;

import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolment;

import java.util.List;

public record US34Response (
        ProgrammeEditionEnrolment progEditionEnrolment,
        List<CourseEditionEnrolment> listCourseEditionEnrolment) {}
