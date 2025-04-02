package PAI.controller;

import PAI.domain.TeacherCategory;
import PAI.factory.ITeacherCategoryFactory;
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
public class US01_ConfigureTeacherCategoryControllerTest {

    private ITeacherCategoryRepository repository;
    private ITeacherCategoryFactory factory;
    private US01_ConfigureTeacherCategoryController controller;

    @BeforeEach
    public void setUp() {
        repository = mock(ITeacherCategoryRepository.class);
        factory = mock(ITeacherCategoryFactory.class);
        controller = new US01_ConfigureTeacherCategoryController(repository, factory);
    }

    @Test
    public void testConstructorWithNullArguments() {
        assertThrows(IllegalArgumentException.class, () -> new US01_ConfigureTeacherCategoryController(null, factory));
        assertThrows(IllegalArgumentException.class, () -> new US01_ConfigureTeacherCategoryController(repository, null));
    }

    @Test
    public void testAddNewCategorySuccessfully() throws Exception {
        Name name = new Name("Matemática");
        TeacherCategory category = new TeacherCategory(new TeacherCategoryID(), name);

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
        TeacherCategory category = new TeacherCategory(new TeacherCategoryID(), name);

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