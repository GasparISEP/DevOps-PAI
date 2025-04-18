package PAI.service;

import PAI.VOs.Name;
import PAI.domain.TeacherCategory;
import PAI.factory.ITeacherCategoryFactory;
import PAI.repository.ITeacherCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TeacherCategoryApplicationServiceTest {

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
    void testRegisterCategorySuccess() throws Exception {
        // Arrange
        when(repository.existsByName(any(Name.class))).thenReturn(false);
        TeacherCategory categoryMock = mock(TeacherCategory.class);
        when(factory.createTeacherCategory(any(Name.class))).thenReturn(categoryMock);
        when(repository.save(categoryMock)).thenReturn(categoryMock);

        // Act
        boolean result = service.registerCategory("Engenharia");

        // Assert
        assertTrue(result);
        verify(repository).existsByName(any(Name.class));
        verify(factory).createTeacherCategory(any(Name.class));
        verify(repository).save(categoryMock);
    }

    @Test
    void testRegisterCategoryAlreadyExists() {
        // Arrange
        when(repository.existsByName(any(Name.class))).thenReturn(true);

        // Act + Assert
        Exception exception = assertThrows(Exception.class,
                () -> service.registerCategory("Engenharia"));

        assertEquals("Category already exists or could not be registered.", exception.getMessage());
        verify(repository).existsByName(any(Name.class));
        verify(factory, never()).createTeacherCategory(any());
        verify(repository, never()).save(any());
    }

    @Test
    void testNullDependenciesThrow() {
        assertThrows(IllegalArgumentException.class, () -> new TeacherCategoryApplicationService(null, factory));
        assertThrows(IllegalArgumentException.class, () -> new TeacherCategoryApplicationService(repository, null));
    }
}