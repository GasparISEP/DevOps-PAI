package PAI.dto.teacher;

import static org.junit.jupiter.api.Assertions.*;

import PAI.dto.course.CourseRequestDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TeacherRequestDTOTest {

    @Test
    void shouldCreateTeacherRequestDTOWithGivenValues() {
        // Arrange
        String name = "João Silva";
        String email = "joao.silva@gmail.com";
        String nif = "123456789";
        String phoneNumber = "123456789";
        String academicBackground = "MEI";
        String street = "Rua das Flores";
        String postalCode = "4470-147";
        String location = "Porto";
        String country = "Portugal";
        String departmentID = "1";

        // Act
        TeacherRequestDTO request = new TeacherRequestDTO(name, email, nif, phoneNumber, academicBackground, street, postalCode, location, country, departmentID);

        TeacherRequestDTO request2 = new TeacherRequestDTO(request._name(), request._email(), request._nif(), request._phoneNumber(), request._academicBackground(), request._street(), request._postalCode(), request._location(), request._country(), request._departmentID());

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
        TeacherRequestDTO request = new TeacherRequestDTO("João Silva", "joao.silva@gmail.com","123456789", "123456789", "MEI", "Rua das Flores", "4470-147", "Porto", "Portugal", "1");
        Set<ConstraintViolation<TeacherRequestDTO>> violations = validator.validate(request);

        assertTrue(violations.isEmpty(), "There should be no validation errors");
    }

    @Test
    void shouldFailValidationWhenNameIsBlank() {
        TeacherRequestDTO request = new TeacherRequestDTO(" ", "joao.silva@gmail.com","123456789", "123456789", "MEI", "Rua das Flores", "4470-147", "Porto", "Portugal", "1");
        Set<ConstraintViolation<TeacherRequestDTO>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals("Teacher Name cannot be blank.", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenEmailIsBlank() {
        TeacherRequestDTO request = new TeacherRequestDTO("João Silva", " ","123456789", "123456789", "MEI", "Rua das Flores", "4470-147", "Porto", "Portugal", "1");
        Set<ConstraintViolation<TeacherRequestDTO>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals("Email cannot be blank.", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenNifIsBlank() {
        TeacherRequestDTO request = new TeacherRequestDTO("João Silva", "joao.silva@gmail.com"," ", "123456789", "MEI", "Rua das Flores", "4470-147", "Porto", "Portugal", "1");
        Set<ConstraintViolation<TeacherRequestDTO>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals("NIF cannot be blank.", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenPhoneNumberIsBlank() {
        TeacherRequestDTO request = new TeacherRequestDTO("João Silva", "joao.silva@gmail.com","123456789", " ", "MEI", "Rua das Flores", "4470-147", "Porto", "Portugal", "1");
        Set<ConstraintViolation<TeacherRequestDTO>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals("Phone Number cannot be blank.", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenAcademicBackgroundIsBlank() {
        TeacherRequestDTO request = new TeacherRequestDTO("João Silva", "joao.silva@gmail.com","123456789", "123456789", " ", "Rua das Flores", "4470-147", "Porto", "Portugal", "1");
        Set<ConstraintViolation<TeacherRequestDTO>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals("Academic Background cannot be blank.", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenStreetIsBlank() {
        TeacherRequestDTO request = new TeacherRequestDTO("João Silva", "joao.silva@gmail.com","123456789", "123456789", "MEI", " ", "4470-147", "Porto", "Portugal", "1");
        Set<ConstraintViolation<TeacherRequestDTO>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals("Street cannot be blank.", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenPostalCodeIsBlank() {
        TeacherRequestDTO request = new TeacherRequestDTO("João Silva", "joao.silva@gmail.com","123456789", "123456789", "MEI", "Rua das Flores", " ", "Porto", "Portugal", "1");
        Set<ConstraintViolation<TeacherRequestDTO>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals("Postal Code cannot be blank.", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenLocationIsBlank() {
        TeacherRequestDTO request = new TeacherRequestDTO("João Silva", "joao.silva@gmail.com","123456789", "123456789", "MEI", "Rua das Flores", "4470-147", " ", "Portugal", "1");
        Set<ConstraintViolation<TeacherRequestDTO>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals("Location cannot be blank.", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenCountryIsBlank() {
        TeacherRequestDTO request = new TeacherRequestDTO("João Silva", "joao.silva@gmail.com","123456789", "123456789", "MEI", "Rua das Flores", "4470-147", "Porto", " ", "1");
        Set<ConstraintViolation<TeacherRequestDTO>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals("Country cannot be blank.", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenDepartmentIdIsBlank() {
        TeacherRequestDTO request = new TeacherRequestDTO("João Silva", "joao.silva@gmail.com","123456789", "123456789", "MEI", "Rua das Flores", "4470-147", "Porto", "Portugal", " ");
        Set<ConstraintViolation<TeacherRequestDTO>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals("Department ID cannot be blank.", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenAllFieldsAreBlank() {
        TeacherRequestDTO request = new TeacherRequestDTO(" ", " ", " ", " ", " ", " ", " ", " ", " ", " ");
        Set<ConstraintViolation<TeacherRequestDTO>> violations = validator.validate(request);

        assertEquals(10, violations.size());
    }
}