package PAI.domain;

import PAI.factory.AddressFactory;
import PAI.factory.ProgrammeCourseListFactory;
import PAI.factory.TeacherCareerProgressionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEnrolmentTest {

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
        Teacher _teacher;
        ProgrammeCourseListFactory _programmeCourseListFactory;
        Programme _programme;

        AttributesForTestsWithoutIsolation() throws Exception {
            _address = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
            _student = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", _address);
            _accessMethod = new AccessMethod("M1");
            _degreeType = new DegreeType("Master", 240);
            _department = new Department("CSE", "Computer Science Engineer");
            _teacherCategory = new TeacherCategory("Assistant Professor");
            _addressFactory = new AddressFactory();
            _tcpFactory = new TeacherCareerProgressionFactory();
            _teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                    "Doutoramento em Engenharia Informática, 2005, ISEP", "Rua São Tomé Porto",
                    "4249-015", "Porto", "Portugal", _addressFactory, "20-12-2010", _teacherCategory, 100, _department, _tcpFactory);
            _programmeCourseListFactory = new ProgrammeCourseListFactory();
            _programme = new Programme("Computer Engineering", "CE", 20, 6, _degreeType, _department, _teacher, _programmeCourseListFactory);
        }
    }

    // Method to initialize attributes for tests without isolation
    private AttributesForTestsWithoutIsolation createActualAttributesForTestsWithoutIsolation() throws Exception {
        return new AttributesForTestsWithoutIsolation();
    }

    // Method to create doubles for tests with isolation
    private Object[] createDoublesForTestsWithIsolation() {
        Student student = mock(Student.class);
        AccessMethod am = mock(AccessMethod.class);
        Programme programme = mock(Programme.class);
        return new Object[]{student, am, programme};
    }

    @Test
    void constructorAlwaysCreatesAnObjectWithoutIsolation() throws Exception {
        //arrange
        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        //act
        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(attributes._student, attributes._accessMethod, attributes._programme, "20-03-2010");
    }

    @Test
    void constructorAlwaysCreatesAnObjectWithIsolation() {
        //arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        Student studentDouble = (Student) doubles[0];
        AccessMethod accessMethodDouble = (AccessMethod) doubles[1];
        Programme programmeDouble = (Programme) doubles[2];

        //act
        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, "20-03-2010");
    }

    public static Stream<Arguments> provideInvalidDateForTestWithoutIsolation() {
        return Stream.of(
                arguments(""),
                arguments(" "),
                arguments((String) null),
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
        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolment(attributes._student, attributes._accessMethod, attributes._programme, date));
    }

    public static Stream<Arguments> provideInvalidDateForTestWithIsolation() {
        return Stream.of(
                arguments(""),
                arguments(" "),
                arguments((String) null),
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
        Object[] doubles = createDoublesForTestsWithIsolation();
        Student studentDouble = (Student) doubles[0];
        AccessMethod accessMethodDouble = (AccessMethod) doubles[1];
        Programme programmeDouble = (Programme) doubles[2];

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, date));
    }

    @Test
    void shouldReturnTrueIfStudentIsTheSameFromEnrolmentWithoutIsolation() throws Exception {
        //arrange
        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(attributes._student, attributes._accessMethod, attributes._programme, "20-03-2010");

        //act
        boolean result = programmeEnrolment1.hasSameStudent(attributes._student);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueIfStudentIsTheSameFromEnrolmentWithIsolation() {
        //arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        Student studentDouble = (Student) doubles[0];
        AccessMethod accessMethodDouble = (AccessMethod) doubles[1];
        Programme programmeDouble = (Programme) doubles[2];

        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, "17-09-2005");

        when(studentDouble.hasSameUniqueNumber(studentDouble)).thenReturn(true);

        //act
        boolean result = programmeEnrolment.hasSameStudent(studentDouble);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfStudentIsNotTheSameFromEnrolmentWithoutIsolation() throws Exception {
        //arrange
        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        Address address2 = new Address("Avenida de Braga, nº17", "4450-897", "Coimbra", "Portugal");
        Student student2 = new Student(2, "Pedro", "159753824", "963996987", "pedro@gmail.com", address2);

        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(attributes._student, attributes._accessMethod, attributes._programme, "20-03-2010");

        //act
        boolean result = programmeEnrolment.hasSameStudent(student2);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfStudentIsNotTheSameFromEnrolmentWithIsolation() {
        //arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        Student studentDouble = (Student) doubles[0];
        AccessMethod accessMethodDouble = (AccessMethod) doubles[1];
        Programme programmeDouble = (Programme) doubles[2];

        ProgrammeEnrolment programmeEnrolmentDouble = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, "12-04-2020");

        when(studentDouble.hasSameUniqueNumber(studentDouble)).thenReturn(false);

        //act
        boolean result = programmeEnrolmentDouble.hasSameStudent(studentDouble);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfEnrolmentHasTheSameStudentAndTheSameProgrammeWithoutIsolation() throws Exception {
        //arrange
        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(attributes._student, attributes._accessMethod, attributes._programme, "20-03-2010");
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(attributes._student, attributes._accessMethod, attributes._programme, "05-03-2012");

        //act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueIfEnrolmentHasTheSameStudentAndTheSameProgrammeWithIsolation() {
        //arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        Student studentDouble = (Student) doubles[0];
        AccessMethod accessMethodDouble = (AccessMethod) doubles[1];
        Programme programmeDouble = (Programme) doubles[2];

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, "17-09-2005");
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, "15-10-2010");

        when(studentDouble.hasSameUniqueNumber(studentDouble)).thenReturn(true);
        when(programmeDouble.isEquals(programmeDouble)).thenReturn(true);

        //act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentHasDifferentStudentsButTheSameProgrammeWithoutIsolation() throws Exception {
        //arrange
        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        Address address2 = new Address("Avenida de Braga, nº17", "4450-897", "Coimbra", "Portugal");
        Student student2 = new Student(2, "Pedro", "159753824", "963996987", "pedro@gmail.com", address2);

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(attributes._student, attributes._accessMethod, attributes._programme, "20-03-2010");
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(student2, attributes._accessMethod, attributes._programme, "17-09-2005");

        //act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentHasDifferentStudentsButTheSameProgrammeWithIsolation() {
        //arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        Student studentDouble = (Student) doubles[0];
        AccessMethod accessMethodDouble = (AccessMethod) doubles[1];
        Programme programmeDouble = (Programme) doubles[2];

        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, "17-09-2005");

        when(studentDouble.hasSameUniqueNumber(studentDouble)).thenReturn(false);
        when(programmeDouble.isEquals(programmeDouble)).thenReturn(true);

        //act
        boolean result = programmeEnrolment.hasSameEnrolment(programmeEnrolment);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentHasTheSameStudentButDifferentProgrammesWithoutIsolation() throws Exception {
        //arrange
        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        DegreeType dt = new DegreeType("Master", 240);
        Department dpt = new Department("CSE", "Space Science Engineer");
        TeacherCategory tc = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", attributes._addressFactory, "20-12-2010", tc, 100, dpt, attributes._tcpFactory);
        Programme programme = new Programme("Space Engineering", "SE", 20, 6, dt, dpt, teacher, attributes._programmeCourseListFactory);

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(attributes._student, attributes._accessMethod, attributes._programme, "20-03-2010");
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(attributes._student, attributes._accessMethod, programme, "15-10-2010");

        //act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentHasTheSameStudentButDifferentProgrammesWithIsolation() throws Exception {
        //arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        Student studentDouble = (Student) doubles[0];
        AccessMethod accessMethodDouble = (AccessMethod) doubles[1];
        Programme programmeDouble = (Programme) doubles[2];

        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, "17-09-2005");

        when(studentDouble.hasSameUniqueNumber(studentDouble)).thenReturn(true);
        when(programmeDouble.isEquals(programmeDouble)).thenReturn(false);

        //act
        boolean result = programmeEnrolment.hasSameEnrolment(programmeEnrolment);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentHasBothDifferentStudentsAndDifferentProgrammesWithoutIsolation() throws Exception {
        //arrange
        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        Address address2 = new Address("Avenida de Braga, nº17", "4450-897", "Coimbra", "Portugal");
        Student student2 = new Student(2, "Pedro", "159753824", "963996987", "pedro@gmail.com", address2);

        TeacherCareerProgressionFactory tcpFactory = new TeacherCareerProgressionFactory();
        DegreeType dt2 = new DegreeType("Master", 240);
        Department dpt2 = new Department("CSE", "Space Science Engineer");
        TeacherCategory tc2 = new TeacherCategory("Assistant Professor");
        Teacher teacher2 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", attributes._addressFactory, "20-12-2010", tc2, 100, dpt2, tcpFactory);
        Programme programme2 = new Programme("Space Engineering", "SE", 20, 6, dt2, dpt2, teacher2, attributes._programmeCourseListFactory);

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(attributes._student, attributes._accessMethod, attributes._programme, "20-03-2010");
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(student2, attributes._accessMethod, programme2, "15-10-2010");

        //act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentHasBothDifferentStudentsAndDifferentProgrammesWithIsolation() {
        //arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        Student studentDouble = (Student) doubles[0];
        AccessMethod accessMethodDouble = (AccessMethod) doubles[1];
        Programme programmeDouble = (Programme) doubles[2];

        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, "17-09-2005");

        when(studentDouble.hasSameUniqueNumber(studentDouble)).thenReturn(false);
        when(programmeDouble.isEquals(programmeDouble)).thenReturn(false);

        //act
        boolean result = programmeEnrolment.hasSameEnrolment(programmeEnrolment);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfProgrammesAreTheSameWithoutIsolation() throws Exception {
        //arrange
        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(attributes._student, attributes._accessMethod, attributes._programme, "20-03-2010");

        //act
        boolean result = programmeEnrolment.hasSameProgramme(attributes._programme);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueIfProgrammesAreTheSameWithIsolation() {
        //arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        Student studentDouble = (Student) doubles[0];
        AccessMethod accessMethodDouble = (AccessMethod) doubles[1];
        Programme programmeDouble = (Programme) doubles[2];

        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, "17-09-2005");

        when(programmeDouble.isEquals(programmeDouble)).thenReturn(true);

        //act
        boolean result = programmeEnrolment.hasSameProgramme(programmeDouble);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfProgrammesAreNotTheSameWithoutIsolation() throws Exception {
        //arrange
        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        DegreeType dt = new DegreeType("Master", 240);
        Department dpt = new Department("CSE", "Space Science Engineer");
        TeacherCategory tc = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", attributes._addressFactory, "20-12-2010", tc, 100, dpt, attributes._tcpFactory);
        Programme programme = new Programme("Space Engineering", "SE", 20, 6, dt, dpt, teacher, attributes._programmeCourseListFactory);

        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(attributes._student, attributes._accessMethod, attributes._programme, "20-03-2010");

        //act
        boolean result = programmeEnrolment.hasSameProgramme(programme);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfProgrammesAreNotTheSameWithIsolation() {
        //arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        Student studentDouble = (Student) doubles[0];
        AccessMethod accessMethodDouble = (AccessMethod) doubles[1];
        Programme programmeDouble = (Programme) doubles[2];

        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, "17-09-2005");

        when(programmeDouble.isEquals(programmeDouble)).thenReturn(false);

        //act
        boolean result = programmeEnrolment.hasSameProgramme(programmeDouble);

        //assert
        assertFalse(result);
    }
}