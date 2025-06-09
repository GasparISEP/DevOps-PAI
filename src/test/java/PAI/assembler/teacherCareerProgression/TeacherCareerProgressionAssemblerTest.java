package PAI.assembler.teacherCareerProgression;

import PAI.VOs.*;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import PAI.dto.teacherCareerProgression.*;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TeacherCareerProgressionAssemblerTest {

    // toUpdateCategory() method

    @Test
    void shouldReturnResponseDTO(){
        //arrange
        UpdateTeacherCategoryDTO doubleUpdateTeacherCategoryDTO = mock(UpdateTeacherCategoryDTO.class);

        when(doubleUpdateTeacherCategoryDTO.date()).thenReturn("2025-06-01");
        when(doubleUpdateTeacherCategoryDTO.teacherID()).thenReturn("AAA");
        when(doubleUpdateTeacherCategoryDTO.teacherCategoryID()).thenReturn
                ("05ab8bc8-33c2-46af-8988-d933e0256b89");
        when(doubleUpdateTeacherCategoryDTO.workingPercentage()).thenReturn(95);

        TeacherCareerProgressionAssembler tcpAssembler = new TeacherCareerProgressionAssembler();

        //act
        UpdateTeacherCategoryResponseDTO result = tcpAssembler.toUpdateTeacherCategoryResponseDTO(doubleUpdateTeacherCategoryDTO);

        //assert
        assertNotNull(result);

    }

    // toUpdateTeacherCategoryCommand() method

    @Test
    void shouldReturnCommand(){
        //arrange
        TeacherCareerProgressionAssembler assembler = new TeacherCareerProgressionAssembler();
        UpdateTeacherCategoryRequestDTO doubleRequestDTO = mock(UpdateTeacherCategoryRequestDTO.class);
        String teacherID = "ABC";
        LocalDate date = mock (LocalDate.class);

        when(doubleRequestDTO.date()).thenReturn(date);
        when(doubleRequestDTO.teacherCategoryID()).thenReturn("3f7bfe9a-d0e7-4b18-9b42-4b0a3f3e0c85");

        //act
        UpdateTeacherCategoryCommand result = assembler.toUpdateTeacherCategoryCommand(teacherID, doubleRequestDTO);

        //assert
        assertNotNull(result);
    }

    // toUpdateWorkingPercentageDTO() method

    @Test
    void shouldReturnWorkingPercentageResponseDTO(){
        //arrange
        Date dateVO = mock(Date.class);
        TeacherID teacherIDVO = mock(TeacherID.class);
        TeacherAcronym acronym = mock(TeacherAcronym.class);
        TeacherCategoryID categoryID = mock(TeacherCategoryID.class);
        WorkingPercentage wp = mock(WorkingPercentage.class);

        TeacherCareerProgressionAssembler assembler = new TeacherCareerProgressionAssembler();
        TeacherCareerProgression teacherCareerProgression = mock(TeacherCareerProgression.class);
        String date = "02-02-2022";
        String teacherID = "ABV";
        String teacherCategoryId = "3f7bfe9a-d0e7-4b18-9b42-4b0a3f3e0c85";
        int workingPercentage = 20;

        when(teacherCareerProgression.getDate()).thenReturn(dateVO);
        when(dateVO.toString()).thenReturn(date);

        when(teacherCareerProgression.getTeacherID()).thenReturn(teacherIDVO);
        when(teacherIDVO.getTeacherAcronym()).thenReturn(acronym);
        when(acronym.getAcronym()).thenReturn(teacherID);


        when(teacherCareerProgression.getTeacherCategoryID()).thenReturn(categoryID);
        when(categoryID.toString()).thenReturn(teacherCategoryId);

        when(teacherCareerProgression.getWorkingPercentage()).thenReturn(wp);
        when(wp.getValue()).thenReturn(workingPercentage);

        UpdateTeacherWorkingPercentageResponseDTO result = assembler.toUpdateWorkingPercentageDTO(teacherCareerProgression);

        //assert
        assertNotNull(result);

    }

    // toUpdateTeacherWorkingPercentageCommand() method

    @Test
    void shouldReturnWorkingPercentageCommand(){
        //arrange
        TeacherCareerProgressionAssembler assembler = new TeacherCareerProgressionAssembler();
        UpdateTeacherWorkingPercentageRequestDTO requestDTO = mock(UpdateTeacherWorkingPercentageRequestDTO.class);
        String teacherID = "ABV";

        when(requestDTO.date()).thenReturn("22-02-2022");
        when(requestDTO.workingPercentage()).thenReturn(70);

        //act
        UpdateTeacherWorkingPercentageCommand result = assembler.toUpdateTeacherWorkingPercentageCommand(teacherID, requestDTO);

        //assert
        assertNotNull(result);
    }
}
