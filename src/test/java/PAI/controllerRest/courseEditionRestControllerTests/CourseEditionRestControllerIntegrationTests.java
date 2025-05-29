package PAI.controllerRest.courseEditionRestControllerTests;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CourseEditionRestControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldDefineRucSuccessfullyAndReturnAccepted() throws Exception {
        // Arrange
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

        // Act & Assert
        mockMvc.perform(patch("/courseeditions/ruc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validRequestBody))
                .andExpect(status().isAccepted())
                .andExpect(content().string("RUC successfully updated"));
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
                .andExpect(status().isBadRequest());
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
}

