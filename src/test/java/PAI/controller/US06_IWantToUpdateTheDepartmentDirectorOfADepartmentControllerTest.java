package PAI.controller;
import PAI.domain.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

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
    void shouldReturnFalseIfDepartmentIsNull () throws Exception {
        //arrange
        DepartmentFactory factory = new DepartmentFactory();
        DepartmentRepository dr1 = new DepartmentRepository(factory);
        US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller = new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(dr1);
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        Department dpt1 = new Department("MAT", "Mathematics");
        Teacher t1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005", tc1, 70, dpt1);

        //act
        boolean result = controller.updateDepartmentDirector(null, t1);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfTeacherIsNull () throws Exception {
        //arrange
        DepartmentFactory factory = new DepartmentFactory();
        DepartmentRepository dr1 = new DepartmentRepository(factory);
        US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller = new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(dr1);
        Department dpt1 = new Department("MAT", "Mathematics");

        //act
        boolean result = controller.updateDepartmentDirector(dpt1, null);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfUpdateDepartmentDirector () throws Exception {
        //arrange
        DepartmentFactory factory = new DepartmentFactory();
        DepartmentRepository dr1 = new DepartmentRepository(factory);
        US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller = new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(dr1);
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        Department dpt1 = new Department("MAT", "Mathematics");
        Teacher t1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005", tc1, 70, dpt1);
        dr1.registerDepartment("MAT", "Mathematics");

        //act
        boolean result = controller.updateDepartmentDirector(dpt1, t1);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTeacherDoesNotBelongToDepartment () throws Exception {
        //arrange
        DepartmentFactory factory = new DepartmentFactory();
        DepartmentRepository dr1 = new DepartmentRepository(factory);
        US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller = new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(dr1);
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        Department dpt1 = new Department("MAT", "Mathematics");
        Department dpt2 = new Department("DED", "Software Engineering");
        Teacher t1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005", tc1, 70, dpt2);
        dr1.registerDepartment("MAT", "Mathematics");

        //act
        boolean result = controller.updateDepartmentDirector(dpt1, t1);

        //assert
        assertFalse(result);
    }


    @Test
    void testGetAllDepartments() throws Exception {

        // Arrange
        DepartmentFactory factory = new DepartmentFactory();
        DepartmentRepository dr1 = new DepartmentRepository(factory);
        US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller = new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(dr1);
        dr1.registerDepartment("MAT", "Mathematics");
        dr1.registerDepartment("DED","Informatic Engineering");

        // Act
        List<Department> departments = controller.getAllDepartments();

        // Assert
        assertEquals(2, departments.size());
    }

}