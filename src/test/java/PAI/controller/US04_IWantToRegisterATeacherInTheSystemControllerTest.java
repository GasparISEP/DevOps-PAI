package PAI.controller;
import PAI.VOs.*;
import PAI.domain.Department;
import PAI.factory.*;
import PAI.repository.DepartmentRepositoryImpl;
import PAI.repository.IDepartmentRepository;
import PAI.repository.ITeacherRepository;
import PAI.repository.TeacherRepositoryImpl;
import PAI.service.ITeacherService;
import PAI.service.TeacherServiceImpl;
import PAI.service.department.DepartmentServiceImpl;
import PAI.service.department.IDepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class US04_IWantToRegisterATeacherInTheSystemControllerTest {
    //arrange
    private ITeacherService _iTeacherServiceDouble;
    private DepartmentServiceImpl _departmentServiceDouble;
    private TeacherAcronym _teacherAcronymDouble;
    private Name _nameDouble;
    private Email _emailDouble;
    private NIF _nifDouble;
    private PhoneNumber _phoneNumberDouble;
    private AcademicBackground _academicBackgroundDouble;
    private Street _streetDouble;
    private PostalCode _postalCodeDouble;
    private PAI.VOs.Location _locationDouble;
    private Country _countryDouble;
    private DepartmentID _departmentIDDouble;

    @BeforeEach
    void factoryDoublesSetup() {
        _iTeacherServiceDouble = mock(ITeacherService.class);
        _departmentServiceDouble = mock(DepartmentServiceImpl.class);
    }

    void createTeacherArgumentDoubles() {
        _teacherAcronymDouble = mock(TeacherAcronym.class);
        _nameDouble = mock(Name.class);
        _emailDouble = mock(Email.class);
        _nifDouble = mock(NIF.class);
        _phoneNumberDouble = mock(PhoneNumber.class);
        _academicBackgroundDouble = mock(AcademicBackground.class);
        _streetDouble = mock(Street.class);
        _postalCodeDouble = mock(PostalCode.class);
        _locationDouble = mock(PAI.VOs.Location.class);
        _countryDouble = mock(Country.class);
        _departmentIDDouble = mock(DepartmentID.class);

    }

    @Test
    void createUS04Controller() {
        //arrange
        //act + assert
        new US04_IWantToRegisterATeacherInTheSystemController(
                _iTeacherServiceDouble, _departmentServiceDouble
        );
    }

    @Test
    void shouldReturnExceptionIfIteacherRepositoryIsNull() {
        //arrange
        //act+ assert
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US04_IWantToRegisterATeacherInTheSystemController(
                    null, _departmentServiceDouble);
        });

        //assert
        assertEquals("TeacherService is null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfDepartmentRepositoryIsNull() {
        //arrange
        //act + act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US04_IWantToRegisterATeacherInTheSystemController(
                    _iTeacherServiceDouble, null);
        });

        //assert
        assertEquals("DepartmentService is null.", exception.getMessage());
    }

    @Test
    void shouldReturnTrueIfTeacherIsRegisteredWithSuccess() throws Exception {
        //arrange

        US04_IWantToRegisterATeacherInTheSystemController controller = new US04_IWantToRegisterATeacherInTheSystemController(
                _iTeacherServiceDouble, _departmentServiceDouble);


        when(_departmentServiceDouble.containsOfIdentity(_departmentIDDouble)).thenReturn(true);

        //act
        boolean result = controller.registerATeacherInTheSystem(
                _teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble, _phoneNumberDouble, _academicBackgroundDouble, _streetDouble, _postalCodeDouble, _locationDouble, _countryDouble, _departmentIDDouble
        );
        //assert
        assertTrue(result);
    }


    @Test
    void shouldReturnFalseIfInvalidDepartment() throws Exception {
        //arrange

        US04_IWantToRegisterATeacherInTheSystemController controller = new US04_IWantToRegisterATeacherInTheSystemController(
                _iTeacherServiceDouble, _departmentServiceDouble);


        when(_departmentServiceDouble.containsOfIdentity(_departmentIDDouble)).thenReturn(false);

        //act
        boolean result = controller.registerATeacherInTheSystem(
                _teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble, _phoneNumberDouble, _academicBackgroundDouble, _streetDouble, _postalCodeDouble, _locationDouble, _countryDouble, _departmentIDDouble
        );//assert
        assertFalse(result);
    }

    //Integration tests
    @Test
    void shouldReturnExceptionIfTeacherServiceIsNull_integrationTest(){
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US04_IWantToRegisterATeacherInTheSystemController(
                    null, createDepartmentService());
        });

        //assert
        assertEquals("TeacherService is null.", exception.getMessage());
    }


    @Test
    void shouldReturnExceptionIfDepartmentServiceIsNull_integrationTest(){
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US04_IWantToRegisterATeacherInTheSystemController(
                    createTeacherService(), null);
        });

        //assert
        assertEquals("DepartmentService is null.", exception.getMessage());
    }

    @Test
    void shouldReturnTrueIfTeacherIsRegisteredWithSuccess_integrationTest() throws Exception {
        //Arrange
        TeacherAcronym teacherAcronym = createTeacherAcronym();
        DepartmentID departmentID= createDepartmentID();
        Name name = new Name("John Doe");
        Email email = new Email("john@doe.com");
        Street street = new Street("123 street");
        PostalCode postalCode = new PostalCode("12345");
        Country country = new Country("Portugal");
        PAI.VOs.Location location = new PAI.VOs.Location("Porto");
        NIF nif = new NIF("123431123",country);
        PhoneNumber phoneNumber = new PhoneNumber("+351","912123123");
        AcademicBackground  academicBackground= new AcademicBackground("Doctor");

        ITeacherService teacherService= createTeacherService();
        IDepartmentService departmentServiceDouble = createDepartmentService();
        US04_IWantToRegisterATeacherInTheSystemController controller = new US04_IWantToRegisterATeacherInTheSystemController(teacherService, departmentServiceDouble);
        //Act
        boolean result = controller.registerATeacherInTheSystem(teacherAcronym,  name,  email,  nif,  phoneNumber,  academicBackground,street, postalCode,  location,  country,  departmentID);
        //Assert
        assertTrue(result);
    }


    @Test
    void shouldReturnFalseIfInvalidDepartment_integrationTest() throws Exception {

        TeacherAcronym teacherAcronym = createTeacherAcronym();
        DepartmentID departmentID= createOtherDepartmentID();
        Name name = new Name("John Doe");
        Email email = new Email("john@doe.com");
        Street street = new Street("123 street");
        PostalCode postalCode = new PostalCode("12345");
        Country country = new Country("Portugal");
        PAI.VOs.Location location = new Location("Porto");
        NIF nif = new NIF("123431123",country);
        PhoneNumber phoneNumber = new PhoneNumber("+351","912123123");
        AcademicBackground  academicBackground= new AcademicBackground("Doctor");

        ITeacherService teacherService= createTeacherService();
        IDepartmentService departmentServiceDouble = createDepartmentService();
        US04_IWantToRegisterATeacherInTheSystemController controller = new US04_IWantToRegisterATeacherInTheSystemController(teacherService, departmentServiceDouble);
        //Act
        boolean result = controller.registerATeacherInTheSystem(teacherAcronym,  name,  email,  nif,  phoneNumber,  academicBackground,street, postalCode,  location,  country,  departmentID);
        //Assert
        assertFalse(result);
    }

    //Methods
    private TeacherRepositoryImpl createTeacherRepo() {
       ITeacherRepository iTeacherRepository = mock(ITeacherRepository.class);
        TeacherListFactoryImpl teacherListFactoryImpl = new TeacherListFactoryImpl();
        return new TeacherRepositoryImpl(teacherListFactoryImpl);
    }
    private TeacherAcronym createTeacherAcronym() throws Exception {
        return new TeacherAcronym("ABC");
    }
    private TeacherID createTeacherID() throws Exception {
        return new TeacherID(createTeacherAcronym());
    }
    private DepartmentServiceImpl createDepartmentService() throws Exception {
        IDepartmentRepository departmentRepository= createDepartmentRepo();
        IDepartmentFactory departmentFactory = new DepartmentFactoryImpl();
        return new DepartmentServiceImpl(departmentFactory,departmentRepository);
    }

    private TeacherServiceImpl createTeacherService() {
        ITeacherRepository teacherRepository= createTeacherRepo();
        ITeacherFactory teacherFactory = new TeacherFactoryImpl(teacherRepository);
        return new TeacherServiceImpl(teacherFactory,teacherRepository);
    }

    private DepartmentRepositoryImpl createDepartmentRepo() throws Exception {
        IDepartmentFactory departmentFactory = new DepartmentFactoryImpl();
        IDepartmentListFactory departmentListFactory = new DepartmentListFactoryImpl();
        DepartmentRepositoryImpl departmentRepository = new DepartmentRepositoryImpl(departmentFactory, departmentListFactory);
        departmentRepository.save(createDepartment());
        return departmentRepository;
    }
    private  DepartmentID createDepartmentID() throws Exception {
        return new DepartmentID(createDepartmentAcronym1());
    }
    private  DepartmentID createOtherDepartmentID() throws Exception {
        return new DepartmentID(createDepartmentAcronym2());
    }
    private DepartmentAcronym createDepartmentAcronym1() throws Exception {
        return new DepartmentAcronym("DCE");
    }
    private DepartmentAcronym createDepartmentAcronym2() throws Exception {
        return new DepartmentAcronym("DME");
    }
    private Name createDepartmentName1() throws Exception {
        return new Name("DCE");
    }
    private Name createDepartmentName2() throws Exception {
        return new Name("DME");
    }
    private Department createDepartment() throws Exception {
        return new Department(createDepartmentAcronym1(), createDepartmentName1());
    }

    private Department createDepartment1() throws Exception {
        return new Department(createDepartmentAcronym2(), createDepartmentName1());
    }
}

