package PAI.controller;
import PAI.domain.*;
import PAI.factory.*;
import PAI.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

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
    void testGetAllDepartments() {
        // Arrange
        DepartmentRepository dr1Double = mock(DepartmentRepository.class);
        US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller =
                new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(dr1Double);

        Department department1 = mock(Department.class);
        Department department2 = mock(Department.class);

        Set<Department> departmentSet = new HashSet<>();
        departmentSet.add(department1);
        departmentSet.add(department2);

        when(dr1Double.getDepartments()).thenReturn(departmentSet);

        // Act
        Set<Department> departments = controller.getAllDepartments();

        // Assert
        assertEquals(2, departments.size());
    }

    //Integration tests

    @Test
    void shouldReturnFalseIfDepartmentIsNull_IntegrationTest () throws Exception {
        //arrange
        DepartmentFactoryImpl factory = new DepartmentFactoryImpl();
        DepartmentListFactoryImpl listFactory = new DepartmentListFactoryImpl();
        DepartmentRepository dr1 = new DepartmentRepository(factory, listFactory);
        US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller = new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(dr1);

        TeacherCategory tc = new TeacherCategory("Assistant Teacher");
        AddressFactory addressFactory = new AddressFactoryImpl();
        Department dpt1 = new Department ("DEI", "Department1");
        TeacherCareerProgressionFactoryImpl tcpFactory = new TeacherCareerProgressionFactoryImpl();
        TeacherCareerProgressionListFactoryImpl tcplF = new TeacherCareerProgressionListFactoryImpl();
        Teacher t1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua das Flores","4444-098","Porto","Portugal", addressFactory,"15-04-2005", tc, 70, dpt1, tcpFactory, tcplF);

        //act
        boolean result = controller.updateDepartmentDirector(null, t1);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfTeacherIsNull_IntegrationTest () throws Exception {
        //arrange
        DepartmentFactoryImpl factory = new DepartmentFactoryImpl();
        DepartmentListFactoryImpl listFactory = new DepartmentListFactoryImpl();
        DepartmentRepository dr1 = new DepartmentRepository(factory, listFactory);
        US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller = new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(dr1);

        Department dpt1 = new Department("MAT", "Mathematics");

        //act
        boolean result = controller.updateDepartmentDirector(dpt1, null);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfUpdateDepartmentDirector_IntegrationTest () throws Exception {
        //arrange
        DepartmentFactoryImpl factory = new DepartmentFactoryImpl();
        DepartmentListFactoryImpl listFactory = new DepartmentListFactoryImpl();
        DepartmentRepository dr1 = new DepartmentRepository(factory, listFactory);
        US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller = new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(dr1);

        TeacherCategory tc = new TeacherCategory("Assistant Teacher");
        AddressFactory addressFactory = new AddressFactoryImpl();
        Department dpt1 = new Department ("DEI", "Department1");
        TeacherCareerProgressionFactoryImpl tcpFactory = new TeacherCareerProgressionFactoryImpl();
        TeacherCareerProgressionListFactoryImpl tcplF = new TeacherCareerProgressionListFactoryImpl();
        Teacher t1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua das Flores","4444-098","Porto","Portugal", addressFactory,"15-04-2005", tc, 70, dpt1, tcpFactory, tcplF);

        dr1.registerDepartment("MAT", "Mathematics");

        //act
        boolean result = controller.updateDepartmentDirector(dpt1, t1);

        //assert
        assertTrue(result);
    }

    @Test
    void testGetAllDepartments_IntegrationTest() throws Exception {
        // Arrange
        DepartmentFactoryImpl factory = new DepartmentFactoryImpl();
        DepartmentListFactoryImpl listFactory = new DepartmentListFactoryImpl();
        DepartmentRepository dr1 = new DepartmentRepository(factory, listFactory);
        US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller = new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(dr1);

        dr1.registerDepartment("MAT", "Mathematics");
        dr1.registerDepartment("DED","Informatic Engineering");

        // Act
        Set<Department> departments = controller.getAllDepartments();

        // Assert
        assertEquals(2, departments.size());
    }
}