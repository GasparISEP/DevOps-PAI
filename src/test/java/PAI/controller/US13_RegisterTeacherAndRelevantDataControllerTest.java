package PAI.controller;

import PAI.domain.*;
import PAI.factory.AddressFactory;
import PAI.factory.TeacherCareerProgressionFactory;
import PAI.repository.DepartmentRepository;
import PAI.repository.TeacherCategoryRepository;
import PAI.repository.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US13_RegisterTeacherAndRelevantDataControllerTest {

    @Test
    void shouldAlwaysCreateObjectController() {
        // Arrange
        TeacherCategoryRepository tcrDouble = mock(TeacherCategoryRepository.class);
        DepartmentRepository dptDouble = mock(DepartmentRepository.class);
        TeacherRepository trDouble = mock(TeacherRepository.class);

        // Act
        US13_RegisterTeacherAndRelevantDataController controllerUS13 = new US13_RegisterTeacherAndRelevantDataController(tcrDouble, dptDouble, trDouble);
    }

    @Test
    void shouldNotCreateCreateObjectControllerWhenTeacherCategoryRepositoryIsNull() {
        // Arrange
        DepartmentRepository dptDouble = mock(DepartmentRepository.class);
        TeacherRepository trDouble = mock(TeacherRepository.class);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new US13_RegisterTeacherAndRelevantDataController(null, dptDouble, trDouble));
    }

    @Test
    void shouldNotCreateCreateObjectControllerWhenDepartmentRepositoryIsNull() {
        // Arrange
        TeacherCategoryRepository tcDouble = mock(TeacherCategoryRepository.class);
        TeacherRepository trDouble = mock(TeacherRepository.class);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new US13_RegisterTeacherAndRelevantDataController(tcDouble, null, trDouble));
    }

    @Test
    void shouldNotCreateCreateObjectControllerWhenTeacherRepositoryIsNull() {
        // Arrange
        TeacherCategoryRepository tcDouble = mock(TeacherCategoryRepository.class);
        DepartmentRepository dptDouble = mock(DepartmentRepository.class);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new US13_RegisterTeacherAndRelevantDataController(tcDouble, dptDouble, null));
    }

    @Test
    void shouldReturnExceptionIfCategoriesListIsEmpty() throws IllegalStateException {
        // Arrange
        TeacherCategoryRepository tcrDouble = mock(TeacherCategoryRepository.class);
        DepartmentRepository dptrDouble = mock(DepartmentRepository.class);
        TeacherRepository trDouble = mock(TeacherRepository.class);


        US13_RegisterTeacherAndRelevantDataController controllerUS13 = new US13_RegisterTeacherAndRelevantDataController(tcrDouble, dptrDouble, trDouble);

        when(tcrDouble.getTeacherCategoryList()).thenThrow(new IllegalStateException("Teacher Category list is empty."));

        // Act + Assert
        assertThrows(IllegalStateException.class, () -> controllerUS13.getTeacherCategoryList());
    }


    @Test
    void shouldReturnCategoryListWithRegisteredCategories() {
        // Arrange
        DepartmentRepository dptrDouble = mock(DepartmentRepository.class);
        TeacherRepository trDouble = mock(TeacherRepository.class);
        TeacherCategoryRepository tcrDouble = mock(TeacherCategoryRepository.class);
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        List<TeacherCategory> tcListDouble = List.of(tcDouble);
        US13_RegisterTeacherAndRelevantDataController controllerUS13 = new US13_RegisterTeacherAndRelevantDataController(tcrDouble, dptrDouble, trDouble);

        when(tcrDouble.getTeacherCategoryList()).thenReturn(tcListDouble);

        // Act
        List<TeacherCategory> result = controllerUS13.getTeacherCategoryList();

        // Assert
        assertEquals(result, tcListDouble);
    }

    @Test
    void shouldReturnExceptionIfDepartmentsListIsEmpty() throws IllegalStateException {
        TeacherCategoryRepository tcrDouble = mock(TeacherCategoryRepository.class);
        DepartmentRepository dptrDouble = mock(DepartmentRepository.class);
        TeacherRepository trDouble = mock(TeacherRepository.class);
        US13_RegisterTeacherAndRelevantDataController controllerUS13 = new US13_RegisterTeacherAndRelevantDataController(tcrDouble, dptrDouble, trDouble);

        when(dptrDouble.getDepartments()).thenThrow(new IllegalStateException("Department list is empty."));

        // Act + Assert
        assertThrows(IllegalStateException.class, () -> controllerUS13.getDepartmentsList());
    }

    @Test
    void shouldReturnDepartmentListWithRegisteredDepartments() {
        // Arrange
        TeacherCategoryRepository tcrDouble = mock(TeacherCategoryRepository.class);
        DepartmentRepository dptrDouble = mock(DepartmentRepository.class);
        TeacherRepository trDouble = mock(TeacherRepository.class);
        US13_RegisterTeacherAndRelevantDataController controllerUS13 = new US13_RegisterTeacherAndRelevantDataController(tcrDouble, dptrDouble, trDouble);

        Department dptDouble = mock(Department.class);
        Set<Department> dptListDouble = new HashSet<>();

        when(dptrDouble.getDepartments()).thenReturn(dptListDouble);

        // Act
        Set<Department> result = controllerUS13.getDepartmentsList();
        // Assert
        assertEquals(result, dptListDouble);
    }

    @Test
    void shouldRegisterTeacher() {
        // Arrange
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        Department dptDouble = mock(Department.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        TeacherCareerProgressionFactory tcpFactoryDouble = mock(TeacherCareerProgressionFactory.class);

        TeacherCategoryRepository tcrDouble = mock(TeacherCategoryRepository.class);
        DepartmentRepository dptrDouble = mock(DepartmentRepository.class);
        TeacherRepository trDouble = mock(TeacherRepository.class);
        US13_RegisterTeacherAndRelevantDataController controllerUS13 = new US13_RegisterTeacherAndRelevantDataController(
                tcrDouble, dptrDouble, trDouble);

        when(trDouble.registerTeacher("JSD", "John Smith Doe", "jsd@isep.ipp.pt",
                "123456789", "B146", "12-05-2019, PhD, ISEP",
                "123 Main St", "12345", "Cityville", "Countryland", addressFactoryDouble, "23-01-2025", tcDouble, 100, dptDouble, tcpFactoryDouble)).thenReturn(true);

        // Act
        boolean result = controllerUS13.registerTeacher("JSD", "John Smith Doe", "jsd@isep.ipp.pt",
                "123456789", "B146", "12-05-2019, PhD, ISEP",
                "123 Main St", "12345", "Cityville", "Countryland", addressFactoryDouble, "23-01-2025", tcDouble, 100, dptDouble, tcpFactoryDouble);

        // Assert
        assertTrue(result);
    }

    public static Stream<Arguments> provideInvalidTeacherAcronymsInController() {
        return Stream.of(
                arguments(null, "Teacher´s acronym must be a 3 capital letter non-empty String."),
                arguments("", "Teacher´s acronym must be a 3 capital letter non-empty String."),
                arguments("   ", "Teacher´s acronym must be a 3 capital letter non-empty String."),
                arguments("A", "Teacher´s acronym must contain only three capital letters."),
                arguments("AF".repeat(101), "Teacher´s acronym must contain only three capital letters."),
                arguments("AFRT".repeat(101), "Teacher´s acronym must contain only three capital letters."),
                arguments("A F R", "Teacher´s acronym must contain only three capital letters."),
                arguments(" AFR", "Teacher´s acronym must contain only three capital letters."),
                arguments("AFR ", "Teacher´s acronym must contain only three capital letters."),
                arguments("AF1", "Teacher´s acronym must contain only three capital letters."),
                arguments("!RT", "Teacher´s acronym must contain only three capital letters."),
                arguments("aaa", "Teacher´s acronym must contain only three capital letters.")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidTeacherAcronymsInController")
    void testInvalidAcronyms(String acronym, String expectedMessage) throws IllegalArgumentException {

        Department dptDouble = mock(Department.class);
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        TeacherCareerProgressionFactory tcpFactoryDouble = mock(TeacherCareerProgressionFactory.class);

        DepartmentRepository dptrDouble = mock(DepartmentRepository.class);
        TeacherRepository trDouble = mock(TeacherRepository.class);
        TeacherCategoryRepository tcrDouble = mock(TeacherCategoryRepository.class);
        US13_RegisterTeacherAndRelevantDataController controllerUS13 = new US13_RegisterTeacherAndRelevantDataController(tcrDouble, dptrDouble, trDouble);

        when(trDouble.registerTeacher(acronym, "John Smith Doe", "jsd@isep.ipp.pt",
                "123456789", "B146", "12-05-2019, PhD, ISEP",
                "123 Main St", "12345", "Cityville", "Countryland", addressFactoryDouble, "20-03-2010", tcDouble, 100, dptDouble, tcpFactoryDouble)).thenThrow(new IllegalArgumentException(expectedMessage));

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> controllerUS13.registerTeacher(acronym, "John Smith Doe", "jsd@isep.ipp.pt",
                "123456789", "B146", "12-05-2019, PhD, ISEP",
                "123 Main St", "12345", "Cityville", "Countryland", addressFactoryDouble, "20-03-2010", tcDouble, 100, dptDouble, tcpFactoryDouble));
    }

    //testing the Teacher name
    public static Stream<Arguments> provideInvalidTeacherNamesInController() {
        return Stream.of(
                arguments(null, "Teacher´s name must be a non-empty String."),
                arguments("", "Teacher´s name must be a non-empty String."),
                arguments("   ", "Teacher´s name must be a non-empty String."),
                arguments("A", "Teacher´s name must be between 2 and 100 characters."),
                arguments("A".repeat(101), "Teacher´s name must be between 2 and 100 characters."),
                arguments("john", "Teacher´s name should start with a capital letter."),
                arguments(" John", "Teacher´s name should start with a capital letter."),
                arguments("123Name", "Teacher´s name must contain only letters and spaces."),
                arguments("!@#Name", "Teacher´s name must contain only letters and spaces.")
        );
    }
    @ParameterizedTest
    @MethodSource("provideInvalidTeacherNamesInController")
    void testInvalidNames(String name, String expectedMessage) throws IllegalArgumentException {
        // Arrange
        Department dptDouble = mock(Department.class);
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        TeacherCareerProgressionFactory tcpFactoryDouble = mock(TeacherCareerProgressionFactory.class);

        DepartmentRepository dptrDouble = mock(DepartmentRepository.class);
        TeacherRepository trDouble = mock(TeacherRepository.class);
        TeacherCategoryRepository tcrDouble = mock(TeacherCategoryRepository.class);
        US13_RegisterTeacherAndRelevantDataController controllerUS13 = new US13_RegisterTeacherAndRelevantDataController(tcrDouble, dptrDouble, trDouble);

        when(trDouble.registerTeacher("JSD", name, "jsd@isep.ipp.pt",
                "123456789", "B146", "12-05-2019, PhD, ISEP",
                "123 Main St", "12345", "Cityville", "Countryland", addressFactoryDouble, "20-03-2010", tcDouble, 100, dptDouble, tcpFactoryDouble)).thenThrow(new IllegalArgumentException(expectedMessage));

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> controllerUS13.registerTeacher("JSD", name , "jsd@isep.ipp.pt",
                "123456789", "B146", "12-05-2019, PhD, ISEP",
                "123 Main St", "12345", "Cityville", "Countryland", addressFactoryDouble, "20-03-2010", tcDouble, 100, dptDouble, tcpFactoryDouble));
    }

    public static Stream<Arguments> provideInvalidTeacherEmailInController() {
        return Stream.of(
                arguments(null, "Teacher´s email must be a non-empty String."),
                arguments("", "Teacher´s email must be a non-empty String."),
                arguments("   ", "Teacher´s email must be a non-empty String."),
                arguments("MMO@isep.ipp.pt", "Invalid email format."),
                arguments("*MO@isep.ipp.pt", "Invalid email format."),
                arguments("M2N@isep.ipp.pt", "Invalid email format."),
                arguments("M MM@isep.ipp.pt", "Invalid email format."),
                arguments("MMJ@gmail.com", "Invalid email format."),
                arguments("MMN", "Invalid email format.")
        );
    }
    @ParameterizedTest
    @MethodSource("provideInvalidTeacherEmailInController")
    void testInvalidEmail(String email, String expectedMessage) {
        // Arrange
        Department dptDouble = mock(Department.class);
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        TeacherCareerProgressionFactory tcpFactoryDouble = mock(TeacherCareerProgressionFactory.class);

        DepartmentRepository dptrDouble = mock(DepartmentRepository.class);
        TeacherRepository trDouble = mock(TeacherRepository.class);
        TeacherCategoryRepository tcrDouble = mock(TeacherCategoryRepository.class);
        US13_RegisterTeacherAndRelevantDataController controllerUS13 = new US13_RegisterTeacherAndRelevantDataController(tcrDouble, dptrDouble, trDouble);

        when(trDouble.registerTeacher("JSD","John Smith Doe", email,
                "123456789", "B146", "12-05-2019, PhD, ISEP",
                "123 Main St", "12345", "Cityville", "Countryland", addressFactoryDouble, "20-03-2010", tcDouble, 100, dptDouble, tcpFactoryDouble)).thenThrow(new IllegalArgumentException(expectedMessage));

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> controllerUS13.registerTeacher("JSD", "John Smith Doe", email,
                "123456789", "B146", "12-05-2019, PhD, ISEP",
                "123 Main St", "12345", "Cityville", "Countryland", addressFactoryDouble, "20-03-2010", tcDouble, 100, dptDouble, tcpFactoryDouble));
    }

    public static Stream<Arguments> provideInvalidTeacherNIFInController() {
        return Stream.of(
                arguments(null, "Teacher´s NIF must be a non-empty String."),
                arguments("", "Teacher´s NIF must be a non-empty String."),
                arguments("   ", "Teacher´s NIF must be a non-empty String."),
                arguments("A23321423", "Teacher´s NIF must contain only 9 characters."),
                arguments("+23321423", "Teacher´s NIF must contain only 9 characters."),
                arguments("1 2 3 3 2 1 4 2 3", "Teacher´s NIF must contain only 9 characters."),
                arguments(" 123456789", "Teacher´s NIF must contain only 9 characters."),
                arguments("123456789 ", "Teacher´s NIF must contain only 9 characters."),
                arguments("1234567890", "Teacher´s NIF must contain only 9 characters.")
        );
    }
    @ParameterizedTest
    @MethodSource("provideInvalidTeacherNIFInController")
    void testInvalidNIF(String nif, String expectedMessage) throws IllegalArgumentException {
        // Arrange
        Department dptDouble = mock(Department.class);
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        TeacherCareerProgressionFactory tcpFactoryDouble = mock(TeacherCareerProgressionFactory.class);

        DepartmentRepository dptrDouble = mock(DepartmentRepository.class);
        TeacherRepository trDouble = mock(TeacherRepository.class);
        TeacherCategoryRepository tcrDouble = mock(TeacherCategoryRepository.class);
        US13_RegisterTeacherAndRelevantDataController controllerUS13 = new US13_RegisterTeacherAndRelevantDataController(tcrDouble, dptrDouble, trDouble);

        when(trDouble.registerTeacher("JSD","John Smith Doe", "jsd@isep.ipp.pt",
                nif, "B146", "12-05-2019, PhD, ISEP",
                "123 Main St", "12345", "Cityville", "Countryland", addressFactoryDouble, "20-03-2010", tcDouble, 100, dptDouble, tcpFactoryDouble)).thenThrow(new IllegalArgumentException(expectedMessage));

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> controllerUS13.registerTeacher("JSD", "John Smith Doe", "jsd@isep.ipp.pt",
                nif, "B146", "12-05-2019, PhD, ISEP",
                "123 Main St", "12345", "Cityville", "Countryland", addressFactoryDouble, "20-03-2010", tcDouble, 100, dptDouble, tcpFactoryDouble));
    }

    public static Stream<Arguments> provideInvalidTeacherPhoneNumberInController() {
        return Stream.of(
                arguments(null, "Teacher´s office phoneNumber must be a non-empty String."),
                arguments("", "Teacher´s office phoneNumber must be a non-empty String."),
                arguments("   ", "Teacher´s office phoneNumber must be a non-empty String."),
                arguments("919234567", "Teacher´s office phoneNumber must have a letter followed by 3 digits."),
                arguments(" B123", "Teacher´s office phoneNumber must have a letter followed by 3 digits."),
                arguments("B123 ", "Teacher´s office phoneNumber must have a letter followed by 3 digits."),
                arguments("b123", "Teacher´s office phoneNumber must have a letter followed by 3 digits."),
                arguments("B12", "Teacher´s office phoneNumber must have a letter followed by 3 digits."),
                arguments("B1234", "Teacher´s office phoneNumber must have a letter followed by 3 digits."),
                arguments("-123", "Teacher´s office phoneNumber must have a letter followed by 3 digits."),
                arguments("B12 3", "Teacher´s office phoneNumber must have a letter followed by 3 digits."),
                arguments("B+23", "Teacher´s office phoneNumber must have a letter followed by 3 digits.")
        );
    }
    @ParameterizedTest
    @MethodSource("provideInvalidTeacherPhoneNumberInController")
    void testInvalidPhoneNumber(String phoneNumber, String expectedMessage) throws IllegalArgumentException {
        // Arrange
        Department dptDouble = mock(Department.class);
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        TeacherCareerProgressionFactory tcpFactoryDouble = mock(TeacherCareerProgressionFactory.class);

        DepartmentRepository dptrDouble = mock(DepartmentRepository.class);
        TeacherRepository trDouble = mock(TeacherRepository.class);
        TeacherCategoryRepository tcrDouble = mock(TeacherCategoryRepository.class);
        US13_RegisterTeacherAndRelevantDataController controllerUS13 = new US13_RegisterTeacherAndRelevantDataController(tcrDouble, dptrDouble, trDouble);

        when(trDouble.registerTeacher("JSD","John Smith Doe", "jsd@isep.ipp.pt",
                "123456789", phoneNumber, "12-05-2019, PhD, ISEP",
                "123 Main St", "12345", "Cityville", "Countryland", addressFactoryDouble, "20-03-2010", tcDouble, 100, dptDouble, tcpFactoryDouble)).thenThrow(new IllegalArgumentException(expectedMessage));

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> controllerUS13.registerTeacher("JSD", "John Smith Doe", "jsd@isep.ipp.pt",
                "123456789", phoneNumber, "12-05-2019, PhD, ISEP",
                "123 Main St", "12345", "Cityville", "Countryland", addressFactoryDouble, "20-03-2010", tcDouble, 100, dptDouble, tcpFactoryDouble));
    }

    public static Stream<Arguments> provideInvalidAcademicBackgroundInController() {
        return Stream.of(
                arguments(null, "Teacher's academic background must be a non-empty String."),
                arguments("", "Teacher's academic background must be a non-empty String."),
                arguments("   ", "Teacher's academic background must be a non-empty String.")
        );
    }
    @ParameterizedTest
    @MethodSource("provideInvalidAcademicBackgroundInController")
    void testInvalidAcademicBackground(String academicBackground, String expectedMessage) throws IllegalArgumentException {
        // Arrange
        Department dptDouble = mock(Department.class);
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        TeacherCareerProgressionFactory tcpFactoryDouble = mock(TeacherCareerProgressionFactory.class);

        DepartmentRepository dptrDouble = mock(DepartmentRepository.class);
        TeacherRepository trDouble = mock(TeacherRepository.class);
        TeacherCategoryRepository tcrDouble = mock(TeacherCategoryRepository.class);
        US13_RegisterTeacherAndRelevantDataController controllerUS13 = new US13_RegisterTeacherAndRelevantDataController(tcrDouble, dptrDouble, trDouble);

        when(trDouble.registerTeacher("JSD","John Smith Doe", "jsd@isep.ipp.pt",
                "123456789", "B146", academicBackground,
                "123 Main St", "12345", "Cityville", "Countryland", addressFactoryDouble, "20-03-2010", tcDouble, 100, dptDouble, tcpFactoryDouble)).thenThrow(new IllegalArgumentException(expectedMessage));

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> controllerUS13.registerTeacher("JSD", "John Smith Doe", "jsd@isep.ipp.pt",
                "123456789", "B146", academicBackground,
                "123 Main St", "12345", "Cityville", "Countryland", addressFactoryDouble, "20-03-2010", tcDouble, 100, dptDouble, tcpFactoryDouble));
    }

    public static Stream<Arguments> provideInvalidStreetInController() {
        return Stream.of(
                arguments(null, "Street cannot be empty!"),
                arguments("", "Street cannot be empty!"),
                arguments("   ", "Street cannot be empty!")
        );
    }
    @ParameterizedTest
    @MethodSource("provideInvalidStreetInController")
    void testInvalidStreet(String street, String expectedMessage) throws IllegalArgumentException {
        // Arrange
        Department dptDouble = mock(Department.class);
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        TeacherCareerProgressionFactory tcpFactoryDouble = mock(TeacherCareerProgressionFactory.class);

        DepartmentRepository dptrDouble = mock(DepartmentRepository.class);
        TeacherRepository trDouble = mock(TeacherRepository.class);
        TeacherCategoryRepository tcrDouble = mock(TeacherCategoryRepository.class);
        US13_RegisterTeacherAndRelevantDataController controllerUS13 = new US13_RegisterTeacherAndRelevantDataController(tcrDouble, dptrDouble, trDouble);

        when(trDouble.registerTeacher("JSD","John Smith Doe", "jsd@isep.ipp.pt",
                "123456789", "B146", "12-05-2019, PhD, ISEP",
                street, "12345", "Cityville", "Countryland", addressFactoryDouble, "20-03-2010", tcDouble, 100, dptDouble, tcpFactoryDouble)).thenThrow(new IllegalArgumentException(expectedMessage));

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> controllerUS13.registerTeacher("JSD", "John Smith Doe", "jsd@isep.ipp.pt",
                "123456789", "B146","12-05-2019, PhD, ISEP",
                street, "12345", "Cityville", "Countryland", addressFactoryDouble, "20-03-2010", tcDouble, 100, dptDouble, tcpFactoryDouble));
    }

    public static Stream<Arguments> provideInvalidPostalCodeInController() {
        return Stream.of(
                arguments(null, "Postal Code cannot be empty!"),
                arguments("", "Postal Code cannot be empty!"),
                arguments("   ", "Postal Code cannot be empty!")
        );
    }
    @ParameterizedTest
    @MethodSource("provideInvalidPostalCodeInController")
    void testInvalidPostalCode(String postalCode, String expectedMessage) throws IllegalArgumentException {
        // Arrange
        Department dptDouble = mock(Department.class);
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        TeacherCareerProgressionFactory tcpFactoryDouble = mock(TeacherCareerProgressionFactory.class);

        DepartmentRepository dptrDouble = mock(DepartmentRepository.class);
        TeacherRepository trDouble = mock(TeacherRepository.class);
        TeacherCategoryRepository tcrDouble = mock(TeacherCategoryRepository.class);
        US13_RegisterTeacherAndRelevantDataController controllerUS13 = new US13_RegisterTeacherAndRelevantDataController(tcrDouble, dptrDouble, trDouble);

        when(trDouble.registerTeacher("JSD","John Smith Doe", "jsd@isep.ipp.pt",
                "123456789", "B146", "12-05-2019, PhD, ISEP",
                "123 Main St", postalCode, "Cityville", "Countryland", addressFactoryDouble, "20-03-2010", tcDouble, 100, dptDouble, tcpFactoryDouble)).thenThrow(new IllegalArgumentException(expectedMessage));

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> controllerUS13.registerTeacher("JSD", "John Smith Doe", "jsd@isep.ipp.pt",
                "123456789", "B146","12-05-2019, PhD, ISEP",
                "123 Main St", postalCode, "Cityville", "Countryland", addressFactoryDouble, "20-03-2010", tcDouble, 100, dptDouble, tcpFactoryDouble));
    }

    public static Stream<Arguments> provideInvalidLocationInController() {
        return Stream.of(
                arguments(null, "Location cannot be empty!"),
                arguments("", "Location cannot be empty!"),
                arguments("   ", "Location cannot be empty!")
        );
    }
    @ParameterizedTest
    @MethodSource("provideInvalidLocationInController")
    void testInvalidLocation(String location, String expectedMessage) throws IllegalArgumentException {
        // Arrange
        Department dptDouble = mock(Department.class);
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        TeacherCareerProgressionFactory tcpFactoryDouble = mock(TeacherCareerProgressionFactory.class);

        DepartmentRepository dptrDouble = mock(DepartmentRepository.class);
        TeacherRepository trDouble = mock(TeacherRepository.class);
        TeacherCategoryRepository tcrDouble = mock(TeacherCategoryRepository.class);
        US13_RegisterTeacherAndRelevantDataController controllerUS13 = new US13_RegisterTeacherAndRelevantDataController(tcrDouble, dptrDouble, trDouble);

        when(trDouble.registerTeacher("JSD","John Smith Doe", "jsd@isep.ipp.pt",
                "123456789", "B146", "12-05-2019, PhD, ISEP",
                "123 Main St", "12345", location, "Countryland", addressFactoryDouble, "20-03-2010", tcDouble, 100, dptDouble, tcpFactoryDouble)).thenThrow(new IllegalArgumentException(expectedMessage));

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> controllerUS13.registerTeacher("JSD", "John Smith Doe", "jsd@isep.ipp.pt",
                "123456789", "B146","12-05-2019, PhD, ISEP",
                "123 Main St", "12345", location, "Countryland", addressFactoryDouble, "20-03-2010", tcDouble, 100, dptDouble, tcpFactoryDouble));
    }

    public static Stream<Arguments> provideInvalidCountryInController() {
        return Stream.of(
                arguments(null, "Country cannot be empty!"),
                arguments("", "Country cannot be empty!"),
                arguments("   ", "Country cannot be empty!")
        );
    }
    @ParameterizedTest
    @MethodSource("provideInvalidCountryInController")
    void testInvalidCountry(String country, String expectedMessage) throws IllegalArgumentException {
        // Arrange
        Department dptDouble = mock(Department.class);
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        TeacherCareerProgressionFactory tcpFactoryDouble = mock(TeacherCareerProgressionFactory.class);

        DepartmentRepository dptrDouble = mock(DepartmentRepository.class);
        TeacherRepository trDouble = mock(TeacherRepository.class);
        TeacherCategoryRepository tcrDouble = mock(TeacherCategoryRepository.class);
        US13_RegisterTeacherAndRelevantDataController controllerUS13 = new US13_RegisterTeacherAndRelevantDataController(tcrDouble, dptrDouble, trDouble);

        when(trDouble.registerTeacher("JSD","John Smith Doe", "jsd@isep.ipp.pt",
                "123456789", "B146", "12-05-2019, PhD, ISEP",
                "123 Main St", "12345", "Cityville", country, addressFactoryDouble, "20-03-2010", tcDouble, 100, dptDouble, tcpFactoryDouble)).thenThrow(new IllegalArgumentException(expectedMessage));

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> controllerUS13.registerTeacher("JSD", "John Smith Doe", "jsd@isep.ipp.pt",
                "123456789", "B146","12-05-2019, PhD, ISEP",
                "123 Main St", "12345", "Cityville", country, addressFactoryDouble, "20-03-2010", tcDouble, 100, dptDouble, tcpFactoryDouble));
    }

    public static Stream<Arguments> provideInvalidDateInController() {
        return Stream.of(
                arguments(null, "Date cannot be empty!"),
                arguments("", "Date cannot be empty!"),
                arguments("   ", "Date cannot be empty!")
        );
    }
    @ParameterizedTest
    @MethodSource("provideInvalidDateInController")
    void testInvalidDate(String date, String expectedMessage) throws IllegalArgumentException {
        // Arrange
        Department dptDouble = mock(Department.class);
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        TeacherCareerProgressionFactory tcpFactoryDouble = mock(TeacherCareerProgressionFactory.class);

        DepartmentRepository dptrDouble = mock(DepartmentRepository.class);
        TeacherRepository trDouble = mock(TeacherRepository.class);
        TeacherCategoryRepository tcrDouble = mock(TeacherCategoryRepository.class);
        US13_RegisterTeacherAndRelevantDataController controllerUS13 = new US13_RegisterTeacherAndRelevantDataController(tcrDouble, dptrDouble, trDouble);

        when(trDouble.registerTeacher("JSD","John Smith Doe", "jsd@isep.ipp.pt",
                "123456789", "B146", "12-05-2019, PhD, ISEP",
                "123 Main St", "12345", "Cityville","Countryland", addressFactoryDouble, date, tcDouble, 100, dptDouble, tcpFactoryDouble)).thenThrow(new IllegalArgumentException(expectedMessage));

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> controllerUS13.registerTeacher("JSD", "John Smith Doe", "jsd@isep.ipp.pt",
                "123456789", "B146","12-05-2019, PhD, ISEP",
                "123 Main St", "12345", "Cityville","Countryland", addressFactoryDouble, date, tcDouble, 100, dptDouble, tcpFactoryDouble));
    }
}