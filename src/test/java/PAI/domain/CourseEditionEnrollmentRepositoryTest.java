package PAI.domain;

import PAI.controller.US24_IWantToKnowNumberOfStudentsEnrolledInCourseEdition;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CourseEditionEnrollmentRepositoryTest {


    @Test
    void shouldReturnTrueWithAValidCourseEditionEnrollment () throws Exception {
        //arrange
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository();
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student(123,"John","223445667","222333444","123@gmail.com",add1);
        Department CSE = new Department("DCE","Department of Computer Engineering");
        SchoolYear sy1 = new SchoolYear("2024/2025","14-10-2024","30-06-2025");
        DegreeType master = new DegreeType("Master",30);
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("JSM", "John Smith", "jsm@isep.ipp.pt", "123456789", "B180","PhD","Rua do Caminho","4554-565","Porto","Portugal","10-10-2024",assistantProfessor,50,CSE);
        CourseRepository courseRepository = new CourseRepository();
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        Course c1 = new Course("Development","DEV",5,1);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1,sy1);
        CourseEdition ce1 = new CourseEdition(c1,pe1);
        LocalDate currentDate = LocalDate.now();

        //act
        boolean result = repository.enrollStudentInACourseEdition(st1,ce1,currentDate);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueWithTwoValidCourseEditionEnrollments () throws Exception {
        //arrange
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository();
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student(123,"John","223445667","222333444","123@gmail.com",add1);
        Department CSE = new Department("DCE","Department of Computer Engineering");
        SchoolYear sy1 = new SchoolYear("2024/2025","14-10-2024","30-06-2025");
        DegreeType master = new DegreeType("Master",30);
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("JSM", "John Smith", "jsm@isep.ipp.pt", "123456789", "B180","PhD","Rua do Caminho","4554-565","Porto","Portugal","10-10-2024",assistantProfessor,50,CSE);
        CourseRepository courseRepository = new CourseRepository();
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        Course c1 = new Course("Development","DEV",5,1);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1,sy1);
        CourseEdition ce1 = new CourseEdition(c1,pe1);
        LocalDate currentDate = LocalDate.now();

        Address add2 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st2 = new Student(123,"John","223445667","222333444","123@gmail.com",add2);
        Department CSE1 = new Department("DCE","Department of Computer Engineering");
        SchoolYear sy2 = new SchoolYear("2024/2025","14-10-2024","30-06-2025");
        DegreeType master1 = new DegreeType("Master",30);
        TeacherCategory assistantProfessor1 = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("JSM", "John Smith", "jsm@isep.ipp.pt", "123456789", "B180","PhD","Rua do Caminho","4554-565","Porto","Portugal","10-10-2024",assistantProfessor1,50,CSE1);
        Programme p2 = new Programme("Computer Engineering", "CE", 20, 6, master1, CSE1, teacher1);
        Course c2 = new Course("Development","DEV",5,1);
        ProgrammeEdition pe2 = new ProgrammeEdition(p2,sy2);
        CourseEdition ce2 = new CourseEdition(c2,pe2);
        LocalDate currentDate1 = LocalDate.now();

        //act
        boolean result1 = repository.enrollStudentInACourseEdition(st1,ce1,currentDate);
        boolean result2 = repository.enrollStudentInACourseEdition(st2,ce2,currentDate1);

        //assert
        assertEquals(true,result1);
        assertEquals(true,result2);
    }

    @Test
    void shouldReturnFalseWhenCourseEditionEnrollmentAlreadyExists() throws Exception {
        //arrange
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository();
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student(123,"John","223445667","222333444","123@gmail.com",add1);
        Department CSE = new Department("DCE","Department of Computer Engineering");
        SchoolYear sy1 = new SchoolYear("2024/2025","14-10-2024","30-06-2025");
        DegreeType master = new DegreeType("Master",30);
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("JSM", "John Smith", "jsm@isep.ipp.pt", "123456789", "B180","PhD","Rua do Caminho","4554-565","Porto","Portugal","10-10-2024",assistantProfessor,50,CSE);
        CourseRepository courseRepository = new CourseRepository();
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        Course c1 = new Course("Development","DEV",5,1);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1,sy1);
        CourseEdition ce1 = new CourseEdition(c1,pe1);
        LocalDate currentDate = LocalDate.now();
        repository.enrollStudentInACourseEdition(st1,ce1,currentDate);

        //act
        boolean result2 = repository.enrollStudentInACourseEdition(st1,ce1,currentDate);

        //assert
        assertFalse(result2);
    }

    @Test
    void shouldConfirmStudentIsEnrollInACourseEdition () throws Exception {
        //arrange
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository();
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student(123,"John","223445667","222333444","123@gmail.com",add1);
        Department CSE = new Department("DCE","Department of Computer Engineering");
        SchoolYear sy1 = new SchoolYear("2024/2025","14-10-2024","30-06-2025");
        DegreeType master = new DegreeType("Master",30);
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("JSM", "John Smith", "jsm@isep.ipp.pt", "123456789", "B180","PhD","Rua do Caminho","4554-565","Porto","Portugal","10-10-2024",assistantProfessor,50,CSE);
        CourseRepository courseRepository = new CourseRepository();
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        Course c1 = new Course("Development","DEV",5,1);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1,sy1);
        CourseEdition ce1 = new CourseEdition(c1,pe1);
        LocalDate currentDate = LocalDate.now();

        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", new Address("Rua A", "4000-001", "Porto", "Portugal"));
        repository.enrollStudentInACourseEdition(student1, ce1, currentDate);

        //act
        //assert
        assertTrue(repository.isStudentEnrolledInCourseEdition(student1, ce1));
    }

    @Test
    void shouldConfirmStudentIsNotEnrollInACourseEdition () throws Exception {
        //arrange
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository();
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student(123,"John","223445667","222333444","123@gmail.com",add1);
        Department CSE = new Department("DCE","Department of Computer Engineering");
        SchoolYear sy1 = new SchoolYear("2024/2025","14-10-2024","30-06-2025");
        DegreeType master = new DegreeType("Master",30);
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("JSM", "John Smith", "jsm@isep.ipp.pt", "123456789", "B180","PhD","Rua do Caminho","4554-565","Porto","Portugal","10-10-2024",assistantProfessor,50,CSE);
        CourseRepository courseRepository = new CourseRepository();
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        Course c1 = new Course("Development","DEV",5,1);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1,sy1);
        CourseEdition ce1 = new CourseEdition(c1,pe1);
        LocalDate currentDate = LocalDate.now();

        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", new Address("Rua A", "4000-001", "Porto", "Portugal"));
        Student student2 = new Student(2, "João", "123456788", "963741257", "joao@gmail.com", new Address("Rua B", "4000-002", "Porto", "Portugal"));
        repository.enrollStudentInACourseEdition(student1, ce1, currentDate);

        //act
        //assert
        assertFalse(repository.isStudentEnrolledInCourseEdition(student2, ce1));

    }

    //US17
    @Test
    void shouldReturnCourseEditionEnrollmentWhenStudentIsEnrolled() throws Exception {
        // Arrange
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository();
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student(123,"John","223445667","222333444","123@gmail.com",add1);
        Department d1 = new Department("DCE","Department of Computer Engineering");
        SchoolYear sy1 = new SchoolYear("2024/2025","14-10-2024","30-06-2025");
        DegreeType dt1 = new DegreeType("Master",30);
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher pd1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, d1);
        CourseRepository courseRepository = new CourseRepository();
        Programme p1 = new Programme("SWITCH DEV","SDV",30,1,dt1,d1,pd1);
        Course c1 = new Course("Development","DEV",5,1);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1,sy1);
        CourseEdition ce1 = new CourseEdition(c1,pe1);
        LocalDate currentDate = LocalDate.now();
        repository.enrollStudentInACourseEdition(st1, ce1, currentDate);

        // Act
        Optional<CourseEditionEnrollment> result = repository.findByStudentAndEdition(st1, ce1);

        // Assert
        assertTrue(result.isPresent(), "The student was enrolled in the course edition successfully.");
        assertEquals(result.get().findStudentInCourseEditionEnrollment(), st1, "The student enrolled in the correct course edition.");
        assertEquals(result.get().findCourseEditionInEnrollment(), ce1, "The course edition enrolled is correct.");
    }

    //US17
    @Test
    void shouldReturnCourseEditionFromEnrollment() throws Exception {
        // Arrange
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository();
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student(123,"John","223445667","222333444","123@gmail.com",add1);
        Department d1 = new Department("DCE","Department of Computer Engineering");
        SchoolYear sy1 = new SchoolYear("2024/2025","14-10-2024","30-06-2025");
        DegreeType dt1 = new DegreeType("Master",30);
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher pd1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, d1);
        CourseRepository courseRepository = new CourseRepository();
        Programme p1 = new Programme("SWITCH DEV","SDV",30,1,dt1,d1,pd1);
        Course c1 = new Course("Development","DEV",5,1);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1,sy1);
        CourseEdition ce1 = new CourseEdition(c1,pe1);
        LocalDate currentDate = LocalDate.now();
        repository.enrollStudentInACourseEdition(st1, ce1, currentDate);

        // Act
        Optional<CourseEditionEnrollment> result = repository.findByStudentAndEdition(st1, ce1);

        // Assert
        assertTrue(result.isPresent(), "The student should be enrolled in the course edition.");
        assertEquals(ce1, result.get().findCourseEditionInEnrollment(), "The course edition returned should match the one in the enrollment.");
    }
    //US17
    @Test
    void shouldThrowExceptionWhenStudentOrCourseEditionIsNull() throws Exception {
        // Arrange
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository();
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student(123,"John","223445667","222333444","123@gmail.com",add1);
        Department d1 = new Department("DCE","Department of Computer Engineering");
        SchoolYear sy1 = new SchoolYear("2024/2025","14-10-2024","30-06-2025");
        DegreeType dt1 = new DegreeType("Master",30);
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher pd1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, d1);
        CourseRepository courseRepository = new CourseRepository();
        Programme p1 = new Programme("SWITCH DEV","SDV",30,1,dt1,d1,pd1);
        Course c1 = new Course("Development","DEV",5,1);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1,sy1);
        CourseEdition ce1 = new CourseEdition(c1,pe1);

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            repository.findByStudentAndEdition(null, ce1);
        });
        assertEquals("Student and CourseEdition cannot be null", thrown.getMessage());

        thrown = assertThrows(IllegalArgumentException.class, () -> {
            repository.findByStudentAndEdition(st1, null);
        });
        assertEquals("Student and CourseEdition cannot be null", thrown.getMessage());
    }
    //US17
    @Test
    void shouldReturnOptionalEmptyWhenNoEnrollmentFound() throws Exception {
        // Arrange
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository();
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student(123,"John","223445667","222333444","123@gmail.com",add1);
        Department d1 = new Department("DCE","Department of Computer Engineering");
        SchoolYear sy1 = new SchoolYear("2024/2025","14-10-2024","30-06-2025");
        DegreeType dt1 = new DegreeType("Master",30);
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher pd1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, d1);
        CourseRepository courseRepository = new CourseRepository();
        Programme p1 = new Programme("SWITCH DEV","SDV",30,1,dt1,d1,pd1);
        Course c1 = new Course("Development","DEV",5,1);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1,sy1);
        CourseEdition ce1 = new CourseEdition(c1,pe1);
        Student st2 = new Student(124,"Maria","223445679","256333444","124@gmail.com",add1);

        // Act
        Optional<CourseEditionEnrollment> result = repository.findByStudentAndEdition(st2, ce1);

        // Assert
        assertFalse(result.isPresent(), "The result should be empty if the student is not enrolled in the course edition.");
    }

    //US17
    @Test
    void shouldReturnEmptyWhenStudentIsNotEnrolledInCourseEdition() throws Exception {
        // Arrange
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository();
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student(123,"John","223445667","222333444","123@gmail.com",add1);
        Department d1 = new Department("DCE","Department of Computer Engineering");
        SchoolYear sy1 = new SchoolYear("2024/2025","14-10-2024","30-06-2025");
        DegreeType dt1 = new DegreeType("Master",30);
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher pd1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, d1);
        CourseRepository courseRepository = new CourseRepository();
        Programme p1 = new Programme("SWITCH DEV","SDV",30,1,dt1,d1,pd1);
        Course c1 = new Course("Development","DEV",5,1);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1,sy1);
        CourseEdition ce1 = new CourseEdition(c1,pe1);

        // Act
        Optional<CourseEditionEnrollment> result = repository.findByStudentAndEdition(st1, ce1);

        // Assert
        assertFalse(result.isPresent(), "Expected no enrollment to be found");
    }


    // US24

    @Test
    void shouldReturnNumberOfStudentsEnrolledInCourseEdition() throws Exception {
        CourseEditionEnrollmentRepository repo = new CourseEditionEnrollmentRepository();

        //create a course edition
        CourseRepository courseRepository = new CourseRepository();
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "24-03-2010", assistantProfessor, 80, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6, master, CSE, teacher);
        Course c1 = new Course ("Informatics", "INF", 6, 1);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition (p1, sY1);
        CourseEdition courseEdition1 = new CourseEdition(c1,pE1);

        //create a student
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        //create an enrollment
        LocalDate currentDate = LocalDate.now();
        repo.enrollStudentInACourseEdition(student1, courseEdition1, currentDate); //add student to the repo

        int studentsEnrolled = repo.numberOfStudentsEnrolledInCourseEdition(courseEdition1);

        assertEquals(1, studentsEnrolled);
    }

    @Test
    void ShouldReturnZeroWhenThereAreNoEnrolmentsInACourse() throws Exception {

        CourseEditionEnrollmentRepository repo = new CourseEditionEnrollmentRepository();

        //create a course edition
        CourseRepository courseRepository = new CourseRepository();
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", "24-03-2010", assistantProfessor, 80, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        Course c1 = new Course("Informatics", "INF", 6, 1);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition(p1, sY1);
        CourseEdition courseEdition1 = new CourseEdition(c1, pE1);

        //create a second Course Edition with the same Professor, department, degreetype, teachercategory and teacher
        Programme p2 = new Programme("Computer Science", "CS", 20, 6, master, CSE, teacher);
        Course c2 = new Course("Informatics", "INF", 6, 1);
        SchoolYear sY2 = new SchoolYear("Ano letivo de", "23-11-2023", "09-12-2024");
        ProgrammeEdition pE2 = new ProgrammeEdition(p2, sY2);
        CourseEdition courseEdition2 = new CourseEdition(c2, pE2);

        //create a student
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        Address address2 = new Address("Avenida Principal, nº10", "1000-001", "Lisboa", "Portugal");
        Student student2 = new Student(12345, "Paula", "222333444", "910000000", "paula@gmail.com", address2);

        //create an enrollment
        LocalDate currentDate = LocalDate.now();
        repo.enrollStudentInACourseEdition(student1, courseEdition2, currentDate); //add student to the repo
        repo.enrollStudentInACourseEdition(student2, courseEdition2, currentDate);

        int studentsEnrolled = repo.numberOfStudentsEnrolledInCourseEdition(courseEdition1);

        assertEquals(0, studentsEnrolled);
    }

    @Test
    void shouldReturnZeroWhenThereAreNoStudents() throws Exception {
        CourseEditionEnrollmentRepository repo = new CourseEditionEnrollmentRepository();

        //create a course edition
        CourseRepository courseRepository = new CourseRepository();
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "24-03-2010", assistantProfessor, 80, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);
        Course c1 = new Course ("Informatics", "INF", 6, 1);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition (p1, sY1);
        CourseEdition courseEdition1 = new CourseEdition(c1,pE1);

        int studentsEnrolled = repo.numberOfStudentsEnrolledInCourseEdition(courseEdition1);

        assertEquals(0, studentsEnrolled);
    }

    @Test
    void testIWantToKnowNumberOfStudentsEnrolledInCourseEdition_NullCourseEdition_ShouldThrowException() {
        // Arrange
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository();
        US24_IWantToKnowNumberOfStudentsEnrolledInCourseEdition controller = new US24_IWantToKnowNumberOfStudentsEnrolledInCourseEdition(repository);

        // Act
        int result = controller.IWantToKnowNumberOfStudentsEnrolledInCourseEdition(null);

        // Assert
        assertEquals(0, result);
    }

    //US28
    @Test
    public void removeExistingEnrollment() throws Exception {
        // arrange
        CourseEditionEnrollmentRepository enrollmentRepository = new CourseEditionEnrollmentRepository();
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal",
                "20-12-2010", assistantProfessor, 100, CSE);
        Programme programme = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        Student student = new Student(1, "John","223445667","222333444","123@gmail.com", new Address("Rua do Caminho", "4554-565", "Porto", "Portugal"));
        Course course = new Course("Programming 101", "P101", 6.0, 1);
        SchoolYear schoolYear = new SchoolYear("2025-2026", "01-09-2025", "31-07-2026");
        ProgrammeEdition programmeEdition = new ProgrammeEdition(programme, schoolYear);
        CourseEdition courseEdition = new CourseEdition(course, programmeEdition);

        // act
        enrollmentRepository.enrollStudentInACourseEdition(student, courseEdition, LocalDate.now());
        boolean result = enrollmentRepository.removeEnrollment(student, courseEdition);

        // assert
        assertTrue(result, "Enrollment should be removed successfully.");
    }

    @Test
    public void removeNonExistingEnrollment() throws Exception {
        // arrange
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", "20-12-2010",
                assistantProfessor, 100, CSE);
        Programme programme = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        Course course = new Course("Programming 101", "P101", 6.0, 1);
        SchoolYear schoolYear = new SchoolYear("2025-2026", "01-09-2025", "31-07-2026");
        ProgrammeEdition programmeEdition = new ProgrammeEdition(programme, schoolYear);
        CourseEdition courseEdition = new CourseEdition(course, programmeEdition);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository();

        Student student = new Student(1, "John","223445667","222333444","123@gmail.com",
                new Address("Rua do Caminho", "4554-565", "Porto", "Portugal"));

        // act and assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.removeEnrollment(student, courseEdition);
        });

        // assert
        assertEquals("Enrollment does not exist.", exception.getMessage());
    }

    @Test
    void removeEnrollment_WithNullCourseEditionOrStudent_ShouldThrowException() throws Exception {
        // arrange
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository();
        Student st1 = new Student(1, "John","223445667","222333444","123@gmail.com",
                new Address("Rua do Caminho", "4554-565", "Porto", "Portugal"));
        Department d1 = new Department("DCE","Department of Computer Engineering");
        SchoolYear sy1 = new SchoolYear("2024/2025","14-10-2024","30-06-2025");
        DegreeType dt1 = new DegreeType("Master",30);
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher pd1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, d1);
        Programme p1 = new Programme("SWITCH DEV","SDV",30,1,dt1,d1,pd1);
        Course c1 = new Course("Development","DEV",5,1);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1,sy1);
        CourseEdition ce1 = new CourseEdition(c1,pe1);

        // act and assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            repository.removeEnrollment(null, ce1);
        });
        assertEquals("Student and CourseEdition cannot be null", thrown.getMessage());

        thrown = assertThrows(IllegalArgumentException.class, () -> {
            repository.removeEnrollment(st1, null);
        });
        assertEquals("Student and CourseEdition cannot be null", thrown.getMessage());
    }

    @Test
    public void removeEnrollmentTwice_ShouldThrowExceptionOnSecondAttempt() throws Exception {
        // arrange
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository();
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", "20-12-2010",
                assistantProfessor, 100, CSE);
        Programme programme = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        Course course = new Course("Programming 101", "P101", 6.0, 1);
        SchoolYear schoolYear = new SchoolYear("2025-2026", "01-09-2025", "31-07-2026");
        ProgrammeEdition programmeEdition = new ProgrammeEdition(programme, schoolYear);
        CourseEdition courseEdition = new CourseEdition(course, programmeEdition);

        Student student = new Student(1, "John","223445667","222333444","123@gmail.com",
                new Address("Rua do Caminho", "4554-565", "Porto", "Portugal"));

        repository.enrollStudentInACourseEdition(student, courseEdition, LocalDate.now());

        // act: remove enrollment the first time
        boolean firstRemoval = repository.removeEnrollment(student, courseEdition);

        // assert first removal
        assertTrue(firstRemoval, "The first removal should succeed.");

        // act and assert: try removing again and expect an exception
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.removeEnrollment(student, courseEdition);
        });

        // assert second removal throws correct exception
        assertEquals("Enrollment does not exist.", exception.getMessage());
    }

}