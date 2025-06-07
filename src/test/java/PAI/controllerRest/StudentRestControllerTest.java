package PAI.controllerRest;

import PAI.VOs.*;
import PAI.VOs.Date;
import PAI.assembler.student.IStudentDTOAssembler;
import PAI.assembler.student.IStudentHateoasAssembler;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import PAI.domain.student.Student;
import PAI.dto.programmeEnrolment.IProgrammeEnrolmentAssembler;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentDTO;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentResponseDTO;
import PAI.dto.student.StudentDTO;
import PAI.dto.student.StudentResponseDTO;
import PAI.service.programmeEnrolment.IProgrammeEnrolmentService;
import PAI.service.student.IStudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentRestControllerTest {

    @Mock
    private IStudentService studentService;

    @Mock
    private IStudentDTOAssembler mapper;

    @Mock
    private IStudentHateoasAssembler hateoasAssembler;

    @Mock
    private IProgrammeEnrolmentService programmeEnrolmentService;

    @Mock
    private IProgrammeEnrolmentAssembler programmeEnrolmentMapperDTO;

    @InjectMocks
    private StudentRestController studentRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenDtoIsNull_thenReturnsBadRequest() {
        ResponseEntity<EntityModel<StudentResponseDTO>> response = studentRestController.registerAStudent(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void whenServiceReturnsStudent_thenReturnsCreatedWithHateoas() {
        StudentDTO dto = mock(StudentDTO.class);

        Name name = new Name("João Silva");
        NIF nif = new NIF("123456789", new Country("Portugal"));
        PhoneNumber phone = new PhoneNumber("+351", "912345678");
        Email email = new Email("joao.silva@example.com");

        Street street = new Street("Rua Central");
        PostalCode postalCode = new PostalCode("1234-567");
        Location location = new Location("Porto");
        Country country = new Country("Portugal");
        Address address = new Address(street, postalCode, location, country);

        when(mapper.toName(dto)).thenReturn(name);
        when(mapper.toNIF(dto)).thenReturn(nif);
        when(mapper.toPhoneNumber(dto)).thenReturn(phone);
        when(mapper.toEmail(dto)).thenReturn(email);
        when(mapper.toAddress(dto)).thenReturn(address);

        Student student = mock(Student.class);
        when(studentService.registerStudent(
                name, nif, phone, email, street, postalCode, location, country
        )).thenReturn(student);

        StudentResponseDTO responseDTO = mock(StudentResponseDTO.class);
        EntityModel<StudentResponseDTO> entityModel = EntityModel.of(responseDTO);

        when(mapper.toStudentResponseDTO(student)).thenReturn(responseDTO);
        when(hateoasAssembler.toModel(responseDTO)).thenReturn(entityModel);

        ResponseEntity<EntityModel<StudentResponseDTO>> response = studentRestController.registerAStudent(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertSame(entityModel, response.getBody());
    }

    @Test
    void whenGetLastStudentID_thenReturnsExpectedValueInMap() {
        int lastStudentID = 1234567;
        when(studentService.getLastStudentID()).thenReturn(lastStudentID);

        ResponseEntity<Map<String, Integer>> response = studentRestController.getLastStudentID();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(lastStudentID, response.getBody().get("lastStudentID"));
    }

    @Test
    void whenGetAllStudents_thenReturnsCollectionWithLinks() {
        Student student = mock(Student.class);
        StudentResponseDTO dto = mock(StudentResponseDTO.class);
        EntityModel<StudentResponseDTO> model = EntityModel.of(dto);

        when(studentService.getAllStudents()).thenReturn(List.of(student));
        when(mapper.toStudentResponseDTO(student)).thenReturn(dto);
        when(hateoasAssembler.toModel(dto)).thenReturn(model);

        ResponseEntity<CollectionModel<EntityModel<StudentResponseDTO>>> response = studentRestController.getAllStudents();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getContent().contains(model));
    }
    @Test
    void whenServiceReturnsEnrolment_thenReturnsCreatedWithBody1() throws Exception {
        // Arrange
        ProgrammeEnrolmentDTO dto = new ProgrammeEnrolmentDTO(1234567, UUID.randomUUID().toString(), "EI", java.time.LocalDate.of(2025,5,20));


        StudentID sid       = new StudentID(1234567);
        AccessMethodID am   = new AccessMethodID(UUID.randomUUID());
        ProgrammeID pid     = new ProgrammeID(new Acronym("EI"));
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
        ResponseEntity<ProgrammeEnrolmentResponseDTO> resp = studentRestController.enrolStudentInProgramme(dto);

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
        ProgrammeEnrolmentDTO dto = new ProgrammeEnrolmentDTO(1234567, UUID.randomUUID().toString(), "EI", java.time.LocalDate.now());


        when(programmeEnrolmentMapperDTO.toStudentID(dto)).thenReturn(new StudentID(1234567));
        when(programmeEnrolmentMapperDTO.toAccessMethodID(dto)).thenReturn(new AccessMethodID(UUID.randomUUID()));
        when(programmeEnrolmentMapperDTO.toProgrammeID(dto)).thenReturn(new ProgrammeID(new Acronym("EI")));
        when(programmeEnrolmentMapperDTO.toDateVO(dto)).thenReturn(new Date(java.time.LocalDate.now()));


        when(programmeEnrolmentService.enrolStudentInProgramme(any(), any(), any(), any())).thenReturn(null);

        // Act
        ResponseEntity<ProgrammeEnrolmentResponseDTO> resp = studentRestController.enrolStudentInProgramme(dto);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        assertNull(resp.getBody());
    }

    @Test
    void whenAnyExceptionThrown_thenReturnsBadRequest() throws Exception {
        // Arrange
        ProgrammeEnrolmentDTO dto = new ProgrammeEnrolmentDTO(1234567, UUID.randomUUID().toString(), "EI", java.time.LocalDate.now());

        when(programmeEnrolmentMapperDTO.toStudentID(dto)).thenThrow(new RuntimeException("Invalid"));

        // Act
        ResponseEntity<ProgrammeEnrolmentResponseDTO> resp = studentRestController.enrolStudentInProgramme(dto);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        assertNull(resp.getBody());
    }

    @Test
    void whenProgrammeEnrolmentDtoIsNull_thenReturnsBadRequest() throws Exception {
        // Act
        ResponseEntity<ProgrammeEnrolmentResponseDTO> resp =
                studentRestController.enrolStudentInProgramme(null);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        assertNull(resp.getBody());

        verifyNoInteractions(programmeEnrolmentMapperDTO);
        verifyNoInteractions(programmeEnrolmentService);
    }

}