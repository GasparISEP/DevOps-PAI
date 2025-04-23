
package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.domain.*;
import PAI.factory.*;
import PAI.repository.ICourseEditionRepository;
import PAI.repository.StudentGradeRepository;
import PAI.service.IStudentGradeService;
import org.junit.jupiter.api.Test;

import java.util.Optional;


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
}
