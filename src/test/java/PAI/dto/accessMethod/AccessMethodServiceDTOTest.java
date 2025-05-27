package PAI.dto.accessMethod;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccessMethodServiceDTOTest {

    @Test
    void shouldCreateDTOWithGivenValues() {
        // Arrange & Act
        AccessMethodServiceDTO dto = new AccessMethodServiceDTO("123", "MyAccessMethod");

        // Assert
        assertEquals("123", dto.id());
        assertEquals("MyAccessMethod", dto.name());
    }

    @Test
    void equals_shouldReturnTrueForSameValues() {
        // Arrange
        AccessMethodServiceDTO dto1 = new AccessMethodServiceDTO("123", "Name");
        AccessMethodServiceDTO dto2 = new AccessMethodServiceDTO("123", "Name");

        // Act & Assert
        assertEquals(dto1, dto2);
    }

    @Test
    void hashCode_shouldBeEqualForSameValues() {
        // Arrange
        AccessMethodServiceDTO dto1 = new AccessMethodServiceDTO("123", "Name");
        AccessMethodServiceDTO dto2 = new AccessMethodServiceDTO("123", "Name");

        // Act & Assert
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void toString_shouldContainFieldValues() {
        // Arrange
        AccessMethodServiceDTO dto = new AccessMethodServiceDTO("123", "Name");

        // Act
        String dtoString = dto.toString();

        // Assert
        assertTrue(dtoString.contains("123"));
        assertTrue(dtoString.contains("Name"));
    }
}