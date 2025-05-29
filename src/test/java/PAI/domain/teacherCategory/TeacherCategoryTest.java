package PAI.domain.teacherCategory;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TeacherCategoryTest {

    @Test
    void shouldCreateTeacherCategorySuccessfully() {
        // Arrange
        TeacherCategoryID doubleId = mock(TeacherCategoryID.class);
        Name doubleName = mock(Name.class);
        when(doubleName.getName()).thenReturn("Catedrático");

        // Act
        TeacherCategory category = new TeacherCategory(doubleId, doubleName);

        // Assert
        assertNotNull(category);
        assertEquals(doubleId, category.identity());
        assertEquals(doubleName, category.getName());
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull() {
        // Arrange
        Name doubleName = mock(Name.class);
        when(doubleName.getName()).thenReturn("Assistant");

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new TeacherCategory(null, doubleName));
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        // Arrange
        TeacherCategoryID doubleId = mock(TeacherCategoryID.class);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new TeacherCategory(doubleId, null));
    }

    @Test
    void shouldNotBeEqualToDifferentType() {
        // Arrange
        TeacherCategoryID doubleId = mock(TeacherCategoryID.class);
        Name doubleName = mock(Name.class);
        when(doubleName.getName()).thenReturn("Catedrático");
        TeacherCategory category = new TeacherCategory(doubleId, doubleName);

        Object other = mock (Object.class);

        // Act + Assert
        assertFalse(category.equals(other));
    }

    @Test
    void shouldBeEqualIfAreOfTheSameInstance() {
        // Arrange
        TeacherCategoryID doubleId = mock(TeacherCategoryID.class);
        Name doubleName = mock(Name.class);
        when(doubleName.getName()).thenReturn("Catedrático");
        TeacherCategory category = new TeacherCategory(doubleId, doubleName);
        TeacherCategory category1 = category;

        // Act + Assert
        assertTrue(category.equals(category1));
    }

    @Test
    void shouldBeEqualIfIdsAreEqualEvenIfNamesDiffer() {
        // Arrange
        TeacherCategoryID doubleId = mock(TeacherCategoryID.class);
        Name doubleName = mock(Name.class);
        when(doubleName.getName()).thenReturn("Catedrático");
        Name doubleName1 = mock(Name.class);
        when(doubleName1.getName()).thenReturn("Assistant");
        TeacherCategory category1 = new TeacherCategory(doubleId, doubleName);
        TeacherCategory category2 = new TeacherCategory(doubleId, doubleName1);

        // Act & Assert
        assertTrue(category1.equals(category2));
    }

    @Test
    void shouldReturnFalseIfIdsAndNamesAreNotEqual() {
        // Arrange
        TeacherCategoryID doubleId = mock(TeacherCategoryID.class);
        TeacherCategoryID doubleId1 = mock(TeacherCategoryID.class);
        Name doubleName = mock(Name.class);
        when(doubleName.getName()).thenReturn("Catedrático");
        Name doubleName1 = mock(Name.class);
        when(doubleName1.getName()).thenReturn("Assistant");
        TeacherCategory category1 = new TeacherCategory(doubleId, doubleName);
        TeacherCategory category2 = new TeacherCategory(doubleId1, doubleName1);

        // Act & Assert
        assertFalse(category1.equals(category2));
    }

    @Test
    void shouldHaveSameHashCodeIfSameId() {
        // Arrange
        TeacherCategoryID doubleId = mock(TeacherCategoryID.class);
        Name doubleName = mock(Name.class);
        when(doubleName.getName()).thenReturn("Assistant");
        Name doubleName1 = mock(Name.class);
        when(doubleName1.getName()).thenReturn("Assistant");

        // Act
        TeacherCategory category1 = new TeacherCategory(doubleId, doubleName);
        TeacherCategory category2 = new TeacherCategory(doubleId, doubleName1);

        // Act + Assert
        assertEquals(category1.hashCode(), category2.hashCode());
    }

    @Test
    void shouldHaveDifferentHashCodeIfHasDifferentId() {
        // Arrange
        TeacherCategoryID doubleId = mock(TeacherCategoryID.class);
        TeacherCategoryID doubleId1 = mock(TeacherCategoryID.class);
        Name doubleName = mock(Name.class);
        when(doubleName.getName()).thenReturn("Assistant");

        // Act
        TeacherCategory category1 = new TeacherCategory(doubleId, doubleName);
        TeacherCategory category2 = new TeacherCategory(doubleId1, doubleName);

        // Act + Assert
        assertNotEquals(category1.hashCode(), category2.hashCode());
    }

    @Test
    void sameAsShouldReturnTrueForSameIdAndName() {
        // Arrange
        TeacherCategoryID doubleId = mock(TeacherCategoryID.class);
        Name doubleName = mock(Name.class);
        when(doubleName.getName()).thenReturn("Assistant");
        TeacherCategory category1 = new TeacherCategory(doubleId, doubleName);
        TeacherCategory category2 = category1;

        // Act + Assert
        assertTrue(category1.sameAs(category2));
    }

    @Test
    void sameAsShouldReturnFalseForDifferentId() {
        // Arrange
        TeacherCategoryID doubleId = mock(TeacherCategoryID.class);
        TeacherCategoryID doubleId1 = mock(TeacherCategoryID.class);
        Name doubleName = mock(Name.class);
        when(doubleName.getName()).thenReturn("Assistant");
        TeacherCategory category1 = new TeacherCategory(doubleId, doubleName);
        TeacherCategory category2 = new TeacherCategory(doubleId1, doubleName);

        // Act + Assert
        assertFalse(category1.sameAs(category2));
    }

    @Test
    void sameAsShouldReturnFalseIfNameAndIDIsDifferent() {
        // Arrange
        TeacherCategoryID doubleId = mock(TeacherCategoryID.class);
        TeacherCategoryID doubleId1 = mock(TeacherCategoryID.class);
        Name doubleName = mock(Name.class);
        when(doubleName.getName()).thenReturn("Assistant");
        Name doubleName1 = mock(Name.class);
        when(doubleName1.getName()).thenReturn("Principal Professor");
        TeacherCategory category1 = new TeacherCategory(doubleId, doubleName);
        TeacherCategory category2 = new TeacherCategory(doubleId1, doubleName1);

        // Act + Assert
        assertFalse(category1.sameAs(category2));
    }

    @Test
    void sameAsShouldReturnFalseIfNameIsDifferent() {
        // Arrange
        TeacherCategoryID doubleId = mock(TeacherCategoryID.class);
        Name doubleName = mock(Name.class);
        when(doubleName.getName()).thenReturn("Assistant");
        Name doubleName1 = mock(Name.class);
        when(doubleName1.getName()).thenReturn("Principal Professor");
        TeacherCategory category1 = new TeacherCategory(doubleId, doubleName);
        TeacherCategory category2 = new TeacherCategory(doubleId, doubleName1);

        // Act + Assert
        assertFalse(category1.sameAs(category2));
    }

    @Test
    void sameAsShouldReturnFalseIfInputIsNull() {
        // Arrange
        TeacherCategoryID doubleId = mock(TeacherCategoryID.class);
        Name doubleName = mock(Name.class);
        when(doubleName.getName()).thenReturn("Assistant");
        Name doubleName1 = mock(Name.class);
        when(doubleName1.getName()).thenReturn("Assistant");
        TeacherCategory category1 = new TeacherCategory(doubleId, doubleName);

        // Act + Assert
        assertFalse(category1.sameAs(null));
    }

    @Test
    void sameAsShouldReturnFalseIfTheyAreNotOfTheSameInstance() {
        // Arrange
        TeacherCategoryID doubleId = mock(TeacherCategoryID.class);
        Name doubleName = mock(Name.class);
        when(doubleName.getName()).thenReturn("Assistant");
        Name doubleName1 = mock(Name.class);
        when(doubleName1.getName()).thenReturn("Assistant");
        TeacherCategory category1 = new TeacherCategory(doubleId, doubleName);
        Object doubleObject = mock (Object.class);

        // Act + Assert
        assertFalse(category1.sameAs(doubleObject));
    }

    @Test
    void shouldReturnCorrectIdAndNameValues() {
        // Arrange
        TeacherCategoryID doubleId = mock(TeacherCategoryID.class);
        Name doubleName = mock(Name.class);
        when(doubleName.getName()).thenReturn("Catedrático");
        TeacherCategory category = new TeacherCategory(doubleId, doubleName);

        // Act + Assert
        assertEquals(doubleId.getValue(), category.identity().getValue());
        assertEquals(doubleName.getName(), category.getName().getName());
    }

    @Test
    void toStringShouldReturnExpectedFormat() {
        // Arrange
        TeacherCategoryID doubleId = mock(TeacherCategoryID.class);
        Name doubleName = mock(Name.class);
        when(doubleName.getName()).thenReturn("Catedrático");
        TeacherCategory category = new TeacherCategory(doubleId, doubleName);

        // Act
        String result = category.toString();

        // Assert
        assertTrue(result.contains(doubleId.toString()));
        assertTrue(result.contains(doubleName.toString()));
    }
}
