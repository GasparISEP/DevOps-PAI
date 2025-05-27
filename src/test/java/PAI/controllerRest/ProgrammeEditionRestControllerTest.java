package PAI.controllerRest;


import PAI.VOs.*;
import PAI.assembler.programmeEdition.IProgrammeEditionAssembler;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.dto.programmeEdition.CountStudentsDto;
import PAI.dto.programmeEdition.ProgrammeEditionDTO;
import PAI.service.programmeEdition.IProgrammeEditionService;
import PAI.service.programmeEdition.ProgrammeEditionService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class ProgrammeEditionRestControllerTest {

    @Test
    void shouldCreateController() {
        //Arrange
        IProgrammeEditionService service = mock(ProgrammeEditionService.class);
        IProgrammeEditionAssembler assembler = mock(IProgrammeEditionAssembler.class);

        //Act
        ProgrammeEditionRestController programmeEditionRestController = new ProgrammeEditionRestController(service, assembler);
        //Assert
        assertNotNull(programmeEditionRestController);
    }

    @Test
    void shouldThrowExceptionAndNotCreateControllerIfServiceNull() {
        //Arrange
        IProgrammeEditionService service = null;
        IProgrammeEditionAssembler assembler = mock(IProgrammeEditionAssembler.class);

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionRestController(service, assembler));
    }
