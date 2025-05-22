package PAI.dto.accessMethod;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class accessMethodResponseDTOTest {

    @Test
    void testConstructor() {
        // Arrange & Act
        AccessMethodResponseDTO dto = new AccessMethodResponseDTO("id", "name");
        //Assert
        assertEquals("id", dto.id());
        assertEquals("name", dto.name());

    }

    @Test
    void testGetId() {
        // Arrange
        AccessMethodResponseDTO dto = new AccessMethodResponseDTO("id", "name");
        // Act
        String id = dto.id();
        // Assert
        assertEquals("id", id);
    }

    @Test
    void testGetName() {
        // Arrange
        AccessMethodResponseDTO dto = new AccessMethodResponseDTO("id", "name");
        // Act
        String name = dto.name();
        // Assert
        assertEquals("name", name);
    }

}