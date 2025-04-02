
package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.VOs.Date;
import PAI.VOs.Grade;
import PAI.VOs.StudentID;
import PAI.domain.*;
import PAI.factory.IStudentGradeRepository;
import PAI.repository.ICourseEditionRepositoryDDD;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US25_IWantToKnowTheAverageGradeOfACourseEditionTest {

    @Test
    void nullStudentGradeRepository() {
        //arrange
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);

        //act & assert
        Exception exception = assertThrows(Exception.class, () ->
            new US25_IWantToKnowTheAverageGradeOfACourseEdition(studentGradeRepository, null)
        );
        assertEquals("GradeStudent Repository cannot be null", exception.getMessage());
    }

    @Test
    void nullCourseEditionRepository() {
        //arrange
        ICourseEditionRepositoryDDD courseEditionRepository = mock(ICourseEditionRepositoryDDD.class);

        //act & assert
        Exception exception = assertThrows(Exception.class, () ->
                new US25_IWantToKnowTheAverageGradeOfACourseEdition(null, courseEditionRepository)
        );
        assertEquals("GradeStudent Repository cannot be null", exception.getMessage());
    }

    @Test
    void averageGradeInACourseEdition () throws Exception {

        //arrange
        IStudentGradeRepository studentGradeRepositoryListDouble = mock(IStudentGradeRepository.class);
        ICourseEditionRepositoryDDD courseEditionRepositoryListDouble = mock(ICourseEditionRepositoryDDD.class);

        US25_IWantToKnowTheAverageGradeOfACourseEdition controlador1 = new US25_IWantToKnowTheAverageGradeOfACourseEdition(studentGradeRepositoryListDouble,courseEditionRepositoryListDouble);

        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);
        CourseEditionDDD courseEditionDouble = mock(CourseEditionDDD.class);

        StudentID student1 = mock(StudentID.class);
        StudentID student2 = mock(StudentID.class);
        Grade grade1 = mock(Grade.class);
        Grade grade2 = mock(Grade.class);
        Date dateDouble = mock(Date.class);
        StudentGrade studentGrade1 = mock(StudentGrade.class);

        when(grade1.knowGrade()).thenReturn(8.0);
        when(grade2.knowGrade()).thenReturn(20.0);

        when(studentGrade1.get_grade()).thenReturn(grade1);
        when(studentGrade1.get_grade()).thenReturn(grade2);


        when(courseEditionRepositoryListDouble.findIdByCourseEdition(courseEditionDouble)).thenReturn(Optional.of(courseEditionID1Double));
        when(studentGradeRepositoryListDouble.addGradeToStudent(grade1, dateDouble, student1, courseEditionID1Double)).thenReturn(true);
        when(studentGradeRepositoryListDouble.addGradeToStudent(grade2, dateDouble, student2, courseEditionID1Double)).thenReturn(true);
        when(studentGradeRepositoryListDouble.getAverageGrade(courseEditionID1Double)).thenReturn(14.0);

        // Act
        Double optC1 = controlador1.IWantToKnowTheAvgGrade(courseEditionDouble);

        //assert
        assertEquals(14,optC1,0.01);
    }

    @Test
    void cantGetAverageGradeInACourseEdition () throws Exception {

        //arrange
        IStudentGradeRepository studentGradeRepositoryListDouble = mock(IStudentGradeRepository.class);
        ICourseEditionRepositoryDDD courseEditionRepositoryListDouble = mock(ICourseEditionRepositoryDDD.class);

        US25_IWantToKnowTheAverageGradeOfACourseEdition controlador1 = new US25_IWantToKnowTheAverageGradeOfACourseEdition(studentGradeRepositoryListDouble,courseEditionRepositoryListDouble);

        CourseEditionDDD courseEditionDouble = mock(CourseEditionDDD.class);

        when(courseEditionRepositoryListDouble.findIdByCourseEdition(courseEditionDouble)).thenReturn(Optional.empty());

        // Act
        Double optC1 = controlador1.IWantToKnowTheAvgGrade(courseEditionDouble);

        //assert
        assertNull(optC1);
    }

}





