package PAI.controllerRest.TeacherCategoryRestControllerTests;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.domain.teacherCategory.TeacherCategory;
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

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class TeacherCategoryRestControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TeacherCategoryRepositorySpringDataImpl repository;

    @Test
    void shouldReturn400_WhenNameIsMissing() throws Exception {
        // arrange

        String uri = "/teachercategories";
        String invalidRequestBody = "{}";

        MvcResult result = mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidRequestBody)).andReturn();

        // act
        int statusCode = result.getResponse().getStatus();

        // assert
        assertEquals(HttpStatus.BAD_REQUEST.value(), statusCode);
    }

    @Test
    void shouldReturn400_WhenNameHasLessThanThreeCharacters() throws Exception {
        // arrange
        String uri = "/teachercategories";
        String invalidRequestBody = "{\"name\": \"12\"}";

        MvcResult result = mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidRequestBody)).andReturn();

        // act
        int statusCode = result.getResponse().getStatus();

        // assert
        assertEquals(HttpStatus.BAD_REQUEST.value(), statusCode);
    }

    @Test
    void shouldReturn400_WhenNameHasEspecialCharacters() throws Exception {
        // arrange
        String uri = "/teachercategories";
        String invalidRequestBody = "{\"name\": \"+++++???\"}";

        MvcResult result = mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidRequestBody)).andReturn();

        // act
        int statusCode = result.getResponse().getStatus();

        // assert
        assertEquals(HttpStatus.BAD_REQUEST.value(), statusCode);
    }

    @Test
    void shouldReturn409_WhenTeacherCategoryAlreadyExists() throws Exception {
        // arrange
        String uri = "/teachercategories";
        String validRequestBody = "{\"name\": \"Professor Auxiliar\"}";

        MvcResult result = mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(validRequestBody)).andReturn();

        // act
        int statusCode = result.getResponse().getStatus();

        // assert
        assertEquals(HttpStatus.CONFLICT.value(), statusCode);
    }

    @Test
    void shouldReturn201_WhenTeacherCategoryWasSuccessfullyCreated() throws Exception {
        // arrange
        String uri = "/teachercategories";
        String validRequestBody = "{\"name\": \"Assistant\"}";

        MvcResult result = mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(validRequestBody)).andReturn();

        // act
        int statusCode = result.getResponse().getStatus();

        // assert
        assertEquals(HttpStatus.CREATED.value(), statusCode);
    }

    // testing getTeacherCategoryByID method

    @Test
    void shouldReturn200_WhenTeacherCategoryWasFoundByID() throws Exception {
        // arrange
        Name name = new Name("Professor Auxiliar");
        TeacherCategoryID id = new TeacherCategoryID(UUID.randomUUID());
        TeacherCategory category = new TeacherCategory(id, name);

        repository.save(category);

        String uri = "/teachercategories/" + category.getId().getValue().toString();

        MvcResult result = mockMvc.perform(get(uri)).andReturn();

        // act
        int statusCode = result.getResponse().getStatus();

        // assert
        assertEquals(HttpStatus.OK.value(), statusCode);
    }

    @Test
    void shouldReturn404_WhenTeacherCategoryDoesNotExist() throws Exception {
        // arrange
        String nonExistingId = UUID.randomUUID().toString();
        String uri = "/teachercategories/" + nonExistingId;

        MvcResult result = mockMvc.perform(get(uri)).andReturn();

        // act
        int statusCode = result.getResponse().getStatus();

        // assert
        assertEquals(HttpStatus.NOT_FOUND.value(),statusCode);
    }
}
