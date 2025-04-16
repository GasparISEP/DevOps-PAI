package PAI.repository;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.domain.TeacherCategory;
import PAI.factory.ITeacherCategoryFactory;
import PAI.factory.ITeacherCategoryListFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TeacherCategoryRepositoryImplTest {

    private TeacherCategoryRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        // Provide an inline implementation for ITeacherCategoryFactory:
        ITeacherCategoryFactory teacherCategoryFactory = new ITeacherCategoryFactory() {
            @Override
            public TeacherCategory createTeacherCategory(Name name) {
                // Create a new TeacherCategory with a random UUID as the ID.
                return new TeacherCategory(new TeacherCategoryID(UUID.randomUUID()), name);
            }
        };

        // Provide an inline implementation for ITeacherCategoryListFactory:
        ITeacherCategoryListFactory teacherCategoryListFactory = new ITeacherCategoryListFactory() {
            @Override
            public List<TeacherCategory> getTeacherCategoryList() {
                // Return a new empty ArrayList to act as our in-memory store.
                return new ArrayList<>();
            }
        };

        repository = new TeacherCategoryRepositoryImpl(teacherCategoryFactory, teacherCategoryListFactory);
    }

    @Test
    void shouldSaveAndRetrieveCategoryById() {
        TeacherCategory category = new TeacherCategory(new TeacherCategoryID(UUID.randomUUID()),
                new Name("Professor Associado"));
        repository.save(category);

        Optional<TeacherCategory> result = repository.ofIdentity(category.getId());

        assertTrue(result.isPresent());
        assertEquals(category, result.get());
    }

    @Test
    void shouldReturnFalseIfCategoryIdNotFound() {
        boolean exists = repository.containsOfIdentity(new TeacherCategoryID(UUID.randomUUID()));
        assertFalse(exists);
    }

    @Test
    void shouldDetectExistingCategoryByName() {
        Name name = new Name("Professor Catedrático");
        TeacherCategory category = new TeacherCategory(new TeacherCategoryID(UUID.randomUUID()), name);
        repository.save(category);

        assertTrue(repository.existsByName(name));
    }

    @Test
    void shouldReturnAllCategories() {
        repository.save(new TeacherCategory(new TeacherCategoryID(UUID.randomUUID()), new Name("Professor A")));
        repository.save(new TeacherCategory(new TeacherCategoryID(UUID.randomUUID()), new Name("Professor B")));

        Iterable<TeacherCategory> all = repository.findAll();

        assertNotNull(all);
        assertTrue(all.iterator().hasNext());
    }

    @Test
    void shouldReturnOptionalTeacherCategoryIDIfTeacherCategoryWasFound() {
        // Arrange using Mockito mocks for Name, TeacherCategory and TeacherCategoryID
        Name nameDouble = mock(Name.class);
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        TeacherCategoryID tcIDDouble = mock(TeacherCategoryID.class);

        when(tcDouble.getName()).thenReturn(nameDouble);
        when(tcDouble.getId()).thenReturn(tcIDDouble);

        repository.save(tcDouble);

        // Act
        Optional<TeacherCategoryID> result = repository.getTeacherCategoryIDFromName(nameDouble);

        // Assert
        assertTrue(result.isPresent());
    }

    @Test
    void shouldReturnOptionalEmptyIfTeacherCategoryNotFound() {
        // Arrange
        Name nameDouble = mock(Name.class);
        Name nameDouble2 = mock(Name.class);
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        TeacherCategoryID tcIDDouble = mock(TeacherCategoryID.class);

        repository.save(tcDouble);

        when(tcDouble.getName()).thenReturn(nameDouble2);

        // Act
        Optional<TeacherCategoryID> result = repository.getTeacherCategoryIDFromName(nameDouble);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnOptionalEmptyIfTeacherCategoryListEmpty() {
        // Arrange
        Name nameDouble = mock(Name.class);

        // Act
        Optional<TeacherCategoryID> result = repository.getTeacherCategoryIDFromName(nameDouble);

        // Assert
        assertTrue(result.isEmpty());
    }
}
