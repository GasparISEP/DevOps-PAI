package PAI.controllerRest;

import PAI.VOs.*;
import PAI.dto.programmeEditionEnrolment.ProgrammeEditionEnrolmentRequest;
import PAI.dto.programmeEditionEnrolment.StudentProgrammeEditionEnrolmentDTO;
import PAI.service.programmeEditionEnrolment.IStudentProgrammeEditionEnrolmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentProgrammeEditionEnrolmentRestControllerTest {

    private IStudentProgrammeEditionEnrolmentService service;
    private StudentProgrammeEditionEnrolmentRestController controller;

    @BeforeEach
    void setUp() {
        service = mock(IStudentProgrammeEditionEnrolmentService.class);
        controller = new StudentProgrammeEditionEnrolmentRestController(service);
    }

    @Test
    void getProgrammeEditionsStudentCanEnrollIn_validStudentId_returns200OK() throws Exception {
        // Arrange
        String studentIdStr = "1500001";
        StudentID studentID = new StudentID(1500001);
        String schoolYearId = UUID.randomUUID().toString();

        List<StudentProgrammeEditionEnrolmentDTO> mockResult = List.of(
                new StudentProgrammeEditionEnrolmentDTO("LEI", "Informática", schoolYearId)
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
                new ProgrammeID(new NameWithNumbersAndSpecialChars(name), new Acronym(acronym)),
                new SchoolYearID(UUID.fromString(schoolYearId))
        );

        // Act
        ResponseEntity<Void> response = controller.enrolStudentInProgrammeEdition(request);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        verify(service).enrolStudentInProgrammeEdition(
                new StudentID(Integer.parseInt(studentId)),
                new ProgrammeID(new NameWithNumbersAndSpecialChars(name), new Acronym(acronym)),
                new SchoolYearID(UUID.fromString(schoolYearId))
        );
    }


}
