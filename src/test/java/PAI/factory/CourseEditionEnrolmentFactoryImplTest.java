package PAI.factory;

import PAI.VOs.CourseEditionID;
import PAI.VOs.StudentID;
import PAI.domain.CourseEdition;
import PAI.domain.CourseEditionEnrolment;
import PAI.domain.Student;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;


class CourseEditionEnrolmentFactoryImplTest {

    @Test
    void should_create_courseEditionEnrollments() {
        // arrange
        try (MockedConstruction<CourseEditionEnrolment> mockEnrollments = mockConstruction(CourseEditionEnrolment.class)) {

            CourseEditionEnrolmentFactoryImpl factory = new CourseEditionEnrolmentFactoryImpl();
            StudentID studentDouble = mock(StudentID.class);
            CourseEditionID courseEditionDouble = mock(CourseEditionID.class);

            // act
            CourseEditionEnrolment courseEditionEnrolment = factory.createCourseEditionEnrolment(studentDouble, courseEditionDouble);

            // assert
            assertNotNull(courseEditionEnrolment);
            assertEquals(1, mockEnrollments.constructed().size());
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

        //arrange

        CourseEditionID courseEditionDouble = mock(CourseEditionID.class);
        CourseEditionEnrolmentFactoryImpl CEEFactory = new CourseEditionEnrolmentFactoryImpl();

        // act + assert

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            CEEFactory.createCourseEditionEnrolment(null,courseEditionDouble);
        });

        assertEquals("Student cannot be null!",exception.getMessage());
    }
    @Test
    void should_throw_an_exception_if_courseEdition_is_null(){

        //arrange
        StudentID studentDouble = mock(StudentID.class);
        CourseEditionEnrolmentFactoryImpl CEEFactory = new CourseEditionEnrolmentFactoryImpl();

        //act + assert

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            CEEFactory.createCourseEditionEnrolment(studentDouble,null);
        });

        assertEquals("Course edition cannot be null!",exception.getMessage());

    }
}