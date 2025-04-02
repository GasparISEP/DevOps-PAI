package PAI.controller;
import PAI.VOs.Date;
import PAI.VOs.TeacherCategoryID;
import PAI.VOs.TeacherID;
import PAI.VOs.WorkingPercentage;
import PAI.domain.*;
import PAI.factory.*;
import PAI.repository.DepartmentRepository;
import PAI.repository.TeacherRepository;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/*

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
        Date dateDouble = mock(Date.class);
        TeacherCategoryID tcIDDouble = mock(TeacherCategoryID.class);
        WorkingPercentage wpDouble = mock(WorkingPercentage.class);
        TeacherID teacherIDDouble = mock(TeacherID.class);

        AddressFactoryImpl addressFactoryDouble = mock(AddressFactoryImpl.class);
        Department dpt1Double = mock(Department.class);

        US04_IWantToRegisterATeacherInTheSystemController controller = new US04_IWantToRegisterATeacherInTheSystemController(
                teacherRepositoryDouble, teacherCategoryRepositoryDouble, departmentRepositoryDouble);

        when(tc1Double.getName()).thenReturn("Professor");
        when(teacherCategoryRepositoryDouble.getTeacherCategoryByName("Professor")).thenReturn(Optional.of(tc1Double));
        when(departmentRepositoryDouble.departmentExists(dpt1Double)).thenReturn(true);

        //act
        boolean result = controller.registerATeacherInTheSystem("ABC", "Jo", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", addressFactoryDouble, dateDouble, tcIDDouble, wpDouble, teacherIDDouble, dpt1Double);
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
        Date dateDouble = mock(Date.class);
        TeacherCategoryID tcIDDouble = mock(TeacherCategoryID.class);
        WorkingPercentage wpDouble = mock(WorkingPercentage.class);
        TeacherID teacherIDDouble = mock(TeacherID.class);

        US04_IWantToRegisterATeacherInTheSystemController controller = new US04_IWantToRegisterATeacherInTheSystemController(
                teacherRepositoryDouble, teacherCategoryRepositoryDouble, departmentRepositoryDouble);

        when(tc1Double.getName()).thenReturn("Professor");
        when(teacherCategoryRepositoryDouble.getTeacherCategoryByName("Professor")).thenReturn(Optional.of(tc1Double));
        when(departmentRepositoryDouble.departmentExists(dpt1Double)).thenReturn(false);

        //act
        boolean result = controller.registerATeacherInTheSystem("ABC", "Jo", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", addressFactoryDouble, dateDouble, tcIDDouble, wpDouble, teacherIDDouble, dpt1Double);
        //assert
        assertFalse(result);
    }

//    @Test
//    void shouldReturnFalseIfInvalidTeacherCategory() {
//        //arrange
//        TeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
//        TeacherCategoryRepository teacherCategoryRepositoryDouble = mock(TeacherCategoryRepository.class);
//        DepartmentRepository departmentRepositoryDouble = mock(DepartmentRepository.class);
//        TeacherCategory tc1Double = mock(TeacherCategory.class);
//        AddressFactoryImpl addressFactoryDouble = mock(AddressFactoryImpl.class);
//        Department dpt1Double = mock(Department.class);
//        Date dateDouble = mock(Date.class);
//        TeacherCategoryID tcIDDouble = mock(TeacherCategoryID.class);
//        WorkingPercentage wpDouble = mock(WorkingPercentage.class);
//        TeacherID teacherIDDouble = mock(TeacherID.class);
//
//        US04_IWantToRegisterATeacherInTheSystemController controller = new US04_IWantToRegisterATeacherInTheSystemController(
//                teacherRepositoryDouble, teacherCategoryRepositoryDouble, departmentRepositoryDouble);
//
//        when(tc1Double.getName()).thenReturn("Professor");
//        when(teacherCategoryRepositoryDouble.getTeacherCategoryByName("Professor")).thenReturn(Optional.empty());
//        when(departmentRepositoryDouble.departmentExists(dpt1Double)).thenReturn(true);
//
//        //act
//        boolean result = controller.registerATeacherInTheSystem("ABC", "Jo", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", addressFactoryDouble, dateDouble, tcIDDouble, wpDouble, teacherIDDouble, dpt1Double);
//        //assert
//        assertFalse(result);
//    }
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

        Date dateDouble = mock(Date.class);
        TeacherCategoryID tcIDDouble = mock(TeacherCategoryID.class);
        WorkingPercentage wpDouble = mock(WorkingPercentage.class);
        TeacherID teacherIDDouble = mock(TeacherID.class);
        Department department = createDepartment();
        IAddressFactory addressFactory = new AddressFactoryImpl();
        TeacherCategory teacherCategory = createTeacherCategory();
        TeacherRepository teacherRepository = createTeacherRepo();
        DepartmentRepository departmentRepository = createDepartmentRepo();
        TeacherCategoryRepository teacherCategoryRepository = createTeacherCategoryRepo();
        US04_IWantToRegisterATeacherInTheSystemController controller = new US04_IWantToRegisterATeacherInTheSystemController(teacherRepository,teacherCategoryRepository,departmentRepository);
        //Act
        boolean result = controller.registerATeacherInTheSystem("JJJ","J Jonah Jameson","jjj@isep.ipp.pt",
                "123123123","+351 912 345 678","Doctorate in Computer Science in 1987,Isep","Rua do Homem Aranha",
                "4430-123","Porto","Portugal",addressFactory,dateDouble, tcIDDouble, wpDouble, teacherIDDouble,department);
        //Assert
        assertTrue(result);
    }
    @Test
    void shouldReturnFalseIfInvalidDepartment_integrationTest() throws Exception {

        Date dateDouble = mock(Date.class);
        TeacherCategoryID tcIDDouble = mock(TeacherCategoryID.class);
        WorkingPercentage wpDouble = mock(WorkingPercentage.class);
        TeacherID teacherIDDouble = mock(TeacherID.class);
        Department department1 = createDepartment1();
        IAddressFactory addressFactory = new AddressFactoryImpl();
        TeacherCategory teacherCategory = createTeacherCategory();
        TeacherRepository teacherRepository = createTeacherRepo();
        DepartmentRepository departmentRepository = createDepartmentRepo();
        TeacherCategoryRepository teacherCategoryRepository = createTeacherCategoryRepo();
        US04_IWantToRegisterATeacherInTheSystemController controller =
                new US04_IWantToRegisterATeacherInTheSystemController(teacherRepository,teacherCategoryRepository,departmentRepository);
        //Act
        boolean result = controller.registerATeacherInTheSystem("JJJ","J Jonah Jameson",
                "jjj@isep.ipp.pt","123123123","B109","Doctorate in Computer Science in 1987,Isep",
                "Rua do Homem Aranha","4430-123","Porto","Portugal",addressFactory,dateDouble, tcIDDouble, wpDouble, teacherIDDouble,department1);
        //Assert
        assertFalse(result);
    }

//    @Test
//    void shouldReturnFalseIfInvalidTeacherCategory_integrationTest() throws Exception {
//        Date dateDouble = mock(Date.class);
//        TeacherCategoryID tcIDDouble = mock(TeacherCategoryID.class);
//        WorkingPercentage wpDouble = mock(WorkingPercentage.class);
//        TeacherID teacherIDDouble = mock(TeacherID.class);
//        Department department = createDepartment();
//        IAddressFactory addressFactory = new AddressFactoryImpl();
//        TeacherCategory teacherCategory1 = createTeacherCategory1();
//        TeacherRepository teacherRepository = createTeacherRepo();
//        DepartmentRepository departmentRepository = createDepartmentRepo();
//        TeacherCategoryRepository teacherCategoryRepository = createTeacherCategoryRepo();
//        US04_IWantToRegisterATeacherInTheSystemController controller =
//                new US04_IWantToRegisterATeacherInTheSystemController(teacherRepository,teacherCategoryRepository,departmentRepository);
//        //Act
//        boolean result = controller.registerATeacherInTheSystem("JJJ","J Jonah Jameson",
//                "jjj@isep.ipp.pt","123123123","B109","Doctorate in Computer Science in 1987,Isep",
//                "Rua do Homem Aranha","4430-123","Porto","Portugal",addressFactory,dateDouble, tcIDDouble, wpDouble, teacherIDDouble,department);
//        //Assert
//        assertFalse(result);
//    }


    //Methods
    private TeacherRepository createTeacherRepo() {
        TeacherCareerProgressionFactoryImpl teacherCareerProgressionFactoryImpl = new TeacherCareerProgressionFactoryImpl();
        TeacherCareerProgressionListFactoryImpl tcpLFactoryDouble = new TeacherCareerProgressionListFactoryImpl();
        ITeacherFactory teacherFactory = new TeacherFactoryImpl(teacherCareerProgressionFactoryImpl,tcpLFactoryDouble);
        TeacherListFactoryImpl teacherListFactoryImpl = new TeacherListFactoryImpl();
        return new TeacherRepository(teacherFactory, teacherListFactoryImpl);
    }
    private TeacherCategoryRepository createTeacherCategoryRepo() throws Exception {
        TeacherCategoryFactoryImpl teacherCategoryFactory = new TeacherCategoryFactoryImpl();
        teacherCategoryFactory.createTeacherCategory("Professor");
        TeacherCategoryListFactoryImpl
                teacherCategoryRepositoryListFactory = new TeacherCategoryListFactoryImpl();

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
        TeacherCategoryFactoryImpl teacherCategoryFactory = new TeacherCategoryFactoryImpl();
        return teacherCategoryFactory.createTeacherCategory("Professor");
    }
    private TeacherCategory createTeacherCategory1() throws Exception {
        TeacherCategoryFactoryImpl teacherCategoryFactory = new TeacherCategoryFactoryImpl();
        return teacherCategoryFactory.createTeacherCategory("Assistant Professor");
    }
    private Department createDepartment() throws Exception {
        return new Department("DCE","Department of Computer Science");
    }
    private Department createDepartment1() throws Exception {
        return new Department("DME","Department of Mechanical Engineering");
    }
}
*/
