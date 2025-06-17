package PAI.controllerRest.programmeEnrolmentRestControllerTests;

import PAI.assembler.programmeEdition.IProgrammeEditionControllerAssembler;
import PAI.assembler.programmeEnrolment.IProgrammeEnrolmentAssembler;
import PAI.assembler.totalEnrolledStudentsInProgrammesByDepartmentAndSchoolYear.ITotalEnrolledStudentsAssembler;
import PAI.dto.totalEnrolledStudents.TotalEnrolledStudentsCommand;
import PAI.service.programmeEditionEnrolment.IStudentProgrammeEditionEnrolmentService;
import PAI.service.totalEnrolledStudentsInProgrammesByDepartmentAndSchoolYear.ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProgrammeEnrolmentRestControllerIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ITotalEnrolledStudentsAssembler totalEnrolledStudentsAssembler;

    @MockBean
    private ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService totalEnrolledStudentsService;

    @MockBean
    private IProgrammeEnrolmentAssembler programmeEnrolmentAssembler;

    @MockBean
    private IStudentProgrammeEditionEnrolmentService iStudentProgrammeEnrolmentService;

    @MockBean
    private IProgrammeEditionControllerAssembler iProgrammeEditionControllerAssembler;

    @Test
    void shouldReturn200AndTotalEnrolledStudentsCount() throws Exception {
        String departmentId = "AAA";
        String schoolYearId = "d19b1a3f-9b68-41c4-9f35-b061e4799d9d";

        TotalEnrolledStudentsCommand command = mock(TotalEnrolledStudentsCommand.class);

        when(totalEnrolledStudentsAssembler.fromRequestToCommand(departmentId, schoolYearId)).thenReturn(command);
        when(totalEnrolledStudentsService.getTotalEnrolledStudentsInProgrammesByDepartmentAndYear(command)).thenReturn(150);

        mockMvc.perform(get("/programme-enrolments/departments/{departmentId}/schoolYears/{schoolYearId}/programme-enrolments/count", departmentId, schoolYearId))
                .andExpect(status().isOk())
                .andExpect(content().string("150"));
    }

    @Test
    void shouldReturn400WhenAssemblerThrowsIllegalArgumentException() throws Exception {
        String departmentId = "INVALID";
        String schoolYearId = "valid-year-id";

        when(totalEnrolledStudentsAssembler.fromRequestToCommand(departmentId, schoolYearId))
                .thenThrow(new IllegalArgumentException("Invalid department ID"));

        mockMvc.perform(get("/programme-enrolments/departments/{departmentId}/schoolYears/{schoolYearId}/programme-enrolments/count", departmentId, schoolYearId))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid department ID"));
    }

    @Test
    void shouldReturn500WhenUnexpectedErrorOccurs() throws Exception {
        String departmentId = "AAA";
        String schoolYearId = "valid-year-id";

        when(totalEnrolledStudentsAssembler.fromRequestToCommand(departmentId, schoolYearId))
                .thenThrow(new RuntimeException("Unexpected error occurred"));

        mockMvc.perform(get("/programme-enrolments/departments/{departmentId}/schoolYears/{schoolYearId}/programme-enrolments/count", departmentId, schoolYearId))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Unexpected error occurred"));
    }
}
