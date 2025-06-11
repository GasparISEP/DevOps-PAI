package PAI.dto.programmeEnrolment;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeEnrolmentIdDTOTest {

    @Test
    void constructorWithUUID_shouldStoreUUIDCorrectly() {
        // Arrange
        UUID uuid = UUID.randomUUID();

        // Act
        ProgrammeEnrolmentIdDTO dto = new ProgrammeEnrolmentIdDTO(uuid);

        // Assert
        assertEquals(uuid, dto.getProgrammeEnrolmentGID());
    }

    @Test
    void noArgsConstructor_shouldAllowManualSetting() {
        // Arrange
        ProgrammeEnrolmentIdDTO dto = new ProgrammeEnrolmentIdDTO();

        // Assert
        assertNull(dto.getProgrammeEnrolmentGID());
    }

    @Test
    void constructorWithNull_shouldStoreNull() {
        // Act
        ProgrammeEnrolmentIdDTO dto = new ProgrammeEnrolmentIdDTO(null);

        // Assert
        assertNull(dto.getProgrammeEnrolmentGID());
    }
}