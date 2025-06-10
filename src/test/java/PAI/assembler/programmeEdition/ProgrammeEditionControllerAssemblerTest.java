package PAI.assembler.programmeEdition;

import PAI.VOs.*;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.Programme.ProgrammeIDResponseDTO;
import PAI.dto.programmeEdition.*;
import org.junit.jupiter.api.Test;
import java.util.UUID;
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
    void shouldHandleDifferentProgrammeNamesAndAcronyms() throws Exception {
        Acronym programmeAcronym = new Acronym("ENG25");
        ProgrammeID programmeID = new ProgrammeID(programmeAcronym);
        SchoolYearID schoolYearID = new SchoolYearID();
        ProgrammeEditionGeneratedID programmeEditionGeneratedID = new ProgrammeEditionGeneratedID(UUID.randomUUID());
        ProgrammeEdition programmeEdition = new ProgrammeEdition(
                new ProgrammeEditionID(programmeID, schoolYearID),
                programmeID,
                schoolYearID,
                programmeEditionGeneratedID
        );

        CountStudentsRequestDto dto = assembler.toCountDTO(programmeEdition);

        assertEquals("ENG25", dto.programmeAcronym());
        assertEquals(schoolYearID.getSchoolYearID().toString(), dto.schoolYearID());
    }
    @Test
    void shouldReturnCorrectDtoUsingMockedProgrammeEdition() throws Exception {
        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        ProgrammeID programmeID = new ProgrammeID(
                new Acronym("MOCK")
        );
        SchoolYearID schoolYearID = new SchoolYearID();
        ProgrammeEditionID editionID = new ProgrammeEditionID(programmeID, schoolYearID);

        when(programmeEdition.identity()).thenReturn(editionID);

        CountStudentsRequestDto dto = assembler.toCountDTO(programmeEdition);

        assertEquals("MOCK", dto.programmeAcronym());
        assertEquals(schoolYearID.getSchoolYearID().toString(), dto.schoolYearID());
    }

    @Test
    void shouldReturnAProgrammeEditionDTO() throws Exception {
        // Arrange
        Acronym programmeAcronym = new Acronym("PPP");
        ProgrammeID programmeID = new ProgrammeID(programmeAcronym);
        SchoolYearID schoolYearID = new SchoolYearID();
        ProgrammeEditionGeneratedID programmeEditionGeneratedID = new ProgrammeEditionGeneratedID(UUID.randomUUID());

        ProgrammeEdition programmeEdition = new ProgrammeEdition(
                new ProgrammeEditionID(programmeID, schoolYearID),
                programmeID,
                schoolYearID,
                programmeEditionGeneratedID
        );

        // Act
        CountStudentsRequestDto dto = assembler.toCountDTO(programmeEdition);

        // Assert
        assertEquals("PPP", dto.programmeAcronym());
        assertEquals(schoolYearID.getSchoolYearID().toString(), dto.schoolYearID());
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