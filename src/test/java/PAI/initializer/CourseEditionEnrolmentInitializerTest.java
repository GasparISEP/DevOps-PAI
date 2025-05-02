package PAI.initializer;

import PAI.controller.US16_EnrolAStudentInACourseEditionController;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;


public class CourseEditionEnrolmentInitializerTest {


    @Test
    public void testLoadCourseEditionEnrolments() throws Exception {
        // Arrange
        US16_EnrolAStudentInACourseEditionController mockController = mock(US16_EnrolAStudentInACourseEditionController.class);

        CourseEditionEnrolmentInitializer initializer = new CourseEditionEnrolmentInitializer();

        //act
        initializer.loadCourseEditionEnrolments(mockController).run();

        // assert
        verify(mockController, atLeastOnce()).enrolStudentInCourseEdition(any(), any());
    }
}

