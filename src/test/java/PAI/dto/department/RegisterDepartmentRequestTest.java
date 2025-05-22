package PAI.dto.department;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RegisterDepartmentRequestTest {

    @Test
    void shouldCreateRegisterDepartmentRequestWithGivenValues() {
        // Arrange
        String name = "Software Engineering Department";
        String acronym = "DEI";

        // Act
        RegisterDepartmentRequest request = new RegisterDepartmentRequest(name, acronym);

        // Assert
        assertEquals(name, request.name());
        assertEquals(acronym, request.acronym());
    }


    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldPassValidationWithValidFields() {
        RegisterDepartmentRequest request = new RegisterDepartmentRequest("Software Engineering", "DEI");
        Set<ConstraintViolation<RegisterDepartmentRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty(), "There should be no validation errors");
    }

    @Test
    void shouldFailValidationWhenNameIsBlank() {
        RegisterDepartmentRequest request = new RegisterDepartmentRequest(" ", "DEI");
        Set<ConstraintViolation<RegisterDepartmentRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals("Name is required", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenAcronymIsBlank() {
        RegisterDepartmentRequest request = new RegisterDepartmentRequest("Software Engineering", " ");
        Set<ConstraintViolation<RegisterDepartmentRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals("Acronym is required", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenAllFieldsAreBlank() {
        RegisterDepartmentRequest request = new RegisterDepartmentRequest(" ", " ");
        Set<ConstraintViolation<RegisterDepartmentRequest>> violations = validator.validate(request);

        assertEquals(2, violations.size());
    }

}