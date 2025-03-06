package PAI.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.*;

class CourseEditionEnrollmentTest {

    @Test
    void should_return_a_valid_course_edition_enrollment() throws Exception {
        //arrange
        Student studentDouble = mock(Student.class);
        CourseEdition courseEditionDouble = mock(CourseEdition.class);

        //act + assert
        CourseEditionEnrollment enrollment = new CourseEditionEnrollment(studentDouble, courseEditionDouble);
    }

    @Test
    void testCourseEditionEnrollmentWhenStudentIsNull() throws IllegalArgumentException {
        // arrange
        CourseEdition courseEditionDouble = mock(CourseEdition.class);

        // act & assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CourseEditionEnrollment(null, courseEditionDouble);
        });
        assertEquals("Student cannot be null!", exception.getMessage());
    }

    @Test
    void testCourseEditionEnrollmentWhenCourseEditionIsNull() throws IllegalArgumentException {
        // arrange
        Student studentDouble = mock(Student.class);


        // act & assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CourseEditionEnrollment(studentDouble, null);
        });
        assertEquals("Course edition cannot be null!", exception.getMessage());
    }

    @Test
    void shouldReturnFalseIfCourseEditionIsNull_EqualsMethod() {
        //arrange
        Student studentDouble = mock(Student.class);
        CourseEdition courseEditionDouble = mock(CourseEdition.class);
        CourseEditionEnrollment enrollment = new CourseEditionEnrollment(studentDouble, courseEditionDouble);

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
        CourseEditionEnrollment enrollment1 = new CourseEditionEnrollment(studentDouble1, courseEditionDouble1);
        CourseEditionEnrollment enrollment2 = new CourseEditionEnrollment(studentDouble2, courseEditionDouble2);

        //act + assert
        assertFalse(enrollment1.equals(enrollment2));
    }


    @Test
    void shouldReturnCourseEditionFromEnrollment()  {
        // Arrange
        Student st1 = mock(Student.class);
        CourseEdition courseEditionDouble = mock(CourseEdition.class);
        CourseEditionEnrollment enrollment = new CourseEditionEnrollment(st1, courseEditionDouble);

        // Act
        CourseEdition result = enrollment.findCourseEditionInEnrollment();

        // Assert
        assertEquals(courseEditionDouble, result);
    }

    @Test
    void shouldReturnStudentInCourseEditionEnrollment()  {
        // Arrange
        Student st1 = mock(Student.class);
        CourseEdition courseEditionDouble = mock(CourseEdition.class);
        CourseEditionEnrollment enrollment1 = new CourseEditionEnrollment(st1, courseEditionDouble);

        // Act
        Student result = enrollment1.findStudentInCourseEditionEnrollment();

        // Assert
        assertEquals(st1, result);
    }

    @Test
    void should_return_a_valid_Student() throws Exception {
        //arrange
        Student doubleSt1 = mock (Student.class);
        CourseEdition doubleCe1 = mock(CourseEdition.class);

        CourseEditionEnrollment cee1 = new CourseEditionEnrollment(doubleSt1, doubleCe1);

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

        CourseEditionEnrollment cee1 = new CourseEditionEnrollment(doubleSt1, doubleCe1);

        //act
        Object returnedCourseEdition = cee1.knowCourseEdition();

        //assert
        assertEquals(doubleCe1, returnedCourseEdition);
    }


    @Test
    void shouldReturnStudentInCourseEdition() {
        // Arrange
        Student st1 = mock(Student.class);
        CourseEdition courseEditionDouble = mock(CourseEdition.class);
        CourseEditionEnrollment enrollment1 = new CourseEditionEnrollment(st1, courseEditionDouble);


        // Act
        Student result = enrollment1.findStudentInCourseEditionEnrollment();

        // Assert
        assertEquals(st1, result);
    }

    @Test
    void shouldReturnTrueIfAllFieldsAreEqual_EqualsMethod() {
        // arrange
        Student studentDouble = mock(Student.class);
        CourseEdition courseEditionDouble = mock(CourseEdition.class);
        CourseEditionEnrollment enrollment1 = new CourseEditionEnrollment(studentDouble, courseEditionDouble);
        CourseEditionEnrollment enrollment2 = new CourseEditionEnrollment(studentDouble, courseEditionDouble);

        // act + assert
        assertTrue(enrollment1.equals(enrollment2));
    }
    @Test
    void shouldReturnTrueIfAllFieldsAreEqual_EqualsMethod_SameReference() {
        // arrange
        Student studentDouble = mock(Student.class);
        CourseEdition courseEditionDouble = mock(CourseEdition.class);
        CourseEditionEnrollment enrollment = new CourseEditionEnrollment(studentDouble, courseEditionDouble);
        // act + assert
        assertTrue(enrollment.equals(enrollment));
    }
}