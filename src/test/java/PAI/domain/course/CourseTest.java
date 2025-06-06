package PAI.domain.course;

import PAI.VOs.*;
import PAI.domain.courseEdition.CourseEdition;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseTest {

    @Test
    void shouldCreateCourseWithCourseIDAsParameter() {
        //SUT = Course ->CourseID, Name , Acronym as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseGeneratedID courseGeneratedID = mock(CourseGeneratedID.class);

        //Act
        Course course = new Course(courseGeneratedID, courseID, name, acronym);

        //Assert
        assertNotNull(course);
    }

    @Test
    void shouldThrowExceptionIfCourseIdIsInvalid() {
        //SUT = Course -> Name , Acronym as Doubles
        //Arrange
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseGeneratedID courseGeneratedID = mock(CourseGeneratedID.class);
        //act + assert
        assertThrows(Exception.class, () -> new Course(courseGeneratedID, null, name, acronym));
    }

    @Test
    void shouldThrowExceptionIfCourseNameIsInvalid() {
        //SUT = Course -> CourseID, Acronym as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Acronym acronym = mock(Acronym.class);
        CourseGeneratedID courseGeneratedID = mock(CourseGeneratedID.class);

        //act + assert
        assertThrows(Exception.class, () -> new Course(courseGeneratedID, courseID, null, acronym));
    }

    @Test
    void shouldThrowExceptionIfCourseAcronymIsInvalid() {
        //SUT = Course -> CourseID, Name, Acronym as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        CourseGeneratedID courseGeneratedID = mock(CourseGeneratedID.class);

        //act + assert
        assertThrows(Exception.class, () -> new Course(courseGeneratedID, courseID, name, null));
    }

    @Test
    void shouldThrowExceptionIfCourseGeneratedIDIsInvalid() {
        //SUT = Course -> CourseID, Name, Acronym as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);

        //act + assert
        assertThrows(Exception.class, () -> new Course(null, courseID, name, acronym));
    }

    @Test
    void shouldReturnCorrectIdentity() {
        //SUT = Course -> CourseID, Name , Acronym as Doubles
        //Arrange
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseID courseID = mock(CourseID.class);
        CourseGeneratedID courseGeneratedID = mock(CourseGeneratedID.class);

        //Act
        Course course = new Course(courseGeneratedID, courseID, name, acronym);

        // Assert
        assertEquals(courseID, course.identity());
    }

    @Test
    void shouldReturnIdentityNotNull() {
        //SUT = Course -> CourseID, Name , Acronym as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseGeneratedID courseGeneratedID = mock(CourseGeneratedID.class);

        Course course = new Course(courseGeneratedID, courseID, name, acronym);

        //Act
        CourseID courseIdentity = course.identity();

        //Assert
        assertNotNull(courseIdentity);
    }

    @Test
    void shouldReturnCourseID() {
        //SUT = Course -> CourseID, Name , Acronym as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseGeneratedID courseGeneratedID = mock(CourseGeneratedID.class);

        Course course = new Course(courseGeneratedID, courseID, name, acronym);

        //Act
        CourseID courseIdentity = course.identity();

        //Assert
        assertEquals(courseIdentity, course.identity());
    }

    @Test
    void shouldReturnExceptionWithSpyInCourse() {
        //SUT = Course -> CourseID, Name , Acronym as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseGeneratedID courseGeneratedID = mock(CourseGeneratedID.class);

        //Act
        //Spy simulates error identity()
        Course course = spy(new Course(courseGeneratedID, courseID, name, acronym));
        doThrow(new RuntimeException("sim error")).when(course).identity();

        //Assert
        assertThrows(RuntimeException.class, course::identity);
    }

    @Test
    void shouldReturnTrueEqualsWhenComparedWithSameObject() {
        //SUT = Course -> CourseID, Name , Acronym as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseGeneratedID courseGeneratedID = mock(CourseGeneratedID.class);

        Course course = new Course(courseGeneratedID, courseID, name, acronym);

        //Act
        boolean result = course.equals(course);
        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseForEqualsWithNullObjectToCompare() {
        //SUT = Course -> CourseID, Name , Acronym as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseGeneratedID courseGeneratedID = mock(CourseGeneratedID.class);

        Course course = new Course(courseGeneratedID, courseID, name, acronym);
        //Act
        boolean result = course.equals(null);
        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueEqualsWhenComparedWithSameInstanceOfObject() {
        //SUT = Course -> CourseID, Name , Acronym as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseGeneratedID courseGeneratedID = mock(CourseGeneratedID.class);

        Course course = new Course(courseGeneratedID, courseID, name, acronym);
        Course course2 = new Course(courseGeneratedID, courseID, name, acronym);
        //Act
        boolean result = course.equals(course2);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueForEqualsOfCourseWithSameParameters() {
        //SUT = Course -> CourseID, Name , Acronym, CourseQuantityCreditsEcts and DurationCourseInCurricularYear as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseGeneratedID courseGeneratedID = mock(CourseGeneratedID.class);

        Course course = new Course(courseGeneratedID, courseID, name, acronym);
        Course course2 = new Course(courseGeneratedID, courseID, name, acronym);
        //Act
        boolean result = course.equals(course2);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfClassesAreDifferent() {
        //SUT = Course -> CourseID, Name , Acronym as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseGeneratedID courseGeneratedID = mock(CourseGeneratedID.class);

        Course course = new Course(courseGeneratedID, courseID, name, acronym);
        CourseEdition courseEdition = mock(CourseEdition.class);

        //Act
        boolean result = course.equals(courseEdition);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfCourseIDsAreNotTheSame() {
        //SUT = Course -> CourseID, Name , Acronym as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        CourseID courseID2 = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseGeneratedID courseGeneratedID = mock(CourseGeneratedID.class);

        Course course = new Course(courseGeneratedID, courseID, name, acronym);
        Course course2 = new Course(courseGeneratedID, courseID2, name, acronym);
        //Act
        boolean result = course.equals(course2);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfCourseSameAsObject() {
        //SUT = Course -> CourseID, Name , Acronym as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseGeneratedID courseGeneratedID = mock(CourseGeneratedID.class);

        Course course = new Course(courseGeneratedID, courseID, name, acronym);
        Object courseDDD2 = course;

        //Act
        boolean result = course.sameAs(courseDDD2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfObjectIsNotSameAsCourse() {
        //SUT = Course -> CourseID, Name , Acronym as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseGeneratedID courseGeneratedID = mock(CourseGeneratedID.class);

        Course course = new Course(courseGeneratedID, courseID, name, acronym);
        CourseEdition courseEdition = mock(CourseEdition.class);

        //Act
        boolean result = course.sameAs(courseEdition);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfNameAcronymOfBothObjectsAreTheSame() {
        //SUT = Course -> CourseID, Name , Acronym as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseGeneratedID courseGeneratedID = mock(CourseGeneratedID.class);

        Course course = new Course(courseGeneratedID, courseID, name, acronym);
        Course course2 = new Course(courseGeneratedID, courseID, name, acronym);

        //Act
        boolean result = course.sameAs(course2);
        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfNameAreNotSameButAcronymAreTheSame() {
        //SUT = Course -> CourseID, Name , Acronym as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Name name2 = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseGeneratedID courseGeneratedID = mock(CourseGeneratedID.class);

        Course course = new Course(courseGeneratedID, courseID, name, acronym);
        Course course2 = new Course(courseGeneratedID, courseID, name2, acronym);

        //Act
        boolean result = course.sameAs(course2);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfAcronymAreNotSameButNameAreTheSame() {
        //SUT = Course -> CourseID, Name , Acronym as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        Acronym acronym2 = mock(Acronym.class);
        CourseGeneratedID courseGeneratedID = mock(CourseGeneratedID.class);

        Course course = new Course(courseGeneratedID, courseID, name, acronym);
        Course course2 = new Course(courseGeneratedID, courseID, name, acronym2);

        //Act
        boolean result = course.sameAs(course2);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnNameFromCourse() {
        //SUT = Course -> CourseID, Name , Acronym, CourseQuantityCreditsEcts and DurationCourseInCurricularYear as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseGeneratedID courseGeneratedID = mock(CourseGeneratedID.class);

        //Act
        Course course = new Course(courseGeneratedID, courseID, name, acronym);

        //Assert
        assertEquals(name, course.getName());
    }

    @Test
    void shouldReturnAcronymFromCourse() {
        //SUT = Course -> CourseID, Name , Acronym as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseGeneratedID courseGeneratedID = mock(CourseGeneratedID.class);

        //Act
        Course course = new Course(courseGeneratedID, courseID, name, acronym);

        //Assert
        assertEquals(acronym, course.getAcronym());
    }

    @Test
    void shouldReturnCourseGeneratedIDFromCourse() {
        //SUT = Course -> CourseID, Name , Acronym as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseGeneratedID courseGeneratedID = mock(CourseGeneratedID.class);
        //Act
        Course course = new Course(courseGeneratedID, courseID, name, acronym);

        //Assert
        assertEquals(courseGeneratedID, course.getCourseGeneratedID());
    }
}