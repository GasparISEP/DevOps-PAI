package PAI.dto.schoolYear;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SchoolYearIDDTOTest {
    private static Validator validator;
    final String validUUID = "40bcdc10-9713-4253-8dbb-ea0ceb07eded";

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidSchoolYearID() {
        SchoolYearIDDTO dto = new SchoolYearIDDTO(validUUID);
        Set<ConstraintViolation<SchoolYearIDDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testBlankSchoolYearID() {
        SchoolYearIDDTO dto = new SchoolYearIDDTO("");
        Set<ConstraintViolation<SchoolYearIDDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("ID is required", violations.iterator().next().getMessage());
    }

    @Test
    void testNullSchoolYearID() {
        SchoolYearIDDTO dto = new SchoolYearIDDTO(null);
        Set<ConstraintViolation<SchoolYearIDDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("ID is required", violations.iterator().next().getMessage());
    }
}