package PAI.controllerRest;

import static org.junit.jupiter.api.Assertions.*;
import PAI.assembler.teacher.ITeacherAssembler;
import PAI.domain.teacher.Teacher;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import PAI.dto.teacher.TeacherDTO;
import PAI.dto.teacherCareerProgression.ITeacherCareerProgressionAssembler;
import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryCommand;
import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryRequestDTO;
import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryResponseDTO;
import PAI.service.teacher.ITeacherService;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import PAI.service.teacherCareerProgression.ITeacherCareerProgressionServiceV2;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

class TeacherRestControllerTest {

    @Test
    void shouldCreateTeacherRestController() {
        // Arrange
        ITeacherService teacherService = mock(ITeacherService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherCareerProgressionServiceV2 careerService = mock(ITeacherCareerProgressionServiceV2.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherRestController controller = new TeacherRestController(teacherService, teacherAssembler,careerService,careerAssembler);
        // Act + Assert
        assertNotNull(controller);
    }

    @Test
    void shouldReturnListOfTeacherDTO() {
        // Arrange
        ITeacherService teacherService = mock(ITeacherService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherCareerProgressionServiceV2 careerService = mock(ITeacherCareerProgressionServiceV2.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherRestController teacherRestController = new TeacherRestController(teacherService, teacherAssembler,careerService,careerAssembler);

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
        ITeacherCareerProgressionServiceV2 careerService = mock(ITeacherCareerProgressionServiceV2.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherRestController teacherRestController = new TeacherRestController(teacherService, teacherAssembler,careerService,careerAssembler);

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
        ITeacherCareerProgressionServiceV2 careerService = mock(ITeacherCareerProgressionServiceV2.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherRestController teacherRestController = new TeacherRestController(teacherService, teacherAssembler,careerService,careerAssembler);

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
        ITeacherCareerProgressionServiceV2 careerService = mock(ITeacherCareerProgressionServiceV2.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherRestController teacherRestController = new TeacherRestController(teacherService, teacherAssembler,careerService,careerAssembler);

        when(teacherService.getAllTeachers()).thenThrow(new RuntimeException());

        // Act
        ResponseEntity<?> response = teacherRestController.getAllTeachers();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(500, response.getStatusCodeValue());
        assertEquals("Unexpected error occurred", response.getBody());
    }

    @Test
    void ShouldReturnCreatForUpdateTeacherCategory() throws Exception{
        // Arrange
        ITeacherService teacherService = mock(ITeacherService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherCareerProgressionServiceV2 careerService = mock(ITeacherCareerProgressionServiceV2.class);
        ITeacherCareerProgressionAssembler categoryAssembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherRestController teacherRestController = new TeacherRestController(teacherService, teacherAssembler,careerService,categoryAssembler);

        UpdateTeacherCategoryRequestDTO request = mock(UpdateTeacherCategoryRequestDTO.class);
        UpdateTeacherCategoryCommand command = mock(UpdateTeacherCategoryCommand.class);
        TeacherCareerProgression teacherCareerProgression = mock(TeacherCareerProgression.class);
        UpdateTeacherCategoryResponseDTO responseDTO = mock(UpdateTeacherCategoryResponseDTO.class);

        when(categoryAssembler.toUpdateTeacherCategoryCommand(request)).thenReturn(command);
        when(careerService.updateTeacherCategoryInTeacherCareerProgression(command)).thenReturn(Optional.of(teacherCareerProgression));
        when(categoryAssembler.toUpdateCategoryDTO(teacherCareerProgression)).thenReturn(responseDTO);

        //act
        ResponseEntity<?> result = teacherRestController.updateTeacherCategory(request);
        //assert
        assertEquals(responseDTO,result.getBody());
    }

    @Test
    void ShouldReturnBadRequestWhenOptionalOfTeacherCareerProgressionIsEmpty() throws Exception{
        // Arrange
        ITeacherService teacherService = mock(ITeacherService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherCareerProgressionServiceV2 careerService = mock(ITeacherCareerProgressionServiceV2.class);
        ITeacherCareerProgressionAssembler categoryAssembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherRestController teacherRestController = new TeacherRestController(teacherService, teacherAssembler,careerService,categoryAssembler);
        UpdateTeacherCategoryRequestDTO request = mock(UpdateTeacherCategoryRequestDTO.class);
        UpdateTeacherCategoryCommand command = mock(UpdateTeacherCategoryCommand.class);

        when(categoryAssembler.toUpdateTeacherCategoryCommand(request)).thenReturn(command);
        when(careerService.updateTeacherCategoryInTeacherCareerProgression(command)).thenReturn(Optional.empty());
        // Act
        ResponseEntity<?> result = teacherRestController.updateTeacherCategory(request);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("Unable to update teacher category", result.getBody());
    }
    @Test
    void ShouldReturnBadRequestWhenAssemblerThrowsIllegalArgumentException() throws Exception {
        // Arrange
        ITeacherService teacherService = mock(ITeacherService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherCareerProgressionServiceV2 careerService = mock(ITeacherCareerProgressionServiceV2.class);
        ITeacherCareerProgressionAssembler categoryAssembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherRestController teacherRestController = new TeacherRestController(teacherService, teacherAssembler, careerService, categoryAssembler);

        UpdateTeacherCategoryRequestDTO request = mock(UpdateTeacherCategoryRequestDTO.class);

        when(categoryAssembler.toUpdateTeacherCategoryCommand(request)).thenThrow(new IllegalArgumentException("Invalid input"));

        // Act
        ResponseEntity<?> result = teacherRestController.updateTeacherCategory(request);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("Invalid input", result.getBody());
    }
    @Test
    void ShouldThrowInternalServerErrorWhenServiceThrowsUnexpectedException() throws Exception{
        // Arrange
        ITeacherService teacherService = mock(ITeacherService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherCareerProgressionServiceV2 careerService = mock(ITeacherCareerProgressionServiceV2.class);
        ITeacherCareerProgressionAssembler categoryAssembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherRestController teacherRestController = new TeacherRestController(teacherService, teacherAssembler, careerService, categoryAssembler);

        UpdateTeacherCategoryRequestDTO request = mock(UpdateTeacherCategoryRequestDTO.class);
        UpdateTeacherCategoryCommand command = mock(UpdateTeacherCategoryCommand.class);

        when(categoryAssembler.toUpdateTeacherCategoryCommand(request)).thenReturn(command);
        when(careerService.updateTeacherCategoryInTeacherCareerProgression(command)).thenThrow(new RuntimeException("Unexpected"));

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
                teacherRestController.updateTeacherCategory(request)
        );

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatusCode());
        assertEquals("Unexpected error", exception.getReason());
    }

}
