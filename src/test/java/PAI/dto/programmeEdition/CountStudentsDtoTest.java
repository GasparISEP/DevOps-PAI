package PAI.dto.programmeEdition;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CountStudentsDtoTest {
    @Test
    void testRecordFields() {
        // Arrange
        String name = "Computer Science";
        String acronym = "CS";
        String yearId = UUID.randomUUID().toString();

        // Act
        CountStudentsDto dto = new CountStudentsDto(acronym, yearId);

        // Assert
        assertEquals(acronym, dto.programmeAcronym());
        assertEquals(yearId, dto.schoolYearID());
    }
}