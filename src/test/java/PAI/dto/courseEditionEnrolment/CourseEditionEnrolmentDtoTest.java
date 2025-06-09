package PAI.dto.courseEditionEnrolment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;

class CourseEditionEnrolmentDtoTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
        
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
        Set<jakarta.validation.ConstraintViolation<CourseEditionEnrolmentDto>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "DTO should be valid");
        assertEquals(studentUniqueNumber, dto.studentUniqueNumber());
        assertEquals(programmeAcronym, dto.programmeAcronym());
        assertEquals(schoolYearId, dto.schoolYearId());
        assertEquals(courseAcronym, dto.courseAcronym());
        assertEquals(courseName, dto.courseName());
        assertEquals(studyPlanDate, dto.studyPlanDate());
    }

    @Test
    void shouldFailWithInvalidStudentNumber() {
        // Arrange
        int studentUniqueNumber = 1; 
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
        Set<jakarta.validation.ConstraintViolation<CourseEditionEnrolmentDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty(), "DTO should be invalid");
        assertTrue(violations.stream()
            .anyMatch(v -> v.getPropertyPath().toString().equals("studentUniqueNumber")),
            "Should have violation for studentUniqueNumber");
    }

    @Test
    void shouldFailWithBlankFields() {
        // Arrange
        int studentUniqueNumber = 1100000;
        String programmeAcronym = ""; 
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
        Set<jakarta.validation.ConstraintViolation<CourseEditionEnrolmentDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty(), "DTO should be invalid");
        assertTrue(violations.stream()
            .anyMatch(v -> v.getPropertyPath().toString().equals("programmeAcronym")),
            "Should have violation for programmeAcronym");
    }

    @Test
    void shouldFailWithNullFields() {
        // Arrange
        int studentUniqueNumber = 1100000;
        String programmeAcronym = "LEIC";
        String schoolYearId = null; 
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
        Set<jakarta.validation.ConstraintViolation<CourseEditionEnrolmentDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty(), "DTO should be invalid");
        assertTrue(violations.stream()
            .anyMatch(v -> v.getPropertyPath().toString().equals("schoolYearId")),
            "Should have violation for schoolYearId");
    }
}
