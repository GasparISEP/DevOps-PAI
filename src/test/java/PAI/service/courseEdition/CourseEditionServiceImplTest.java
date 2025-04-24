package PAI.service.courseEdition;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.CourseEdition;
import PAI.factory.ICourseEditionFactory;
import PAI.repository.ICourseEditionRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

class CourseEditionServiceImplTest {

    //-----Constructor Tests-----
    @Test
    void shouldCreateACourseEditionServiceImpl () {
        // Arrange
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl courseEditionService;

        // Act
        courseEditionService = new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepository);

        // Assert
        assertNotNull(courseEditionService);
    }

    @Test
    void shouldReturnNullWhenCreateCourseEditionMethodIsCall() {
        // Arrange
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepository);
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
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepository);

        // Act
        Iterable<CourseEdition> result = courseEditionService.findAll();

        // Assert
        assertNull(result);
    }

    @Test
    void shouldReturnNullWhenFindByIdMethodIsCall() {
        // Arrange
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepository);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);

        // Act
        List<CourseEditionID> result = courseEditionService.findCourseEditionsByProgrammeEditionID(programmeEditionID);

        // Assert
        assertNull(result);
    }

    @Test
    void shouldReturnNullWhenOfIdentityMethodIsCall() {
        // Arrange
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepository);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        // Act
        CourseEdition result = courseEditionService.ofIdentity(courseEditionID);

        // Assert
        assertNull(result);
    }

    @Test
    void shouldReturnFalseWhenContainsOfIdentityMethodIsCall() {
        // Arrange
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepository);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        // Act
        boolean result = courseEditionService.containsOfIdentity(courseEditionID);

        // Assert
        assertFalse(result);
    }
}
