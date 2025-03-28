package PAI.factory;

import PAI.domain.CourseInStudyPlan;
import PAI.domain.CourseInStudyPlan_2;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;


class CourseInStudyPlanListFactoryImpl_2Test {

    @Test
    void shouldCreateArrayListWhenConstructorIsCalled() {
        //arrange
        ICourseInStudyPlanListFactory_2 iCourseInStudyPlanListFactory_2 = new CourseInStudyPlanListFactoryImpl_2();

        //act
        List<CourseInStudyPlan_2> courseInStudyPlanList_2 = iCourseInStudyPlanListFactory_2.newArrayList();

        //assert
        assertNotNull(courseInStudyPlanList_2);
        assertInstanceOf(ArrayList.class, courseInStudyPlanList_2);
    }
}
