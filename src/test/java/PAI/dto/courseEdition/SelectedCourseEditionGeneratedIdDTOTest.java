package PAI.dto.courseEdition;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SelectedCourseEditionGeneratedIdDTOTest {
    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        validator = factory.getValidator();
    }

    @Test
    void shouldCreateCourseEditionReferenceDTO() {

        UUID courseEditionID = UUID.randomUUID();

        SelectedCourseEditionGeneratedIdDTO dto = new SelectedCourseEditionGeneratedIdDTO(
                courseEditionID
        );
        assertEquals(courseEditionID, dto.courseEditionID());
    }

    @Test
    void shouldFailValidationWhenFieldsAreNullOrBlank() {
        SelectedCourseEditionGeneratedIdDTO dto = new SelectedCourseEditionGeneratedIdDTO(
                null

        );
        Set<ConstraintViolation<SelectedCourseEditionGeneratedIdDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty(), "Deve ter violações de validação");

        for (ConstraintViolation<SelectedCourseEditionGeneratedIdDTO> violation : violations) {
            System.out.println("Campo: " + violation.getPropertyPath() + " - Erro: " + violation.getMessage());
        }
    }
}