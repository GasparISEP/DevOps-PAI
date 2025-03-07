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
            Course actualCourse = (Course) context.arguments().get(0);
            ProgrammeEdition actualProgrammeEdition = (ProgrammeEdition) context.arguments().get(1);
            when(courseEditionMock.getCourse()).thenReturn(actualCourse);
            when(courseEditionMock.getProgrammeEdition()).thenReturn(actualProgrammeEdition);
        })) {

            // Act
            CourseEdition courseEdition = courseEditionFactory.newCourseEdition(courseDouble, programmeEditionDouble);

            // Assert
            assertNotNull(courseEdition);
            assertEquals(courseDouble, courseEdition.getCourse());
            assertEquals(programmeEditionDouble, courseEdition.getProgrammeEdition());

            List<CourseEdition> courseEditions = courseEditionDouble.constructed();
            assertEquals(1, courseEditions.size());

            assertEquals(courseEdition, courseEditions.get(0));
        }
    }

}