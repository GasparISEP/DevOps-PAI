package PAI.controller;

import PAI.VOs.StudentID;
import PAI.domain.Address;
import PAI.factory.IStudentFactory;
import PAI.factory.StudentFactoryImpl;
import PAI.factory.IStudentListFactory;
import PAI.factory.StudentListFactoryImpl;
import PAI.repository.StudentRepository;
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
        IStudentFactory sFactory = new StudentFactoryImpl();
        IStudentListFactory studentList = new StudentListFactoryImpl();
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
        StudentID studentID = new StudentID(1234567);
        Address address = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        IStudentFactory sFactory = new StudentFactoryImpl();
        IStudentListFactory studentList = new StudentListFactoryImpl();
        StudentRepository studentRepository = new StudentRepository(sFactory, studentList);
        US08_IWantToRegisterAStudentInTheSystemController ctrl = new US08_IWantToRegisterAStudentInTheSystemController(studentRepository);

        //act
        boolean result = ctrl.registerStudent(studentID, "Rita", "123456789", "963741258", "rita@gmail.com", address);

        //assert
        assertTrue(result);
    }

    @Test
    void registerStudentWithValidParametersReturnsTrueWithIsolation() throws Exception {
        //arrange
        StudentID studentID = new StudentID(1234567);
        Address addressDouble = mock(Address.class);
        StudentRepository studentRepositoryDouble = mock(StudentRepository.class);
        US08_IWantToRegisterAStudentInTheSystemController ctrl = new US08_IWantToRegisterAStudentInTheSystemController(studentRepositoryDouble);

        when(studentRepositoryDouble.registerStudent(studentID, "Rita", "123456789", "963741258", "rita@gmail.com", addressDouble))
                .thenReturn(true);

        //act
        boolean result = ctrl.registerStudent(studentID, "Rita", "123456789", "963741258", "rita@gmail.com", addressDouble);

        //assert
        assertTrue(result);
    }

    public static Stream<Arguments> provideInvalidParametersWithoutIsolation() {
        return Stream.of(
                Arguments.of(new StudentID(1345678), "123456789"),
                Arguments.of(new StudentID(1234567), "987654321")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidParametersWithoutIsolation")
    void throwsExceptionIfNIFOrIDAreRepeatedWithoutIsolation(StudentID studentID, String NIF) throws Exception {
        //arrange
        StudentID studentID1 = new StudentID(1234567);
        Address address = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        IStudentFactory sFactory = new StudentFactoryImpl();
        IStudentListFactory studentList = new StudentListFactoryImpl();
        StudentRepository studentRepository = new StudentRepository(sFactory, studentList);
        US08_IWantToRegisterAStudentInTheSystemController ctrl = new US08_IWantToRegisterAStudentInTheSystemController(studentRepository);
        ctrl.registerStudent(studentID1, "Rita", "123456789", "963741258", "rita@gmail.com", address);

        //act
        Exception exception = assertThrows(Exception.class, () -> ctrl.registerStudent(studentID, "Andreia", NIF, "917852336", "andreia@gmail.com", address));

        //assert
        assertEquals(exception.getMessage(), "Duplicate ID or NIF detected. Student cannot be added.");
    }

    public static Stream<Arguments> provideInvalidParametersWithIsolation() {
        return Stream.of(
                Arguments.of(1345678, "123456789"),
                Arguments.of(1234567, "987654321")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidParametersWithIsolation")
    void throwsExceptionIfNIFOrIDAreRepeatedWithoutIsolationWithIsolation(int uniqueNumber, String NIF) throws Exception {
        //arrange
        StudentID mockStudentID1 = mock(StudentID.class);
        StudentID mockStudentID2 = mock(StudentID.class);

        when(mockStudentID2.getUniqueNumber()).thenReturn(uniqueNumber);

        Address addressDouble = mock(Address.class);
        StudentRepository studentRepositoryDouble = mock(StudentRepository.class);
        US08_IWantToRegisterAStudentInTheSystemController ctrl = new US08_IWantToRegisterAStudentInTheSystemController(studentRepositoryDouble);

        // ID is different, but NIF duplicated ("123456789")
        when(studentRepositoryDouble.registerStudent(mockStudentID1, "Rita", "123456789", "963741258", "rita@gmail.com", addressDouble))
                .thenReturn(true);

        ctrl.registerStudent(mockStudentID1, "Rita", "123456789", "963741258", "rita@gmail.com", addressDouble);

        // Caso 2: ID is duplicated (1), but NIF is different ("987654321")
        when(studentRepositoryDouble.registerStudent(mockStudentID2, "Andreia", NIF, "917852336", "andreia@gmail.com", addressDouble))
                .thenThrow(new Exception("Duplicate ID or NIF detected. Student cannot be added."));

        //act
        Exception exception = assertThrows(Exception.class, () -> ctrl.registerStudent(mockStudentID2, "Andreia", NIF, "917852336", "andreia@gmail.com", addressDouble));

        //assert
        assertEquals(exception.getMessage(), "Duplicate ID or NIF detected. Student cannot be added.");
    }
}