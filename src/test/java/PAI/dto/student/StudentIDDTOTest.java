package PAI.dto.student;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentIDDTOTest {

    @Test
    void testValidStudentIDConversion() {
        StudentIDDTO dto = new StudentIDDTO("1234567");
        String studentID = dto.studentID();

        assertEquals("1234567", studentID);
    }

}
