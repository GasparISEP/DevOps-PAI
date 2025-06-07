package PAI.VOs;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StudyPlanGeneratedIDTest {

    @Test
    void testContructorNotNull () {
        // Arrange & Act
        UUID uuid = UUID.randomUUID();
        StudyPlanGeneratedID generatedID = new StudyPlanGeneratedID(uuid);

        // Assert
        assertNotNull(uuid);
    }

    @Test
    void getUUIDShouldReturnUUID () {
        // Arrange
        UUID uuid = UUID.randomUUID();
        StudyPlanGeneratedID generatedID = new StudyPlanGeneratedID(uuid);

        // Act
        UUID result = generatedID.getUUID();

        // Assert
        assertEquals(result, uuid);
    }
}