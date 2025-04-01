package PAI.repository;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.domain.TeacherCategoryV2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TeacherCategoryRepositoryV2ImplTest {

    private TeacherCategoryRepositoryV2Impl repository;

    @BeforeEach
    void setUp() {
        repository = new TeacherCategoryRepositoryV2Impl();
    }

    @Test
    void shouldSaveAndRetrieveCategoryById() {
        TeacherCategoryV2 category = new TeacherCategoryV2(new TeacherCategoryID(), new Name("Professor Associado"));
        repository.save(category);

        Optional<TeacherCategoryV2> result = repository.ofIdentity(category.getId());

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
        TeacherCategoryV2 category = new TeacherCategoryV2(new TeacherCategoryID(), name);
        repository.save(category);

        assertTrue(repository.existsByName(name));
    }

    @Test
    void shouldReturnAllCategories() {
        repository.save(new TeacherCategoryV2(new TeacherCategoryID(), new Name("Professor A")));
        repository.save(new TeacherCategoryV2(new TeacherCategoryID(), new Name("Professor B")));

        Iterable<TeacherCategoryV2> all = repository.findAll();

        assertNotNull(all);
        assertTrue(all.iterator().hasNext());
    }
}