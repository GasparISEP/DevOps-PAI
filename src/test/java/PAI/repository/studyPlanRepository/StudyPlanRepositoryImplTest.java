package PAI.repository.studyPlanRepository;

import PAI.VOs.*;
import PAI.domain.studyPlan.StudyPlan;
import PAI.persistence.mem.studyPlan.IStudyPlanListFactory;
import PAI.persistence.mem.studyPlan.StudyPlanRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        ProgrammeID pid1 = new ProgrammeID(new NameWithNumbersAndSpecialChars("ENGENHARIA"), new Acronym("ENG"));
        Date date1 = new Date("01-01-2023");
        StudyPlanID id1 = new StudyPlanID(pid1, date1);
        StudyPlan sp = new StudyPlan(pid1, date1, new DurationInYears(3), new MaxEcts(180), id1);
        repository.save(sp);
        ProgrammeID pid2 = new ProgrammeID(new NameWithNumbersAndSpecialChars("DIREITO"), new Acronym("DIR"));
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
        ProgrammeID pid1 = new ProgrammeID(new NameWithNumbersAndSpecialChars("MATEMÁTICA"), new Acronym("MAT"));
        Date date1 = new Date("10-03-2023");
        StudyPlanID id1 = new StudyPlanID(pid1, date1);
        StudyPlan sp = new StudyPlan(pid1, date1, new DurationInYears(3), new MaxEcts(180), id1);
        repository.save(sp);
        ProgrammeID pid2 = new ProgrammeID(new NameWithNumbersAndSpecialChars("HISTÓRIA"), new Acronym("HIS"));
        Date date2 = new Date("01-04-2024");
        StudyPlanID id2 = new StudyPlanID(pid2, date2);

        // Act + Assert
        assertFalse(repository.containsOfIdentity(id2));
    }
}