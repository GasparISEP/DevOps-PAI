package PAI.repository.studyPlanRepository;

import PAI.VOs.*;
import PAI.domain.studyPlan.StudyPlan;
import PAI.domain.studyPlan.IStudyPlanFactory;
import PAI.persistence.mem.studyPlanRepository.IStudyPlanListFactory;
import PAI.persistence.mem.studyPlanRepository.StudyPlanRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudyPlanRepositoryImplTest {

    @Test
    void testCreateStudyPlanNewPlan() throws Exception {
        // Arrange
        IStudyPlanFactory factory = mock(IStudyPlanFactory.class);
        IStudyPlanListFactory listFactory = mock(IStudyPlanListFactory.class);
        List<StudyPlan> studyPlanList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(studyPlanList);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        MaxEcts quantityOfEcts = mock(MaxEcts.class);


        when(factory.newStudyPlan_2(programmeID, implementationDate, durationInYears, quantityOfEcts))
                .thenAnswer(invocation -> new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts));

        StudyPlanRepositoryImpl repository = new StudyPlanRepositoryImpl(factory, listFactory);

        // Act
        boolean created = repository.createStudyPlan_2(programmeID, implementationDate, durationInYears, quantityOfEcts);

        // Assert
        assertTrue(created);
        assertEquals(1, repository.getAllStudyPlans_2().size());
    }

    @Test
    void testCreateStudyPlanDuplicatePlan() throws Exception {
        // Arrange
        IStudyPlanFactory factory = mock(IStudyPlanFactory.class);
        IStudyPlanListFactory listFactory = mock(IStudyPlanListFactory.class);
        List<StudyPlan> studyPlanList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(studyPlanList);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        MaxEcts quantityOfEcts = mock(MaxEcts.class);

        StudyPlanRepositoryImpl repository = new StudyPlanRepositoryImpl(factory, listFactory);

        // Act
        boolean createdFirstStudyPlan = repository.createStudyPlan_2(programmeID, implementationDate, durationInYears, quantityOfEcts);
        boolean createdSecondStudyPlan = repository.createStudyPlan_2(programmeID, implementationDate, durationInYears, quantityOfEcts);

        // Assert
        assertTrue(createdFirstStudyPlan);
        assertFalse(createdSecondStudyPlan);
        assertEquals(1, repository.getAllStudyPlans_2().size());
    }

    @Test
    void testGetAllStudyPlansReturnsMultiplePlans() throws Exception {
        // Arrange
        IStudyPlanFactory factory = mock(IStudyPlanFactory.class);
        IStudyPlanListFactory listFactory = mock(IStudyPlanListFactory.class);
        List<StudyPlan> studyPlanList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(studyPlanList);

        ProgrammeID programmeID1 = mock(ProgrammeID.class);
        ProgrammeID programmeID2 = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        MaxEcts quantityOfEcts = mock(MaxEcts.class);

        when(factory.newStudyPlan_2(programmeID1, implementationDate, durationInYears, quantityOfEcts))
                .thenAnswer(invocation -> new StudyPlan(programmeID1, implementationDate, durationInYears, quantityOfEcts));
        when(factory.newStudyPlan_2(programmeID2, implementationDate, durationInYears, quantityOfEcts))
                .thenAnswer(invocation -> new StudyPlan(programmeID2, implementationDate, durationInYears, quantityOfEcts));

        StudyPlanRepositoryImpl repository = new StudyPlanRepositoryImpl(factory, listFactory);

        // Act
        boolean created1 = repository.createStudyPlan_2(programmeID1, implementationDate, durationInYears, quantityOfEcts);
        boolean created2 = repository.createStudyPlan_2(programmeID2, implementationDate, durationInYears, quantityOfEcts);
        List<StudyPlan> allPlans = repository.getAllStudyPlans_2();

        // Assert
        assertTrue(created1);
        assertTrue(created2);
        assertEquals(2, allPlans.size());
    }

    @Test
    void testFindByStudyPlanIDFound() throws Exception {
        // Arrange
        IStudyPlanFactory factory = mock(IStudyPlanFactory.class);
        IStudyPlanListFactory listFactory = mock(IStudyPlanListFactory.class);
        List<StudyPlan> studyPlanList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(studyPlanList);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        MaxEcts quantityOfEcts = mock(MaxEcts.class);

        when(factory.newStudyPlan_2(programmeID, implementationDate, durationInYears, quantityOfEcts))
                .thenAnswer(invocation -> new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts));

        StudyPlanRepositoryImpl repository = new StudyPlanRepositoryImpl(factory, listFactory);

        // Act
        repository.createStudyPlan_2(programmeID, implementationDate, durationInYears, quantityOfEcts);
        StudyPlan createdPlan = repository.getAllStudyPlans_2().get(0);
        StudyPlanID studyPlanID = createdPlan.identity();

        // Act
        Optional<StudyPlan> foundPlan = repository.ofIdentity(studyPlanID);

        // Assert
        assertTrue(foundPlan.isPresent());
        assertEquals(createdPlan, foundPlan.get());
    }

    @Test
    void testFindByStudyPlanIDNotFound() throws Exception {
        // Arrange
        IStudyPlanFactory factory = mock(IStudyPlanFactory.class);
        IStudyPlanListFactory listFactory = mock(IStudyPlanListFactory.class);
        List<StudyPlan> studyPlanList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(studyPlanList);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);

        StudyPlanRepositoryImpl repository = new StudyPlanRepositoryImpl(factory, listFactory);

        // Act
        StudyPlanID nonExistentID = new StudyPlanID(programmeID, implementationDate);
        Optional<StudyPlan> foundPlanOpt = repository.ofIdentity(nonExistentID);

        // Assert
        assertFalse(foundPlanOpt.isPresent());
    }

    @Test
    void shouldGetAllStudyPlansByProgrammeID() throws Exception {
        // arrange
        StudyPlan studyPlan1 = mock(StudyPlan.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        when(studyPlan1.getProgrammeID()).thenReturn(programmeID);

        IStudyPlanFactory factory = mock(IStudyPlanFactory.class);
        IStudyPlanListFactory listFactory = mock(IStudyPlanListFactory.class);
        List<StudyPlan> studyPlanList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(studyPlanList);
        studyPlanList.add(studyPlan1);

        StudyPlanRepositoryImpl repository = new StudyPlanRepositoryImpl(factory, listFactory);

        // act
        List<StudyPlan> listStudyPlansByProgrammeID = repository.getAllStudyPlansByProgrammeId(programmeID);

        // assert
        assertEquals(studyPlan1, listStudyPlansByProgrammeID.get(0));
        assertEquals(listStudyPlansByProgrammeID.size(), 1);
    }

    @Test
    void shouldReturnEmptyListWhenProgrammeIDNotMatchAnyProgrammeIDinStudyPlan(){
        // arrange
        StudyPlan studyPlan1 = mock(StudyPlan.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        ProgrammeID programmeID2 = mock(ProgrammeID.class);
        when(studyPlan1.getProgrammeID()).thenReturn(programmeID);


        IStudyPlanFactory factory = mock(IStudyPlanFactory.class);
        IStudyPlanListFactory listFactory = mock(IStudyPlanListFactory.class);
        List<StudyPlan> studyPlanList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(studyPlanList);
        studyPlanList.add(studyPlan1);

        StudyPlanRepositoryImpl repository = new StudyPlanRepositoryImpl(factory, listFactory);

        // act
        List<StudyPlan> listStudyPlansByProgrammeID = repository.getAllStudyPlansByProgrammeId(programmeID2);

        // assert
        assertEquals(listStudyPlansByProgrammeID.size(), 0);
    }

    @Test
    void testSaveAddsStudyPlan() {
        StudyPlan studyPlan1 = mock(StudyPlan.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);

        IStudyPlanFactory factory = mock(IStudyPlanFactory.class);
        IStudyPlanListFactory listFactory = mock(IStudyPlanListFactory.class);
        List<StudyPlan> studyPlanList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(studyPlanList);

        StudyPlanRepositoryImpl repository = new StudyPlanRepositoryImpl(factory, listFactory);
        StudyPlanID id = new StudyPlanID(programmeID, implementationDate);
        when(studyPlan1.identity()).thenReturn(id);

        StudyPlan saved = repository.save(studyPlan1);

        assertEquals(studyPlan1, saved);
        assertTrue(repository.containsOfIdentity(id));
    }

    @Test
    void testFindAllReturnsAllStudyPlans() {
        StudyPlan studyPlan1 = mock(StudyPlan.class);
        StudyPlan studyPlan2 = mock(StudyPlan.class);

        IStudyPlanFactory factory = mock(IStudyPlanFactory.class);
        IStudyPlanListFactory listFactory = mock(IStudyPlanListFactory.class);
        List<StudyPlan> studyPlanList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(studyPlanList);

        StudyPlanRepositoryImpl repository = new StudyPlanRepositoryImpl(factory, listFactory);

        repository.save(studyPlan1);
        repository.save(studyPlan2);

        //act
        List<StudyPlan> all = (List<StudyPlan>) repository.findAll();

        //assert
        assertEquals(2, all.size());
        assertTrue(all.contains(studyPlan1) && all.contains(studyPlan2));
    }

    @Test
    void testOfIdentityReturnsCorrectPlan() {
        StudyPlan studyPlan1 = mock(StudyPlan.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);

        IStudyPlanFactory factory = mock(IStudyPlanFactory.class);
        IStudyPlanListFactory listFactory = mock(IStudyPlanListFactory.class);
        List<StudyPlan> studyPlanList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(studyPlanList);

        StudyPlanRepositoryImpl repository = new StudyPlanRepositoryImpl(factory, listFactory);
        StudyPlanID id = new StudyPlanID(programmeID, implementationDate);
        when(studyPlan1.identity()).thenReturn(id);

        repository.save(studyPlan1);

        Optional<StudyPlan> found = repository.ofIdentity(id);

        assertTrue(found.isPresent());
        assertEquals(studyPlan1, found.get());
    }

    @Test
    void testOfIdentityReturnsEmptyWhenNotFound() {
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);

        IStudyPlanFactory factory = mock(IStudyPlanFactory.class);
        IStudyPlanListFactory listFactory = mock(IStudyPlanListFactory.class);
        List<StudyPlan> studyPlanList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(studyPlanList);
        StudyPlanRepositoryImpl repository = new StudyPlanRepositoryImpl(factory, listFactory);
        StudyPlanID id = new StudyPlanID(programmeID, implementationDate);

        Optional<StudyPlan> found = repository.ofIdentity(id);

        assertFalse(found.isPresent());
    }

    @Test
    void testContainsOfIdentityReturnsTrueWhenExists() {
        StudyPlan studyPlan1 = mock(StudyPlan.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        when(studyPlan1.getProgrammeID()).thenReturn(programmeID);

        IStudyPlanFactory factory = mock(IStudyPlanFactory.class);
        IStudyPlanListFactory listFactory = mock(IStudyPlanListFactory.class);
        List<StudyPlan> studyPlanList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(studyPlanList);

        StudyPlanRepositoryImpl repository = new StudyPlanRepositoryImpl(factory, listFactory);
        StudyPlanID id = new StudyPlanID(programmeID, implementationDate);
        when(studyPlan1.identity()).thenReturn(id);

        repository.save(studyPlan1);

        assertTrue(repository.containsOfIdentity(id));
    }

    @Test
    void testContainsOfIdentityReturnsFalseWhenNotExists() {
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);

        IStudyPlanFactory factory = mock(IStudyPlanFactory.class);
        IStudyPlanListFactory listFactory = mock(IStudyPlanListFactory.class);
        List<StudyPlan> studyPlanList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(studyPlanList);
        StudyPlanRepositoryImpl repository = new StudyPlanRepositoryImpl(factory, listFactory);
        StudyPlanID id = new StudyPlanID(programmeID, implementationDate);

        assertFalse(repository.containsOfIdentity(id));
    }


    @Test
    void shouldReturnStudyPlanIDFromLastStudyPlanByProgrammeID() {
        // Arrange
        IStudyPlanFactory studyPlanFactoryDouble = mock(IStudyPlanFactory.class);
        IStudyPlanListFactory listFactoryDouble = mock(IStudyPlanListFactory.class);
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);

        List<StudyPlan> listOfStudyPlan = new ArrayList<>();
        when(listFactoryDouble.newArrayList()).thenReturn(listOfStudyPlan);

        StudyPlanRepositoryImpl repository = new StudyPlanRepositoryImpl(studyPlanFactoryDouble, listFactoryDouble);

        StudyPlan studyPlan1Double = mock(StudyPlan.class);
        StudyPlan studyPlan2Double = mock(StudyPlan.class);

        when(studyPlan1Double.getProgrammeID()).thenReturn(programmeIDDouble);
        when(studyPlan2Double.getProgrammeID()).thenReturn(programmeIDDouble);

        StudyPlanID studyPlanID1Double = mock(StudyPlanID.class);
        StudyPlanID studyPlanID2Double = mock(StudyPlanID.class);

        when(studyPlan1Double.identity()).thenReturn(studyPlanID1Double);
        when(studyPlan2Double.identity()).thenReturn(studyPlanID2Double);


        listOfStudyPlan.add(studyPlan1Double);
        listOfStudyPlan.add(studyPlan2Double);

        // Act
        StudyPlanID result = repository.getLatestStudyPlanIDByProgrammeID(programmeIDDouble);

        // Assert
        assertEquals(studyPlanID2Double, result);
    }

    @Test
    void testGetLatestStudyPlanIDByProgrammeIDSinglePlan() throws Exception {
        // Arrange
        IStudyPlanFactory factory = mock(IStudyPlanFactory.class);
        IStudyPlanListFactory listFactory = mock(IStudyPlanListFactory.class);
        List<StudyPlan> studyPlanList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(studyPlanList);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        MaxEcts quantityOfEcts = mock(MaxEcts.class);

        // Criar um plano de estudos real para teste
        StudyPlan studyPlan = new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts);
        StudyPlanID expectedID = studyPlan.identity();

        when(factory.newStudyPlan_2(programmeID, implementationDate, durationInYears, quantityOfEcts))
                .thenReturn(studyPlan);

        StudyPlanRepositoryImpl repository = new StudyPlanRepositoryImpl(factory, listFactory);

        // Act
        repository.createStudyPlan_2(programmeID, implementationDate, durationInYears, quantityOfEcts);
        StudyPlanID actualID = repository.getLatestStudyPlanIDByProgrammeID(programmeID);

        // Assert
        assertEquals(expectedID, actualID);
    }

    @Test
    void testFindAllReturnsEmptyForNewRepository() {
        // Arrange
        IStudyPlanFactory factory = mock(IStudyPlanFactory.class);
        IStudyPlanListFactory listFactory = mock(IStudyPlanListFactory.class);
        List<StudyPlan> studyPlanList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(studyPlanList);

        StudyPlanRepositoryImpl repository = new StudyPlanRepositoryImpl(factory, listFactory);

        // Act
        List<StudyPlan> allPlans = repository.getAllStudyPlans_2();

        // Assert
        assertTrue(allPlans.isEmpty());
    }

    @Test
    void testOfIdentityWithMultiplePlans() throws Exception {
        // Arrange
        IStudyPlanFactory factory = mock(IStudyPlanFactory.class);
        IStudyPlanListFactory listFactory = mock(IStudyPlanListFactory.class);
        List<StudyPlan> studyPlanList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(studyPlanList);

        ProgrammeID programmeID1 = mock(ProgrammeID.class);
        ProgrammeID programmeID2 = mock(ProgrammeID.class);
        Date implementationDate1 = mock(Date.class);
        Date implementationDate2 = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        MaxEcts quantityOfEcts = mock(MaxEcts.class);

        // Criar dois planos, para diferentes programas
        StudyPlan studyPlan1 = new StudyPlan(programmeID1, implementationDate1, durationInYears, quantityOfEcts);
        StudyPlan studyPlan2 = new StudyPlan(programmeID2, implementationDate2, durationInYears, quantityOfEcts);

        StudyPlanID expectedID = studyPlan2.identity();

        when(factory.newStudyPlan_2(programmeID1, implementationDate1, durationInYears, quantityOfEcts))
                .thenReturn(studyPlan1);
        when(factory.newStudyPlan_2(programmeID2, implementationDate2, durationInYears, quantityOfEcts))
                .thenReturn(studyPlan2);

        StudyPlanRepositoryImpl repository = new StudyPlanRepositoryImpl(factory, listFactory);
        repository.createStudyPlan_2(programmeID1, implementationDate1, durationInYears, quantityOfEcts);
        repository.createStudyPlan_2(programmeID2, implementationDate2, durationInYears, quantityOfEcts);

        // Act
        Optional<StudyPlan> foundPlan = repository.ofIdentity(expectedID);

        // Assert
        assertTrue(foundPlan.isPresent());
        assertEquals(studyPlan2, foundPlan.get());
    }
}