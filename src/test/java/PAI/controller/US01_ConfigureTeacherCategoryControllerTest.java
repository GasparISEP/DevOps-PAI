package PAI.controller;
import PAI.domain.TeacherCategory;
import PAI.domain.TeacherCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class US01_ConfigureTeacherCategoryControllerTest {

    private TeacherCategoryRepository repository;
    private US01_ConfigureTeacherCategoryController controller;

    @BeforeEach
    public void setUp() {
        repository = new TeacherCategoryRepository();
        controller = new US01_ConfigureTeacherCategoryController(repository);
    }

    @Test
    public void testAddCategory() throws Exception {
        controller.addCategory("Math");
        TeacherCategory category = controller.getCategory("Math");
        assertNotNull(category);
        assertEquals("Math", category.getName());
    }

    @Test
    public void testAddDuplicateCategory() {
        assertThrows(Exception.class, () -> {
            controller.addCategory("Math");
            controller.addCategory("Math");
        });
    }

    @Test
    public void testGetCategory() throws Exception {
        controller.addCategory("Science");
        TeacherCategory category = controller.getCategory("Science");
        assertNotNull(category);
        assertEquals("Science", category.getName());
    }

    @Test
    public void testGetNonExistentCategory() {
        assertThrows(Exception.class, () -> {
            controller.getCategory("History");
        });
    }

    @Test
    public void testListCategories() throws Exception {
        controller.addCategory("Math");
        controller.addCategory("Science");
        List<TeacherCategory> categories = controller.listCategories();
        assertEquals(2, categories.size());
        assertTrue(categories.stream().anyMatch(c -> c.getName().equals("Math")));
        assertTrue(categories.stream().anyMatch(c -> c.getName().equals("Science")));
    }
}