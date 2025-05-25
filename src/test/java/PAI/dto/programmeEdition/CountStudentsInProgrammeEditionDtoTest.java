package PAI.dto.programmeEdition;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountStudentsInProgrammeEditionDtoTest {
    @Test
    void testRecordFields() {
        // Arrange
        String name = "Computer Science";
        String acronym = "CS";
        String yearId = "2025-2026";

        // Act
        CountStudentsInProgrammeEditionDto dto = new CountStudentsInProgrammeEditionDto(name, acronym, yearId);

        // Assert
        assertEquals(name, dto.programmeName());
        assertEquals(acronym, dto.programmeAcronym());
        assertEquals(yearId, dto.schoolYearID());
    }

    @Test
    void testEqualsAndHashCode() {
        CountStudentsInProgrammeEditionDto dto1 = new CountStudentsInProgrammeEditionDto("Comp Sci", "CS", "2025-2026");
        CountStudentsInProgrammeEditionDto dto2 = new CountStudentsInProgrammeEditionDto("Comp Sci", "CS", "2025-2026");
        CountStudentsInProgrammeEditionDto dto3 = new CountStudentsInProgrammeEditionDto( "Math", "MATH", "2025-2026");

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1, dto3);
    }
}