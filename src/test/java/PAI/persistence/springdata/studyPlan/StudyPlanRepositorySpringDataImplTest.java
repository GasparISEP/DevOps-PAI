package PAI.persistence.springdata.studyPlan;

import PAI.VOs.*;
import PAI.domain.studyPlan.StudyPlan;
import PAI.mapper.studyPlan.IStudyPlanMapper;
import PAI.mapper.studyPlan.IStudyPlanIDMapper;
import PAI.persistence.datamodel.studyPlan.StudyPlanDataModel;
import PAI.persistence.datamodel.studyPlan.StudyPlanIDDataModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudyPlanRepositorySpringDataImplTest {

    private IStudyPlanMapper _iStudyPlanMapper;
    private IStudyPlanRepositorySpringData _iStudyPlanRepositorySpringData;
    private StudyPlanRepositorySpringDataImpl _studyPlanRepositorySpringDataImpl;
    private IStudyPlanIDMapper _iStudyPlanIDMapper;

    @BeforeEach
    void setUp() {
        _iStudyPlanMapper = mock(IStudyPlanMapper.class);
        _iStudyPlanRepositorySpringData = mock(IStudyPlanRepositorySpringData.class);
        _iStudyPlanIDMapper = mock(IStudyPlanIDMapper.class);

        _studyPlanRepositorySpringDataImpl = new StudyPlanRepositorySpringDataImpl(_iStudyPlanRepositorySpringData, _iStudyPlanIDMapper, _iStudyPlanMapper);
    }

    @Test
    void constructorShouldNotThrowExceptionWhenIDMapperIsNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new StudyPlanRepositorySpringDataImpl(_iStudyPlanRepositorySpringData, null, _iStudyPlanMapper));
        assertEquals("iStudyPlanIDMapper cannot be null", ex.getMessage());
    }

    @Test
    void constructorShouldNotThrowExceptionWhenMapperIsNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new StudyPlanRepositorySpringDataImpl(_iStudyPlanRepositorySpringData, _iStudyPlanIDMapper, null));
        assertEquals("iStudyPlanMapper cannot be null", ex.getMessage());
    }

    @Test
    void constructorShouldNotThrowExceptionWhenRepositoryIsNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new StudyPlanRepositorySpringDataImpl(null, _iStudyPlanIDMapper, _iStudyPlanMapper));
        assertEquals("iStudyPlanRepositorySpringData cannot be null", ex.getMessage());
    }

    @Test
    void saveShouldMapDomainToDataModelAndBack() throws Exception {
        StudyPlan studyPlanDomain = mock(StudyPlan.class);
        StudyPlanDataModel studyPlanDataModel = mock(StudyPlanDataModel.class);
        StudyPlan studyPlanBackToDomain = mock(StudyPlan.class);

        when(_iStudyPlanMapper.toDataModel(studyPlanDomain)).thenReturn(studyPlanDataModel);
        when(_iStudyPlanRepositorySpringData.save(studyPlanDataModel)).thenReturn(studyPlanDataModel);
        when(_iStudyPlanMapper.toDomain(studyPlanDataModel)).thenReturn(studyPlanBackToDomain);

        StudyPlan result = _studyPlanRepositorySpringDataImpl.save(studyPlanDomain);

        assertEquals(studyPlanBackToDomain, result);
    }

    @Test
    void saveShouldThrowExceptionWhenMappingBackFails() throws Exception {
        StudyPlan studyPlanDomain = mock(StudyPlan.class);
        StudyPlanDataModel studyPlanDataModel = mock(StudyPlanDataModel.class);

        when(_iStudyPlanMapper.toDataModel(studyPlanDomain)).thenReturn(studyPlanDataModel);
        when(_iStudyPlanRepositorySpringData.save(studyPlanDataModel)).thenReturn(studyPlanDataModel);
        when(_iStudyPlanMapper.toDomain(studyPlanDataModel)).thenThrow(new IllegalArgumentException("Error mapping StudyPlanDataModel to domain"));

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> _studyPlanRepositorySpringDataImpl.save(studyPlanDomain));
        assertEquals("Error mapping StudyPlanDataModel to domain", ex.getMessage());
    }

    @Test
    void saveShouldThrowExceptionWhenStudyPlanIsNull() throws Exception {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> _studyPlanRepositorySpringDataImpl.save(null));
        assertEquals("Study Plan cannot be null", ex.getMessage());
    }

    @Test
    void findAllShouldReturnAllMapped() throws Exception {
        StudyPlanDataModel dm1 = mock(StudyPlanDataModel.class);
        StudyPlanDataModel dm2 = mock(StudyPlanDataModel.class);
        StudyPlan e1 = mock(StudyPlan.class);
        StudyPlan e2 = mock(StudyPlan.class);

        when(_iStudyPlanRepositorySpringData.findAll()).thenReturn(Arrays.asList(dm1, dm2));
        when(_iStudyPlanMapper.toDomain(dm1)).thenReturn(e1);
        when(_iStudyPlanMapper.toDomain(dm2)).thenReturn(e2);

        Iterable<StudyPlan> all = _studyPlanRepositorySpringDataImpl.findAll();
        List<StudyPlan> list = Arrays.asList(e1, e2);

        assertEquals(list, toList(all));
    }

    @Test
    void ofIdendityShouldReturnEmptyWhenNotFound() {
        StudyPlanID id = mock(StudyPlanID.class);
        StudyPlanIDDataModel idDM = mock(StudyPlanIDDataModel.class);
        when(_iStudyPlanIDMapper.toDataModel(id)).thenReturn(idDM);

        when(_iStudyPlanRepositorySpringData.findById(idDM))
                .thenReturn(java.util.Optional.empty());

        Optional<StudyPlan> result = _studyPlanRepositorySpringDataImpl.ofIdentity(id);
        assertFalse(result.isPresent());
    }

    @Test
    void containsOfIdendityShouldReflectExistsById() {
        StudyPlanID id = mock(StudyPlanID.class);
        StudyPlanIDDataModel idDM = mock(StudyPlanIDDataModel.class);
        when(_iStudyPlanIDMapper.toDataModel(id)).thenReturn(idDM);

        when(_iStudyPlanRepositorySpringData.existsById(idDM)).thenReturn(true);
        assertTrue(_studyPlanRepositorySpringDataImpl.containsOfIdentity(id));

        when(_iStudyPlanRepositorySpringData.existsById(idDM)).thenReturn(false);
        assertFalse(_studyPlanRepositorySpringDataImpl.containsOfIdentity(id));
    }

    @Test
    void findAllShouldThrowRuntimeExceptionWhenMappingFails() throws Exception {
        StudyPlanDataModel dm = mock(StudyPlanDataModel.class);

        when(_iStudyPlanRepositorySpringData.findAll()).thenReturn(List.of(dm));
        when(_iStudyPlanMapper.toDomain(dm)).thenThrow(new RuntimeException("Error mapping StudyPlanDataModel to domain"));

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> _studyPlanRepositorySpringDataImpl.findAll());
        assertTrue(ex.getCause() instanceof RuntimeException);
        assertEquals("Error mapping StudyPlanDataModel to domain", ex.getCause().getMessage());
    }

    @Test
    void ofIdentityShouldThrowRuntimeExceptionWhenMappingFails() throws Exception {
        StudyPlanDataModel dm = mock(StudyPlanDataModel.class);
        StudyPlanID id = mock(StudyPlanID.class);
        StudyPlanIDDataModel idDM = mock(StudyPlanIDDataModel.class);
        when(_iStudyPlanIDMapper.toDataModel(id)).thenReturn(idDM);

        when(_iStudyPlanRepositorySpringData.findById(idDM))
                .thenReturn(Optional.of(dm));

        when(_iStudyPlanMapper.toDomain(dm)).thenThrow(new RuntimeException("Error mapping StudyPlanDataModel to domain"));

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> _studyPlanRepositorySpringDataImpl.ofIdentity(id));

        assertEquals("Error mapping StudyPlanDataModel to domain", ex.getCause().getMessage());

        assertNotNull(ex.getCause());
        assertEquals("Error mapping StudyPlanDataModel to domain", ex.getCause().getMessage());
    }

    @Test
    void ofIdentityShouldReturnEmptyWhenIdIsNull() {
        Optional<StudyPlan> result = _studyPlanRepositorySpringDataImpl.ofIdentity(null);
        assertTrue(result.isEmpty());
    }

    @Test
    void ofIdentityShouldReturnEmptyWhenRepositoryReturnsEmpty() {
        StudyPlanID id = mock(StudyPlanID.class);
        StudyPlanIDDataModel idDM = mock(StudyPlanIDDataModel.class);

        when(_iStudyPlanIDMapper.toDataModel(id)).thenReturn(idDM);
        when(_iStudyPlanRepositorySpringData.findById(idDM)).thenReturn(Optional.empty());

        Optional<StudyPlan> result = _studyPlanRepositorySpringDataImpl.ofIdentity(id);
        assertTrue(result.isEmpty());
    }

    @Test
    void ofIdentityShouldReturnMappedDomainWhenRepositoryReturnsDataModel() throws Exception {
        StudyPlanID id = mock(StudyPlanID.class);
        StudyPlanIDDataModel idDM = mock(StudyPlanIDDataModel.class);
        StudyPlanDataModel dm = mock(StudyPlanDataModel.class);
        StudyPlan domain = mock(StudyPlan.class);

        when(_iStudyPlanIDMapper.toDataModel(id)).thenReturn(idDM);
        when(_iStudyPlanRepositorySpringData.findById(idDM)).thenReturn(Optional.of(dm));
        when(_iStudyPlanMapper.toDomain(dm)).thenReturn(domain);

        Optional<StudyPlan> result = _studyPlanRepositorySpringDataImpl.ofIdentity(id);
        assertTrue(result.isPresent());
        assertSame(domain, result.get());
    }

    @Test
    void ofIdentityShouldThrowRuntimeExceptionWhenMapperFails() throws Exception {
        StudyPlanID id = mock(StudyPlanID.class);
        StudyPlanIDDataModel idDM = mock(StudyPlanIDDataModel.class);
        StudyPlanDataModel dm = mock(StudyPlanDataModel.class);

        when(_iStudyPlanIDMapper.toDataModel(id)).thenReturn(idDM);
        when(_iStudyPlanRepositorySpringData.findById(idDM)).thenReturn(Optional.of(dm));
        when(_iStudyPlanMapper.toDomain(dm)).thenThrow(new Exception("mapping failed"));

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> _studyPlanRepositorySpringDataImpl.ofIdentity(id));

        assertTrue(ex.getMessage().contains("Error mapping StudyPlanDataModel to domain"));
    }

    @Test
    void findAllShouldIgnoreNullDataModels() throws Exception {
        StudyPlanDataModel dm1 = mock(StudyPlanDataModel.class);
        StudyPlan plan1 = mock(StudyPlan.class);

        when(_iStudyPlanRepositorySpringData.findAll()).thenReturn(Arrays.asList(dm1, null));
        when(_iStudyPlanMapper.toDomain(dm1)).thenReturn(plan1);

        Iterable<StudyPlan> result = _studyPlanRepositorySpringDataImpl.findAll();
        List<StudyPlan> resultList = toList(result);

        assertEquals(1, resultList.size());
        assertSame(plan1, resultList.get(0));
    }

    @Test
    void containsOfIdentityShouldReturnFalseWhenIdIsNull() {
        boolean result = _studyPlanRepositorySpringDataImpl.containsOfIdentity(null);
        assertFalse(result);
    }

    // helper to convert Iterable to List
    private List<StudyPlan> toList(Iterable<StudyPlan> it) {
        List<StudyPlan> list = new java.util.ArrayList<>();
        it.forEach(list::add);
        return list;
    }

    @Test
    void shouldReturnLatestStudyPlanForProgrammeID() throws Exception {
        // Arrange
        ProgrammeID pid = new ProgrammeID(new Acronym("ENG"));
        Date earlierDate = new Date("01-01-2020");
        Date laterDate = new Date("01-01-2023");

        StudyPlanID spid1 = new StudyPlanID(pid, earlierDate);
        StudyPlanID spid2 = new StudyPlanID(pid, laterDate);

        StudyPlan plan1 = new StudyPlan(pid, earlierDate, new DurationInYears(3), new MaxEcts(180), spid1, new StudyPlanGeneratedID(UUID.randomUUID()));
        StudyPlan plan2 = new StudyPlan(pid, laterDate, new DurationInYears(3), new MaxEcts(180), spid2, new StudyPlanGeneratedID(UUID.randomUUID()));

        StudyPlanDataModel dataModel1 = Mockito.mock(StudyPlanDataModel.class);
        StudyPlanDataModel dataModel2 = Mockito.mock(StudyPlanDataModel.class);

        List<StudyPlanDataModel> dataModels = List.of(dataModel1, dataModel2);
        when(_iStudyPlanRepositorySpringData.findAll()).thenReturn(dataModels);
        when(_iStudyPlanMapper.toDomain(dataModel1)).thenReturn(plan1);
        when(_iStudyPlanMapper.toDomain(dataModel2)).thenReturn(plan2);

        // Act
        StudyPlanID latest = _studyPlanRepositorySpringDataImpl.findLatestByProgrammeID(pid);

        // Assert
        assertNotNull(latest);
        assertEquals(laterDate, latest.getDate());
    }

    @Test
    void shouldThrowIfNoPlansFoundForProgrammeID() throws Exception {
        // Arrange
        ProgrammeID pid = new ProgrammeID(new Acronym("ENG"));
        StudyPlanDataModel unrelatedDataModel = Mockito.mock(StudyPlanDataModel.class);

        ProgrammeID otherPid = new ProgrammeID(new Acronym("MATH"));
        StudyPlanID otherID = new StudyPlanID(otherPid, new Date("01-01-2021"));
        StudyPlan unrelatedPlan = new StudyPlan(otherPid, new Date("01-01-2021"), new DurationInYears(3), new MaxEcts(180), otherID, new StudyPlanGeneratedID(UUID.randomUUID()));

        when(_iStudyPlanRepositorySpringData.findAll()).thenReturn(List.of(unrelatedDataModel));
        when(_iStudyPlanMapper.toDomain(unrelatedDataModel)).thenReturn(unrelatedPlan);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                _studyPlanRepositorySpringDataImpl.findLatestByProgrammeID(pid));
        assertEquals("No study plans found for given ProgrammeID", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfProgrammeIDIsNull() {
        // Arrange

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> _studyPlanRepositorySpringDataImpl.findLatestByProgrammeID(null));
    }
}