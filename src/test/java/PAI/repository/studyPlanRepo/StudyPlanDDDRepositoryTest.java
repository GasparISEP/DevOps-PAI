package PAI.repository.studyPlanRepo;

import PAI.VOs.*;
import PAI.domain.studyPlan.StudyPlanDDD;
import PAI.domain.studyPlan.IStudyPlanDDDFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudyPlanDDDRepositoryTest {

    @Test
    void testCreateStudyPlanNewPlan() throws Exception {
        // Arrange
        IStudyPlanDDDFactory factory = mock(IStudyPlanDDDFactory.class);
        IStudyPlanDDDListFactory listFactory = mock(IStudyPlanDDDListFactory.class);
        List<StudyPlanDDD> studyPlanList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(studyPlanList);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        QuantEcts quantityOfEcts = mock(QuantEcts.class);


        when(factory.newStudyPlan_2(programmeID, implementationDate, durationInYears, quantityOfEcts))
                .thenAnswer(invocation -> new StudyPlanDDD(programmeID, implementationDate, durationInYears, quantityOfEcts));

        StudyPlanDDDRepository repository = new StudyPlanDDDRepository(factory, listFactory);

        // Act
        boolean created = repository.createStudyPlan_2(programmeID, implementationDate, durationInYears, quantityOfEcts);

        // Assert
        assertTrue(created);
        assertEquals(1, repository.getAllStudyPlans_2().size());
    }

    @Test
    void testCreateStudyPlanDuplicatePlan() throws Exception {
        // Arrange
        IStudyPlanDDDFactory factory = mock(IStudyPlanDDDFactory.class);
        IStudyPlanDDDListFactory listFactory = mock(IStudyPlanDDDListFactory.class);
        List<StudyPlanDDD> studyPlanList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(studyPlanList);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        QuantEcts quantityOfEcts = mock(QuantEcts.class);

        StudyPlanDDDRepository repository = new StudyPlanDDDRepository(factory, listFactory);

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
        IStudyPlanDDDFactory factory = mock(IStudyPlanDDDFactory.class);
        IStudyPlanDDDListFactory listFactory = mock(IStudyPlanDDDListFactory.class);
        List<StudyPlanDDD> studyPlanList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(studyPlanList);

        ProgrammeID programmeID1 = mock(ProgrammeID.class);
        ProgrammeID programmeID2 = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        QuantEcts quantityOfEcts = mock(QuantEcts.class);

        when(factory.newStudyPlan_2(programmeID1, implementationDate, durationInYears, quantityOfEcts))
                .thenAnswer(invocation -> new StudyPlanDDD(programmeID1, implementationDate, durationInYears, quantityOfEcts));
        when(factory.newStudyPlan_2(programmeID2, implementationDate, durationInYears, quantityOfEcts))
                .thenAnswer(invocation -> new StudyPlanDDD(programmeID2, implementationDate, durationInYears, quantityOfEcts));

        StudyPlanDDDRepository repository = new StudyPlanDDDRepository(factory, listFactory);

        // Act
        boolean created1 = repository.createStudyPlan_2(programmeID1, implementationDate, durationInYears, quantityOfEcts);
        boolean created2 = repository.createStudyPlan_2(programmeID2, implementationDate, durationInYears, quantityOfEcts);
        List<StudyPlanDDD> allPlans = repository.getAllStudyPlans_2();

        // Assert
        assertTrue(created1);
        assertTrue(created2);
        assertEquals(2, allPlans.size());
    }

    @Test
    void testFindByStudyPlanIDFound() throws Exception {
        // Arrange
        IStudyPlanDDDFactory factory = mock(IStudyPlanDDDFactory.class);
        IStudyPlanDDDListFactory listFactory = mock(IStudyPlanDDDListFactory.class);
        List<StudyPlanDDD> studyPlanList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(studyPlanList);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        QuantEcts quantityOfEcts = mock(QuantEcts.class);

        when(factory.newStudyPlan_2(programmeID, implementationDate, durationInYears, quantityOfEcts))
                .thenAnswer(invocation -> new StudyPlanDDD(programmeID, implementationDate, durationInYears, quantityOfEcts));

        StudyPlanDDDRepository repository = new StudyPlanDDDRepository(factory, listFactory);

        // Act
        repository.createStudyPlan_2(programmeID, implementationDate, durationInYears, quantityOfEcts);
        StudyPlanDDD createdPlan = repository.getAllStudyPlans_2().get(0);
        StudyPlanID studyPlanID = createdPlan.getStudyPlanID();

        // Act
        Optional<StudyPlanDDD> foundPlan = repository.findStudyPlanByID(studyPlanID);

        // Assert
        assertTrue(foundPlan.isPresent());
        assertEquals(createdPlan, foundPlan.get());
    }

    @Test
    void testFindByStudyPlanIDNotFound() throws Exception {
        // Arrange
        IStudyPlanDDDFactory factory = mock(IStudyPlanDDDFactory.class);
        IStudyPlanDDDListFactory listFactory = mock(IStudyPlanDDDListFactory.class);
        List<StudyPlanDDD> studyPlanList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(studyPlanList);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);

        StudyPlanDDDRepository repository = new StudyPlanDDDRepository(factory, listFactory);

        // Act
        StudyPlanID nonExistentID = new StudyPlanID(programmeID, implementationDate);
        Optional<StudyPlanDDD> foundPlanOpt = repository.findStudyPlanByID(nonExistentID);

        // Assert
        assertFalse(foundPlanOpt.isPresent());
    }

    @Test
    void shouldGetAllStudyPlansByProgrammeID() throws Exception {
        // arrange
        StudyPlanDDD studyPlanDDD1 = mock(StudyPlanDDD.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        when(studyPlanDDD1.getProgrammeID()).thenReturn(programmeID);

        IStudyPlanDDDFactory factory = mock(IStudyPlanDDDFactory.class);
        IStudyPlanDDDListFactory listFactory = mock(IStudyPlanDDDListFactory.class);
        List<StudyPlanDDD> studyPlanList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(studyPlanList);
        studyPlanList.add(studyPlanDDD1);

        StudyPlanDDDRepository repository = new StudyPlanDDDRepository(factory, listFactory);

        // act
        List<StudyPlanDDD> listStudyPlansByProgrammeID = repository.getAllStudyPlansByProgrammeId(programmeID);

        // assert
        assertEquals(studyPlanDDD1, listStudyPlansByProgrammeID.get(0));
        assertEquals(listStudyPlansByProgrammeID.size(), 1);
    }

    @Test
    void shouldReturnEmptyListWhenProgrammeIDNotMatchAnyProgrammeIDinStudyPlan(){
        // arrange
        StudyPlanDDD studyPlanDDD1 = mock(StudyPlanDDD.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        ProgrammeID programmeID2 = mock(ProgrammeID.class);
        when(studyPlanDDD1.getProgrammeID()).thenReturn(programmeID);


        IStudyPlanDDDFactory factory = mock(IStudyPlanDDDFactory.class);
        IStudyPlanDDDListFactory listFactory = mock(IStudyPlanDDDListFactory.class);
        List<StudyPlanDDD> studyPlanList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(studyPlanList);
        studyPlanList.add(studyPlanDDD1);

        StudyPlanDDDRepository repository = new StudyPlanDDDRepository(factory, listFactory);

        // act
        List<StudyPlanDDD> listStudyPlansByProgrammeID = repository.getAllStudyPlansByProgrammeId(programmeID2);

        // assert
        assertEquals(listStudyPlansByProgrammeID.size(), 0);
    }
}
