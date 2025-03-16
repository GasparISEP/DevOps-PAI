package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseEditionEnrolmentTest {



    @Test
    void should_create_valid_course_edition_enrollment_instance() {
        // arrange
        Student studentDouble = mock(Student.class);
        CourseEdition courseEditionDouble = mock(CourseEdition.class);

        // act
        CourseEditionEnrolment enrollment = new CourseEditionEnrolment(studentDouble, courseEditionDouble);

        // assert
        assertNotNull(enrollment);
    }

    @Test
    void should_contain_the_correct_student() {
        // arrange
        Student studentDouble = mock(Student.class);
        CourseEdition courseEditionDouble = mock(CourseEdition.class);
        CourseEditionEnrolment enrollment = new CourseEditionEnrolment(studentDouble, courseEditionDouble);

        // act + assert
        assertTrue(enrollment.hasStudent(studentDouble));
    }

    @Test
    void should_return_true_if_enrollment_contains_correct_course_edition() {
        // arrange
        Student studentDouble = mock(Student.class);
        CourseEdition courseEditionDouble = mock(CourseEdition.class);
        CourseEditionEnrolment enrollment = new CourseEditionEnrolment(studentDouble, courseEditionDouble);

        // act + assert
        assertTrue(enrollment.hasCourseEdition(courseEditionDouble));
    }


    @Test
    void testCourseEditionEnrollmentWhenStudentIsNull() throws IllegalArgumentException {

        // arrange
        CourseEdition courseEditionDouble = mock(CourseEdition.class);

        // act & assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CourseEditionEnrolment(null, courseEditionDouble);
        });
        assertEquals("Student cannot be null!", exception.getMessage());
    }

    @Test
    void testCourseEditionEnrollmentWhenCourseEditionIsNull() throws IllegalArgumentException {

        // arrange
        Student studentDouble = mock(Student.class);

        // act & assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CourseEditionEnrolment(studentDouble, null);
        });
        assertEquals("Course edition cannot be null!", exception.getMessage());
    }

    @Test
    void shouldReturnFalseIfCourseEditionIsNull_EqualsMethod() {
        //arrange
        Student studentDouble = mock(Student.class);
        CourseEdition courseEditionDouble = mock(CourseEdition.class);
        CourseEditionEnrolment enrollment = new CourseEditionEnrolment(studentDouble, courseEditionDouble);

        // act + assert
        assertFalse(enrollment.equals(null));
    }

    @Test
    void shouldReturnFalseIfCourseEditionIsEqualThis_EqualsMethod() {
        //arrange
        Student studentDouble1 = mock(Student.class);
        Student studentDouble2= mock(Student.class);
        CourseEdition courseEditionDouble1 = mock(CourseEdition.class);
        CourseEdition courseEditionDouble2 = mock(CourseEdition.class);
        CourseEditionEnrolment enrollment1 = new CourseEditionEnrolment(studentDouble1, courseEditionDouble1);
        CourseEditionEnrolment enrollment2 = new CourseEditionEnrolment(studentDouble2, courseEditionDouble2);

        //act + assert
        assertFalse(enrollment1.equals(enrollment2));
    }


    @Test
    void shouldReturnCourseEditionFromEnrollment()  {
        // Arrange
        Student st1 = mock(Student.class);
        CourseEdition courseEditionDouble = mock(CourseEdition.class);
        CourseEditionEnrolment enrollment = new CourseEditionEnrolment(st1, courseEditionDouble);

        // Act
        boolean result = enrollment.hasCourseEdition(courseEditionDouble);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnStudentInCourseEditionEnrollment()  {
        // Arrange
        Student st1 = mock(Student.class);
        CourseEdition courseEditionDouble = mock(CourseEdition.class);
        CourseEditionEnrolment enrollment1 = new CourseEditionEnrolment(st1, courseEditionDouble);

        // Act
        boolean result = enrollment1.hasStudent(st1);

        // Assert
        assertTrue(result);
    }

    @Test
    void should_return_a_valid_Student() throws Exception {
        //arrange
        Student doubleSt1 = mock (Student.class);
        CourseEdition doubleCe1 = mock(CourseEdition.class);

        CourseEditionEnrolment cee1 = new CourseEditionEnrolment(doubleSt1, doubleCe1);

        //act
        Object returnedStudent = cee1.knowStudent();

        //assert
        assertEquals(doubleSt1, returnedStudent);

    }

    @Test
    void should_return_a_valid_course_edition() throws Exception {
        //arrange
        Student doubleSt1 = mock (Student.class);
        CourseEdition doubleCe1 = mock(CourseEdition.class);

        CourseEditionEnrolment cee1 = new CourseEditionEnrolment(doubleSt1, doubleCe1);

        //act
        Object returnedCourseEdition = cee1.knowCourseEdition();

        //assert
        assertEquals(doubleCe1, returnedCourseEdition);
    }

    @Test
    void shouldReturnTrueIfAllFieldsAreEqual_EqualsMethod() {

        // arrange
        Student studentDouble = mock(Student.class);
        CourseEdition courseEditionDouble = mock(CourseEdition.class);
        CourseEditionEnrolment enrollment1 = new CourseEditionEnrolment(studentDouble, courseEditionDouble);
        CourseEditionEnrolment enrollment2 = new CourseEditionEnrolment(studentDouble, courseEditionDouble);

        // act + assert
        assertTrue(enrollment1.equals(enrollment2));
    }
    @Test
    void shouldReturnTrueIfAllFieldsAreEqual_EqualsMethod_SameReference() {

        // arrange
        Student studentDouble = mock(Student.class);
        CourseEdition courseEditionDouble = mock(CourseEdition.class);
        CourseEditionEnrolment enrollment = new CourseEditionEnrolment(studentDouble, courseEditionDouble);

        // act + assert
        assertTrue(enrollment.equals(enrollment));
    }

    @Test
    void shouldReturnTrueWhenTestingHashCode () {

        // arrange
        Student studentDouble = mock(Student.class);
        CourseEdition courseEditionDouble = mock(CourseEdition.class);
        CourseEditionEnrolment enrollment = new CourseEditionEnrolment(studentDouble, courseEditionDouble);

        // act
        int result = enrollment.hashCode();

        // assert
        assertEquals(enrollment.hashCode(), result);
    }

    @Test
    void shouldReturnTrueWhenTwoObjectsHasTheSameHashCode () {

        // arrange
        Student studentDouble = mock(Student.class);
        CourseEdition courseEditionDouble = mock(CourseEdition.class);
        CourseEditionEnrolment enrollment = new CourseEditionEnrolment(studentDouble, courseEditionDouble);
        CourseEditionEnrolment enrollment1 = new CourseEditionEnrolment(studentDouble, courseEditionDouble);

        //act & assert
        assertEquals(enrollment.hashCode(), enrollment1.hashCode());
    }

    @Test
    void shouldReturnFalseWhenTwoObjectsHasDifferentHashCode () {

        // arrange
        Student studentDouble = mock(Student.class);
        Student studentDouble1 = mock(Student.class);
        CourseEdition courseEditionDouble = mock(CourseEdition.class);
        CourseEdition courseEditionDouble1 = mock(CourseEdition.class);
        CourseEditionEnrolment enrollment = new CourseEditionEnrolment(studentDouble, courseEditionDouble);
        CourseEditionEnrolment enrollment1 = new CourseEditionEnrolment(studentDouble1, courseEditionDouble1);

        // act & assert
        assertNotEquals(enrollment.hashCode(), enrollment1.hashCode());
    }

    @Test
    void newEnrollment_ShouldBeActive() {
        // Arrange
        Student studentMock = mock(Student.class);
        CourseEdition courseEditionMock = mock(CourseEdition.class);

        // Act
        CourseEditionEnrolment enrollment = new CourseEditionEnrolment(studentMock, courseEditionMock);

        // Assert
        assertTrue(enrollment.isEnrollmentActive());
    }

    @Test
    void deactivateEnrollment_ShouldSetEnrollmentToInactive() {
        // Arrange
        Student studentMock = mock(Student.class);
        CourseEdition courseEditionMock = mock(CourseEdition.class);
        CourseEditionEnrolment enrollment = new CourseEditionEnrolment(studentMock, courseEditionMock);

        // Act
        enrollment.deactivateEnrollment();

        // Assert
        assertFalse(enrollment.isEnrollmentActive());
    }
}