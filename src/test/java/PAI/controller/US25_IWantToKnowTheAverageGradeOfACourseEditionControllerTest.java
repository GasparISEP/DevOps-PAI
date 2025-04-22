
package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.VOs.Date;
import PAI.VOs.Grade;
import PAI.VOs.StudentID;
import PAI.domain.*;
import PAI.factory.IStudentGradeRepository;
import PAI.repository.ICourseEditionRepository;
import PAI.repository.StudentGradeRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US25_IWantToKnowTheAverageGradeOfACourseEditionControllerTest {

    @Test
    void nullStudentGradeRepository() {
        //arrange
        StudentGradeRepository studentGradeRepository = mock(StudentGradeRepository.class);

        //act & assert
        Exception exception = assertThrows(Exception.class, () ->
            new US25_IWantToKnowTheAverageGradeOfACourseEditionController(studentGradeRepository, null)
        );
        assertEquals("GradeStudent Repository cannot be null", exception.getMessage());
    }

    @Test
    void nullCourseEditionRepository() {
        //arrange
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);

        //act & assert
        Exception exception = assertThrows(Exception.class, () ->
                new US25_IWantToKnowTheAverageGradeOfACourseEditionController(null, courseEditionRepository)
        );
        assertEquals("GradeStudent Repository cannot be null", exception.getMessage());
    }

    @Test
    void averageGradeInACourseEdition () throws Exception {

        //arrange
        StudentGradeRepository studentGradeRepositoryListDouble = mock(StudentGradeRepository.class);
        ICourseEditionRepository courseEditionRepositoryListDouble = mock(ICourseEditionRepository.class);

        US25_IWantToKnowTheAverageGradeOfACourseEditionController controlador1 = new US25_IWantToKnowTheAverageGradeOfACourseEditionController(studentGradeRepositoryListDouble,courseEditionRepositoryListDouble);

        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);
        CourseEdition courseEditionDouble = mock(CourseEdition.class);

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
        StudentGradeRepository studentGradeRepositoryListDouble = mock(StudentGradeRepository.class);
        ICourseEditionRepository courseEditionRepositoryListDouble = mock(ICourseEditionRepository.class);

        US25_IWantToKnowTheAverageGradeOfACourseEditionController controlador1 = new US25_IWantToKnowTheAverageGradeOfACourseEditionController(studentGradeRepositoryListDouble,courseEditionRepositoryListDouble);

        CourseEdition courseEditionDouble = mock(CourseEdition.class);

        when(courseEditionRepositoryListDouble.findIdByCourseEdition(courseEditionDouble)).thenReturn(Optional.empty());

        // Act
        Double optC1 = controlador1.IWantToKnowTheAvgGrade(courseEditionDouble);

        //assert
        assertNull(optC1);
    }

}





