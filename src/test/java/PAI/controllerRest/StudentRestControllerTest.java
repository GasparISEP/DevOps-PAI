package PAI.controllerRest;

import PAI.VOs.*;
import PAI.VOs.Date;
import PAI.assembler.ProgrammeAndCourses.IProgrammeAndCoursesAssembler;
import PAI.assembler.programmeEnrolment.IProgrammeEnrolmentHATEOASAssembler;
import PAI.assembler.student.IStudentDTOAssembler;
import PAI.assembler.student.IStudentHateoasAssembler;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import PAI.domain.student.Student;
import PAI.assembler.programmeEnrolment.IProgrammeEnrolmentAssembler;
import PAI.dto.ProgrammeAndCourses.StudentEnrolmentResultDto;
import PAI.dto.ProgrammeAndCourses.StudentProgrammeEnrolmentRequestDto;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentDTO;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentResponseDTO;
import PAI.dto.student.StudentDTO;
import PAI.dto.student.StudentResponseDTO;
import PAI.service.programmeEnrolment.IProgrammeEnrolmentService;
import PAI.service.student.IProgrammeAndCoursesEnrolmentService;
import PAI.service.student.IStudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.http.MediaType;




class StudentRestControllerTest {

    private MockMvc mockMvc;


    @Mock
    private IStudentService studentService;

    @Mock
    private IStudentDTOAssembler mapper;

    @Mock
    private IStudentHateoasAssembler hateoasAssembler;

    @Mock
    private IProgrammeEnrolmentService programmeEnrolmentService;

    @Mock private IProgrammeEnrolmentAssembler programmeEnrolmentMapper;
    @Mock private IProgrammeEnrolmentHATEOASAssembler enrolmentHateoasAssembler;

    @Mock private IProgrammeAndCoursesEnrolmentService programmeAndCoursesEnrolmentService;
    @Mock private IProgrammeAndCoursesAssembler programmeAndCoursesAssembler;



    @InjectMocks
    private StudentRestController studentRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(studentRestController)
                .build();
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
    void whenServiceReturnsEnrolment_thenPostReturnsCreatedEntityModel() throws Exception {
        // Arrange
        ProgrammeEnrolmentDTO inDto = new ProgrammeEnrolmentDTO(
                1234567, UUID.randomUUID().toString(), "EI", LocalDate.of(2025, 6, 8)
        );

        StudentID sid = new StudentID(inDto.getStudentID());
        AccessMethodID am = new AccessMethodID(UUID.randomUUID());
        ProgrammeID pid = new ProgrammeID(new Acronym(inDto.getProgrammeAcronym()));
        Date dt = new Date(inDto.getDate());
        when(programmeEnrolmentMapper.toStudentID(inDto)).thenReturn(sid);
        when(programmeEnrolmentMapper.toAccessMethodID(inDto)).thenReturn(am);
        when(programmeEnrolmentMapper.toProgrammeID(inDto)).thenReturn(pid);
        when(programmeEnrolmentMapper.toDateVO(inDto)).thenReturn(dt);

        ProgrammeEnrolment pe = mock(ProgrammeEnrolment.class);
        when(programmeEnrolmentService.enrolStudentInProgramme(sid, am, pid, dt))
                .thenReturn(pe);

        ProgrammeEnrolmentResponseDTO outDto =
                mock(ProgrammeEnrolmentResponseDTO.class);

        when(programmeEnrolmentMapper.toProgrammeEnrolmentDTO(pe)).thenReturn(outDto);

        EntityModel<ProgrammeEnrolmentResponseDTO> model = EntityModel.of(outDto,
                linkTo(methodOn(StudentRestController.class)
                        .getEnrolmentByStudentAndProgramme(inDto.getStudentID(), inDto.getProgrammeAcronym()))
                        .withSelfRel()
        );
        when(enrolmentHateoasAssembler.toModel(outDto)).thenReturn(model);

        ResponseEntity<EntityModel<ProgrammeEnrolmentResponseDTO>> resp =
                studentRestController.enrolStudentInProgramme(inDto);

        // Assert
        assertEquals(HttpStatus.CREATED, resp.getStatusCode());
        assertSame(model, resp.getBody());
    }


    @Test
    void whenGetEnrolmentByStudentAndProgramme_ServiceSucceeds_thenReturnsOk() {
        int studentId = 1234567;
        String acr  = "CS101";
        StudentID sid = new StudentID(studentId);
        ProgrammeID pid = new ProgrammeID(new Acronym(acr));

        ProgrammeEnrolment pe = mock(ProgrammeEnrolment.class);
        when(programmeEnrolmentService.findEnrolmentByStudentAndProgramme(sid, pid))
                .thenReturn(pe);

        ProgrammeEnrolmentResponseDTO dto = mock(ProgrammeEnrolmentResponseDTO.class);

        when(programmeEnrolmentMapper.toProgrammeEnrolmentDTO(pe)).thenReturn(dto);

        ResponseEntity<ProgrammeEnrolmentResponseDTO> resp =
                studentRestController.getEnrolmentByStudentAndProgramme(studentId, acr);

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertSame(dto, resp.getBody());
    }




