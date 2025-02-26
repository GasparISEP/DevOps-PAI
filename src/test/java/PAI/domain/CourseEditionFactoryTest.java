package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CourseEditionFactoryTest {

    @Test
    void shouldReturnCourseEditionSuccessfully() throws Exception {
        //SUT = CourseEditionfactory - ProgrammeEdition and Course Isolated
        //Arrange
        CourseEditionFactory courseEditionFactory = new CourseEditionFactory();

        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        Course course = mock (Course.class);

        //Act
        CourseEdition result = courseEditionFactory.newCourseEdition(course, programmeEdition);

        //Assert
        assertNotNull(result);

    }

    @Test
    void shouldNotCreateCourseEditionIfCourseIsNull() throws Exception {
        //SUT = CourseEdition - ProgrammeEdition isolated and Course forced to be null
        //Arrange
        CourseEditionFactory courseEditionFactory = new CourseEditionFactory();
        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        Course course = null;

        //Act + Assert
        assertThrows(Exception.class, () -> {courseEditionFactory.newCourseEdition(course, programmeEdition);});

    }

    @Test
    void shouldNotCreateCourseEditionIfProgrammeEditionIsNull() throws Exception {
        //SUT = CourseEdition - ProgrammeEdition forced to be null and Course isolated
        //Arrange
        CourseEditionFactory courseEditionFactory = new CourseEditionFactory();
        ProgrammeEdition programmeEdition = null;
        Course course = mock (Course.class);

        //Act + Assert
        assertThrows(Exception.class, () -> {courseEditionFactory.newCourseEdition(course, programmeEdition);});

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