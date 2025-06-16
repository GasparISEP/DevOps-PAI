package PAI.dto.courseEdition;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public record CourseEditionResponseDTO(
        UUID courseEditionGeneratedID,
        String programmeAcronym,
        UUID schoolYearID,
        String courseAcronym,
        String courseName,
        LocalDate studyPlanImplementationDate,
        String courseEditionID
        ) {
}
