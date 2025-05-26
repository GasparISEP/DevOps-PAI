package PAI.assembler.teacherCategory;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.VOs.TeacherID;
import PAI.controller.US16_EnrolAStudentInACourseEditionController;
import PAI.domain.teacherCategory.TeacherCategory;
import PAI.dto.teacherCategory.TeacherCategoryRequestDTO;
import PAI.dto.teacherCategory.TeacherCategoryResponseDTO;
import org.h2.command.dml.MergeUsing;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TeacherCategoryAssemblerImplTest {

    //testing method toDTO

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

    @Test
    void shouldReturnAnExceptionWhenTeacherCategoryIsNull() {
        // arrange
        TeacherCategoryAssemblerImpl teacherCategoryAssembler = new TeacherCategoryAssemblerImpl();

        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            teacherCategoryAssembler.toDTO(null);
        });

        // assert
        assertEquals("Teacher Category cannot be null", exception.getMessage());
    }

    //testing method toVO

    @Test
    void shouldReturnANameVO() {
        //arrange
        TeacherCategoryRequestDTO doublleTeacherCategoryRequestDTO = mock(TeacherCategoryRequestDTO.class);
        when (doublleTeacherCategoryRequestDTO.name()).thenReturn("Assistant Professor");

        TeacherCategoryAssemblerImpl teacherCategoryAssembler = new TeacherCategoryAssemblerImpl();

        //act
        Name result = teacherCategoryAssembler.toVO(doublleTeacherCategoryRequestDTO);

        //assert
        assertNotNull(result);
    }

    @Test
    void shouldReturnAnExceptionWhenTeacherCategoryRequestDTOIsNull() {
        // arrange
        TeacherCategoryAssemblerImpl teacherCategoryAssembler = new TeacherCategoryAssemblerImpl();

        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            teacherCategoryAssembler.toVO(null);
        });

        // assert
        assertEquals("Teacher Category Request DTO cannot be null", exception.getMessage());
    }

    @Test
    void shouldConvertListOfTeachersToDTOs(){
        //arrange
        TeacherCategoryAssemblerImpl assembler = new TeacherCategoryAssemblerImpl();

        TeacherCategory teacherCategory = mock(TeacherCategory.class);
        Name name = mock(Name.class);
        when(name.getName()).thenReturn("Assistant Professor");
        when(teacherCategory.getName()).thenReturn(name);
        TeacherCategoryID categoryID = mock(TeacherCategoryID.class);
        when(categoryID.getValue()).thenReturn(UUID.fromString("3f7bfe9a-d0e7-4b18-9b42-4b0a3f3e0c85"));
        when(teacherCategory.getId()).thenReturn(categoryID);
        Iterable<TeacherCategory> teacherCategories = List.of(teacherCategory);
        //act
        Iterable<TeacherCategoryResponseDTO> teacherCategoryDTOs = assembler.toDTOs(teacherCategories);
        //assert
        assertNotNull(teacherCategoryDTOs);
        assertTrue(teacherCategoryDTOs.iterator().hasNext());
    }
    @Test
    void shouldReturnAnEmptyListWhenTeacherCategoryIsNull(){
        TeacherCategoryAssemblerImpl assembler = new TeacherCategoryAssemblerImpl();
        //act
        Iterable<TeacherCategoryResponseDTO> teacherCategoryResponseDTOS = assembler.toDTOs(null);
        assertFalse(teacherCategoryResponseDTOS.iterator().hasNext());
    }
}