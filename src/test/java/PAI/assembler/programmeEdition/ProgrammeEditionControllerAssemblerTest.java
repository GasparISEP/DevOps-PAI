package PAI.assembler.programmeEdition;

import PAI.VOs.*;
import PAI.domain.programme.Programme;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.domain.schoolYear.SchoolYear;
import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.programmeEdition.*;
import org.junit.jupiter.api.Test;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEditionControllerAssemblerTest {

    private final IProgrammeEditionControllerAssembler assembler = new ProgrammeEditionControllerAssembler();

    @Test
    void shouldReturnProgrammeEditionRequestServiceDTOWhenToServiceDTOFromRequestDTO() {
        // Arrange
        ProgrammeEditionRequestDTO programmeEditionRequestDTO = mock(ProgrammeEditionRequestDTO.class);
        ProgrammeIDDTO programme = mock(ProgrammeIDDTO.class);

        when(programmeEditionRequestDTO.programme()).thenReturn(programme);

        // Act
        ProgrammeEditionRequestServiceDTO result = assembler.toServiceDTOFromRequestDTO(programmeEditionRequestDTO);

        // arrange
        assertNotNull(result);
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionRequestDTOIsNull() {
        // Arrange
        ProgrammeEditionRequestDTO programmeEditionRequestDTO = null;

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            assembler.toServiceDTOFromRequestDTO(programmeEditionRequestDTO);
        });
    }

    @Test
    void shouldReturnProgrammeEditionResponseDTOWhenToResponseDTO() {
        // Arrange
        ProgrammeEditionResponseServiceDTO editionDTO = mock(ProgrammeEditionResponseServiceDTO.class);
        ProgrammeIDDTO programme = mock(ProgrammeIDDTO.class);

        when(editionDTO.programme()).thenReturn(programme);

        // Act
        ProgrammeEditionResponseDTO result = assembler.toResponseDTOFromServiceDTO(editionDTO);

        // arrange
        assertNotNull(result);
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionDTOIsNull() {
        // Arrange
        ProgrammeEditionResponseServiceDTO editionDTO = null;

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            assembler.toResponseDTOFromServiceDTO(editionDTO);
        });
    }

    @Test
    void shouldConvertRequestDTOToServiceDTO() {
        // Arrange
        ProgrammeIDDTO programmeIDDTO = new ProgrammeIDDTO("PPP");
        ProgrammeEditionRequestDTO requestDTO = mock(ProgrammeEditionRequestDTO.class);
        when(requestDTO.programme()).thenReturn(programmeIDDTO);

        // Act
        ProgrammeEditionRequestServiceDTO result = assembler.toServiceDTOFromRequestDTO(requestDTO);

        // Assert
        assertNotNull(result);
        assertEquals("PPP", result.programme().acronym());
    }

    @Test
    void shouldThrowExceptionWhenRequestDTOIsNull() {
        assertThrows(IllegalArgumentException.class, () -> assembler.toServiceDTOFromRequestDTO(null));
    }

    @Test
    void shouldConvertServiceResponseDTOToResponseDTO() {
        // Arrange
        ProgrammeIDDTO programmeIDDTO = new ProgrammeIDDTO("PPP");
        ProgrammeEditionResponseServiceDTO serviceDTO = mock(ProgrammeEditionResponseServiceDTO.class);
        when(serviceDTO.programme()).thenReturn(programmeIDDTO);
        when(serviceDTO.schoolYearId()).thenReturn("2024-2025");

        // Act
        ProgrammeEditionResponseDTO result = assembler.toResponseDTOFromServiceDTO(serviceDTO);

        // Assert
        assertNotNull(result);
        assertEquals("PPP", result.programme().acronym());
        assertEquals("2024-2025", result.schoolYearId());
    }

    @Test
    void shouldThrowExceptionWhenResponseServiceDTOIsNull() {
        assertThrows(IllegalArgumentException.class, () -> assembler.toResponseDTOFromServiceDTO(null));
    }

    @Test
    void shouldReturnEmptyListWhenCallingToResponseDTOList() {
        // Arrange
        ProgrammeEditionResponseServiceDTO serviceDTO = mock(ProgrammeEditionResponseServiceDTO.class);

        // Act
        var result = assembler.toResponseDTOList(serviceDTO);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void toIdDto_shouldConvertValidProgrammeEditionID() throws Exception {
        // Arrange
        Acronym acronym = new Acronym("ISEP");
        ProgrammeID programmeID = new ProgrammeID(acronym);
        SchoolYearID schoolYearID = new SchoolYearID(UUID.randomUUID());

        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);

        // Act
        ProgrammeEditionIdDto dto = assembler.toIdDto(programmeEditionID);

        // Assert
        assertNotNull(dto);
        assertEquals("ISEP", dto.programmeAcronym());
        assertEquals(schoolYearID.getSchoolYearID().toString(), dto.schoolYearId());
    }

    @Test
    void toIdDto_shouldThrowExceptionWhenIdIsNull() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            assembler.toIdDto(null);
        });

        assertEquals("ProgrammeEditionID cannot be null", exception.getMessage());
    }

    @Test
    void whenValidProgrammeAndSchoolYear_thenReturnCorrectDTO() {
        // Arrange
        Programme programme = mock(Programme.class);
        NameWithNumbersAndSpecialChars programmeName = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);

        when(programme.getProgrammeName()).thenReturn(programmeName);
        when(programmeName.getNameWithNumbersAndSpecialChars()).thenReturn("Software");

        when(programme.getAcronym()).thenReturn(acronym);
        when(acronym.getAcronym()).thenReturn("SWD");

        UUID uuid = UUID.randomUUID();
        SchoolYear schoolYear = mock(SchoolYear.class);
        Description description = mock(Description.class);

        when(schoolYear.identity()).thenReturn(new SchoolYearID(uuid));
        when(schoolYear.getDescription()).thenReturn(description);
        when(description.getDescription()).thenReturn("2025");

        ProgrammeEditionControllerAssembler assembler = new ProgrammeEditionControllerAssembler();

        // Act
        ProgrammeEditionWithNameAndDescriptionResponseDTO dto = assembler.toProgrammeEditionIdResponseDto(programme, schoolYear);

        // Assert
        assertEquals("SWD", dto.programmeAcronym());
        assertEquals(uuid.toString(), dto.schoolYearId());
        assertEquals("Software", dto.programmeName());
        assertEquals("2025", dto.description());
    }

    @Test
    void whenProgrammeIsNull_thenThrowIllegalArgumentException() {
        SchoolYear schoolYear = mock(SchoolYear.class);
        ProgrammeEditionControllerAssembler assembler = new ProgrammeEditionControllerAssembler();

        assertThrows(IllegalArgumentException.class, () -> {
            assembler.toProgrammeEditionIdResponseDto(null, schoolYear);
        });
    }

    @Test
    void whenSchoolYearIsNull_thenThrowIllegalArgumentException() {
        Programme programme = mock(Programme.class);
        ProgrammeEditionControllerAssembler assembler = new ProgrammeEditionControllerAssembler();

        assertThrows(IllegalArgumentException.class, () -> {
            assembler.toProgrammeEditionIdResponseDto(programme, null);
        });
    }

    @Test
    void toRequestServiceDtoFromIDs_shouldReturnCorrectDto() {
        // Arrange
        String programmeAcronym = "CS";
        String schoolYearID = "2024";

        // Act
        RequestServiceDto dto = assembler.toRequestServiceDtoFromIDs(programmeAcronym, schoolYearID);

        // Assert
        assertThat(dto).isNotNull();
        assertThat(dto.programmeAcronym()).isEqualTo(programmeAcronym);
        assertThat(dto.schoolYearID()).isEqualTo(schoolYearID);
    }
}