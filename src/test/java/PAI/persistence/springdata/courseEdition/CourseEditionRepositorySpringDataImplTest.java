package PAI.persistence.springdata.courseEdition;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.CourseEdition;
import PAI.mapper.courseEdition.ICourseEditionIDMapper;
import PAI.mapper.courseEdition.ICourseEditionMapper;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import PAI.repository.ICourseEditionRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseEditionRepositorySpringDataImplTest {

    //-----Constructor Tests-----
    @Test
    void shouldCreateCourseEditionRepositorySpringDataImplWhenConstructorIsCalled() {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData;

        // Act
        courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper);

        // Assert
        assertNotNull(courseEditionRepositorySpringData);
    }

    @Test
    void shouldThrowExceptionWhenConstructorIsCalledWithNullCourseEditionRepositorySpringData() {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = null;
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper);});

        // Assert
        assertEquals("CourseEditionRepositorySpringData cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenConstructorIsCalledWithNullCourseEditionMapper() {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = null;
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper);});

        // Assert
        assertEquals("CourseEditionMapper cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenConstructorIsCalledWithNullCourseEditionIDMapper() {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = null;

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper);});

        // Assert
        assertEquals("CourseEditionIDMapper cannot be null", exception.getMessage());
    }

    //-----findCourseEditionsByProgrammeEdition Tests-----
    @Test
    void shouldReturnNullWhenFindCourseEditionsByProgrammeEditionMethodIsCalled() {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper);
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
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper);
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
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper);
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
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper);
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
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper);

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
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        // Act
        Optional<CourseEdition> result = courseEditionRepositorySpringData.ofIdentity(courseEditionID);

        // Assert
        assertEquals(Optional.empty(), result);
    }

    //-----containsOfIdentity Tests-----
    @Test
    void shouldReturnFalseWhenToContainsOfIdentityMethodReceivesANullCourseEditionID() {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper);

        CourseEditionID courseEditionID = null;

        // Act
        boolean result = courseEditionRepositorySpringData.containsOfIdentity(courseEditionID);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfCourseEditionIDAlreadyExistsInTheRepository() throws Exception {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper);

        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);

        when(courseEditionIDMapper.toDataModel(courseEditionID)).thenReturn(courseEditionIDDataModel);
        when(courseEditionRepoSD.existsById(courseEditionIDDataModel)).thenReturn(true);

        // Act
        boolean result = courseEditionRepositorySpringData.containsOfIdentity(courseEditionID);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfCourseEditionIDDoesNotExistsInTheRepository() throws Exception {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper);

        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);

        when(courseEditionIDMapper.toDataModel(courseEditionID)).thenReturn(courseEditionIDDataModel);
        when(courseEditionRepoSD.existsById(courseEditionIDDataModel)).thenReturn(false);

        // Act
        boolean result = courseEditionRepositorySpringData.containsOfIdentity(courseEditionID);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfCourseEditionIDMapperThrowsAnException() throws Exception {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper);

        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        when(courseEditionIDMapper.toDataModel(courseEditionID)).thenThrow(IllegalArgumentException.class);

        // Act
        boolean result = courseEditionRepositorySpringData.containsOfIdentity(courseEditionID);

        // Assert
        assertFalse(result);
    }
}