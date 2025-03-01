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
    void constructorAlwaysCreatesAnObject() throws Exception {
        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);
        AccessMethod am1 = new AccessMethod("M1");
        DegreeType master = new DegreeType("Master", 240);
        Department cse = new Department("CSE", "Computer Science Engineer");
        Teacher teacher = mock(Teacher.class);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, cse, teacher);


        //act
        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(student1, am1, p1,"20-03-2010");
    }

    public static Stream<Arguments> provideInvalidAttributes() {
        return Stream.of(
                arguments(""),
                arguments(" "),
                arguments((Object) null),
                arguments("2024-12-10"),
                arguments("10/12/2024"),
                arguments("10 de dezembro de 2024"),
                arguments("32-01-2024")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidAttributes")
    void invalidAttributesDoNotCreateObject(String date) throws Exception {
        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);
        AccessMethod am1 = new AccessMethod("M1");
        DegreeType master = new DegreeType("Master", 240);
        Department cse = new Department("CSE", "Computer Science Engineer");
        Teacher teacher = mock(Teacher.class);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, cse, teacher);

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolment(student1,am1,p1,date));
    }


    //findStudentInEnrolment
    @Test
    void shouldReturnStudentWhenNeeded() throws Exception {
        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);
        AccessMethod am1 = new AccessMethod("Concurso Nacional");

        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        Teacher teacher = mock(Teacher.class);
        Programme CE = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(student1,am1,CE,"17-09-2005");

        //act
        Student result = programmeEnrolment1.findStudentInEnrollments();

        //assert
        assertEquals(student1,result);
    }

    //hasSameEnrolment TESTS (4)
    @Test
    void shouldReturnTrueIfEnrolmentHasTheSameStudentAndTheSameProgramme() throws Exception {
        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);
        AccessMethod am1 = new AccessMethod("Concurso Nacional");

        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        Teacher teacher = mock(Teacher.class);
        Programme CE = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(student1,am1,CE,"17-09-2005");
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(student1,am1,CE,"15-10-2010");
        //act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        //assert
        assertTrue (result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentHasDifferentStudentsButTheSameProgramme() throws Exception {
        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        Address address2 = new Address("Avenida de Braga, nº17", "4450-897", "Coimbra", "Portugal");
        Student student2 = new Student(2, "Pedro", "159753824", "963996987", "pedro@gmail.com", address2);

        AccessMethod am1 = new AccessMethod("Concurso Nacional");
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        Teacher teacher = mock(Teacher.class);
        Programme CE = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(student2,am1,CE,"17-09-2005");
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(student1,am1,CE,"15-10-2010");
        //act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        //assert
        assertFalse (result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentHasTheSameStudentButDifferentProgrammes() throws Exception {
        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        AccessMethod am1 = new AccessMethod("Concurso Nacional");
        DegreeType master1 = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        Teacher teacher1 = mock(Teacher.class);
        Programme CE = new Programme("Computer Engineering", "CE", 20, 6, master1, CSE, teacher1);

        DegreeType master2 = new DegreeType("Master", 240);
        Department SSE = new Department("CSE", "Space Science Engineer");
        Teacher teacher2 = mock(Teacher.class);
        Programme SE = new Programme("Space Engineering", "SE", 20, 6, master2, SSE, teacher2);

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(student1,am1,CE,"17-09-2005");
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(student1,am1,SE,"15-10-2010");
        //act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        //assert
        assertFalse (result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentHasBothDifferentStudentsAndDifferentProgrammes() throws Exception {
        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        Address address2 = new Address("Avenida de Braga, nº17", "4450-897", "Coimbra", "Portugal");
        Student student2 = new Student(2, "Pedro", "159753824", "963996987", "pedro@gmail.com", address2);

        AccessMethod am1 = new AccessMethod("Concurso Nacional");
        DegreeType master1 = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        Teacher teacher1 = mock(Teacher.class);
        Programme CE = new Programme("Computer Engineering", "CE", 20, 6, master1, CSE, teacher1);

        DegreeType master2 = new DegreeType("Master", 240);
        Department SSE = new Department("CSE", "Space Science Engineer");
        Teacher teacher2 = mock(Teacher.class);
        Programme SE = new Programme("Space Engineering", "SE", 20, 6, master2, SSE, teacher2);

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(student1,am1,CE,"17-09-2005");
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(student2,am1,SE,"15-10-2010");
        //act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        //assert
        assertFalse (result);
    }

    //getProgramme
    @Test
    void shouldReturnAProgramme() throws Exception {
        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);
        AccessMethod am1 = new AccessMethod("Concurso Nacional");

        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        Teacher teacher = mock(Teacher.class);
        Programme CE = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(student1,am1,CE,"17-09-2005");

        //act
        Programme result = programmeEnrolment1.getProgramme();

        //assert
        assertEquals(CE,result);
    }
}