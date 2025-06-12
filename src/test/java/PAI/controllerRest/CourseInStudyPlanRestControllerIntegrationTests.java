package PAI.controllerRest;

import PAI.VOs.*;
import PAI.assembler.courseInStudyPlan.ICourseInStudyPlanAssembler;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanRequestDTO;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanResponseDTO;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanServiceDTO;
import PAI.service.courseInStudyPlan.IAddCourseToAProgrammeService;
import PAI.service.courseInStudyPlan.ICourseInStudyPlanService;
import PAI.service.studyPlan.IStudyPlanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
public class CourseInStudyPlanRestControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICourseInStudyPlanAssembler assembler;

    @MockBean
    private IAddCourseToAProgrammeService service;

    @MockBean
    private IStudyPlanService studyPlanService;

    @MockBean
    private ICourseInStudyPlanService courseInStudyPlanService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturn400_WhenRequestIsInvalid() throws Exception {
        // Envia JSON vazio que falha na validação
        String invalidBody = "{}";

        mockMvc.perform(post("/course-in-study-plan")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn201_WhenRequestIsValid() throws Exception {
        // arrange
        CourseInStudyPlanRequestDTO request = new CourseInStudyPlanRequestDTO(1, 1, "MATH", "Mathematics", "LEI", "Licenciatura em Engenharia Informática", "2025-01-01" , 1, 6.0);
        // configure com atributos válidos, exemplo fictício:
        request.courseAcronym();
        request.courseName();
        request.curricularYear();
        request.semester();
        request.credits();
        request.duration();
        request.programmeAcronym();
        request.programmeName();
        request.studyPlanDate();

        CourseInStudyPlanServiceDTO serviceDTO = Mockito.mock(CourseInStudyPlanServiceDTO.class);

        Acronym acronym = new Acronym("CS101");
        Name name = new Name("Computer Science");
        CourseID courseID = new CourseID(acronym, name);
        Acronym programmeAcronymWith = new Acronym("CS");
        ProgrammeID programmeID = new ProgrammeID(programmeAcronymWith);
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, Date.now());
        CourseInStudyPlanGeneratedID generatedID = CourseInStudyPlanGeneratedID.randomID();

        CourseInStudyPlanResponseDTO responseDTO = new CourseInStudyPlanResponseDTO(1, 1, "MATH",
                "LEI", "Licenciatura em Engenharia Informática", "2025-01-01", 1, 6.0, generatedID.getId());

        Mockito.when(assembler.toCommand(request)).thenReturn(Mockito.mock(PAI.dto.courseInStudyPlan.CourseInStudyPlanCommand.class));
        Mockito.when(service.addCourseToAProgramme(Mockito.any())).thenReturn(serviceDTO);
        Mockito.when(assembler.toDTO(serviceDTO)).thenReturn(responseDTO);

        // act
        String jsonBody = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/course-in-study-plan")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldReturn500_WhenServiceThrowsException() throws Exception {
        // arrange
        CourseInStudyPlanRequestDTO request = new CourseInStudyPlanRequestDTO(
                1, // semester
                1, // curricularYear
                "ACR", // courseAcronym
                "Mathematics", // courseName
                "LEI", // programmeAcronym
                "Engenharia Informática", // programmeName
                "2025-01-01", // studyPlanDate
                1, // duration
                6.0 // credits
        );

        Mockito.when(assembler.toCommand(Mockito.any())).thenThrow(new RuntimeException("Unexpected"));

        String jsonBody = objectMapper.writeValueAsString(request);

        // act + assert
        mockMvc.perform(post("/course-in-study-plan")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isInternalServerError());
    }


}
