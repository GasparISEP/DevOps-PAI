package PAI.service.studyPlan;

import PAI.VOs.*;
import PAI.assembler.studyPlan.IStudyPlanAssembler;
import PAI.domain.degreeType.DegreeType;
import PAI.domain.programme.Programme;
import PAI.domain.repositoryInterfaces.degreeType.IDegreeTypeRepository;
import PAI.domain.repositoryInterfaces.programme.IProgrammeRepository;
import PAI.domain.studyPlan.IStudyPlanFactory;
import PAI.domain.studyPlan.StudyPlan;
import PAI.domain.repositoryInterfaces.studyPlan.IStudyPlanRepository;
import PAI.dto.studyPlan.RegisterStudyPlanCommand;
import PAI.dto.studyPlan.StudyPlanDTO;
import PAI.exception.BusinessRuleViolationException;
import jakarta.persistence.EntityNotFoundException;
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

    @Mock
    private IStudyPlanAssembler assembler;

    @Mock
    private IProgrammeRepository programmeRepository;

    @Mock
    private IDegreeTypeRepository degreeTypeRepository;

    @InjectMocks
    private StudyPlanServiceImpl service;

    private Date date;
    private ProgrammeID programmeID;
    private StudyPlan studyPlan;
    private StudyPlanID studyPlanID;
    private DurationInYears durationInYears;
    private MaxEcts maxEcts;
    private QuantSemesters quantSemestersDouble;
    private Programme programmeDouble;
    private DegreeTypeID degreeTypeIDDouble;
    private DegreeType degreeTypeDouble;
    private StudyPlanDTO studyPlanDTO;
    private RegisterStudyPlanCommand studyPlanCommand;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        date = mock(Date.class);
        durationInYears = mock(DurationInYears.class);
        programmeDouble = mock(Programme.class);
        programmeID = mock(ProgrammeID.class);
        maxEcts = mock(MaxEcts.class);
        quantSemestersDouble = mock(QuantSemesters.class);
        degreeTypeIDDouble = mock(DegreeTypeID.class);
        degreeTypeDouble = mock(DegreeType.class);
        studyPlanDTO = mock(StudyPlanDTO.class);
        studyPlanCommand = mock(RegisterStudyPlanCommand.class);

        studyPlan = mock(StudyPlan.class);
        studyPlanID = mock(StudyPlanID.class);

        when(studyPlan.identity()).thenReturn(studyPlanID);
        when(programmeDouble.getProgrammeID()).thenReturn(programmeID);
        when(studyPlanCommand.getStartDate()).thenReturn(date);
        when(programmeDouble.getQuantSemesters()).thenReturn(quantSemestersDouble);
        when(degreeTypeDouble.getMaxEcts()).thenReturn(maxEcts);
        when(factory.createStudyPlan(programmeID, date, quantSemestersDouble, maxEcts)).thenReturn(studyPlan);
    }

    private void createStudyPlanStubConfigurations() throws Exception {
        Optional<Programme> programmeOptional = Optional.of(programmeDouble);
        Optional<DegreeType> degreeTypeOptional = Optional.of(degreeTypeDouble);
        when(studyPlanCommand.getProgrammeId()).thenReturn(programmeID);
        when(programmeDouble.getDegreeTypeID()).thenReturn(degreeTypeIDDouble);
        when(programmeRepository.ofIdentity(programmeID)).thenReturn(programmeOptional);
        when(degreeTypeRepository.ofIdentity(degreeTypeIDDouble)).thenReturn(degreeTypeOptional);
        when(repository.save(studyPlan)).thenReturn(studyPlan);
        when(assembler.toDTO(studyPlan)).thenReturn(studyPlanDTO);
    }

    @Test
    void createStudyPlan_SuccessWhenNotExists() throws Exception {
        // Arrange
        createStudyPlanStubConfigurations();
        when(repository.containsOfIdentity(studyPlanID)).thenReturn(false);

        // Act
        StudyPlanDTO result = service.createStudyPlan(studyPlanCommand);

        // Assert
        assertSame(studyPlanDTO, result);
    }

    @Test
    void createStudyPlan_FailsWhenAlreadyExists() throws Exception {
        // Arrange
        createStudyPlanStubConfigurations();
        when(repository.containsOfIdentity(studyPlanID)).thenReturn(true);

        // Act & Assert
        assertThrows(BusinessRuleViolationException.class, () -> service.createStudyPlan(studyPlanCommand));
    }

    @Test
    void shouldThrowEntityNotFoundExceptionWhenProgramDoesNotExist(){
        // Arrange
        when(studyPlanCommand.getProgrammeId()).thenReturn(programmeID);
        when(programmeRepository.ofIdentity(programmeID)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> service.createStudyPlan(studyPlanCommand));
    }

    @Test
    void shouldThrowEntityNotFoundExceptionWhenDegreeTypeDoesNotExist(){
        // Arrange
        when(studyPlanCommand.getProgrammeId()).thenReturn(programmeID);
        when(programmeRepository.ofIdentity(programmeID)).thenReturn(Optional.of(programmeDouble));
        when(programmeDouble.getDegreeTypeID()).thenReturn(degreeTypeIDDouble);
        when(degreeTypeRepository.ofIdentity(degreeTypeIDDouble)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> service.createStudyPlan(studyPlanCommand));
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
        when(repository.ofIdentity(studyPlanID)).thenReturn(Optional.of(studyPlan));

        Optional<StudyPlan> opt = service.findByID(studyPlanID);

        assertTrue(opt.isPresent());
        assertSame(studyPlan, opt.get());
    }

    @Test
    void findById_ReturnsEmptyWhenNotFound() {
        when(repository.ofIdentity(studyPlanID)).thenReturn(Optional.empty());

        Optional<StudyPlan> opt = service.findByID(studyPlanID);

        assertFalse(opt.isPresent());
    }

    @Test
    void constructorThrowsWhenRepositoryIsNull() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new StudyPlanServiceImpl(null, factory, assembler, programmeRepository, degreeTypeRepository);
        });

        assertEquals("Repository cannot be null", exception.getMessage());
    }

    @Test
    void constructorThrowsWhenFactoryIsNull() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new StudyPlanServiceImpl(repository, null, assembler, programmeRepository, degreeTypeRepository);
        });

        assertEquals("Factory cannot be null", exception.getMessage());
    }

    @Test
    void constructorThrowsWhenAssemblerIsNull() {
        // Arrange
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new StudyPlanServiceImpl(repository, factory, null, programmeRepository, degreeTypeRepository);
        });
        // Assert
        assertEquals("Assembler cannot be null", exception.getMessage());
    }

    @Test
    void constructorThrowsWhenProgrammeRepositoryIsNull() {
        // Arrange
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new StudyPlanServiceImpl(repository, factory, assembler, null, degreeTypeRepository);
        });
        // Assert
        assertEquals("Programme repository cannot be null", exception.getMessage());
    }

    @Test
    void constructorThrowsWhenDegreeTypeRepositoryIsNull() {
        // Arrange
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new StudyPlanServiceImpl(repository, factory, assembler, programmeRepository, null);
        });
        // Assert
        assertEquals("Degree type repository cannot be null", exception.getMessage());
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
