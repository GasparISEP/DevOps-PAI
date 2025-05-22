package PAI.dto.student;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentResponseDTOTest {

    @Test
    void shouldCreateStudentResponseDTO() {

        //Arrange
        int studentID = 1234567;
        String studentName = "Paulo";
        String nif = "123456789";
        String nifCountry = "Portugal";
        String street = "Rua dos Pintos";
        String postalCode = "1234-000";
        String location = "Coimbra";
        String addressCountry = "Portugal";
        String phoneCountryCode = "+351";
        String phoneNumber = "987654321";
        String email = "paulo.andre@mail.pt";
        String acamedicEmail = "1234567.isep.ipp.pt";

        //Act
        StudentResponseDTO result = new StudentResponseDTO(studentID, studentName, nif, nifCountry, street, postalCode, location, addressCountry, phoneCountryCode, phoneNumber, email, acamedicEmail);

        //Assert
        assertNotNull(result);
        assertEquals(studentID, result.getStudentID());
        assertEquals(studentName, result.getName());
        assertEquals(nif, result.getNIF());
        assertEquals(nifCountry, result.getNIFCountry());
        assertEquals(street, result.getStreet());
        assertEquals(postalCode, result.getPostalCode());
        assertEquals(location, result.getLocation());
        assertEquals(addressCountry, result.getAddressCountry());
        assertEquals(phoneCountryCode, result.getPhoneCountryCode());
        assertEquals(phoneNumber, result.getPhoneNumber());
    }
}