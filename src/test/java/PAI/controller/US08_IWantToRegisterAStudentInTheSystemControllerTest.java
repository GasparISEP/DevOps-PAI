package PAI.controller;

import PAI.domain.Address;
import PAI.domain.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class US08_IWantToRegisterAStudentInTheSystemControllerTest {


    @Test
    void IWantToRegisterAStudentInTheSystemControllerConstructorTest() {
        //arrange
        StudentRepository studentRepository = new StudentRepository();
        //act
        US08_IWantToRegisterAStudentInTheSystemController ctrl = new US08_IWantToRegisterAStudentInTheSystemController(studentRepository);
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
    void registerStudentWithValidParametersReturnsTrue() throws Exception {
        //arrange
        Address address = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        StudentRepository studentRepository = new StudentRepository();
        US08_IWantToRegisterAStudentInTheSystemController ctrl = new US08_IWantToRegisterAStudentInTheSystemController(studentRepository);
        //act
        boolean result = ctrl.registerStudent(1, "Rita", "123456789", "963741258", "rita@gmail.com", address);

        //assert
        assertTrue(result);
    }

    public static Stream<Arguments> provideInvalidParameters() {
        return Stream.of(
                Arguments.of(2, "123456789"),
                Arguments.of(1, "987654321")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidParameters")
    void throwsExceptionIfNIFOrUniqueNumberAreRepeated(int uniqueNumber, String NIF) throws Exception {
        //arrange
        Address address = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        StudentRepository studentRepository = new StudentRepository();
        US08_IWantToRegisterAStudentInTheSystemController ctrl = new US08_IWantToRegisterAStudentInTheSystemController(studentRepository);
        ctrl.registerStudent(1, "Rita", "123456789", "963741258", "rita@gmail.com", address);

        //act
        Exception exception = assertThrows(Exception.class, () -> ctrl.registerStudent(uniqueNumber, "Andreia", NIF, "917852336", "andreia@gmail.com", address));

        //assert
        assertEquals(exception.getMessage(), "Duplicate unique number or NIF detected. Student cannot be added.");
    }
}