package PAI.dto.department;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DepartmentDTOTest {

    @Test
    void shouldCreateDepartmentDTOCorrectly() {
        // Arrange
        String id = "DEI";

        // Act
        DepartmentDTO departmentDTO = new DepartmentDTO(id);

        // Assert
        assertEquals(id, departmentDTO.id());
    }

    @Test
    void shouldHandleNullId() {
        DepartmentDTO departmentDTO = new DepartmentDTO(null);

        assertNull(departmentDTO.id());;
    }
}
