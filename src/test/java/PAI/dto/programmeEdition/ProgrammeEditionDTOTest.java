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

class ProgrammeEditionDTOTest {
    private static Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidProgrammeEditionDTO() {
        ProgrammeIDDTO programme = mock(ProgrammeIDDTO.class);
        SchoolYearIDRequestDTO schoolYear = mock(SchoolYearIDRequestDTO.class);
        ProgrammeEditionDTO programmeEditionDTO = new ProgrammeEditionDTO(programme, schoolYear);
        Set<ConstraintViolation<ProgrammeEditionDTO>> violations = validator.validate(programmeEditionDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testNullProgrammeIDDTO() {
        ProgrammeIDDTO programme = null;
        SchoolYearIDRequestDTO schoolYear = mock(SchoolYearIDRequestDTO.class);
        ProgrammeEditionDTO programmeEditionDTO = new ProgrammeEditionDTO(programme, schoolYear);
        Set<ConstraintViolation<ProgrammeEditionDTO>> violations = validator.validate(programmeEditionDTO);
        assertFalse(violations.isEmpty());
        assertEquals("Programme is required", violations.iterator().next().getMessage());
    }

    @Test
    void testNullSchoolYearIDRequestDTO() {
        ProgrammeIDDTO programme = mock(ProgrammeIDDTO.class);
        SchoolYearIDRequestDTO schoolYear = null;
        ProgrammeEditionDTO programmeEditionDTO = new ProgrammeEditionDTO(programme, schoolYear);
        Set<ConstraintViolation<ProgrammeEditionDTO>> violations = validator.validate(programmeEditionDTO);
        assertFalse(violations.isEmpty());
        assertEquals("School Year is required", violations.iterator().next().getMessage());
    }

}