package PAI.factory;

import PAI.VOs.StudentID;
import PAI.domain.*;
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
        Student _student;
        AccessMethod _accessMethod;
        DegreeType _degreeType;
        Department _department;
        TeacherCategory _teacherCategory;
        IAddressFactory _addressFactory;
        TeacherCareerProgressionFactoryImpl _tcpFactory;
        TeacherCareerProgressionListFactoryImpl _tcpLFactoryDouble;
        Teacher _teacher;
        ProgrammeCourseListFactoryImpl _programmeCourseListFactoryImpl1;
        Programme _programme;
        String _date;
        CourseInStudyPlanFactoryImpl _courseInStudyPlanFactory;
        StudyPlanListFactoryImpl _studyPlanArrayListFactory;
        StudyPlanFactoryImpl _studyPlanFactory;
        CourseFactoryImpl _courseFactoryImpl;

        AttributesForTestsWithoutIsolation() throws Exception {
            _address = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
            _student = new Student(new StudentID(1234567), "Rita", "123456789", "963741258", "rita@gmail.com", _address);
            _accessMethod = new AccessMethod("M1");
            _date = "14-02-2024";
            _degreeType = new DegreeType("Master", 240);
            _department = new Department("CSE", "Computer Science Engineer");
            _teacherCategory = new TeacherCategory("Assistant Professor");
            _addressFactory = new AddressFactoryImpl();
            _tcpFactory = new TeacherCareerProgressionFactoryImpl();
            _tcpLFactoryDouble = new TeacherCareerProgressionListFactoryImpl();
            _teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
                    "Doutoramento em Engenharia Informática, 2005, ISEP", "Rua São Tomé Porto",
                    "4249-015", "Porto", "Portugal", _addressFactory, "20-12-2010", _teacherCategory, 100, _department, _tcpFactory, _tcpLFactoryDouble);
            _programmeCourseListFactoryImpl1 = new ProgrammeCourseListFactoryImpl();
            _courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
            _studyPlanArrayListFactory = new StudyPlanListFactoryImpl();
            _studyPlanFactory = new StudyPlanFactoryImpl();
            _courseFactoryImpl = new CourseFactoryImpl();
            _programme = new Programme("Computer Engineering", "CE", 20, 6, _degreeType, _department, _teacher, _programmeCourseListFactoryImpl1, _courseInStudyPlanFactory, _studyPlanArrayListFactory, _studyPlanFactory, _courseFactoryImpl);
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
        return new Object[]{studentDouble, accessMethodDouble, programmeDouble};
    }

    @Test
    void shouldCreateProgrammeEnrolmentWithoutIsolation() throws Exception {
        //Arrange
        ProgrammeEnrolmentFactoryImpl peFactory = new ProgrammeEnrolmentFactoryImpl();

        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        //Act
        ProgrammeEnrolment programmeEnrolment = peFactory.createProgrammeEnrolment(attributes._student, attributes._accessMethod, attributes._programme, attributes._date);

        //Assert

    }

    @Test
    void shouldCreateProgrammeEnrolmentWithIsolation() throws IllegalArgumentException {
        //Arrange
        ProgrammeEnrolmentFactoryImpl peFactory = new ProgrammeEnrolmentFactoryImpl();

        Object[] doubles = createDoublesForTestsWithIsolation();
        Student studentDouble = (Student) doubles[0];
        AccessMethod accessMethodDouble = (AccessMethod) doubles[1];
        Programme programmeDouble = (Programme) doubles[2];
        String date = "14-02-2024";

        try (
                MockedConstruction<ProgrammeEnrolment> programmeEnrolmentDouble = mockConstruction(ProgrammeEnrolment.class, (mock, context) -> {

                })) {

        //Act
            ProgrammeEnrolment result = peFactory.createProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, date);

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
        assertThrows(IllegalArgumentException.class, () -> peFactory.createProgrammeEnrolment(null, attributes._accessMethod, attributes._programme, "14-02-2024"));
    }

    @Test
    void nullStudentDoesNotCreateObjectAndThrowsExceptionWithIsolation() {
        //arrange
        ProgrammeEnrolmentFactoryImpl peFactory = new ProgrammeEnrolmentFactoryImpl();

        Object[] doubles = createDoublesForTestsWithIsolation();
        AccessMethod accessMethodDouble = (AccessMethod) doubles[1];
        Programme programmeDouble = (Programme) doubles[2];

        try (
                MockedConstruction<ProgrammeEnrolment> programmeEnrolmentDouble = mockConstruction(ProgrammeEnrolment.class, (mock, context) -> {
                    throw new RuntimeException(new InstantiationException("Argument cannot be null"));
                })) {
            //Act
            try {
                peFactory.createProgrammeEnrolment(null, accessMethodDouble, programmeDouble, "14-02-2024");
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
        assertThrows(IllegalArgumentException.class, () -> peFactory.createProgrammeEnrolment(attributes._student, null, attributes._programme, "14-02-2024"));
    }

    @Test
    void nullAccessMethodDoesNotCreateObjectAndThrowsExceptionWithIsolation() {
        //arrange
        ProgrammeEnrolmentFactoryImpl peFactory = new ProgrammeEnrolmentFactoryImpl();

        Object[] doubles = createDoublesForTestsWithIsolation();
        Student studentDouble = (Student) doubles[0];
        Programme programmeDouble = (Programme) doubles[2];

        try (
                MockedConstruction<ProgrammeEnrolment> programmeEnrolmentDouble = mockConstruction(ProgrammeEnrolment.class, (mock, context) -> {
                    throw new RuntimeException(new InstantiationException("Argument cannot be null"));
                })) {
            //Act
            try {
                peFactory.createProgrammeEnrolment(studentDouble, null, programmeDouble, "14-02-2024");
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
        assertThrows(IllegalArgumentException.class, () -> peFactory.createProgrammeEnrolment(attributes._student, attributes._accessMethod, null, "14-02-2024"));
    }

    @Test
    void nullProgrammeDoesNotCreateObjectAndThrowsExceptionWithIsolation() {
        //arrange
        ProgrammeEnrolmentFactoryImpl peFactory = new ProgrammeEnrolmentFactoryImpl();

        Object[] doubles = createDoublesForTestsWithIsolation();
        Student studentDouble = (Student) doubles[0];
        AccessMethod accessMethodDouble = (AccessMethod) doubles[1];

        try (
                MockedConstruction<ProgrammeEnrolment> programmeEnrolmentDouble = mockConstruction(ProgrammeEnrolment.class, (mock, context) -> {
                    throw new RuntimeException(new InstantiationException("Argument cannot be null"));
                })) {
            //Act
            try {
                peFactory.createProgrammeEnrolment(studentDouble, accessMethodDouble, null, "14-02-2024");
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
        assertThrows(IllegalArgumentException.class, () -> peFactory.createProgrammeEnrolment(attributes._student, attributes._accessMethod, attributes._programme, null));
    }

    public static Stream<Arguments> provideBlankDateForTestWithoutIsolation() {
        return Stream.of(
                arguments(""),
                arguments(" ")
        );
    }

    @ParameterizedTest
    @MethodSource("provideBlankDateForTestWithoutIsolation")
    void blankDateDoesNotCreateObjectAndThrowsExceptionWithoutIsolation(String date) throws Exception {
        //Arrange
        ProgrammeEnrolmentFactoryImpl peFactory = new ProgrammeEnrolmentFactoryImpl();

        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> peFactory.createProgrammeEnrolment(attributes._student, attributes._accessMethod, attributes._programme, date));
    }

    @Test
    void shouldReturnExceptionIfDateIsNullOrBlankWithIsolation() {
        //Arrange
        ProgrammeEnrolmentFactoryImpl peFactory = new ProgrammeEnrolmentFactoryImpl();

        Object[] doubles = createDoublesForTestsWithIsolation();
        Student studentDouble = (Student) doubles[0];
        AccessMethod accessMethodDouble = (AccessMethod) doubles[1];
        Programme programmeDouble = (Programme) doubles[2];
        String date = "12-04-2019";

        try (
                MockedConstruction<ProgrammeEnrolment> programmeEnrolmentDouble = mockConstruction(ProgrammeEnrolment.class, (mock, context) -> {
                    throw new RuntimeException(new InstantiationException("Date cannot be empty!"));
                })) {
            //Act
            try {
                peFactory.createProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, date);
                fail("Expected exception not thrown");
            }
            catch (Exception e)
            {
            //Assert
                assertTrue(e.getCause().getMessage().contains("Date cannot be empty!"));
            }
        }
    }

    public static Stream<Arguments> provideInvalidDateForTestWithoutIsolation() {
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
    @MethodSource("provideInvalidDateForTestWithoutIsolation")
    void invalidDateDoesNotCreateObjectAndThrowsExceptionWithoutIsolation(String date) throws Exception {
        //Arrange
        ProgrammeEnrolmentFactoryImpl peFactory = new ProgrammeEnrolmentFactoryImpl();

        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> peFactory.createProgrammeEnrolment(attributes._student, attributes._accessMethod, attributes._programme, date));
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
        Student studentDouble = (Student) doubles[0];
        AccessMethod accessMethodDouble = (AccessMethod) doubles[1];
        Programme programmeDouble = (Programme) doubles[2];

        try (
                MockedConstruction<ProgrammeEnrolment> programmeEnrolmentDouble = mockConstruction(ProgrammeEnrolment.class, (mock, context) -> {
                    throw new RuntimeException(new InstantiationException("Invalid date. Please check whether the day, month, year or date format (dd-MM-yyyy) are correct."));
                })) {
            //Act
            try {
                peFactory.createProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, date);
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