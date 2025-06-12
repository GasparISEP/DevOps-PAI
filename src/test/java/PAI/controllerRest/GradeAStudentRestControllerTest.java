package PAI.controllerRest;

import PAI.assembler.courseEdition.ICourseEditionAssembler;
import PAI.assembler.courseEdition.ICourseEditionHateoasAssembler;
import PAI.assembler.courseEdition.IStudentCountAssembler;
import PAI.assembler.courseEditionEnrolment.ICourseEditionEnrolmentAssembler;
import PAI.assembler.courseEditionEnrolment.ICourseEditionEnrolmentHateoasAssembler;
import PAI.assembler.programmeEdition.IProgrammeEditionServiceAssembler;
import PAI.assembler.studentGrade.IStudentGradeAssembler;
import PAI.assembler.studentGrade.StudentGradeRepresentationModelAssembler;
import PAI.dto.studentGrade.GradeAStudentRequestDTO;
import PAI.dto.studentGrade.GradeAStudentResponseDTO;
import PAI.service.courseEdition.ICourseEditionService;
import PAI.service.courseEdition.ICreateCourseEditionService;
import PAI.service.courseEdition.IDefineRucService;
import PAI.service.courseEditionEnrolment.ICourseEditionEnrolmentService;
import PAI.service.studentGrade.IGradeAStudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourseEditionRestController.class)
@AutoConfigureMockMvc
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class GradeAStudentRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean private ICourseEditionEnrolmentService courseEditionEnrolmentService;
    @MockBean private ICourseEditionEnrolmentAssembler courseEditionEnrolmentAssembler;
    @MockBean private ICreateCourseEditionService createCourseEditionService;
    @MockBean private ICourseEditionService courseEditionService;
    @MockBean private ICourseEditionAssembler courseEditionAssembler;
    @MockBean private IGradeAStudentService gradeAStudentService;
    @MockBean private IStudentGradeAssembler studentGradeAssembler;
    @MockBean private IProgrammeEditionServiceAssembler programmeEditionAssembler;
    @MockBean private IDefineRucService defineRucService;
    @MockBean private ICourseEditionHateoasAssembler courseEditionHateoasAssembler;
    @MockBean private IStudentCountAssembler studentCountAssembler;
    @MockBean private ICourseEditionEnrolmentHateoasAssembler courseEditionEnrolmentHateoasAssembler;
    @MockBean private StudentGradeRepresentationModelAssembler studentGradeRepresentationModelAssembler;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void gradeAStudent_shouldReturnCreatedResponse() throws Exception {
        // Arrange
        GradeAStudentRequestDTO requestDTO = new GradeAStudentRequestDTO(
                123456,
                18.5,
                "2025-06-09",
                "CS", "Computer Science",
                "f81d4fae-7dec-11d0-a765-00a0c91e6bf6",
                "DSOFT", "Desenvolvimento de Software",
                "15-08-2017"
        );

        GradeAStudentResponseDTO responseDTO = new GradeAStudentResponseDTO(
                123456,
                18.5,
                "2025-06-09",
                "ceid-1",
                "peid-1",
                "cspid-1",
                "pid-1",
                "syid-1",
                "cid-1",
                "spid-1"
        );

        when(studentGradeAssembler.toDomain(requestDTO)).thenReturn(null);
        when(gradeAStudentService.gradeAStudent(null)).thenReturn(responseDTO);
        when(studentGradeRepresentationModelAssembler.toModel(responseDTO))
                .thenReturn(EntityModel.of(responseDTO));

        // Act & Assert
        mockMvc.perform(post("/course-editions/studentgrades/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$._studentUniqueNumber").value(123456))
                .andExpect(jsonPath("$._grade").value(18.5))
                .andExpect(jsonPath("$._date").value("2025-06-09"));
    }

}
