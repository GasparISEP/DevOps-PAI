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
    void shouldNotCreateCreateObjectControllerWhenTeacherCategoryRepositoryIsNull() {
        // Arrange
        DepartmentRepository dptDouble = mock(DepartmentRepository.class);
        TeacherRepository trDouble = mock(TeacherRepository.class);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new US13_RegisterTeacherAndRelevantDataController(null, dptDouble, trDouble));
    }

    @Test
    void shouldNotCreateCreateObjectControllerWhenDepartmentRepositoryIsNull() {
        // Arrange
        TeacherCategoryRepository tcDouble = mock(TeacherCategoryRepository.class);
        TeacherRepository trDouble = mock(TeacherRepository.class);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new US13_RegisterTeacherAndRelevantDataController(tcDouble, null, trDouble));
    }

    @Test
    void shouldNotCreateCreateObjectControllerWhenTeacherRepositoryIsNull() {
        // Arrange
        TeacherCategoryRepository tcDouble = mock(TeacherCategoryRepository.class);
        DepartmentRepository dptDouble = mock(DepartmentRepository.class);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new US13_RegisterTeacherAndRelevantDataController(tcDouble, dptDouble, null));
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
        TeacherCategoryRepository tcrDouble = mock(TeacherCategoryRepository.class);
        DepartmentRepository dptrDouble = mock(DepartmentRepository.class);
        TeacherRepository trDouble = mock(TeacherRepository.class);
        US13_RegisterTeacherAndRelevantDataController controller = new US13_RegisterTeacherAndRelevantDataController(tcrDouble, dptrDouble, trDouble);
        Department dptDouble = mock(Department.class);
        List<Department> dptListDouble = List.of(dptDouble);

        when(dptrDouble.getDepartmentList()).thenReturn(dptListDouble);

        // Act
        List<Department> result = controller.getDepartmentsList();
        // Assert
        assertEquals(result, dptListDouble);
    }

    @Test
    void shouldRegisterTeacher() throws Exception {
        // Arrange
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        Department dptDouble = mock(Department.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        TeacherCategoryRepository tcrDouble = mock(TeacherCategoryRepository.class);
        DepartmentRepository dptrDouble = mock(DepartmentRepository.class);
        TeacherRepository trDouble = mock(TeacherRepository.class);
        TeacherCareerProgressionFactory TCPfactoryDouble = mock(TeacherCareerProgressionFactory.class);

        when(trDouble.registerTeacher("JSD", "John Smith Doe", "jsd@isep.ipp.pt",
                "123456789", "B146", "12-05-2019, PhD, ISEP",
                "123 Main St", "12345", "Cityville", "Countryland", addressFactoryDouble,"23-01-2025", tcDouble, 100, dptDouble, TCPfactoryDouble)).thenReturn(true);

        US13_RegisterTeacherAndRelevantDataController controller = new US13_RegisterTeacherAndRelevantDataController(
                tcrDouble, dptrDouble, trDouble);

        // Act
        boolean result = controller.registerTeacher("JSD", "John Smith Doe", "jsd@isep.ipp.pt",
                "123456789", "B146", "12-05-2019, PhD, ISEP",
                "123 Main St", "12345", "Cityville", "Countryland", addressFactoryDouble,"23-01-2025", tcDouble, 100, dptDouble, TCPfactoryDouble);

        // Assert
        assertTrue(result);
    }

}