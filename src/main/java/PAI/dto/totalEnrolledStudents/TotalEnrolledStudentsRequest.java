package PAI.dto.totalEnrolledStudents;

import jakarta.validation.constraints.NotBlank;

public record TotalEnrolledStudentsRequest(

        @NotBlank(message = "Department ID is required")
        String departmentID,

        @NotBlank(message = "School Year ID is required")
        String schoolYearID
) {}
