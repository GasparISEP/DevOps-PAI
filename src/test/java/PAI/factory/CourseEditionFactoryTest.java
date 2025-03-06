package PAI.factory;

import PAI.domain.Course;
import PAI.domain.CourseEdition;
import PAI.domain.ProgrammeEdition;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseEditionFactoryTest {

    @Test
    void shouldCreateCourseEditionWhenConstructorIsCalled() throws Exception {
        //SUT = CourseEditionfactory - ProgrammeEdition and Course Isolated
        //Arrange
            //Doubles' instantiation
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        Course courseDouble = mock (Course.class);

            //SUT
        CourseEditionFactory courseEditionFactory = new CourseEditionFactory();


        try (MockedConstruction<CourseEdition> courseEditionDouble = mockConstruction(CourseEdition.class,(courseEditionMock, context) -> {

            when(courseEditionMock.getCourse()).thenReturn(courseDouble);
            when(courseEditionMock.getProgrammeEdition()).thenReturn(programmeEditionDouble);
        })) {

            // Act
            CourseEdition result = courseEditionFactory.newCourseEdition(courseDouble, programmeEditionDouble);

            // Assert
            assertNotNull(result);
            assertEquals(courseDouble, result.getCourse());
            assertEquals(programmeEditionDouble, result.getProgrammeEdition());

            List<CourseEdition> courseEditions = courseEditionDouble.constructed();
            assertEquals(1, courseEditions.size());

            assertEquals(result, courseEditions.get(0));
        }
    }

    @Test
    void shouldNotCreateCourseEditionIfCourseIsNull() throws Exception {
        //SUT = CourseEdition - ProgrammeEdition isolated and Course forced to be null
        //Arrange
        CourseEditionFactory courseEditionFactory = new CourseEditionFactory();
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        Course course = null;

        //Act + Assert
        assertThrows(Exception.class, () -> {courseEditionFactory.newCourseEdition(course, programmeEditionDouble);});

    }

    @Test
    void shouldNotCreateCourseEditionIfProgrammeEditionIsNull() throws Exception {
        //SUT = CourseEdition - ProgrammeEdition forced to be null and Course isolated
        //Arrange
        CourseEditionFactory courseEditionFactory = new CourseEditionFactory();
        ProgrammeEdition programmeEdition = null;
        Course courseDouble = mock (Course.class);

        //Act + Assert
        assertThrows(Exception.class, () -> {courseEditionFactory.newCourseEdition(courseDouble, programmeEdition);});

    }

    @Test
    void shouldNotCreateCourseEditionIfProgrammeEditionAndCourseAreNull() throws Exception {
        //SUT = CourseEdition - ProgrammeEdition and Course forced to be null
        //Arrange
        CourseEditionFactory courseEditionFactory = new CourseEditionFactory();
        ProgrammeEdition programmeEdition = null;
        Course course = null;

        //Act + Assert
        assertThrows(Exception.class, () -> {courseEditionFactory.newCourseEdition(course, programmeEdition);});

    }
}