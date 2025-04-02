package PAI.controller;
import PAI.VOs.*;
import PAI.VOs.Location;
import PAI.domain.*;
import PAI.factory.*;
import PAI.repository.DepartmentRepositoryImpl;
import PAI.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class US04_IWantToRegisterATeacherInTheSystemControllerTest {
    //arrange
    private TeacherRepository _iTeacherRepoDouble;
    private DepartmentRepositoryImpl _departmentRepoDouble;
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
        _iTeacherRepoDouble = mock(TeacherRepository.class);
        _departmentRepoDouble = mock(DepartmentRepositoryImpl.class);
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
                _iTeacherRepoDouble, _departmentRepoDouble
        );
    }

    @Test
    void shouldReturnExceptionIfIteacherRepositoryIsNull() {
        //arrange
        //act+ assert
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US04_IWantToRegisterATeacherInTheSystemController(
                    null, _departmentRepoDouble);
        });

        //assert
        assertEquals("TeacherRepository is null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfDepartmentRepositoryIsNull() {
        //arrange
        //act + act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US04_IWantToRegisterATeacherInTheSystemController(
                    _iTeacherRepoDouble, null);
        });

        //assert
        assertEquals("DepartmentRepository is null.", exception.getMessage());
    }

    @Test
    void shouldReturnTrueIfTeacherIsRegisteredWithSuccess() {
        //arrange

        US04_IWantToRegisterATeacherInTheSystemController controller = new US04_IWantToRegisterATeacherInTheSystemController(
                _iTeacherRepoDouble, _departmentRepoDouble);


        when(_departmentRepoDouble.departmentExists(_departmentIDDouble)).thenReturn(true);

        //act
        boolean result = controller.registerATeacherInTheSystem(
                _teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble, _phoneNumberDouble, _academicBackgroundDouble, _streetDouble, _postalCodeDouble, _locationDouble, _countryDouble, _departmentIDDouble
        );
        //assert
        assertTrue(result);
    }


    @Test
    void shouldReturnFalseIfInvalidDepartment() {
        //arrange

        US04_IWantToRegisterATeacherInTheSystemController controller = new US04_IWantToRegisterATeacherInTheSystemController(
                _iTeacherRepoDouble, _departmentRepoDouble);


        when(_departmentRepoDouble.departmentExists(_departmentIDDouble)).thenReturn(false);

        //act
        boolean result = controller.registerATeacherInTheSystem(
                _teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble, _phoneNumberDouble, _academicBackgroundDouble, _streetDouble, _postalCodeDouble, _locationDouble, _countryDouble, _departmentIDDouble
        );//assert
        assertFalse(result);
    }

    //Integration tests
    @Test
    void shouldReturnExceptionIfTeacherRepositoryIsNull_integrationTest() throws Exception {
        DepartmentRepositoryImpl departmentRepository = createDepartmentRepo();
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US04_IWantToRegisterATeacherInTheSystemController(
                    null, departmentRepository);
        });

        //assert
        assertEquals("TeacherRepository is null.", exception.getMessage());
    }


    @Test
    void shouldReturnExceptionIfDepartmentRepositoryIsNull_integrationTest() throws Exception {
        TeacherRepository teacherRepository = createTeacherRepo();
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US04_IWantToRegisterATeacherInTheSystemController(
                    teacherRepository, null);
        });

        //assert
        assertEquals("DepartmentRepository is null.", exception.getMessage());
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

        TeacherRepository teacherRepository = createTeacherRepo();
        DepartmentRepositoryImpl departmentRepository = createDepartmentRepo();

        US04_IWantToRegisterATeacherInTheSystemController controller = new US04_IWantToRegisterATeacherInTheSystemController(teacherRepository, departmentRepository);
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

        TeacherRepository teacherRepository = createTeacherRepo();
        DepartmentRepositoryImpl departmentRepository = createDepartmentRepo();

        US04_IWantToRegisterATeacherInTheSystemController controller = new US04_IWantToRegisterATeacherInTheSystemController(teacherRepository, departmentRepository);
        //Act
        boolean result = controller.registerATeacherInTheSystem(teacherAcronym,  name,  email,  nif,  phoneNumber,  academicBackground,street, postalCode,  location,  country,  departmentID);
        //Assert
        assertFalse(result);
    }

    //Methods
    private TeacherRepository createTeacherRepo() {
        ITeacherFactory teacherFactory = new TeacherFactoryImpl();
        TeacherListFactoryImpl teacherListFactoryImpl = new TeacherListFactoryImpl();
        return new TeacherRepository(teacherFactory, teacherListFactoryImpl);
    }
    private TeacherAcronym createTeacherAcronym() throws Exception {
        return new TeacherAcronym("ABC");
    }
    private TeacherID createTeacherID() throws Exception {
        return new TeacherID(createTeacherAcronym());
    }

    private DepartmentRepositoryImpl createDepartmentRepo() throws Exception {
        IDepartmentFactory departmentFactory = new DepartmentFactoryImpl();
        IDepartmentListFactory departmentListFactory = new DepartmentListFactoryImpl();
        DepartmentRepositoryImpl departmentRepository = new DepartmentRepositoryImpl(departmentFactory, departmentListFactory);
        departmentRepository.registerDepartment(createDepartmentAcronym1(), createDepartmentName1());
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

