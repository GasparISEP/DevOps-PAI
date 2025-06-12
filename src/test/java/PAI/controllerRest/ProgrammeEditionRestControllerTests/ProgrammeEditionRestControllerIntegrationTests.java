package PAI.controllerRest.ProgrammeEditionRestControllerTests;

import PAI.VOs.*;
import PAI.domain.programme.Programme;
import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.programmeEdition.ProgrammeEditionRequestDTO;
import PAI.persistence.springdata.programme.ProgrammeRepositorySpringDataImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProgrammeEditionRestControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ProgrammeRepositorySpringDataImpl programmeRepository;

    private final String validProgrammeName = "Computer Science";
    private final String validAcronym = "CSE";

    @BeforeEach
    void setUp() {

        // Create Programme
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars(validProgrammeName);
        Acronym acronym = new Acronym(validAcronym);
        MaxEcts maxEcts = new MaxEcts(10);
        QuantSemesters quantSemesters = new QuantSemesters(1);
        DegreeTypeID degreeTypeID = new DegreeTypeID(UUID.randomUUID().toString());
        DepartmentID departmentID = new DepartmentID(new DepartmentAcronym("AAA"));
        TeacherID teacherID = new TeacherID(new TeacherAcronym("AAA"));
        ProgrammeID programmeID = new ProgrammeID(acronym);
        Programme programme = new Programme(name, acronym, maxEcts, quantSemesters, degreeTypeID, departmentID, teacherID, programmeID);
        programmeRepository.save(programme);
    }

    @Test
    void shouldReturn400WhenInvalidBody() throws Exception {
        // arrange
        String uri = "/programme-editions";
        String body = "{}";

        MvcResult result = mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)).andReturn();

        // act
        int statusCode = result.getResponse().getStatus();

        // assert
        assertEquals(HttpStatus.BAD_REQUEST.value(), statusCode);
    }

    @Test
    void shouldReturn400WhenProgrammeEditionRequestDTOIsNull() throws Exception {
        // arrange
        String uri = "/programme-editions";
        ProgrammeEditionRequestDTO requestBody = null;
        String body = new ObjectMapper().writeValueAsString(requestBody);

        MvcResult result = mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)).andReturn();

        // act
        int statusCode = result.getResponse().getStatus();

        // assert
        assertEquals(HttpStatus.BAD_REQUEST.value(), statusCode);
    }

    @Test
    void shouldReturn400WhenAcronymIsEmpty() throws Exception {
        // arrange
        String uri = "/programme-editions";
        ProgrammeIDDTO programme = new ProgrammeIDDTO("");
        ProgrammeEditionRequestDTO requestBody = new ProgrammeEditionRequestDTO(programme);
        String body = new ObjectMapper().writeValueAsString(requestBody);

        MvcResult result = mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)).andReturn();

        // act
        int statusCode = result.getResponse().getStatus();

        // assert
        assertEquals(HttpStatus.BAD_REQUEST.value(), statusCode);
    }

    @Test
    void shouldReturn400WhenAcronymIsNull() throws Exception {
        // arrange
        String uri = "/programme-editions";
        ProgrammeIDDTO programme = new ProgrammeIDDTO(null);
        ProgrammeEditionRequestDTO requestBody = new ProgrammeEditionRequestDTO(programme);
        String body = new ObjectMapper().writeValueAsString(requestBody);

        MvcResult result = mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)).andReturn();

        // act
        int statusCode = result.getResponse().getStatus();

        // assert
        assertEquals(HttpStatus.BAD_REQUEST.value(), statusCode);
    }

    @Test
    void shouldReturn400WhenAcronymIsShort() throws Exception {
        // arrange
        String uri = "/programme-editions";
        ProgrammeIDDTO programme = new ProgrammeIDDTO("CS");
        ProgrammeEditionRequestDTO requestBody = new ProgrammeEditionRequestDTO(programme);
        String body = new ObjectMapper().writeValueAsString(requestBody);

        MvcResult result = mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)).andReturn();

        // act
        int statusCode = result.getResponse().getStatus();

        // assert
        assertEquals(HttpStatus.BAD_REQUEST.value(), statusCode);
    }

    @Test
    void shouldReturn400WhenAcronymIsLong() throws Exception {
        // arrange
        String uri = "/programme-editions";
        ProgrammeIDDTO programme = new ProgrammeIDDTO( "CSEE");
        ProgrammeEditionRequestDTO requestBody = new ProgrammeEditionRequestDTO(programme);
        String body = new ObjectMapper().writeValueAsString(requestBody);

        MvcResult result = mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)).andReturn();

        // act
        int statusCode = result.getResponse().getStatus();

        // assert
        assertEquals(HttpStatus.BAD_REQUEST.value(), statusCode);
    }

    @Test
    void shouldReturn201WhenParametersAreValid() throws Exception {
        //arrange
        String uri = "/programme-editions";

        ProgrammeIDDTO programme = new ProgrammeIDDTO(validAcronym);
        ProgrammeEditionRequestDTO requestBody = new ProgrammeEditionRequestDTO(programme);

        String body = new ObjectMapper().writeValueAsString(requestBody);

        MvcResult result = mockMvc.perform(post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andReturn();

        //act
        int statusCode = result.getResponse().getStatus();

        //assert
        assertEquals(HttpStatus.CREATED.value(), statusCode);
    }

    @Test
    void shouldReturn400WhenAcronymNotInRepo() throws Exception {
        // arrange
        String uri = "/programme-editions";
        ProgrammeIDDTO programme = new ProgrammeIDDTO("BBB");
        ProgrammeEditionRequestDTO requestBody = new ProgrammeEditionRequestDTO(programme);
        String body = new ObjectMapper().writeValueAsString(requestBody);

        MvcResult result = mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)).andReturn();

        // act
        int statusCode = result.getResponse().getStatus();

        // assert
        assertEquals(HttpStatus.BAD_REQUEST.value(), statusCode);
    }

    @Test
    void getNumberOfStudents_returnsOkWithStudentCount() throws Exception {
        UUID schoolYearID = UUID.randomUUID();
        SchoolYearID validSchoolYearID = new SchoolYearID(schoolYearID);
        mockMvc.perform(get("/programme-editions/" + validAcronym + "/" + validSchoolYearID + "/students"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNumber());
    }

    @Test
    void getProgrammeEditions_returnsOkWithProgrammeEditions() throws Exception {
        mockMvc.perform(get("/programme-editions"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }
}
