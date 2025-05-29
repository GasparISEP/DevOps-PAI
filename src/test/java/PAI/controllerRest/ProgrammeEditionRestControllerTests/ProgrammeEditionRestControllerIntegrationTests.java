package PAI.controllerRest.ProgrammeEditionRestControllerTests;

import PAI.VOs.*;
import PAI.domain.programme.Programme;
import PAI.domain.schoolYear.SchoolYear;
import PAI.dto.Programme.ProgrammeIDRequestDTO;
import PAI.dto.programmeEdition.ProgrammeEditionRequestDTO;
import PAI.dto.schoolYear.SchoolYearIDRequestDTO;
import PAI.persistence.springdata.programme.ProgrammeRepositorySpringDataImpl;
import PAI.persistence.springdata.schoolYear.SchoolYearRepositorySpringDataImpl;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProgrammeEditionRestControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SchoolYearRepositorySpringDataImpl schoolYearRepository;

    @Autowired
    ProgrammeRepositorySpringDataImpl programmeRepository;

    private final String validSchoolYearId = "c46eb9c9-2b4f-4c6b-bf3c-4704589bbcec";
    private final String validProgrammeName = "Computer Science";
    private final String validAcronym = "CSE";

    @BeforeEach
    void setUp() {
        // Create SchoolYear
        Description description = new Description("2024/2025");
        Date startDate = new Date("01-06-2024");
        Date endDate = new Date("31-06-2025");
        SchoolYear schoolYear = new SchoolYear(UUID.fromString(validSchoolYearId), description, startDate, endDate);
        schoolYearRepository.save(schoolYear);

        // Create Programme
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars(validProgrammeName);
        Acronym acronym = new Acronym(validAcronym);
        MaxEcts maxEcts = new MaxEcts(10);
        QuantSemesters quantSemesters = new QuantSemesters(1);
        DegreeTypeID degreeTypeID = new DegreeTypeID(UUID.randomUUID().toString());
        DepartmentID departmentID = new DepartmentID(new DepartmentAcronym("AAA"));
        TeacherID teacherID = new TeacherID(new TeacherAcronym("AAA"));
        ProgrammeID programmeID = new ProgrammeID(name, acronym);
        Programme programme = new Programme(name, acronym, maxEcts, quantSemesters, degreeTypeID, departmentID, teacherID, programmeID);
        programmeRepository.save(programme);
    }

    @Test
    void shouldReturn400WhenInvalidBody() throws Exception {
        // arrange
        String uri = "/programmeeditions";
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
    void shouldReturn400WhenProgrammeNameIsEmpty() throws Exception {
        // arrange
        String uri = "/programmeeditions";
        ProgrammeIDRequestDTO programme = new ProgrammeIDRequestDTO("", validAcronym);
        SchoolYearIDRequestDTO schoolYear = new SchoolYearIDRequestDTO(validSchoolYearId);
        ProgrammeEditionRequestDTO requestBody = new ProgrammeEditionRequestDTO(programme, schoolYear);
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
    void shouldReturn400WhenProgrammeNameIsMissing() throws Exception {
        // arrange
        String uri = "/programmeeditions";
        ProgrammeIDRequestDTO programme = null;
        SchoolYearIDRequestDTO schoolYear = new SchoolYearIDRequestDTO(validSchoolYearId);
        ProgrammeEditionRequestDTO requestBody = new ProgrammeEditionRequestDTO(programme, schoolYear);
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
    void shouldReturn400WhenProgrammeNameIsNull() throws Exception {
        // arrange
        String uri = "/programmeeditions";
        ProgrammeIDRequestDTO programme = new ProgrammeIDRequestDTO(null, validAcronym);
        SchoolYearIDRequestDTO schoolYear = new SchoolYearIDRequestDTO(validSchoolYearId);
        ProgrammeEditionRequestDTO requestBody = new ProgrammeEditionRequestDTO(programme, schoolYear);
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
    void shouldReturn400WhenAcronymNameIsEmpty() throws Exception {
        // arrange
        String uri = "/programmeeditions";
        ProgrammeIDRequestDTO programme = new ProgrammeIDRequestDTO(validProgrammeName, "");
        SchoolYearIDRequestDTO schoolYear = new SchoolYearIDRequestDTO(validSchoolYearId);
        ProgrammeEditionRequestDTO requestBody = new ProgrammeEditionRequestDTO(programme, schoolYear);
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
        String uri = "/programmeeditions";
        ProgrammeIDRequestDTO programme = new ProgrammeIDRequestDTO(validProgrammeName, null);
        SchoolYearIDRequestDTO schoolYear = new SchoolYearIDRequestDTO(validSchoolYearId);
        ProgrammeEditionRequestDTO requestBody = new ProgrammeEditionRequestDTO(programme, schoolYear);
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
        String uri = "/programmeeditions";
        ProgrammeIDRequestDTO programme = new ProgrammeIDRequestDTO(validProgrammeName, "CS");
        SchoolYearIDRequestDTO schoolYear = new SchoolYearIDRequestDTO(validSchoolYearId);
        ProgrammeEditionRequestDTO requestBody = new ProgrammeEditionRequestDTO(programme, schoolYear);
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
        String uri = "/programmeeditions";
        ProgrammeIDRequestDTO programme = new ProgrammeIDRequestDTO(validProgrammeName, "CSEE");
        SchoolYearIDRequestDTO schoolYear = new SchoolYearIDRequestDTO(validSchoolYearId);
        ProgrammeEditionRequestDTO requestBody = new ProgrammeEditionRequestDTO(programme, schoolYear);
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
    void shouldReturn400WhenSchoolYearIsEmpty() throws Exception {
        // arrange
        String uri = "/programmeeditions";
        ProgrammeIDRequestDTO programme = new ProgrammeIDRequestDTO(validProgrammeName, validAcronym);
        SchoolYearIDRequestDTO schoolYear = new SchoolYearIDRequestDTO("");
        ProgrammeEditionRequestDTO requestBody = new ProgrammeEditionRequestDTO(programme, schoolYear);
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
    void shouldReturn400WhenSchoolYearIsNull() throws Exception {
        // arrange
        String uri = "/programmeeditions";
        ProgrammeIDRequestDTO programme = new ProgrammeIDRequestDTO(validProgrammeName, validAcronym);
        SchoolYearIDRequestDTO schoolYear = new SchoolYearIDRequestDTO(null);
        ProgrammeEditionRequestDTO requestBody = new ProgrammeEditionRequestDTO(programme, schoolYear);
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
    void shouldReturn400WhenSchoolYearIsMissing() throws Exception {
        // arrange
        String uri = "/programmeeditions";
        ProgrammeIDRequestDTO programme = new ProgrammeIDRequestDTO(validProgrammeName, validAcronym);
        SchoolYearIDRequestDTO schoolYear = null;
        ProgrammeEditionRequestDTO requestBody = new ProgrammeEditionRequestDTO(programme, schoolYear);
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
    void shouldReturn400WhenSchoolYearIsInvalidUUID() throws Exception {
        // arrange
        String uri = "/programmeeditions";
        ProgrammeIDRequestDTO programme = new ProgrammeIDRequestDTO(validProgrammeName, validAcronym);
        SchoolYearIDRequestDTO schoolYear = new SchoolYearIDRequestDTO("I-AM-UUID");
        ProgrammeEditionRequestDTO requestBody = new ProgrammeEditionRequestDTO(programme, schoolYear);
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
        // arrange
        String uri = "/programmeeditions";
        ProgrammeIDRequestDTO programme = new ProgrammeIDRequestDTO(validProgrammeName, validAcronym);
        SchoolYearIDRequestDTO schoolYear = new SchoolYearIDRequestDTO(validSchoolYearId);
        ProgrammeEditionRequestDTO requestBody = new ProgrammeEditionRequestDTO(programme, schoolYear);
        String body = new ObjectMapper().writeValueAsString(requestBody);

        MvcResult result = mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)).andReturn();

        // act
        int statusCode = result.getResponse().getStatus();

        // assert
        assertEquals(HttpStatus.CREATED.value(), statusCode);
        assertTrue(result.getResponse().getContentAsString().contains(validSchoolYearId));
        assertTrue(result.getResponse().getContentAsString().contains(validProgrammeName));
        assertTrue(result.getResponse().getContentAsString().contains(validAcronym));
    }

    @Test
    void shouldReturn400WhenSchoolYearNotInRepo() throws Exception {
        // arrange
        String uri = "/programmeeditions";
        ProgrammeIDRequestDTO programme = new ProgrammeIDRequestDTO(validProgrammeName, validAcronym);
        SchoolYearIDRequestDTO schoolYear = new SchoolYearIDRequestDTO("c46eb9c9-2b4f-4c6b-bf3c-4704589bbbbb");
        ProgrammeEditionRequestDTO requestBody = new ProgrammeEditionRequestDTO(programme, schoolYear);
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
    void shouldReturn400WhenProgrammeNameNotInRepo() throws Exception {
        // arrange
        String uri = "/programmeeditions";
        ProgrammeIDRequestDTO programme = new ProgrammeIDRequestDTO("programme", validAcronym);
        SchoolYearIDRequestDTO schoolYear = new SchoolYearIDRequestDTO(validSchoolYearId);
        ProgrammeEditionRequestDTO requestBody = new ProgrammeEditionRequestDTO(programme, schoolYear);
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
    void shouldReturn400WhenAcronymNotInRepo() throws Exception {
        // arrange
        String uri = "/programmeeditions";
        ProgrammeIDRequestDTO programme = new ProgrammeIDRequestDTO(validProgrammeName, "BBB");
        SchoolYearIDRequestDTO schoolYear = new SchoolYearIDRequestDTO(validSchoolYearId);
        ProgrammeEditionRequestDTO requestBody = new ProgrammeEditionRequestDTO(programme, schoolYear);
        String body = new ObjectMapper().writeValueAsString(requestBody);

        MvcResult result = mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)).andReturn();

        // act
        int statusCode = result.getResponse().getStatus();

        // assert
        assertEquals(HttpStatus.BAD_REQUEST.value(), statusCode);
    }
}
