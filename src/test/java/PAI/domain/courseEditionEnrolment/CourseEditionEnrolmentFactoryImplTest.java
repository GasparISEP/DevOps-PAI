package PAI.domain.courseEditionEnrolment;

import PAI.VOs.CourseEditionID;
import PAI.VOs.Date;
import PAI.VOs.EnrolmentStatus;
import PAI.VOs.StudentID;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class CourseEditionEnrolmentFactoryImplTest {

    @Test
    void should_create_courseEditionEnrollments() {

        // arrange
        StudentID studentIDDouble = mock(StudentID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);

        try (MockedConstruction<CourseEditionEnrolment> mockEnrolments =
                     mockConstruction(CourseEditionEnrolment.class, (mock, context) -> {
                         when(mock.knowStudent()).thenReturn((StudentID) context.arguments().get(0));
                         when(mock.knowCourseEdition()).thenReturn((CourseEditionID) context.arguments().get(1));
                     })) {

            CourseEditionEnrolmentFactoryImpl factory = new CourseEditionEnrolmentFactoryImpl();

            // act
            CourseEditionEnrolment courseEditionEnrolment = factory.createCourseEditionEnrolment(studentIDDouble, courseEditionIDDouble);

            // assert
            assertNotNull(courseEditionEnrolment);
            assertEquals(1, mockEnrolments.constructed().size());
            assertEquals(studentIDDouble, courseEditionEnrolment.knowStudent());
            assertEquals(courseEditionIDDouble, courseEditionEnrolment.knowCourseEdition());
        }
    }

    @Test
    void should_create_exactly_one_instance_of_CourseEditionEnrollment() {
        try (MockedConstruction<CourseEditionEnrolment> mockEnrollments = mockConstruction(CourseEditionEnrolment.class)) {
            // arrange
            CourseEditionEnrolmentFactoryImpl factory = new CourseEditionEnrolmentFactoryImpl();
            StudentID studentDouble = mock(StudentID.class);
            CourseEditionID courseEditionDouble = mock(CourseEditionID.class);

            // act
            factory.createCourseEditionEnrolment(studentDouble, courseEditionDouble);

            // assert
            assertEquals(1, mockEnrollments.constructed().size());
        }
    }

    @Test
    void should_create_instance_of_CourseEditionEnrollment_class() {
        try (MockedConstruction<CourseEditionEnrolment> mockEnrollments = mockConstruction(CourseEditionEnrolment.class)) {
            // arrange
            CourseEditionEnrolmentFactoryImpl factory = new CourseEditionEnrolmentFactoryImpl();
            StudentID studentDouble = mock(StudentID.class);
            CourseEditionID courseEditionDouble = mock(CourseEditionID.class);

            // act
            factory.createCourseEditionEnrolment(studentDouble, courseEditionDouble);

            // assert
            assertEquals(CourseEditionEnrolment.class, mockEnrollments.constructed().get(0).getClass());
        }
    }

    @Test
    void should_throw_an_exception_if_student_is_null() throws IllegalArgumentException {

        // arrange
        CourseEditionEnrolmentFactoryImpl ceeFactory = new CourseEditionEnrolmentFactoryImpl();
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);

        try (
                MockedConstruction<CourseEditionEnrolment> ceeConstructorMock = mockConstruction(CourseEditionEnrolment.class, (mock, context) -> {
                    throw new RuntimeException(new InstantiationException("Student ID cannot be null."));
                })) {
            // act
            try {
                ceeFactory.createCourseEditionEnrolment(null, courseEditionIDDouble);
                fail("Expected exception not thrown");
            } catch (Exception e) {
                // assert
                assertTrue(e.getCause().getMessage().contains("Student ID cannot be null."));
            }
        }
    }

    @Test
    void should_throw_an_exception_if_courseEdition_is_null() {

        // arrange
        StudentID studentIDDouble = mock(StudentID.class);
        CourseEditionEnrolmentFactoryImpl ceeFactory = new CourseEditionEnrolmentFactoryImpl();

        try (
                MockedConstruction<CourseEditionEnrolment> ceeConstructorMock = mockConstruction(CourseEditionEnrolment.class, (mock, context) -> {
                    throw new RuntimeException(new InstantiationException("Course Edition ID cannot be null."));
                })) {
            // act
            try {
                ceeFactory.createCourseEditionEnrolment(studentIDDouble, null);
                fail("Expected exception not thrown");
            } catch (Exception e) {
                // assert
                assertTrue(e.getCause().getMessage().contains("Course Edition ID cannot be null."));
            }
        }
    }
}