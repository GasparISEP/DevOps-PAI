package PAI.dto.teacher;

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

}