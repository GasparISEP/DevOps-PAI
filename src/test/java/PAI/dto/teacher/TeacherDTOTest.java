package PAI.dto.teacher;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherDTOTest {

    @Test
    void shouldCreateTeacherDTO() {
        //Arrange
        String id = "1";
        String name = "zacarias";
        String email = "abc";
        String nif = "123456789";
        String phoneNumber = "987654321";
        String academicBackground = "no";
        String street = "rua";
        String postalCode = "4100";
        String location = "porto";
        String country = "Portugal";
        String departmentID = "1";

        //Act
        TeacherDTO teacherDTO = new TeacherDTO(id, name, email, nif, phoneNumber, academicBackground, street, postalCode, location, country, departmentID);

        //Assert
        assertNotNull(teacherDTO);
    }

}