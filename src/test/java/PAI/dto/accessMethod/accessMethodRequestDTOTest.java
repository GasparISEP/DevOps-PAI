package PAI.dto.accessMethod;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class accessMethodRequestDTOTest {

    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenNameIsNull_thenValidationFails() {
        AccessMethodRequestDTO dto = new AccessMethodRequestDTO(null);
        Set<ConstraintViolation<AccessMethodRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Name is required")));
    }

    @Test
    void whenNameIsBlank_thenValidationFails() {
        AccessMethodRequestDTO dto = new AccessMethodRequestDTO("   ");
        Set<ConstraintViolation<AccessMethodRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Name is required")));
    }

    @Test
    void whenNameTooShort_thenValidationFails() {
        AccessMethodRequestDTO dto = new AccessMethodRequestDTO("a");
        Set<ConstraintViolation<AccessMethodRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("between 3 and 255")));
    }

    @Test
    void whenNameTooLong_thenValidationFails() {
        String longName = "a".repeat(256);
        AccessMethodRequestDTO dto = new AccessMethodRequestDTO(longName);
        Set<ConstraintViolation<AccessMethodRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("between 3 and 255")));
    }

    @Test
    void whenNameIsValid_thenValidationSucceeds() {
        AccessMethodRequestDTO dto = new AccessMethodRequestDTO("Valid Name");
        Set<ConstraintViolation<AccessMethodRequestDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }
}
