package PAI.service.courseEdition;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.CourseEdition;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

class CourseEditionServiceImplTest {

    @Test
    void shouldCreateACourseEditionServiceImpl () {
        // Arrange
        CourseEditionServiceImpl courseEditionService;

        // Act
        courseEditionService = new CourseEditionServiceImpl();

        // Assert
        assertNotNull(courseEditionService);
    }

    @Test
    void shouldReturnNullWhenCreateCourseEditionMethodIsCall() {
        // Arrange
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl();
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);

        // Act
        CourseEdition result = courseEditionService.createCourseEdition(courseInStudyPlanID, programmeEditionID);

        // Assert
        assertNull(result);
    }

    @Test
    void shouldReturnNullWhenFindAllMethodIsCall() {
        // Arrange
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl();

        // Act
        Iterable<CourseEdition> result = courseEditionService.findAll();

        // Assert
        assertNull(result);
    }

    @Test
    void shouldReturnNullWhenFindByIdMethodIsCall() {
        // Arrange
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl();
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);

        // Act
        List<CourseEditionID> result = courseEditionService.findCourseEditionsByProgrammeEditionID(programmeEditionID);

        // Assert
        assertNull(result);
    }

    @Test
    void shouldReturnNullWhenOfIdentityMethodIsCall() {
        // Arrange
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl();
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        // Act
        CourseEdition result = courseEditionService.ofIdentity(courseEditionID);

        // Assert
        assertNull(result);
    }

    @Test
    void shouldReturnFalseWhenContainsOfIdentityMethodIsCall() {
        // Arrange
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl();
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        // Act
        boolean result = courseEditionService.containsOfIdentity(courseEditionID);

        // Assert
        assertFalse(result);
    }
}
