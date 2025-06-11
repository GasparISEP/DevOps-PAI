package PAI.assembler.teacher;

import PAI.dto.teacher.TeacherDTO;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import java.util.Collections;

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
        assertTrue(result.hasLink("self"));
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

    @Test
    void shouldCreateCollectionModelWithTeacherDto(){
        //arrange
        TeacherDTO teacherDTO = mock(TeacherDTO.class);
        Iterable<TeacherDTO> teacherDTOS = Collections.singleton(teacherDTO);
        TeacherHateoasAssemblerImpl hateoasAssembler = new TeacherHateoasAssemblerImpl();
        //act
        CollectionModel<EntityModel<TeacherDTO>> result = hateoasAssembler.toCollectionModel(teacherDTOS);
        //assert
        assertEquals(1, result.getContent().size());
        assertTrue(result.hasLink("teachers"));
        assertTrue(result.getContent().stream().anyMatch(em -> em.getContent() == teacherDTO));
    }

    @Test
    void anyDTOFromCollectionModelShouldHaveSelfLink(){
        //arrange
        TeacherDTO teacherDTO = mock(TeacherDTO.class);
        Iterable<TeacherDTO> teacherDTOS = Collections.singleton(teacherDTO);
        TeacherHateoasAssemblerImpl hateoasAssembler = new TeacherHateoasAssemblerImpl();
        //act
        CollectionModel<EntityModel<TeacherDTO>> result = hateoasAssembler.toCollectionModel(teacherDTOS);
        //assert
        assertTrue(result.getContent().iterator().next().hasLink("self"));
    }

}