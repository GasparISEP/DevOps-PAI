package PAI.assembler.programmeEdition;

import PAI.VOs.*;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.dto.programmeEdition.CountStudentsInProgrammeEditionDto;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

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
        CountStudentsInProgrammeEditionDto dto = assembler.toCountStudentsInProgrammeEditionDTO(programmeEdition);

        // Assert
        assertEquals("PPP", dto.programmeAcronym());
        assertEquals("Programme", dto.programmeName());
        assertEquals(schoolYearID.getSchoolYearID(), dto.schoolYearID());
    }
    @Test
    void shouldThrowExceptionIfProgrammeEditionIsNull(){
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

        CountStudentsInProgrammeEditionDto dto= new CountStudentsInProgrammeEditionDto(programmeName,programmeAcronym,schoolYearID);

        // Act
        ProgrammeEdition programmeEdition = assembler.CountStudentsInProgrammeEditionDTOtoDomain(dto);

        // Assert
        assertEquals("PPP", programmeEdition.findProgrammeIDInProgrammeEdition().getProgrammeAcronym());
        assertEquals("Programme",programmeEdition.findProgrammeIDInProgrammeEdition().getProgrammeName());
        assertEquals(schoolYearID, programmeEdition.findSchoolYearIDInProgrammeEdition().getSchoolYearID());
    }
    @Test
    void shouldThrowExceptionIfProgrammeEditionDTOIsNull(){
        //arrange
        //act + assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            assembler.CountStudentsInProgrammeEditionDTOtoDomain(null);
        });
        assertEquals("ProgrammeEditionDTO cannot be null", exception.getMessage());
    }
    @Test
    void shouldReturnProgrammeEditions(){
        // Arrange

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> assembler.toCountStudentsInProgrammeEditionDTOList(null));
    }
    @Test
    void toDTOList_shouldThrowException_whenInputIsNull() {
        //arrange
        //act + assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            assembler.toCountStudentsInProgrammeEditionDTOList(null);
        });
        assertEquals("programmeEditions cannot be null", exception.getMessage());
    }
    @Test
    void toDTOList_shouldReturnEmptyList_whenInputIsEmpty() {
        List<CountStudentsInProgrammeEditionDto> result = assembler.toCountStudentsInProgrammeEditionDTOList(Collections.emptyList());
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    @Test
    void toCountStudentsInProgrammeEditionDTOList_shouldConvertAllElementsCorrectly() throws Exception {
        ProgrammeID programmeID = new ProgrammeID(new NameWithNumbersAndSpecialChars("Engineering"), new Acronym("ENG"));
        SchoolYearID schoolYearID = new SchoolYearID(UUID.randomUUID());
        ProgrammeEditionID id = new ProgrammeEditionID(programmeID, schoolYearID);

        ProgrammeEdition edition = new ProgrammeEdition(id, programmeID, schoolYearID);
        List<ProgrammeEdition> editions = List.of(edition);

        List<CountStudentsInProgrammeEditionDto> result = assembler.toCountStudentsInProgrammeEditionDTOList(editions);

        assertNotNull(result);
        assertEquals(1, result.size());

        CountStudentsInProgrammeEditionDto dto = result.get(0);
        assertEquals("Engineering", dto.programmeName());
        assertEquals("ENG", dto.programmeAcronym());
        assertEquals(schoolYearID,new SchoolYearID(dto.schoolYearID()));
    }


}