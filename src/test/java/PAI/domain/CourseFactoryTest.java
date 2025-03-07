package PAI.domain;

import PAI.factory.CourseFactory;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mockConstruction;

class CourseFactoryTest {

    @Test
    void shouldCreateCourseWhenConstructorIsInvoked() throws Exception {

        //Arrange

        String name = "Informatics";
        String acronym = "INF";
        double quantityOfEcts = 6;
        int durationCourseInSemester = 1;

        // Act
        try (MockedConstruction<Course> courseMocked = mockConstruction(Course.class)) {

            // SUT
            CourseFactory courseFactory = new CourseFactory();

            Course course = courseFactory.createCourse(name, acronym, quantityOfEcts, durationCourseInSemester);

            // Assert
            assertNotNull(course);
            assertEquals(1, courseMocked.constructed().size());
        }
    }
}