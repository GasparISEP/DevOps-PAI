package PAI.dto.courseInStudyPlan;

import PAI.VOs.Date;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CourseInStudyPlanRequestDTOTest {

    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenCourseAcronymIsBlank_thenValidationFails() {
        CourseInStudyPlanRequestDTO dto = new CourseInStudyPlanRequestDTO(
                1, 1, " ", "Some Name", "PAI", "Programme Name", "01-01-2024", 1, 5.0);
        Set<ConstraintViolation<CourseInStudyPlanRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Course Acronym is required")));
    }

    @Test
    void whenCourseNameIsBlank_thenValidationFails() {
        CourseInStudyPlanRequestDTO dto = new CourseInStudyPlanRequestDTO(
                1, 1, "CS101", "  ", "PAI", "Programme Name", "01-01-2024", 1, 5.0);
        Set<ConstraintViolation<CourseInStudyPlanRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Course Name is required")));
    }

    @Test
    void whenProgrammeAcronymIsBlank_thenValidationFails() {
        CourseInStudyPlanRequestDTO dto = new CourseInStudyPlanRequestDTO(
                1, 1, "CS101", "Course Name", "   ", "Programme Name", "01-01-2024", 1, 5.0);
        Set<ConstraintViolation<CourseInStudyPlanRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Programme Acronym is required")));
    }

    @Test
    void whenProgrammeNameIsBlank_thenValidationFails() {
        CourseInStudyPlanRequestDTO dto = new CourseInStudyPlanRequestDTO(
                1, 1, "CS101", "Course Name", "PAI", "  ", "01-01-2024", 1, 5.0);
        Set<ConstraintViolation<CourseInStudyPlanRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Programme Name is required")));
    }

    @Test
    void whenDateIsNull_thenValidationFails() {
        CourseInStudyPlanRequestDTO dto = new CourseInStudyPlanRequestDTO(
                1, 1, "CS101", "Course Name", "PAI", "Programme Name", null, 1, 5.0);
        Set<ConstraintViolation<CourseInStudyPlanRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Date is required")));
    }

    @Test
    void whenAllFieldsValid_thenValidationSucceeds() {
        CourseInStudyPlanRequestDTO dto = new CourseInStudyPlanRequestDTO(
                1, 1, "CS101", "Course Name", "PAI", "Programme Name", "01-01-2024", 1, 5.0);
        Set<ConstraintViolation<CourseInStudyPlanRequestDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }
}
