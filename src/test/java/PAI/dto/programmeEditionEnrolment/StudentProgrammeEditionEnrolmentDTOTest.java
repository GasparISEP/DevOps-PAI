package PAI.dto.programmeEditionEnrolment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentProgrammeEditionEnrolmentDTOTest {

    @Test
    void constructorAndGetters_ShouldReturnCorrectValues() {
        // Arrange
        String expectedAcronym = "LEI";
        String expectedSchoolYearId = "2024-2025";

        // Act
        StudentProgrammeEditionEnrolmentDTO dto = new StudentProgrammeEditionEnrolmentDTO(
                expectedAcronym,
                expectedSchoolYearId
        );

        // Assert
        assertEquals(expectedAcronym, dto.getProgrammeAcronym());
        assertEquals(expectedSchoolYearId, dto.getSchoolYearId());
    }
}
