package PAI.persistence.springdata.studyPlan;

import PAI.VOs.StudyPlanID;
import PAI.domain.studyPlan.StudyPlan;
import PAI.mapper.studyPlan.IStudyPlanMapper;
import PAI.mapper.studyPlan.IStudyPlanIDMapper;
import PAI.persistence.datamodel.studyPlan.StudyPlanDataModel;
import PAI.persistence.datamodel.studyPlan.StudyPlanIDDataModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
}