package PAI.assembler.teacherCareerProgression;

import PAI.VOs.*;
import PAI.assembler.teacherCategory.TeacherCategoryInternalAssemblerImpl;
import PAI.domain.teacher.Teacher;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import PAI.domain.teacherCategory.TeacherCategory;
import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryDTO;
import PAI.dto.teacherCategory.TeacherCategoryDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TeacherCareerProgressionInternalAssemblerImplTest {

    @Test
    void shouldReturnATeacherCategoryDTO() {
        //arrange
        Date doubleDate = mock(Date.class);
        when(doubleDate.getLocalDate()).thenReturn(LocalDate.of(2025,06,01));

        TeacherID doubleTeacherID = mock(TeacherID.class);
        TeacherAcronym doubleTeacherAcronym = mock(TeacherAcronym.class);
        when (doubleTeacherID.getTeacherAcronym()).thenReturn(doubleTeacherAcronym);
        when (doubleTeacherAcronym.getAcronym()).thenReturn("AAA");

        TeacherCategoryID doubleTeacherCategoryID = mock(TeacherCategoryID.class);
        when(doubleTeacherCategoryID.getValue()).thenReturn(UUID.randomUUID());

        WorkingPercentage doubleWorkingPercentage = mock(WorkingPercentage.class);
        when (doubleWorkingPercentage.getValue()).thenReturn(95);

        TeacherCareerProgression doubleTeacherCareerProgression = mock (TeacherCareerProgression.class);
        when (doubleTeacherCareerProgression.getDate()).thenReturn(doubleDate);
        when (doubleTeacherCareerProgression.getTeacherID()).thenReturn(doubleTeacherID);
        when (doubleTeacherCareerProgression.getTeacherCategoryID()).thenReturn(doubleTeacherCategoryID);
        when (doubleTeacherCareerProgression.getWorkingPercentage()).thenReturn(doubleWorkingPercentage);

        TeacherCareerProgressionInternalAssemblerImpl teacherCareerProgressionInternalAssembler =
                new TeacherCareerProgressionInternalAssemblerImpl();

        //act
        UpdateTeacherCategoryDTO result = teacherCareerProgressionInternalAssembler.toDTO(doubleTeacherCareerProgression);

        //assert
        assertNotNull(result);
    }

    @Test
    void shouldReturnAnExceptionWhenTeacherCareerProgressionIsNull() {
        // arrange
        TeacherCareerProgressionInternalAssemblerImpl teacherCareerProgressionInternalAssembler =
                new TeacherCareerProgressionInternalAssemblerImpl();

        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            teacherCareerProgressionInternalAssembler.toDTO(null);
        });

        // assert
        assertEquals("Teacher Career Progression cannot be null", exception.getMessage());
    }

    // testing toDTOList method

    @Test
    void shouldReturnAListOfUpdateTeacherCategoryDTO() {
        // arrange
        TeacherCareerProgressionInternalAssemblerImpl teacherCareerProgressionInternalAssembler =
                new TeacherCareerProgressionInternalAssemblerImpl();

        TeacherCareerProgression doubleTeacherCareerProgression1 = mock(TeacherCareerProgression.class);
        Date doubleDate = mock(Date.class);
        when (doubleDate.getLocalDate()).thenReturn(LocalDate.of(2025,06,01));
        when (doubleTeacherCareerProgression1.getDate()).thenReturn(doubleDate);
        TeacherID doubleTeacherID = mock(TeacherID.class);
        TeacherAcronym doubleTeacherAcronym = mock(TeacherAcronym.class);
        when (doubleTeacherAcronym.getAcronym()).thenReturn("AAA");
        when (doubleTeacherID.getTeacherAcronym()).thenReturn(doubleTeacherAcronym);
        when (doubleTeacherCareerProgression1.getTeacherID()).thenReturn(doubleTeacherID);
        TeacherCategoryID doubleTeacherCategoryID = mock(TeacherCategoryID.class);
        when(doubleTeacherCategoryID.getValue()).thenReturn(UUID.randomUUID());
        when (doubleTeacherCareerProgression1.getTeacherCategoryID()).thenReturn(doubleTeacherCategoryID);
        WorkingPercentage doubleWorkingPercentage = mock(WorkingPercentage.class);
        when (doubleWorkingPercentage.getValue()).thenReturn(95);
        when (doubleTeacherCareerProgression1.getWorkingPercentage()).thenReturn(doubleWorkingPercentage);

        TeacherCareerProgression doubleTeacherCareerProgression2 = mock(TeacherCareerProgression.class);
        Date doubleDate1 = mock(Date.class);
        when (doubleDate1.getLocalDate()).thenReturn(LocalDate.of(2025,05,01));
        when (doubleTeacherCareerProgression2.getDate()).thenReturn(doubleDate1);
        TeacherID doubleTeacherID1 = mock(TeacherID.class);
        TeacherAcronym doubleTeacherAcronym1 = mock(TeacherAcronym.class);
        when (doubleTeacherAcronym1.getAcronym()).thenReturn("ABA");
        when (doubleTeacherID1.getTeacherAcronym()).thenReturn(doubleTeacherAcronym1);
        when (doubleTeacherCareerProgression2.getTeacherID()).thenReturn(doubleTeacherID1);
        TeacherCategoryID doubleTeacherCategoryID1 = mock(TeacherCategoryID.class);
        when(doubleTeacherCategoryID1.getValue()).thenReturn(UUID.randomUUID());
        when (doubleTeacherCareerProgression2.getTeacherCategoryID()).thenReturn(doubleTeacherCategoryID1);
        WorkingPercentage doubleWorkingPercentage1 = mock(WorkingPercentage.class);
        when (doubleWorkingPercentage1.getValue()).thenReturn(98);
        when (doubleTeacherCareerProgression2.getWorkingPercentage()).thenReturn(doubleWorkingPercentage1);

        List<TeacherCareerProgression> listTCP = List.of(doubleTeacherCareerProgression1, doubleTeacherCareerProgression2);

        // act
        List<UpdateTeacherCategoryDTO> result =  teacherCareerProgressionInternalAssembler.toDTOList(listTCP);

        // assert
        assertEquals(2, result.size());
    }

    @Test
    void shouldReturnAnEmptyListOfUpdateTeacherCategoryDTO() {
        // arrange
        TeacherCareerProgressionInternalAssemblerImpl teacherCareerProgressionInternalAssembler =
                new TeacherCareerProgressionInternalAssemblerImpl();

        List<TeacherCareerProgression> listTCP = List.of();

        // act
        List<UpdateTeacherCategoryDTO> result =  teacherCareerProgressionInternalAssembler.toDTOList(listTCP);

        // assert
        assertEquals(0, result.size());
    }

    @Test
    void shouldReturnAnExceptionWhenAListOfTeacherCareerProgressionsIsNull() {
        // arrange
        TeacherCareerProgressionInternalAssemblerImpl teacherCareerProgressionInternalAssembler =
                new TeacherCareerProgressionInternalAssemblerImpl();

        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            teacherCareerProgressionInternalAssembler.toDTOList(null);
        });

        // assert
        assertEquals("Teacher Career Progression List cannot be null", exception.getMessage());
    }

}