package PAI.dto.ProgrammeAndCourses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeEditionEnrolmentDTOTest {
    @Test
    void testRecordStoresValuesCorrectly() {
        // Arrange
        int expectedStudentId = 1000001;
        String expectedProgrammeAcronym = "CSD";
        String expectedSchoolYearId = "2024-2025";

        // Act
        ProgrammeEditionEnrolmentDTO dto = new ProgrammeEditionEnrolmentDTO(
                expectedStudentId,
                expectedProgrammeAcronym,
                expectedSchoolYearId
        );

        // Assert
        assertEquals(expectedStudentId, dto.studentId());
        assertEquals(expectedProgrammeAcronym, dto.programmeAcronym());
        assertEquals(expectedSchoolYearId, dto.schoolYearId());
    }
}

