package PAI.controllerRest.courseEditionRestControllerTests;

import PAI.VOs.CourseEditionGeneratedID;
import PAI.VOs.TeacherAcronym;
import PAI.VOs.TeacherID;
import PAI.assembler.courseEdition.CourseEditionAssemblerImpl;
import PAI.assembler.courseEdition.CourseEditionRUCHateoasAssembler;
import PAI.assembler.courseEdition.CreateCourseEditionHateoasAssemblerImpl;
import PAI.assembler.courseEdition.ICourseEditionAssembler;
import PAI.dto.courseEdition.CourseEditionRequestDTO;
import PAI.dto.courseEdition.CreateCourseEditionCommand;
import PAI.dto.courseEdition.DefineRucResponseDTO;
import PAI.dto.courseEditionEnrolment.CourseEditionEnrolmentDto;
import PAI.exception.CourseEditionCreationException;
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
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


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
        ICourseEditionAssembler courseEditionAssembler = mock(ICourseEditionAssembler.class);
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

    /*@Test
    void whenCreateCourseEditionWithValidData_thenReturnsCreated() throws Exception {
        // Arrange
        UUID schoolYearID = UUID.randomUUID();
        UUID courseID = UUID.randomUUID();
        UUID generatedId = UUID.randomUUID();

        // Cria o request DTO
        CourseEditionRequestDTO requestDTO = new CourseEditionRequestDTO(
                "Software Development",
                "SDV",
                schoolYearID,
                "SA",
                "Software Architecture",
                LocalDate.of(2023, 9, 1)
        );

        // Cria o command esperado a partir do DTO
        CreateCourseEditionCommand command = new CreateCourseEditionCommand(
                new NameWithNumbersAndSpecialChars(requestDTO.programmeName()),
                new Acronym(requestDTO.programmeAcronym()),
                new SchoolYearID(requestDTO.schoolYearID()),
                new Acronym(requestDTO.courseAcronym()),
                new Name(requestDTO.courseName()),
                new Date(requestDTO.studyPlanImplementationDate())
        );

        // Serviço retorna DTO com o UUID que queremos
        CourseEditionServiceResponseDTO serviceResponseDTO = new CourseEditionServiceResponseDTO(
                generatedId,
                "SDV",
                schoolYearID,
                "SA",
                "Software Architecture",
                LocalDate.of(2023, 9, 1),
                courseID.toString(),
                "AAB"
        );

        // Resposta DTO igual ao esperado
        CourseEditionResponseDTO responseDTO = new CourseEditionResponseDTO(
                generatedId,
                "SDV",
                schoolYearID,
                "SA",
                "Software Architecture",
                LocalDate.of(2023, 9, 1),
                courseID.toString(),
                "AAB"
        );

        // Modelo HATEOAS
        EntityModel<CourseEditionResponseDTO> responseModel = EntityModel.of(responseDTO);

        // Configura os mocks para usar os objetos exatos (sem any())
        when(courseEditionAssembler.toCommand(requestDTO)).thenReturn(command);
        when(createCourseEditionService.createCourseEditionForRestApi(command)).thenReturn(serviceResponseDTO);
        when(courseEditionAssembler.toResponseDTO(serviceResponseDTO)).thenReturn(responseDTO);
        when(createCourseEditionHateoasAssembler.toModel(responseDTO)).thenReturn(responseModel);

        // Para conferir que os IDs estão batendo
        System.out.println("Expected Location: /course-editions/" + generatedId);
        System.out.println("ServiceResponseDTO ID: " + serviceResponseDTO.courseEditionID());

        // Act & Assert
        mockMvc.perform(post("/course-editions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/course-editions/" + generatedId.toString()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }*/




    @Test
    void whenCreateCourseEditionReturnsNull_thenReturnsBadRequest() throws Exception {
        // Arrange
        CourseEditionRequestDTO requestDTO = new CourseEditionRequestDTO(
                "LEIC", UUID.randomUUID(),
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
                 "LEIC", UUID.randomUUID(),
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


}