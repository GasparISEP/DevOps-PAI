package PAI.repository;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.domain.TeacherCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TeacherCategoryRepositoryImplTest {

    private TeacherCategoryRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        repository = new TeacherCategoryRepositoryImpl();
    }

    @Test
    void shouldSaveAndRetrieveCategoryById() {
        TeacherCategory category = new TeacherCategory(new TeacherCategoryID(), new Name("Professor Associado"));
        repository.save(category);

        Optional<TeacherCategory> result = repository.ofIdentity(category.getId());

        assertTrue(result.isPresent());
        assertEquals(category, result.get());
    }

    @Test
    void shouldReturnFalseIfCategoryIdNotFound() {
        boolean exists = repository.containsOfIdentity(new TeacherCategoryID());
        assertFalse(exists);
    }

    @Test
    void shouldDetectExistingCategoryByName() {
        Name name = new Name("Professor Catedrático");
        TeacherCategory category = new TeacherCategory(new TeacherCategoryID(), name);
        repository.save(category);

        assertTrue(repository.existsByName(name));
    }

    @Test
    void shouldReturnAllCategories() {
        repository.save(new TeacherCategory(new TeacherCategoryID(), new Name("Professor A")));
        repository.save(new TeacherCategory(new TeacherCategoryID(), new Name("Professor B")));

        Iterable<TeacherCategory> all = repository.findAll();

        assertNotNull(all);
        assertTrue(all.iterator().hasNext());
    }
}