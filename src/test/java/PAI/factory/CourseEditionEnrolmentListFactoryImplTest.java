package PAI.factory;

import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolmentListFactoryImpl;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CourseEditionEnrolmentListFactoryImplTest {

    @Test
    void testCourseEditionEnrollmentListFactory() {

        // arrange
        CourseEditionEnrolmentListFactoryImpl factory = new CourseEditionEnrolmentListFactoryImpl();

        // act
        Set<CourseEditionEnrolment> result = factory.getCourseEditionEnrolmentList();

        // assert
        assertEquals(HashSet.class, result.getClass());
    }
}