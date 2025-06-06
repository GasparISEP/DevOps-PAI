package PAI.controllerRest.SchoolYearRestControllerTests;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class SchoolYearRestControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturn200WithCurrentSchoolYear() throws Exception {
        // arrange
        String uri = "/school-years/current";

        MvcResult result = mockMvc.perform(get(uri)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        // act
        int statusCode = result.getResponse().getStatus();
        String responseBody = result.getResponse().getContentAsString();
        // assert
        assertEquals(HttpStatus.OK.value(), statusCode);
        assertNotNull(responseBody);
        assertTrue(responseBody.contains("id"));
        assertTrue(responseBody.contains("description"));
        assertTrue(responseBody.contains("startDate"));
        assertTrue(responseBody.contains("endDate"));
    }

}
