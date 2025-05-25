package PAI.dto.teacher;

import jakarta.validation.constraints.*;

public record RegisterTeacherRequestDTO(

        @NotBlank(message = "Teacher ID cannot be blank.")
        String id,

        @NotBlank(message = "Teacher Name cannot be blank.")
        String name,

        @NotBlank(message = "Email cannot be blank.")
        String email,

        @NotBlank(message = "NIF cannot be blank.")
        String nif,

        @NotBlank(message = "CountryCode cannot be blank.")
        String countryCode,

        @NotBlank(message = "Phone Number cannot be blank.")
        String phoneNumber,

        @NotBlank(message = "Academic Background cannot be blank.")
        String academicBackground,

        @NotBlank(message = "Street cannot be blank.")
        String street,

        @NotBlank(message = "Postal Code cannot be blank.")
        String postalCode,

        @NotBlank(message = "Location cannot be blank.")
        String location,

        @NotBlank(message = "Country cannot be blank.")
        String country,

        @NotBlank(message = "Department ID cannot be blank.")
        String departmentID

) {}
