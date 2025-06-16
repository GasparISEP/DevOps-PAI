package PAI.dto.Programme;

import PAI.dto.teacher.TeacherIdDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeDirectorResponseDTOTest {

    @Test
    void testConstructorAndGetter() {
        TeacherIdDTO teacher1 = new TeacherIdDTO("ABC");
        TeacherIdDTO teacher2 = new TeacherIdDTO("XYZ");

        List<TeacherIdDTO> teacherList = List.of(teacher1, teacher2);
        ProgrammeDirectorResponseDTO dto = new ProgrammeDirectorResponseDTO(teacherList);

        // One assert as you requested
        assertEquals(teacherList, dto.teachers());
    }
}