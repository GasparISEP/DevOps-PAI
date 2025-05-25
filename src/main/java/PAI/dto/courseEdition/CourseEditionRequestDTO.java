package PAI.dto.courseEdition;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;
import java.util.Date;

public record CourseEditionRequestDTO(
        @NotBlank String programmeName,
        @NotBlank String programmeAcronym,
        @NotNull UUID schoolYearID,

        @NotBlank String courseAcronym,
        @NotBlank String courseName,
        @NotBlank String studyPlanProgrammeName,
        @NotBlank String studyPlanProgrammeAcronym,
        @NotNull Date studyPlanImplementationDate
) { }
