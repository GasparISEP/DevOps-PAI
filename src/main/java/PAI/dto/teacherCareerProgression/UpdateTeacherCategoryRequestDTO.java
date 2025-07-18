package PAI.dto.teacherCareerProgression;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdateTeacherCategoryRequestDTO(

        @NotNull(message = "The date field is required!")
        LocalDate date,

        @NotNull (message = "The Teacher Category ID field is required!")
        @NotBlank (message = "The Teacher Category ID field cannot be blank!")
        String teacherCategoryID
) {
}
