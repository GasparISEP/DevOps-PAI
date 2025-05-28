package PAI.dto.programmeEdition;

import PAI.dto.Programme.ProgrammeIDRequestDTO;
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

class ProgrammeEditionRequestDTOTest {
    private static Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidProgrammeEditionRequestDTO() {
        ProgrammeIDRequestDTO programme = mock(ProgrammeIDRequestDTO.class);
        SchoolYearIDRequestDTO schoolYear = mock(SchoolYearIDRequestDTO.class);
        ProgrammeEditionRequestDTO ProgrammeEditionRequestDTO = new ProgrammeEditionRequestDTO(programme, schoolYear);
        Set<ConstraintViolation<ProgrammeEditionRequestDTO>> violations = validator.validate(ProgrammeEditionRequestDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testNullProgrammeIDRequestDTO() {
        ProgrammeIDRequestDTO programme = null;
        SchoolYearIDRequestDTO schoolYear = mock(SchoolYearIDRequestDTO.class);
        ProgrammeEditionRequestDTO ProgrammeEditionRequestDTO = new ProgrammeEditionRequestDTO(programme, schoolYear);
        Set<ConstraintViolation<ProgrammeEditionRequestDTO>> violations = validator.validate(ProgrammeEditionRequestDTO);
        assertFalse(violations.isEmpty());
        assertEquals("Programme is required", violations.iterator().next().getMessage());
    }

    @Test
    void testNullSchoolYearIDRequestDTO() {
        ProgrammeIDRequestDTO programme = mock(ProgrammeIDRequestDTO.class);
        SchoolYearIDRequestDTO schoolYear = null;
        ProgrammeEditionRequestDTO ProgrammeEditionRequestDTO = new ProgrammeEditionRequestDTO(programme, schoolYear);
        Set<ConstraintViolation<ProgrammeEditionRequestDTO>> violations = validator.validate(ProgrammeEditionRequestDTO);
        assertFalse(violations.isEmpty());
        assertEquals("School Year is required", violations.iterator().next().getMessage());
    }

}