package PAI.persistence.springdata.teacherCategory;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.domain.teacherCategory.TeacherCategory;
import PAI.mapper.teacherCategory.TeacherCategoryIDMapperImpl;
import PAI.mapper.teacherCategory.TeacherCategoryMapperImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@Import({
        TeacherCategoryRepositorySpringDataImpl.class,
        TeacherCategoryMapperImpl.class,
        TeacherCategoryIDMapperImpl.class
})
class TeacherCategoryRepositoryPersistenceTest {

    @Autowired
    private TeacherCategoryRepositorySpringDataImpl repository;

    @Test
    void shouldPersistAndRetrieveTeacherCategory() {
        // Arrange
        Name name = new Name("Professor Auxiliar");
        TeacherCategoryID id = new TeacherCategoryID(UUID.randomUUID());
        TeacherCategory teacherCategory = new TeacherCategory(id, name);

        // Act
        TeacherCategory registered = repository.save(teacherCategory);
        List<TeacherCategory> allCategories = repository.getTeacherCategoryList();

        // Assert
        assertNotNull(registered);
        assertEquals(1, allCategories.size());
        assertEquals("Professor Auxiliar", allCategories.get(0).getName().getName());
    }

    @Test
    void shouldNotRegisterDuplicateTeacherCategory() {
        // Arrange
        Name name = new Name("Assistant Professor");
        TeacherCategoryID id1 = new TeacherCategoryID(UUID.randomUUID());
        TeacherCategory category1 = new TeacherCategory(id1,name);
        TeacherCategory category2 = new TeacherCategory(id1,name);

        repository.save(category1);

        // Act
        TeacherCategory secondTry = repository.save(category2);

        // Assert
        assertNotNull(secondTry);
        assertEquals(1, repository.getTeacherCategoryList().size());
    }
}
