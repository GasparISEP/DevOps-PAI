package PAI.persistence.springdata.courseInStudyPlan;

import PAI.VOs.CourseInStudyPlanID;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.mapper.courseInStudyPlan.ICourseInStudyPlanIDMapper;
import PAI.mapper.courseInStudyPlan.ICourseInStudyPlanMapper;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanDataModel;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseInStudyPlanRepositorySpringDataImplTest {

    private ICourseInStudyPlanMapper _iCourseInStudyPlanMapper;
    private ICourseInStudyPlanRepositorySpringData _iCourseInStudyPlanRepositorySpringData;
    private CourseInStudyPlanRepositorySpringDataImpl _courseInStudyPlanRepositorySpringDataImpl;
    private ICourseInStudyPlanIDMapper _iCourseInStudyPlanIDMapper;

    @BeforeEach
    void setUp() {
        _iCourseInStudyPlanMapper = mock(ICourseInStudyPlanMapper.class);
        _iCourseInStudyPlanRepositorySpringData = mock(ICourseInStudyPlanRepositorySpringData.class);
        _iCourseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);

        _courseInStudyPlanRepositorySpringDataImpl = new CourseInStudyPlanRepositorySpringDataImpl(_iCourseInStudyPlanMapper, _iCourseInStudyPlanRepositorySpringData, _iCourseInStudyPlanIDMapper);
    }

    @Test
    void constructorShouldThrowExceptionWhenMapperIsNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new CourseInStudyPlanRepositorySpringDataImpl(null, _iCourseInStudyPlanRepositorySpringData, _iCourseInStudyPlanIDMapper));
        assertEquals("iCourseInStudyPlanMapper cannot be null", ex.getMessage());
    }

    @Test
    void constructorShouldThrowWhenExceptionRepositoryIsNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new CourseInStudyPlanRepositorySpringDataImpl(_iCourseInStudyPlanMapper, null, _iCourseInStudyPlanIDMapper));
        assertEquals("iCourseInStudyPlanRepositorySpringData cannot be null", ex.getMessage());
    }

    @Test
    void constructorShouldThrowWhenExceptionIDMapperIsNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new CourseInStudyPlanRepositorySpringDataImpl(_iCourseInStudyPlanMapper, _iCourseInStudyPlanRepositorySpringData, null));
        assertEquals("iCourseInStudyPlanIDMapper cannot be null", ex.getMessage());
    }

    @Test
    void saveShouldMapDomainToDataModelAndBack() throws Exception {
        CourseInStudyPlan domain = mock(CourseInStudyPlan.class);
        CourseInStudyPlanDataModel dm = mock(CourseInStudyPlanDataModel.class);
        CourseInStudyPlan back = mock(CourseInStudyPlan.class);

        when(_iCourseInStudyPlanMapper.toDataModel(domain)).thenReturn(dm);
        when(_iCourseInStudyPlanRepositorySpringData.save(dm)).thenReturn(dm);
        when(_iCourseInStudyPlanMapper.toDomain(dm)).thenReturn(back);

        CourseInStudyPlan result = _courseInStudyPlanRepositorySpringDataImpl.save(domain);

        assertSame(back, result, "O objeto retornado deveria ser o mesmo que o mapeado de volta.");
    }

    @Test
    void saveShouldThrowWhenMappingBackFails() throws Exception {
        CourseInStudyPlan domain = mock(CourseInStudyPlan.class);
        CourseInStudyPlanDataModel dm = mock(CourseInStudyPlanDataModel.class);

        when(_iCourseInStudyPlanMapper.toDataModel(domain)).thenReturn(dm);
        when(_iCourseInStudyPlanRepositorySpringData.save(dm)).thenReturn(dm);
        when(_iCourseInStudyPlanMapper.toDomain(dm)).thenThrow(new RuntimeException("Error mapping CourseInStudyPlanDataModel to domain"));

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> _courseInStudyPlanRepositorySpringDataImpl.save(domain));
        assertEquals("Error mapping CourseInStudyPlanDataModel to domain", ex.getMessage());
    }

    @Test
    void findAllShouldReturnAllMapped() throws Exception {
        CourseInStudyPlanDataModel dm1 = mock(CourseInStudyPlanDataModel.class);
        CourseInStudyPlanDataModel dm2 = mock(CourseInStudyPlanDataModel.class);
        CourseInStudyPlan e1 = mock(CourseInStudyPlan.class);
        CourseInStudyPlan e2 = mock(CourseInStudyPlan.class);

        when(_iCourseInStudyPlanRepositorySpringData.findAll()).thenReturn(Arrays.asList(dm1, dm2));
        when(_iCourseInStudyPlanMapper.toDomain(dm1)).thenReturn(e1);
        when(_iCourseInStudyPlanMapper.toDomain(dm2)).thenReturn(e2);

        Iterable<CourseInStudyPlan> all = _courseInStudyPlanRepositorySpringDataImpl.findAll();
        List<CourseInStudyPlan> list = Arrays.asList(e1, e2);

        assertEquals(list, toList(all));
    }

    @Test
    void ofIdentityShouldReturnEmptyWhenNotFound() {
        // dado um ID de domínio qualquer
        CourseInStudyPlanID id = mock(CourseInStudyPlanID.class);
        CourseInStudyPlanIDDataModel idDM = mock(CourseInStudyPlanIDDataModel.class);
        when(_iCourseInStudyPlanIDMapper.toDataModel(id)).thenReturn(idDM);

        // e o Spring Data não encontra nada
        when(_iCourseInStudyPlanRepositorySpringData.findById(idDM))
                .thenReturn(Optional.empty());

        // então ofIdentity devolve Optional.empty()
        Optional<CourseInStudyPlan> result =
                _courseInStudyPlanRepositorySpringDataImpl.ofIdentity(id);
        assertFalse(result.isPresent());
    }

    @Test
    void containsOfIdentityShouldReflectExistsById() {
        CourseInStudyPlanID id = mock(CourseInStudyPlanID.class);
        CourseInStudyPlanIDDataModel idDM = mock(CourseInStudyPlanIDDataModel.class);
        when(_iCourseInStudyPlanIDMapper.toDataModel(id)).thenReturn(idDM);

        when(_iCourseInStudyPlanRepositorySpringData.existsById(idDM)).thenReturn(true);
        assertTrue(_courseInStudyPlanRepositorySpringDataImpl.containsOfIdentity(id));

        when(_iCourseInStudyPlanRepositorySpringData.existsById(idDM)).thenReturn(false);
        assertFalse(_courseInStudyPlanRepositorySpringDataImpl.containsOfIdentity(id));
    }

    @Test
    void findAllShouldThrowRuntimeExceptionWhenMappingFails() {
        CourseInStudyPlanDataModel dm = mock(CourseInStudyPlanDataModel.class);

        when(_iCourseInStudyPlanRepositorySpringData.findAll())
                .thenReturn(List.of(dm));
        when(_iCourseInStudyPlanMapper.toDomain(dm))
                .thenThrow(new RuntimeException("mapping error"));

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> _courseInStudyPlanRepositorySpringDataImpl.findAll());
        assertTrue(ex.getCause() instanceof RuntimeException);
        assertEquals("mapping error", ex.getCause().getMessage());
    }

    @Test
    void ofIdentityShouldThrowRuntimeExceptionWhenMappingFails() {
        // dado um DataModel qualquer
        CourseInStudyPlanDataModel dm = mock(CourseInStudyPlanDataModel.class);
        // e um ID de domínio e o seu DataModel correspondente
        CourseInStudyPlanID id = mock(CourseInStudyPlanID.class);
        CourseInStudyPlanIDDataModel idDM = mock(CourseInStudyPlanIDDataModel.class);
        when(_iCourseInStudyPlanIDMapper.toDataModel(id)).thenReturn(idDM);

        // simula o findById a devolver o DataModel
        when(_iCourseInStudyPlanRepositorySpringData.findById(idDM))
                .thenReturn(Optional.of(dm));
        // simula falha no mapper
        when(_iCourseInStudyPlanMapper.toDomain(dm))
                .thenThrow(new RuntimeException("mapping error"));

        // executa e verifica
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> _courseInStudyPlanRepositorySpringDataImpl.ofIdentity(id));

        // a mensagem do RuntimeException externo
        assertEquals("Error mapping CourseInStudyPlanDataModel to domain", ex.getMessage());
        // e o cause mantém a mensagem original
        assertNotNull(ex.getCause());
        assertEquals("mapping error", ex.getCause().getMessage());
    }

    @Test
    void saveShouldThrowWhenEntityIsNull() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> _courseInStudyPlanRepositorySpringDataImpl.save(null)
        );
        assertEquals("Course In Study Plan cannot be null.", ex.getMessage());
    }

    @Test
    void ofIdentityShouldReturnEmptyWhenIdIsNull() {
        // quando passo null, deve devolver Optional.empty()
        Optional<CourseInStudyPlan> result =
                _courseInStudyPlanRepositorySpringDataImpl.ofIdentity(null);
        assertFalse(result.isPresent());
    }

    @Test
    void containsOfIdentityShouldReturnFalseWhenIdIsNull() {
        // quando passo null, deve devolver false (não existe)
        assertFalse(_courseInStudyPlanRepositorySpringDataImpl.containsOfIdentity(null));
    }

    @Test
    void ofIdentityShouldReturnMappedDomainWhenFound() throws Exception {
        CourseInStudyPlanID id = mock(CourseInStudyPlanID.class);
        CourseInStudyPlanIDDataModel idDM = mock(CourseInStudyPlanIDDataModel.class);
        CourseInStudyPlanDataModel dm = mock(CourseInStudyPlanDataModel.class);
        CourseInStudyPlan domain = mock(CourseInStudyPlan.class);

        when(_iCourseInStudyPlanIDMapper.toDataModel(id)).thenReturn(idDM);
        when(_iCourseInStudyPlanRepositorySpringData.findById(idDM)).thenReturn(Optional.of(dm));
        when(_iCourseInStudyPlanMapper.toDomain(dm)).thenReturn(domain);

        Optional<CourseInStudyPlan> result = _courseInStudyPlanRepositorySpringDataImpl.ofIdentity(id);

        assertTrue(result.isPresent());
        assertEquals(domain, result.get());
    }

    // helper to convert Iterable to List
    private List<CourseInStudyPlan> toList(Iterable<CourseInStudyPlan> it) {
        List<CourseInStudyPlan> list = new java.util.ArrayList<>();
        it.forEach(list::add);
        return list;
    }
}