package PAI.controllerRest;

import PAI.VOs.*;
import PAI.VOs.Date;
import PAI.assembler.ProgrammeAndCourses.IProgrammeAndCoursesAssembler;
import PAI.assembler.courseEditionEnrolment.ICourseEditionEnrolmentAssembler;
import PAI.assembler.programmeEnrolment.IProgrammeEnrolmentHATEOASAssembler;
import PAI.assembler.student.IStudentDTOAssembler;
import PAI.assembler.student.IStudentHateoasAssembler;
import PAI.assembler.totalEnrolledStudentsInProgrammesByDepartmentAndSchoolYear.ITotalEnrolledStudentsAssembler;
import PAI.domain.courseEdition.CourseEdition;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import PAI.domain.student.Student;
import PAI.assembler.programmeEnrolment.IProgrammeEnrolmentAssembler;
import PAI.dto.ProgrammeAndCourses.StudentEnrolmentResultDto;
import PAI.dto.ProgrammeAndCourses.StudentProgrammeEnrolmentRequestDto;
import PAI.dto.courseEditionEnrolment.EnrolledCourseEditionDTO;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentDTO;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentIdDTO;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentResponseDTO;
import PAI.dto.student.StudentDTO;
import PAI.dto.student.StudentResponseDTO;
import PAI.service.programmeEditionEnrolment.IStudentProgrammeEditionEnrolmentService;
import PAI.service.courseEdition.ICourseEditionService;
import PAI.service.courseEditionEnrolment.ICourseEditionEnrolmentService;
import PAI.service.programmeEnrolment.IProgrammeEnrolmentService;
import PAI.service.student.IProgrammeAndCoursesEnrolmentService;
import PAI.service.student.IStudentService;
import PAI.service.totalEnrolledStudentsInProgrammesByDepartmentAndSchoolYear.ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;



class StudentRestControllerTest {

    private MockMvc mockMvc;


    @Mock
    private IStudentService studentService;

    @Mock
    private IStudentDTOAssembler mapper;

    @Mock
    private IStudentHateoasAssembler hateoasAssembler;

    @Mock
    private IStudentProgrammeEditionEnrolmentService studentProgrammeEnrolmentService;


    @Mock
    private IProgrammeEnrolmentService programmeEnrolmentService;

    @Mock
    private IProgrammeEnrolmentAssembler programmeEnrolmentMapper;
    @Mock
    private IProgrammeEnrolmentHATEOASAssembler enrolmentHateoasAssembler;

    @Mock
    private ITotalEnrolledStudentsAssembler totalEnrolledStudentsAssembler;
    @Mock
    private ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService totalEnrolledStudentsService;

    @Mock
    private IProgrammeAndCoursesEnrolmentService programmeAndCoursesEnrolmentService;
    @Mock
    private IProgrammeAndCoursesAssembler programmeAndCoursesAssembler;

    @Mock
    private ICourseEditionEnrolmentAssembler courseEditionEnrolmentAssembler;
    @Mock
    private ICourseEditionService courseEditionService;
    @Mock
    private ICourseEditionEnrolmentService courseEditionEnrolmentService;

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


        UUID generatedGID = UUID.randomUUID();
        ProgrammeEnrolmentResponseDTO outDto = new ProgrammeEnrolmentResponseDTO(
                generatedGID,
                inDto.getStudentID(),
                inDto.getAccessMethodID(),
                inDto.getProgrammeAcronym(),
                inDto.getDate()
        );
        when(programmeEnrolmentMapper.toProgrammeEnrolmentDTO(pe)).thenReturn(outDto);

        EntityModel<ProgrammeEnrolmentResponseDTO> model = EntityModel.of(
                outDto,
                linkTo(methodOn(StudentRestController.class)
                        .getEnrolmentByStudentAndProgramme(generatedGID))
                        .withSelfRel()
        );
        when(enrolmentHateoasAssembler.toModel(outDto)).thenReturn(model);

        // Act
        ResponseEntity<EntityModel<ProgrammeEnrolmentResponseDTO>> resp =
                studentRestController.enrolStudentInProgramme(inDto);

