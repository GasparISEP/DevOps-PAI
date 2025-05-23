package PAI.controllerRest;

import static org.junit.jupiter.api.Assertions.*;
import PAI.assembler.teacher.ITeacherAssembler;
import PAI.domain.teacher.Teacher;
import PAI.dto.teacher.TeacherDTO;
import PAI.service.teacher.ITeacherService;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.List;

class TeacherRestControllerTest {

    @Test
    void shouldCreateTeacherRestController() {
        // Arrange
        ITeacherService teacherService = mock(ITeacherService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        TeacherRestController controller = new TeacherRestController(teacherService, teacherAssembler);
        // Act + Assert
        assertNotNull(controller);
    }

    @Test
    void shouldReturnListOfTeacherDTO() {
        // Arrange
        ITeacherService teacherService = mock(ITeacherService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        TeacherRestController teacherRestController = new TeacherRestController(teacherService, teacherAssembler);

        Teacher teacher1 = mock(Teacher.class);
        Teacher teacher2 = mock(Teacher.class);
        Teacher teacher3 = mock(Teacher.class);

        TeacherDTO teacherDTO1 = mock(TeacherDTO.class);
        TeacherDTO teacherDTO2 = mock(TeacherDTO.class);
        TeacherDTO teacherDTO3 = mock(TeacherDTO.class);

        List<Teacher> teachers = List.of(teacher1, teacher2, teacher3);
        List<TeacherDTO> teacherDTOs = List.of(teacherDTO1, teacherDTO2, teacherDTO3);

        when(teacherService.getAllTeachers()).thenReturn(teachers);
        when(teacherAssembler.toDTOs(teachers)).thenReturn(teacherDTOs);

        // Act
        ResponseEntity<?> response = teacherRestController.getAllTeachers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(((Iterable<?>) response.getBody()).iterator().hasNext());
        assertTrue(((Collection<?>) response.getBody()).contains(teacherDTO1));
        assertTrue(((Collection<?>) response.getBody()).contains(teacherDTO2));
        assertTrue(((Collection<?>) response.getBody()).contains(teacherDTO3));
    }

    @Test
    void shouldReturnEmptyListOfTeacherDTOIfNoTeachersExist() {
        // Arrange
        ITeacherService teacherService = mock(ITeacherService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        TeacherRestController teacherRestController = new TeacherRestController(teacherService, teacherAssembler);

        List<Teacher> teachers = List.of();
        List<TeacherDTO> teacherDTOs = List.of();

        when(teacherService.getAllTeachers()).thenReturn(teachers);
        when(teacherAssembler.toDTOs(teachers)).thenReturn(teacherDTOs);

        // Act
        ResponseEntity<?> response = teacherRestController.getAllTeachers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        assertFalse(((Iterable<?>) response.getBody()).iterator().hasNext());
    }

    @Test
    void shouldReturnBadRequesteIfIllegalArgumentExceptionIsThrown() {
        // Arrange
        ITeacherService teacherService = mock(ITeacherService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        TeacherRestController teacherRestController = new TeacherRestController(teacherService, teacherAssembler);

        String errorMessage = "Invalid input data";

        when(teacherService.getAllTeachers()).thenThrow(new IllegalArgumentException(errorMessage));

        // Act
        ResponseEntity<?> response = teacherRestController.getAllTeachers();

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(400, response.getStatusCodeValue());
        assertEquals(errorMessage, response.getBody());
    }

    @Test
    void shouldReturnInternalServerErrorIfUnexpectedExceptionIsThrown() {
        // Arrange
        ITeacherService teacherService = mock(ITeacherService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        TeacherRestController teacherRestController = new TeacherRestController(teacherService, teacherAssembler);

        when(teacherService.getAllTeachers()).thenThrow(new RuntimeException());

        // Act
        ResponseEntity<?> response = teacherRestController.getAllTeachers();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(500, response.getStatusCodeValue());
        assertEquals("Unexpected error occurred", response.getBody());
    }

}
