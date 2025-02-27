package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseInStudyPlanFactoryTest {

    @Test
    void shouldCreateCourseInStudyPlan() throws Exception {

        //arrange
        Course course = mock(Course.class);
        Programme programme = mock(Programme.class);

        when(programme.getQuantityOfSemester()).thenReturn(6);
        when(programme.calculateNumberOfYears(6)).thenReturn(3);

        CourseInStudyPlanFactory courseInStudyPlanFactory = new CourseInStudyPlanFactory();

        //act
        CourseInStudyPlan courseInStudyPlan = courseInStudyPlanFactory.newCourseInStudyPlan(1,1, course, programme);

        //assert
        assertNotNull(courseInStudyPlan);
    }
}