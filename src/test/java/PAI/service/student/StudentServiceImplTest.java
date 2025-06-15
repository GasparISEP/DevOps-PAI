package PAI.service.student;

import PAI.VOs.*;
import PAI.domain.student.Student;
import PAI.domain.student.IStudentFactory;
import PAI.domain.repositoryInterfaces.student.IStudentRepository;
import org.apache.commons.lang3.stream.Streams;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentServiceImplTest {

    @Test
    void shouldCreateStudentService() {
        //arrange
        IStudentFactory studentFactoryDouble = mock(IStudentFactory.class);
        IStudentRepository studentRepositoryDouble = mock(IStudentRepository.class);

        //act
        StudentServiceImpl studentServiceImpl = new StudentServiceImpl(studentFactoryDouble, studentRepositoryDouble);

        //assert
        assertNotNull(studentServiceImpl);
    }

    static Stream<Arguments> testNullInputs() {
        return Streams.of(
                Arguments.of(null, mock(IStudentRepository.class), "Student Factory cannot be null!"),
                Arguments.of(mock(IStudentFactory.class), null, "Student Repository cannot be null!")
        );
    }

    @ParameterizedTest
    @MethodSource("testNullInputs")
    void shouldThrowExceptionIfInputsAreNull(IStudentFactory studentFactoryDouble, IStudentRepository studentRepositoryDouble, String expectedMessage) {
        //arrange

        //act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new StudentServiceImpl(studentFactoryDouble, studentRepositoryDouble));

        //assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldRegisterStudentSuccessfully() throws Exception {
        // Arrange
        IStudentFactory studentFactoryDouble = mock(IStudentFactory.class);
        IStudentRepository studentRepositoryDouble = mock(IStudentRepository.class);

        StudentServiceImpl studentServiceImpl = new StudentServiceImpl(studentFactoryDouble, studentRepositoryDouble);

        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        Street streetDouble = mock(Street.class);
        PostalCode postalCodeDouble = mock(PostalCode.class);
        Location locationDouble = mock(Location.class);
        Country countryDouble = mock(Country.class);


        int lastID = 1000002;
        int newID = lastID + 1;
        StudentID expectedStudentID = new StudentID(newID);
        StudentAcademicEmail expectedAcademicEmail = new StudentAcademicEmail(newID);
        Student studentDouble = mock(Student.class);

        when(studentRepositoryDouble.lastStudentID()).thenReturn(lastID);
        when(studentRepositoryDouble.existsByStudentIDOrNIF(expectedStudentID, nifDouble)).thenReturn(false);
        when(studentFactoryDouble.newStudent(expectedStudentID, nameDouble, nifDouble, phoneNumberDouble, emailDouble,
                streetDouble, postalCodeDouble, locationDouble, countryDouble, expectedAcademicEmail)).thenReturn(studentDouble);
        when(studentRepositoryDouble.save(studentDouble)).thenReturn(studentDouble);

        // Act
        Student result = studentServiceImpl.registerStudent(
                nameDouble, nifDouble, phoneNumberDouble, emailDouble,
                streetDouble, postalCodeDouble, locationDouble, countryDouble
        );

        // Assert
        assertEquals(studentDouble, result);
    }

    @Test
    void shouldReturnAllStudents() {
        // arrange
        IStudentFactory studentFactoryDouble = mock(IStudentFactory.class);
        IStudentRepository studentRepositoryDouble = mock(IStudentRepository.class);
        StudentServiceImpl studentServiceImpl = new StudentServiceImpl(studentFactoryDouble, studentRepositoryDouble);

        Student student1 = mock(Student.class);
        Student student2 = mock(Student.class);

        Iterable<Student> iterable = List.of(student1, student2);

        when(studentRepositoryDouble.findAll()).thenReturn(iterable);

        // act
        List<Student> result = studentServiceImpl.getAllStudents();

        // assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(student1));
        assertTrue(result.contains(student2));
    }

    @Test
    void getNameByStudentID() {
        //arrange
        IStudentFactory studentFactoryDouble = mock(IStudentFactory.class);
        IStudentRepository studentRepositoryDouble = mock(IStudentRepository.class);

        StudentServiceImpl studentServiceImpl = new StudentServiceImpl(studentFactoryDouble, studentRepositoryDouble);

        StudentID studentID = mock(StudentID.class);
        Student student = mock(Student.class);
        Name name = mock(Name.class);

        when(studentRepositoryDouble.ofIdentity(studentID)).thenReturn(Optional.of(student));
        when(student.getStudentName()).thenReturn(name);

        //act
        Name result = studentServiceImpl.getNameByStudentID(studentID);

        //assert
        assertEquals(name, result);
    }

    @Test
    void shouldThrowExceptionWhenStudentNotFound() {
        //arrange
        IStudentFactory studentFactoryDouble = mock(IStudentFactory.class);
        IStudentRepository studentRepositoryDouble = mock(IStudentRepository.class);

        StudentServiceImpl studentServiceImpl = new StudentServiceImpl(studentFactoryDouble, studentRepositoryDouble);

        StudentID studentID = mock(StudentID.class);

        when(studentRepositoryDouble.ofIdentity(studentID)).thenReturn(Optional.empty());

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> { studentServiceImpl.getNameByStudentID(studentID);
        });
    }
}