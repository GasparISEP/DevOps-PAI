package PAI.factory;

import PAI.domain.TeacherCategory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TeacherCategoryRepositoryListFactoryTest {

    @Test
    void shouldReturnEmptyTeacherCategoryList() {
        // Arrange
        TeacherCategoryRepositoryListFactoryImpl factory = new TeacherCategoryRepositoryListFactoryImpl();

        // Act
        List<TeacherCategory> categoryList = factory.getTeacherCategoryList();

        // Assert
        assertNotNull(categoryList);
        assertTrue(categoryList.isEmpty());
    }
//
//    @Test
//    void shouldReturnEmptyTeacherCategoryListWithMockedConstruction() {
//        try (MockedConstruction<ArrayList> mockedConstruction = mockConstruction(ArrayList.class)) {
//            // Arrange
//            TeacherCategoryRepositoryListFactoryImpl factory = new TeacherCategoryRepositoryListFactoryImpl();
//
//            // Act
//            List<TeacherCategory> categoryList = factory.getTeacherCategoryList();
//
//            // Assert
//            List<ArrayList> createdInstances = mockedConstruction.constructed();
//            assertEquals(1, createdInstances.size()); // Ensure only one ArrayList was created
//            assertSame(createdInstances.get(0), categoryList); // Ensure it's the same list instance
//            assertTrue(categoryList.isEmpty()); // Ensure the list is empty
//        }
//    }
}