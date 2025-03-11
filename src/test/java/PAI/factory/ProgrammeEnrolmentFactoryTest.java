package PAI.factory;

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

class ProgrammeEnrolmentFactoryTest {

    // Creation of actual attributes for tests without isolation
    private class AttributesForTestsWithoutIsolation {
        Address _address;
        Student _student;
        AccessMethod _accessMethod;
        DegreeType _degreeType;
        Department _department;
        TeacherCategory _teacherCategory;
        AddressFactory _addressFactory;
        TeacherCareerProgressionFactory _tcpFactory;
        TeacherCareerProgressionListFactory _tcpLFactoryDouble;
        Teacher _teacher;
        ProgrammeCourseListFactoryImpl _programmeCourseListFactoryImpl1;
        Programme _programme;
        String _date;
        CourseInStudyPlanFactoryImpl _courseInStudyPlanFactory;
        StudyPlanListFactoryImpl _studyPlanArrayListFactory;
        StudyPlanFactoryImpl _studyPlanFactory;
        CourseFactory _courseFactory;

        AttributesForTestsWithoutIsolation() throws Exception {
            _address = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
            _student = new Student("1234567", "Rita", "123456789", "963741258", "rita@gmail.com", _address);
            _accessMethod = new AccessMethod("M1");
            _date = "14-02-2024";
            _degreeType = new DegreeType("Master", 240);
            _department = new Department("CSE", "Computer Science Engineer");
            _teacherCategory = new TeacherCategory("Assistant Professor");
            _addressFactory = new AddressFactory();
            _tcpFactory = new TeacherCareerProgressionFactory();
            _tcpLFactoryDouble = new TeacherCareerProgressionListFactory();
            _teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                    "Doutoramento em Engenharia Informática, 2005, ISEP", "Rua São Tomé Porto",
                    "4249-015", "Porto", "Portugal", _addressFactory, "20-12-2010", _teacherCategory, 100, _department, _tcpFactory, _tcpLFactoryDouble);
            _programmeCourseListFactoryImpl1 = new ProgrammeCourseListFactoryImpl();
            _courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
            _studyPlanArrayListFactory = new StudyPlanListFactoryImpl();
            _studyPlanFactory = new StudyPlanFactoryImpl();
            _courseFactory = new CourseFactory();
            _programme = new Programme("Computer Engineering", "CE", 20, 6, _degreeType, _department, _teacher, _programmeCourseListFactoryImpl1, _courseInStudyPlanFactory, _studyPlanArrayListFactory, _studyPlanFactory, _courseFactory);
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
        //arrange
        ProgrammeEnrolmentFactory peFactory = new ProgrammeEnrolmentFactory();

        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        //act
        ProgrammeEnrolment programmeEnrolment = peFactory.createProgrammeEnrolment(attributes._student, attributes._accessMethod, attributes._programme, attributes._date);

        //assert

    }

