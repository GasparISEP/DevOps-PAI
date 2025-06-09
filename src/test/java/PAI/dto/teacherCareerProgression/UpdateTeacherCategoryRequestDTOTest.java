package PAI.dto.teacherCareerProgression;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UpdateTeacherCategoryRequestDTOTest {

    private Validator validator;

    @BeforeEach
    void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldPassValidationWhenAllFieldsAreValid() {
        LocalDate date = mock (LocalDate.class);
        UpdateTeacherCategoryRequestDTO request = new UpdateTeacherCategoryRequestDTO(date, "123");
        Set<ConstraintViolation<UpdateTeacherCategoryRequestDTO>> violations = validator.validate(request);
        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldFailValidationWhenDateIsNull() {
        UpdateTeacherCategoryRequestDTO request = new UpdateTeacherCategoryRequestDTO(null, "123");
        Set<ConstraintViolation<UpdateTeacherCategoryRequestDTO>> violations = validator.validate(request);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("The date field is required!")));
    }

    @Test
    void shouldFailValidationWhenCategoryIdIsBlank() {
        LocalDate date = mock (LocalDate.class);
        UpdateTeacherCategoryRequestDTO request = new UpdateTeacherCategoryRequestDTO(date, "");
        Set<ConstraintViolation<UpdateTeacherCategoryRequestDTO>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("The Teacher Category ID field cannot be blank!")));
    }

    @Test
    void shouldFailValidationWhenCategoryIdIsNull() {
        LocalDate date = mock (LocalDate.class);
        UpdateTeacherCategoryRequestDTO request = new UpdateTeacherCategoryRequestDTO(date, null);
        Set<ConstraintViolation<UpdateTeacherCategoryRequestDTO>> violations = validator.validate(request);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("The Teacher Category ID field is required!")));
    }

}