package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StudentGradeIDTest {

    @Test
    void shouldRandomNumber (){
        //arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        StudentID studentIDDouble = mock(StudentID.class);
        StudentGradeID studentGradeId1 = new StudentGradeID(studentIDDouble,courseEditionIDDouble);
        //assert
        assertNotNull(studentGradeId1);
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
        CourseEditionID courseEditionID2Double = mock(CourseEditionID.class);
        StudentID studentID2Double = mock(StudentID.class);
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

}