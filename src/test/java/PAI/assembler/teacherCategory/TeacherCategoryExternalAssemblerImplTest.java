package PAI.assembler.teacherCategory;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.dto.teacherCategory.TeacherCategoryDTO;
import PAI.dto.teacherCategory.TeacherCategoryRequestDTO;
import PAI.dto.teacherCategory.TeacherCategoryResponseDTO;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TeacherCategoryExternalAssemblerImplTest {

    //testing method toResponseDTO

    @Test
    void shouldReturnATeacherCategoryResponseDTO() {
        //arrange
        Name doubleName = mock(Name.class);
        when(doubleName.getName()).thenReturn("Assistant Professor");

        TeacherCategoryID doubleID = mock(TeacherCategoryID.class);
        when (doubleID.getValue()).thenReturn(UUID.randomUUID());

        TeacherCategoryDTO doubleTeacherCategoryDTO = mock (TeacherCategoryDTO.class);

        TeacherCategoryExternalAssemblerImpl teacherCategoryAssembler = new TeacherCategoryExternalAssemblerImpl();

        //act
        TeacherCategoryResponseDTO result = teacherCategoryAssembler.toResponseDTO(doubleTeacherCategoryDTO);

        //assert
        assertNotNull(result);
    }

    @Test
    void shouldReturnAnExceptionWhenTeacherCategoryIsNull() {
        // arrange
        TeacherCategoryExternalAssemblerImpl teacherCategoryAssembler = new TeacherCategoryExternalAssemblerImpl();

        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            teacherCategoryAssembler.toResponseDTO(null);
        });

        // assert
        assertEquals("Teacher Category DTO cannot be null.", exception.getMessage());
    }

    //testing method toVO

    @Test
    void shouldReturnANameVO() {
        //arrange
        TeacherCategoryRequestDTO doublleTeacherCategoryRequestDTO = mock(TeacherCategoryRequestDTO.class);
        when (doublleTeacherCategoryRequestDTO.name()).thenReturn("Assistant Professor");

        TeacherCategoryExternalAssemblerImpl teacherCategoryAssembler = new TeacherCategoryExternalAssemblerImpl();

        //act
        Name result = teacherCategoryAssembler.toVO(doublleTeacherCategoryRequestDTO);

        //assert
        assertNotNull(result);
    }

    @Test
    void shouldReturnAnExceptionWhenTeacherCategoryRequestDTOIsNull() {
        // arrange
        TeacherCategoryExternalAssemblerImpl teacherCategoryAssembler = new TeacherCategoryExternalAssemblerImpl();

        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            teacherCategoryAssembler.toVO(null);
        });

        // assert
        assertEquals("Teacher Category Request DTO cannot be null", exception.getMessage());
    }
//
//    @Test
//    void shouldConvertListOfTeachersToDTOs(){
//        //arrange
//        TeacherCategoryExternalAssemblerImpl assembler = new TeacherCategoryExternalAssemblerImpl();
//
//        TeacherCategory teacherCategory = mock(TeacherCategory.class);
//        Name name = mock(Name.class);
//        when(name.getName()).thenReturn("Assistant Professor");
//        when(teacherCategory.getName()).thenReturn(name);
//        TeacherCategoryID categoryID = mock(TeacherCategoryID.class);
//        when(categoryID.getValue()).thenReturn(UUID.fromString("3f7bfe9a-d0e7-4b18-9b42-4b0a3f3e0c85"));
//        when(teacherCategory.getId()).thenReturn(categoryID);
//        Iterable<TeacherCategory> teacherCategories = List.of(teacherCategory);
//        //act
//        Iterable<TeacherCategoryResponseDTO> teacherCategoryDTOs = assembler.toDTOs(teacherCategories);
//        //assert
//        assertNotNull(teacherCategoryDTOs);
//        assertTrue(teacherCategoryDTOs.iterator().hasNext());
//    }
    @Test
    void shouldReturnAnEmptyListWhenTeacherCategoryIsNull(){
        TeacherCategoryExternalAssemblerImpl assembler = new TeacherCategoryExternalAssemblerImpl();
        //act
        Iterable<TeacherCategoryResponseDTO> teacherCategoryResponseDTOS = assembler.toDTOs(null);
        assertFalse(teacherCategoryResponseDTOS.iterator().hasNext());
    }
}