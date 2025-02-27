package PAI.controller;
import PAI.domain.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US06_IWantToUpdateTheDepartmentDirectorOfADepartmentControllerTest {

    @Test
    void shouldReturnExceptionIfDepartmentRepositoryIsNull() {
        //arrange

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(null);
        });

        //assert
        assertEquals("Department Repository cannot be null!", exception.getMessage());
    }

    @Test
    void shouldReturnFalseIfDepartmentIsNull () {
        //arrange
        DepartmentRepository dr1Double = mock(DepartmentRepository.class);
        US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller = new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(dr1Double);
        Teacher t1Double = mock(Teacher.class);

        //act
        boolean result = controller.updateDepartmentDirector(null, t1Double);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfTeacherIsNull () {
        //arrange
        DepartmentRepository dr1Double = mock(DepartmentRepository.class);
        US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller = new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(dr1Double);
        Department dpt1Double = mock(Department.class);

        //act
        boolean result = controller.updateDepartmentDirector(dpt1Double, null);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfUpdateDepartmentDirector (){
        //arrange
        DepartmentRepository dr1Double = mock(DepartmentRepository.class);
        US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller = new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(dr1Double);
        Department dpt1Double = mock(Department.class);
        Teacher t1Double = mock(Teacher.class);

        when(dr1Double.updateOfDepartmentDirector(dpt1Double, t1Double)).thenReturn(true);

        //act
        boolean result = controller.updateDepartmentDirector(dpt1Double, t1Double);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfUpdateDepartmentDirectorNotSucessfull (){
        //arrange
        DepartmentRepository dr1Double = mock(DepartmentRepository.class);
        US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller = new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(dr1Double);
        Department dpt1Double = mock(Department.class);
        Teacher t1Double = mock(Teacher.class);

        when(dr1Double.updateOfDepartmentDirector(dpt1Double, t1Double)).thenReturn(false);

        //act
        boolean result = controller.updateDepartmentDirector(dpt1Double, t1Double);

        //assert
        assertFalse(result);
    }

    @Test
    void testGetAllDepartments() throws Exception {
        // Arrange
        DepartmentRepository dr1Double = mock(DepartmentRepository.class);
        US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller =
                new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(dr1Double);

        List<Department> departmentList = mock(List.class);

        when(departmentList.size()).thenReturn(2);
        when(dr1Double.getDepartmentList()).thenReturn(departmentList);

        // Act
        List<Department> departments = controller.getAllDepartments();

        // Assert
        assertEquals(2, departments.size());
    }
}