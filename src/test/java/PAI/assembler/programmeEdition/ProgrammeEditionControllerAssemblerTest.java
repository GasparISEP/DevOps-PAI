package PAI.assembler.programmeEdition;

import PAI.VOs.*;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.Programme.ProgrammeIDRequestDTO;
import PAI.dto.programmeEdition.CountStudentsDto;
import PAI.dto.programmeEdition.ProgrammeEditionDTO;
import PAI.dto.programmeEdition.ProgrammeEditionRequestDTO;
import PAI.dto.programmeEdition.ProgrammeEditionResponseDTO;
import PAI.dto.schoolYear.SchoolYearIDDTO;
import PAI.dto.schoolYear.SchoolYearIDRequestDTO;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    @Test
    void shouldHandleDifferentProgrammeNamesAndAcronyms() throws Exception {
        NameWithNumbersAndSpecialChars programmeName = new NameWithNumbersAndSpecialChars("Engenharia@2025");
        Acronym programmeAcronym = new Acronym("ENG25");
        ProgrammeID programmeID = new ProgrammeID(programmeName, programmeAcronym);
        SchoolYearID schoolYearID = new SchoolYearID();
        ProgrammeEdition programmeEdition = new ProgrammeEdition(
                new ProgrammeEditionID(programmeID, schoolYearID),
                programmeID,
                schoolYearID
        );

        CountStudentsDto dto = assembler.toCountDTO(programmeEdition);

        assertEquals("ENG25", dto.programmeAcronym());
        assertEquals("Engenharia@2025", dto.programmeName());
        assertEquals(schoolYearID.getSchoolYearID(), dto.schoolYearID());
    }
    @Test
    void shouldReturnCorrectDtoUsingMockedProgrammeEdition() throws Exception {
        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        ProgrammeID programmeID = new ProgrammeID(
                new NameWithNumbersAndSpecialChars("MockedName"),
                new Acronym("MOCK")
        );
        SchoolYearID schoolYearID = new SchoolYearID();
        ProgrammeEditionID editionID = new ProgrammeEditionID(programmeID, schoolYearID);

        when(programmeEdition.identity()).thenReturn(editionID);

        CountStudentsDto dto = assembler.toCountDTO(programmeEdition);

        assertEquals("MockedName", dto.programmeName());
        assertEquals("MOCK", dto.programmeAcronym());
        assertEquals(schoolYearID.getSchoolYearID(), dto.schoolYearID());
    }

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
        CountStudentsDto dto = assembler.toCountDTO(programmeEdition);

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
            assembler.toCountDTO(null);
        });
        assertEquals("ProgrammeEdition cannot be null", exception.getMessage());
    }

}