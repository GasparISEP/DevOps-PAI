package PAI.dto.totalEnrolledStudents;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TotalEnrolledStudentsRequestTest {

    @Test
    void shouldCreateTotalEnrolledStudentsRequestWhenGivenValues() {
        // Arrange
        String departmentID = "CSS";
        String schoolYearID = "b8f8e7c6-3a2d-4d99-9f84-32f4a1e1a7ab";

        // Act
        TotalEnrolledStudentsRequest request = new TotalEnrolledStudentsRequest(departmentID, schoolYearID);

        // Assert
        assertEquals(departmentID, request.departmentID());
        assertEquals(schoolYearID, request.schoolYearID());
    }

    @Test
    void shouldNotCreateRequestWhenDepartmentIDGivenIsBlank() {
        // Arrange
        String departmentID = "";
        String schoolYearID = "b8f8e7c6-3a2d-4d99-9f84-32f4a1e1a7ab";
        TotalEnrolledStudentsRequest request = new TotalEnrolledStudentsRequest(departmentID, schoolYearID);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Act
        Set<ConstraintViolation<TotalEnrolledStudentsRequest>> violations = validator.validate(request);

        // Assert
        assertFalse(violations.isEmpty());
        assertEquals("Department ID is required", violations.iterator().next().getMessage());
    }

    @Test
    void shouldNotCreateRequestWhenSchoolYearIDGivenIsBlank() {
        // Arrange
        String departmentID = "dep-123";
        String schoolYearID = "";
        TotalEnrolledStudentsRequest request = new TotalEnrolledStudentsRequest(departmentID, schoolYearID);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Act
        Set<ConstraintViolation<TotalEnrolledStudentsRequest>> violations = validator.validate(request);

        // Assert
        assertFalse(violations.isEmpty());
        assertEquals("School Year ID is required", violations.iterator().next().getMessage());
    }

    @Test
    void shouldNotCreateRequestWhenBothFieldsAreBlank() {
        // Arrange
        String departmentID = "";
        String schoolYearID = "";
        TotalEnrolledStudentsRequest request = new TotalEnrolledStudentsRequest(departmentID, schoolYearID);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Act
        Set<ConstraintViolation<TotalEnrolledStudentsRequest>> violations = validator.validate(request);

        // Assert
        assertFalse(violations.isEmpty());
        assertEquals(2, violations.size());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().equals("Department ID is required")));

        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().equals("School Year ID is required")));
    }
}