package PAI.dto.teacher;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeacherWithRelevantDataRequestDTOTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldCreateDTOWithGivenValues() {
        TeacherWithRelevantDataRequestDTO dto = new TeacherWithRelevantDataRequestDTO(
                "1", "João Silva", "joao.silva@gmail.com", "123456789", "+351", "912345678",
                "MEI", "Rua das Flores", "4470-147", "Porto", "Portugal", "1",
                "2024-01-01", "TC1", "100");

        assertEquals("1", dto.id());
        assertEquals("João Silva", dto.name());
        assertEquals("joao.silva@gmail.com", dto.email());
        assertEquals("123456789", dto.nif());
        assertEquals("+351", dto.countryCode());
        assertEquals("912345678", dto.phoneNumber());
        assertEquals("MEI", dto.academicBackground());
        assertEquals("Rua das Flores", dto.street());
        assertEquals("4470-147", dto.postalCode());
        assertEquals("Porto", dto.location());
        assertEquals("Portugal", dto.country());
        assertEquals("1", dto.departmentID());
        assertEquals("2024-01-01", dto.date());
        assertEquals("TC1", dto.teacherCategoryID());
        assertEquals("100", dto.workingPercentage());
    }

    @Test
    void shouldPassValidationWithValidFields() {
        TeacherWithRelevantDataRequestDTO dto = new TeacherWithRelevantDataRequestDTO(
                "1", "João Silva", "joao.silva@gmail.com", "123456789", "+351", "912345678",
                "MEI", "Rua das Flores", "4470-147", "Porto", "Portugal", "1",
                "2024-01-01", "TC1", "100");

        Set<ConstraintViolation<TeacherWithRelevantDataRequestDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "There should be no validation errors");
    }

    // Individual field blank tests
    @Test void shouldFailWhenIdIsBlank() {
        assertSingleViolation(new TeacherWithRelevantDataRequestDTO(" ", "name", "email", "nif", "+351", "912", "acad", "street", "code", "loc", "country", "dep", "date", "cat", "100"), "Teacher ID cannot be blank.");
    }

    @Test void shouldFailWhenNameIsBlank() {
        assertSingleViolation(new TeacherWithRelevantDataRequestDTO("id", " ", "email", "nif", "+351", "912", "acad", "street", "code", "loc", "country", "dep", "date", "cat", "100"), "Teacher Name cannot be blank.");
    }

    @Test void shouldFailWhenEmailIsBlank() {
        assertSingleViolation(new TeacherWithRelevantDataRequestDTO("id", "name", " ", "nif", "+351", "912", "acad", "street", "code", "loc", "country", "dep", "date", "cat", "100"), "Email cannot be blank.");
    }

    @Test void shouldFailWhenNifIsBlank() {
        assertSingleViolation(new TeacherWithRelevantDataRequestDTO("id", "name", "email", " ", "+351", "912", "acad", "street", "code", "loc", "country", "dep", "date", "cat", "100"), "NIF cannot be blank.");
    }

    @Test void shouldFailWhenCountryCodeIsBlank() {
        assertSingleViolation(new TeacherWithRelevantDataRequestDTO("id", "name", "email", "nif", " ", "912", "acad", "street", "code", "loc", "country", "dep", "date", "cat", "100"), "CountryCode cannot be blank.");
    }

    @Test void shouldFailWhenPhoneNumberIsBlank() {
        assertSingleViolation(new TeacherWithRelevantDataRequestDTO("id", "name", "email", "nif", "+351", " ", "acad", "street", "code", "loc", "country", "dep", "date", "cat", "100"), "Phone Number cannot be blank.");
    }

    @Test void shouldFailWhenAcademicBackgroundIsBlank() {
        assertSingleViolation(new TeacherWithRelevantDataRequestDTO("id", "name", "email", "nif", "+351", "912", " ", "street", "code", "loc", "country", "dep", "date", "cat", "100"), "Academic Background cannot be blank.");
    }

    @Test void shouldFailWhenStreetIsBlank() {
        assertSingleViolation(new TeacherWithRelevantDataRequestDTO("id", "name", "email", "nif", "+351", "912", "acad", " ", "code", "loc", "country", "dep", "date", "cat", "100"), "Street cannot be blank.");
    }

    @Test void shouldFailWhenPostalCodeIsBlank() {
        assertSingleViolation(new TeacherWithRelevantDataRequestDTO("id", "name", "email", "nif", "+351", "912", "acad", "street", " ", "loc", "country", "dep", "date", "cat", "100"), "Postal Code cannot be blank.");
    }

    @Test void shouldFailWhenLocationIsBlank() {
        assertSingleViolation(new TeacherWithRelevantDataRequestDTO("id", "name", "email", "nif", "+351", "912", "acad", "street", "code", " ", "country", "dep", "date", "cat", "100"), "Location cannot be blank.");
    }

    @Test void shouldFailWhenCountryIsBlank() {
        assertSingleViolation(new TeacherWithRelevantDataRequestDTO("id", "name", "email", "nif", "+351", "912", "acad", "street", "code", "loc", " ", "dep", "date", "cat", "100"), "Country cannot be blank.");
    }

    @Test void shouldFailWhenDepartmentIDIsBlank() {
        assertSingleViolation(new TeacherWithRelevantDataRequestDTO("id", "name", "email", "nif", "+351", "912", "acad", "street", "code", "loc", "country", " ", "date", "cat", "100"), "Department ID cannot be blank.");
    }

    @Test void shouldFailWhenDateIsBlank() {
        assertSingleViolation(new TeacherWithRelevantDataRequestDTO("id", "name", "email", "nif", "+351", "912", "acad", "street", "code", "loc", "country", "dep", " ", "cat", "100"), "Date cannot be blank.");
    }

    @Test void shouldFailWhenTeacherCategoryIDIsBlank() {
        assertSingleViolation(new TeacherWithRelevantDataRequestDTO("id", "name", "email", "nif", "+351", "912", "acad", "street", "code", "loc", "country", "dep", "date", " ", "100"), "Teacher Category ID cannot be blank.");
    }

    @Test void shouldFailWhenWorkingPercentageIsBlank() {
        assertSingleViolation(new TeacherWithRelevantDataRequestDTO("id", "name", "email", "nif", "+351", "912", "acad", "street", "code", "loc", "country", "dep", "date", "cat", " "), "Working Percentage cannot be blank.");
    }

    @Test
    void shouldFailValidationWhenAllFieldsAreBlank() {
        TeacherWithRelevantDataRequestDTO dto = new TeacherWithRelevantDataRequestDTO(
                " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ");
        Set<ConstraintViolation<TeacherWithRelevantDataRequestDTO>> violations = validator.validate(dto);

        assertEquals(15, violations.size(), "All 15 fields should fail validation");
    }

    // Helper to simplify repeated test logic
    private void assertSingleViolation(TeacherWithRelevantDataRequestDTO dto, String expectedMessage) {
        Set<ConstraintViolation<TeacherWithRelevantDataRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty(), "Validation should fail");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals(expectedMessage)), "Expected message not found: " + expectedMessage);
    }
}