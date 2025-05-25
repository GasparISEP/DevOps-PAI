package PAI.dto.courseEdition;

import java.util.UUID;
import java.util.Date;

public record CourseEditionRequestDTO(
        String programmeName,
        String programmeAcronym,
        UUID schoolYearID,

        String courseAcronym,
        String courseName,
        String studyPlanProgrammeName,
        String studyPlanProgrammeAcronym,
        Date studyPlanImplementationDate
) { }
