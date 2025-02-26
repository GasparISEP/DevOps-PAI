package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TeacherCategoryFactoryTest {

    @Test
    void shouldCreateTeacherCategory() throws Exception {
        // Arrange
        TeacherCategoryFactory factory = new TeacherCategoryFactory();
        String categoryName = "Professor Adjunto";

        // Act
        TeacherCategory category = factory.createTeacherCategory(categoryName);

        // Assert
        assertNotNull(category); //checks that TeacherCategory object is not null
        assertEquals(categoryName, category.getName()); //
    }
}
