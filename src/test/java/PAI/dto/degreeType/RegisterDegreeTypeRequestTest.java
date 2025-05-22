package PAI.dto.degreeType;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RegisterDegreeTypeRequestTest {

    private static Validator validator;

    @BeforeAll
    static void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldCreateDegreeTypeRequestWithValidData() {
        RegisterDegreeTypeRequest request = new RegisterDegreeTypeRequest("Mestrado", 180);
        Set<ConstraintViolation<RegisterDegreeTypeRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty(), "Should not have any violations for valid data");

        assertEquals("Mestrado", request.name());
        assertEquals(180, request.maxEcts());
    }

    @Test
    void shouldHaveViolationWhenNameIsNull() {
        RegisterDegreeTypeRequest request = new RegisterDegreeTypeRequest(null, 180);
        Set<ConstraintViolation<RegisterDegreeTypeRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("name") &&
                        v.getMessage().equals("Name is required.")));
    }

    @Test
    void shouldHaveViolationWhenNameIsBlank() {
        RegisterDegreeTypeRequest request = new RegisterDegreeTypeRequest("  ", 180);
        Set<ConstraintViolation<RegisterDegreeTypeRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("name") &&
                        v.getMessage().equals("Name is required.")));
    }

    @Test
    void shouldHaveViolationWhenMaxEctsIsZero() {
        RegisterDegreeTypeRequest request = new RegisterDegreeTypeRequest("Mestrado", 0);
        Set<ConstraintViolation<RegisterDegreeTypeRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("maxEcts") &&
                        v.getMessage().equals("Max ECTS must be greater than 0.")));
    }

    @Test
    void shouldHaveViolationWhenMaxEctsIsNegative() {
        RegisterDegreeTypeRequest request = new RegisterDegreeTypeRequest("Mestrado", -10);
        Set<ConstraintViolation<RegisterDegreeTypeRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("maxEcts") &&
                        v.getMessage().equals("Max ECTS must be greater than 0.")));
    }
}