package PAI.domain;

import PAI.VOs.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentGradeTest {

    @Test
    void shouldCreateValidGradeStudent() throws Exception {
        // Arrange
        StudentID student1 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);

        // Act
        StudentGrade studentGrade1 = new StudentGrade(grade, dateDouble, student1, courseEditionID1);
        // Assert
        assertNotNull(studentGrade1);
    }


    @Test
    void shouldNotCreateValidGradeStudentWithGradeNull() {
        // Arrange
        StudentID student1 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Date dateDouble = mock(Date.class);

        // Act + Assert

        assertThrows(Exception.class, () -> new StudentGrade(null, dateDouble, student1, courseEditionID1));

    }

    @Test
    void shouldNotCreateValidGradeStudentWithDateNull() {
        // Arrange
        StudentID student1 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);

        // Act + Assert
        assertThrows(Exception.class, () -> new StudentGrade(grade, null, student1, courseEditionID1));

    }

    @Test
    void shouldNotCreateValidGradeStudentWithStudentNull() {
        // Arrange
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);

        // Act + Assert

        assertThrows(Exception.class, () -> new StudentGrade(grade, dateDouble, null, courseEditionID1));

    }

    @Test
    void shouldNotCreateValidGradeStudentWithCourseEditionNull()  {
        // Arrange
        StudentID student1 = mock(StudentID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);

        // Act + Assert

        assertThrows(Exception.class, () -> new StudentGrade(grade, dateDouble, student1, null));

    }

    @Test
    void shouldReturnCorrectGrade() throws Exception {
        // Arrange
        StudentID student1 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);
        when(grade.knowGrade()).thenReturn(18.0);

        // Act
        StudentGrade studentGrade = new StudentGrade(grade, dateDouble, student1, courseEditionID1);

        // Assert
        assertEquals(18, grade.knowGrade(), 0.01);
    }

    @Test
    void shouldReturnCorrectCourseEdition() throws Exception {
        // Arrange
        StudentID student1 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);

        // Act
        StudentGrade studentGrade = new StudentGrade(grade, dateDouble, student1, courseEditionID1);

        // Assert
        assertEquals(courseEditionID1, studentGrade.KnowCourseEdition());
    }

    @Test
    void shouldHaveThisCourseEdition() throws Exception {
        // Arrange
        StudentID student1 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);

        StudentGrade studentGrade = new StudentGrade(grade, dateDouble, student1, courseEditionID1);

        // Act
        boolean result = studentGrade.hasThisCourseEdition(courseEditionID1);

        //Assert
        assertTrue(result);


    }

    @Test
    void shouldNotHaveThisCourseEdition() throws Exception {
        // Arrange
        StudentID student1 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        CourseEditionID courseEditionID2 = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);

        StudentGrade studentGrade = new StudentGrade(grade, dateDouble, student1, courseEditionID1);

        // Act
        boolean result = studentGrade.hasThisCourseEdition(courseEditionID2);

        //Assert
        assertFalse(result);
    }


    @Test
    void shouldReturnTrueIfHasThisCourseEdition() throws Exception {
        // Arrange
        StudentID student1 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);

        // Act
        StudentGrade studentGrade = new StudentGrade(grade, dateDouble, student1, courseEditionID1);

        // Assert
        assertTrue(studentGrade.hasThisCourseEdition(courseEditionID1));
    }

    @Test
    void shouldReturnFalseIfDoesNotHaveThisCourseEdition() throws Exception {
        // Arrange
        StudentID student1 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        CourseEditionID courseEditionID2 = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);

        // Act
        StudentGrade studentGrade = new StudentGrade(grade, dateDouble, student1, courseEditionID1);

        // Assert
        assertFalse(studentGrade.hasThisCourseEdition(courseEditionID2));
    }

    //equals

    @Test

    void shouldReturnTrueWhenFromSameLoc() throws Exception{
        //arrange
        StudentID student1 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);

        StudentGrade studentGrade = new StudentGrade(grade, dateDouble, student1, courseEditionID1);
        StudentGrade studentGrade1 = studentGrade;
        //act
        boolean result = studentGrade.equals(studentGrade1);
        //assert
        assertTrue(result);
    }

    @Test

    void shouldReturnFalseWhenFromDifferentInstances() throws Exception{
        //arrange
        StudentID student1 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);;
        Teacher teacher = mock(Teacher.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);

        StudentGrade studentGrade = new StudentGrade(grade, dateDouble, student1, courseEditionID1);

        //act
        boolean result = studentGrade.equals(teacher);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenDifferentStudent() throws Exception{
        //arrange
        StudentID student1 = mock(StudentID.class);
        StudentID student2 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);

        StudentGrade studentGrade = new StudentGrade(grade, dateDouble, student1, courseEditionID1);
        StudentGrade studentGrade1 = new StudentGrade(grade, dateDouble, student2, courseEditionID1);

        //act
        boolean result = studentGrade.equals(studentGrade1);
        //assert
        assertFalse(result);
    }
