package PAI.controllerRest;

import PAI.VOs.*;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import PAI.dto.programmeEnrolment.IProgrammeEnrolmentAssembler;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentDTO;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentResponseDTO;
import PAI.service.programmeEnrolment.IProgrammeEnrolmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgrammeEnrolmentRestControllerTest {

    @Mock
    private IProgrammeEnrolmentService programmeEnrolmentService;

    @Mock
    private IProgrammeEnrolmentAssembler programmeEnrolmentMapperDTO;

    @InjectMocks
    private ProgrammeEnrolmentRestController programmeEnrolmentRestController;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenDtoIsNull_thenReturnsBadRequest() {
        //Arrange + act
        ResponseEntity<ProgrammeEnrolmentResponseDTO> resp = programmeEnrolmentRestController.enrolStudentInProgramme(null);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }

    @Test
    void whenServiceReturnsEnrolment_thenReturnsCreatedWithBody1() throws Exception {
        // Arrange
        ProgrammeEnrolmentDTO dto = new ProgrammeEnrolmentDTO(1234567, UUID.randomUUID().toString(), "Engenharia Informática", "EI", java.time.LocalDate.of(2025,5,20));


        StudentID sid       = new StudentID(1234567);
        AccessMethodID am   = new AccessMethodID(UUID.randomUUID());
        ProgrammeID pid     = new ProgrammeID(new NameWithNumbersAndSpecialChars("Engenharia Informática"), new Acronym("EI"));
        Date date           = new Date(java.time.LocalDate.of(2025,5,20));

        when(programmeEnrolmentMapperDTO.toStudentID (dto)).thenReturn(sid);
        when(programmeEnrolmentMapperDTO.toAccessMethodID(dto)).thenReturn(am);
        when(programmeEnrolmentMapperDTO.toProgrammeID(dto)).thenReturn(pid);
        when(programmeEnrolmentMapperDTO.toDateVO(dto)).thenReturn(date);


        ProgrammeEnrolment pe = mock(ProgrammeEnrolment.class);
        when(programmeEnrolmentService.enrolStudentInProgramme(sid,am,pid,date)).thenReturn(pe);


        ProgrammeEnrolmentResponseDTO outDto = new ProgrammeEnrolmentResponseDTO(1234567,"Exames Nacionais","Engenharia Informática", date.getLocalDate());
        when(programmeEnrolmentMapperDTO.toProgrammeEnrolmentDTO(pe)).thenReturn(outDto);

        // Act
        ResponseEntity<ProgrammeEnrolmentResponseDTO> resp = programmeEnrolmentRestController.enrolStudentInProgramme(dto);

        // Assert
        assertEquals(HttpStatus.CREATED, resp.getStatusCode());
        assertSame(outDto, resp.getBody());

        verify(programmeEnrolmentMapperDTO).toStudentID(dto);
        verify(programmeEnrolmentMapperDTO).toAccessMethodID(dto);
        verify(programmeEnrolmentMapperDTO).toProgrammeID(dto);
        verify(programmeEnrolmentMapperDTO).toDateVO(dto);

    }


    @Test
    void whenServiceReturnsNull_thenReturnsBadRequest() throws Exception {
        // Arrange
        ProgrammeEnrolmentDTO dto = new ProgrammeEnrolmentDTO(1234567, UUID.randomUUID().toString(), "Engenharia Informática","EI", java.time.LocalDate.now());


        when(programmeEnrolmentMapperDTO.toStudentID(dto)).thenReturn(new StudentID(1234567));
        when(programmeEnrolmentMapperDTO.toAccessMethodID(dto)).thenReturn(new AccessMethodID(UUID.randomUUID()));
        when(programmeEnrolmentMapperDTO.toProgrammeID(dto)).thenReturn(new ProgrammeID(new NameWithNumbersAndSpecialChars("Engenharia Informática"), new Acronym("EI")));
        when(programmeEnrolmentMapperDTO.toDateVO(dto)).thenReturn(new Date(java.time.LocalDate.now()));


        when(programmeEnrolmentService.enrolStudentInProgramme(any(), any(), any(), any())).thenReturn(null);

        // Act
        ResponseEntity<ProgrammeEnrolmentResponseDTO> resp = programmeEnrolmentRestController.enrolStudentInProgramme(dto);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        assertNull(resp.getBody());
    }

    @Test
    void whenAnyExceptionThrown_thenReturnsBadRequest() throws Exception {
        // Arrange
        ProgrammeEnrolmentDTO dto = new ProgrammeEnrolmentDTO(1234567, UUID.randomUUID().toString(), "Engenharia Informática","EI", java.time.LocalDate.now());

        when(programmeEnrolmentMapperDTO.toStudentID(dto)).thenThrow(new RuntimeException("Invalid"));

        // Act
        ResponseEntity<ProgrammeEnrolmentResponseDTO> resp = programmeEnrolmentRestController.enrolStudentInProgramme(dto);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        assertNull(resp.getBody());
    }
}