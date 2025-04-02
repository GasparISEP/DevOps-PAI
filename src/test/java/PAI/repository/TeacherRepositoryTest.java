package PAI.repository;

import PAI.VOs.*;
import PAI.VOs.Location;
import PAI.domain.*;
import PAI.factory.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TeacherRepositoryTest {

    // Arrange
    private ITeacherFactory _teacherFactory;
    private ITeacherListFactory _teacherListFactory;

    private List _teacherListDouble;
    private Iterator _iterator;

    private TeacherAcronym _teacherAcronymDouble;
    private Name _nameDouble;
    private Email _emailDouble;
    private NIF _nifDouble;
    private PhoneNumber _phoneNumberDouble;
    private AcademicBackground _academicBackgroundDouble;
    private DepartmentID _departmentIDDouble;
    private Street _streetDouble;
    private PostalCode _postalCodeDouble;
    private Location _locationDouble;
    private Country _countryDouble;
    private Teacher _teacherDouble;
    private TeacherID _teacherID;

    private void createFactoriesDoubles(){
        _teacherFactory = mock(ITeacherFactory.class);
        _teacherListFactory = mock(ITeacherListFactory.class);
    }

    private void createListDouble(ITeacherListFactory teacherListDouble){
        _teacherListDouble = mock(ArrayList.class);
        when(teacherListDouble.newList()).thenReturn(_teacherListDouble);
        when(_teacherListDouble.add(_teacherDouble)).thenReturn(true);
        _iterator= mock(Iterator.class);
        when(_teacherListDouble.iterator()).thenReturn(_iterator);
    }

    private void createTeacherAndArgumentsDouble (){
        _teacherAcronymDouble = mock(TeacherAcronym.class);
        _nameDouble = mock(Name.class);
        _emailDouble = mock(Email.class);
        _nifDouble = mock(NIF.class);
        _phoneNumberDouble = mock(PhoneNumber.class);
        _academicBackgroundDouble = mock(AcademicBackground.class);
        _departmentIDDouble = mock(DepartmentID.class);
        _streetDouble = mock(Street.class);
        _postalCodeDouble = mock(PostalCode.class);
        _locationDouble = mock(Location.class);
        _countryDouble = mock(Country.class);
        _teacherDouble = mock(Teacher.class);
        _teacherID = mock(TeacherID.class);
    }


    // Tests Beginning

    @Test
    void shouldCreateTeacherRepository() {
        // Arrange
        createFactoriesDoubles();

        // Act
        new TeacherRepository(_teacherFactory, _teacherListFactory);

        // Assert
    }

    @Test
    void shouldReturnTeacherWhenSaveIsCalled(){
        // Arrange
        createFactoriesDoubles();
        Teacher teacher = mock(Teacher.class);

        TeacherRepository teacherRepository = new TeacherRepository(_teacherFactory, _teacherListFactory);

        // Act
        Teacher teacher1 = teacherRepository.save(teacher);

        // Assert
        assertEquals(teacher, teacher1);
    }

    @Test
    void shouldReturnAllTeachersWhenFindAllIsCalled() {
        // Arrange
        createFactoriesDoubles();
        Teacher teacher1 = mock(Teacher.class);
        Teacher teacher2 = mock(Teacher.class);
        List<Teacher> listTeachersDouble = List.of(teacher1, teacher2);
        _teacherListFactory = () -> listTeachersDouble;
        TeacherRepository teacherRepository = new TeacherRepository(_teacherFactory, _teacherListFactory);

        // Act
        Iterable<Teacher> result = teacherRepository.findAll();

        // Assert
        assertIterableEquals(listTeachersDouble, result);
    }

    @Test
    void shouldReturnATeacherWhenOfIdentityIsCalledWithExistingID(){
        // Arrange
        createFactoriesDoubles();
        TeacherID idDouble = mock(TeacherID.class);
        Teacher teacher = mock(Teacher.class);
        when(teacher.identity()).thenReturn(idDouble);

        List<Teacher> teachers = List.of(teacher);
        _teacherListFactory = () -> teachers;

        TeacherRepository repository = new TeacherRepository(_teacherFactory, _teacherListFactory);

        // Act
        Optional<Teacher> result = repository.ofIdentity(idDouble);

        // Assert
        assertTrue(result.isPresent());
    }

    @Test
    void shouldNotReturnATeacherWhenOfIdentityIsCalledWithNoExistingID(){
        // Arrange
        createFactoriesDoubles();
        TeacherID idDouble = mock(TeacherID.class);
        TeacherID id2Double = mock(TeacherID.class);
        Teacher teacher = mock(Teacher.class);
        when(teacher.identity()).thenReturn(idDouble);

        List<Teacher> teachers = List.of(teacher);
        _teacherListFactory = () -> teachers;

        TeacherRepository repository = new TeacherRepository(_teacherFactory, _teacherListFactory);

        // Act
        Optional<Teacher> result = repository.ofIdentity(id2Double);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnTrueWhenTeacherIsPresentAndContainsOfIdentityIsCalled(){
        // Arrange
        createFactoriesDoubles();
        TeacherID idDouble = mock(TeacherID.class);
        Teacher teacher = mock(Teacher.class);
        when(teacher.identity()).thenReturn(idDouble);

        List<Teacher> teachers = List.of(teacher);
        _teacherListFactory = () -> teachers;

        TeacherRepository repository = new TeacherRepository(_teacherFactory, _teacherListFactory);

        // Act
        boolean result = repository.containsOfIdentity(idDouble);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenTeacherIsNotPresentAndContainsOfIdentityIsCalled(){
        // Arrange
        createFactoriesDoubles();
        TeacherID idDouble = mock(TeacherID.class);
        TeacherID id2Double = mock(TeacherID.class);
        Teacher teacher = mock(Teacher.class);
        when(teacher.identity()).thenReturn(idDouble);

        List<Teacher> teachers = List.of(teacher);
        _teacherListFactory = () -> teachers;

        TeacherRepository repository = new TeacherRepository(_teacherFactory, _teacherListFactory);

        // Act
        boolean result = repository.containsOfIdentity(id2Double);

        // Assert
        assertFalse(result);
    }


    @Test
    void shouldReturnTeacherIDWhenValidTeacherIsRegistered() {
        // Arrange
        createFactoriesDoubles();
        createTeacherAndArgumentsDouble();
        createListDouble(_teacherListFactory);

        TeacherRepository teacherRepository = new TeacherRepository(_teacherFactory, _teacherListFactory);

        when(_teacherFactory.createTeacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                _phoneNumberDouble, _academicBackgroundDouble, _streetDouble, _postalCodeDouble, _locationDouble,
                _countryDouble, _departmentIDDouble)).thenReturn(_teacherDouble);
        when(_iterator.hasNext()).thenReturn(false);
        when(_teacherDouble.identity()).thenReturn(_teacherID);

        // Act
        Optional<TeacherID> result = teacherRepository.registerTeacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                _phoneNumberDouble, _academicBackgroundDouble, _streetDouble, _postalCodeDouble, _locationDouble, _countryDouble, _departmentIDDouble);

        // Assert
        assertTrue(result.isPresent());
    }

    //Test to register two valid teachers
    @Test
    void shouldReturnTrueWhenASecondValidTeacherIsRegistered() {
        // Arrange
        createFactoriesDoubles();
        createTeacherAndArgumentsDouble();
        createListDouble(_teacherListFactory);
        Teacher teacherDouble2 = mock(Teacher.class);
        TeacherID teacherID2 = mock(TeacherID.class);

        TeacherRepository teacherRepository = new TeacherRepository(_teacherFactory, _teacherListFactory);

        when(_teacherFactory.createTeacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                _phoneNumberDouble, _academicBackgroundDouble, _streetDouble, _postalCodeDouble, _locationDouble,
                _countryDouble, _departmentIDDouble)).thenReturn(_teacherDouble, teacherDouble2);

        // Mock iterator behavior
        when(_iterator.hasNext()).thenReturn(false, true, false);
        when(_teacherDouble.identity()).thenReturn(_teacherID);
        when(_iterator.next()).thenReturn(_teacherDouble);
        when(teacherDouble2.identity()).thenReturn(teacherID2);

        teacherRepository.registerTeacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                _phoneNumberDouble, _academicBackgroundDouble, _streetDouble, _postalCodeDouble, _locationDouble,
                _countryDouble, _departmentIDDouble);

        // Act
        Optional<TeacherID> result = teacherRepository.registerTeacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                _phoneNumberDouble, _academicBackgroundDouble, _streetDouble, _postalCodeDouble, _locationDouble,
                _countryDouble, _departmentIDDouble);

        // Assert
        assertTrue(result.isPresent());
    }

    @Test
    public void shouldReturnFalseWhenADuplicatedTeacherIsRegistered() {
        // Arrange
        createFactoriesDoubles();
        createTeacherAndArgumentsDouble();
        createListDouble(_teacherListFactory);
        Teacher teacherDouble2 = mock(Teacher.class);

        TeacherRepository teacherRepository = new TeacherRepository(_teacherFactory, _teacherListFactory);

        when(_teacherFactory.createTeacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                _phoneNumberDouble, _academicBackgroundDouble, _streetDouble, _postalCodeDouble, _locationDouble,
                _countryDouble, _departmentIDDouble)).thenReturn(_teacherDouble, teacherDouble2);

        // Mock iterator behavior
        when(_iterator.hasNext()).thenReturn(false, true);
        when(_teacherDouble.identity()).thenReturn(_teacherID);
        when(_iterator.next()).thenReturn(_teacherDouble);
        when(teacherDouble2.sameAs(_teacherDouble)).thenReturn(true);
        when(teacherDouble2.identity()).thenReturn(_teacherID);


        teacherRepository.registerTeacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                _phoneNumberDouble, _academicBackgroundDouble, _streetDouble, _postalCodeDouble, _locationDouble,
                _countryDouble, _departmentIDDouble);

        // Act
        Optional<TeacherID> result = teacherRepository.registerTeacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                _phoneNumberDouble, _academicBackgroundDouble, _streetDouble, _postalCodeDouble, _locationDouble,
                _countryDouble, _departmentIDDouble);

        // Assert
        assertTrue(result.isEmpty());
    }


    // Other Tests

    @Test
    void shouldReturnATeacherWhenGetTeacherByNIFIsCalledWithExistingNIF() {
        // Arrange
        createFactoriesDoubles();
        NIF nifDouble = mock(NIF.class);
        Teacher teacher = mock(Teacher.class);
        when(teacher.hasThisNIF(nifDouble)).thenReturn(true);

        List<Teacher> teachers = List.of(teacher);
        _teacherListFactory = () -> teachers;

        TeacherRepository repository = new TeacherRepository(_teacherFactory, _teacherListFactory);

        // Act
        Optional<Teacher> result = repository.getTeacherByNIF(nifDouble);

        // Assert
        assertTrue(result.isPresent());
    }

    @Test
    void shouldNotReturnATeacherWhenGetTeacherByNIFIsCalledWithNoExistingNIF(){
        // Arrange
        createFactoriesDoubles();
        NIF nifDouble = mock(NIF.class);
        Teacher teacher = mock(Teacher.class);
        when(teacher.hasThisNIF(nifDouble)).thenReturn(false);

        List<Teacher> teachers = List.of(teacher);
        _teacherListFactory = () -> teachers;

        TeacherRepository repository = new TeacherRepository(_teacherFactory, _teacherListFactory);

        // Act
        Optional<Teacher> result = repository.getTeacherByNIF(nifDouble);

        // Assert
        assertTrue(result.isEmpty());
    }
}