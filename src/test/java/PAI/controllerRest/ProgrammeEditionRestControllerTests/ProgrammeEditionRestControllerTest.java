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
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

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

        //arrange
        IProgrammeEditionService service = null;
        IProgrammeEditionControllerAssembler controllerAssembler = mock(IProgrammeEditionControllerAssembler.class);
        ICourseAssembler courseAssembler = mock(ICourseAssembler.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);
        IProgrammeEditionHateoasAssembler hateoasAssembler = mock(IProgrammeEditionHateoasAssembler.class);

        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionRestController(service, controllerAssembler,availableCoursesService,
                courseAssembler, hateoasAssembler));

        // assert
        assertEquals("ProgrammeEditionService cannot be null.", exception.getMessage());
    }

    @Test
    void getAllProgrammeEditions_shouldReturnList() throws Exception {
        // Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeEditionControllerAssembler controllerAssembler = mock(IProgrammeEditionControllerAssembler.class);
        ICourseAssembler courseAssembler = mock(ICourseAssembler.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);
        IProgrammeEditionHateoasAssembler hateoasAssembler = mock(IProgrammeEditionHateoasAssembler.class);

        ProgrammeEditionRestController controller = new ProgrammeEditionRestController(
                programmeEditionService, controllerAssembler, availableCoursesService, courseAssembler, hateoasAssembler);

        ProgrammeEditionResponseServiceDTO p1 = mock(ProgrammeEditionResponseServiceDTO.class);
        ProgrammeEditionResponseServiceDTO p2 = mock(ProgrammeEditionResponseServiceDTO.class);

        ProgrammeEditionResponseDTO dto1 = mock(ProgrammeEditionResponseDTO.class);
        ProgrammeEditionResponseDTO dto2 = mock(ProgrammeEditionResponseDTO.class);

        when(programmeEditionService.findAllProgrammeEditions()).thenReturn(List.of(p1, p2));

        when(controllerAssembler.toResponseDTOFromServiceDTO(p1)).thenReturn(dto1);
        when(controllerAssembler.toResponseDTOFromServiceDTO(p2)).thenReturn(dto2);

        EntityModel<ProgrammeEditionResponseDTO> model1 = EntityModel.of(dto1);
        EntityModel<ProgrammeEditionResponseDTO> model2 = EntityModel.of(dto2);

        when(hateoasAssembler.toModel(dto1)).thenReturn(model1);
        when(hateoasAssembler.toModel(dto2)).thenReturn(model2);

        // Act
        ResponseEntity<List<EntityModel<ProgrammeEditionResponseDTO>>> response = controller.getAllProgrammeEditions();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
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

        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionRestController(service, controllerAssembler,availableCoursesService,
                courseAssembler, hateoasAssembler));
        // assert
        assertEquals("ProgrammeEditionControllerAssembler cannot be null.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionAndNotCreateControllerIfHateoasAssemblerNull() {

        //arrange
        IProgrammeEditionService service = mock(IProgrammeEditionService.class);
        IProgrammeEditionControllerAssembler controllerAssembler = null;
        ICourseAssembler courseAssembler = mock(ICourseAssembler.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);
        IProgrammeEditionHateoasAssembler hateoasAssembler = null;

        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionRestController(service, controllerAssembler,availableCoursesService,
                courseAssembler, hateoasAssembler));

        // assert
        assertEquals("ProgrammeEditionControllerAssembler cannot be null.", exception.getMessage());
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

        ProgrammeEditionResponseServiceDTO serviceDto1 = new ProgrammeEditionResponseServiceDTO(new ProgrammeIDDTO(programmeAcronym), UUID.randomUUID().toString());
        ProgrammeEditionResponseServiceDTO serviceDto2 = new ProgrammeEditionResponseServiceDTO(new ProgrammeIDDTO(programmeAcronym), UUID.randomUUID().toString());
        List<ProgrammeEditionResponseServiceDTO> serviceDtos = List.of(serviceDto1, serviceDto2);

        ProgrammeEditionResponseDTO responseDto1 = new ProgrammeEditionResponseDTO(new ProgrammeIDDTO(programmeAcronym), serviceDto1.schoolYearId());
        ProgrammeEditionResponseDTO responseDto2 = new ProgrammeEditionResponseDTO(new ProgrammeIDDTO(programmeAcronym), serviceDto2.schoolYearId());

        when(programmeEditionService.getProgrammeEditionIDsByProgrammeID(any())).thenReturn(serviceDtos);
        when(controllerAssembler.toResponseDTOFromServiceDTO(serviceDto1)).thenReturn(responseDto1);
        when(controllerAssembler.toResponseDTOFromServiceDTO(serviceDto2)).thenReturn(responseDto2);

        EntityModel<ProgrammeEditionResponseDTO> entityModel1 = EntityModel.of(responseDto1);
        EntityModel<ProgrammeEditionResponseDTO> entityModel2 = EntityModel.of(responseDto2);

        when(hateoasAssembler.toModel(responseDto1)).thenReturn(entityModel1);
        when(hateoasAssembler.toModel(responseDto2)).thenReturn(entityModel2);

        // Act
        ResponseEntity<List<EntityModel<ProgrammeEditionResponseDTO>>> response = controller.getProgrammeEditionsByProgrammeID(programmeAcronym);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertTrue(response.getBody().contains(entityModel1));
        assertTrue(response.getBody().contains(entityModel2));
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

        when(hateoasAssembler.toModel(any())).thenThrow(new IllegalArgumentException("Should not be called"));

        // Act
        ResponseEntity<List<EntityModel<ProgrammeEditionResponseDTO>>> response = controller.getProgrammeEditionsByProgrammeID(programmeAcronym);

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
    void shouldReturnOkWithListOfCoursesForValidProgrammeEditionIdDto() {
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
    void shouldReturnBadRequestWhensSchoolYearIdIsInvalid() {
        // Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeEditionControllerAssembler controllerAssembler = mock(IProgrammeEditionControllerAssembler.class);
        ICourseAssembler courseAssembler = mock(ICourseAssembler.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);
        IProgrammeEditionHateoasAssembler hateoasAssembler = mock(IProgrammeEditionHateoasAssembler.class);

        ProgrammeEditionRestController controller = new ProgrammeEditionRestController(
                programmeEditionService, controllerAssembler, availableCoursesService, courseAssembler, hateoasAssembler
        );

        ProgrammeEditionIdDto invalidDto = new ProgrammeEditionIdDto("LEI", "not-a-valid-uuid");

        // Act
        ResponseEntity<List<CourseIDDTO>> response = controller.getAvailableCourses(invalidDto);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }


    @Test
    void shouldThrowExceptionAndNotCreateControllerIfAvailableCoursesNull() {

        // arrange
        IProgrammeEditionService service = mock(IProgrammeEditionService.class);
        IProgrammeEditionControllerAssembler controllerAssembler = mock(IProgrammeEditionControllerAssembler.class);
        ICourseAssembler courseAssembler = mock(ICourseAssembler.class);
        IAvailableCoursesService availableCoursesService = null;
        IProgrammeEditionHateoasAssembler hateoasAssembler = mock(IProgrammeEditionHateoasAssembler.class);

        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new ProgrammeEditionRestController(service, controllerAssembler, availableCoursesService, courseAssembler, hateoasAssembler)
        );

        // assert
        assertEquals("AvailableCoursesService cannot be null.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionAndNotCreateControllerIfCourseAssemblerNull() {

        // arrange
        IProgrammeEditionService service = mock(IProgrammeEditionService.class);
        IProgrammeEditionControllerAssembler controllerAssembler = mock(IProgrammeEditionControllerAssembler.class);
        ICourseAssembler courseAssembler = null;
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);
        IProgrammeEditionHateoasAssembler hateoasAssembler = mock(IProgrammeEditionHateoasAssembler.class);

        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new ProgrammeEditionRestController(service, controllerAssembler, availableCoursesService, courseAssembler, hateoasAssembler)
        );

        // assert
        assertEquals("CourseAssembler cannot be null.", exception.getMessage());
    }
}