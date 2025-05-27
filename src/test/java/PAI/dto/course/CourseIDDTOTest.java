package PAI.dto.course;

import PAI.dto.course.CourseIDDTO;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CourseIDDTOTest {

    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldPassValidationWithValidFields() {
        CourseIDDTO dto = new CourseIDDTO("ABC", "Engenharia");
        Set<ConstraintViolation<CourseIDDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldFailWhenAcronymIsNull() {
        CourseIDDTO dto = new CourseIDDTO(null, "Engenharia");
        Set<ConstraintViolation<CourseIDDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        assertEquals("Course Acronym is required", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailWhenAcronymHasWrongSize() {
        CourseIDDTO dto = new CourseIDDTO("AB", "Engenharia");
        Set<ConstraintViolation<CourseIDDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        assertEquals("Acronym must have 3 characters", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailWhenNameIsBlank() {
        CourseIDDTO dto = new CourseIDDTO("ABC", " ");
        Set<ConstraintViolation<CourseIDDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        assertEquals("Course Name is required", violations.iterator().next().getMessage());
    }
}