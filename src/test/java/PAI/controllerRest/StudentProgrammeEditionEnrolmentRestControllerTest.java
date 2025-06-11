package PAI.controllerRest;

import PAI.VOs.*;
import PAI.assembler.programmeEditionEnrolment.IProgrammeEditionEnrolmentAssembler;
import PAI.assembler.programmeEditionEnrolment.IProgrammeEditionEnrolmentHateoasAssembler;
import PAI.dto.programmeEditionEnrolment.ProgrammeEditionEnrolmentDetailDto;
import PAI.dto.programmeEditionEnrolment.ProgrammeEditionEnrolmentRequest;
import PAI.dto.programmeEditionEnrolment.StudentProgrammeEditionEnrolmentDTO;
import PAI.service.programmeEditionEnrolment.IProgrammeEditionEnrolmentService;
import PAI.service.programmeEditionEnrolment.IStudentProgrammeEditionEnrolmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@WebMvcTest(StudentProgrammeEditionEnrolmentRestController.class)
class StudentProgrammeEditionEnrolmentRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IProgrammeEditionEnrolmentService programmeEditionEnrolmentService;

    @MockitoBean
    private IProgrammeEditionEnrolmentAssembler programmeEditionEnrolmentAssembler;

    @MockitoBean   
    private IStudentProgrammeEditionEnrolmentService service;

    @MockitoBean
    private IProgrammeEditionEnrolmentHateoasAssembler programmeEditionEnrolmentHateoasAssembler;

    private StudentProgrammeEditionEnrolmentRestController controller;

    @BeforeEach
    void setUp() {
        controller = new StudentProgrammeEditionEnrolmentRestController(service, programmeEditionEnrolmentAssembler, programmeEditionEnrolmentService, programmeEditionEnrolmentHateoasAssembler);
    }


    @Test
    void getProgrammeEditionsStudentCanEnrollIn_validStudentId_returns200OK() throws Exception {
        // Arrange
        String studentIdStr = "1500001";
        StudentID studentID = new StudentID(1500001);
        String schoolYearId = UUID.randomUUID().toString();

        List<StudentProgrammeEditionEnrolmentDTO> mockResult = List.of(
                new StudentProgrammeEditionEnrolmentDTO("LEI", schoolYearId)
        );

        when(service.findAvailableProgrammeEditionsForStudent(studentID)).thenReturn(mockResult);

        // Act
        ResponseEntity<List<StudentProgrammeEditionEnrolmentDTO>> response = controller.getProgrammeEditionsStudentCanEnrollIn(studentIdStr);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("LEI", response.getBody().get(0).getProgrammeAcronym());
    }

    @Test
    void getProgrammeEditionsStudentCanEnrollIn_invalidStudentId_returns400BadRequest() {
        // Arrange
        String invalidStudentId = "abc";

        // Act
        ResponseEntity<List<StudentProgrammeEditionEnrolmentDTO>> response = controller.getProgrammeEditionsStudentCanEnrollIn(invalidStudentId);

        // Assert
        assertEquals(400, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void enrolStudentInProgrammeEdition_validRequest_returns200OK() {
        // Arrange
        String studentId = "1500001";
        String acronym = "LEI";
        String name = "Engenharia Informática";
        String schoolYearId = UUID.randomUUID().toString();

        ProgrammeEditionEnrolmentRequest request = new ProgrammeEditionEnrolmentRequest(studentId, acronym, name, schoolYearId);

        doNothing().when(service).enrolStudentInProgrammeEdition(
                new StudentID(Integer.parseInt(studentId)),
                new ProgrammeID(new Acronym(acronym)),
                new SchoolYearID(UUID.fromString(schoolYearId))
        );

        // Act
        ResponseEntity<Void> response = controller.enrolStudentInProgrammeEdition(request);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        verify(service).enrolStudentInProgrammeEdition(
                new StudentID(Integer.parseInt(studentId)),
                new ProgrammeID(new Acronym(acronym)),
                new SchoolYearID(UUID.fromString(schoolYearId))
        );
    }

    @Test
    void enrolStudentInProgrammeEdition_shouldReturnBadRequest_whenServiceThrowsException() {
        // Arrange
        String studentId = "1000001";
        String acronym = "LEI";
        String name = "Licenciatura em Engenharia Informática";
        String schoolYearId = UUID.randomUUID().toString();

        ProgrammeEditionEnrolmentRequest request = new ProgrammeEditionEnrolmentRequest(
                studentId, acronym, name, schoolYearId
        );

        // Mock the service with specific parameters
        doThrow(new RuntimeException("Simulated exception"))
                .when(service)
                .enrolStudentInProgrammeEdition(
                    new StudentID(Integer.parseInt(studentId)),
                    new ProgrammeID(new Acronym(acronym)),
                    new SchoolYearID(UUID.fromString(schoolYearId))
                );

        // Act
        ResponseEntity<Void> response = controller.enrolStudentInProgrammeEdition(request);

        // Assert
        assertEquals(400, response.getStatusCodeValue());
        verify(service).enrolStudentInProgrammeEdition(
            new StudentID(Integer.parseInt(studentId)),
            new ProgrammeID(new Acronym(acronym)),
            new SchoolYearID(UUID.fromString(schoolYearId))
        );
    }

    @Test
    void enrolStudentInProgrammeEdition_invalidStudentId_returns400BadRequest() {
        // Arrange
        String invalidStudentId = "abc";
        String acronym = "LEI";
        String name = "Licenciatura em Engenharia Informática";
        String schoolYearId = UUID.randomUUID().toString();

        ProgrammeEditionEnrolmentRequest request = new ProgrammeEditionEnrolmentRequest(
                invalidStudentId, acronym, name, schoolYearId
        );

        // Act
        ResponseEntity<Void> response = controller.enrolStudentInProgrammeEdition(request);

        // Assert
        assertEquals(400, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void shouldReturn200OKAndListOfProgrammeEditionEnrolments_whenGetProgrammeEditionEnrollmentsByStudentID_validStudentId() throws Exception {
        // Arrange
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentDetailDto programmeEditionEnrolmentDetailDto = new ProgrammeEditionEnrolmentDetailDto(1000001, "LEI", "2024-2025", "some-uuid-string");
        EntityModel<ProgrammeEditionEnrolmentDetailDto> programmeEditionEnrolmentDetailDtoEntityModel = EntityModel.of(programmeEditionEnrolmentDetailDto);
        List<ProgrammeEditionID> listOfEditionIDs = List.of(programmeEditionID);
        List<ProgrammeEditionEnrolmentDetailDto> listOfDetailDtos = List.of(programmeEditionEnrolmentDetailDto);

        CollectionModel<EntityModel<ProgrammeEditionEnrolmentDetailDto>> collectionModel =
                CollectionModel.of(List.of(programmeEditionEnrolmentDetailDtoEntityModel));

        when(programmeEditionEnrolmentService.getProgrammeEditionEnrolmentsByStudentID(any(StudentID.class)))
                .thenReturn(listOfEditionIDs);
        when(programmeEditionEnrolmentAssembler.toDtoList(eq(listOfEditionIDs), any(StudentID.class)))
                .thenReturn(listOfDetailDtos);
        when(programmeEditionEnrolmentHateoasAssembler.toCollectionModel(eq(listOfDetailDtos)))
                .thenReturn(collectionModel);

        // Act & Assert
        mockMvc.perform(get("/students/1000001/programme-edition-enrolments")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldReturn400BadRequest_whenGetProgrammeEditionEnrollmentsByStudentID_invalidStudentId() throws Exception {
        // Act && Assert
        mockMvc.perform(get("/students/15000011/programme-edition-enrolments"))
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void shouldReturn200OKAndEmptyList_whenGetProgrammeEditionEnrollmentsByStudentID_studentNotEnrolledInAnyProgrammeEdition() throws Exception {
        // Arrange
        when(programmeEditionEnrolmentService.getProgrammeEditionEnrolmentsByStudentID(any(StudentID.class)))
            .thenReturn(List.of());
        when(programmeEditionEnrolmentAssembler.toDtoList(anyList(), any(StudentID.class)))
            .thenReturn(List.of());
        when(programmeEditionEnrolmentHateoasAssembler.toCollectionModel(List.of()))
            .thenReturn(CollectionModel.empty());

        // Act & Assert
        mockMvc.perform(get("/students/1000001/programme-edition-enrolments"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
