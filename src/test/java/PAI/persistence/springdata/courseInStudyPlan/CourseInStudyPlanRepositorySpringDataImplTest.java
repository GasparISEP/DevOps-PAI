package PAI.persistence.springdata.courseInStudyPlan;

import PAI.VOs.*;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.mapper.courseInStudyPlan.ICourseInStudyPlanIDMapper;
import PAI.mapper.courseInStudyPlan.ICourseInStudyPlanMapper;
import PAI.mapper.studyPlan.IStudyPlanIDMapper;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanDataModel;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;
import PAI.persistence.datamodel.studyPlan.StudyPlanIDDataModel;
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
    private IStudyPlanIDMapper _iStudyPlanIDMapper;

    @BeforeEach
    void setUp() {
        _iCourseInStudyPlanMapper = mock(ICourseInStudyPlanMapper.class);
        _iCourseInStudyPlanRepositorySpringData = mock(ICourseInStudyPlanRepositorySpringData.class);
        _iCourseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        _iStudyPlanIDMapper = mock(IStudyPlanIDMapper.class);

        _courseInStudyPlanRepositorySpringDataImpl = new CourseInStudyPlanRepositorySpringDataImpl(_iCourseInStudyPlanMapper, _iCourseInStudyPlanRepositorySpringData, _iCourseInStudyPlanIDMapper, _iStudyPlanIDMapper);
    }

    @Test
    void constructorShouldThrowExceptionWhenMapperIsNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new CourseInStudyPlanRepositorySpringDataImpl(null, _iCourseInStudyPlanRepositorySpringData, _iCourseInStudyPlanIDMapper, _iStudyPlanIDMapper));
        assertEquals("iCourseInStudyPlanMapper cannot be null", ex.getMessage());
    }

    @Test
    void constructorShouldThrowWhenExceptionRepositoryIsNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new CourseInStudyPlanRepositorySpringDataImpl(_iCourseInStudyPlanMapper, null, _iCourseInStudyPlanIDMapper, _iStudyPlanIDMapper));
        assertEquals("iCourseInStudyPlanRepositorySpringData cannot be null", ex.getMessage());
    }

    @Test
    void constructorShouldThrowWhenExceptionIDMapperIsNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new CourseInStudyPlanRepositorySpringDataImpl(_iCourseInStudyPlanMapper, _iCourseInStudyPlanRepositorySpringData, null, _iStudyPlanIDMapper));
        assertEquals("iCourseInStudyPlanIDMapper cannot be null", ex.getMessage());
    }

    @Test
    void constructorShouldThrowWhenStudyPlanIDMapperIsNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new CourseInStudyPlanRepositorySpringDataImpl(_iCourseInStudyPlanMapper, _iCourseInStudyPlanRepositorySpringData, _iCourseInStudyPlanIDMapper, null));
        assertEquals("iStudyPlanIDMapper cannot be null", ex.getMessage());
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

    @Test
    void shouldReturnTotalCreditsEctsInStudyPlanSoFar() {
        // arrange
        StudyPlanIDDataModel studyPlanIDDataModel = mock(StudyPlanIDDataModel.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        when(_iStudyPlanIDMapper.toDataModel(studyPlanID)).thenReturn(studyPlanIDDataModel);
        Semester semester = mock(Semester.class);
        when(semester.toInt()).thenReturn(1);
        CurricularYear curricularYear = mock(CurricularYear.class);
        when(curricularYear.toInt()).thenReturn(1);
        DurationCourseInCurricularYear durationOfCourse = mock(DurationCourseInCurricularYear.class);
        when(durationOfCourse.getDuration()).thenReturn(1);

        when(_iCourseInStudyPlanRepositorySpringData.sumCombinedCredits(studyPlanIDDataModel, semester.toInt(), curricularYear.toInt()))
                .thenReturn(30.0);

        // act
        double result = _courseInStudyPlanRepositorySpringDataImpl.getTotalCreditsEctsInStudyPlanSoFar(studyPlanID, semester, curricularYear, durationOfCourse);

        // assert
        assertEquals(30, result);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenParametersAreNull() {
        // act & assert
        assertThrows(IllegalArgumentException.class, () -> _courseInStudyPlanRepositorySpringDataImpl.getTotalCreditsEctsInStudyPlanSoFar(null, null, null, null));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenStudyPlanIDIsNull() {
        // act & assert
        assertThrows(IllegalArgumentException.class, () -> _courseInStudyPlanRepositorySpringDataImpl.getTotalCreditsEctsInStudyPlanSoFar(null, mock(Semester.class), mock(CurricularYear.class), mock(DurationCourseInCurricularYear.class)));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSemesterIsNull() {
        // act & assert
        assertThrows(IllegalArgumentException.class, () -> _courseInStudyPlanRepositorySpringDataImpl.getTotalCreditsEctsInStudyPlanSoFar(mock(StudyPlanID.class), null, mock(CurricularYear.class), mock(DurationCourseInCurricularYear.class)));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenCurricularYearIsNull() {
        // act & assert
        assertThrows(IllegalArgumentException.class, () -> _courseInStudyPlanRepositorySpringDataImpl.getTotalCreditsEctsInStudyPlanSoFar(mock(StudyPlanID.class), mock(Semester.class), null, mock(DurationCourseInCurricularYear.class)));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenDurationCourseInCurricularYearIsNull() {
        // act & assert
        assertThrows(IllegalArgumentException.class, () -> _courseInStudyPlanRepositorySpringDataImpl.getTotalCreditsEctsInStudyPlanSoFar(mock(StudyPlanID.class), mock(Semester.class), mock(CurricularYear.class), null));
    }

    @Test
    void shouldHaveNullDataModelWhenIDIsNull() {
        // Arrange
        CourseInStudyPlanID id = null;

        // Act
        CourseInStudyPlanIDDataModel result = _iCourseInStudyPlanIDMapper.toDataModel(id);

        // Assert
        assertNull(result, "DataModel should be null when ID is null");
    }
}