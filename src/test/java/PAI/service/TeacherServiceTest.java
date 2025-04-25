package PAI.service;

import PAI.VOs.*;
import PAI.domain.Teacher;
import PAI.factory.ITeacherFactory;
import PAI.factory.TeacherFactoryImpl;
import PAI.persistence.springdata.TeacherRepositorySpringData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TeacherServiceTest {

    private ITeacherFactory teacherFactoryDouble;
    private TeacherRepositorySpringData teacherRepositoryDouble;
    private ITeacherService teacherService;

    private TeacherAcronym acronymDouble;
    private Name nameDouble;
    private Email emailDouble;
    private NIF nifDouble;
    private PhoneNumber phoneNumberDouble;
    private AcademicBackground academicBackgroundDouble;
    private Street streetDouble;
    private PostalCode postalCodeDouble;
    private Location locationDouble;
    private Country countryDouble;
    private DepartmentID departmentIDDouble;

    @BeforeEach
    void setUp() {
        teacherFactoryDouble = mock(TeacherFactoryImpl.class);
        teacherRepositoryDouble = mock(TeacherRepositorySpringData.class);
        teacherService = new TeacherService(teacherFactoryDouble, teacherRepositoryDouble);

        acronymDouble = mock(TeacherAcronym.class);
        nameDouble = mock(Name.class);
        emailDouble = mock(Email.class);
        nifDouble = mock(NIF.class);
        phoneNumberDouble = mock(PhoneNumber.class);
        academicBackgroundDouble = mock(AcademicBackground.class);
        streetDouble = mock(Street.class);
        postalCodeDouble = mock(PostalCode.class);
        locationDouble = mock(Location.class);
        countryDouble = mock(Country.class);
        departmentIDDouble = mock(DepartmentID.class);
    }

    @Test
    void testConstructor () {
        // Arrange
        ITeacherFactory teacherFactoryDouble = mock(ITeacherFactory.class);
        TeacherRepositorySpringData teacherRepositoryDouble = mock(TeacherRepositorySpringData.class);

        // Arrange + Act
        TeacherService teacherService = new TeacherService(teacherFactoryDouble, teacherRepositoryDouble);

        // Assert
        assertNotNull(teacherService);
    }

    @Test
    void testConstructorDoesNotBuildDueToNullFactory () {
        // Arrange
        TeacherRepositorySpringData teacherRepositoryDouble = mock(TeacherRepositorySpringData.class);

        // Act
        IllegalArgumentException expectedException = assertThrows(IllegalArgumentException.class, () -> {
            new TeacherService(null, teacherRepositoryDouble);
        });

        // Assert
        assertEquals("Teacher Factory must not be null.", expectedException.getMessage());
    }

    @Test
    void testConstructorDoesNotBuildDueToNullRepository () {
        // Arrange
        ITeacherFactory teacherFactoryDouble = mock(ITeacherFactory.class);

        // Act
        IllegalArgumentException expectedException = assertThrows(IllegalArgumentException.class, () -> {
            new TeacherService(teacherFactoryDouble, null);
        });

        // Assert
        assertEquals("Teacher Repository must not be null.", expectedException.getMessage());
    }

    @Test
    void registerTeacherShouldRegisterTeacher() {

        // Arrange
        Teacher teacherDouble = mock(Teacher.class);
        TeacherID teacherIDdouble = mock(TeacherID.class);

        when(teacherFactoryDouble.createTeacher(
                acronymDouble, nameDouble, emailDouble, nifDouble, phoneNumberDouble, academicBackgroundDouble,
                streetDouble, postalCodeDouble, locationDouble, countryDouble, departmentIDDouble)).thenReturn(teacherDouble);

        when(teacherDouble.identity()).thenReturn(teacherIDdouble);
        when(teacherRepositoryDouble.save(teacherDouble)).thenReturn(teacherDouble);

        // Act
        Optional<TeacherID> result = teacherService.registerTeacher(
                acronymDouble, nameDouble, emailDouble, nifDouble, phoneNumberDouble, academicBackgroundDouble,
                streetDouble, postalCodeDouble, locationDouble, countryDouble, departmentIDDouble);

        // Assert
        assertEquals(result.get(), teacherIDdouble);
    }

    public static Stream<Arguments> provideInvalidAttributes () {
        return Stream.of(
                // Arrange
                arguments(null, mock(Name.class), mock(Email.class), mock(NIF.class), mock(PhoneNumber.class), mock(AcademicBackground.class), mock(Street.class), mock(PostalCode.class), mock(Location.class), mock(Country.class), mock(DepartmentID.class), "Passing parameters should not be null."),
                arguments(mock(TeacherAcronym.class), null, mock(Email.class), mock(NIF.class), mock(PhoneNumber.class), mock(AcademicBackground.class), mock(Street.class), mock(PostalCode.class), mock(Location.class), mock(Country.class), mock(DepartmentID.class), "Passing parameters should not be null."),
                arguments(mock(TeacherAcronym.class), mock(Name.class), null, mock(NIF.class), mock(PhoneNumber.class), mock(AcademicBackground.class), mock(Street.class), mock(PostalCode.class), mock(Location.class), mock(Country.class), mock(DepartmentID.class), "Passing parameters should not be null."),
                arguments(mock(TeacherAcronym.class), mock(Name.class), mock(Email.class), null, mock(PhoneNumber.class), mock(AcademicBackground.class), mock(Street.class), mock(PostalCode.class), mock(Location.class), mock(Country.class), mock(DepartmentID.class), "Passing parameters should not be null."),
                arguments(mock(TeacherAcronym.class), mock(Name.class), mock(Email.class), mock(NIF.class), null, mock(AcademicBackground.class), mock(Street.class), mock(PostalCode.class), mock(Location.class), mock(Country.class), mock(DepartmentID.class), "Passing parameters should not be null."),
                arguments(mock(TeacherAcronym.class), mock(Name.class), mock(Email.class), mock(NIF.class), mock(PhoneNumber.class), null, mock(Street.class), mock(PostalCode.class), mock(Location.class), mock(Country.class), mock(DepartmentID.class), "Passing parameters should not be null."),
                arguments(mock(TeacherAcronym.class), mock(Name.class), mock(Email.class), mock(NIF.class), mock(PhoneNumber.class), mock(AcademicBackground.class), null, mock(PostalCode.class), mock(Location.class), mock(Country.class), mock(DepartmentID.class), "Passing parameters should not be null."),
                arguments(mock(TeacherAcronym.class), mock(Name.class), mock(Email.class), mock(NIF.class), mock(PhoneNumber.class), mock(AcademicBackground.class), mock(Street.class), null, mock(Location.class), mock(Country.class), mock(DepartmentID.class), "Passing parameters should not be null."),
                arguments(mock(TeacherAcronym.class), mock(Name.class), mock(Email.class), mock(NIF.class), mock(PhoneNumber.class), mock(AcademicBackground.class), mock(Street.class), mock(PostalCode.class), null, mock(Country.class), mock(DepartmentID.class), "Passing parameters should not be null."),
                arguments(mock(TeacherAcronym.class), mock(Name.class), mock(Email.class), mock(NIF.class), mock(PhoneNumber.class), mock(AcademicBackground.class), mock(Street.class), mock(PostalCode.class), mock(Location.class), null, mock(DepartmentID.class), "Passing parameters should not be null."),
                arguments(mock(TeacherAcronym.class), mock(Name.class), mock(Email.class), mock(NIF.class), mock(PhoneNumber.class), mock(AcademicBackground.class), mock(Street.class), mock(PostalCode.class), mock(Location.class), mock(Country.class), null, "Passing parameters should not be null.")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidAttributes")
    void shouldThrowExceptionIfFactoryInRegisterTeacherDidNotCreateTeacherDueToInvalidAttributes (TeacherAcronym acronym, Name name, Email email, NIF nif, PhoneNumber phoneNumber, AcademicBackground academicBackground,
                                                                                                  Street street, PostalCode postalCode, Location location, Country country, DepartmentID departmentID, String expectedMessage) {
        // Arrange
        when(teacherFactoryDouble.createTeacher(acronym, name, email, nif, phoneNumber, academicBackground,
                street, postalCode, location, country, departmentID)).thenThrow(new IllegalArgumentException("Passing parameters should not be null."));

        // Act
        Exception exception = assertThrows(Exception.class, () -> teacherService.registerTeacher(acronym, name, email, nif, phoneNumber, academicBackground,
                street, postalCode, location, country, departmentID));

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldNotSaveTeacherInDatabase () {

        // Arrange
        Teacher teacherDouble = mock(Teacher.class);

        when(teacherFactoryDouble.createTeacher(
                acronymDouble, nameDouble, emailDouble, nifDouble, phoneNumberDouble, academicBackgroundDouble,
                streetDouble, postalCodeDouble, locationDouble, countryDouble, departmentIDDouble)).thenReturn(teacherDouble);

        when(teacherRepositoryDouble.save(teacherDouble)).thenThrow(new RuntimeException("Database is currently down."));

        // Act
        Exception exception = assertThrows(Exception.class, () -> teacherService.registerTeacher(acronymDouble, nameDouble, emailDouble, nifDouble, phoneNumberDouble, academicBackgroundDouble,
                streetDouble, postalCodeDouble, locationDouble, countryDouble, departmentIDDouble));

        // Assert
        assertEquals("Database is currently down.", exception.getMessage());
    }

    @Test
    void shouldReturnTrueIfTeacherExistsWhenCallingExistsByID(){
        //Arrange
        TeacherID id = mock(TeacherID.class);
        when(teacherRepositoryDouble.containsOfIdentity(id)).thenReturn(true);
        //Act
        boolean result = teacherService.existsById(id);
        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTeacherDoesNotExistsWhenCallingExistsByID(){
        //Arrange
        TeacherID id = mock(TeacherID.class);
        when(teacherRepositoryDouble.containsOfIdentity(id)).thenReturn(false);
        //Act
        boolean result = teacherService.existsById(id);
        //Assert
        assertFalse(result);
    }
}