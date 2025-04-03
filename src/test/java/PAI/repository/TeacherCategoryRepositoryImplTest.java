package PAI.repository;

import PAI.VOs.*;
import PAI.domain.TeacherCategory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @Test
    void shouldReturnOptionalTeacherCategoryIDIfTeacherCategoryWasFound() {
        //Arrange
        Name nameDouble = mock(Name.class);
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        TeacherCategoryID tcIDDouble = mock(TeacherCategoryID.class);

        when(tcDouble.getName()).thenReturn(nameDouble);
        when(tcDouble.getId()).thenReturn(tcIDDouble);

        repository.save(tcDouble);

        //Act
        Optional<TeacherCategoryID> result = repository.getTeacherCategoryIDFromName(nameDouble);

        //Assert
        assertTrue(result.isPresent());
    }

    @Test
    void shouldReturnOptionalEmptyIfTeacherCategoryNotFound() {
        //Arrange
        Name nameDouble = mock(Name.class);
        Name nameDouble2 = mock(Name.class);
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        TeacherCategoryID tcIDDouble = mock(TeacherCategoryID.class);

        repository.save(tcDouble);

        when(tcDouble.getName()).thenReturn(nameDouble2);

        //Act
        Optional<TeacherCategoryID> result = repository.getTeacherCategoryIDFromName(nameDouble);

        //Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnOptionalEmptyIfTeacherCategoryListEmpty() {
        //Arrange
        Name nameDouble = mock(Name.class);

        //Act
        Optional<TeacherCategoryID> result = repository.getTeacherCategoryIDFromName(nameDouble);

        //Assert
        assertTrue(result.isEmpty());
    }
}