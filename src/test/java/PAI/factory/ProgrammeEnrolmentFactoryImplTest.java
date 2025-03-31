package PAI.factory;

import PAI.VOs.*;
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
        DegreeType_ID _degreeTypeID;
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

        AttributesForTestsWithoutIsolation() throws Exception {
            _address = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
            _studentID = new StudentID(1234567);
            _student = new Student(_studentID, new Name("Rita"), new NIF("123456789", new Country("Portugal")), new PhoneNumber("+351","963741258"), new Email("rita@gmail.com"), _address, new StudentAcademicEmail(_studentID));
            _accessMethod = new AccessMethodID();
            _date = new Date("14-02-2024");
            _degreeType = new DegreeType("Master", 240);
            _degreeTypeID = new DegreeType_ID("Master");
            _department = new Department("CSE", "Computer Science Engineer");
            _teacherCategoryID = new TeacherCategoryID();
            _addressFactory = new AddressFactoryImpl();
            _tcpFactory = new TeacherCareerProgressionFactoryImpl();
            _tcpLFactoryDouble = new TeacherCareerProgressionListFactoryImpl();
            _teacherID = TeacherID.createNew();
            _wp = new WorkingPercentage(100);
            _teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
                    "Doutoramento em Engenharia Informática, 2005, ISEP", "Rua São Tomé Porto",
                    "4249-015", "Porto", "Portugal", _addressFactory, _date, _teacherCategoryID, _wp, _teacherID, _department, _tcpFactory, _tcpLFactoryDouble);
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