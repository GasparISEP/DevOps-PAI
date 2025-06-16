package PAI.dto.Programme;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeDirectorVOsDTOTest {

    private static Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidTeacherID() {
        ProgrammeDirectorVOsDTO dto = new ProgrammeDirectorVOsDTO("TCH");
        Set<ConstraintViolation<ProgrammeDirectorVOsDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testNullTeacherID() {
        ProgrammeDirectorVOsDTO dto = new ProgrammeDirectorVOsDTO(null);
        Set<ConstraintViolation<ProgrammeDirectorVOsDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("Teacher ID is required", violations.iterator().next().getMessage());
    }

    @Test
    void testBlankTeacherID() {
        ProgrammeDirectorVOsDTO dto = new ProgrammeDirectorVOsDTO("");
        Set<ConstraintViolation<ProgrammeDirectorVOsDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("Teacher ID is required", violations.iterator().next().getMessage());
    }

    @Test
    void testWhitespaceTeacherID() {
        ProgrammeDirectorVOsDTO dto = new ProgrammeDirectorVOsDTO("  ");
        Set<ConstraintViolation<ProgrammeDirectorVOsDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("Teacher ID is required", violations.iterator().next().getMessage());
    }
}