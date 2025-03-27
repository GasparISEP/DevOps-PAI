package PAI.factory;

import PAI.domain.CourseInStudyPlan;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;


class CourseInStudyPlanListFactoryImplTest {

    @Test
    void shouldCreateArrayListWhenConstructorIsCalled() {
        //arrange
        ICourseInStudyPlanListFactory iCourseInStudyPlanListFactory = new CourseInStudyPlanListFactoryImpl();

        //act
        List<CourseInStudyPlan> courseInStudyPlanList = iCourseInStudyPlanListFactory.newArrayList();

        //assert
        assertNotNull(courseInStudyPlanList);
        assertInstanceOf(ArrayList.class, courseInStudyPlanList);
    }
}