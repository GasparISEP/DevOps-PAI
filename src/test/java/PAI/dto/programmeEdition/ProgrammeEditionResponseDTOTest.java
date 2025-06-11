package PAI.dto.programmeEdition;
import PAI.dto.Programme.ProgrammeIDDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProgrammeEditionResponseDTOTest {
    private static Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidProgrammeEditionResponseDTO() {
        //arrange
        ProgrammeIDDTO programme = mock(ProgrammeIDDTO.class);
        String schoolYear = "5a5ea957-4319-4baa-be1b-6c680f479f68";
        ProgrammeEditionResponseDTO ProgrammeEditionResponseDTO = new ProgrammeEditionResponseDTO(programme, schoolYear);
        //act
        Set<ConstraintViolation<ProgrammeEditionResponseDTO>> violations = validator.validate(ProgrammeEditionResponseDTO);
        //assert
        assertTrue(violations.isEmpty());
    }

    @Test
    void testNullProgrammeIDResponseDTO() {
        //arrange
        String schoolYear = "5a5ea957-4319-4baa-be1b-6c680f479f68";
        ProgrammeEditionResponseDTO ProgrammeEditionResponseDTO = new ProgrammeEditionResponseDTO(null, schoolYear);
        //act
        Set<ConstraintViolation<ProgrammeEditionResponseDTO>> violations = validator.validate(ProgrammeEditionResponseDTO);
        //assert
        assertFalse(violations.isEmpty());
        assertEquals("Programme is required", violations.iterator().next().getMessage());
    }

    @Test
    void testNullSchoolYearID() {
        //arrange
        ProgrammeIDDTO programme = mock(ProgrammeIDDTO.class);
        ProgrammeEditionResponseDTO ProgrammeEditionResponseDTO = new ProgrammeEditionResponseDTO(programme, null);
        //act
        Set<ConstraintViolation<ProgrammeEditionResponseDTO>> violations = validator.validate(ProgrammeEditionResponseDTO);
        //assert
        assertFalse(violations.isEmpty());
        assertEquals("School Year ID is required", violations.iterator().next().getMessage());
    }
}