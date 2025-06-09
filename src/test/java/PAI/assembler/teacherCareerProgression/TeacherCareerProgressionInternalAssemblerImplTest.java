package PAI.assembler.teacherCareerProgression;

import PAI.VOs.*;
import PAI.assembler.teacherCategory.TeacherCategoryInternalAssemblerImpl;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import PAI.domain.teacherCategory.TeacherCategory;
import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryDTO;
import PAI.dto.teacherCategory.TeacherCategoryDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
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

}