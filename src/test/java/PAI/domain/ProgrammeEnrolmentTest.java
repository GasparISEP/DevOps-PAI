package PAI.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.mock;

class ProgrammeEnrolmentTest {

    @Test
    void constructorAlwaysCreatesAnObjectWithoutIsolation() throws Exception {
        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        AccessMethod am1 = new AccessMethod("M1");

        DegreeType dt1 = new DegreeType("Master", 240);
        Department dpt1 = new Department("CSE", "Computer Science Engineer");
        TeacherCategory tc1 = new TeacherCategory("Assistant Professor");
        AddressFactory addressFactory = new AddressFactory();
        TeacherCareerProgressionFactory tcpFactory = new TeacherCareerProgressionFactory();
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informática, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", addressFactory,"20-12-2010", tc1, 100, dpt1, tcpFactory);
        ProgrammeCourseListFactory programmeCourseListFactory = new ProgrammeCourseListFactory();
        Programme programme1 = new Programme("Computer Engineering", "CE", 20, 6, dt1, dpt1, teacher1, programmeCourseListFactory);


        //act
        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(student1, am1, programme1,"20-03-2010");
    }

    @Test
    void constructorAlwaysCreatesAnObjectWithIsolation() {
        //arrange
        Student studentDouble = mock(Student.class);
        AccessMethod accessMethodDouble = mock(AccessMethod.class);
        Programme programmeDouble = mock(Programme.class);

        //act
        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble,"20-03-2010");
    }

    public static Stream<Arguments> provideInvalidDate() {
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
    @MethodSource("provideInvalidDate")
    void invalidDateDoesNotCreateObjectWithoutIsolation(String date) throws Exception {
        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        AccessMethod am1 = new AccessMethod("M1");

        DegreeType dt1 = new DegreeType("Master", 240);
        Department dpt1 = new Department("CSE", "Computer Science Engineer");
        TeacherCategory tc1 = new TeacherCategory("Assistant Professor");
        AddressFactory addressFactory = new AddressFactory();
        TeacherCareerProgressionFactory tcpFactory = new TeacherCareerProgressionFactory();
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informática, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", addressFactory,"20-12-2010", tc1, 100, dpt1, tcpFactory);
        ProgrammeCourseListFactory programmeCourseListFactory = new ProgrammeCourseListFactory();

        Programme programme1 = new Programme("Computer Engineering", "CE", 20, 6, dt1, dpt1, teacher1, programmeCourseListFactory);

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolment(student1, am1, programme1, date));
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
    void invalidDateDoesNotCreateObjectWithIsolation(String date) {
        //arrange
        Student studentDouble = mock(Student.class);
        AccessMethod accessMethodDouble = mock(AccessMethod.class);
        Programme programmeDouble = mock(Programme.class);

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, date));
    }

    @Test
    void shouldReturnStudentFromEnrolmentWithoutIsolation() throws Exception {
        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        AccessMethod am1 = new AccessMethod("Concurso Nacional");

        DegreeType dt1 = new DegreeType("Master", 240);

        Department dpt1 = new Department("CSE", "Computer Science Engineer");
        TeacherCategory tc1 = new TeacherCategory("Assistant Professor");
        AddressFactory addressFactory = new AddressFactory();
        TeacherCareerProgressionFactory tcpFactory = new TeacherCareerProgressionFactory();
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto","Portugal", addressFactory,"20-12-2010", tc1, 100, dpt1, tcpFactory);
        ProgrammeCourseListFactory programmeCourseListFactory = new ProgrammeCourseListFactory();

        Programme programme1 = new Programme("Computer Engineering", "CE", 20, 6, dt1, dpt1, teacher1, programmeCourseListFactory);

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(student1, am1, programme1,"17-09-2005");

        //act
        Student result = programmeEnrolment1.getStudentFromEnrolment();

        //assert
        assertEquals(student1,result);
    }

    @Test
    void shouldReturnStudentFromEnrolmentWithIsolation() {
        //arrange
        Student studentDouble = mock(Student.class);
        AccessMethod accessMethodDouble = mock(AccessMethod.class);
        Programme programmeDouble = mock(Programme.class);

        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble,"17-09-2005");

        //act
        Student result = programmeEnrolment.getStudentFromEnrolment();

        //assert
        assertEquals(studentDouble,result);
    }

    @Test
    void shouldReturnTrueIfEnrolmentHasTheSameStudentAndTheSameProgrammeWithoutIsolation() throws Exception {
        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);
        AccessMethod am1 = new AccessMethod("Concurso Nacional");

        DegreeType dt1 = new DegreeType("Master", 240);
        Department dpt1 = new Department("CSE", "Computer Science Engineer");
        TeacherCategory tc1 = new TeacherCategory("Assistant Professor");
        AddressFactory addressFactory = new AddressFactory();
        TeacherCareerProgressionFactory tcpFactory = new TeacherCareerProgressionFactory();
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", addressFactory,"20-12-2010", tc1, 100, dpt1, tcpFactory);
        ProgrammeCourseListFactory programmeCourseListFactory = new ProgrammeCourseListFactory();

        Programme programme1 = new Programme("Computer Engineering", "CE", 20, 6, dt1, dpt1, teacher1, programmeCourseListFactory);

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(student1, am1, programme1,"17-09-2005");
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(student1, am1, programme1,"15-10-2010");

        //act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueIfEnrolmentHasTheSameStudentAndTheSameProgrammeWithIsolation() {
        //arrange
        Student studentDouble = mock(Student.class);
        AccessMethod accessMethodDouble = mock(AccessMethod.class);
        AccessMethod accessMethodDouble2 = mock(AccessMethod.class);
        Programme programmeDouble = mock(Programme.class);

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble,"17-09-2005");
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(studentDouble, accessMethodDouble2, programmeDouble,"15-10-2010");

        //act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentHasDifferentStudentsButTheSameProgrammeWithoutIsolation() throws Exception {
        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        Address address2 = new Address("Avenida de Braga, nº17", "4450-897", "Coimbra", "Portugal");
        Student student2 = new Student(2, "Pedro", "159753824", "963996987", "pedro@gmail.com", address2);

        AccessMethod am1 = new AccessMethod("Concurso Nacional");

        DegreeType dt1 = new DegreeType("Master", 240);
        Department dpt1 = new Department("CSE", "Computer Science Engineer");
        TeacherCategory tc1 = new TeacherCategory("Assistant Professor");
        AddressFactory addressFactory = new AddressFactory();
        TeacherCareerProgressionFactory tcpFactory = new TeacherCareerProgressionFactory();
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", addressFactory,"20-12-2010", tc1, 100, dpt1, tcpFactory);
        ProgrammeCourseListFactory programmeCourseListFactory = new ProgrammeCourseListFactory();

        Programme programme1 = new Programme("Computer Engineering", "CE", 20, 6, dt1, dpt1, teacher1, programmeCourseListFactory);

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(student1, am1, programme1,"17-09-2005");
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(student2, am1, programme1,"17-09-2005");

        //act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentHasDifferentStudentsButTheSameProgrammeWithIsolation() {
        //arrange
        Student studentDouble = mock(Student.class);
        Student studentDouble2 = mock(Student.class);
        AccessMethod accessMethodDouble = mock(AccessMethod.class);
        Programme programmeDouble = mock(Programme.class);

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble,"17-09-2005");
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(studentDouble2, accessMethodDouble, programmeDouble,"15-10-2010");

        //act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentHasTheSameStudentButDifferentProgrammesWithoutIsolation() throws Exception {
        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        AccessMethod am1 = new AccessMethod("Concurso Nacional");
        TeacherCareerProgressionFactory tcpFactory = new TeacherCareerProgressionFactory();

        DegreeType dt1 = new DegreeType("Master", 240);
        Department dpt1 = new Department("CSE", "Computer Science Engineer");
        TeacherCategory tc1 = new TeacherCategory("Assistant Professor");
        AddressFactory addressFactory = new AddressFactory();

        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", addressFactory,"20-12-2010", tc1, 100, dpt1, tcpFactory);
        ProgrammeCourseListFactory programmeCourseListFactory1 = new ProgrammeCourseListFactory();

        Programme programme1 = new Programme("Computer Engineering", "CE", 20, 6, dt1, dpt1, teacher1, programmeCourseListFactory1);

        DegreeType dt2 = new DegreeType("Master", 240);
        Department dpt2 = new Department("CSE", "Space Science Engineer");
        TeacherCategory tc2 = new TeacherCategory("Assistant Professor");
        Teacher teacher2 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", addressFactory,"20-12-2010", tc2, 100, dpt2, tcpFactory);

        Programme programme2 = new Programme("Space Engineering", "SE", 20, 6, dt2, dpt2, teacher2, programmeCourseListFactory1);

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(student1, am1, programme1,"17-09-2005");
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(student1, am1, programme2,"15-10-2010");

        //act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        //assert
        assertFalse (result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentHasTheSameStudentButDifferentProgrammesWithIsolation() throws Exception {
        //arrange
        Student studentDouble = mock(Student.class);
        AccessMethod accessMethodDouble = mock(AccessMethod.class);
        Programme programmeDouble = mock(Programme.class);
        Programme programmeDouble2 = mock(Programme.class);


        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble,"17-09-2005");
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble2,"15-10-2010");

        //act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        //assert
        assertFalse (result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentHasBothDifferentStudentsAndDifferentProgrammesWithoutIsolation() throws Exception {
        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        Address address2 = new Address("Avenida de Braga, nº17", "4450-897", "Coimbra", "Portugal");
        Student student2 = new Student(2, "Pedro", "159753824", "963996987", "pedro@gmail.com", address2);

        AccessMethod am1 = new AccessMethod("Concurso Nacional");
        TeacherCareerProgressionFactory tcpFactory = new TeacherCareerProgressionFactory();

        DegreeType dt1 = new DegreeType("Master", 240);
        Department dpt1 = new Department("CSE", "Computer Science Engineer");
        TeacherCategory tc1 = new TeacherCategory("Assistant Professor");
        AddressFactory addressFactory = new AddressFactory();
        ProgrammeCourseListFactory programmeCourseListFactory = new ProgrammeCourseListFactory();

        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", addressFactory,"20-12-2010", tc1, 100, dpt1, tcpFactory);
        Programme programme1 = new Programme("Computer Engineering", "CE", 20, 6, dt1, dpt1, teacher1, programmeCourseListFactory);

        DegreeType dt2 = new DegreeType("Master", 240);
        Department dpt2 = new Department("CSE", "Space Science Engineer");
        TeacherCategory tc2 = new TeacherCategory("Assistant Professor");
        Teacher teacher2 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", addressFactory,"20-12-2010", tc2, 100, dpt2, tcpFactory);

        Programme programme2 = new Programme("Space Engineering", "SE", 20, 6, dt2, dpt2, teacher2, programmeCourseListFactory);

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(student1, am1, programme1,"17-09-2005");
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(student2, am1, programme2,"15-10-2010");

        //act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        //assert
        assertFalse (result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentHasBothDifferentStudentsAndDifferentProgrammesWithIsolation() {
        //arrange
        Student studentDouble = mock(Student.class);
        Student studentDouble2 = mock(Student.class);
        AccessMethod accessMethodDouble = mock(AccessMethod.class);
        Programme programmeDouble = mock(Programme.class);
        Programme programmeDouble2 = mock(Programme.class);

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble,"17-09-2005");
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(studentDouble2, accessMethodDouble, programmeDouble2,"15-10-2010");

        //act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        //assert
        assertFalse (result);
    }

    @Test
    void shouldReturnProgrammeFromEnrolmentWithoutIsolation() throws Exception {
        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);
        AccessMethod am1 = new AccessMethod("Concurso Nacional");

        DegreeType dt1 = new DegreeType("Master", 240);
        Department dpt1 = new Department("CSE", "Computer Science Engineer");
        TeacherCategory tc1 = new TeacherCategory("Assistant Professor");
        AddressFactory addressFactory = new AddressFactory();
        TeacherCareerProgressionFactory tcpFactory = new TeacherCareerProgressionFactory();
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", addressFactory,"20-12-2010", tc1, 100, dpt1, tcpFactory);
        ProgrammeCourseListFactory programmeCourseListFactory = new ProgrammeCourseListFactory();

        Programme programme1 = new Programme("Computer Engineering", "CE", 20, 6, dt1, dpt1, teacher1, programmeCourseListFactory);

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(student1, am1, programme1,"17-09-2005");

        //act
        Programme result = programmeEnrolment1.getProgrammeFromEnrolment();

        //assert
        assertEquals(programme1,result);
    }

    @Test
    void shouldReturnProgrammeFromEnrolmentWithIsolation() {
        //arrange
        Student studentDouble = mock(Student.class);
        AccessMethod accessMethodDouble = mock(AccessMethod.class);
        Programme programmeDouble = mock(Programme.class);

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble,"17-09-2005");

        //act
        Programme result = programmeEnrolment1.getProgrammeFromEnrolment();

        //assert
        assertEquals(programmeDouble,result);
    }
}