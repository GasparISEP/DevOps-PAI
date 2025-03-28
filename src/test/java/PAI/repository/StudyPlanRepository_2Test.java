package PAI.repository;

import PAI.VOs.Date;
import PAI.VOs.DurationInYears;
import PAI.VOs.ProgrammeID;
import PAI.VOs.StudyPlanID;
import PAI.domain.StudyPlan_2;
import PAI.factory.IStudyPlanFactory_2;
import PAI.factory.IStudyPlanListFactory_2;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudyPlanRepository_2Test {

    @Test
    void testCreateStudyPlanNewPlan() throws Exception {
        // Arrange
        IStudyPlanFactory_2 factory = mock(IStudyPlanFactory_2.class);
        IStudyPlanListFactory_2 listFactory = mock(IStudyPlanListFactory_2.class);
        List<StudyPlan_2> studyPlanList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(studyPlanList);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);

        when(factory.newStudyPlan_2(programmeID, implementationDate, durationInYears))
                .thenAnswer(invocation -> new StudyPlan_2(programmeID, implementationDate, durationInYears));

        StudyPlanRepository_2 repository = new StudyPlanRepository_2(factory, listFactory);

        // Act
        boolean created = repository.createStudyPlan_2(programmeID, implementationDate, durationInYears);

        // Assert
        assertTrue(created);
        assertEquals(1, repository.getAllStudyPlans_2().size());
    }

    @Test
    void testCreateStudyPlanDuplicatePlan() throws Exception {
        // Arrange
        IStudyPlanFactory_2 factory = mock(IStudyPlanFactory_2.class);
        IStudyPlanListFactory_2 listFactory = mock(IStudyPlanListFactory_2.class);
        List<StudyPlan_2> studyPlanList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(studyPlanList);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);

        StudyPlanRepository_2 repository = new StudyPlanRepository_2(factory, listFactory);

        // Act
        boolean createdFirstStudyPlan = repository.createStudyPlan_2(programmeID, implementationDate, durationInYears);
        boolean createdSecondStudyPlan = repository.createStudyPlan_2(programmeID, implementationDate, durationInYears);

        // Assert
        assertTrue(createdFirstStudyPlan);
        assertFalse(createdSecondStudyPlan);
        assertEquals(1, repository.getAllStudyPlans_2().size());
    }

    @Test
    void testGetAllStudyPlansReturnsMultiplePlans() throws Exception {
        // Arrange
        IStudyPlanFactory_2 factory = mock(IStudyPlanFactory_2.class);
        IStudyPlanListFactory_2 listFactory = mock(IStudyPlanListFactory_2.class);
        List<StudyPlan_2> studyPlanList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(studyPlanList);

        ProgrammeID programmeID1 = mock(ProgrammeID.class);
        ProgrammeID programmeID2 = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);

        when(factory.newStudyPlan_2(programmeID1, implementationDate, durationInYears))
                .thenAnswer(invocation -> new StudyPlan_2(programmeID1, implementationDate, durationInYears));
        when(factory.newStudyPlan_2(programmeID2, implementationDate, durationInYears))
                .thenAnswer(invocation -> new StudyPlan_2(programmeID2, implementationDate, durationInYears));

        StudyPlanRepository_2 repository = new StudyPlanRepository_2(factory, listFactory);

        // Act
        boolean created1 = repository.createStudyPlan_2(programmeID1, implementationDate, durationInYears);
        boolean created2 = repository.createStudyPlan_2(programmeID2, implementationDate, durationInYears);
        List<StudyPlan_2> allPlans = repository.getAllStudyPlans_2();

        // Assert
        assertTrue(created1);
        assertTrue(created2);
        assertEquals(2, allPlans.size());
    }

    @Test
    void testFindByStudyPlanIDFound() throws Exception {
        // Arrange
        IStudyPlanFactory_2 factory = mock(IStudyPlanFactory_2.class);
        IStudyPlanListFactory_2 listFactory = mock(IStudyPlanListFactory_2.class);
        List<StudyPlan_2> studyPlanList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(studyPlanList);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);

        when(factory.newStudyPlan_2(programmeID, implementationDate, durationInYears))
                .thenAnswer(invocation -> new StudyPlan_2(programmeID, implementationDate, durationInYears));

        StudyPlanRepository_2 repository = new StudyPlanRepository_2(factory, listFactory);

        // Act
        repository.createStudyPlan_2(programmeID, implementationDate, durationInYears);
        StudyPlan_2 createdPlan = repository.getAllStudyPlans_2().get(0);
        StudyPlanID studyPlanID = createdPlan.getStudyPlanID();

        // Act
        Optional<StudyPlan_2> foundPlanOpt = repository.findByStudyPlanID(studyPlanID);

        // Assert
        assertTrue(foundPlanOpt.isPresent());
        assertEquals(createdPlan, foundPlanOpt.get());
    }

    @Test
    void testFindByStudyPlanIDNotFound() throws Exception {
        // Arrange
        IStudyPlanFactory_2 factory = mock(IStudyPlanFactory_2.class);
        IStudyPlanListFactory_2 listFactory = mock(IStudyPlanListFactory_2.class);
        List<StudyPlan_2> studyPlanList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(studyPlanList);

        StudyPlanRepository_2 repository = new StudyPlanRepository_2(factory, listFactory);

        // Act
        StudyPlanID nonExistentID = new StudyPlanID();
        Optional<StudyPlan_2> foundPlanOpt = repository.findByStudyPlanID(nonExistentID);

        // Assert
        assertFalse(foundPlanOpt.isPresent());
    }
}