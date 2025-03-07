package PAI.factory;

import PAI.domain.CourseEditionEnrollment;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CourseEditionEnrollmentListFactoryTest {

    @Test
    void testCourseEditionEnrollmentListFactory() {

        // arrange
        CourseEditionEnrollmentListFactory factory = new CourseEditionEnrollmentListFactory();

        // act
        Set<CourseEditionEnrollment> result = factory.getCourseEditionEnrollmentList();

        // assert
        assertEquals(HashSet.class, result.getClass());
    }
}