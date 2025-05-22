package PAI.dto.student;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentDTOTest {

    @Test
    void shouldCreateStudentDTO() {
        //Arrange
        int studentID = 1234567;
        String studentName = "Paulo";
        String nif = "123456789";
        String nifCountry = "Portugal";
        String street = "Rua dos Pintos";
        String postalCode = "12345";
        String location = "Coimbra";
        String addressCountry = "Portugal";
        String phoneCountryCode = "+351";
        String phoneNumber = "987654321";
        String email = "paulo.andre@mail.pt";

        //Act
        StudentDTO studentDTO = new StudentDTO(studentID, studentName, nif, nifCountry, street, postalCode, location, addressCountry, phoneCountryCode, phoneNumber, email);

        //Assert
        assertNotNull(studentDTO);
        assertEquals(studentID, studentDTO.getStudentID());
        assertEquals(studentName, studentDTO.getName());
        assertEquals(nif, studentDTO.getNIF());
        assertEquals(nifCountry, studentDTO.getNIFCountry());
        assertEquals(street, studentDTO.getStreet());
        assertEquals(postalCode, studentDTO.getPostalCode());
        assertEquals(location, studentDTO.getLocation());
        assertEquals(addressCountry, studentDTO.getAddressCountry());
        assertEquals(phoneCountryCode, studentDTO.getPhoneCountryCode());
        assertEquals(phoneNumber, studentDTO.getPhoneNumber());
        assertEquals(email, studentDTO.getEmail());
    }

}