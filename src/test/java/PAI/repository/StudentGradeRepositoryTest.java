
package PAI.repository;

import PAI.domain.CourseEdition;
import PAI.domain.StudentGrade;
import PAI.factory.IStudentGradeFactory;
import PAI.factory.IStudentGradeListFactory;
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
        IStudentGradeFactory IStudentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeListFactory IStudentGradeListFactory = mock(IStudentGradeListFactory.class);

        List<StudentGrade> mockGradeList = spy(new ArrayList<>());

        when(IStudentGradeListFactory.newArrayList()).thenReturn(mockGradeList);

        StudentGradeRepository list = new StudentGradeRepository(IStudentGradeFactory, IStudentGradeListFactory);

        Student student1 = mock(Student.class);
        Student student2 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        StudentGrade studentGrade1 = mock(StudentGrade.class);
        StudentGrade studentGrade2 = mock(StudentGrade.class);

        when(IStudentGradeFactory.newGradeStudent(10, "10-10-2025", student1, courseEdition1))
                .thenReturn(studentGrade1);

        when(IStudentGradeFactory.newGradeStudent(8, "10-10-2025", student2, courseEdition1))
                .thenReturn(studentGrade2);

        // Act
        boolean result1 = list.addGradeToStudent(10, "10-10-2025", student1, courseEdition1);
        boolean result2 = list.addGradeToStudent(8, "10-10-2025", student2, courseEdition1);

        // Assert
        assertTrue(result1);
        assertTrue(result2);

    }

    @Test
    void shouldNotAddGradeWhenStudentHasAlreadyGradeAtCertainCourseEdition() throws Exception {
        // Arrange
        IStudentGradeFactory IStudentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeListFactory IStudentGradeListFactory = mock(IStudentGradeListFactory.class);

        List<StudentGrade> mockGradeList = spy(new ArrayList<>());

        when(IStudentGradeListFactory.newArrayList()).thenReturn(mockGradeList);

        StudentGradeRepository list = new StudentGradeRepository(IStudentGradeFactory, IStudentGradeListFactory);

        Student student1 = mock(Student.class);
        List<StudentGrade> emptyGradeList = spy(new ArrayList<>());
        when(IStudentGradeListFactory.newArrayList()).thenReturn(emptyGradeList);

        StudentGrade studentGrade1 = mock(StudentGrade.class);

        when(IStudentGradeListFactory.newArrayList()).thenReturn(emptyGradeList);

        Student student2 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);


        when(IStudentGradeFactory.newGradeStudent(10, "10-10-2025", student1, courseEdition1))
                .thenReturn(studentGrade1);

        list.addGradeToStudent(10, "10-10-2025", student1, courseEdition1);

        when(IStudentGradeFactory.newGradeStudent(10, "10-10-2025", student1, courseEdition1)).thenReturn(studentGrade1);


        // Act

        boolean result1 = list.addGradeToStudent(10, "10-10-2025", student1, courseEdition1);


        // Assert
        assertTrue(result1);

    }

    @Test
    void shouldGradeStudentsAndHave100ApprovalRate() throws Exception {

        // Arrange
        IStudentGradeFactory IStudentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeListFactory IStudentGradeListFactory = mock(IStudentGradeListFactory.class);

        List<StudentGrade> mockGradeList = spy(new ArrayList<>());

        when(IStudentGradeListFactory.newArrayList()).thenReturn(mockGradeList);

        StudentGradeRepository list = new StudentGradeRepository(IStudentGradeFactory, IStudentGradeListFactory);

        Student student1 = mock(Student.class);
        Student student2 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        StudentGrade studentGrade1 = mock(StudentGrade.class);
        StudentGrade studentGrade2 = mock(StudentGrade.class);

        when(IStudentGradeFactory.newGradeStudent(10, "10-10-2025", student1, courseEdition1)).thenReturn(studentGrade1);
        when(IStudentGradeFactory.newGradeStudent(20, "10-10-2025", student2, courseEdition1)).thenReturn(studentGrade2);

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
    void shouldGradeStudentsAndHave0ApprovalRate() throws IllegalArgumentException {

        // Arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeListFactory studentGradeListFactory = mock(IStudentGradeListFactory.class);

        List<StudentGrade> mockGradeList = spy(new ArrayList<>());

        when(studentGradeListFactory.newArrayList()).thenReturn(mockGradeList);

        StudentGradeRepository list = new StudentGradeRepository(studentGradeFactory, studentGradeListFactory);

        CourseEdition courseEdition3 = mock(CourseEdition.class);

        // act
        double approvalRate = list.knowApprovalRate(courseEdition3);

        // Assert
        assertEquals(0.0, approvalRate, 0.01);
    }

    @Test
    void shouldGetAverageGradeOfCourseEditionOf15() throws Exception {

        // Arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeListFactory studentGradeListFactory = mock(IStudentGradeListFactory.class);

        List<StudentGrade> mockGradeList = spy(new ArrayList<>());

        when(studentGradeListFactory.newArrayList()).thenReturn(mockGradeList);

        StudentGradeRepository list = new StudentGradeRepository(studentGradeFactory, studentGradeListFactory);

        Student student1 = mock(Student.class);
        Student student2 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        StudentGrade studentGrade1 = mock(StudentGrade.class);
        StudentGrade studentGrade2 = mock(StudentGrade.class);

        when(studentGradeFactory.newGradeStudent(10, "10-10-2025", student1, courseEdition1)).thenReturn(studentGrade1);
        when(studentGradeFactory.newGradeStudent(20, "10-10-2025", student2, courseEdition1)).thenReturn(studentGrade2);

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
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeListFactory studentGradeListFactory = mock(IStudentGradeListFactory.class);

        List<StudentGrade> mockGradeList = spy(new ArrayList<>());

        when(studentGradeListFactory.newArrayList()).thenReturn(mockGradeList);

        StudentGradeRepository list = new StudentGradeRepository(studentGradeFactory, studentGradeListFactory);
        Student student1 = mock(Student.class);
        Student student2 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        StudentGrade studentGrade1 = mock(StudentGrade.class);
        StudentGrade studentGrade2 = mock(StudentGrade.class);

        when(studentGradeFactory.newGradeStudent(0, "10-10-2025", student1, courseEdition1)).thenReturn(studentGrade1);
        when(studentGradeFactory.newGradeStudent(0, "10-10-2025", student2, courseEdition1)).thenReturn(studentGrade2);

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
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeListFactory studentGradeListFactory = mock(IStudentGradeListFactory.class);
        List<StudentGrade> mockGradeList = spy(new ArrayList<>());

        when(studentGradeListFactory.newArrayList()).thenReturn(mockGradeList);

        StudentGradeRepository list = new StudentGradeRepository(studentGradeFactory, studentGradeListFactory);

        CourseEdition courseEdition1 = mock(CourseEdition.class);

        // Act
        Double averageGrade = list.KnowAverageGrade(courseEdition1);

        // Assert
        assertNull(averageGrade);
    }

    @Test
    void shouldNotAddGradeToAStudentWithFactoryNull() throws IllegalArgumentException {
        // Arrange
        IStudentGradeListFactory studentGradeListFactory = mock(IStudentGradeListFactory.class);

        when(studentGradeListFactory.newArrayList()).thenReturn(new ArrayList<>());

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new StudentGradeRepository(null, studentGradeListFactory));

        assertEquals("Factory cannot be null!", exception.getMessage());
    }

    @Test
    void shouldNotAddGradeToAStudentWithListFactoryNull() throws IllegalArgumentException {
        // Arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new StudentGradeRepository(studentGradeFactory, null));

        assertEquals("Factory cannot be null!", exception.getMessage());
    }

}

