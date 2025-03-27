
package PAI.factory;

import PAI.VOs.Grade;
import PAI.domain.CourseEdition;
import PAI.domain.StudentGrade;
import PAI.domain.Student;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentGradeFactoryImplTest {
    @Test
    void creatingConstrutor () throws Exception {
        // Arrange
        String date = "05-03-2025";
        Student student = mock(Student.class);
        CourseEdition courseEdition =  mock(CourseEdition.class);
        Grade grade = mock(Grade.class);

        try(MockedConstruction<StudentGrade> mockConstruction =  mockConstruction(StudentGrade.class,(mock, context) -> {
            Grade gradeAtual = (Grade) context.arguments().get(0);
            String dateAtual = (String) context.arguments().get(1);
            Student studentAtual = (Student) context.arguments().get(2);
            CourseEdition courseEditionAtual = (CourseEdition) context.arguments().get(3);

            when(gradeAtual.knowGrade()).thenReturn(11.0);
            when(mock.get_grade()).thenReturn(gradeAtual);
            when(mock.get_date()).thenReturn(LocalDate.parse(dateAtual, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            when(mock.get_student()).thenReturn(studentAtual);
            when(mock.get_courseEdition()).thenReturn(courseEditionAtual);

        } )) {

            // act
            StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl();
            StudentGrade studentGrade = factory.newGradeStudent(grade, date, student, courseEdition);

            // Assert
            assertEquals(1, mockConstruction.constructed().size());
            StudentGrade createdStudent = mockConstruction.constructed().get(0);

            assertEquals(grade, createdStudent.get_grade());
            assertEquals(date, createdStudent.get_date().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            assertEquals(student, createdStudent.get_student());
            assertEquals(courseEdition, createdStudent.get_courseEdition());

        }


    }

    @Test
    void creatingConstrutorWith0Grade () throws Exception {
        // Arrange
        Grade grade = mock(Grade.class);
        String date = "05-03-2025";
        Student student = mock(Student.class);
        CourseEdition courseEdition =  mock(CourseEdition.class);

        try(MockedConstruction<StudentGrade> mockConstruction =  mockConstruction(StudentGrade.class,(mock, context) -> {
            Grade gradeAtual = (Grade) context.arguments().get(0);
            String dateAtual = (String) context.arguments().get(1);
            Student studentAtual = (Student) context.arguments().get(2);
            CourseEdition courseEditionAtual = (CourseEdition) context.arguments().get(3);

            when(gradeAtual.knowGrade()).thenReturn(11.0);
            when(mock.get_grade()).thenReturn(gradeAtual);
            when(mock.get_date()).thenReturn(LocalDate.parse(dateAtual, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            when(mock.get_student()).thenReturn(studentAtual);
            when(mock.get_courseEdition()).thenReturn(courseEditionAtual);

        } )) {

            // act
            StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl();
            StudentGrade studentGrade = factory.newGradeStudent(grade, date, student, courseEdition);

            // Assert
            assertEquals(1, mockConstruction.constructed().size());
            StudentGrade createdStudent = mockConstruction.constructed().get(0);

            assertEquals(grade, createdStudent.get_grade());
            assertEquals(date, createdStudent.get_date().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            assertEquals(student, createdStudent.get_student());
            assertEquals(courseEdition, createdStudent.get_courseEdition());

        }
    }

    @Test
    void shouldThrowExceptionWhenGradeIsNull() {
        // Arrange
        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl();
        Student student = mock(Student.class);
        CourseEdition courseEdition = mock(CourseEdition.class);
        Grade grade = mock(Grade.class);
        String date = "05-03-2025";

        when(grade.knowGrade()).thenReturn(10.0);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                factory.newGradeStudent(null, date, student, courseEdition));
        assertEquals("Grade cannot be null.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDateIsNull() {
        // Arrange
        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl();
        Student student = mock(Student.class);
        CourseEdition courseEdition = mock(CourseEdition.class);
        Grade grade = mock(Grade.class);

        when(grade.knowGrade()).thenReturn(10.0);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                factory.newGradeStudent(grade, null, student, courseEdition));
        assertEquals("Date cannot be null or empty!", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDateIsEmpty() {
        // Arrange
        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl();
        Student student = mock(Student.class);
        CourseEdition courseEdition = mock(CourseEdition.class);
        Grade grade = mock(Grade.class);

        when(grade.knowGrade()).thenReturn(10.0);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                factory.newGradeStudent(grade, "", student, courseEdition));
        assertEquals("Date cannot be null or empty!", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDateIsBlank() {
        // Arrange
        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl();
        Student student = mock(Student.class);
        CourseEdition courseEdition = mock(CourseEdition.class);
        Grade grade = mock(Grade.class);

        when(grade.knowGrade()).thenReturn(10.0);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                factory.newGradeStudent(grade, "   ", student, courseEdition));
        assertEquals("Date cannot be null or empty!", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenStudentIsNull() {
        // Arrange
        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl();
        CourseEdition courseEdition = mock(CourseEdition.class);
        String date = "05-03-2025";
        Grade grade = mock(Grade.class);

        when(grade.knowGrade()).thenReturn(10.0);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                factory.newGradeStudent(grade, date,null, courseEdition));
        assertEquals("Student cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCourseEditionIsNull() {
        // Arrange
        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl();
        Student student = mock(Student.class);
        String date = "05-03-2025";
        Grade grade = mock(Grade.class);

        when(grade.knowGrade()).thenReturn(10.0);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                factory.newGradeStudent(grade, date,student, null));
        assertEquals("Course Edition cannot be null", exception.getMessage());
    }

}

