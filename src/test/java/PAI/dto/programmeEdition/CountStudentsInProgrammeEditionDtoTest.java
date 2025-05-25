package PAI.dto.programmeEdition;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CountStudentsInProgrammeEditionDtoTest {
    @Test
    void testRecordFields() {
        // Arrange
        String name = "Computer Science";
        String acronym = "CS";
        UUID yearId = UUID.randomUUID();

        // Act
        CountStudentsInProgrammeEditionDto dto = new CountStudentsInProgrammeEditionDto(name, acronym, yearId);

        // Assert
        assertEquals(name, dto.programmeName());
        assertEquals(acronym, dto.programmeAcronym());
        assertEquals(yearId, dto.schoolYearID());
    }
}