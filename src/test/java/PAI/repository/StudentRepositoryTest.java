package PAI.repository;

import PAI.VOs.*;
import PAI.domain.Address;
import PAI.domain.CourseEdition;
import PAI.domain.Student;
import PAI.domain.StudentGrade;
import PAI.factory.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;


import java.util.ArrayList;
import java.util.Iterator;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

class StudentRepositoryTest {

    @Nested
    class TestsWithIsolation {
        private IStudentFactory _studentFactoryImplDouble;
        private IStudentListFactory _studentListFactoryImplDouble;
        private Iterator _iterator;
        private Address _addressDouble;
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

            _addressDouble = mock(Address.class);
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

            when(_studentFactoryImplDouble.newStudent(_studentID1, _name, _nif, _phone, _email, _addressDouble, _academicEmail)).thenReturn(_studentDouble1);
            when(_studentFactoryImplDouble.newStudent(_studentID1, _name, _nif, _phone, _email, _addressDouble, _academicEmail)).thenReturn(_studentDouble2);

            when(_iterator.hasNext()).thenReturn(false, true, false);
            when(_iterator.next()).thenReturn(_studentDouble1);

            when(_studentDouble1.sameAs(_studentDouble2)).thenReturn(true);

            studentRepository.registerStudent(_studentID1, _name, _nif, _phone, _email, _addressDouble, _academicEmail);

            // Act & Assert
            Exception exception = assertThrows(Exception.class, () -> {
                studentRepository.registerStudent(_studentID1, _name, _nif, _phone, _email, _addressDouble, _academicEmail);
            });
            assertEquals(exception.getMessage(), "Duplicate ID or NIF detected. Student cannot be added.");
        }

        @Test
        void testRegisterDuplicateStudentIDThrowsException() throws Exception {
            // Arrange
            StudentRepository studentRepository = new StudentRepository(_studentFactoryImplDouble, _studentListFactoryImplDouble);
            when(_studentFactoryImplDouble.newStudent(_studentID1, _name, _nif, _phone, _email, _addressDouble, _academicEmail)).thenReturn(_studentDouble1);
            when(_studentFactoryImplDouble.newStudent(_studentID1, _name, _nif, _phone, _email, _addressDouble, _academicEmail)).thenReturn(_studentDouble2);

            when(_iterator.hasNext()).thenReturn(false);

            studentRepository.registerStudent(_studentID1, _name, _nif, _phone, _email, _addressDouble, _academicEmail);

            when(_iterator.hasNext()).thenReturn(true, false);
            when(_iterator.next()).thenReturn(_studentDouble1);

            when(_studentDouble1.isEquals(_studentDouble2)).thenReturn(true);

            // Act & Assert
            Exception exception = assertThrows(Exception.class, () -> {
                studentRepository.registerStudent(_studentID1, _name, _nif, _phone, _email, _addressDouble, _academicEmail);
            });
            assertEquals("Duplicate ID or NIF detected. Student cannot be added.", exception.getMessage());
        }

        @Test
        void shouldReturnTrueWhenValidStudentsAreRegistered() throws Exception {
            // Arrange
            StudentRepository studentRepository = new StudentRepository(_studentFactoryImplDouble, _studentListFactoryImplDouble);
            when(_studentFactoryImplDouble.newStudent(_studentID1, _name, _nif, _phone, _email, _addressDouble, _academicEmail)).thenReturn(_studentDouble1);
            when(_studentFactoryImplDouble.newStudent(_studentID2, _name, _nif, _phone, _email, _addressDouble, _academicEmail)).thenReturn(_studentDouble2);
            when(_iterator.hasNext()).thenReturn(false, true, false);
            when(_iterator.next()).thenReturn(_studentDouble1);
            when(_studentDouble2.isEquals(_studentDouble1) && _studentDouble2.sameAs(_studentDouble1)).thenReturn(false);

            studentRepository.registerStudent(_studentID1, _name, _nif, _phone, _email, _addressDouble, _academicEmail);

            // Act
            boolean result = studentRepository.registerStudent(_studentID2, _name, _nif, _phone, _email, _addressDouble, _academicEmail);

            // Assert
            assertTrue(result);
        }

