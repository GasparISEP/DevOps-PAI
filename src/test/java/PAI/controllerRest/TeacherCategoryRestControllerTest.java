package PAI.controllerRest;

import PAI.VOs.Name;
import PAI.assembler.teacherCategory.ITeacherCategoryExternalAssembler;
import PAI.dto.teacherCategory.TeacherCategoryDTO;
import PAI.dto.teacherCategory.TeacherCategoryRequestDTO;
import PAI.dto.teacherCategory.TeacherCategoryResponseDTO;
import PAI.exception.AlreadyRegisteredException;
import PAI.service.teacherCategory.ITeacherCategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TeacherCategoryRestControllerTest {

    // testing constructor method

    @Test
    void shouldReturnAnExceptionIfTeacherCategoryServiceIsNull() {
        //arrange
        ITeacherCategoryExternalAssembler doubleTeacherCategoryAssemblerInterface = mock (ITeacherCategoryExternalAssembler.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TeacherCategoryRestController(null, doubleTeacherCategoryAssemblerInterface);
        });

        //assert
        assertEquals("Teacher Category Service Interface cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnAnExceptionIfTeacherCategoryAssemblerIsNull() {
        //arrange
        ITeacherCategoryService doubleTeacherCategoryServiceInterface = mock (ITeacherCategoryService.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TeacherCategoryRestController(doubleTeacherCategoryServiceInterface, null);
        });

        //assert
        assertEquals("Teacher Category Assembler Interface cannot be null.", exception.getMessage());
    }

    //testing configureTeacherCategory method

    @Test
    void shouldReturnAnCreateStatusCodeWhenIsPossibleToConfigureATeacherCategory() throws Exception {
        //arrange
        ITeacherCategoryExternalAssembler doubleTeacherCategoryAssemblerInterface = mock (ITeacherCategoryExternalAssembler.class);
        ITeacherCategoryService doubleTeacherCategoryServiceInterface = mock (ITeacherCategoryService.class);
        TeacherCategoryRestController controller = new TeacherCategoryRestController
                (doubleTeacherCategoryServiceInterface, doubleTeacherCategoryAssemblerInterface);

        TeacherCategoryRequestDTO doubleTeacherCategoryRequestDTO = mock (TeacherCategoryRequestDTO.class);
        TeacherCategoryResponseDTO doubleTeacherCategoryResponseDTO = mock(TeacherCategoryResponseDTO.class);
        TeacherCategoryDTO doubleTeacherCategoryDTO = mock (TeacherCategoryDTO.class);

        Name doubleName = mock (Name.class);
        when(doubleName.getName()).thenReturn("Assistant");

        when(doubleTeacherCategoryAssemblerInterface.toVO(doubleTeacherCategoryRequestDTO)).thenReturn(doubleName);
        when (doubleTeacherCategoryServiceInterface.configureTeacherCategory(doubleName)).thenReturn(doubleTeacherCategoryDTO);
        when(doubleTeacherCategoryAssemblerInterface.toResponseDTO(doubleTeacherCategoryDTO)).thenReturn(doubleTeacherCategoryResponseDTO);

        //act
        ResponseEntity<Object> result = controller.configureTeacherCategory (doubleTeacherCategoryRequestDTO);

        //assert
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

}