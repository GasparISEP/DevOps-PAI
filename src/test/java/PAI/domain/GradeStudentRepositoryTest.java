
package PAI.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GradeStudentRepositoryTest {

    @Test
    void shouldAddGradeToAStudent() throws Exception {
        // Arrange
        GradeStudentFactory gradeStudentFactory = mock(GradeStudentFactory.class);
        GradeStudentRepository list = new GradeStudentRepository(gradeStudentFactory);

        Student student1 = mock(Student.class);
        Student student2 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        GradeStudent gradeStudent1 = mock(GradeStudent.class);
        GradeStudent gradeStudent2 = mock(GradeStudent.class);

        when(gradeStudentFactory.newGradeStudent(10, "10-10-2025", student1, courseEdition1))
                .thenReturn(gradeStudent1);

        when(gradeStudentFactory.newGradeStudent(8, "10-10-2025", student2, courseEdition1))
                .thenReturn(gradeStudent2);

        // Act
        Optional<GradeStudent> result1 = list.addGradeToStudent(10, "10-10-2025", student1, courseEdition1);
        Optional<GradeStudent> result2 = list.addGradeToStudent(8, "10-10-2025", student2, courseEdition1);

        // Assert
        assertTrue(result1.isPresent());
        assertTrue(result2.isPresent());
    }


    @Test
    void shouldNotGradeAStudentOnCourseEditionWithoutStudents() throws Exception {

        // Arrange
        GradeStudentFactory gradeStudentFactory = mock(GradeStudentFactory.class);
        GradeStudentRepository list = new GradeStudentRepository(gradeStudentFactory);

        CourseEdition courseEdition1 = mock(CourseEdition.class);


        // Act
        double approvalRate = list.knowApprovalRate(courseEdition1);

        // Assert
        assertEquals(0, approvalRate, 0.01);
    }

    @Test
    void shouldGradeAStudent100() throws Exception {

        // Arrange
        GradeStudentFactory gradeStudentFactory = mock(GradeStudentFactory.class);
        GradeStudentRepository list = new GradeStudentRepository(gradeStudentFactory);


        Student student1 = mock(Student.class);
        Student student2 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        GradeStudent gradeStudent1 = mock(GradeStudent.class);
        GradeStudent gradeStudent2 = mock(GradeStudent.class);

        when(gradeStudentFactory.newGradeStudent(10, "10-10-2025", student1, courseEdition1)).thenReturn(gradeStudent1);
        when(gradeStudentFactory.newGradeStudent(20, "10-10-2025", student2, courseEdition1)).thenReturn(gradeStudent2);

        when(gradeStudent1.knowGrade()).thenReturn(10.0);
        when(gradeStudent2.knowGrade()).thenReturn(20.0);

        when(gradeStudent1.hasThisCourseEdition(courseEdition1)).thenReturn(true);
        when(gradeStudent2.hasThisCourseEdition(courseEdition1)).thenReturn(true);


        list.addGradeToStudent(10, "10-10-2025", student1, courseEdition1);
        list.addGradeToStudent(20, "10-10-2025", student2, courseEdition1);

        // Act
        double approvalRate = list.knowApprovalRate(courseEdition1);

        // Assert
        assertEquals(100, approvalRate, 0.01);
    }

    @Test
    void shouldReturnEmptyWhenGradeIsInvalid() throws Exception {

        // Arrange
        GradeStudentFactory gradeStudentFactory = mock(GradeStudentFactory.class);
        GradeStudentRepository gradeStudentRepository = new GradeStudentRepository(gradeStudentFactory);

        Student validStudent = mock(Student.class);
        CourseEdition courseEditionValid = mock(CourseEdition.class);

        when(gradeStudentFactory.newGradeStudent(25, "10/10/2025", validStudent, courseEditionValid)).thenThrow(new IllegalArgumentException("Grade is Invalid!"));

        // Act
        Optional<GradeStudent> result = gradeStudentRepository.addGradeToStudent(25, "10/10/2025", validStudent, courseEditionValid);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldGetAverageGradeOfCourseEditionOf15() throws Exception {

        // Arrange
        GradeStudentFactory gradeStudentFactory = mock(GradeStudentFactory.class);
        GradeStudentRepository list = new GradeStudentRepository(gradeStudentFactory);


        Student student1 = mock(Student.class);
        Student student2 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        GradeStudent gradeStudent1 = mock(GradeStudent.class);
        GradeStudent gradeStudent2 = mock(GradeStudent.class);

        when(gradeStudentFactory.newGradeStudent(10, "10-10-2025", student1, courseEdition1)).thenReturn(gradeStudent1);
        when(gradeStudentFactory.newGradeStudent(20, "10-10-2025", student2, courseEdition1)).thenReturn(gradeStudent2);

        when(gradeStudent1.knowGrade()).thenReturn(10.0);
        when(gradeStudent2.knowGrade()).thenReturn(20.0);

        when(gradeStudent1.hasThisCourseEdition(courseEdition1)).thenReturn(true);
        when(gradeStudent2.hasThisCourseEdition(courseEdition1)).thenReturn(true);

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
        GradeStudentRepository list = new GradeStudentRepository(gradeStudentFactory);


        Student student1 = mock(Student.class);
        Student student2 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        GradeStudent gradeStudent1 = mock(GradeStudent.class);
        GradeStudent gradeStudent2 = mock(GradeStudent.class);

        when(gradeStudentFactory.newGradeStudent(0, "10-10-2025", student1, courseEdition1)).thenReturn(gradeStudent1);
        when(gradeStudentFactory.newGradeStudent(0, "10-10-2025", student2, courseEdition1)).thenReturn(gradeStudent2);

        when(gradeStudent1.knowGrade()).thenReturn(0.0);
        when(gradeStudent2.knowGrade()).thenReturn(0.0);

        when(gradeStudent1.hasThisCourseEdition(courseEdition1)).thenReturn(true);
        when(gradeStudent2.hasThisCourseEdition(courseEdition1)).thenReturn(true);

        list.addGradeToStudent(0, "10-10-2025", student1, courseEdition1);
        list.addGradeToStudent(0, "10-10-2025", student2, courseEdition1);

        // Act
        Double averageGrade = list.KnowAverageGrade(courseEdition1);

        // Assert
        // Assert
        assertEquals(0, averageGrade, 0.01);

    }

    @Test
    void shouldNotGetAverageGradeOnCourseEditionWithoutStudents() throws Exception {

        // Arrange
        GradeStudentFactory gradeStudentFactory = mock(GradeStudentFactory.class);
        GradeStudentRepository list = new GradeStudentRepository(gradeStudentFactory);

        CourseEdition courseEdition1 = mock(CourseEdition.class);

        // Act
        Double averageGrade = list.KnowAverageGrade(courseEdition1);

        // Assert
        assertNull(averageGrade);
    }


}

