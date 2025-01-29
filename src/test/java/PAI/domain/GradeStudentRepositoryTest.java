package PAI.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GradeStudentRepositoryTest {

    @Test
    void shouldAddGradeToAStudent() throws Exception {
        // Arrange
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        GradeStudentRepository list = new GradeStudentRepository(courseEditionRepository);
        CourseEditionEnrollmentRepository enrollmentRepository = new CourseEditionEnrollmentRepository();



        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        Department dpt1 = new Department("MAT", "Mathematics");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005", tc1, 70, dpt1);
        Course c1 = new Course("Informatics", "INF", 6, 1);
        CourseRepository courseRepository = new CourseRepository();
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher, courseRepository);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition(p1, sY1);
        CourseEdition courseEdition1 = new CourseEdition(c1, pE1);
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);
        Student student2 = new Student(2, "João", "123456786", "963741258", "joao@gmail.com", address1);
        LocalDate currentDate = LocalDate.now();

        courseEditionRepository.createCourseEdition(c1, pE1);

        enrollmentRepository.enrollStudentInACourseEdition(student1, courseEdition1, currentDate);
        enrollmentRepository.enrollStudentInACourseEdition(student2, courseEdition1, currentDate);


        // Act
        Optional<GradeStudent> result1 = list.addGradeToStudent(10, "10/10/2025", student1, courseEdition1,enrollmentRepository);
        Optional<GradeStudent> result2 = list.addGradeToStudent(8, "10/10/2025", student2, courseEdition1,enrollmentRepository);

        // Assert
        assertTrue(result1.isPresent());
        assertTrue(result2.isPresent());
    }

    @Test
    void shouldNotAddGradeToAStudent() throws Exception {
        // Arrange
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        GradeStudentRepository list = new GradeStudentRepository(courseEditionRepository);
        CourseEditionEnrollmentRepository enrollmentRepository = new CourseEditionEnrollmentRepository();


        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        Department dpt1 = new Department("MAT", "Mathematics");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005", tc1, 70, dpt1);
        Course c1 = new Course("Informatics", "INF", 6, 1);
        Course c2 = new Course("Informatics", "INF", 6, 1);
        CourseRepository courseRepository = new CourseRepository();
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher, courseRepository);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition(p1, sY1);
        CourseEdition courseEdition1 = new CourseEdition(c1, pE1);
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);
        Student student2 = new Student(2, "João", "123456786", "963741258", "joao@gmail.com", address1);
        LocalDate currentDate = LocalDate.now();

        courseEditionRepository.createCourseEdition(c1, pE1);
        enrollmentRepository.enrollStudentInACourseEdition(student1, courseEdition1,currentDate);
        enrollmentRepository.enrollStudentInACourseEdition(student2, courseEdition1, currentDate);

        // Act
        Optional<GradeStudent> result1 = list.addGradeToStudent(10, "10/10/2025", student1, courseEdition1,enrollmentRepository);
        Optional<GradeStudent> result2 = list.addGradeToStudent(8, "10/10/2025", student2, null,enrollmentRepository);

        // Assert
        assertFalse(result2.isPresent());

    }

    @Test
    void shouldNotAddGradeToAStudentWithInvalidCourseEdition() throws Exception {
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        GradeStudentRepository list = new GradeStudentRepository(courseEditionRepository);
        CourseEditionEnrollmentRepository enrollmentRepository = new CourseEditionEnrollmentRepository();

        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005", tc1, 70, CSE);
        CourseRepository courseRepository = new CourseRepository();
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher, courseRepository);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Course c1 = new Course("Informatics", "INF", 10, 1);
        ProgrammeEdition pE1 = new ProgrammeEdition(p1, sY1);
        CourseEdition invalidCourseEdition = new CourseEdition(c1, pE1);
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);
        LocalDate currentDate = LocalDate.now();

        enrollmentRepository.enrollStudentInACourseEdition(student1, invalidCourseEdition, currentDate);

        // Act
        Optional<GradeStudent> result = list.addGradeToStudent(18, "10/02/2025", student1, invalidCourseEdition,enrollmentRepository);

        // Assert
        assertFalse(result.isPresent());

    }

    @Test
    void shouldNotGradeAStudentOnCourseEditionWithoutStudents() throws Exception {
        // Arrange
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        GradeStudentRepository list = new GradeStudentRepository(courseEditionRepository);
        CourseEditionEnrollmentRepository enrollmentRepository = new CourseEditionEnrollmentRepository();


        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        Department dpt1 = new Department("MAT", "Mathematics");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005", tc1, 70, dpt1);
        Course c1 = new Course("Informatics", "INF", 6, 1);
        Course c2 = new Course("Science", "SCI", 6, 1);
        CourseRepository courseRepository = new CourseRepository();
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher, courseRepository);
        Programme p2 = new Programme("Computer Science", "CES", 20, 6, master, CSE, teacher, courseRepository);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        SchoolYear sY2 = new SchoolYear("Ano letivo de", "23-11-2023", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition(p1, sY1);
        ProgrammeEdition pE2 = new ProgrammeEdition(p2, sY2);
        CourseEdition courseEdition1 = new CourseEdition(c1, pE1);
        CourseEdition courseEdition2 = new CourseEdition(c2, pE2);
        LocalDate currentDate = LocalDate.now();


        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);
        Student student2 = new Student(2, "João", "123456786", "963741258", "joao@gmail.com", address1);


        courseEditionRepository.createCourseEdition(c1, pE1);
        enrollmentRepository.enrollStudentInACourseEdition(student1, courseEdition1, currentDate);
        enrollmentRepository.enrollStudentInACourseEdition(student2, courseEdition1, currentDate);


        list.addGradeToStudent(10, "10/10/2025", student1, courseEdition2,enrollmentRepository);
        list.addGradeToStudent(20, "10/10/2025", student2, courseEdition2,enrollmentRepository);

        // Act
        double approvalRate = list.knowApprovalRate(courseEdition1);

        // Assert
        assertEquals(0, approvalRate, 0.01);
    }

    @Test
    void shouldGradeAStudent100() throws Exception {
        // Arrange
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        GradeStudentRepository list = new GradeStudentRepository(courseEditionRepository);
        CourseEditionEnrollmentRepository enrollmentRepository = new CourseEditionEnrollmentRepository();


        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        Department dpt1 = new Department("MAT", "Mathematics");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005", tc1, 70, dpt1);
        Course c1 = new Course("Informatics", "INF", 6, 1);
        Course c2 = new Course("Science", "SCI", 6, 1);
        CourseRepository courseRepository = new CourseRepository();
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher, courseRepository);
        Programme p2 = new Programme("Computer Science", "CES", 20, 6, master, CSE, teacher, courseRepository);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        SchoolYear sY2 = new SchoolYear("Ano letivo de", "23-11-2023", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition(p1, sY1);
        ProgrammeEdition pE2 = new ProgrammeEdition(p2, sY2);
        CourseEdition courseEdition1 = new CourseEdition(c1, pE1);
        CourseEdition courseEdition2 = new CourseEdition(c2, pE2);
        LocalDate currentDate = LocalDate.now();


        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);
        Student student2 = new Student(2, "João", "123456786", "963741258", "joao@gmail.com", address1);


        courseEditionRepository.createCourseEdition(c1, pE1);
        enrollmentRepository.enrollStudentInACourseEdition(student1, courseEdition1, currentDate);
        enrollmentRepository.enrollStudentInACourseEdition(student2, courseEdition1, currentDate);


        list.addGradeToStudent(10, "10/10/2025", student1, courseEdition1,enrollmentRepository);
        list.addGradeToStudent(20, "10/10/2025", student2, courseEdition1,enrollmentRepository);

        // Act
        double approvalRate = list.knowApprovalRate(courseEdition1);

        // Assert
        assertEquals(100, approvalRate, 0.01);
    }

    @Test
    void shouldReturnEmptyWhenGradeIsInvalid() throws Exception {
        // Arrange
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        GradeStudentRepository gradeStudentRepository = new GradeStudentRepository(courseEditionRepository);
        CourseEditionEnrollmentRepository enrollmentRepository = new CourseEditionEnrollmentRepository();


        DegreeType master = new DegreeType("Master", 240);
        Department cseDepartment = new Department("CSE", "Computer Science Engineer");
        TeacherCategory teacherCategory = new TeacherCategory("Professor Adjunto");
        Department mathDepartment = new Department("MAT", "Mathematics");
        Teacher teacher = new Teacher(
                "ABC",
                "Joe Doe",
                "abc@isep.ipp.pt",
                "123456789",
                "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua das Flores", "4444-098", "Porto", "Portugal",
                "15-04-2005",
                teacherCategory,
                70,
                mathDepartment
        );
        Course c1 = new Course("Informatics", "INF", 6, 1);
        CourseRepository courseRepository = new CourseRepository();
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, cseDepartment, teacher, courseRepository);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition(p1, sY1);

        courseEditionRepository.createCourseEdition(c1, pE1);

        CourseEdition courseEditionValid = new CourseEdition(c1, pE1);

        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student validStudent = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);
        LocalDate currentDate = LocalDate.now();

        enrollmentRepository.enrollStudentInACourseEdition(validStudent, courseEditionValid, currentDate);

        // Act
        Optional<GradeStudent> result = gradeStudentRepository.addGradeToStudent(25, "10/10/2025", validStudent, courseEditionValid,enrollmentRepository);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldGetAverageGradeOfCourseEditionOf15() throws Exception {
        // Arrange
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        GradeStudentRepository list = new GradeStudentRepository(courseEditionRepository);
        CourseEditionEnrollmentRepository enrollmentRepository = new CourseEditionEnrollmentRepository();


        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        Department dpt1 = new Department("MAT", "Mathematics");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005", tc1, 70, dpt1);
        Course c1 = new Course("Informatics", "INF", 6, 1);
        Course c2 = new Course("Science", "SCI", 6, 1);
        CourseRepository courseRepository = new CourseRepository();
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher, courseRepository);
        Programme p2 = new Programme("Computer Science", "CES", 20, 6, master, CSE, teacher, courseRepository);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        SchoolYear sY2 = new SchoolYear("Ano letivo de", "23-11-2023", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition(p1, sY1);
        ProgrammeEdition pE2 = new ProgrammeEdition(p2, sY2);
        CourseEdition courseEdition1 = new CourseEdition(c1, pE1);
        CourseEdition courseEdition2 = new CourseEdition(c2, pE2);
        LocalDate currentDate = LocalDate.now();

        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);
        Student student2 = new Student(2, "João", "123456786", "963741258", "joao@gmail.com", address1);


        courseEditionRepository.createCourseEdition(c1, pE1);
        enrollmentRepository.enrollStudentInACourseEdition(student1, courseEdition1, currentDate);
        enrollmentRepository.enrollStudentInACourseEdition(student2, courseEdition1, currentDate);


        list.addGradeToStudent(10, "10/10/2025", student1, courseEdition1,enrollmentRepository);
        list.addGradeToStudent(20, "10/10/2025", student2, courseEdition1,enrollmentRepository);

        // Act
        Double averageGrade = list.KnowAverageGrade(courseEdition1);

        // Assert
        assertEquals(15, averageGrade, 0.01);
    }

    @Test
    void shouldGetAverageGradeOf0() throws Exception {
        // Arrange
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        GradeStudentRepository list = new GradeStudentRepository(courseEditionRepository);
        CourseEditionEnrollmentRepository enrollmentRepository = new CourseEditionEnrollmentRepository();


        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        Department dpt1 = new Department("MAT", "Mathematics");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005", tc1, 70, dpt1);
        Course c1 = new Course("Informatics", "INF", 6, 1);
        Course c2 = new Course("Science", "SCI", 6, 1);
        CourseRepository courseRepository = new CourseRepository();
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher, courseRepository);
        Programme p2 = new Programme("Computer Science", "CES", 20, 6, master, CSE, teacher, courseRepository);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        SchoolYear sY2 = new SchoolYear("Ano letivo de", "23-11-2023", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition(p1, sY1);
        ProgrammeEdition pE2 = new ProgrammeEdition(p2, sY2);
        CourseEdition courseEdition1 = new CourseEdition(c1, pE1);
        CourseEdition courseEdition2 = new CourseEdition(c2, pE2);
        LocalDate currentDate = LocalDate.now();


        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);
        Student student2 = new Student(2, "João", "123456786", "963741258", "joao@gmail.com", address1);

        courseEditionRepository.createCourseEdition(c1, pE1);

        enrollmentRepository.enrollStudentInACourseEdition(student1, courseEdition1, currentDate);
        enrollmentRepository.enrollStudentInACourseEdition(student2, courseEdition1, currentDate);

        list.addGradeToStudent(0, "10/10/2025", student1, courseEdition1,enrollmentRepository);
        list.addGradeToStudent(0, "10/10/2025", student2, courseEdition1,enrollmentRepository);

        // Act
        Double averageGrade = list.KnowAverageGrade(courseEdition1);

        // Assert
        assertEquals(0, averageGrade, 0.01);
    }

    @Test
    void shouldNotGetAverageGradeOnCourseEditionWithoutStudents() throws Exception {
        // Arrange
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        GradeStudentRepository list = new GradeStudentRepository(courseEditionRepository);
        CourseEditionEnrollmentRepository enrollmentRepository = new CourseEditionEnrollmentRepository();


        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        Department dpt1 = new Department("MAT", "Mathematics");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005", tc1, 70, dpt1);
        Course c1 = new Course("Informatics", "INF", 6, 1);
        Course c2 = new Course("Science", "SCI", 6, 1);
        CourseRepository courseRepository = new CourseRepository();
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher, courseRepository);
        Programme p2 = new Programme("Computer Science", "CES", 20, 6, master, CSE, teacher, courseRepository);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        SchoolYear sY2 = new SchoolYear("Ano letivo de", "23-11-2023", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition(p1, sY1);
        ProgrammeEdition pE2 = new ProgrammeEdition(p2, sY2);
        CourseEdition courseEdition1 = new CourseEdition(c1, pE1);
        CourseEdition courseEdition2 = new CourseEdition(c2, pE2);
        LocalDate currentDate = LocalDate.now();


        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);
        Student student2 = new Student(2, "João", "123456786", "963741258", "joao@gmail.com", address1);


        courseEditionRepository.createCourseEdition(c1, pE1);
        enrollmentRepository.enrollStudentInACourseEdition(student1, courseEdition1, currentDate);
        enrollmentRepository.enrollStudentInACourseEdition(student2, courseEdition1, currentDate);



        list.addGradeToStudent(10, "10/10/2025", student1, courseEdition2,enrollmentRepository);
        list.addGradeToStudent(20, "10/10/2025", student2, courseEdition2,enrollmentRepository);

        // Act
        Double averageGrade = list.KnowAverageGrade(courseEdition1);

        // Assert
        assertNull(averageGrade);
    }
}