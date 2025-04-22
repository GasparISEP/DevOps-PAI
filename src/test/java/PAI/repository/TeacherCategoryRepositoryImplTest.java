package PAI.repository;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.domain.TeacherCategory;
import PAI.factory.ITeacherCategoryFactory;
import PAI.factory.ITeacherCategoryListFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TeacherCategoryRepositoryImplTest {

    private TeacherCategoryRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        ITeacherCategoryFactory factory = name -> new TeacherCategory(new TeacherCategoryID(UUID.randomUUID()), name);

        ITeacherCategoryListFactory listFactory = ArrayList::new;

        repository = new TeacherCategoryRepositoryImpl(factory, listFactory);
    }

    @Test
    void shouldSaveAndRetrieveCategoryById() {
        // Arrange
        TeacherCategoryID id = new TeacherCategoryID();
        Name name = new Name("Assistente");
        TeacherCategory category = new TeacherCategory(id, name);

        // Act
        repository.save(category);
        Optional<TeacherCategory> result = repository.ofIdentity(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(category, result.get());
    }

    @Test
    void shouldReturnFalseIfIdNotFound() {
        // Act
        boolean exists = repository.containsOfIdentity(new TeacherCategoryID());

        // Assert
        assertFalse(exists);
    }

    @Test
    void shouldDetectExistingCategoryByName() {
        // Arrange
        Name name = new Name("Catedrático");
        TeacherCategory category = new TeacherCategory(new TeacherCategoryID(), name);
        repository.save(category);

        // Act & Assert
        assertTrue(repository.existsByName(name));
    }

    @Test
    void shouldReturnAllSavedCategories() {
        // Arrange
        repository.save(new TeacherCategory(new TeacherCategoryID(), new Name("Catedrático")));
        repository.save(new TeacherCategory(new TeacherCategoryID(), new Name("Assistente")));

        // Act
        Iterable<TeacherCategory> all = repository.findAll();

        // Assert
        assertNotNull(all);
        assertTrue(all.iterator().hasNext());
    }

    @Test
    void shouldFindByNameIfExists() {
        // Arrange
        Name name = new Name("Auxiliar");
        repository.save(new TeacherCategory(new TeacherCategoryID(), name));

        // Act
        Optional<TeacherCategory> found = repository.findByName(name);

        // Assert
        assertTrue(found.isPresent());
        assertEquals(name, found.get().getName());
    }

    @Test
    void shouldReturnOptionalEmptyIfNameNotFound() {
        // Act
        Optional<TeacherCategory> found = repository.findByName(new Name("Inexistente"));

        // Assert
        assertTrue(found.isEmpty());
    }

    @Test
    void shouldReturnIdIfFoundByName() {
        // Arrange
        Name name = new Name("Catedrático");
        TeacherCategory category = new TeacherCategory(new TeacherCategoryID(), name);
        repository.save(category);

        // Act
        Optional<TeacherCategoryID> id = repository.getTeacherCategoryIDFromName(name);

        // Assert
        assertTrue(id.isPresent());
    }

    @Test
    void shouldReturnEmptyIdIfNameNotMatched() {
        // Act
        Optional<TeacherCategoryID> id = repository.getTeacherCategoryIDFromName(new Name("Inexistente"));

        // Assert
        assertTrue(id.isEmpty());
    }

    @Test
    void registerShouldAddIfNotExists() {
        // Arrange
        Name name = new Name("Novo");

        // Act
        boolean registered = repository.registerTeacherCategory(name);

        // Assert
        assertTrue(registered);
        assertTrue(repository.existsByName(name));
    }

    @Test
    void registerShouldReturnFalseIfAlreadyExists() {
        // Arrange
        Name name = new Name("Duplicado");
        repository.registerTeacherCategory(name);

        // Act
        boolean registeredAgain = repository.registerTeacherCategory(name);

        // Assert
        assertFalse(registeredAgain);
    }

    @Test
    void registerShouldThrowIfNameIsNull() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> repository.registerTeacherCategory(null));
    }

    @Test
    void registerTeacherCategoryShouldThrowIfNameIsNull() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> repository.registerTeacherCategory(null));
    }

    @Test
    void registerTeacherCategoryShouldReturnFalseIfFactoryThrowsException() {
        // Arrange
        ITeacherCategoryFactory mockFactory = mock(ITeacherCategoryFactory.class);
        ITeacherCategoryListFactory mockListFactory = mock(ITeacherCategoryListFactory.class);
        when(mockListFactory.getTeacherCategoryList()).thenReturn(new ArrayList<>());

        // Simular exceção ao tentar criar categoria
        when(mockFactory.createTeacherCategory(any())).thenThrow(new RuntimeException("Erro na factory"));

        TeacherCategoryRepositoryImpl repo = new TeacherCategoryRepositoryImpl(mockFactory, mockListFactory);

        // Act
        boolean result = repo.registerTeacherCategory(new Name("Catedrático"));

        // Assert
        assertFalse(result, "Deve retornar false se a factory lançar exceção");
    }

    @Test
    void constructorShouldThrowIfFactoryIsNull() {
        ITeacherCategoryListFactory listFactory = ArrayList::new;
        assertThrows(IllegalArgumentException.class, () -> new TeacherCategoryRepositoryImpl(null, listFactory));
    }

    @Test
    void constructorShouldThrowIfListFactoryIsNull() {
        ITeacherCategoryFactory factory = name -> new TeacherCategory(new TeacherCategoryID(), name);
        assertThrows(IllegalArgumentException.class, () -> new TeacherCategoryRepositoryImpl(factory, null));
    }

}
