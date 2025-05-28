package PAI.assembler.programmeEdition;

import PAI.VOs.*;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.programmeEdition.CountStudentsDto;
import PAI.dto.programmeEdition.ProgrammeEditionDTO;
import PAI.dto.programmeEdition.ProgrammeEditionIdDto;
import PAI.dto.schoolYear.SchoolYearIDDTO;
import PAI.dto.schoolYear.SchoolYearIDRequestDTO;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEditionAssemblerImplTest {
    private final ProgrammeEditionAssemblerImpl assembler = new ProgrammeEditionAssemblerImpl();

    @Test
    void shouldReturnAProgrammeEditionDTO() throws Exception {
        // Arrange
        NameWithNumbersAndSpecialChars programmeName = new NameWithNumbersAndSpecialChars("Programme");
        Acronym programmeAcronym = new Acronym("PPP");
        ProgrammeID programmeID = new ProgrammeID(programmeName, programmeAcronym);
        SchoolYearID schoolYearID = new SchoolYearID();
        ProgrammeEdition programmeEdition = new ProgrammeEdition(
                new ProgrammeEditionID(programmeID, schoolYearID),
                programmeID,
                schoolYearID
        );

        // Act
        CountStudentsDto dto = assembler.toCountStudentsInProgrammeEditionDTO(programmeEdition);

        // Assert
        assertEquals("PPP", dto.programmeAcronym());
        assertEquals("Programme", dto.programmeName());
        assertEquals(schoolYearID.getSchoolYearID(), dto.schoolYearID());
    }

    @Test
    void shouldThrowExceptionIfProgrammeEditionIsNull() {
        //arrange
        //act + assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            assembler.toCountStudentsInProgrammeEditionDTO(null);
        });
        assertEquals("ProgrammeEdition cannot be null", exception.getMessage());
    }

    @Test
    void shouldReturnAProgrammeEdition() throws Exception {
        // Arrange
        String programmeName = "Programme";
        String programmeAcronym = "PPP";
        UUID schoolYearID = UUID.randomUUID();

        CountStudentsDto dto = new CountStudentsDto(programmeName, programmeAcronym, schoolYearID);

        // Act
        ProgrammeEdition programmeEdition = assembler.CountStudentsInProgrammeEditionDTOtoDomain(dto);

        // Assert
        assertEquals("PPP", programmeEdition.findProgrammeIDInProgrammeEdition().getProgrammeAcronym());
        assertEquals("Programme", programmeEdition.findProgrammeIDInProgrammeEdition().getProgrammeName());
        assertEquals(schoolYearID, programmeEdition.findSchoolYearIDInProgrammeEdition().getSchoolYearID());
    }

    @Test
    void shouldThrowExceptionIfProgrammeEditionDTOIsNull() {
        //arrange
        //act + assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            assembler.CountStudentsInProgrammeEditionDTOtoDomain(null);
        });
        assertEquals("ProgrammeEditionDTO cannot be null", exception.getMessage());
    }

    @Test
    void shouldCreateSchoolYearIdFromProgrammeEditionDTO() {
        // Arrange
        SchoolYearIDDTO schoolYearIDRequestDTO = mock(SchoolYearIDDTO.class);
        ProgrammeEditionDTO dto = mock(ProgrammeEditionDTO.class);
        when(schoolYearIDRequestDTO.id()).thenReturn(UUID.randomUUID().toString());
        when(dto.schoolYear()).thenReturn(schoolYearIDRequestDTO);

        // Act
        SchoolYearID result = assembler.toSchoolYearID(dto);

        // Assert
        assertNotNull(result);
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionDTOIsNull() {
        // Arrange
        SchoolYearIDRequestDTO schoolYearIDRequestDTO = mock(SchoolYearIDRequestDTO.class);
        ProgrammeEditionDTO dto = null;
        when(schoolYearIDRequestDTO.id()).thenReturn(UUID.randomUUID().toString());

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            assembler.toSchoolYearID(dto);
        });
    }

    @Test
    void shouldCreateProgrammeIdFromProgrammeEditionDTO() {
        // Arrange
        ProgrammeIDDTO programmeIDDTO = mock(ProgrammeIDDTO.class);
        ProgrammeEditionDTO dto = mock(ProgrammeEditionDTO.class);
        when(programmeIDDTO.name()).thenReturn("Computer Science");
        when(programmeIDDTO.acronym()).thenReturn("CSE");
        when(dto.programme()).thenReturn(programmeIDDTO);

        // Act
        ProgrammeID result = assembler.toProgrammeID(dto);

        // Assert
        assertNotNull(result);
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionDTOIsNullForToProgramme() {
        // Arrange
        ProgrammeIDDTO programmeIDDTO = mock(ProgrammeIDDTO.class);
        ProgrammeEditionDTO dto = null;
        when(programmeIDDTO.name()).thenReturn("Computer Science");
        when(programmeIDDTO.acronym()).thenReturn("CSE");

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            assembler.toProgrammeID(dto);
        });
    }

    @Test
    void shouldReturnProgrammeEditionDTOWithValidParameters() {
        // Arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        when(programmeID.getProgrammeName()).thenReturn("Computer Science");
        when(programmeID.getProgrammeAcronym()).thenReturn("CSE");
        when(schoolYearID.getSchoolYearID()).thenReturn(UUID.randomUUID());

        // Act
        ProgrammeEditionDTO result = assembler.toDTO(programmeID, schoolYearID);

        // Assert
        assertNotNull(result);
    }

    @Test
    void shouldThrowExceptionWhenProgrammeIdIsNull() {
        // Arrange
        ProgrammeID programmeID = null;
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        when(schoolYearID.getSchoolYearID()).thenReturn(UUID.randomUUID());

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            assembler.toDTO(programmeID,schoolYearID);
        });
    }

    @Test
    void shouldThrowExceptionWhenSchoolYearIdIsNull() {
        // Arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        SchoolYearID schoolYearID = null;
        when(programmeID.getProgrammeName()).thenReturn("Computer Science");
        when(programmeID.getProgrammeAcronym()).thenReturn("CSE");

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            assembler.toDTO(programmeID,schoolYearID);
        });
    }

    @Test
    void shouldReturnProgrammeEditionID() throws Exception {
        // Arrange
        ProgrammeEditionIdDto programmeEditionIdDto = mock(ProgrammeEditionIdDto.class);
        when(programmeEditionIdDto.programmeName()).thenReturn("Computer Science");
        when(programmeEditionIdDto.programmeAcronym()).thenReturn("CSE");
        when(programmeEditionIdDto.schoolYearId()).thenReturn(UUID.randomUUID().toString());
        // Act
        ProgrammeEditionID result = assembler.toProgrammeEditionID(programmeEditionIdDto);
        // Assert
        assertNotNull(result);
    }
}