@Test
void getAllProgrammeEditions_shouldReturnList(){
        // Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeEditionAssembler assembler = mock(IProgrammeEditionAssembler.class);
        ProgrammeEditionRestController programmeEditionRestController = new ProgrammeEditionRestController(programmeEditionService, assembler);

        CountStudentsDto dto1 = new CountStudentsDto("Engineering", "ENG", UUID.randomUUID());
        CountStudentsDto dto2 = new CountStudentsDto("Law", "LAW", UUID.randomUUID());

        List<CountStudentsDto> programmeEditionsDTOs = List.of(dto1, dto2);

        when(programmeEditionService.getAllProgrammeEditions()).thenReturn(programmeEditionsDTOs);

        // Act
        ResponseEntity<Iterable<CountStudentsDto>> response = programmeEditionRestController.getAllProgrammeEditions();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());

        List<CountStudentsDto> resultList = new ArrayList<>();
        response.getBody().forEach(resultList::add);

        assertEquals(2, resultList.size());
        assertTrue(resultList.contains(dto1));
        assertTrue(resultList.contains(dto2));
    }



    @Test
    void getNumberOfStudents_shouldReturnCorrectCount() throws Exception {
        // Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeEditionAssembler assembler = mock(IProgrammeEditionAssembler.class);
        ProgrammeEditionRestController controller = new ProgrammeEditionRestController(programmeEditionService, assembler);

        String programmeName = "Engineering";
        String programmeAcronym = "ENG";
        UUID schoolYearID = UUID.randomUUID();

        CountStudentsDto expectedDto =
                new CountStudentsDto(programmeName, programmeAcronym, schoolYearID);

        // Mock service behavior
        when(programmeEditionService.countTotalNumberOfStudentsInAProgrammeEdition(expectedDto)).thenReturn(42);

        // Act
        ResponseEntity<Integer> response = controller.getNumberOfStudents(programmeName, programmeAcronym, schoolYearID);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(42, response.getBody());
    }

    @Test
    void shouldThrowExceptionAndNotCreateControllerIfAssemblerNull() {
        //Arrange
        IProgrammeEditionService service = mock(IProgrammeEditionService.class);
        IProgrammeEditionAssembler assembler = null;
        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionRestController(service, assembler));
    }

    @Test
    void getProgrammeEditionsByProgrammeID_shouldReturnListOfDTOs() throws Exception {
        // Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeEditionAssembler programmeEditionAssembler = mock(IProgrammeEditionAssembler.class);
        ProgrammeEditionRestController controller = new ProgrammeEditionRestController(programmeEditionService, programmeEditionAssembler);

        String programmeName = "Engineering";
        String programmeAcronym = "ENG";

        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars(programmeName);
        Acronym acronym = new Acronym(programmeAcronym);
        ProgrammeID programmeID = new ProgrammeID(name, acronym);

        SchoolYearID schoolYearID1 = new SchoolYearID(UUID.randomUUID());
        SchoolYearID schoolYearID2 = new SchoolYearID(UUID.randomUUID());

        ProgrammeEdition edition1 = new ProgrammeEdition(new ProgrammeEditionID(programmeID, schoolYearID1), programmeID, schoolYearID1);
        ProgrammeEdition edition2 = new ProgrammeEdition(new ProgrammeEditionID(programmeID, schoolYearID2), programmeID, schoolYearID2);

        List<ProgrammeEdition> editions = List.of(edition1, edition2);

        ProgrammeEditionDTO dto1 = new ProgrammeEditionDTO(
                new PAI.dto.Programme.ProgrammeIDDTO(programmeName, programmeAcronym),
                new PAI.dto.schoolYear.SchoolYearIDRequestDTO(schoolYearID1.getSchoolYearID().toString())
        );
        ProgrammeEditionDTO dto2 = new ProgrammeEditionDTO(
                new PAI.dto.Programme.ProgrammeIDDTO(programmeName, programmeAcronym),
                new PAI.dto.schoolYear.SchoolYearIDRequestDTO(schoolYearID2.getSchoolYearID().toString())
        );

        when(programmeEditionService.getProgrammeEditionsByProgrammeID(programmeID)).thenReturn(editions);
        when(programmeEditionAssembler.toDTO(programmeID, schoolYearID1)).thenReturn(dto1);
        when(programmeEditionAssembler.toDTO(programmeID, schoolYearID2)).thenReturn(dto2);

        // Act
        ResponseEntity<List<ProgrammeEditionDTO>> response = controller.getProgrammeEditionsByProgrammeID(programmeName, programmeAcronym);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        List<ProgrammeEditionDTO> responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(2, responseBody.size());
        assertTrue(responseBody.contains(dto1));
        assertTrue(responseBody.contains(dto2));
    }

    @Test
    void getProgrammeEditionsByProgrammeID_shouldReturnEmptyListIfNoneFound() throws Exception {
        // Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeEditionAssembler programmeEditionAssembler = mock(IProgrammeEditionAssembler.class);
        ProgrammeEditionRestController controller = new ProgrammeEditionRestController(programmeEditionService, programmeEditionAssembler);

        String programmeName = "Engineering";
        String programmeAcronym = "ENG";

        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars(programmeName);
        Acronym acronym = new Acronym(programmeAcronym);
        ProgrammeID programmeID = new ProgrammeID(name, acronym);

        when(programmeEditionService.getProgrammeEditionsByProgrammeID(programmeID)).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<ProgrammeEditionDTO>> response = controller.getProgrammeEditionsByProgrammeID(programmeName, programmeAcronym);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
    }

    @Test
    void getProgrammeEditionsByProgrammeID_shouldThrowExceptionIfServiceFails() throws Exception {
        // Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeEditionAssembler programmeEditionAssembler = mock(IProgrammeEditionAssembler.class);
        ProgrammeEditionRestController controller = new ProgrammeEditionRestController(programmeEditionService, programmeEditionAssembler);

        String programmeName = "Engineering";
        String programmeAcronym = "ENG";

        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars(programmeName);
        Acronym acronym = new Acronym(programmeAcronym);
        ProgrammeID programmeID = new ProgrammeID(name, acronym);

        when(programmeEditionService.getProgrammeEditionsByProgrammeID(programmeID)).thenThrow(new RuntimeException("Internal error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> controller.getProgrammeEditionsByProgrammeID(programmeName, programmeAcronym));
    }

    @Test
    void getProgrammeEditionsByProgrammeID_shouldThrowExceptionIfInvalidValueObject() {
        // Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeEditionAssembler programmeEditionAssembler = mock(IProgrammeEditionAssembler.class);
        ProgrammeEditionRestController controller = new ProgrammeEditionRestController(programmeEditionService, programmeEditionAssembler);

        String invalidProgrammeName = "";
        String validAcronym = "ENG";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> controller.getProgrammeEditionsByProgrammeID(invalidProgrammeName, validAcronym));
    }
}