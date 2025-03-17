
package PAI.controller;

import PAI.domain.*;
import PAI.repository.GradeStudentRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US25_IWantToKnowTheAverageGradeOfACourseEditionTest {

    @Test
    void nullGradeStudentRepository() {
        //arrange
        GradeStudentRepository gradeStudentRepository = null;

        //act & assert
        Exception exception = assertThrows(Exception.class, () ->
            new US25_IWantToKnowTheAverageGradeOfACourseEdition(gradeStudentRepository)
        );
        assertEquals("GradeStudent Repository cannot be null", exception.getMessage());
    }

    @Test
    void averageGradeInACourseEdition () throws Exception {

        //arrange
        GradeStudentRepository list = mock(GradeStudentRepository.class);

        US25_IWantToKnowTheAverageGradeOfACourseEdition controlador1 = new US25_IWantToKnowTheAverageGradeOfACourseEdition(list);

        CourseEdition courseEdition1 = mock(CourseEdition.class);

        Student student1 = mock(Student.class);
        Student student2 = mock(Student.class);
        StudentGrade studentGrade1 = mock(StudentGrade.class);
        StudentGrade studentGrade2 = mock(StudentGrade.class);

        when(list.addGradeToStudent(8, "10-10-2025", student1, courseEdition1)).thenReturn(Optional.of(studentGrade1));
        when(list.addGradeToStudent(20, "10-10-2025", student2, courseEdition1)).thenReturn(Optional.of(studentGrade2));
        when(list.KnowAverageGrade(courseEdition1)).thenReturn(14.0);

        // Act
        double optC1 = controlador1.IWantToKnowTheAvgGrade(courseEdition1);

        //assert
        assertEquals(14,optC1,0.01);
    }

}



