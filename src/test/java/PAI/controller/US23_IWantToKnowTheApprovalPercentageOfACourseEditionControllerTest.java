
package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.domain.*;
import PAI.factory.*;
import PAI.repository.ICourseEditionRepository;
import PAI.repository.StudentGradeRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US23_IWantToKnowTheApprovalPercentageOfACourseEditionControllerTest {

    @Test
    void shouldCreateController() {
        //arrange
        StudentGradeRepository studentGradeRepository = mock(StudentGradeRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        US23_IWantToKnowTheApprovalPercentageOfACourseEditionController controller = new US23_IWantToKnowTheApprovalPercentageOfACourseEditionController(studentGradeRepository,courseEditionRepository);
        //act
        assertNotNull(controller);
    }

//    @Test
//    void shouldReturnApprovalPercentageInCourseEdition(){
//        //arrange
//        StudentGradeRepository studentGradeRepository = mock(StudentGradeRepository.class);
//        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
//        US23_IWantToKnowTheApprovalPercentageOfACourseEditionController controller = new US23_IWantToKnowTheApprovalPercentageOfACourseEditionController(studentGradeRepository,courseEditionRepository);
//        CourseEdition courseEdition_DDDDouble = mock(CourseEdition.class);
//        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
//        when(courseEditionRepository.findIdByCourseEdition(courseEdition_DDDDouble)).thenReturn(Optional.of(courseEditionIDDouble));
//        when(studentGradeRepository.knowApprovalRate(courseEditionIDDouble)).thenReturn(16.0);
//        //act
//        double result = controller.CalculateApprovalPercentageOfACourseEdition(courseEdition_DDDDouble);
//        //assert
//        assertEquals(16.0,result);
//    }
}
