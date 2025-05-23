package PAI.controller;

import PAI.VOs.Name;
import PAI.domain.teacherCategory.TeacherCategory;
import PAI.service.teacherCategory.TeacherCategoryServiceImpl;
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

        //Arrange
        Name doubleName = mock (Name.class);
        when(doubleName.getName()).thenReturn("Assistant");

        TeacherCategory doubleTeacherCategory = mock (TeacherCategory.class);

        when(service.configureTeacherCategory(doubleName)).thenReturn(doubleTeacherCategory);

        //Act
        boolean result = controller.configureTeacherCategory(doubleName);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testConfigureTeacherCategoryDuplicateThrows() throws Exception {

        //Arrange
        Name doubleName = mock (Name.class);
        when(doubleName.getName()).thenReturn("Assistant");
        when(service.configureTeacherCategory(doubleName)).thenThrow(new Exception("Teacher Category already exists or could not be registered."));

        //Act
        Exception ex = assertThrows(Exception.class,
                () -> controller.configureTeacherCategory(doubleName)
        );

        //Assert
        assertEquals("Teacher Category already exists or could not be registered.", ex.getMessage());

    }

    @Test
    void shouldRejectInvalidLowercaseName() {
        assertThrows(IllegalArgumentException.class, () -> new Name("história"));
    }
}
