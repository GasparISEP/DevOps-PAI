package PAI.dto.teacherCareerProgression;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UpdateTeacherWorkingPercentageRequestDTO(

    @NotBlank(message = "Date is required.")
    String date,

    @Min(value = 0, message = "Working percentage must be at least 0")
    @Max(value = 100, message = "Working percentage must be at most 100")
    int workingPercentage

) {
}


