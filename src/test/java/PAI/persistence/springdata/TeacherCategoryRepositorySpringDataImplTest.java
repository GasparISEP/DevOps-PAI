package PAI.persistence.springdata;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.domain.TeacherCategory;
import PAI.factory.ITeacherCategoryFactory;
import PAI.mapper.ITeacherCategoryMapper;
import PAI.mapper.TeacherCategoryIDMapperImpl;
import PAI.persistence.datamodel.TeacherCategoryDataModel;
import PAI.persistence.datamodel.TeacherCategoryIDDataModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeacherCategoryRepositorySpringDataImplTest {

    @Mock
    private ITeacherCategoryRepositorySpringData jpaRepository;

    @Mock
    private ITeacherCategoryMapper mapper;

    @Mock
    private ITeacherCategoryFactory factory;

    @Mock
    private TeacherCategoryIDMapperImpl idMapper;

    @Mock
    private TeacherCategory teacherCategory;

    @Mock
    private TeacherCategoryDataModel dataModel;

    @InjectMocks
    private TeacherCategoryRepositorySpringDataImpl repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerTeacherCategory_shouldSaveWhenNameNotExists() {
        Name name = new Name("Auxiliar");

        when(jpaRepository.existsByName("Auxiliar")).thenReturn(false);
        when(factory.createTeacherCategory(name)).thenReturn(teacherCategory);
        when(mapper.toDataModel(teacherCategory)).thenReturn(dataModel);

        boolean result = repository.registerTeacherCategory(name);

        assertTrue(result);
        verify(jpaRepository).save(dataModel);
    }

    @Test
    void registerTeacherCategory_shouldNotSaveWhenNameExists() {
        Name name = new Name("Auxiliar");

        when(jpaRepository.existsByName("Auxiliar")).thenReturn(true);

        boolean result = repository.registerTeacherCategory(name);

        assertFalse(result);
        verify(jpaRepository, never()).save(any());
    }

    @Test
    void existsByName_shouldReturnTrue() {
        Name name = new Name("Assistente");
        when(jpaRepository.existsByName("Assistente")).thenReturn(true);

        assertTrue(repository.existsByName(name));
    }

    @Test
    void save_shouldCallJpaRepositorySave() {
        when(mapper.toDataModel(teacherCategory)).thenReturn(dataModel);

        TeacherCategory result = repository.save(teacherCategory);

        verify(jpaRepository).save(dataModel);
        assertEquals(teacherCategory, result);
    }

    @Test
    void ofIdentity_shouldReturnDomainModel() {
        TeacherCategoryID id = mock(TeacherCategoryID.class);
        TeacherCategoryIDDataModel idDataModel = mock(TeacherCategoryIDDataModel.class);

        when(idMapper.toDataModel(id)).thenReturn(idDataModel);
        when(jpaRepository.findById(idDataModel)).thenReturn(Optional.of(dataModel));
        when(mapper.toDomainModel(dataModel)).thenReturn(teacherCategory);

        Optional<TeacherCategory> result = repository.ofIdentity(id);

        assertTrue(result.isPresent());
        assertEquals(teacherCategory, result.get());
    }

    @Test
    void containsOfIdentity_shouldReturnTrue() {
        TeacherCategoryID id = mock(TeacherCategoryID.class);
        TeacherCategoryIDDataModel idDataModel = mock(TeacherCategoryIDDataModel.class);

        when(idMapper.toDataModel(id)).thenReturn(idDataModel);
        when(jpaRepository.existsById(idDataModel)).thenReturn(true);

        assertTrue(repository.containsOfIdentity(id));
    }

    @Test
    void findByName_shouldReturnDomainModel() {
        Name name = new Name("Catedrático");

        when(jpaRepository.findByName("Catedrático")).thenReturn(Optional.of(dataModel));
        when(mapper.toDomainModel(dataModel)).thenReturn(teacherCategory);

        Optional<TeacherCategory> result = repository.findByName(name);

        assertTrue(result.isPresent());
        assertEquals(teacherCategory, result.get());
    }

    @Test
    void getTeacherCategoryIDFromName_shouldReturnMappedID() {
        Name name = new Name("Auxiliar");
        TeacherCategoryIDDataModel idDataModel = mock(TeacherCategoryIDDataModel.class);
        TeacherCategoryID domainID = mock(TeacherCategoryID.class);

        when(jpaRepository.findByName("Auxiliar")).thenReturn(Optional.of(dataModel));
        when(dataModel.getId()).thenReturn(idDataModel);
        when(idMapper.toDomainModel(idDataModel)).thenReturn(domainID);

        Optional<TeacherCategoryID> result = repository.getTeacherCategoryIDFromName(name);

        assertTrue(result.isPresent());
        assertEquals(domainID, result.get());
    }

    @Test
    void findAll_shouldReturnMappedList() {
        when(jpaRepository.findAll()).thenReturn(List.of(dataModel));
        when(mapper.toDomainModel(dataModel)).thenReturn(teacherCategory);

        List<TeacherCategory> result = (List<TeacherCategory>) repository.findAll();

        assertEquals(1, result.size());
        assertEquals(teacherCategory, result.get(0));
    }
}