    @Test
    void shouldCreateProgrammeEnrolmentWithIsolation() throws IllegalArgumentException {
        //arrange
        ProgrammeEnrolmentFactory peFactory = new ProgrammeEnrolmentFactory();

        Object[] doubles = createDoublesForTestsWithIsolation();
        Student studentDouble = (Student) doubles[0];
        AccessMethod accessMethodDouble = (AccessMethod) doubles[1];
        Programme programmeDouble = (Programme) doubles[2];
        String date = "14-02-2024";

        try (
                MockedConstruction<ProgrammeEnrolment> programmeEnrolmentDouble = mockConstruction(ProgrammeEnrolment.class, (mock, context) -> {
                    Student actualStudent = (Student) context.arguments().get(0);
                    AccessMethod actualAccessMethod = (AccessMethod) context.arguments().get(1);
                    Programme actualProgramme = (Programme) context.arguments().get(2);
                    String actualDate = (String) context.arguments().get(3);

                    assertEquals(studentDouble, actualStudent);
                    assertEquals(accessMethodDouble, actualAccessMethod);
                    assertEquals(programmeDouble, actualProgramme);
                    assertEquals(date, actualDate);
                })) {

        //act
            ProgrammeEnrolment result = peFactory.createProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, date);

        //assert
            //
            List<ProgrammeEnrolment> constructed = programmeEnrolmentDouble.constructed();
            ProgrammeEnrolment created = (constructed).get(0);
            assertEquals(created, result);
        }
    }

    @Test
    void shouldReturnExceptionIfStudentIsNullWithoutIsolation() throws Exception {
        //arrange
        ProgrammeEnrolmentFactory peFactory = new ProgrammeEnrolmentFactory();

        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> peFactory.createProgrammeEnrolment(null, attributes._accessMethod, attributes._programme, attributes._date));
    }

    @Test
    void shouldReturnExceptionIfStudentIsNullWithIsolation() {
        //arrange
        ProgrammeEnrolmentFactory peFactory = new ProgrammeEnrolmentFactory();

        Object[] doubles = createDoublesForTestsWithIsolation();
        AccessMethod accessMethodDouble = (AccessMethod) doubles[1];
        Programme programmeDouble = (Programme) doubles[2];
        String date = "14-02-2024";

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> peFactory.createProgrammeEnrolment(null, accessMethodDouble, programmeDouble, date));
    }

    @Test
    void shouldReturnExceptionIfAccessMethodIsNullWithoutIsolation() throws Exception {
        //arrange
        ProgrammeEnrolmentFactory peFactory = new ProgrammeEnrolmentFactory();

        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> peFactory.createProgrammeEnrolment(attributes._student, null, attributes._programme, attributes._date));
    }

    @Test
    void shouldReturnExceptionIfAccessMethodIsNullWithIsolation() {
        //arrange
        ProgrammeEnrolmentFactory peFactory = new ProgrammeEnrolmentFactory();

        Object[] doubles = createDoublesForTestsWithIsolation();
        Student studentDouble = (Student) doubles[0];
        Programme programmeDouble = (Programme) doubles[2];
        String date = "14-02-2024";

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> peFactory.createProgrammeEnrolment(studentDouble, null, programmeDouble, date));
    }

    @Test
    void shouldReturnExceptionIfProgrammeIsNullWithoutIsolation() throws Exception {
        //arrange
        ProgrammeEnrolmentFactory peFactory = new ProgrammeEnrolmentFactory();

        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> peFactory.createProgrammeEnrolment(attributes._student, attributes._accessMethod, null, attributes._date));
    }

    @Test
    void shouldReturnExceptionIfProgrammeIsNullWithIsolation() {
        //arrange
        ProgrammeEnrolmentFactory peFactory = new ProgrammeEnrolmentFactory();

        Object[] doubles = createDoublesForTestsWithIsolation();
        Student studentDouble = (Student) doubles[0];
        AccessMethod accessMethodDouble = (AccessMethod) doubles[1];
        String date = "14-02-2024";

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> peFactory.createProgrammeEnrolment(studentDouble, accessMethodDouble, null, date));
    }

    @Test
    void shouldReturnExceptionIfDateIsNullWithoutIsolation() throws Exception {
        //arrange
        ProgrammeEnrolmentFactory peFactory = new ProgrammeEnrolmentFactory();

        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> peFactory.createProgrammeEnrolment(attributes._student, attributes._accessMethod, attributes._programme, null));
    }

    @Test
    void shouldReturnExceptionIfDateIsNullWithIsolation() {
        //arrange
        ProgrammeEnrolmentFactory peFactory = new ProgrammeEnrolmentFactory();

        Object[] doubles = createDoublesForTestsWithIsolation();
        Student studentDouble = (Student) doubles[0];
        AccessMethod accessMethodDouble = (AccessMethod) doubles[1];
        Programme programmeDouble = (Programme) doubles[2];

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> peFactory.createProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, null));
    }

    public static Stream<Arguments> provideInvalidDateForTestWithoutIsolation() {
        return Stream.of(
                arguments(""),
                arguments(" "),
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
        //arrange
        ProgrammeEnrolmentFactory peFactory = new ProgrammeEnrolmentFactory();

        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> peFactory.createProgrammeEnrolment(attributes._student, attributes._accessMethod, attributes._programme, date));
    }

    public static Stream<Arguments> provideInvalidDateForTestWithIsolation() {
        return Stream.of(
                arguments(""),
                arguments(" "),
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
        ProgrammeEnrolmentFactory peFactory = new ProgrammeEnrolmentFactory();

        Object[] doubles = createDoublesForTestsWithIsolation();
        Student studentDouble = (Student) doubles[0];
        AccessMethod accessMethodDouble = (AccessMethod) doubles[1];
        Programme programmeDouble = (Programme) doubles[2];

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> peFactory.createProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, date));
    }
}