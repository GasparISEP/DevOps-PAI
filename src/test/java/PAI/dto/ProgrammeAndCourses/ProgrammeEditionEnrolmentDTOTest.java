package PAI.dto.ProgrammeAndCourses;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeEditionEnrolmentDTOTest {
    @Test
    void testRecordStoresValuesCorrectly() {
        // Arrange
        int expectedStudentId = 1000001;
        String expectedProgrammeAcronym = "CSD";
        String expectedSchoolYearId = "2024-2025";
        UUID expectedGeneratedID = UUID.randomUUID();

        // Act
        ProgrammeEditionEnrolmentDTO dto = new ProgrammeEditionEnrolmentDTO(
                expectedStudentId,
                expectedProgrammeAcronym,
                expectedSchoolYearId,
                expectedGeneratedID
        );

        // Assert
        assertEquals(expectedStudentId, dto.studentId());
        assertEquals(expectedProgrammeAcronym, dto.programmeAcronym());
        assertEquals(expectedSchoolYearId, dto.schoolYearId());
        assertEquals(expectedGeneratedID, dto.genID());
    }
}

