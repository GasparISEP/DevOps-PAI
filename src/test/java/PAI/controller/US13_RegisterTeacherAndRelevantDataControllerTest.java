package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class US13_RegisterTeacherAndRelevantDataControllerTest {

    @Test
    void shouldAlwaysCreateObjectController() {
        // Arrange
        TeacherCategoryFactory doubleTeacherCategoryFactory = mock(TeacherCategoryFactory.class);
        TeacherCategoryRepository tcr = new TeacherCategoryRepository(doubleTeacherCategoryFactory);
        DepartmentFactory factory = new DepartmentFactory();
        DepartmentRepository dpt = new DepartmentRepository(factory);
        TeacherRepository tr = new TeacherRepository();

        // Act
        US13_RegisterTeacherAndRelevantDataController controller = new US13_RegisterTeacherAndRelevantDataController(tcr, dpt, tr);
    }

    @Test
    void shouldReturnExceptionIfCategoriesListIsEmpty() throws IllegalStateException {
        // Arrange
        TeacherCategoryFactory doubleTeacherCategoryFactory = mock(TeacherCategoryFactory.class);
        TeacherCategoryRepository tcr = new TeacherCategoryRepository(doubleTeacherCategoryFactory);
        US13_RegisterTeacherAndRelevantDataController tcrControllerList = new US13_RegisterTeacherAndRelevantDataController(tcr, null, null);
        // Act + Assert
        assertThrows(IllegalStateException.class, () -> tcrControllerList.getTeacherCategoryList());
    }

    @Test
    void shouldReturnCategoryListWithRegisteredCategories() throws Exception {
        // Arrange
        TeacherCategoryFactory doubleTeacherCategoryFactory = mock(TeacherCategoryFactory.class);
        TeacherCategoryRepository tcr = new TeacherCategoryRepository(doubleTeacherCategoryFactory);
        tcr.registerTeacherCategory("Assistant Professor");
        tcr.registerTeacherCategory("Director Professor");
        US13_RegisterTeacherAndRelevantDataController controller = new US13_RegisterTeacherAndRelevantDataController(tcr, null, null);
        // Act
        List<TeacherCategory> result = controller.getTeacherCategoryList();
        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void shouldReturnExceptionIfDepartmentsListIsEmpty() throws IllegalStateException {
        DepartmentFactory factory= new DepartmentFactory();
        DepartmentRepository dptr = new DepartmentRepository(factory);
        US13_RegisterTeacherAndRelevantDataController dptrControllerList = new US13_RegisterTeacherAndRelevantDataController(null, dptr, null);
        // Act + Assert
        assertThrows(IllegalStateException.class, () -> dptrControllerList.getDepartmentsList());
    }

    @Test
    void shouldReturnDepartmentListWithRegisteredDepartments() throws Exception {
        // Arrange
        DepartmentFactory factory= new DepartmentFactory();
        DepartmentRepository dptr = new DepartmentRepository(factory);
        dptr.registerDepartment("CSE", "Computer Science");
        dptr.registerDepartment("CIV", "Civil Engineering");
        US13_RegisterTeacherAndRelevantDataController controller = new US13_RegisterTeacherAndRelevantDataController(null, dptr, null);
        // Act
        List<Department> result = controller.getDepartmentsList();
        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void shouldRegisterTeacher() throws Exception {
        // Arrange
        TeacherCategory tc = new TeacherCategory("Assistant Professor");
        Department dpt = new Department("CSE", "Computer Science");
        TeacherRepository teacherRepository = new TeacherRepository();
        US13_RegisterTeacherAndRelevantDataController controller = new US13_RegisterTeacherAndRelevantDataController(
                null, null, teacherRepository
        );

        // Act
        boolean result = controller.registerTeacher("JSD", "John Smith Doe", "jsd@isep.ipp.pt",
                "123456789", "B146", "12-05-2019, PhD, ISEP",
                "123 Main St", "12345", "Cityville", "Countryland",
                "23-01-2025", tc, 100, dpt);

        // Assert
        assertTrue(result);
    }

}