@Test
    void shouldReturnFalseWhenStudentIsNull() throws Exception {
    //arrange
    StudentID student1 = mock(StudentID.class);
    CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
    Grade grade = mock(Grade.class);
    Date dateDouble = mock(Date.class);

    StudentGrade studentGrade = new StudentGrade(grade, dateDouble, student1, courseEditionID1);

    //act
    boolean result = studentGrade.equals(null);
    //assert
    assertFalse(result);
}


    @Test
    void testGetDate() throws Exception {
        StudentID student = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);

        StudentGrade studentGrade = new StudentGrade(grade, dateDouble, student, courseEditionID1);

        Date result = studentGrade.get_date();

        assertNotNull(result);
    }

    @Test
    void testGetStudent() throws Exception {
        StudentID student1 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);

        StudentGrade studentGrade = new StudentGrade(grade, dateDouble, student1, courseEditionID1);

        assertEquals(student1, studentGrade.get_student());
    }

    @Test
    void testGetCourseEdition() throws Exception {
        StudentID student1 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);

        StudentGrade studentGrade = new StudentGrade(grade, dateDouble, student1, courseEditionID1);

        assertEquals(courseEditionID1, studentGrade.get_courseEditionID());
    }

    @Test

    void shouldReturnTrueIfHasThisStudent () throws Exception{
        //arrange
        StudentID student1 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);
        StudentGrade studentGradeDouble1 = new StudentGrade(grade,dateDouble,student1,courseEditionID1);
        //act
        boolean result = studentGradeDouble1.hasThisStudent(student1);
        //assert
        assertTrue(result);
    }

    @Test

    void shouldReturnFalseIfHasNotThisStudent () throws Exception{
        //arrange
        StudentID student1 = mock(StudentID.class);
        StudentID student2 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);
        StudentGrade studentGradeDouble1 = new StudentGrade(grade,dateDouble,student1,courseEditionID1);
        //act
        boolean result = studentGradeDouble1.hasThisStudent(student2);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnGrade() throws Exception{
        //arrange
        StudentID student1 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);
        StudentGrade studentGradeDouble1 = new StudentGrade(grade,dateDouble,student1,courseEditionID1);
        //act
        Grade result = studentGradeDouble1.get_grade();
        //assert
        assertNotNull(result);
    }

    @Test
    void shouldReturnStudentGradeID() throws Exception{
        //arrange
        StudentID student1 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);
        StudentGrade studentGradeDouble1 = new StudentGrade(grade,dateDouble,student1,courseEditionID1);
        //act
        StudentGradeID result = studentGradeDouble1.identity();
        //assert
        assertNotNull(result);
    }

    @Test
    void shouldNotBeEqualWhenIdsAreDifferent() throws Exception {
        StudentID student1 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);
        StudentGrade grade1 = new StudentGrade(grade, dateDouble, student1, courseEditionID1);
        StudentGrade grade2 = new StudentGrade(grade, dateDouble, student1, courseEditionID1);

        assertNotEquals(grade1, grade2);
    }

    @Test
    void shouldBeEqualWhenStudentGradeIdsAreEqual() throws Exception {
        //arrange
        Date dateDouble = mock(Date.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        StudentGrade grade1 = new StudentGrade(mock(Grade.class), dateDouble, mock(StudentID.class), courseEditionID1);
        StudentGrade grade2 = new StudentGrade(mock(Grade.class), dateDouble, mock(StudentID.class), courseEditionID1);


        Field idField = StudentGrade.class.getDeclaredField("_studentGrade_id");
        idField.setAccessible(true);
        StudentGradeID sharedId = new StudentGradeID();

        idField.set(grade1, sharedId);
        idField.set(grade2, sharedId);

        // Act
        boolean result = grade1.equals(grade2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnStudentGradeIDWithGetter() throws Exception {
        // Arrange
        Grade grade = mock(Grade.class);
        StudentID student = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Date dateDouble = mock(Date.class);

        StudentGrade studentGrade = new StudentGrade(grade, dateDouble, student, courseEditionID1);

        // Act
        StudentGradeID id = studentGrade.identity();

        // Assert
        assertNotNull(id);
    }

    //sameAs
    @Test
    void shouldReturnFalseIfObjectsSameAreFromDifferentInstances() throws Exception{
        // Arrange
        Grade grade = mock(Grade.class);
        StudentID student = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Date dateDouble = mock(Date.class);

        StudentGrade studentGrade = new StudentGrade(grade, dateDouble, student, courseEditionID1);
        //act
        boolean result = studentGrade.sameAs(student);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfHaveSameContent() throws Exception{
        // Arrange
        Grade grade = mock(Grade.class);
        StudentID student = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Date dateDouble = mock(Date.class);
        StudentGrade studentGrade = new StudentGrade(grade, dateDouble, student, courseEditionID1);
        StudentGrade studentGrade1 = new StudentGrade(grade, dateDouble, student, courseEditionID1);
        //act
        boolean result = studentGrade.sameAs(studentGrade1);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldGetDoubleGrade() throws Exception {
        Grade grade = mock(Grade.class);
        Date date = mock(Date.class);
        StudentID studentID = mock();
        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);

        when(grade.knowGrade()).thenReturn(10.0);
        StudentGrade s1 = new StudentGrade(grade, date, studentID, courseEditionID1Double);

        double res = s1.knowGrade();

        assertEquals(res, 10.0);

    }
}
