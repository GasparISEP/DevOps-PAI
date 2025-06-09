package PAI.dto.programmeEdition;

import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.schoolYear.SchoolYearIDDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProgrammeEditionServiceDTOTest {
    private static Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidProgrammeEditionDTO() {
        ProgrammeIDDTO programme = mock(ProgrammeIDDTO.class);
        SchoolYearIDDTO schoolYear = mock(SchoolYearIDDTO.class);
        ProgrammeEditionServiceDTO programmeEditionServiceDTO = new ProgrammeEditionServiceDTO(programme, schoolYear);
        Set<ConstraintViolation<ProgrammeEditionServiceDTO>> violations = validator.validate(programmeEditionServiceDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testNullProgrammeIDDTO() {
        ProgrammeIDDTO programme = null;
        SchoolYearIDDTO schoolYear = mock(SchoolYearIDDTO.class);
        ProgrammeEditionServiceDTO programmeEditionServiceDTO = new ProgrammeEditionServiceDTO(programme, schoolYear);
        Set<ConstraintViolation<ProgrammeEditionServiceDTO>> violations = validator.validate(programmeEditionServiceDTO);
        assertFalse(violations.isEmpty());
        assertEquals("Programme is required", violations.iterator().next().getMessage());
    }

    @Test
    void testNullSchoolYearIDDTO() {
        ProgrammeIDDTO programme = mock(ProgrammeIDDTO.class);
        SchoolYearIDDTO schoolYear = null;
        ProgrammeEditionServiceDTO programmeEditionServiceDTO = new ProgrammeEditionServiceDTO(programme, schoolYear);
        Set<ConstraintViolation<ProgrammeEditionServiceDTO>> violations = validator.validate(programmeEditionServiceDTO);
        assertFalse(violations.isEmpty());
        assertEquals("School Year is required", violations.iterator().next().getMessage());
    }

}