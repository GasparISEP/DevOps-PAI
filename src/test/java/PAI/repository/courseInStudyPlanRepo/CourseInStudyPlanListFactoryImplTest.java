package PAI.repository.courseInStudyPlanRepo;

import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;


class CourseInStudyPlanListFactoryImplTest {

    @Test
    void shouldCreateArrayListWhenConstructorIsCalled() {
        //arrange
        ICourseInStudyPlanDDDListFactory iCourseInStudyPlanListFactory_2 = new CourseInStudyPlanDDDListFactoryImpl();

        //act
        List<CourseInStudyPlan> courseInStudyPlanList_2 = iCourseInStudyPlanListFactory_2.newArrayList();

        //assert
        assertNotNull(courseInStudyPlanList_2);
        assertInstanceOf(ArrayList.class, courseInStudyPlanList_2);
    }
}
