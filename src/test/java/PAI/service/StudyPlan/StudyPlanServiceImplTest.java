package PAI.service.StudyPlan;

import PAI.VOs.*;
import PAI.domain.studyPlan.IStudyPlanFactory;
import PAI.domain.studyPlan.StudyPlan;
import PAI.repository.studyPlanRepository.IStudyPlanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudyPlanServiceImplTest {

    @Mock
    private IStudyPlanRepository repository;

    @Mock
    private IStudyPlanFactory factory;

    @InjectMocks
    private StudyPlanServiceImpl service;

    private Date date;
    private ProgrammeID programmeID;
    private StudyPlan candidate;
    private StudyPlanID candidateId;
    private DurationInYears durationInYears;
    private MaxEcts maxEcts;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        date = mock(Date.class);
        durationInYears = mock(DurationInYears.class);
        programmeID = mock(ProgrammeID.class);
        maxEcts = mock(MaxEcts.class);

        candidate = mock(StudyPlan.class);
        candidateId = mock(StudyPlanID.class);

        when(candidate.identity()).thenReturn(candidateId);
        when(factory.createStudyPlan(programmeID, date, durationInYears, maxEcts))
                .thenReturn(candidate);
    }

    @Test
    void createStudyPlan_SuccessWhenNotExists() throws Exception {
        when(repository.containsOfIdentity(candidateId)).thenReturn(false);

        boolean result = service.createStudyPlan(programmeID, date,durationInYears, maxEcts);

        assertTrue(result);
        verify(repository).save(candidate);
    }

    @Test
    void createStudyPlan_FailsWhenAlreadyExists() throws Exception {
        when(repository.containsOfIdentity(candidateId)).thenReturn(true);

        boolean result = service.createStudyPlan(programmeID, date,durationInYears, maxEcts);

        assertFalse(result);
        verify(repository, never()).save(any());
    }

    @Test
    void getAllStudyPlan_ReturnsListFromRepository() throws Exception {
        StudyPlan sp1 = mock(StudyPlan.class);
        StudyPlan sp2 = mock(StudyPlan.class);
        when(repository.findAll()).thenReturn(Arrays.asList(sp1, sp2));

        List<StudyPlan> all = service.getAllStudyPlans();

        assertEquals(2, all.size());
        assertTrue(all.contains(sp1));
        assertTrue(all.contains(sp2));
    }

    @Test
    void getStudyPlanByProgrammeID_FiltersCorrectly() throws Exception {
        StudyPlan sp1 = mock(StudyPlan.class);
        StudyPlan sp2 = mock(StudyPlan.class);
        ProgrammeID otherId = mock(ProgrammeID.class);

        StudyPlanID id1 = mock(StudyPlanID.class);
        StudyPlanID id2 = mock(StudyPlanID.class);

        when(sp1.identity()).thenReturn(id1);
        when(sp2.identity()).thenReturn(id2);
        when(id1.getProgrammeID()).thenReturn(programmeID);
        when(id2.getProgrammeID()).thenReturn(otherId);

        when(repository.findAll()).thenReturn(Arrays.asList(sp1, sp2));

        List<StudyPlan> filtered = service.getStudyPlansByProgrammeID(programmeID);

        assertEquals(1, filtered.size());
        assertSame(sp1, filtered.get(0));
    }

    @Test
    void findById_ReturnsOptionalFromRepository() {
        when(repository.ofIdentity(candidateId)).thenReturn(Optional.of(candidate));

        Optional<StudyPlan> opt = service.findByID(candidateId);

        assertTrue(opt.isPresent());
        assertSame(candidate, opt.get());
    }

    @Test
    void findById_ReturnsEmptyWhenNotFound() {
        when(repository.ofIdentity(candidateId)).thenReturn(Optional.empty());

        Optional<StudyPlan> opt = service.findByID(candidateId);

        assertFalse(opt.isPresent());
    }

    @Test
    void constructorThrowsWhenRepositoryIsNull() {
        IStudyPlanFactory factory = mock(IStudyPlanFactory.class);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new StudyPlanServiceImpl(null, factory);
        });

        assertEquals("Repository cannot be null", exception.getMessage());
    }

    @Test
    void constructorThrowsWhenFactoryIsNull() {
        IStudyPlanRepository repository = mock(IStudyPlanRepository.class);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new StudyPlanServiceImpl(repository, null);
        });

        assertEquals("Factory cannot be null", exception.getMessage());
    }

    @Test
    void getLatestStudyPlanIDByProgrammeID_ReturnsLastStudyPlanID() {
        ProgrammeID programmeID = mock(ProgrammeID.class);

        StudyPlan sp1 = mock(StudyPlan.class);
        StudyPlan sp2 = mock(StudyPlan.class);

        StudyPlanID id1 = mock(StudyPlanID.class);
        StudyPlanID id2 = mock(StudyPlanID.class);

        when(sp1.identity()).thenReturn(id1);
        when(sp2.identity()).thenReturn(id2);
        when(sp1.identity().getProgrammeID()).thenReturn(programmeID);
        when(sp2.identity().getProgrammeID()).thenReturn(programmeID);

        when(repository.findAll()).thenReturn(List.of(sp1, sp2));

        StudyPlanID result = service.getLatestStudyPlanIDByProgrammeID(programmeID);

        assertEquals(id2, result);
    }

    @Test
    void getLatestStudyPlanIDByProgrammeID_ThrowsIllegalArgumentIfEmpty() {
        ProgrammeID programmeID = mock(ProgrammeID.class);
        when(repository.findAll()).thenReturn(List.of());

        assertThrows(IllegalArgumentException.class, () -> {
            service.getLatestStudyPlanIDByProgrammeID(programmeID);
        });
    }
}