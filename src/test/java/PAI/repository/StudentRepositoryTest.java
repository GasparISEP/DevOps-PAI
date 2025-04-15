package PAI.repository;

import PAI.VOs.*;
import PAI.VOs.Location;
import PAI.domain.*;
import PAI.factory.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;


import java.util.ArrayList;
import java.util.Iterator;

import java.util.List;
import java.util.Optional;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

class StudentRepositoryTest {

    @Nested
    class TestsWithIsolation {
        private IStudentFactory _studentFactoryImplDouble;
        private IStudentListFactory _studentListFactoryImplDouble;
        private Iterator _iterator;
        private Street _streetDouble;
        private PostalCode _postalCodeDouble;
        private Location _locationDouble;
        private Country _countryDouble;
        private Student _studentDouble1;
        private Student _studentDouble2;
        private StudentID _studentID1;
        private StudentID _studentID2;
        private Name _name;
        private NIF _nif;
        private PhoneNumber _phone;
        private Email _email;
        private StudentAcademicEmail _academicEmail;

        @BeforeEach
        //arrange
        void setup() {
            _studentFactoryImplDouble = mock(IStudentFactory.class);
            _studentListFactoryImplDouble = mock(IStudentListFactory.class);

            _streetDouble = mock(Street.class);
            _postalCodeDouble = mock(PostalCode.class);
            _locationDouble = mock(Location.class);
            _countryDouble = mock(Country.class);
            _studentDouble1 = mock(Student.class);
            _studentDouble2 = mock(Student.class);
            _studentID1 = mock(StudentID.class);
            _studentID2 = mock(StudentID.class);
            _name = mock(Name.class);
            _nif = mock(NIF.class);
            _phone = mock(PhoneNumber.class);
            _email = mock(Email.class);
            _academicEmail = mock(StudentAcademicEmail.class);

            // Create ArrayList mock
            ArrayList<Student> _studentListDouble = mock(ArrayList.class);
            when(_studentListFactoryImplDouble.newArrayList()).thenReturn(_studentListDouble);
            // Configure ArrayList behaviour
            when(_studentListDouble.add(_studentDouble1)).thenReturn(true);
            _iterator = mock(Iterator.class);
            when(_studentListDouble.iterator()).thenReturn(_iterator);
        }

        @Test
        void shouldCreateStudentRepository() {

            //act
            new StudentRepository(_studentFactoryImplDouble, _studentListFactoryImplDouble);
        }

        @Test
        void shouldThrowExceptionWhenStudentFactoryisNull() {

            //act
            assertThrows(IllegalArgumentException.class, () -> new StudentRepository(null, _studentListFactoryImplDouble));
        }

        @Test
        void shouldThrowExceptionWhenStudentFactoryListIsNull() {

            //act
            assertThrows(IllegalArgumentException.class, () -> new StudentRepository(_studentFactoryImplDouble, null));
        }

        @Test
        void testRegisterDuplicateNIFThrowsException() throws Exception {
            // Arrange
            StudentRepository studentRepository = new StudentRepository(_studentFactoryImplDouble, _studentListFactoryImplDouble);

            when(_studentFactoryImplDouble.newStudent(_studentID1, _name, _nif, _phone, _email, _streetDouble, _postalCodeDouble, _locationDouble, _countryDouble, _academicEmail)).thenReturn(_studentDouble1);
            when(_studentFactoryImplDouble.newStudent(_studentID1, _name, _nif, _phone, _email, _streetDouble, _postalCodeDouble, _locationDouble, _countryDouble, _academicEmail)).thenReturn(_studentDouble2);

            when(_iterator.hasNext()).thenReturn(false, true, false);
            when(_iterator.next()).thenReturn(_studentDouble1);

            when(_studentDouble1.sameAs(_studentDouble2)).thenReturn(true);

            studentRepository.registerStudent(_studentID1, _name, _nif, _phone, _email, _streetDouble, _postalCodeDouble, _locationDouble, _countryDouble, _academicEmail);

            // Act & Assert
            Exception exception = assertThrows(Exception.class, () -> {
                studentRepository.registerStudent(_studentID1, _name, _nif, _phone, _email, _streetDouble, _postalCodeDouble, _locationDouble, _countryDouble, _academicEmail);
            });
            assertEquals(exception.getMessage(), "Duplicate ID or NIF detected. Student cannot be added.");
        }

