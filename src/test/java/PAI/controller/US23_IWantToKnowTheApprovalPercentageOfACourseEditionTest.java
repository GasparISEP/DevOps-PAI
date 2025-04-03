
package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.domain.*;
import PAI.factory.*;
import PAI.repository.ICourseEditionRepositoryDDD;
import org.junit.jupiter.api.Test;

import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US23_IWantToKnowTheApprovalPercentageOfACourseEditionTest {

    @Test
    void shouldCreateController() {
        //arrange
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        ICourseEditionRepositoryDDD courseEditionRepository = mock(ICourseEditionRepositoryDDD.class);
        US23_IWantToKnowTheApprovalPercentageOfACourseEdition controller = new US23_IWantToKnowTheApprovalPercentageOfACourseEdition(studentGradeRepository,courseEditionRepository);
        //act
        assertNotNull(controller);
    }

    @Test
    void shouldReturnApprovalPercentageInCourseEdition(){
        //arrange
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        ICourseEditionRepositoryDDD courseEditionRepository = mock(ICourseEditionRepositoryDDD.class);
        US23_IWantToKnowTheApprovalPercentageOfACourseEdition controller = new US23_IWantToKnowTheApprovalPercentageOfACourseEdition(studentGradeRepository,courseEditionRepository);
        CourseEditionDDD courseEdition_DDDDouble = mock(CourseEditionDDD.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        when(courseEditionRepository.findIdByCourseEdition(courseEdition_DDDDouble)).thenReturn(Optional.of(courseEditionIDDouble));
        when(studentGradeRepository.knowApprovalRate(courseEditionIDDouble)).thenReturn(16.0);
        //act
        double result = controller.CalculateApprovalPercentageOfACourseEdition(courseEdition_DDDDouble);
        //assert
        assertEquals(16.0,result);
    }
}
