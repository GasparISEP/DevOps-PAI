package PAI.factory;

import PAI.VOs.*;
import PAI.VOs.Location;
import PAI.domain.*;
import PAI.domain.programme.ProgrammeDDD;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedConstruction;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.*;

class ProgrammeEnrolmentFactoryImplTest {

    // Creation of actual attributes for tests without isolation
    private class AttributesForTestsWithoutIsolation {
        Address _address;
        StudentID _studentID;
        Student _student;
        AccessMethodID _accessMethod;
        DegreeType _degreeType;
        DegreeTypeID _degreeTypeID;
        Department _department;
        TeacherCategoryID _teacherCategoryID;
        IAddressFactory _addressFactory;
        TeacherCareerProgressionFactoryImpl _tcpFactory;
        TeacherCareerProgressionListFactoryImpl _tcpLFactoryDouble;
        Teacher _teacher;
        ProgrammeCourseListFactoryImpl _programmeCourseListFactoryImpl1;
        ProgrammeDDD _programme;
        Date _date;
        WorkingPercentage _wp;
        CourseInStudyPlanFactoryImpl _courseInStudyPlanFactory;
        StudyPlanListFactoryImpl _studyPlanArrayListFactory;
        StudyPlanFactoryImpl _studyPlanFactory;
        CourseFactoryImpl _courseFactoryImpl;
        TeacherID _teacherID;
        AddressVO _addressVO;
        Street _street;
        PostalCode _postalCode;
        Location _location;
        Country _country;
        TeacherAcronym _teacherAcronym;
        Name _name;
        Email _email;
        NIF _nif;
        PhoneNumber _phoneNumber;
        AcademicBackground _academicBackground;

        AttributesForTestsWithoutIsolation() throws Exception {
            _street = new Street("Praceta do Sol, nº19");
            _postalCode = new PostalCode("3745-144");
            _location = new Location("Tomar");
            _country = new Country("Portugal");
            _addressVO = new AddressVO(_street, _postalCode, _location, _country);
            _address = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
            _studentID = new StudentID(1234567);
            _student = new Student(_studentID, new Name("Rita"), new NIF("123456789", new Country("Portugal")), new PhoneNumber("+351","963741258"), new Email("rita@gmail.com"), _address, new StudentAcademicEmail(_studentID));
            _accessMethod = new AccessMethodID();
            _date = new Date("14-02-2024");
            _degreeType = new DegreeType("Master", 240);
            _degreeTypeID = new DegreeTypeID("Master");
            _department = new Department("CSE", "Computer Science Engineer");
            _teacherCategoryID = new TeacherCategoryID();
            _addressFactory = new AddressFactoryImpl();
            _tcpFactory = new TeacherCareerProgressionFactoryImpl();
            _tcpLFactoryDouble = new TeacherCareerProgressionListFactoryImpl();
            _teacherAcronym = new TeacherAcronym("ABC");
            _teacherID = new TeacherID(_teacherAcronym);
            _wp = new WorkingPercentage(100);
            _name = new Name("Joe Doe");
            _email = new Email("abc@isep.ipp.pt");
            _nif = new NIF("123456789", _country);
            _phoneNumber = new PhoneNumber("+351", "912 345 678");
            _academicBackground = new AcademicBackground("Doutoramento em Engenharia Informática, 2005, ISEP");
            _teacher = new Teacher(_teacherAcronym, _name, _email, _nif, _phoneNumber, _academicBackground, _addressVO, _department);
            _programmeCourseListFactoryImpl1 = new ProgrammeCourseListFactoryImpl();
            _courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
            _studyPlanArrayListFactory = new StudyPlanListFactoryImpl();
            _studyPlanFactory = new StudyPlanFactoryImpl();
            _courseFactoryImpl = new CourseFactoryImpl();
            _programme = new ProgrammeDDD(new NameWithNumbersAndSpecialChars("Computer Engineering"), new Acronym("CE"),
                    new QuantEcts(20), new QuantSemesters(6), _degreeTypeID,
                    _department, _teacherID);
        }
    }

    // Method to initialize attributes for tests without isolation
    private AttributesForTestsWithoutIsolation createActualAttributesForTestsWithoutIsolation() throws Exception {
        return new AttributesForTestsWithoutIsolation();
    }

    // Method to create doubles for tests with isolation
    private Object[] createDoublesForTestsWithIsolation() {
        Student studentDouble = mock(Student.class);
        AccessMethod accessMethodDouble = mock(AccessMethod.class);
        Programme programmeDouble = mock(Programme.class);
        Date dateDouble = mock(Date.class);
        return new Object[]{studentDouble, accessMethodDouble, programmeDouble, dateDouble};
    }

