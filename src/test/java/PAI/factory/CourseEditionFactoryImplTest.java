package PAI.factory;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.CourseEditionDDD;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseEditionFactoryImplTest {

    @Test
    void shouldCreateCourseEditionWhenConstructorIsCalled() throws Exception {
        // Arrange
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);

        try (MockedConstruction<CourseEditionDDD> courseEditionDouble = mockConstruction(CourseEditionDDD.class, (mock, context) -> {
            when(mock.identity()).thenReturn(courseEditionIDDouble);
            when(mock.getProgrammeEditionID()).thenReturn(programmeEditionIDDouble);
        })) {

            // SUT
            ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();

            // Act
            CourseEditionDDD courseEdition = ICourseEditionFactory.newCourseEdition(courseInStudyPlanIDDouble, programmeEditionIDDouble);

            // Assert
            assertNotNull(courseEdition);
            assertEquals(courseEditionIDDouble, courseEdition.identity());
            assertEquals(programmeEditionIDDouble, courseEdition.getProgrammeEditionID());

            List<CourseEditionDDD> courseEditions = courseEditionDouble.constructed();
            assertEquals(1, courseEditions.size());
            assertSame(courseEdition, courseEditions.get(0));
        }
    }

}


