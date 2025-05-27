package PAI.dto.teacherCareerProgression;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
class UpdateTeacherWorkingPercentageRequestDTOTest {

    private Validator validator;

    @BeforeEach
    void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldPassValidationWhenAllFieldsAreValid() {
        UpdateTeacherWorkingPercentageRequestDTO request = new UpdateTeacherWorkingPercentageRequestDTO("12-05-2024", "ABC", 70);
        Set<ConstraintViolation<UpdateTeacherWorkingPercentageRequestDTO>> violations = validator.validate(request);
        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldFailValidationWhenDateIsBlank() {
        UpdateTeacherWorkingPercentageRequestDTO request = new UpdateTeacherWorkingPercentageRequestDTO(" ", "ABC", 70);
        Set<ConstraintViolation<UpdateTeacherWorkingPercentageRequestDTO>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Date is required.")));
    }

    @Test
    void shouldFailValidationWhenTeacherIdIsBlank() {
        UpdateTeacherWorkingPercentageRequestDTO request = new UpdateTeacherWorkingPercentageRequestDTO("12-05-2024", "", 70);
        Set<ConstraintViolation<UpdateTeacherWorkingPercentageRequestDTO>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Teacher ID required.")));
    }

    @Test
    void shouldFailValidationWhenWorkingPercentageIsNegative() {
        UpdateTeacherWorkingPercentageRequestDTO request = new UpdateTeacherWorkingPercentageRequestDTO("12-05-2024", "ABC",-1 );
        Set<ConstraintViolation<UpdateTeacherWorkingPercentageRequestDTO>> violations = validator.validate(request);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Working percentage must be at least 0")));
    }

    @Test
    void shouldFailValidationWhenWorkingPercentageIsAbove100() {
        UpdateTeacherWorkingPercentageRequestDTO request = new UpdateTeacherWorkingPercentageRequestDTO("12-05-2024", "ABC",101 );
        Set<ConstraintViolation<UpdateTeacherWorkingPercentageRequestDTO>> violations = validator.validate(request);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Working percentage must be at most 100")));
    }
  
}