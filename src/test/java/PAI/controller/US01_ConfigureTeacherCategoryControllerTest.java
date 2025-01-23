package PAI.controller;

import PAI.domain.TeacherCategory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class US01_ConfigureTeacherCategoryControllerTest {

    @Test
    void shouldCreateThisController() {
        // arrange
        US01_ConfigureTeacherCategoryController ctrl1 = new US01_ConfigureTeacherCategoryController();
        // act & assert
        assertNotNull(ctrl1);
    }

    @Test
    void shouldAddCategory() throws Exception {
        // arrange
        US01_ConfigureTeacherCategoryController ctrl1 = new US01_ConfigureTeacherCategoryController();
        // act
        ctrl1.addCategory("Math");
        // assert
        assertNotNull(ctrl1.getCategory("Math"));
    }

    @Test
    void shouldNotAddDuplicateCategory() {
        // arrange
        US01_ConfigureTeacherCategoryController ctrl1 = new US01_ConfigureTeacherCategoryController();
        // act & assert
        assertThrows(Exception.class, () -> {
            ctrl1.addCategory("Math");
            ctrl1.addCategory("Math");
        });
    }

    @Test
    void shouldGetCategory() throws Exception {
        // arrange
        US01_ConfigureTeacherCategoryController ctrl1 = new US01_ConfigureTeacherCategoryController();
        ctrl1.addCategory("Science");
        // act
        TeacherCategory category = ctrl1.getCategory("Science");
        // assert
        assertNotNull(category);
        assertEquals("Science", category.getName());
    }

    @Test
    void shouldReturnNullForNonExistentCategory() throws Exception {
        // arrange
        US01_ConfigureTeacherCategoryController ctrl1 = new US01_ConfigureTeacherCategoryController();
        // act
        TeacherCategory category = ctrl1.getCategory("NonExistent");
        // assert
        assertNull(category);
    }

    @Test
    void shouldListCategories() throws Exception {
        // arrange
        US01_ConfigureTeacherCategoryController ctrl1 = new US01_ConfigureTeacherCategoryController();
        ctrl1.addCategory("Math");
        ctrl1.addCategory("Science");
        // act
        List<TeacherCategory> categories = ctrl1.listCategories();
        // assert
        assertEquals(2, categories.size());
    }

    @Test
    void shouldThrowExceptionForInvalidCategoryName() {
        // arrange
        US01_ConfigureTeacherCategoryController ctrl1 = new US01_ConfigureTeacherCategoryController();
        // act & assert
        Exception exception = assertThrows(Exception.class, () -> {
            ctrl1.getCategory("");
        });
        assertEquals("Invalid category name: ", exception.getMessage());
    }
}