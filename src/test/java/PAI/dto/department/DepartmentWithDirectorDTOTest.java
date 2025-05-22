package PAI.dto.department;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentWithDirectorDTOTest {

    @Test
    void shouldCreateDepartmentDTOCorrectly() {
        // Arrange
        String id = "DEI";
        String name = "Software Engineering Department";
        String acronym = "DEI";
        String teacherID = "AAM";

        // Act
        DepartmentWithDirectorDTO departmentWithDirectorDTO = new DepartmentWithDirectorDTO(id, name, acronym, teacherID);

        // Assert
        assertEquals(id, departmentWithDirectorDTO.id());
        assertEquals(name, departmentWithDirectorDTO.name());
        assertEquals(acronym, departmentWithDirectorDTO.acronym());
        assertEquals(teacherID, departmentWithDirectorDTO.teacherID());
    }

    @Test
    void shouldHandleNullId() {
        DepartmentWithDirectorDTO departmentWithDirectorDTO = new DepartmentWithDirectorDTO(null, "Design", "DSN", "AAM");

        assertNull(departmentWithDirectorDTO.id());
        assertEquals("Design", departmentWithDirectorDTO.name());
        assertEquals("DSN", departmentWithDirectorDTO.acronym());
        assertEquals("AAM", departmentWithDirectorDTO.teacherID());
    }
}
