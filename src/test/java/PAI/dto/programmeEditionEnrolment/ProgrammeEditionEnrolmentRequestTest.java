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

    @Test
    void gettersAndSetters_shouldWorkCorrectly() {
        // Arrange
        String studentId = "123456";
        String acronym = "LEI";
        String programmeName = "Engenharia Informática";
        String schoolYearId = "a1b2c3d4-e5f6-7890-1234-56789abcdef0";

        ProgrammeEditionEnrolmentRequest request = new ProgrammeEditionEnrolmentRequest();
        request.setStudentId(studentId);
        request.setProgrammeAcronym(acronym);
        request.setProgrammeName(programmeName);
        request.setSchoolYearId(schoolYearId);

        // Assert (testa os getters diretamente)
        assertEquals(studentId, request.getStudentId());
        assertEquals(acronym, request.getProgrammeAcronym());
        assertEquals(programmeName, request.getProgrammeName());
        assertEquals(schoolYearId, request.getSchoolYearId());
    }


}
