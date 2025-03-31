package PAI.domain;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TeacherCategoryV2Test {

    @Test
    void shouldCreateTeacherCategorySuccessfully() {
        // Arrange
        TeacherCategoryID id = new TeacherCategoryID();
        Name name = new Name("Professor Adjunto");

        // Act
        TeacherCategoryV2 category = new TeacherCategoryV2(id, name);

        // Assert
        assertNotNull(category);
        assertEquals(id, category.getId());
        assertEquals(name, category.getName());
        assertEquals("Professor Adjunto", category.getNameValue());
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull() {
        Name name = new Name("Professor Associado");
        assertThrows(IllegalArgumentException.class, () -> new TeacherCategoryV2(null, name));
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        TeacherCategoryID id = new TeacherCategoryID();
        assertThrows(IllegalArgumentException.class, () -> new TeacherCategoryV2(id, null));
    }

    @Test
    void shouldBeEqualToItself() {
        TeacherCategoryV2 category = new TeacherCategoryV2(new TeacherCategoryID(), new Name("Professor Adjunto"));
        assertEquals(category, category);
    }

    @Test
    void shouldNotBeEqualToDifferentType() {
        TeacherCategoryV2 category = new TeacherCategoryV2(new TeacherCategoryID(), new Name("Professor Adjunto"));
        Object other = new Object();
        assertNotEquals(category, other);
    }

    @Test
    void shouldBeEqualIfIdsAreEqual() {
        TeacherCategoryID sharedId = new TeacherCategoryID();
        TeacherCategoryV2 c1 = new TeacherCategoryV2(sharedId, new Name("Professor A"));
        TeacherCategoryV2 c2 = new TeacherCategoryV2(sharedId, new Name("Professor B")); // different name

        assertEquals(c1, c2); // Equals by ID only
    }

    @Test
    void shouldNotBeEqualIfIdsAreDifferent() {
        TeacherCategoryV2 c1 = new TeacherCategoryV2(new TeacherCategoryID(), new Name("Professor A"));
        TeacherCategoryV2 c2 = new TeacherCategoryV2(new TeacherCategoryID(), new Name("Professor A"));

        assertNotEquals(c1, c2); // Different IDs
    }

    @Test
    void shouldHaveSameHashCodeIfSameId() {
        TeacherCategoryID id = new TeacherCategoryID();
        TeacherCategoryV2 c1 = new TeacherCategoryV2(id, new Name("Amador"));
        TeacherCategoryV2 c2 = new TeacherCategoryV2(id, new Name("Meio Amador"));

        assertEquals(c1.hashCode(), c2.hashCode());
    }
}