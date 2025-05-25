package PAI.dto.courseEdition;

import java.util.Date;
import java.util.UUID;

public record CourseEditionResponseDTO(
        String courseEditionID,

        String programmeName,
        String programmeAcronym,
        UUID schoolYearID,

        String courseAcronym,
        String courseName,
        Date studyPlanImplementationDate
) {
}
