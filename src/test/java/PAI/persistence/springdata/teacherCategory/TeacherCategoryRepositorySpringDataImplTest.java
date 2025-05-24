package PAI.persistence.springdata.teacherCategory;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.domain.teacherCategory.TeacherCategory;
import PAI.domain.teacherCategory.ITeacherCategoryFactory;
import PAI.mapper.teacherCategory.ITeacherCategoryMapper;
import PAI.mapper.teacherCategory.TeacherCategoryIDMapperImpl;
import PAI.persistence.datamodel.teacherCategory.TeacherCategoryDataModel;
import PAI.persistence.datamodel.teacherCategory.TeacherCategoryIDDataModel;
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

    @Test
    void getTeacherCategoryList_shouldReturnListOfTeacherCategories() {
        // Arrange
        when(jpaRepository.findAll()).thenReturn(List.of(dataModel));
        when(mapper.toDomainModel(dataModel)).thenReturn(teacherCategory);

        // Act
        List<TeacherCategory> result = repository.getTeacherCategoryList();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(teacherCategory, result.get(0));
    }

    @Test
    public void testGetTeacherCategoryNameByID() throws Exception {
        Name name = mock(Name.class);
        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);
        TeacherCategory teacherCategory1 = new TeacherCategory(teacherCategoryID, name);
        TeacherCategoryIDDataModel teacherCategoryIDDataModel = mock(TeacherCategoryIDDataModel.class);
        TeacherCategoryDataModel dataModel = mock(TeacherCategoryDataModel.class);

        // Arrange
        when(idMapper.toDataModel(teacherCategoryID)).thenReturn(teacherCategoryIDDataModel);
        when(jpaRepository.findById(teacherCategoryIDDataModel)).thenReturn(Optional.of(dataModel));
        when(mapper.toDomainModel(dataModel)).thenReturn(teacherCategory1);

        // Act
        Optional<Name> result = repository.findNameByID(teacherCategoryID);

        // Assert
        assertEquals(Optional.of(teacherCategory1.getName()), result);
    }

    @Test
    public void testCantGetTeacherCategoryNameByIDIfEmpty() throws Exception {
        //Arrange
        Name name = mock(Name.class);
        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);
        TeacherCategory teacherCategory1 = new TeacherCategory(teacherCategoryID, name);
        TeacherCategoryIDDataModel teacherCategoryIDDataModel = mock(TeacherCategoryIDDataModel.class);
        TeacherCategoryDataModel dataModel = mock(TeacherCategoryDataModel.class);
        when(idMapper.toDataModel(teacherCategoryID)).thenReturn(teacherCategoryIDDataModel);
        when(mapper.toDomainModel(dataModel)).thenReturn(teacherCategory1);
        // Act
        Optional<Name> result = repository.findNameByID(teacherCategoryID);
        // Assert
        assertEquals(Optional.empty() ,result);
    }

    @Test
    void testFindNameByID_shouldThrowRuntimeException() {
        // Arrange
        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);  // Mock do ID

        // Simula o comportamento do idMapper, mas faz ele lançar uma exceção
        when(idMapper.toDataModel(teacherCategoryID)).thenThrow(new IllegalArgumentException("Domain ID cannot be null"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            repository.findNameByID(teacherCategoryID);
        });

        // Verifique se a mensagem da exceção está correta
        assertEquals("Failed to retrieve and map Name by ID", exception.getMessage());
        assertInstanceOf(RuntimeException.class, exception.getCause());
    }
}
