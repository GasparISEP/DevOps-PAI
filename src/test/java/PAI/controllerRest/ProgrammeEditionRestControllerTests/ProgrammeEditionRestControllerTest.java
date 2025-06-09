package PAI.controllerRest.ProgrammeEditionRestControllerTests;


import PAI.VOs.*;
import PAI.assembler.programmeEdition.IProgrammeEditionControllerAssembler;
import PAI.controllerRest.ProgrammeEditionRestController;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.dto.programmeEdition.CountStudentsDto;
import PAI.dto.programmeEdition.ProgrammeEditionServiceDTO;
import PAI.dto.programmeEdition.ProgrammeEditionRequestDTO;
import PAI.dto.programmeEdition.ProgrammeEditionResponseDTO;
import PAI.service.programmeEdition.IProgrammeEditionService;
import PAI.service.programmeEdition.ProgrammeEditionService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class ProgrammeEditionRestControllerTest {

    @Test
    void shouldCreateController() {
        //Arrange
        IProgrammeEditionService service = mock(ProgrammeEditionService.class);
        IProgrammeEditionControllerAssembler controllerAssembler = mock(IProgrammeEditionControllerAssembler.class);

        //Act
        ProgrammeEditionRestController programmeEditionRestController = new ProgrammeEditionRestController(service, controllerAssembler);
        //Assert
        assertNotNull(programmeEditionRestController);
    }

    @Test
    void shouldThrowExceptionAndNotCreateControllerIfServiceNull() {
        //Arrange
        IProgrammeEditionService service = null;
        IProgrammeEditionControllerAssembler controllerAssembler = mock(IProgrammeEditionControllerAssembler.class);


        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionRestController(service, controllerAssembler));
    }

    @Test
    void getAllProgrammeEditions_shouldReturnList() throws Exception {
        // Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeEditionControllerAssembler controllerAssembler = mock(IProgrammeEditionControllerAssembler.class);

        ProgrammeEditionRestController programmeEditionRestController = new ProgrammeEditionRestController(programmeEditionService, controllerAssembler);

        ProgrammeEdition p1 = mock(ProgrammeEdition.class);
        ProgrammeEdition p2 = mock(ProgrammeEdition.class);

        List<ProgrammeEdition> programmeEditionsList = List.of(p1,p2);

        when(programmeEditionService.findAllProgrammeEditions()).thenReturn(programmeEditionsList);

        // Act
        ResponseEntity<List<CountStudentsDto>> response = programmeEditionRestController.getAllProgrammeEditions();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());

        List<CountStudentsDto> resultList = new ArrayList<>();
        response.getBody().forEach(resultList::add);

        assertEquals(2, resultList.size());
    }


    @Test
    void getNumberOfStudents_shouldReturnCorrectCount() throws Exception {
        // Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeEditionControllerAssembler controllerAssembler = mock(IProgrammeEditionControllerAssembler.class);

        ProgrammeEditionRestController controller = new ProgrammeEditionRestController(programmeEditionService, controllerAssembler);

        String programmeAcronym = "ENG";
        String  schoolYearID = UUID.randomUUID().toString();

        CountStudentsDto expectedDto =
                new CountStudentsDto(programmeAcronym, schoolYearID);

        // Mock service behavior
        when(programmeEditionService.countTotalNumberOfStudentsInAProgrammeEdition(expectedDto)).thenReturn(4);

        // Act
        ResponseEntity<Integer> response = controller.getNumberOfStudents(programmeAcronym, schoolYearID);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(4, response.getBody());
    }

    @Test
    void shouldThrowExceptionAndNotCreateControllerIfAssemblerNull() {
        //Arrange
        IProgrammeEditionService service = mock(IProgrammeEditionService.class);
        IProgrammeEditionControllerAssembler controllerAssembler = null;

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionRestController(service, controllerAssembler));
    }

    @Test
    void shouldThrowExceptionAndNotCreateControllerIfControllerAssemblerNull() {
        //Arrange
        IProgrammeEditionService service = mock(IProgrammeEditionService.class);
        IProgrammeEditionControllerAssembler controllerAssembler = null;

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionRestController(service, controllerAssembler));
    }

    @Test
    void getProgrammeEditionsByProgrammeID_shouldReturnListOfDTOs() throws Exception {
        // Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeEditionControllerAssembler controllerAssembler = mock(IProgrammeEditionControllerAssembler.class);

        ProgrammeEditionRestController controller = new ProgrammeEditionRestController(programmeEditionService, controllerAssembler);

        String programmeAcronym = "ENG";

        Acronym acronym = new Acronym(programmeAcronym);
        ProgrammeID programmeID = new ProgrammeID(acronym);

        SchoolYearID schoolYearID1 = new SchoolYearID(UUID.randomUUID());
        SchoolYearID schoolYearID2 = new SchoolYearID(UUID.randomUUID());

        ProgrammeEditionID editionID1 = new ProgrammeEditionID(programmeID, schoolYearID1);
        ProgrammeEditionID editionID2 = new ProgrammeEditionID(programmeID, schoolYearID2);

        List<ProgrammeEditionID> editionIDs = List.of(editionID1, editionID2);

        ProgrammeEditionServiceDTO dto1 = new ProgrammeEditionServiceDTO(
                new PAI.dto.Programme.ProgrammeIDDTO(programmeAcronym),
                new PAI.dto.schoolYear.SchoolYearIDDTO(schoolYearID1.getSchoolYearID().toString())
        );
        ProgrammeEditionServiceDTO dto2 = new ProgrammeEditionServiceDTO(
                new PAI.dto.Programme.ProgrammeIDDTO(programmeAcronym),
                new PAI.dto.schoolYear.SchoolYearIDDTO(schoolYearID2.getSchoolYearID().toString())
        );

        when(programmeEditionService.getProgrammeEditionIDsByProgrammeID(programmeID)).thenReturn(editionIDs);
        when(controllerAssembler.toDTOFromIDs(programmeID, schoolYearID1)).thenReturn(dto1);
        when(controllerAssembler.toDTOFromIDs(programmeID, schoolYearID2)).thenReturn(dto2);

        // Act
        ResponseEntity<List<ProgrammeEditionServiceDTO>> response = controller.getProgrammeEditionsByProgrammeID(programmeAcronym);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        List<ProgrammeEditionServiceDTO> responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(2, responseBody.size());
        assertTrue(responseBody.contains(dto1));
        assertTrue(responseBody.contains(dto2));
    }


    @Test
    void getProgrammeEditionsByProgrammeID_shouldReturnEmptyListIfNoneFound() throws Exception {
        // Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeEditionControllerAssembler controllerAssembler = mock(IProgrammeEditionControllerAssembler.class);

        ProgrammeEditionRestController controller = new ProgrammeEditionRestController(programmeEditionService, controllerAssembler);

        String programmeAcronym = "ENG";

        Acronym acronym = new Acronym(programmeAcronym);
        ProgrammeID programmeID = new ProgrammeID(acronym);

        when(programmeEditionService.getProgrammeEditionIDsByProgrammeID(programmeID)).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<ProgrammeEditionServiceDTO>> response = controller.getProgrammeEditionsByProgrammeID(programmeAcronym);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
    }


    @Test
    void getProgrammeEditionsByProgrammeID_shouldThrowExceptionIfServiceFails() throws Exception {
        // Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeEditionControllerAssembler controllerAssembler = mock(IProgrammeEditionControllerAssembler.class);

        ProgrammeEditionRestController controller = new ProgrammeEditionRestController(programmeEditionService, controllerAssembler);

        String programmeAcronym = "ENG";

        Acronym acronym = new Acronym(programmeAcronym);
        ProgrammeID programmeID = new ProgrammeID(acronym);

        when(programmeEditionService.getProgrammeEditionIDsByProgrammeID(programmeID)).thenThrow(new RuntimeException("Internal error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> controller.getProgrammeEditionsByProgrammeID(programmeAcronym));
    }


    @Test
    void shouldCreateAProgrammeEditionForTheCurrentSchoolYear() throws Exception {
        // Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeEditionControllerAssembler controllerAssembler = mock(IProgrammeEditionControllerAssembler.class);

        ProgrammeEditionRestController controller = new ProgrammeEditionRestController(programmeEditionService, controllerAssembler);

        ProgrammeEditionServiceDTO peDTO = mock(ProgrammeEditionServiceDTO.class);
        ProgrammeEditionServiceDTO peServiceResult = mock(ProgrammeEditionServiceDTO.class);
        ProgrammeEditionResponseDTO responseDTO = mock(ProgrammeEditionResponseDTO.class);
        ProgrammeEditionRequestDTO request = mock(ProgrammeEditionRequestDTO.class);

        when(controllerAssembler.toDTO(request)).thenReturn(peDTO);
        when(programmeEditionService.createProgrammeEditionAndSave(peDTO)).thenReturn(peServiceResult);
        when(controllerAssembler.toResponseDTO(peServiceResult)).thenReturn(responseDTO);
        // Act
        ResponseEntity<?> response = controller.createAProgrammeEditionForTheCurrentSchoolYear(request);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void shouldReturnBadRequestIfInvalidRequestDTO() throws Exception {
        // Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeEditionControllerAssembler controllerAssembler = mock(IProgrammeEditionControllerAssembler.class);

        ProgrammeEditionRestController controller = new ProgrammeEditionRestController(programmeEditionService, controllerAssembler);

        ProgrammeEditionServiceDTO peDTO = mock(ProgrammeEditionServiceDTO.class);
        ProgrammeEditionServiceDTO peServiceResult = mock(ProgrammeEditionServiceDTO.class);
        ProgrammeEditionResponseDTO responseDTO = mock(ProgrammeEditionResponseDTO.class);
        ProgrammeEditionRequestDTO request = mock(ProgrammeEditionRequestDTO.class);

        when(controllerAssembler.toDTO(request)).thenReturn(peDTO);
        when(programmeEditionService.createProgrammeEditionAndSave(peDTO)).thenThrow(new IllegalArgumentException("Programme is already Registered"));
        when(controllerAssembler.toResponseDTO(peServiceResult)).thenReturn(responseDTO);
        // Act
        ResponseEntity<?> response = controller.createAProgrammeEditionForTheCurrentSchoolYear(request);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Programme is already Registered", response.getBody());
    }
}