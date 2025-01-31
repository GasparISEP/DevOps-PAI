package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class US13_RegisterTeacherAndRelevantDataControllerTest {

    @Test
    void shouldAlwaysCreateObjectController() {
        // Arrange
        TeacherCategoryRepository tcr = new TeacherCategoryRepository();
        DepartmentRepository dpt = new DepartmentRepository();
        TeacherRepository tr = new TeacherRepository();

        // Act
        US13_RegisterTeacherAndRelevantDataController controller = new US13_RegisterTeacherAndRelevantDataController(tcr, dpt, tr);
    }

    @Test
    void shouldNotReturnCategoryListIfNull() {
        // Arrange
        TeacherCategoryRepository tcr = new TeacherCategoryRepository();
        US13_RegisterTeacherAndRelevantDataController tcrControllerList = new US13_RegisterTeacherAndRelevantDataController(tcr, null, null);
        // Act
        List<TeacherCategory> result = tcrControllerList.getTeacherCategoriesList();
        // Assert
        assertNotNull(result);
    }

    @Test
    void shouldReturnCategoryListEvenIfEmpty() {
        // Arrange
        TeacherCategoryRepository tcr = new TeacherCategoryRepository();
        US13_RegisterTeacherAndRelevantDataController tcrControllerList = new US13_RegisterTeacherAndRelevantDataController(tcr, null, null);
        // Act
        List<TeacherCategory> result = tcrControllerList.getTeacherCategoriesList();
        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnCategoryListWithRegisteredCategories() throws Exception {
        // Arrange
        TeacherCategoryRepository tcr = new TeacherCategoryRepository();
        tcr.registerTeacherCategory("Assistant Professor");
        tcr.registerTeacherCategory("Director Professor");
        US13_RegisterTeacherAndRelevantDataController controller = new US13_RegisterTeacherAndRelevantDataController(tcr, null, null);
        // Act
        List<TeacherCategory> result = controller.getTeacherCategoriesList();
        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void shouldReturnDepartmentListEvenIfEmpty() throws IllegalStateException {
        DepartmentRepository dptr = new DepartmentRepository();
        US13_RegisterTeacherAndRelevantDataController dptrControllerList = new US13_RegisterTeacherAndRelevantDataController(null, dptr, null);
        // Act + Assert
        assertThrows(IllegalStateException.class, () -> dptrControllerList.getDepartmentsList());
    }

    @Test
    void shouldReturnDepartmentListWithRegisteredDepartments() throws Exception {
        // Arrange
        DepartmentRepository dptr = new DepartmentRepository();
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