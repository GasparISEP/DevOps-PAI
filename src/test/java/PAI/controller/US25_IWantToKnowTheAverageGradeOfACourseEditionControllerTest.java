
package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.VOs.Date;
import PAI.VOs.Grade;
import PAI.VOs.StudentID;
import PAI.domain.*;
import PAI.factory.IStudentGradeRepository;
import PAI.repository.ICourseEditionRepository;
import PAI.repository.IStudentRepository;
import PAI.repository.StudentGradeRepository;
import PAI.service.IStudentGradeService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US25_IWantToKnowTheAverageGradeOfACourseEditionControllerTest {

    @Test
    public void validConstructor() throws Exception {
        //arrange
        IStudentGradeService studentGradeService = mock(IStudentGradeService.class);

        // act
        US25_IWantToKnowTheAverageGradeOfACourseEditionController us25 = new US25_IWantToKnowTheAverageGradeOfACourseEditionController(studentGradeService);

        //assert
        assertNotNull(us25);
    }

    @Test
    void nullStudentGradeService() {
        //arrange
        IStudentGradeService studentGradeService = null;

        //act & assert
        Exception exception = assertThrows(Exception.class, () ->
            new US25_IWantToKnowTheAverageGradeOfACourseEditionController(studentGradeService)
        );
        assertEquals("GradeStudent Service cannot be null", exception.getMessage());
    }



    @Test
    void averageGradeInACourseEdition () throws Exception {

        //arrange
        IStudentGradeService studentGradeService = mock(IStudentGradeService.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        when(studentGradeService.getAverageGrade(courseEditionID)).thenReturn(14.0);

        US25_IWantToKnowTheAverageGradeOfACourseEditionController controlador1 = new US25_IWantToKnowTheAverageGradeOfACourseEditionController(studentGradeService);

        // Act
        Double optC1 = controlador1.IWantToKnowTheAvgGrade(courseEditionID);

        //assert
        assertEquals(14,optC1,0.01);
    }

    @Test
    void nullStudentGradeServiceInAverageGrade() throws Exception {
        //arrange
        IStudentGradeService studentGradeService = mock(IStudentGradeService.class);
        CourseEditionID courseEditionID = null;
        when(studentGradeService.getAverageGrade(courseEditionID)).thenReturn(14.0);

        US25_IWantToKnowTheAverageGradeOfACourseEditionController controlador1 = new US25_IWantToKnowTheAverageGradeOfACourseEditionController(studentGradeService);

        //act & assert
        Exception exception = assertThrows(Exception.class, () -> controlador1.IWantToKnowTheAvgGrade(courseEditionID));
        assertEquals("Course Edition cannot be null", exception.getMessage());
    }


}





