package PAI.dto.student;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import PAI.VOs.StudentID;

class StudentIDDTOTest {

    @Test
    void shouldReturnStudentID() {
        // Arrange
        StudentID mockStudentID =  mock(StudentID.class);

        // Act
        StudentIDDTO dto = new StudentIDDTO(mockStudentID);

        // Assert
        assertEquals(mockStudentID, dto.studentID());
    }

    @Test
    void shouldSupportEquality() {
        //arrange
        int number = 1234567;
        StudentID studentID1 = mock(StudentID.class);

        StudentIDDTO dto1 = new StudentIDDTO(studentID1);
        StudentIDDTO dto2 = new StudentIDDTO(studentID1);

        //act
        when(studentID1.getUniqueNumber()).thenReturn(number);

        //assert
        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

}