    @Test
    void whenPostEnrolmentServiceReturnsNull_thenReturnsBadRequest() throws Exception {
        ProgrammeEnrolmentDTO inDto = new ProgrammeEnrolmentDTO(
                1234567, UUID.randomUUID().toString(), "EI", LocalDate.now()
        );
        when(programmeEnrolmentMapper.toStudentID(inDto)).thenReturn(new StudentID(1234567));
        when(programmeEnrolmentMapper.toAccessMethodID(inDto))
                .thenReturn(new AccessMethodID(UUID.randomUUID()));
        when(programmeEnrolmentMapper.toProgrammeID(inDto))
                .thenReturn(new ProgrammeID(new Acronym("EI")));
        when(programmeEnrolmentMapper.toDateVO(inDto))
                .thenReturn(new Date(LocalDate.now()));

        when(programmeEnrolmentService
                .enrolStudentInProgramme(any(),any(),any(),any()))
                .thenReturn(null);

        ResponseEntity<EntityModel<ProgrammeEnrolmentResponseDTO>> resp =
                studentRestController.enrolStudentInProgramme(inDto);

        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        assertNull(resp.getBody());
    }

    @Test
    void whenPostEnrolmentThrows_thenReturnsBadRequest() {
        ProgrammeEnrolmentDTO inDto = new ProgrammeEnrolmentDTO(
                1234, UUID.randomUUID().toString(), "EI", LocalDate.now()
        );
        when(programmeEnrolmentMapper.toStudentID(inDto))
                .thenThrow(new RuntimeException("fail"));

        ResponseEntity<EntityModel<ProgrammeEnrolmentResponseDTO>> resp =
                studentRestController.enrolStudentInProgramme(inDto);

        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        assertNull(resp.getBody());
    }

    @Test
    void whenPostEnrolmentDtoIsNull_thenReturnsBadRequest_NoInteractions() {
        ResponseEntity<EntityModel<ProgrammeEnrolmentResponseDTO>> resp =
                studentRestController.enrolStudentInProgramme(null);

        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        assertNull(resp.getBody());
        verifyNoInteractions(programmeEnrolmentMapper, programmeEnrolmentService, enrolmentHateoasAssembler);
    }



    @Test
    void whenGetEnrolmentByStudentAndProgramme_ServiceReturnsEnrolment_thenReturnsOk() {
        int studentId = 1234567;
        String progId = "EI";
        StudentID sid = new StudentID(studentId);
        ProgrammeID pid = new ProgrammeID(new Acronym(progId));

        ProgrammeEnrolment pe = mock(ProgrammeEnrolment.class);
        when(programmeEnrolmentService.findEnrolmentByStudentAndProgramme(sid, pid))
                .thenReturn(pe);

        ProgrammeEnrolmentResponseDTO outDto = mock(ProgrammeEnrolmentResponseDTO.class);

        when(programmeEnrolmentMapper.toProgrammeEnrolmentDTO(pe)).thenReturn(outDto);

        ResponseEntity<ProgrammeEnrolmentResponseDTO> resp =
                studentRestController.getEnrolmentByStudentAndProgramme(studentId, progId);

        assertEquals(HttpStatus.OK, resp.getStatusCode());
    }

    @Test
    void whenGetEnrolmentByStudentAndProgramme_ServiceReturnsNull_thenReturnsNotFound() {
        int studentId = 1234567;
        String progId = "XX999";
        StudentID sid = new StudentID(studentId);
        ProgrammeID pid = new ProgrammeID(new Acronym(progId));

        when(programmeEnrolmentService.findEnrolmentByStudentAndProgramme(sid, pid))
                .thenReturn(null);

        ResponseEntity<ProgrammeEnrolmentResponseDTO> resp =
                studentRestController.getEnrolmentByStudentAndProgramme(studentId, progId);

        assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
        assertNull(resp.getBody());
    }
    @Test
    void whenEnrolStudent_thenReturnsCreatedWithResultDto() throws Exception {
        // Arrange
        StudentProgrammeEnrolmentRequestDto dto = mock(StudentProgrammeEnrolmentRequestDto.class);

        StudentID studentID = mock(StudentID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        List<CourseID> courseIDs = List.of(mock(CourseID.class));

        US34Response responseDomain = mock(US34Response.class);
        StudentEnrolmentResultDto resultDto = mock(StudentEnrolmentResultDto.class);

        when(programmeAndCoursesAssembler.toStudentID(dto)).thenReturn(studentID);
        when(programmeAndCoursesAssembler.toProgrammeEditionID(dto)).thenReturn(programmeEditionID);
        when(programmeAndCoursesAssembler.toCourseIDs(dto)).thenReturn(courseIDs);
        when(programmeAndCoursesEnrolmentService.enrollStudentInProgrammeAndCourses(studentID, programmeEditionID, courseIDs)).thenReturn(responseDomain);
        when(programmeAndCoursesAssembler.toDto(responseDomain)).thenReturn(resultDto);

        // Act
        ResponseEntity<StudentEnrolmentResultDto> response = studentRestController.enrolStudent(dto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertSame(resultDto, response.getBody());
    }


}