        // Assert
        assertEquals(HttpStatus.CREATED, resp.getStatusCode());
        assertSame(model, resp.getBody());
    }


    @Test
    void whenGetEnrolmentByGID_ServiceReturnsNull_thenReturnsNotFound() {
        // Arrange
        UUID exampleGID = UUID.randomUUID();
        var idDto = new ProgrammeEnrolmentIdDTO(exampleGID);
        var vo = new ProgrammeEnrolmentGeneratedID(exampleGID);
        when(programmeEnrolmentMapper.toProgrammeEnrolmentGeneratedID(idDto))
                .thenReturn(vo);


        when(studentProgrammeEnrolmentService.findStudentIDByProgrammeEnrolmentGeneratedID(vo))
                .thenReturn(new StudentID(1234567));
        when(studentProgrammeEnrolmentService.findProgrammeIDByProgrammeEnrolmentGeneratedID(vo))
                .thenReturn(new ProgrammeID(new Acronym("XX999")));

        when(programmeEnrolmentService.findEnrolmentByStudentAndProgramme(any(), any()))
                .thenReturn(null);

        // Act
        ResponseEntity<ProgrammeEnrolmentResponseDTO> response =
                studentRestController.getEnrolmentByStudentAndProgramme(exampleGID);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
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
                .enrolStudentInProgramme(any(), any(), any(), any()))
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

    @Test
    void whenGetEnrolmentByGID_ServiceSucceeds_thenReturnsOkWithDto() {
        // Arrange
        UUID exampleGID = UUID.randomUUID();
        ProgrammeEnrolmentGeneratedID vo =
                new ProgrammeEnrolmentGeneratedID(exampleGID);

        when(programmeEnrolmentMapper
                .toProgrammeEnrolmentGeneratedID(any(ProgrammeEnrolmentIdDTO.class)))
                .thenReturn(vo);


        StudentID sid = new StudentID(1234567);
        ProgrammeID pid = new ProgrammeID(new Acronym("CS101"));
        when(studentProgrammeEnrolmentService
                .findStudentIDByProgrammeEnrolmentGeneratedID(vo))
                .thenReturn(sid);
        when(studentProgrammeEnrolmentService
                .findProgrammeIDByProgrammeEnrolmentGeneratedID(vo))
                .thenReturn(pid);

        ProgrammeEnrolment pe = mock(ProgrammeEnrolment.class);
        when(programmeEnrolmentService
                .findEnrolmentByStudentAndProgramme(sid, pid))
                .thenReturn(pe);

        ProgrammeEnrolmentResponseDTO outDto = new ProgrammeEnrolmentResponseDTO(
                exampleGID,
                sid.getUniqueNumber(),
                "WEB",
                pid.getAcronym().getAcronym(),
                LocalDate.of(2025, 6, 8)
        );
        when(programmeEnrolmentMapper.toProgrammeEnrolmentDTO(pe))
                .thenReturn(outDto);

        // Act
        ResponseEntity<ProgrammeEnrolmentResponseDTO> response =
                studentRestController.getEnrolmentByStudentAndProgramme(exampleGID);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertSame(outDto, response.getBody());
    }


    @Test
    void whenGetEnrolmentByGID_ServiceReturnsNull_thenReturnsNotFound1() {
        // Arrange
        UUID exampleGID = UUID.randomUUID();
        ProgrammeEnrolmentIdDTO idDto = new ProgrammeEnrolmentIdDTO(exampleGID);
        ProgrammeEnrolmentGeneratedID vo = new ProgrammeEnrolmentGeneratedID(exampleGID);
        when(programmeEnrolmentMapper.toProgrammeEnrolmentGeneratedID(idDto))
                .thenReturn(vo);

        when(studentProgrammeEnrolmentService.findStudentIDByProgrammeEnrolmentGeneratedID(vo))
                .thenReturn(new StudentID(1234567));
        when(studentProgrammeEnrolmentService.findProgrammeIDByProgrammeEnrolmentGeneratedID(vo))
                .thenReturn(new ProgrammeID(new Acronym("XX999")));
        when(programmeEnrolmentService.findEnrolmentByStudentAndProgramme(any(), any()))
                .thenReturn(null);

        // Act
        ResponseEntity<ProgrammeEnrolmentResponseDTO> response =
                studentRestController.getEnrolmentByStudentAndProgramme(exampleGID);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void whenGetEnrolmentByGID_MapperThrows_thenReturnsInternalServerError() {
        // Arrange
        UUID exampleGID = UUID.randomUUID();

        when(programmeEnrolmentMapper
                .toProgrammeEnrolmentGeneratedID(any(ProgrammeEnrolmentIdDTO.class)))
                .thenThrow(new RuntimeException("fail in mapper"));

        // Act
        ResponseEntity<ProgrammeEnrolmentResponseDTO> response =
                studentRestController.getEnrolmentByStudentAndProgramme(exampleGID);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    // --- Tests for findEnrolledCourseEditionsForStudent ---

    @Test
    void findEnrolledCourseEditionsForStudent_ReturnsListOfEnrolments() throws Exception {
        // Arrange
        int studentUniqueNumber = 1241564;
        StudentID studentID = new StudentID(studentUniqueNumber);

        Acronym mockCourseAcronym = new Acronym("MAT");
        Name mockCourseName = new Name("Mathematics I");
        CourseID mockCourseID = new CourseID(mockCourseAcronym, mockCourseName);

        Acronym programmeAcronym = new Acronym("LEIC");
        ProgrammeID mockProgrammeID = new ProgrammeID(programmeAcronym);

        LocalDate lDate = LocalDate.of(2023, 9, 1);
        Date studyPlanDate = new Date(lDate);

        StudyPlanID mockStudyPlanID = mock(StudyPlanID.class);
        when(mockStudyPlanID.getDate()).thenReturn(studyPlanDate);
        when(mockStudyPlanID.getLocalDate()).thenReturn(lDate);
        when(mockStudyPlanID.getProgrammeID()).thenReturn(mockProgrammeID);

        CourseInStudyPlanID mockCourseInStudyPlanID = new CourseInStudyPlanID(mockCourseID, mockStudyPlanID);

        UUID mockSchoolYearUUID = UUID.randomUUID();
        SchoolYearID mockSchoolYearID = new SchoolYearID(mockSchoolYearUUID);
        ProgrammeEditionID mockProgrammeEditionID = new ProgrammeEditionID(mockProgrammeID, mockSchoolYearID);

        UUID mockCourseEditionGeneratedUUID = UUID.randomUUID();
        CourseEditionGeneratedID mockCourseEditionGeneratedID = new CourseEditionGeneratedID(mockCourseEditionGeneratedUUID);

        CourseEdition mockCourseEdition = mock(CourseEdition.class);
        when(mockCourseEdition.getCourseInStudyPlanID()).thenReturn(mockCourseInStudyPlanID);
        when(mockCourseEdition.getProgrammeEditionID()).thenReturn(mockProgrammeEditionID);
        when(mockCourseEdition.getCourseEditionGeneratedID()).thenReturn(mockCourseEditionGeneratedID);

        UUID mockEnrolmentGeneratedUUID = UUID.randomUUID();
        CourseEditionEnrolmentGeneratedID mockEnrolmentGeneratedID = new CourseEditionEnrolmentGeneratedID(mockEnrolmentGeneratedUUID);
        US35EnrolledCourseDetails enrolledDetails = new US35EnrolledCourseDetails(mockCourseEdition, mockEnrolmentGeneratedID);

        when(courseEditionEnrolmentService.findEnrolledCourseEditionsForStudent(studentID))
                .thenReturn(List.of(enrolledDetails));

        // Act
        ResponseEntity<List<EnrolledCourseEditionDTO>> response =
                studentRestController.findEnrolledCourseEditionsForStudent(studentUniqueNumber);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void findEnrolledCourseEditionsForStudent_ReturnsEmptyListWhenNoEnrolments() {
        // Arrange
        int studentUniqueNumber = 1241654;
        StudentID studentID = new StudentID(studentUniqueNumber);

        when(courseEditionEnrolmentService.findEnrolledCourseEditionsForStudent(studentID))
                .thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<EnrolledCourseEditionDTO>> response =
                studentRestController.findEnrolledCourseEditionsForStudent(studentUniqueNumber);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
    }

    @Test
    void findEnrolledCourseEditionsForStudent_ServiceThrowsException_Propagates() {
        // Arrange
        int studentUniqueNumber = 1241654;
        StudentID studentID = new StudentID(studentUniqueNumber);

        when(courseEditionEnrolmentService.findEnrolledCourseEditionsForStudent(studentID))
                .thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            studentRestController.findEnrolledCourseEditionsForStudent(studentUniqueNumber);
        });

        assertEquals("Database error", thrown.getMessage());
        verify(courseEditionEnrolmentService).findEnrolledCourseEditionsForStudent(studentID);
    }

            // `removeStudentEnrolmentFromACourseEdition`Tests

    @Test

    void removeStudentEnrolmentFromACourseEdition_SuccessfullyRemoved() throws Exception {
        // Arrange
        int studentUniqueNumber = 1241654;
        UUID courseEditionGeneratedUUID = UUID.randomUUID();

        StudentID studentID = new StudentID(studentUniqueNumber);
        CourseEditionGeneratedID ceGeneratedID = new CourseEditionGeneratedID(courseEditionGeneratedUUID);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        when(courseEditionEnrolmentAssembler.toStudentID(studentUniqueNumber)).thenReturn(studentID);
        when(courseEditionService.findCourseEditionByGeneratedID(ceGeneratedID)).thenReturn(courseEditionID);
        when(courseEditionEnrolmentService.removeCourseEditionEnrolment(studentID, courseEditionID)).thenReturn(true);

        // Act
        ResponseEntity<String> response =
                studentRestController.removeStudentEnrolmentFromACourseEdition(studentUniqueNumber, courseEditionGeneratedUUID);

        // Assert
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("Successfully removed the enrolment from course edition.", response.getBody());
    }

    @Test
    void removeStudentEnrolmentFromACourseEdition_StudentNotEnrolled() throws Exception {
        // Arrange
        int studentUniqueNumber = 1241543;
        UUID courseEditionGeneratedUUID = UUID.randomUUID();

        StudentID studentID = new StudentID(studentUniqueNumber);
        CourseEditionGeneratedID ceGeneratedID = new CourseEditionGeneratedID(courseEditionGeneratedUUID);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        when(courseEditionEnrolmentAssembler.toStudentID(studentUniqueNumber)).thenReturn(studentID);
        when(courseEditionService.findCourseEditionByGeneratedID(ceGeneratedID)).thenReturn(courseEditionID);
        when(courseEditionEnrolmentService.removeCourseEditionEnrolment(studentID, courseEditionID)).thenReturn(false);

        // Act
        ResponseEntity<String> response =
                studentRestController.removeStudentEnrolmentFromACourseEdition(studentUniqueNumber, courseEditionGeneratedUUID);

        // Assert
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
        assertEquals("Student is not enrolled in that course edition or enrolment could not be removed.", response.getBody());
    }

    @Test
    void removeStudentEnrolmentFromACourseEdition_InvalidStudentIDFormat() throws Exception {
        // Arrange
        int studentUniqueNumber = -1; // invalid input
        UUID courseEditionGeneratedUUID = UUID.randomUUID();

        when(courseEditionEnrolmentAssembler.toStudentID(studentUniqueNumber))
                .thenThrow(new IllegalArgumentException("Student ID must be positive"));

        // Act
        ResponseEntity<String> response =
                studentRestController.removeStudentEnrolmentFromACourseEdition(studentUniqueNumber, courseEditionGeneratedUUID);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid identifier format: Student ID must be positive", response.getBody());
    }

    @Test
    void removeStudentEnrolmentFromACourseEdition_CourseEditionServiceThrowsException() throws Exception {
        // Arrange
        int studentUniqueNumber = 1241543;
        UUID courseEditionGeneratedUUID = UUID.randomUUID();

        StudentID studentID = new StudentID(studentUniqueNumber);
        CourseEditionGeneratedID ceGeneratedID = new CourseEditionGeneratedID(courseEditionGeneratedUUID);

        when(courseEditionEnrolmentAssembler.toStudentID(studentUniqueNumber)).thenReturn(studentID);
        when(courseEditionService.findCourseEditionByGeneratedID(ceGeneratedID))
                .thenThrow(new IllegalStateException("Course edition not found in service"));

        // Act
        ResponseEntity<String> response =
                studentRestController.removeStudentEnrolmentFromACourseEdition(studentUniqueNumber, courseEditionGeneratedUUID);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error processing enrolment removal: Course edition not found in service", response.getBody());
    }

    @Test
    void removeStudentEnrolmentFromACourseEdition_GeneralException() throws Exception {
        // Arrange
        int studentUniqueNumber = 1241543;
        UUID courseEditionGeneratedUUID = UUID.randomUUID();

        StudentID studentID = new StudentID(studentUniqueNumber);
        CourseEditionGeneratedID ceGeneratedID = new CourseEditionGeneratedID(courseEditionGeneratedUUID);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        when(courseEditionEnrolmentAssembler.toStudentID(studentUniqueNumber)).thenReturn(studentID);
        when(courseEditionService.findCourseEditionByGeneratedID(ceGeneratedID)).thenReturn(courseEditionID);
        when(courseEditionEnrolmentService.removeCourseEditionEnrolment(studentID, courseEditionID))
                .thenThrow(new RuntimeException("Unexpected error during removal"));

        // Act
        ResponseEntity<String> response =
                studentRestController.removeStudentEnrolmentFromACourseEdition(studentUniqueNumber, courseEditionGeneratedUUID);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error processing enrolment removal: Unexpected error during removal", response.getBody());
    }
}