package PAI.dto.courseEditionEnrolment;

import java.util.UUID;

public record EnrolledCourseEditionDTO(
        String courseAcronym,
        String courseName,
        String programmeAcronym,
        int studyPlanStartYear,
        String courseEditionGeneratedUUID,
        UUID enrolmentGeneratedId,
        UUID schoolYearId
) {}