package PAI.controller;

import PAI.domain.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


class US26_CountStudentsInProgrammesFromDepartmentInSchoolYearControllerTest {

    //testing the constructor
    //valid constructor
    @Test
    void shouldCreateControllerWhenRepositoriesAreValid(){
        // arrange
        ProgrammeEditionEnrollmentRepo pEERepo = new ProgrammeEditionEnrollmentRepo();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository();
        DepartmentRepository departmentRepository = new DepartmentRepository();

        // Act
        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller =
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(pEERepo, schoolYearRepository, departmentRepository);

        // Assert
        assertNotNull(controller);
    }

    //test when ProgramEditionEnrollmentRepo is null
    @Test
    void shouldThrowExceptionWhenPEERepoIsNull(){
        // arrange
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository();
        DepartmentRepository departmentRepository = new DepartmentRepository();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(null, schoolYearRepository, departmentRepository)
        );
        assertEquals("Repositories cannot be null.", exception.getMessage());
    }

    //test when SchoolYearRepo is null
    @Test
    void shouldThrowExceptionWhenSchoolYearRepoIsNull(){
        // arrange
        ProgrammeEditionEnrollmentRepo pEERepo = new ProgrammeEditionEnrollmentRepo();
        DepartmentRepository departmentRepository = new DepartmentRepository();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(pEERepo, null, departmentRepository)
        );
        assertEquals("Repositories cannot be null.", exception.getMessage());
    }

    //test when DepartmentRepo is null
    @Test
    void shouldThrowExceptionWhenDepartmentRepoNull(){
        // arrange
        ProgrammeEditionEnrollmentRepo pEERepo = new ProgrammeEditionEnrollmentRepo();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(pEERepo, schoolYearRepository, null)
        );
        assertEquals("Repositories cannot be null.", exception.getMessage());
    }

    //test that ensures that the method returns a positive int when there are students enrolled in Programmes from specified department and school year
    @Test
    void shouldReturnCorrectCountWhenStudentsAreEnrolledInDepartmentAndSchoolYear() throws Exception {
        // arrange
        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        SchoolYear schoolYear1 = new SchoolYear("Ano letivo de", "01-09-2024", "31-07-2025");
        SchoolYear schoolYear2 = new SchoolYear("Ano letivo de", "01-09-2022", "31-07-2023");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, department1);
        Programme programme1 = new Programme("Licenciatura Engenharia Informática", "LEI", 25, 6, master, department1, teacher1);
        Programme programme2 = new Programme("Licenciatura Engenharia de Computação", "LEC", 30, 6, master, department1, teacher1);

        ProgrammeEdition edition1 = new ProgrammeEdition(programme1, schoolYear1);
        ProgrammeEdition edition2 = new ProgrammeEdition(programme2, schoolYear1);
        ProgrammeEdition edition3 = new ProgrammeEdition(programme2, schoolYear2);

        Address address1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Address address2 = new Address("Rua do Lumiar", "4554-566", "Porto", "Portugal");
        Address address3 = new Address("Rua da Pedra", "4556-575", "Porto", "Portugal");
        Student student1 = new Student(1, "João Silva", "123456789", "221234567", "joao123@gmail.com", address1);
        Student student2 = new Student(2, "Rita Mendes", "123455649", "221234567", "rita123@gmail.com", address2);
        Student student3 = new Student(3, "Ana Luisa", "123456439", "221234569", "ana123@gmail.com", address3);

        LocalDate currentDate = LocalDate.now();

        ProgrammeEditionEnrollmentRepo pEERepo = new ProgrammeEditionEnrollmentRepo();

        pEERepo.enrollStudentInProgrammeEdition(student1, edition1, currentDate);
        pEERepo.enrollStudentInProgrammeEdition(student2, edition2, currentDate);
        pEERepo.enrollStudentInProgrammeEdition(student3, edition3, currentDate);

        SchoolYearRepository schoolYearRepository = new SchoolYearRepository();
        schoolYearRepository.addSchoolYear("Ano letivo de", "01-09-2024", "31-07-2025");
        schoolYearRepository.addSchoolYear("Ano letivo de", "01-09-2022", "31-07-2023");

        DepartmentRepository departmentRepository = new DepartmentRepository();
        departmentRepository.registerDepartment("DEI", "Departamento Engenharia Informática");

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller = new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(pEERepo, schoolYearRepository, departmentRepository);
        // act
        int result = controller.countStudentsInProgrammesFromDepartmentInSchoolYear(department1, schoolYear1);

        // assert
        assertEquals(2, result);
    }

    //test that ensures that the method throws an exception when Department is null
    @Test
    void shouldThrowExceptionWhenDepartmentIsNull() throws Exception {
        // arrange
        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        SchoolYear schoolYear1 = new SchoolYear("Ano letivo de", "01-09-2024", "31-07-2025");
        SchoolYear schoolYear2 = new SchoolYear("Ano letivo de", "01-09-2022", "31-07-2023");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, department1);
        Programme programme1 = new Programme("Licenciatura Engenharia Informática", "LEI", 25, 6, master, department1, teacher1);
        Programme programme2 = new Programme("Licenciatura Engenharia de Computação", "LEC", 30, 6, master, department1, teacher1);

        ProgrammeEdition edition1 = new ProgrammeEdition(programme1, schoolYear1);
        ProgrammeEdition edition2 = new ProgrammeEdition(programme2, schoolYear1);
        ProgrammeEdition edition3 = new ProgrammeEdition(programme2, schoolYear2);

        Address address1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Address address2 = new Address("Rua do Lumiar", "4554-566", "Porto", "Portugal");
        Address address3 = new Address("Rua da Pedra", "4556-575", "Porto", "Portugal");
        Student student1 = new Student(1, "João Silva", "123456789", "221234567", "joao123@gmail.com", address1);
        Student student2 = new Student(2, "Rita Mendes", "123455649", "221234567", "rita123@gmail.com", address2);
        Student student3 = new Student(3, "Ana Luisa", "123456439", "221234569", "ana123@gmail.com", address3);

        LocalDate currentDate = LocalDate.now();

        ProgrammeEditionEnrollmentRepo pEERepo = new ProgrammeEditionEnrollmentRepo();

        pEERepo.enrollStudentInProgrammeEdition(student1, edition1, currentDate);
        pEERepo.enrollStudentInProgrammeEdition(student2, edition2, currentDate);
        pEERepo.enrollStudentInProgrammeEdition(student3, edition3, currentDate);

        SchoolYearRepository schoolYearRepository = new SchoolYearRepository();
        schoolYearRepository.addSchoolYear("Ano letivo de", "01-09-2024", "31-07-2025");
        schoolYearRepository.addSchoolYear("Ano letivo de", "01-09-2022", "31-07-2023");

        DepartmentRepository departmentRepository = new DepartmentRepository();
        departmentRepository.registerDepartment("DEI", "Departamento Engenharia Informática");

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller = new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(pEERepo, schoolYearRepository, departmentRepository);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {
            controller.countStudentsInProgrammesFromDepartmentInSchoolYear(null, schoolYear1);
        });
        // Assert
        assertEquals("Department or SchoolYear cannot be null", exception.getMessage());
    }

    //test that ensures that the method throws an exception when School Year is null
    @Test
    void shouldThrowExceptionWhenSchoolYearIsNull() throws Exception {
        // arrange
        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        SchoolYear schoolYear1 = new SchoolYear("Ano letivo de", "01-09-2024", "31-07-2025");
        SchoolYear schoolYear2 = new SchoolYear("Ano letivo de", "01-09-2022", "31-07-2023");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, department1);
        Programme programme1 = new Programme("Licenciatura Engenharia Informática", "LEI", 25, 6, master, department1, teacher1);
        Programme programme2 = new Programme("Licenciatura Engenharia de Computação", "LEC", 30, 6, master, department1, teacher1);

        ProgrammeEdition edition1 = new ProgrammeEdition(programme1, schoolYear1);
        ProgrammeEdition edition2 = new ProgrammeEdition(programme2, schoolYear1);
        ProgrammeEdition edition3 = new ProgrammeEdition(programme2, schoolYear2);

        Address address1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Address address2 = new Address("Rua do Lumiar", "4554-566", "Porto", "Portugal");
        Address address3 = new Address("Rua da Pedra", "4556-575", "Porto", "Portugal");
        Student student1 = new Student(1, "João Silva", "123456789", "221234567", "joao123@gmail.com", address1);
        Student student2 = new Student(2, "Rita Mendes", "123455649", "221234567", "rita123@gmail.com", address2);
        Student student3 = new Student(3, "Ana Luisa", "123456439", "221234569", "ana123@gmail.com", address3);

        LocalDate currentDate = LocalDate.now();

        ProgrammeEditionEnrollmentRepo pEERepo = new ProgrammeEditionEnrollmentRepo();
        pEERepo.enrollStudentInProgrammeEdition(student1, edition1, currentDate);
        pEERepo.enrollStudentInProgrammeEdition(student2, edition2, currentDate);
        pEERepo.enrollStudentInProgrammeEdition(student3, edition3, currentDate);

        SchoolYearRepository schoolYearRepository = new SchoolYearRepository();
        schoolYearRepository.addSchoolYear("Ano letivo de", "01-09-2024", "31-07-2025");
        schoolYearRepository.addSchoolYear("Ano letivo de", "01-09-2022", "31-07-2023");

        DepartmentRepository departmentRepository = new DepartmentRepository();
        departmentRepository.registerDepartment("DEI", "Departamento Engenharia Informática");

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller = new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(pEERepo, schoolYearRepository, departmentRepository);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {
            controller.countStudentsInProgrammesFromDepartmentInSchoolYear(department1, null);
        });

        // Assert
        assertEquals("Department or SchoolYear cannot be null", exception.getMessage());
    }
}