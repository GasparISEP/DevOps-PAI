package PAI.dto.courseEdition;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.validation.Validator;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class DefineRucRequestDTOTest {

    private Validator validator;

    @BeforeEach
    void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private SelectedCourseEditionIdDTO validCourseEditionDTO() {
        return new SelectedCourseEditionIdDTO(
                "Software Engineering",
                "SE",
                UUID.randomUUID(),
                "CS101",
                "Intro to Programming",
                LocalDate.now()
        );
    }

    @Test
    void shouldPassValidation_WhenAllFieldsAreValid() {
        //arrange
        DefineRucRequestDTO dto = new DefineRucRequestDTO("GOM", validCourseEditionDTO());
        //act
        Set<ConstraintViolation<DefineRucRequestDTO>> violations = validator.validate(dto);
        //assert
        assertTrue(violations.isEmpty(), "DTO should be valid");
    }

    @Test
    void shouldFailWhenTeacherIDIsBlank() {
        //arrange
        DefineRucRequestDTO dto = new DefineRucRequestDTO("", validCourseEditionDTO());
        //act
        Set<ConstraintViolation<DefineRucRequestDTO>> violations = validator.validate(dto);
        //assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v ->
                v.getPropertyPath().toString().equals("teacherID") &&
                        v.getMessage().equals("Teacher ID is required")));
    }

    @Test
    void shouldFailWhenCourseEditionDTOIsNull() {
        //arrange
        DefineRucRequestDTO dto = new DefineRucRequestDTO("GOM", null);
        //act
        Set<ConstraintViolation<DefineRucRequestDTO>> violations = validator.validate(dto);
        //assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v ->
                v.getPropertyPath().toString().equals("courseEditionDTO") &&
                        v.getMessage().equals("Course edition is required")));
    }
}