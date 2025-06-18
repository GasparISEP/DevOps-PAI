package PAI.controllerRest.courseEditionRestControllerTests;

import PAI.assembler.courseEdition.ICourseEditionAssembler;
import PAI.assembler.courseEdition.ICourseEditionRUCHateoasAssembler;
import PAI.assembler.courseEditionEnrolment.ICourseEditionEnrolmentAssembler;
import PAI.assembler.courseEditionEnrolment.ICourseEditionEnrolmentHateoasAssembler;
import PAI.controller.US16_EnrolAStudentInACourseEditionController;
import PAI.dto.courseEdition.CourseEditionRequestDTO;
import PAI.dto.courseEditionEnrolment.CourseEditionEnrolmentDto;
import PAI.initializer.CourseEditionEnrolmentInitializer;
import PAI.service.courseEditionEnrolment.ICourseEditionEnrolmentService;
import PAI.service.courseEdition.ICourseEditionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.test.context.ActiveProfiles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class CourseEditionRestControllerRealIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ICourseEditionAssembler courseEditionAssembler;

    @Autowired
    private ICourseEditionService courseEditionService;

    @Autowired
    private ICourseEditionRUCHateoasAssembler courseEditionRUCHateoasAssembler;

    @Autowired
    private ICourseEditionEnrolmentService courseEditionEnrolmentService;

    @Autowired
    private ICourseEditionEnrolmentAssembler courseEditionEnrolmentAssembler;

    @Autowired
    private ICourseEditionEnrolmentHateoasAssembler courseEditionEnrolmentHateoasAssembler;

    @Autowired
    private CourseEditionEnrolmentInitializer initializer;
    @Autowired
    private US16_EnrolAStudentInACourseEditionController controller;

    @BeforeEach
    void setUp() {
        initializer.loadCourseEditionEnrolments(controller, "src/test/resources/CourseEditionEnrolmentTest.csv");
    }
    @Test
    void enrolStudentInCourseEdition_thenReturnsCreatedWhenStudentNotEnrolled() throws Exception {
        // Arrange
        int studentId = 1001000;

        CourseEditionEnrolmentDto requestDto = new CourseEditionEnrolmentDto(
                studentId,
                "CSD",
                "550e8400-e29b-41d4-a716-446655440009",
                "ARIT",
                "Arithmancy",
                "15-10-2023"
        );

        String jsonBody = objectMapper.writeValueAsString(requestDto);

        // Act & Assert
        mockMvc.perform(post("/course-editions/students/{id}/courses-edition-enrolments", studentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andExpect(status().isCreated());
    }

    @Test
    void enrolStudentInCourseEdition_thenReturnsBadRequestWhenStudentAlreadyEnrolled() throws Exception {
        // Arrange
        int studentId = 1000001;

        CourseEditionEnrolmentDto requestDto = new CourseEditionEnrolmentDto(
                studentId,
                "CSD",
                "550e8400-e29b-41d4-a716-446655440009",
                "ARIT",
                "Arithmancy",
                "01-09-2023"
        );

        String jsonBody = objectMapper.writeValueAsString(requestDto);

        // Act & Assert
        mockMvc.perform(post("/course-editions/students/{id}/courses-edition-enrolments", studentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getCourseEditionsByProgrammeEditionID_shouldReturnValidResponse() throws Exception {
        // Arrange
        CourseEditionRequestDTO requestDTO = new CourseEditionRequestDTO(
                "CSD",
                UUID.fromString("550e8400-e29b-41d4-a716-446655440001"),
                "ARIT",
                "Arithmancy",
                LocalDate.parse("2015-09-01")
        );

        // Act & Assert
        mockMvc.perform(get("/course-editions/programmeditions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"));
    }
} 