        @Test
        void shouldReturnOptionalWithStudentIfStudentWithSpecificNIFIsFound() throws Exception {
            // Arrange
            StudentID studentIDToBeFound = mock(StudentID.class);
            StudentRepository studentRepository = new StudentRepository(_studentFactoryImplDouble, _studentListFactoryImplDouble);
            // Register Second Student
            when(_studentFactoryImplDouble.newStudent(_studentID1, _name, _nif, _phone, _email, _addressDouble, _academicEmail)).thenReturn(_studentDouble1);
            when(_studentFactoryImplDouble.newStudent(studentIDToBeFound, _name, _nif, _phone, _email, _addressDouble, _academicEmail)).thenReturn(_studentDouble2);
            when(_iterator.hasNext()).thenReturn(false, true, false);
            when(_iterator.next()).thenReturn(_studentDouble1, _studentDouble2);

            studentRepository.registerStudent(_studentID1, _name, _nif, _phone, _email, _addressDouble, _academicEmail);

            NIF nifDouble = mock(NIF.class);

            when(_studentDouble1.isEquals(_studentDouble2)).thenReturn(false);
            when(_studentDouble1.sameAs(_studentDouble2)).thenReturn(true);
            // Register Second Student
            studentRepository.registerStudent(studentIDToBeFound, _name, nifDouble, _phone, _email, _addressDouble, _academicEmail);

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
            when(_studentFactoryImplDouble.newStudent(_studentID1, _name, _nif, _phone, _email, _addressDouble, _academicEmail)).thenReturn(_studentDouble1);
            when(_studentFactoryImplDouble.newStudent(_studentID2, _name, _nif, _phone, _email, _addressDouble, _academicEmail)).thenReturn(_studentDouble2);

            when(_iterator.hasNext()).thenReturn(false, true, false);
            when(_iterator.next()).thenReturn(_studentDouble1, _studentDouble2);

            studentRepository.registerStudent(_studentID1, _name, _nif, _phone, _email, _addressDouble, _academicEmail);

            when(_studentDouble1.equals(_studentDouble2)).thenReturn(false);
            when(_studentDouble1.sameAs(_studentDouble2)).thenReturn(false);

            studentRepository.registerStudent(_studentID2, _name, _nif, _phone, _email, _addressDouble, _academicEmail);

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

    @Nested
    class TestsWithoutIsolation {

        private StudentFactoryImpl _studentFactoryImpl;
        private StudentListFactoryImpl _studentListFactoryImpl;
        private Address _address1;
        private Address _address2;

        @BeforeEach
        //arrange
        void setup() throws Exception{
            _studentFactoryImpl = new StudentFactoryImpl();
            _studentListFactoryImpl = new StudentListFactoryImpl();
            _address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
            _address2 = new Address("Rua das Flores, nº7", "3000-200", "Coimbra", "Portugal");
        }

        @Test
        void shouldCreateRepositoryWithValidInputs() {
            //arrange

            //act
            new StudentRepository(_studentFactoryImpl, _studentListFactoryImpl);
        }

        @Test
        void shouldThrowExceptionWhenStudentWithDuplicateNIFIsRegistered() throws Exception {
            // Arrange
            StudentRepository repository = new StudentRepository(_studentFactoryImpl, _studentListFactoryImpl);

            StudentID studentID1 = new StudentID(1234567);
            StudentID studentID2 = new StudentID(1789023);

            Name name = mock(Name.class);
            NIF nif = mock(NIF.class);
            PhoneNumber phone = mock(PhoneNumber.class);
            Email email = mock(Email.class);
            StudentAcademicEmail academicEmail = mock(StudentAcademicEmail.class);

            // Act
            repository.registerStudent(studentID1, name, nif, phone, email, _address1, academicEmail);

            // Assert
            assertThrows(Exception.class, () -> {
                repository.registerStudent(studentID2, name, nif, phone, email, _address2, academicEmail);
            });
        }

        @Test
        void shouldThrowExceptionWhenStudentWithDuplicateIDIsRegistered() throws Exception {
            // Arrange
            StudentRepository repository = new StudentRepository(_studentFactoryImpl, _studentListFactoryImpl);

            StudentID studentID1 = new StudentID(1234567);
            Name name = mock(Name.class);
            NIF nif = mock(NIF.class);
            PhoneNumber phone = mock(PhoneNumber.class);
            Email email = mock(Email.class);
            StudentAcademicEmail academicEmail = mock(StudentAcademicEmail.class);

            // Act
            repository.registerStudent(studentID1, name, nif, phone, email, _address1, academicEmail);

            // Assert
            assertThrows(Exception.class, () -> {
                repository.registerStudent(studentID1, name, nif, phone, email, _address2, academicEmail);
            });
        }

        @Test
        void shouldReturnTrueWhenStudentsWithValidAttributesAreRegistered() throws Exception {
            // Arrange
            StudentRepository repository = new StudentRepository(_studentFactoryImpl, _studentListFactoryImpl);

            Name name = mock(Name.class);
            PhoneNumber phone = mock(PhoneNumber.class);
            Email email = mock(Email.class);
            StudentAcademicEmail academicEmail = mock(StudentAcademicEmail.class);

            StudentID studentID1 = new StudentID(1234567);
            StudentID studentID2 = new StudentID(1789023);
            StudentID studentID3 = new StudentID(1122332);

            NIF nif1 = mock(NIF.class);
            NIF nif2 = mock(NIF.class);
            NIF nif3 = mock(NIF.class);

            // Act
            boolean result1 = repository.registerStudent(studentID1, name, nif1, phone, email, _address1, academicEmail);
            boolean result2 = repository.registerStudent(studentID2, name, nif2, phone, email, _address2, academicEmail);
            boolean result3 = repository.registerStudent(studentID3, name, nif3, phone, email, _address1, academicEmail);

            // Assert
            assertTrue(result1 && result2 && result3);
        }

        @Test
        void shouldReturnEmptyOptionalIfStudentIDExistsInTheRepository() throws Exception {
            // Arrange
            StudentRepository repository = new StudentRepository(_studentFactoryImpl, _studentListFactoryImpl);

            Name name = mock(Name.class);
            PhoneNumber phone = mock(PhoneNumber.class);
            Email email = mock(Email.class);
            StudentAcademicEmail academicEmail = mock(StudentAcademicEmail.class);

            StudentID studentID1 = new StudentID(1234567);
            StudentID studentID2 = new StudentID(1789023);

            NIF nif1 = mock(NIF.class);
            NIF nif2 = mock(NIF.class);

            repository.registerStudent(studentID1, name, nif1, phone, email, _address1, academicEmail);
            repository.registerStudent(studentID2, name, nif2, phone, email, _address2, academicEmail);

            // Act
            Optional<Student> studentFromList = repository.getStudentByID(studentID2);

            // Assert
            assertTrue(studentFromList.isPresent());
        }

        @Test
        void shouldReturnEmptyOptionalIfStudentIDDoesNotExistInTheRepository() throws Exception {
            // Arrange
            StudentRepository repository = new StudentRepository(_studentFactoryImpl, _studentListFactoryImpl);

            Name name = mock(Name.class);
            NIF nif = mock(NIF.class);
            NIF nif2 = mock(NIF.class);
            PhoneNumber phone = mock(PhoneNumber.class);
            Email email = mock(Email.class);
            StudentAcademicEmail academicEmail = mock(StudentAcademicEmail.class);

            StudentID studentID1 = new StudentID(1234567);
            StudentID studentID2 = new StudentID(1789023);

            repository.registerStudent(studentID1, name, nif, phone, email, _address1, academicEmail);
            repository.registerStudent(studentID2, name, nif2, phone, email, _address2, academicEmail);

            // Act
            Optional<Student> studentFromList = repository.getStudentByID(new StudentID(1555555));

            // Assert
            assertTrue(studentFromList.isEmpty());
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

        StudentID studentID1 = new StudentID(1234567);
        Name name = mock(Name.class);
        NIF nif = mock(NIF.class);
        PhoneNumber phone = mock(PhoneNumber.class);
        Email email = mock(Email.class);
        StudentAcademicEmail academicEmail = mock(StudentAcademicEmail.class);
        Address address = mock(Address.class);


        Student student = mock(Student.class);
        when(student.identity()).thenReturn(studentID1);
        when(student.isEquals(any())).thenReturn(false);
        when(student.sameAs(any())).thenReturn(false);


        when(studentFactory.newStudent(studentID1, name, nif, phone, email, address, academicEmail)).thenReturn(student);

        // Act
        repository.registerStudent(studentID1, name, nif, phone, email, address, academicEmail);
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

        StudentID studentID1 = new StudentID(1234567);

        Student student = mock(Student.class);
        when(student.identity()).thenReturn(studentID1);
        when(student.isEquals(any())).thenReturn(false);
        when(student.sameAs(any())).thenReturn(false);

        // Act
        Optional<StudentID> result = repository.findIdByStudent(student);

        // Assert
        assertTrue(result.isEmpty());
    }


}