package PAI.service.courseEdition;

import PAI.VOs.*;
import PAI.domain.courseEdition.CourseEdition;
import PAI.domain.courseEdition.ICourseEditionFactory;
import PAI.domain.repositoryInterfaces.courseEdition.ICourseEditionRepository;
import PAI.exception.NotFoundException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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

    //-----createAndSaveCourseEdition Tests-----
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
    void shouldReturnNullWhenCreateCourseEditionMethodReceivesANullProgrammeEditionIDAndNullCourseInStudyPlan() {
        // Arrange
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepository);

        CourseInStudyPlanID courseInStudyPlanID = null;
        ProgrammeEditionID programmeEditionID = null;

        // Act
        CourseEdition result = courseEditionService.createAndSaveCourseEdition(courseInStudyPlanID, programmeEditionID);

        // Assert
        assertNull(result);
    }

    @Test
    void shouldReturnASavedCourseEditionWhenCreateAndSaveCourseEditionMethodCreatesACourseEditionInTheSystem() throws Exception {
        // Arrange
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepository);

        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        CourseEdition courseEdition = mock(CourseEdition.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        when(courseEditionFactory.createCourseEditionToDomain(courseInStudyPlanID, programmeEditionID)).thenReturn(courseEdition);
        when(courseEdition.identity()).thenReturn(courseEditionID);
        when(courseEditionRepository.containsOfIdentity(courseEditionID)).thenReturn(false);
        when(courseEditionRepository.save(courseEdition)).thenReturn(courseEdition);

        // Act
        CourseEdition result = courseEditionService.createAndSaveCourseEdition(courseInStudyPlanID, programmeEditionID);

        // Assert
        assertNotNull(result);
        assertEquals(courseEdition, result);
    }

    @Test
    void shouldReturnNullWhenCourseEditionAlreadyExistsInTheSystem() throws Exception {
        // Arrange
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepository);

        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        CourseEdition courseEdition = mock(CourseEdition.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        when(courseEditionFactory.createCourseEditionToDomain(courseInStudyPlanID, programmeEditionID)).thenReturn(courseEdition);
        when(courseEdition.identity()).thenReturn(courseEditionID);
        when(courseEditionRepository.containsOfIdentity(courseEditionID)).thenReturn(true);

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

        when(courseEditionFactory.createCourseEditionToDomain(courseInStudyPlanID, programmeEditionID)).thenThrow(IllegalArgumentException.class);

        // Act
        CourseEdition result = courseEditionService.createAndSaveCourseEdition(courseInStudyPlanID, programmeEditionID);

        // Assert
        assertNull(result);
    }

    //-----findAll Tests-----
    @Test
    void shouldReturnAnIterableListWithCourseEditionsWhenFindAllMethodIsCallIfTheSystemContainsCourseEdition() {
        // Arrange
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepository);

        when(courseEditionRepository.findAll()).thenReturn(List.of());

        // Act
        Iterable<CourseEdition> result = courseEditionService.findAll();

        // Assert
        assertNotNull(result);
        assertFalse(result.iterator().hasNext());
    }

    @Test
    void shouldReturnEmptyIterableWhenFindAllMethodIsCallIfTheSystemDoesNotContainAnyCourseEdition() {
        // Arrange
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepository);

        CourseEdition courseEdition1 = mock(CourseEdition.class);
        CourseEdition courseEdition2 = mock(CourseEdition.class);
        CourseEdition courseEdition3 = mock(CourseEdition.class);

        when(courseEditionRepository.findAll()).thenReturn(List.of(courseEdition1, courseEdition2, courseEdition3));

        // Act
        Iterable<CourseEdition> result = courseEditionService.findAll();

        // Assert
        assertNotNull(result);
        assertTrue(result.iterator().hasNext());
        List<CourseEdition> verifyList = (List<CourseEdition>) result;
        assertTrue(verifyList.size() == 3);
        assertTrue(verifyList.contains(courseEdition1));
        assertTrue(verifyList.contains(courseEdition2));
        assertTrue(verifyList.contains(courseEdition3));
    }

    //-----findCourseEditionsByProgrammeEditionID Tests-----
    @Test
    void shouldReturnAListContainingAllTheCoursesEditionIDInTheSystemThatHaveTheGivenProgrammeEditionID() {
        // Arrange
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepository);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);

        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        CourseEditionID courseEditionID2 = mock(CourseEditionID.class);
        CourseEditionID courseEditionID3 = mock(CourseEditionID.class);

        when(courseEditionRepository.findCourseEditionsByProgrammeEditionID(programmeEditionID)).thenReturn(List.of(courseEditionID1, courseEditionID2, courseEditionID3));

        // Act
        List<CourseEditionID> result = courseEditionService.findCourseEditionsByProgrammeEditionID(programmeEditionID);

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.contains(courseEditionID1));
        assertTrue(result.contains(courseEditionID2));
        assertTrue(result.contains(courseEditionID3));
    }

    @Test
    void shouldReturnAnEmptyListOfCoursesEditionIDIfTheSystemDoesNotHaveAnyCourseEditionWithTheGivenProgrammeEditionID() {
        // Arrange
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepository);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);

        when(courseEditionRepository.findCourseEditionsByProgrammeEditionID(programmeEditionID)).thenReturn(List.of());

        // Act
        List<CourseEditionID> result = courseEditionService.findCourseEditionsByProgrammeEditionID(programmeEditionID);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void shouldReturnAnEmptyListOfCoursesEditionIDIfTheGivenProgrammeEditionIDIsNull() {
        // Arrange
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepository);
        ProgrammeEditionID programmeEditionID = null;

        // Act
        List<CourseEditionID> result = courseEditionService.findCourseEditionsByProgrammeEditionID(programmeEditionID);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void shouldReturnAnEmptyListOfCoursesEditionIDIfCourseEditionRepositoryThrowsAnException() {
        // Arrange
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepository);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);

        when(courseEditionRepository.findCourseEditionsByProgrammeEditionID(programmeEditionID)).thenThrow(IllegalArgumentException.class);

        // Act
        List<CourseEditionID> result = courseEditionService.findCourseEditionsByProgrammeEditionID(programmeEditionID);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    //------ofIdentity Tests-----
    @Test
    void shouldReturnEmptyOptionalIfTheGivenCourseEditionIDIsNull() {
        // Arrange
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepository);
        CourseEditionID courseEditionID = null;

        // Act
        Optional<CourseEdition> result = courseEditionService.ofIdentity(courseEditionID);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(courseEditionRepository, times(0)).ofIdentity(courseEditionID);
    }

    @Test
    void shouldReturnEmptyOptionalIfThereIsNoCourseEditionInTheSystemThatHasTheGivenCourseEditionID() {
        // Arrange
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepository);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        when(courseEditionRepository.ofIdentity(courseEditionID)).thenReturn(Optional.empty());

        // Act
        Optional<CourseEdition> result = courseEditionService.ofIdentity(courseEditionID);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(courseEditionRepository, times(1)).ofIdentity(courseEditionID);
    }

    @Test
    void shouldReturnOptionalWithTheCourseEditionInTheSystemThatContainsTheGivenCourseEditionID() {
        // Arrange
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepository);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        CourseEdition expectedCourseEdition = mock(CourseEdition.class);
        Optional<CourseEdition> expectedOptional = Optional.of(expectedCourseEdition);


        when(courseEditionRepository.ofIdentity(courseEditionID)).thenReturn(expectedOptional);

        // Act
        Optional<CourseEdition> result = courseEditionService.ofIdentity(courseEditionID);

        // Assert
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(expectedOptional, result);
        verify(courseEditionRepository, times(1)).ofIdentity(courseEditionID);
    }

    //-----containsOfIdentity Tests-----
    @Test
    void shouldReturnFalseWhenThereIsNoCourseEditionInTheSystemWithCourseEditionIDGiven() {
        // Arrange
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepository);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        when(courseEditionRepository.containsOfIdentity(courseEditionID)).thenReturn(false);
        // Act
        boolean result = courseEditionService.containsOfIdentity(courseEditionID);

        // Assert
        assertFalse(result);
        verify(courseEditionRepository, times(1)).containsOfIdentity(courseEditionID);
    }

    @Test
    void shouldReturnTrueWhenThereIsACourseEditionInTheSystemWithCourseEditionIDGiven() {
        // Arrange
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepository);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        when(courseEditionRepository.containsOfIdentity(courseEditionID)).thenReturn(true);
        // Act
        boolean result = courseEditionService.containsOfIdentity(courseEditionID);

        // Assert
        assertTrue(result);
        verify(courseEditionRepository, times(1)).containsOfIdentity(courseEditionID);
    }

    @Test
    void shouldReturnFalseWhenContainsOfIdentityReceivesANullCourseEditionID() {
        // Arrange
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepository);
        CourseEditionID courseEditionID = null;

        // Act
        boolean result = courseEditionService.containsOfIdentity(courseEditionID);

        // Assert
        assertFalse(result);
        verify(courseEditionRepository, times(0)).containsOfIdentity(courseEditionID);
    }

    //-----findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID Tests-----
    @Test
    void shouldReturnListOfCourseEditionIDsWhenBothParametersAreValid() throws Exception {
        // Arrange
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepository);

        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        List<CourseEditionID> expectedCourseEditions = List.of(mock(CourseEditionID.class));

        when(courseEditionRepository.findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID(programmeEditionID, courseInStudyPlanID))
                .thenReturn(expectedCourseEditions);

        // Act
        List<CourseEditionID> result = courseEditionService.findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID(
                programmeEditionID, courseInStudyPlanID);

        // Assert
        assertNotNull(result);
        assertEquals(expectedCourseEditions, result);
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionIDIsNull() throws Exception {
        // Arrange
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepository);

        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            courseEditionService.findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID(null, courseInStudyPlanID);
        });

        assertEquals("ProgrammeEditionID cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCourseInStudyPlanIDIsNull() throws Exception {
        // Arrange
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepository);

        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            courseEditionService.findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID(programmeEditionID, null);
        });

        assertEquals("CourseInStudyPlanID cannot be null", exception.getMessage());
    }

    @Test
    void shouldReturnEmptyListWhenNoMatchingCourseEditionsFound() throws Exception {
        // Arrange
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepository);

        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);

        when(courseEditionRepository.findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID(programmeEditionID, courseInStudyPlanID))
                .thenReturn(List.of());

        // Act
        List<CourseEditionID> result = courseEditionService.findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID(
                programmeEditionID, courseInStudyPlanID);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldPropagateExceptionWhenRepositoryThrowsException() throws Exception {
        // Arrange
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepository);

        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);

        when(courseEditionRepository.findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID(programmeEditionID, courseInStudyPlanID))
                .thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            courseEditionService.findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID(
                    programmeEditionID, courseInStudyPlanID);
        });

        assertEquals("Database error", exception.getMessage());
    }

    @Test
    void shouldReturnCourseEditionID_WhenFindCourseEditionByGeneratedID_FindsACourseEdition () throws Exception {
        // Arrange
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ICourseEditionFactory factory = mock(ICourseEditionFactory.class);
        ICourseEditionService courseEditionService = new CourseEditionServiceImpl(factory, courseEditionRepository);

        CourseEditionGeneratedID generatedIDDouble = mock(CourseEditionGeneratedID.class);
        CourseEdition courseEditionDouble = mock(CourseEdition.class);
        CourseEditionID expectedCourseEditionIDDouble = mock(CourseEditionID.class);

        when(generatedIDDouble.getCourseEditionGeneratedID()).thenReturn(UUID.randomUUID());

        when(courseEditionRepository.findCourseEditionByGeneratedId(generatedIDDouble))
                .thenReturn(Optional.of(courseEditionDouble));
        when(courseEditionDouble.identity()).thenReturn(expectedCourseEditionIDDouble);

        // Act
        CourseEditionID result = courseEditionService.findCourseEditionIDByGeneratedID(generatedIDDouble);

        // Assert
        assertEquals(result, expectedCourseEditionIDDouble);
    }

    @Test
    void shouldThrowNotFoundException_WhenFindCourseEditionByGeneratedID_DoesNotFindACourseEdition () throws Exception {
        // Arrange
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ICourseEditionFactory factory = mock(ICourseEditionFactory.class);
        ICourseEditionService courseEditionService = new CourseEditionServiceImpl(factory, courseEditionRepository);

        CourseEditionGeneratedID generatedIDDouble = mock(CourseEditionGeneratedID.class);

        when(generatedIDDouble.getCourseEditionGeneratedID()).thenReturn(UUID.randomUUID());

        when(courseEditionRepository.findCourseEditionByGeneratedId(generatedIDDouble))
                .thenReturn(Optional.empty());

        // Act
        Exception expectedException = assertThrows(NotFoundException.class, () -> {
            courseEditionService.findCourseEditionIDByGeneratedID(generatedIDDouble);
        });

        // Assert
        assertEquals("CourseEdition not found with Universally Unique ID: " + generatedIDDouble, expectedException.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenFindCourseEditionByGeneratedIDReceivesNullGeneratedID() throws Exception {
        // Arrange
        ICourseEditionFactory factory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl(factory, courseEditionRepository);

        CourseEditionGeneratedID generatedID = null;

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            courseEditionService.findCourseEditionIDByGeneratedID(generatedID);
        });

        assertEquals("Course Edition Generated ID cannot be null.", exception.getMessage());
    }

    @Test
    void getCourseEditionsByProgrammeIDAndCourseID_returnsMatchingCourseEditions() {
        // Arrange
        ICourseEditionFactory factory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository repository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl service = new CourseEditionServiceImpl(factory, repository);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        CourseID courseID = mock(CourseID.class);
        CourseEdition edition1 = mock(CourseEdition.class);
        CourseEdition edition2 = mock(CourseEdition.class);
        when(repository.findByProgrammeIDAndCourseID(programmeID, courseID)).thenReturn(List.of(edition1, edition2));

        // Act
        List<CourseEdition> result = service.getCourseEditionsByProgrammeIDAndCourseID(programmeID, courseID);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(edition1));
        assertTrue(result.contains(edition2));
    }

    @Test
    void getCourseEditionsByProgrammeIDAndCourseID_returnsEmptyListWhenNoMatch() {
        // Arrange
        ICourseEditionFactory factory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository repository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl service = new CourseEditionServiceImpl(factory, repository);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        CourseID courseID = mock(CourseID.class);
        when(repository.findByProgrammeIDAndCourseID(programmeID, courseID)).thenReturn(List.of());

        // Act
        List<CourseEdition> result = service.getCourseEditionsByProgrammeIDAndCourseID(programmeID, courseID);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void getCourseEditionsByProgrammeIDAndCourseID_returnsEmptyListWhenRepositoryReturnsNull() {
        // Arrange
        ICourseEditionFactory factory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository repository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl service = new CourseEditionServiceImpl(factory, repository);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        CourseID courseID = mock(CourseID.class);
        when(repository.findByProgrammeIDAndCourseID(programmeID, courseID)).thenReturn(null);

        // Act
        List<CourseEdition> result = service.getCourseEditionsByProgrammeIDAndCourseID(programmeID, courseID);

        // Assert
        assertNull(result);
    }

    @Test
    void getSchoolYearIDsFromCourseEditions_returnsCorrectSchoolYearIDs() {
        // Arrange
        ICourseEditionFactory factory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository repository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl service = new CourseEditionServiceImpl(factory, repository);

        CourseEdition ce1 = mock(CourseEdition.class);
        CourseEdition ce2 = mock(CourseEdition.class);
        ProgrammeEditionID peid1 = mock(ProgrammeEditionID.class);
        ProgrammeEditionID peid2 = mock(ProgrammeEditionID.class);
        SchoolYearID syid1 = mock(SchoolYearID.class);
        SchoolYearID syid2 = mock(SchoolYearID.class);
        when(ce1.getProgrammeEditionID()).thenReturn(peid1);
        when(ce2.getProgrammeEditionID()).thenReturn(peid2);
        when(peid1.getSchoolYearID()).thenReturn(syid1);
        when(peid2.getSchoolYearID()).thenReturn(syid2);

        List<CourseEdition> courseEditions = List.of(ce1, ce2);

        // Act
        List<SchoolYearID> result = service.getSchoolYearIDsFromCourseEditions(courseEditions);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(syid1));
        assertTrue(result.contains(syid2));
    }

    @Test
    void getSchoolYearIDsFromCourseEditions_returnsEmptyListForEmptyInput() {
        // Arrange
        ICourseEditionFactory factory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository repository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl service = new CourseEditionServiceImpl(factory, repository);

        // Act
        List<SchoolYearID> result = service.getSchoolYearIDsFromCourseEditions(List.of());

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void buildCourseEditionID_shouldReturnValidCourseEditionID() {
        ICourseEditionFactory factory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository repository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl service = new CourseEditionServiceImpl(factory, repository);
        String programmeAcronym = "LEIC";
        String schoolYearId = "123e4567-e89b-12d3-a456-426614174000";
        String courseAcronym = "ESOFT";
        String courseName = "Engineering Software";
        String localDate = "2024-06-18";

        CourseEditionID id = service.buildCourseEditionID(programmeAcronym, schoolYearId, courseAcronym, courseName, localDate);
        assertNotNull(id);
        assertEquals("LEIC", id.getProgrammeEditionID().getProgrammeID().getAcronym().getValue());
        assertEquals("ESOFT", id.getCourseInStudyPlanID().getCourseID().getAcronym().getValue());
        assertEquals("Engineering Software", id.getCourseInStudyPlanID().getCourseID().getName().getName());
        assertEquals("18-06-2024", id.getCourseInStudyPlanID().getStudyPlanID().getDate().toString());
    }

    @Test
    void buildCourseEditionID_shouldThrowExceptionForInvalidUUID() {
        ICourseEditionFactory factory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository repository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl service = new CourseEditionServiceImpl(factory, repository);
        String programmeAcronym = "LEIC";
        String schoolYearId = "invalid-uuid";
        String courseAcronym = "ESOFT";
        String courseName = "Engineering Software";
        String localDate = "2024-06-18";

        assertThrows(IllegalArgumentException.class, () ->
            service.buildCourseEditionID(programmeAcronym, schoolYearId, courseAcronym, courseName, localDate)
        );
    }

    @Test
    void buildCourseEditionID_shouldThrowExceptionForInvalidDate() {
        ICourseEditionFactory factory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository repository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl service = new CourseEditionServiceImpl(factory, repository);
        String programmeAcronym = "LEIC";
        String schoolYearId = "123e4567-e89b-12d3-a456-426614174000";
        String courseAcronym = "ESOFT";
        String courseName = "Engineering Software";
        String localDate = "invalid-date";

        assertThrows(Exception.class, () ->
            service.buildCourseEditionID(programmeAcronym, schoolYearId, courseAcronym, courseName, localDate)
        );
    }
}
