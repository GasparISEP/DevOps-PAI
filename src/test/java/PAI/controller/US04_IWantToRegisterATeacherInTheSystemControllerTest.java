package PAI.controller;
import PAI.domain.*;
import PAI.factory.*;
import PAI.repository.DepartmentRepository;
import PAI.repository.TeacherCategoryRepository;
import PAI.repository.TeacherRepository;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class US04_IWantToRegisterATeacherInTheSystemControllerTest {

    @Test
    void shouldReturnExceptionIfTeacherRepositoryIsNull() {
        //arrange
        TeacherCategoryRepository teacherCategoryRepositoryDouble = mock(TeacherCategoryRepository.class);
        DepartmentRepository departmentRepositoryDouble = mock(DepartmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US04_IWantToRegisterATeacherInTheSystemController(
                    null, teacherCategoryRepositoryDouble, departmentRepositoryDouble);
        });

        //assert
        assertEquals("TeacherRepository is null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfTeacherCategoryRepositoryIsNull() {
        //arrange
        TeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
        DepartmentRepository departmentRepositoryDouble = mock(DepartmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US04_IWantToRegisterATeacherInTheSystemController(
                    teacherRepositoryDouble, null, departmentRepositoryDouble);
        });

        //assert
        assertEquals("TeacherCategoryRepository is null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfDepartmentRepositoryIsNull() {
        //arrange
        TeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
        TeacherCategoryRepository teacherCategoryRepositoryDouble = mock(TeacherCategoryRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US04_IWantToRegisterATeacherInTheSystemController(
                    teacherRepositoryDouble, teacherCategoryRepositoryDouble, null);
        });

        //assert
        assertEquals("DepartmentRepository is null.", exception.getMessage());
    }

    @Test
    void shouldReturnTrueIfTeacherIsRegisteredWithSuccess() {
        //arrange
        TeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
        TeacherCategoryRepository teacherCategoryRepositoryDouble = mock(TeacherCategoryRepository.class);
        DepartmentRepository departmentRepositoryDouble = mock(DepartmentRepository.class);
        TeacherCategory tc1Double = mock(TeacherCategory.class);
        AddressFactoryImpl addressFactoryDouble = mock(AddressFactoryImpl.class);
        Department dpt1Double = mock(Department.class);
        TeacherCareerProgressionFactory TCPfactoryDouble = mock(TeacherCareerProgressionFactory.class);
        TeacherCareerProgressionListFactory tcpLFactoryDouble = mock(TeacherCareerProgressionListFactory.class);


        US04_IWantToRegisterATeacherInTheSystemController controller = new US04_IWantToRegisterATeacherInTheSystemController(
                teacherRepositoryDouble, teacherCategoryRepositoryDouble, departmentRepositoryDouble);

        when(tc1Double.getName()).thenReturn("Professor");
        when(teacherCategoryRepositoryDouble.getTeacherCategoryByName("Professor")).thenReturn(Optional.of(tc1Double));
        when(departmentRepositoryDouble.departmentExists(dpt1Double)).thenReturn(true);

        //act
        boolean result = controller.registerATeacherInTheSystem("ABC", "Jo", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", addressFactoryDouble, "15-04-2005", tc1Double, 70, dpt1Double, TCPfactoryDouble, tcpLFactoryDouble);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfInvalidDepartment() {
        //arrange
        TeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
        TeacherCategoryRepository teacherCategoryRepositoryDouble = mock(TeacherCategoryRepository.class);
        DepartmentRepository departmentRepositoryDouble = mock(DepartmentRepository.class);
        TeacherCategory tc1Double = mock(TeacherCategory.class);
        AddressFactoryImpl addressFactoryDouble = mock(AddressFactoryImpl.class);
        Department dpt1Double = mock(Department.class);
        TeacherCareerProgressionFactory TCPfactoryDouble = mock(TeacherCareerProgressionFactory.class);
        TeacherCareerProgressionListFactory tcpLFactoryDouble = mock(TeacherCareerProgressionListFactory.class);


        US04_IWantToRegisterATeacherInTheSystemController controller = new US04_IWantToRegisterATeacherInTheSystemController(
                teacherRepositoryDouble, teacherCategoryRepositoryDouble, departmentRepositoryDouble);

        when(tc1Double.getName()).thenReturn("Professor");
        when(teacherCategoryRepositoryDouble.getTeacherCategoryByName("Professor")).thenReturn(Optional.of(tc1Double));
        when(departmentRepositoryDouble.departmentExists(dpt1Double)).thenReturn(false);

        //act
        boolean result = controller.registerATeacherInTheSystem("ABC", "Jo", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", addressFactoryDouble, "15-04-2005", tc1Double, 70, dpt1Double, TCPfactoryDouble, tcpLFactoryDouble);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfInvalidTeacherCategory() {
        //arrange
        TeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
        TeacherCategoryRepository teacherCategoryRepositoryDouble = mock(TeacherCategoryRepository.class);
        DepartmentRepository departmentRepositoryDouble = mock(DepartmentRepository.class);
        TeacherCategory tc1Double = mock(TeacherCategory.class);
        AddressFactoryImpl addressFactoryDouble = mock(AddressFactoryImpl.class);
        Department dpt1Double = mock(Department.class);
        TeacherCareerProgressionFactory TCPfactoryDouble = mock(TeacherCareerProgressionFactory.class);
        TeacherCareerProgressionListFactory tcpLFactoryDouble = mock(TeacherCareerProgressionListFactory.class);

        US04_IWantToRegisterATeacherInTheSystemController controller = new US04_IWantToRegisterATeacherInTheSystemController(
                teacherRepositoryDouble, teacherCategoryRepositoryDouble, departmentRepositoryDouble);

        when(tc1Double.getName()).thenReturn("Professor");
        when(teacherCategoryRepositoryDouble.getTeacherCategoryByName("Professor")).thenReturn(Optional.empty());
        when(departmentRepositoryDouble.departmentExists(dpt1Double)).thenReturn(true);

        //act
        boolean result = controller.registerATeacherInTheSystem("ABC", "Jo", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", addressFactoryDouble, "15-04-2005", tc1Double, 70, dpt1Double, TCPfactoryDouble, tcpLFactoryDouble);
        //assert
        assertFalse(result);
    }
    //Integration tests
    @Test
    void shouldReturnExceptionIfTeacherRepositoryIsNull_integrationTest() throws Exception {
        TeacherCategoryRepository teacherCategoryRepository = createTeacherCategoryRepo();
        DepartmentRepository departmentRepository = createDepartmentRepo();
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US04_IWantToRegisterATeacherInTheSystemController(
                    null, teacherCategoryRepository, departmentRepository);
        });

        //assert
        assertEquals("TeacherRepository is null.", exception.getMessage());
    }
    @Test
    void shouldReturnExceptionIfTeacherCategoryRepositoryIsNull_integrationTest() throws Exception {
        TeacherRepository teacherRepository = createTeacherRepo();
        DepartmentRepository departmentRepository = createDepartmentRepo();
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US04_IWantToRegisterATeacherInTheSystemController(
                    teacherRepository, null, departmentRepository);
        });

        //assert
        assertEquals("TeacherCategoryRepository is null.", exception.getMessage());
    }
    @Test
    void shouldReturnExceptionIfDepartmentRepositoryIsNull_integrationTest() throws Exception {
        TeacherRepository teacherRepository = createTeacherRepo();
        TeacherCategoryRepository teacherCategoryRepository = createTeacherCategoryRepo();
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US04_IWantToRegisterATeacherInTheSystemController(
                    teacherRepository, teacherCategoryRepository, null);
        });

        //assert
        assertEquals("DepartmentRepository is null.", exception.getMessage());
}
    @Test
    void shouldReturnTrueIfTeacherIsRegisteredWithSuccess_integrationTest() throws Exception {
        //Arrange
        Department department = createDepartment();
        AddressFactoryImpl addressFactory = new AddressFactoryImpl();
        TeacherCategory teacherCategory = createTeacherCategory();
        TeacherCareerProgressionFactory teacherCareerProgressionFactory = new TeacherCareerProgressionFactory();
        TeacherCareerProgressionListFactory teacherCareerProgressionListFactory = new TeacherCareerProgressionListFactory();
        TeacherRepository teacherRepository = createTeacherRepo();
        DepartmentRepository departmentRepository = createDepartmentRepo();
        TeacherCategoryRepository teacherCategoryRepository = createTeacherCategoryRepo();
        US04_IWantToRegisterATeacherInTheSystemController controller = new US04_IWantToRegisterATeacherInTheSystemController(teacherRepository,teacherCategoryRepository,departmentRepository);
        //Act
        boolean result = controller.registerATeacherInTheSystem("JJJ","J Jonah Jameson","jjj@isep.ipp.pt",
                "123123123","B109","Doctorate in Computer Science in 1987,Isep","Rua do Homem Aranha",
                "4430-123","Porto","Portugal",addressFactory,"12-01-2025",teacherCategory,
                100,department,teacherCareerProgressionFactory,teacherCareerProgressionListFactory);
        //Assert
        assertTrue(result);
    }
    @Test
    void shouldReturnFalseIfInvalidDepartment_integrationTest() throws Exception {
        Department department1 = createDepartment1();
        AddressFactoryImpl addressFactory = new AddressFactoryImpl();
        TeacherCategory teacherCategory = createTeacherCategory();
        TeacherCareerProgressionFactory teacherCareerProgressionFactory = new TeacherCareerProgressionFactory();
        TeacherCareerProgressionListFactory teacherCareerProgressionListFactory = new TeacherCareerProgressionListFactory();
        TeacherRepository teacherRepository = createTeacherRepo();
        DepartmentRepository departmentRepository = createDepartmentRepo();
        TeacherCategoryRepository teacherCategoryRepository = createTeacherCategoryRepo();
        US04_IWantToRegisterATeacherInTheSystemController controller =
                new US04_IWantToRegisterATeacherInTheSystemController(teacherRepository,teacherCategoryRepository,departmentRepository);
        //Act
        boolean result = controller.registerATeacherInTheSystem("JJJ","J Jonah Jameson",
                "jjj@isep.ipp.pt","123123123","B109","Doctorate in Computer Science in 1987,Isep",
                "Rua do Homem Aranha","4430-123","Porto","Portugal",addressFactory,"12-01-2025",
                teacherCategory,100,department1,teacherCareerProgressionFactory,teacherCareerProgressionListFactory);
        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfInvalidTeacherCategory_integrationTest() throws Exception {
        Department department = createDepartment();
        AddressFactoryImpl addressFactory = new AddressFactoryImpl();
        TeacherCategory teacherCategory1 = createTeacherCategory1();
        TeacherCareerProgressionFactory teacherCareerProgressionFactory = new TeacherCareerProgressionFactory();
        TeacherCareerProgressionListFactory teacherCareerProgressionListFactory = new TeacherCareerProgressionListFactory();
        TeacherRepository teacherRepository = createTeacherRepo();
        DepartmentRepository departmentRepository = createDepartmentRepo();
        TeacherCategoryRepository teacherCategoryRepository = createTeacherCategoryRepo();
        US04_IWantToRegisterATeacherInTheSystemController controller =
                new US04_IWantToRegisterATeacherInTheSystemController(teacherRepository,teacherCategoryRepository,departmentRepository);
        //Act
        boolean result = controller.registerATeacherInTheSystem("JJJ","J Jonah Jameson",
                "jjj@isep.ipp.pt","123123123","B109","Doctorate in Computer Science in 1987,Isep",
                "Rua do Homem Aranha","4430-123","Porto","Portugal",addressFactory,"12-01-2025",
                teacherCategory1,100,department,teacherCareerProgressionFactory,teacherCareerProgressionListFactory);
        //Assert
        assertFalse(result);
    }


    //Methods
    private TeacherRepository createTeacherRepo() {
        TeacherCareerProgressionFactory teacherCareerProgressionFactory= new TeacherCareerProgressionFactory();
        TeacherCareerProgressionListFactory tcpLFactoryDouble = new TeacherCareerProgressionListFactory();
        TeacherFactory teacherFactory = new TeacherFactoryImpl(teacherCareerProgressionFactory,tcpLFactoryDouble);
        TeacherListFactory teacherListFactory = new TeacherListFactory();
        return new TeacherRepository(teacherFactory, teacherListFactory);
    }
    private TeacherCategoryRepository createTeacherCategoryRepo() throws Exception {
        TeacherCategoryFactory teacherCategoryFactory = new TeacherCategoryFactory();
        teacherCategoryFactory.createTeacherCategory("Professor");
        TeacherCategoryRepositoryListFactory teacherCategoryRepositoryListFactory = new TeacherCategoryRepositoryListFactory();

     TeacherCategoryRepository teacherCategoryRepository= new TeacherCategoryRepository(teacherCategoryFactory,teacherCategoryRepositoryListFactory);
     teacherCategoryRepository.registerTeacherCategory("Professor");
     return teacherCategoryRepository;
    }
    private DepartmentRepository createDepartmentRepo() throws Exception {
        IDepartmentFactory departmentFactory = new DepartmentFactoryImpl();
        IDepartmentListFactory departmentListFactory = new DepartmentListFactoryImpl();
        DepartmentRepository departmentRepository =  new DepartmentRepository(departmentFactory, departmentListFactory);
        departmentRepository.registerDepartment("DCE","Department of Computer Science");
        return departmentRepository;
    }
    private TeacherCategory createTeacherCategory() throws Exception {
        TeacherCategoryFactory teacherCategoryFactory = new TeacherCategoryFactory();
        return teacherCategoryFactory.createTeacherCategory("Professor");
    }
    private TeacherCategory createTeacherCategory1() throws Exception {
        TeacherCategoryFactory teacherCategoryFactory = new TeacherCategoryFactory();
        return teacherCategoryFactory.createTeacherCategory("Assistant Professor");
    }
    private Department createDepartment() throws Exception {
        return new Department("DCE","Department of Computer Science");
    }
    private Department createDepartment1() throws Exception {
        return new Department("DME","Department of Mechanical Engineering");
    }
}