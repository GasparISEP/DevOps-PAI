package PAI.dto.studentGrade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentGradeResponseDTOTest {
    private StudentGradeResponseDTO dto;

    @BeforeEach
    void setUp() {
        dto = new StudentGradeResponseDTO(
                1234567,
                95.0,
                "2025-05-21",
                "ce456",
                "pe789",
                "csp101",
                "pr456",
                "sy2025",
                "c999",
                "sp2023"
        );
    }

    @Test
    void shouldReturnCorrectStudentUniqueNumber() {
        assertEquals(1234567, dto._studentUniqueNumber());
    }

    @Test
    void shouldReturnCorrectGrade() {
        assertEquals(95, dto._grade());
    }

    @Test
    void shouldReturnCorrectDate() {
        assertEquals("2025-05-21", dto._date());
    }

    @Test
    void shouldReturnCorrectCourseEditionId() {
        assertEquals("ce456", dto._courseEditionId());
    }

    @Test
    void shouldReturnCorrectProgrammeEditionId() {
        assertEquals("pe789", dto._programmeEditionID());
    }

    @Test
    void shouldReturnCorrectCourseInStudyPlanId() {
        assertEquals("csp101", dto._courseInStudyPlanID());
    }

    @Test
    void shouldReturnCorrectProgrammeId() {
        assertEquals("pr456", dto._programmeId());
    }

    @Test
    void shouldReturnCorrectSchoolYearId() {
        assertEquals("sy2025", dto._schoolYearId());
    }

    @Test
    void shouldReturnCorrectCourseId() {
        assertEquals("c999", dto._courseId());
    }

    @Test
    void shouldReturnCorrectStudyPlanId() {
        assertEquals("sp2023", dto._studyPlanId());
    }

}