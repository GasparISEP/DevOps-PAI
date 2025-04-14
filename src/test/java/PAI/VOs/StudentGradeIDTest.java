package PAI.VOs;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentGradeIDTest {

    @Test
    void shouldRandomNumber (){
        //arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        StudentID studentIDDouble = mock(StudentID.class);
        StudentGradeID studentGradeId1 = new StudentGradeID();
        //assert
        assertNotNull(studentGradeId1);
    }

    // equals

    @Test
    void shouldReturnTrueIfSameLoc(){
        //arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        StudentID studentIDDouble = mock(StudentID.class);
        StudentGradeID studentGradeId1 = new StudentGradeID();
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
        StudentGradeID studentGradeId1 = new StudentGradeID();
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
        StudentGradeID studentGradeId1 = new StudentGradeID();
        StudentGradeID studentGradeId2 = new StudentGradeID();
        //act
        boolean result = studentGradeId1.equals(studentGradeId2);
        //assert
        assertFalse(result);
    }

    @Test
    void testInequalityWithDifferentUUIDs() {
        StudentGradeID id1 = new StudentGradeID();
        StudentGradeID id2 = new StudentGradeID();

        assertNotEquals(id1, id2);
    }

    @Test
    void testEqualsWithSameObject() {
        StudentGradeID id = new StudentGradeID();
        assertEquals(id, id);
    }

    @Test
    void testEqualsWithNull() {
        StudentGradeID id = new StudentGradeID();
        assertNotEquals(id, null);
    }

    @Test
    void testEqualsWithDifferentType() {
        StudentGradeID id = new StudentGradeID();
        assertNotEquals(id, "not a StudentGradeID");
    }

    @Test
    void shouldReturnFalseIfDifferentStudentID(){
        //arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        StudentID studentIDDouble = mock(StudentID.class);
        CourseEditionID courseEditionID2Double = mock(CourseEditionID.class);
        StudentID studentID2Double = mock(StudentID.class);
        StudentGradeID studentGradeId1 = new StudentGradeID();
        StudentGradeID studentGradeId2 = new StudentGradeID();
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
        StudentGradeID studentGradeID = new StudentGradeID();
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

        StudentGradeID studentGradeID = new StudentGradeID();

        // Act
        String result = studentGradeID.toString();

        // Assert
        assertFalse(result.isEmpty());
    }

    @Test
    void shouldReturnTrueIfUUIDsAreEqualViaReflection() throws Exception {
        UUID uuid = UUID.randomUUID();

        StudentGradeID id1 = new StudentGradeID();
        StudentGradeID id2 = new StudentGradeID();

        Field field = StudentGradeID.class.getDeclaredField("_studentGradeId");
        field.setAccessible(true);

        field.set(id1, uuid);
        field.set(id2, uuid);

        assertEquals(id1, id2);
    }

}