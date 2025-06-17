package PAI.VOs;

import PAI.domain.courseEdition.CourseEdition;

public record EnrolledCourseDetails(
        CourseEdition courseEdition,
        CourseEditionEnrolmentGeneratedID enrolmentGeneratedID
) {}