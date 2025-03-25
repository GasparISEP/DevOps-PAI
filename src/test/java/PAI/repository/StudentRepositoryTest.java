package PAI.repository;

import PAI.domain.Address;
import PAI.domain.Student;
import PAI.factory.IStudentFactory;
import PAI.factory.StudentFactoryImpl;
import PAI.factory.IStudentListFactory;
import PAI.factory.StudentListFactoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;


import java.util.ArrayList;
import java.util.Iterator;

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

        @BeforeEach
        //arrange
        void setup() {
            _studentFactoryImplDouble = mock(IStudentFactory.class);
            _studentListFactoryImplDouble = mock(IStudentListFactory.class);

            _addressDouble = mock(Address.class);
            _studentDouble1 = mock(Student.class);
            _studentDouble2 = mock(Student.class);

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

            when(_studentFactoryImplDouble.newStudent("1234567", "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble)).thenReturn(_studentDouble1);
            when(_studentFactoryImplDouble.newStudent("1789023", "Miguel", "123456789", "912345678", "miguel@gmail.com", _addressDouble)).thenReturn(_studentDouble2);
            when(_iterator.hasNext()).thenReturn(false, true, false);
            when(_iterator.next()).thenReturn(_studentDouble1);
            when(_studentDouble1.hasSameNIF(_studentDouble2)).thenReturn(true);

            studentRepository.registerStudent("1234567", "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble);

            // Act & Assert
            Exception exception = assertThrows(Exception.class, () -> {
                studentRepository.registerStudent("1789023", "Miguel", "123456789", "912345678", "miguel@gmail.com", _addressDouble);
            });
            assertEquals(exception.getMessage(), "Duplicate unique number or NIF detected. Student cannot be added.");
        }

        @Test
        void testRegisterDuplicateUniqueNumberThrowsException() throws Exception {
            // Arrange
            StudentRepository studentRepository = new StudentRepository(_studentFactoryImplDouble, _studentListFactoryImplDouble);
            when(_studentFactoryImplDouble.newStudent("1234567", "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble)).thenReturn(_studentDouble1);
            when(_studentFactoryImplDouble.newStudent("1234567", "Miguel", "132489912", "912345678", "miguel@gmail.com", _addressDouble)).thenReturn(_studentDouble2);
            when(_iterator.hasNext()).thenReturn(false, true, false);
            when(_iterator.next()).thenReturn(_studentDouble1);
            when(_studentDouble1.hasSameUniqueNumber(_studentDouble2)).thenReturn(true);

            studentRepository.registerStudent("1234567", "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble);

            // Act & Assert
            Exception exception = assertThrows(Exception.class, () -> {
                studentRepository.registerStudent("1234567", "Miguel", "132489912", "912345678", "miguel@gmail.com", _addressDouble);
            });
            assertEquals(exception.getMessage(), "Duplicate unique number or NIF detected. Student cannot be added.");
        }

        @Test
        void shouldReturnTrueWhenValidStudentsAreRegistered() throws Exception {
            // Arrange
            StudentRepository studentRepository = new StudentRepository(_studentFactoryImplDouble, _studentListFactoryImplDouble);
            when(_studentFactoryImplDouble.newStudent("1234567", "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble)).thenReturn(_studentDouble1);
            when(_studentFactoryImplDouble.newStudent("1789023", "Miguel", "132489912", "912345678", "miguel@gmail.com", _addressDouble)).thenReturn(_studentDouble2);
            when(_iterator.hasNext()).thenReturn(false, true, false);
            when(_iterator.next()).thenReturn(_studentDouble1);
            when(_studentDouble2.hasSameUniqueNumber(_studentDouble1) && _studentDouble2.hasSameNIF(_studentDouble1)).thenReturn(false);

            studentRepository.registerStudent("1234567", "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble);

            // Act
            boolean result = studentRepository.registerStudent("1789023", "Miguel", "132489912", "912345678", "miguel@gmail.com", _addressDouble);

            // Assert
            assertTrue(result);
        }

        @Test
        void shouldReturnOptionalWithStudentIfStudentWithSpecificNIFIsFound() throws Exception {
            // Arrange
            String uniqueNumberToBeFound = "1789077";
            StudentRepository studentRepository = new StudentRepository(_studentFactoryImplDouble, _studentListFactoryImplDouble);
            when(_studentFactoryImplDouble.newStudent("1234567", "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble)).thenReturn(_studentDouble1);
            when(_studentFactoryImplDouble.newStudent(uniqueNumberToBeFound, "Miguel", "132489912", "912345678", "miguel@gmail.com", _addressDouble)).thenReturn(_studentDouble2);
            when(_iterator.hasNext()).thenReturn(false, true, false);
            when(_iterator.next()).thenReturn(_studentDouble1, _studentDouble2);

            studentRepository.registerStudent("1234567", "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble);
            studentRepository.registerStudent(uniqueNumberToBeFound, "Miguel", "132489912", "912345678", "miguel@gmail.com", _addressDouble);

            when(_studentDouble1.hasThisUniqueNumber(uniqueNumberToBeFound)).thenReturn(false);
            when(_studentDouble2.hasThisUniqueNumber(uniqueNumberToBeFound)).thenReturn(true);

            when(_iterator.hasNext()).thenReturn(true, true);
            when(_iterator.next()).thenReturn(_studentDouble1, _studentDouble2);

            // Act
            Optional<Student> studentFound = studentRepository.getStudentByUniqueNumber(uniqueNumberToBeFound);

            // Assert
            assertEquals(studentFound.get(), _studentDouble2);
        }

        @Test
        void shouldReturnOptionalWithoutStudentIfStudentWithSpecificNIFIsNotFound() throws Exception {
            // Arrange
            String uniqueNumberToBeFound = "1234534";
            StudentRepository studentRepository = new StudentRepository(_studentFactoryImplDouble, _studentListFactoryImplDouble);
            when(_studentFactoryImplDouble.newStudent("1234567", "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble)).thenReturn(_studentDouble1);
            when(_studentFactoryImplDouble.newStudent("1789023", "Miguel", "132489912", "912345678", "miguel@gmail.com", _addressDouble)).thenReturn(_studentDouble2);
            when(_iterator.hasNext()).thenReturn(false, true, false);
            when(_iterator.next()).thenReturn(_studentDouble1, _studentDouble2);

            studentRepository.registerStudent("1234567", "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble);
            studentRepository.registerStudent("1789023", "Miguel", "132489912", "912345678", "miguel@gmail.com", _addressDouble);

            when(_studentDouble1.hasThisUniqueNumber(uniqueNumberToBeFound) || _studentDouble2.hasThisUniqueNumber(uniqueNumberToBeFound)).thenReturn(false);

            when(_studentDouble1.hasThisUniqueNumber(uniqueNumberToBeFound)).thenReturn(false);
            when(_studentDouble2.hasThisUniqueNumber(uniqueNumberToBeFound)).thenReturn(false);

            when(_iterator.hasNext()).thenReturn(true, true, false);
            when(_iterator.next()).thenReturn(_studentDouble1, _studentDouble2);

            // Act
            Optional<Student> studentNotFound = studentRepository.getStudentByUniqueNumber(uniqueNumberToBeFound);

            // Assert
            assertTrue(studentNotFound.isEmpty());
        }
    }
    //
    //

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

            // Act
            repository.registerStudent("1234567", "Daniela", "123456789", "911855911", "danijose@gmail.com", _address1);

            // Assert
            assertThrows(Exception.class, () -> {
                repository.registerStudent("1789023", "Miguel", "123456789", "912345678", "miguel@gmail.com", _address2);
            });
        }

        @Test
        void shouldThrowExceptionWhenStudentWithDuplicateUniqueNumberIsRegistered() throws Exception {
            // Arrange
            StudentRepository repository = new StudentRepository(_studentFactoryImpl, _studentListFactoryImpl);

            // Act
            repository.registerStudent("1234567", "Daniela", "123456789", "911855911", "danijose@gmail.com", _address1);

            // Assert
            assertThrows(Exception.class, () -> {
                repository.registerStudent("1234567", "Miguel", "987654321", "912345678", "miguel@gmail.com", _address2);
            });
        }

        @Test
        void shouldReturnTrueWhenStudentsWithValidAttributesAreRegistered() throws Exception {
            // Arrange
            StudentRepository repository = new StudentRepository(_studentFactoryImpl, _studentListFactoryImpl);

            // Act
            boolean result1 = repository.registerStudent("1234567", "Daniela", "123456789", "911855911", "danijose@gmail.com", _address1);
            boolean result2 = repository.registerStudent("1789023", "Miguel", "987654321", "912345678", "miguel@gmail.com", _address2);
            boolean result3 = repository.registerStudent("1122332", "Paula", "456789123", "910000000", "paula@gmail.com", _address1);

            // Assert
            assertTrue(result1 && result2 && result3);
        }

        @Test
        void shouldReturnEmptyOptionalIfUniqueNumberDoesntExistInTheRepository() throws Exception {
            // Arrange
            StudentRepository repository = new StudentRepository(_studentFactoryImpl, _studentListFactoryImpl);
            repository.registerStudent("1234567", "Daniela", "123456789", "911855911", "danijose@gmail.com", _address1);
            repository.registerStudent("1789023", "Miguel", "987654321", "912345678", "miguel@gmail.com", _address2);

            // Act
            Optional<Student> studentFromList = repository.getStudentByUniqueNumber("1789023");

            // Assert
            assertTrue(studentFromList.isPresent());
        }

        @Test
        void shouldReturnStudentOptionalIfUniqueNumberExistsInTheRepository() throws Exception {
            // Arrange
            StudentRepository repository = new StudentRepository(_studentFactoryImpl, _studentListFactoryImpl);
            repository.registerStudent("1234567", "Daniela", "123456789", "911855911", "danijose@gmail.com", _address1);
            repository.registerStudent("1789023", "Miguel", "987654321", "912345678", "miguel@gmail.com", _address2);

            // Act
            Optional<Student> studentFromList = repository.getStudentByUniqueNumber("1555555");

            // Assert
            assertTrue(studentFromList.isEmpty());
        }
    }
}