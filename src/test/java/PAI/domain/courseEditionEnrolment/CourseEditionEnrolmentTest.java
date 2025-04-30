package PAI.domain.courseEditionEnrolment;

import PAI.VOs.CourseEditionEnrolmentID;
import PAI.VOs.CourseEditionID;
import PAI.VOs.StudentID;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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

    @Test
    void should_return_a_CourseEditionEnrolmentID(){

        // arrange
        StudentID studentIDDouble = mock(StudentID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseEditionEnrolment courseEditionEnrolment = new CourseEditionEnrolment(studentIDDouble, courseEditionIDDouble);

        // act
        CourseEditionEnrolmentID result = courseEditionEnrolment.identity();

        // assert
        assertNotNull(result);
    }

    @Test
    void should_return_true_if_are_different_CourseEditionEnrolment(){

        // arrange
        StudentID studentIDDouble = mock(StudentID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseEditionEnrolment courseEditionEnrolment = new CourseEditionEnrolment(studentIDDouble, courseEditionIDDouble);
        CourseEditionEnrolment courseEditionEnrolment1 = new CourseEditionEnrolment(studentIDDouble, courseEditionIDDouble);

        // act
        boolean result = courseEditionEnrolment.sameAs (courseEditionEnrolment1);

        // assert
        assertTrue(result);
    }

    @Test
    void should_return_false_if_CourseEditionEnrolment_is_Null(){

        // arrange
        StudentID studentIDDouble = mock(StudentID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseEditionEnrolment courseEditionEnrolment = new CourseEditionEnrolment(studentIDDouble, courseEditionIDDouble);

        // act
        boolean result = courseEditionEnrolment.sameAs (null);

        // assert
        assertFalse(result);
    }

    @Test
    void should_return_true_if_are_the_same_memory_reference (){

        // arrange
        StudentID studentIDDouble = mock(StudentID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseEditionEnrolment courseEditionEnrolment = new CourseEditionEnrolment(studentIDDouble, courseEditionIDDouble);
        CourseEditionEnrolment courseEditionEnrolment1 = courseEditionEnrolment;

        // act
        boolean result = courseEditionEnrolment.sameAs (courseEditionEnrolment1);

        // assert
        assertTrue(result);
    }

    @Test
    void should_return_true_when_same_studentId_and_CourseEditionId() {

        // arrange
        StudentID studentId1 = mock(StudentID.class);
        CourseEditionID courseEditionId1 = mock(CourseEditionID.class);

        StudentID studentId2 = studentId1;
        CourseEditionID courseEditionId2 = courseEditionId1;

        CourseEditionEnrolment enrolment1 = new CourseEditionEnrolment(studentId1, courseEditionId1);
        CourseEditionEnrolment enrolment2 = new CourseEditionEnrolment(studentId2, courseEditionId2);

        // act
        boolean result = enrolment1.sameAs(enrolment2);

        // assert
        assertTrue(result);
    }

    @Test
    void should_return_False_if_Objects_are_not_of_the_same_instance() {

        // arrange
        StudentID studentId1 = mock(StudentID.class);
        CourseEditionID courseEditionId1 = mock(CourseEditionID.class);

        Object object =mock(Object.class);

        CourseEditionEnrolment enrolment1 = new CourseEditionEnrolment(studentId1, courseEditionId1);

        // act
        boolean result = enrolment1.sameAs(object);

        // assert
        assertFalse(result);
    }

    @Test
    void should_return_false_if_are_not_the_same_object() {

        // arrange
        StudentID studentId1 = mock(StudentID.class);
        CourseEditionID courseEditionId1 = mock(CourseEditionID.class);
        StudentID studentId2 = mock(StudentID.class);
        CourseEditionID courseEditionId2 = mock(CourseEditionID.class);
        CourseEditionEnrolment courseEditionEnrolment = new CourseEditionEnrolment(studentId1, courseEditionId1);
        CourseEditionEnrolment courseEditionEnrolment1 = new CourseEditionEnrolment(studentId2, courseEditionId2);

        // act
        boolean result = courseEditionEnrolment.sameAs(courseEditionEnrolment1);

        // assert
        assertFalse(result);
    }

    @Test
    void should_return_false_if_has_not_the_same_StudentID() {

        // arrange
        StudentID studentId1 = mock(StudentID.class);
        CourseEditionID courseEditionId1 = mock(CourseEditionID.class);
        StudentID studentId2 = mock(StudentID.class);
        CourseEditionEnrolment courseEditionEnrolment = new CourseEditionEnrolment(studentId1, courseEditionId1);
        CourseEditionEnrolment courseEditionEnrolment1 = new CourseEditionEnrolment(studentId2, courseEditionId1);

        // act
        boolean result = courseEditionEnrolment.sameAs(courseEditionEnrolment1);

        // assert
        assertFalse(result);
    }

    @Test
    void should_return_false_if_has_not_the_same_CourseEdition_ID() {

        // arrange
        StudentID studentId1 = mock(StudentID.class);
        CourseEditionID courseEditionId1 = mock(CourseEditionID.class);
        CourseEditionID courseEditionId2 = mock(CourseEditionID.class);
        CourseEditionEnrolment courseEditionEnrolment = new CourseEditionEnrolment(studentId1, courseEditionId1);
        CourseEditionEnrolment courseEditionEnrolment1 = new CourseEditionEnrolment(studentId1, courseEditionId2);

        // act
        boolean result = courseEditionEnrolment.sameAs(courseEditionEnrolment1);

        // assert
        assertFalse(result);
    }

    @Test
    void should_return_false_for_different_Students_in_CourseEdition_Enrollment()  {

        // arrange
        StudentID st1 = mock(StudentID.class);
        StudentID st2 = mock(StudentID.class);
        CourseEditionID courseEditionDouble = mock(CourseEditionID.class);
        CourseEditionEnrolment enrollment1 = new CourseEditionEnrolment(st1, courseEditionDouble);

        // act
        boolean result = enrollment1.hasStudent(st2);

        // assert
        assertFalse(result);
    }

    @Test
    void should_return_false_when_Student_Id_is_null()  {

        // arrange
        StudentID st1 = mock(StudentID.class);
        CourseEditionID courseEditionDouble = mock(CourseEditionID.class);
        CourseEditionEnrolment enrollment1 = new CourseEditionEnrolment(st1, courseEditionDouble);

        // act
        boolean result = enrollment1.hasStudent(null);

        // assert
        assertFalse(result);
    }

    @Test
    void should_return_false_when_CourseEdition_Id_is_Null()  {

        // arrange
        StudentID st1 = mock(StudentID.class);
        CourseEditionID courseEditionDouble = mock(CourseEditionID.class);
        CourseEditionEnrolment enrollment = new CourseEditionEnrolment(st1, courseEditionDouble);

        // act
        boolean result = enrollment.hasCourseEdition(null);

        // assert
        assertFalse(result);
    }

    @Test
    void should_create_course_edition_enrolment_with_default_values() {

        // arrange
        StudentID studentID = mock(StudentID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        // act
        CourseEditionEnrolment enrolment = new CourseEditionEnrolment(studentID, courseEditionID);

        // assert
        assertNotNull(enrolment.getEnrolmentDate());
        assertTrue(enrolment.isEnrolmentActive());
        assertEquals(studentID, enrolment.knowStudent());
        assertEquals(courseEditionID, enrolment.knowCourseEdition());
    }

    @Test
    void should_return_correct_enrolment_date() {

        // arrange
        StudentID studentID = mock(StudentID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        LocalDate expectedDate = LocalDate.of(2024, 5, 20);

        // act
        CourseEditionEnrolment enrolment = new CourseEditionEnrolment(studentID, courseEditionID, expectedDate, true);

        // assert
        assertEquals(expectedDate, enrolment.getEnrolmentDate());
    }

}