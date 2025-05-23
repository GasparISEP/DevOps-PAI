package PAI.dto.teacher;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherDTOTest {

    @Test
    void shouldCreateTeacherDTO() {
        //Arrange
        String id = "abc";
        String name = "zacarias";
        String email = "abc";
        String nif = "123456789";
        String phoneNumber = "987654321";
        String academicBackground = "LEI";
        String street = "Rua Numero 11";
        String postalCode = "4100";
        String location = "Porto";
        String country = "Portugal";
        String departmentID = "1";

        //Act
        TeacherDTO teacherDTO = new TeacherDTO(id, name, email, nif, phoneNumber, academicBackground, street, postalCode, location, country, departmentID);

        //Assert
        assertNotNull(teacherDTO);
        assertEquals(id, teacherDTO.id());
        assertEquals(name, teacherDTO.name());
        assertEquals(email, teacherDTO.email());
        assertEquals(nif, teacherDTO.nif());
        assertEquals(phoneNumber, teacherDTO.phoneNumber());
        assertEquals(academicBackground, teacherDTO.academicBackground());
        assertEquals(street, teacherDTO.street());
        assertEquals(postalCode, teacherDTO.postalCode());
        assertEquals(location, teacherDTO.location());
        assertEquals(country, teacherDTO.country());
        assertEquals(departmentID, teacherDTO.departmentID());
    }

    @Test
    void shouldAllowNullFields() {
        //Arrange + Act
        TeacherDTO teacherDTO = new TeacherDTO(null, null, null, null, null, null, null, null, null, null, null);

        //Assert
        assertNotNull(teacherDTO);
        assertNull(teacherDTO.id());
        assertNull(teacherDTO.name());
        assertNull(teacherDTO.email());
        assertNull(teacherDTO.nif());
        assertNull(teacherDTO.phoneNumber());
        assertNull(teacherDTO.academicBackground());
        assertNull(teacherDTO.street());
        assertNull(teacherDTO.postalCode());
        assertNull(teacherDTO.location());
        assertNull(teacherDTO.country());
        assertNull(teacherDTO.departmentID());
    }

}

