package PAI.persistence.springdata.courseEdition;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.CourseEdition;
import PAI.mapper.courseEdition.ICourseEditionMapper;
import PAI.repository.ICourseEditionRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CourseEditionRepositorySpringDataImplTest {

    //-----Constructor Tests-----
    @Test
    void shouldCreateCourseEditionRepositorySpringDataImplWhenConstructorIsCalled() {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData;

        // Act
        courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper);

        // Assert
        assertNotNull(courseEditionRepositorySpringData);
    }

    @Test
    void shouldThrowExceptionWhenConstructorIsCalledWithNullCourseEditionRepositorySpringData() {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = null;
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper);});

        // Assert
        assertEquals("CourseEditionRepositorySpringData cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenConstructorIsCalledWithNullCourseEditionMapper() {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = null;

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper);});

        // Assert
        assertEquals("CourseEditionMapper cannot be null", exception.getMessage());
    }


    //-----createAndSaveCourseEdition Tests-----
    @Test
    void shouldReturnAlwaysFalseWhenCreateAndSaveCourseEditionMethodIsCalled() {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper);
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);

        // Act
        boolean result = courseEditionRepositorySpringData.createAndSaveCourseEdition(courseInStudyPlanID, programmeEditionID);

        // Assert
        assertFalse(result);
    }

    //-----findCourseEditionsByProgrammeEdition Tests-----
    @Test
    void shouldReturnNullWhenFindCourseEditionsByProgrammeEditionMethodIsCalled() {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);

        // Act
        List result = courseEditionRepositorySpringData.findCourseEditionsByProgrammeEdition(programmeEditionID);

        // Assert
        assertNull(result);
    }

    //-----findIdByCourseEdition Tests-----
    @Test
    void shouldReturnOptionalEmptyWhenFindIdByCourseEditionMethodIsCalled() {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper);
        CourseEdition courseEdition = mock(CourseEdition.class);

        // Act
        Optional result = courseEditionRepositorySpringData.findIdByCourseEdition(courseEdition);

        // Assert
        assertEquals(Optional.empty(), result);
    }

    //-----findWhichProgrammeEditionBelongsToACourseEdition-----
    @Test
    void shouldReturnNullWhenFindWhichProgrammeEditionBelongsToACourseEditionMethodIsCalled() throws Exception {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper);
        CourseEdition courseEdition = mock(CourseEdition.class);

        // Act
        ProgrammeEditionID result = courseEditionRepositorySpringData.findWhichProgrammeEditionBelongsToACourseEdition(courseEdition);

        // Assert
        assertNull(result);
    }

    //-----save Tests-----
    @Test
    void shouldReturnNullWhenSaveMethodIsCalled() {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper);
        CourseEdition courseEdition = mock(CourseEdition.class);

        // Act
        CourseEdition result = courseEditionRepositorySpringData.save(courseEdition);

        // Assert
        assertNull(result);
    }

    //-----findAll Tests-----
    @Test
    void shouldReturnNullWhenFindAllMethodIsCalled() {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper);

        // Act
        Iterable<CourseEdition> result = courseEditionRepositorySpringData.findAll();

        // Assert
        assertNull(result);
    }

    //-----ofIdentity Tests-----
    @Test
    void shouldReturnOptionalEmptyWhenOfIdentityMethodIsCalled() {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        // Act
        Optional<CourseEdition> result = courseEditionRepositorySpringData.ofIdentity(courseEditionID);

        // Assert
        assertEquals(Optional.empty(), result);
    }

    //-----containsOfIdentity Tests-----
    @Test
    void shouldReturnFalseWhenContainsOfIdentityMethodIsCalled() {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        // Act
        boolean result = courseEditionRepositorySpringData.containsOfIdentity(courseEditionID);

        // Assert
        assertFalse(result);
    }
}