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
import static org.mockito.Mockito.when;

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
    void shouldThrowExceptionWhenCourseEditionServiceImplReceivesANullCourseEditionFactory () {
        // Arrange
        ICourseEditionFactory courseEditionFactory = null;
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepository);});

        // Assert
        assertEquals("courseEditionFactory cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCourseEditionServiceImplReceivesANullCourseEditionRepository () {
        // Arrange
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = null;

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepository);});

        // Assert
        assertEquals("courseEditionRepository cannot be null", exception.getMessage());
    }

    //-----createCourseEdition Tests-----
    @Test
    void shouldReturnNullWhenCreateCourseEditionMethodReceivesANullCourseInStudyPlanID() {
        // Arrange
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepository);

        CourseInStudyPlanID courseInStudyPlanID = null;
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);

        // Act
        CourseEdition result = courseEditionService.createAndSaveCourseEdition(courseInStudyPlanID, programmeEditionID);

        // Assert
        assertNull(result);
    }

    @Test
    void shouldReturnNullWhenCreateCourseEditionMethodReceivesANullProgrammeEditionID() {
        // Arrange
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepository);

        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionID = null;

        // Act
        CourseEdition result = courseEditionService.createAndSaveCourseEdition(courseInStudyPlanID, programmeEditionID);

        // Assert
        assertNull(result);
    }

    @Test
    void shouldReturnAValidCourseEditionWhenCreateCourseEditionMethodCreatesACourseEditionInTheSystem() throws Exception {
        // Arrange
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepository);

        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        CourseEdition courseEdition = mock(CourseEdition.class);

        when(courseEditionFactory.newCourseEdition_2(courseInStudyPlanID, programmeEditionID)).thenReturn(courseEdition);
        when(courseEditionRepository.save(courseEdition)).thenReturn(courseEdition);

        // Act
        CourseEdition result = courseEditionService.createAndSaveCourseEdition(courseInStudyPlanID, programmeEditionID);

        // Assert
        assertNotNull(result);
        assertEquals(courseEdition, result);
    }

    @Test
    void shouldReturnNullWhenCreateCourseEditionMethodCantSaveACourseEditionInTheSystem() throws Exception {
        // Arrange
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepository);

        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        CourseEdition courseEdition = mock(CourseEdition.class);

        when(courseEditionFactory.newCourseEdition_2(courseInStudyPlanID, programmeEditionID)).thenReturn(courseEdition);
        when(courseEditionRepository.save(courseEdition)).thenReturn(null);

        // Act
        CourseEdition result = courseEditionService.createAndSaveCourseEdition(courseInStudyPlanID, programmeEditionID);

        // Assert
        assertNull(result);
    }

    @Test
    void shouldReturnNullWhenCreateCourseEditionMethodCantCreateACourseEditionInTheSystem() throws Exception {
        // Arrange
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepository);

        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);

        when(courseEditionFactory.newCourseEdition_2(courseInStudyPlanID, programmeEditionID)).thenThrow(IllegalArgumentException.class);

        // Act
        CourseEdition result = courseEditionService.createAndSaveCourseEdition(courseInStudyPlanID, programmeEditionID);

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
