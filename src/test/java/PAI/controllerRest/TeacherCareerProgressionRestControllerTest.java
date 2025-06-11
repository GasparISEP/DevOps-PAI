package PAI.controllerRest;

import PAI.VOs.TeacherCareerProgressionID;
import PAI.assembler.teacherCareerProgression.ITeacherCareerProgressionAssembler;
import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryDTO;
import PAI.exception.NotFoundException;
import PAI.service.teacherCareerProgression.ICreateTeacherCareerProgressionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TeacherCareerProgressionRestController.class)
class TeacherCareerProgressionRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICreateTeacherCareerProgressionService careerService;

    @MockBean
    private ITeacherCareerProgressionAssembler categoryAssembler;

    @Test
    void shouldReturnAnExceptionIfTeacherCareerProgressionServiceIsNull() {
        //arrange

        ITeacherCareerProgressionAssembler doubleTCPAssembler = mock(ITeacherCareerProgressionAssembler.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TeacherCareerProgressionRestController(null, doubleTCPAssembler);
        });

        //assert
        assertEquals("Teacher Career Progression Service Interface cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnAnExceptionIfTeacherCareerProgressionAssemblerIsNull() {
        //arrange
        ICreateTeacherCareerProgressionService doubleTCPService = mock(ICreateTeacherCareerProgressionService.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TeacherCareerProgressionRestController(doubleTCPService, null);
        });

        //assert
        assertEquals("Teacher Career Progression Assembler Interface cannot be null.", exception.getMessage());
    }

    // testing getAllTeacherCareerProgressions method

    @Test
    void shouldReturnAListOfUpdateTeacherCategoryResponseDTO () throws Exception {
        // arrange
        ICreateTeacherCareerProgressionService careerService = mock(ICreateTeacherCareerProgressionService.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);

        TeacherCareerProgressionRestController controller = new TeacherCareerProgressionRestController(careerService, careerAssembler);

        UpdateTeacherCategoryDTO doubleUpdateTeacherCategoryDTO1 = mock (UpdateTeacherCategoryDTO.class);
        UpdateTeacherCategoryDTO doubleUpdateTeacherCategoryDTO2 = mock (UpdateTeacherCategoryDTO.class);

        when(careerService.getAllTeacherCareerProgression()).
                thenReturn(List.of (doubleUpdateTeacherCategoryDTO1, doubleUpdateTeacherCategoryDTO2));

        // act
        ResponseEntity<Object> result =  controller.getAllTeacherCareerProgression();

        // assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void shouldReturnAnEmptyListOfUpdateTeacherCategoryResponseDTO () throws Exception {
        // arrange
        ICreateTeacherCareerProgressionService careerService = mock(ICreateTeacherCareerProgressionService.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);

        TeacherCareerProgressionRestController controller = new TeacherCareerProgressionRestController(careerService, careerAssembler);

        when(careerService.getAllTeacherCareerProgression()).
                thenReturn(List.of ());

        // act
        ResponseEntity<Object> result =  controller.getAllTeacherCareerProgression();

        // assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    // testing getTeacherCareerProgressionByID method

    @Test
    void shouldReturn404WhenTeacherCareerProgressionNotFound() throws Exception {
        // arrange
        UUID progressionId = UUID.randomUUID();
        String url = "/teacher-career-progressions/" + progressionId;

        when(careerService.getTeacherCareerProgressionByID(any()))
                .thenThrow(new NotFoundException("This teacher career progression id does not exist!"));

        // act & assert
        mockMvc.perform(get(url))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturn400WhenServiceThrowsIllegalArgumentException() throws Exception {
        // arrange
        UUID progressionId = UUID.randomUUID();
        String url = "/teacher-career-progressions/" + progressionId;

        when(careerService.getTeacherCareerProgressionByID(any()))
                .thenThrow(new IllegalArgumentException("Teacher Career Progression ID is required!"));

        // act & assert
        mockMvc.perform(get(url))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn200WhenIdWasFound () {
        // arrange
        ICreateTeacherCareerProgressionService careerService = mock(ICreateTeacherCareerProgressionService.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);

        TeacherCareerProgressionRestController controller = new TeacherCareerProgressionRestController(careerService, careerAssembler);

        String id = UUID.randomUUID().toString();
        TeacherCareerProgressionID doubleTeacherCareerProgressionID = mock (TeacherCareerProgressionID.class);
        UpdateTeacherCategoryDTO doubleUpdateTeacherCategoryDTO = mock (UpdateTeacherCategoryDTO.class);

        when(careerService.getTeacherCareerProgressionByID(doubleTeacherCareerProgressionID)).thenReturn(doubleUpdateTeacherCategoryDTO);

        // act
        ResponseEntity<Object> result =  controller.getTeacherCareerProgressionByID(id);

        // assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}