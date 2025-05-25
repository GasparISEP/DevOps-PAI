package PAI.dto.teacher;

import PAI.VOs.*;
import PAI.VOs.Email;

public record RegisterTeacherCommandDTO(
        TeacherID id,
        Name name,
        Email email,
        NIF nif,
        PhoneNumber phoneNumber,
        AcademicBackground academicBackground,
        Street street,
        PostalCode postalCode,
        Location location,
        Country country,
        DepartmentID departmentID
) {
    public RegisterTeacherCommandDTO {
        if (id == null || id.getTeacherAcronym().getAcronym().isBlank()) {
            throw new IllegalArgumentException("Id is required");
        }
        if (name == null || name.getName().isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (email == null || email.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (nif == null || nif.getNIF().isBlank()) {
            throw new IllegalArgumentException("NIF is required");
        }
        if (phoneNumber == null || phoneNumber.getNumber().isBlank()) {
            throw new IllegalArgumentException("PhoneNumber is required");
        }
        if (academicBackground == null || academicBackground.getAcademicBackground().isBlank()) {
            throw new IllegalArgumentException("Academic Background is required");
        }
        if (street == null || street.getStreet().isBlank()) {
            throw new IllegalArgumentException("Street is required");
        }
        if (postalCode == null || postalCode.getPostalCode().isBlank()) {
            throw new IllegalArgumentException("Postal Code is required");
        }
        if (location == null || location.getLocation().isBlank()) {
            throw new IllegalArgumentException("Location is required");
        }
        if (country == null || country.getCountryName().isBlank()) {
            throw new IllegalArgumentException("Country is required");
        }
        if (departmentID == null || departmentID.getAcronym().getAcronym().isBlank()) {
            throw new IllegalArgumentException("Department ID is required");
        }
    }
}
