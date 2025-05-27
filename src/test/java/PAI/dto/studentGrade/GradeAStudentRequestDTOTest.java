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
    void shouldPassValidationWithValidData() {
        GradeAStudentRequestDTO dto = new GradeAStudentRequestDTO(
                18,
                "2025-05-23",
                1234567,
                "Software Engineering",
                "SE",
                "2024-2025",
                "CSE101",
                "Computer Science",
                "2023-09-01"
        );

        Set<ConstraintViolation<GradeAStudentRequestDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "DTO should pass with valid data.");
    }

    @Test
    void shouldFailValidationWhenGradeIsZero() {
        GradeAStudentRequestDTO dto = new GradeAStudentRequestDTO(
                0,
                "2025-05-23",
                1234567,
                "SE",
                "SE",
                "2024-2025",
                "CSE101",
                "CS",
                "2023-09-01"
        );

        Set<ConstraintViolation<GradeAStudentRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldFailValidationWhenDateIsBlank() {
        GradeAStudentRequestDTO dto = new GradeAStudentRequestDTO(
                18,
                " ",
                1234567,
                "SE",
                "SE",
                "2024-2025",
                "CSE101",
                "CS",
                "2023-09-01"
        );

        Set<ConstraintViolation<GradeAStudentRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldFailValidationWhenStudentUniqueNumberIsNegative() {
        GradeAStudentRequestDTO dto = new GradeAStudentRequestDTO(
                18,
                "2025-05-23",
                -1,
                "SE",
                "SE",
                "2024-2025",
                "CSE101",
                "CS",
                "2023-09-01"
        );

        Set<ConstraintViolation<GradeAStudentRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldFailValidationWhenProgrammeNameIsBlank() {
        GradeAStudentRequestDTO dto = new GradeAStudentRequestDTO(
                18,
                "2025-05-23",
                1234567,
                " ",
                "SE",
                "2024-2025",
                "CSE101",
                "CS",
                "2023-09-01"
        );

        Set<ConstraintViolation<GradeAStudentRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldFailValidationWhenProgrammeAcronymIsBlank() {
        GradeAStudentRequestDTO dto = new GradeAStudentRequestDTO(
                18,
                "2025-05-23",
                1234567,
                "SE",
                " ",
                "2024-2025",
                "CSE101",
                "CS",
                "2023-09-01"
        );

        Set<ConstraintViolation<GradeAStudentRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldFailValidationWhenSchoolYearIdIsBlank() {
        GradeAStudentRequestDTO dto = new GradeAStudentRequestDTO(
                18,
                "2025-05-23",
                1234567,
                "SE",
                "SE",
                " ",
                "CSE101",
                "CS",
                "2023-09-01"
        );

        Set<ConstraintViolation<GradeAStudentRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldFailValidationWhenCourseAcronymIsBlank() {
        GradeAStudentRequestDTO dto = new GradeAStudentRequestDTO(
                18,
                "2025-05-23",
                1234567,
                "SE",
                "SE",
                "2024-2025",
                " ",
                "CS",
                "2023-09-01"
        );

        Set<ConstraintViolation<GradeAStudentRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldFailValidationWhenCourseNameIsBlank() {
        GradeAStudentRequestDTO dto = new GradeAStudentRequestDTO(
                18,
                "2025-05-23",
                1234567,
                "SE",
                "SE",
                "2024-2025",
                "CSE101",
                " ",
                "2023-09-01"
        );

        Set<ConstraintViolation<GradeAStudentRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldFailValidationWhenStudyPlanDateIsBlank() {
        GradeAStudentRequestDTO dto = new GradeAStudentRequestDTO(
                18,
                "2025-05-23",
                1234567,
                "SE",
                "SE",
                "2024-2025",
                "CSE101",
                "CS",
                " "
        );

        Set<ConstraintViolation<GradeAStudentRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }
}
