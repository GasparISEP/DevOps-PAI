package PAI.repository;

import PAI.VOs.Date;
import PAI.VOs.Grade;
import PAI.VOs.StudentGradeID;
import PAI.VOs.StudentID;
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

            when(_studentFactoryImplDouble.newStudent(_studentID1, "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble)).thenReturn(_studentDouble1);
            when(_studentFactoryImplDouble.newStudent(_studentID1, "Miguel", "123456789", "912345678", "miguel@gmail.com", _addressDouble)).thenReturn(_studentDouble2);
            when(_iterator.hasNext()).thenReturn(false, true, false);
            when(_iterator.next()).thenReturn(_studentDouble1);
            when(_studentDouble1.sameAs(_studentDouble2)).thenReturn(true);

            studentRepository.registerStudent(_studentID1, "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble);

            // Act & Assert
            Exception exception = assertThrows(Exception.class, () -> {
                studentRepository.registerStudent(_studentID1, "Miguel", "123456789", "912345678", "miguel@gmail.com", _addressDouble);
            });
            assertEquals(exception.getMessage(), "Duplicate ID or NIF detected. Student cannot be added.");
        }

        @Test
        void testRegisterDuplicateStudentIDThrowsException() throws Exception {
            // Arrange
            StudentRepository studentRepository = new StudentRepository(_studentFactoryImplDouble, _studentListFactoryImplDouble);
            when(_studentFactoryImplDouble.newStudent(_studentID1, "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble)).thenReturn(_studentDouble1);
            when(_studentFactoryImplDouble.newStudent(_studentID1, "Miguel", "132489912", "912345678", "miguel@gmail.com", _addressDouble)).thenReturn(_studentDouble2);

            when(_iterator.hasNext()).thenReturn(false);

            studentRepository.registerStudent(_studentID1, "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble);

            when(_iterator.hasNext()).thenReturn(true, false);
            when(_iterator.next()).thenReturn(_studentDouble1);

            when(_studentDouble1.isEquals(_studentDouble2)).thenReturn(true);

            // Act & Assert
            Exception exception = assertThrows(Exception.class, () -> {
                studentRepository.registerStudent(_studentID1, "Miguel", "132489912", "912345678", "miguel@gmail.com", _addressDouble);
            });
            assertEquals("Duplicate ID or NIF detected. Student cannot be added.", exception.getMessage());
        }

        @Test
        void shouldReturnTrueWhenValidStudentsAreRegistered() throws Exception {
            // Arrange
            StudentRepository studentRepository = new StudentRepository(_studentFactoryImplDouble, _studentListFactoryImplDouble);
            when(_studentFactoryImplDouble.newStudent(_studentID1, "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble)).thenReturn(_studentDouble1);
            when(_studentFactoryImplDouble.newStudent(_studentID2, "Miguel", "132489912", "912345678", "miguel@gmail.com", _addressDouble)).thenReturn(_studentDouble2);
            when(_iterator.hasNext()).thenReturn(false, true, false);
            when(_iterator.next()).thenReturn(_studentDouble1);
            when(_studentDouble2.isEquals(_studentDouble1) && _studentDouble2.sameAs(_studentDouble1)).thenReturn(false);

            studentRepository.registerStudent(_studentID1, "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble);

            // Act
            boolean result = studentRepository.registerStudent(_studentID2, "Miguel", "132489912", "912345678", "miguel@gmail.com", _addressDouble);

            // Assert
            assertTrue(result);
        }

//        @Test
//        void shouldReturnOptionalWithStudentIfStudentWithSpecificNIFIsFound() throws Exception {
//            // Arrange
//            StudentID studentIDToBeFound = mock(StudentID.class);
//            StudentRepository studentRepository = new StudentRepository(_studentFactoryImplDouble, _studentListFactoryImplDouble);
//
//            when(_studentFactoryImplDouble.newStudent(_studentID1, "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble)).thenReturn(_studentDouble1);
//            when(_studentFactoryImplDouble.newStudent(studentIDToBeFound, "Miguel", "132489912", "912345678", "miguel@gmail.com", _addressDouble)).thenReturn(_studentDouble2);
//            when(_iterator.hasNext()).thenReturn(false, true, false);
//            when(_iterator.next()).thenReturn(_studentDouble1, _studentDouble2);
//
//            studentRepository.registerStudent(_studentID1, "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble);
//
//            when(_studentDouble1.isEquals(_studentDouble2)).thenReturn(false);
//            when(_studentDouble1.sameAs(_studentDouble2)).thenReturn(true);
//
//            studentRepository.registerStudent(studentIDToBeFound, "Miguel", "132489912", "912345678", "miguel@gmail.com", _addressDouble);
//
//            when(_iterator.hasNext()).thenReturn(true, true);
//            when(_iterator.next()).thenReturn(_studentDouble1, _studentDouble2);
//
//            when(_studentDouble1.identity()).thenReturn(_studentID1);
//            when(_studentDouble2.identity()).thenReturn(studentIDToBeFound);
//
//            when(_studentID1.isEquals(studentIDToBeFound)).thenReturn(false);
//            when(studentIDToBeFound.isEquals(studentIDToBeFound)).thenReturn(true);
//
//            // Act
//            Optional<Student> studentFound = studentRepository.getStudentByID(studentIDToBeFound);
//
//            // Assert
//            assertEquals(_studentDouble2, studentFound.get());
//        }

        @Test
        void shouldReturnOptionalWithoutStudentIfStudentWithSpecificNIFIsNotFound() throws Exception {
            // Arrange
            StudentID studentIDToBeFound = mock(StudentID.class);
            StudentRepository studentRepository = new StudentRepository(_studentFactoryImplDouble, _studentListFactoryImplDouble);
            when(_studentFactoryImplDouble.newStudent(_studentID1, "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble)).thenReturn(_studentDouble1);
            when(_studentFactoryImplDouble.newStudent(_studentID2, "Miguel", "132489912", "912345678", "miguel@gmail.com", _addressDouble)).thenReturn(_studentDouble2);

            when(_iterator.hasNext()).thenReturn(false, true, false);
            when(_iterator.next()).thenReturn(_studentDouble1, _studentDouble2);

            studentRepository.registerStudent(_studentID1, "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble);

            when(_studentDouble1.equals(_studentDouble2)).thenReturn(false);
            when(_studentDouble1.sameAs(_studentDouble2)).thenReturn(false);

            studentRepository.registerStudent(_studentID2, "Miguel", "132489912", "912345678", "miguel@gmail.com", _addressDouble);

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

            // Act
            repository.registerStudent(studentID1, "Daniela", "123456789", "911855911", "danijose@gmail.com", _address1);

            // Assert
            assertThrows(Exception.class, () -> {
                repository.registerStudent(studentID2, "Miguel", "123456789", "912345678", "miguel@gmail.com", _address2);
            });
        }

        @Test
        void shouldThrowExceptionWhenStudentWithDuplicateIDIsRegistered() throws Exception {
            // Arrange
            StudentRepository repository = new StudentRepository(_studentFactoryImpl, _studentListFactoryImpl);

            StudentID studentID1 = new StudentID(1234567);

            // Act
            repository.registerStudent(studentID1, "Daniela", "123456789", "911855911", "danijose@gmail.com", _address1);

            // Assert
            assertThrows(Exception.class, () -> {
                repository.registerStudent(studentID1, "Miguel", "987654321", "912345678", "miguel@gmail.com", _address2);
            });
        }

        @Test
        void shouldReturnTrueWhenStudentsWithValidAttributesAreRegistered() throws Exception {
            // Arrange
            StudentRepository repository = new StudentRepository(_studentFactoryImpl, _studentListFactoryImpl);

            StudentID studentID1 = new StudentID(1234567);
            StudentID studentID2 = new StudentID(1789023);
            StudentID studentID3 = new StudentID(1122332);

            // Act
            boolean result1 = repository.registerStudent(studentID1, "Daniela", "123456789", "911855911", "danijose@gmail.com", _address1);
            boolean result2 = repository.registerStudent(studentID2, "Miguel", "987654321", "912345678", "miguel@gmail.com", _address2);
            boolean result3 = repository.registerStudent(studentID3, "Paula", "456789123", "910000000", "paula@gmail.com", _address1);

            // Assert
            assertTrue(result1 && result2 && result3);
        }

        @Test
        void shouldReturnEmptyOptionalIfStudentIDDoesntExistInTheRepository() throws Exception {
            // Arrange
            StudentRepository repository = new StudentRepository(_studentFactoryImpl, _studentListFactoryImpl);

            StudentID studentID1 = new StudentID(1234567);
            StudentID studentID2 = new StudentID(1789023);

            repository.registerStudent(studentID1, "Daniela", "123456789", "911855911", "danijose@gmail.com", _address1);
            repository.registerStudent(studentID2, "Miguel", "987654321", "912345678", "miguel@gmail.com", _address2);

            // Act
            Optional<Student> studentFromList = repository.getStudentByID(studentID2);

            // Assert
            assertTrue(studentFromList.isPresent());
        }

        @Test
        void shouldReturnStudentOptionalIfStudentIDExistsInTheRepository() throws Exception {
            // Arrange
            StudentRepository repository = new StudentRepository(_studentFactoryImpl, _studentListFactoryImpl);

            StudentID studentID1 = new StudentID(1234567);
            StudentID studentID2 = new StudentID(1789023);

            repository.registerStudent(studentID1, "Daniela", "123456789", "911855911", "danijose@gmail.com", _address1);
            repository.registerStudent(studentID2, "Miguel", "987654321", "912345678", "miguel@gmail.com", _address2);

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
        String name = "Daniela";
        String nif = "123456789";
        String phone = "911855911";
        String email = "danijose@gmail.com";
        Address address = mock(Address.class);


        Student student = mock(Student.class);
        when(student.identity()).thenReturn(studentID1);
        when(student.isEquals(any())).thenReturn(false);
        when(student.sameAs(any())).thenReturn(false);


        when(studentFactory.newStudent(studentID1, name, nif, phone, email, address)).thenReturn(student);

        // Act
        repository.registerStudent(studentID1, name, nif, phone, email, address);
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
        String name = "Daniela";
        String nif = "123456789";
        String phone = "911855911";
        String email = "danijose@gmail.com";
        Address address = mock(Address.class);


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