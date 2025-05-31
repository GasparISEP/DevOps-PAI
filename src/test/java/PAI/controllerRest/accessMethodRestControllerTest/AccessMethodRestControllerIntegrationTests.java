package PAI.controllerRest.accessMethodRestControllerTest;

import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.VOs.AccessMethodID;
import PAI.domain.accessMethod.AccessMethod;
import PAI.persistence.springdata.accessMethod.AccessMethodRepositorySpringDataImpl;
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
public class AccessMethodRestControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccessMethodRepositorySpringDataImpl repository;

    @Test
    void shouldReturn201_WhenAccessMethodSuccessfullyCreated() throws Exception {
        String uri = "/access-methods";
        String validRequestBody = "{\"name\": \"New Method\"}";

        MvcResult result = mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(validRequestBody)).andReturn();

        assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
    }

    @Test
    void shouldReturn200_WhenAccessMethodFoundByID() throws Exception {
        //Arrange
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("My Method");
        AccessMethodID id = new AccessMethodID(UUID.randomUUID());
        AccessMethod method = new AccessMethod(id, name);
        repository.save(method);

        String uri = "/access-methods/" + id.getAccessMethodID();

        //Act
        MvcResult result = mockMvc.perform(get(uri)).andReturn();

        //Assert
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    void shouldReturn400_WhenNameIsMissing() throws Exception {
        //Arrange
        String uri = "/access-methods";
        String invalidRequestBody = "{}";

        //Act
        MvcResult result = mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidRequestBody)).andReturn();

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
    }

    @Test
    void shouldReturn400_WhenNameHasLessThanThreeCharacters() throws Exception {
        //Arrange
        String uri = "/access-methods";
        String invalidRequestBody = "{\"name\": \"PP\"}";

        //Act
        MvcResult result = mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidRequestBody)).andReturn();

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
    }


    @Test
    void shouldReturn409_WhenAccessMethodAlreadyExists() throws Exception {
        //Arrange
        String uri = "/access-methods";
        String validName = "Name";

        AccessMethodID id = new AccessMethodID(UUID.randomUUID());
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars(validName);
        AccessMethod accessMethod = new AccessMethod(id, name);
        repository.save(accessMethod);

        String requestBody = "{\"name\": \"" + validName + "\"}";

        //Act
        MvcResult result = mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)).andReturn();

        //Assert
        assertEquals(HttpStatus.CONFLICT.value(), result.getResponse().getStatus());
    }

    @Test
    void shouldReturn404_WhenAccessMethodDoesNotExist() throws Exception {
        //Arrange
        String nonExistingId = UUID.randomUUID().toString();
        String uri = "/access-methods/" + nonExistingId;

        //Act
        MvcResult result = mockMvc.perform(get(uri)).andReturn();

        //Assert
        assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
    }
}


