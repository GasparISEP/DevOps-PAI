package PAI.dto.studentGrade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record StudentGradeRequestDTO(

        @Positive(message = "Grade must be a positive number.")
        int grade,

        @NotBlank(message = "Date is required.")
        String date,

        @Positive(message = "Student unique number must be positive.")
        int studentUniqueNumber,

        @NotBlank(message = "Programme name is required.")
        String programmeName,

        @NotBlank(message = "Programme acronym is required.")
        String programmeAcronym,

        @NotBlank(message = "School year ID is required.")
        String schoolYearId,

        @NotBlank(message = "Course acronym is required.")
        String courseAcronym,

        @NotBlank(message = "Course name is required.")
        String courseName,

        @NotBlank(message = "Study plan date is required.")
        String studyPlanDate
) {}