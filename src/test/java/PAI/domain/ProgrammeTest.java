package PAI.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProgrammeTest {

    //test ensures that the isInDepartment method returns false when the program is not associated with the department
    @Test
    void shouldReturnFalseWhenProgrammeIsNotInDepartment() throws Exception {
        // arrange
        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher t1 = new Teacher("CED", "Jane Doe", "ced@isep.ipp.pt", "100056789", "B107",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, department1);

        Department department2 = new Department("DEQ", "Departamento Engenharia Química");
        Programme P1 = new Programme("Licenciatura Engenharia Informática", "LEI", 25, 6, master, department1, t1);

        // act
        boolean result = P1.isInDepartment(department2);

        // assert
        assertFalse(result);
    }

    //test ensures that the isInDepartment method returns true when the program is associated with the department
    @Test
    void shouldReturnTrueWhenProgrammeIsNotInDepartment() throws Exception {
        // arrange
        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher t1 = new Teacher("CED", "Jane Doe", "ced@isep.ipp.pt", "100056789", "B107",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, department1);
        Programme P1 = new Programme("Licenciatura Engenharia Informática", "LEI", 25, 6, master, department1, t1);

        // act
        boolean result = P1.isInDepartment(department1);

        // assert
        assertTrue(result);
    }

    // Test to check the constructor
    @Test
    void createAProgramme () throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);
        //act + assert
        Programme CE = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);
    }


    // Test to empty name in Programme
    @Test
    void emptyNameDontCreateAProgramme () throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);

        //act + assert

        assertThrows(Exception.class, () -> new Programme("", "CE", 20,6,master,CSE,teacher1));
    }


    // Test to a null name in Programme
    @Test
    void nullNameDontCreateAProgramme () throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);

        //act + assert

        assertThrows(Exception.class, () -> new Programme(null, "CE", 20,6,master,CSE,teacher1));
    }


    // Test to empty Acronym in Programme
    @Test
    void emptyAcronymDontCreateAProgramme () throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);

        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "", 20,6,master,CSE,teacher1));
    }

    // Test to a null Acronym in Programme
    @Test
    void nullAcronymDontCreateAProgramme () throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);

        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", null, 20,6,master,CSE,teacher1));
    }

    // Test to check if negative number of ECTS dont create a programme
    @Test
    void lessThan0ECTSDontCreateAProgramme () throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);

        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", -1,6,master,CSE,teacher1));
    }

    // Teste to check if number 0 of ECTS dont create a programme
    @Test
    void zeroECTSDontCreateAProgramme () throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);

        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", 0,6,master,CSE,teacher1));
    }


    @Test
    void moreThan30ECTSDontCreateAProgramme () throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);

        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", 31,6,master,CSE,teacher1));

    }

    @Test
    void lessThanZeroSemestersDontCreateAProgramme () throws Exception {
        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);

        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", 31,-1,master,CSE,teacher1));

    }

    @Test
    void ZeroSemestersDontCreateAProgramme () throws Exception {
        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);

        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", 31,0,master,CSE,teacher1));

    }

    @Test
    void specialCharactersInNameDontCreateAProgramme () throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);

        //act + assert

        assertThrows(Exception.class, () -> new Programme("@Computer Science", "CE", 20,6,master,CSE,teacher1));
    }


    @Test
    void numbersInAcronymDontCreateAProgramme () throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);

        //act + assert

        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "123", 20,6,master,CSE,teacher1));
    }

    @Test
    void specialCharactersInAcronymDontCreateAProgramme () throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);

        //act + assert

        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "@CE", 20,6,master,CSE,teacher1));
    }

    @Test
    void equalsProgrammeReturnTrue () throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010",assistantProfessor,100, CSE);
        Programme CE = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);
        Programme CEE = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);

        //act
        boolean result = CE.equals(CEE);

        //assert
        assertTrue(result);
    }

    @Test
    void notEqualsProgrammeReturnFalse () throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);
        Programme CE = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);
        Programme CEE = new Programme("Computer Engineering", "CEE", 20,6,master,CSE,teacher);

        //act
        boolean result = CE.equals(CEE);

        //assert
        assertFalse(result);
    }

    @Test
    void equalsCompareObjectReturnTrue() throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);
        Programme CE = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);

        //act
        boolean result = CE.equals(CE);

        //assert
        assertTrue(result);
    }

    @Test
    void equalsDontCompareDifferentObjectAndReturnFalse() throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);
        Programme CE = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);

        //act
        boolean result = CE.equals(teacher);

        //assert
        assertFalse(result);
    }



    @Test
    void creatNewProgrammeDirector() throws Exception {
        //arrange

        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);
        Teacher teacher1 = new Teacher("ABC", "John Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        Programme CE = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);

        //act + assert
        CE.newProgrammeDirector(teacher1);
    }

    @Test
    void shouldReturnTrueIfTheEnrolmentInTheProgrammeIsSuccessful() throws Exception {
        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        AccessMethod am1 = new AccessMethod("Maiores 23");

        AccessMethodRepository amr = new AccessMethodRepository();
        amr.registerAccessMethod("Maiores 23");

        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);
        Programme CE = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);

        //act
        boolean result = CE.enrolStudentInProgramme(student1, am1, amr);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentIsNotSuccessfulBecauseAccessMethodIsNotRegistered() throws Exception {
        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        AccessMethod am1 = new AccessMethod("Maiores 23");

        AccessMethodRepository amr = new AccessMethodRepository();
        amr.registerAccessMethod("CNA");

        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010",assistantProfessor,100, CSE);
        Programme CE = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);

        //act + assert
        assertThrows(Exception.class, () -> CE.enrolStudentInProgramme(student1, am1, amr));
    }

    @Test
    void shouldReturnFalseIfEnrolmentIsNotSuccessfulBecauseStudentIsAlreadyEnrolledInTheProgramme() throws Exception {
        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        AccessMethod am1 = new AccessMethod("Maiores 23");

        AccessMethodRepository amr = new AccessMethodRepository();
        amr.registerAccessMethod("Maiores 23");

        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        Programme CE = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);

        //act
        CE.enrolStudentInProgramme(student1, am1, amr);

        //assert
        assertThrows(Exception.class, () -> CE.enrolStudentInProgramme(student1, am1, amr));
    }

    @Test
    void shouldEnrolSuccessfullyMoreThanOneStudent() throws Exception {
        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        Address address2 = new Address("Avenida de Braga, nº17", "4450-897", "Coimbra", "Portugal");
        Student student2 = new Student(2, "Pedro", "159753824", "963996987", "pedro@gmail.com", address2);

        AccessMethod am1 = new AccessMethod("Maiores 23");

        AccessMethodRepository amr = new AccessMethodRepository();
        amr.registerAccessMethod("Maiores 23");

        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);
        Programme CE = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);

        //act
        boolean result1 = CE.enrolStudentInProgramme(student1, am1, amr);
        boolean result2 = CE.enrolStudentInProgramme(student2, am1, amr);

        //assert
        assertTrue(result1);
        assertTrue(result2);
    }

    // AddCourseToASemesterOfProgramme tests

    @Test
    void shouldReturnTrueIfCourseIsAddedToAProgramme() throws Exception {
        //arrange
        DegreeType degreeTypeDouble = mock(DegreeType.class);
        Department departmentDouble = mock(Department.class);
        Teacher teacherDouble = mock(Teacher.class);
        Programme lei = new Programme("Engenharia Informática", "LEI", 30,
                2, degreeTypeDouble, departmentDouble, teacherDouble);
        Course courseDouble = mock(Course.class);
         //act
        boolean result = lei.addCourseToAProgramme(courseDouble);
        //assert
        assertTrue(result);
    }


    @Test
    void shouldThrowExceptionIfCourseAlreadyExistsInProgramme() throws Exception {
        //arrange
        DegreeType degreeTypeDouble = mock(DegreeType.class);
        Department departmentDouble = mock(Department.class);
        Teacher teacherDouble = mock(Teacher.class);
        Course courseDouble = mock(Course.class);
        Programme programme = new Programme("Engenharia Informática", "LEI", 30,
                2, degreeTypeDouble, departmentDouble, teacherDouble);
        programme.addCourseToAProgramme(courseDouble);

        //act & assert
        assertThrows(Exception.class, () -> programme.addCourseToAProgramme(courseDouble));
    }

    @Test
    void shouldIncreaseCourseListSizeWhenCourseIsAdded() throws Exception {
        // arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Nº100", "4435-696","Gondomar","Portugal", "20-12-2010", teacherCategory1,100, department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        CourseRepository courseRepository = new CourseRepository();
        courseRepository.registerCourse("matemática", "MTA", 5, 1);
        Course course1 = courseRepository.getAllCourses().get(0);
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1);
        int initialSize = lei.getCourseList().size();
        // act
        lei.addCourseToAProgramme(course1);
        // assert
        assertEquals(initialSize + 1, lei.getCourseList().size());
    }

    @Test
    void shouldContainAddedCourseInCourseList() throws Exception {
        // arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Nº100", "4435-696","Gondomar","Portugal", "20-12-2010", teacherCategory1,100, department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        CourseRepository courseRepository = new CourseRepository();
        courseRepository.registerCourse("matemática", "MTA", 5, 1);
        Course course1 = courseRepository.getAllCourses().get(0);
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1);
        // act
        lei.addCourseToAProgramme(course1);
        // assert
        assertTrue(lei.getCourseList().contains(course1));
    }

    //US17
    @Test
    void shouldReturnTrueIfStudentIsEnrolledInProgramme() throws Exception {
        // Arrange
        Address address = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student student = new Student(1, "João Silva", "123456789", "221234567", "joao123@gmail.com", address);
        DegreeType master = new DegreeType("Master", 240);
        Department department = new Department("CSE", "Computer Science Engineer");
        AccessMethod am1 = new AccessMethod("M1");
        AccessMethodRepository amr = new AccessMethodRepository();
        amr.registerAccessMethod("M1");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua São Tomé Porto", "4249-015", "Porto", "Portugal",
                "20-12-2010", assistantProfessor, 100, department);
        Programme programme = new Programme("Computer Engineering", "CE", 20, 6, master, department, teacher);
        programme.enrolStudentInProgramme(student,am1,amr);

        // Act
        boolean result = programme.isStudentEnrolled(student);

        // Assert
        assertTrue(result, "The student must be enrolled in the programme.");
    }

    //US17
    @Test
    void shouldReturnFalseIfStudentIsNotEnrolledInProgramme() throws Exception {
        // Arrange
        Address address = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student student = new Student(1, "João Silva", "123456789", "221234567", "joao123@gmail.com", address);
        Student student1 = new Student(2, "Joana Silva", "123000009", "221234567", "joana123@gmail.com", address);
        AccessMethod am1 = new AccessMethod("M1");
        AccessMethodRepository amr = new AccessMethodRepository();
        amr.registerAccessMethod("M1");
        DegreeType master = new DegreeType("Master", 240);
        Department department = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua São Tomé Porto", "4249-015", "Porto", "Portugal",
                "20-12-2010", assistantProfessor, 100, department);
        Programme programme = new Programme("Computer Engineering", "CE", 20, 6, master, department, teacher);
        programme.enrolStudentInProgramme(student,am1,amr);

        // Act
        boolean result = programme.isStudentEnrolled(student1);

        // Assert
        assertFalse(result, "The student should not be enrolled in the programme.");
    }
    //US17
    @Test
    void shouldReturnCorrectStudentFromEnrolment() throws Exception {
        // Arrange
        Address address = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student student = new Student(1, "João Silva", "123456789", "221234567", "joao123@gmail.com", address);
        DegreeType master = new DegreeType("Master", 240);
        AccessMethod am1 = new AccessMethod("M1");
        AccessMethodRepository amr = new AccessMethodRepository();
        amr.registerAccessMethod("M1");
        Department department = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua São Tomé Porto", "4249-015", "Porto", "Portugal",
                "20-12-2010", assistantProfessor, 100, department);
        Programme programme = new Programme("Computer Engineering", "CE", 20, 6, master, department, teacher);
        programme.enrolStudentInProgramme(student,am1,amr);
        Enrolment enrolment = new Enrolment(student, am1);

        // Act
        Student enrolledStudent = enrolment.findStudentInEnrollments();

        // Assert
        assertEquals(student, enrolledStudent, "The student found in the enrolment must be the same as the student created.");
    }

    //US17
    @Test
    void shouldReturnFalseWhenComparedWithNull() throws Exception {
        // Arrange
        DegreeType master = new DegreeType("Master", 240);
        Department department = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua São Tomé Porto", "4249-015", "Porto", "Portugal",
                "20-12-2010", assistantProfessor, 100, department);
        Programme programme = new Programme("Computer Engineering", "CE", 20, 6, master, department, teacher);

        // Act
        boolean result = programme.equals(null);

        // Assert
        assertFalse(result, "The equals method should return false when comparing with null.");
    }

    //US17
    @Test
    void shouldReturnFalseWhenComparedWithDifferentClassObject() throws Exception {
        // Arrange
        DegreeType master = new DegreeType("Master", 240);
        Department department = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua São Tomé Porto", "4249-015", "Porto", "Portugal",
                "20-12-2010", assistantProfessor, 100, department);
        Programme programme = new Programme("Computer Engineering", "CE", 20, 6, master, department, teacher);
        String differentClassObject = "Not a Programme";

        // Act
        boolean result = programme.equals(differentClassObject);

        // Assert
        assertFalse(result, "The equals method should return false when comparing with an object of a different class.");
    }

    @Test
    void shouldReturnCourseList() throws Exception {
        //arrange
        CourseRepository courseRepository = new CourseRepository();
        Course course1 = new Course("Programming", "PROG", 25, 1);
        Course course2 = new Course("Mathematics", "MATH", 25, 2);
        DegreeType master = new DegreeType("Master", 240);
        Department cse = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informática, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, cse);
        Programme programme = new Programme("Computer Engineering", "CE", 30, 6, master, cse, teacher);

        // act
        courseRepository.registerCourse("Programming", "PROG", 25, 1);
        courseRepository.registerCourse("Mathematics", "MATH", 25, 2);

        programme.addCourseToAProgramme(course1);
        programme.addCourseToAProgramme(course2);

        List<Course> courseList = programme.getCourseList();

        //assert
        assertEquals(2, courseList.size(), "O número de cursos deve ser 2");
        assertTrue(courseList.contains(course1), "A lista deve conter o curso Programming");
        assertTrue(courseList.contains(course2), "A lista deve conter o curso Mathematics");

    }

    @Test
    void shouldReturnQuantityOfSemesters() throws Exception {
        // Arrange
        DegreeType master = new DegreeType("Master", 240);
        Department cse = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informática, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, cse);
        Programme programme = new Programme("Computer Engineering", "CE", 30, 6, master, cse, teacher);

        // Act
        int quantityOfSemesters = programme.getQuantityOfSemester();

        // Assert
        assertEquals(6, quantityOfSemesters, "The quantity of semesters should be 6.");
    }

    @Test
    void shouldReturnCorrectQuantityOfEcts() throws Exception {
        // Arrange
        DegreeType master = new DegreeType("Master", 240);
        Department cse = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informática, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, cse);
        Programme programme = new Programme("Computer Engineering", "CE", 20, 6, master, cse, teacher);

        // Act
        int quantityOfEcts = programme.getQuantityOfEcts();

        // Assert
        assertEquals(20, quantityOfEcts, "The quantity of ECTS should be 20.");
    }

    @Test
    void shouldCalculateNumberOfYearsDirectly() throws Exception {
        DegreeType master = new DegreeType("Master", 240);
        Department cse = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informática, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, cse);
        Programme programme = new Programme("Computer Engineering", "CE", 20, 6, master, cse, teacher);
        // Testar valores pares
        assertEquals(1, programme.calculateNumberOfYears(2));
        assertEquals(3, programme.calculateNumberOfYears(6));
        assertEquals(10, programme.calculateNumberOfYears(20));

        // Testar valores ímpares
        assertEquals(2, programme.calculateNumberOfYears(3));
        assertEquals(4, programme.calculateNumberOfYears(7));
        assertEquals(6, programme.calculateNumberOfYears(11));

        // Testar valores extremos
        assertEquals(1, programme.calculateNumberOfYears(1));
        assertEquals(50, programme.calculateNumberOfYears(99));
    }

    @Test
    void shouldReturnStudyPlan() throws Exception {
        //arrange
        DegreeType master = new DegreeType("Master", 240);
        Department cse = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informática, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, cse);
        Programme programme = new Programme("Computer Engineering", "CE", 30, 6, master, cse, teacher);

        // act
        StudyPlan studyPlan = programme.getStudyPlan();

        //assert
        assertNotNull(studyPlan);
    }
}