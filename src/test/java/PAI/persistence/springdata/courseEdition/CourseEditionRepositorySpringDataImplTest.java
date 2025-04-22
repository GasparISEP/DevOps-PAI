package PAI.persistence.springdata.courseEdition;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.CourseEdition;
import PAI.repository.ICourseEditionRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CourseEditionRepositorySpringDataImplTest {

    //-----createAndSaveCourseEdition Tests-----
    @Test
    void shouldReturnAlwaysFalseWhenCreateAndSaveCourseEditionMethodIsCalled() {
        // Arrange
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl();
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
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl();
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
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl();
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
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl();
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
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl();
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
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl();

        // Act
        Iterable<CourseEdition> result = courseEditionRepositorySpringData.findAll();

        // Assert
        assertNull(result);
    }

    //-----ofIdentity Tests-----
    @Test
    void shouldReturnOptionalEmptyWhenOfIdentityMethodIsCalled() {
        // Arrange
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl();
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
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl();
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        // Act
        boolean result = courseEditionRepositorySpringData.containsOfIdentity(courseEditionID);

        // Assert
        assertFalse(result);
    }
}