package PAI.domain;

import org.junit.jupiter.api.Test;

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
    void shouldNotCreateValidGradeStudentWithGradeInvalid() throws Exception {
        // Arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        // Act + Assert

        assertThrows(Exception.class, () -> new GradeStudent(-1, "10-02-2025", student1, courseEdition1));

    }

    @Test
    void shouldNotCreateValidGradeStudentWithDateInvalid() throws Exception {
        // Arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        // Act + Assert

        assertThrows(Exception.class, () -> new GradeStudent(20, "1a-02-2025", student1, courseEdition1));

    }

    @Test
    void shouldNotCreateValidGradeStudentWithDateNull() throws Exception {
        // Arrange
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        // Act + Assert
        assertThrows(Exception.class, () -> new GradeStudent(10, null, student1, courseEdition1));

    }

    @Test
    void shouldNotCreateValidGradeStudentWithStudentNull() throws Exception {
        // Arrange
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        // Act + Assert

        assertThrows(Exception.class, () -> new GradeStudent(15, "10-02-2025", null, courseEdition1));

    }

    @Test
    void shouldNotCreateValidGradeStudentWithCourseEditionNull() throws Exception {
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



}
