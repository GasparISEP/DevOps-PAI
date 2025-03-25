package PAI.factory;

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
        double grade = 11.0;
        String date = "05-03-2025";
        Student student = mock(Student.class);
        CourseEdition courseEdition =  mock(CourseEdition.class);

        try(MockedConstruction<StudentGrade> mockConstruction =  mockConstruction(StudentGrade.class,(mock, context) -> {
            double gradeAtual = (Double) context.arguments().get(0);
            String dateAtual = (String) context.arguments().get(1);
            Student studentAtual = (Student) context.arguments().get(2);
            CourseEdition courseEditionAtual = (CourseEdition) context.arguments().get(3);

            when(mock.knowGrade()).thenReturn(gradeAtual);
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

            assertEquals(grade, createdStudent.knowGrade());
            assertEquals(date, createdStudent.get_date().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            assertEquals(student, createdStudent.get_student());
            assertEquals(courseEdition, createdStudent.get_courseEdition());

        }


    }

    @Test
    void creatingConstrutorWith0Grade () throws Exception {
        // Arrange
        double grade = 0.0;
        String date = "05-03-2025";
        Student student = mock(Student.class);
        CourseEdition courseEdition =  mock(CourseEdition.class);

        try(MockedConstruction<StudentGrade> mockConstruction =  mockConstruction(StudentGrade.class,(mock, context) -> {
            double gradeAtual = (Double) context.arguments().get(0);
            String dateAtual = (String) context.arguments().get(1);
            Student studentAtual = (Student) context.arguments().get(2);
            CourseEdition courseEditionAtual = (CourseEdition) context.arguments().get(3);

            when(mock.knowGrade()).thenReturn(gradeAtual);
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

            assertEquals(grade, createdStudent.knowGrade());
            assertEquals(date, createdStudent.get_date().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            assertEquals(student, createdStudent.get_student());
            assertEquals(courseEdition, createdStudent.get_courseEdition());

        }
    }

    @Test
    void shouldThrowExceptionWhenGradeIsLessThanZero() {
        // Arrange
        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl();
        Student student = mock(Student.class);
        CourseEdition courseEdition = mock(CourseEdition.class);
        String date = "05-03-2025";

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                factory.newGradeStudent(-1, date, student, courseEdition));
        assertEquals("Grade cannot be less than 0 or higher than 20.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenGradeIsMoreThanTwenty() {
        // Arrange
        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl();
        Student student = mock(Student.class);
        CourseEdition courseEdition = mock(CourseEdition.class);
        String date = "05-03-2025";

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                factory.newGradeStudent(21, date, student, courseEdition));
        assertEquals("Grade cannot be less than 0 or higher than 20.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDateIsNull() {
        // Arrange
        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl();
        Student student = mock(Student.class);
        CourseEdition courseEdition = mock(CourseEdition.class);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                factory.newGradeStudent(10, null, student, courseEdition));
        assertEquals("Date cannot be null or empty!", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDateIsEmpty() {
        // Arrange
        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl();
        Student student = mock(Student.class);
        CourseEdition courseEdition = mock(CourseEdition.class);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                factory.newGradeStudent(10, "", student, courseEdition));
        assertEquals("Date cannot be null or empty!", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDateIsBlank() {
        // Arrange
        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl();
        Student student = mock(Student.class);
        CourseEdition courseEdition = mock(CourseEdition.class);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                factory.newGradeStudent(10, "   ", student, courseEdition));
        assertEquals("Date cannot be null or empty!", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenStudentIsNull() {
        // Arrange
        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl();
        CourseEdition courseEdition = mock(CourseEdition.class);
        String date = "05-03-2025";

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                factory.newGradeStudent(10, date,null, courseEdition));
        assertEquals("Student cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCourseEditionIsNull() {
        // Arrange
        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl();
        Student student = mock(Student.class);
        String date = "05-03-2025";

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                factory.newGradeStudent(10, date,student, null));
        assertEquals("Course Edition cannot be null", exception.getMessage());
    }

}