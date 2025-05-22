package PAI.dto.teacher;

public record TeacherDTO (
        String id,
        String name,
        String email,
        String nif,
        String phoneNumber,
        String academicBackground,
        String street,
        String postalCode,
        String location,
        String country,
        String departmentID
) {}