    @Test
    void shouldCreateProgrammeEnrolmentWithoutIsolation() throws Exception {
        //Arrange
        ProgrammeEnrolmentFactoryImpl peFactory = new ProgrammeEnrolmentFactoryImpl();

        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        //Act
        ProgrammeEnrolment programmeEnrolment = peFactory.createProgrammeEnrolment(attributes._student.identity(), attributes._accessMethod, attributes._programme.getProgrammeID(), attributes._date);

        //Assert

    }

    @Test
    void shouldCreateProgrammeEnrolmentWithIsolation() throws IllegalArgumentException {
        //Arrange
        ProgrammeEnrolmentFactoryImpl peFactory = new ProgrammeEnrolmentFactoryImpl();

        Object[] doubles = createDoublesForTestsWithIsolation();
        StudentID studentDouble = mock(StudentID.class);
        AccessMethodID accessMethodDouble = mock(AccessMethodID.class);
        ProgrammeID programmeDouble = mock(ProgrammeID.class);
        Date dateDouble = (Date) doubles[3];

        try (
                MockedConstruction<ProgrammeEnrolment> programmeEnrolmentDouble = mockConstruction(ProgrammeEnrolment.class, (mock, context) -> {

                })) {

        //Act
            ProgrammeEnrolment result = peFactory.createProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, dateDouble);

        //Assert
            //
            List<ProgrammeEnrolment> constructed = programmeEnrolmentDouble.constructed();
            ProgrammeEnrolment created = (constructed).get(0);
            assertEquals(created, result);
        }
    }

    @Test
    void nullStudentDoesNotCreateObjectAndThrowsExceptionWithoutIsolation() throws Exception {
        //Arrange
        ProgrammeEnrolmentFactoryImpl peFactory = new ProgrammeEnrolmentFactoryImpl();

        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> peFactory.createProgrammeEnrolment(null, attributes._accessMethod, attributes._programme.getProgrammeID(), attributes._date));
    }

    @Test
    void nullStudentDoesNotCreateObjectAndThrowsExceptionWithIsolation() {
        //arrange
        ProgrammeEnrolmentFactoryImpl peFactory = new ProgrammeEnrolmentFactoryImpl();

        Object[] doubles = createDoublesForTestsWithIsolation();
        AccessMethodID accessMethodDouble = mock(AccessMethodID.class);
        ProgrammeID programmeDouble = mock(ProgrammeID.class);
        Date dateDouble = (Date) doubles[3];

        try (
                MockedConstruction<ProgrammeEnrolment> programmeEnrolmentDouble = mockConstruction(ProgrammeEnrolment.class, (mock, context) -> {
                    throw new RuntimeException(new InstantiationException("Argument cannot be null"));
                })) {
            //Act
            try {
                peFactory.createProgrammeEnrolment(null, accessMethodDouble, programmeDouble, dateDouble);
                fail("Expected exception not thrown");
            }
            catch (Exception e)
            {
                //Assert
                assertTrue(e.getCause().getMessage().contains("Argument cannot be null"));
            }
        }
    }

    @Test
    void nullAccessMethodDoesNotCreateObjectAndThrowsExceptionWithoutIsolation() throws Exception {
        //Arrange
        ProgrammeEnrolmentFactoryImpl peFactory = new ProgrammeEnrolmentFactoryImpl();

        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> peFactory.createProgrammeEnrolment(attributes._student.identity(), null, attributes._programme.getProgrammeID(), attributes._date));
    }

    @Test
    void nullAccessMethodDoesNotCreateObjectAndThrowsExceptionWithIsolation() {
        //arrange
        ProgrammeEnrolmentFactoryImpl peFactory = new ProgrammeEnrolmentFactoryImpl();

        Object[] doubles = createDoublesForTestsWithIsolation();
        StudentID studentDouble = mock(StudentID.class);
        ProgrammeID programmeDouble = mock(ProgrammeID.class);
        Date dateDouble = (Date) doubles[3];

        try (
                MockedConstruction<ProgrammeEnrolment> programmeEnrolmentDouble = mockConstruction(ProgrammeEnrolment.class, (mock, context) -> {
                    throw new RuntimeException(new InstantiationException("Argument cannot be null"));
                })) {
            //Act
            try {
                peFactory.createProgrammeEnrolment(studentDouble, null, programmeDouble, dateDouble);
                fail("Expected exception not thrown");
            }
            catch (Exception e)
            {
                //Assert
                assertTrue(e.getCause().getMessage().contains("Argument cannot be null"));
            }
        }
    }

    @Test
    void nullProgrammeDoesNotCreateObjectAndThrowsExceptionWithoutIsolation() throws Exception {
        //Arrange
        ProgrammeEnrolmentFactoryImpl peFactory = new ProgrammeEnrolmentFactoryImpl();

        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> peFactory.createProgrammeEnrolment(attributes._student.identity(), attributes._accessMethod, null, attributes._date));
    }

    @Test
    void nullProgrammeDoesNotCreateObjectAndThrowsExceptionWithIsolation() {
        //arrange
        ProgrammeEnrolmentFactoryImpl peFactory = new ProgrammeEnrolmentFactoryImpl();

        Object[] doubles = createDoublesForTestsWithIsolation();
        StudentID studentDouble = mock(StudentID.class);
        AccessMethodID accessMethodDouble = mock(AccessMethodID.class);
        ProgrammeID programmeDouble = mock(ProgrammeID.class);
        Date dateDouble = (Date) doubles[3];

        try (
                MockedConstruction<ProgrammeEnrolment> programmeEnrolmentDouble = mockConstruction(ProgrammeEnrolment.class, (mock, context) -> {
                    throw new RuntimeException(new InstantiationException("Argument cannot be null"));
                })) {
            //Act
            try {
                peFactory.createProgrammeEnrolment(studentDouble, accessMethodDouble, null, dateDouble);
                fail("Expected exception not thrown");
            }
            catch (Exception e)
            {
                //Assert
                assertTrue(e.getCause().getMessage().contains("Argument cannot be null"));
            }
        }
    }

    @Test
    void nullDateDoesNotCreateObjectAndThrowsExceptionWithoutIsolation() throws Exception {
        //Arrange
        ProgrammeEnrolmentFactoryImpl peFactory = new ProgrammeEnrolmentFactoryImpl();

        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> peFactory.createProgrammeEnrolment(attributes._student.identity(), attributes._accessMethod, attributes._programme.getProgrammeID(),null));
    }

    @Test
    void shouldReturnExceptionIfDateIsNullOrBlankWithIsolation() {
        //Arrange
        ProgrammeEnrolmentFactoryImpl peFactory = new ProgrammeEnrolmentFactoryImpl();

        Object[] doubles = createDoublesForTestsWithIsolation();
        StudentID studentDouble = mock(StudentID.class);
        AccessMethodID accessMethodDouble = mock(AccessMethodID.class);
        ProgrammeID programmeDouble = mock(ProgrammeID.class);
        Date dateDouble = (Date) doubles[3];

        try (
                MockedConstruction<ProgrammeEnrolment> programmeEnrolmentDouble = mockConstruction(ProgrammeEnrolment.class, (mock, context) -> {
                    throw new RuntimeException(new InstantiationException("Date cannot be empty!"));
                })) {
            //Act
            try {
                peFactory.createProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, dateDouble);
                fail("Expected exception not thrown");
            }
            catch (Exception e)
            {
            //Assert
                assertTrue(e.getCause().getMessage().contains("Date cannot be empty!"));
            }
        }
    }

    public static Stream<Arguments> provideInvalidDateForTestWithIsolation() {
        return Stream.of(
                arguments("2024-12-10"),
                arguments("10/12/2024"),
                arguments("10 de dezembro de 2024"),
                arguments("32-01-2024"),
                arguments("30-100-2024"),
                arguments("340-100-2024")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidDateForTestWithIsolation")
    void invalidDateDoesNotCreateObjectAndThrowsExceptionWithIsolation(String date) {
        //arrange
        ProgrammeEnrolmentFactoryImpl peFactory = new ProgrammeEnrolmentFactoryImpl();

        Object[] doubles = createDoublesForTestsWithIsolation();
        StudentID studentDouble = mock(StudentID.class);
        AccessMethodID accessMethodDouble = mock(AccessMethodID.class);
        ProgrammeID programmeDouble = mock(ProgrammeID.class);
        Date dateDouble = (Date) doubles[3];

        try (
                MockedConstruction<ProgrammeEnrolment> programmeEnrolmentDouble = mockConstruction(ProgrammeEnrolment.class, (mock, context) -> {
                    throw new RuntimeException(new InstantiationException("Invalid date. Please check whether the day, month, year or date format (dd-MM-yyyy) are correct."));
                })) {
            //Act
            try {
                peFactory.createProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, dateDouble);
                fail("Expected exception not thrown");
            }
            catch (Exception e)
            {
                //Assert
                assertTrue(e.getCause().getMessage().contains("Invalid date. Please check whether the day, month, year or date format (dd-MM-yyyy) are correct."));
            }
        }
    }
}