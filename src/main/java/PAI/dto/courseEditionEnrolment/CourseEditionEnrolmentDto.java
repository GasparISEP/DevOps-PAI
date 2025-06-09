package PAI.dto.courseEditionEnrolment;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseEditionEnrolmentDto(

    @NotNull(message = "Student unique number is required")
    @Min(value = 1000000, message = "Student unique number must be at least 7 digits and more than 1000000")
    @Max(value = 2000000, message = "Student unique number must be at most 7 digits and less than 2000000")
    int studentUniqueNumber,
    @NotBlank(message = "Programme acronym is required")
    String programmeAcronym,        
    @NotBlank(message = "School year ID is required")
    String schoolYearId,            
    @NotBlank(message = "Course acronym is required")
    String courseAcronym,           
    @NotBlank(message = "Course name is required")
    String courseName,              
    @NotBlank(message = "Study plan date is required")
    String studyPlanDate            
) {}
