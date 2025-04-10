package PAI.VOs;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseEditionIDTest {

    @Test
    void shouldReturnCourseEditionIDNotNull() {
        //Arrange
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);

        //Act
        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionIDDouble, courseInStudyPlanIDDouble);
        //Assert
        assertNotNull(courseEditionID);
    }

    @Test
    void shouldThrowExceptionIfCourseInStudyPlanIDIsNull() {
        //Arrange
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);

        //Act
        //Assert
        assertThrows(Exception.class, () -> {new CourseEditionID(programmeEditionIDDouble, null);});
    }

    @Test
    void shouldThrowExceptionIfProgrammeEditionIDIsNull() {
        //Arrange
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);

        //Act
        //Assert
        assertThrows(Exception.class, () -> {new CourseEditionID(null, courseInStudyPlanIDDouble);});
    }

    @Test
    void shouldThrowExceptionIfAttributesAreNull() {
        //Arrange
        //Act
        //Assert
        assertThrows(Exception.class, () -> {new CourseEditionID(null, null);});
    }

    @Test
    void shouldReturnCorrectToString() {
        // Arrange
        ProgrammeEditionID mockProgrammeEditionID = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID mockCourseInStudyPlanID = mock(CourseInStudyPlanID.class);

        when(mockProgrammeEditionID.toString()).thenReturn("PE123");
        when(mockCourseInStudyPlanID.toString()).thenReturn("CSP456");

        CourseEditionID courseEditionID = new CourseEditionID(mockProgrammeEditionID, mockCourseInStudyPlanID);

        // Act
        String result = courseEditionID.toString();

        // Assert
        assertEquals("PE123-CSP456", result);
}

    @Test
    void shouldReturnsEqualsIfObjectsAreEqual() {
        //Arrange
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionIDDouble, courseInStudyPlanIDDouble);
        CourseEditionID courseEditionID2 = courseEditionID;
        //Act + Assert
        assertEquals(courseEditionID, courseEditionID2);
    }

    @Test
    void shouldReturnEqualsIfDifferentObjectsHaveSameAttributes() {
        //Arrange
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionIDDouble, courseInStudyPlanIDDouble);
        CourseEditionID courseEditionID2 = new CourseEditionID(programmeEditionIDDouble, courseInStudyPlanIDDouble);

        //Act
        boolean result = courseEditionID.equals(courseEditionID2);
        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnNotEqualsIfCourseEditionIDsHaveDifferentIDs() {
        //Arrange
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble1 = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble1 = mock(CourseInStudyPlanID.class);
        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionIDDouble, courseInStudyPlanIDDouble);
        CourseEditionID courseEditionID2 = new CourseEditionID(programmeEditionIDDouble1, courseInStudyPlanIDDouble1);
        CourseEditionID courseEditionID3 = new CourseEditionID(programmeEditionIDDouble1, courseInStudyPlanIDDouble);
        CourseEditionID courseEditionID4 = new CourseEditionID(programmeEditionIDDouble, courseInStudyPlanIDDouble1);
        //Act+Assert
        assertNotEquals(courseEditionID, courseEditionID2);
        assertNotEquals(courseEditionID, courseEditionID3);
        assertNotEquals(courseEditionID, courseEditionID4);
        assertNotEquals(courseEditionID2, courseEditionID3);
        assertNotEquals(courseEditionID3, courseEditionID4);
    }

    @Test
    void shouldReturnNotEqualsIfComparingWithNull() {
        //Arrange
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionIDDouble, courseInStudyPlanIDDouble);
        CourseEditionID courseEditionID2 = null;
        //Act
        boolean result = courseEditionID.equals(courseEditionID2);
        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnNotEqualsIfObjectsAreNotFromSameClass() {
       //Arrange
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionIDDouble, courseInStudyPlanIDDouble);
        CourseID courseID = new CourseID();
        //Act
        boolean result = courseEditionID.equals(courseID);
        //Assert
        assertFalse(result);
    }

}