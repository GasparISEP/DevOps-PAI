package PAI.controllerRest;

import PAI.assembler.totalEnrolledStudentsInProgrammesByDepartmentAndSchoolYear.ITotalEnrolledStudentsAssembler;

import PAI.dto.totalEnrolledStudents.TotalEnrolledStudentsCommand;
import PAI.dto.totalEnrolledStudents.TotalEnrolledStudentsRequest;

import PAI.service.totalEnrolledStudentsInProgrammesByDepartmentAndSchoolYear.ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgrammeEnrolmentRestControllerTest {

    @Mock
    private ITotalEnrolledStudentsAssembler totalEnrolledStudentsAssembler;

    @Mock
    private ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService totalEnrolledStudentsService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnTotalNumberOfEnrolledStudents() throws Exception {
        // Arrange
        ProgrammeEnrolmentRestController programmeEnrolmentRestController = new ProgrammeEnrolmentRestController(totalEnrolledStudentsAssembler,totalEnrolledStudentsService);

        String departmentId ="AAA";
        String schoolYearId ="d19b1a3f-9b68-41c4-9f35-b061e4799d9d";
        TotalEnrolledStudentsCommand command = mock(TotalEnrolledStudentsCommand.class);
        when(totalEnrolledStudentsAssembler.fromRequestToCommand(departmentId, schoolYearId)).thenReturn(command);
        when(totalEnrolledStudentsService.getTotalEnrolledStudentsInProgrammesByDepartmentAndYear(command)).thenReturn(100);

        // Act
        ResponseEntity<?> result = programmeEnrolmentRestController.countByDepartmentAndSchoolYear(departmentId, schoolYearId);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(100, result.getBody());
        verify(totalEnrolledStudentsAssembler).fromRequestToCommand(departmentId, schoolYearId);
        verify(totalEnrolledStudentsService).getTotalEnrolledStudentsInProgrammesByDepartmentAndYear(command);
    }

    @Test
    void shouldReturnBadRequestWhenDepartmentIdIsNull() throws Exception {
        // Arrange
        String departmentId = null;
        String schoolYearId ="d19b1a3f-9b68-41c4-9f35-b061e4799d9d";
        ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService totalEnrolledStudentsService = mock(ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService.class);
        ProgrammeEnrolmentRestController programmeEnrolmentRestController = new ProgrammeEnrolmentRestController(totalEnrolledStudentsAssembler,totalEnrolledStudentsService);

        // Act
        ResponseEntity<?> result = programmeEnrolmentRestController.countByDepartmentAndSchoolYear(departmentId, schoolYearId);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("departmentID cannot be null", result.getBody());
    }

    @Test
    void shouldReturnBadRequestWhenSchoolYearIdIsNull() throws Exception {
        // Arrange
        String departmentId ="AAA";
        String schoolYearId = null;
        ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService totalEnrolledStudentsService = mock(ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService.class);
        ProgrammeEnrolmentRestController programmeEnrolmentRestController = new ProgrammeEnrolmentRestController(totalEnrolledStudentsAssembler,totalEnrolledStudentsService);

        // Act
        ResponseEntity<?> result = programmeEnrolmentRestController.countByDepartmentAndSchoolYear(departmentId, schoolYearId);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("schoolYearID cannot be null", result.getBody());
    }

    @Test
    void shouldReturnBadRequestWhenDepartmentIDIsNotInTheSystem()  {
        // Arrange
        String departmentId ="AAA";
        String schoolYearId ="d19b1a3f-9b68-41c4-9f35-b061e4799d9d";
        ITotalEnrolledStudentsAssembler totalEnrolledStudentsAssembler = mock(ITotalEnrolledStudentsAssembler.class);
        ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService totalEnrolledStudentsService = mock(ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService.class);
        ProgrammeEnrolmentRestController programmeEnrolmentRestController = new ProgrammeEnrolmentRestController(totalEnrolledStudentsAssembler,totalEnrolledStudentsService);

        IllegalArgumentException exception = new IllegalArgumentException("Invalid Department ID");
        when(totalEnrolledStudentsAssembler.fromRequestToCommand(departmentId, schoolYearId)).thenThrow(exception);

        // Act
        ResponseEntity<?> result = programmeEnrolmentRestController.countByDepartmentAndSchoolYear(departmentId, schoolYearId);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("Invalid Department ID", result.getBody());
    }

    @Test
    void shouldReturnInternalServerErrorWhenDepartmentIDIsNotInTheSystem()  {
        // Arrange
        String departmentId ="AAA";
        String schoolYearId ="d19b1a3f-9b68-41c4-9f35-b061e4799d9d";
        ITotalEnrolledStudentsAssembler totalEnrolledStudentsAssembler = mock(ITotalEnrolledStudentsAssembler.class);
        ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService totalEnrolledStudentsService = mock(ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService.class);
        ProgrammeEnrolmentRestController programmeEnrolmentRestController = new ProgrammeEnrolmentRestController(totalEnrolledStudentsAssembler,totalEnrolledStudentsService);


        RuntimeException exception = new RuntimeException("Unexpected error occurred");
        TotalEnrolledStudentsRequest request = mock(TotalEnrolledStudentsRequest.class);
        when(totalEnrolledStudentsAssembler.fromRequestToCommand(departmentId, schoolYearId)).thenThrow(exception);

        // Act
        ResponseEntity<?> result = programmeEnrolmentRestController.countByDepartmentAndSchoolYear(departmentId, schoolYearId);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        assertEquals("Unexpected error occurred", result.getBody());
    }
}