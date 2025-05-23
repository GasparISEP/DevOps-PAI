package PAI.dto.programmeEditionEnrolment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProgrammeEditionEnrolmentRequestTest {

    @Test
    void constructorAndGetters_shouldReturnCorrectValues() {
        // Arrange
        String studentId = "1500001";
        String acronym = "LEI";
        String name = "Engenharia Informática";
        String schoolYearId = "2024-2025";

        // Act
        ProgrammeEditionEnrolmentRequest request = new ProgrammeEditionEnrolmentRequest(studentId, acronym, name, schoolYearId);

        // Assert
        assertEquals(studentId, request.getStudentId());
        assertEquals(acronym, request.getProgrammeAcronym());
        assertEquals(name, request.getProgrammeName());
        assertEquals(schoolYearId, request.getSchoolYearId());
    }
}
