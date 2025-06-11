package PAI.assembler.programmeEdition;

import PAI.VOs.*;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.programmeEdition.RequestServiceDto;
import PAI.dto.programmeEdition.ProgrammeEditionRequestServiceDTO;
import PAI.dto.programmeEdition.ProgrammeEditionResponseServiceDTO;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEditionServiceAssemblerImplTest {

    private final ProgrammeEditionServiceAssemblerImpl assembler = new ProgrammeEditionServiceAssemblerImpl();

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
        ProgrammeEditionResponseServiceDTO dto = assembler.toResponseServiceDTOFromProgrammeEdition(programmeEdition);

        // Assert
        assertEquals("PPP", dto.programme().acronym());
        assertEquals(schoolYearID.getSchoolYearID().toString(), dto.schoolYearId());
    }

    @Test
    void shouldThrowExceptionIfProgrammeEditionIsNull() {
        //arrange
        //act + assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            assembler.toResponseServiceDTOFromProgrammeEdition(null);
        });
        assertEquals("ProgrammeEdition cannot be null", exception.getMessage());
    }

    @Test
    void shouldReturnAProgrammeEdition() throws Exception {
        // Arrange
        String programmeAcronym = "PPP";
        String schoolYearID = UUID.randomUUID().toString();

        RequestServiceDto dto = new RequestServiceDto(programmeAcronym, schoolYearID);

        // Act
        ProgrammeEdition programmeEdition = assembler.toProgrammeEditionFromRequestServiceDTO(dto);

        // Assert
        assertEquals("PPP", programmeEdition.findProgrammeIDInProgrammeEdition().getProgrammeAcronym());
        assertEquals(schoolYearID, programmeEdition.findSchoolYearIDInProgrammeEdition().getSchoolYearID().toString());
    }

    @Test
    void shouldThrowExceptionIfProgrammeEditionDTOIsNull() {
        //arrange
        //act + assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            assembler.toProgrammeEditionFromRequestServiceDTO(null);
        });
        assertEquals("ProgrammeEditionDTO cannot be null", exception.getMessage());
    }


    @Test
    void shouldCreateProgrammeIdFromProgrammeEditionDTO() {
        // Arrange
        ProgrammeIDDTO programmeIDDTO = mock(ProgrammeIDDTO.class);
        ProgrammeEditionRequestServiceDTO dto = mock(ProgrammeEditionRequestServiceDTO.class);
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
        ProgrammeEditionRequestServiceDTO dto = null;
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
        when(programmeID.getProgrammeAcronym()).thenReturn("CSE");
        when(schoolYearID.getSchoolYearID()).thenReturn(UUID.randomUUID());

        // Act
        ProgrammeEditionResponseServiceDTO result = assembler.toServiceResponseDTOFromIDs(programmeID, schoolYearID);

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
            assembler.toServiceResponseDTOFromIDs(programmeID,schoolYearID);
        });
    }

    @Test
    void shouldThrowExceptionWhenSchoolYearIdIsNull() {
        // Arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        SchoolYearID schoolYearID = null;
        when(programmeID.getProgrammeAcronym()).thenReturn("CSE");

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            assembler.toServiceResponseDTOFromIDs(programmeID,schoolYearID);
        });
    }

//    @Test
//    void shouldReturnProgrammeEditionID() throws Exception {
//        // Arrange
//        ProgrammeEditionIdDto programmeEditionIdDto = mock(ProgrammeEditionIdDto.class);
//        when(programmeEditionIdDto.programmeName()).thenReturn("Computer Science");
//        when(programmeEditionIdDto.programmeAcronym()).thenReturn("CSE");
//        when(programmeEditionIdDto.schoolYearId()).thenReturn(UUID.randomUUID().toString());
//        // Act
//        ProgrammeEditionID result = assembler.toProgrammeEditionID(programmeEditionIdDto);
//        // Assert
//        assertNotNull(result);
//    }
}