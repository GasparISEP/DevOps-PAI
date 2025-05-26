package PAI.controllerRest;


import PAI.dto.programmeEdition.CountStudentsInProgrammeEditionDto;
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

        //Act
        ProgrammeEditionRestController programmeEditionRestController = new ProgrammeEditionRestController(service);
        //Assert
        assertNotNull(programmeEditionRestController);
    }

    @Test
    void shouldThrowExceptionAndNotCreateControllerIfServiceNull() {
        //Arrange
        IProgrammeEditionService service = null;
        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionRestController(service));
    }
@Test
void getAllProgrammeEditions_shouldReturnList(){
        // Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        ProgrammeEditionRestController programmeEditionRestController = new ProgrammeEditionRestController(programmeEditionService);

        CountStudentsInProgrammeEditionDto dto1 = new CountStudentsInProgrammeEditionDto("Engineering", "ENG", UUID.randomUUID());
        CountStudentsInProgrammeEditionDto dto2 = new CountStudentsInProgrammeEditionDto("Law", "LAW", UUID.randomUUID());

        List<CountStudentsInProgrammeEditionDto> programmeEditionsDTOs = List.of(dto1, dto2);

        when(programmeEditionService.getAllProgrammeEditions()).thenReturn(programmeEditionsDTOs);

        // Act
        ResponseEntity<Iterable<CountStudentsInProgrammeEditionDto>> response = programmeEditionRestController.getAllProgrammeEditions();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());

        List<CountStudentsInProgrammeEditionDto> resultList = new ArrayList<>();
        response.getBody().forEach(resultList::add);

        assertEquals(2, resultList.size());
        assertTrue(resultList.contains(dto1));
        assertTrue(resultList.contains(dto2));
    }



    @Test
    void getNumberOfStudents_shouldReturnCorrectCount() throws Exception {
        // Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        ProgrammeEditionRestController controller = new ProgrammeEditionRestController(programmeEditionService);

        String programmeName = "Engineering";
        String programmeAcronym = "ENG";
        UUID schoolYearID = UUID.randomUUID();

        CountStudentsInProgrammeEditionDto expectedDto =
                new CountStudentsInProgrammeEditionDto(programmeName, programmeAcronym, schoolYearID);

        // Mock service behavior
        when(programmeEditionService.countTotalNumberOfStudentsInAProgrammeEdition(expectedDto)).thenReturn(42);

        // Act
        ResponseEntity<Integer> response = controller.getNumberOfStudents(programmeName, programmeAcronym, schoolYearID);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(42, response.getBody());
    }

}