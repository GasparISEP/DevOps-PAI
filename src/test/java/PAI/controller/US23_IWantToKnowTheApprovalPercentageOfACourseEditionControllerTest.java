
package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.service.IStudentGradeService;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US23_IWantToKnowTheApprovalPercentageOfACourseEditionControllerTest {

    @Test
    void shouldCreateControllerWhenServiceIsProvided(){
        //arrange
        IStudentGradeService iStudentGradeService = mock(IStudentGradeService.class);
        US23_IWantToKnowTheApprovalPercentageOfACourseEditionController controller = new US23_IWantToKnowTheApprovalPercentageOfACourseEditionController(iStudentGradeService);
        //assert
        assertNotNull(controller);
    }

    @Test
    void shouldThrowExceptionWhenServiceIsNull(){
        //assert
        assertThrows(IllegalArgumentException.class, () -> new US23_IWantToKnowTheApprovalPercentageOfACourseEditionController(null));
    }

    @Test
    void shouldReturnApprovalPercentage(){
        IStudentGradeService iStudentGradeService = mock(IStudentGradeService.class);
        US23_IWantToKnowTheApprovalPercentageOfACourseEditionController controller = new US23_IWantToKnowTheApprovalPercentageOfACourseEditionController(iStudentGradeService);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        when(iStudentGradeService.knowApprovalRate(courseEditionID)).thenReturn(20.0);
        //act
        double result = controller.CalculateApprovalPercentageOfACourseEdition(courseEditionID);
        //assert
        assertEquals(20.0,result);
    }
    @Test
    void shouldThrowExceptionWhenCourseEditionIDIsNull(){
        //arrange
        IStudentGradeService iStudentGradeService = mock(IStudentGradeService.class);
        US23_IWantToKnowTheApprovalPercentageOfACourseEditionController controller = new US23_IWantToKnowTheApprovalPercentageOfACourseEditionController(iStudentGradeService);
        CourseEditionID courseEditionID = null;
        //assert
        assertThrows(IllegalArgumentException.class, () ->controller.CalculateApprovalPercentageOfACourseEdition(courseEditionID));
    }
}
