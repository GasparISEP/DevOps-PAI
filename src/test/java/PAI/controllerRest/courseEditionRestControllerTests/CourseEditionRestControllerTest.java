package PAI.controllerRest.courseEditionRestControllerTests;
import PAI.VOs.*;
import PAI.assembler.courseEdition.ICourseEditionAssembler;
import PAI.assembler.courseEdition.ICourseEditionHateoasAssembler;
import PAI.assembler.courseEdition.IStudentCountAssembler;
import PAI.assembler.programmeEdition.IProgrammeEditionAssembler;
import PAI.assembler.studentGrade.IStudentGradeAssembler;
import PAI.assembler.courseEditionEnrolment.ICourseEditionEnrolmentHateoasAssembler;
import PAI.controllerRest.CourseEditionRestController;
import PAI.domain.courseEdition.CourseEdition;
import PAI.dto.studentGrade.GradeAStudentCommand;
import PAI.dto.studentGrade.GradeAStudentRequestDTO;
import PAI.dto.studentGrade.GradeAStudentResponseDTO;
import PAI.dto.courseEdition.*;
import PAI.service.courseEdition.ICourseEditionService;
import PAI.service.courseEdition.ICreateCourseEditionService;
import PAI.service.courseEdition.IDefineRucService;
import PAI.service.studentGrade.IGradeAStudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import PAI.assembler.courseEditionEnrolment.ICourseEditionEnrolmentAssembler;
import PAI.dto.courseEditionEnrolment.CourseEditionEnrolmentDto;
import PAI.service.courseEditionEnrolment.ICourseEditionEnrolmentService;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CourseEditionRestController.class)
class CourseEditionRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ICourseEditionEnrolmentService courseEditionEnrolmentService;

    @MockBean
    private ICourseEditionEnrolmentAssembler courseEditionEnrolmentAssembler;

    @MockBean
    private ICreateCourseEditionService createCourseEditionService;

    @MockBean
    private ICourseEditionAssembler courseEditionAssembler;

    @MockBean
    private IDefineRucService defineRucService;

    @MockBean
    private IGradeAStudentService gradeAStudentService;

    @MockBean
    private IStudentGradeAssembler studentGradeAssembler;

    @MockBean
    private IProgrammeEditionAssembler programmeEditionAssembler;

    @MockBean
    private ICourseEditionService courseEditionService;

    @MockBean
    private ICourseEditionHateoasAssembler courseEditionHateoasAssembler;

    @MockBean
    private IStudentCountAssembler studentCountAssembler;

    @MockBean
    private CourseEditionEnrolmentDto validEnrolmentDto;

    @Autowired
    private CourseEditionRestController courseEditionRestController;

    @MockBean
    private ICourseEditionEnrolmentHateoasAssembler courseEditionEnrolmentHateoasAssembler;

    @BeforeEach
    void setUp() {
        validEnrolmentDto = new CourseEditionEnrolmentDto(
            1100000,
            "LEIC",
            "123e4567-e89b-12d3-a456-426614174000",
            "ESOFT",
            "Engineering Software",
            "01-01-2024"
        );
    }

    @Test
    void whenEnrolStudent_thenReturnsCreated() throws Exception {
        // Arrange
        int studentId = 1100000;
        CourseEditionID mockCourseEditionID = mock(CourseEditionID.class);
        StudentID mockStudentID = mock(StudentID.class);
        
        when(courseEditionEnrolmentAssembler.toCourseEditionID(any(CourseEditionEnrolmentDto.class)))
            .thenReturn(mockCourseEditionID);
        when(courseEditionEnrolmentAssembler.toStudentID(any(int.class)))
            .thenReturn(mockStudentID);
        when(courseEditionEnrolmentService.enrolStudentInACourseEdition(mockStudentID, mockCourseEditionID))
            .thenReturn(true);
        when(courseEditionEnrolmentHateoasAssembler.toModel(any(CourseEditionEnrolmentDto.class)))
            .thenReturn(EntityModel.of(validEnrolmentDto));

        // Act & Assert
        mockMvc.perform(post("/courseeditions/students/{id}/courses-edition-enrolments", studentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validEnrolmentDto)))
                .andExpect(status().isCreated());
        }

    @Test
    void whenEnrolStudentAlreadyEnrolled_thenReturnsBadRequest() throws Exception {
        // Arrange
        int studentId = 1100000;
        CourseEditionID mockCourseEditionID = mock(CourseEditionID.class);
        StudentID mockStudentID = mock(StudentID.class);
        
        when(courseEditionEnrolmentAssembler.toCourseEditionID(any(CourseEditionEnrolmentDto.class)))
            .thenReturn(mockCourseEditionID);
        when(courseEditionEnrolmentAssembler.toStudentID(studentId))
            .thenReturn(mockStudentID);
        when(courseEditionEnrolmentService.enrolStudentInACourseEdition(any(), any()))
            .thenReturn(false);

        // Act & Assert
        mockMvc.perform(post("/courseeditions/students/{id}/courses-edition-enrolments", studentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validEnrolmentDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Student already enrolled in this course edition"));
    }

    @Test
    void whenEnrolStudentWithInvalidData_thenReturnsBadRequest() throws Exception {
        // Arrange
        int studentId = 0;    
        CourseEditionEnrolmentDto invalidDto = new CourseEditionEnrolmentDto(
            0,
            "",
            "",
            "",
            "",
            "" 
        );

        // Act & Assert
        mockMvc.perform(post("/courseeditions/students/{id}/courses-edition-enrolments", studentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenEnrolStudentWithException_thenReturnsBadRequest() throws Exception {
        // Arrange
        int studentId = 1100000;
        
        when(courseEditionEnrolmentAssembler.toCourseEditionID(any(CourseEditionEnrolmentDto.class)))
            .thenThrow(new RuntimeException("Test exception"));

        // Act & Assert
        mockMvc.perform(post("/courseeditions/students/{id}/courses-edition-enrolments", studentId)                
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validEnrolmentDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Error processing enrollment: Test exception"));
    }

    @Test
    void whenRemoveStudentEnrolmentFromACourseEdition_thenReturnsSuccess() throws Exception {
        //arrange
        when(courseEditionEnrolmentAssembler.toCourseEditionID(any(CourseEditionEnrolmentDto.class))).thenReturn(mock(CourseEditionID.class));
        when(courseEditionEnrolmentAssembler.toStudentID(any(int.class))).thenReturn(mock(StudentID.class));
        when(courseEditionEnrolmentService.removeCourseEditionEnrolment(any(), any())).thenReturn(true);
        //act + assert
        mockMvc.perform(patch("/courseeditions/enrolments/students/remove")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validEnrolmentDto)))
                .andExpect(status().isAccepted());

    }

    @Test
    void shouldReturnBadRequestWhenRemoveCourseEditionEnrolment_throwsException() throws Exception {
        //arrange
        when(courseEditionEnrolmentAssembler.toCourseEditionID(any(CourseEditionEnrolmentDto.class))).thenReturn(mock(CourseEditionID.class));
        when(courseEditionEnrolmentAssembler.toStudentID(any(int.class))).thenReturn(mock(StudentID.class));
        when(courseEditionEnrolmentService.removeCourseEditionEnrolment(any(), any())).thenThrow(new RuntimeException("Test exception"));

        //act + assert
        mockMvc.perform(patch("/courseeditions/enrolments/students/remove")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validEnrolmentDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnNotAcceptableWhenRemoveCourseEditionEnrolmentCannotBeRemoved() throws Exception {
        //arrange
        when(courseEditionEnrolmentAssembler.toCourseEditionID(any(CourseEditionEnrolmentDto.class))).thenReturn(mock(CourseEditionID.class));
        when(courseEditionEnrolmentAssembler.toStudentID(any(int.class))).thenReturn(mock(StudentID.class));
        when(courseEditionEnrolmentService.removeCourseEditionEnrolment(any(), any())).thenReturn(false);
        //act + assert
        mockMvc.perform(patch("/courseeditions/enrolments/students/remove")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validEnrolmentDto)))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    void whenValidRequest_thenReturnsCreatedResponse() {
        // Arrange
        UUID schoolYearID = UUID.randomUUID();

        CourseEditionRequestDTO dto = new CourseEditionRequestDTO(
                "LEI", "LEIC", schoolYearID,
                "SA", "Software Architecture", LocalDate.of(2023, 9, 1)
        );

        CreateCourseEditionCommand command = new CreateCourseEditionCommand(
                new NameWithNumbersAndSpecialChars(dto.programmeName()), new Acronym(dto.programmeAcronym()), new SchoolYearID(dto.schoolYearID()),
                new Acronym (dto.courseAcronym()), new Name(dto.courseName()), new Date(dto.studyPlanImplementationDate())
        );

        CourseEditionResponseDTO responseDTO = new CourseEditionResponseDTO(
                "id123", "LEIC", schoolYearID,
                "SA", "Software Architecture", LocalDate.of(2023, 9, 1)
        );

        when(courseEditionAssembler.toCommand(dto)).thenReturn(command);
        when(createCourseEditionService.createCourseEditionAndReturnDTO(any(), any()))
                .thenReturn(responseDTO);

        // Act
        ResponseEntity<?> response = courseEditionRestController.createCourseEdition(dto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(response.getHeaders().getLocation().toString().contains("/courseeditions/id123"));
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void whenServiceReturnsNull_thenReturnsBadRequest() {
        // Arrange
        CourseEditionRequestDTO dto = new CourseEditionRequestDTO(
                "LEI", "LEIC", UUID.randomUUID(),
                "SA", "Software Architecture", LocalDate.of(2023, 9, 1)
        );

        when(courseEditionAssembler.toCommand(any())).thenReturn(mock(CreateCourseEditionCommand.class));
        when(createCourseEditionService.createCourseEditionAndReturnDTO(any(), any())).thenReturn(null);

        // Act
        ResponseEntity<?> response = courseEditionRestController.createCourseEdition(dto);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void whenExceptionThrown_thenReturnsBadRequestWithMessage() {
        // Arrange
        CourseEditionRequestDTO dto = new CourseEditionRequestDTO(
                "LEI", "LEIC", UUID.randomUUID(),
                "SA", "Software Architecture", LocalDate.of(2023, 9, 1)
        );

        when(courseEditionAssembler.toCommand(any())).thenThrow(new RuntimeException("Invalid request"));

        // Act
        ResponseEntity<?> response = courseEditionRestController.createCourseEdition(dto);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid request", response.getBody());
    }

    @Test
    void whenGetStudentsEnrolledInCourseEdition_thenReturnsListOfStudents() throws Exception {
        // Arrange
        CourseEditionRequestDTO requestDTO = new CourseEditionRequestDTO(
            "L.EIC",                                   
            "LEIC",                                     
            UUID.randomUUID(),                         
            "ESOFT",                                 
            "Engineering Software",                     
            LocalDate.now()                             
        );
        
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        CourseEditionResponseDTO courseEditionResponseDTO = mock(CourseEditionResponseDTO.class);
        EntityModel<CourseEditionResponseDTO> mockEntityModel = EntityModel.of(courseEditionResponseDTO);
        CollectionModel<EntityModel<CourseEditionResponseDTO>> mockCollectionModel = CollectionModel.of(List.of(mockEntityModel));

        when(courseEditionAssembler.toProgrammeEditionID(requestDTO)).thenReturn(programmeEditionID);
        when(courseEditionAssembler.toCourseInStudyPlanID(requestDTO)).thenReturn(courseInStudyPlanID);
        when(courseEditionService.findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID(programmeEditionID, courseInStudyPlanID))
            .thenReturn(List.of(courseEditionID));
        when(courseEditionAssembler.toResponseDTOList(List.of(courseEditionID))).thenReturn(List.of(courseEditionResponseDTO));
        when(courseEditionHateoasAssembler.toCollectionModel(List.of(courseEditionResponseDTO))).thenReturn(mockCollectionModel);

        // Act & Assert
        mockMvc.perform(get("/courseeditions/programmeditions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"));
    }

    @Test
    void findAllCourseEditionsShouldReturnAllCourseEditions() throws Exception {
        // Arrange
        CourseEdition courseEditionDouble1 = mock(CourseEdition.class);
        CourseEdition courseEditionDouble2 = mock(CourseEdition.class);

        CourseEditionResponseDTO responseDouble1 = new CourseEditionResponseDTO(
            "courseEdition123",
            "PRG1",
            UUID.randomUUID(),
            "Course1",
            "Course Name 1",
            LocalDate.of(2024, 1, 1)
        );

        CourseEditionResponseDTO responseDouble2 = new CourseEditionResponseDTO(
            "courseEdition456",
            "PRG2",
            UUID.randomUUID(),
            "Course2",
            "Course Name 2",
            LocalDate.of(2024, 1, 1)
        );

        when(createCourseEditionService.findAll()).thenReturn(List.of(courseEditionDouble1, courseEditionDouble2));
        when(courseEditionAssembler.toResponseDTO(courseEditionDouble1)).thenReturn(responseDouble1);
        when(courseEditionAssembler.toResponseDTO(courseEditionDouble2)).thenReturn(responseDouble2);

        // Act
        MvcResult result = mockMvc.perform(get("/courseeditions"))
            .andExpect(status().isOk())
            .andReturn();

        // Assert
        String jsonResponse = result.getResponse().getContentAsString();
        List<CourseEditionResponseDTO> actualResponse = objectMapper.readValue(jsonResponse,
            objectMapper.getTypeFactory().constructCollectionType(List.class, CourseEditionResponseDTO.class));

        assertEquals(2, actualResponse.size());
    }

    @Test
    void gradeAStudentMethodShouldGradeAStudentSuccessfully_AndReturn201() throws Exception {
        // Arrange
        GradeAStudentRequestDTO requestDTO = new GradeAStudentRequestDTO(
                1234567, 18, "12-04-2025", "Engenharia Informática", "EI",
                "6a7c6ce1-850d-428e-92e1-a8c9de5e3c21", "DSOFT",
                "Desenvolvimento de Software", "12-04-2025");
        GradeAStudentCommand command = mock(GradeAStudentCommand.class);
        GradeAStudentResponseDTO responseDTO = new GradeAStudentResponseDTO(1234567, 18, "12-04-2025", "courseEdition123", "programmeEdition123", "courseInStudyPlan123", "programme123", "schoolYear123", "course123", "studyPlan123");

        when(studentGradeAssembler.toDomain(requestDTO)).thenReturn(command);
        when(gradeAStudentService.gradeAStudent(command)).thenReturn(responseDTO);

        // Act + Assert
        mockMvc.perform(post("/courseeditions/studentgrades/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
            {
                "grade": 18,
                "date": "12-04-2025",
                "studentUniqueNumber": 1234567,
                "programmeName": "Engenharia Informática",
                "programmeAcronym": "EI",
                "schoolYearId": "6a7c6ce1-850d-428e-92e1-a8c9de5e3c21",
                "courseAcronym": "DSOFT",
                "courseName": "Desenvolvimento de Software",
                "studyPlanImplementationDate": "12-04-2025"
            }
        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$._studentUniqueNumber").value(1234567))
                .andExpect(jsonPath("$._grade").value(18))
                .andExpect(jsonPath("$._date").value("12-04-2025"));
    }

    @Test
    void whenDefineRucSuccessfullyThenReturnsAcceptedWithHateoas() throws Exception {
        // Arrange
        SelectedCourseEditionIdDTO courseEditionDTO = new SelectedCourseEditionIdDTO(
                "Data Science", "DSD", UUID.randomUUID(), "ARIT", "Arithmancy", LocalDate.now());

        DefineRucRequestDTO requestDTO = new DefineRucRequestDTO("AAB", courseEditionDTO);

        TeacherID teacherID = mock(TeacherID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        when(courseEditionAssembler.createTeacherID("AAB")).thenReturn(teacherID);
        when(courseEditionAssembler.fromDtoToCourseEditionID(courseEditionDTO)).thenReturn(courseEditionID);
        when(defineRucService.assignRucToCourseEdition(teacherID, courseEditionID)).thenReturn(true);

        when(courseEditionHateoasAssembler.toModel(any(DefineRucResponseDTO.class)))
                .thenAnswer(invocation -> {
                    DefineRucResponseDTO dto = invocation.getArgument(0);
                    EntityModel<DefineRucResponseDTO> model = EntityModel.of(dto);
                    model.add(Link.of("http://localhost/courseeditions/ruc").withRel("define-ruc"));
                    return model;
                });

        // Act & Assert
        mockMvc.perform(patch("/courseeditions/ruc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.teacherID").value("AAB"))
                .andExpect(jsonPath("$.courseEditionDTO.courseName").value("Arithmancy"))
                .andExpect(jsonPath("$._links.define-ruc.href").value("http://localhost/courseeditions/ruc"));
    }

    @Test
    void whenDefineRucFailsThenReturnsInternalServerError() throws Exception {
        // Arrange
        SelectedCourseEditionIdDTO courseEditionDTO = new SelectedCourseEditionIdDTO(
                "LEI", "LEIC", UUID.randomUUID(), "POO", "Programming", LocalDate.now());

        DefineRucRequestDTO requestDTO = new DefineRucRequestDTO("GOM", courseEditionDTO);

        TeacherID teacherID = mock(TeacherID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        when(courseEditionAssembler.createTeacherID("GOM")).thenReturn(teacherID);
        when(courseEditionAssembler.fromDtoToCourseEditionID(courseEditionDTO)).thenReturn(courseEditionID);
        doThrow(new RuntimeException("DB error")).when(defineRucService).assignRucToCourseEdition(teacherID, courseEditionID);

        // Act & Assert
        mockMvc.perform(patch("/courseeditions/ruc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Unexpected error occurred"));
    }

    @Test
    void whenAssignRucThrowsIllegalArgumentExceptionThenReturnsNotFoundWithMessage() throws Exception {
        // Arrange
        SelectedCourseEditionIdDTO courseEditionDTO = new SelectedCourseEditionIdDTO(
                "LEI", "LEIC", UUID.randomUUID(), "POO", "Programming", LocalDate.now());

        DefineRucRequestDTO requestDTO = new DefineRucRequestDTO("GOM", courseEditionDTO);

        TeacherID teacherID = mock(TeacherID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        when(courseEditionAssembler.createTeacherID("GOM")).thenReturn(teacherID);
        when(courseEditionAssembler.fromDtoToCourseEditionID(courseEditionDTO)).thenReturn(courseEditionID);
        when(defineRucService.assignRucToCourseEdition(teacherID, courseEditionID))
                .thenThrow(new IllegalArgumentException("Invalid teacher or course edition"));

        // Act & Assert
        mockMvc.perform(patch("/courseeditions/ruc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Invalid teacher or course edition"));
    }

    @Test
    void getCourseEditionAverageGrade_Success() throws Exception {
        String programmeAcronym = "LEI";
        String VALID_SCHOOL_YEAR_UUID = "123e4567-e89b-12d3-a456-426614174000";
        String courseAcronym = "ESOFT";
        String studyPlanDate = "01-01-2024";
        Double expectedAverageGrade = 15.5;

        when(gradeAStudentService.getAverageGrade(any(CourseEditionID.class))).thenReturn(expectedAverageGrade);

        mockMvc.perform(get("/courseeditions/averagegrade")
                        .param("programmeAcronym", programmeAcronym)
                        .param("schoolYearId", VALID_SCHOOL_YEAR_UUID)
                        .param("courseAcronym", courseAcronym)
                        .param("studyPlanDate", studyPlanDate))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(expectedAverageGrade));
    }

    @Test
    void getCourseEditionAverageGrade_ServiceReturnsNull_ShouldReturnOkWithNullBody() throws Exception {
        String programmeAcronym = "LEI";
        String schoolYearId = "123e4567-e89b-12d3-a456-426614174000";
        String courseAcronym = "ESOFT";
        String studyPlanDate = "01-01-2024";

        when(gradeAStudentService.getAverageGrade(any(PAI.VOs.CourseEditionID.class))).thenReturn(null);

        mockMvc.perform(get("/courseeditions/averagegrade")
                        .param("programmeAcronym", programmeAcronym)
                        .param("schoolYearId", schoolYearId)
                        .param("courseAcronym", courseAcronym)
                        .param("studyPlanDate", studyPlanDate))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
    @Test
    void successfullyGetCourseEditionApprovalRate() throws Exception {
        String programmeAcronym = "LEI";
        String schoolYearId = "123e4567-e89b-12d3-a456-426614174000";
        String courseAcronym = "ESOFT";
        String studyPlanDate = "01-01-2024";
        double expectedApprovalRate = 85.5;

        when(gradeAStudentService.knowApprovalRate(any(CourseEditionID.class)))
                .thenReturn(expectedApprovalRate);

        mockMvc.perform(get("/courseeditions/approvalpercentage")
                        .param("programmeAcronym", programmeAcronym)
                        .param("schoolYearId", schoolYearId)
                        .param("courseAcronym", courseAcronym)
                        .param("studyPlanDate", studyPlanDate))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(expectedApprovalRate));
    }
    @Test
    void shouldReturnZeroIfCourseEditionApprovalRateIsZero() throws Exception {
        String programmeAcronym = "LEI";
        String schoolYearId = "123e4567-e89b-12d3-a456-426614174000";
        String courseAcronym = "ESOFT";
        String studyPlanDate = "01-01-2024";
        double expectedApprovalRate = 0.0;

        when(gradeAStudentService.knowApprovalRate(any(CourseEditionID.class)))
                .thenReturn(expectedApprovalRate);

        mockMvc.perform(get("/courseeditions/approvalpercentage")
                        .param("programmeAcronym", programmeAcronym)
                        .param("schoolYearId", schoolYearId)
                        .param("courseAcronym", courseAcronym)
                        .param("studyPlanDate", studyPlanDate))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(expectedApprovalRate));
    }

    @Test
    public void testGetNumberOfStudentsInCourseEdition() throws Exception {
        // Arrange: criar DTO de exemplo e ID de domínio simulado
        SelectedCourseEditionIdDTO dto = new SelectedCourseEditionIdDTO(
                "Engenharia Informática", "EI",
                UUID.randomUUID(),
                "PAI", "Programação Avançada de Interfaces",
                java.time.LocalDate.of(2025,1,1)
        );

        CourseEditionID mockCourseEditionID = mock(CourseEditionID.class);
        StudentCountDTO studentCountDTODouble = mock(StudentCountDTO.class);
        int studentCount = 5;
        when(courseEditionAssembler.fromDtoToCourseEditionID(dto)).thenReturn(mockCourseEditionID);
        when(courseEditionEnrolmentService.numberOfStudentsEnrolledInCourseEdition(mockCourseEditionID)).thenReturn(studentCount);
        when(studentCountAssembler.fromDomainToDTO(studentCount)).thenReturn(studentCountDTODouble);
        when(studentCountDTODouble.studentCount()).thenReturn(studentCount);

        // Act & Assert
        mockMvc.perform(post("/courseeditions/studentscount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentCount").value(studentCount));
    }

    @Test
    void testGetNumberOfStudentsInCourseEdition_ExceptionThrown() throws Exception {
        SelectedCourseEditionIdDTO dto = new SelectedCourseEditionIdDTO(
                "Engenharia Informática",
                "EI",
                UUID.randomUUID(),
                "PAI",
                "Programação Avançada de Interfaces",
                LocalDate.of(2025, 1, 1)
        );

        // Mockar o assembler para lançar uma exceção
        Mockito.when(courseEditionAssembler.fromDtoToCourseEditionID(Mockito.any()))
                .thenThrow(new RuntimeException("Simulated Exception"));

        mockMvc.perform(post("/courseeditions/studentscount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("")); // ou .andExpect(content().string("null")) dependendo da configuração
    }

    @Test
    void getCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID_Success() throws Exception {
        // Arrange
        CourseEditionRequestDTO requestDTO = new CourseEditionRequestDTO(
            "Software Engineering",     
            "LEIC",                   
            UUID.randomUUID(),          
            "ESOFT",                   
            "Engineering Software",     
            LocalDate.now()             
        );

        ProgrammeEditionID mockProgrammeEditionID = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID mockCourseInStudyPlanID = mock(CourseInStudyPlanID.class);
        CourseEditionID mockCourseEditionID = mock(CourseEditionID.class);
        CourseEditionResponseDTO mockResponseDTO = mock(CourseEditionResponseDTO.class);
        EntityModel<CourseEditionResponseDTO> mockEntityModel = EntityModel.of(mockResponseDTO);
        CollectionModel<EntityModel<CourseEditionResponseDTO>> mockCollectionModel = CollectionModel.of(List.of(mockEntityModel));

        when(courseEditionAssembler.toProgrammeEditionID(requestDTO)).thenReturn(mockProgrammeEditionID);
        when(courseEditionAssembler.toCourseInStudyPlanID(requestDTO)).thenReturn(mockCourseInStudyPlanID);
        when(courseEditionService.findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID(mockProgrammeEditionID, mockCourseInStudyPlanID))
            .thenReturn(List.of(mockCourseEditionID));
        when(courseEditionAssembler.toResponseDTOList(List.of(mockCourseEditionID))).thenReturn(List.of(mockResponseDTO));
        when(courseEditionHateoasAssembler.toCollectionModel(List.of(mockResponseDTO))).thenReturn(mockCollectionModel);

        // Act & Assert
        mockMvc.perform(get("/courseeditions/programmeditions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"));
    }

    @Test
    void getCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID_EmptyResult() throws Exception {
        // Arrange
        CourseEditionRequestDTO requestDTO = new CourseEditionRequestDTO(
            "Software Engineering",
            "LEIC",
            UUID.randomUUID(),
            "ESOFT",
            "Engineering Software",
            LocalDate.now()
        );

        ProgrammeEditionID mockProgrammeEditionID = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID mockCourseInStudyPlanID = mock(CourseInStudyPlanID.class);
        CourseEditionResponseDTO mockResponseDTO = mock(CourseEditionResponseDTO.class);
        EntityModel<CourseEditionResponseDTO> mockEntityModel = EntityModel.of(mockResponseDTO);
        CollectionModel<EntityModel<CourseEditionResponseDTO>> mockCollectionModel = CollectionModel.of(List.of(mockEntityModel));

        when(courseEditionAssembler.toProgrammeEditionID(requestDTO)).thenReturn(mockProgrammeEditionID);
        when(courseEditionAssembler.toCourseInStudyPlanID(requestDTO)).thenReturn(mockCourseInStudyPlanID);
        when(courseEditionService.findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID(mockProgrammeEditionID, mockCourseInStudyPlanID))
            .thenReturn(List.of());
        when(courseEditionAssembler.toResponseDTOList(List.of())).thenReturn(List.of());
        when(courseEditionHateoasAssembler.toCollectionModel(List.of())).thenReturn(CollectionModel.empty());

        // Act & Assert
        mockMvc.perform(get("/courseeditions/programmeditions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestDTO)))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/hal+json"));
    }

    @Test
    void getCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID_InvalidRequest() throws Exception {
        // Arrange
        CourseEditionRequestDTO invalidRequestDTO = new CourseEditionRequestDTO(
            "",                        
            "",                        
            null,                       
            "",                        
            "",                         
            null                        
        );

        // Act & Assert
        mockMvc.perform(get("/courseeditions/programmeditions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequestDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID_ServiceThrowsException() throws Exception {
        // Arrange
        CourseEditionRequestDTO requestDTO = new CourseEditionRequestDTO(
            "Software Engineering",
            "LEIC",
            UUID.randomUUID(),
            "ESOFT",
            "Engineering Software",
            LocalDate.now()
        );

        ProgrammeEditionID mockProgrammeEditionID = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID mockCourseInStudyPlanID = mock(CourseInStudyPlanID.class);

        when(courseEditionAssembler.toProgrammeEditionID(requestDTO)).thenReturn(mockProgrammeEditionID);
        when(courseEditionAssembler.toCourseInStudyPlanID(requestDTO)).thenReturn(mockCourseInStudyPlanID);
        when(courseEditionService.findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID(mockProgrammeEditionID, mockCourseInStudyPlanID))
            .thenThrow(new RuntimeException("Test exception"));

        // Act & Assert
        mockMvc.perform(get("/courseeditions/programmeditions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID_HateoasAssemblerThrowsException() throws Exception {
        // Arrange
        CourseEditionRequestDTO requestDTO = new CourseEditionRequestDTO(
            "Software Engineering",
            "LEIC",
            UUID.randomUUID(),
            "ESOFT",
            "Engineering Software",
            LocalDate.now()
        );

        ProgrammeEditionID mockProgrammeEditionID = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID mockCourseInStudyPlanID = mock(CourseInStudyPlanID.class);
        CourseEditionID mockCourseEditionID = mock(CourseEditionID.class);
        CourseEditionResponseDTO mockResponseDTO = mock(CourseEditionResponseDTO.class);

        when(courseEditionAssembler.toProgrammeEditionID(requestDTO)).thenReturn(mockProgrammeEditionID);
        when(courseEditionAssembler.toCourseInStudyPlanID(requestDTO)).thenReturn(mockCourseInStudyPlanID);
        when(courseEditionService.findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID(mockProgrammeEditionID, mockCourseInStudyPlanID))
            .thenReturn(List.of(mockCourseEditionID));
        when(courseEditionAssembler.toResponseDTOList(List.of(mockCourseEditionID))).thenReturn(List.of(mockResponseDTO));
        when(courseEditionHateoasAssembler.toCollectionModel(List.of(mockResponseDTO)))
            .thenThrow(new RuntimeException("HATEOAS assembly failed"));

        // Act & Assert
        mockMvc.perform(get("/courseeditions/programmeditions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }
}
