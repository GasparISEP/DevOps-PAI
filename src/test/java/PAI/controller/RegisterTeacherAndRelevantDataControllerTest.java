package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class RegisterTeacherAndRelevantDataControllerTest {

    @Test
    void shouldAlwaysCreateObjectController() {
        // Arrange
        TeacherCategoryRepository tcr = new TeacherCategoryRepository();
        DepartmentRepository dpt = new DepartmentRepository();
        TeacherRepository tr = new TeacherRepository();

        // Act
        RegisterTeacherAndRelevantDataController controller = new RegisterTeacherAndRelevantDataController(tcr, dpt, tr);
    }

    @Test
    void shouldNotReturnCategoryListIfNull() {
        // Arrange
        TeacherCategoryRepository tcr = new TeacherCategoryRepository();
        RegisterTeacherAndRelevantDataController tcrControllerList = new RegisterTeacherAndRelevantDataController(tcr, null, null);
        // Act
        List<TeacherCategory> result = tcrControllerList.getTeacherCategoriesList();
        // Assert
        assertNotNull(result);
    }

    @Test
    void shouldReturnCategoryListEvenIfEmpty() {
        // Arrange
        TeacherCategoryRepository tcr = new TeacherCategoryRepository();
        RegisterTeacherAndRelevantDataController tcrControllerList = new RegisterTeacherAndRelevantDataController(tcr, null, null);
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
        RegisterTeacherAndRelevantDataController controller = new RegisterTeacherAndRelevantDataController(tcr, null, null);
        // Act
        List<TeacherCategory> result = controller.getTeacherCategoriesList();
        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void shouldNotReturnDepartmentListIfNull() {
        // Arrange
        DepartmentRepository dptr = new DepartmentRepository();
        RegisterTeacherAndRelevantDataController dptrControllerList = new RegisterTeacherAndRelevantDataController(null, dptr, null);
        // Act
        List<Department> result = dptrControllerList.getDepartmentsList();
        // Assert
        assertNotNull(result);
    }

    @Test
    void shouldReturnDepartmentListEvenIfEmpty() {
        DepartmentRepository dptr = new DepartmentRepository();
        RegisterTeacherAndRelevantDataController dptrControllerList = new RegisterTeacherAndRelevantDataController(null, dptr, null);
        // Act
        List<Department> result = dptrControllerList.getDepartmentsList();
        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnDepartmentListWithRegisteredDepartments() throws Exception {
        // Arrange
        DepartmentRepository dptr = new DepartmentRepository();
        dptr.registerDepartment("CSE", "Computer Science");
        dptr.registerDepartment("CIV", "Civil Engineering");
        RegisterTeacherAndRelevantDataController controller = new RegisterTeacherAndRelevantDataController(null, dptr, null);
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
        RegisterTeacherAndRelevantDataController controller = new RegisterTeacherAndRelevantDataController(
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