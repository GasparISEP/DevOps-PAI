package PAI.persistence.springdata.teacherCategory;

import PAI.VOs.Name;
import PAI.domain.teacherCategory.TeacherCategory;
import PAI.domain.teacherCategory.TeacherCategoryFactorySpringImpl;
import PAI.mapper.teacherCategory.TeacherCategoryIDMapperImpl;
import PAI.mapper.teacherCategory.TeacherCategoryMapperImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({
        TeacherCategoryRepositorySpringDataImpl.class,
        TeacherCategoryMapperImpl.class,
        TeacherCategoryIDMapperImpl.class,
        TeacherCategoryFactorySpringImpl.class
})
class TeacherCategoryRepositoryPersistenceTest {

    @Autowired
    private TeacherCategoryRepositorySpringDataImpl repository;

    @Test
    void shouldPersistAndRetrieveTeacherCategory() {
        // Arrange
        Name name = new Name("Professor Auxiliar");

        // Act
        boolean registered = repository.registerTeacherCategory(name);
        List<TeacherCategory> allCategories = repository.getTeacherCategoryList();

        // Assert
        assertTrue(registered);
        assertEquals(1, allCategories.size());
        assertEquals("Professor Auxiliar", allCategories.get(0).getNameValue());
    }

    @Test
    void shouldNotRegisterDuplicateTeacherCategory() {
        // Arrange
        Name name = new Name("Professor Associado");
        repository.registerTeacherCategory(name);

        // Act
        boolean secondTry = repository.registerTeacherCategory(name);

        // Assert
        assertFalse(secondTry);
        assertEquals(1, repository.getTeacherCategoryList().size());
    }
}
