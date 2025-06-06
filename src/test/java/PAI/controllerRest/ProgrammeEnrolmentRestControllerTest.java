package PAI.controllerRest;

import PAI.VOs.*;
import PAI.assembler.totalEnrolledStudentsInProgrammesByDepartmentAndSchoolYear.ITotalEnrolledStudentsAssembler;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import PAI.dto.programmeEnrolment.IProgrammeEnrolmentAssembler;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentDTO;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentResponseDTO;
import PAI.dto.totalEnrolledStudents.TotalEnrolledStudentsCommand;
import PAI.dto.totalEnrolledStudents.TotalEnrolledStudentsRequest;
import PAI.service.programmeEnrolment.IProgrammeEnrolmentService;
import PAI.service.totalEnrolledStudentsInProgrammesByDepartmentAndSchoolYear.ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgrammeEnrolmentRestControllerTest {

    @Mock
    private ITotalEnrolledStudentsAssembler totalEnrolledStudentsAssembler;

    @Mock
    private ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService totalEnrolledStudentsService;


    @InjectMocks
    private ProgrammeEnrolmentRestController programmeEnrolmentRestController;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnTotalNumberOfEnrolledStudents() throws Exception {
        // Arrange
        ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService iTotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService = mock(ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService.class);

        ProgrammeEnrolmentRestController programmeEnrolmentRestController = new ProgrammeEnrolmentRestController(totalEnrolledStudentsAssembler,totalEnrolledStudentsService);

        TotalEnrolledStudentsRequest request = mock(TotalEnrolledStudentsRequest.class);
        TotalEnrolledStudentsCommand command = mock(TotalEnrolledStudentsCommand.class);
        when(totalEnrolledStudentsAssembler.fromRequestToCommand(request)).thenReturn(command);
        when(totalEnrolledStudentsService.getTotalEnrolledStudentsInProgrammesByDepartmentAndYear(command)).thenReturn(100);

        // Act
        ResponseEntity<?> result = programmeEnrolmentRestController.countByDepartmentAndSchoolYear(request);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(100, result.getBody());
        verify(totalEnrolledStudentsAssembler).fromRequestToCommand(request);
        verify(totalEnrolledStudentsService).getTotalEnrolledStudentsInProgrammesByDepartmentAndYear(command);
    }

    @Test
    void shouldReturnBadRequestWhenRequestIsNull() throws Exception {
        // Arrange

        ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService totalEnrolledStudentsService = mock(ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService.class);
        ProgrammeEnrolmentRestController programmeEnrolmentRestController = new ProgrammeEnrolmentRestController(totalEnrolledStudentsAssembler,totalEnrolledStudentsService);


        TotalEnrolledStudentsRequest request = null;

        // Act
        ResponseEntity<?> result = programmeEnrolmentRestController.countByDepartmentAndSchoolYear(request);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("Request cannot be null", result.getBody());
    }

    @Test
    void shouldReturnBadRequestWhenDepartmentIDIsNotInTheSystem() throws Exception {
        // Arrange
        IProgrammeEnrolmentService programmeEnrolmentService = mock(IProgrammeEnrolmentService.class);
        IProgrammeEnrolmentAssembler programmeEnrolmentAssembler = mock(IProgrammeEnrolmentAssembler.class);
        ITotalEnrolledStudentsAssembler totalEnrolledStudentsAssembler = mock(ITotalEnrolledStudentsAssembler.class);
        ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService totalEnrolledStudentsService = mock(ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService.class);
        ProgrammeEnrolmentRestController programmeEnrolmentRestController = new ProgrammeEnrolmentRestController(totalEnrolledStudentsAssembler,totalEnrolledStudentsService);


        IllegalArgumentException exception = new IllegalArgumentException("Invalid Department ID");
        TotalEnrolledStudentsRequest request = mock(TotalEnrolledStudentsRequest.class);
        when(totalEnrolledStudentsAssembler.fromRequestToCommand(request)).thenThrow(exception);
        // Act
        ResponseEntity<?> result = programmeEnrolmentRestController.countByDepartmentAndSchoolYear(request);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("Invalid Department ID", result.getBody());
    }

    @Test
    void shouldReturnInternalServerErrorWhenDepartmentIDIsNotInTheSystem() throws Exception {
        // Arrange
        IProgrammeEnrolmentService programmeEnrolmentService = mock(IProgrammeEnrolmentService.class);
        IProgrammeEnrolmentAssembler programmeEnrolmentAssembler = mock(IProgrammeEnrolmentAssembler.class);
        ITotalEnrolledStudentsAssembler totalEnrolledStudentsAssembler = mock(ITotalEnrolledStudentsAssembler.class);
        ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService totalEnrolledStudentsService = mock(ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService.class);
        ProgrammeEnrolmentRestController programmeEnrolmentRestController = new ProgrammeEnrolmentRestController(totalEnrolledStudentsAssembler,totalEnrolledStudentsService);


        RuntimeException exception = new RuntimeException("Unexpected error occurred");
        TotalEnrolledStudentsRequest request = mock(TotalEnrolledStudentsRequest.class);
        when(totalEnrolledStudentsAssembler.fromRequestToCommand(request)).thenThrow(exception);

        // Act
        ResponseEntity<?> result = programmeEnrolmentRestController.countByDepartmentAndSchoolYear(request);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        assertEquals("Unexpected error occurred", result.getBody());
    }
}