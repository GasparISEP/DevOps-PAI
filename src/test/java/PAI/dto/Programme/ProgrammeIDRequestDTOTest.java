package PAI.dto.Programme;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeIDRequestDTOTest {
    private static Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidProgrammeIDRequestDTO() {
        ProgrammeIDRequestDTO dto = new ProgrammeIDRequestDTO("Computer Science", "CSE");
        Set<ConstraintViolation<ProgrammeIDRequestDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testBlankName() {
        ProgrammeIDRequestDTO dto = new ProgrammeIDRequestDTO("  ", "CSE");
        Set<ConstraintViolation<ProgrammeIDRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("Programme Name is required", violations.iterator().next().getMessage());
    }

    @Test
    public void testNullName() {
        ProgrammeIDRequestDTO dto = new ProgrammeIDRequestDTO(null, "CSE");
        Set<ConstraintViolation<ProgrammeIDRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("Programme Name is required", violations.iterator().next().getMessage());
    }

    @Test
    public void testShortAcronym() {
        ProgrammeIDRequestDTO dto = new ProgrammeIDRequestDTO("Engineering", "CS");
        Set<ConstraintViolation<ProgrammeIDRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("Acronym must have 3 characters", violations.iterator().next().getMessage());
    }

    @Test
    public void testLongAcronym() {
        ProgrammeIDRequestDTO dto = new ProgrammeIDRequestDTO("Engineering", "COMP");
        Set<ConstraintViolation<ProgrammeIDRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("Acronym must have 3 characters", violations.iterator().next().getMessage());
    }

    @Test
    public void testNullAcronym() {
        ProgrammeIDRequestDTO dto = new ProgrammeIDRequestDTO("Engineering", null);
        Set<ConstraintViolation<ProgrammeIDRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("Programme Acronym is required", violations.iterator().next().getMessage());
    }

    @Test
    public void testBlankAcronym() {
        ProgrammeIDRequestDTO dto = new ProgrammeIDRequestDTO("Engineering", "");
        Set<ConstraintViolation<ProgrammeIDRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("Acronym must have 3 characters", violations.iterator().next().getMessage());
    }
}