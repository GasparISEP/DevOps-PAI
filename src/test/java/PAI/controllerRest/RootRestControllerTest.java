package PAI.controllerRest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RootRestController.class)
public class RootRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnCapabilitiesWithAllowHeader() throws Exception {
        //Act & Assert
        mockMvc.perform(options("/"))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.ALLOW, containsString("GET")))
                .andExpect(header().string(HttpHeaders.ALLOW, containsString("OPTIONS")))
                .andExpect(jsonPath("$.teachers").exists())
                .andExpect(jsonPath("$.departments").exists())
                .andExpect(jsonPath("$.teachers.url", startsWith("http")))
                .andExpect(jsonPath("$.teachers.roles.administrator", hasItem("GET")));
    }

    @Test
    void shouldReturnCapabilitiesOnGetRequest() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.ALLOW, containsString("GET")))
                .andExpect(jsonPath("$.teachers").exists())
                .andExpect(jsonPath("$.departments").exists());
    }
}
