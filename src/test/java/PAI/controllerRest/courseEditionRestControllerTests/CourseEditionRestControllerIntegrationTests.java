package PAI.controllerRest.courseEditionRestControllerTests;
import PAI.VOs.*;
import PAI.assembler.courseEdition.CourseEditionAssemblerImpl;
import PAI.assembler.courseEdition.CourseEditionHateoasAssembler;
import PAI.dto.courseEdition.*;
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
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CourseEditionRestControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DefineRucServiceImpl defineRucService;

    @MockBean
    private CourseEditionAssemblerImpl courseEditionAssembler;

    @MockBean
    private CourseEditionHateoasAssembler courseEditionHateoasAssembler;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ICreateCourseEditionService createCourseEditionService;


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
                Link.of("http://localhost/courseeditions/" + courseEditionId + "/ruc").withRel("define-ruc"));

        when(courseEditionAssembler.createTeacherID("AAB")).thenReturn(teacherID);
        when(courseEditionAssembler.fromDtoToCourseEditionGeneratedID(any())).thenReturn(courseEditionGeneratedID);
        when(defineRucService.assignRucToCourseEdition(teacherID, courseEditionGeneratedID)).thenReturn(true);
        when(courseEditionHateoasAssembler.toModel(any())).thenReturn(responseModel);

        // Act & Assert
        mockMvc.perform(patch("/courseeditions/{id}/ruc", courseEditionId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.teacherID").value("AAB"))
                .andExpect(jsonPath("$._links.define-ruc.href").value("http://localhost/courseeditions/" + courseEditionId + "/ruc"));
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

        mockMvc.perform(patch("/courseeditions/{id}/ruc", courseEditionId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Teacher not found"));
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

        mockMvc.perform(patch("/courseeditions/{id}/ruc", nonExistentId)
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

        mockMvc.perform(patch("/courseeditions/ruc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validRequestBody))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void whenCreateCourseEditionWithValidData_thenReturnsCreated() throws Exception {
        // Arrange
        CourseEditionRequestDTO requestDTO = new CourseEditionRequestDTO(
                "Software Development", "SDV", UUID.randomUUID(),
                "SA", "Software Architecture", LocalDate.of(2023, 9, 1));

        CreateCourseEditionCommand command = new CreateCourseEditionCommand(
                new NameWithNumbersAndSpecialChars(requestDTO.programmeName()), new Acronym(requestDTO.programmeAcronym()),
                new SchoolYearID(requestDTO.schoolYearID()), new Acronym(requestDTO.courseAcronym()),
                new Name(requestDTO.courseName()), new Date(requestDTO.studyPlanImplementationDate()));

        CourseEditionResponseDTO responseDTO = new CourseEditionResponseDTO(
                "courseEditionID123","SDV",
                requestDTO.schoolYearID(), "SA", "Software Architecture",
                LocalDate.of(2023, 9, 1));

        when(courseEditionAssembler.toCommand(any())).thenReturn(command);
        when(createCourseEditionService.createCourseEditionAndReturnDTO(any(), any())).thenReturn(responseDTO);

        // Act
        MvcResult result = mockMvc.perform(post("/courseeditions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/courseeditions/" + responseDTO.courseEditionID()))
                .andReturn();

        // Assert
        String jsonResponse = result.getResponse().getContentAsString();
        CourseEditionResponseDTO actualResponse = objectMapper.readValue(jsonResponse, CourseEditionResponseDTO.class);

        assertEquals(responseDTO.courseEditionID(), actualResponse.courseEditionID());
        assertEquals(responseDTO.programmeAcronym(), actualResponse.programmeAcronym());
        assertEquals(responseDTO.schoolYearID(), actualResponse.schoolYearID());
        assertEquals(responseDTO.courseAcronym(), actualResponse.courseAcronym());
        assertEquals(responseDTO.courseName(), actualResponse.courseName());
        assertEquals(responseDTO.studyPlanImplementationDate(), actualResponse.studyPlanImplementationDate());
    }

    @Test
    void whenCreateCourseEditionReturnsNull_thenReturnsBadRequest() throws Exception {
        // Arrange
        CourseEditionRequestDTO requestDTO = new CourseEditionRequestDTO(
                "LEI", "LEIC", UUID.randomUUID(),
                "SA", "Software Architecture", LocalDate.of(2023, 9, 1));

        CreateCourseEditionCommand command = new CreateCourseEditionCommand(
                new NameWithNumbersAndSpecialChars(requestDTO.programmeName()), new Acronym(requestDTO.programmeAcronym()),
                new SchoolYearID(requestDTO.schoolYearID()), new Acronym(requestDTO.courseAcronym()),
                new Name(requestDTO.courseName()), new Date(requestDTO.studyPlanImplementationDate()));

        when(courseEditionAssembler.toCommand(any())).thenReturn(command);
        when(createCourseEditionService.createCourseEditionAndReturnDTO(any(), any())).thenReturn(null);

        // Act & Assert
        mockMvc.perform(post("/courseeditions")
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

        when(courseEditionAssembler.toCommand(any())).thenThrow(new RuntimeException("Test Exception"));

        // Act & Assert
        mockMvc.perform(post("/courseeditions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Test Exception"));
    }
}

