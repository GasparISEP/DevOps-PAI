package PAI.controllerRest;


import PAI.VOs.*;
import PAI.assembler.course.ICourseAssembler;
import PAI.assembler.programmeEdition.IProgrammeEditionControllerAssembler;
import PAI.controllerRest.ProgrammeEditionRestController;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.course.CourseIDDTO;
import PAI.dto.programmeEdition.*;
import PAI.service.programmeEdition.IProgrammeEditionService;
import PAI.service.programmeEdition.ProgrammeEditionService;
import PAI.service.programmeEnrolment.IAvailableCoursesService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class ProgrammeEditionRestControllerTest {

    @Test
    void shouldCreateController() {
        //Arrange
        IProgrammeEditionService service = mock(ProgrammeEditionService.class);
        IProgrammeEditionControllerAssembler controllerAssembler = mock(IProgrammeEditionControllerAssembler.class);
        ICourseAssembler courseAssembler = mock(ICourseAssembler.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);

        //Act
        ProgrammeEditionRestController programmeEditionRestController = new ProgrammeEditionRestController(service, controllerAssembler,availableCoursesService,courseAssembler);
        //Assert
        assertNotNull(programmeEditionRestController);
    }

    @Test
    void shouldThrowExceptionAndNotCreateControllerIfServiceNull() {
        //Arrange
        IProgrammeEditionService service = null;
        IProgrammeEditionControllerAssembler controllerAssembler = mock(IProgrammeEditionControllerAssembler.class);
        ICourseAssembler courseAssembler = mock(ICourseAssembler.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);


        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionRestController(service, controllerAssembler,availableCoursesService,courseAssembler));
    }

    @Test
    void getAllProgrammeEditions_shouldReturnList() throws Exception {
        // Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeEditionControllerAssembler controllerAssembler = mock(IProgrammeEditionControllerAssembler.class);
        ICourseAssembler courseAssembler = mock(ICourseAssembler.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);

        ProgrammeEditionRestController programmeEditionRestController = new ProgrammeEditionRestController(programmeEditionService, controllerAssembler,availableCoursesService,courseAssembler);

        ProgrammeEdition p1 = mock(ProgrammeEdition.class);
        ProgrammeEdition p2 = mock(ProgrammeEdition.class);

        List<ProgrammeEdition> programmeEditionsList = List.of(p1,p2);

        when(programmeEditionService.findAllProgrammeEditions()).thenReturn(programmeEditionsList);

        // Act
        ResponseEntity<List<CountStudentsRequestDto>> response = programmeEditionRestController.getAllProgrammeEditions();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());

        List<CountStudentsRequestDto> resultList = new ArrayList<>();
        response.getBody().forEach(resultList::add);

        assertEquals(2, resultList.size());
    }


    @Test
    void getNumberOfStudents_shouldReturnCorrectCount() throws Exception {
        // Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeEditionControllerAssembler controllerAssembler = mock(IProgrammeEditionControllerAssembler.class);
        ICourseAssembler courseAssembler = mock(ICourseAssembler.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);

        ProgrammeEditionRestController controller = new ProgrammeEditionRestController(programmeEditionService, controllerAssembler,availableCoursesService,courseAssembler);

        String programmeAcronym = "ENG";
        String  schoolYearID = UUID.randomUUID().toString();

        CountStudentsRequestDto expectedDto =
                new CountStudentsRequestDto(programmeAcronym, schoolYearID);

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
        ICourseAssembler courseAssembler = mock(ICourseAssembler.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionRestController(service, controllerAssembler,availableCoursesService,courseAssembler));
    }

    @Test
    void getProgrammeEditionsByProgrammeID_shouldReturnListOfDTOs() throws Exception {
        // Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeEditionControllerAssembler controllerAssembler = mock(IProgrammeEditionControllerAssembler.class);
        ICourseAssembler courseAssembler = mock(ICourseAssembler.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);

        ProgrammeEditionRestController controller = new ProgrammeEditionRestController(programmeEditionService, controllerAssembler,availableCoursesService,courseAssembler);

        String programmeAcronym = "ENG";

        Acronym acronym = new Acronym(programmeAcronym);
        ProgrammeID programmeID = new ProgrammeID(acronym);

        SchoolYearID schoolYearID1 = new SchoolYearID(UUID.randomUUID());
        SchoolYearID schoolYearID2 = new SchoolYearID(UUID.randomUUID());

        ProgrammeEditionID editionID1 = new ProgrammeEditionID(programmeID, schoolYearID1);
        ProgrammeEditionID editionID2 = new ProgrammeEditionID(programmeID, schoolYearID2);

        List<ProgrammeEditionID> editionIDs = List.of(editionID1, editionID2);

        ProgrammeEditionResponseServiceDTO dto1 = new ProgrammeEditionResponseServiceDTO(
                new ProgrammeIDDTO(programmeAcronym),
                schoolYearID1.toString()
        );
        ProgrammeEditionResponseServiceDTO dto2 = new ProgrammeEditionResponseServiceDTO(
                new ProgrammeIDDTO(programmeAcronym),
                schoolYearID2.toString()
        );

        when(programmeEditionService.getProgrammeEditionIDsByProgrammeID(programmeID)).thenReturn(editionIDs);
        when(controllerAssembler.toDTOFromIDs(programmeID, schoolYearID1)).thenReturn(dto1);
        when(controllerAssembler.toDTOFromIDs(programmeID, schoolYearID2)).thenReturn(dto2);

        // Act
        ResponseEntity<List<ProgrammeEditionResponseServiceDTO>> response = controller.getProgrammeEditionsByProgrammeID(programmeAcronym);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        List<ProgrammeEditionResponseServiceDTO> responseBody = response.getBody();
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
        ICourseAssembler courseAssembler = mock(ICourseAssembler.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);

        ProgrammeEditionRestController controller = new ProgrammeEditionRestController(programmeEditionService, controllerAssembler,availableCoursesService,courseAssembler);

        String programmeAcronym = "ENG";

        Acronym acronym = new Acronym(programmeAcronym);
        ProgrammeID programmeID = new ProgrammeID(acronym);

        when(programmeEditionService.getProgrammeEditionIDsByProgrammeID(programmeID)).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<ProgrammeEditionResponseServiceDTO>> response = controller.getProgrammeEditionsByProgrammeID(programmeAcronym);

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
        ICourseAssembler courseAssembler = mock(ICourseAssembler.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);

        ProgrammeEditionRestController controller = new ProgrammeEditionRestController(programmeEditionService, controllerAssembler,availableCoursesService,courseAssembler);

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
        ICourseAssembler courseAssembler = mock(ICourseAssembler.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);

        ProgrammeEditionRestController controller = new ProgrammeEditionRestController(programmeEditionService, controllerAssembler,availableCoursesService,courseAssembler);

        ProgrammeEditionRequestServiceDTO peRequestDTO = mock(ProgrammeEditionRequestServiceDTO.class);
        ProgrammeEditionResponseServiceDTO peServiceResult = mock(ProgrammeEditionResponseServiceDTO.class);
        ProgrammeEditionResponseDTO responseDTO = mock(ProgrammeEditionResponseDTO.class);
        ProgrammeEditionRequestDTO request = mock(ProgrammeEditionRequestDTO.class);

        when(controllerAssembler.toDTO(request)).thenReturn(peRequestDTO);
        when(programmeEditionService.createProgrammeEditionAndSave(peRequestDTO)).thenReturn(peServiceResult);
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
        ICourseAssembler courseAssembler = mock(ICourseAssembler.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);

        ProgrammeEditionRestController controller = new ProgrammeEditionRestController(programmeEditionService, controllerAssembler,availableCoursesService,courseAssembler);

        ProgrammeEditionRequestServiceDTO peDTO = mock(ProgrammeEditionRequestServiceDTO.class);
        ProgrammeEditionResponseServiceDTO peServiceResult = mock(ProgrammeEditionResponseServiceDTO.class);
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

    @Test
    void shouldReturnBadRequestIfInvalidProgrammeEditionIdDto() {
        // arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeEditionControllerAssembler controllerAssembler = mock(IProgrammeEditionControllerAssembler.class);
        ICourseAssembler courseAssembler = mock(ICourseAssembler.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);

        ProgrammeEditionRestController controller = new ProgrammeEditionRestController(
                programmeEditionService, controllerAssembler, availableCoursesService, courseAssembler
        );

        ProgrammeEditionIdDto programmeEditionIdDto = mock(ProgrammeEditionIdDto.class);


        when(programmeEditionIdDto.programmeAcronym()).thenReturn("CS");
        when(programmeEditionIdDto.schoolYearId()).thenReturn(UUID.randomUUID().toString());


        CourseID courseID = mock(CourseID.class);
        List<CourseID> list = List.of(courseID);
        CourseIDDTO courseIDDTO = mock(CourseIDDTO.class);
        List<CourseIDDTO> list1 = List.of(courseIDDTO);


        when(availableCoursesService.getListOfCourseIdForAGivenProgrammeEdition(any())).thenReturn(list);
        when(courseAssembler.toDTOList(list)).thenReturn(list1);

        // act
        ResponseEntity<List<CourseIDDTO>> response = controller.getAvailableCourses(programmeEditionIdDto);

        // assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(list1, response.getBody());
    }

    @Test
    void shouldThrowExceptionAndNotCreateControllerIfAvailableCoursesNull() {

        // arrange
        IProgrammeEditionService service = mock(IProgrammeEditionService.class);
        IProgrammeEditionControllerAssembler controllerAssembler = mock(IProgrammeEditionControllerAssembler.class);
        ICourseAssembler courseAssembler = mock(ICourseAssembler.class);
        IAvailableCoursesService availableCoursesService = null;

        // act + assert
        assertThrows(IllegalArgumentException.class, () ->
                new ProgrammeEditionRestController(service, controllerAssembler, availableCoursesService, courseAssembler)
        );
    }

    @Test
    void shouldThrowExceptionAndNotCreateControllerIfCourseAssemblerNull() {

        // arrange
        IProgrammeEditionService service = mock(IProgrammeEditionService.class);
        IProgrammeEditionControllerAssembler controllerAssembler = mock(IProgrammeEditionControllerAssembler.class);
        ICourseAssembler courseAssembler = null;
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);

        // act + assert
        assertThrows(IllegalArgumentException.class, () ->
                new ProgrammeEditionRestController(service, controllerAssembler, availableCoursesService, courseAssembler)
        );
    }
}