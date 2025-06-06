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
        ProgrammeIDDTO dto = new ProgrammeIDDTO("CSE");
        Set<ConstraintViolation<ProgrammeIDDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testShortAcronym() {
        ProgrammeIDDTO dto = new ProgrammeIDDTO("CS");
        Set<ConstraintViolation<ProgrammeIDDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("Acronym must have 3 characters", violations.iterator().next().getMessage());
    }

    @Test
    public void testLongAcronym() {
        ProgrammeIDDTO dto = new ProgrammeIDDTO("COMP");
        Set<ConstraintViolation<ProgrammeIDDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("Acronym must have 3 characters", violations.iterator().next().getMessage());
    }

    @Test
    public void testNullAcronym() {
        ProgrammeIDDTO dto = new ProgrammeIDDTO( null);
        Set<ConstraintViolation<ProgrammeIDDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("Programme Acronym is required", violations.iterator().next().getMessage());
    }

    @Test
    public void testBlankAcronym() {
        ProgrammeIDDTO dto = new ProgrammeIDDTO("");
        Set<ConstraintViolation<ProgrammeIDDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("Acronym must have 3 characters", violations.iterator().next().getMessage());
    }
}