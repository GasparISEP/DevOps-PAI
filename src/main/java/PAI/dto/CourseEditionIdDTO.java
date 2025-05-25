package PAI.dto;

import java.util.Date;
import java.util.UUID;

public record CourseEditionIdDTO(
        //ProgrammeEditionId
        String programmeName,
        String programmeAcronym,
        UUID schoolYearId,

        //CourseInStudyPlanId
        String courseAcronym,
        String courseName,
        String studyPlanProgrammeName,
        String studyPlanProgrammeAcronym,
        Date studyPlanImplementationDate
) {}
