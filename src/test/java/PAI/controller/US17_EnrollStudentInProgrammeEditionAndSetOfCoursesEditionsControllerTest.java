package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsControllerTest {

    @Test
    void testEnrollStudentInProgrammeEditionAndSetOfCoursesEditions_Success() throws Exception {
        // Arrange
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository();
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = new ProgrammeEditionEnrollmentRepo();
        ProgrammeList programmeList = new ProgrammeList();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = new CourseEditionEnrollmentRepository();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository();
        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrollmentRepo,
                        programmeEditionRepository,
                        programmeList,
                        courseEditionEnrollmentRepository,
                        courseEditionRepository,
                        schoolYearRepository);
        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Address add1 = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123666789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto",
                "Portugal", "20-12-2010", assistantProfessor, 100, department1);
        schoolYearRepository.addSchoolYear("24/25", "23-11-2024", "09-12-2025");
        SchoolYear schoolYear = schoolYearRepository.getCurrentSchoolYear();
        AccessMethodFactory accessMethodFactory = new AccessMethodFactory();
        AccessMethodRepository amr = new AccessMethodRepository(accessMethodFactory);
        AccessMethod am1 = new AccessMethod("Over 23");
        amr.registerAccessMethod("Over 23");
        Student student = new Student(1, "João Silva", "999999999", "221234567", "joao123@gmail.com", add1);
        Course c1 = new Course("Development", "DEV", 5, 1);
        Course c2 = new Course("Development1", "DEV1", 5, 1);
        Programme programme1 = new Programme("Computer Engineering", "CSE", 25, 6, master, department1, teacher1);
        if (!programme1.isStudentEnrolled(student)) {
            programme1.enrolStudentInProgramme(student, am1, amr);
        }
        programmeEditionRepository.createProgrammeEdition(programme1, schoolYear);
        Optional<ProgrammeEdition> pe1Opt = programmeEditionRepository.findProgrammeEditionBySchoolYearAndProgramme(programme1, schoolYear);
        ProgrammeEdition pe1 = pe1Opt.get();
        courseEditionRepository.createCourseEdition(c1, pe1);
        courseEditionRepository.createCourseEdition(c2, pe1);
        CourseEdition ce1 = courseEditionRepository.getCourseEditions().get(0);
        CourseEdition ce2 = courseEditionRepository.getCourseEditions().get(1);
        // Act
        boolean result = controller.enrollStudentInProgrammeEditionAndSetOfCoursesEditions(student, programme1, schoolYear);
        boolean result2 = courseEditionEnrollmentRepository.isStudentEnrolledInCourseEdition(student, ce1);
        boolean result3 = courseEditionEnrollmentRepository.isStudentEnrolledInCourseEdition(student, ce2);
        // Assert
        assertTrue(result, "The student is enrolled in the ProgrammeEdition.");
        assertTrue(result2, "The Student is enrolled in the CourseEdition.");
        assertTrue(result3, "The Student is enrolled in the CourseEdition.");
    }

    @Test
    void testEnrollStudentInProgrammeEditionAndSetOfCoursesEditions_StudentNotEnrolledInProgramme() throws Exception {
        // Arrange
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository();
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = new ProgrammeEditionEnrollmentRepo();
        ProgrammeList programmeList = new ProgrammeList();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = new CourseEditionEnrollmentRepository();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository();
        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrollmentRepo,
                        programmeEditionRepository,
                        programmeList,
                        courseEditionEnrollmentRepository,
                        courseEditionRepository,
                        schoolYearRepository);
        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Address add1 = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");
        Student student = new Student(1, "João Silva", "999999999", "221234567", "joao123@gmail.com", add1);
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123666789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto",
                "Portugal", "20-12-2010", assistantProfessor, 100, department1);
        schoolYearRepository.addSchoolYear("24/25", "23-11-2024", "09-12-2025");
        SchoolYear schoolYear = schoolYearRepository.getCurrentSchoolYear();
        AccessMethodFactory accessMethodFactory = new AccessMethodFactory();
        AccessMethodRepository amr = new AccessMethodRepository(accessMethodFactory);
        amr.registerAccessMethod("Over 23");
        Programme programme1 = new Programme("Computer Engineering", "CSE", 25, 6, master, department1, teacher1);
        programmeEditionRepository.createProgrammeEdition(programme1, schoolYear);
        // Act & Assert:
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            controller.enrollStudentInProgrammeEditionAndSetOfCoursesEditions(student, programme1, schoolYear);
        });
        assertEquals("Student should enrolled in Programme.", exception.getMessage());
    }

    @Test
    void testEnrollStudentInProgrammeEditionAndSetOfCoursesEditions_ProgrammeEditionNotFound() throws Exception {
        // Arrange
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository();
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = new ProgrammeEditionEnrollmentRepo();
        ProgrammeList programmeList = new ProgrammeList();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = new CourseEditionEnrollmentRepository();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository();
        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrollmentRepo,
                        programmeEditionRepository,
                        programmeList,
                        courseEditionEnrollmentRepository,
                        courseEditionRepository,
                        schoolYearRepository);
        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Address add1 = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123666789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto",
                "Portugal", "20-12-2010", assistantProfessor, 100, department1);
        schoolYearRepository.addSchoolYear("24/25", "23-11-2024", "09-12-2025");
        SchoolYear schoolYear = schoolYearRepository.getCurrentSchoolYear();
        AccessMethodFactory accessMethodFactory = new AccessMethodFactory();
        AccessMethodRepository amr = new AccessMethodRepository(accessMethodFactory);
        AccessMethod am1 = new AccessMethod("Over 23");
        amr.registerAccessMethod("Over 23");
        Student student = new Student(1, "João Silva", "999999999", "221234567", "joao123@gmail.com", add1);
        Programme programme1 = new Programme("Computer Engineering", "CSE", 25, 6, master, department1, teacher1);
        if (!programme1.isStudentEnrolled(student)) {
            programme1.enrolStudentInProgramme(student, am1, amr);
        }
        // Act & Assert
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            controller.enrollStudentInProgrammeEditionAndSetOfCoursesEditions(student, programme1, schoolYear);
        });
        assertEquals("Programme edition not found for the given school year.", exception.getMessage());
    }

    @Test
    void testEnrollStudentInProgrammeEditionAndSetOfCoursesEditions_StudentAlreadyEnrolledInProgrammeEdition() throws Exception {
        // Arrange
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository();
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = new ProgrammeEditionEnrollmentRepo();
        ProgrammeList programmeList = new ProgrammeList();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = new CourseEditionEnrollmentRepository();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository();
        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrollmentRepo,
                        programmeEditionRepository,
                        programmeList,
                        courseEditionEnrollmentRepository,
                        courseEditionRepository,
                        schoolYearRepository);
        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Address add1 = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123666789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto",
                "Portugal", "20-12-2010", assistantProfessor, 100, department1);
        schoolYearRepository.addSchoolYear("24/25", "23-11-2024", "09-12-2025");
        SchoolYear schoolYear = schoolYearRepository.getCurrentSchoolYear();
        AccessMethodFactory accessMethodFactory = new AccessMethodFactory();
        AccessMethodRepository amr = new AccessMethodRepository(accessMethodFactory);
        AccessMethod am1 = new AccessMethod("Over 23");
        amr.registerAccessMethod("Over 23");
        Student student = new Student(1, "João Silva", "999999999", "221234567", "joao123@gmail.com", add1);
        Programme programme1 = new Programme("Computer Engineering", "CSE", 25, 6, master, department1, teacher1);
        if (!programme1.isStudentEnrolled(student)) {
            programme1.enrolStudentInProgramme(student, am1, amr);
        }
        programmeEditionRepository.createProgrammeEdition(programme1, schoolYear);
        Optional<ProgrammeEdition> peOptional = programmeEditionRepository.findProgrammeEditionBySchoolYearAndProgramme(programme1, schoolYear);
        ProgrammeEdition programmeEdition = peOptional.get();
        programmeEditionEnrollmentRepo.enrollStudentInProgrammeEdition(student, programmeEdition, LocalDate.now());
        // Act & Assert
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            controller.enrollStudentInProgrammeEditionAndSetOfCoursesEditions(student, programme1, schoolYear);
        });
        assertEquals("Student is already enrolled in the programme edition.", exception.getMessage());
    }

    @Test
    void testEnrollStudentInCourseEditionAndSetOfCoursesEditions_StudentAlreadyEnrolledInCourseEdition() throws Exception {
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository();
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = new ProgrammeEditionEnrollmentRepo();
        ProgrammeList programmeList = new ProgrammeList();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = new CourseEditionEnrollmentRepository();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository();
        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrollmentRepo,
                        programmeEditionRepository,
                        programmeList,
                        courseEditionEnrollmentRepository,
                        courseEditionRepository,
                        schoolYearRepository);
        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Address add1 = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123666789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto",
                "Portugal", "20-12-2010", assistantProfessor, 100, department1);
        schoolYearRepository.addSchoolYear("24/25", "23-11-2024", "09-12-2025");
        SchoolYear schoolYear = schoolYearRepository.getCurrentSchoolYear();
        AccessMethodFactory accessMethodFactory = new AccessMethodFactory();
        AccessMethodRepository amr = new AccessMethodRepository(accessMethodFactory);
        AccessMethod am1 = new AccessMethod("Over 23");
        amr.registerAccessMethod("Over 23");
        Student student = new Student(1, "João Silva", "999999999", "221234567", "joao123@gmail.com", add1);
        Course c1 = new Course("Development", "DEV", 5, 1);
        Course c2 = new Course("Development1", "DEV1", 5, 1);
        Programme programme1 = new Programme("Computer Engineering", "CSE", 25, 6, master, department1, teacher1);
        if (!programme1.isStudentEnrolled(student)) {
            programme1.enrolStudentInProgramme(student, am1, amr);
        }
        programmeEditionRepository.createProgrammeEdition(programme1, schoolYear);
        Optional<ProgrammeEdition> pe1Opt = programmeEditionRepository.findProgrammeEditionBySchoolYearAndProgramme(programme1, schoolYear);
        ProgrammeEdition pe1 = pe1Opt.get();
        courseEditionRepository.createCourseEdition(c1, pe1);
        courseEditionRepository.createCourseEdition(c2, pe1);
        CourseEdition ce1 = courseEditionRepository.getCourseEditions().get(0);
        CourseEdition ce2 = courseEditionRepository.getCourseEditions().get(1);
        courseEditionEnrollmentRepository.enrollStudentInACourseEdition(student, ce1,LocalDate.now());
        courseEditionEnrollmentRepository.enrollStudentInACourseEdition(student, ce2,LocalDate.now());
        // Act + Assert
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            controller.enrollStudentInProgrammeEditionAndSetOfCoursesEditions(student, programme1, schoolYear);
        });
        assertEquals("This course edition enrollment is already in the list.", exception.getMessage());
    }

    @Test
    void testGetAllProgrammes() throws Exception {
        // Arrange
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository();
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = new ProgrammeEditionEnrollmentRepo();
        ProgrammeList programmeList = new ProgrammeList();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = new CourseEditionEnrollmentRepository();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository();
        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrollmentRepo,
                        programmeEditionRepository,
                        programmeList,
                        courseEditionEnrollmentRepository,
                        courseEditionRepository,
                        schoolYearRepository);
        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123666789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015",
                "Porto", "Portugal", "20-12-2010", new TeacherCategory("Assistant Professor"), 100, department1);
        programmeList.registerProgramme("Computer Science Engineering", "CSE", 25, 6, master, department1, teacher1);
        programmeList.registerProgramme("Computer Science", "CS", 26, 6, master, department1, teacher1);

        // Act
        List<Programme> programmes = controller.getAllProgrammes();
        // Assert
        assertNotNull(programmes, "The list of programmes should not be null.");
        assertEquals(2, programmes.size(), "The list of programmes should contain exactly 2 programmes.");
        assertTrue(programmes.contains(new Programme("Computer Science Engineering", "CSE", 25, 6, master, department1, teacher1)),
                "The list should contain the programme 'Computer Science Engineering'.");
        assertTrue(programmes.contains(new Programme("Computer Science", "CS", 26, 6, master, department1, teacher1)),
                "The list should contain the programme 'Computer Science'.");
    }
    @Test
    void testGetAllSchoolYears() throws Exception {

        // Arrange
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository();
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = new ProgrammeEditionEnrollmentRepo();
        ProgrammeList programmeList = new ProgrammeList();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = new CourseEditionEnrollmentRepository();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository();
        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrollmentRepo,
                        programmeEditionRepository,
                        programmeList,
                        courseEditionEnrollmentRepository,
                        courseEditionRepository,
                        schoolYearRepository);
        schoolYearRepository.addSchoolYear("24/25", "23-11-2024", "09-12-2025");
        schoolYearRepository.addSchoolYear("25/26", "10-11-2025", "09-12-2026");
        // Act
        List<SchoolYear> schoolYears = controller.getAllSchoolYears();
        // Assert
        assertNotNull(schoolYears, "The list of school years should not be null.");
        assertEquals(2, schoolYears.size(), "The list of school years should contain exactly 2 years.");
        assertTrue(schoolYears.contains(new SchoolYear("24/25", "23-11-2024", "09-12-2025")),
                "The list should contain the school year '24/25'.");
        assertTrue(schoolYears.contains(new SchoolYear("25/26", "10-11-2025", "09-12-2026")),
                "The list should contain the school year '25/26'.");
    }

    @Test
    void shouldReturnExceptionIfProgrammeEditionEnrollmentRepoIsNull (){
        //arrange
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository();
        ProgrammeList programmeList = new ProgrammeList();
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = new CourseEditionEnrollmentRepository();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository();

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(null,programmeEditionRepository,
                    programmeList, courseEditionEnrollmentRepository, courseEditionRepository, schoolYearRepository);
        });
        //assert
        assertEquals("Programme edition enrollment repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfProgrammeEditionRepositoryIsNull (){
        //arrange
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = new ProgrammeEditionEnrollmentRepo();
        ProgrammeList programmeList = new ProgrammeList();
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = new CourseEditionEnrollmentRepository();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository();

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeEditionEnrollmentRepo,null,
                    programmeList, courseEditionEnrollmentRepository, courseEditionRepository, schoolYearRepository);
        });

        //assert
        assertEquals("Programme edition repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfProgrammeListIsNull (){
        //arrange
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = new ProgrammeEditionEnrollmentRepo();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository();
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = new CourseEditionEnrollmentRepository();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository();

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeEditionEnrollmentRepo,programmeEditionRepository,
                    null, courseEditionEnrollmentRepository, courseEditionRepository, schoolYearRepository);
        });

        //assert
        assertEquals("Programme list cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfCourseEditionEnrollmentRepositoryIsNull (){
        //arrange
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = new ProgrammeEditionEnrollmentRepo();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository();
        ProgrammeList programmeList = new ProgrammeList();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository();

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeEditionEnrollmentRepo,programmeEditionRepository,
                    programmeList, null, courseEditionRepository, schoolYearRepository);
        });

        //assert
        assertEquals("Course edition enrollment repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfCourseEditionRepositoryIsNull (){
        //arrange
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = new ProgrammeEditionEnrollmentRepo();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository();
        ProgrammeList programmeList = new ProgrammeList();
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = new CourseEditionEnrollmentRepository();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository();

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeEditionEnrollmentRepo,programmeEditionRepository,
                    programmeList, courseEditionEnrollmentRepository, null, schoolYearRepository);
        });

        //assert
        assertEquals("Course edition repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfSchoolYearRepositoryIsNull (){
        //arrange
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = new ProgrammeEditionEnrollmentRepo();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository();
        ProgrammeList programmeList = new ProgrammeList();
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = new CourseEditionEnrollmentRepository();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeEditionEnrollmentRepo,programmeEditionRepository,
                    programmeList, courseEditionEnrollmentRepository, courseEditionRepository, null);
        });

        //assert
        assertEquals("School year repository cannot be null.", exception.getMessage());
    }


}


