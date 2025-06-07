package PAI.dto.courseEdition;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentCountDTOTest {

    @Test
    void shouldCreateStudentCountDTOWithGivenValue() {
        //arrange
        int studentCount = 22;

        //act
        StudentCountDTO result = new StudentCountDTO(22);

        //assert
        assertEquals(studentCount, result.studentCount());
    }
}