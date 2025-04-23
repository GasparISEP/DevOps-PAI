package PAI.controller;

import PAI.VOs.Name;
import PAI.service.TeacherCategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class US01_ConfigureTeacherCategoryControllerTest {

    private TeacherCategoryServiceImpl service;
    private US01_ConfigureTeacherCategoryController controller;

    @BeforeEach
    public void setUp() {
        service = mock(TeacherCategoryServiceImpl.class);
        controller = new US01_ConfigureTeacherCategoryController(service);
    }

    @Test
    public void testConstructorWithNullArgument() {
        assertThrows(IllegalArgumentException.class, () -> new US01_ConfigureTeacherCategoryController(null));
    }

    @Test
    public void testConfigureTeacherCategorySuccess() throws Exception {
        when(service.registerCategory("Matemática")).thenReturn(true);

        boolean result = controller.configureTeacherCategory("Matemática");

        assertTrue(result);
        verify(service).registerCategory("Matemática");
    }

    @Test
    public void testConfigureTeacherCategoryDuplicateThrows() throws Exception {
        when(service.registerCategory("Física fisica")).thenThrow(new Exception("Category already exists or could not be registered."));

        Exception ex = assertThrows(Exception.class,
                () -> controller.configureTeacherCategory("Física fisica")
        );
        assertEquals("Category already exists or could not be registered.", ex.getMessage());

        verify(service).registerCategory("Física fisica");
    }

    @Test
    void shouldRejectInvalidLowercaseName() {
        assertThrows(IllegalArgumentException.class, () -> new Name("história"));
    }
}
