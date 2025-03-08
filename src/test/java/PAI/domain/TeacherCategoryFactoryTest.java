package PAI.domain;

import PAI.factory.TeacherCategoryFactory;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

public class TeacherCategoryFactoryTest {

    @Test
    void shouldCreateTeacherCategory() throws Exception {
        // Arrange
        String categoryName = "Professor Adjunto";

        // Mocking the construction of TeacherCategory
        try (MockedConstruction<TeacherCategory> mockedConstruction = mockConstruction(
                TeacherCategory.class,
                (mock, context) -> {
                    // Stub the getName method to return the categoryName
                    String actualName = (String) context.arguments().get(0);
                    when(mock.getName()).thenReturn(actualName);
                })) {

            // Act: Call the factory to create a TeacherCategory
            TeacherCategoryFactory factory = new TeacherCategoryFactory();
            TeacherCategory category = factory.createTeacherCategory(categoryName);

            // Assert
            // Ensure the TeacherCategory was created through the factory
            List<TeacherCategory> createdInstances = mockedConstruction.constructed();
            assertEquals(1, createdInstances.size());  // Ensure only one instance was created
            assertEquals(categoryName, createdInstances.get(0).getName());  // Check the name of the created category
            assertEquals(categoryName, category.getName());  // Check the name of the returned category
        }
    }

    @Test
    void shouldCreateTeacherCategoryDirectly() throws Exception {
        // Arrange
        String categoryName = "Professor Adjunto";

        // Mocking the construction of TeacherCategory directly
        try (MockedConstruction<TeacherCategory> mockedConstruction = mockConstruction(
                TeacherCategory.class,
                (mock, context) -> {
                    String actualName = (String) context.arguments().get(0);
                    when(mock.getName()).thenReturn(actualName);
                })) {

            // Act: Call the constructor directly instead of using a factory
            TeacherCategory category = new TeacherCategory(categoryName);

            // Assert
            List<TeacherCategory> createdInstances = mockedConstruction.constructed();
            assertEquals(1, createdInstances.size());
            assertEquals(categoryName, createdInstances.get(0).getName());
            assertEquals(categoryName, category.getName());
        }
    }
    @Test
    void shouldThrowExceptionWithNullName() {
        // Arrange
        TeacherCategoryFactory factory = new TeacherCategoryFactory();

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> factory.createTeacherCategory(null));
        assertEquals("Name must be non-empty string.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWithEmptyName() {
        // Arrange
        TeacherCategoryFactory factory = new TeacherCategoryFactory();

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> factory.createTeacherCategory(""));
        assertEquals("Name must be non-empty string.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWithBlankName() {
        // Arrange
        TeacherCategoryFactory factory = new TeacherCategoryFactory();

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> factory.createTeacherCategory("   "));
        assertEquals("Name must be non-empty string.", exception.getMessage());
    }


//    @Test
//    void shouldCreateTeacherCategory() throws Exception {
//        // Arrange
//        TeacherCategoryFactory factory = new TeacherCategoryFactory();
//        String categoryName = "Professor Adjunto";
//
//        // Act
//        TeacherCategory category = factory.createTeacherCategory(categoryName);
//
//        // Assert
//        assertNotNull(category); //checks that TeacherCategory object is not null
//        assertEquals(categoryName, category.getName()); //checks if name of category is the same as the one passed as parameter
//    }
//@Test
//void shouldThrowExceptionWithNullName() {
//    // Arrange
//    TeacherCategoryFactory factory = new TeacherCategoryFactory();
//
//    // Act & Assert
//    assertThrows(Exception.class, () -> factory.createTeacherCategory(null)); //checks if exception is thrown when name is null
//}
//
//    @Test
//    void shouldThrowExceptionWithEmptyName() {
//        // Arrange
//        TeacherCategoryFactory factory = new TeacherCategoryFactory();
//
//        // Act & Assert
//        assertThrows(Exception.class, () -> factory.createTeacherCategory(""));//checks if exception is thrown when name is empty
//    }
//
//    @Test
//    void shouldThrowExceptionWithBlankName() {
//        // Arrange
//        TeacherCategoryFactory factory = new TeacherCategoryFactory();
//
//        // Act & Assert
//        assertThrows(Exception.class, () -> factory.createTeacherCategory("   "));//checks if exception is thrown when name is blank
//
//    }

}
