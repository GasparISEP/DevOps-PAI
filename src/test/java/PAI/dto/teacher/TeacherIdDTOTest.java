package PAI.dto.teacher;

import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherIdDTOTest {

    @Test
    void shouldCreateTeacherIdDTO() {
        //arrange
        String id = "ABC";
        //act
        TeacherIdDTO teacherIdDTO = new TeacherIdDTO(id);
        //assert
        assertNotNull(teacherIdDTO);
        assertEquals(id, teacherIdDTO.id());
    }

    @Test
    void shouldCreateTeacherIdDtoWithNullId() {
        //arrange
        String id = null;
        //act
        TeacherIdDTO teacherIdDTO = new TeacherIdDTO(id);
        //assert
        assertNotNull(teacherIdDTO);
        assertEquals(id, teacherIdDTO.id());
    }

}