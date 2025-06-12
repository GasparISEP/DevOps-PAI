package PAI.controllerRest.TeacherRestControllerTests;

import PAI.VOs.*;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import PAI.domain.teacherCategory.TeacherCategory;
import PAI.persistence.springdata.teacherCareerProgression.ITeacherCareerProgressionRepoSpringData;
import PAI.persistence.springdata.teacherCareerProgression.TeacherCareerProgressionRepoSpringDataImpl;
import PAI.persistence.springdata.teacherCategory.TeacherCategoryRepositorySpringDataImpl;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class TeacherRestControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TeacherCareerProgressionRepoSpringDataImpl repository;

    @Autowired
    private TeacherCategoryRepositorySpringDataImpl teacherCategoryRepositorySpringData;

    @Test
    void shouldReturn201_WhenTeacherCareerProgressionWasSuccessfullyCreated() throws Exception {
        // arrange

        TeacherCareerProgressionID teacherCareerProgressionID = new TeacherCareerProgressionID(UUID.randomUUID());
        Date date = new Date(LocalDate.of(2025,6,1));
        TeacherCategoryID teacherCategoryID = new TeacherCategoryID(UUID.fromString("fc7910e4-b840-4df4-b8e5-2ddda0385b32"));
        TeacherCategoryID teacherCategoryID1 = new TeacherCategoryID(UUID.fromString("fc7910e4-b840-4df4-b8e5-2ddda0385b33"));
        WorkingPercentage workingPercentage = new WorkingPercentage(90);
        TeacherID teacherID = new TeacherID( new TeacherAcronym( "AAA"));
        TeacherCareerProgression teacherCareerProgression = new TeacherCareerProgression(teacherCareerProgressionID, date, teacherCategoryID1,
                workingPercentage, teacherID);

        TeacherCategory teacherCategory = new TeacherCategory(teacherCategoryID, new Name("Assistant"));

        teacherCategoryRepositorySpringData.save(teacherCategory);
        repository.save(teacherCareerProgression);

        String teacherId = "AAA";
        String validRequestBody = """
            {
                "date": "2025-06-02",
                "teacherCategoryID": "fc7910e4-b840-4df4-b8e5-2ddda0385b32"
            }
        """;

        MvcResult result = mockMvc.perform(post("/teachers/" + teacherId + "/careerprogressions/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(validRequestBody)).andReturn();

        // act
        int statusCode = result.getResponse().getStatus();

        // assert
        assertEquals(HttpStatus.CREATED.value(), statusCode);
    }

    @Test
    void shouldReturn400_WhenTeacherCategoryIsTheSameAsThePreviousOne() throws Exception {
        // arrange

        TeacherCareerProgressionID teacherCareerProgressionID = new TeacherCareerProgressionID(UUID.randomUUID());
        Date date = new Date(LocalDate.of(2025,6,1));
        TeacherCategoryID teacherCategoryID = new TeacherCategoryID(UUID.fromString("fc7910e4-b840-4df4-b8e5-2ddda0385b32"));
        WorkingPercentage workingPercentage = new WorkingPercentage(90);
        TeacherID teacherID = new TeacherID( new TeacherAcronym( "AAA"));
        TeacherCareerProgression teacherCareerProgression = new TeacherCareerProgression(teacherCareerProgressionID, date, teacherCategoryID,
                workingPercentage, teacherID);

        TeacherCategory teacherCategory = new TeacherCategory(teacherCategoryID, new Name("Assistant"));

        teacherCategoryRepositorySpringData.save(teacherCategory);
        repository.save(teacherCareerProgression);

        String teacherId = "AAA";
        String validRequestBody = """
            {
                "date": "2025-06-02",
                "teacherCategoryID": "fc7910e4-b840-4df4-b8e5-2ddda0385b32"
            }
        """;

        MvcResult result = mockMvc.perform(post("/teachers/" + teacherId + "/careerprogressions/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(validRequestBody)).andReturn();

        // act
        int statusCode = result.getResponse().getStatus();

        // assert
        assertEquals(HttpStatus.BAD_REQUEST.value(), statusCode);
    }

    @Test
    void shouldReturn404_WhenTeacherCategoryIsInvalidBecauseDoesNotExists() throws Exception {
        // arrange

        TeacherCareerProgressionID teacherCareerProgressionID = new TeacherCareerProgressionID(UUID.randomUUID());
        Date date = new Date(LocalDate.of(2025,6,1));
        TeacherCategoryID teacherCategoryID = new TeacherCategoryID(UUID.fromString("fc7910e4-b840-4df4-b8e5-2ddda0385b32"));
        WorkingPercentage workingPercentage = new WorkingPercentage(90);
        TeacherID teacherID = new TeacherID( new TeacherAcronym( "AAA"));
        TeacherCareerProgression teacherCareerProgression = new TeacherCareerProgression(teacherCareerProgressionID, date, teacherCategoryID,
                workingPercentage, teacherID);

        repository.save(teacherCareerProgression);

        String teacherId = "AAA";
        String validRequestBody = """
            {
                "date": "2025-06-02",
                "teacherCategoryID": "fc7910e4-b840-4df4-b8e5-2ddda0385b32"
            }
        """;

        MvcResult result = mockMvc.perform(post("/teachers/" + teacherId + "/careerprogressions/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(validRequestBody)).andReturn();

        // act
        int statusCode = result.getResponse().getStatus();

        // assert
        assertEquals(HttpStatus.NOT_FOUND.value(), statusCode);
    }

    @Test
    void shouldReturn404_WhenHasNoPreviousTeacherCareerProgression() throws Exception {
        // arrange
        TeacherCareerProgressionID teacherCareerProgressionID = new TeacherCareerProgressionID(UUID.randomUUID());
        Date date = new Date(LocalDate.of(2025,6,1));
        TeacherCategoryID teacherCategoryID = new TeacherCategoryID(UUID.fromString("fc7910e4-b840-4df4-b8e5-2ddda0385b32"));
        TeacherCategoryID teacherCategoryID1 = new TeacherCategoryID(UUID.fromString("fc7910e4-b840-4df4-b8e5-2ddda0385b33"));
        WorkingPercentage workingPercentage = new WorkingPercentage(90);
        TeacherID teacherID = new TeacherID( new TeacherAcronym( "AAA"));
        TeacherCareerProgression teacherCareerProgression = new TeacherCareerProgression(teacherCareerProgressionID, date, teacherCategoryID1,
                workingPercentage, teacherID);

        TeacherCategory teacherCategory = new TeacherCategory(teacherCategoryID, new Name("Assistant"));

        teacherCategoryRepositorySpringData.save(teacherCategory);
        repository.save(teacherCareerProgression);

        String teacherId = "XXX";
        String validRequestBody = """
            {
                "date": "2021-06-01",
                "teacherCategoryID": "fc7910e4-b840-4df4-b8e5-2ddda0385b32"
            }
        """;

        MvcResult result = mockMvc.perform(post("/teachers/" + teacherId + "/careerprogressions/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(validRequestBody)).andReturn();

        // act
        int statusCode = result.getResponse().getStatus();

        // assert
        assertEquals(HttpStatus.NOT_FOUND.value(), statusCode);
    }

    @Test
    void shouldReturn400_WhenHasNothingInBody() throws Exception {
        // arrange
        String teacherId = "AAA";
        String validRequestBody = """
            {
                "date": "",
                "teacherCategoryID": ""
            }
        """;

        MvcResult result = mockMvc.perform(post("/teachers/" + teacherId + "/careerprogressions/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(validRequestBody)).andReturn();

        // act
        int statusCode = result.getResponse().getStatus();

        // assert
        assertEquals(HttpStatus.BAD_REQUEST.value(), statusCode);
    }

}
