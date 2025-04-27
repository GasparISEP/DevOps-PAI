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

    private ICourseInStudyPlanMapper _courseInStudyPlanMapper;
    private ICourseInStudyPlanRepositorySpringData _courseInStudyPlanRepositorySpringData;
    private CourseInStudyPlanRepositorySpringDataImpl _courseInStudyPlanRepositorySpringDataImpl;
    private ICourseInStudyPlanIDMapper _courseInStudyPlanIDMapper;

    @BeforeEach
    void setUp() {
        _courseInStudyPlanMapper = mock(ICourseInStudyPlanMapper.class);
        _courseInStudyPlanRepositorySpringData = mock(ICourseInStudyPlanRepositorySpringData.class);
        _courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);

        _courseInStudyPlanRepositorySpringDataImpl = new CourseInStudyPlanRepositorySpringDataImpl(_courseInStudyPlanMapper, _courseInStudyPlanRepositorySpringData, _courseInStudyPlanIDMapper);
    }

    @Test
    void constructorShouldThrowWhenMapperIsNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new CourseInStudyPlanRepositorySpringDataImpl(null, _courseInStudyPlanRepositorySpringData, _courseInStudyPlanIDMapper));
        assertEquals("iCourseInStudyPlanMapper cannot be null", ex.getMessage());
    }

    @Test
    void constructorShouldThrowWhenRepositoryIsNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new CourseInStudyPlanRepositorySpringDataImpl(_courseInStudyPlanMapper, null, _courseInStudyPlanIDMapper));
        assertEquals("iCourseInStudyPlanRepositorySpringData cannot be null", ex.getMessage());
    }

    @Test
    void constructorShouldThrowWhenIDMapperIsNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new CourseInStudyPlanRepositorySpringDataImpl(_courseInStudyPlanMapper, _courseInStudyPlanRepositorySpringData, null));
        assertEquals("iCourseInStudyPlanIDMapper cannot be null", ex.getMessage());
    }

    @Test
    void saveShouldMapDomainToDataModelAndBack() throws Exception {
        CourseInStudyPlan domain = mock(CourseInStudyPlan.class);
        CourseInStudyPlanDataModel dm = mock(CourseInStudyPlanDataModel.class);
        CourseInStudyPlan back = mock(CourseInStudyPlan.class);

        when(_courseInStudyPlanMapper.toDataModel(domain)).thenReturn(dm);
        when(_courseInStudyPlanRepositorySpringData.save(dm)).thenReturn(dm);
        when(_courseInStudyPlanMapper.toDomain(dm)).thenReturn(back);

        CourseInStudyPlan result = _courseInStudyPlanRepositorySpringDataImpl.save(domain);

        verify(_courseInStudyPlanMapper).toDataModel(domain);
        verify(_courseInStudyPlanRepositorySpringData).save(dm);
        verify(_courseInStudyPlanMapper).toDomain(dm);
        assertSame(back, result);
    }

    @Test
    void saveShouldThrowWhenMappingBackFails() throws Exception {
        CourseInStudyPlan domain = mock(CourseInStudyPlan.class);
        CourseInStudyPlanDataModel dm = mock(CourseInStudyPlanDataModel.class);

        when(_courseInStudyPlanMapper.toDataModel(domain)).thenReturn(dm);
        when(_courseInStudyPlanRepositorySpringData.save(dm)).thenReturn(dm);
        when(_courseInStudyPlanMapper.toDomain(dm)).thenThrow(new RuntimeException("mapping error"));

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> _courseInStudyPlanRepositorySpringDataImpl.save(domain));
        assertEquals("mapping error", ex.getMessage());
    }

    @Test
    void findAllShouldReturnAllMapped() throws Exception {
        CourseInStudyPlanDataModel dm1 = mock(CourseInStudyPlanDataModel.class);
        CourseInStudyPlanDataModel dm2 = mock(CourseInStudyPlanDataModel.class);
        CourseInStudyPlan e1 = mock(CourseInStudyPlan.class);
        CourseInStudyPlan e2 = mock(CourseInStudyPlan.class);

        when(_courseInStudyPlanRepositorySpringData.findAll()).thenReturn(Arrays.asList(dm1, dm2));
        when(_courseInStudyPlanMapper.toDomain(dm1)).thenReturn(e1);
        when(_courseInStudyPlanMapper.toDomain(dm2)).thenReturn(e2);

        Iterable<CourseInStudyPlan> all = _courseInStudyPlanRepositorySpringDataImpl.findAll();
        List<CourseInStudyPlan> list = Arrays.asList(e1, e2);

        assertEquals(list, toList(all));
    }


    @Test
    void ofIdentityShouldReturnEmptyWhenNotFound() {
        // dado um ID de domínio qualquer
        CourseInStudyPlanID id = mock(CourseInStudyPlanID.class);
        CourseInStudyPlanIDDataModel idDM = mock(CourseInStudyPlanIDDataModel.class);
        when(_courseInStudyPlanIDMapper.toDataModel(id)).thenReturn(idDM);

        // e o Spring Data não encontra nada
        when(_courseInStudyPlanRepositorySpringData.findById(idDM))
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
        when(_courseInStudyPlanIDMapper.toDataModel(id)).thenReturn(idDM);

        when(_courseInStudyPlanRepositorySpringData.existsById(idDM)).thenReturn(true);
        assertTrue(_courseInStudyPlanRepositorySpringDataImpl.containsOfIdentity(id));

        when(_courseInStudyPlanRepositorySpringData.existsById(idDM)).thenReturn(false);
        assertFalse(_courseInStudyPlanRepositorySpringDataImpl.containsOfIdentity(id));
    }

    @Test
    void findAllShouldThrowRuntimeExceptionWhenMappingFails() {
        CourseInStudyPlanDataModel dm = mock(CourseInStudyPlanDataModel.class);

        when(_courseInStudyPlanRepositorySpringData.findAll())
                .thenReturn(List.of(dm));
        when(_courseInStudyPlanMapper.toDomain(dm))
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
        when(_courseInStudyPlanIDMapper.toDataModel(id)).thenReturn(idDM);

        // simula o findById a devolver o DataModel
        when(_courseInStudyPlanRepositorySpringData.findById(idDM))
                .thenReturn(Optional.of(dm));
        // simula falha no mapper
        when(_courseInStudyPlanMapper.toDomain(dm))
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

    // helper to convert Iterable to List
    private List<CourseInStudyPlan> toList(Iterable<CourseInStudyPlan> it) {
        List<CourseInStudyPlan> list = new java.util.ArrayList<>();
        it.forEach(list::add);
        return list;
    }
}