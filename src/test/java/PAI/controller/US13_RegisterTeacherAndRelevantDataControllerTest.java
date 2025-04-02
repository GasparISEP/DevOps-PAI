package PAI.controller;
import PAI.VOs.*;
import PAI.domain.Department;
import PAI.domain.TeacherCategory;
import PAI.repository.DepartmentRepository;
import PAI.repository.TeacherCareerProgressionRepository;
import PAI.repository.TeacherCategoryRepositoryImpl;
import PAI.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US13_RegisterTeacherAndRelevantDataControllerTest {
    // Arrange
    private TeacherCategoryRepositoryImpl _teacherCategoryRepoDouble;
    private DepartmentRepository _departmentRepoDouble;
    private TeacherRepository _teacherRepoDouble;
    private TeacherCareerProgressionRepository _teacherCareerProgressionRepoDouble;

    private TeacherAcronym _teacherAcronymDouble;
    private Name _nameDouble;
    private Email _emailDouble;
    private NIF _nifDouble;
    private PhoneNumber _phoneNumberDouble;
    private AcademicBackground _academicBackgroundDouble;
    private Street _streetDouble;
    private PostalCode _postalCodeDouble;
    private Location _locationDouble;
    private Country _countryDouble;
    private DepartmentID _departmentIDDouble;
    private Date _dateDouble;
    private TeacherCategoryID _teacherCategoryIDDouble;
    private WorkingPercentage _workingPercentageDouble;

    @BeforeEach
    void factoryDoublesSetup(){
        _teacherCategoryRepoDouble = mock(TeacherCategoryRepositoryImpl.class);
        _departmentRepoDouble = mock(DepartmentRepository.class);
        _teacherRepoDouble = mock(TeacherRepository.class);
        _teacherCareerProgressionRepoDouble = mock(TeacherCareerProgressionRepository.class);
    }

    private void createTeacherArgumentDoubles(){
        _teacherAcronymDouble = mock(TeacherAcronym.class);
        _nameDouble = mock(Name.class);
        _emailDouble = mock(Email.class);
        _nifDouble = mock(NIF.class);
        _phoneNumberDouble = mock(PhoneNumber.class);
        _academicBackgroundDouble = mock(AcademicBackground.class);
        _streetDouble = mock(Street.class);
        _postalCodeDouble = mock(PostalCode.class);
        _locationDouble = mock(Location.class);
        _countryDouble = mock(Country.class);
        _departmentIDDouble = mock(DepartmentID.class);
        _dateDouble = mock(Date.class);
        _teacherCategoryIDDouble = mock(TeacherCategoryID.class);
        _workingPercentageDouble = mock(WorkingPercentage.class);
    }


    // Tests

    @Test
    void shouldCreateObjectController() {
        // Arrange

        // Act
        new US13_RegisterTeacherAndRelevantDataController(
                _teacherCategoryRepoDouble, _departmentRepoDouble, _teacherRepoDouble, _teacherCareerProgressionRepoDouble);

        // Assert
    }

    @Test
    void shouldNotCreateObjectControllerWhenTeacherCategoryRepositoryIsNull() {
        // Arrange

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new US13_RegisterTeacherAndRelevantDataController(
                null, _departmentRepoDouble, _teacherRepoDouble, _teacherCareerProgressionRepoDouble));
    }

    @Test
    void shouldNotCreateCreateObjectControllerWhenDepartmentRepositoryIsNull() {
        // Arrange

        // Act + Assert
       assertThrows(IllegalArgumentException.class, () -> new US13_RegisterTeacherAndRelevantDataController(
               _teacherCategoryRepoDouble, null, _teacherRepoDouble, _teacherCareerProgressionRepoDouble));
    }

    @Test
    void shouldNotCreateCreateObjectControllerWhenTeacherRepositoryIsNull() {
        // Arrange

        // Act + Assert
         assertThrows(IllegalArgumentException.class, () -> new US13_RegisterTeacherAndRelevantDataController(
                 _teacherCategoryRepoDouble, _departmentRepoDouble, null, _teacherCareerProgressionRepoDouble));
    }

    @Test
    void shouldNotCreateCreateObjectControllerWhenTeacherCareerProgressionIsNull() {
        // Arrange

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new US13_RegisterTeacherAndRelevantDataController(
                _teacherCategoryRepoDouble, _departmentRepoDouble, _teacherRepoDouble, null));
    }

    @Test
    void shouldReturnExceptionIfCategoriesListIsEmpty() throws IllegalStateException {
        // Arrange


        US13_RegisterTeacherAndRelevantDataController controllerUS13Double = new US13_RegisterTeacherAndRelevantDataController(
                _teacherCategoryRepoDouble, _departmentRepoDouble, _teacherRepoDouble, _teacherCareerProgressionRepoDouble);

        when(_teacherCategoryRepoDouble.getTeacherCategoryList()).thenThrow(new IllegalStateException("Teacher Category list is empty."));

        // Act + Assert
        assertThrows(IllegalStateException.class, () -> controllerUS13Double.getTeacherCategoryList());
    }

    @Test
    void shouldReturnCategoryListWithRegisteredCategories() {
        // Arrange
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        List<TeacherCategory> tcListDouble = List.of(tcDouble);
        US13_RegisterTeacherAndRelevantDataController controllerUS13Double = new US13_RegisterTeacherAndRelevantDataController(
                _teacherCategoryRepoDouble, _departmentRepoDouble, _teacherRepoDouble, _teacherCareerProgressionRepoDouble);

        when(_teacherCategoryRepoDouble.getTeacherCategoryList()).thenReturn(tcListDouble);

        // Act
        List<TeacherCategory> result = controllerUS13Double.getTeacherCategoryList();

        // Assert
        assertEquals(result, tcListDouble);
    }

    @Test
    void shouldReturnExceptionIfDepartmentsListIsEmpty() throws IllegalStateException {
        // Arrange
        US13_RegisterTeacherAndRelevantDataController controllerUS13Double = new US13_RegisterTeacherAndRelevantDataController(
                _teacherCategoryRepoDouble, _departmentRepoDouble, _teacherRepoDouble, _teacherCareerProgressionRepoDouble);

        when(_departmentRepoDouble.getDepartmentIDs()).thenThrow(new IllegalStateException("Department list is empty."));

        // Act + Assert
        assertThrows(IllegalStateException.class, () -> controllerUS13Double.getDepartmentIDList());
    }

    @Test
    void shouldReturnDepartmentListWithRegisteredDepartments() {
        // Arrange
        US13_RegisterTeacherAndRelevantDataController controllerUS13Double = new US13_RegisterTeacherAndRelevantDataController(
                _teacherCategoryRepoDouble, _departmentRepoDouble, _teacherRepoDouble, _teacherCareerProgressionRepoDouble);

        DepartmentID dptDouble = mock(DepartmentID.class);
        Set<DepartmentID> dptListDouble = new HashSet<>();

        dptListDouble.add(dptDouble);

        when(_departmentRepoDouble.getDepartmentIDs()).thenReturn(dptListDouble);

        // Act
        Set<DepartmentID> result = controllerUS13Double.getDepartmentIDList();
        // Assert
        assertEquals(dptListDouble, result);
    }

    @Test
    void shouldRegisterTeacher() throws Exception {
        // Arrange
        US13_RegisterTeacherAndRelevantDataController controllerUS13Double = new US13_RegisterTeacherAndRelevantDataController(
                _teacherCategoryRepoDouble, _departmentRepoDouble, _teacherRepoDouble, _teacherCareerProgressionRepoDouble);

        createTeacherArgumentDoubles();
        TeacherID teacherID = mock(TeacherID.class);
        Optional<TeacherID> optionalTeacherID = Optional.of(teacherID);

        when(_teacherRepoDouble.registerTeacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                _phoneNumberDouble, _academicBackgroundDouble, _streetDouble, _postalCodeDouble, _locationDouble,
                _countryDouble, _departmentIDDouble)).thenReturn(optionalTeacherID);

        when(_teacherCareerProgressionRepoDouble.createTeacherCareerProgression(_dateDouble, _teacherCategoryIDDouble, _workingPercentageDouble, teacherID)).thenReturn(true);

        // Act
        boolean result = controllerUS13Double.registerTeacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                _phoneNumberDouble, _academicBackgroundDouble, _streetDouble, _postalCodeDouble, _locationDouble,
                _countryDouble, _departmentIDDouble, _dateDouble, _teacherCategoryIDDouble, _workingPercentageDouble);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldNotRegisterTeacher() throws Exception {
        // Arrange
        US13_RegisterTeacherAndRelevantDataController controllerUS13Double = new US13_RegisterTeacherAndRelevantDataController(
                _teacherCategoryRepoDouble, _departmentRepoDouble, _teacherRepoDouble, _teacherCareerProgressionRepoDouble);

        createTeacherArgumentDoubles();
        Optional<TeacherID> optionalEmpty = Optional.empty();

        when(_teacherRepoDouble.registerTeacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                _phoneNumberDouble, _academicBackgroundDouble, _streetDouble, _postalCodeDouble, _locationDouble,
                _countryDouble, _departmentIDDouble)).thenReturn(optionalEmpty);

        // Act
        boolean result = controllerUS13Double.registerTeacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                _phoneNumberDouble, _academicBackgroundDouble, _streetDouble, _postalCodeDouble, _locationDouble,
                _countryDouble, _departmentIDDouble, _dateDouble, _teacherCategoryIDDouble, _workingPercentageDouble);

        // Assert
        assertFalse(result);
    }
}