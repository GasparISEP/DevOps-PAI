package PAI.dto.department;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DepartmentDTOTest {

    @Test
    void shouldCreateDepartmentDTOCorrectly() {
        // Arrange
        String id = "DEI";
        String name = "Software Engineering Department";
        String acronym = "DEI";

        // Act
        DepartmentDTO departmentDTO = new DepartmentDTO(id, name, acronym);

        // Assert
        assertEquals(id, departmentDTO.id());
        assertEquals(name, departmentDTO.name());
        assertEquals(acronym, departmentDTO.acronym());
    }

    @Test
    void shouldHandleNullId() {
        DepartmentDTO departmentDTO = new DepartmentDTO(null, "Design", "DSN");

        assertNull(departmentDTO.id());
        assertEquals("Design", departmentDTO.name());
        assertEquals("DSN", departmentDTO.acronym());
    }
}
