package PAI.dto.courseEditionEnrolment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CourseEditionEnrolmentDtoTest {
    
    @Test
    void shouldCreateValidCourseEditionEnrolmentDto() {
        // Arrange
        int studentUniqueNumber = 1100000;
        String programmeAcronym = "LEIC";
        String schoolYearId = "123e4567-e89b-12d3-a456-426614174000";
        String courseAcronym = "ESOFT";
        String courseName = "Engineering Software";
        String studyPlanDate = "01-01-2024";

        // Act
        CourseEditionEnrolmentDto dto = new CourseEditionEnrolmentDto(
            studentUniqueNumber,
            programmeAcronym,
            schoolYearId,
            courseAcronym,
            courseName,
            studyPlanDate
        );

        // Assert
        assertNotNull(dto);
        assertEquals(studentUniqueNumber, dto.studentUniqueNumber());
        assertEquals(programmeAcronym, dto.programmeAcronym());
        assertEquals(schoolYearId, dto.schoolYearId());
        assertEquals(courseAcronym, dto.courseAcronym());
        assertEquals(courseName, dto.courseName());
        assertEquals(studyPlanDate, dto.studyPlanDate());
    }

    @Test
    void shouldCreateDtoWithMinimumValues() {
        // Arrange
        int studentUniqueNumber = 1;
        String programmeAcronym = "B";
        String schoolYearId = "00000000-0000-0000-0000-000000000000";
        String courseAcronym = "C";
        String courseName = "D";
        String studyPlanDate = "01-01-2024";

        // Act
        CourseEditionEnrolmentDto dto = new CourseEditionEnrolmentDto(
            studentUniqueNumber,
            programmeAcronym,
            schoolYearId,
            courseAcronym,
            courseName,
            studyPlanDate
        );

        // Assert
        assertNotNull(dto);
        assertEquals(studentUniqueNumber, dto.studentUniqueNumber());
        assertEquals(programmeAcronym, dto.programmeAcronym());
        assertEquals(schoolYearId, dto.schoolYearId());
        assertEquals(courseAcronym, dto.courseAcronym());
        assertEquals(courseName, dto.courseName());
        assertEquals(studyPlanDate, dto.studyPlanDate());
    }
}