        @Test
        void testRegisterDuplicateStudentIDThrowsException() throws Exception {
            // Arrange
            StudentRepository studentRepository = new StudentRepository(_studentFactoryImplDouble, _studentListFactoryImplDouble);
            when(_studentFactoryImplDouble.newStudent(_studentID1, _name, _nif, _phone, _email, _streetDouble, _postalCodeDouble, _locationDouble, _countryDouble, _academicEmail)).thenReturn(_studentDouble1);
            when(_studentFactoryImplDouble.newStudent(_studentID1, _name, _nif, _phone, _email, _streetDouble, _postalCodeDouble, _locationDouble, _countryDouble, _academicEmail)).thenReturn(_studentDouble2);

            when(_iterator.hasNext()).thenReturn(false);

            studentRepository.registerStudent(_studentID1, _name, _nif, _phone, _email, _streetDouble, _postalCodeDouble, _locationDouble, _countryDouble, _academicEmail);

            when(_iterator.hasNext()).thenReturn(true, false);
            when(_iterator.next()).thenReturn(_studentDouble1);

            when(_studentDouble1.isEquals(_studentDouble2)).thenReturn(true);

            // Act & Assert
            Exception exception = assertThrows(Exception.class, () -> {
                studentRepository.registerStudent(_studentID1, _name, _nif, _phone, _email, _streetDouble, _postalCodeDouble, _locationDouble, _countryDouble, _academicEmail);
            });
            assertEquals("Duplicate ID or NIF detected. Student cannot be added.", exception.getMessage());
        }

        @Test
        void shouldReturnTrueWhenValidStudentsAreRegistered() throws Exception {
            // Arrange
            StudentRepository studentRepository = new StudentRepository(_studentFactoryImplDouble, _studentListFactoryImplDouble);
            when(_studentFactoryImplDouble.newStudent(_studentID1, _name, _nif, _phone, _email, _streetDouble, _postalCodeDouble, _locationDouble, _countryDouble, _academicEmail)).thenReturn(_studentDouble1);
            when(_studentFactoryImplDouble.newStudent(_studentID2, _name, _nif, _phone, _email, _streetDouble, _postalCodeDouble, _locationDouble, _countryDouble, _academicEmail)).thenReturn(_studentDouble2);
            when(_iterator.hasNext()).thenReturn(false, true, false);
            when(_iterator.next()).thenReturn(_studentDouble1);
            when(_studentDouble2.isEquals(_studentDouble1) && _studentDouble2.sameAs(_studentDouble1)).thenReturn(false);

            studentRepository.registerStudent(_studentID1, _name, _nif, _phone, _email, _streetDouble, _postalCodeDouble, _locationDouble, _countryDouble, _academicEmail);

            // Act
            boolean result = studentRepository.registerStudent(_studentID2, _name, _nif, _phone, _email, _streetDouble, _postalCodeDouble, _locationDouble, _countryDouble, _academicEmail);

            // Assert
            assertTrue(result);
        }

        @Test
        void shouldReturnOptionalWithStudentIfStudentWithSpecificNIFIsFound() throws Exception {
            // Arrange
            StudentID studentIDToBeFound = mock(StudentID.class);
            StudentRepository studentRepository = new StudentRepository(_studentFactoryImplDouble, _studentListFactoryImplDouble);
            // Register Second Student
            when(_studentFactoryImplDouble.newStudent(_studentID1, _name, _nif, _phone, _email, _streetDouble, _postalCodeDouble, _locationDouble, _countryDouble, _academicEmail)).thenReturn(_studentDouble1);
            when(_studentFactoryImplDouble.newStudent(studentIDToBeFound, _name, _nif, _phone, _email, _streetDouble, _postalCodeDouble, _locationDouble, _countryDouble, _academicEmail)).thenReturn(_studentDouble2);
            when(_iterator.hasNext()).thenReturn(false, true, false);
            when(_iterator.next()).thenReturn(_studentDouble1, _studentDouble2);

            studentRepository.registerStudent(_studentID1, _name, _nif, _phone, _email, _streetDouble, _postalCodeDouble, _locationDouble, _countryDouble, _academicEmail);

            NIF nifDouble = mock(NIF.class);

            when(_studentDouble1.isEquals(_studentDouble2)).thenReturn(false);
            when(_studentDouble1.sameAs(_studentDouble2)).thenReturn(true);
            // Register Second Student
            studentRepository.registerStudent(studentIDToBeFound, _name, nifDouble, _phone, _email, _streetDouble, _postalCodeDouble, _locationDouble, _countryDouble, _academicEmail);

            when(_iterator.hasNext()).thenReturn(true, true);
            when(_iterator.next()).thenReturn(_studentDouble1, _studentDouble2);

            when(_studentDouble1.identity()).thenReturn(_studentID1);
            when(_studentDouble2.identity()).thenReturn(studentIDToBeFound);

            when(_studentID1.isEquals(studentIDToBeFound)).thenReturn(false);
            when(studentIDToBeFound.isEquals(studentIDToBeFound)).thenReturn(true);

            // Act
            Optional<Student> studentFound = studentRepository.getStudentByID(studentIDToBeFound);

            // Assert
            assertEquals(_studentDouble2, studentFound.get());
        }

