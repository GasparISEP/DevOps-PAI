package PAI.service;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.domain.TeacherCategory;
import PAI.factory.ITeacherCategoryFactory;
import PAI.repository.ITeacherCategoryRepository;
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

    // TESTES DE CAIXA OPACA - registerCategory(String)

    @Test
    void whenRegisterValidCategory_thenReturnsTrue() throws Exception {
        // Teste de caixa opaca: comportamento correto ao registar uma categoria válida

        //Arrange
        String input = "Catedrático";
        Name name = new Name(input);
        TeacherCategory category = mock(TeacherCategory.class);

        when(repository.existsByName(name)).thenReturn(false);
        when(factory.createTeacherCategory(name)).thenReturn(category);
        when(repository.save(category)).thenReturn(category);

        //Act
        boolean result = service.registerCategory(input);

        //Assert
        assertTrue(result);
    }

    @Test
    void whenRegisterExistingCategory_thenThrowsException() {

        // Teste de caixa opaca: verificar se uma exceção é lançada ao tentar registar uma categoria existente

        //Arrange
        String input = "Assistente";
        Name name = new Name(input);
        when(repository.existsByName(name)).thenReturn(true);

        //Act & Assert
        Exception ex = assertThrows(Exception.class, () -> service.registerCategory(input));
        assertTrue(ex.getMessage().contains("already exists"));
    }

    @Test
    void whenInvalidCategoryName_thenThrowsIllegalArgumentException() {

        // Teste de caixa opaca: verificar se uma IllegalArgumentException é lançada para nomes inválidos

        // Arrange
        // (service já instanciado no BeforeEach)

        // Act & Assert
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> service.registerCategory(null)),
                () -> assertThrows(IllegalArgumentException.class, () -> service.registerCategory("")),
                () -> assertThrows(IllegalArgumentException.class, () -> service.registerCategory("jo")),
                () -> assertThrows(IllegalArgumentException.class, () -> service.registerCategory("123"))
        );
    }

    // TESTES DE CAIXA OPACA - existsById(TeacherCategoryID)=

    @Test
    void whenIdExists_thenReturnsTrue() {
        // Teste de caixa opaca: verificar se um ID existente é detetado

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

        // Teste de caixa opaca: verificar se um ID inexistente é detetado

        //Arrange
        TeacherCategoryID id = mock(TeacherCategoryID.class);
        when(repository.containsOfIdentity(id)).thenReturn(false);

        //Act
        boolean result = service.existsById(id);

        //Assert
        assertFalse(result);
    }

    // TESTES DE CAIXA OPACA - getAllTeacherCategories()

    @Test
    void shouldReturnAllTeacherCategories() {
        // Teste de caixa opaca: devolver todas as categorias existentes

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
        // Teste de caixa opaca: devolver lista vazia se não existirem categorias

        //Arrange
        when(repository.findAll()).thenReturn(List.of());

        //Act
        Iterable<TeacherCategory> result = service.getAllTeacherCategories();

        //Assert
        assertTrue(((List<TeacherCategory>) result).isEmpty());
    }

    // ============================================================
    // TESTE - Construtor TeacherCategoryServiceImpl
    // ============================================================

    @Test
    void constructorShouldThrowIfDependenciesAreNull() {
        // Teste : garantir que dependências nulas são rejeitadas

        // --- Arrange ---
        // 'repository' e 'factory' mockados no @BeforeEach

        // --- Act & Assert ---
        assertThrows(IllegalArgumentException.class, () -> new TeacherCategoryServiceImpl(null, factory));
        assertThrows(IllegalArgumentException.class, () -> new TeacherCategoryServiceImpl(repository, null));
    }
}
