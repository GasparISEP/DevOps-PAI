
package PAI.factory;

import PAI.VOs.CourseEditionID;
import PAI.VOs.Date;
import PAI.VOs.Grade;
import PAI.VOs.StudentID;
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
        StudentID student = mock(StudentID.class);
        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);

        try(MockedConstruction<StudentGrade> mockConstruction =  mockConstruction(StudentGrade.class,(mock, context) -> {
            Grade gradeAtual = (Grade) context.arguments().get(0);
            Date dateAtual = (Date) context.arguments().get(1);
            StudentID studentAtual = (StudentID) context.arguments().get(2);
            CourseEditionID courseEditionIDAtual = (CourseEditionID) context.arguments().get(3);

            when(gradeAtual.knowGrade()).thenReturn(11.0);
            when(mock.get_grade()).thenReturn(gradeAtual);
            when(mock.get_date()).thenReturn(dateDouble);
            when(mock.get_student()).thenReturn(studentAtual);
            when(mock.get_courseEditionID()).thenReturn(courseEditionIDAtual);

        } )) {

            // act
            StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl();
            StudentGrade studentGrade = factory.newGradeStudent(grade, dateDouble, student, courseEditionID1Double);

            // Assert
            assertEquals(1, mockConstruction.constructed().size());
            StudentGrade createdStudent = mockConstruction.constructed().get(0);

            assertEquals(grade, createdStudent.get_grade());
            assertEquals(dateDouble, createdStudent.get_date());
            assertEquals(student, createdStudent.get_student());
            assertEquals(courseEditionID1Double, createdStudent.get_courseEditionID());

        }


    }

    @Test
    void creatingConstrutorWith0Grade () throws Exception {
        // Arrange
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);
        StudentID student = mock(StudentID.class);
        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);

        try(MockedConstruction<StudentGrade> mockConstruction =  mockConstruction(StudentGrade.class,(mock, context) -> {
            Grade gradeAtual = (Grade) context.arguments().get(0);
            Date dateAtual = (Date) context.arguments().get(1);
            StudentID studentAtual = (StudentID) context.arguments().get(2);
            CourseEditionID courseEditionAtualID = (CourseEditionID) context.arguments().get(3);

            when(gradeAtual.knowGrade()).thenReturn(11.0);
            when(mock.get_grade()).thenReturn(gradeAtual);
            when(mock.get_date()).thenReturn(dateAtual);
            when(mock.get_student()).thenReturn(studentAtual);
            when(mock.get_courseEditionID()).thenReturn(courseEditionAtualID);

        } )) {

            // act
            StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl();
            StudentGrade studentGrade = factory.newGradeStudent(grade, dateDouble, student, courseEditionID1Double);

            // Assert
            assertEquals(1, mockConstruction.constructed().size());
            StudentGrade createdStudent = mockConstruction.constructed().get(0);

            assertEquals(grade, createdStudent.get_grade());
            assertEquals(dateDouble, createdStudent.get_date());
            assertEquals(student, createdStudent.get_student());
            assertEquals(courseEditionID1Double, createdStudent.get_courseEditionID());

        }
    }

    @Test
    void shouldThrowExceptionWhenGradeIsNull() {
        // Arrange
        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl();
        StudentID student = mock(StudentID.class);
        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);

        when(grade.knowGrade()).thenReturn(10.0);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                factory.newGradeStudent(null, dateDouble, student, courseEditionID1Double));
        assertEquals("Grade cannot be null.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDateIsNull() {
        // Arrange
        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl();
        StudentID student = mock(StudentID.class);
        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);

        when(grade.knowGrade()).thenReturn(10.0);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                factory.newGradeStudent(grade, null, student, courseEditionID1Double));
        assertEquals("Date cannot be null or empty!", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenStudentIsNull() {
        // Arrange
        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl();
        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);

        when(grade.knowGrade()).thenReturn(10.0);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                factory.newGradeStudent(grade, dateDouble,null, courseEditionID1Double));
        assertEquals("Student cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCourseEditionIsNull() {
        // Arrange
        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl();
        StudentID student = mock(StudentID.class);
        Date dateDouble = mock(Date.class);
        Grade grade = mock(Grade.class);

        when(grade.knowGrade()).thenReturn(10.0);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                factory.newGradeStudent(grade, dateDouble,student, null));
        assertEquals("Course Edition cannot be null", exception.getMessage());
    }

}

