package PAI.persistence.mem.teacherCategory;

import PAI.VOs.*;
import PAI.domain.teacherCategory.TeacherCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TeacherCategoryRepositoryImplTest {

    private TeacherCategoryRepositoryImpl repository;

    @BeforeEach
    void setUp() {

        ITeacherCategoryListFactory listFactory = ArrayList::new;

        repository = new TeacherCategoryRepositoryImpl(listFactory);
    }

    // testing ofIdentity method

    @Test
    void shouldSaveAndRetrieveCategoryById() {

        // Arrange
        TeacherCategoryID id = mock(TeacherCategoryID.class);
        Name name = mock(Name.class);
        TeacherCategory category = mock(TeacherCategory.class);

        when(name.getName()).thenReturn("Assistant Professor");
        when(id.getValue()).thenReturn(UUID.randomUUID());
        when(category.getName()).thenReturn(name);
        when(category.identity()).thenReturn(id);

        repository.save(category);

        // Act
        Optional<TeacherCategory> result = repository.ofIdentity(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(category, result.get());
    }

    @Test
    void shouldNotReturnCategoryIfIdDoesNotMatchExisting() {
        // Arrange
        TeacherCategoryID doubleTeacherCategoryID = mock(TeacherCategoryID.class);
        TeacherCategoryID doubleTeacherCategoryID1 = mock(TeacherCategoryID.class);
        Name name = mock(Name.class);
        TeacherCategory category = mock(TeacherCategory.class);

        when(doubleTeacherCategoryID.getValue()).thenReturn(UUID.randomUUID());
        when(doubleTeacherCategoryID1.getValue()).thenReturn(UUID.randomUUID());

        when(category.identity()).thenReturn(doubleTeacherCategoryID);
        when(category.getName()).thenReturn(name);

        repository.save(category);

        // Act
        Optional<TeacherCategory> result = repository.ofIdentity(doubleTeacherCategoryID1);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnFalseIfIdDoesNotExist() {

        // Arrange
        TeacherCategoryID id = mock(TeacherCategoryID.class);
        when(id.getValue()).thenReturn(UUID.randomUUID());

        // Act
        Optional<TeacherCategory> result = repository.ofIdentity(id);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void shouldReturnFalseIfTheInputIsNull() {

        // Arrange

        // Act
        Optional<TeacherCategory> result = repository.ofIdentity(null);

        // Assert
        assertFalse (result.isPresent());
    }

    // testing containsOfIdentity method

    @Test
    void shouldReturnFalseIfIdNotFound() {
        // Arrange
        TeacherCategoryID doubleId = mock(TeacherCategoryID.class);
        when(doubleId.getValue()).thenReturn(UUID.randomUUID());

        // Act
        boolean exists = repository.containsOfIdentity(doubleId);

        // Assert
        assertFalse(exists);
    }

    @Test
    void shouldReturnTrueIfItExists() {
        // Arrange
        TeacherCategoryID doubleId = mock(TeacherCategoryID.class);
        when(doubleId.getValue()).thenReturn(UUID.randomUUID());

        TeacherCategory doubleTeacherCategory = mock (TeacherCategory.class);
        when (doubleTeacherCategory.identity()).thenReturn(doubleId);

        repository.save(doubleTeacherCategory);

        // Act
        boolean exists = repository.containsOfIdentity(doubleId);

        // Assert
        assertTrue(exists);
    }

    @Test
    void should_return_false_if_ID_is_null(){

        //arrange

        //act
        boolean idExists = repository.containsOfIdentity(null);

        //assert
        assertFalse(idExists);
    }

    // testing existsByName method

    @Test
    void shouldDetectExistingCategoryByName() {
        // Arrange
        TeacherCategoryID id = mock(TeacherCategoryID.class);
        Name name = mock(Name.class);
        TeacherCategory category = mock(TeacherCategory.class);

        when(name.getName()).thenReturn("Assistant Professor");
        when(id.getValue()).thenReturn(UUID.randomUUID());
        when(category.getName()).thenReturn(name);
        when(category.identity()).thenReturn(id);

        repository.save(category);

        // Act & Assert
        assertTrue(repository.existsByName(name));
    }

    @Test
    void shouldReturnFalseIfTeacherCategoryNameDoesNotExist () {
        // Arrange
        TeacherCategoryID id = mock(TeacherCategoryID.class);
        Name name = mock(Name.class);
        Name name1 = mock(Name.class);
        TeacherCategory category = mock(TeacherCategory.class);

        when(name.getName()).thenReturn("Assistant Professor");
        when(id.getValue()).thenReturn(UUID.randomUUID());
        when(category.getName()).thenReturn(name);
        when(category.identity()).thenReturn(id);

        repository.save(category);

        // Act & Assert
        assertFalse(repository.existsByName(name1));
    }

    // testing findAll method

    @Test
    void shouldReturnAllSavedCategories() {
        // Arrange
        TeacherCategoryID id = mock(TeacherCategoryID.class);
        Name name = mock(Name.class);
        TeacherCategory category = mock(TeacherCategory.class);

        when(name.getName()).thenReturn("Assistant Professor");
        when(id.getValue()).thenReturn(UUID.randomUUID());
        when(category.getName()).thenReturn(name);
        when(category.identity()).thenReturn(id);

        TeacherCategoryID id2 = mock(TeacherCategoryID.class);
        Name name2 = mock(Name.class);
        TeacherCategory category2 = mock(TeacherCategory.class);

        when(name2.getName()).thenReturn("Catedrático");
        when(id2.getValue()).thenReturn(UUID.randomUUID());
        when(category2.getName()).thenReturn(name);
        when(category2.identity()).thenReturn(id);

        repository.save(category);
        repository.save(category2);

        // Act
        Iterable<TeacherCategory> all = repository.findAll();

        // Assert
        assertNotNull(all);
        assertTrue(all.iterator().hasNext());
    }

    @Test
    void shouldReturnAnEmptyListOfTeacherCategories() {
        // Arrange

        // Act
        Iterable<TeacherCategory> all = repository.findAll();

        // Assert
        assertFalse (all.iterator().hasNext());
    }

    // testing findByName method

    @Test
    void shouldFindByNameIfExists() {
        // Arrange
        TeacherCategoryID id = mock(TeacherCategoryID.class);
        Name name = mock(Name.class);
        TeacherCategory category = mock(TeacherCategory.class);

        when(name.getName()).thenReturn("Assistant Professor");
        when(id.getValue()).thenReturn(UUID.randomUUID());
        when(category.getName()).thenReturn(name);
        when(category.identity()).thenReturn(id);

        repository.save(category);

        // Act
        Optional<TeacherCategory> found = repository.findByName(name);

        // Assert
        assertTrue(found.isPresent());
        assertEquals(name, found.get().getName());
    }

    @Test
    void shouldReturnOptionalEmptyIfNameNotFound() {

        // Arrange
        Name doubleName = mock(Name.class);
        when(doubleName.getName()).thenReturn("Assistant Professor");

        // Act
        Optional<TeacherCategory> found = repository.findByName(doubleName);

        // Assert
        assertFalse(found.isPresent());
    }

    @Test
    void shouldReturnOptionalEmptyIfInputIsNull() {

        // Arrange

        // Act
        Optional<TeacherCategory> found = repository.findByName(null);

        // Assert
        assertFalse(found.isPresent());
    }

    @Test
    void shouldNotFindIfNameDoesNotMatchExistingCategory() {
        // Arrange
        Name doubleName1 = mock(Name.class);
        Name doubleName2 = mock(Name.class);
        TeacherCategory category = mock(TeacherCategory.class);

        when(doubleName1.getName()).thenReturn("Assistant Professor");
        when(doubleName2.getName()).thenReturn("Adjust Professor");

        when(category.getName()).thenReturn(doubleName1);
        repository.save(category);

        // Act
        Optional<TeacherCategory> result = repository.findByName(doubleName2);

        // Assert
        assertFalse(result.isPresent());
    }

    // testing getTeacherCategoryIdFromName

    @Test
    void shouldReturnIdIfFoundByName() {
        // Arrange
        TeacherCategoryID id = mock(TeacherCategoryID.class);
        Name name = mock(Name.class);
        TeacherCategory category = mock(TeacherCategory.class);

        when(name.getName()).thenReturn("Assistant Professor");
        when(id.getValue()).thenReturn(UUID.randomUUID());
        when(category.getName()).thenReturn(name);
        when(category.identity()).thenReturn(id);
        when(category.identity()).thenReturn(id);
        repository.save(category);

        // Act
        Optional<TeacherCategoryID> teacherCategoryID = repository.getTeacherCategoryIDFromName(name);

        // Assert
        assertTrue(teacherCategoryID.isPresent());
        assertEquals(id, teacherCategoryID.get());
    }

    @Test
    void shouldReturnEmptyIdIfNameNotMatched() {
        // Arrange
        Name doubleName = mock(Name.class);
        when(doubleName.getName()).thenReturn("Inexistente");

        // Act
        Optional<TeacherCategoryID> id = repository.getTeacherCategoryIDFromName(doubleName);

        // Assert
        assertTrue(id.isEmpty());
    }

    @Test
    void shouldReturnEmptyIfInputIsNull() {
        // Arrange

        // Act
        Optional<TeacherCategoryID> id = repository.getTeacherCategoryIDFromName(null);

        // Assert
        assertFalse(id.isPresent());
    }

    @Test
    void shouldNotReturnIdIfNameDoesNotMatchStoredCategory() {
        // Arrange
        Name doubleName = mock(Name.class);
        Name doubleName2 = mock(Name.class);
        TeacherCategory doubleTeacherCategory = mock(TeacherCategory.class);
        TeacherCategoryID doubleTeacherCategoryId = mock(TeacherCategoryID.class);

        when(doubleName.getName()).thenReturn("Assistant Professor");
        when(doubleName2.getName()).thenReturn("Auxiliary Professor");

        when(doubleTeacherCategory.getName()).thenReturn(doubleName);
        when(doubleTeacherCategory.identity()).thenReturn(doubleTeacherCategoryId);

        repository.save(doubleTeacherCategory);

        // Act
        Optional<TeacherCategoryID> result = repository.getTeacherCategoryIDFromName(doubleName2);

        // Assert
        assertTrue(result.isEmpty());
    }


    // testing constructor method

    @Test
    void constructorShouldThrowIfListFactoryIsNull() {
        //Arrange

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new TeacherCategoryRepositoryImpl( null));
    }

    // testing save method

    @Test
    void shouldReturnTeacherCategoryIfItWasPossibleToSave() {
        //arrange
        ITeacherCategoryListFactory doubleTeacherCategoryListFactory = mock(ITeacherCategoryListFactory.class);
        TeacherCategoryRepositoryImpl repository = new TeacherCategoryRepositoryImpl(doubleTeacherCategoryListFactory);
        List<TeacherCategory> doubleTeacherCategoryList = mock(ArrayList.class);

        TeacherCategory doubleTeacherCategory = mock(TeacherCategory.class);

        when(doubleTeacherCategoryList.add(doubleTeacherCategory)).thenReturn(true);

        //act
        TeacherCategory result = repository.save(doubleTeacherCategory);

        //assert
        assertNotNull(result);
    }

    @Test
    void shouldReturnNullIfInputIsNull() {
        //arrange
        ITeacherCategoryListFactory doubleTeacherCategoryListFactory = mock(ITeacherCategoryListFactory.class);
        TeacherCategoryRepositoryImpl repository = new TeacherCategoryRepositoryImpl(doubleTeacherCategoryListFactory);

        //act
        TeacherCategory result = repository.save(null);

        //assert
        assertNull(result);
    }

    // testing getTeacherCategoryList

    @Test
    void shouldReturnAllSavedTeacherCategories() {
        // Arrange
        TeacherCategoryID id = mock(TeacherCategoryID.class);
        Name name = mock(Name.class);
        TeacherCategory category = mock(TeacherCategory.class);

        when(name.getName()).thenReturn("Assistant Professor");
        when(id.getValue()).thenReturn(UUID.randomUUID());
        when(category.getName()).thenReturn(name);
        when(category.identity()).thenReturn(id);

        TeacherCategoryID id2 = mock(TeacherCategoryID.class);
        Name name2 = mock(Name.class);
        TeacherCategory category2 = mock(TeacherCategory.class);

        when(name2.getName()).thenReturn("Catedrático");
        when(id2.getValue()).thenReturn(UUID.randomUUID());
        when(category2.getName()).thenReturn(name);
        when(category2.identity()).thenReturn(id);

        repository.save(category);
        repository.save(category2);

        // Act
        List <TeacherCategory> all = repository.getTeacherCategoryList();

        // Assert
        assertNotNull(all);
        assertTrue(all.iterator().hasNext());
    }

    @Test
    void shouldReturnAnEmptyListOfNamesOfTeacherCategories() {
        // Arrange

        // Act
        List<TeacherCategory> result = repository.getTeacherCategoryList();

        // Assert
        assertEquals (result, Collections.emptyList());
    }
}
