package PAI.dto.schoolYear;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SchoolYearIDRequestDTOTest {
    private static Validator validator;
    final String validUUID = "40bcdc10-9713-4253-8dbb-ea0ceb07eded";

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidSchoolYearID() {
        SchoolYearIDRequestDTO dto = new SchoolYearIDRequestDTO(validUUID);
        Set<ConstraintViolation<SchoolYearIDRequestDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testBlankSchoolYearID() {
        SchoolYearIDRequestDTO dto = new SchoolYearIDRequestDTO("");
        Set<ConstraintViolation<SchoolYearIDRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("ID is required", violations.iterator().next().getMessage());
    }

    @Test
    void testNullSchoolYearID() {
        SchoolYearIDRequestDTO dto = new SchoolYearIDRequestDTO(null);
        Set<ConstraintViolation<SchoolYearIDRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("ID is required", violations.iterator().next().getMessage());
    }
}