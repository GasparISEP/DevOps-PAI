package PAI.domain;

import PAI.VOs.Grade;
import PAI.VOs.StudentGrade_ID;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentGradeTest {

    @Test
    void shouldCreateValidGradeStudent() throws Exception {
        // Arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);
        Grade grade = mock(Grade.class);

        // Act
        StudentGrade studentGrade1 = new StudentGrade(grade, "10-02-2025", student1, courseEdition1);
        // Assert
        assertNotNull(studentGrade1);
    }


    @Test
    void shouldNotCreateValidGradeStudentWithGradeNull() {
        // Arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        // Act + Assert

        assertThrows(Exception.class, () -> new StudentGrade(null, "10-02-2025", student1, courseEdition1));

    }

    @Test
    void shouldNotCreateValidGradeStudentWithDateInvalid()  {
        // Arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);
        Grade grade = mock(Grade.class);

        // Act + Assert

        assertThrows(Exception.class, () -> new StudentGrade(grade, "1a-02-2025", student1, courseEdition1));

    }

    @Test
    void shouldNotCreateValidGradeStudentWithDateNull() {
        // Arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);
        Grade grade = mock(Grade.class);

        // Act + Assert
        assertThrows(Exception.class, () -> new StudentGrade(grade, null, student1, courseEdition1));

    }

    @Test
    void shouldNotCreateValidGradeStudentWithStudentNull() {
        // Arrange
        CourseEdition courseEdition1 = mock(CourseEdition.class);
        Grade grade = mock(Grade.class);

        // Act + Assert

        assertThrows(Exception.class, () -> new StudentGrade(grade, "10-02-2025", null, courseEdition1));

    }

    @Test
    void shouldNotCreateValidGradeStudentWithCourseEditionNull()  {
        // Arrange
        Student student1 = mock(Student.class);
        Grade grade = mock(Grade.class);

        // Act + Assert

        assertThrows(Exception.class, () -> new StudentGrade(grade, "10-02-2025", student1, null));

    }

    @Test
    void shouldReturnCorrectGrade() throws Exception {
        // Arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);
        Grade grade = mock(Grade.class);
        when(grade.knowGrade()).thenReturn(18.0);

        // Act
        StudentGrade studentGrade = new StudentGrade(grade, "10-02-2025", student1, courseEdition1);

        // Assert
        assertEquals(18, grade.knowGrade(), 0.01);
    }

    @Test
    void shouldReturnCorrectCourseEdition() throws Exception {
        // Arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);
        Grade grade = mock(Grade.class);

        // Act
        StudentGrade studentGrade = new StudentGrade(grade, "10-02-2025", student1, courseEdition1);

        // Assert
        assertEquals(courseEdition1, studentGrade.KnowCourseEdition());
    }

    @Test
    void shouldHaveThisCourseEdition() throws Exception {
        // Arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);
        Grade grade = mock(Grade.class);

        StudentGrade studentGrade = new StudentGrade(grade, "10-02-2025", student1, courseEdition1);

        // Act
        boolean result = studentGrade.hasThisCourseEdition(courseEdition1);

        //Assert
        assertTrue(result);


    }

    @Test
    void shouldNotHaveThisCourseEdition() throws Exception {
        // Arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);
        CourseEdition courseEdition2 = mock(CourseEdition.class);
        Grade grade = mock(Grade.class);

        StudentGrade studentGrade = new StudentGrade(grade, "10-02-2025", student1, courseEdition1);

        // Act
        boolean result = studentGrade.hasThisCourseEdition(courseEdition2);

        //Assert
        assertFalse(result);
    }


    @Test
    void shouldReturnTrueIfHasThisCourseEdition() throws Exception {
        // Arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);
        Grade grade = mock(Grade.class);

        // Act
        StudentGrade studentGrade = new StudentGrade(grade, "10-02-2025", student1, courseEdition1);

        // Assert
        assertTrue(studentGrade.hasThisCourseEdition(courseEdition1));
    }

    @Test
    void shouldReturnFalseIfDoesNotHaveThisCourseEdition() throws Exception {
        // Arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);
        CourseEdition courseEdition2 = mock(CourseEdition.class);
        Grade grade = mock(Grade.class);

        // Act
        StudentGrade studentGrade = new StudentGrade(grade, "10-02-2025", student1, courseEdition1);

        // Assert
        assertFalse(studentGrade.hasThisCourseEdition(courseEdition2));
    }

    //equals

    @Test

    void shouldReturnTrueWhenFromSameLoc() throws Exception{
        //arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);
        Grade grade = mock(Grade.class);

        StudentGrade studentGrade = new StudentGrade(grade, "10-02-2025", student1, courseEdition1);
        StudentGrade studentGrade1 = studentGrade;
        //act
        boolean result = studentGrade.equals(studentGrade1);
        //assert
        assertTrue(result);
    }

    @Test

    void shouldReturnFalseWhenFromDifferentInstances() throws Exception{
        //arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);
        Teacher teacher = mock(Teacher.class);
        Grade grade = mock(Grade.class);

        StudentGrade studentGrade = new StudentGrade(grade, "10-02-2025", student1, courseEdition1);

        //act
        boolean result = studentGrade.equals(teacher);
        //assert
        assertFalse(result);
    }
    @Test

    void shouldReturnTrueWhenSameContent() throws Exception{
        //arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);
        Grade grade = mock(Grade.class);

        StudentGrade studentGrade = new StudentGrade(grade, "10-02-2025", student1, courseEdition1);
        StudentGrade studentGrade1 = new StudentGrade(grade, "10-02-2025", student1, courseEdition1);

        //act
        boolean result = studentGrade.equals(studentGrade1);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenDifferentStudent() throws Exception{
        //arrange
        Student student1 = mock(Student.class);
        Student student2 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);
        Grade grade = mock(Grade.class);

        StudentGrade studentGrade = new StudentGrade(grade, "10-02-2025", student1, courseEdition1);
        StudentGrade studentGrade1 = new StudentGrade(grade, "10-02-2025", student2, courseEdition1);

        //act
        boolean result = studentGrade.equals(studentGrade1);
        //assert
        assertFalse(result);
    }
@Test
    void shouldReturnFalseWhenStudentIsNull() throws Exception {
    //arrange
    Student student1 = mock(Student.class);
    CourseEdition courseEdition1 = mock(CourseEdition.class);
    Grade grade = mock(Grade.class);

    StudentGrade studentGrade = new StudentGrade(grade, "10-02-2025", student1, courseEdition1);

    //act
    boolean result = studentGrade.equals(null);
    //assert
    assertFalse(result);
}


    @Test
    void testGetDate() throws Exception {
        LocalDate expectedDate = LocalDate.of(2025, 3, 5);
        Student student = mock(Student.class);
        CourseEdition courseEdition = mock(CourseEdition.class);
        Grade grade = mock(Grade.class);

        StudentGrade studentGrade = new StudentGrade(grade, "05-03-2025", student, courseEdition);

        assertEquals(expectedDate, studentGrade.get_date());
    }

    @Test
    void testGetStudent() throws Exception {
        Student expectedStudent = mock(Student.class);
        CourseEdition courseEdition = mock(CourseEdition.class);
        Grade grade = mock(Grade.class);

        StudentGrade studentGrade = new StudentGrade(grade, "10-03-2025", expectedStudent, courseEdition);

        assertEquals(expectedStudent, studentGrade.get_student());
    }

    @Test
    void testGetCourseEdition() throws Exception {
        Student student = mock(Student.class);
        CourseEdition expectedCourseEdition = mock(CourseEdition.class);
        Grade grade = mock(Grade.class);

        StudentGrade studentGrade = new StudentGrade(grade, "15-03-2025", student, expectedCourseEdition);

        assertEquals(expectedCourseEdition, studentGrade.get_courseEdition());
    }

    @Test

    void shouldReturnTrueIfHasThisStudent () throws Exception{
        //arrange
        Student studentDouble = mock(Student.class);
        CourseEdition courseEdition = mock(CourseEdition.class);
        Grade grade = mock(Grade.class);
        StudentGrade studentGradeDouble1 = new StudentGrade(grade,"22-02-2022",studentDouble,courseEdition);
        //act
        boolean result = studentGradeDouble1.hasThisStudent(studentDouble);
        //assert
        assertTrue(result);
    }

    @Test

    void shouldReturnFalseIfHasNotThisStudent () throws Exception{
        //arrange
        Student studentDouble1 = mock(Student.class);
        Student studentDouble2 = mock(Student.class);
        CourseEdition courseEdition = mock(CourseEdition.class);
        Grade grade = mock(Grade.class);
        StudentGrade studentGradeDouble1 = new StudentGrade(grade,"22-02-2022",studentDouble1,courseEdition);
        //act
        boolean result = studentGradeDouble1.hasThisStudent(studentDouble2);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnGrade() throws Exception{
        //arrange
        Student studentDouble1 = mock(Student.class);
        CourseEdition courseEdition = mock(CourseEdition.class);
        Grade grade = mock(Grade.class);
        StudentGrade studentGradeDouble1 = new StudentGrade(grade,"22-02-2022",studentDouble1,courseEdition);
        //act
        Grade result = studentGradeDouble1.get_grade();
        //assert
        assertNotNull(result);
    }

    @Test
    void shouldReturnStudentGradeID() throws Exception{
        //arrange
        Student studentDouble1 = mock(Student.class);
        CourseEdition courseEdition = mock(CourseEdition.class);
        Grade grade = mock(Grade.class);
        StudentGrade studentGradeDouble1 = new StudentGrade(grade,"22-02-2022",studentDouble1,courseEdition);
        //act
        StudentGrade_ID result = studentGradeDouble1.get_studentGrade_id();
        //assert
        assertNotNull(result);
    }
}
