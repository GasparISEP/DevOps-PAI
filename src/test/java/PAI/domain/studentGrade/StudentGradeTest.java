package PAI.domain.studentGrade;

import PAI.VOs.*;
import PAI.domain.teacher.Teacher;
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
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeGeneratedID studentGradeGeneratedID = mock(StudentGradeGeneratedID.class);

        // Act
        StudentGrade studentGrade1 = new StudentGrade(grade, dateDouble, student1, courseEditionID1, studentGradeID, studentGradeGeneratedID);
        // Assert
        assertNotNull(studentGrade1);
    }


    @Test
    void shouldNotCreateValidGradeStudentWithGradeNull() {
        // Arrange
        StudentID student1 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Date dateDouble = mock(Date.class);
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeGeneratedID studentGradeGeneratedID = mock(StudentGradeGeneratedID.class);

        // Act + Assert

        assertThrows(Exception.class, () -> new StudentGrade(null, dateDouble, student1, courseEditionID1,studentGradeID, studentGradeGeneratedID));

    }

    @Test
    void shouldNotCreateValidGradeStudentWithDateNull() {
        // Arrange
        StudentID student1 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeGeneratedID studentGradeGeneratedID = mock(StudentGradeGeneratedID.class);

        // Act + Assert
        assertThrows(Exception.class, () -> new StudentGrade(grade, null, student1, courseEditionID1,studentGradeID, studentGradeGeneratedID));

    }

    @Test
    void shouldNotCreateValidGradeStudentWithStudentIDNull() {
        // Arrange
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeGeneratedID studentGradeGeneratedID = mock(StudentGradeGeneratedID.class);

        // Act + Assert

        assertThrows(Exception.class, () -> new StudentGrade(grade, dateDouble, null, courseEditionID1,studentGradeID, studentGradeGeneratedID));

    }

    @Test
    void shouldNotCreateValidGradeStudentWithCourseEditionIDNull()  {
        // Arrange
        StudentID student1 = mock(StudentID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeGeneratedID studentGradeGeneratedID = mock(StudentGradeGeneratedID.class);

        // Act + Assert

        assertThrows(Exception.class, () -> new StudentGrade(grade, dateDouble, student1, null,studentGradeID, studentGradeGeneratedID));

    }
    @Test
    void shouldNotCreateValidGradeStudentWithStudentGradeIDNull()  {
        // Arrange
        StudentID student1 = mock(StudentID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        StudentGradeGeneratedID studentGradeGeneratedID = mock(StudentGradeGeneratedID.class);

        // Act + Assert

        assertThrows(Exception.class, () -> new StudentGrade(grade, dateDouble, student1, courseEditionID,null, studentGradeGeneratedID));

    }

    @Test
    void shouldNotCreateValidGradeStudentWithStudentGradeGeneratedIDNull()  {
        // Arrange
        StudentID student1 = mock(StudentID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        StudentGradeID studentGradeID = mock(StudentGradeID.class);

        // Act + Assert

        assertThrows(Exception.class, () -> new StudentGrade(grade, dateDouble, student1, courseEditionID,studentGradeID, null));

    }

    @Test
    void shouldReturnCorrectGrade() throws Exception {
        // Arrange
        StudentID student1 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeGeneratedID studentGradeGeneratedID = mock(StudentGradeGeneratedID.class);
        when(grade.knowGrade()).thenReturn(18.0);


        // Act
        StudentGrade studentGrade = new StudentGrade(grade, dateDouble, student1, courseEditionID1,studentGradeID, studentGradeGeneratedID);

        // Assert
        assertEquals(18, grade.knowGrade(), 0.01);
    }

    @Test
    void shouldReturnCorrectCourseEditionID() throws Exception {
        // Arrange
        StudentID student1 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeGeneratedID studentGradeGeneratedID = mock(StudentGradeGeneratedID.class);

        // Act
        StudentGrade studentGrade = new StudentGrade(grade, dateDouble, student1, courseEditionID1,studentGradeID, studentGradeGeneratedID);

        // Assert
        assertEquals(courseEditionID1, studentGrade.getCourseEditionID());
    }

    @Test
    void shouldHaveThisCourseEditionID() throws Exception {
        // Arrange
        StudentID student1 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeGeneratedID studentGradeGeneratedID = mock(StudentGradeGeneratedID.class);

        StudentGrade studentGrade = new StudentGrade(grade, dateDouble, student1, courseEditionID1,studentGradeID, studentGradeGeneratedID);

        // Act
        boolean result = studentGrade.hasThisCourseEditionID(courseEditionID1);

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
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeGeneratedID studentGradeGeneratedID = mock(StudentGradeGeneratedID.class);

        StudentGrade studentGrade = new StudentGrade(grade, dateDouble, student1, courseEditionID1,studentGradeID, studentGradeGeneratedID);

        // Act
        boolean result = studentGrade.hasThisCourseEditionID(courseEditionID2);

        //Assert
        assertFalse(result);
    }


    @Test
    void shouldReturnTrueIfHasThisCourseEditionID() throws Exception {
        // Arrange
        StudentID student1 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeGeneratedID studentGradeGeneratedID = mock(StudentGradeGeneratedID.class);

        // Act
        StudentGrade studentGrade = new StudentGrade(grade, dateDouble, student1, courseEditionID1,studentGradeID, studentGradeGeneratedID);

        // Assert
        assertTrue(studentGrade.hasThisCourseEditionID(courseEditionID1));
    }

    @Test
    void shouldReturnFalseIfDoesNotHaveThisCourseEdition() throws Exception {
        // Arrange
        StudentID student1 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        CourseEditionID courseEditionID2 = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeGeneratedID studentGradeGeneratedID = mock(StudentGradeGeneratedID.class);

        // Act
        StudentGrade studentGrade = new StudentGrade(grade, dateDouble, student1, courseEditionID1,studentGradeID, studentGradeGeneratedID);

        // Assert
        assertFalse(studentGrade.hasThisCourseEditionID(courseEditionID2));
    }

    //equals

    @Test

    void shouldReturnTrueWhenFromSameLoc() throws Exception{
        //arrange
        StudentID student1 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeGeneratedID studentGradeGeneratedID = mock(StudentGradeGeneratedID.class);

        StudentGrade studentGrade = new StudentGrade(grade, dateDouble, student1, courseEditionID1,studentGradeID, studentGradeGeneratedID);
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
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Teacher teacher = mock(Teacher.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeGeneratedID studentGradeGeneratedID = mock(StudentGradeGeneratedID.class);

        StudentGrade studentGrade = new StudentGrade(grade, dateDouble, student1, courseEditionID1,studentGradeID, studentGradeGeneratedID);

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
        StudentGradeID studentGradeID1 = mock(StudentGradeID.class);
        StudentGradeID studentGradeID2 = mock(StudentGradeID.class);
        StudentGradeGeneratedID studentGradeGeneratedID1 = mock(StudentGradeGeneratedID.class);
        StudentGradeGeneratedID studentGradeGeneratedID2 = mock(StudentGradeGeneratedID.class);

        StudentGrade studentGrade = new StudentGrade(grade, dateDouble, student1, courseEditionID1,studentGradeID1, studentGradeGeneratedID1);
        StudentGrade studentGrade1 = new StudentGrade(grade, dateDouble, student2, courseEditionID1,studentGradeID2, studentGradeGeneratedID2);

        //act
        boolean result = studentGrade.equals(studentGrade1);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenStudentIDIsNull() throws Exception {
        //arrange
        StudentID student1 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeGeneratedID studentGradeGeneratedID = mock(StudentGradeGeneratedID.class);

        StudentGrade studentGrade = new StudentGrade(grade, dateDouble, student1, courseEditionID1,studentGradeID,studentGradeGeneratedID);

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
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeGeneratedID studentGradeGeneratedID = mock(StudentGradeGeneratedID.class);

        StudentGrade studentGrade = new StudentGrade(grade, dateDouble, student, courseEditionID1, studentGradeID,studentGradeGeneratedID);

        Date result = studentGrade.getDate();

        assertNotNull(result);
    }

    @Test
    void testGetStudent() throws Exception {
        StudentID student1 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeGeneratedID studentGradeGeneratedID = mock(StudentGradeGeneratedID.class);

        StudentGrade studentGrade = new StudentGrade(grade, dateDouble, student1, courseEditionID1,studentGradeID,studentGradeGeneratedID);

        assertEquals(student1, studentGrade.getStudentID());
    }

    @Test
    void testGetCourseEdition() throws Exception {
        StudentID student1 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeGeneratedID studentGradeGeneratedID = mock(StudentGradeGeneratedID.class);

        StudentGrade studentGrade = new StudentGrade(grade, dateDouble, student1, courseEditionID1,studentGradeID, studentGradeGeneratedID);

        assertEquals(courseEditionID1, studentGrade.getCourseEditionID());
    }

    @Test
    void testGetStudentGradeGeneratedID() throws Exception {
        StudentID student1 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeGeneratedID studentGradeGeneratedID = mock(StudentGradeGeneratedID.class);

        StudentGrade studentGrade = new StudentGrade(grade, dateDouble, student1, courseEditionID1,studentGradeID, studentGradeGeneratedID);

        assertEquals(studentGradeGeneratedID, studentGrade.getStudentGradeGeneratedID());
    }

    @Test

    void shouldReturnTrueIfHasThisStudent () throws Exception{
        //arrange
        StudentID student1 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeGeneratedID studentGradeGeneratedID = mock(StudentGradeGeneratedID.class);

        StudentGrade studentGradeDouble1 = new StudentGrade(grade,dateDouble,student1,courseEditionID1,studentGradeID, studentGradeGeneratedID);
        //act
        boolean result = studentGradeDouble1.hasThisStudentID(student1);
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
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeGeneratedID studentGradeGeneratedID = mock(StudentGradeGeneratedID.class);

        StudentGrade studentGradeDouble1 = new StudentGrade(grade,dateDouble,student1,courseEditionID1,studentGradeID, studentGradeGeneratedID);
        //act
        boolean result = studentGradeDouble1.hasThisStudentID(student2);
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
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeGeneratedID studentGradeGeneratedID = mock(StudentGradeGeneratedID.class);

        StudentGrade studentGradeDouble1 = new StudentGrade(grade,dateDouble,student1,courseEditionID1,studentGradeID, studentGradeGeneratedID);
        //act
        Grade result = studentGradeDouble1.getGrade();
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
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeGeneratedID studentGradeGeneratedID = mock(StudentGradeGeneratedID.class);

        StudentGrade studentGradeDouble1 = new StudentGrade(grade,dateDouble,student1,courseEditionID1,studentGradeID, studentGradeGeneratedID);
        //act
        StudentGradeID result = studentGradeDouble1.identity();
        //assert
        assertNotNull(result);
    }

    @Test
    void shouldNotBeEqualWhenIdsAreDifferent() throws Exception {
        StudentID studentID1 = mock(StudentID.class);
        StudentID studentID2 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);
        StudentGradeID studentGradeID1 = mock(StudentGradeID.class);
        StudentGradeID studentGradeID2 = mock(StudentGradeID.class);
        StudentGradeGeneratedID studentGradeGeneratedID1 = mock(StudentGradeGeneratedID.class);
        StudentGradeGeneratedID studentGradeGeneratedID2 = mock(StudentGradeGeneratedID.class);


        StudentGrade grade1 = new StudentGrade(grade, dateDouble, studentID1, courseEditionID1,studentGradeID1, studentGradeGeneratedID1);
        StudentGrade grade2 = new StudentGrade(grade, dateDouble, studentID2, courseEditionID1,studentGradeID2, studentGradeGeneratedID2);

        assertNotEquals(grade1, grade2);
    }

    @Test
    void shouldBeEqualWhenStudentGradeIdsAreEqual() throws Exception {
        //arrange
        Date dateDouble = mock(Date.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeGeneratedID studentGradeGeneratedID = mock(StudentGradeGeneratedID.class);

        StudentGrade grade1 = new StudentGrade(mock(Grade.class), dateDouble, mock(StudentID.class), courseEditionID1,studentGradeID, studentGradeGeneratedID);
        StudentGrade grade2 = new StudentGrade(mock(Grade.class), dateDouble, mock(StudentID.class), courseEditionID1,studentGradeID, studentGradeGeneratedID);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        StudentID studentIDDouble = mock(StudentID.class);


        Field idField = StudentGrade.class.getDeclaredField("studentGradeID");
        idField.setAccessible(true);
        StudentGradeID sharedId = new StudentGradeID(studentIDDouble,courseEditionID1);

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
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeGeneratedID studentGradeGeneratedID = mock(StudentGradeGeneratedID.class);

        StudentGrade studentGrade = new StudentGrade(grade, dateDouble, student, courseEditionID1,studentGradeID, studentGradeGeneratedID);

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
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeGeneratedID studentGradeGeneratedID = mock(StudentGradeGeneratedID.class);

        StudentGrade studentGrade = new StudentGrade(grade, dateDouble, student, courseEditionID1,studentGradeID,studentGradeGeneratedID);
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
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeGeneratedID studentGradeGeneratedID = mock(StudentGradeGeneratedID.class);

        StudentGrade studentGrade = new StudentGrade(grade, dateDouble, student, courseEditionID1, studentGradeID, studentGradeGeneratedID);
        StudentGrade studentGrade1 = new StudentGrade(grade, dateDouble, student, courseEditionID1, studentGradeID, studentGradeGeneratedID);
        //act
        boolean result = studentGrade.sameAs(studentGrade1);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfHaveDifferentContent() throws Exception{
        // Arrange
        Grade grade = mock(Grade.class);
        StudentID student = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        CourseEditionID courseEditionID2 = mock(CourseEditionID.class);
        Date dateDouble = mock(Date.class);
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeGeneratedID studentGradeGeneratedID = mock(StudentGradeGeneratedID.class);

        StudentGrade studentGrade = new StudentGrade(grade, dateDouble, student, courseEditionID1, studentGradeID, studentGradeGeneratedID);
        StudentGrade studentGrade1 = new StudentGrade(grade, dateDouble, student, courseEditionID2, studentGradeID, studentGradeGeneratedID);
        //act
        boolean result = studentGrade.sameAs(studentGrade1);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfHaveDifferentContent2() throws Exception{
        // Arrange
        Grade grade = mock(Grade.class);
        StudentID student = mock(StudentID.class);
        StudentID student2 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        Date dateDouble = mock(Date.class);
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeGeneratedID studentGradeGeneratedID = mock(StudentGradeGeneratedID.class);

        StudentGrade studentGrade = new StudentGrade(grade, dateDouble, student, courseEditionID1, studentGradeID, studentGradeGeneratedID);
        StudentGrade studentGrade1 = new StudentGrade(grade, dateDouble, student2, courseEditionID1, studentGradeID, studentGradeGeneratedID);
        //act
        boolean result = studentGrade.sameAs(studentGrade1);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldGetDoubleGrade() throws Exception {
        Grade grade = mock(Grade.class);
        Date date = mock(Date.class);
        StudentID studentID = mock();
        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeGeneratedID studentGradeGeneratedID = mock(StudentGradeGeneratedID.class);

        when(grade.knowGrade()).thenReturn(10.0);
        StudentGrade s1 = new StudentGrade(grade, date, studentID, courseEditionID1Double, studentGradeID,studentGradeGeneratedID);

        double res = s1.knowGrade();

        assertEquals(res, 10.0);

    }
}
