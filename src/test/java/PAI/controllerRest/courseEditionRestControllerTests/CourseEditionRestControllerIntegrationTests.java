package PAI.controllerRest.courseEditionRestControllerTests;

import PAI.VOs.*;
import PAI.assembler.courseEdition.CourseEditionAssemblerImpl;
import PAI.assembler.courseEdition.CourseEditionRUCHateoasAssembler;
import PAI.assembler.courseEdition.CreateCourseEditionHateoasAssemblerImpl;
import PAI.dto.courseEdition.*;
import PAI.exception.CourseEditionCreationException;
import PAI.persistence.datamodel.studentGrade.StudentGradeDM;
import PAI.persistence.springdata.studentGrade.IStudentGradeRepositorySpringData;
import PAI.service.courseEdition.DefineRucServiceImpl;
import PAI.service.courseEdition.ICreateCourseEditionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CourseEditionRestControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IStudentGradeRepositorySpringData studentGradeRepository;

    @MockBean
    private DefineRucServiceImpl defineRucService;

    @MockBean
    private CourseEditionAssemblerImpl courseEditionAssembler;

    @MockBean
    private CourseEditionRUCHateoasAssembler courseEditionRUCHateoasAssembler;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ICreateCourseEditionService createCourseEditionService;

    @MockBean
    private CreateCourseEditionHateoasAssemblerImpl createCourseEditionHateoasAssembler;



    @Test
    void shouldDefineRucSuccessfullyAndReturnAccepted() throws Exception {
        // Arrange
        UUID courseEditionId = UUID.randomUUID();

        String requestBody = """
    {
        "teacherID": "AAB",
        "courseEditionID": "%s"
    }
    """.formatted(courseEditionId);

        TeacherID teacherID = new TeacherID(new TeacherAcronym("AAB"));
        CourseEditionGeneratedID courseEditionGeneratedID = mock(CourseEditionGeneratedID.class);

        DefineRucResponseDTO responseDTO = new DefineRucResponseDTO("AAB", courseEditionId);
        EntityModel<DefineRucResponseDTO> responseModel = EntityModel.of(responseDTO,
                Link.of("http://localhost/course-editions/" + courseEditionId + "/ruc").withRel("define-ruc"));

        when(courseEditionAssembler.createTeacherID("AAB")).thenReturn(teacherID);
        when(courseEditionAssembler.fromDtoToCourseEditionGeneratedID(any())).thenReturn(courseEditionGeneratedID);
        when(defineRucService.assignRucToCourseEdition(teacherID, courseEditionGeneratedID)).thenReturn(true);
        when(courseEditionRUCHateoasAssembler.toModel(any())).thenReturn(responseModel);

        // Act & Assert
        mockMvc.perform(patch("/course-editions/{id}/ruc", courseEditionId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.teacherID").value("AAB"))
                .andExpect(jsonPath("$._links.define-ruc.href").value("http://localhost/course-editions/" + courseEditionId + "/ruc"));
    }


    @Test
    void shouldReturnErrorWhenTeacherIDDoesNotExist() throws Exception {
        UUID courseEditionId = UUID.randomUUID();

        String requestBody = """
        {
          "teacherID": "BBB"
        }
        """;

        when(courseEditionAssembler.createTeacherID("BBB"))
                .thenThrow(new IllegalArgumentException("Teacher not found"));

        mockMvc.perform(patch("/course-editions/{id}/ruc", courseEditionId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())  // <-- Corrigido para 400
                .andExpect(jsonPath("$.code").value("ARGUMENT_INVALID"))
                .andExpect(jsonPath("$.message").value("Teacher not found"));
    }


    @Test
    void shouldReturnErrorWhenCourseEditionIDDoesNotExist() throws Exception {
        UUID nonExistentId = UUID.fromString("00001111-1111-1111-1111-111111111111");

        String requestBody = """
    {
      "teacherID": "AAB",
      "courseEditionID": "%s"
    }
    """.formatted(nonExistentId);

        when(courseEditionAssembler.createTeacherID("AAB"))
                .thenReturn(new TeacherID(new TeacherAcronym("AAB")));

        when(courseEditionAssembler.fromDtoToCourseEditionGeneratedID(any()))
                .thenReturn(mock(CourseEditionGeneratedID.class));

        when(defineRucService.assignRucToCourseEdition(any(), any())).thenReturn(false);

        mockMvc.perform(patch("/course-editions/{id}/ruc", nonExistentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isNotFound());
    }


    @Test
    void shouldReturnInternalServerError() throws Exception {
        String validRequestBody = """
    {
        "teacherID": "AAB",
        "courseEditionDTO": {
            "programmeName": "Data Science",
            "programmeAcronym": "DSD",
            "schoolYearID": "550e8400-e29b-41d4-a716-446655440002",
            "courseAcronym": "ARIT",
            "courseName": "Arithmancy",
            "studyPlanProgrammeName": "",
            "studyPlanProgrammeAcronym": "",
            "studyPlanImplementationDate": "2023-07-01"
        }
    }
    """;

        when(defineRucService.assignRucToCourseEdition(any(), any()))
                .thenThrow(new RuntimeException("Unexpected failure"));

        mockMvc.perform(patch("/course-editions/ruc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validRequestBody))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void whenCreateCourseEditionWithValidData_thenReturnsCreated() throws Exception {
        // Arrange
        UUID schoolYearID = UUID.randomUUID();
        UUID generatedId = UUID.randomUUID();

        CourseEditionRequestDTO requestDTO = new CourseEditionRequestDTO(
                "Software Development",
                "SDV",
                schoolYearID,
                "SA",
                "Software Architecture",
                LocalDate.of(2023, 9, 1)
        );

        CreateCourseEditionCommand command = new CreateCourseEditionCommand(
                new NameWithNumbersAndSpecialChars(requestDTO.programmeName()),
                new Acronym(requestDTO.programmeAcronym()),
                new SchoolYearID(requestDTO.schoolYearID()),
                new Acronym(requestDTO.courseAcronym()),
                new Name(requestDTO.courseName()),
                new Date(requestDTO.studyPlanImplementationDate())
        );

        CourseEditionServiceResponseDTO serviceResponseDTO = new CourseEditionServiceResponseDTO(
                generatedId,
                "SDV",
                schoolYearID,
                "SA",
                "Software Architecture",
                LocalDate.of(2023, 9, 1),
                generatedId.toString()
        );

        CourseEditionResponseIDDTO responseIDDTO = new CourseEditionResponseIDDTO(
                "SDV",
                schoolYearID,
                "SA",
                "Software Architecture",
                LocalDate.of(2023, 9, 1),
                generatedId.toString()
        );

        EntityModel<CourseEditionResponseIDDTO> responseModel = EntityModel.of(responseIDDTO);

        when(courseEditionAssembler.toCommand(any(CourseEditionRequestDTO.class))).thenReturn(command);
        when(createCourseEditionService.createCourseEditionForRestApi(command)).thenReturn(serviceResponseDTO);
        when(courseEditionAssembler.toResponseIDDTO(serviceResponseDTO)).thenReturn(responseIDDTO);
        when(createCourseEditionHateoasAssembler.toModel(responseIDDTO)).thenReturn(responseModel);

        // Act & Assert
        mockMvc.perform(post("/course-editions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/course-editions/" + generatedId.toString()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    void whenCreateCourseEditionReturnsNull_thenReturnsBadRequest() throws Exception {
        // Arrange
        CourseEditionRequestDTO requestDTO = new CourseEditionRequestDTO(
                "LEI", "LEIC", UUID.randomUUID(),
                "SA", "Software Architecture", LocalDate.of(2023, 9, 1)
        );

        CreateCourseEditionCommand command = mock(CreateCourseEditionCommand.class);

        when(courseEditionAssembler.toCommand(any())).thenReturn(command);
        when(createCourseEditionService.createCourseEditionForRestApi(any()))
                .thenThrow(new CourseEditionCreationException("Failed to create CourseEdition.", new RuntimeException()));


        // Act & Assert
        mockMvc.perform(post("/course-editions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }



    @Test
    void whenCreateCourseEditionThrowsException_thenReturnsBadRequest() throws Exception {
        // Arrange
        CourseEditionRequestDTO requestDTO = new CourseEditionRequestDTO(
                "LEI", "LEIC", UUID.randomUUID(),
                "SA", "Software Architecture", LocalDate.of(2023, 9, 1));

        when(courseEditionAssembler.toCommand(any())).thenThrow(new IllegalArgumentException("Test Exception"));

        // Act & Assert
        mockMvc.perform(post("/course-editions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Test Exception"))
                .andExpect(jsonPath("$.code").value("ARGUMENT_INVALID"));
    }


    @Sql(scripts = "/test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void whenGetEnrolments_thenReturnMinimalDTOsOnly() throws Exception {
        mockMvc.perform(get("/course-editions/students/1234567/courseeditionenrolments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].courseEditionID").exists())
                .andExpect(jsonPath("$[0].courseEditionName").exists())
                .andExpect(jsonPath("$[0].programmeAcronym").doesNotExist())
                .andExpect(jsonPath("$[0].schoolYearId").doesNotExist())
                .andExpect(jsonPath("$[0].courseAcronym").doesNotExist())
                .andExpect(jsonPath("$[0].studyPlanDate").doesNotExist());
    }

    @Test
    @Sql(scripts = {"/test-data-completo.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void whenGradeAStudent_thenReturnsCreatedWithoutHateoas() throws Exception {
        mockMvc.perform(post("/course-editions/studentgrades/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
        {
            "studentUniqueNumber": 1102840,
            "grade": 18,
            "date": "13-06-2025",
            "programmeName": "Engenharia Informática",
            "programmeAcronym": "LEI",
            "schoolYearId": "11111111-1111-1111-1111-111111111111",
            "courseAcronym": "PAI",
            "courseName": "Processos de Apoio à Inovação",
            "studyPlanImplementationDate": "01-09-2020"
        }
        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$._studentUniqueNumber").value(1102840))
                .andExpect(jsonPath("$._grade").value(18.0))
                .andExpect(jsonPath("$._links").doesNotExist());
    }



    @Test
    @Sql(scripts = "/test-data-completo.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void whenGradeAStudent_thenStudentGradeIsPersisted() throws Exception {
        String requestJson = """
    {
        "studentUniqueNumber": 1102840,
        "grade": 18,
        "date": "13-06-2025",
        "programmeName": "Engenharia Informática",
        "programmeAcronym": "LEI",
        "schoolYearId": "11111111-1111-1111-1111-111111111111",
        "courseAcronym": "PAI",
        "courseName": "Processos de Apoio à Inovação",
        "studyPlanImplementationDate": "01-09-2020"
    }
    """;

        mockMvc.perform(post("/course-editions/studentgrades/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated());

        List<StudentGradeDM> allGrades = studentGradeRepository.findAll();
        assertFalse(allGrades.isEmpty(), "Expected at least one StudentGrade persisted.");
        StudentGradeDM grade = allGrades.get(0);
        assertEquals(1102840, grade.getStudentId().getUniqueNumber());
        assertEquals(18.0, grade.getGrade());
        assertEquals(LocalDate.of(2025, 6, 13), grade.getDate());
    }



    @Test
    @Sql(scripts = {"/test-data-completo.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void whenGradeAStudentWithHateoas_thenReturnsCreatedWithLinks() throws Exception {
        String requestJson = """
    {
           "studentUniqueNumber": 1102840,
           "grade": 18,
           "date": "13-06-2025",
           "programmeName": "Engenharia Informática",
           "programmeAcronym": "LEI",
           "schoolYearId": "11111111-1111-1111-1111-111111111111",
           "courseAcronym": "PAI",
           "courseName": "Processos de Apoio à Inovação",
           "studyPlanImplementationDate": "01-09-2020"
    }
    """;

        mockMvc.perform(post("/course-editions/studentgrades/register/hateoas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith("application/hal+json"))
                .andExpect(jsonPath("$._studentUniqueNumber").value(1102840))
                .andExpect(jsonPath("$._grade").value(18.0))
                .andExpect(jsonPath("$._links.student-details.href").value(
                        org.hamcrest.Matchers.containsString("/students/1102840")));
    }
}