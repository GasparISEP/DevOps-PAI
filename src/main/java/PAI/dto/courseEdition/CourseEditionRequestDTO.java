package PAI.dto.courseEdition;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;
import java.util.Date;

public record CourseEditionRequestDTO(
        @NotBlank(message = "Programme acronym cannot be blank")
        String programmeAcronym,

        @NotNull(message = "School Year ID is required")
        UUID schoolYearID,

        @NotBlank(message = "Course acronym cannot be blank")
        String courseAcronym,

        @NotBlank(message = "Course name cannot be blank")
        String courseName,

        @NotNull(message = "Study plan implementation date is required")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate studyPlanImplementationDate
) { }
