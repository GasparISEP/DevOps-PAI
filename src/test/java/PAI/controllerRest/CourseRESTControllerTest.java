package PAI.controllerRest;

import PAI.assembler.course.ICourseAssembler;
import PAI.dto.course.CourseIDDTO;
import PAI.service.course.ICourseService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.servlet.server.AbstractServletWebServerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseRESTControllerTest {

    @Test
    void shouldReturnListOfCourseIDDTOs() {
        // Arrange
        ICourseService courseService = mock(ICourseService.class);
        ICourseAssembler courseAssembler = mock(ICourseAssembler.class);
        AbstractServletWebServerFactory webServerFactory = mock(AbstractServletWebServerFactory.class);
        CourseRESTController controller = new CourseRESTController(courseService, courseAssembler, webServerFactory);

        CourseIDDTO dto1 = new CourseIDDTO("ABC", "Engineering");
        CourseIDDTO dto2 = new CourseIDDTO("DEF", "Mathematics");
        when(courseService.getAllCourseIDDTO()).thenReturn(List.of(dto1, dto2));

        // Act
        ResponseEntity<List<CourseIDDTO>> response = controller.getAllCoursesIDDTO();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("ABC", response.getBody().get(0).acronym());
        assertEquals("Engineering", response.getBody().get(0).name());
    }

    @Test
    void shouldReturnNoContentWhenNoCoursesExist() {
        // Arrange
        ICourseService courseService = mock(ICourseService.class);
        ICourseAssembler courseAssembler = mock(ICourseAssembler.class);
        AbstractServletWebServerFactory webServerFactory = mock(AbstractServletWebServerFactory.class);
        CourseRESTController controller = new CourseRESTController(courseService, courseAssembler, webServerFactory);

        when(courseService.getAllCourseIDDTO()).thenReturn(List.of());

        // Act
        ResponseEntity<List<CourseIDDTO>> response = controller.getAllCoursesIDDTO();

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void shouldThrowExceptionWhenServiceFails() {
        // Arrange
        ICourseService courseService = mock(ICourseService.class);
        ICourseAssembler courseAssembler = mock(ICourseAssembler.class);
        AbstractServletWebServerFactory webServerFactory = mock(AbstractServletWebServerFactory.class);
        CourseRESTController controller = new CourseRESTController(courseService, courseAssembler, webServerFactory);

        when(courseService.getAllCourseIDDTO()).thenThrow(new RuntimeException("Unexpected"));

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, controller::getAllCoursesIDDTO);
        assertEquals("Unexpected", thrown.getMessage());
    }
}