        @Test
        void shouldReturnOptionalWithoutStudentIfStudentWithSpecificNIFIsNotFound() throws Exception {
            // Arrange
            StudentID studentIDToBeFound = mock(StudentID.class);
            StudentRepository studentRepository = new StudentRepository(_studentFactoryImplDouble, _studentListFactoryImplDouble);
            when(_studentFactoryImplDouble.newStudent(_studentID1, _name, _nif, _phone, _email, _streetDouble, _postalCodeDouble, _locationDouble, _countryDouble, _academicEmail)).thenReturn(_studentDouble1);
            when(_studentFactoryImplDouble.newStudent(_studentID2, _name, _nif, _phone, _email, _streetDouble, _postalCodeDouble, _locationDouble, _countryDouble, _academicEmail)).thenReturn(_studentDouble2);

            when(_iterator.hasNext()).thenReturn(false, true, false);
            when(_iterator.next()).thenReturn(_studentDouble1, _studentDouble2);

            studentRepository.registerStudent(_studentID1, _name, _nif, _phone, _email, _streetDouble, _postalCodeDouble, _locationDouble, _countryDouble, _academicEmail);

            when(_studentDouble1.equals(_studentDouble2)).thenReturn(false);
            when(_studentDouble1.sameAs(_studentDouble2)).thenReturn(false);

            studentRepository.registerStudent(_studentID2, _name, _nif, _phone, _email, _streetDouble, _postalCodeDouble, _locationDouble, _countryDouble, _academicEmail);

            when(_iterator.hasNext()).thenReturn(true, true, false);
            when(_iterator.next()).thenReturn(_studentDouble1, _studentDouble2);

            when(_studentDouble1.identity()).thenReturn(_studentID1);
            when(_studentDouble2.identity()).thenReturn(_studentID2);

            when(_studentID1.isEquals(studentIDToBeFound)).thenReturn(false);
            when(_studentID2.isEquals(studentIDToBeFound)).thenReturn(false);

            // Act
            Optional<Student> studentNotFound = studentRepository.getStudentByID(studentIDToBeFound);

            // Assert
            assertTrue(studentNotFound.isEmpty());
        }
    }

    @Test
    void shouldReturnIdWhenStudentExistsInList() throws Exception {
        // Arrange
        IStudentFactory studentFactory = mock(IStudentFactory.class);
        IStudentListFactory studentListFactory = mock(IStudentListFactory.class);
        List<Student> studentList = new ArrayList<>();
        when(studentListFactory.newArrayList()).thenReturn(studentList);

        StudentRepository repository = new StudentRepository(studentFactory, studentListFactory);

        UniqueNumber uniqueNumberDouble= mock(UniqueNumber.class);
        NIF nifDouble = mock(NIF.class);
        StudentID studentID = new StudentID(uniqueNumberDouble, nifDouble);

        Student student = mock(Student.class);
        repository.save(student);
        when(student.identity()).thenReturn(studentID);

        when(student.sameAs(any())).thenReturn(true);



        // Act
        Optional<StudentID> result = repository.findIdByStudent(student);

        // Assert
        assertTrue(result.isPresent());
    }


