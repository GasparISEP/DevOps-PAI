package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsControllerTest {

    @Test
    void testEnrollStudentInProgrammeEditionAndSetOfCoursesEditions_StudentNotEnrolledInProgramme() throws Exception {
        // Arrange
        ProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactory();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory);
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = new ProgrammeEditionEnrollmentRepo();
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory);
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        CourseEditionEnrollmentFactory factoryDouble = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository= new CourseEditionEnrollmentRepository (factoryDouble);
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository();
        ProgrammeEnrolmentRepository enrolmentRepository = new ProgrammeEnrolmentRepository();
        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrollmentRepo,
                        programmeEditionRepository,
                        programmeList,
                        courseEditionEnrollmentRepository,
                        courseEditionRepository,
                        schoolYearRepository,
                        enrolmentRepository);
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
        ProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactory();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory);
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = new ProgrammeEditionEnrollmentRepo();
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory);
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        CourseEditionEnrollmentFactory factoryDouble = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository= new CourseEditionEnrollmentRepository (factoryDouble);
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository();
        ProgrammeEnrolmentRepository enrolmentRepository = new ProgrammeEnrolmentRepository();
        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrollmentRepo,
                        programmeEditionRepository,
                        programmeList,
                        courseEditionEnrollmentRepository,
                        courseEditionRepository,
                        schoolYearRepository,
                        enrolmentRepository);
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
        if (!enrolmentRepository.isStudentEnrolled(student, programme1)) {
            enrolmentRepository.enrolStudents(student, am1, programme1, "05-12-2020");
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
        ProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactory();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory);
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = new ProgrammeEditionEnrollmentRepo();
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory);
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        CourseEditionEnrollmentFactory factoryDouble = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository= new CourseEditionEnrollmentRepository (factoryDouble);
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository();
        ProgrammeEnrolmentRepository enrolmentRepository = new ProgrammeEnrolmentRepository();
        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrollmentRepo,
                        programmeEditionRepository,
                        programmeList,
                        courseEditionEnrollmentRepository,
                        courseEditionRepository,
                        schoolYearRepository,
                        enrolmentRepository);
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
        if (!enrolmentRepository.isStudentEnrolled(student, programme1)) {
            enrolmentRepository.enrolStudents(student, am1, programme1, "05-12-2020");
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
    void testGetAllProgrammes() throws Exception {
        // Arrange
        ProgrammeEditionRepository programmeEditionRepository = mock(ProgrammeEditionRepository.class);
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = mock (ProgrammeEditionEnrollmentRepo.class);
        ProgrammeList programmeList = mock(ProgrammeList.class);
        CourseEditionRepository courseEditionRepository = mock(CourseEditionRepository.class);
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = mock(CourseEditionEnrollmentRepository.class);
        SchoolYearRepository schoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository enrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrollmentRepo,
                        programmeEditionRepository,
                        programmeList,
                        courseEditionEnrollmentRepository,
                        courseEditionRepository,
                        schoolYearRepository,
                        enrolmentRepository);

        Programme programme1 = mock(Programme.class);
        Programme programme2 = mock(Programme.class);

        when(programmeList.getAllProgrammes()).thenReturn(List.of(programme1, programme2));

        // Act
        List<Programme> programmes = controller.getAllProgrammes();
        // Assert
        assertNotNull(programmes, "The list of programmes should not be null.");
        assertEquals(2, programmes.size(), "The list of programmes should contain exactly 2 programmes.");
        assertTrue(programmes.contains(programme1));
        assertTrue(programmes.contains(programme2));
    }
    @Test
    void testGetAllSchoolYears() throws Exception {

        // Arrange
        ProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactory();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory);
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = new ProgrammeEditionEnrollmentRepo();
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory);
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        CourseEditionEnrollmentFactory factoryDouble = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository= new CourseEditionEnrollmentRepository (factoryDouble);
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository();
        ProgrammeEnrolmentRepository enrolmentRepository = new ProgrammeEnrolmentRepository();
        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrollmentRepo,
                        programmeEditionRepository,
                        programmeList,
                        courseEditionEnrollmentRepository,
                        courseEditionRepository,
                        schoolYearRepository,
                        enrolmentRepository);
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
        ProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactory();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory);
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory);
        CourseEditionEnrollmentFactory factoryDouble = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository= new CourseEditionEnrollmentRepository (factoryDouble);
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository();
        ProgrammeEnrolmentRepository enrolmentRepository = new ProgrammeEnrolmentRepository();

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(null,programmeEditionRepository,
                    programmeList, courseEditionEnrollmentRepository, courseEditionRepository, schoolYearRepository, enrolmentRepository);
        });
        //assert
        assertEquals("Programme edition enrollment repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfProgrammeEditionRepositoryIsNull (){
        //arrange
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = new ProgrammeEditionEnrollmentRepo();
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory);
        CourseEditionEnrollmentFactory factoryDouble = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository= new CourseEditionEnrollmentRepository (factoryDouble);
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository();
        ProgrammeEnrolmentRepository enrolmentRepository = new ProgrammeEnrolmentRepository();

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeEditionEnrollmentRepo,null,
                    programmeList, courseEditionEnrollmentRepository, courseEditionRepository, schoolYearRepository, enrolmentRepository);
        });

        //assert
        assertEquals("Programme edition repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfProgrammeListIsNull (){
        //arrange
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = new ProgrammeEditionEnrollmentRepo();
        ProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactory();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory);
        CourseEditionEnrollmentFactory factoryDouble = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository= new CourseEditionEnrollmentRepository (factoryDouble);
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository();
        ProgrammeEnrolmentRepository enrolmentRepository = new ProgrammeEnrolmentRepository();

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeEditionEnrollmentRepo,programmeEditionRepository,
                    null, courseEditionEnrollmentRepository, courseEditionRepository, schoolYearRepository, enrolmentRepository);
        });

        //assert
        assertEquals("Programme list cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfCourseEditionEnrollmentRepositoryIsNull (){
        //arrange
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = new ProgrammeEditionEnrollmentRepo();
        ProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactory();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory);
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory);
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository();
        ProgrammeEnrolmentRepository enrolmentRepository = new ProgrammeEnrolmentRepository();

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeEditionEnrollmentRepo,programmeEditionRepository,
                    programmeList, null, courseEditionRepository, schoolYearRepository, enrolmentRepository);
        });

        //assert
        assertEquals("Course edition enrollment repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfCourseEditionRepositoryIsNull (){
        //arrange
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = new ProgrammeEditionEnrollmentRepo();
        ProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactory();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory);
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory);
        CourseEditionEnrollmentFactory factoryDouble = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository= new CourseEditionEnrollmentRepository (factoryDouble);
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository();
        ProgrammeEnrolmentRepository enrolmentRepository = new ProgrammeEnrolmentRepository();

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeEditionEnrollmentRepo,programmeEditionRepository,
                    programmeList, courseEditionEnrollmentRepository, null, schoolYearRepository, enrolmentRepository);
        });

        //assert
        assertEquals("Course edition repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfSchoolYearRepositoryIsNull (){
        //arrange
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = new ProgrammeEditionEnrollmentRepo();
        ProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactory();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory);
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory);
        CourseEditionEnrollmentFactory factoryDouble = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository= new CourseEditionEnrollmentRepository (factoryDouble);
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        ProgrammeEnrolmentRepository enrolmentRepository = new ProgrammeEnrolmentRepository();

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeEditionEnrollmentRepo,programmeEditionRepository,
                    programmeList, courseEditionEnrollmentRepository, courseEditionRepository, null, enrolmentRepository);
        });

        //assert
        assertEquals("School year repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfEnrolmentRepositoryIsNull (){
        //arrange
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = new ProgrammeEditionEnrollmentRepo();
        ProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactory();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory);
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory);
        CourseEditionEnrollmentFactory factoryDouble = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository= new CourseEditionEnrollmentRepository (factoryDouble);
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository();

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeEditionEnrollmentRepo,programmeEditionRepository,
                    programmeList, courseEditionEnrollmentRepository, courseEditionRepository, schoolYearRepository, null);
        });

        //assert
        assertEquals("Enrolment repository cannot be null.", exception.getMessage());
    }

}


