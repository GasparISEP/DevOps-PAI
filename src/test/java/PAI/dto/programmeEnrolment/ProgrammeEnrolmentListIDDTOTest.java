package PAI.dto.programmeEnrolment;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProgrammeEnrolmentListIDDTOTest {

    @Test
    public void shouldCreateDTOWithCorrectValues() {
        // Arrange
        String acronym = "CS";
        String enrolmentID = "123e4567-e89b-12d3-a456-426614174000";

        // Act
        ProgrammeEnrolmentListIDDTO dto = new ProgrammeEnrolmentListIDDTO(acronym, enrolmentID);

        // Assert
        assertEquals(acronym, dto.programmeAcronym());
        assertEquals(enrolmentID, dto.programmeEnrolmentGeneratedID());
    }

    @Test
    public void shouldImplementEqualsAndHashCodeCorrectly() {
        // Arrange
        ProgrammeEnrolmentListIDDTO dto1 = new ProgrammeEnrolmentListIDDTO("CS", "ID123");
        ProgrammeEnrolmentListIDDTO dto2 = new ProgrammeEnrolmentListIDDTO("CS", "ID123");
        ProgrammeEnrolmentListIDDTO dto3 = new ProgrammeEnrolmentListIDDTO("ENG", "ID456");

        // Assert
        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1, dto3);
    }

    @Test
    public void toStringShouldIncludeFieldValues() {
        // Arrange
        ProgrammeEnrolmentListIDDTO dto = new ProgrammeEnrolmentListIDDTO("BIO", "ID789");

        // Act
        String str = dto.toString();

        // Assert
        assertTrue(str.contains("BIO"));
        assertTrue(str.contains("ID789"));
    }
}