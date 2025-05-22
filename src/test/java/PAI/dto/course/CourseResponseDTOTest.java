package PAI.dto.course;


import PAI.dto.department.RegisterDepartmentRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CourseResponseDTOTest {

    @Test
    void shouldCreateCourseResponseDTOWithGivenValues() {
        // Arrange
        String name = "Software Engineering Department";
        String acronym = "DEI";

        // Act
        CourseResponseDTO request = new CourseResponseDTO(name, acronym);

        CourseResponseDTO request2 = new CourseResponseDTO(request._acronym(), request._name());

        // Assert
        assertEquals(request, request2);
    }

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldPassValidationWithValidFields() {
        CourseResponseDTO request = new CourseResponseDTO("DEI", "Software Engineering");
        Set<ConstraintViolation<CourseResponseDTO>> violations = validator.validate(request);

        assertTrue(violations.isEmpty(), "There should be no validation errors");
    }

    @Test
    void shouldFailValidationWhenNameIsBlank() {
        CourseResponseDTO request = new CourseResponseDTO("DEI", " ");
        Set<ConstraintViolation<CourseResponseDTO>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals("Name cannot be blank.", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenAcronymIsBlank() {
        CourseResponseDTO request = new CourseResponseDTO(" ", "Mechanical Engineering");
        Set<ConstraintViolation<CourseResponseDTO>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals("Acronym cannot be blank.", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenAllFieldsAreBlank() {
        CourseResponseDTO request = new CourseResponseDTO(" ", " ");
        Set<ConstraintViolation<CourseResponseDTO>> violations = validator.validate(request);

        assertEquals(2, violations.size());
    }
}