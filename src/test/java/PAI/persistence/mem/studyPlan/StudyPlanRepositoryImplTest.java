package PAI.persistence.mem.studyPlan;

import PAI.VOs.*;
import PAI.domain.studyPlan.StudyPlan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudyPlanRepositoryImplTest {

    private IStudyPlanListFactory listFactory;
    private List<StudyPlan> studyPlanList;
    private StudyPlanRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        listFactory = mock(IStudyPlanListFactory.class);
        studyPlanList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(studyPlanList);
        repository = new StudyPlanRepositoryImpl(listFactory);
    }

    @Test
    void testSaveAddsEntityToListAndReturnsIt() {
        //arrange
        StudyPlan studyPlan = mock(StudyPlan.class);

        //act
        StudyPlan saved = repository.save(studyPlan);

        //assert
        assertSame(studyPlan, saved);
        assertTrue(studyPlanList.contains(studyPlan));
    }

    @Test
    void testFindAllReturnsAllSavedEntities() {
        // arrange
        StudyPlan sp1 = mock(StudyPlan.class);
        StudyPlan sp2 = mock(StudyPlan.class);

        repository.save(sp1);
        repository.save(sp2);

        // act
        Iterable<StudyPlan> all = repository.findAll();

        // assert
        List<StudyPlan> asList = new ArrayList<>();
        all.forEach(asList::add);
        assertEquals(2, asList.size());
        assertTrue(asList.contains(sp1));
        assertTrue(asList.contains(sp2));
    }

    @Test
    void testGetAllCourseInStudyPlanListReturnsBackingListInstance() {
        // act
        List<StudyPlan> listFromRepo = repository.getAllStudyPlansList();

        // assert
        assertSame(studyPlanList, listFromRepo);
    }

    @Test
    void testOfIdentityReturnsPresentWhenFound() {
        // arrange
        StudyPlan sp = mock(StudyPlan.class);
        Date date = mock(Date.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        StudyPlanID id = new StudyPlanID(programmeID, date);

        when(sp.identity()).thenReturn(id);
        repository.save(sp);

        // act
        Optional<StudyPlan> found = repository.ofIdentity(id);

        // assert
        assertTrue(found.isPresent());
        assertSame(sp, found.get());
    }

    @Test
    void testOfIdentityReturnsEmptyWhenNotFound() {
        // arrange
        Date date = mock(Date.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        StudyPlanID id = new StudyPlanID(programmeID, date);

        // act
        Optional<StudyPlan> found = repository.ofIdentity(id);

        // assert
        assertFalse(found.isPresent());
    }

    @Test
    void testContainsOfIdentityTrueWhenExists() {
        // arrange
        StudyPlan sp = mock(StudyPlan.class);
        Date date = mock(Date.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        StudyPlanID id = new StudyPlanID(programmeID, date);

        when(sp.identity()).thenReturn(id);
        repository.save(sp);

        // act + assert
        assertTrue(repository.containsOfIdentity(id));
    }

    @Test
    void testContainsOfIdentityFalseWhenNotExists() {
        //arrange
        Date date = mock(Date.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        StudyPlanID id = new StudyPlanID(programmeID, date);
        //act + assert
        assertFalse(repository.containsOfIdentity(id));
    }

    @Test
    void ofIdentityShouldReturnEmptyWhenNoMatchingID() throws Exception {
        //arrange
        ProgrammeID pid1 = new ProgrammeID(new Acronym("ENG"));
        Date date1 = new Date("01-01-2023");
        StudyPlanID id1 = new StudyPlanID(pid1, date1);
        StudyPlanGeneratedID generatedID = new StudyPlanGeneratedID(UUID.randomUUID());

        StudyPlan sp = new StudyPlan(pid1, date1, new DurationInYears(3), new MaxEcts(180), id1, generatedID);
        repository.save(sp);
        ProgrammeID pid2 = new ProgrammeID(new Acronym("DIR"));
        Date date2 = new Date("02-02-2024");
        StudyPlanID id2 = new StudyPlanID(pid2, date2);
        //act
        Optional<StudyPlan> result = repository.ofIdentity(id2);
        //assert
        assertFalse(result.isPresent());
    }

    @Test
    void containsOfIdentityShouldReturnFalseWhenNoMatchingID() throws Exception {
        //arrange
        ProgrammeID pid1 = new ProgrammeID(new Acronym("MAT"));
        Date date1 = new Date("10-03-2023");
        StudyPlanID id1 = new StudyPlanID(pid1, date1);
        StudyPlanGeneratedID generatedID = new StudyPlanGeneratedID(UUID.randomUUID());

        StudyPlan sp = new StudyPlan(pid1, date1, new DurationInYears(3), new MaxEcts(180), id1, generatedID);
        repository.save(sp);
        ProgrammeID pid2 = new ProgrammeID(new Acronym("HIS"));
        Date date2 = new Date("01-04-2024");
        StudyPlanID id2 = new StudyPlanID(pid2, date2);

        // Act + Assert
        assertFalse(repository.containsOfIdentity(id2));
    }

    @Test
    void shouldReturnLatestStudyPlanForProgramme() throws Exception {
        // Arrange
        ProgrammeID pid1 = new ProgrammeID(new Acronym("MAT"));
        Date date1 = new Date("10-03-2020");
        Date date2 = new Date("10-03-2023");
        StudyPlanID id1 = new StudyPlanID(pid1, date1);
        StudyPlanID id2 = new StudyPlanID(pid1, date2);
        StudyPlanGeneratedID generatedID = new StudyPlanGeneratedID(UUID.randomUUID());
        StudyPlanGeneratedID generatedID2 = new StudyPlanGeneratedID(UUID.randomUUID());

        StudyPlan sp = new StudyPlan(pid1, date1, new DurationInYears(3), new MaxEcts(180), id1, generatedID);
        StudyPlan sp2 = new StudyPlan(pid1, date2, new DurationInYears(3), new MaxEcts(180), id2, generatedID2);
        repository.save(sp);
        repository.save(sp2);

        // Assert
        StudyPlanID latest = repository.findLatestByProgrammeID(pid1);

        // Assert
        assertEquals(new Date("10-03-2023"), latest.getDate());
    }

    @Test
    void shouldThrowExceptionIfNoStudyPlanExistForProgrammeID() throws Exception {
        // Arrange
        ProgrammeID pid1 = new ProgrammeID(new Acronym("MAT"));
        Date date1 = new Date("10-03-2020");
        StudyPlanID id1 = new StudyPlanID(pid1, date1);
        StudyPlanGeneratedID generatedID = new StudyPlanGeneratedID(UUID.randomUUID());

        StudyPlan sp = new StudyPlan(pid1, date1, new DurationInYears(3), new MaxEcts(180), id1, generatedID);
        repository.save(sp);

        ProgrammeID unknownProgramme = new ProgrammeID(new Acronym("XYZ"));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> repository.findLatestByProgrammeID(unknownProgramme));
        assertEquals("No study plans found for given ProgrammeID", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfProgrammeIDIsNull() {
        // Arrange

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> repository.findLatestByProgrammeID(null));
    }

    @Test
    void findByGeneratedID_ReturnsStudyPlanWhenFound() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        StudyPlanGeneratedID spGenID = new StudyPlanGeneratedID(uuid);

        StudyPlan studyPlanToFind = mock(StudyPlan.class);
        when(studyPlanToFind.getGeneratedID()).thenReturn(spGenID); // Stub

        repository.save(studyPlanToFind);

        // Act
        Optional<StudyPlan> foundStudyPlan = repository.findByGeneratedID(spGenID);

        // Assert
        assertTrue(foundStudyPlan.isPresent(), "Study plan should be found.");
    }

    @Test
    void findByGeneratedID_ReturnsEmptyWhenNotFound() {
        // Arrange
        UUID nonExistentUuid = UUID.randomUUID();
        StudyPlanGeneratedID nonExistentSpGenID = new StudyPlanGeneratedID(nonExistentUuid);
        StudyPlan studyPlanDouble = mock(StudyPlan.class);

        when(studyPlanDouble.identity()).thenReturn(mock(StudyPlanID.class));
        when(studyPlanDouble.getGeneratedID()).thenReturn(new StudyPlanGeneratedID(UUID.randomUUID()));

        repository.save(studyPlanDouble);

        // Act
        Optional<StudyPlan> foundStudyPlan = repository.findByGeneratedID(nonExistentSpGenID);

        // Assert
        assertFalse(foundStudyPlan.isPresent(), "Study plan should not be found.");
    }

    @Test
    void findByGeneratedID_ThrowsExceptionWhenNullID() {
        // Arrange

        // Act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> repository.findByGeneratedID(null));

        // Assert
        assertEquals("StudyPlanGeneratedID cannot be null.", thrown.getMessage());
    }
}