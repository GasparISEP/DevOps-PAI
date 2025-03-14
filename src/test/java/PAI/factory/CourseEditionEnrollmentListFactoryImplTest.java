package PAI.factory;

import PAI.domain.CourseEditionEnrollment;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CourseEditionEnrollmentListFactoryImplTest {

    @Test
    void testCourseEditionEnrollmentListFactory() {

        // arrange
        CourseEditionEnrollmentListFactoryImpl factory = new CourseEditionEnrollmentListFactoryImpl();

        // act
        Set<CourseEditionEnrollment> result = factory.getCourseEditionEnrollmentList();

        // assert
        assertEquals(HashSet.class, result.getClass());
    }
}