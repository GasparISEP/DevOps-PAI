package PAI.dto.teacherCareerProgression;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UpdateTeacherCategoryRequestDTOTest {

    private Validator validator;

    @BeforeEach
    void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldPassValidationWhenAllFieldsAreValid() {
        UpdateTeacherCategoryRequestDTO request = new UpdateTeacherCategoryRequestDTO("12-05-2024", "ABC", "123");
        Set<ConstraintViolation<UpdateTeacherCategoryRequestDTO>> violations = validator.validate(request);
        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldFailValidationWhenDateIsBlank() {
        UpdateTeacherCategoryRequestDTO request = new UpdateTeacherCategoryRequestDTO(" ", "ABC", "123");
        Set<ConstraintViolation<UpdateTeacherCategoryRequestDTO>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Date is required.")));
    }

    @Test
    void shouldFailValidationWhenTeacherIdIsBlank() {
        UpdateTeacherCategoryRequestDTO request = new UpdateTeacherCategoryRequestDTO("12-05-2024", "", "123");
        Set<ConstraintViolation<UpdateTeacherCategoryRequestDTO>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Teacher ID required.")));
    }

    @Test
    void shouldFailValidationWhenCategoryIdIsBlank() {
        UpdateTeacherCategoryRequestDTO request = new UpdateTeacherCategoryRequestDTO("12-05-2024", "ABC", "");
        Set<ConstraintViolation<UpdateTeacherCategoryRequestDTO>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("New teacher category required")));
    }

}