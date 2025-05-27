package PAI.dto.programmeEdition;

import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.schoolYear.SchoolYearIDRequestDTO;
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
        ProgrammeIDDTO programme = mock(ProgrammeIDDTO.class);
        SchoolYearIDRequestDTO schoolYear = mock(SchoolYearIDRequestDTO.class);
        ProgrammeEditionResponseDTO ProgrammeEditionResponseDTO = new ProgrammeEditionResponseDTO(programme, schoolYear);
        Set<ConstraintViolation<ProgrammeEditionResponseDTO>> violations = validator.validate(ProgrammeEditionResponseDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testNullProgrammeIDDTO() {
        ProgrammeIDDTO programme = null;
        SchoolYearIDRequestDTO schoolYear = mock(SchoolYearIDRequestDTO.class);
        ProgrammeEditionResponseDTO ProgrammeEditionResponseDTO = new ProgrammeEditionResponseDTO(programme, schoolYear);
        Set<ConstraintViolation<ProgrammeEditionResponseDTO>> violations = validator.validate(ProgrammeEditionResponseDTO);
        assertFalse(violations.isEmpty());
        assertEquals("Programme is required", violations.iterator().next().getMessage());
    }

    @Test
    void testNullSchoolYearIDRequestDTO() {
        ProgrammeIDDTO programme = mock(ProgrammeIDDTO.class);
        SchoolYearIDRequestDTO schoolYear = null;
        ProgrammeEditionResponseDTO ProgrammeEditionResponseDTO = new ProgrammeEditionResponseDTO(programme, schoolYear);
        Set<ConstraintViolation<ProgrammeEditionResponseDTO>> violations = validator.validate(ProgrammeEditionResponseDTO);
        assertFalse(violations.isEmpty());
        assertEquals("School Year is required", violations.iterator().next().getMessage());
    }

}