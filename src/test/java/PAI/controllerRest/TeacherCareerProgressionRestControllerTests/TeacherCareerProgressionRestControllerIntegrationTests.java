package PAI.controllerRest.TeacherCareerProgressionRestControllerTests;

import PAI.VOs.*;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import PAI.persistence.springdata.teacherCareerProgression.TeacherCareerProgressionRepoSpringDataImpl;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class TeacherCareerProgressionRestControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TeacherCareerProgressionRepoSpringDataImpl repository;

    // testing getAllTeacherCareerProgressions method

    @Test
    void shouldReturn200_WhenWeHaveAListOfTeacherCareerProgressions() throws Exception {
        // arrange

        String uri = "/teacher-career-progressions";;

        MvcResult result = mockMvc.perform(get(uri)).andReturn ();

        // act
        int statusCode = result.getResponse().getStatus();

        // assert
        assertEquals(HttpStatus.OK.value(), statusCode);
    }

    // testing getTeacherCareerProgressionById method

    @Test
    void shouldReturn200_WhenTeacherCareerCategoryWasFoundByID() throws Exception {
        // arrange
        TeacherID teacherID = new TeacherID(new TeacherAcronym("XXX"));
        Date date = new Date(LocalDate.of(2025, 6, 1));
        TeacherCategoryID teacherCategoryID = new TeacherCategoryID(UUID.randomUUID());
        WorkingPercentage workingPercentage = new WorkingPercentage(80);
        TeacherCareerProgressionID teacherCareerProgressionID = new TeacherCareerProgressionID(UUID.randomUUID());
        TeacherCareerProgression teacherCareerProgression = new TeacherCareerProgression(teacherCareerProgressionID, date, teacherCategoryID,
                workingPercentage, teacherID);

        repository.save(teacherCareerProgression);

        String uri = "/teacher-career-progressions/" + teacherCareerProgression.identity().getIDValue();

        MvcResult result = mockMvc.perform(get(uri)).andReturn();

        // act
        int statusCode = result.getResponse().getStatus();

        // assert
        assertEquals(HttpStatus.OK.value(), statusCode);
    }

    @Test
    void shouldReturn404_WhenTeacherCareerProgressionDoesNotExist() throws Exception {
        // arrange
        String nonExistingId = UUID.randomUUID().toString();
        String uri = "/teacher-career-progressions/" + nonExistingId;

        MvcResult result = mockMvc.perform(get(uri)).andReturn();

        // act
        int statusCode = result.getResponse().getStatus();

        // assert
        assertEquals(HttpStatus.NOT_FOUND.value(),statusCode);
    }
}
