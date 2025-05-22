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
        String postalCode = "1234-000";
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

    @Test
    void shouldSetAttributesForStudentDTO() {
        //Arrange
        StudentDTO studentDTO = new StudentDTO();

        //Act
        studentDTO.setStudentID(1234567);
        studentDTO.setName("Paulo");
        studentDTO.setNIF("123456789");
        studentDTO.setNIFCountry("Portugal");
        studentDTO.setStreet("Rua dos Pintos");
        studentDTO.setPostalCode("1234-000");
        studentDTO.setLocation("Coimbra");
        studentDTO.setAddressCountry("Portugal");
        studentDTO.setPhoneCountryCode("+351");
        studentDTO.setPhoneNumber("987654321");
        studentDTO.setEmail("paulo.andre@mail.pt");

        //Assert
        assertEquals(1234567, studentDTO.getStudentID());
        assertEquals("Paulo", studentDTO.getName());
        assertEquals("123456789", studentDTO.getNIF());
        assertEquals("Portugal", studentDTO.getNIFCountry());
        assertEquals("Rua dos Pintos", studentDTO.getStreet());
        assertEquals("1234-000", studentDTO.getPostalCode());
        assertEquals("Coimbra", studentDTO.getLocation());
        assertEquals("Portugal", studentDTO.getAddressCountry());
        assertEquals("+351", studentDTO.getPhoneCountryCode());
        assertEquals("987654321", studentDTO.getPhoneNumber());
        assertEquals("paulo.andre@mail.pt", studentDTO.getEmail());
    }
}