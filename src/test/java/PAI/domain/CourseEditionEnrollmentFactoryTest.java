package PAI.domain;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class CourseEditionEnrollmentFactoryTest {

    @Test
    void should_create_a_valid_courseEditionEnrollment(){

        //arrange

        CourseEditionEnrollmentFactory factory = new CourseEditionEnrollmentFactory();
        Student studentDouble = mock(Student.class);
        CourseEdition courseEditionDouble = mock(CourseEdition.class);

        //act

        CourseEditionEnrollment courseEditionEnrollment = factory.createCourseEditionEnrollment(studentDouble,courseEditionDouble);

        //assert

        assertNotNull(courseEditionEnrollment);
    }
    @Test
    void should_throw_an_exception_when_student_is_null(){

        //arrange

        CourseEditionEnrollmentFactory factory = new CourseEditionEnrollmentFactory();
        CourseEdition courseEdition = mock(CourseEdition.class);

        //act + assert

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createCourseEditionEnrollment(null, courseEdition);
        });
        assertEquals("Student cannot be null!", exception.getMessage());
    }
    @Test
    void should_throw_an_exception_when_courseEdition_is_null(){
        //arrange

        CourseEditionEnrollmentFactory factory = new CourseEditionEnrollmentFactory();
        Student student = mock(Student.class);

        //act + assert

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createCourseEditionEnrollment(student, null);
        });
        assertEquals("Course edition cannot be null!", exception.getMessage());
    }
}