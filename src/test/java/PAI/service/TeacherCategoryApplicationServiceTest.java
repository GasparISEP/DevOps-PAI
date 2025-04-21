package PAI.service;

import PAI.VOs.Name;
import PAI.domain.TeacherCategory;
import PAI.factory.ITeacherCategoryFactory;
import PAI.repository.ITeacherCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeacherCategoryApplicationServiceTest {

    private ITeacherCategoryRepository repository;
    private ITeacherCategoryFactory factory;
    private TeacherCategoryApplicationService service;

    @BeforeEach
    void setUp() {
        repository = mock(ITeacherCategoryRepository.class);
        factory = mock(ITeacherCategoryFactory.class);
        service = new TeacherCategoryApplicationService(repository, factory);
    }

    @Test
    void shouldRegisterCategorySuccessfully_whenNameIsValidAndUnique() throws Exception {
        // Arrange
        String inputName = "Catedrático";
        when(repository.existsByName(any(Name.class))).thenReturn(false);
        when(factory.createTeacherCategory(any(Name.class))).thenReturn(mock(TeacherCategory.class));

        // Act
        boolean result = service.registerCategory(inputName);

        // Assert
        assertTrue(result);
        verify(repository).existsByName(any(Name.class));
        verify(factory).createTeacherCategory(any(Name.class));
        verify(repository).save(any(TeacherCategory.class));
    }

    @Test
    void shouldThrowException_whenCategoryNameAlreadyExists() {
        // Arrange
        String inputName = "Auxiliar";
        when(repository.existsByName(any(Name.class))).thenReturn(true);

        // Act & Assert
        Exception ex = assertThrows(Exception.class, () -> service.registerCategory(inputName));
        assertEquals("Category already exists or could not be registered.", ex.getMessage());

        verify(repository).existsByName(any(Name.class));
        verify(factory, never()).createTeacherCategory(any());
        verify(repository, never()).save(any());
    }

    @Test
    void shouldThrowException_whenNameIsInvalid() {
        // Arrange
        String invalidName = "a";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> service.registerCategory(invalidName));
        verify(repository, never()).existsByName(any());
        verify(factory, never()).createTeacherCategory(any());
        verify(repository, never()).save(any());
    }
}
