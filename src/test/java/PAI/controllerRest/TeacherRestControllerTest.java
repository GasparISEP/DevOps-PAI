package PAI.controllerRest;

import static org.junit.jupiter.api.Assertions.*;

import PAI.VOs.*;
import PAI.assembler.teacher.ITeacherAssembler;
import PAI.assembler.teacher.ITeacherHateoasAssembler;
import PAI.assembler.teacherCareerProgression.ITeacherCareerProgressionAssembler;
import PAI.assembler.teacherCareerProgression.IUpdateTeacherCategoryHateoasAssembler;
import PAI.assembler.teacherCareerProgression.IUpdateTeacherWorkingPercentageHateoasAssembler;
import PAI.domain.teacher.Teacher;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import PAI.dto.teacher.*;
import PAI.dto.teacherCareerProgression.*;
import PAI.exception.BusinessRuleViolationException;
import PAI.exception.NotFoundException;
import PAI.service.teacher.ITeacherRegistrationService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import PAI.service.teacher.ITeacherWithRelevantDataService;
import PAI.service.teacherCareerProgression.ICreateTeacherCareerProgressionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@WebMvcTest(TeacherRestController.class)
class TeacherRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ITeacherRegistrationService teacherService;

    @MockBean
    private ITeacherAssembler teacherAssembler;

    @MockBean
    private ITeacherHateoasAssembler teacherHateoasAssembler;

    @MockBean
    private ICreateTeacherCareerProgressionService careerService;

    @MockBean
    private ITeacherCareerProgressionAssembler categoryAssembler;

    @MockBean
    private ITeacherWithRelevantDataService teacherWithRelevantDataService;

    @MockBean
    private TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler;

    @MockBean
    private IUpdateTeacherWorkingPercentageHateoasAssembler updateTeacherWorkingPercentageHateoasAssembler;

    @MockBean
    private IUpdateTeacherCategoryHateoasAssembler updateTeacherCategoryHateoasAssembler;


    // testing constructor method

    @Test
    void shouldCreateTeacherRestController() {
        // Arrange
        ITeacherRegistrationService teacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherHateoasAssembler teacherHateoasAssembler = mock(ITeacherHateoasAssembler.class);
        ICreateTeacherCareerProgressionService careerService = mock(ICreateTeacherCareerProgressionService.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);
        ITeacherWithRelevantDataService teacherWithRelevantDataService = mock(ITeacherWithRelevantDataService.class);
        TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IUpdateTeacherWorkingPercentageHateoasAssembler updateTeacherWorkingPercentageHateoasAssembler = mock(IUpdateTeacherWorkingPercentageHateoasAssembler.class);
        IUpdateTeacherCategoryHateoasAssembler updateTeacherCategoryHateoasAssembler = mock(IUpdateTeacherCategoryHateoasAssembler.class);
        TeacherRestController controller = new TeacherRestController(teacherService, teacherAssembler, teacherHateoasAssembler, careerService,careerAssembler, teacherWithRelevantDataService,teacherWithRelevantDataAssembler, updateTeacherWorkingPercentageHateoasAssembler, updateTeacherCategoryHateoasAssembler);
        // Act + Assert
        assertNotNull(controller);
    }

    @Test
    void shouldReturnAnExceptionIfTeacherServiceIsNull() {
        //arrange
        ITeacherAssembler doubleTeacherAssembler = mock(ITeacherAssembler.class);
        ITeacherHateoasAssembler doubleTeacherHateoasAssembler = mock(ITeacherHateoasAssembler.class);
        ICreateTeacherCareerProgressionService doubleTCPService = mock(ICreateTeacherCareerProgressionService.class);
        ITeacherCareerProgressionAssembler doubleTCPAssembler = mock(ITeacherCareerProgressionAssembler.class);
        ITeacherWithRelevantDataService doubleTeacherWithRelevantDataService = mock(ITeacherWithRelevantDataService.class);
        TeacherWithRelevantDataAssembler doubleTeacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IUpdateTeacherWorkingPercentageHateoasAssembler doubleUpdateTeacherWorkingPercentageHateoasAssembler =
                mock(IUpdateTeacherWorkingPercentageHateoasAssembler.class);
        IUpdateTeacherCategoryHateoasAssembler doubleUpdateTeacherCategoryHateoasAssembler = mock(IUpdateTeacherCategoryHateoasAssembler.class);


        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TeacherRestController(null, doubleTeacherAssembler, doubleTeacherHateoasAssembler ,doubleTCPService,
                    doubleTCPAssembler, doubleTeacherWithRelevantDataService,doubleTeacherWithRelevantDataAssembler,
                    doubleUpdateTeacherWorkingPercentageHateoasAssembler, doubleUpdateTeacherCategoryHateoasAssembler);
        });

        //assert
        assertEquals("Teacher Registration Service Interface cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnAnExceptionIfTeacherAssemblerIsNull() {
        //arrange
        ITeacherHateoasAssembler doubleTeacherHateoasAssembler = mock(ITeacherHateoasAssembler.class);
        ITeacherRegistrationService doubleTeacherService = mock(ITeacherRegistrationService.class);
        ICreateTeacherCareerProgressionService doubleTCPService = mock(ICreateTeacherCareerProgressionService.class);
        ITeacherCareerProgressionAssembler doubleTCPAssembler = mock(ITeacherCareerProgressionAssembler.class);
        ITeacherWithRelevantDataService doubleTeacherWithRelevantDataService = mock(ITeacherWithRelevantDataService.class);
        TeacherWithRelevantDataAssembler doubleTeacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IUpdateTeacherWorkingPercentageHateoasAssembler doubleUpdateTeacherWorkingPercentageHateoasAssembler =
                mock(IUpdateTeacherWorkingPercentageHateoasAssembler.class);
        IUpdateTeacherCategoryHateoasAssembler doubleUpdateTeacherCategoryHateoasAssembler = mock(IUpdateTeacherCategoryHateoasAssembler.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TeacherRestController(doubleTeacherService, null , doubleTeacherHateoasAssembler, doubleTCPService,
                    doubleTCPAssembler, doubleTeacherWithRelevantDataService,doubleTeacherWithRelevantDataAssembler,
                    doubleUpdateTeacherWorkingPercentageHateoasAssembler, doubleUpdateTeacherCategoryHateoasAssembler);
        });

        //assert
        assertEquals("Teacher Assembler Interface cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnAnExceptionIfTeacherHateoasAssemblerIsNull() {
        //arrange
        ITeacherAssembler doubleTeacherAssembler = mock(ITeacherAssembler.class);
        ITeacherRegistrationService doubleTeacherService = mock(ITeacherRegistrationService.class);
        ICreateTeacherCareerProgressionService doubleTCPService = mock(ICreateTeacherCareerProgressionService.class);
        ITeacherCareerProgressionAssembler doubleTCPAssembler = mock(ITeacherCareerProgressionAssembler.class);
        ITeacherWithRelevantDataService doubleTeacherWithRelevantDataService = mock(ITeacherWithRelevantDataService.class);
        TeacherWithRelevantDataAssembler doubleTeacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IUpdateTeacherWorkingPercentageHateoasAssembler doubleUpdateTeacherWorkingPercentageHateoasAssembler =
                mock(IUpdateTeacherWorkingPercentageHateoasAssembler.class);
        IUpdateTeacherCategoryHateoasAssembler doubleUpdateTeacherCategoryHateoasAssembler = mock(IUpdateTeacherCategoryHateoasAssembler.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TeacherRestController(doubleTeacherService, doubleTeacherAssembler , null, doubleTCPService, doubleTCPAssembler,
                    doubleTeacherWithRelevantDataService,doubleTeacherWithRelevantDataAssembler, doubleUpdateTeacherWorkingPercentageHateoasAssembler,
                    doubleUpdateTeacherCategoryHateoasAssembler);
        });
        //assert
        assertEquals("Teacher Hateoas Assembler Interface cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnAnExceptionIfTeacherCareerProgressionServiceIsNull() {
        //arrange
        ITeacherRegistrationService doubleTeacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler doubleTeacherAssembler = mock(ITeacherAssembler.class);
        ITeacherHateoasAssembler doubleTeacherHateoasAssembler = mock(ITeacherHateoasAssembler.class);
        ITeacherCareerProgressionAssembler doubleTCPAssembler = mock(ITeacherCareerProgressionAssembler.class);
        ITeacherWithRelevantDataService doubleTeacherWithRelevantDataService = mock(ITeacherWithRelevantDataService.class);
        TeacherWithRelevantDataAssembler doubleTeacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IUpdateTeacherWorkingPercentageHateoasAssembler doubleUpdateTeacherWorkingPercentageHateoasAssembler =
                mock(IUpdateTeacherWorkingPercentageHateoasAssembler.class);
        IUpdateTeacherCategoryHateoasAssembler doubleUpdateTeacherCategoryHateoasAssembler = mock(IUpdateTeacherCategoryHateoasAssembler.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TeacherRestController(doubleTeacherService, doubleTeacherAssembler ,doubleTeacherHateoasAssembler, null, doubleTCPAssembler,
                    doubleTeacherWithRelevantDataService,doubleTeacherWithRelevantDataAssembler, doubleUpdateTeacherWorkingPercentageHateoasAssembler,
                    doubleUpdateTeacherCategoryHateoasAssembler);
        });

        //assert
        assertEquals("Teacher Career Progression Service Interface cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnAnExceptionIfTeacherCareerProgressionAssemblerIsNull() {
        //arrange
        ITeacherRegistrationService doubleTeacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler doubleTeacherAssembler = mock(ITeacherAssembler.class);
        ITeacherHateoasAssembler doubleTeacherHateoasAssembler = mock(ITeacherHateoasAssembler.class);
        ICreateTeacherCareerProgressionService doubleTCPService = mock(ICreateTeacherCareerProgressionService.class);
        ITeacherWithRelevantDataService doubleTeacherWithRelevantDataService = mock(ITeacherWithRelevantDataService.class);
        TeacherWithRelevantDataAssembler doubleTeacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IUpdateTeacherWorkingPercentageHateoasAssembler doubleUpdateTeacherWorkingPercentageHateoasAssembler =
                mock(IUpdateTeacherWorkingPercentageHateoasAssembler.class);
        IUpdateTeacherCategoryHateoasAssembler doubleUpdateTeacherCategoryHateoasAssembler = mock(IUpdateTeacherCategoryHateoasAssembler.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TeacherRestController(doubleTeacherService, doubleTeacherAssembler , doubleTeacherHateoasAssembler, doubleTCPService, null,
                    doubleTeacherWithRelevantDataService,doubleTeacherWithRelevantDataAssembler, doubleUpdateTeacherWorkingPercentageHateoasAssembler,
                    doubleUpdateTeacherCategoryHateoasAssembler);
        });

        //assert
        assertEquals("Teacher Career Progression Assembler Interface cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnAnExceptionIfTeacherWithRelevantDataServiceIsNull() {
        //arrange
        ITeacherRegistrationService doubleTeacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler doubleTeacherAssembler = mock(ITeacherAssembler.class);
        ITeacherHateoasAssembler doubleTeacherHateoasAssembler = mock(ITeacherHateoasAssembler.class);
        ICreateTeacherCareerProgressionService doubleTCPService = mock(ICreateTeacherCareerProgressionService.class);
        ITeacherCareerProgressionAssembler doubleTCPAssembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherWithRelevantDataAssembler doubleTeacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IUpdateTeacherWorkingPercentageHateoasAssembler doubleUpdateTeacherWorkingPercentageHateoasAssembler =
                mock(IUpdateTeacherWorkingPercentageHateoasAssembler.class);
        IUpdateTeacherCategoryHateoasAssembler doubleUpdateTeacherCategoryHateoasAssembler = mock(IUpdateTeacherCategoryHateoasAssembler.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TeacherRestController(doubleTeacherService, doubleTeacherAssembler , doubleTeacherHateoasAssembler, doubleTCPService, doubleTCPAssembler,
                    null,doubleTeacherWithRelevantDataAssembler, doubleUpdateTeacherWorkingPercentageHateoasAssembler,
                    doubleUpdateTeacherCategoryHateoasAssembler);
        });

        //assert
        assertEquals("Teacher With Relevant Data Service Interface cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnAnExceptionIfTeacherWithRelevantDataAssemblerIsNull() {
        //arrange
        ITeacherRegistrationService doubleTeacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler doubleTeacherAssembler = mock(ITeacherAssembler.class);
        ITeacherHateoasAssembler doubleTeacherHateoasAssembler = mock(ITeacherHateoasAssembler.class);
        ICreateTeacherCareerProgressionService doubleTCPService = mock(ICreateTeacherCareerProgressionService.class);
        ITeacherCareerProgressionAssembler doubleTCPAssembler = mock(ITeacherCareerProgressionAssembler.class);
        ITeacherWithRelevantDataService doubleTeacherWithRelevantDataService = mock(ITeacherWithRelevantDataService.class);
        IUpdateTeacherWorkingPercentageHateoasAssembler doubleUpdateTeacherWorkingPercentageHateoasAssembler =
                mock(IUpdateTeacherWorkingPercentageHateoasAssembler.class);
        IUpdateTeacherCategoryHateoasAssembler doubleUpdateTeacherCategoryHateoasAssembler = mock(IUpdateTeacherCategoryHateoasAssembler.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TeacherRestController(doubleTeacherService, doubleTeacherAssembler , doubleTeacherHateoasAssembler, doubleTCPService, doubleTCPAssembler,
                    doubleTeacherWithRelevantDataService,null, doubleUpdateTeacherWorkingPercentageHateoasAssembler,
                    doubleUpdateTeacherCategoryHateoasAssembler);
        });

        //assert
        assertEquals("Teacher With Relevant Data Assembler Interface cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnAnExceptionIfUpdateTeacherWorkingPercentageHateoasAssemblerIsNull() {
        //arrange
        ITeacherRegistrationService doubleTeacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler doubleTeacherAssembler = mock(ITeacherAssembler.class);
        ITeacherHateoasAssembler doubleTeacherHateoasAssembler = mock(ITeacherHateoasAssembler.class);
        ICreateTeacherCareerProgressionService doubleTCPService = mock(ICreateTeacherCareerProgressionService.class);
        ITeacherCareerProgressionAssembler doubleTCPAssembler = mock(ITeacherCareerProgressionAssembler.class);
        ITeacherWithRelevantDataService doubleTeacherWithRelevantDataService = mock(ITeacherWithRelevantDataService.class);
        TeacherWithRelevantDataAssembler doubleTeacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IUpdateTeacherCategoryHateoasAssembler doubleUpdateTeacherCategoryHateoasAssembler = mock(IUpdateTeacherCategoryHateoasAssembler.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TeacherRestController(doubleTeacherService, doubleTeacherAssembler , doubleTeacherHateoasAssembler,doubleTCPService, doubleTCPAssembler,
                    doubleTeacherWithRelevantDataService,doubleTeacherWithRelevantDataAssembler, null,
                    doubleUpdateTeacherCategoryHateoasAssembler);
        });

        //assert
        assertEquals("Update Teacher Working Percentage Hateoas Assembler Interface cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnAnExceptionIfUpdateTeacherCategoryHateoasAssemblerIsNull() {
        //arrange
        ITeacherRegistrationService doubleTeacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler doubleTeacherAssembler = mock(ITeacherAssembler.class);
        ITeacherHateoasAssembler doubleTeacherHateoasAssembler = mock(ITeacherHateoasAssembler.class);
        ICreateTeacherCareerProgressionService doubleTCPService = mock(ICreateTeacherCareerProgressionService.class);
        ITeacherCareerProgressionAssembler doubleTCPAssembler = mock(ITeacherCareerProgressionAssembler.class);
        ITeacherWithRelevantDataService doubleTeacherWithRelevantDataService = mock(ITeacherWithRelevantDataService.class);
        TeacherWithRelevantDataAssembler doubleTeacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IUpdateTeacherWorkingPercentageHateoasAssembler doubleUpdateTeacherWorkingPercentageHateoasAssembler =
                mock(IUpdateTeacherWorkingPercentageHateoasAssembler.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TeacherRestController(doubleTeacherService, doubleTeacherAssembler , doubleTeacherHateoasAssembler,doubleTCPService, doubleTCPAssembler,
                    doubleTeacherWithRelevantDataService,doubleTeacherWithRelevantDataAssembler, doubleUpdateTeacherWorkingPercentageHateoasAssembler,
                    null);
        });

        //assert
        assertEquals("Update Teacher Category Hateoas Assembler Interface cannot be null.", exception.getMessage());
    }

    // testing getAllTeachers() method

    @Test
    void shouldReturnListOfTeacherDTO() {
        // Arrange
        ITeacherRegistrationService teacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherHateoasAssembler teacherHateoasAssembler = mock(ITeacherHateoasAssembler.class);
        ICreateTeacherCareerProgressionService careerService = mock(ICreateTeacherCareerProgressionService.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);
        ITeacherWithRelevantDataService teacherWithRelevantDataService = mock(ITeacherWithRelevantDataService.class);
        TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IUpdateTeacherWorkingPercentageHateoasAssembler updateTeacherWorkingPercentageHateoasAssembler = mock(IUpdateTeacherWorkingPercentageHateoasAssembler.class);
        IUpdateTeacherCategoryHateoasAssembler doubleUpdateTeacherCategoryHateoasAssembler = mock(IUpdateTeacherCategoryHateoasAssembler.class);
        TeacherRestController teacherRestController = new TeacherRestController(teacherService, teacherAssembler, teacherHateoasAssembler, careerService,careerAssembler, teacherWithRelevantDataService, teacherWithRelevantDataAssembler,updateTeacherWorkingPercentageHateoasAssembler, doubleUpdateTeacherCategoryHateoasAssembler);

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
        ITeacherRegistrationService teacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherHateoasAssembler teacherHateoasAssembler = mock(ITeacherHateoasAssembler.class);
        ICreateTeacherCareerProgressionService careerService = mock(ICreateTeacherCareerProgressionService.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);
        ITeacherWithRelevantDataService teacherWithRelevantDataService = mock(ITeacherWithRelevantDataService.class);
        TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IUpdateTeacherWorkingPercentageHateoasAssembler updateTeacherWorkingPercentageHateoasAssembler = mock(IUpdateTeacherWorkingPercentageHateoasAssembler.class);
        IUpdateTeacherCategoryHateoasAssembler doubleUpdateTeacherCategoryHateoasAssembler = mock(IUpdateTeacherCategoryHateoasAssembler.class);
        TeacherRestController teacherRestController = new TeacherRestController(teacherService, teacherAssembler, teacherHateoasAssembler, careerService,careerAssembler, teacherWithRelevantDataService, teacherWithRelevantDataAssembler,updateTeacherWorkingPercentageHateoasAssembler, doubleUpdateTeacherCategoryHateoasAssembler);

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
    void shouldReturnBadRequestIfIllegalArgumentExceptionIsThrown() {
        // Arrange
        ITeacherRegistrationService teacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherHateoasAssembler teacherHateoasAssembler = mock(ITeacherHateoasAssembler.class);
        ICreateTeacherCareerProgressionService careerService = mock(ICreateTeacherCareerProgressionService.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);
        ITeacherWithRelevantDataService teacherWithRelevantDataService = mock(ITeacherWithRelevantDataService.class);
        TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IUpdateTeacherWorkingPercentageHateoasAssembler updateTeacherWorkingPercentageHateoasAssembler = mock(IUpdateTeacherWorkingPercentageHateoasAssembler.class);
        IUpdateTeacherCategoryHateoasAssembler doubleUpdateTeacherCategoryHateoasAssembler = mock(IUpdateTeacherCategoryHateoasAssembler.class);
        TeacherRestController teacherRestController = new TeacherRestController(teacherService, teacherAssembler, teacherHateoasAssembler, careerService,careerAssembler, teacherWithRelevantDataService, teacherWithRelevantDataAssembler, updateTeacherWorkingPercentageHateoasAssembler, doubleUpdateTeacherCategoryHateoasAssembler);

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
        ITeacherRegistrationService teacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherHateoasAssembler teacherHateoasAssembler = mock(ITeacherHateoasAssembler.class);
        ICreateTeacherCareerProgressionService careerService = mock(ICreateTeacherCareerProgressionService.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);
        ITeacherWithRelevantDataService teacherWithRelevantDataService = mock(ITeacherWithRelevantDataService.class);
        TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IUpdateTeacherWorkingPercentageHateoasAssembler updateTeacherWorkingPercentageHateoasAssembler = mock(IUpdateTeacherWorkingPercentageHateoasAssembler.class);
        IUpdateTeacherCategoryHateoasAssembler doubleUpdateTeacherCategoryHateoasAssembler = mock(IUpdateTeacherCategoryHateoasAssembler.class);
        TeacherRestController teacherRestController = new TeacherRestController(teacherService, teacherAssembler, teacherHateoasAssembler, careerService,careerAssembler, teacherWithRelevantDataService, teacherWithRelevantDataAssembler,updateTeacherWorkingPercentageHateoasAssembler, doubleUpdateTeacherCategoryHateoasAssembler);

        when(teacherService.getAllTeachers()).thenThrow(new RuntimeException());

        // Act
        ResponseEntity<?> response = teacherRestController.getAllTeachers();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(500, response.getStatusCodeValue());
        assertEquals("Unexpected error occurred", response.getBody());
    }

    @Test
    void shouldReturnCreatedTeacherDTOIfSuccessful() throws Exception {
        // Arrange
        ITeacherRegistrationService teacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherHateoasAssembler teacherHateoasAssembler = mock(ITeacherHateoasAssembler.class);
        ICreateTeacherCareerProgressionService careerService = mock(ICreateTeacherCareerProgressionService.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);
        ITeacherWithRelevantDataService teacherWithRelevantDataService = mock(ITeacherWithRelevantDataService.class);
        TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IUpdateTeacherWorkingPercentageHateoasAssembler updateTeacherWorkingPercentageHateoasAssembler = mock(IUpdateTeacherWorkingPercentageHateoasAssembler.class);
        IUpdateTeacherCategoryHateoasAssembler doubleUpdateTeacherCategoryHateoasAssembler = mock(IUpdateTeacherCategoryHateoasAssembler.class);
        TeacherRestController teacherRestController = new TeacherRestController(teacherService, teacherAssembler, teacherHateoasAssembler,careerService,careerAssembler, teacherWithRelevantDataService, teacherWithRelevantDataAssembler,updateTeacherWorkingPercentageHateoasAssembler, doubleUpdateTeacherCategoryHateoasAssembler);

        RegisterTeacherRequestDTO requestDTO = mock(RegisterTeacherRequestDTO.class);
        when(requestDTO.id()).thenReturn("JAB");
        when(requestDTO.name()).thenReturn("João");
        when(requestDTO.email()).thenReturn("JAB@isep.ipp.pt");
        when(requestDTO.nif()).thenReturn("123456789");
        when(requestDTO.countryCode()).thenReturn("+351");
        when(requestDTO.phoneNumber()).thenReturn("987654321");
        when(requestDTO.academicBackground()).thenReturn("LEI");
        when(requestDTO.street()).thenReturn("Rua Numero 1");
        when(requestDTO.postalCode()).thenReturn("4000-100");
        when(requestDTO.location()).thenReturn("Porto");
        when(requestDTO.country()).thenReturn("Portugal");
        when(requestDTO.departmentID()).thenReturn("DEI");

        TeacherID teacherID = mock(TeacherID.class);
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);
        when(teacherID.getTeacherAcronym()).thenReturn(teacherAcronym);
        when(teacherAcronym.getAcronym()).thenReturn("JAB");

        Name name = mock(Name.class);
        when(name.getName()).thenReturn("João");

        Email email = mock(Email.class);
        when(email.getEmail()).thenReturn("JAB@isep.ipp.pt");

        NIF nif = mock(NIF.class);
        when(nif.getNIF()).thenReturn("123456789");

        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        when(phoneNumber.getCountryCode()).thenReturn("+351");
        when(phoneNumber.getNumber()).thenReturn("987654321");

        AcademicBackground academicBackground = mock(AcademicBackground.class);
        when(academicBackground.getAcademicBackground()).thenReturn("LEI");

        Street street = mock(Street.class);
        when(street.getStreet()).thenReturn("Rua Numero 1");

        PostalCode postalCode = mock(PostalCode.class);
        when(postalCode.getPostalCode()).thenReturn("4000-100");

        Location location = mock(Location.class);
        when(location.getLocation()).thenReturn("Porto");

        Country country = mock(Country.class);
        when(country.getCountryName()).thenReturn("Portugal");

        DepartmentID departmentID = mock(DepartmentID.class);
        DepartmentAcronym departmentAcronym = mock(DepartmentAcronym.class);
        when(departmentID.getAcronym()).thenReturn(departmentAcronym);
        when(departmentAcronym.getAcronym()).thenReturn("DEI");

        RegisterTeacherCommandDTO commandDTO = mock(RegisterTeacherCommandDTO.class);
        when(commandDTO.id()).thenReturn(teacherID);
        when(commandDTO.name()).thenReturn(name);
        when(commandDTO.email()).thenReturn(email);
        when(commandDTO.nif()).thenReturn(nif);
        when(commandDTO.phoneNumber()).thenReturn(phoneNumber);
        when(commandDTO.academicBackground()).thenReturn(academicBackground);
        when(commandDTO.street()).thenReturn(street);
        when(commandDTO.postalCode()).thenReturn(postalCode);
        when(commandDTO.location()).thenReturn(location);
        when(commandDTO.country()).thenReturn(country);
        when(commandDTO.departmentID()).thenReturn(departmentID);

        Teacher teacher = mock(Teacher.class);
        TeacherDTO teacherDTO = mock(TeacherDTO.class);

        when(teacherDTO.id()).thenReturn("JAB");
        when(teacherDTO.name()).thenReturn("João");
        when(teacherDTO.email()).thenReturn("JAB@isep.ipp.pt");
        when(teacherDTO.nif()).thenReturn("123456789");
        when(teacherDTO.countryCode()).thenReturn("+351");
        when(teacherDTO.phoneNumber()).thenReturn("987654321");
        when(teacherDTO.academicBackground()).thenReturn("LEI");
        when(teacherDTO.street()).thenReturn("Rua Numero 1");
        when(teacherDTO.postalCode()).thenReturn("4000-100");
        when(teacherDTO.location()).thenReturn("Porto");
        when(teacherDTO.country()).thenReturn("Portugal");
        when(teacherDTO.departmentID()).thenReturn("DEI");

        when(teacherAssembler.toRegisterTeacherCommandDTO(requestDTO)).thenReturn(commandDTO);
        when(teacherService.createAndSaveTeacher(commandDTO)).thenReturn(teacher);
        when(teacherAssembler.toDTO(teacher)).thenReturn(teacherDTO);

        // Act
        ResponseEntity<?> response = teacherRestController.registerTeacher(requestDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(teacherDTO, response.getBody());
    }

    @Test
    void shouldReturnConflictWhenBusinessRuleViolation() throws Exception {
        //Arrange
        ITeacherRegistrationService teacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherHateoasAssembler teacherHateoasAssembler = mock(ITeacherHateoasAssembler.class);
        ICreateTeacherCareerProgressionService careerService = mock(ICreateTeacherCareerProgressionService.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);
        ITeacherWithRelevantDataService teacherWithRelevantDataService = mock(ITeacherWithRelevantDataService.class);
        TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IUpdateTeacherWorkingPercentageHateoasAssembler updateTeacherWorkingPercentageHateoasAssembler = mock(IUpdateTeacherWorkingPercentageHateoasAssembler.class);
        IUpdateTeacherCategoryHateoasAssembler doubleUpdateTeacherCategoryHateoasAssembler = mock(IUpdateTeacherCategoryHateoasAssembler.class);
        TeacherRestController teacherRestController = new TeacherRestController(teacherService, teacherAssembler, teacherHateoasAssembler, careerService,careerAssembler, teacherWithRelevantDataService, teacherWithRelevantDataAssembler,updateTeacherWorkingPercentageHateoasAssembler, doubleUpdateTeacherCategoryHateoasAssembler);

        RegisterTeacherRequestDTO requestDTO = mock(RegisterTeacherRequestDTO.class);
        when(requestDTO.id()).thenReturn("JAB");
        when(requestDTO.name()).thenReturn("João");
        when(requestDTO.email()).thenReturn("JAB@isep.ipp.pt");
        when(requestDTO.nif()).thenReturn("123456789");
        when(requestDTO.countryCode()).thenReturn("+351");
        when(requestDTO.phoneNumber()).thenReturn("987654321");
        when(requestDTO.academicBackground()).thenReturn("LEI");
        when(requestDTO.street()).thenReturn("Rua Numero 1");
        when(requestDTO.postalCode()).thenReturn("4000-100");
        when(requestDTO.location()).thenReturn("Porto");
        when(requestDTO.country()).thenReturn("Portugal");
        when(requestDTO.departmentID()).thenReturn("DEI");

        RegisterTeacherCommandDTO commandDTO = mock(RegisterTeacherCommandDTO.class);
        when(commandDTO.id()).thenReturn(mock(TeacherID.class));
        when(commandDTO.name()).thenReturn(mock(Name.class));
        when(commandDTO.email()).thenReturn(mock(Email.class));
        when(commandDTO.nif()).thenReturn(mock(NIF.class));
        when(commandDTO.phoneNumber()).thenReturn(mock(PhoneNumber.class));
        when(commandDTO.academicBackground()).thenReturn(mock(AcademicBackground.class));
        when(commandDTO.street()).thenReturn(mock(Street.class));
        when(commandDTO.postalCode()).thenReturn(mock(PostalCode.class));
        when(commandDTO.location()).thenReturn(mock(Location.class));
        when(commandDTO.country()).thenReturn(mock(Country.class));
        when(commandDTO.departmentID()).thenReturn(mock(DepartmentID.class));

        when(teacherAssembler.toRegisterTeacherCommandDTO(requestDTO)).thenReturn(commandDTO);
        when(teacherService.createAndSaveTeacher(commandDTO)).thenReturn(mock(Teacher.class));
        when(teacherAssembler.toRegisterTeacherCommandDTO(requestDTO)).thenReturn(commandDTO);
        when(teacherService.createAndSaveTeacher(commandDTO)).thenThrow(new BusinessRuleViolationException("Teacher already exists"));

        // Act
        ResponseEntity<?> response = teacherRestController.registerTeacher(requestDTO);

        // Assert
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(409, response.getStatusCodeValue());
        assertEquals("Teacher already exists", response.getBody());
    }

    @Test
    void shouldReturnBadRequestWhenIllegalArgument() {
        // Arrange
        ITeacherRegistrationService teacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherHateoasAssembler teacherHateoasAssembler = mock(ITeacherHateoasAssembler.class);
        ICreateTeacherCareerProgressionService careerService = mock(ICreateTeacherCareerProgressionService.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);
        ITeacherWithRelevantDataService teacherWithRelevantDataService = mock(ITeacherWithRelevantDataService.class);
        TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IUpdateTeacherWorkingPercentageHateoasAssembler updateTeacherWorkingPercentageHateoasAssembler = mock(IUpdateTeacherWorkingPercentageHateoasAssembler.class);
        IUpdateTeacherCategoryHateoasAssembler doubleUpdateTeacherCategoryHateoasAssembler = mock(IUpdateTeacherCategoryHateoasAssembler.class);
        TeacherRestController teacherRestController = new TeacherRestController(teacherService, teacherAssembler, teacherHateoasAssembler, careerService,careerAssembler, teacherWithRelevantDataService, teacherWithRelevantDataAssembler, updateTeacherWorkingPercentageHateoasAssembler, doubleUpdateTeacherCategoryHateoasAssembler);

        RegisterTeacherRequestDTO requestDTO = mock(RegisterTeacherRequestDTO.class);
        when(requestDTO.id()).thenReturn("JAB");
        when(requestDTO.name()).thenReturn("João");
        when(requestDTO.email()).thenReturn("JAB@isep.ipp.pt");
        when(requestDTO.nif()).thenReturn("123456789");
        when(requestDTO.countryCode()).thenReturn("+351");
        when(requestDTO.phoneNumber()).thenReturn("987654321");
        when(requestDTO.academicBackground()).thenReturn("LEI");
        when(requestDTO.street()).thenReturn("Rua Numero 1");
        when(requestDTO.postalCode()).thenReturn("4000-100");
        when(requestDTO.location()).thenReturn("Porto");
        when(requestDTO.country()).thenReturn("Portugal");
        when(requestDTO.departmentID()).thenReturn("DEI");

        when(teacherAssembler.toRegisterTeacherCommandDTO(requestDTO)).thenThrow(new IllegalArgumentException("Invalid id"));

        // Act
        ResponseEntity<?> response = teacherRestController.registerTeacher(requestDTO);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Invalid id", response.getBody());
    }

    @Test
    void shouldReturnInternalServerErrorWhenUnexpectedException() {
        // Arrange
        ITeacherRegistrationService teacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherHateoasAssembler teacherHateoasAssembler = mock(ITeacherHateoasAssembler.class);
        ICreateTeacherCareerProgressionService careerService = mock(ICreateTeacherCareerProgressionService.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);
        ITeacherWithRelevantDataService teacherWithRelevantDataService = mock(ITeacherWithRelevantDataService.class);
        TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IUpdateTeacherWorkingPercentageHateoasAssembler updateTeacherWorkingPercentageHateoasAssembler = mock(IUpdateTeacherWorkingPercentageHateoasAssembler.class);
        IUpdateTeacherCategoryHateoasAssembler doubleUpdateTeacherCategoryHateoasAssembler = mock(IUpdateTeacherCategoryHateoasAssembler.class);
        TeacherRestController teacherRestController = new TeacherRestController(teacherService, teacherAssembler, teacherHateoasAssembler,careerService,careerAssembler, teacherWithRelevantDataService, teacherWithRelevantDataAssembler,updateTeacherWorkingPercentageHateoasAssembler, doubleUpdateTeacherCategoryHateoasAssembler);

        RegisterTeacherRequestDTO requestDTO = mock(RegisterTeacherRequestDTO.class);
        when(requestDTO.id()).thenReturn("JAB");
        when(requestDTO.name()).thenReturn("João");
        when(requestDTO.email()).thenReturn("JAB@isep.ipp.pt");
        when(requestDTO.nif()).thenReturn("123456789");
        when(requestDTO.countryCode()).thenReturn("+351");
        when(requestDTO.phoneNumber()).thenReturn("987654321");
        when(requestDTO.academicBackground()).thenReturn("LEI");
        when(requestDTO.street()).thenReturn("Rua Numero 1");
        when(requestDTO.postalCode()).thenReturn("4000-100");
        when(requestDTO.location()).thenReturn("Porto");
        when(requestDTO.country()).thenReturn("Portugal");
        when(requestDTO.departmentID()).thenReturn("DEI");

        when(teacherAssembler.toRegisterTeacherCommandDTO(requestDTO)).thenThrow(new RuntimeException("Unexpected error occurred"));

        // Act
        ResponseEntity<?> response = teacherRestController.registerTeacher(requestDTO);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(500, response.getStatusCodeValue());
        assertEquals("Unexpected error occurred", response.getBody());
    }

    // updateTeacherCategory() method

    @Test
    void shouldReturnCreatForUpdateTeacherCategory() throws Exception{
        // Arrange
        ITeacherRegistrationService teacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherHateoasAssembler teacherHateoasAssembler = mock(ITeacherHateoasAssembler.class);
        ICreateTeacherCareerProgressionService careerService = mock(ICreateTeacherCareerProgressionService.class);
        ITeacherCareerProgressionAssembler categoryAssembler = mock(ITeacherCareerProgressionAssembler.class);
        ITeacherWithRelevantDataService teacherWithRelevantDataService = mock(ITeacherWithRelevantDataService.class);
        TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IUpdateTeacherWorkingPercentageHateoasAssembler updateTeacherWorkingPercentageHateoasAssembler = mock(IUpdateTeacherWorkingPercentageHateoasAssembler.class);
        IUpdateTeacherCategoryHateoasAssembler doubleUpdateTeacherCategoryHateoasAssembler = mock(IUpdateTeacherCategoryHateoasAssembler.class);

        TeacherRestController teacherRestController = new TeacherRestController(teacherService, teacherAssembler, teacherHateoasAssembler, careerService,categoryAssembler, teacherWithRelevantDataService, teacherWithRelevantDataAssembler,updateTeacherWorkingPercentageHateoasAssembler, doubleUpdateTeacherCategoryHateoasAssembler);

        UpdateTeacherCategoryRequestDTO request = mock(UpdateTeacherCategoryRequestDTO.class);
        UpdateTeacherCategoryCommand command = mock(UpdateTeacherCategoryCommand.class);
        UpdateTeacherCategoryResponseDTO responseDTO = mock(UpdateTeacherCategoryResponseDTO.class);
        UpdateTeacherCategoryDTO doubleUpdateTeacherCategoryDTO = mock(UpdateTeacherCategoryDTO.class);

        String teacherId = "ABC";

        when(categoryAssembler.toUpdateTeacherCategoryCommand(teacherId, request)).thenReturn(command);
        when(careerService.updateTeacherCategory(command)).thenReturn(doubleUpdateTeacherCategoryDTO);
        when(categoryAssembler.toUpdateTeacherCategoryResponseDTO(doubleUpdateTeacherCategoryDTO)).thenReturn(responseDTO);
        when (doubleUpdateTeacherCategoryHateoasAssembler.toModel(responseDTO)).thenReturn(EntityModel.of(responseDTO));

        //act
        ResponseEntity<?> result = teacherRestController.updateTeacherCategory(teacherId,request);

        //assert
        assertEquals(EntityModel.of(responseDTO),result.getBody());
    }

    @Test
    void shouldReturnBadRequestWhenDoesNotExistPreviousTCPForThisTeacher() throws Exception{
        // Arrange
        String teacherId = "XXX";
        String validRequestBody = """
            {
                "date": "2025-06-01",
                "teacherCategoryID": "05ab8bc8-33c2-46af-8988-d933e0256b89"
            }
        """;

        UpdateTeacherCategoryCommand command = mock(UpdateTeacherCategoryCommand.class);
        when(categoryAssembler.toUpdateTeacherCategoryCommand(eq(teacherId), any()))
                .thenReturn(command);

        when(careerService.updateTeacherCategory(command))
                .thenThrow(new NotFoundException("This teacher has no previous career progression record."));

        // Act & Assert
        mockMvc.perform(post("/teachers/" + teacherId + "/careerprogressions/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validRequestBody))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnBadRequestWhenDateIsBeforeTheLastUpdate() throws Exception {
        // Arrange
        String teacherId = "AAA";
        String validRequestBody = """
            {
                "date": "2021-06-01",
                "teacherCategoryID": "05ab8bc8-33c2-46af-8988-d933e0256b89"
            }
        """;

        UpdateTeacherCategoryCommand command = mock(UpdateTeacherCategoryCommand.class);
        when(categoryAssembler.toUpdateTeacherCategoryCommand(eq(teacherId), any()))
                .thenReturn(command);

        when(careerService.updateTeacherCategory(command))
                .thenThrow(new BusinessRuleViolationException("The date must be equal to or later than the previous update."));

        // Act & Assert
        mockMvc.perform(post("/teachers/" + teacherId + "/careerprogressions/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validRequestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestIfCategoryIsTheSameAsLastUpdate() throws Exception{
        // Arrange
        String teacherId = "AAA";
        String validRequestBody = """
            {
                "date": "2021-06-01",
                "teacherCategoryID": "05ab8bc8-33c2-46af-8988-d933e0256b89"
            }
        """;

        UpdateTeacherCategoryCommand command = mock(UpdateTeacherCategoryCommand.class);
        when(categoryAssembler.toUpdateTeacherCategoryCommand(eq(teacherId), any()))
                .thenReturn(command);

        when(careerService.updateTeacherCategory(command))
                .thenThrow(new BusinessRuleViolationException("The Teacher Category must be different to the previous update."));

        // Act & Assert
        mockMvc.perform(post("/teachers/" + teacherId + "/careerprogressions/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validRequestBody))
                .andExpect(status().isBadRequest());
    }

    // updateWorkingPercentage() method

    @Test
    void shouldReturnCreatedWhenUpdateTeacherWorkingPercentageSuccessful() throws Exception {
        // Arrange
        ITeacherRegistrationService teacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherHateoasAssembler teacherHateoasAssembler = mock(ITeacherHateoasAssembler.class);
        ICreateTeacherCareerProgressionService careerService = mock(ICreateTeacherCareerProgressionService.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);
        ITeacherWithRelevantDataService teacherWithRelevantDataService = mock(ITeacherWithRelevantDataService.class);
        TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IUpdateTeacherWorkingPercentageHateoasAssembler updateTeacherWorkingPercentageHateoasAssembler = mock(IUpdateTeacherWorkingPercentageHateoasAssembler.class);
        IUpdateTeacherCategoryHateoasAssembler doubleUpdateTeacherCategoryHateoasAssembler = mock(IUpdateTeacherCategoryHateoasAssembler.class);
        TeacherRestController controller = new TeacherRestController(teacherService, teacherAssembler,teacherHateoasAssembler, careerService, careerAssembler, teacherWithRelevantDataService, teacherWithRelevantDataAssembler,updateTeacherWorkingPercentageHateoasAssembler, doubleUpdateTeacherCategoryHateoasAssembler);

        UpdateTeacherWorkingPercentageRequestDTO request = mock(UpdateTeacherWorkingPercentageRequestDTO.class);
        String teacherID = "AAA";
        UpdateTeacherWorkingPercentageCommand command = mock(UpdateTeacherWorkingPercentageCommand.class);
        TeacherCareerProgression progression = mock(TeacherCareerProgression.class);
        UpdateTeacherWorkingPercentageResponseDTO responseDTO = mock(UpdateTeacherWorkingPercentageResponseDTO.class);
        EntityModel<UpdateTeacherWorkingPercentageResponseDTO> hateoasModel = mock(EntityModel.class);

        when(careerAssembler.toUpdateTeacherWorkingPercentageCommand(teacherID, request)).thenReturn(command);
        when(careerService.updateTeacherWorkingPercentageInTeacherCareerProgression(command)).thenReturn(Optional.of(progression));
        when(careerAssembler.toUpdateWorkingPercentageDTO(progression)).thenReturn(responseDTO);
        when(updateTeacherWorkingPercentageHateoasAssembler.toModel(responseDTO)).thenReturn(hateoasModel);

        // Act
        ResponseEntity<?> response = controller.updateTeacherWorkingPercentage(teacherID,request);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(hateoasModel, response.getBody());
    }

    @Test
    void shouldReturnBadRequestWhenUpdateTeacherWorkingPercentageReturnsEmpty() throws Exception {
        // Arrange
        ITeacherRegistrationService teacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherHateoasAssembler teacherHateoasAssembler = mock(ITeacherHateoasAssembler.class);
        ICreateTeacherCareerProgressionService careerService = mock(ICreateTeacherCareerProgressionService.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);
        ITeacherWithRelevantDataService teacherWithRelevantDataService = mock(ITeacherWithRelevantDataService.class);
        TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IUpdateTeacherWorkingPercentageHateoasAssembler updateTeacherWorkingPercentageHateoasAssembler = mock(IUpdateTeacherWorkingPercentageHateoasAssembler.class);
        IUpdateTeacherCategoryHateoasAssembler doubleUpdateTeacherCategoryHateoasAssembler = mock(IUpdateTeacherCategoryHateoasAssembler.class);
        TeacherRestController controller = new TeacherRestController(teacherService, teacherAssembler, teacherHateoasAssembler, careerService, careerAssembler, teacherWithRelevantDataService, teacherWithRelevantDataAssembler,updateTeacherWorkingPercentageHateoasAssembler, doubleUpdateTeacherCategoryHateoasAssembler);

        UpdateTeacherWorkingPercentageRequestDTO request = mock(UpdateTeacherWorkingPercentageRequestDTO.class);
        String teacherID = "AAA";
        UpdateTeacherWorkingPercentageCommand command = mock(UpdateTeacherWorkingPercentageCommand.class);
        UpdateTeacherWorkingPercentageResponseDTO responseDTO = mock(UpdateTeacherWorkingPercentageResponseDTO.class);
        TeacherCareerProgression progression = mock(TeacherCareerProgression.class);

        when(careerAssembler.toUpdateTeacherWorkingPercentageCommand(teacherID,request)).thenReturn(command);
        when(careerService.updateTeacherWorkingPercentageInTeacherCareerProgression(command)).thenReturn(Optional.empty());
        when(careerAssembler.toUpdateWorkingPercentageDTO(progression)).thenReturn(responseDTO);

        // Act
        ResponseEntity<?> response = controller.updateTeacherWorkingPercentage(responseDTO.teacherID(), request);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Unable to update teacher working percentage", response.getBody());
    }

    @Test
    void shouldReturnBadRequestWhenUpdateTeacherWorkingPercentageThrowsIllegalArgumentException() {
        // Arrange
        ITeacherRegistrationService teacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherHateoasAssembler teacherHateoasAssembler = mock(ITeacherHateoasAssembler.class);
        ICreateTeacherCareerProgressionService careerService = mock(ICreateTeacherCareerProgressionService.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);
        ITeacherWithRelevantDataService teacherWithRelevantDataService = mock(ITeacherWithRelevantDataService.class);
        TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IUpdateTeacherWorkingPercentageHateoasAssembler updateTeacherWorkingPercentageHateoasAssembler = mock(IUpdateTeacherWorkingPercentageHateoasAssembler.class);
        IUpdateTeacherCategoryHateoasAssembler doubleUpdateTeacherCategoryHateoasAssembler = mock(IUpdateTeacherCategoryHateoasAssembler.class);
        TeacherRestController controller = new TeacherRestController(teacherService, teacherAssembler, teacherHateoasAssembler, careerService, careerAssembler, teacherWithRelevantDataService, teacherWithRelevantDataAssembler,updateTeacherWorkingPercentageHateoasAssembler, doubleUpdateTeacherCategoryHateoasAssembler);

        UpdateTeacherWorkingPercentageRequestDTO request = mock(UpdateTeacherWorkingPercentageRequestDTO.class);
        String teacherID = "AAA";
        UpdateTeacherWorkingPercentageResponseDTO responseDTO = mock(UpdateTeacherWorkingPercentageResponseDTO.class);
        TeacherCareerProgression progression = mock(TeacherCareerProgression.class);

        when(careerAssembler.toUpdateTeacherWorkingPercentageCommand(teacherID, request)).thenThrow(new IllegalArgumentException("Invalid input"));
        when(careerAssembler.toUpdateWorkingPercentageDTO(progression)).thenReturn(responseDTO);

        // Act
        ResponseEntity<?> response = controller.updateTeacherWorkingPercentage(teacherID, request);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid input", response.getBody());
    }

    @Test
    void shouldThrowInternalServerErrorWhenUpdateTeacherWorkingPercentageThrowsUnexpectedException() throws Exception {
        // Arrange
        ITeacherRegistrationService teacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherHateoasAssembler teacherHateoasAssembler = mock(ITeacherHateoasAssembler.class);
        ICreateTeacherCareerProgressionService careerService = mock(ICreateTeacherCareerProgressionService.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);
        ITeacherWithRelevantDataService teacherWithRelevantDataService = mock(ITeacherWithRelevantDataService.class);
        TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IUpdateTeacherWorkingPercentageHateoasAssembler updateTeacherWorkingPercentageHateoasAssembler = mock(IUpdateTeacherWorkingPercentageHateoasAssembler.class);
        IUpdateTeacherCategoryHateoasAssembler doubleUpdateTeacherCategoryHateoasAssembler = mock(IUpdateTeacherCategoryHateoasAssembler.class);
        TeacherRestController controller = new TeacherRestController(teacherService, teacherAssembler, teacherHateoasAssembler, careerService, careerAssembler, teacherWithRelevantDataService, teacherWithRelevantDataAssembler,updateTeacherWorkingPercentageHateoasAssembler, doubleUpdateTeacherCategoryHateoasAssembler);

        UpdateTeacherWorkingPercentageRequestDTO request = mock(UpdateTeacherWorkingPercentageRequestDTO.class);
        String teacherID = "AAA";
        UpdateTeacherWorkingPercentageCommand command = mock(UpdateTeacherWorkingPercentageCommand.class);
        UpdateTeacherWorkingPercentageResponseDTO responseDTO = mock(UpdateTeacherWorkingPercentageResponseDTO.class);
        TeacherCareerProgression progression = mock(TeacherCareerProgression.class);

        when(careerAssembler.toUpdateTeacherWorkingPercentageCommand(teacherID, request)).thenReturn(command);
        when(careerService.updateTeacherWorkingPercentageInTeacherCareerProgression(command)).thenThrow(new RuntimeException("Unexpected error"));
        when(careerAssembler.toUpdateWorkingPercentageDTO(progression)).thenReturn(responseDTO);

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
                controller.updateTeacherWorkingPercentage(teacherID, request)
        );
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatusCode());
        assertEquals("Unexpected error", exception.getReason());
    }

    @Test
    void shouldReturnValidResponseWhenRegisterTeacherWithRelevantData () throws Exception {
        // Arrange
        ITeacherRegistrationService teacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherHateoasAssembler teacherHateoasAssembler = mock(ITeacherHateoasAssembler.class);
        ICreateTeacherCareerProgressionService careerService = mock(ICreateTeacherCareerProgressionService.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);
        ITeacherWithRelevantDataService teacherWithRelevantDataService = mock(ITeacherWithRelevantDataService.class);
        TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IUpdateTeacherWorkingPercentageHateoasAssembler updateTeacherWorkingPercentageHateoasAssembler = mock(IUpdateTeacherWorkingPercentageHateoasAssembler.class);
        IUpdateTeacherCategoryHateoasAssembler doubleUpdateTeacherCategoryHateoasAssembler = mock(IUpdateTeacherCategoryHateoasAssembler.class);
        TeacherRestController controller = new TeacherRestController(teacherService, teacherAssembler, teacherHateoasAssembler, careerService,careerAssembler, teacherWithRelevantDataService, teacherWithRelevantDataAssembler,updateTeacherWorkingPercentageHateoasAssembler, doubleUpdateTeacherCategoryHateoasAssembler);
        TeacherWithRelevantDataRequestDTO teacherWithRelevantDataRequestDTO = mock(TeacherWithRelevantDataRequestDTO.class);
        TeacherWithRelevantDataDTO teacherWithRelevantDataDTO = mock(TeacherWithRelevantDataDTO.class);

        TeacherID teacherID = mock(TeacherID.class);
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        NIF nif = mock(NIF.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AcademicBackground academicBackground = mock(AcademicBackground.class);
        Street street = mock(Street.class);
        PostalCode postalCode = mock(PostalCode.class);
        Location location = mock(Location.class);
        Country addressCountry = mock(Country.class);
        DepartmentID departmentID = mock(DepartmentID.class);
        Date date = mock(Date.class);
        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);
        WorkingPercentage workingPercentage = mock(WorkingPercentage.class);

        when(teacherWithRelevantDataAssembler.toTeacherID(teacherWithRelevantDataRequestDTO.id())).thenReturn(teacherID);
        when(teacherWithRelevantDataAssembler.toName(teacherWithRelevantDataRequestDTO.name())).thenReturn(name);
        when(teacherWithRelevantDataAssembler.toEmail(teacherWithRelevantDataRequestDTO.email())).thenReturn(email);
        when(teacherWithRelevantDataAssembler.toNif(teacherWithRelevantDataRequestDTO.nif(), teacherWithRelevantDataRequestDTO.country())).thenReturn(nif);
        when(teacherWithRelevantDataAssembler.toPhoneNumber(teacherWithRelevantDataRequestDTO.countryCode(), teacherWithRelevantDataRequestDTO.phoneNumber())).thenReturn(phoneNumber);
        when(teacherWithRelevantDataAssembler.toAcademicBackground(teacherWithRelevantDataRequestDTO.academicBackground())).thenReturn(academicBackground);
        when(teacherWithRelevantDataAssembler.toStreet(teacherWithRelevantDataRequestDTO.street())).thenReturn(street);
        when(teacherWithRelevantDataAssembler.toPostalCode(teacherWithRelevantDataRequestDTO.postalCode())).thenReturn(postalCode);
        when(teacherWithRelevantDataAssembler.toLocation(teacherWithRelevantDataRequestDTO.location())).thenReturn(location);
        when(teacherWithRelevantDataAssembler.toCountry(teacherWithRelevantDataRequestDTO.country())).thenReturn(addressCountry);
        when(teacherWithRelevantDataAssembler.toDepartmentID(teacherWithRelevantDataRequestDTO.departmentID())).thenReturn(departmentID);
        when(teacherWithRelevantDataAssembler.toDate(teacherWithRelevantDataRequestDTO.date())).thenReturn(date);
        when(teacherWithRelevantDataAssembler.toTeacherCategoryID(teacherWithRelevantDataRequestDTO.teacherCategoryID())).thenReturn(teacherCategoryID);
        when(teacherWithRelevantDataAssembler.toWorkingPercentage(teacherWithRelevantDataRequestDTO.workingPercentage())).thenReturn(workingPercentage);
        when(teacherWithRelevantDataService.registerTeacherWithRelevantData(teacherID, name, email, nif, phoneNumber, academicBackground, street, postalCode, location, addressCountry, departmentID, date, teacherCategoryID, workingPercentage)).thenReturn(teacherWithRelevantDataDTO);

        //Act
        ResponseEntity<?> response = controller.registerTeacherWithRelevantData(teacherWithRelevantDataRequestDTO);

        //Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(teacherWithRelevantDataDTO, response.getBody());
    }

    @Test
    void shouldReturnConflictWhenBusinessRuleViolationExceptionWhenRegisterTeacherWithRelevantData() throws Exception {
        // Arrange
        ITeacherRegistrationService teacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherHateoasAssembler teacherHateoasAssembler = mock(ITeacherHateoasAssembler.class);
        ICreateTeacherCareerProgressionService careerService = mock(ICreateTeacherCareerProgressionService.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);
        ITeacherWithRelevantDataService teacherWithRelevantDataService = mock(ITeacherWithRelevantDataService.class);
        TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IUpdateTeacherWorkingPercentageHateoasAssembler updateTeacherWorkingPercentageHateoasAssembler = mock(IUpdateTeacherWorkingPercentageHateoasAssembler.class);
        IUpdateTeacherCategoryHateoasAssembler doubleUpdateTeacherCategoryHateoasAssembler = mock(IUpdateTeacherCategoryHateoasAssembler.class);
        TeacherRestController controller = new TeacherRestController(teacherService, teacherAssembler,teacherHateoasAssembler, careerService,careerAssembler, teacherWithRelevantDataService, teacherWithRelevantDataAssembler,updateTeacherWorkingPercentageHateoasAssembler, doubleUpdateTeacherCategoryHateoasAssembler);
        TeacherWithRelevantDataRequestDTO teacherWithRelevantDataRequestDTO = mock(TeacherWithRelevantDataRequestDTO.class);
        TeacherWithRelevantDataDTO teacherWithRelevantDataDTO = mock(TeacherWithRelevantDataDTO.class);

        TeacherID teacherID = mock(TeacherID.class);
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        NIF nif = mock(NIF.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AcademicBackground academicBackground = mock(AcademicBackground.class);
        Street street = mock(Street.class);
        PostalCode postalCode = mock(PostalCode.class);
        Location location = mock(Location.class);
        Country addressCountry = mock(Country.class);
        DepartmentID departmentID = mock(DepartmentID.class);
        Date date = mock(Date.class);
        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);
        WorkingPercentage workingPercentage = mock(WorkingPercentage.class);

        when(teacherWithRelevantDataAssembler.toTeacherID(teacherWithRelevantDataRequestDTO.id())).thenReturn(teacherID);
        when(teacherWithRelevantDataAssembler.toName(teacherWithRelevantDataRequestDTO.name())).thenReturn(name);
        when(teacherWithRelevantDataAssembler.toEmail(teacherWithRelevantDataRequestDTO.email())).thenReturn(email);
        when(teacherWithRelevantDataAssembler.toNif(teacherWithRelevantDataRequestDTO.nif(), teacherWithRelevantDataRequestDTO.country())).thenReturn(nif);
        when(teacherWithRelevantDataAssembler.toPhoneNumber(teacherWithRelevantDataRequestDTO.countryCode(), teacherWithRelevantDataRequestDTO.phoneNumber())).thenReturn(phoneNumber);
        when(teacherWithRelevantDataAssembler.toAcademicBackground(teacherWithRelevantDataRequestDTO.academicBackground())).thenReturn(academicBackground);
        when(teacherWithRelevantDataAssembler.toStreet(teacherWithRelevantDataRequestDTO.street())).thenReturn(street);
        when(teacherWithRelevantDataAssembler.toPostalCode(teacherWithRelevantDataRequestDTO.postalCode())).thenReturn(postalCode);
        when(teacherWithRelevantDataAssembler.toLocation(teacherWithRelevantDataRequestDTO.location())).thenReturn(location);
        when(teacherWithRelevantDataAssembler.toCountry(teacherWithRelevantDataRequestDTO.country())).thenReturn(addressCountry);
        when(teacherWithRelevantDataAssembler.toDepartmentID(teacherWithRelevantDataRequestDTO.departmentID())).thenReturn(departmentID);
        when(teacherWithRelevantDataAssembler.toDate(teacherWithRelevantDataRequestDTO.date())).thenReturn(date);
        when(teacherWithRelevantDataAssembler.toTeacherCategoryID(teacherWithRelevantDataRequestDTO.teacherCategoryID())).thenReturn(teacherCategoryID);
        when(teacherWithRelevantDataAssembler.toWorkingPercentage(teacherWithRelevantDataRequestDTO.workingPercentage())).thenReturn(workingPercentage);
        when(teacherWithRelevantDataService.registerTeacherWithRelevantData(teacherID, name, email, nif, phoneNumber, academicBackground, street, postalCode, location, addressCountry, departmentID, date, teacherCategoryID, workingPercentage)).thenThrow(new BusinessRuleViolationException("Teacher already exists"));

        //Act
        ResponseEntity<?> response = controller.registerTeacherWithRelevantData(teacherWithRelevantDataRequestDTO);
        // Assert
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(409, response.getStatusCodeValue());
        assertEquals("Teacher already exists", response.getBody());
    }

    @Test
    void shouldReturnBadRequestWhenIllegalArgumentWhenRegisteringTeacherWithRelevantData() throws Exception {
        // Arrange
        ITeacherRegistrationService teacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherHateoasAssembler teacherHateoasAssembler = mock(ITeacherHateoasAssembler.class);
        ICreateTeacherCareerProgressionService careerService = mock(ICreateTeacherCareerProgressionService.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);
        ITeacherWithRelevantDataService teacherWithRelevantDataService = mock(ITeacherWithRelevantDataService.class);
        TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IUpdateTeacherWorkingPercentageHateoasAssembler updateTeacherWorkingPercentageHateoasAssembler = mock(IUpdateTeacherWorkingPercentageHateoasAssembler.class);
        IUpdateTeacherCategoryHateoasAssembler doubleUpdateTeacherCategoryHateoasAssembler = mock(IUpdateTeacherCategoryHateoasAssembler.class);
        TeacherRestController controller = new TeacherRestController(teacherService, teacherAssembler, teacherHateoasAssembler, careerService,careerAssembler, teacherWithRelevantDataService, teacherWithRelevantDataAssembler,updateTeacherWorkingPercentageHateoasAssembler, doubleUpdateTeacherCategoryHateoasAssembler);
        TeacherWithRelevantDataRequestDTO teacherWithRelevantDataRequestDTO = mock(TeacherWithRelevantDataRequestDTO.class);
        TeacherWithRelevantDataDTO teacherWithRelevantDataDTO = mock(TeacherWithRelevantDataDTO.class);

        TeacherID teacherID = mock(TeacherID.class);
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        NIF nif = mock(NIF.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AcademicBackground academicBackground = mock(AcademicBackground.class);
        Street street = mock(Street.class);
        PostalCode postalCode = mock(PostalCode.class);
        Location location = mock(Location.class);
        Country addressCountry = mock(Country.class);
        DepartmentID departmentID = mock(DepartmentID.class);
        Date date = mock(Date.class);
        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);
        WorkingPercentage workingPercentage = mock(WorkingPercentage.class);

        when(teacherWithRelevantDataAssembler.toTeacherID(teacherWithRelevantDataRequestDTO.id())).thenReturn(teacherID);
        when(teacherWithRelevantDataAssembler.toName(teacherWithRelevantDataRequestDTO.name())).thenReturn(name);
        when(teacherWithRelevantDataAssembler.toEmail(teacherWithRelevantDataRequestDTO.email())).thenReturn(email);
        when(teacherWithRelevantDataAssembler.toNif(teacherWithRelevantDataRequestDTO.nif(), teacherWithRelevantDataRequestDTO.country())).thenReturn(nif);
        when(teacherWithRelevantDataAssembler.toPhoneNumber(teacherWithRelevantDataRequestDTO.countryCode(), teacherWithRelevantDataRequestDTO.phoneNumber())).thenReturn(phoneNumber);
        when(teacherWithRelevantDataAssembler.toAcademicBackground(teacherWithRelevantDataRequestDTO.academicBackground())).thenReturn(academicBackground);
        when(teacherWithRelevantDataAssembler.toStreet(teacherWithRelevantDataRequestDTO.street())).thenReturn(street);
        when(teacherWithRelevantDataAssembler.toPostalCode(teacherWithRelevantDataRequestDTO.postalCode())).thenReturn(postalCode);
        when(teacherWithRelevantDataAssembler.toLocation(teacherWithRelevantDataRequestDTO.location())).thenReturn(location);
        when(teacherWithRelevantDataAssembler.toCountry(teacherWithRelevantDataRequestDTO.country())).thenReturn(addressCountry);
        when(teacherWithRelevantDataAssembler.toDepartmentID(teacherWithRelevantDataRequestDTO.departmentID())).thenReturn(departmentID);
        when(teacherWithRelevantDataAssembler.toDate(teacherWithRelevantDataRequestDTO.date())).thenReturn(date);
        when(teacherWithRelevantDataAssembler.toTeacherCategoryID(teacherWithRelevantDataRequestDTO.teacherCategoryID())).thenReturn(teacherCategoryID);
        when(teacherWithRelevantDataAssembler.toWorkingPercentage(teacherWithRelevantDataRequestDTO.workingPercentage())).thenReturn(workingPercentage);
        when(teacherWithRelevantDataService.registerTeacherWithRelevantData(teacherID, name, email, nif, phoneNumber, academicBackground, street, postalCode, location, addressCountry, departmentID, date, teacherCategoryID, workingPercentage)).thenThrow(new IllegalArgumentException("Invalid id"));

        //Act
        ResponseEntity<?> response = controller.registerTeacherWithRelevantData(teacherWithRelevantDataRequestDTO);
        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Invalid id", response.getBody());
    }

    @Test
    void shouldReturnInternalServerErrorWhenUnexpectedExceptionWhenRegisteringTeacherWithRelevantData() throws Exception {
        // Arrange
        ITeacherRegistrationService teacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherHateoasAssembler teacherHateoasAssembler = mock(ITeacherHateoasAssembler.class);
        ICreateTeacherCareerProgressionService careerService = mock(ICreateTeacherCareerProgressionService.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);
        ITeacherWithRelevantDataService teacherWithRelevantDataService = mock(ITeacherWithRelevantDataService.class);
        TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IUpdateTeacherWorkingPercentageHateoasAssembler updateTeacherWorkingPercentageHateoasAssembler = mock(IUpdateTeacherWorkingPercentageHateoasAssembler.class);
        IUpdateTeacherCategoryHateoasAssembler doubleUpdateTeacherCategoryHateoasAssembler = mock(IUpdateTeacherCategoryHateoasAssembler.class);
        TeacherRestController controller = new TeacherRestController(teacherService, teacherAssembler, teacherHateoasAssembler, careerService,careerAssembler, teacherWithRelevantDataService, teacherWithRelevantDataAssembler,updateTeacherWorkingPercentageHateoasAssembler, doubleUpdateTeacherCategoryHateoasAssembler);
        TeacherWithRelevantDataRequestDTO teacherWithRelevantDataRequestDTO = mock(TeacherWithRelevantDataRequestDTO.class);
        TeacherWithRelevantDataDTO teacherWithRelevantDataDTO = mock(TeacherWithRelevantDataDTO.class);

        TeacherID teacherID = mock(TeacherID.class);
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        NIF nif = mock(NIF.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AcademicBackground academicBackground = mock(AcademicBackground.class);
        Street street = mock(Street.class);
        PostalCode postalCode = mock(PostalCode.class);
        Location location = mock(Location.class);
        Country addressCountry = mock(Country.class);
        DepartmentID departmentID = mock(DepartmentID.class);
        Date date = mock(Date.class);
        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);
        WorkingPercentage workingPercentage = mock(WorkingPercentage.class);

        when(teacherWithRelevantDataAssembler.toTeacherID(teacherWithRelevantDataRequestDTO.id())).thenReturn(teacherID);
        when(teacherWithRelevantDataAssembler.toName(teacherWithRelevantDataRequestDTO.name())).thenReturn(name);
        when(teacherWithRelevantDataAssembler.toEmail(teacherWithRelevantDataRequestDTO.email())).thenReturn(email);
        when(teacherWithRelevantDataAssembler.toNif(teacherWithRelevantDataRequestDTO.nif(), teacherWithRelevantDataRequestDTO.country())).thenReturn(nif);
        when(teacherWithRelevantDataAssembler.toPhoneNumber(teacherWithRelevantDataRequestDTO.countryCode(), teacherWithRelevantDataRequestDTO.phoneNumber())).thenReturn(phoneNumber);
        when(teacherWithRelevantDataAssembler.toAcademicBackground(teacherWithRelevantDataRequestDTO.academicBackground())).thenReturn(academicBackground);
        when(teacherWithRelevantDataAssembler.toStreet(teacherWithRelevantDataRequestDTO.street())).thenReturn(street);
        when(teacherWithRelevantDataAssembler.toPostalCode(teacherWithRelevantDataRequestDTO.postalCode())).thenReturn(postalCode);
        when(teacherWithRelevantDataAssembler.toLocation(teacherWithRelevantDataRequestDTO.location())).thenReturn(location);
        when(teacherWithRelevantDataAssembler.toCountry(teacherWithRelevantDataRequestDTO.country())).thenReturn(addressCountry);
        when(teacherWithRelevantDataAssembler.toDepartmentID(teacherWithRelevantDataRequestDTO.departmentID())).thenReturn(departmentID);
        when(teacherWithRelevantDataAssembler.toDate(teacherWithRelevantDataRequestDTO.date())).thenReturn(date);
        when(teacherWithRelevantDataAssembler.toTeacherCategoryID(teacherWithRelevantDataRequestDTO.teacherCategoryID())).thenReturn(teacherCategoryID);
        when(teacherWithRelevantDataAssembler.toWorkingPercentage(teacherWithRelevantDataRequestDTO.workingPercentage())).thenReturn(workingPercentage);
        when(teacherWithRelevantDataService.registerTeacherWithRelevantData(teacherID, name, email, nif, phoneNumber, academicBackground, street, postalCode, location, addressCountry, departmentID, date, teacherCategoryID, workingPercentage)).thenThrow(new RuntimeException("Unexpected error occurred"));

        //Act
        ResponseEntity<?> response = controller.registerTeacherWithRelevantData(teacherWithRelevantDataRequestDTO);
        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(500, response.getStatusCodeValue());
        assertEquals("Unexpected error occurred", response.getBody());
    }

    @Test
    void shouldReturnTeacherDTOWhenTeacherExists() {
        // Arrange
        ITeacherRegistrationService teacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherHateoasAssembler teacherHateoasAssembler = mock(ITeacherHateoasAssembler.class);
        ICreateTeacherCareerProgressionService careerService = mock(ICreateTeacherCareerProgressionService.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);
        ITeacherWithRelevantDataService teacherWithRelevantDataService = mock(ITeacherWithRelevantDataService.class);
        TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IUpdateTeacherWorkingPercentageHateoasAssembler updateTeacherWorkingPercentageHateoasAssembler = mock(IUpdateTeacherWorkingPercentageHateoasAssembler.class);
        IUpdateTeacherCategoryHateoasAssembler doubleUpdateTeacherCategoryHateoasAssembler = mock(IUpdateTeacherCategoryHateoasAssembler.class);

        TeacherRestController controller = new TeacherRestController(teacherService, teacherAssembler,teacherHateoasAssembler, careerService, careerAssembler, teacherWithRelevantDataService, teacherWithRelevantDataAssembler, updateTeacherWorkingPercentageHateoasAssembler, doubleUpdateTeacherCategoryHateoasAssembler);

        String id = "JAB";
        TeacherID teacherID = mock(TeacherID.class);
        Teacher teacher = mock(Teacher.class);
        TeacherDTO teacherDTO = mock(TeacherDTO.class);

        when(teacherAssembler.fromStringToTeacherID(id)).thenReturn(teacherID);
        when(teacherService.getTeacherById(teacherID)).thenReturn(Optional.of(teacher));
        when(teacherAssembler.toDTO(teacher)).thenReturn(teacherDTO);

        // Act
        ResponseEntity<?> response = controller.getTeacherById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(teacherDTO, response.getBody());
    }

    @Test
    void shouldReturnNotFoundWhenTeacherDoesNotExist() {
        // Arrange
        ITeacherRegistrationService teacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherHateoasAssembler teacherHateoasAssembler = mock(ITeacherHateoasAssembler.class);
        ICreateTeacherCareerProgressionService careerService = mock(ICreateTeacherCareerProgressionService.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);
        ITeacherWithRelevantDataService teacherWithRelevantDataService = mock(ITeacherWithRelevantDataService.class);
        TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IUpdateTeacherWorkingPercentageHateoasAssembler updateTeacherWorkingPercentageHateoasAssembler = mock(IUpdateTeacherWorkingPercentageHateoasAssembler.class);
        IUpdateTeacherCategoryHateoasAssembler doubleUpdateTeacherCategoryHateoasAssembler = mock(IUpdateTeacherCategoryHateoasAssembler.class);

        TeacherRestController controller = new TeacherRestController(teacherService, teacherAssembler, teacherHateoasAssembler, careerService, careerAssembler, teacherWithRelevantDataService, teacherWithRelevantDataAssembler, updateTeacherWorkingPercentageHateoasAssembler, doubleUpdateTeacherCategoryHateoasAssembler);

        String id = "JAB";
        TeacherID teacherID = mock(TeacherID.class);

        when(teacherAssembler.fromStringToTeacherID(id)).thenReturn(teacherID);
        when(teacherService.getTeacherById(teacherID)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<?> response = controller.getTeacherById(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Teacher not found", response.getBody());
    }

    @Test
    void shouldReturnBadRequestWhenIllegalArgumentExceptionThrown() {
        // Arrange
        ITeacherRegistrationService teacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherHateoasAssembler teacherHateoasAssembler = mock(ITeacherHateoasAssembler.class);
        ICreateTeacherCareerProgressionService careerService = mock(ICreateTeacherCareerProgressionService.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);
        ITeacherWithRelevantDataService teacherWithRelevantDataService = mock(ITeacherWithRelevantDataService.class);
        TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IUpdateTeacherWorkingPercentageHateoasAssembler updateTeacherWorkingPercentageHateoasAssembler = mock(IUpdateTeacherWorkingPercentageHateoasAssembler.class);
        IUpdateTeacherCategoryHateoasAssembler doubleUpdateTeacherCategoryHateoasAssembler = mock(IUpdateTeacherCategoryHateoasAssembler.class);

        TeacherRestController controller = new TeacherRestController(teacherService, teacherAssembler, teacherHateoasAssembler, careerService, careerAssembler, teacherWithRelevantDataService, teacherWithRelevantDataAssembler, updateTeacherWorkingPercentageHateoasAssembler, doubleUpdateTeacherCategoryHateoasAssembler);

        String id = "JAB";
        when(teacherAssembler.fromStringToTeacherID(id)).thenThrow(new IllegalArgumentException("Invalid id"));

        // Act
        ResponseEntity<?> response = controller.getTeacherById(id);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid id", response.getBody());
    }

    @Test
    void shouldReturnInternalServerErrorWhenUnexpectedExceptionThrown() {
        // Arrange
        ITeacherRegistrationService teacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherHateoasAssembler teacherHateoasAssembler = mock(ITeacherHateoasAssembler.class);
        ICreateTeacherCareerProgressionService careerService = mock(ICreateTeacherCareerProgressionService.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);
        ITeacherWithRelevantDataService teacherWithRelevantDataService = mock(ITeacherWithRelevantDataService.class);
        TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IUpdateTeacherWorkingPercentageHateoasAssembler updateTeacherWorkingPercentageHateoasAssembler = mock(IUpdateTeacherWorkingPercentageHateoasAssembler.class);
        IUpdateTeacherCategoryHateoasAssembler doubleUpdateTeacherCategoryHateoasAssembler = mock(IUpdateTeacherCategoryHateoasAssembler.class);

        TeacherRestController controller = new TeacherRestController(teacherService, teacherAssembler, teacherHateoasAssembler, careerService, careerAssembler, teacherWithRelevantDataService, teacherWithRelevantDataAssembler, updateTeacherWorkingPercentageHateoasAssembler, doubleUpdateTeacherCategoryHateoasAssembler);

        String id = "JAB";
        when(teacherAssembler.fromStringToTeacherID(id)).thenThrow(new RuntimeException("Unexpected error occurred"));

        // Act
        ResponseEntity<?> response = controller.getTeacherById(id);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Unexpected error occurred", response.getBody());
    }

}
