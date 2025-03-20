package PAI.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StudentGradeTest {

    @Test
    void shouldCreateValidGradeStudent() throws Exception {
        // Arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        // Act
        StudentGrade studentGrade1 = new StudentGrade(18, "10-02-2025", student1, courseEdition1);
        // Assert
        assertNotNull(studentGrade1);
    }


    @Test
    void shouldNotCreateValidGradeStudentWithGradeInvalid() {
        // Arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        // Act + Assert

        assertThrows(Exception.class, () -> new StudentGrade(-1, "10-02-2025", student1, courseEdition1));

    }

    @Test
    void shouldNotCreateValidGradeStudentWithDateInvalid()  {
        // Arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        // Act + Assert

        assertThrows(Exception.class, () -> new StudentGrade(20, "1a-02-2025", student1, courseEdition1));

    }

    @Test
    void shouldNotCreateValidGradeStudentWithDateNull() {
        // Arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        // Act + Assert
        assertThrows(Exception.class, () -> new StudentGrade(10, null, student1, courseEdition1));

    }

    @Test
    void shouldNotCreateValidGradeStudentWithStudentNull() {
        // Arrange
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        // Act + Assert

        assertThrows(Exception.class, () -> new StudentGrade(15, "10-02-2025", null, courseEdition1));

    }

    @Test
    void shouldNotCreateValidGradeStudentWithCourseEditionNull()  {
        // Arrange
        Student student1 = mock(Student.class);

        // Act + Assert

        assertThrows(Exception.class, () -> new StudentGrade(15, "10-02-2025", student1, null));

    }

    @Test
    void shouldReturnCorrectGrade() throws Exception {
        // Arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        // Act
        StudentGrade studentGrade = new StudentGrade(18, "10-02-2025", student1, courseEdition1);

        // Assert
        assertEquals(18, studentGrade.knowGrade(), 0.01);
    }

    @Test
    void shouldReturnCorrectCourseEdition() throws Exception {
        // Arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        // Act
        StudentGrade studentGrade = new StudentGrade(18, "10-02-2025", student1, courseEdition1);

        // Assert
        assertEquals(courseEdition1, studentGrade.KnowCourseEdition());
    }

    @Test
    void shouldHaveThisCourseEdition() throws Exception {
        // Arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        StudentGrade studentGrade = new StudentGrade(18, "10-02-2025", student1, courseEdition1);

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


        StudentGrade studentGrade = new StudentGrade(18, "10-02-2025", student1, courseEdition1);

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

        // Act
        StudentGrade studentGrade = new StudentGrade(18, "10-02-2025", student1, courseEdition1);

        // Assert
        assertTrue(studentGrade.hasThisCourseEdition(courseEdition1));
    }

    @Test
    void shouldReturnFalseIfDoesNotHaveThisCourseEdition() throws Exception {
        // Arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);
        CourseEdition courseEdition2 = mock(CourseEdition.class);

        // Act
        StudentGrade studentGrade = new StudentGrade(18, "10-02-2025", student1, courseEdition1);

        // Assert
        assertFalse(studentGrade.hasThisCourseEdition(courseEdition2));
    }

    //equals

    @Test

    void shouldReturnTrueWhenFromSameLoc() throws Exception{
        //arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        StudentGrade studentGrade = new StudentGrade(18, "10-02-2025", student1, courseEdition1);
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

        StudentGrade studentGrade = new StudentGrade(18, "10-02-2025", student1, courseEdition1);

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

        StudentGrade studentGrade = new StudentGrade(18, "10-02-2025", student1, courseEdition1);
        StudentGrade studentGrade1 = new StudentGrade(18, "10-02-2025", student1, courseEdition1);

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

        StudentGrade studentGrade = new StudentGrade(18, "10-02-2025", student1, courseEdition1);
        StudentGrade studentGrade1 = new StudentGrade(18, "10-02-2025", student2, courseEdition1);

        //act
        boolean result = studentGrade.equals(studentGrade1);
        //assert
        assertFalse(result);
    }
@Test
    void shouldReturnFalseWhenStudentisNull() throws Exception {
    //arrange
    Student student1 = mock(Student.class);
    CourseEdition courseEdition1 = mock(CourseEdition.class);

    StudentGrade studentGrade = new StudentGrade(18, "10-02-2025", student1, courseEdition1);

    //act
    boolean result = studentGrade.equals(null);
    //assert
    assertFalse(result);
}

    @Test
    void testGradeBoundaries() throws Exception {
        Student student = mock(Student.class);
        CourseEdition courseEdition = mock(CourseEdition.class);

        // Testa o limite inferior (0)
        StudentGrade studentGradeMin = new StudentGrade(0.0, "10-12-2023", student, courseEdition);
        assertEquals(0.0, studentGradeMin.knowGrade());

        // Testa o limite superior (20)
        StudentGrade studentGradeMax = new StudentGrade(20.0, "10-12-2023", student, courseEdition);
        assertEquals(20.0, studentGradeMax.knowGrade());

        // Testa um valor abaixo do limite inferior (-0.1)
        Exception exceptionLow = assertThrows(IllegalArgumentException.class, () ->
                new StudentGrade(-0.1, "10-12-2023", student, courseEdition));
        assertEquals("Grade should be between 0 and 20", exceptionLow.getMessage());

        // Testa um valor acima do limite superior (20.1)
        Exception exceptionHigh = assertThrows(IllegalArgumentException.class, () ->
                new StudentGrade(20.1, "10-12-2023", student, courseEdition));
        assertEquals("Grade should be between 0 and 20", exceptionHigh.getMessage());
    }

    @Test
    void testGetDate() throws Exception {
        LocalDate expectedDate = LocalDate.of(2025, 3, 5);
        Student student = mock(Student.class);
        CourseEdition courseEdition = mock(CourseEdition.class);

        StudentGrade studentGrade = new StudentGrade(12.0, "05-03-2025", student, courseEdition);

        assertEquals(expectedDate, studentGrade.get_date());
    }

    @Test
    void testGetStudent() throws Exception {
        Student expectedStudent = mock(Student.class);
        CourseEdition courseEdition = mock(CourseEdition.class);

        StudentGrade studentGrade = new StudentGrade(14.0, "10-03-2025", expectedStudent, courseEdition);

        assertEquals(expectedStudent, studentGrade.get_student());
    }

    @Test
    void testGetCourseEdition() throws Exception {
        Student student = mock(Student.class);
        CourseEdition expectedCourseEdition = mock(CourseEdition.class);

        StudentGrade studentGrade = new StudentGrade(16.5, "15-03-2025", student, expectedCourseEdition);

        assertEquals(expectedCourseEdition, studentGrade.get_courseEdition());
    }



}
