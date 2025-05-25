package PAI.dto.Programme;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Set;


import static org.junit.jupiter.api.Assertions.*;

class ProgrammeIDDTOTest {
    private static Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidProgrammeIDDTO() {
        ProgrammeIDDTO dto = new ProgrammeIDDTO("Computer Science", "CSE");
        Set<ConstraintViolation<ProgrammeIDDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testBlankName() {
        ProgrammeIDDTO dto = new ProgrammeIDDTO("  ", "CSE");
        Set<ConstraintViolation<ProgrammeIDDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("Programme Name is required", violations.iterator().next().getMessage());
    }

    @Test
    public void testNullName() {
        ProgrammeIDDTO dto = new ProgrammeIDDTO(null, "CSE");
        Set<ConstraintViolation<ProgrammeIDDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("Programme Name is required", violations.iterator().next().getMessage());
    }

    @Test
    public void testShortAcronym() {
        ProgrammeIDDTO dto = new ProgrammeIDDTO("Engineering", "CS");
        Set<ConstraintViolation<ProgrammeIDDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("Acronym must have 3 characters", violations.iterator().next().getMessage());
    }

    @Test
    public void testLongAcronym() {
        ProgrammeIDDTO dto = new ProgrammeIDDTO("Engineering", "COMP");
        Set<ConstraintViolation<ProgrammeIDDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("Acronym must have 3 characters", violations.iterator().next().getMessage());
    }

    @Test
    public void testNullAcronym() {
        ProgrammeIDDTO dto = new ProgrammeIDDTO("Engineering", null);
        Set<ConstraintViolation<ProgrammeIDDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("Programme Acronym is required", violations.iterator().next().getMessage());
    }

    @Test
    public void testBlackAcronym() {
        ProgrammeIDDTO dto = new ProgrammeIDDTO("Engineering", "");
        Set<ConstraintViolation<ProgrammeIDDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("Programme Acronym is required", violations.iterator().next().getMessage());
    }
}