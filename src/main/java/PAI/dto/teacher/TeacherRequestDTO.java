package PAI.dto.teacher;

import jakarta.validation.constraints.*;

public record TeacherRequestDTO(

        @NotBlank(message = "Teacher Name cannot be blank.")
        String _name,

        @NotBlank(message = "Email cannot be blank.")
        String _email,

        @NotBlank(message = "NIF cannot be blank.")
        String _nif,

        @NotBlank(message = "Phone Number cannot be blank.")
        String _phoneNumber,

        @NotBlank(message = "Academic Background cannot be blank.")
        String _academicBackground,

        @NotBlank(message = "Street cannot be blank.")
        String _street,

        @NotBlank(message = "Postal Code cannot be blank.")
        String _postalCode,

        @NotBlank(message = "Location cannot be blank.")
        String _location,

        @NotBlank(message = "Country cannot be blank.")
        String _country,

        @NotBlank(message = "Department ID cannot be blank.")
        String _departmentID

) {}
