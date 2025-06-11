package PAI.controllerRest.ProgrammeEditionRestControllerTests;


import PAI.VOs.*;
import PAI.assembler.course.ICourseAssembler;
import PAI.assembler.programmeEdition.IProgrammeEditionControllerAssembler;
import PAI.assembler.programmeEdition.IProgrammeEditionHateoasAssembler;
import PAI.controllerRest.ProgrammeEditionRestController;
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
        IProgrammeEditionHateoasAssembler hateoasAssembler = mock(IProgrammeEditionHateoasAssembler.class);

        //Act
        ProgrammeEditionRestController programmeEditionRestController = new ProgrammeEditionRestController(service, controllerAssembler,availableCoursesService,
                courseAssembler, hateoasAssembler);
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
        IProgrammeEditionHateoasAssembler hateoasAssembler = mock(IProgrammeEditionHateoasAssembler.class);


        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionRestController(service, controllerAssembler,availableCoursesService,
                courseAssembler, hateoasAssembler));
    }

    @Test
    void getAllProgrammeEditions_shouldReturnList() throws Exception {
        // Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeEditionControllerAssembler controllerAssembler = mock(IProgrammeEditionControllerAssembler.class);
        ICourseAssembler courseAssembler = mock(ICourseAssembler.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);
        IProgrammeEditionHateoasAssembler hateoasAssembler = mock(IProgrammeEditionHateoasAssembler.class);

        ProgrammeEditionRestController programmeEditionRestController = new ProgrammeEditionRestController(programmeEditionService, controllerAssembler,
                availableCoursesService,courseAssembler, hateoasAssembler);

        ProgrammeEditionResponseServiceDTO p1 = mock(ProgrammeEditionResponseServiceDTO.class);
        ProgrammeEditionResponseServiceDTO p2 = mock(ProgrammeEditionResponseServiceDTO.class);

        List<ProgrammeEditionResponseServiceDTO> programmeEditionsList = List.of(p1,p2);

        when(programmeEditionService.findAllProgrammeEditions()).thenReturn(programmeEditionsList);

        // Act
        ResponseEntity<List<ProgrammeEditionResponseDTO>> response = programmeEditionRestController.getAllProgrammeEditions();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());

        List<ProgrammeEditionResponseDTO> resultList = new ArrayList<>(response.getBody());

        assertEquals(2, resultList.size());
    }


    @Test
    void getNumberOfStudents_shouldReturnCorrectCount() throws Exception {
        // Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeEditionControllerAssembler controllerAssembler = mock(IProgrammeEditionControllerAssembler.class);
        ICourseAssembler courseAssembler = mock(ICourseAssembler.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);
        IProgrammeEditionHateoasAssembler hateoasAssembler = mock(IProgrammeEditionHateoasAssembler.class);

        ProgrammeEditionRestController controller = new ProgrammeEditionRestController(programmeEditionService, controllerAssembler,availableCoursesService,
                courseAssembler, hateoasAssembler);

        String programmeAcronym = "ENG";
        String  schoolYearID = UUID.randomUUID().toString();

        RequestServiceDto expectedDto =
                new RequestServiceDto(programmeAcronym, schoolYearID);

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
        IProgrammeEditionHateoasAssembler hateoasAssembler = mock(IProgrammeEditionHateoasAssembler.class);

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionRestController(service, controllerAssembler,availableCoursesService,
                courseAssembler, hateoasAssembler));
    }

    @Test
    void shouldThrowExceptionAndNotCreateControllerIfHateoasAssemblerNull() {
        //Arrange
        IProgrammeEditionService service = mock(IProgrammeEditionService.class);
        IProgrammeEditionControllerAssembler controllerAssembler = null;
        ICourseAssembler courseAssembler = mock(ICourseAssembler.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);
        IProgrammeEditionHateoasAssembler hateoasAssembler = null;

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionRestController(service, controllerAssembler,availableCoursesService,
                courseAssembler, hateoasAssembler));
    }

    @Test
    void getProgrammeEditionsByProgrammeID_shouldReturnListOfDTOs() throws Exception {
        // Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeEditionControllerAssembler controllerAssembler = mock(IProgrammeEditionControllerAssembler.class);
        ICourseAssembler courseAssembler = mock(ICourseAssembler.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);
        IProgrammeEditionHateoasAssembler hateoasAssembler = mock(IProgrammeEditionHateoasAssembler.class);

        ProgrammeEditionRestController controller = new ProgrammeEditionRestController(
                programmeEditionService, controllerAssembler, availableCoursesService, courseAssembler, hateoasAssembler
        );

        String programmeAcronym = "ENG";
        ProgrammeEditionRequestServiceDTO requestDTO = new ProgrammeEditionRequestServiceDTO(new ProgrammeIDDTO(programmeAcronym));

        ProgrammeEditionResponseServiceDTO serviceDto1 = new ProgrammeEditionResponseServiceDTO(new ProgrammeIDDTO(programmeAcronym), UUID.randomUUID().toString());
        ProgrammeEditionResponseServiceDTO serviceDto2 = new ProgrammeEditionResponseServiceDTO(new ProgrammeIDDTO(programmeAcronym), UUID.randomUUID().toString());
        List<ProgrammeEditionResponseServiceDTO> serviceDtos = List.of(serviceDto1, serviceDto2);

        ProgrammeEditionResponseDTO responseDto1 = new ProgrammeEditionResponseDTO(new ProgrammeIDDTO(programmeAcronym), serviceDto1.schoolYearId());
        ProgrammeEditionResponseDTO responseDto2 = new ProgrammeEditionResponseDTO(new ProgrammeIDDTO(programmeAcronym), serviceDto2.schoolYearId());

        when(programmeEditionService.getProgrammeEditionIDsByProgrammeID(any())).thenReturn(serviceDtos);
        when(controllerAssembler.toResponseDTOFromServiceDTO(serviceDto1)).thenReturn(responseDto1);
        when(controllerAssembler.toResponseDTOFromServiceDTO(serviceDto2)).thenReturn(responseDto2);

        // Act
        ResponseEntity<List<ProgrammeEditionResponseDTO>> response = controller.getProgrammeEditionsByProgrammeID(programmeAcronym);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        List<ProgrammeEditionResponseDTO> responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(2, responseBody.size());
        assertTrue(responseBody.contains(responseDto1));
        assertTrue(responseBody.contains(responseDto2));
    }

    @Test
    void getProgrammeEditionsByProgrammeID_shouldReturnEmptyListIfNoneFound() throws Exception {
        // Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeEditionControllerAssembler controllerAssembler = mock(IProgrammeEditionControllerAssembler.class);
        ICourseAssembler courseAssembler = mock(ICourseAssembler.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);
        IProgrammeEditionHateoasAssembler hateoasAssembler = mock(IProgrammeEditionHateoasAssembler.class);

        ProgrammeEditionRestController controller = new ProgrammeEditionRestController(
                programmeEditionService, controllerAssembler, availableCoursesService, courseAssembler, hateoasAssembler
        );

        String programmeAcronym = "ENG";

        when(programmeEditionService.getProgrammeEditionIDsByProgrammeID(any())).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<ProgrammeEditionResponseDTO>> response = controller.getProgrammeEditionsByProgrammeID(programmeAcronym);

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
        IProgrammeEditionHateoasAssembler hateoasAssembler = mock(IProgrammeEditionHateoasAssembler.class);

        ProgrammeEditionRestController controller = new ProgrammeEditionRestController(
                programmeEditionService, controllerAssembler, availableCoursesService, courseAssembler, hateoasAssembler
        );

        String programmeAcronym = "ENG";

        when(programmeEditionService.getProgrammeEditionIDsByProgrammeID(any()))
                .thenThrow(new RuntimeException("Internal error"));

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
        IProgrammeEditionHateoasAssembler hateoasAssembler = mock(IProgrammeEditionHateoasAssembler.class);

        ProgrammeEditionRestController controller = new ProgrammeEditionRestController(programmeEditionService, controllerAssembler,availableCoursesService,
                courseAssembler, hateoasAssembler);

        ProgrammeEditionRequestServiceDTO peRequestDTO = mock(ProgrammeEditionRequestServiceDTO.class);
        ProgrammeEditionResponseServiceDTO peServiceResult = mock(ProgrammeEditionResponseServiceDTO.class);
        ProgrammeEditionResponseDTO responseDTO = mock(ProgrammeEditionResponseDTO.class);
        ProgrammeEditionRequestDTO request = mock(ProgrammeEditionRequestDTO.class);

        when(controllerAssembler.toServiceDTOFromRequestDTO(request)).thenReturn(peRequestDTO);
        when(programmeEditionService.createProgrammeEditionAndSave(peRequestDTO)).thenReturn(peServiceResult);
        when(controllerAssembler.toResponseDTOFromServiceDTO(peServiceResult)).thenReturn(responseDTO);
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
        IProgrammeEditionHateoasAssembler hateoasAssembler = mock(IProgrammeEditionHateoasAssembler.class);

        ProgrammeEditionRestController controller = new ProgrammeEditionRestController(programmeEditionService, controllerAssembler,availableCoursesService,
                courseAssembler, hateoasAssembler);

        ProgrammeEditionRequestServiceDTO peDTO = mock(ProgrammeEditionRequestServiceDTO.class);
        ProgrammeEditionResponseServiceDTO peServiceResult = mock(ProgrammeEditionResponseServiceDTO.class);
        ProgrammeEditionResponseDTO responseDTO = mock(ProgrammeEditionResponseDTO.class);
        ProgrammeEditionRequestDTO request = mock(ProgrammeEditionRequestDTO.class);

        when(controllerAssembler.toServiceDTOFromRequestDTO(request)).thenReturn(peDTO);
        when(programmeEditionService.createProgrammeEditionAndSave(peDTO)).thenThrow(new IllegalArgumentException("Programme is already Registered"));
        when(controllerAssembler.toResponseDTOFromServiceDTO(peServiceResult)).thenReturn(responseDTO);
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
        IProgrammeEditionHateoasAssembler hateoasAssembler = mock(IProgrammeEditionHateoasAssembler.class);

        ProgrammeEditionRestController controller = new ProgrammeEditionRestController(
                programmeEditionService, controllerAssembler, availableCoursesService, courseAssembler, hateoasAssembler
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
        IProgrammeEditionHateoasAssembler hateoasAssembler = mock(IProgrammeEditionHateoasAssembler.class);

        // act + assert
        assertThrows(IllegalArgumentException.class, () ->
                new ProgrammeEditionRestController(service, controllerAssembler, availableCoursesService, courseAssembler, hateoasAssembler)
        );
    }

    @Test
    void shouldThrowExceptionAndNotCreateControllerIfCourseAssemblerNull() {

        // arrange
        IProgrammeEditionService service = mock(IProgrammeEditionService.class);
        IProgrammeEditionControllerAssembler controllerAssembler = mock(IProgrammeEditionControllerAssembler.class);
        ICourseAssembler courseAssembler = null;
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);
        IProgrammeEditionHateoasAssembler hateoasAssembler = mock(IProgrammeEditionHateoasAssembler.class);

        // act + assert
        assertThrows(IllegalArgumentException.class, () ->
                new ProgrammeEditionRestController(service, controllerAssembler, availableCoursesService, courseAssembler, hateoasAssembler)
        );
    }
}