
package PAI.controller;

import PAI.domain.*;
import PAI.factory.GradeStudentFactory;
import PAI.factory.GradeStudentFactoryImpl;
import PAI.factory.GradeStudentListFactory;
import PAI.factory.GradeStudentListFactoryImpl;
import PAI.repository.GradeStudentRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
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
        GradeStudentFactory gradeStudentFactory = mock(GradeStudentFactory.class);
        GradeStudentListFactory gradeStudentListFactory = mock(GradeStudentListFactory.class);

        List<GradeStudent> mockGradeList = spy(new ArrayList<>());

        when(gradeStudentListFactory.newArrayList()).thenReturn(mockGradeList);

        GradeStudentRepository list = mock(GradeStudentRepository.class);

        US25_IWantToKnowTheAverageGradeOfACourseEdition controlador1 = new US25_IWantToKnowTheAverageGradeOfACourseEdition(list);

        CourseEdition courseEdition1 = mock(CourseEdition.class);

        Student student1 = mock(Student.class);
        Student student2 = mock(Student.class);
        GradeStudent gradeStudent1 = mock(GradeStudent.class);
        GradeStudent gradeStudent2 = mock(GradeStudent.class);

        when(gradeStudentFactory.newGradeStudent(8, "10-10-2025", student1, courseEdition1)).thenReturn(gradeStudent1);
        when(gradeStudentFactory.newGradeStudent(20, "10-10-2025", student2, courseEdition1)).thenReturn(gradeStudent2);

        when(gradeStudent1.knowGrade()).thenReturn(8.0);
        when(gradeStudent2.knowGrade()).thenReturn(20.0);

        when(gradeStudent1.hasThisCourseEdition(courseEdition1)).thenReturn(true);
        when(gradeStudent2.hasThisCourseEdition(courseEdition1)).thenReturn(true);

        when(list.addGradeToStudent(8, "10-10-2025", student1, courseEdition1)).thenReturn(Optional.of(gradeStudent1));
        when(list.addGradeToStudent(20, "10-10-2025", student2, courseEdition1)).thenReturn(Optional.of(gradeStudent2));
        when(list.KnowAverageGrade(courseEdition1)).thenReturn(14.0);

        // Act
        double optC1 = controlador1.IWantToKnowTheAvgGrade(courseEdition1);

        //assert
        assertEquals(14,optC1,0.01);
    }

}



