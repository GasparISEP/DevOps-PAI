
package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.VOs.Date;
import PAI.VOs.Grade;
import PAI.VOs.StudentID;
import PAI.domain.*;
import PAI.factory.*;
import PAI.repository.ICourseEditionRepository;
import PAI.repository.StudentGradeRepository;
import PAI.repository.CourseEditionEnrolmentRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US23_IWantToKnowTheApprovalPercentageOfACourseEditionTest {

    @Test
    void shouldCreateController() {
        //arrange
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        US23_IWantToKnowTheApprovalPercentageOfACourseEdition controller = new US23_IWantToKnowTheApprovalPercentageOfACourseEdition(studentGradeRepository,courseEditionRepository);
        //act
        assertNotNull(controller);
    }

    @Test
    void shouldReturnApprovalPercentageInCourseEdition(){
        //arrange
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        US23_IWantToKnowTheApprovalPercentageOfACourseEdition controller = new US23_IWantToKnowTheApprovalPercentageOfACourseEdition(studentGradeRepository,courseEditionRepository);
        CourseEdition_2 courseEdition_2Double = mock(CourseEdition_2.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        when(courseEditionRepository.findIdByCourseEdition(courseEdition_2Double)).thenReturn(Optional.of(courseEditionIDDouble));
        when(studentGradeRepository.knowApprovalRate(courseEditionIDDouble)).thenReturn(16.0);
        //act
        double result = controller.IWantToKnowTheApprovalPercentageOfACourseEdition(courseEdition_2Double);
        //assert
        assertEquals(16.0,result);
    }
}
