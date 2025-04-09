package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseEditionEnrolmentIDTest {

    @Test
    void shouldReturnAValidCourseEditionEnrolmentID() {
        //arrange
        StudentID doubleStudentID = mock (StudentID.class);
        CourseEditionID doubleCourseEditionID = mock (CourseEditionID.class);

        //act & assert
        CourseEditionEnrolmentID ceeID = new CourseEditionEnrolmentID(doubleStudentID, doubleCourseEditionID);
    }

    @Test
    void shouldReturnAnExceptionIfStudentIDIsNull() {
        //arrange
        CourseEditionID doubleCourseEditionID = mock (CourseEditionID.class);

        //act & assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CourseEditionEnrolmentID(null, doubleCourseEditionID);
        });
        assertEquals("StudentID and CourseEditionID cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnAnExceptionIfCourseEditionIDIsNull() {
        //arrange
        StudentID doubleStudentID = mock (StudentID.class);

        //act & assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CourseEditionEnrolmentID(doubleStudentID, null);
        });
        assertEquals("StudentID and CourseEditionID cannot be null.", exception.getMessage());
    }


    //testing equals method
    @Test
    void shouldReturnFalseWhenTwoIDsAreDifferent() {
        //arrange
        StudentID doubleStudentID = mock (StudentID.class);
        CourseEditionID doubleCourseEditionID = mock (CourseEditionID.class);

        StudentID doubleStudentID1 = mock (StudentID.class);
        CourseEditionID doubleCourseEditionID1 = mock (CourseEditionID.class);

        CourseEditionEnrolmentID ceeID1 = new CourseEditionEnrolmentID(doubleStudentID, doubleCourseEditionID);
        CourseEditionEnrolmentID ceeID2 = new CourseEditionEnrolmentID(doubleStudentID1, doubleCourseEditionID1);

        //act
        boolean result = ceeID1.equals(ceeID2);

        //assert
        assertFalse (result);
    }

    @Test
    void shouldReturnFalseWhenIdIsNull() {
        //arrange
        StudentID doubleStudentID = mock (StudentID.class);
        CourseEditionID doubleCourseEditionID = mock (CourseEditionID.class);

        CourseEditionEnrolmentID ceeID1 = new CourseEditionEnrolmentID(doubleStudentID, doubleCourseEditionID);

        //act
        boolean result = ceeID1.equals(null);

        //assert
        assertFalse (result);
    }

    @Test
    void shouldReturnFalseWhenIdAreNotFromSameClass() {
        //arrange
        StudentID doubleStudentID = mock (StudentID.class);
        CourseEditionID doubleCourseEditionID = mock (CourseEditionID.class);

        CourseEditionEnrolmentID courseEditionEnrolmentID = new CourseEditionEnrolmentID(doubleStudentID, doubleCourseEditionID);

        //act
        boolean result = courseEditionEnrolmentID.equals(doubleCourseEditionID);

        //assert
        assertFalse (result);
    }

    @Test
    void shouldReturnTrueWhenTwoIDsHaveTheSameMemoryReference() {
        //arrange
        StudentID doubleStudentID = mock (StudentID.class);
        CourseEditionID doubleCourseEditionID = mock (CourseEditionID.class);

        CourseEditionEnrolmentID courseEditionEnrolmentID1 = new CourseEditionEnrolmentID(doubleStudentID, doubleCourseEditionID);
        CourseEditionEnrolmentID courseEditionEnrolmentID2 = courseEditionEnrolmentID1;

        boolean result = courseEditionEnrolmentID1.equals(courseEditionEnrolmentID2);

        assertTrue (result);
    }

    @Test
    void shouldReturnTrueWhenTwoIdHaveTheSameID() {
        //Arrange
        StudentID doubleStudentID = mock (StudentID.class);
        CourseEditionID doubleCourseEditionID = mock (CourseEditionID.class);

        CourseEditionEnrolmentID courseEditionEnrolmentID = new CourseEditionEnrolmentID(doubleStudentID, doubleCourseEditionID);
        CourseEditionEnrolmentID courseEditionEnrolmentID2 = new CourseEditionEnrolmentID(doubleStudentID, doubleCourseEditionID);

        //act
        boolean result = courseEditionEnrolmentID.equals(courseEditionEnrolmentID2);

        //Assert
        assertTrue (result);
    }

    //testing hashCode method
    @Test
    void shouldReturnAHashCodeForOneId() {
        //Arrange
        StudentID doubleStudentID = mock (StudentID.class);
        CourseEditionID doubleCourseEditionID = mock (CourseEditionID.class);
        CourseEditionEnrolmentID courseEditionEnrolmentID1 = new CourseEditionEnrolmentID(doubleStudentID, doubleCourseEditionID);

        //Act
        int result = courseEditionEnrolmentID1.hashCode();

        //Assert
        assertNotNull (result);
    }

    @Test
    void shouldReturnTheSameHashCodeForTwoIDs() {
        //Arrange
        StudentID doubleStudentID = mock (StudentID.class);
        CourseEditionID doubleCourseEditionID = mock (CourseEditionID.class);

        CourseEditionEnrolmentID courseEditionEnrolmentID1 = new CourseEditionEnrolmentID(doubleStudentID, doubleCourseEditionID);
        int courseEditionEnrolmentID2 = courseEditionEnrolmentID1.hashCode();

        //Act
        int result = courseEditionEnrolmentID1.hashCode();

        //Assert
        assertEquals(courseEditionEnrolmentID2, result);
    }

    @Test
    void shouldReturnADifferentHashCodeForTwoIDs() {
        //Arrange
        StudentID doubleStudentID1 = mock (StudentID.class);
        CourseEditionID doubleCourseEditionID1 = mock (CourseEditionID.class);

        StudentID doubleStudentID2 = mock (StudentID.class);
        CourseEditionID doubleCourseEditionID2 = mock (CourseEditionID.class);

        CourseEditionEnrolmentID courseEditionEnrolmentID1 = new CourseEditionEnrolmentID(doubleStudentID1,doubleCourseEditionID1);
        CourseEditionEnrolmentID courseEditionEnrolmentID2 = new CourseEditionEnrolmentID(doubleStudentID2, doubleCourseEditionID2);

        //Act
        int result = courseEditionEnrolmentID1.hashCode();

        //Assert
        assertNotEquals(courseEditionEnrolmentID2.hashCode(), result);
    }

    //testing toString method
//    @Test
//    void shouldReturnAStringWithTheID() {
//        //Arrange
//        StudentID doubleStudentID = mock (StudentID.class);
//        CourseEditionID doubleCourseEditionID = mock (CourseEditionID.class);
//
//        when (doubleStudentID.toString()).thenReturn("1");
//        when (doubleCourseEditionID.toString()).thenReturn("2");
//
//        CourseEditionEnrolmentID courseEditionEnrolmentID = new CourseEditionEnrolmentID(doubleStudentID, doubleCourseEditionID);
//
//        //Act
//        String ceeString = courseEditionEnrolmentID.toString();
//
//        //Assert
//        assertEquals("CourseEditionEnrolmentID =12", ceeString);
//    }
}