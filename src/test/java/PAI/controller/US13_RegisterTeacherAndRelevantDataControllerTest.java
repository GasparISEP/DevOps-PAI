 package PAI.controller;

import PAI.VOs.*;
import PAI.domain.Department;
import PAI.domain.TeacherCategory;
import PAI.repository.*;
import PAI.service.ITeacherCareerProgressionService;
import PAI.service.ITeacherService;
import PAI.service.TeacherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US13_RegisterTeacherAndRelevantDataControllerTest {

    // Arrange
    private ITeacherCategoryRepository _teacherCategoryRepoDouble;
    private IDepartmentRepository _departmentRepoDouble;
    private ITeacherService _teacherServiceDouble;
    private ITeacherCareerProgressionService _teacherCareerProgressionServiceDouble;

    @BeforeEach
    void factoryDoublesSetup() {
        _teacherCategoryRepoDouble = mock(ITeacherCategoryRepository.class);
        _departmentRepoDouble = mock(IDepartmentRepository.class);
        _teacherServiceDouble = mock(TeacherServiceImpl.class);
        _teacherCareerProgressionServiceDouble = mock(ITeacherCareerProgressionService.class);
    }


    // Tests

    @Test
    void shouldCreateObjectController() {
        // Arrange

        // Act
        new US13_RegisterTeacherAndRelevantDataController(
                _teacherCategoryRepoDouble, _departmentRepoDouble, _teacherServiceDouble, _teacherCareerProgressionServiceDouble);

        // Assert
    }

    @Test
    void shouldNotCreateObjectControllerWhenTeacherCategoryRepositoryIsNull() {
        // Arrange

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new US13_RegisterTeacherAndRelevantDataController(
                null, _departmentRepoDouble, _teacherServiceDouble, _teacherCareerProgressionServiceDouble));
    }

    @Test
    void shouldNotCreateCreateObjectControllerWhenDepartmentRepositoryIsNull() {
        // Arrange

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new US13_RegisterTeacherAndRelevantDataController(
                _teacherCategoryRepoDouble, null, _teacherServiceDouble, _teacherCareerProgressionServiceDouble));
    }

    @Test
    void shouldNotCreateCreateObjectControllerWhenTeacherRepositoryIsNull() {
        // Arrange

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new US13_RegisterTeacherAndRelevantDataController(
                _teacherCategoryRepoDouble, _departmentRepoDouble, null, _teacherCareerProgressionServiceDouble));
    }

    @Test
    void shouldNotCreateCreateObjectControllerWhenTeacherCareerProgressionIsNull() {
        // Arrange

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new US13_RegisterTeacherAndRelevantDataController(
                _teacherCategoryRepoDouble, _departmentRepoDouble, _teacherServiceDouble, null));
    }

    @Test
    void shouldReturnExceptionIfCategoriesListIsEmpty() throws Exception {
        // Arrange
        US13_RegisterTeacherAndRelevantDataController controllerUS13Double = new US13_RegisterTeacherAndRelevantDataController(
                _teacherCategoryRepoDouble, _departmentRepoDouble, _teacherServiceDouble, _teacherCareerProgressionServiceDouble);

        when(_teacherCategoryRepoDouble.findAll()).thenThrow(new IllegalStateException("Teacher Category list is empty."));

        // Act + Assert
        assertThrows(IllegalStateException.class, () -> controllerUS13Double.getTeacherCategoryList());
    }

    @Test
    void shouldReturnCategoryListWithRegisteredCategories() throws Exception {
        // Arrange
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        List<TeacherCategory> tcListDouble = List.of(tcDouble);
        US13_RegisterTeacherAndRelevantDataController controllerUS13Double = new US13_RegisterTeacherAndRelevantDataController(
                _teacherCategoryRepoDouble, _departmentRepoDouble, _teacherServiceDouble, _teacherCareerProgressionServiceDouble);

        when(_teacherCategoryRepoDouble.findAll()).thenReturn(tcListDouble);

        // Act
        Iterable<TeacherCategory> result = controllerUS13Double.getTeacherCategoryList();

        // Assert
        assertEquals(tcListDouble, result);
    }

    @Test
    void shouldReturnExceptionIfDepartmentsListIsEmpty() throws Exception {
        // Arrange
        US13_RegisterTeacherAndRelevantDataController controllerUS13Double = new US13_RegisterTeacherAndRelevantDataController(
                _teacherCategoryRepoDouble, _departmentRepoDouble, _teacherServiceDouble, _teacherCareerProgressionServiceDouble);

        when(_departmentRepoDouble.findAll()).thenThrow(new IllegalStateException("Department list is empty."));

        // Act + Assert
        assertThrows(IllegalStateException.class, () -> controllerUS13Double.getDepartmentList());
    }

    @Test
    void shouldReturnDepartmentListWithRegisteredDepartments() throws Exception {
        // Arrange
        US13_RegisterTeacherAndRelevantDataController controllerUS13Double = new US13_RegisterTeacherAndRelevantDataController(
                _teacherCategoryRepoDouble, _departmentRepoDouble, _teacherServiceDouble, _teacherCareerProgressionServiceDouble);

        Iterable<Department> dptListDouble = new HashSet<>();

        when(_departmentRepoDouble.findAll()).thenReturn(dptListDouble);

        // Act
        Iterable<Department> result = controllerUS13Double.getDepartmentList();
        // Assert
        assertEquals(dptListDouble, result);
    }

    @Test
    void shouldRegisterTeacherWithValidInputsIsolated() throws Exception {
        // Arrange
        US13_RegisterTeacherAndRelevantDataController controllerUS13Double = new US13_RegisterTeacherAndRelevantDataController(
                _teacherCategoryRepoDouble, _departmentRepoDouble, _teacherServiceDouble, _teacherCareerProgressionServiceDouble);

        TeacherID teacherID = mock(TeacherID.class);
        Optional<TeacherID> optionalTeacherID = Optional.of(teacherID);

        when(_teacherServiceDouble.registerTeacher(any(TeacherAcronym.class), any(Name.class), any(Email.class),
                any(NIF.class), any(PhoneNumber.class), any(AcademicBackground.class), any(Street.class),
                any(PostalCode.class), any(Location.class), any(Country.class), any(DepartmentID.class))).thenReturn(optionalTeacherID);

        when(_teacherCareerProgressionServiceDouble.createTeacherCareerProgression(any(Date.class), any(TeacherCategoryID.class),
                any(WorkingPercentage.class), any(TeacherID.class))).thenReturn(true);

        // Act
        boolean result = controllerUS13Double.registerTeacher("ACR", "Alice", "alice@exemplo.com",
                "123456789", "912345678", "Mestre em Ciências", "Rua das Flores",
                "1234-567", "Lisboa", "Portugal", "DEI", "01-09-2024",
                "550e8400-e29b-41d4-a716-446655440000", 100, "+351");

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldNotRegisterTeacherIsolated() throws Exception {
        // Arrange
        US13_RegisterTeacherAndRelevantDataController controllerUS13Double = new US13_RegisterTeacherAndRelevantDataController(
                _teacherCategoryRepoDouble, _departmentRepoDouble, _teacherServiceDouble, _teacherCareerProgressionServiceDouble);

        Optional<TeacherID> optionalEmpty = Optional.empty();

        when(_teacherServiceDouble.registerTeacher(any(TeacherAcronym.class), any(Name.class), any(Email.class),
                any(NIF.class), any(PhoneNumber.class), any(AcademicBackground.class), any(Street.class),
                any(PostalCode.class), any(Location.class), any(Country.class), any(DepartmentID.class))).thenReturn(optionalEmpty);

        // Act
        boolean result = controllerUS13Double.registerTeacher("ACR", "Alice", "alice@exemplo.com", "123456789", "912345678",
                "Mestre em Ciências", "Rua das Flores", "1234-567", "Lisboa", "Portugal",
                "DEI", "01-09-2024", "550e8400-e29b-41d4-a716-446655440000",
                100, "+351");

        // Assert
        assertFalse(result);
    }

//    @Test
//    void shouldRegisterATeacherIntegrationTest() throws Exception {
//        // Arrange
//        ITeacherCategoryFactory teacherCategoryFactory = new TeacherCategoryFactoryImpl();
//        ITeacherCategoryListFactory teacherCategoryListFactory = new TeacherCategoryListFactoryImpl();
//        ITeacherCategoryRepository teacherCategoryRepository = new TeacherCategoryRepositoryImpl(teacherCategoryFactory, teacherCategoryListFactory);
//        IDepartmentListFactory departmentListFactory = new DepartmentListFactoryImpl();
//        IDepartmentFactory departmentFactory = new DepartmentFactoryImpl();
//        IDepartmentRepository departmentRepository = new DepartmentRepositoryImpl(departmentFactory, departmentListFactory);
//        ITeacherFactory teacherFactory = new TeacherFactoryImpl();
//        ITeacherIDMapper teacherIDMapper = new TeacherIDMapper();
//        INIFMapper nifMapper = new NIFMapper();
//        IPhoneNumberMapper phoneNumberMapper = new PhoneNumberMapper();
//        IAddressMapper addressMapper = new AddressMapper();
//        ITeacherAcademicEmailMapper teacherAcademicEmailMapper = new TeacherAcademicEmailMapper();
//        ITeacherMapper teacherMapper = new TeacherMapper(teacherFactory, teacherIDMapper, nifMapper, phoneNumberMapper, addressMapper, teacherAcademicEmailMapper);
//        ITeacherRepositorySpringData iTeacherRepositorySpringData = mock(ITeacherRepositorySpringData.class);
//        ITeacherRepository teacherRepositorySpringData = new TeacherRepositorySpringData(iTeacherRepositorySpringData, teacherMapper, teacherIDMapper, nifMapper);
//        ITeacherService teacherService = new TeacherService(teacherFactory, teacherRepositorySpringData);
//        ITeacherCareerProgressionListFactory teacherCareerProgressionListFactory = new TeacherCareerProgressionListFactoryImpl();
//        ITeacherCareerProgressionFactory teacherCareerProgressionFactory = new TeacherCareerProgressionFactoryImpl();
//        TeacherCareerProgressionRepository teacherCareerProgressionRepository = new TeacherCareerProgressionRepository(teacherCareerProgressionFactory, teacherCareerProgressionListFactory);
//
//        US13_RegisterTeacherAndRelevantDataController controllerUS13Double = new US13_RegisterTeacherAndRelevantDataController(
//                teacherCategoryRepository, departmentRepository, teacherService, teacherCareerProgressionRepository);
//
//        // Act
//        boolean result = controllerUS13Double.registerTeacher("ACR", "Alice", "alice@exemplo.com", "123456789", "912345678",
//                "Mestre em Ciências", "Rua das Flores", "1234-567", "Lisboa", "Portugal",
//                "DEI", "01-09-2024", "550e8400-e29b-41d4-a716-446655440000",
//                100, "+351");
//
//        // Assert
//        assertTrue(result);
//    }
//
//    @Test
//    void shouldNotRegisterATeacherWhenTeacherIsDuplicateIntegrationTest() throws Exception {
//        // Arrange
//        ITeacherCategoryFactory teacherCategoryFactory = new TeacherCategoryFactoryImpl();
//        ITeacherCategoryListFactory teacherCategoryListFactory = new TeacherCategoryListFactoryImpl();
//        TeacherCategoryRepositoryImpl teacherCategoryRepository = new TeacherCategoryRepositoryImpl(teacherCategoryFactory, teacherCategoryListFactory);
//        IDepartmentListFactory departmentListFactory = new DepartmentListFactoryImpl();
//        IDepartmentFactory departmentFactory = new DepartmentFactoryImpl();
//        DepartmentRepositoryImpl departmentRepository = new DepartmentRepositoryImpl(departmentFactory, departmentListFactory);
//        ITeacherListFactory teacherListFactory = new TeacherListFactoryImpl();
//        ITeacherFactory teacherFactory = new TeacherFactoryImpl();
//        TeacherRepository teacherRepository = new TeacherRepository(teacherFactory, teacherListFactory);
//        ITeacherCareerProgressionListFactory teacherCareerProgressionListFactory = new TeacherCareerProgressionListFactoryImpl();
//        ITeacherCareerProgressionFactory teacherCareerProgressionFactory = new TeacherCareerProgressionFactoryImpl();
//        TeacherCareerProgressionRepository teacherCareerProgressionRepository = new TeacherCareerProgressionRepository(teacherCareerProgressionFactory, teacherCareerProgressionListFactory);
//
//        US13_RegisterTeacherAndRelevantDataController controllerUS13Double = new US13_RegisterTeacherAndRelevantDataController(
//                teacherCategoryRepository, departmentRepository, teacherRepository, teacherCareerProgressionRepository);
//
//        controllerUS13Double.registerTeacher("ACR", "Alice", "alice@exemplo.com", "123456789", "912345678",
//                "Mestre em Ciências", "Rua das Flores", "1234-567", "Lisboa", "Portugal",
//                "DEI", "01-09-2024", "550e8400-e29b-41d4-a716-446655440000",
//                100, "+351");
//
//        // Act
//        boolean result = controllerUS13Double.registerTeacher("ACR", "Alice", "alice@exemplo.com", "123456789", "912345678",
//                "Mestre em Ciências", "Rua das Flores", "1234-567", "Lisboa", "Portugal",
//                "DEI", "01-09-2024", "550e8400-e29b-41d4-a716-446655440000",
//                100, "+351");
//
//        // Assert
//        assertFalse(result);
//    }
}

