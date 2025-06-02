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
    void shouldReturnCapabilitiesWithAllowHeaderForAdministratorRole() throws Exception {
        mockMvc.perform(options("/").param("role", "administrator"))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.ALLOW, containsString("GET")))
                .andExpect(header().string(HttpHeaders.ALLOW, containsString("OPTIONS")))
                .andExpect(jsonPath("$.teachers").exists())
                .andExpect(jsonPath("$.departments").exists())
                .andExpect(jsonPath("$.teachers.methods", hasItems("GET", "POST")));
    }

    @Test
    void shouldReturnCapabilitiesOnGetRequestForStudentRole() throws Exception {
        mockMvc.perform(get("/").param("role", "student"))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.ALLOW, containsString("GET")))
                .andExpect(jsonPath("$.teachers").exists())
                .andExpect(jsonPath("$.teachers.methods", hasItem("GET")))
                .andExpect(jsonPath("$.departments.methods", hasItem("GET")))
                .andExpect(jsonPath("$.teachers.methods", not(hasItem("POST")))); // student não tem POST
    }

    @Test
    void shouldReturnAnonymousCapabilitiesWhenRoleIsMissing() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.info", containsString("Role not recognized")));
    }

    @Test
    void shouldReturnEmptyCapabilitiesForUnknownRole() throws Exception {
        mockMvc.perform(get("/").param("role", "ghost"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.info", containsString("Role not recognized")));
    }

    @Test
    void shouldReturnFullCatalogOnCatalogEndpoint() throws Exception {
        mockMvc.perform(get("/catalog"))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.ALLOW, containsString("GET")))
                .andExpect(jsonPath("$.teachers").exists())
                .andExpect(jsonPath("$.departments").exists())
                .andExpect(jsonPath("$.students").exists());
    }
}

