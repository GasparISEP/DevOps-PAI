package PAI.dto.Programme;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeIDResponseDTOTest {
    private static Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidProgrammeIDResponseDTO() {
        ProgrammeIDResponseDTO dto = new ProgrammeIDResponseDTO("Computer Science", "CSE");
        Set<ConstraintViolation<ProgrammeIDResponseDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testBlankName() {
        ProgrammeIDResponseDTO dto = new ProgrammeIDResponseDTO("  ", "CSE");
        Set<ConstraintViolation<ProgrammeIDResponseDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("Programme Name is required", violations.iterator().next().getMessage());
    }

    @Test
    public void testNullName() {
        ProgrammeIDResponseDTO dto = new ProgrammeIDResponseDTO(null, "CSE");
        Set<ConstraintViolation<ProgrammeIDResponseDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("Programme Name is required", violations.iterator().next().getMessage());
    }

    @Test
    public void testShortAcronym() {
        ProgrammeIDResponseDTO dto = new ProgrammeIDResponseDTO("Engineering", "CS");
        Set<ConstraintViolation<ProgrammeIDResponseDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("Acronym must have 3 characters", violations.iterator().next().getMessage());
    }

    @Test
    public void testLongAcronym() {
        ProgrammeIDResponseDTO dto = new ProgrammeIDResponseDTO("Engineering", "COMP");
        Set<ConstraintViolation<ProgrammeIDResponseDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("Acronym must have 3 characters", violations.iterator().next().getMessage());
    }

    @Test
    public void testNullAcronym() {
        ProgrammeIDResponseDTO dto = new ProgrammeIDResponseDTO("Engineering", null);
        Set<ConstraintViolation<ProgrammeIDResponseDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("Programme Acronym is required", violations.iterator().next().getMessage());
    }

    @Test
    public void testBlankAcronym() {
        ProgrammeIDResponseDTO dto = new ProgrammeIDResponseDTO("Engineering", "");
        Set<ConstraintViolation<ProgrammeIDResponseDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("Acronym must have 3 characters", violations.iterator().next().getMessage());
    }
}