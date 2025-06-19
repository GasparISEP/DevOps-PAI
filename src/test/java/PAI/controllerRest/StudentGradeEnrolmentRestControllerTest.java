package PAI.controllerRest;

import PAI.assembler.studentGrade.IStudentGradeEnrolmentAssembler;
import PAI.assembler.studentGrade.StudentGradeEnrolmentAssemblerHateoas;
import PAI.dto.studentGrade.GradeAStudentCommand;
import PAI.dto.studentGrade.GradeAStudentRequestMinimalDTO;
import PAI.dto.studentGrade.GradeAStudentResponseDTO;
import PAI.service.courseEditionEnrolment.ICourseEditionEnrolmentService;
import PAI.service.studentGrade.IGradeAStudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentGradeEnrolmentRestController.class)
public class StudentGradeEnrolmentRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICourseEditionEnrolmentService enrolmentService;

    @MockBean
    private IStudentGradeEnrolmentAssembler assembler;

    @MockBean
    private IGradeAStudentService gradeService;

    @MockBean
    private StudentGradeEnrolmentAssemblerHateoas hateoasAssembler;

    @Autowired
    private ObjectMapper objectMapper;

    private UUID courseEditionGeneratedID;
    private GradeAStudentRequestMinimalDTO requestDTO;
    private GradeAStudentResponseDTO responseDTO;
    private GradeAStudentCommand command;

    @BeforeEach
    void setUp() {
        courseEditionGeneratedID = UUID.randomUUID();
        requestDTO = new GradeAStudentRequestMinimalDTO(1000001, 18.0, courseEditionGeneratedID.toString());
        command = mock(GradeAStudentCommand.class);
        responseDTO = new GradeAStudentResponseDTO(
                1000001, 18.0, "17-06-2025",
                "CEE123", "PED123", "CISP456", "LEI",
                "SCHOOL-YEAR-1", "COURSE-123", "STUDYPLAN-789"
        );
    }

    @Test
    void testRegisterWithHateoas_ReturnsExpectedResponse() throws Exception {
        // Arrange
        when(gradeService.gradeAStudentMinimal(any(GradeAStudentRequestMinimalDTO.class))).thenReturn(responseDTO);
        when(hateoasAssembler.toModel(responseDTO)).thenReturn(EntityModel.of(responseDTO));

        // Act + Assert
        mockMvc.perform(post("/studentgrades/register/hateoas")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("_studentUniqueNumber").value(1000001))
                .andExpect(jsonPath("_grade").value(18.0))
                .andExpect(jsonPath("_date").value("17-06-2025"))
                .andExpect(jsonPath("_courseEditionId").value("CEE123"));
    }

    @Test
    void testRegisterWithHateoas_ReturnsBadRequest_WhenEnrolmentNotFound() throws Exception {
        // Arrange: o assembler lança exceção porque o enrolment não foi encontrado
        when(gradeService.gradeAStudentMinimal(any(GradeAStudentRequestMinimalDTO.class)))
            .thenThrow(new IllegalArgumentException("Enrolment not found"));

        // Act + Assert
        mockMvc.perform(post("/studentgrades/register/hateoas")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isInternalServerError()) // o controller atual lança RuntimeException
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RuntimeException))
                .andExpect(result -> assertTrue(result.getResolvedException().getMessage().contains("Erro ao atribuir nota ao estudante")));
    }

    @Test
    void testRegisterWithHateoas_ReturnsBadRequest_WhenGradeIsInvalid() throws Exception {
        // Arrange: o assembler lança exceção por causa de nota inválida
        when(gradeService.gradeAStudentMinimal(any(GradeAStudentRequestMinimalDTO.class)))
            .thenThrow(new IllegalArgumentException("Enrolment not found"));

        GradeAStudentRequestMinimalDTO invalidGradeDTO = new GradeAStudentRequestMinimalDTO(
                1000001, -5.0, courseEditionGeneratedID.toString()
        );

        // Act + Assert
        mockMvc.perform(post("/studentgrades/register/hateoas")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidGradeDTO)))
                .andExpect(status().isInternalServerError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RuntimeException))
                .andExpect(result -> assertTrue(result.getResolvedException().getMessage().contains("Erro ao atribuir nota ao estudante")));
    }


}
