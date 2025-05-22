package PAI.dto.courseEditionEnrolment;

import jakarta.validation.constraints.NotBlank;

public record CourseEditionEnrolmentDto(

    @NotBlank(message = "Student unique number is required")
    int studentUniqueNumber,        
    @NotBlank(message = "Programme name is required")
    String programmeName,        
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
