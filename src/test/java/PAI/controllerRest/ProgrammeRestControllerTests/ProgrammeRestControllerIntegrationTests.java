package PAI.controllerRest.ProgrammeRestControllerTests;

import PAI.dto.Programme.ProgrammeIDResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProgrammeRestControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturn200WithListOfProgrammesIDs() throws Exception {
        // arrange
        String uri = "/programmes/ids";

        MvcResult result = mockMvc.perform(get(uri)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        // act
        int statusCode = result.getResponse().getStatus();
        String responseBody = result.getResponse().getContentAsString();
        ProgrammeIDResponseDTO[] responseList = new ObjectMapper().readValue(responseBody, ProgrammeIDResponseDTO[].class);
        // assert

        assertEquals(HttpStatus.OK.value(), statusCode);
        assertNotNull(responseList);
    }
}
