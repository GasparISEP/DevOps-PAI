package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsControllerTest {

    private US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller;
    private ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo;
    private ProgrammeEditionRepository programmeEditionRepository;
    private ProgrammeList programmeList;
    private CourseEditionEnrollmentRepository courseEditionEnrollmentRepository;
    private CourseEditionRepository courseEditionRepository;
    private SchoolYear schoolYear;
    private Student student;
    private ProgrammeEdition programmeEdition;
    private CourseEdition courseEdition;
    private StudentRepository studentRepository;

    @Test
    void testEnrollStudentInProgrammeEdition_Success() throws Exception {
        // Arrange
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository();
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = new ProgrammeEditionEnrollmentRepo();
        ProgrammeList programmeList = new ProgrammeList();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = new CourseEditionEnrollmentRepository();
        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrollmentRepo,
                        programmeEditionRepository,
                        programmeList,
                        courseEditionEnrollmentRepository,
                        courseEditionRepository);
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
        StudentRepository studentRepository = new StudentRepository();
        studentRepository.registerStudent(1, "João Silva", "999999999", "221234567", "joao123@gmail.com", add1);
        Enrolment enrolment = new Enrolment(student, am1);
        Programme programme1 = new Programme("Computer Science Engineering", "CSE", 25, 6, master, department1, teacher1);
        programmeList.registerProgramme("Computer Science Engineering", "CSE", 25, 6, master, department1, teacher1);
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
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        ProgrammeList programmeList = new ProgrammeList();
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = new CourseEditionEnrollmentRepository();
        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrollmentRepo,
                        programmeEditionRepository,
                        programmeList,
                        courseEditionEnrollmentRepository,
                        courseEditionRepository);
        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Address add1 = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123666789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto",
                "Portugal", "20-12-2010", assistantProfessor, 100, department1);
        SchoolYear schoolYear = new SchoolYear("Academic Year", "23-11-2024", "09-12-2025");
        Programme programme1 = new Programme("Computer Science Engineering", "CSE", 25, 6, master, department1, teacher1);
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
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        ProgrammeList programmeList = new ProgrammeList();
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = new CourseEditionEnrollmentRepository();
        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrollmentRepo,
                        programmeEditionRepository,
                        programmeList,
                        courseEditionEnrollmentRepository,
                        courseEditionRepository);
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
        Programme programme1 = new Programme("Computer Science Engineering", "CSE", 25, 6, master, department1, teacher1);
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
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        ProgrammeList programmeList = new ProgrammeList();
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = new CourseEditionEnrollmentRepository();
        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrollmentRepo,
                        programmeEditionRepository,
                        programmeList,
                        courseEditionEnrollmentRepository,
                        courseEditionRepository);

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
        Programme programme = new Programme("Computer Science", "CS", 25, 6, master, department, teacher);
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
}