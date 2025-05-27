package PAI.assembler.programmeEdition;

import PAI.domain.programme.Programme;
import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.programmeEdition.ProgrammeEditionDTO;
import PAI.dto.programmeEdition.ProgrammeEditionRequestDTO;
import PAI.dto.programmeEdition.ProgrammeEditionResponseDTO;
import PAI.dto.schoolYear.SchoolYearDTO;
import PAI.dto.schoolYear.SchoolYearIDRequestDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEditionControllerAssemblerTest {
    private final IProgrammeEditionControllerAssembler assembler = new ProgrammeEditionControllerAssembler();

    @Test
    void shouldReturnProgrammeEditionDTOWhenToDTO(){
        // Arrange
        ProgrammeEditionRequestDTO programmeEditionRequestDTO = mock(ProgrammeEditionRequestDTO.class);
        ProgrammeIDDTO programme = mock(ProgrammeIDDTO.class);
        SchoolYearIDRequestDTO schoolYear = mock(SchoolYearIDRequestDTO.class);

        when(programmeEditionRequestDTO.programme()).thenReturn(programme);
        when(programmeEditionRequestDTO.schoolYear()).thenReturn(schoolYear);

        // Act
        ProgrammeEditionDTO result = assembler.toDTO(programmeEditionRequestDTO);

        // arrange
        assertNotNull(result);
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionRequestDTOIsNull(){
        // Arrange
        ProgrammeEditionRequestDTO programmeEditionRequestDTO = null;

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            assembler.toDTO(programmeEditionRequestDTO);
        });
    }

    @Test
    void shouldReturnProgrammeEditionResponseDTOWhenToResponseDTO(){
        // Arrange
        ProgrammeEditionDTO editionDTO = mock(ProgrammeEditionDTO.class);
        ProgrammeIDDTO programme = mock(ProgrammeIDDTO.class);
        SchoolYearIDRequestDTO schoolYear = mock(SchoolYearIDRequestDTO.class);

        when(editionDTO.programme()).thenReturn(programme);
        when(editionDTO.schoolYear()).thenReturn(schoolYear);

        // Act
        ProgrammeEditionResponseDTO result = assembler.toResponseDTO(editionDTO);

        // arrange
        assertNotNull(result);
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionDTOIsNull(){
        // Arrange
        ProgrammeEditionDTO editionDTO = null;

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            assembler.toResponseDTO(editionDTO);
        });
    }
}