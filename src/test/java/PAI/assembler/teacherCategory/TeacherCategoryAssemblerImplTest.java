package PAI.assembler.teacherCategory;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.domain.teacherCategory.TeacherCategory;
import PAI.dto.teacherCategory.TeacherCategoryResponseDTO;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TeacherCategoryAssemblerImplTest {

    @Test
    void shouldReturnATeacherCategoryResponseDTO() {
        //arrange
        Name doubleName = mock(Name.class);
        when(doubleName.getName()).thenReturn("Assistant Professor");

        TeacherCategoryID doubleID = mock(TeacherCategoryID.class);
        when (doubleID.getValue()).thenReturn(UUID.randomUUID());

        TeacherCategory doubleTeacherCategory = mock (TeacherCategory.class);
        when(doubleTeacherCategory.getName()).thenReturn(doubleName);
        when(doubleTeacherCategory.getId()).thenReturn(doubleID);

        TeacherCategoryAssemblerImpl teacherCategoryAssembler = new TeacherCategoryAssemblerImpl();

        //act
        TeacherCategoryResponseDTO result = teacherCategoryAssembler.toDTO(doubleTeacherCategory);

        //assert
        assertNotNull(result);
    }
}