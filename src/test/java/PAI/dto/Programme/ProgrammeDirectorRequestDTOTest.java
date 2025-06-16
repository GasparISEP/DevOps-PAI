package PAI.dto.Programme;

import PAI.dto.teacher.TeacherIdDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeDirectorRequestDTOTest {

    private static Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidTeacher() {
        TeacherIdDTO teacher = new TeacherIdDTO("ABC");
        ProgrammeDirectorRequestDTO dto = new ProgrammeDirectorRequestDTO(teacher);

        Set<ConstraintViolation<ProgrammeDirectorRequestDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidTeacherAcronym() {
        TeacherIdDTO teacher = new TeacherIdDTO("AB");
        ProgrammeDirectorRequestDTO dto = new ProgrammeDirectorRequestDTO(teacher);

        Set<ConstraintViolation<ProgrammeDirectorRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());

        // Because validation happens on nested TeacherIDDTO,
        // the message should reflect the TeacherIDDTO's constraint
        assertEquals("Acronym must have exactly 3 characters",
                violations.iterator().next().getMessage());
    }

    @Test
    void testNullTeacher() {
        ProgrammeDirectorRequestDTO dto = new ProgrammeDirectorRequestDTO(null);

        Set<ConstraintViolation<ProgrammeDirectorRequestDTO>> violations = validator.validate(dto);

        // Expect at least one violation because teacher is null
        assertFalse(violations.isEmpty());
    }
}
