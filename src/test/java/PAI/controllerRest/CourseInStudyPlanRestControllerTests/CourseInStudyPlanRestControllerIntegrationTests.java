package PAI.controllerRest.CourseInStudyPlanRestControllerTests;

import PAI.VOs.*;
import PAI.assembler.courseInStudyPlan.ICourseInStudyPlanAssembler;
import PAI.assembler.courseInStudyPlan.ICourseInStudyPlanHateoasAssembler;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanRequestDTO;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanResponseDTO;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanServiceDTO;
import PAI.service.courseInStudyPlan.IAddCourseToAProgrammeService;
import PAI.service.courseInStudyPlan.ICourseInStudyPlanService;
import PAI.service.studyPlan.IStudyPlanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;

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
    private ICourseInStudyPlanHateoasAssembler hateoasAssembler;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() throws Exception {
        Mockito.when(hateoasAssembler.toModel(Mockito.any(CourseInStudyPlanResponseDTO.class)))
                .thenAnswer(invocation -> {
                    CourseInStudyPlanResponseDTO dto = invocation.getArgument(0);
                    return EntityModel.of(dto);
                });
        Mockito.when(hateoasAssembler.toCollectionModel(Mockito.anyList()))
                .thenAnswer(invocation -> {
                    var dtos = invocation.getArgument(0);
                    var entities = ((List<CourseInStudyPlanResponseDTO>) dtos).stream()
                            .map(EntityModel::of)
                            .collect(Collectors.toList());
                    return CollectionModel.of(entities);
                });
    }

    @Test
    void shouldReturn400_WhenRequestIsInvalid() throws Exception {
        String invalidBody = "{}";

        mockMvc.perform(post("/courses-in-study-plan")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn201_WhenRequestIsValid() throws Exception {
        CourseInStudyPlanRequestDTO request = new CourseInStudyPlanRequestDTO(1, 1, "MATH", "Mathematics", "LEI", "Licenciatura em Engenharia Informática", "2025-01-01" , 1, 6.0);

        CourseInStudyPlanServiceDTO serviceDTO = Mockito.mock(CourseInStudyPlanServiceDTO.class);
        CourseInStudyPlanResponseDTO responseDTO = new CourseInStudyPlanResponseDTO(
                1, 1, "MATH", "LEI", "Licenciatura em Engenharia Informática", "2025-01-01", 1, 6.0, java.util.UUID.randomUUID());

        Mockito.when(assembler.toCommand(request)).thenReturn(Mockito.mock(PAI.dto.courseInStudyPlan.CourseInStudyPlanCommand.class));
        Mockito.when(service.addCourseToAProgramme(Mockito.any())).thenReturn(serviceDTO);
        Mockito.when(assembler.toDTO(serviceDTO)).thenReturn(responseDTO);

        String jsonBody = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/courses-in-study-plan")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldReturn500_WhenServiceThrowsException() throws Exception {
        CourseInStudyPlanRequestDTO request = new CourseInStudyPlanRequestDTO(
                1, 1, "ACR", "Mathematics", "LEI", "Engenharia Informática", "2025-01-01", 1, 6.0);

        Mockito.when(assembler.toCommand(Mockito.any())).thenThrow(new RuntimeException("Unexpected"));

        String jsonBody = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/courses-in-study-plan")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isInternalServerError());
    }

}

