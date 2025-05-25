package PAI.dto.teacherCategory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TeacherCategoryRequestDTO(

        @NotNull(message = "The name field is required!")
        @NotBlank(message = "The name field cannot be blank!")
        @Size(min = 3, max = 255, message = "The name must be between 3 and 255 characters long!")
        String name
) {
}
