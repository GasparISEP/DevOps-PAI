package PAI.domain;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseEdition_2Test {

    //US19
    @Test
    void shouldCreateCourseEditionWithoutCourseEditionIDAsParameter() {
        //SUT = CourseEdition -> ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock (CourseInStudyPlanID.class);

        //Act
        CourseEdition_2 courseEdition = new CourseEdition_2(courseInStudyPlanIDDouble, programmeEditionIDDouble);

        //Assert
        assertNotNull(courseEdition);

    }

    @Test
    void shouldCreateCourseEditionWithCourseEditionIDAsParameter() {
        //SUT = CourseEdition -> CourseEditionID, ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock (CourseInStudyPlanID.class);

        //Act
        CourseEdition_2 courseEdition = new CourseEdition_2(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble);

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
        assertThrows(Exception.class, () -> {new CourseEdition_2(null, courseInStudyPlanIDDouble, programmeEditionIDDouble);});

    }

    @Test
    void shouldThrowExceptionIfCourseInStudyPlanIDIsNulWithCourseEditionIDAsParameter() {
        //SUT = CourseEdition -> ProgrammeEditionID + CourseEditionID as Doubles and CourseInStudyPlanID forced to be null
        //Arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);

        //Act + Assert
        assertThrows(Exception.class, () -> {new CourseEdition_2(courseEditionIDDouble, null, programmeEditionIDDouble);});

    }

    @Test
    void shouldThrowExceptionIfCourseInStudyPlanIDIsNullWithoutCourseEditionIDAsParameter() {
        //SUT = CourseEdition -> ProgrammeEditionID as Double and CourseInStudyPlanID forced to be null
        //Arrange
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);

        //Act + Assert
        assertThrows(Exception.class, () -> {new CourseEdition_2(null, programmeEditionIDDouble);});

    }

    @Test
    void shouldThrowExceptionIfProgrammeEditionIDIsNullWithCourseEditionIDAsParameter() {
        //SUT = CourseEdition -> ProgrammeEditionID forced to be null and CourseInStudyPlanID + CourseEditionID as Doubles
        //Arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock (CourseInStudyPlanID.class);

        //Act + Assert
        assertThrows(Exception.class, () -> {new CourseEdition_2(courseEditionIDDouble, courseInStudyPlanIDDouble, null);});

    }

    @Test
    void shouldThrowExceptionIfProgrammeEditionIDIsNullWithoutCourseEditionIDAsParameter() {
        //SUT = CourseEdition -> ProgrammeEditionID forced to be null and CourseInStudyPlanID as Double
        //Arrange
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock (CourseInStudyPlanID.class);

        //Act + Assert
        assertThrows(Exception.class, () -> {new CourseEdition_2(courseInStudyPlanIDDouble, null);});

    }

    @Test
    void shouldThrowExceptionIfProgrammeEditionIDAndCourseInStudyPlanIDAreNullWithCourseEditionIDAsParameter() {
        //SUT = CourseEdition -> CourseEditionID as Double, ProgrammeEditionID and CourseInStudyPlanID forced to be null
        //Arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        //Act + Assert
        assertThrows(Exception.class, () -> {new CourseEdition_2(courseEditionIDDouble,null, null);});

    }

    @Test
    void shouldThrowExceptionIfProgrammeEditionIDAndCourseInStudyPlanIDAreNullWithoutCourseEditionIDAsParameter() {
        //SUT = CourseEdition -> ProgrammeEditionID and CourseInStudyPlanID forced to be null
        //Arrange
        //Act + Assert
        assertThrows(Exception.class, () -> {new CourseEdition_2(null, null);});

    }

    @Test
    void shouldReturnIdentityNotNull() {
        //SUT = CourseEdition -> CourseEditionID, ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock (CourseInStudyPlanID.class);
        CourseEdition_2 courseEdition = new CourseEdition_2(courseInStudyPlanIDDouble, programmeEditionIDDouble);

        //Act
        CourseEditionID courseEditionID = courseEdition.identity();

        //Assert
        assertNotNull(courseEditionID);
    }

    @Test
    void shouldReturnCourseEditionID() {
        //SUT = CourseEdition -> CourseEditionID, ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock (CourseInStudyPlanID.class);
        CourseEdition_2 courseEdition = new CourseEdition_2(courseInStudyPlanIDDouble, programmeEditionIDDouble);

        //Act
        CourseEditionID courseEditionID = courseEdition.identity();

        //Assert
        assertEquals(courseEditionID, courseEdition.identity());
    }

    @Test
    void shouldReturnExceptionWithSpyInCourseEdition() {
        //Arrange
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock (CourseInStudyPlanID.class);

        //Act
            //Spy simulates error identity()
        CourseEdition_2 courseEdition = spy(new CourseEdition_2(courseInStudyPlanIDDouble, programmeEditionIDDouble));
        doThrow(new RuntimeException("sim error")).when(courseEdition).identity();

        //Assert
        assertThrows(RuntimeException.class, courseEdition::identity);
    }

    @Test
    void shouldReturnTrueIfCourseEditionSameAsObject() {
        //SUT = CourseEdition -> ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock (CourseInStudyPlanID.class);

        CourseEdition_2 courseEdition = new CourseEdition_2(courseInStudyPlanIDDouble, programmeEditionIDDouble);
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
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock (CourseInStudyPlanID.class);

        CourseEdition_2 courseEdition = new CourseEdition_2(courseInStudyPlanIDDouble, programmeEditionIDDouble);
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
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock (CourseInStudyPlanID.class);

        CourseEdition_2 courseEdition1 = new CourseEdition_2(courseInStudyPlanIDDouble, programmeEditionIDDouble);
        CourseEdition_2 courseEdition2 = new CourseEdition_2(courseInStudyPlanIDDouble, programmeEditionIDDouble);

        //Act
        boolean result = courseEdition1.sameAs(courseEdition2);
        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfCourseInStudyPlanIDAreNotSameButProgrammeEditionIDAreTheSame() {
        //SUT = CourseEdition -> ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble1 = mock (CourseInStudyPlanID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble2 = mock (CourseInStudyPlanID.class);

        CourseEdition_2 courseEdition1 = new CourseEdition_2(courseInStudyPlanIDDouble1, programmeEditionIDDouble);
        CourseEdition_2 courseEdition2 = new CourseEdition_2(courseInStudyPlanIDDouble2, programmeEditionIDDouble);

        //Act
        boolean result = courseEdition1.sameAs(courseEdition2);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfCourseInStudyPlanIDAreTheSameButProgrammeEditionIDAreNotSame() {
        //SUT = CourseEdition -> ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        ProgrammeEditionID programmeEditionIDDouble1 = mock(ProgrammeEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble2 = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble1 = mock (CourseInStudyPlanID.class);

        CourseEdition_2 courseEdition1 = new CourseEdition_2(courseInStudyPlanIDDouble1, programmeEditionIDDouble1);
        CourseEdition_2 courseEdition2 = new CourseEdition_2(courseInStudyPlanIDDouble1, programmeEditionIDDouble2);

        //Act
        boolean result = courseEdition1.sameAs(courseEdition2);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnProgrammeEditionIDFromCourseEdition() {
        //SUT = CourseEdition -> ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);

        //Act
        CourseEdition_2 courseEdition = new CourseEdition_2(courseInStudyPlanIDDouble, programmeEditionIDDouble);

        //Assert
        assertEquals(programmeEditionIDDouble, courseEdition.getProgrammeEditionID());
    }

    @Test
    void shouldReturnCourseInbStudyPlanIDFromCourseEdition() {
        //SUT = CourseEdition -> ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);

        //Act
        CourseEdition_2 courseEdition = new CourseEdition_2(courseInStudyPlanIDDouble, programmeEditionIDDouble);

        //Assert
        assertEquals(courseInStudyPlanIDDouble, courseEdition.getCourseInStudyPlanID());
    }

    @Test
    void shouldReturnFalseForEqualsWithNullObjectToCompare() {
        //SUT = CourseEdition -> ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);

        CourseEdition_2 courseEdition = new CourseEdition_2(courseInStudyPlanIDDouble, programmeEditionIDDouble);

        //Act
        boolean result = courseEdition.equals(null);

        assertFalse(result);
    }

    @Test
    void shouldReturnTrueEqualsWhenComparedWithSameInstanceOfObject() {
        //SUT = CourseEdition -> ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);

        CourseEdition_2 courseEdition = new CourseEdition_2(courseInStudyPlanIDDouble, programmeEditionIDDouble);

        //Act
        boolean result = courseEdition.equals(courseEdition);

        assertTrue(result);
    }

    @Test
    void shouldReturnFalseForEqualsOfCourseEditionsWithSameParameters() {
        //SUT = CourseEdition -> ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);

        CourseEdition_2 courseEdition = new CourseEdition_2(courseInStudyPlanIDDouble, programmeEditionIDDouble);
        CourseEdition_2 courseEdition2 = new CourseEdition_2(courseInStudyPlanIDDouble, programmeEditionIDDouble);

        boolean result = courseEdition.equals(courseEdition2);

        //Assert
            //false because ids are different (ids are created when object is created)
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfClassesAreDifferent() {
        //SUT = CourseEdition -> ProgrammeEditionID, CourseinStudyPlanID and TeacherCategory as Doubles
        //Arrange
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock (CourseInStudyPlanID.class);

        CourseEdition_2 courseEdition = new CourseEdition_2(courseInStudyPlanIDDouble, programmeEditionIDDouble);
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

        CourseEdition_2 courseEditionDouble1 = new CourseEdition_2(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble);
        CourseEdition_2 courseEditionDouble2 = new CourseEdition_2(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble);

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

        CourseEdition_2 courseEditionDouble1 = new CourseEdition_2(courseEditionIDDouble1, courseInStudyPlanIDDouble, programmeEditionIDDouble);
        CourseEdition_2 courseEditionDouble2 = new CourseEdition_2(courseEditionIDDouble2, courseInStudyPlanIDDouble, programmeEditionIDDouble);

        //Act
        boolean result = courseEditionDouble1.equals(courseEditionDouble2);

        //Assert
        assertFalse(result);
    }
}