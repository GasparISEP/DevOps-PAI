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

class CourseRequestDTOTest {

    @Test
    void shouldCreateCourseRequestDTOWithGivenValues() {
        // Arrange
        String name = "Software Engineering Department";
        String acronym = "DEI";

        // Act
        CourseRequestDTO request = new CourseRequestDTO(name, acronym);

        CourseRequestDTO request2 = new CourseRequestDTO(request._acronym(), request._name());

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
        CourseRequestDTO request = new CourseRequestDTO("DEI", "Software Engineering");
        Set<ConstraintViolation<CourseRequestDTO>> violations = validator.validate(request);

        assertTrue(violations.isEmpty(), "There should be no validation errors");
    }

    @Test
    void shouldFailValidationWhenNameIsBlank() {
        CourseRequestDTO request = new CourseRequestDTO("DEI", " ");
        Set<ConstraintViolation<CourseRequestDTO>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals("Name cannot be blank.", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenAcronymIsBlank() {
        CourseRequestDTO request = new CourseRequestDTO(" ", "Mechanical Engineering");
        Set<ConstraintViolation<CourseRequestDTO>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals("Acronym cannot be blank.", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenAllFieldsAreBlank() {
        CourseRequestDTO request = new CourseRequestDTO(" ", " ");
        Set<ConstraintViolation<CourseRequestDTO>> violations = validator.validate(request);

        assertEquals(2, violations.size());
    }
}