    @Test
    void shouldReturnEmptyWhenStudentDoesNotExistsInList() throws Exception {
        // Arrange
        IStudentFactory studentFactory = mock(IStudentFactory.class);
        IStudentListFactory studentListFactory = mock(IStudentListFactory.class);
        List<Student> studentList = new ArrayList<>();
        when(studentListFactory.newArrayList()).thenReturn(studentList);

        StudentRepository repository = new StudentRepository(studentFactory, studentListFactory);

        UniqueNumber uniqueNumberDouble= mock(UniqueNumber.class);
        NIF nifDouble = mock(NIF.class);
        StudentID studentID = new StudentID(uniqueNumberDouble, nifDouble);

        Student student = mock(Student.class);
        when(student.identity()).thenReturn(studentID);
        when(student.isEquals(any())).thenReturn(false);
        when(student.sameAs(any())).thenReturn(false);

        // Act
        Optional<StudentID> result = repository.findIdByStudent(student);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void saveMethodShouldSaveInTheStudentList () {
        // Arrange
        IStudentFactory iStudentFactory = mock(IStudentFactory.class);
        IStudentListFactory iStudentListFactory = mock(IStudentListFactory.class);
        StudentRepository studentRepository = new StudentRepository(iStudentFactory, iStudentListFactory);

        Student studentDouble = mock(Student.class);

        // Act
        Student result = studentRepository.save(studentDouble);

        // Assert
        assertEquals(studentDouble, result);
    }

    @Test
    void shouldReturnListOfStudentsWhenItIsNotEmpty () {
        // Arrange
        IStudentFactory iStudentFactory = mock(IStudentFactory.class);
        IStudentListFactory iStudentListFactory = mock(IStudentListFactory.class);
        StudentRepository studentRepository = new StudentRepository(iStudentFactory, iStudentListFactory);

        Student studentDouble = mock(Student.class);
        studentRepository.save(studentDouble);

        // Act
        Iterable<Student> result = studentRepository.findAll();

        // Assert
        assertIterableEquals(List.of(studentDouble), result);
    }

    @Test
    void shouldReturnEmptyListOfStudentsWhenItIsEmpty () {
        //Arrange
        IStudentFactory iStudentFactory = mock(IStudentFactory.class);
        IStudentListFactory iStudentListFactory = mock(IStudentListFactory.class);
        StudentRepository studentRepository = new StudentRepository(iStudentFactory, iStudentListFactory);

        //Act + Assert
        Exception exception = assertThrows(Exception.class, () -> studentRepository.findAll());
    }

    @Test
    public void shouldReturnOptionalPresentWhenTCPExists() {
        //Arrange
        IStudentFactory iStudentFactory = mock(IStudentFactory.class);
        IStudentListFactory iStudentListFactory = mock(IStudentListFactory.class);

        StudentRepository studentRepository = new StudentRepository(iStudentFactory, iStudentListFactory);

        Student studentDouble = mock(Student.class);
        StudentID studentIDDouble = mock(StudentID.class);


        //Act
        Optional<Student> result = studentRepository.ofIdentity(studentIDDouble);

        //Assert
        assertFalse(result.isPresent());
    }

    @Test
    public void shouldReturnOptionalEmptyWhenTCPExists() {
        //Arrange
        IStudentFactory iStudentFactory = mock(IStudentFactory.class);
        IStudentListFactory iStudentListFactory = mock(IStudentListFactory.class);

        StudentRepository studentRepository = new StudentRepository(iStudentFactory, iStudentListFactory);

        StudentID studentIDDouble = mock(StudentID.class);

        //Act
        Optional<Student> result = studentRepository.ofIdentity(studentIDDouble);

        //Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnTrueIfContainsIdentity () {
        // Arrange
        IStudentFactory iStudentFactory = mock(IStudentFactory.class);
        IStudentListFactory iStudentListFactory = mock(IStudentListFactory.class);


        Student studentDouble = mock(Student.class);
        StudentID studentID = mock(StudentID.class);

        StudentRepository studentRepository = new StudentRepository(iStudentFactory, iStudentListFactory);
        studentRepository.save(studentDouble);

        when(studentDouble.identity()).thenReturn(studentID);

        // Act
        boolean result = studentRepository.containsOfIdentity(studentID);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfContainsIdentity () {
        // Arrange
        IStudentFactory iStudentFactory = mock(IStudentFactory.class);
        IStudentListFactory iStudentListFactory = mock(IStudentListFactory.class);

        StudentID studentID = mock(StudentID.class);

        StudentRepository studentRepository = new StudentRepository(iStudentFactory, iStudentListFactory);

        // Act
        boolean result = studentRepository.containsOfIdentity(studentID);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldNotReturnIdWhenTeacherDoesntExistsInList() throws Exception {
        // Arrange
        IStudentFactory iStudentFactory = mock(IStudentFactory.class);
        IStudentListFactory iStudentListFactory = mock(IStudentListFactory.class);
        List<Student> studentList = new ArrayList<>();
        when(iStudentListFactory.newArrayList()).thenReturn(studentList);

        StudentRepository repository = new StudentRepository(iStudentFactory, iStudentListFactory);

        StudentID studentID1 = mock(StudentID.class);
        Name name = mock(Name.class);
        NIF nif = mock(NIF.class);
        PhoneNumber phone = mock(PhoneNumber.class);
        Email email = mock(Email.class);
        Student student = mock(Student.class);
        Street street = mock(Street.class);
        PostalCode postalCode = mock(PostalCode.class);
        Location location = mock(Location.class);
        Country country = mock(Country.class);
        StudentAcademicEmail studentAcademicEmail = mock(StudentAcademicEmail.class);
        when(student.identity()).thenReturn(studentID1);


        when(iStudentFactory.newStudent(studentID1, name, nif, phone, email, street, postalCode, location, country, studentAcademicEmail)).thenReturn(student);
        when(student.sameAs(any())).thenReturn(false);

        // Act
        repository.registerStudent(studentID1, name, nif, phone, email, street, postalCode, location, country, studentAcademicEmail);
        Optional<StudentID> result = repository.findIdByStudent(student);

        // Assert
        assertTrue(result.isEmpty());
    }
}