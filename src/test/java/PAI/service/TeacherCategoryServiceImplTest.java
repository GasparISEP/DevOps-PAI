package PAI.service;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.VOs.TeacherID;
import PAI.domain.TeacherCategory;
import PAI.factory.ITeacherCategoryFactory;
import PAI.repository.ITeacherCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TeacherCategoryServiceImplTest {

    private ITeacherCategoryRepository repository;
    private ITeacherCategoryFactory factory;

    // SUT
    private ITeacherCategoryService service;

    @BeforeEach
    void setUp() {
        repository = mock(ITeacherCategoryRepository.class);
        factory = mock(ITeacherCategoryFactory.class);
        service = new TeacherCategoryServiceImpl(repository, factory);
    }

    @Test
    void testRegisterCategorySuccess() throws Exception {
        // Arrange
        when(repository.existsByName(any(Name.class))).thenReturn(false);
        TeacherCategory mockCategory = mock(TeacherCategory.class);
        when(factory.createTeacherCategory(any(Name.class))).thenReturn(mockCategory);
        when(repository.save(mockCategory)).thenReturn(mockCategory);

        // Act
        boolean result = service.registerCategory("Catedrático");

        // Assert
        assertTrue(result);
        verify(repository).existsByName(any(Name.class));
        verify(factory).createTeacherCategory(any(Name.class));
        verify(repository).save(mockCategory);
    }

    @Test
    void testRegisterCategoryAlreadyExists() {
        // Arrange
        when(repository.existsByName(any(Name.class))).thenReturn(true);

        // Act + Assert
        Exception ex = assertThrows(Exception.class,
                () -> service.registerCategory("Assistente"));
        assertEquals("Category already exists or could not be registered.", ex.getMessage());

        verify(repository).existsByName(any(Name.class));
        verify(factory, never()).createTeacherCategory(any());
        verify(repository, never()).save(any());
    }

    @Test
    void testNullDependenciesThrow() {
        assertThrows(IllegalArgumentException.class, () -> new TeacherCategoryServiceImpl(null, factory));
        assertThrows(IllegalArgumentException.class, () -> new TeacherCategoryServiceImpl(repository, null));
    }

    @Test
    void shouldThrowWhenNameIsInvalid() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> service.registerCategory(null)),
                () -> assertThrows(IllegalArgumentException.class, () -> service.registerCategory("")),
                () -> assertThrows(IllegalArgumentException.class, () -> service.registerCategory("a")),
                () -> assertThrows(IllegalArgumentException.class, () -> service.registerCategory(" inválido")),
                () -> assertThrows(IllegalArgumentException.class, () -> service.registerCategory("1nválido"))
        );
    }

    @Test
    void shouldReturnTrueIfTeacherCategoryExistsWhenCallingExistsByID(){
        //Arrange
        TeacherCategoryID id = mock(TeacherCategoryID.class);
        when(repository.containsOfIdentity(id)).thenReturn(true);
        //Act
        boolean result = service.existsById(id);
        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTeacherCategoryExistsWhenCallingExistsByID(){
        //Arrange
        TeacherCategoryID id = mock(TeacherCategoryID.class);
        when(repository.containsOfIdentity(id)).thenReturn(false);
        //Act
        boolean result = service.existsById(id);
        //Assert
        assertFalse(result);
    }
}
