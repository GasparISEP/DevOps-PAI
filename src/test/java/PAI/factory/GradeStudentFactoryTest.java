package PAI.factory;

import PAI.domain.CourseEdition;
import PAI.domain.GradeStudent;
import PAI.domain.Student;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GradeStudentFactoryTest {
    @Test
    void creatingConstrutor () throws Exception {
        // Arrange
        double grade = 11.0;
        String date = "05-03-2025";
        Student student = mock(Student.class);
        CourseEdition courseEdition =  mock(CourseEdition.class);

        try(MockedConstruction<GradeStudent> mockConstruction =  mockConstruction(GradeStudent.class,(mock, context) -> {
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
            GradeStudentFactory factory = new GradeStudentFactory();
            GradeStudent gradeStudent = factory.newGradeStudent(grade, date, student, courseEdition);

            // Assert
            assertEquals(1, mockConstruction.constructed().size());
            GradeStudent createdStudent = mockConstruction.constructed().get(0);

            assertEquals(grade, createdStudent.knowGrade());
            assertEquals(date, createdStudent.get_date().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            assertEquals(student, createdStudent.get_student());
            assertEquals(courseEdition, createdStudent.get_courseEdition());

        }


    }

}