package PAI.VOs;

import PAI.domain.courseEdition.CourseEdition;

public record US35EnrolledCourseDetails(
        CourseEdition courseEdition,
        CourseEditionEnrolmentGeneratedID enrolmentGeneratedID
) {}