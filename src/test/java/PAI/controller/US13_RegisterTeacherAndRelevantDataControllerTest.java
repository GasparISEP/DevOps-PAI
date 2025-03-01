package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US13_RegisterTeacherAndRelevantDataControllerTest {

    @Test
    void shouldAlwaysCreateObjectController() {
        // Arrange
        TeacherCategoryRepository tcrDouble = mock(TeacherCategoryRepository.class);
        DepartmentRepository dptDouble = mock(DepartmentRepository.class);
        TeacherRepository trDouble = mock(TeacherRepository.class);

        // Act
        US13_RegisterTeacherAndRelevantDataController controller = new US13_RegisterTeacherAndRelevantDataController(tcrDouble, dptDouble, trDouble);
    }

    @Test
    void shouldReturnExceptionIfCategoriesListIsEmpty() throws IllegalStateException {
        // Arrange
        TeacherCategoryRepository tcrDouble = mock(TeacherCategoryRepository.class);
        DepartmentRepository dptrDouble = mock(DepartmentRepository.class);
        TeacherRepository trDouble = mock(TeacherRepository.class);


        US13_RegisterTeacherAndRelevantDataController tcrControllerList = new US13_RegisterTeacherAndRelevantDataController(tcrDouble, dptrDouble, trDouble);

        when(tcrDouble.getTeacherCategoryList()).thenThrow(new IllegalStateException("Teacher Category list is empty."));

        // Act + Assert
        assertThrows(IllegalStateException.class, () -> tcrControllerList.getTeacherCategoryList());
    }


    @Test
    void shouldReturnCategoryListWithRegisteredCategories() {
        // Arrange
        DepartmentRepository dptrDouble = mock(DepartmentRepository.class);
        TeacherRepository trDouble = mock(TeacherRepository.class);
        TeacherCategoryRepository tcrDouble = mock(TeacherCategoryRepository.class);
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        List<TeacherCategory> tcListDouble = List.of(tcDouble);
        US13_RegisterTeacherAndRelevantDataController controller = new US13_RegisterTeacherAndRelevantDataController(tcrDouble, dptrDouble, trDouble);

        when(tcrDouble.getTeacherCategoryList()).thenReturn(tcListDouble);

        // Act
        List<TeacherCategory> result = controller.getTeacherCategoryList();

        // Assert
        assertEquals(result, tcListDouble);
    }

    @Test
    void shouldReturnExceptionIfDepartmentsListIsEmpty() throws IllegalStateException {
        TeacherCategoryRepository tcrDouble = mock(TeacherCategoryRepository.class);
        DepartmentRepository dptrDouble = mock(DepartmentRepository.class);
        TeacherRepository trDouble = mock(TeacherRepository.class);
        US13_RegisterTeacherAndRelevantDataController dptrControllerList = new US13_RegisterTeacherAndRelevantDataController(tcrDouble, dptrDouble, trDouble);

        when(dptrDouble.getDepartmentList()).thenThrow(new IllegalStateException("Department list is empty."));

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
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        Department dptDouble = mock(Department.class);
        TeacherCategoryRepository tcrDouble = mock(TeacherCategoryRepository.class);
        DepartmentRepository dptrDouble = mock(DepartmentRepository.class);
        TeacherRepository trDouble = mock(TeacherRepository.class);
        TeacherCareerProgressionFactory TCPfactoryDouble = mock(TeacherCareerProgressionFactory.class);

        when(trDouble.registerTeacher("JSD", "John Smith Doe", "jsd@isep.ipp.pt",
                "123456789", "B146", "12-05-2019, PhD, ISEP",
                "123 Main St", "12345", "Cityville", "Countryland",
                "23-01-2025", tcDouble, 100, dptDouble, TCPfactoryDouble)).thenReturn(true);

        US13_RegisterTeacherAndRelevantDataController controller = new US13_RegisterTeacherAndRelevantDataController(
                tcrDouble, dptrDouble, trDouble);

        // Act
        boolean result = controller.registerTeacher("JSD", "John Smith Doe", "jsd@isep.ipp.pt",
                "123456789", "B146", "12-05-2019, PhD, ISEP",
                "123 Main St", "12345", "Cityville", "Countryland",
                "23-01-2025", tcDouble, 100, dptDouble, TCPfactoryDouble);

        // Assert
        assertTrue(result);
    }

}