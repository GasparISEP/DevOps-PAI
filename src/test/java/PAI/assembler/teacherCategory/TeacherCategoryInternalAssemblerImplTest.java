package PAI.assembler.teacherCategory;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.domain.teacherCategory.TeacherCategory;
import PAI.dto.teacherCategory.TeacherCategoryDTO;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TeacherCategoryInternalAssemblerImplTest {

    //testing method toDTO

    @Test
    void shouldReturnATeacherCategoryDTO() {
        //arrange
        Name doubleName = mock(Name.class);
        when(doubleName.getName()).thenReturn("Assistant Professor");

        TeacherCategoryID doubleID = mock(TeacherCategoryID.class);
        when (doubleID.getValue()).thenReturn(UUID.randomUUID());

        TeacherCategory doubleTeacherCategory = mock (TeacherCategory.class);
        when (doubleTeacherCategory.getName()).thenReturn(doubleName);
        when (doubleTeacherCategory.identity()).thenReturn(doubleID);

        TeacherCategoryInternalAssemblerImpl teacherCategoryAssembler = new TeacherCategoryInternalAssemblerImpl();

        //act
        TeacherCategoryDTO result = teacherCategoryAssembler.toDTO(doubleTeacherCategory);

        //assert
        assertNotNull(result);
    }

    @Test
    void shouldReturnAnExceptionWhenTeacherCategoryIsNull() {
        // arrange
        TeacherCategoryInternalAssemblerImpl teacherCategoryAssembler = new TeacherCategoryInternalAssemblerImpl();

        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            teacherCategoryAssembler.toDTO(null);
        });

        // assert
        assertEquals("Teacher Category cannot be null", exception.getMessage());
    }


}