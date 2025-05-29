package PAI.controllerRest.courseEditionRestControllerTests;
import PAI.VOs.CourseEditionID;
import PAI.VOs.TeacherAcronym;
import PAI.VOs.TeacherID;
import PAI.assembler.courseEdition.CourseEditionAssemblerImpl;
import PAI.assembler.courseEdition.CourseEditionHateoasAssembler;
import PAI.controllerRest.CourseEditionRestController;
import PAI.dto.courseEdition.DefineRucResponseDTO;
import PAI.dto.courseEdition.SelectedCourseEditionIdDTO;
import PAI.service.courseEdition.DefineRucServiceImpl;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
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


    @Test
    void shouldDefineRucSuccessfullyAndReturnAccepted() throws Exception {
        // Arrange
        String requestBody = """
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

        TeacherID teacherID = new TeacherID(new TeacherAcronym("AAB"));
        SelectedCourseEditionIdDTO courseEditionDTO = new SelectedCourseEditionIdDTO(
                "Data Science", "DSD", UUID.fromString("550e8400-e29b-41d4-a716-446655440002"),
                "ARIT", "Arithmancy", LocalDate.parse("2023-07-01")
        );
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        DefineRucResponseDTO responseDTO = new DefineRucResponseDTO("AAB", courseEditionDTO);
        EntityModel<DefineRucResponseDTO> responseModel = EntityModel.of(responseDTO,
                linkTo(methodOn(CourseEditionRestController.class).defineRucForCourseEdition(null))
                        .withRel("define-ruc"));

        when(courseEditionAssembler.createTeacherID("AAB")).thenReturn(teacherID);
        when(courseEditionAssembler.fromDtoToCourseEditionID(any())).thenReturn(courseEditionID);
        when(defineRucService.assignRucToCourseEdition(teacherID, courseEditionID)).thenReturn(true);
        when(courseEditionHateoasAssembler.toModel(any())).thenReturn(responseModel);

        // Act & Assert
        mockMvc.perform(patch("/courseeditions/ruc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.teacherID").value("AAB"))
                .andExpect(jsonPath("$._links.define-ruc.href").value("http://localhost/courseeditions/ruc"));
    }

    @Test
    void shouldReturnErrorWhenTeacherIDIsMalformed() throws Exception {
        String invalidRequestBody = """
    {
      "teacherID": "INVALID_UUID",
      "courseEditionDTO": {
        "programmeName": "Computer Sci",
        "programmeAcronym": "CSD",
        "schoolYearID": "00001111-1111-1111-1111-111111111111",
        "courseAcronym": "ALCH",
        "courseName": "Alchemy",
        "studyPlanProgrammeName": "",
        "studyPlanProgrammeAcronym": "",
        "studyPlanImplementationDate": "2022-03-15"
      }
    }
    """;

        mockMvc.perform(patch("/courseeditions/ruc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidRequestBody))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnErrorWhenTeacherIDDoesNotExist() throws Exception {

        String validRequestBody = """
    {
      "teacherID": "BBB",
      "courseEditionDTO": {
        "programmeName": "Computer Sci",
        "programmeAcronym": "CSD",
        "schoolYearID": "00001111-1111-1111-1111-111111111111",
        "courseAcronym": "ALCH",
        "courseName": "Alchemy",
        "studyPlanProgrammeName": "",
        "studyPlanProgrammeAcronym": "",
        "studyPlanImplementationDate": "2022-03-15"
      }
    }
    """;

        mockMvc.perform(patch("/courseeditions/ruc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validRequestBody))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnErrorWhenCourseEditionIDDoesNotExist() throws Exception {
        String validRequestBody = """
    {
      "teacherID": "AAB",
      "courseEditionDTO": {
        "programmeName": "Science Dev",
        "programmeAcronym": "CSD",
        "schoolYearID": "00001111-1111-1111-1111-111111111111",
        "courseAcronym": "POT5",
        "courseName": "PotionsV",
        "studyPlanProgrammeName": "",
        "studyPlanProgrammeAcronym": "",
        "studyPlanImplementationDate": "2022-03-15"
      }
    }
    """;

        mockMvc.perform(patch("/courseeditions/ruc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validRequestBody))
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
}

