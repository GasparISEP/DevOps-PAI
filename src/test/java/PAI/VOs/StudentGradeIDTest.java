package PAI.VOs;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentGradeIDTest {

    @Test
    void shouldReturnStudentGradeID (){
        //arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        StudentID studentIDDouble = mock(StudentID.class);
        StudentGradeID studentGradeId1 = new StudentGradeID(studentIDDouble,courseEditionIDDouble);
        //assert
        assertNotNull(studentGradeId1);
    }

    @Test
    void shouldReturnStudentID () {
        //arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        StudentID studentIDDouble = mock(StudentID.class);
        StudentGradeID studentGradeId = new StudentGradeID(studentIDDouble, courseEditionIDDouble);
        //act
        StudentID result = studentGradeId.get_studentID();
        //act
        assertEquals(studentIDDouble, result);
    }

    @Test
    void shouldReturnCourseEditionID () {
        //arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        StudentID studentIDDouble = mock(StudentID.class);
        StudentGradeID studentGradeId = new StudentGradeID(studentIDDouble, courseEditionIDDouble);
        //act
        CourseEditionID result = studentGradeId.get_courseEdition();
        //act
        assertEquals(courseEditionIDDouble, result);
    }

    // equals

    @Test
    void shouldReturnTrueIfSameLoc(){
        //arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        StudentID studentIDDouble = mock(StudentID.class);
        StudentGradeID studentGradeId1 = new StudentGradeID(studentIDDouble,courseEditionIDDouble);
        StudentGradeID studentGradeId2 = studentGradeId1;
        //act
        boolean result = studentGradeId1.equals(studentGradeId2);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfFromDifferentInstances(){
        //arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        StudentID studentIDDouble = mock(StudentID.class);
        StudentGradeID studentGradeId1 = new StudentGradeID(studentIDDouble,courseEditionIDDouble);
        Object o = new Object();
        //act
        boolean result = studentGradeId1.equals(o);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfDifferentContent(){
        //arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        StudentID studentIDDouble = mock(StudentID.class);
        CourseEditionID courseEditionID2Double = mock(CourseEditionID.class);
        StudentID studentID2Double = mock(StudentID.class);
        StudentGradeID studentGradeId1 = new StudentGradeID(studentIDDouble,courseEditionIDDouble);
        StudentGradeID studentGradeId2 = new StudentGradeID(studentID2Double,courseEditionID2Double);
        //act
        boolean result = studentGradeId1.equals(studentGradeId2);
        //assert
        assertFalse(result);
    }


    @Test
    void shouldReturnFalseIfDifferentStudentID(){
        //arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        StudentID studentIDDouble = mock(StudentID.class);
        StudentID studentID2Double = mock(StudentID.class);
        StudentGradeID studentGradeId1 = new StudentGradeID(studentIDDouble,courseEditionIDDouble);
        StudentGradeID studentGradeId2 = new StudentGradeID(studentID2Double,courseEditionIDDouble);
        //act
        boolean result = studentGradeId1.equals(studentGradeId2);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfDifferentCourseEditionID(){
        //arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        StudentID studentIDDouble = mock(StudentID.class);
        CourseEditionID courseEditionID2Double = mock(CourseEditionID.class);
        StudentGradeID studentGradeId1 = new StudentGradeID(studentIDDouble,courseEditionIDDouble);
        StudentGradeID studentGradeId2 = new StudentGradeID(studentIDDouble,courseEditionID2Double);
        //act
        boolean result = studentGradeId1.equals(studentGradeId2);
        //assert
        assertFalse(result);
    }
    @Test
    void testToString() {
        //arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        StudentID studentIDDouble = mock(StudentID.class);
        StudentGradeID studentGradeID = new StudentGradeID(studentIDDouble,courseEditionIDDouble);
        //act
        String result = studentGradeID.toString();
        //assert
        assertEquals(studentGradeID.toString(),result);
    }
    @Test
    void testToStringNotEmpty() {
        // Arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        StudentID studentIDDouble = mock(StudentID.class);

        when(studentIDDouble.toString()).thenReturn("MockStudentID");
        when(courseEditionIDDouble.toString()).thenReturn("MockCourseEditionID");

        StudentGradeID studentGradeID = new StudentGradeID(studentIDDouble,courseEditionIDDouble);

        // Act
        String result = studentGradeID.toString();

        // Assert
        assertFalse(result.isEmpty());
    }

}