package PAI.controllerRest.CourseInStudyPlanRestControllerTests;

import PAI.VOs.Acronym;
import PAI.VOs.ProgrammeID;
import PAI.VOs.StudyPlanID;
import PAI.assembler.courseInStudyPlan.ICourseInStudyPlanAssembler;
import PAI.assembler.courseInStudyPlan.ICourseInStudyPlanHateoasAssembler;
import PAI.controllerRest.CourseInStudyPlanRestController;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanCommand;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanRequestDTO;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanResponseDTO;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanServiceDTO;
import PAI.service.courseInStudyPlan.IAddCourseToAProgrammeService;
import PAI.service.courseInStudyPlan.ICourseInStudyPlanService;
import PAI.service.studyPlan.IStudyPlanService;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseInStudyPlanRestControllerTest {

    @Test
    void shouldThrowException_WhenAddCourseServiceIsNull() {
        // arrange
        ICourseInStudyPlanAssembler assembler = mock(ICourseInStudyPlanAssembler.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        ICourseInStudyPlanService courseInStudyPlanService = mock(ICourseInStudyPlanService.class);
        ICourseInStudyPlanHateoasAssembler hateoasAssembler = mock(ICourseInStudyPlanHateoasAssembler.class);

        // act & assert
        assertThrows(IllegalArgumentException.class, () ->
                new CourseInStudyPlanRestController(assembler, null, studyPlanService, courseInStudyPlanService, hateoasAssembler));
    }

    @Test
    void shouldThrowException_WhenAssemblerIsNull() {
        // arrange
        IAddCourseToAProgrammeService addCourseService = mock(IAddCourseToAProgrammeService.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        ICourseInStudyPlanService courseInStudyPlanService = mock(ICourseInStudyPlanService.class);
        ICourseInStudyPlanHateoasAssembler hateoasAssembler = mock(ICourseInStudyPlanHateoasAssembler.class);

        // act & assert
        assertThrows(IllegalArgumentException.class, () ->
                new CourseInStudyPlanRestController(null, addCourseService, studyPlanService, courseInStudyPlanService, hateoasAssembler));
    }

    @Test
    void shouldThrowException_WhenStudyPlanServiceIsNull() {
        // arrange
        ICourseInStudyPlanAssembler assembler = mock(ICourseInStudyPlanAssembler.class);
        IAddCourseToAProgrammeService addCourseService = mock(IAddCourseToAProgrammeService.class);
        ICourseInStudyPlanService courseInStudyPlanService = mock(ICourseInStudyPlanService.class);
        ICourseInStudyPlanHateoasAssembler hateoasAssembler = mock(ICourseInStudyPlanHateoasAssembler.class);

        // act & assert
        assertThrows(IllegalArgumentException.class, () ->
                new CourseInStudyPlanRestController(assembler, addCourseService, null, courseInStudyPlanService, hateoasAssembler));
    }

    @Test
    void shouldThrowException_WhenCourseInStudyPlanServiceIsNull() {
        // arrange
        ICourseInStudyPlanAssembler assembler = mock(ICourseInStudyPlanAssembler.class);
        IAddCourseToAProgrammeService addCourseService = mock(IAddCourseToAProgrammeService.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        ICourseInStudyPlanHateoasAssembler hateoasAssembler = mock(ICourseInStudyPlanHateoasAssembler.class);

        // act & assert
        assertThrows(IllegalArgumentException.class, () ->
                new CourseInStudyPlanRestController(assembler, addCourseService, studyPlanService, null, hateoasAssembler));
    }

    @Test
    void shouldThrowException_WhenHateoasAssemblerIsNull() {
        // arrange
        ICourseInStudyPlanAssembler assembler = mock(ICourseInStudyPlanAssembler.class);
        IAddCourseToAProgrammeService addCourseService = mock(IAddCourseToAProgrammeService.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        ICourseInStudyPlanService courseInStudyPlanService = mock(ICourseInStudyPlanService.class);

        // act & assert
        assertThrows(IllegalArgumentException.class, () ->
                new CourseInStudyPlanRestController(assembler, addCourseService, studyPlanService, courseInStudyPlanService, null));
    }

    @Test
    void should_CreateCourseInStudyPlanRestController() {

        // arrange
        IAddCourseToAProgrammeService serviceDouble = mock(IAddCourseToAProgrammeService.class);
        ICourseInStudyPlanAssembler assemblerDouble = mock(ICourseInStudyPlanAssembler.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        ICourseInStudyPlanService courseInStudyPlanService = mock(ICourseInStudyPlanService.class);
        ICourseInStudyPlanHateoasAssembler hateoasAssembler = mock(ICourseInStudyPlanHateoasAssembler.class);

        // act
        CourseInStudyPlanRestController controller = new CourseInStudyPlanRestController(assemblerDouble, serviceDouble, studyPlanService, courseInStudyPlanService, hateoasAssembler);

        // assert
        assertNotNull(controller);
    }

    @Test
    void should_ReturnCreateResponse_WhenCourseInStudyPlanCreatedSucessfully() throws Exception {

        // arrange
        IAddCourseToAProgrammeService serviceDouble = mock(IAddCourseToAProgrammeService.class);
        ICourseInStudyPlanAssembler assemblerDouble = mock(ICourseInStudyPlanAssembler.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        ICourseInStudyPlanService courseInStudyPlanService = mock(ICourseInStudyPlanService.class);
        ICourseInStudyPlanHateoasAssembler hateoasAssembler = mock(ICourseInStudyPlanHateoasAssembler.class);
        CourseInStudyPlanRestController controller = new CourseInStudyPlanRestController(assemblerDouble, serviceDouble, studyPlanService, courseInStudyPlanService, hateoasAssembler);

        CourseInStudyPlanRequestDTO requestDTO = mock(CourseInStudyPlanRequestDTO.class);
        CourseInStudyPlanCommand command = mock(CourseInStudyPlanCommand.class);
        CourseInStudyPlanServiceDTO serviceDTO = mock(CourseInStudyPlanServiceDTO.class);
        CourseInStudyPlanResponseDTO responseDTO = mock(CourseInStudyPlanResponseDTO.class);
        EntityModel<CourseInStudyPlanResponseDTO> entityModel = EntityModel.of(responseDTO);

        when(assemblerDouble.toCommand(requestDTO)).thenReturn(command);
        when(serviceDouble.addCourseToAProgramme(command)).thenReturn(serviceDTO);
        when(assemblerDouble.toDTO(serviceDTO)).thenReturn(responseDTO);
        when(hateoasAssembler.toModel(responseDTO)).thenReturn(entityModel);

        // act
        ResponseEntity<EntityModel<CourseInStudyPlanResponseDTO>> response = controller.create(requestDTO);

        // assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getContent());

    }

    @Test
    void should_ReturnException_WhenCourseInStudyPlanCreationFails() throws Exception {
        // arrange
        IAddCourseToAProgrammeService serviceDouble = mock(IAddCourseToAProgrammeService.class);
        ICourseInStudyPlanAssembler assemblerDouble = mock(ICourseInStudyPlanAssembler.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        ICourseInStudyPlanService courseInStudyPlanService = mock(ICourseInStudyPlanService.class);
        ICourseInStudyPlanHateoasAssembler hateoasAssembler = mock(ICourseInStudyPlanHateoasAssembler.class);

        CourseInStudyPlanRestController controller = new CourseInStudyPlanRestController(assemblerDouble, serviceDouble, studyPlanService, courseInStudyPlanService, hateoasAssembler);

        CourseInStudyPlanRequestDTO requestDTO = mock(CourseInStudyPlanRequestDTO.class);

        when(assemblerDouble.toCommand(requestDTO)).thenThrow(new IllegalArgumentException("Invalid data"));

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> controller.create(requestDTO));
    }

    @Test
    void shouldReturnListOfCourseInStudyPlanResponseDTO_whenValidProgramme() throws Exception {
        // Arrange
        ICourseInStudyPlanAssembler assemblerDouble = mock(ICourseInStudyPlanAssembler.class);
        IAddCourseToAProgrammeService addCourseServiceDouble = mock(IAddCourseToAProgrammeService.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        ICourseInStudyPlanService courseInStudyPlanService = mock(ICourseInStudyPlanService.class);
        ICourseInStudyPlanHateoasAssembler hateoasAssembler = mock(ICourseInStudyPlanHateoasAssembler.class);
        CourseInStudyPlanRestController controller = new CourseInStudyPlanRestController(assemblerDouble, addCourseServiceDouble, studyPlanService, courseInStudyPlanService, hateoasAssembler);

        String acronym = "ENG";

        ProgrammeID programmeID = new ProgrammeID(new Acronym(acronym));
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        when(studyPlanService.getLatestStudyPlanIDByProgrammeID(programmeID)).thenReturn(studyPlanID);

        CourseInStudyPlanServiceDTO serviceDTO1 = mock(CourseInStudyPlanServiceDTO.class);
        CourseInStudyPlanServiceDTO serviceDTO2 = mock(CourseInStudyPlanServiceDTO.class);
        List<CourseInStudyPlanServiceDTO> serviceDTOList = List.of(serviceDTO1, serviceDTO2);

        when(courseInStudyPlanService.getCourseSummariesByStudyPlanID(studyPlanID)).thenReturn(serviceDTOList);

        CourseInStudyPlanResponseDTO dto1 = mock(CourseInStudyPlanResponseDTO.class);
        CourseInStudyPlanResponseDTO dto2 = mock(CourseInStudyPlanResponseDTO.class);

        EntityModel<CourseInStudyPlanResponseDTO> em1 = EntityModel.of(dto1);
        EntityModel<CourseInStudyPlanResponseDTO> em2 = EntityModel.of(dto2);
        List<EntityModel<CourseInStudyPlanResponseDTO>> emList = List.of(em1, em2);
        CollectionModel<EntityModel<CourseInStudyPlanResponseDTO>> collectionModel = CollectionModel.of(emList);

        when(assemblerDouble.toDTO(any())).thenReturn(dto1).thenReturn(dto2);
        when(hateoasAssembler.toCollectionModel(anyIterable())).thenReturn(collectionModel);

        // Act
        ResponseEntity<CollectionModel<EntityModel<CourseInStudyPlanResponseDTO>>> response = controller.getCoursesInStudyPlanByProgrammeID(acronym);

        // Assert
        assertNotNull(response.getBody());
        assertFalse(response.getBody().getContent().isEmpty());
    }

    @Test
    void shouldReturnEmptyList_whenNoCoursesFound() throws Exception {
        // Arrange
        ICourseInStudyPlanAssembler assemblerDouble = mock(ICourseInStudyPlanAssembler.class);
        IAddCourseToAProgrammeService addCourseServiceDouble = mock(IAddCourseToAProgrammeService.class);
        ICourseInStudyPlanHateoasAssembler hateoasAssembler = mock(ICourseInStudyPlanHateoasAssembler.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        ICourseInStudyPlanService courseInStudyPlanService = mock(ICourseInStudyPlanService.class);

        CourseInStudyPlanRestController controller = new CourseInStudyPlanRestController(
                assemblerDouble,
                addCourseServiceDouble,
                studyPlanService,
                courseInStudyPlanService,
                hateoasAssembler
                );

        String acronym = "LAW";
        ProgrammeID programmeID = new ProgrammeID(new Acronym(acronym));
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        when(studyPlanService.getLatestStudyPlanIDByProgrammeID(programmeID)).thenReturn(studyPlanID);
        when(courseInStudyPlanService.getCourseSummariesByStudyPlanID(studyPlanID)).thenReturn(new ArrayList<>());

        when(hateoasAssembler.toCollectionModel(anyList())).thenReturn(CollectionModel.empty());

        // Act
        ResponseEntity<CollectionModel<EntityModel<CourseInStudyPlanResponseDTO>>> response = controller.getCoursesInStudyPlanByProgrammeID(acronym);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getContent().isEmpty());
    }


    @Test
    void shouldThrowException_whenServiceFails() {
        // Arrange
        ICourseInStudyPlanAssembler assemblerDouble = mock(ICourseInStudyPlanAssembler.class);
        IAddCourseToAProgrammeService addCourseServiceDouble = mock(IAddCourseToAProgrammeService.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        ICourseInStudyPlanService courseInStudyPlanService = mock(ICourseInStudyPlanService.class);
        ICourseInStudyPlanHateoasAssembler hateoasAssembler = mock(ICourseInStudyPlanHateoasAssembler.class);
        CourseInStudyPlanRestController controller = new CourseInStudyPlanRestController(assemblerDouble, addCourseServiceDouble, studyPlanService, courseInStudyPlanService, hateoasAssembler);

        String acronym = "MAT";

        ProgrammeID programmeID = new ProgrammeID(new Acronym(acronym));

        when(studyPlanService.getLatestStudyPlanIDByProgrammeID(programmeID))
                .thenThrow(new RuntimeException("Service failure"));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.getCoursesInStudyPlanByProgrammeID(acronym);
        });

        assertEquals("Service failure", exception.getMessage());
    }
}