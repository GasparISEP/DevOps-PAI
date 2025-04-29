 package PAI.controller;

import PAI.VOs.*;
import PAI.domain.Department;
import PAI.domain.TeacherCategory;
import PAI.factory.*;
import PAI.persistence.springdata.Department.DepartmentRepositorySpringDataImpl;
import PAI.persistence.springdata.TeacherCategoryRepositorySpringDataImpl;
import PAI.persistence.springdata.teacherCareerProgression.TeacherCareerProgressionRepoSpringDataImpl;
import PAI.repository.*;
import PAI.service.*;
import PAI.service.teacherCareerProgression.ITeacherCareerProgressionService;
import PAI.service.ITeacherService;
import PAI.service.teacherCareerProgression.TeacherCareerProgressionServiceImpl;
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
    private ITeacherCategoryService _teacherCategoryServiceDouble;
    private IDepartmentRepository _departmentRepoDouble;
    private ITeacherService _teacherServiceDouble;
    private ITeacherCareerProgressionService _teacherCareerProgressionServiceDouble;

    @BeforeEach
    void factoryDoublesSetup() {
        _teacherCategoryServiceDouble = mock(TeacherCategoryServiceImpl.class);
        _departmentRepoDouble = mock(IDepartmentRepository.class);
        _teacherServiceDouble = mock(TeacherServiceImpl.class);
        _teacherCareerProgressionServiceDouble = mock(TeacherCareerProgressionServiceImpl.class);
    }


    // Tests

    @Test
    void shouldCreateObjectController() {
        // Arrange

        // Act
        new US13_RegisterTeacherAndRelevantDataController(
                _teacherCategoryServiceDouble, _departmentRepoDouble, _teacherServiceDouble, _teacherCareerProgressionServiceDouble);

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
                _teacherCategoryServiceDouble, null, _teacherServiceDouble, _teacherCareerProgressionServiceDouble));
    }

    @Test
    void shouldNotCreateCreateObjectControllerWhenTeacherRepositoryIsNull() {
        // Arrange

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new US13_RegisterTeacherAndRelevantDataController(
                _teacherCategoryServiceDouble, _departmentRepoDouble, null, _teacherCareerProgressionServiceDouble));
    }

    @Test
    void shouldNotCreateCreateObjectControllerWhenTeacherCareerProgressionIsNull() {
        // Arrange

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new US13_RegisterTeacherAndRelevantDataController(
                _teacherCategoryServiceDouble, _departmentRepoDouble, _teacherServiceDouble, null));
    }

    @Test
    void shouldReturnExceptionIfCategoriesListIsEmpty() {
        // Arrange
        US13_RegisterTeacherAndRelevantDataController controllerUS13Double = new US13_RegisterTeacherAndRelevantDataController(
                _teacherCategoryServiceDouble, _departmentRepoDouble, _teacherServiceDouble, _teacherCareerProgressionServiceDouble);

        when(_teacherCategoryServiceDouble.getAllTeacherCategories()).thenThrow(new IllegalStateException("Teacher Category list is empty."));

        // Act + Assert
        assertThrows(IllegalStateException.class, () -> controllerUS13Double.getTeacherCategoryList());
    }

    @Test
    void shouldReturnCategoryListWithRegisteredCategories() throws Exception {
        // Arrange
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        List<TeacherCategory> tcListDouble = List.of(tcDouble);
        US13_RegisterTeacherAndRelevantDataController controllerUS13Double = new US13_RegisterTeacherAndRelevantDataController(
                _teacherCategoryServiceDouble, _departmentRepoDouble, _teacherServiceDouble, _teacherCareerProgressionServiceDouble);

        when(_teacherCategoryServiceDouble.getAllTeacherCategories()).thenReturn(tcListDouble);

        // Act
        Iterable<TeacherCategory> result = controllerUS13Double.getTeacherCategoryList();

        // Assert
        assertEquals(tcListDouble, result);
    }

    @Test
    void shouldReturnExceptionIfDepartmentsListIsEmpty() {
        // Arrange
        US13_RegisterTeacherAndRelevantDataController controllerUS13Double = new US13_RegisterTeacherAndRelevantDataController(
                _teacherCategoryServiceDouble, _departmentRepoDouble, _teacherServiceDouble, _teacherCareerProgressionServiceDouble);

        when(_departmentRepoDouble.findAll()).thenThrow(new IllegalStateException("Department list is empty."));

        // Act + Assert
        assertThrows(IllegalStateException.class, () -> controllerUS13Double.getDepartmentList());
    }

    @Test
    void shouldReturnDepartmentListWithRegisteredDepartments() {
        // Arrange
        US13_RegisterTeacherAndRelevantDataController controllerUS13Double = new US13_RegisterTeacherAndRelevantDataController(
                _teacherCategoryServiceDouble, _departmentRepoDouble, _teacherServiceDouble, _teacherCareerProgressionServiceDouble);

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
                _teacherCategoryServiceDouble, _departmentRepoDouble, _teacherServiceDouble, _teacherCareerProgressionServiceDouble);

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
                _teacherCategoryServiceDouble, _departmentRepoDouble, _teacherServiceDouble, _teacherCareerProgressionServiceDouble);

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

    @Test
    void shouldRegisterATeacherIntegrationTest() throws Exception {
        // Arrange dependencies of TeacherCategory aggregate
        ITeacherCategoryFactory teacherCategoryFactory = new TeacherCategoryFactoryImpl();
        ITeacherCategoryRepository teacherCategoryRepository = mock(TeacherCategoryRepositorySpringDataImpl.class);
        ITeacherCategoryService teacherCategoryService = new TeacherCategoryServiceImpl(teacherCategoryRepository, teacherCategoryFactory);

        // Arrange dependencies of Department aggregate
        IDepartmentRepository departmentRepository = mock(DepartmentRepositorySpringDataImpl.class);

        // Arrange dependencies of Teacher aggregate
        ITeacherService teacherService = mock(TeacherServiceImpl.class);

        // Arrange dependencies of TCP aggregate
        ITeacherCareerProgressionFactory tcpFactory = new TeacherCareerProgressionFactoryImpl();
        ITeacherCareerProgressionRepository tcpRepository = mock(TeacherCareerProgressionRepoSpringDataImpl.class);
        ITeacherCareerProgressionService tcpService = new TeacherCareerProgressionServiceImpl(tcpRepository, tcpFactory);

        // Arrange call of registerTeacher method from TeacherService
        when(teacherService.registerTeacher(
            any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()
        )).thenReturn(Optional.of(new TeacherID(new TeacherAcronym("ACR"))));


        // Arrange dependencies of the tcpService's createCareerProgression method
//        Date date = new Date("11-04-2025");
//        UUID id = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
//        TeacherCategoryID tcID = new TeacherCategoryID(id);
//        WorkingPercentage workingPercentage = new WorkingPercentage(100);
//
//        TeacherID teacherID = new TeacherID(new TeacherAcronym("ACR"));
//
//        when(tcpService.createTeacherCareerProgression(date, tcID, workingPercentage, teacherID)).thenReturn(true);

        US13_RegisterTeacherAndRelevantDataController controllerUS13Double = new US13_RegisterTeacherAndRelevantDataController(
                teacherCategoryService, departmentRepository, teacherService, tcpService);

        // Act
        boolean result = controllerUS13Double.registerTeacher("ACR", "Alice", "alice@exemplo.com", "123456789", "912345678",
                "Mestre em Ciências", "Rua das Flores", "1234-567", "Lisboa", "Portugal",
                "DEI", "11-04-2025", "550e8400-e29b-41d4-a716-446655440000",
                100, "+351");

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldNotRegisterATeacherWhenTeacherIsDuplicateIntegrationTest() throws Exception {
        // Arrange dependencies of TeacherCategory aggregate
        ITeacherCategoryFactory teacherCategoryFactory = new TeacherCategoryFactoryImpl();
        ITeacherCategoryRepository teacherCategoryRepository = mock(TeacherCategoryRepositorySpringDataImpl.class);
        ITeacherCategoryService teacherCategoryService = new TeacherCategoryServiceImpl(teacherCategoryRepository, teacherCategoryFactory);

        // Arrange dependencies of Department aggregate
        IDepartmentRepository departmentRepository = mock(DepartmentRepositorySpringDataImpl.class);

        // Arrange dependencies of Teacher aggregate
        ITeacherService teacherService = mock(TeacherServiceImpl.class);

        // Arrange dependencies of TCP aggregate
        ITeacherCareerProgressionFactory tcpFactory = new TeacherCareerProgressionFactoryImpl();
        ITeacherCareerProgressionRepository tcpRepository = mock(TeacherCareerProgressionRepoSpringDataImpl.class);
        ITeacherCareerProgressionService tcpService = new TeacherCareerProgressionServiceImpl(tcpRepository, tcpFactory);

        US13_RegisterTeacherAndRelevantDataController controllerUS13Double = new US13_RegisterTeacherAndRelevantDataController(
                teacherCategoryService, departmentRepository, teacherService, tcpService);

        // Arrange call of registerTeacher method from TeacherService
        when(teacherService.registerTeacher(
                any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()
        )).thenReturn(Optional.of(new TeacherID(new TeacherAcronym("ACR"))));

        controllerUS13Double.registerTeacher("ACR", "Alice", "alice@exemplo.com", "123456789", "912345678",
                "Mestre em Ciências", "Rua das Flores", "1234-567", "Lisboa", "Portugal",
                "DEI", "01-09-2024", "550e8400-e29b-41d4-a716-446655440000",
                100, "+351");

        // Arrange second call of registerTeacher method from TeacherService
        when(teacherService.registerTeacher(
                any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()
        )).thenReturn(Optional.empty());

        // Act
        boolean result = controllerUS13Double.registerTeacher("ACR", "Alice", "alice@exemplo.com", "123456789", "912345678",
                "Mestre em Ciências", "Rua das Flores", "1234-567", "Lisboa", "Portugal",
                "DEI", "01-09-2024", "550e8400-e29b-41d4-a716-446655440000",
                100, "+351");

        // Assert
        assertFalse(result);
    }
}

