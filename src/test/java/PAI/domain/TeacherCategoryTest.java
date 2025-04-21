package PAI.domain;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TeacherCategoryTest {

    @Test
    void shouldCreateTeacherCategorySuccessfully() {
        // Arrange
        TeacherCategoryID id = new TeacherCategoryID();
        Name name = new Name("Catedrático");

        // Act
        TeacherCategory category = new TeacherCategory(id, name);

        // Assert
        assertNotNull(category);
        assertEquals(id, category.getId());
        assertEquals(name, category.getName());
        assertEquals("Catedrático", category.getNameValue());
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull() {
        // Arrange
        Name name = new Name("Assistente");

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new TeacherCategory(null, name));
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        // Arrange
        TeacherCategoryID id = new TeacherCategoryID();

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new TeacherCategory(id, null));
    }

    @Test
    void shouldBeEqualToItself() {
        // Arrange
        TeacherCategory category = new TeacherCategory(new TeacherCategoryID(), new Name("Auxiliar"));

        // Act + Assert
        assertEquals(category, category);
    }

    @Test
    void shouldNotBeEqualToDifferentType() {
        // Arrange
        TeacherCategory category = new TeacherCategory(new TeacherCategoryID(), new Name("Auxiliar"));
        Object other = new Object();

        // Act + Assert
        assertNotEquals(category, other);
    }

    @Test
    void shouldBeEqualIfIdsAreEqualEvenIfNamesDiffer() {
        // Arrange
        TeacherCategoryID sharedId = new TeacherCategoryID();
        TeacherCategory c1 = new TeacherCategory(sharedId, new Name("Catedrático"));
        TeacherCategory c2 = new TeacherCategory(sharedId, new Name("Assistente")); // different name

        // Act + Assert
        assertEquals(c1, c2); // Equality by ID
    }

    @Test
    void shouldNotBeEqualIfIdsAreDifferent() {
        // Arrange
        TeacherCategory c1 = new TeacherCategory(new TeacherCategoryID(), new Name("Assistente"));
        TeacherCategory c2 = new TeacherCategory(new TeacherCategoryID(), new Name("Assistente"));

        // Act + Assert
        assertNotEquals(c1, c2);
    }

    @Test
    void shouldHaveSameHashCodeIfSameId() {
        // Arrange
        TeacherCategoryID id = new TeacherCategoryID();
        TeacherCategory c1 = new TeacherCategory(id, new Name("Catedrático"));
        TeacherCategory c2 = new TeacherCategory(id, new Name("Auxiliar"));

        // Act + Assert
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    void sameAsShouldReturnTrueForSameId() {
        // Arrange
        TeacherCategoryID id = new TeacherCategoryID();
        TeacherCategory c1 = new TeacherCategory(id, new Name("Catedrático"));
        TeacherCategory c2 = new TeacherCategory(id, new Name("Assistente"));

        // Act + Assert
        assertTrue(c1.sameAs(c2));
    }

    @Test
    void shouldReturnCorrectIdAndNameValues() {
        // Arrange
        TeacherCategoryID id = new TeacherCategoryID();
        Name name = new Name("Auxiliar");
        TeacherCategory category = new TeacherCategory(id, name);

        // Act + Assert
        assertEquals(id.getValue(), category.getIdValue());
        assertEquals(name.getName(), category.getNameValue());
    }

    @Test
    void sameAsShouldReturnFalseForDifferentId() {
        // Arrange
        TeacherCategory c1 = new TeacherCategory(new TeacherCategoryID(), new Name("Catedrático"));
        TeacherCategory c2 = new TeacherCategory(new TeacherCategoryID(), new Name("Catedrático"));

        // Act + Assert
        assertFalse(c1.sameAs(c2));
    }

    @Test
    void toStringShouldReturnExpectedFormat() {
        // Arrange
        TeacherCategoryID id = new TeacherCategoryID();
        Name name = new Name("Assistente");
        TeacherCategory category = new TeacherCategory(id, name);

        // Act
        String result = category.toString();

        // Assert
        assertTrue(result.contains(id.toString()));
        assertTrue(result.contains(name.toString())); // se o Name não sobrescreve toString, isto será um hash
    }
}
