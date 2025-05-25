package PAI.controllerRest;
import PAI.assembler.courseEdition.ICourseEditionAssembler;
import PAI.domain.courseEdition.CourseEdition;
import PAI.dto.RemoveCourseEditionEnrolmentDTO;
import PAI.dto.courseEdition.CourseEditionRequestDTO;
import PAI.dto.courseEdition.CourseEditionResponseDTO;
import PAI.dto.courseEdition.CreateCourseEditionCommand;
import PAI.service.courseEdition.ICreateCourseEditionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import PAI.assembler.courseEditionEnrolment.ICourseEditionEnrolmentAssembler;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.dto.courseEditionEnrolment.CourseEditionEnrolmentDto;
import PAI.service.courseEditionEnrolment.ICourseEditionEnrolmentService;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CourseEditionRestController.class)
class CourseEditionRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ICourseEditionEnrolmentService courseEditionEnrolmentService;

    @MockBean
    private ICourseEditionEnrolmentAssembler courseEditionEnrolmentAssembler;

    @MockBean
    private ICreateCourseEditionService createCourseEditionService;

    @MockBean
    private ICourseEditionAssembler courseEditionAssembler;

    private CourseEditionEnrolmentDto validEnrolmentDto;

    @BeforeEach
    void setUp() {
        validEnrolmentDto = new CourseEditionEnrolmentDto(
            1100000,
            "LEI",
            "LEIC",
            "123e4567-e89b-12d3-a456-426614174000",
            "ESOFT",
            "Engineering Software",
            "01-01-2024"
        );
    }

    @Test
    void whenEnrolStudent_thenReturnsCreated() throws Exception {
        // Arrange
        CourseEditionEnrolment mockEnrolment = mock(CourseEditionEnrolment.class);
        when(courseEditionEnrolmentAssembler.toDomain(any(CourseEditionEnrolmentDto.class)))
            .thenReturn(mockEnrolment);
        when(courseEditionEnrolmentService.enrolStudentInACourseEdition(any(), any()))
            .thenReturn(true);

        // Act & Assert
        mockMvc.perform(post("/courseeditions/students/enrolments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validEnrolmentDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void whenEnrolStudentAlreadyEnrolled_thenReturnsBadRequest() throws Exception {
        // Arrange
        CourseEditionEnrolment mockEnrolment = mock(CourseEditionEnrolment.class);
        when(courseEditionEnrolmentAssembler.toDomain(any(CourseEditionEnrolmentDto.class)))
            .thenReturn(mockEnrolment);
        when(courseEditionEnrolmentService.enrolStudentInACourseEdition(any(), any()))
            .thenReturn(false);

        // Act & Assert
        mockMvc.perform(post("/courseeditions/students/enrolments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validEnrolmentDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenEnrolStudentWithInvalidData_thenReturnsBadRequest() throws Exception {
        // Arrange
        CourseEditionEnrolmentDto invalidDto = new CourseEditionEnrolmentDto(
            0,
            "",
            "",
            "",
            "",
            "",
            "" 
        );

        // Act & Assert
        mockMvc.perform(post("/courseeditions/students/enrolments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenEnrolStudentWithException_thenReturnsBadRequest() throws Exception {
        // Arrange
        when(courseEditionEnrolmentAssembler.toDomain(any(CourseEditionEnrolmentDto.class)))
            .thenThrow(new RuntimeException("Test exception"));

        // Act & Assert
        mockMvc.perform(post("/courseeditions/students/enrolments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validEnrolmentDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenRemoveStudentEnrolmentFromACourseEdition_thenReturnsSuccess() throws Exception {
        //arrange
        //arrange
        //ProgrammeEditionID
        String programmeName = "programmeName";
        String programmeAcronym = "LEI";
        UUID schoolYearId = UUID.randomUUID();

        //CourseInStudyPlan
        String courseAcronym = "SAS";
        String courseName = "Desenvolvimento de Software";
        String studyPlanProgrammeName = "studyPlanProgrammeName";
        String studyPlanProgrammeAcronym  = "LEI1";
        String studyPlanImplementationDate = "01-10-2024";

        int studentID = 1241924;

        RemoveCourseEditionEnrolmentDTO removeCourseEditionEnrolmentDTO = new RemoveCourseEditionEnrolmentDTO(programmeName, programmeAcronym, schoolYearId, courseAcronym, courseName, studyPlanProgrammeName, studyPlanProgrammeAcronym, studyPlanImplementationDate, studentID);
        when(courseEditionEnrolmentService.removeCourseEditionEnrolment(any(), any())).thenReturn(true);
        //act + assert
        mockMvc.perform(patch("/courseeditions/enrolments/students/remove")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(removeCourseEditionEnrolmentDTO)))
                .andExpect(status().isAccepted());

    }

    @Test
    void whenCreateCourseEditionWithValidData_thenReturnsCreated() throws Exception {
        // Arrange
        CourseEditionRequestDTO requestDTO = new CourseEditionRequestDTO(
                "Software Development",
                "SDV",
                UUID.randomUUID(),
                "SA",
                "Software Architecture",
                LocalDate.of(2023, 9, 1));

        CreateCourseEditionCommand command = new CreateCourseEditionCommand(
                requestDTO.programmeName(),
                requestDTO.programmeAcronym(),
                requestDTO.schoolYearID(),
                requestDTO.courseAcronym(),
                requestDTO.courseName(),
                requestDTO.studyPlanImplementationDate());

        CourseEdition mockCourseEdition = mock(CourseEdition.class);

        CourseEditionResponseDTO responseDTO = new CourseEditionResponseDTO(
                "courseEditionID123",
                "Software Development",
                "SDV",
                UUID.randomUUID(),
                "SA",
                "Software Architecture",
                LocalDate.of(2023, 9, 1));

        when(courseEditionAssembler.toCommand(any(CourseEditionRequestDTO.class)))
                .thenReturn(command);

        when(createCourseEditionService.createAndSaveCourseEdition(any(), any()))
                .thenReturn(mockCourseEdition);

        when(courseEditionAssembler.toResponseDTO(mockCourseEdition))
                .thenReturn(responseDTO);

        // Act
        MvcResult result = mockMvc.perform(post("/courseeditions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/courseeditions/" + responseDTO.courseEditionID()))
                .andReturn();

        // Assert
        String jsonResponse = result.getResponse().getContentAsString();
        CourseEditionResponseDTO actualResponse = objectMapper.readValue(jsonResponse, CourseEditionResponseDTO.class);

        assertEquals(responseDTO.courseEditionID(), actualResponse.courseEditionID());
        assertEquals(responseDTO.programmeName(), actualResponse.programmeName());
        assertEquals(responseDTO.programmeAcronym(), actualResponse.programmeAcronym());
        assertEquals(responseDTO.schoolYearID(), actualResponse.schoolYearID());
        assertEquals(responseDTO.courseAcronym(), actualResponse.courseAcronym());
        assertEquals(responseDTO.courseName(), actualResponse.courseName());
        assertEquals(responseDTO.studyPlanImplementationDate(), actualResponse.studyPlanImplementationDate());
    }

    @Test
    void whenCreateCourseEditionReturnsNull_thenReturnsBadRequest() throws Exception {
        // Arrange
        CourseEditionRequestDTO requestDTO = new CourseEditionRequestDTO(
                "LEI",
                "LEIC",
                UUID.randomUUID(),
                "Software Architecture",
                "LEI",
                LocalDate.of(2023, 9, 1));

        CreateCourseEditionCommand command = new CreateCourseEditionCommand(
                requestDTO.programmeName(),
                requestDTO.programmeAcronym(),
                requestDTO.schoolYearID(),
                requestDTO.courseAcronym(),
                requestDTO.courseName(),
                requestDTO.studyPlanImplementationDate()
        );

        when(courseEditionAssembler.toCommand(any(CourseEditionRequestDTO.class)))
                .thenReturn(command);

        when(createCourseEditionService.createAndSaveCourseEdition(any(), any()))
                .thenReturn(null);

        // Act & Assert
        mockMvc.perform(post("/courseeditions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenCreateCourseEditionThrowsException_thenReturnsBadRequest() throws Exception {
        // Arrange
        CourseEditionRequestDTO requestDTO = new CourseEditionRequestDTO(
                "LEI",
                "LEIC",
                UUID.randomUUID(),
                "Software Architecture",
                "LEI",
                LocalDate.of(2023, 9, 1));

        when(courseEditionAssembler.toCommand(any(CourseEditionRequestDTO.class)))
                .thenThrow(new RuntimeException("Test Exception"));

        // Act & Assert
        mockMvc.perform(post("/courseeditions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }

}
