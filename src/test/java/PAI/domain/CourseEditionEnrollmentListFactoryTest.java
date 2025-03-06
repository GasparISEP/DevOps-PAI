package PAI.domain;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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