package PAI.domain;

import PAI.factory.CourseEditionEnrollmentFactory;
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
            // Arrange
            CourseEditionEnrollmentFactory factory = new CourseEditionEnrollmentFactory();
            Student studentDouble = mock(Student.class);
            CourseEdition courseEditionDouble = mock(CourseEdition.class);

            // Act
            CourseEditionEnrollment courseEditionEnrollment = factory.createCourseEditionEnrollment(studentDouble, courseEditionDouble);

            // Assert
            assertNotNull(courseEditionEnrollment);
        }
    }
    @Test
    void should_create_exactly_one_instance_of_CourseEditionEnrollment() {
        try (MockedConstruction<CourseEditionEnrollment> mockEnrollments = mockConstruction(CourseEditionEnrollment.class)) {
            // Arrange
            CourseEditionEnrollmentFactory factory = new CourseEditionEnrollmentFactory();
            Student studentDouble = mock(Student.class);
            CourseEdition courseEditionDouble = mock(CourseEdition.class);

            // Act
            factory.createCourseEditionEnrollment(studentDouble, courseEditionDouble);

            // Assert
            assertEquals(1, mockEnrollments.constructed().size());
        }
    }
    @Test
    void should_create_instance_of_CourseEditionEnrollment_class() {
        try (MockedConstruction<CourseEditionEnrollment> mockEnrollments = mockConstruction(CourseEditionEnrollment.class)) {
            // Arrange
            CourseEditionEnrollmentFactory factory = new CourseEditionEnrollmentFactory();
            Student studentDouble = mock(Student.class);
            CourseEdition courseEditionDouble = mock(CourseEdition.class);

            // Act
            factory.createCourseEditionEnrollment(studentDouble, courseEditionDouble);

            // Assert
            assertEquals(CourseEditionEnrollment.class, mockEnrollments.constructed().get(0).getClass());
        }
    }
}