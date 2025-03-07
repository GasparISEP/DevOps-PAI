package PAI.factory;

import PAI.domain.CourseEdition;
import PAI.domain.CourseEditionEnrollment;
import PAI.domain.Student;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;


class CourseEditionEnrollmentFactoryTest {

    @Test
    void should_not_return_null_when_creating_courseEditionEnrollment() {
        try (MockedConstruction<CourseEditionEnrollment> mockEnrollments = mockConstruction(CourseEditionEnrollment.class)) {
            // arrange
            CourseEditionEnrollmentFactory factory = new CourseEditionEnrollmentFactory();
            Student studentDouble = mock(Student.class);
            CourseEdition courseEditionDouble = mock(CourseEdition.class);

            // act
            CourseEditionEnrollment courseEditionEnrollment = factory.createCourseEditionEnrollment(studentDouble, courseEditionDouble);

            // assert
            assertNotNull(courseEditionEnrollment);
        }
    }
    @Test
    void should_create_exactly_one_instance_of_CourseEditionEnrollment() {
        try (MockedConstruction<CourseEditionEnrollment> mockEnrollments = mockConstruction(CourseEditionEnrollment.class)) {
            // arrange
            CourseEditionEnrollmentFactory factory = new CourseEditionEnrollmentFactory();
            Student studentDouble = mock(Student.class);
            CourseEdition courseEditionDouble = mock(CourseEdition.class);

            // act
            factory.createCourseEditionEnrollment(studentDouble, courseEditionDouble);

            // assert
            assertEquals(1, mockEnrollments.constructed().size());
        }
    }
    @Test
    void should_create_instance_of_CourseEditionEnrollment_class() {
        try (MockedConstruction<CourseEditionEnrollment> mockEnrollments = mockConstruction(CourseEditionEnrollment.class)) {
            // arrange
            CourseEditionEnrollmentFactory factory = new CourseEditionEnrollmentFactory();
            Student studentDouble = mock(Student.class);
            CourseEdition courseEditionDouble = mock(CourseEdition.class);

            // act
            factory.createCourseEditionEnrollment(studentDouble, courseEditionDouble);

            // assert
            assertEquals(CourseEditionEnrollment.class, mockEnrollments.constructed().get(0).getClass());
        }
    }
}