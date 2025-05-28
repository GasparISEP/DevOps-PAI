package PAI.dto.programmeEdition;

import PAI.dto.Programme.ProgrammeIDResponseDTO;
import PAI.dto.schoolYear.SchoolYearIDResponseDTO;
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
        ProgrammeIDResponseDTO programme = mock(ProgrammeIDResponseDTO.class);
        SchoolYearIDResponseDTO schoolYear = mock(SchoolYearIDResponseDTO.class);
        ProgrammeEditionResponseDTO ProgrammeEditionResponseDTO = new ProgrammeEditionResponseDTO(programme, schoolYear);
        Set<ConstraintViolation<ProgrammeEditionResponseDTO>> violations = validator.validate(ProgrammeEditionResponseDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testNullProgrammeIDResponseDTO() {
        ProgrammeIDResponseDTO programme = null;
        SchoolYearIDResponseDTO schoolYear = mock(SchoolYearIDResponseDTO.class);
        ProgrammeEditionResponseDTO ProgrammeEditionResponseDTO = new ProgrammeEditionResponseDTO(programme, schoolYear);
        Set<ConstraintViolation<ProgrammeEditionResponseDTO>> violations = validator.validate(ProgrammeEditionResponseDTO);
        assertFalse(violations.isEmpty());
        assertEquals("Programme is required", violations.iterator().next().getMessage());
    }

    @Test
    void testNullSchoolYearIDResponseDTO() {
        ProgrammeIDResponseDTO programme = mock(ProgrammeIDResponseDTO.class);
        SchoolYearIDResponseDTO schoolYear = null;
        ProgrammeEditionResponseDTO ProgrammeEditionResponseDTO = new ProgrammeEditionResponseDTO(programme, schoolYear);
        Set<ConstraintViolation<ProgrammeEditionResponseDTO>> violations = validator.validate(ProgrammeEditionResponseDTO);
        assertFalse(violations.isEmpty());
        assertEquals("School Year is required", violations.iterator().next().getMessage());
    }

}