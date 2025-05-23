package PAI.dto.studentGrade;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GradeAStudentRequestDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldCreateGradeAStudentRequestDTOWithGivenValues() {
        // Arrange
        double grade = 9.5;
        String date = "2025-05-22";
        String studentID = "12345";
        String courseEditionID = "2023-01-A";

        // Act
        GradeAStudentRequestDTO request = new GradeAStudentRequestDTO(grade, date, studentID, courseEditionID);

        // Assert
        assertEquals(grade, request.grade());
        assertEquals(date, request.date());
        assertEquals(studentID, request.studentID());
        assertEquals(courseEditionID, request.courseEditionID());
    }

    @Test
    void shouldPassValidationWithValidFields() {
        GradeAStudentRequestDTO request = new GradeAStudentRequestDTO(9.5, "2025-05-22", "12345", "2023-01-A");
        Set<ConstraintViolation<GradeAStudentRequestDTO>> violations = validator.validate(request);

        assertTrue(violations.isEmpty(), "There should be no validation errors");
    }

    @Test
    void shouldFailValidationWhenGradeIsNotPositive() {
        GradeAStudentRequestDTO request = new GradeAStudentRequestDTO(0, "2025-05-22", "12345", "2023-01-A");
        Set<ConstraintViolation<GradeAStudentRequestDTO>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals("Grade must be a positive number.", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenDateIsBlank() {
        GradeAStudentRequestDTO request = new GradeAStudentRequestDTO(9.5, " ", "12345", "2023-01-A");
        Set<ConstraintViolation<GradeAStudentRequestDTO>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals("Date is required.", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenStudentIDIsBlank() {
        GradeAStudentRequestDTO request = new GradeAStudentRequestDTO(9.5, "2025-05-22", " ", "2023-01-A");
        Set<ConstraintViolation<GradeAStudentRequestDTO>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals("Student ID is required.", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenCourseEditionIDIsBlank() {
        GradeAStudentRequestDTO request = new GradeAStudentRequestDTO(9.5, "2025-05-22", "12345", " ");
        Set<ConstraintViolation<GradeAStudentRequestDTO>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals("Course Edition ID is required.", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenMultipleFieldsAreInvalid() {
        GradeAStudentRequestDTO request = new GradeAStudentRequestDTO(0, " ", " ", " ");
        Set<ConstraintViolation<GradeAStudentRequestDTO>> violations = validator.validate(request);

        assertEquals(4, violations.size());
    }
}
