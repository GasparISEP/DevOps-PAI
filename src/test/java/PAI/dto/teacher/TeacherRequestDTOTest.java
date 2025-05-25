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
        String teacherID = "1";
        String name = "João Silva";
        String email = "joao.silva@gmail.com";
        String nif = "123456789";
        String countryCode = "+351";
        String phoneNumber = "123456789";
        String academicBackground = "MEI";
        String street = "Rua das Flores";
        String postalCode = "4470-147";
        String location = "Porto";
        String country = "Portugal";
        String departmentID = "1";

        // Act
        TeacherRequestDTO request = new TeacherRequestDTO(teacherID, name, email, nif, countryCode, phoneNumber, academicBackground, street, postalCode, location, country, departmentID);

        // Assert
        assertEquals(teacherID, request.id());
        assertEquals(name, request.name());
        assertEquals(email, request.email());
        assertEquals(nif, request.nif());
        assertEquals(countryCode, request.countryCode());
        assertEquals(phoneNumber, request.phoneNumber());
        assertEquals(academicBackground, request.academicBackground());
        assertEquals(street, request.street());
        assertEquals(postalCode, request.postalCode());
        assertEquals(location, request.location());
        assertEquals(country, request.country());
        assertEquals(departmentID, request.departmentID());
    }


    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldPassValidationWithValidFields() {
        TeacherRequestDTO request = new TeacherRequestDTO("1","João Silva", "joao.silva@gmail.com","123456789", "+351", "123456789", "MEI", "Rua das Flores", "4470-147", "Porto", "Portugal", "1");
        Set<ConstraintViolation<TeacherRequestDTO>> violations = validator.validate(request);

        assertTrue(violations.isEmpty(), "There should be no validation errors");
    }

    @Test
    void shouldFailValidationWhenTeacherIdIsBlank() {
        TeacherRequestDTO request = new TeacherRequestDTO(" ","João Silva", "joao.silva@gmail.com","123456789", "+351", "123456789", "MEI", "Rua das Flores", "4470-147", "Porto", "Portugal", "1");
        Set<ConstraintViolation<TeacherRequestDTO>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals("Teacher ID cannot be blank.", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenTeacherNameIsBlank() {
        TeacherRequestDTO request = new TeacherRequestDTO("1"," ", "joao.silva@gmail.com","123456789", "+351", "123456789", "MEI", "Rua das Flores", "4470-147", "Porto", "Portugal", "1");
        Set<ConstraintViolation<TeacherRequestDTO>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals("Teacher Name cannot be blank.", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenEmailIsBlank() {
        TeacherRequestDTO request = new TeacherRequestDTO("1","João Silva", " ","123456789", "+351", "123456789", "MEI", "Rua das Flores", "4470-147", "Porto", "Portugal", "1");
        Set<ConstraintViolation<TeacherRequestDTO>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals("Email cannot be blank.", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenNifIsBlank() {
        TeacherRequestDTO request = new TeacherRequestDTO("1","João Silva", "joao.silva@gmail.com"," ", "+351", "123456789", "MEI", "Rua das Flores", "4470-147", "Porto", "Portugal", "1");
        Set<ConstraintViolation<TeacherRequestDTO>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals("NIF cannot be blank.", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenCountryCodeIsBlank() {
        TeacherRequestDTO request = new TeacherRequestDTO("1","João Silva", "joao.silva@gmail.com","123456789", " ", "123456789", "MEI", "Rua das Flores", "4470-147", "Porto", "Portugal", "1");
        Set<ConstraintViolation<TeacherRequestDTO>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals("CountryCode cannot be blank.", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenPhoneNumberIsBlank() {
        TeacherRequestDTO request = new TeacherRequestDTO("1","João Silva", "joao.silva@gmail.com","123456789", "+351", " ", "MEI", "Rua das Flores", "4470-147", "Porto", "Portugal", "1");
        Set<ConstraintViolation<TeacherRequestDTO>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals("Phone Number cannot be blank.", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenAcademicBackgroundIsBlank() {
        TeacherRequestDTO request = new TeacherRequestDTO("1","João Silva", "joao.silva@gmail.com","123456789", "+351", "123456789", " ", "Rua das Flores", "4470-147", "Porto", "Portugal", "1");
        Set<ConstraintViolation<TeacherRequestDTO>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals("Academic Background cannot be blank.", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenStreetIsBlank() {
        TeacherRequestDTO request = new TeacherRequestDTO("1","João Silva", "joao.silva@gmail.com","123456789", "+351", "123456789", "MEI", " ", "4470-147", "Porto", "Portugal", "1");
        Set<ConstraintViolation<TeacherRequestDTO>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals("Street cannot be blank.", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenPostalCodeIsBlank() {
        TeacherRequestDTO request = new TeacherRequestDTO("1","João Silva", "joao.silva@gmail.com","123456789", "+351", "123456789", "MEI", "Rua das Flores", " ", "Porto", "Portugal", "1");
        Set<ConstraintViolation<TeacherRequestDTO>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals("Postal Code cannot be blank.", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenLocationIsBlank() {
        TeacherRequestDTO request = new TeacherRequestDTO("1","João Silva", "joao.silva@gmail.com","123456789", "+351", "123456789", "MEI", "Rua das Flores", "4470-147", " ", "Portugal", "1");
        Set<ConstraintViolation<TeacherRequestDTO>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals("Location cannot be blank.", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenCountryIsBlank() {
        TeacherRequestDTO request = new TeacherRequestDTO("1","João Silva", "joao.silva@gmail.com","123456789", "+351", "123456789", "MEI", "Rua das Flores", "4470-147", "Porto", " ", "1");
        Set<ConstraintViolation<TeacherRequestDTO>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals("Country cannot be blank.", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenDepartmentIdIsBlank() {
        TeacherRequestDTO request = new TeacherRequestDTO("1","João Silva", "joao.silva@gmail.com","123456789", "+351", "123456789", "MEI", "Rua das Flores", "4470-147", "Porto", "Portugal", " ");
        Set<ConstraintViolation<TeacherRequestDTO>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals("Department ID cannot be blank.", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenAllFieldsAreBlank() {
        TeacherRequestDTO request = new TeacherRequestDTO(" "," "," ", " ", " ", "123456789", " ", " ", " ", " ", " ", " ");
        Set<ConstraintViolation<TeacherRequestDTO>> violations = validator.validate(request);

        assertEquals(11, violations.size());
    }
}