package PAI.controllerRest;

import PAI.assembler.courseInStudyPlan.CourseInStudyPlanAssemblerImpl;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanCommand;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanRequestDTO;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanResponseDTO;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanServiceDTO;
import PAI.service.courseInStudyPlan.CourseInStudyPlanServiceImpl;
import PAI.service.courseInStudyPlan.IAddCourseToAProgrammeService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseInStudyPlanRestControllerTest {

    @Test
    void should_CreateCourseInStudyPlanRestController() {

        // arrange
        IAddCourseToAProgrammeService serviceDouble = mock(IAddCourseToAProgrammeService.class);
        CourseInStudyPlanAssemblerImpl assemblerDouble = mock(CourseInStudyPlanAssemblerImpl.class);


        // act
        CourseInStudyPlanRestController controller = new CourseInStudyPlanRestController(assemblerDouble, serviceDouble);

        // assert
        assertNotNull(controller);
    }

    @Test
    void should_ReturnCreateResponse_WhenCourseInStudyPlanCreatedSucessfully() throws Exception {

        // arrange
        IAddCourseToAProgrammeService serviceDouble = mock(IAddCourseToAProgrammeService.class);
        CourseInStudyPlanAssemblerImpl assemblerDouble = mock(CourseInStudyPlanAssemblerImpl.class);
        CourseInStudyPlanRestController controller = new CourseInStudyPlanRestController(assemblerDouble, serviceDouble);

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
    void should_ReturnBadRequest_WhenCourseInStudyPlanCreationFails() throws Exception {

        // arrange
        IAddCourseToAProgrammeService serviceDouble = mock(IAddCourseToAProgrammeService.class);
        CourseInStudyPlanAssemblerImpl assemblerDouble = mock(CourseInStudyPlanAssemblerImpl.class);
        CourseInStudyPlanRestController controller = new CourseInStudyPlanRestController(assemblerDouble, serviceDouble);

        CourseInStudyPlanRequestDTO requestDTO = mock(CourseInStudyPlanRequestDTO.class);

        when(assemblerDouble.toCommand(requestDTO)).thenThrow(new IllegalArgumentException("Invalid data"));

        // act
        ResponseEntity<CourseInStudyPlanResponseDTO> response = controller.create(requestDTO);

        // assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void should_HandleInternalError() throws Exception {

        // arrange
        IAddCourseToAProgrammeService serviceDouble = mock(IAddCourseToAProgrammeService.class);
        CourseInStudyPlanAssemblerImpl assemblerDouble = mock(CourseInStudyPlanAssemblerImpl.class);
        CourseInStudyPlanRestController controller = new CourseInStudyPlanRestController(assemblerDouble, serviceDouble);

        CourseInStudyPlanRequestDTO requestDTO = mock(CourseInStudyPlanRequestDTO.class);

        when(assemblerDouble.toCommand(requestDTO)).thenThrow(new RuntimeException("Unexpected error"));

        // act
        ResponseEntity<CourseInStudyPlanResponseDTO> response = controller.create(requestDTO);

        // assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }
}