package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsControllerTest {

    @Test
    void testEnrollStudentInProgrammeEdition_Success() throws Exception {
        // Arrange
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository();
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = new ProgrammeEditionEnrollmentRepo();
        ProgrammeList programmeList = new ProgrammeList();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = new CourseEditionEnrollmentRepository();
        StudentRepository studentRepository = new StudentRepository();
        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrollmentRepo,
                        programmeEditionRepository,
                        programmeList,
                        courseEditionEnrollmentRepository,
                        courseEditionRepository,
                        studentRepository);
        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Address add1 = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123666789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto",
                "Portugal", "20-12-2010", assistantProfessor, 100, department1);
        SchoolYear schoolYear = new SchoolYear("Academic Year", "23-11-2024", "09-12-2025");
        AccessMethodRepository amr = new AccessMethodRepository();
        AccessMethod am1 = new AccessMethod("Over 23");
        amr.registerAccessMethod("Over 23");
        Student student = new Student(1, "João Silva", "999999999", "221234567", "joao123@gmail.com", add1);
        studentRepository.registerStudent(1, "João Silva", "999999999", "221234567", "joao123@gmail.com", add1);
        Enrolment enrolment = new Enrolment(student, am1);
        CourseRepository courseRepository = new CourseRepository();
        Programme programme1 = new Programme("Computer Science Engineering", "CSE", 25, 6, master, department1, teacher1, courseRepository);
        programmeList.registerProgramme("Computer Science Engineering", "CSE", 25, 6, master, department1, teacher1, courseRepository);
        if (!programme1.isStudentEnrolled(student)) {
            programme1.enrolStudentInProgramme(student, am1, amr);
        }
        programmeEditionRepository.createProgrammeEdition(programme1, schoolYear);

        // Act
        Optional<ProgrammeEdition> result = controller.enrollStudentInProgrammeEdition(student, programme1, schoolYear);

        // Assert
        assertTrue(result.isPresent(), "The student is enrolled in the ProgrammeEdition.");
        ProgrammeEdition programmeEdition = result.get();
        assertEquals(programmeEditionRepository.findProgrammeEditionBySchoolYearAndProgramme(programme1, schoolYear).get(), programmeEdition,
                "The returned ProgrammeEdition should be the same.");
        assertTrue(programmeEditionEnrollmentRepo.isStudentEnrolledInThisProgrammeEdition(student, programmeEdition),
                "The student should be enrolled in the ProgrammeEdition.");
        assertTrue(programme1.isStudentEnrolled(student), "The student should be enrolled in the programme.");
    }

    @Test
    void testEnrollStudentInProgrammeEdition_StudentNotEnrolledInProgramme() throws Exception {
        // Arrange
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository();
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = new ProgrammeEditionEnrollmentRepo();
        ProgrammeList programmeList = new ProgrammeList();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = new CourseEditionEnrollmentRepository();
        StudentRepository studentRepository = new StudentRepository();
        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrollmentRepo,
                        programmeEditionRepository,
                        programmeList,
                        courseEditionEnrollmentRepository,
                        courseEditionRepository,
                        studentRepository);
        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Address add1 = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123666789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto",
                "Portugal", "20-12-2010", assistantProfessor, 100, department1);
        SchoolYear schoolYear = new SchoolYear("Academic Year", "23-11-2024", "09-12-2025");
        CourseRepository courseRepository = new CourseRepository();
        Programme programme1 = new Programme("Computer Science Engineering", "CSE", 25, 6, master, department1, teacher1, courseRepository);
        Student student = new Student(1, "João Silva", "999999999", "221234567", "joao123@gmail.com", add1);

        // Act & Assert
        IllegalStateException exception = assertThrows(IllegalStateException.class, () ->
                controller.enrollStudentInProgrammeEdition(student, programme1, schoolYear));
        assertEquals("Student not enrolled in Programme.", exception.getMessage());
    }

    @Test
    void testEnrollStudentInProgrammeEdition_ProgrammeEditionNotFound() throws Exception {
        // Arrange
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository();
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = new ProgrammeEditionEnrollmentRepo();
        ProgrammeList programmeList = new ProgrammeList();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = new CourseEditionEnrollmentRepository();
        StudentRepository studentRepository = new StudentRepository();
        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrollmentRepo,
                        programmeEditionRepository,
                        programmeList,
                        courseEditionEnrollmentRepository,
                        courseEditionRepository,
                        studentRepository);
        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Address add1 = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123666789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto",
                "Portugal", "20-12-2010", assistantProfessor, 100, department1);
        SchoolYear schoolYear = new SchoolYear("Academic Year", "23-11-2024", "09-12-2025");
        AccessMethodRepository amr = new AccessMethodRepository();
        AccessMethod am1 = new AccessMethod("Over 23");
        amr.registerAccessMethod("Over 23");
        CourseRepository courseRepository = new CourseRepository();
        Programme programme1 = new Programme("Computer Science Engineering", "CSE", 25, 6, master, department1, teacher1, courseRepository);
        Student student = new Student(1, "João Silva", "999999999", "221234567", "joao123@gmail.com", add1);

        programme1.enrolStudentInProgramme(student, am1,amr);

        // Act & Assert
        IllegalStateException exception = assertThrows(IllegalStateException.class, () ->
                controller.enrollStudentInProgrammeEdition(student, programme1, schoolYear));
        assertEquals("Programme edition not found for the given school year.", exception.getMessage());
    }

    @Test
    void testEnrollStudentInProgrammeEdition_StudentAlreadyEnrolledInEdition() throws Exception {
        // Arrange
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository();
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = new ProgrammeEditionEnrollmentRepo();
        ProgrammeList programmeList = new ProgrammeList();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = new CourseEditionEnrollmentRepository();
        StudentRepository studentRepository = new StudentRepository();
        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrollmentRepo,
                        programmeEditionRepository,
                        programmeList,
                        courseEditionEnrollmentRepository,
                        courseEditionRepository,
                        studentRepository);

        DegreeType master = new DegreeType("Master", 240);
        Department department = new Department("DEI", "Computer Science Department");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Address address = new Address("Rua São Tomé", "4249-015", "Porto", "Portugal");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.pt", "123456789", "B106",
                "PhD in Computer Science, 2005, ISEP", "Rua São Tomé", "4249-015", "Porto",
                "Portugal", "20-12-2010", assistantProfessor, 100, department);
        SchoolYear schoolYear = new SchoolYear("2024/2025", "23-11-2024", "09-12-2025");
        AccessMethodRepository amr = new AccessMethodRepository();
        AccessMethod am1 = new AccessMethod("Over 23");
        amr.registerAccessMethod("Over 23");
        CourseRepository courseRepository = new CourseRepository();
        Programme programme = new Programme("Computer Science", "CS", 25, 6, master, department, teacher, courseRepository);
        Student student = new Student(1, "João Silva", "999999999", "221234567", "joao123@gmail.com", address);

        programme.enrolStudentInProgramme(student, am1, amr);
        programmeEditionRepository.createProgrammeEdition(programme, schoolYear);
        ProgrammeEdition programmeEdition = programmeEditionRepository.findProgrammeEditionBySchoolYearAndProgramme(programme, schoolYear).get();
        programmeEditionEnrollmentRepo.enrollStudentInProgrammeEdition(student, programmeEdition, LocalDate.now());

        // Act & Assert
        IllegalStateException exception = assertThrows(IllegalStateException.class, () ->
                controller.enrollStudentInProgrammeEdition(student, programme, schoolYear)
        );
        assertEquals("Student is already enrolled in the programme edition.", exception.getMessage());
    }

    @Test
    void testEnrollStudentInCourseEditions_Success() throws Exception {
        // Arrange
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository();
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = new ProgrammeEditionEnrollmentRepo();
        ProgrammeList programmeList = new ProgrammeList();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = new CourseEditionEnrollmentRepository();
        StudentRepository studentRepository = new StudentRepository();
        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrollmentRepo,
                        programmeEditionRepository,
                        programmeList,
                        courseEditionEnrollmentRepository,
                        courseEditionRepository,
                        studentRepository);

        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Address add1 = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123666789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto",
                "Portugal", "20-12-2010", assistantProfessor, 100, department1);
        SchoolYear schoolYear = new SchoolYear("Academic Year", "23-11-2024", "09-12-2025");
        CourseRepository courseRepository = new CourseRepository();
        Programme programme1 = new Programme("Computer Science Engineering", "CSE", 25, 6, master, department1, teacher1, courseRepository);
        ProgrammeEdition programmeEdition = new ProgrammeEdition(programme1, schoolYear);
        programmeEditionRepository.createProgrammeEdition(programme1, schoolYear);

        Student student = new Student(1, "João Silva", "999999999", "221234567", "joao123@gmail.com", add1);

        Course c1 = new Course("Development", "DEV", 5, 1);
        Course c2 = new Course("Computer Science Engineering", "CSE", 5, 1);
        courseEditionRepository.createCourseEdition(c1, programmeEdition);
        courseEditionRepository.createCourseEdition(c2, programmeEdition);
        CourseEdition ce1 = courseEditionRepository.getCourseEditions().get(0);
        CourseEdition ce2 = courseEditionRepository.getCourseEditions().get(1);

        // Act
        Optional<List<CourseEdition>> result = controller.enrollStudentInCourseEditions(student, programmeEdition);
        Optional<CourseEditionEnrollment> enrollment1 = courseEditionEnrollmentRepository.findByStudentAndEdition(student, ce1);
        Optional<CourseEditionEnrollment> enrollment2 = courseEditionEnrollmentRepository.findByStudentAndEdition(student, ce2);
        List<CourseEdition> enrolledCourses = result.get();

        // Assert

        assertTrue(enrollment1.isPresent(), "The student is enrolled in the first course edition.");
        assertTrue(enrollment2.isPresent(), "The student is enrolled in the second course edition.");
        assertEquals(2, enrolledCourses.size(), "The student is enrolled in all course editions.");
        assertTrue(enrolledCourses.contains(ce1), "The student should be enrolled in the first course edition.");
        assertTrue(enrolledCourses.contains(ce2), "The student should be enrolled in the second course edition.");

    }

    @Test
    void testEnrollStudentInCourseEditions_StudentAlreadyEnrolled() throws Exception {
        // Arrange
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository();
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = new ProgrammeEditionEnrollmentRepo();
        ProgrammeList programmeList = new ProgrammeList();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = new CourseEditionEnrollmentRepository();
        StudentRepository studentRepository = new StudentRepository();
        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrollmentRepo,
                        programmeEditionRepository,
                        programmeList,
                        courseEditionEnrollmentRepository,
                        courseEditionRepository,
                        studentRepository);
        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Address add1 = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123666789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto",
                "Portugal", "20-12-2010", assistantProfessor, 100, department1);
        SchoolYear schoolYear = new SchoolYear("Academic Year", "23-11-2024", "09-12-2025");
        CourseRepository courseRepository = new CourseRepository();
        Programme programme1 = new Programme("Computer Science Engineering", "CSE", 25, 6, master, department1, teacher1, courseRepository);
        ProgrammeEdition programmeEdition = new ProgrammeEdition(programme1, schoolYear);
        programmeEditionRepository.createProgrammeEdition(programme1, schoolYear);
        Student student = new Student(1, "João Silva", "999999999", "221234567", "joao123@gmail.com", add1);
        Course c1 = new Course("Development", "DEV", 5, 1);
        Course c2 = new Course("Computer Science Engineering", "CSE", 5, 1);
        courseEditionRepository.createCourseEdition(c1, programmeEdition);
        courseEditionRepository.createCourseEdition(c2, programmeEdition);
        controller.enrollStudentInCourseEditions(student, programmeEdition);

        // Act
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            controller.enrollStudentInCourseEditions(student, programmeEdition);
        });

        // Assert
        assertEquals("This course edition enrollment is already in the list.", thrown.getMessage());
    }

    @Test
    void testGetAllProgrammes() throws Exception {
        // Arrange
        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123666789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015",
                "Porto", "Portugal", "20-12-2010", new TeacherCategory("Assistant Professor"), 100, department1);
        ProgrammeList programmeList = new ProgrammeList();
        CourseRepository courseRepository = new CourseRepository();
        programmeList.registerProgramme("Computer Science Engineering", "CSE", 25, 6, master, department1, teacher1, courseRepository);
        programmeList.registerProgramme("Computer Science", "CS", 26, 6, master, department1, teacher1, courseRepository);
        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        null,
                        null,
                        programmeList,
                        null,
                        null,
                        null
                );

        // Act
        List<Programme> programmes = controller.getAllProgrammes();

        // Assert
        assertNotNull(programmes, "The list of programmes should not be null.");
        assertEquals(2, programmes.size(), "The list of programmes should contain exactly 2 programmes.");
        assertTrue(programmes.contains(new Programme("Computer Science Engineering", "CSE", 25, 6, master, department1, teacher1, courseRepository)),
                "The list should contain the programme 'Computer Science Engineering'.");
        assertTrue(programmes.contains(new Programme("Computer Science", "CS", 26, 6, master, department1, teacher1, courseRepository)),
                "The list should contain the programme 'Computer Science'.");

    }


    @Test
    void testFindStudentById_WhenStudentExists() throws Exception {
        //arrange
        StudentRepository studentRepository = new StudentRepository();
        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        null, null, null, null, null, studentRepository);

        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        studentRepository.registerStudent(1001, "João Silva", "123456789", "912345678", "joao@email.com", add1);

        //act
        Optional<Student> foundStudent = controller.findStudentById(1001);

        //assert
        assertTrue(foundStudent.isPresent(), "O estudante deveria estar presente.");
        assertEquals(1001, foundStudent.get().getUniqueNumber(), "O número único deveria coincidir.");
    }

    @Test
    void testFindStudentById_WhenStudentDoesNotExist() {
        //arrange
        StudentRepository studentRepository = new StudentRepository();
        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        null, null, null, null, null, studentRepository);

        //act
        Optional<Student> foundStudent = controller.findStudentById(9999);

        //assert
        assertFalse(foundStudent.isPresent(), "O estudante não deveria ser encontrado.");
    }
}




