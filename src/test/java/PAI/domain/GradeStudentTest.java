package PAI.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class GradeStudentTest {

    @Test
    void shouldCreateValidGradeStudent() throws Exception {
        // Arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        // Act
        GradeStudent gradeStudent1 = new GradeStudent(18, "10-02-2025", student1, courseEdition1);
        // Assert
        assertNotNull(gradeStudent1);
    }


    @Test
    void shouldNotCreateValidGradeStudentWithGradeInvalid() {
        // Arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        // Act + Assert

        assertThrows(Exception.class, () -> new GradeStudent(-1, "10-02-2025", student1, courseEdition1));

    }

    @Test
    void shouldNotCreateValidGradeStudentWithDateInvalid()  {
        // Arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        // Act + Assert

        assertThrows(Exception.class, () -> new GradeStudent(20, "1a-02-2025", student1, courseEdition1));

    }

    @Test
    void shouldNotCreateValidGradeStudentWithDateNull() {
        // Arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        // Act + Assert
        assertThrows(Exception.class, () -> new GradeStudent(10, null, student1, courseEdition1));

    }

    @Test
    void shouldNotCreateValidGradeStudentWithStudentNull() {
        // Arrange
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        // Act + Assert

        assertThrows(Exception.class, () -> new GradeStudent(15, "10-02-2025", null, courseEdition1));

    }

    @Test
    void shouldNotCreateValidGradeStudentWithCourseEditionNull()  {
        // Arrange
        Student student1 = mock(Student.class);

        // Act + Assert

        assertThrows(Exception.class, () -> new GradeStudent(15, "10-02-2025", student1, null));

    }

    @Test
    void shouldReturnCorrectGrade() throws Exception {
        // Arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        // Act
        GradeStudent gradeStudent = new GradeStudent(18, "10-02-2025", student1, courseEdition1);

        // Assert
        assertEquals(18, gradeStudent.knowGrade(), 0.01);
    }

    @Test
    void shouldReturnCorrectCourseEdition() throws Exception {
        // Arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        // Act
        GradeStudent gradeStudent = new GradeStudent(18, "10-02-2025", student1, courseEdition1);

        // Assert
        assertEquals(courseEdition1, gradeStudent.KnowCourseEdition());
    }

    @Test
    void shouldHaveThisCourseEdition() throws Exception {
        // Arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        GradeStudent gradeStudent = new GradeStudent(18, "10-02-2025", student1, courseEdition1);

        // Act
        boolean result = gradeStudent.hasThisCourseEdition(courseEdition1);

        //Assert
        assertTrue(result);


    }

    @Test
    void shouldNotHaveThisCourseEdition() throws Exception {
        // Arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);
        CourseEdition courseEdition2 = mock(CourseEdition.class);


        GradeStudent gradeStudent = new GradeStudent(18, "10-02-2025", student1, courseEdition1);

        // Act
        boolean result = gradeStudent.hasThisCourseEdition(courseEdition2);

        //Assert
        assertFalse(result);
    }


    @Test
    void shouldReturnTrueIfHasThisCourseEdition() throws Exception {
        // Arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        // Act
        GradeStudent gradeStudent = new GradeStudent(18, "10-02-2025", student1, courseEdition1);

        // Assert
        assertTrue(gradeStudent.hasThisCourseEdition(courseEdition1));
    }

    @Test
    void shouldReturnFalseIfDoesNotHaveThisCourseEdition() throws Exception {
        // Arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);
        CourseEdition courseEdition2 = mock(CourseEdition.class);

        // Act
        GradeStudent gradeStudent = new GradeStudent(18, "10-02-2025", student1, courseEdition1);

        // Assert
        assertFalse(gradeStudent.hasThisCourseEdition(courseEdition2));
    }

    //equals

    @Test

    void shouldReturnTrueWhenFromSameLoc() throws Exception{
        //arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        GradeStudent gradeStudent = new GradeStudent(18, "10-02-2025", student1, courseEdition1);
        GradeStudent gradeStudent1 = gradeStudent;
        //act
        boolean result = gradeStudent.equals(gradeStudent1);
        //assert
        assertTrue(result);
    }

    @Test

    void shouldReturnFalseWhenFromDifferentInstances() throws Exception{
        //arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);
        Teacher teacher = mock(Teacher.class);

        GradeStudent gradeStudent = new GradeStudent(18, "10-02-2025", student1, courseEdition1);

        //act
        boolean result = gradeStudent.equals(teacher);
        //assert
        assertFalse(result);
    }
    @Test

    void shouldReturnTrueWhenSameContent() throws Exception{
        //arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        GradeStudent gradeStudent = new GradeStudent(18, "10-02-2025", student1, courseEdition1);
        GradeStudent gradeStudent1 = new GradeStudent(18, "10-02-2025", student1, courseEdition1);

        //act
        boolean result = gradeStudent.equals(gradeStudent1);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenDifferentStudent() throws Exception{
        //arrange
        Student student1 = mock(Student.class);
        Student student2 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        GradeStudent gradeStudent = new GradeStudent(18, "10-02-2025", student1, courseEdition1);
        GradeStudent gradeStudent1 = new GradeStudent(18, "10-02-2025", student2, courseEdition1);

        //act
        boolean result = gradeStudent.equals(gradeStudent1);
        //assert
        assertFalse(result);
    }
@Test
    void shouldReturnFalseWhenStudentisNull() throws Exception {
    //arrange
    Student student1 = mock(Student.class);
    CourseEdition courseEdition1 = mock(CourseEdition.class);

    GradeStudent gradeStudent = new GradeStudent(18, "10-02-2025", student1, courseEdition1);

    //act
    boolean result = gradeStudent.equals(null);
    //assert
    assertFalse(result);
}

    @Test
    void testGradeBoundaries() throws Exception {
        Student student = mock(Student.class);
        CourseEdition courseEdition = mock(CourseEdition.class);

        // Testa o limite inferior (0)
        GradeStudent gradeStudentMin = new GradeStudent(0.0, "10-12-2023", student, courseEdition);
        assertEquals(0.0, gradeStudentMin.knowGrade());

        // Testa o limite superior (20)
        GradeStudent gradeStudentMax = new GradeStudent(20.0, "10-12-2023", student, courseEdition);
        assertEquals(20.0, gradeStudentMax.knowGrade());

        // Testa um valor abaixo do limite inferior (-0.1)
        Exception exceptionLow = assertThrows(IllegalArgumentException.class, () ->
                new GradeStudent(-0.1, "10-12-2023", student, courseEdition));
        assertEquals("Grade should be between 0 and 20", exceptionLow.getMessage());

        // Testa um valor acima do limite superior (20.1)
        Exception exceptionHigh = assertThrows(IllegalArgumentException.class, () ->
                new GradeStudent(20.1, "10-12-2023", student, courseEdition));
        assertEquals("Grade should be between 0 and 20", exceptionHigh.getMessage());
    }

    @Test
    void testGetDate() throws Exception {
        LocalDate expectedDate = LocalDate.of(2025, 3, 5);
        Student student = mock(Student.class);
        CourseEdition courseEdition = mock(CourseEdition.class);

        GradeStudent gradeStudent = new GradeStudent(12.0, "05-03-2025", student, courseEdition);

        assertEquals(expectedDate, gradeStudent.get_date());
    }

    @Test
    void testGetStudent() throws Exception {
        Student expectedStudent = mock(Student.class);
        CourseEdition courseEdition = mock(CourseEdition.class);

        GradeStudent gradeStudent = new GradeStudent(14.0, "10-03-2025", expectedStudent, courseEdition);

        assertEquals(expectedStudent, gradeStudent.get_student());
    }

    @Test
    void testGetCourseEdition() throws Exception {
        Student student = mock(Student.class);
        CourseEdition expectedCourseEdition = mock(CourseEdition.class);

        GradeStudent gradeStudent = new GradeStudent(16.5, "15-03-2025", student, expectedCourseEdition);

        assertEquals(expectedCourseEdition, gradeStudent.get_courseEdition());
    }



}
