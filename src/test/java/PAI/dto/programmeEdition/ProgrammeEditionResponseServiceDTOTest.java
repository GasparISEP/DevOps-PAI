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

class ProgrammeEditionResponseServiceDTOTest {
    private static Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidProgrammeEditionDTO() {
        //arrange
        ProgrammeIDDTO programme = mock(ProgrammeIDDTO.class);
        String schoolYear = "5a5ea957-4319-4baa-be1b-6c680f479f68";
        ProgrammeEditionResponseServiceDTO programmeEditionResponseServiceDTO = new ProgrammeEditionResponseServiceDTO(programme, schoolYear);
        //act
        Set<ConstraintViolation<ProgrammeEditionResponseServiceDTO>> violations = validator.validate(programmeEditionResponseServiceDTO);
        //assert
        assertTrue(violations.isEmpty());
    }

    @Test
    void testNullProgrammeIDDTO() {
        //arrange
        String schoolYear = "5a5ea957-4319-4baa-be1b-6c680f479f68";
        ProgrammeEditionResponseServiceDTO programmeEditionResponseServiceDTO = new ProgrammeEditionResponseServiceDTO(null, schoolYear);
        //act
        Set<ConstraintViolation<ProgrammeEditionResponseServiceDTO>> violations = validator.validate(programmeEditionResponseServiceDTO);
        //assert
        assertFalse(violations.isEmpty());
        assertEquals("Programme is required", violations.iterator().next().getMessage());
    }

    @Test
    void testNullSchoolYearIDDTO() {
        //arrange
        ProgrammeIDDTO programme = mock(ProgrammeIDDTO.class);
        ProgrammeEditionResponseServiceDTO programmeEditionResponseServiceDTO = new ProgrammeEditionResponseServiceDTO(programme, null);
        //act
        Set<ConstraintViolation<ProgrammeEditionResponseServiceDTO>> violations = validator.validate(programmeEditionResponseServiceDTO);
        //assert
        assertFalse(violations.isEmpty());
        assertEquals("School Year ID is required", violations.iterator().next().getMessage());
    }
}