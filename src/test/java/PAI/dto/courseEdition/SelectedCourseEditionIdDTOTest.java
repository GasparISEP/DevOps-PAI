package PAI.dto.courseEdition;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SelectedCourseEditionIdDTOTest {

    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldCreateCourseEditionReferenceDTO() {
        String programmeName = "Software Development";
        String programmeAcronym = "MEI";
        UUID schoolYearID = UUID.randomUUID();
        String courseAcronym = "AP";
        String courseName = "Advanced Programming";
        LocalDate studyPlanImplementationDate = LocalDate.now();

        SelectedCourseEditionIdDTO dto = new SelectedCourseEditionIdDTO(
                programmeName, programmeAcronym, schoolYearID,
                courseAcronym, courseName, studyPlanImplementationDate
        );

        assertEquals(programmeName, dto.programmeName());
        assertEquals(programmeAcronym, dto.programmeAcronym());
        assertEquals(schoolYearID, dto.schoolYearID());
        assertEquals(courseAcronym, dto.courseAcronym());
        assertEquals(courseName, dto.courseName());
        assertEquals(studyPlanImplementationDate, dto.studyPlanImplementationDate());
    }

    @Test
    void shouldFailValidationWhenFieldsAreNullOrBlank() {
        // DTO com campos inválidos
        SelectedCourseEditionIdDTO dto = new SelectedCourseEditionIdDTO(
                "",  // Blank
                null, // Null
                null, // Null
                "",   // Blank
                "",   // Blank
                null  // Null
        );

        Set<ConstraintViolation<SelectedCourseEditionIdDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty(), "Deve ter violações de validação");

        // Opcional: verificar mensagens de erro específicas
        for (ConstraintViolation<SelectedCourseEditionIdDTO> violation : violations) {
            System.out.println("Campo: " + violation.getPropertyPath() + " - Erro: " + violation.getMessage());
        }
    }
}