package PAI.assembler.teacher;

import PAI.dto.teacher.TeacherDTO;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class TeacherHateoasAssemblerImplTest {

    @Test
    void shouldCreateEntityModelWithTeacherDto(){
        //arrange
        TeacherDTO teacherDTO = mock(TeacherDTO.class);
        TeacherHateoasAssemblerImpl hateoasAssembler = new TeacherHateoasAssemblerImpl();
        //act
        EntityModel<TeacherDTO> result = hateoasAssembler.toModel(teacherDTO);
        //assert
        assertEquals(teacherDTO, result.getContent());
        assertTrue(result.hasLink("teachers"));
    }

    @Test
    void createdEntityModelShouldNotBeNull(){
        //arrange
        TeacherDTO teacherDTO = mock(TeacherDTO.class);
        TeacherHateoasAssemblerImpl hateoasAssembler = new TeacherHateoasAssemblerImpl();
        //act
        EntityModel<TeacherDTO> result = hateoasAssembler.toModel(teacherDTO);
        //assert
        assertNotNull(result.getContent());
    }

    @Test
    void nullTeacherDTOThrowsException(){
        //arrange
        TeacherDTO teacherDTO = null;
        TeacherHateoasAssemblerImpl hateoasAssembler = new TeacherHateoasAssemblerImpl();
        //act + assert
        assertThrows(IllegalArgumentException.class,() -> hateoasAssembler.toModel(teacherDTO));
    }

}