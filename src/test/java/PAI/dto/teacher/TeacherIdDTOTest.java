package PAI.dto.teacher;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TeacherIdDTOTest {

    private static Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidAcronym() {
        TeacherIdDTO dto = new TeacherIdDTO("ABC");
        Set<ConstraintViolation<TeacherIdDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testNullAcronym() {
        TeacherIdDTO dto = new TeacherIdDTO(null);
        Set<ConstraintViolation<TeacherIdDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("Teacher Acronym is required", violations.iterator().next().getMessage());
    }

//    @Test
//    public void testBlankAcronym() {
//        TeacherIdDTO dto = new TeacherIdDTO(" ");
//        Set<ConstraintViolation<TeacherIdDTO>> violations = validator.validate(dto);
//        assertFalse(violations.isEmpty());
//        assertEquals("Teacher Acronym is required", violations.iterator().next().getMessage());
//    }

    @Test
    public void testShortAcronym() {
        TeacherIdDTO dto = new TeacherIdDTO("AB");
        Set<ConstraintViolation<TeacherIdDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("Acronym must have exactly 3 characters", violations.iterator().next().getMessage());
    }

    @Test
    public void testLongAcronym() {
        TeacherIdDTO dto = new TeacherIdDTO("ABCD");
        Set<ConstraintViolation<TeacherIdDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("Acronym must have exactly 3 characters", violations.iterator().next().getMessage());
    }
}