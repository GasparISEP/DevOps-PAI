package PAI.service.courseEdition;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.TeacherID;
import PAI.domain.CourseEdition;
import PAI.factory.ICourseEditionFactory;
import PAI.repository.ICourseEditionRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

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
    void shouldReturnAValidCourseEditionWhenCreateCourseEditionMethodCreatesACourseEditionInTheSystem() throws Exception {
        // Arrange
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl courseEditionService = new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepository);

        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        CourseEdition courseEdition = mock(CourseEdition.class);

        when(courseEditionFactory.createCourseEditionToDomain(courseInStudyPlanID, programmeEditionID)).thenReturn(courseEdition);
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

        when(courseEditionFactory.createCourseEditionToDomain(courseInStudyPlanID, programmeEditionID)).thenReturn(courseEdition);
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

    //----- assignRucToCourseEdition Tests -----

    @Test
    void shouldReturnTrueWhenAssignRucAndEditionExists() throws Exception {
        // Arrange
        ICourseEditionFactory factory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository repo = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl service = new CourseEditionServiceImpl(factory, repo);

        TeacherID tId = mock(TeacherID.class);
        CourseEditionID ceId = mock(CourseEditionID.class);
        CourseEdition ce = mock(CourseEdition.class);

        // este "when" é obrigatório para corrigir o comportamento:
        when(repo.ofIdentity(ceId)).thenReturn(Optional.of(ce));
        when(repo.save(ce)).thenReturn(ce);
        when(ce.setRuc(tId)).thenReturn(true); // <- **esta linha é a chave**

        // Act
        boolean result = service.assignRucToCourseEdition(tId, ceId);

        // Assert
        assertTrue(result);
        verify(ce).setRuc(tId);
        verify(repo).save(ce);
    }


    @Test
    void shouldReturnFalseWhenAssignRucAndEditionNotExists() throws Exception {
        // Arrange
        ICourseEditionFactory factory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository repo = mock(ICourseEditionRepository.class);
        CourseEditionServiceImpl service = new CourseEditionServiceImpl(factory, repo);

        TeacherID tId = mock(TeacherID.class);
        CourseEditionID ceId = mock(CourseEditionID.class);

        when(repo.ofIdentity(ceId)).thenReturn(Optional.empty());

        // Act
        boolean result = service.assignRucToCourseEdition(tId, ceId);

        // Assert
        assertFalse(result);
        verify(repo, never()).save(any());
    }
}
