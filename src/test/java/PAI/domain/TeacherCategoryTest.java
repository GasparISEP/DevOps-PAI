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
        Name name = new Name("Professor Adjunto");

        // Act
        TeacherCategory category = new TeacherCategory(id, name);

        // Assert
        assertNotNull(category);
        assertEquals(id, category.getId());
        assertEquals(name, category.getName());
        assertEquals("Professor Adjunto", category.getNameValue());
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull() {
        Name name = new Name("Professor Associado");
        assertThrows(IllegalArgumentException.class, () -> new TeacherCategory(null, name));
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        TeacherCategoryID id = new TeacherCategoryID();
        assertThrows(IllegalArgumentException.class, () -> new TeacherCategory(id, null));
    }

    @Test
    void shouldBeEqualToItself() {
        TeacherCategory category = new TeacherCategory(new TeacherCategoryID(), new Name("Professor Adjunto"));
        assertEquals(category, category);
    }

    @Test
    void shouldNotBeEqualToDifferentType() {
        TeacherCategory category = new TeacherCategory(new TeacherCategoryID(), new Name("Professor Adjunto"));
        Object other = new Object();
        assertNotEquals(category, other);
    }

    @Test
    void shouldBeEqualIfIdsAreEqual() {
        TeacherCategoryID sharedId = new TeacherCategoryID();
        TeacherCategory c1 = new TeacherCategory(sharedId, new Name("Professor A"));
        TeacherCategory c2 = new TeacherCategory(sharedId, new Name("Professor B")); // different name

        assertEquals(c1, c2); // Equals by ID only
    }

    @Test
    void shouldNotBeEqualIfIdsAreDifferent() {
        TeacherCategory c1 = new TeacherCategory(new TeacherCategoryID(), new Name("Professor A"));
        TeacherCategory c2 = new TeacherCategory(new TeacherCategoryID(), new Name("Professor A"));

        assertNotEquals(c1, c2); // Different IDs
    }

    @Test
    void shouldHaveSameHashCodeIfSameId() {
        TeacherCategoryID id = new TeacherCategoryID();
        TeacherCategory c1 = new TeacherCategory(id, new Name("Amador"));
        TeacherCategory c2 = new TeacherCategory(id, new Name("Meio Amador"));

        assertEquals(c1.hashCode(), c2.hashCode());
    }
}