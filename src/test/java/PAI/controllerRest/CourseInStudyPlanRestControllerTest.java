package PAI.controllerRest;

import PAI.VOs.Acronym;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.VOs.ProgrammeID;
import PAI.VOs.StudyPlanID;
import PAI.assembler.courseInStudyPlan.ICourseInStudyPlanAssembler;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanCommand;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanRequestDTO;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanResponseDTO;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanServiceDTO;
import PAI.service.courseInStudyPlan.IAddCourseToAProgrammeService;
import PAI.service.courseInStudyPlan.ICourseInStudyPlanService;
import PAI.service.studyPlan.IStudyPlanService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseInStudyPlanRestControllerTest {

    @Test
    void should_CreateCourseInStudyPlanRestController() {

        // arrange
        IAddCourseToAProgrammeService serviceDouble = mock(IAddCourseToAProgrammeService.class);
        ICourseInStudyPlanAssembler assemblerDouble = mock(ICourseInStudyPlanAssembler.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        ICourseInStudyPlanService courseInStudyPlanService = mock(ICourseInStudyPlanService.class);

        // act
        CourseInStudyPlanRestController controller = new CourseInStudyPlanRestController(assemblerDouble, serviceDouble, studyPlanService, courseInStudyPlanService);

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
        CourseInStudyPlanRestController controller = new CourseInStudyPlanRestController(assemblerDouble, serviceDouble, studyPlanService, courseInStudyPlanService);

        CourseInStudyPlanRequestDTO requestDTO = mock(CourseInStudyPlanRequestDTO.class);
        CourseInStudyPlanCommand command = mock(CourseInStudyPlanCommand.class);
        CourseInStudyPlanServiceDTO serviceDTO = mock(CourseInStudyPlanServiceDTO.class);
        CourseInStudyPlanResponseDTO responseDTO = mock(CourseInStudyPlanResponseDTO.class);

        when(assemblerDouble.toCommand(requestDTO)).thenReturn(command);
        when(serviceDouble.addCourseToAProgramme(command)).thenReturn(serviceDTO);
        when(assemblerDouble.toDTO(serviceDTO)).thenReturn(responseDTO);

        // act
        ResponseEntity<CourseInStudyPlanResponseDTO> response = controller.create(requestDTO);

        // assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());

    }

    @Test
    void should_ReturnException_WhenCourseInStudyPlanCreationFails() throws Exception {
        // arrange
        IAddCourseToAProgrammeService serviceDouble = mock(IAddCourseToAProgrammeService.class);
        ICourseInStudyPlanAssembler assemblerDouble = mock(ICourseInStudyPlanAssembler.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        ICourseInStudyPlanService courseInStudyPlanService = mock(ICourseInStudyPlanService.class);

        CourseInStudyPlanRestController controller = new CourseInStudyPlanRestController(assemblerDouble, serviceDouble, studyPlanService, courseInStudyPlanService);

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
        CourseInStudyPlanRestController controller = new CourseInStudyPlanRestController(assemblerDouble, addCourseServiceDouble, studyPlanService, courseInStudyPlanService);

        String name = "Engineering";
        String acronym = "ENG";

        ProgrammeID programmeID = new ProgrammeID(new NameWithNumbersAndSpecialChars(name), new Acronym(acronym));
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        when(studyPlanService.getLatestStudyPlanIDByProgrammeID(programmeID)).thenReturn(studyPlanID);

        CourseInStudyPlan course1 = mock(CourseInStudyPlan.class);
        CourseInStudyPlan course2 = mock(CourseInStudyPlan.class);
        List<CourseInStudyPlan> courseList = List.of(course1, course2);

        when(courseInStudyPlanService.getCoursesByStudyPlanId(studyPlanID)).thenReturn(courseList);

        CourseInStudyPlanResponseDTO dto1 = mock(CourseInStudyPlanResponseDTO.class);
        CourseInStudyPlanResponseDTO dto2 = mock(CourseInStudyPlanResponseDTO.class);

        when(assemblerDouble.toDTOFromEntity(course1)).thenReturn(dto1);
        when(assemblerDouble.toDTOFromEntity(course2)).thenReturn(dto2);

        // Act
        ResponseEntity<List<CourseInStudyPlanResponseDTO>> response = controller.getCoursesInStudyPlanByProgrammeID(name, acronym);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertTrue(response.getBody().contains(dto1));
        assertTrue(response.getBody().contains(dto2));
    }

    @Test
    void shouldReturnEmptyList_whenNoCoursesFound() throws Exception {
        // Arrange
        ICourseInStudyPlanAssembler assemblerDouble = mock(ICourseInStudyPlanAssembler.class);
        IAddCourseToAProgrammeService addCourseServiceDouble = mock(IAddCourseToAProgrammeService.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        ICourseInStudyPlanService courseInStudyPlanService = mock(ICourseInStudyPlanService.class);
        CourseInStudyPlanRestController controller = new CourseInStudyPlanRestController(assemblerDouble, addCourseServiceDouble, studyPlanService, courseInStudyPlanService);

        String name = "Law";
        String acronym = "LAW";

        ProgrammeID programmeID = new ProgrammeID(new NameWithNumbersAndSpecialChars(name), new Acronym(acronym));
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        when(studyPlanService.getLatestStudyPlanIDByProgrammeID(programmeID)).thenReturn(studyPlanID);
        when(courseInStudyPlanService.getCoursesByStudyPlanId(studyPlanID)).thenReturn(new ArrayList<>());

        // Act
        ResponseEntity<List<CourseInStudyPlanResponseDTO>> response = controller.getCoursesInStudyPlanByProgrammeID(name, acronym);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
    }

    @Test
    void shouldThrowException_whenServiceFails() {
        // Arrange
        ICourseInStudyPlanAssembler assemblerDouble = mock(ICourseInStudyPlanAssembler.class);
        IAddCourseToAProgrammeService addCourseServiceDouble = mock(IAddCourseToAProgrammeService.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        ICourseInStudyPlanService courseInStudyPlanService = mock(ICourseInStudyPlanService.class);
        CourseInStudyPlanRestController controller = new CourseInStudyPlanRestController(assemblerDouble, addCourseServiceDouble, studyPlanService, courseInStudyPlanService);

        String name = "Math";
        String acronym = "MAT";

        ProgrammeID programmeID = new ProgrammeID(new NameWithNumbersAndSpecialChars(name), new Acronym(acronym));

        when(studyPlanService.getLatestStudyPlanIDByProgrammeID(programmeID))
                .thenThrow(new RuntimeException("Service failure"));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.getCoursesInStudyPlanByProgrammeID(name, acronym);
        });

        assertEquals("Service failure", exception.getMessage());
    }
}