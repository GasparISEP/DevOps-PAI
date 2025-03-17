
package PAI.repository;

import PAI.domain.CourseEdition;
import PAI.domain.StudentGrade;
import PAI.factory.GradeStudentFactory;
import PAI.factory.GradeStudentListFactory;
import PAI.domain.Student;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentGradeRepositoryTest {

    @Test
    void shouldAddGradeToAStudent() throws Exception {
        // Arrange
        GradeStudentFactory gradeStudentFactory = mock(GradeStudentFactory.class);
        GradeStudentListFactory gradeStudentListFactory = mock(GradeStudentListFactory.class);

        List<StudentGrade> mockGradeList = spy(new ArrayList<>());

        when(gradeStudentListFactory.newArrayList()).thenReturn(mockGradeList);

        GradeStudentRepository list = new GradeStudentRepository(gradeStudentFactory, gradeStudentListFactory);

        Student student1 = mock(Student.class);
        Student student2 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        StudentGrade studentGrade1 = mock(StudentGrade.class);
        StudentGrade studentGrade2 = mock(StudentGrade.class);

        when(gradeStudentFactory.newGradeStudent(10, "10-10-2025", student1, courseEdition1))
                .thenReturn(studentGrade1);

        when(gradeStudentFactory.newGradeStudent(8, "10-10-2025", student2, courseEdition1))
                .thenReturn(studentGrade2);

        // Act
        Optional<StudentGrade> result1 = list.addGradeToStudent(10, "10-10-2025", student1, courseEdition1);
        Optional<StudentGrade> result2 = list.addGradeToStudent(8, "10-10-2025", student2, courseEdition1);

        // Assert
        assertTrue(result1.isPresent());
        assertTrue(result2.isPresent());

    }

    @Test
    void shouldNotGradeAStudentOnCourseEditionWithoutStudents() throws Exception {
        // Arrange
        GradeStudentFactory gradeStudentFactory = mock(GradeStudentFactory.class);
        GradeStudentListFactory gradeStudentListFactory = mock(GradeStudentListFactory.class);

        Student student1 = mock(Student.class);
        List<StudentGrade> emptyGradeList = spy(new ArrayList<>());
        when(gradeStudentListFactory.newArrayList()).thenReturn(emptyGradeList);

        StudentGrade studentGrade1 = mock(StudentGrade.class);

        when(gradeStudentListFactory.newArrayList()).thenReturn(emptyGradeList);

        GradeStudentRepository list = new GradeStudentRepository(gradeStudentFactory, gradeStudentListFactory);
        CourseEdition courseEdition1 = mock(CourseEdition.class);
        CourseEdition courseEdition2 = mock(CourseEdition.class);

        when(gradeStudentFactory.newGradeStudent(10, "10-10-2025", student1, courseEdition1)).thenReturn(studentGrade1);

        // Act
        Optional<StudentGrade> result1 = list.addGradeToStudent(10, "10-10-2025", student1, courseEdition2);

        // Assert
        assertFalse(result1.isPresent());
    }


    @Test
    void shouldGradeAStudent100() throws Exception {

        // Arrange
        GradeStudentFactory gradeStudentFactory = mock(GradeStudentFactory.class);
        GradeStudentListFactory gradeStudentListFactory = mock(GradeStudentListFactory.class);

        List<StudentGrade> mockGradeList = spy(new ArrayList<>());

        when(gradeStudentListFactory.newArrayList()).thenReturn(mockGradeList);

        GradeStudentRepository list = new GradeStudentRepository(gradeStudentFactory, gradeStudentListFactory);

        Student student1 = mock(Student.class);
        Student student2 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        StudentGrade studentGrade1 = mock(StudentGrade.class);
        StudentGrade studentGrade2 = mock(StudentGrade.class);

        when(gradeStudentFactory.newGradeStudent(10, "10-10-2025", student1, courseEdition1)).thenReturn(studentGrade1);
        when(gradeStudentFactory.newGradeStudent(20, "10-10-2025", student2, courseEdition1)).thenReturn(studentGrade2);

        when(studentGrade1.knowGrade()).thenReturn(10.0);
        when(studentGrade2.knowGrade()).thenReturn(20.0);

        when(studentGrade1.hasThisCourseEdition(courseEdition1)).thenReturn(true);
        when(studentGrade2.hasThisCourseEdition(courseEdition1)).thenReturn(true);
        list.addGradeToStudent(10, "10-10-2025", student1, courseEdition1);
        list.addGradeToStudent(20, "10-10-2025", student2, courseEdition1);

        // act
        double approvalRate = list.knowApprovalRate(courseEdition1);

        // Assert
        assertEquals(100.0, approvalRate, 0.01);
    }

    @Test
    void shouldReturnEmptyWhenGradeIsInvalid() throws Exception {

        // Arrange
        GradeStudentFactory gradeStudentFactory = mock(GradeStudentFactory.class);
        GradeStudentListFactory gradeStudentListFactory = mock(GradeStudentListFactory.class);

        List<StudentGrade> mockGradeList = spy(new ArrayList<>());

        when(gradeStudentListFactory.newArrayList()).thenReturn(mockGradeList);

        GradeStudentRepository list = new GradeStudentRepository(gradeStudentFactory, gradeStudentListFactory);

        Student validStudent = mock(Student.class);
        CourseEdition courseEditionValid = mock(CourseEdition.class);

        when(gradeStudentFactory.newGradeStudent(25, "10/10/2025", validStudent, courseEditionValid)).thenThrow(new IllegalArgumentException("Grade is Invalid!"));

        // Act
        Optional<StudentGrade> result = list.addGradeToStudent(25, "10/10/2025", validStudent, courseEditionValid);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldGradeAStudent0() throws IllegalArgumentException {

        // Arrange
        GradeStudentFactory gradeStudentFactory = mock(GradeStudentFactory.class);
        GradeStudentListFactory gradeStudentListFactory = mock(GradeStudentListFactory.class);

        List<StudentGrade> mockGradeList = spy(new ArrayList<>());

        when(gradeStudentListFactory.newArrayList()).thenReturn(mockGradeList);

        GradeStudentRepository list = new GradeStudentRepository(gradeStudentFactory, gradeStudentListFactory);

        CourseEdition courseEdition3 = mock(CourseEdition.class);

        // act
        double approvalRate = list.knowApprovalRate(courseEdition3);

        // Assert
        assertEquals(0.0, approvalRate, 0.01);
    }

    @Test
    void shouldGetAverageGradeOfCourseEditionOf15() throws Exception {

        // Arrange
        GradeStudentFactory gradeStudentFactory = mock(GradeStudentFactory.class);
        GradeStudentListFactory gradeStudentListFactory = mock(GradeStudentListFactory.class);

        List<StudentGrade> mockGradeList = spy(new ArrayList<>());

        when(gradeStudentListFactory.newArrayList()).thenReturn(mockGradeList);

        GradeStudentRepository list = new GradeStudentRepository(gradeStudentFactory, gradeStudentListFactory);

        Student student1 = mock(Student.class);
        Student student2 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        StudentGrade studentGrade1 = mock(StudentGrade.class);
        StudentGrade studentGrade2 = mock(StudentGrade.class);

        when(gradeStudentFactory.newGradeStudent(10, "10-10-2025", student1, courseEdition1)).thenReturn(studentGrade1);
        when(gradeStudentFactory.newGradeStudent(20, "10-10-2025", student2, courseEdition1)).thenReturn(studentGrade2);

        when(studentGrade1.knowGrade()).thenReturn(10.0);
        when(studentGrade2.knowGrade()).thenReturn(20.0);

        when(studentGrade1.hasThisCourseEdition(courseEdition1)).thenReturn(true);
        when(studentGrade2.hasThisCourseEdition(courseEdition1)).thenReturn(true);

        list.addGradeToStudent(10, "10-10-2025", student1, courseEdition1);
        list.addGradeToStudent(20, "10-10-2025", student2, courseEdition1);

        // Act
        Double averageGrade = list.KnowAverageGrade(courseEdition1);

        // Assert
        assertEquals(15, averageGrade, 0.01);
    }

    @Test
    void shouldGetAverageGradeOf0() throws Exception {

        // Arrange
        GradeStudentFactory gradeStudentFactory = mock(GradeStudentFactory.class);
        GradeStudentListFactory gradeStudentListFactory = mock(GradeStudentListFactory.class);

        List<StudentGrade> mockGradeList = spy(new ArrayList<>());

        when(gradeStudentListFactory.newArrayList()).thenReturn(mockGradeList);

        GradeStudentRepository list = new GradeStudentRepository(gradeStudentFactory, gradeStudentListFactory);
        Student student1 = mock(Student.class);
        Student student2 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        StudentGrade studentGrade1 = mock(StudentGrade.class);
        StudentGrade studentGrade2 = mock(StudentGrade.class);

        when(gradeStudentFactory.newGradeStudent(0, "10-10-2025", student1, courseEdition1)).thenReturn(studentGrade1);
        when(gradeStudentFactory.newGradeStudent(0, "10-10-2025", student2, courseEdition1)).thenReturn(studentGrade2);

        when(studentGrade1.knowGrade()).thenReturn(0.0);
        when(studentGrade2.knowGrade()).thenReturn(0.0);

        when(studentGrade1.hasThisCourseEdition(courseEdition1)).thenReturn(true);
        when(studentGrade2.hasThisCourseEdition(courseEdition1)).thenReturn(true);

        list.addGradeToStudent(0, "10-10-2025", student1, courseEdition1);
        list.addGradeToStudent(0, "10-10-2025", student2, courseEdition1);

        // Act
        Double averageGrade = list.KnowAverageGrade(courseEdition1);

        // Assert
        assertEquals(0, averageGrade, 0.01);

    }

    @Test
    void shouldNotGetAverageGradeOnCourseEditionWithoutStudents() throws IllegalArgumentException {

        // Arrange
        GradeStudentFactory gradeStudentFactory = mock(GradeStudentFactory.class);
        GradeStudentListFactory gradeStudentListFactory = mock(GradeStudentListFactory.class);

        List<StudentGrade> mockGradeList = spy(new ArrayList<>());

        when(gradeStudentListFactory.newArrayList()).thenReturn(mockGradeList);

        GradeStudentRepository list = new GradeStudentRepository(gradeStudentFactory, gradeStudentListFactory);

        CourseEdition courseEdition1 = mock(CourseEdition.class);

        // Act
        Double averageGrade = list.KnowAverageGrade(courseEdition1);

        // Assert
        assertNull(averageGrade);
    }

    @Test
    void shouldNotAddGradeToAStudentWithFactoryNull() throws IllegalArgumentException {
        // Arrange
        GradeStudentListFactory gradeStudentListFactory = mock(GradeStudentListFactory.class);
        when(gradeStudentListFactory.newArrayList()).thenReturn(new ArrayList<>());

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new GradeStudentRepository(null, gradeStudentListFactory));

        assertEquals("Factory cannot be null!", exception.getMessage());
    }

    @Test
    void shouldNotAddGradeToAStudentWithListFactoryNull() throws IllegalArgumentException {
        // Arrange
        GradeStudentFactory gradeStudentFactory = mock(GradeStudentFactory.class);


        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new GradeStudentRepository(gradeStudentFactory, null));

        assertEquals("Factory cannot be null!", exception.getMessage());
    }

}

