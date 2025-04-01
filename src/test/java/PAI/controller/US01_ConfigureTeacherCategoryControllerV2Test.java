package PAI.controller;

import PAI.domain.TeacherCategoryV2;
import PAI.factory.ITeacherCategoryFactoryV2;
import PAI.repository.ITeacherCategoryRepository;
import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Testes unitários para o controller V2 de configuração de categorias de professor.
 */
public class US01_ConfigureTeacherCategoryControllerV2Test {

    private ITeacherCategoryRepository repository;
    private ITeacherCategoryFactoryV2 factory;
    private US01_ConfigureTeacherCategoryControllerV2 controller;

    @BeforeEach
    public void setUp() {
        repository = mock(ITeacherCategoryRepository.class);
        factory = mock(ITeacherCategoryFactoryV2.class);
        controller = new US01_ConfigureTeacherCategoryControllerV2(repository, factory);
    }

    @Test
    public void testConstructorWithNullArguments() {
        assertThrows(IllegalArgumentException.class, () -> new US01_ConfigureTeacherCategoryControllerV2(null, factory));
        assertThrows(IllegalArgumentException.class, () -> new US01_ConfigureTeacherCategoryControllerV2(repository, null));
    }

    @Test
    public void testAddNewCategorySuccessfully() throws Exception {
        Name name = new Name("Matemática");
        TeacherCategoryV2 category = new TeacherCategoryV2(new TeacherCategoryID(), name);

        when(repository.existsByName(any(Name.class))).thenReturn(false);
        when(factory.createTeacherCategory(any(Name.class))).thenReturn(category);
        when(repository.save(category)).thenReturn(category);

        boolean result = controller.configureTeacherCategory("Matemática");

        assertTrue(result);
        verify(repository).save(category);
    }

    @Test
    public void testDuplicateCategoryThrowsException() throws Exception {
        Name name = new Name("Física Fisica");

        when(repository.existsByName(any(Name.class))).thenReturn(true);

        Exception ex = assertThrows(Exception.class, () -> controller.configureTeacherCategory("Física fisica"));
        assertEquals("Category already exists.", ex.getMessage());
    }

    @Test
    public void testCategoryWithSpecialCharacters() throws Exception {
        Name name = new Name("Comp-Sci");
        TeacherCategoryV2 category = new TeacherCategoryV2(new TeacherCategoryID(), name);

        when(repository.existsByName(any(Name.class))).thenReturn(false);
        when(factory.createTeacherCategory(any(Name.class))).thenReturn(category);
        when(repository.save(category)).thenReturn(category);

        boolean result = controller.configureTeacherCategory("Comp-Sci");

        assertTrue(result);
        verify(repository).save(category);
    }

    @Test
    public void testInvalidLowercaseNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Name("história"));
    }
}