package PAI.factory;

import PAI.domain.CourseEditionEnrolment;
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
        Set<CourseEditionEnrolment> result = factory.getCourseEditionEnrollmentList();

        // assert
        assertEquals(HashSet.class, result.getClass());
    }
}