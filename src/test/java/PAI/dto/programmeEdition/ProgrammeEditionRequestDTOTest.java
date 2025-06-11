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

class ProgrammeEditionRequestDTOTest {
    private static Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidProgrammeEditionRequestDTO() {
        //arrange
        ProgrammeIDDTO programme = mock(ProgrammeIDDTO.class);
        ProgrammeEditionRequestDTO ProgrammeEditionRequestDTO = new ProgrammeEditionRequestDTO(programme);
        //act
        Set<ConstraintViolation<ProgrammeEditionRequestDTO>> violations = validator.validate(ProgrammeEditionRequestDTO);
        //assert
        assertTrue(violations.isEmpty());
    }

    @Test
    void testNullProgrammeIDRequestDTO() {
        //arrange
        ProgrammeIDDTO programme = null;
        ProgrammeEditionRequestDTO ProgrammeEditionRequestDTO = new ProgrammeEditionRequestDTO(programme);
        //act
        Set<ConstraintViolation<ProgrammeEditionRequestDTO>> violations = validator.validate(ProgrammeEditionRequestDTO);
        //assert
        assertFalse(violations.isEmpty());
        assertEquals("Programme is required", violations.iterator().next().getMessage());
    }
}