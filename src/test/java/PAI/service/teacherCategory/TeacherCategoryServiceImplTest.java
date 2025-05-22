package PAI.service.teacherCategory;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.domain.teacherCategory.TeacherCategory;
import PAI.domain.teacherCategory.ITeacherCategoryFactory;
import PAI.domain.repositoryInterfaces.teacherCategory.ITeacherCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeacherCategoryServiceImplTest {

    private ITeacherCategoryRepository repository;
    private ITeacherCategoryFactory factory;

    private TeacherCategoryServiceImpl service; // SUT

    @BeforeEach
    void setUp() {
        repository = mock(ITeacherCategoryRepository.class);
        factory = mock(ITeacherCategoryFactory.class);
        service = new TeacherCategoryServiceImpl(repository, factory);
    }

    // Testing configureTeacherCategory method

    @Test
    void whenRegisterValidCategory_thenReturnsTrue() throws Exception {

        //Arrange
        Name doubleName = mock (Name.class);
        TeacherCategory doubleTeacherCategory = mock(TeacherCategory.class);

        when(repository.existsByName(doubleName)).thenReturn(false);
        when(factory.createTeacherCategory(doubleName)).thenReturn(doubleTeacherCategory);
        when(repository.save(doubleTeacherCategory)).thenReturn(doubleTeacherCategory);

        //Act
        TeacherCategory result = service.configureTeacherCategory(doubleName);

        //Assert
        assertNotNull(result);
    }

    @Test
    void whenRegisterExistingCategory_thenThrowsException() {

        //Arrange
        Name doubleName = mock (Name.class);
        when(repository.existsByName(doubleName)).thenReturn(true);

        //Act & Assert
        Exception ex = assertThrows(Exception.class, () -> service.configureTeacherCategory(doubleName));
        assertTrue(ex.getMessage().contains("already exists"));
    }


    // Testing existsById method

    @Test
    void whenIdExists_thenReturnsTrue() {

        //Arrange
        TeacherCategoryID id = mock(TeacherCategoryID.class);
        when(repository.containsOfIdentity(id)).thenReturn(true);

        //Act
        boolean result = service.existsById(id);

        //Assert
        assertTrue(result);
    }

    @Test
    void whenIdDoesNotExist_thenReturnsFalse() {

        //Arrange
        TeacherCategoryID id = mock(TeacherCategoryID.class);
        when(repository.containsOfIdentity(id)).thenReturn(false);

        //Act
        boolean result = service.existsById(id);

        //Assert
        assertFalse(result);
    }

    // testing getAllTeacherCategories method

    @Test
    void shouldReturnAllTeacherCategories() {

        //Arrange
        TeacherCategory cat1 = mock(TeacherCategory.class);
        TeacherCategory cat2 = mock(TeacherCategory.class);
        List<TeacherCategory> categories = List.of(cat1, cat2);

        when(repository.findAll()).thenReturn(categories);

        //Act
        Iterable<TeacherCategory> result = service.getAllTeacherCategories();

        //Assert
        assertEquals(2, ((List<TeacherCategory>) result).size());
    }

    @Test
    void shouldReturnEmptyListIfNoTeacherCategoriesExist() {

        //Arrange
        when(repository.findAll()).thenReturn(List.of());

        //Act
        Iterable<TeacherCategory> result = service.getAllTeacherCategories();

        //Assert
        assertTrue(((List<TeacherCategory>) result).isEmpty());
    }

    // Testing TeacherCategoryServiceImpl Constructor

    @Test
    void constructorShouldThrowIfDependenciesAreNull() {

        // Arrange

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new TeacherCategoryServiceImpl(null, factory));
        assertThrows(IllegalArgumentException.class, () -> new TeacherCategoryServiceImpl(repository, null));
    }
}
