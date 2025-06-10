package PAI.domain.courseEdition;

import PAI.VOs.*;
import PAI.domain.teacherCategory.TeacherCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CourseEditionTest {

    //US19

    @Test
    void shouldCreateCourseEdition() {
        //SUT = CourseEdition -> CourseEditionID, ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseEditionGeneratedID courseEditionGeneratedID = mock(CourseEditionGeneratedID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);

        //Act
        CourseEdition courseEdition = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble, courseEditionGeneratedID);

        //Assert
        assertNotNull(courseEdition);

    }

    @Test
    void shouldThrowExceptionIfCourseEditionIDIsNull() {
        //SUT = CourseEdition -> ProgrammeEditionID as Double and CourseInStudyPlanID forced to be null
        //Arrange
        CourseEditionGeneratedID courseEditionGeneratedID = mock(CourseEditionGeneratedID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);

        //Act + Assert
        Exception exception = assertThrows(Exception.class, () -> {
            new CourseEdition(null, courseInStudyPlanIDDouble, programmeEditionIDDouble, courseEditionGeneratedID);
        });
        assertEquals("CourseEditionID must be valid", exception.getMessage());

    }

    @Test
    void shouldThrowExceptionIfCourseInStudyPlanIDIsNull() {
        //SUT = CourseEdition -> ProgrammeEditionID + CourseEditionID as Doubles and CourseInStudyPlanID forced to be null
        //Arrange
        CourseEditionGeneratedID courseEditionGeneratedID = mock(CourseEditionGeneratedID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);

        //Act + Assert
        Exception exception = assertThrows(Exception.class, () -> {
            new CourseEdition(courseEditionIDDouble, null, programmeEditionIDDouble, courseEditionGeneratedID);
        });
        assertEquals("CourseInStudyPlanID must be valid", exception.getMessage());
    }


    @Test
    void shouldThrowExceptionIfProgrammeEditionIDIsNull() {
        //SUT = CourseEdition -> ProgrammeEditionID forced to be null and CourseInStudyPlanID + CourseEditionID as Doubles
        //Arrange
        CourseEditionGeneratedID courseEditionGeneratedID = mock(CourseEditionGeneratedID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);

        //Act + Assert
        Exception exception = assertThrows(Exception.class, () -> {
            new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, null, courseEditionGeneratedID);
        });
        assertEquals("ProgrammeEdition must be valid", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfCourseEditionGeneratedIDisNull() {
        //SUT = CourseEdition
        //Arrange
        CourseEditionGeneratedID courseEditionGeneratedID = null;
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);

        //Act + Assert
        Exception exception = assertThrows(Exception.class, () -> {
            new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble, courseEditionGeneratedID);
        });
        assertEquals("CourseEditionGeneratedID must be valid", exception.getMessage());

    }

    //Repeated tests with second constructor which includes a teacher(ruc)ID

    @Test
    void shouldCreateCourseEditionWithSecondConstructor() {
        //SUT = CourseEdition
        //Arrange
        CourseEditionGeneratedID courseEditionGeneratedID = mock(CourseEditionGeneratedID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        TeacherID teacherIDDouble = mock(TeacherID.class);

        //Act
        CourseEdition courseEdition = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble, courseEditionGeneratedID, teacherIDDouble);

        //Assert
        assertNotNull(courseEdition);

    }

    @Test
    void shouldThrowExceptionIfCourseEditionIDIsNullWithSecondConstructor() {
        //SUT = CourseEdition -> ProgrammeEditionID as Double and CourseInStudyPlanID forced to be null
        //Arrange
        CourseEditionGeneratedID courseEditionGeneratedID = mock(CourseEditionGeneratedID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        TeacherID teacherIDDouble = mock(TeacherID.class);

        //Act + Assert
        Exception exception = assertThrows(Exception.class, () -> {
            new CourseEdition(null, courseInStudyPlanIDDouble, programmeEditionIDDouble, courseEditionGeneratedID, teacherIDDouble);
        });
        assertEquals("CourseEditionID must be valid", exception.getMessage());

    }

    @Test
    void shouldThrowExceptionIfCourseInStudyPlanIDIsNullWithSecondConstructor() {
        //SUT = CourseEdition -> ProgrammeEditionID + CourseEditionID as Doubles and CourseInStudyPlanID forced to be null
        //Arrange
        CourseEditionGeneratedID courseEditionGeneratedID = mock(CourseEditionGeneratedID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        TeacherID teacherIDDouble = mock(TeacherID.class);

        //Act + Assert
        Exception exception = assertThrows(Exception.class, () -> {
            new CourseEdition(courseEditionIDDouble, null, programmeEditionIDDouble, courseEditionGeneratedID, teacherIDDouble);
        });
        assertEquals("CourseInStudyPlanID must be valid", exception.getMessage());
    }


    @Test
    void shouldThrowExceptionIfProgrammeEditionIDIsNullWithSecondConstructor() {
        //SUT = CourseEdition -> ProgrammeEditionID forced to be null and CourseInStudyPlanID + CourseEditionID as Doubles
        //Arrange
        CourseEditionGeneratedID courseEditionGeneratedID = mock(CourseEditionGeneratedID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        TeacherID teacherIDDouble = mock(TeacherID.class);


        //Act + Assert
        Exception exception = assertThrows(Exception.class, () -> {
            new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, null, courseEditionGeneratedID, teacherIDDouble);
        });
        assertEquals("ProgrammeEdition must be valid", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfCourseEditionGeneratedIDisNullWithSecondConstructor() {
        //SUT = CourseEdition
        //Arrange
        CourseEditionGeneratedID courseEditionGeneratedID = null;
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        TeacherID teacherIDDouble = mock(TeacherID.class);

        //Act + Assert
        Exception exception = assertThrows(Exception.class, () -> {
            new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble, courseEditionGeneratedID, teacherIDDouble);
        });
        assertEquals("CourseEditionGeneratedID must be valid", exception.getMessage());

    }

    @Test
    void shouldThrowExceptionIfTeacherIDisNullWithSecondConstructor() {
        //SUT = CourseEdition
        //Arrange
        CourseEditionGeneratedID courseEditionGeneratedID = mock(CourseEditionGeneratedID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        TeacherID teacherIDDouble = null;

        //Act + Assert
        Exception exception = assertThrows(Exception.class, () -> {
            new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble, courseEditionGeneratedID, teacherIDDouble);
        });
        assertEquals("TeacherID must be valid", exception.getMessage());

    }


    @Test
    void shouldCreateCourseEditionWithRuc() {
        // SUT = CourseEdition -> CourseEditionID, ProgrammeEditionID and CourseInStudyPlanID as Doubles
        // Arrange
        CourseEditionGeneratedID courseEditionGeneratedID = mock(CourseEditionGeneratedID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        TeacherID teacherID = mock(TeacherID.class);

        // Act
        CourseEdition courseEdition = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble, courseEditionGeneratedID, teacherID);

        // Assert
        assertNotNull(courseEdition);
        assertEquals(teacherID, courseEdition.getRuc());
    }

    @Test
    void shouldThrowExceptionIfProgrammeEditionIDAndCourseInStudyPlanIDAreNull() {
        //SUT = CourseEdition -> CourseEditionID as Double, ProgrammeEditionID and CourseInStudyPlanID forced to be null
        //Arrange
        CourseEditionGeneratedID courseEditionGeneratedID = mock(CourseEditionGeneratedID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        //Act + Assert
        assertThrows(Exception.class, () -> {
            new CourseEdition(courseEditionIDDouble, null, null, courseEditionGeneratedID);
        });

    }


    @Test
    void shouldReturnIdentityNotNull() {
        //SUT = CourseEdition -> CourseEditionID, ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseEditionGeneratedID courseEditionGeneratedID = mock(CourseEditionGeneratedID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        CourseEdition courseEdition = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble, courseEditionGeneratedID);

        //Act
        CourseEditionID courseEditionID = courseEdition.identity();

        //Assert
        assertNotNull(courseEditionID);
    }

    @Test
    void shouldReturnCourseEditionID() {
        //SUT = CourseEdition -> CourseEditionID, ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseEditionGeneratedID courseEditionGeneratedID = mock(CourseEditionGeneratedID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        CourseEdition courseEdition = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble, courseEditionGeneratedID);

        //Act
        CourseEditionID courseEditionID = courseEdition.identity();

        //Assert
        assertEquals(courseEditionID, courseEdition.identity());
    }

    @Test
    void shouldReturnNullWhenGetRucMethodIsCalled() {
        //SUT = CourseEdition -> CourseEditionID, ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseEditionGeneratedID courseEditionGeneratedID = mock(CourseEditionGeneratedID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        CourseEdition courseEdition = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble, courseEditionGeneratedID);

        //Act
        TeacherID teacherID = courseEdition.getRuc();

        //Assert
        assertNull(teacherID);
    }

    @Test
    void shouldReturnATeacherIDWhenGetRucMethodIsCalledFromACourseEditionThatHasAValidRuc() {
        //SUT = CourseEdition -> CourseEditionID, ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseEditionGeneratedID courseEditionGeneratedID = mock(CourseEditionGeneratedID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        TeacherID teacherID = mock(TeacherID.class);
        CourseEdition courseEdition = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble, courseEditionGeneratedID);
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
        CourseEditionGeneratedID courseEditionGeneratedID = mock(CourseEditionGeneratedID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);

        CourseEdition courseEdition = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble, courseEditionGeneratedID);
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
        CourseEditionGeneratedID courseEditionGeneratedID = mock(CourseEditionGeneratedID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);

        CourseEdition courseEdition = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble, courseEditionGeneratedID);
        TeacherCategory teacherCategoryDouble = mock(TeacherCategory.class);

        //Act
        boolean result = courseEdition.sameAs(teacherCategoryDouble);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfCourseInStudyPlanIDAndProgrammeEditionIDOfBothObjectsAreTheSame() {
        //SUT = CourseEdition -> ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseEditionGeneratedID courseEditionGeneratedID = mock(CourseEditionGeneratedID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);

        CourseEdition courseEdition1 = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble, courseEditionGeneratedID);
        CourseEdition courseEdition2 = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble, courseEditionGeneratedID);

        //Act
        boolean result = courseEdition1.sameAs(courseEdition2);
        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfCourseInStudyPlanIDAreNotSameButProgrammeEditionIDAreTheSame() {
        //SUT = CourseEdition -> ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseEditionGeneratedID courseEditionGeneratedID = mock(CourseEditionGeneratedID.class);
        CourseEditionGeneratedID courseEditionGeneratedID2 = mock(CourseEditionGeneratedID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseEditionID courseEditionIDDouble2 = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble1 = mock(CourseInStudyPlanID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble2 = mock(CourseInStudyPlanID.class);

        CourseEdition courseEdition1 = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble1, programmeEditionIDDouble, courseEditionGeneratedID);
        CourseEdition courseEdition2 = new CourseEdition(courseEditionIDDouble2, courseInStudyPlanIDDouble2, programmeEditionIDDouble, courseEditionGeneratedID2);

        //Act
        boolean result = courseEdition1.sameAs(courseEdition2);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfCourseInStudyPlanIDAreTheSameButProgrammeEditionIDAreNotSame() {
        //SUT = CourseEdition -> ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseEditionGeneratedID courseEditionGeneratedID = mock(CourseEditionGeneratedID.class);
        CourseEditionGeneratedID courseEditionGeneratedID2 = mock(CourseEditionGeneratedID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseEditionID courseEditionIDDouble2 = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble1 = mock(ProgrammeEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble2 = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble1 = mock(CourseInStudyPlanID.class);

        CourseEdition courseEdition1 = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble1, programmeEditionIDDouble1, courseEditionGeneratedID);
        CourseEdition courseEdition2 = new CourseEdition(courseEditionIDDouble2, courseInStudyPlanIDDouble1, programmeEditionIDDouble2, courseEditionGeneratedID2);

        //Act
        boolean result = courseEdition1.sameAs(courseEdition2);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnProgrammeEditionIDFromCourseEdition() {
        //SUT = CourseEdition -> ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseEditionGeneratedID courseEditionGeneratedID = mock(CourseEditionGeneratedID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);

        //Act
        CourseEdition courseEdition = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble, courseEditionGeneratedID);

        //Assert
        assertEquals(programmeEditionIDDouble, courseEdition.getProgrammeEditionID());
    }

    @Test
    void shouldReturnCourseEditionGeneratedIDFromCourseEdition() {
        //SUT = CourseEdition
        //Arrange
        CourseEditionGeneratedID courseEditionGeneratedID = mock(CourseEditionGeneratedID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);

        //Act
        CourseEdition courseEdition = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble, courseEditionGeneratedID);

        //Assert
        assertEquals(courseEditionGeneratedID, courseEdition.getCourseEditionGeneratedID());
    }

    @Test
    void shouldReturnCourseInbStudyPlanIDFromCourseEdition() {
        //SUT = CourseEdition -> ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseEditionGeneratedID courseEditionGeneratedID = mock(CourseEditionGeneratedID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);

        //Act
        CourseEdition courseEdition = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble, courseEditionGeneratedID);

        //Assert
        assertEquals(courseInStudyPlanIDDouble, courseEdition.getCourseInStudyPlanID());
    }

    @Test
    void shouldReturnFalseForEqualsWithNullObjectToCompare() {
        //SUT = CourseEdition -> ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseEditionGeneratedID courseEditionGeneratedID = mock(CourseEditionGeneratedID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);

        CourseEdition courseEdition = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble, courseEditionGeneratedID);

        //Act
        boolean result = courseEdition.equals(null);

        assertFalse(result);
    }

    @Test
    void shouldReturnTrueEqualsWhenComparedWithSameInstanceOfObject() {
        //SUT = CourseEdition -> ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseEditionGeneratedID courseEditionGeneratedID = mock(CourseEditionGeneratedID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);

        CourseEdition courseEdition = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble, courseEditionGeneratedID);

        //Act
        boolean result = courseEdition.equals(courseEdition);

        assertTrue(result);
    }

    @Test
    void shouldReturnTrueForEqualsOfCourseEditionsWithSameParameters() {
        //SUT = CourseEdition -> ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseEditionGeneratedID courseEditionGeneratedID = mock(CourseEditionGeneratedID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);

        CourseEdition courseEdition = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble, courseEditionGeneratedID);
        CourseEdition courseEdition2 = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble, courseEditionGeneratedID);

        boolean result = courseEdition.equals(courseEdition2);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfClassesAreDifferent() {
        //SUT = CourseEdition -> ProgrammeEditionID, CourseinStudyPlanID and TeacherCategory as Doubles
        //Arrange
        CourseEditionGeneratedID courseEditionGeneratedID = mock(CourseEditionGeneratedID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);

        CourseEdition courseEdition = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble, courseEditionGeneratedID);
        TeacherCategory teacherCategoryDouble = mock(TeacherCategory.class);

        //Act
        boolean result = courseEdition.equals(teacherCategoryDouble);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfCourseEditionIDAreTheSame() {
        //SUT = CourseEdition -> CourseEditionID, ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseEditionGeneratedID courseEditionGeneratedID = mock(CourseEditionGeneratedID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);

        CourseEdition courseEditionDouble1 = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble, courseEditionGeneratedID);
        CourseEdition courseEditionDouble2 = new CourseEdition(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble, courseEditionGeneratedID);

        //Act
        boolean result = courseEditionDouble1.equals(courseEditionDouble2);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfCourseEditionIDsAreNotTheSame() {
        //SUT = CourseEdition -> CourseEditionID, ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        CourseEditionGeneratedID courseEditionGeneratedID = mock(CourseEditionGeneratedID.class);
        CourseEditionGeneratedID courseEditionGeneratedID1 = mock(CourseEditionGeneratedID.class);
        CourseEditionID courseEditionIDDouble1 = mock(CourseEditionID.class);
        CourseEditionID courseEditionIDDouble2 = mock(CourseEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);

        CourseEdition courseEditionDouble1 = new CourseEdition(courseEditionIDDouble1, courseInStudyPlanIDDouble, programmeEditionIDDouble, courseEditionGeneratedID1);
        CourseEdition courseEditionDouble2 = new CourseEdition(courseEditionIDDouble2, courseInStudyPlanIDDouble, programmeEditionIDDouble, courseEditionGeneratedID);

        //Act
        boolean result = courseEditionDouble1.equals(courseEditionDouble2);

        //Assert
        assertFalse(result);
    }

    // US20 - Test if the RUC is correctly defined if teacher is valid
    @Test
    void shouldReturnTrueIfRucIsSet() throws Exception {

        // Arrange
        CourseEditionGeneratedID courseEditionGeneratedID = mock(CourseEditionGeneratedID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        CourseEdition courseEdition2 = new CourseEdition(courseEditionIDDouble, courseInStudyPlanID, programmeEditionID, courseEditionGeneratedID);
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
        CourseEditionGeneratedID courseEditionGeneratedID = mock(CourseEditionGeneratedID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        CourseEdition courseEdition2 = new CourseEdition(courseEditionIDDouble, courseInStudyPlanID, programmeEditionID, courseEditionGeneratedID);

        // Act
        boolean result = courseEdition2.setRuc(null);

        // Assert
        Assertions.assertFalse(result);
    }
}