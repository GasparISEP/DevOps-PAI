package PAI.service;

import PAI.VOs.Name;
import PAI.repository.ITeacherCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TeacherCategoryApplicationServiceTest {

    private ITeacherCategoryRepository repository;
    private TeacherCategoryApplicationService service;

    @BeforeEach
    void setUp() {
        repository = mock(ITeacherCategoryRepository.class);
        service = new TeacherCategoryApplicationService(repository);
    }

    @Test
    void testRegisterCategorySuccess() throws Exception {
        when(repository.registerTeacherCategory(any(Name.class))).thenReturn(true);

        boolean result = service.registerCategory("Engenharia");

        assertTrue(result);
        verify(repository).registerTeacherCategory(any(Name.class));
    }

    @Test
    void testRegisterCategoryAlreadyExists() {
        when(repository.registerTeacherCategory(any(Name.class))).thenReturn(false);

        Exception exception = assertThrows(Exception.class,
                () -> service.registerCategory("Engenharia"));

        assertEquals("Category already exists or could not be registered.", exception.getMessage());
    }

    @Test
    void testNullRepositoryThrows() {
        assertThrows(IllegalArgumentException.class, () -> new TeacherCategoryApplicationService(null));
    }
}
