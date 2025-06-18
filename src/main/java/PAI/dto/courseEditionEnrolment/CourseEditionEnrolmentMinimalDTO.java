package PAI.dto.courseEditionEnrolment;

import java.util.UUID;

public record CourseEditionEnrolmentMinimalDTO(
        UUID courseEditionGeneratedID,
        String courseEditionName
) {}
// This class is a minimal representation of a course edition enrolment,
// containing only the course edition ID and name.
