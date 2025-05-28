package PAI.assembler.programmeEdition;

import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.Programme.ProgrammeIDRequestDTO;
import PAI.dto.programmeEdition.ProgrammeEditionDTO;
import PAI.dto.programmeEdition.ProgrammeEditionRequestDTO;
import PAI.dto.programmeEdition.ProgrammeEditionResponseDTO;
import PAI.dto.schoolYear.SchoolYearIDDTO;
import PAI.dto.schoolYear.SchoolYearIDRequestDTO;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEditionControllerAssemblerTest {
    private final IProgrammeEditionControllerAssembler assembler = new ProgrammeEditionControllerAssembler();

    @Test
    void shouldReturnProgrammeEditionDTOWhenToDTO() {
        // Arrange
        ProgrammeEditionRequestDTO programmeEditionRequestDTO = mock(ProgrammeEditionRequestDTO.class);
        ProgrammeIDRequestDTO programme = mock(ProgrammeIDRequestDTO.class);
        SchoolYearIDRequestDTO schoolYear = mock(SchoolYearIDRequestDTO.class);

        when(programmeEditionRequestDTO.programme()).thenReturn(programme);
        when(programmeEditionRequestDTO.schoolYear()).thenReturn(schoolYear);

        // Act
        ProgrammeEditionDTO result = assembler.toDTO(programmeEditionRequestDTO);

        // arrange
        assertNotNull(result);
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionRequestDTOIsNull() {
        // Arrange
        ProgrammeEditionRequestDTO programmeEditionRequestDTO = null;

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            assembler.toDTO(programmeEditionRequestDTO);
        });
    }

    @Test
    void shouldReturnProgrammeEditionResponseDTOWhenToResponseDTO() {
        // Arrange
        ProgrammeEditionDTO editionDTO = mock(ProgrammeEditionDTO.class);
        ProgrammeIDDTO programme = mock(ProgrammeIDDTO.class);
        SchoolYearIDDTO schoolYear = mock(SchoolYearIDDTO.class);

        when(editionDTO.programme()).thenReturn(programme);
        when(editionDTO.schoolYear()).thenReturn(schoolYear);

        // Act
        ProgrammeEditionResponseDTO result = assembler.toResponseDTO(editionDTO);

        // arrange
        assertNotNull(result);
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionDTOIsNull() {
        // Arrange
        ProgrammeEditionDTO editionDTO = null;

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            assembler.toResponseDTO(editionDTO);
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
        ProgrammeEditionDTO result = assembler.toDTOFromIDs(programmeID, schoolYearID);

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
            assembler.toDTOFromIDs(programmeID,schoolYearID);
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
            assembler.toDTOFromIDs(programmeID,schoolYearID);
        });
    }
}