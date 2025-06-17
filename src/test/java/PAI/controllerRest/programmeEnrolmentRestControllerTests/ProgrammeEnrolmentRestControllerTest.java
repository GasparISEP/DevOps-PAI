package PAI.controllerRest.programmeEnrolmentRestControllerTests;

import PAI.VOs.*;
import PAI.assembler.programmeEdition.IProgrammeEditionControllerAssembler;
import PAI.assembler.programmeEnrolment.IProgrammeEnrolmentAssembler;
import PAI.assembler.totalEnrolledStudentsInProgrammesByDepartmentAndSchoolYear.ITotalEnrolledStudentsAssembler;

import PAI.controllerRest.ProgrammeEnrolmentRestController;
import PAI.dto.programmeEdition.ProgrammeEditionWithNameAndDescriptionResponseDTO;
import PAI.dto.totalEnrolledStudents.TotalEnrolledStudentsCommand;
import PAI.dto.totalEnrolledStudents.TotalEnrolledStudentsRequest;

import PAI.service.programmeEditionEnrolment.IStudentProgrammeEditionEnrolmentService;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentIdDTO;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import PAI.VOs.ProgrammeEnrolmentGeneratedID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.ProgrammeEditionID;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgrammeEnrolmentRestControllerTest {

    @Mock
    private ITotalEnrolledStudentsAssembler totalEnrolledStudentsAssembler;

    @Mock
    private ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService totalEnrolledStudentsService;

    @Mock
    private IProgrammeEnrolmentAssembler programmeEnrolmentAssembler;

    @Mock
    private IStudentProgrammeEditionEnrolmentService iStudentProgrammeEnrolmentService;

    @Mock
    private IProgrammeEditionControllerAssembler iProgrammeEditionControllerAssembler;

    @InjectMocks
    private ProgrammeEnrolmentRestController programmeEnrolmentRestController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(programmeEnrolmentRestController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void shouldReturnTotalNumberOfEnrolledStudents() throws Exception {
        // Arrange

        ProgrammeEnrolmentRestController programmeEnrolmentRestController = new ProgrammeEnrolmentRestController(totalEnrolledStudentsAssembler,totalEnrolledStudentsService, programmeEnrolmentAssembler, iStudentProgrammeEnrolmentService, iProgrammeEditionControllerAssembler);

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
        ProgrammeEnrolmentRestController programmeEnrolmentRestController = new ProgrammeEnrolmentRestController(totalEnrolledStudentsAssembler,totalEnrolledStudentsService, programmeEnrolmentAssembler, iStudentProgrammeEnrolmentService, iProgrammeEditionControllerAssembler);

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
        ProgrammeEnrolmentRestController programmeEnrolmentRestController = new ProgrammeEnrolmentRestController(totalEnrolledStudentsAssembler,totalEnrolledStudentsService, programmeEnrolmentAssembler, iStudentProgrammeEnrolmentService, iProgrammeEditionControllerAssembler);

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
        ProgrammeEnrolmentRestController programmeEnrolmentRestController = new ProgrammeEnrolmentRestController(totalEnrolledStudentsAssembler,totalEnrolledStudentsService, programmeEnrolmentAssembler, iStudentProgrammeEnrolmentService, iProgrammeEditionControllerAssembler);

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
        ProgrammeEnrolmentRestController programmeEnrolmentRestController = new ProgrammeEnrolmentRestController(totalEnrolledStudentsAssembler,totalEnrolledStudentsService, programmeEnrolmentAssembler, iStudentProgrammeEnrolmentService, iProgrammeEditionControllerAssembler);


        RuntimeException exception = new RuntimeException("Unexpected error occurred");
        TotalEnrolledStudentsRequest request = mock(TotalEnrolledStudentsRequest.class);
        when(totalEnrolledStudentsAssembler.fromRequestToCommand(departmentId, schoolYearId)).thenThrow(exception);

        // Act
        ResponseEntity<?> result = programmeEnrolmentRestController.countByDepartmentAndSchoolYear(departmentId, schoolYearId);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        assertEquals("Unexpected error occurred", result.getBody());
    }

    @Test
    void whenPostEmptyBodyThenBadRequest() throws Exception {
        mockMvc.perform(post("/programme-enrolments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
            .andExpect(status().isBadRequest());
    }

    @Test
    void whenNoAvailableEditions_thenReturnsEmptyJsonArray() throws Exception {
        UUID gid = UUID.fromString("123e4567-e89b-12d3-a456-426614174001");
        ProgrammeEnrolmentGeneratedID genId = new ProgrammeEnrolmentGeneratedID(gid);

        when(programmeEnrolmentAssembler.toProgrammeEnrolmentGeneratedID(any(ProgrammeEnrolmentIdDTO.class)))
            .thenReturn(genId);
        when(iStudentProgrammeEnrolmentService.findDateByProgrammeEnrolmentGeneratedID(genId))
            .thenReturn(LocalDate.of(2020, 1, 1));
        when(iStudentProgrammeEnrolmentService.findProgrammeIDByProgrammeEnrolmentGeneratedID(genId))
            .thenReturn(new ProgrammeID(new Acronym("CSD")));
        when(iStudentProgrammeEnrolmentService.getAvailableProgrammeEditions(any(ProgrammeID.class), any(LocalDate.class)))
            .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/programme-enrolments/{programmeEnrolmentGID}/available-programme-editions", gid)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string("[]"));
    }

    @Test
    void whenAvailableEditions_thenReturnsJsonArray() throws Exception {
        UUID gid = UUID.fromString("123e4567-e89b-12d3-a456-426614174002");
        ProgrammeEnrolmentGeneratedID genId = new ProgrammeEnrolmentGeneratedID(gid);
        LocalDate enrolDate = LocalDate.of(2020, 1, 1);
        ProgrammeID programmeID = new ProgrammeID(new Acronym("CSD"));
        SchoolYearID schoolYearID = new SchoolYearID(UUID.fromString("d19b1a3f-9b68-41c4-9f35-b061e4799d9d"));
        ProgrammeEditionID editionID = new ProgrammeEditionID(programmeID, schoolYearID);
        StudentID studentID = new StudentID(1234567);

        ProgrammeEditionWithNameAndDescriptionResponseDTO dto =
                new ProgrammeEditionWithNameAndDescriptionResponseDTO(
                        "CSD",
                        "d19b1a3f-9b68-41c4-9f35-b061e4799d9d",
                        "Programme Name",
                        "Programme Description"
                );

        when(programmeEnrolmentAssembler.toProgrammeEnrolmentGeneratedID(any())).thenReturn(genId);
        when(iStudentProgrammeEnrolmentService.findStudentIDByProgrammeEnrolmentGeneratedID(genId)).thenReturn(studentID);
        when(iStudentProgrammeEnrolmentService.getProgrammesEditionsIdWhereStudentIsEnrolled(studentID)).thenReturn(List.of());
        when(iStudentProgrammeEnrolmentService.findDateByProgrammeEnrolmentGeneratedID(genId)).thenReturn(enrolDate);
        when(iStudentProgrammeEnrolmentService.findProgrammeIDByProgrammeEnrolmentGeneratedID(genId)).thenReturn(programmeID);
        when(iStudentProgrammeEnrolmentService.getAvailableProgrammeEditions(programmeID, enrolDate)).thenReturn(List.of(editionID));
        when(iStudentProgrammeEnrolmentService.possibleProgrammeEditionsWhereStudentCanBeEnrolled(any(), any())).thenReturn(List.of(editionID));
        when(iStudentProgrammeEnrolmentService.programmeEditionWithNameAndDescription(editionID)).thenReturn(dto);

        String expectedJson = objectMapper.writeValueAsString(List.of(dto));

        mockMvc.perform(get("/programme-enrolments/{programmeEnrolmentGID}/available-programme-editions", gid)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    void whenAssemblerThrowsException_thenReturnsInternalServerError() throws Exception {
        UUID gid = UUID.randomUUID();

        when(programmeEnrolmentAssembler
                .toProgrammeEnrolmentGeneratedID(any(ProgrammeEnrolmentIdDTO.class)))
                .thenThrow(new RuntimeException("assembly failure"));

        mockMvc.perform(get("/programme-enrolments/{programmeEnrolmentGID}/available-programme-editions", gid)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(""));
    }

    @Test
    void testPostEndpointReturnsCorrectResponse() throws Exception {
        // Arrange
        UUID uuid = UUID.randomUUID();
        ProgrammeEnrolmentIdDTO dto = new ProgrammeEnrolmentIdDTO(uuid);

        // Act & Assert
        mockMvc.perform(post("/programme-enrolments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }
}