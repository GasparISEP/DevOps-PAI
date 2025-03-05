package PAI.controller;

import PAI.domain.Address;
import PAI.domain.StudentFactory;
import PAI.domain.StudentListFactory;
import PAI.domain.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US08_IWantToRegisterAStudentInTheSystemControllerTest {


    @Test
    void IWantToRegisterAStudentInTheSystemControllerConstructorTestWithoutIsolation() {
        //arrange
        StudentFactory sFactory = new StudentFactory();
        StudentListFactory studentList = new StudentListFactory();
        StudentRepository studentRepository = new StudentRepository(sFactory, studentList);
        //act
        US08_IWantToRegisterAStudentInTheSystemController ctrl = new US08_IWantToRegisterAStudentInTheSystemController(studentRepository);
    }

    @Test
    void IWantToRegisterAStudentInTheSystemControllerConstructorTestWithIsolation() {
        //arrange
        StudentRepository studentRepositoryDouble = mock(StudentRepository.class);
        //act
        US08_IWantToRegisterAStudentInTheSystemController ctrl = new US08_IWantToRegisterAStudentInTheSystemController(studentRepositoryDouble);
    }

    @Test
    void nullRepositoryDoesNotCreateObject1() {
        //arrange

        //act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new US08_IWantToRegisterAStudentInTheSystemController(null));

        //assert
        assertEquals(exception.getMessage(), "Student repository cannot be null!");
    }

    @Test
    void registerStudentWithValidParametersReturnsTrueWithoutIsolation() throws Exception {
        //arrange
        Address address = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        StudentFactory sFactory = new StudentFactory();
        StudentListFactory studentList = new StudentListFactory();
        StudentRepository studentRepository = new StudentRepository(sFactory, studentList);
        US08_IWantToRegisterAStudentInTheSystemController ctrl = new US08_IWantToRegisterAStudentInTheSystemController(studentRepository);

        //act
        boolean result = ctrl.registerStudent(1, "Rita", "123456789", "963741258", "rita@gmail.com", address);

        //assert
        assertTrue(result);
    }

    @Test
    void registerStudentWithValidParametersReturnsTrueWithIsolation() throws Exception {
        //arrange
        Address addressDouble = mock(Address.class);
        StudentRepository studentRepositoryDouble = mock(StudentRepository.class);
        US08_IWantToRegisterAStudentInTheSystemController ctrl = new US08_IWantToRegisterAStudentInTheSystemController(studentRepositoryDouble);

        when(studentRepositoryDouble.registerStudent(1, "Rita", "123456789", "963741258", "rita@gmail.com", addressDouble))
                .thenReturn(true);

        //act
        boolean result = ctrl.registerStudent(1, "Rita", "123456789", "963741258", "rita@gmail.com", addressDouble);

        //assert
        assertTrue(result);
    }

    public static Stream<Arguments> provideInvalidParametersWithoutIsolation() {
        return Stream.of(
                Arguments.of(2, "123456789"),
                Arguments.of(1, "987654321")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidParametersWithoutIsolation")
    void throwsExceptionIfNIFOrUniqueNumberAreRepeatedWithoutIsolation(int uniqueNumber, String NIF) throws Exception {
        //arrange
        Address address = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        StudentFactory sFactory = new StudentFactory();
        StudentListFactory studentList = new StudentListFactory();
        StudentRepository studentRepository = new StudentRepository(sFactory, studentList);
        US08_IWantToRegisterAStudentInTheSystemController ctrl = new US08_IWantToRegisterAStudentInTheSystemController(studentRepository);
        ctrl.registerStudent(1, "Rita", "123456789", "963741258", "rita@gmail.com", address);

        //act
        Exception exception = assertThrows(Exception.class, () -> ctrl.registerStudent(uniqueNumber, "Andreia", NIF, "917852336", "andreia@gmail.com", address));

        //assert
        assertEquals(exception.getMessage(), "Duplicate unique number or NIF detected. Student cannot be added.");
    }

    public static Stream<Arguments> provideInvalidParametersWithIsolation() {
        return Stream.of(
                Arguments.of(2, "123456789"),
                Arguments.of(1, "987654321")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidParametersWithIsolation")
    void throwsExceptionIfNIFOrUniqueNumberAreRepeatedWithoutIsolationWithIsolation(int uniqueNumber, String NIF) throws Exception {
        //arrange
        Address addressDouble = mock(Address.class);
        StudentRepository studentRepositoryDouble = mock(StudentRepository.class);
        US08_IWantToRegisterAStudentInTheSystemController ctrl = new US08_IWantToRegisterAStudentInTheSystemController(studentRepositoryDouble);

        // uniqueNumber is different, but NIF duplicated ("123456789")
        when(studentRepositoryDouble.registerStudent(1, "Rita", "123456789", "963741258", "rita@gmail.com", addressDouble))
                .thenReturn(true);

        ctrl.registerStudent(1, "Rita", "123456789", "963741258", "rita@gmail.com", addressDouble);

        // Caso 2: uniqueNumber is duplicated (1), but NIF is different ("987654321")
        when(studentRepositoryDouble.registerStudent(uniqueNumber, "Andreia", NIF, "917852336", "andreia@gmail.com", addressDouble))
                .thenThrow(new Exception("Duplicate unique number or NIF detected. Student cannot be added."));

        //act
        Exception exception = assertThrows(Exception.class, () -> ctrl.registerStudent(uniqueNumber, "Andreia", NIF, "917852336", "andreia@gmail.com", addressDouble));

        //assert
        assertEquals(exception.getMessage(), "Duplicate unique number or NIF detected. Student cannot be added.");
    }
}