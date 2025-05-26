package PAI.persistence.springdata.teacherCategory;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.domain.teacherCategory.TeacherCategory;
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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeacherCategoryRepositorySpringDataImplTest {

    @Mock
    private ITeacherCategoryRepositorySpringData jpaRepository;

    @Mock
    private ITeacherCategoryMapper mapper;

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

    // testing existByName method

    @Test
    void existsByName_shouldReturnTrue() {

        // Arrange
        Name doubleName = mock (Name.class);
        when (doubleName.getName()).thenReturn("Assistant");
        when(jpaRepository.existsByName(doubleName.getName())).thenReturn(true);

        // Act & Assert
        assertTrue(repository.existsByName(doubleName));
    }

    @Test
    void shouldReturnFalseIfTheTeacherCategoryNameDoesNotExist() {

        // Arrange
        Name doubleName = mock (Name.class);
        when (doubleName.getName()).thenReturn("Assistant");
        when(jpaRepository.existsByName(doubleName.getName())).thenReturn(false);

        // Act & Assert
        assertFalse(repository.existsByName(doubleName));
    }

    // testing save method

    @Test
    void save_shouldCallJpaRepositorySave() {
        //Arrange
        when(mapper.toDataModel(teacherCategory)).thenReturn(dataModel);

        //Act
        TeacherCategory result = repository.save(teacherCategory);

        //Assert
        assertEquals(teacherCategory, result);
    }

    //testing ofIdentity method

    @Test
    void ofIdentity_shouldReturnDomainModel() {

        //Arrange
        TeacherCategoryID id = mock(TeacherCategoryID.class);
        TeacherCategoryIDDataModel idDataModel = mock(TeacherCategoryIDDataModel.class);

        when(idMapper.toDataModel(id)).thenReturn(idDataModel);
        when(jpaRepository.findById(idDataModel)).thenReturn(Optional.of(dataModel));
        when(mapper.toDomainModel(dataModel)).thenReturn(teacherCategory);

        //Act
        Optional<TeacherCategory> result = repository.ofIdentity(id);

        //Assert
        assertTrue(result.isPresent());
        assertEquals(teacherCategory, result.get());
    }

    //testing containsOfIdentity method

    @Test
    void containsOfIdentity_shouldReturnTrue() {

        //Arrange
        TeacherCategoryID id = mock(TeacherCategoryID.class);
        TeacherCategoryIDDataModel idDataModel = mock(TeacherCategoryIDDataModel.class);

        when(idMapper.toDataModel(id)).thenReturn(idDataModel);
        when(jpaRepository.existsById(idDataModel)).thenReturn(true);

        // Act & Assert
        assertTrue(repository.containsOfIdentity(id));
    }

    @Test
    void containsOfIdentity_shouldReturnFalse() {

        //Arrange
        TeacherCategoryID id = mock(TeacherCategoryID.class);
        TeacherCategoryIDDataModel idDataModel = mock(TeacherCategoryIDDataModel.class);

        when(idMapper.toDataModel(id)).thenReturn(idDataModel);
        when(jpaRepository.existsById(idDataModel)).thenReturn(false);

        // Act & Assert
        assertFalse(repository.containsOfIdentity(id));
    }

    // testing findByName method

    @Test
    void findByName_shouldReturnDomainModel() {

        //Arrange
        Name doubleName = mock (Name.class);
        when(doubleName.getName()).thenReturn("Catedrático");

        when(jpaRepository.findByName(doubleName.getName())).thenReturn(Optional.of(dataModel));
        when(mapper.toDomainModel(dataModel)).thenReturn(teacherCategory);

        //Act
        Optional<TeacherCategory> result = repository.findByName(doubleName);

        //Assert
        assertTrue(result.isPresent());
        assertEquals(teacherCategory, result.get());
    }

    @Test
    void getTeacherCategoryIDFromName_shouldReturnMappedID() {

        //Arrange
        Name doubleName = mock (Name.class);
        when(doubleName.getName()).thenReturn("Auxiliar");

        TeacherCategoryIDDataModel idDataModel = mock(TeacherCategoryIDDataModel.class);
        TeacherCategoryID domainID = mock(TeacherCategoryID.class);

        when(jpaRepository.findByName(doubleName.getName())).thenReturn(Optional.of(dataModel));
        when(dataModel.getId()).thenReturn(idDataModel);
        when(idMapper.toDomainModel(idDataModel)).thenReturn(domainID);

        //Act
        Optional<TeacherCategoryID> result = repository.getTeacherCategoryIDFromName(doubleName);

        //Assert
        assertTrue(result.isPresent());
        assertEquals(domainID, result.get());
    }

    @Test
    void findAll_shouldReturnMappedList() {

        //Arrange
        when(jpaRepository.findAll()).thenReturn(List.of(dataModel));
        when(mapper.toDomainModel(dataModel)).thenReturn(teacherCategory);

        //Act
        List<TeacherCategory> result = (List<TeacherCategory>) repository.findAll();

        //Assert
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
    public void testGetTeacherCategoryNameByID() {

        // Arrange
        Name doubleName = mock (Name.class);
        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);
        TeacherCategory teacherCategory1 = mock(TeacherCategory.class);
        TeacherCategoryIDDataModel teacherCategoryIDDataModel = mock(TeacherCategoryIDDataModel.class);
        TeacherCategoryDataModel dataModel = mock(TeacherCategoryDataModel.class);

        when(doubleName.getName()).thenReturn("Assistant Professor");
        when(teacherCategoryID.getValue()).thenReturn(UUID.randomUUID());
        when(idMapper.toDataModel(teacherCategoryID)).thenReturn(teacherCategoryIDDataModel);
        when(jpaRepository.findById(teacherCategoryIDDataModel)).thenReturn(Optional.of(dataModel));
        when(mapper.toDomainModel(dataModel)).thenReturn(teacherCategory1);
        when(teacherCategory1.getName()).thenReturn(doubleName);

        // Act
        Optional<Name> result = repository.findNameByID(teacherCategoryID);

        // Assert
        assertEquals(Optional.of(teacherCategory1.getName()), result);
    }

    @Test
    public void testCantGetTeacherCategoryNameByIDIfEmpty() {

        //Arrange
        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);
        TeacherCategory teacherCategory1 = mock(TeacherCategory.class);
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
        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);

        when(idMapper.toDataModel(teacherCategoryID)).thenThrow(new IllegalArgumentException("Domain ID cannot be null"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            repository.findNameByID(teacherCategoryID);
        });

        assertEquals("Failed to retrieve and map Name by ID", exception.getMessage());
        assertInstanceOf(RuntimeException.class, exception.getCause());
    }
}
