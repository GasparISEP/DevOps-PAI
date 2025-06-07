package PAI.dto.student;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentResponseDTOTest {

    @Test
    void shouldCreateStudentResponseDTO() {
        // Arrange
        int studentID = 1234567;
        String studentName = "Paulo";
        String nif = "123456789";
        String nifCountry = "Portugal";
        String street = "Rua dos Pintos";
        String postalCode = "1234-000";
        String location = "Coimbra";
        String addressCountry = "Portugal";
        String countryCode = "+351";
        String phoneNumber = "987654321";
        String email = "paulo.andre@mail.pt";
        String academicEmail = "1234567.isep.ipp.pt";

        // Act
        StudentResponseDTO result = new StudentResponseDTO(
                studentID, studentName, nif, nifCountry,
                street, postalCode, location, addressCountry,
                countryCode, phoneNumber, email, academicEmail
        );

        // Assert
        assertNotNull(result);
        assertEquals(studentID, result.getStudentID());
        assertEquals(studentName, result.getName());
        assertEquals(nif, result.getNif());
        assertEquals(nifCountry, result.getNifCountry());
        assertEquals(street, result.getStreet());
        assertEquals(postalCode, result.getPostalCode());
        assertEquals(location, result.getLocation());
        assertEquals(addressCountry, result.getAddressCountry());
        assertEquals(countryCode, result.getCountryCode());
        assertEquals(phoneNumber, result.getPhoneNumber());
        assertEquals(email, result.getEmail());
        assertEquals(academicEmail, result.getAcademicEmail());
    }
}