package PAI.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
class ProgrammeTest {
    // Test to check the constructor
    @Test
    void createAProgramme () throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Address addressIsep = new Address("Rua São Tomé Porto","4249-015","Porto", "Portugal");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", addressIsep, assistantProfessor, CSE);

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
        Address addressIsep = new Address("Rua São Tomé Porto","4249-015","Porto", "Portugal");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", addressIsep, assistantProfessor, CSE);


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
        Address addressIsep = new Address("Rua São Tomé Porto","4249-015","Porto", "Portugal");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", addressIsep, assistantProfessor, CSE);


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
        Address addressIsep = new Address("Rua São Tomé Porto","4249-015","Porto", "Portugal");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", addressIsep, assistantProfessor, CSE);


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
        Address addressIsep = new Address("Rua São Tomé Porto","4249-015","Porto", "Portugal");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", addressIsep, assistantProfessor, CSE);


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
        Address addressIsep = new Address("Rua São Tomé Porto","4249-015","Porto", "Portugal");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", addressIsep, assistantProfessor, CSE);


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
        Address addressIsep = new Address("Rua São Tomé Porto","4249-015","Porto", "Portugal");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", addressIsep, assistantProfessor, CSE);


        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", 0,6,master,CSE,teacher1));
    }


    @Test
    void moreThan30ECTSDontCreateAProgramme () throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Address addressIsep = new Address("Rua São Tomé Porto","4249-015","Porto", "Portugal");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", addressIsep, assistantProfessor, CSE);



        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", 31,6,master,CSE,teacher1));

    }

    @Test
    void lessThanZeroSemestersDontCreateAProgramme () throws Exception {
        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Address addressIsep = new Address("Rua São Tomé Porto","4249-015","Porto", "Portugal");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", addressIsep, assistantProfessor, CSE);

        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", 31,-1,master,CSE,teacher1));

    }

    @Test
    void ZeroSemestersDontCreateAProgramme () throws Exception {
        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Address addressIsep = new Address("Rua São Tomé Porto","4249-015","Porto", "Portugal");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", addressIsep, assistantProfessor, CSE);


        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", 31,0,master,CSE,teacher1));

    }

    @Test
    void specialCharactersInNameDontCreateAProgramme () throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Address addressIsep = new Address("Rua São Tomé Porto","4249-015","Porto", "Portugal");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", addressIsep, assistantProfessor, CSE);


        //act + assert

        assertThrows(Exception.class, () -> new Programme("@Computer Science", "CE", 20,6,master,CSE,teacher1));
    }


    @Test
    void numbersInAcronymDontCreateAProgramme () throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Address addressIsep = new Address("Rua São Tomé Porto","4249-015","Porto", "Portugal");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", addressIsep, assistantProfessor, CSE);


        //act + assert

        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "123", 20,6,master,CSE,teacher1));
    }

    @Test
    void specialCharactersInAcronymDontCreateAProgramme () throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Address addressIsep = new Address("Rua São Tomé Porto","4249-015","Porto", "Portugal");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", addressIsep, assistantProfessor, CSE);


        //act + assert

        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "@CE", 20,6,master,CSE,teacher1));
    }

    @Test
    void equalsProgrammeReturnTrue () throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Address addressIsep = new Address("Rua São Tomé Porto","4249-015","Porto", "Portugal");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", addressIsep, assistantProfessor, CSE);
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
        Address addressIsep = new Address("Rua São Tomé Porto","4249-015","Porto", "Portugal");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", addressIsep, assistantProfessor, CSE);
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
        Address addressIsep = new Address("Rua São Tomé Porto","4249-015","Porto", "Portugal");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", addressIsep, assistantProfessor, CSE);
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
        Address addressIsep = new Address("Rua São Tomé Porto","4249-015","Porto", "Portugal");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", addressIsep, assistantProfessor, CSE);
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
        Address addressIsep = new Address("Rua São Tomé Porto","4249-015","Porto", "Portugal");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", addressIsep, assistantProfessor, CSE);
        Teacher teacher1 = new Teacher("ABC", "John Doe", "abc@isep.ipp.pt", "123456789", "B106", addressIsep, assistantProfessor, CSE);
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
        Address addressIsep = new Address("Rua São Tomé Porto","4249-015","Porto", "Portugal");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", addressIsep, assistantProfessor, CSE);
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
        Address addressIsep = new Address("Rua São Tomé Porto","4249-015","Porto", "Portugal");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", addressIsep, assistantProfessor, CSE);
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
        Address addressIsep = new Address("Rua São Tomé Porto","4249-015","Porto", "Portugal");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", addressIsep, assistantProfessor, CSE);
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
        Address addressIsep = new Address("Rua São Tomé Porto","4249-015","Porto", "Portugal");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", addressIsep, assistantProfessor, CSE);
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
    void shouldReturnTrueIfCourseIsAddedToASemesterOfAProgramme() throws Exception {
        //arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Address address1 = new Address("Rua São Tomé Nº100", "4435-696","Gondomar","Portugal");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123",address1, teacherCategory1,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Course course1 = new Course("matemática", "MTA", 5, 1);
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1);
        CourseRepository courseRepository = new CourseRepository();
        courseRepository.registerCourse("matemática", "MTA", 5, 1);
        //act
        boolean result = lei.addCourseToASemesterOfAProgramme(1, course1, courseRepository);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnExceptionIfSemesterDoesNotExistInAProgramme() throws Exception {
        //arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Address address1 = new Address("Rua São Tomé Nº100", "4435-696","Gondomar","Portugal");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123",address1, teacherCategory1,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Course course1 = new Course("matemática", "MTA", 5, 1);
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1);
        CourseRepository courseRepository = new CourseRepository();
        //act & assert
        assertThrows(Exception.class, () -> lei.addCourseToASemesterOfAProgramme(3, course1, courseRepository));
    }

    @Test
    void shouldReturnExceptionIfSemesterIsNotPositive() throws Exception {
        //arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Address address1 = new Address("Rua São Tomé Nº100", "4435-696","Gondomar","Portugal");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123",address1, teacherCategory1,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Course course1 = new Course("matemática", "MTA", 5, 1);
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1);
        CourseRepository courseRepository = new CourseRepository();
        //act & assert
        assertThrows(Exception.class, () -> lei.addCourseToASemesterOfAProgramme(0, course1, courseRepository));
    }

    @Test
    void shouldReturnExceptionIfCourseInNull() throws Exception {
        //arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Address address1 = new Address("Rua São Tomé Nº100", "4435-696","Gondomar","Portugal");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123",address1, teacherCategory1,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Course course1 = null;
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1);
        CourseRepository courseRepository = new CourseRepository();
        courseRepository.isCourseRegistered(course1);
        //act & assert
        assertThrows(Exception.class, () -> lei.addCourseToASemesterOfAProgramme(2, course1, courseRepository));
    }

    @Test
    void shouldReturnExceptionIfCourseIsNotInTheSystem() throws Exception {
        //arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Address address1 = new Address("Rua São Tomé Nº100", "4435-696","Gondomar","Portugal");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123",address1, teacherCategory1,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Course course1 = new Course("matemática", "MTA", 5, 1);
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1);
        CourseRepository courseRepository = new CourseRepository();
        //act & assert
        assertThrows(Exception.class, () -> lei.addCourseToASemesterOfAProgramme(2, course1, courseRepository));
    }

    @Test
    void shouldReturnExceptionIfAnualCourseAlreadyExistsInTwoDifferentSemestersInAProgramme() throws Exception {
        //arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Address address1 = new Address("Rua São Tomé Nº100", "4435-696","Gondomar","Portugal");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123",address1, teacherCategory1,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Course course1 = new Course("matemática", "MTA", 5, 2);
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 4, degree1, department1, teacher1);
        CourseRepository courseRepository = new CourseRepository();
        courseRepository.registerCourse("matemática", "MTA", 5, 2);
        lei.addCourseToASemesterOfAProgramme(1, course1, courseRepository);
        lei.addCourseToASemesterOfAProgramme(2, course1, courseRepository);
        //act & assert
        assertThrows(Exception.class, () -> lei.addCourseToASemesterOfAProgramme(3, course1, courseRepository));
    }

    @Test
    void shouldReturnExceptionIfCourseAlreadyExistsInAProgramme() throws Exception {
        //arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Address address1 = new Address("Rua São Tomé Nº100", "4435-696","Gondomar","Portugal");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123",address1, teacherCategory1,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Course course1 = new Course("matemática", "MTA", 5, 1);
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 4, degree1, department1, teacher1);
        CourseRepository courseRepository = new CourseRepository();
        courseRepository.registerCourse("matemática", "MTA", 5, 1);
        lei.addCourseToASemesterOfAProgramme(1, course1, courseRepository);
        //act & assert
        assertThrows(Exception.class, () -> lei.addCourseToASemesterOfAProgramme(2, course1, courseRepository));
    }

    @ParameterizedTest
    @ValueSource(ints = {2,3,4,5,6})
    void shouldReturnExceptionIfAnualCourseIsBeingAddedToADifferentYearInAProgramme_test1(int semester) throws Exception {
        //arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Address address1 = new Address("Rua São Tomé Nº100", "4435-696","Gondomar","Portugal");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123",address1, teacherCategory1,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Course course1 = new Course("matemática", "MTA", 6, 2);
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 4, degree1, department1, teacher1);
        CourseRepository courseRepository = new CourseRepository();
        courseRepository.registerCourse("matemática", "MTA", 6, 2);
        lei.addCourseToASemesterOfAProgramme(2, course1, courseRepository);
        //act & assert
        assertThrows(Exception.class, () -> lei.addCourseToASemesterOfAProgramme(semester, course1, courseRepository));
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,5,6})
    void shouldReturnExceptionIfAnualCourseIsBeingAddedToADifferentYearInAProgramme_test2(int semester) throws Exception {
        //arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Address address1 = new Address("Rua São Tomé Nº100", "4435-696","Gondomar","Portugal");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123",address1, teacherCategory1,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Course course1 = new Course("matemática", "MTA", 6, 2);
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 4, degree1, department1, teacher1);
        CourseRepository courseRepository = new CourseRepository();
        courseRepository.registerCourse("matemática", "MTA", 6, 2);
        lei.addCourseToASemesterOfAProgramme(3, course1, courseRepository);
        //act & assert
        assertThrows(Exception.class, () -> lei.addCourseToASemesterOfAProgramme(semester, course1, courseRepository));
    }

    @Test
    void shouldReturnTrueIfAnualCourseIsBeingAddedToTheSameYearInAProgramme() throws Exception {
        //arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Address address1 = new Address("Rua São Tomé Nº100", "4435-696","Gondomar","Portugal");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123",address1, teacherCategory1,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Course estagio = new Course("matemática", "MTA", 6, 2);
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1);
        CourseRepository courseRepository = new CourseRepository();
        courseRepository.registerCourse("matemática", "MTA", 6, 2);
        lei.addCourseToASemesterOfAProgramme(2, estagio, courseRepository);
        //act
        boolean result = lei.addCourseToASemesterOfAProgramme(1, estagio, courseRepository);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnExceptionIfCourseAddedToASemesterSurpassTheLimtitCreditECTSForThatSemester() throws Exception {
        //arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Address address1 = new Address("Rua São Tomé Nº100", "4435-696","Gondomar","Portugal");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123",address1, teacherCategory1,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Course course1 = new Course("matemática", "MTA", 20, 1);
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1);
        CourseRepository courseRepository = new CourseRepository();
        courseRepository.registerCourse("matemática", "MTA", 20, 1);
        //act & assert
        assertThrows(Exception.class, () -> lei.addCourseToASemesterOfAProgramme(1, course1, courseRepository));
    }

    @Test
    void shouldReturnFalseIfCourseToBeAddedAlreadyExistsInSemesterOfAProgramme() throws Exception {
        //arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Address address1 = new Address("Rua São Tomé Nº100", "4435-696","Gondomar","Portugal");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123",address1, teacherCategory1,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Course course1 = new Course("matemática", "MTA", 5, 1);
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1);
        CourseRepository courseRepository = new CourseRepository();
        courseRepository.registerCourse("matemática", "MTA", 5, 1);
        lei.addCourseToASemesterOfAProgramme(1, course1, courseRepository);
        //act & assert
        assertThrows(Exception.class, () -> lei.addCourseToASemesterOfAProgramme(1, course1, courseRepository));
    }

    @Test
    void shouldReturnTrueIfCourseIsAddedToASemesterOfAProgrammeThatContainsOtherDifferentCourses() throws Exception {
        //arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Address address1 = new Address("Rua São Tomé Nº100", "4435-696","Gondomar","Portugal");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123",address1, teacherCategory1,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Course course1 = new Course("matemática", "MTA", 5, 1);
        Course course2 = new Course("português", "POR", 5, 1);
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1);
        CourseRepository courseRepository = new CourseRepository();
        courseRepository.registerCourse("matemática", "MTA", 5, 1);
        courseRepository.registerCourse("português", "POR", 5, 1);
        lei.addCourseToASemesterOfAProgramme(1, course1, courseRepository);
        //act
        boolean result = lei.addCourseToASemesterOfAProgramme(1, course2, courseRepository);
        //assert
        assertTrue(result);
    }
}