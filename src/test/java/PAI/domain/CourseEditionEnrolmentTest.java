package PAI.domain;

import PAI.VOs.CourseEditionID;
import PAI.VOs.StudentID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseEditionEnrolmentTest {



    @Test
    void should_create_valid_course_edition_enrollment_instance() {
        // arrange
        StudentID studentIDDouble = mock(StudentID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);

        // act
        CourseEditionEnrolment enrollment = new CourseEditionEnrolment(studentIDDouble, courseEditionIDDouble);

        // assert
        assertNotNull(enrollment);
    }

    @Test
    void should_contain_the_correct_student() {
        // arrange
        StudentID studentIDDouble = mock(StudentID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseEditionEnrolment enrollment = new CourseEditionEnrolment(studentIDDouble, courseEditionIDDouble);

        // act + assert
        assertTrue(enrollment.hasStudent(studentIDDouble));
    }

    @Test
    void should_return_true_if_enrollment_contains_correct_course_edition() {
        // arrange
        StudentID studentIDDouble = mock(StudentID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseEditionEnrolment enrollment = new CourseEditionEnrolment(studentIDDouble, courseEditionIDDouble);

        // act + assert
        assertTrue(enrollment.hasCourseEdition(courseEditionIDDouble));
    }


    @Test
    void testCourseEditionEnrollmentWhenStudentIsNull() throws IllegalArgumentException {

        // arrange
        CourseEditionID courseEditionDouble = mock(CourseEditionID.class);

        // act & assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CourseEditionEnrolment(null, courseEditionDouble);
        });
        assertEquals("Student cannot be null!", exception.getMessage());
    }

    @Test
    void testCourseEditionEnrollmentWhenCourseEditionIsNull() throws IllegalArgumentException {

        // arrange
        StudentID studentDouble = mock(StudentID.class);

        // act & assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CourseEditionEnrolment(studentDouble, null);
        });
        assertEquals("Course edition cannot be null!", exception.getMessage());
    }

    @Test
    void shouldReturnFalseIfCourseEditionIsNull_EqualsMethod() {
        //arrange
        StudentID studentDouble = mock(StudentID.class);
        CourseEditionID courseEditionDouble = mock(CourseEditionID.class);
        CourseEditionEnrolment enrollment = new CourseEditionEnrolment(studentDouble, courseEditionDouble);

        // act + assert
        assertFalse(enrollment.equals(null));
    }

    @Test
    void shouldReturnFalseIfCourseEditionIsEqualThis_EqualsMethod() {
        //arrange
        StudentID studentDouble1 = mock(StudentID.class);
        StudentID studentDouble2= mock(StudentID.class);
        CourseEditionID courseEditionDouble1 = mock(CourseEditionID.class);
        CourseEditionID courseEditionDouble2 = mock(CourseEditionID.class);
        CourseEditionEnrolment enrollment1 = new CourseEditionEnrolment(studentDouble1, courseEditionDouble1);
        CourseEditionEnrolment enrollment2 = new CourseEditionEnrolment(studentDouble2, courseEditionDouble2);

        //act + assert
        assertFalse(enrollment1.equals(enrollment2));
    }


    @Test
    void shouldReturnCourseEditionFromEnrollment()  {
        // Arrange
        StudentID st1 = mock(StudentID.class);
        CourseEditionID courseEditionDouble = mock(CourseEditionID.class);
        CourseEditionEnrolment enrollment = new CourseEditionEnrolment(st1, courseEditionDouble);

        // Act
        boolean result = enrollment.hasCourseEdition(courseEditionDouble);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnStudentInCourseEditionEnrollment()  {
        // Arrange
        StudentID st1 = mock(StudentID.class);
        CourseEditionID courseEditionDouble = mock(CourseEditionID.class);
        CourseEditionEnrolment enrollment1 = new CourseEditionEnrolment(st1, courseEditionDouble);

        // Act
        boolean result = enrollment1.hasStudent(st1);

        // Assert
        assertTrue(result);
    }

    @Test
    void should_return_a_valid_Student() throws Exception {
        //arrange
        StudentID doubleSt1 = mock (StudentID.class);
        CourseEditionID doubleCe1 = mock(CourseEditionID.class);

        CourseEditionEnrolment cee1 = new CourseEditionEnrolment(doubleSt1, doubleCe1);

        //act
        Object returnedStudent = cee1.knowStudent();

        //assert
        assertEquals(doubleSt1, returnedStudent);

    }

    @Test
    void should_return_a_valid_course_edition() throws Exception {
        //arrange
        StudentID doubleSt1 = mock (StudentID.class);
        CourseEditionID doubleCe1 = mock(CourseEditionID.class);

        CourseEditionEnrolment cee1 = new CourseEditionEnrolment(doubleSt1, doubleCe1);

        //act
        Object returnedCourseEdition = cee1.knowCourseEdition();

        //assert
        assertEquals(doubleCe1, returnedCourseEdition);
    }

    @Test
    void shouldReturnTrueIfAllFieldsAreEqual_EqualsMethod() {

        // arrange
        StudentID studentDouble = mock(StudentID.class);
        CourseEditionID courseEditionDouble = mock(CourseEditionID.class);
        CourseEditionEnrolment enrollment1 = new CourseEditionEnrolment(studentDouble, courseEditionDouble);
        CourseEditionEnrolment enrollment2 = new CourseEditionEnrolment(studentDouble, courseEditionDouble);

        // act + assert
        assertTrue(enrollment1.equals(enrollment2));
    }
    @Test
    void shouldReturnTrueIfAllFieldsAreEqual_EqualsMethod_SameReference() {

        // arrange
        StudentID studentDouble = mock(StudentID.class);
        CourseEditionID courseEditionDouble = mock(CourseEditionID.class);
        CourseEditionEnrolment enrollment = new CourseEditionEnrolment(studentDouble, courseEditionDouble);

        // act + assert
        assertTrue(enrollment.equals(enrollment));
    }

    @Test
    void shouldReturnTrueWhenTestingHashCode () {

        // arrange
        StudentID studentDouble = mock(StudentID.class);
        CourseEditionID courseEditionDouble = mock(CourseEditionID.class);
        CourseEditionEnrolment enrollment = new CourseEditionEnrolment(studentDouble, courseEditionDouble);

        // act
        int result = enrollment.hashCode();

        // assert
        assertEquals(enrollment.hashCode(), result);
    }

    @Test
    void shouldReturnTrueWhenTwoObjectsHasTheSameHashCode () {

        // arrange
        StudentID studentDouble = mock(StudentID.class);
        CourseEditionID courseEditionDouble = mock(CourseEditionID.class);
        CourseEditionEnrolment enrollment = new CourseEditionEnrolment(studentDouble, courseEditionDouble);
        CourseEditionEnrolment enrollment1 = new CourseEditionEnrolment(studentDouble, courseEditionDouble);

        //act & assert
        assertEquals(enrollment.hashCode(), enrollment1.hashCode());
    }

    @Test
    void shouldReturnFalseWhenTwoObjectsHasDifferentHashCode () {

        // arrange
        StudentID studentDouble = mock(StudentID.class);
        StudentID studentDouble1 = mock(StudentID.class);
        CourseEditionID courseEditionDouble = mock(CourseEditionID.class);
        CourseEditionID courseEditionDouble1 = mock(CourseEditionID.class);
        CourseEditionEnrolment enrollment = new CourseEditionEnrolment(studentDouble, courseEditionDouble);
        CourseEditionEnrolment enrollment1 = new CourseEditionEnrolment(studentDouble1, courseEditionDouble1);

        // act & assert
        assertNotEquals(enrollment.hashCode(), enrollment1.hashCode());
    }

    @Test
    void newEnrollment_ShouldBeActiveByDefault() {
        // Arrange
        StudentID studentIDMock = mock(StudentID.class);
        CourseEditionID courseEditionIDMock = mock(CourseEditionID.class);

        // Act
        CourseEditionEnrolment enrollment = new CourseEditionEnrolment(studentIDMock, courseEditionIDMock);

        // Assert
        assertTrue(enrollment.isEnrolmentActive(), "New enrolment should be active by default");
    }

    @Test
    void deactivateEnrollment_ShouldSetEnrollmentToInactive() {
        // Arrange
        StudentID studentIDMock = mock(StudentID.class);
        CourseEditionID courseEditionIDMock = mock(CourseEditionID.class);
        CourseEditionEnrolment enrollment = new CourseEditionEnrolment(studentIDMock, courseEditionIDMock);

        // Act
        enrollment.deactivateEnrolment();

        // Assert
        assertFalse(enrollment.isEnrolmentActive());
    }

    @Test
    void deactivateEnrollment_ShouldRemainInactiveAfterMultipleDeactivations() {
        // Arrange
        StudentID studentIDMock = mock(StudentID.class);
        CourseEditionID courseEditionIDMock = mock(CourseEditionID.class);
        CourseEditionEnrolment enrollment = new CourseEditionEnrolment(studentIDMock, courseEditionIDMock);

        // Act
        enrollment.deactivateEnrolment();
        enrollment.deactivateEnrolment(); // Calling again to ensure no state change

        // Assert
        assertFalse(enrollment.isEnrolmentActive(), "Enrolment should remain inactive after multiple deactivations");
    }
}