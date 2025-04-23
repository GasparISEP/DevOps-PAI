package PAI.domain;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.TeacherID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseEditionTest {

    //US19

    @Test
    void shouldCreateCourseEdition() {
        //SUT = CourseEdition -> CourseEditionID, ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock (CourseInStudyPlanID.class);

        //Act
        CourseEdition courseEdition = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble);

        //Assert
        assertNotNull(courseEdition);

    }

    @Test
    void shouldThrowExceptionIfCourseEditionIDIsNull(){
        //SUT = CourseEdition -> ProgrammeEditionID as Double and CourseInStudyPlanID forced to be null
        //Arrange
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock (CourseInStudyPlanID.class);

        //Act + Assert
        assertThrows(Exception.class, () -> {new CourseEdition(null, courseInStudyPlanIDDouble, programmeEditionIDDouble);});

    }

    @Test
    void shouldThrowExceptionIfCourseInStudyPlanIDIsNull() {
        //SUT = CourseEdition -> ProgrammeEditionID + CourseEditionID as Doubles and CourseInStudyPlanID forced to be null
        //Arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);

        //Act + Assert
        assertThrows(Exception.class, () -> {new CourseEdition(courseEditionIDDouble, null, programmeEditionIDDouble);});

    }


    @Test
    void shouldThrowExceptionIfProgrammeEditionIDIsNull() {
        //SUT = CourseEdition -> ProgrammeEditionID forced to be null and CourseInStudyPlanID + CourseEditionID as Doubles
        //Arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock (CourseInStudyPlanID.class);

        //Act + Assert
        assertThrows(Exception.class, () -> {new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, null);});

    }

    @Test
    void shouldThrowExceptionIfProgrammeEditionIDAndCourseInStudyPlanIDAreNull() {
        //SUT = CourseEdition -> CourseEditionID as Double, ProgrammeEditionID and CourseInStudyPlanID forced to be null
        //Arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        //Act + Assert
        assertThrows(Exception.class, () -> {new CourseEdition(courseEditionIDDouble,null, null);});

    }


    @Test
    void shouldReturnIdentityNotNull() {
        //SUT = CourseEdition -> CourseEditionID, ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock (CourseInStudyPlanID.class);
        CourseEdition courseEdition = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble);

        //Act
        CourseEditionID courseEditionID = courseEdition.identity();

        //Assert
        assertNotNull(courseEditionID);
    }

    @Test
    void shouldReturnCourseEditionID() {
        //SUT = CourseEdition -> CourseEditionID, ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock (CourseInStudyPlanID.class);
        CourseEdition courseEdition = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble);

        //Act
        CourseEditionID courseEditionID = courseEdition.identity();

        //Assert
        assertEquals(courseEditionID, courseEdition.identity());
    }

    @Test
    void shouldReturnNullWhenGetRucMethodIsCalled() {
        //SUT = CourseEdition -> CourseEditionID, ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock (CourseInStudyPlanID.class);
        CourseEdition courseEdition = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble);

        //Act
        TeacherID teacherID = courseEdition.getRuc();

        //Assert
        assertNull(teacherID);
    }

    @Test
    void shouldReturnATeacherIDWhenGetRucMethodIsCalledFromACourseEditionThatHasAValidRuc() {
        //SUT = CourseEdition -> CourseEditionID, ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock (CourseInStudyPlanID.class);
        TeacherID teacherID = mock (TeacherID.class);
        CourseEdition courseEdition = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble);
        courseEdition.setRuc(teacherID);

        //Act
        TeacherID teacherIDResult = courseEdition.getRuc();

        //Assert
        assertNotNull(teacherIDResult);
        assertEquals(teacherID, teacherIDResult);
    }

    @Test
    void shouldReturnTrueIfCourseEditionSameAsObject() {
        //SUT = CourseEdition -> ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock (CourseInStudyPlanID.class);

        CourseEdition courseEdition = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble);
        Object courseEdition2 = courseEdition;

        //Act
        boolean result = courseEdition.sameAs(courseEdition2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfObjectIsNotSameAsCourseEdition() {
        //SUT = CourseEdition -> ProgrammeEditionID, CourseinStudyPlanID and TeacherCategory as Doubles
        //Arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock (CourseInStudyPlanID.class);

        CourseEdition courseEdition = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble);
        TeacherCategory teacherCategoryDouble = mock (TeacherCategory.class);

        //Act
        boolean result = courseEdition.sameAs(teacherCategoryDouble);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfCourseInStudyPlanIDAndProgrammeEditionIDOfBothObjectsAreTheSame() {
        //SUT = CourseEdition -> ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock (CourseInStudyPlanID.class);

        CourseEdition courseEdition1 = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble);
        CourseEdition courseEdition2 = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble);

        //Act
        boolean result = courseEdition1.sameAs(courseEdition2);
        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfCourseInStudyPlanIDAreNotSameButProgrammeEditionIDAreTheSame() {
        //SUT = CourseEdition -> ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseEditionID courseEditionIDDouble2 = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble1 = mock (CourseInStudyPlanID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble2 = mock (CourseInStudyPlanID.class);

        CourseEdition courseEdition1 = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble1, programmeEditionIDDouble);
        CourseEdition courseEdition2 = new CourseEdition(courseEditionIDDouble2, courseInStudyPlanIDDouble2, programmeEditionIDDouble);

        //Act
        boolean result = courseEdition1.sameAs(courseEdition2);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfCourseInStudyPlanIDAreTheSameButProgrammeEditionIDAreNotSame() {
        //SUT = CourseEdition -> ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseEditionID courseEditionIDDouble2 = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble1 = mock(ProgrammeEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble2 = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble1 = mock (CourseInStudyPlanID.class);

        CourseEdition courseEdition1 = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble1, programmeEditionIDDouble1);
        CourseEdition courseEdition2 = new CourseEdition(courseEditionIDDouble2, courseInStudyPlanIDDouble1, programmeEditionIDDouble2);

        //Act
        boolean result = courseEdition1.sameAs(courseEdition2);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnProgrammeEditionIDFromCourseEdition() {
        //SUT = CourseEdition -> ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);

        //Act
        CourseEdition courseEdition = new CourseEdition(courseEditionIDDouble,courseInStudyPlanIDDouble, programmeEditionIDDouble);

        //Assert
        assertEquals(programmeEditionIDDouble, courseEdition.getProgrammeEditionID());
    }

    @Test
    void shouldReturnCourseInbStudyPlanIDFromCourseEdition() {
        //SUT = CourseEdition -> ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);

        //Act
        CourseEdition courseEdition = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble);

        //Assert
        assertEquals(courseInStudyPlanIDDouble, courseEdition.getCourseInStudyPlanID());
    }

    @Test
    void shouldReturnFalseForEqualsWithNullObjectToCompare() {
        //SUT = CourseEdition -> ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);

        CourseEdition courseEdition = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble);

        //Act
        boolean result = courseEdition.equals(null);

        assertFalse(result);
    }

    @Test
    void shouldReturnTrueEqualsWhenComparedWithSameInstanceOfObject() {
        //SUT = CourseEdition -> ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);

        CourseEdition courseEdition = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble);

        //Act
        boolean result = courseEdition.equals(courseEdition);

        assertTrue(result);
    }

    @Test
    void shouldReturnTrueForEqualsOfCourseEditionsWithSameParameters() {
        //SUT = CourseEdition -> ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);

        CourseEdition courseEdition = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble);
        CourseEdition courseEdition2 = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble);

        boolean result = courseEdition.equals(courseEdition2);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfClassesAreDifferent() {
        //SUT = CourseEdition -> ProgrammeEditionID, CourseinStudyPlanID and TeacherCategory as Doubles
        //Arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock (CourseInStudyPlanID.class);

        CourseEdition courseEdition = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble);
        TeacherCategory teacherCategoryDouble = mock (TeacherCategory.class);

        //Act
        boolean result = courseEdition.equals(teacherCategoryDouble);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfCourseEditionIDAreTheSame() {
        //SUT = CourseEdition -> CourseEditionID, ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);

        CourseEdition courseEditionDouble1 = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble);
        CourseEdition courseEditionDouble2 = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble);

        //Act
        boolean result = courseEditionDouble1.equals(courseEditionDouble2);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfCourseEditionIDsAreNotTheSame() {
        //SUT = CourseEdition -> CourseEditionID, ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseEditionID courseEditionIDDouble1 = mock(CourseEditionID.class);
        CourseEditionID courseEditionIDDouble2 = mock(CourseEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);

        CourseEdition courseEditionDouble1 = new CourseEdition(courseEditionIDDouble1, courseInStudyPlanIDDouble, programmeEditionIDDouble);
        CourseEdition courseEditionDouble2 = new CourseEdition(courseEditionIDDouble2, courseInStudyPlanIDDouble, programmeEditionIDDouble);

        //Act
        boolean result = courseEditionDouble1.equals(courseEditionDouble2);

        //Assert
        assertFalse(result);
    }

    // US20 - Test if the RUC is correctly defined if teacher is valid
    @Test
    void shouldReturnTrueIfRucIsSet() throws Exception {

        // Arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        CourseEdition courseEdition2 = new CourseEdition(courseEditionIDDouble, courseInStudyPlanID, programmeEditionID);
        TeacherID teacherID = mock(TeacherID.class);

        // Act
        boolean result = courseEdition2.setRuc(teacherID);

        // Assert
        Assertions.assertTrue(result);
    }

    // US20 - Test if the RUC is not defined when teacher is null
    @Test
    void shouldReturnFalseIfTeacherIsNull() throws Exception {

        // Arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        CourseEdition courseEdition2 = new CourseEdition(courseEditionIDDouble, courseInStudyPlanID, programmeEditionID);

        // Act
        boolean result = courseEdition2.setRuc(null);

        // Assert
        Assertions.assertFalse(result);
    }
}