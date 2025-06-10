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

class ProgrammeEditionRequestServiceDTOTest {
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
        ProgrammeEditionRequestServiceDTO programmeEditionRequestServiceDTO = new ProgrammeEditionRequestServiceDTO(programme);
        //act
        Set<ConstraintViolation<ProgrammeEditionRequestServiceDTO>> violations = validator.validate(programmeEditionRequestServiceDTO);
        //assert
        assertTrue(violations.isEmpty());
    }

    @Test
    void testNullProgrammeIDDTO() {
        //arrange
        ProgrammeEditionRequestServiceDTO programmeEditionRequestServiceDTO = new ProgrammeEditionRequestServiceDTO(null);
        //act
        Set<ConstraintViolation<ProgrammeEditionRequestServiceDTO>> violations = validator.validate(programmeEditionRequestServiceDTO);
        //assert
        assertFalse(violations.isEmpty());
        assertEquals("Programme is required", violations.iterator().next().getMessage());
    }
}
