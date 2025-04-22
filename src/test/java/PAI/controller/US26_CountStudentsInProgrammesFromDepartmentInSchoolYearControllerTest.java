package PAI.controller;
import PAI.VOs.*;

import PAI.persistence.mem.SchoolYearRepositoryMem;
import PAI.repository.*;

import PAI.repository.programmeRepository.IProgrammeRepository;
import PAI.persistence.mem.ProgrammeRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US26_CountStudentsInProgrammesFromDepartmentInSchoolYearControllerTest {
    //testing the constructor
    //valid constructor
    @Test
    void shouldCreateControllerWhenRepositoriesAreValid() {
        // arrange
        IProgrammeEditionEnrolmentRepository PEERepoDouble = mock(ProgrammeEditionEnrolmentRepositoryImpl.class);
        ISchoolYearRepository schoolYearRepoDouble = mock(SchoolYearRepositoryMem.class);
        IDepartmentRepository departmentRepoDouble = mock(DepartmentRepositoryImpl.class);
        IProgrammeRepository ProgrammeDDDRepository = mock(ProgrammeRepositoryImpl.class);

        // Act & Assert
        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller =
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(PEERepoDouble, schoolYearRepoDouble, departmentRepoDouble, ProgrammeDDDRepository);
    }

    //test when ProgrammeDDDRepository is null
    @Test
    void shouldThrowExceptionWhenProgrammeDDDRepositoryIsNull() {
        // arrange
        ISchoolYearRepository schoolYearRepoDouble = mock(SchoolYearRepositoryMem.class);
        IDepartmentRepository departmentRepoDouble = mock(DepartmentRepositoryImpl.class);
        IProgrammeEditionEnrolmentRepository PEERepoDouble = mock(ProgrammeEditionEnrolmentRepositoryImpl.class);
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(PEERepoDouble, schoolYearRepoDouble, departmentRepoDouble, null)
        );
        assertEquals("Repositories cannot be null.", exception.getMessage());
    }
    //test when ProgramEditionEnrollmentRepo is null

    @Test
    void shouldThrowExceptionWhenPEERepoIsNull() {
        // arrange
        IProgrammeRepository programmeDDDRepository = mock(ProgrammeRepositoryImpl.class);
        ISchoolYearRepository schoolYearRepoDouble = mock(SchoolYearRepositoryMem.class);
        IDepartmentRepository departmentRepoDouble = mock(DepartmentRepositoryImpl.class);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(null, schoolYearRepoDouble, departmentRepoDouble, programmeDDDRepository)
        );
        assertEquals("Repositories cannot be null.", exception.getMessage());
    }

    //test when SchoolYearRepo is null
    @Test
    void shouldThrowExceptionWhenSchoolYearRepoIsNull() {
        // arrange
        IProgrammeRepository programmeDDDRepository = mock(ProgrammeRepositoryImpl.class);
        IDepartmentRepository departmentRepoDouble = mock(DepartmentRepositoryImpl.class);
        IProgrammeEditionEnrolmentRepository PEERepoDouble = mock(ProgrammeEditionEnrolmentRepositoryImpl.class);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(PEERepoDouble, null, departmentRepoDouble, programmeDDDRepository)
        );
        assertEquals("Repositories cannot be null.", exception.getMessage());
    }

    //test when DepartmentRepo is null
    @Test
    void shouldThrowExceptionWhenDepartmentRepoIsNull() {
        // arrange
        IProgrammeRepository programmeDDDRepository = mock(ProgrammeRepositoryImpl.class);
        ISchoolYearRepository schoolYearRepoDouble = mock(SchoolYearRepositoryMem.class);
        IProgrammeEditionEnrolmentRepository PEERepoDouble = mock(ProgrammeEditionEnrolmentRepositoryImpl.class);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(PEERepoDouble, schoolYearRepoDouble, null, programmeDDDRepository)
        );
        assertEquals("Repositories cannot be null.", exception.getMessage());
    }

    //test that ensures that the method returns a positive int when there are students enrolled in Programmes from specified department and school year
    @Test
    void shouldReturnCorrectCountWhenStudentsAreEnrolledInDepartmentAndInSchoolYear() {
        // Arrange
        DepartmentID departmentDouble = mock(DepartmentID.class);
        SchoolYearID schoolYearDouble = mock(SchoolYearID.class);
        IProgrammeEditionEnrolmentRepository PEERepoDouble = mock(ProgrammeEditionEnrolmentRepositoryImpl.class);
        ISchoolYearRepository schoolYearRepoDouble = mock(SchoolYearRepositoryMem.class);
        IDepartmentRepository departmentRepoDouble = mock(DepartmentRepositoryImpl.class);
        IProgrammeRepository programmeDDDRepository = mock(ProgrammeRepositoryImpl.class);
        List<ProgrammeID> programmeIDs = List.of(mock(ProgrammeID.class));

        when(schoolYearRepoDouble.schoolYearExistsByID(schoolYearDouble)).thenReturn(true);
        when(departmentRepoDouble.departmentExists(departmentDouble)).thenReturn(true);
        when(programmeDDDRepository.findProgrammeByDepartment(departmentDouble)).thenReturn(programmeIDs);

        when(PEERepoDouble.countStudentsInProgrammesFromDepartmentInSchoolYear(schoolYearDouble, programmeIDs)).thenReturn(3);

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller =
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(PEERepoDouble, schoolYearRepoDouble, departmentRepoDouble, programmeDDDRepository);

        // Act
        int result = controller.countStudentsInProgrammesFromDepartmentInSchoolYear(departmentDouble, schoolYearDouble);

        // Assert
        assertEquals(3, result);
    }

    //test that ensures that the method throws an exception when Department is null
    @Test
    void shouldThrowExceptionWhenDepartmentIsNull() {
        // arrange
        SchoolYearID schoolYearDouble = mock(SchoolYearID.class);
        IProgrammeEditionEnrolmentRepository PEERepoDouble = mock(ProgrammeEditionEnrolmentRepositoryImpl.class);
        ISchoolYearRepository schoolYearRepoDouble = mock(SchoolYearRepositoryMem.class);
        IDepartmentRepository departmentRepoDouble = mock(DepartmentRepositoryImpl.class);
        IProgrammeRepository programmeDDDRepository = mock(ProgrammeRepositoryImpl.class);

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller =
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(PEERepoDouble, schoolYearRepoDouble, departmentRepoDouble, programmeDDDRepository);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {
            controller.countStudentsInProgrammesFromDepartmentInSchoolYear(null, schoolYearDouble);
        });
        // Assert
        assertEquals("Department or SchoolYear cannot be null", exception.getMessage());
    }

    //test that ensures that the method throws an exception when School Year is null
    @Test
    void shouldThrowExceptionWhenSchoolYearIsNull() {
        // arrange
        DepartmentID departmentID = mock(DepartmentID.class);
        IProgrammeEditionEnrolmentRepository PEERepoDouble = mock(ProgrammeEditionEnrolmentRepositoryImpl.class);
        ISchoolYearRepository schoolYearRepoDouble = mock(SchoolYearRepositoryMem.class);
        IDepartmentRepository departmentRepoDouble = mock(DepartmentRepositoryImpl.class);
        IProgrammeRepository programmeDDDRepository = mock(ProgrammeRepositoryImpl.class);

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller =
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(PEERepoDouble, schoolYearRepoDouble, departmentRepoDouble, programmeDDDRepository);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {
            controller.countStudentsInProgrammesFromDepartmentInSchoolYear(departmentID, null);
        });
        // Assert
        assertEquals("Department or SchoolYear cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfSchoolYearIsNotInRepo() {
        // Arrange
        DepartmentID departmentDouble = mock(DepartmentID.class);
        SchoolYearID schoolYearDouble = mock(SchoolYearID.class);
        IProgrammeEditionEnrolmentRepository PEERepoDouble = mock(ProgrammeEditionEnrolmentRepositoryImpl.class);
        ISchoolYearRepository schoolYearRepoDouble = mock(SchoolYearRepositoryMem.class);
        IDepartmentRepository departmentRepoDouble = mock(DepartmentRepositoryImpl.class);
        IProgrammeRepository programmeDDDRepository = mock(ProgrammeRepositoryImpl.class);
        List<ProgrammeID> programmeIDs = List.of(mock(ProgrammeID.class));

        when(schoolYearRepoDouble.schoolYearExistsByID(schoolYearDouble)).thenReturn(false);
        when(departmentRepoDouble.departmentExists(departmentDouble)).thenReturn(true);
        when(programmeDDDRepository.findProgrammeByDepartment(departmentDouble)).thenReturn(programmeIDs);

        when(PEERepoDouble.countStudentsInProgrammesFromDepartmentInSchoolYear(schoolYearDouble, programmeIDs)).thenReturn(3);

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller =
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(PEERepoDouble, schoolYearRepoDouble, departmentRepoDouble, programmeDDDRepository);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {
            controller.countStudentsInProgrammesFromDepartmentInSchoolYear(departmentDouble, schoolYearDouble);
        });
        // Assert
        assertEquals("SchoolYear does not exist.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfDepartmentIsNotInRepo() {
        // Arrange
        DepartmentID departmentDouble = mock(DepartmentID.class);
        SchoolYearID schoolYearDouble = mock(SchoolYearID.class);
        IProgrammeEditionEnrolmentRepository PEERepoDouble = mock(ProgrammeEditionEnrolmentRepositoryImpl.class);
        ISchoolYearRepository schoolYearRepoDouble = mock(SchoolYearRepositoryMem.class);
        IDepartmentRepository departmentRepoDouble = mock(DepartmentRepositoryImpl.class);
        IProgrammeRepository programmeDDDRepository = mock(ProgrammeRepositoryImpl.class);
        List<ProgrammeID> programmeIDs = List.of(mock(ProgrammeID.class));

        when(schoolYearRepoDouble.schoolYearExistsByID(schoolYearDouble)).thenReturn(true);
        when(departmentRepoDouble.departmentExists(departmentDouble)).thenReturn(false);
        when(programmeDDDRepository.findProgrammeByDepartment(departmentDouble)).thenReturn(programmeIDs);

        when(PEERepoDouble.countStudentsInProgrammesFromDepartmentInSchoolYear(schoolYearDouble, programmeIDs)).thenReturn(3);

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller =
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(PEERepoDouble, schoolYearRepoDouble, departmentRepoDouble, programmeDDDRepository);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {
            controller.countStudentsInProgrammesFromDepartmentInSchoolYear(departmentDouble, schoolYearDouble);
        });
        // Assert
        assertEquals("Department does not exist.", exception.getMessage());
    }

    @Test
    void testGetAllSchoolYears_NotNull() {
        IProgrammeEditionEnrolmentRepository PEERepoDouble = mock(ProgrammeEditionEnrolmentRepositoryImpl.class);
        ISchoolYearRepository schoolYearRepoDouble = mock(SchoolYearRepositoryMem.class);
        IDepartmentRepository departmentRepoDouble = mock(DepartmentRepositoryImpl.class);
        IProgrammeRepository programmeDDDRepository = mock(ProgrammeRepositoryImpl.class);

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller =
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(PEERepoDouble, schoolYearRepoDouble, departmentRepoDouble, programmeDDDRepository);

        // Act
        List<SchoolYearID> doubleSchoolYears = controller.getAllSchoolYearsIDs();

        // Assert
        assertNotNull(doubleSchoolYears, "The list of school years should not be null.");
    }

    @Test
    void testGetAllSchoolYears_IsNotEmptyList() {
        IProgrammeEditionEnrolmentRepository PEERepoDouble = mock(ProgrammeEditionEnrolmentRepositoryImpl.class);
        ISchoolYearRepository schoolYearRepoDouble = mock(SchoolYearRepositoryMem.class);
        IDepartmentRepository departmentRepoDouble = mock(DepartmentRepositoryImpl.class);
        IProgrammeRepository programmeDDDRepository = mock(ProgrammeRepositoryImpl.class);

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller =
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(PEERepoDouble, schoolYearRepoDouble, departmentRepoDouble, programmeDDDRepository);

        SchoolYearID doubleSchoolYear1 = mock(SchoolYearID.class);
        SchoolYearID doubleSchoolYear2 = mock(SchoolYearID.class);
        when(schoolYearRepoDouble.getAllSchoolYearsIDs()).thenReturn(List.of(doubleSchoolYear1, doubleSchoolYear2));
        // Act
        List<SchoolYearID> doubleSchoolYears = controller.getAllSchoolYearsIDs();

        // Assert
        assertTrue(doubleSchoolYears.size() == 2);
    }

    @Test
    void testGetAllSchoolYears_EmptyList() {
        IProgrammeEditionEnrolmentRepository PEERepoDouble = mock(ProgrammeEditionEnrolmentRepositoryImpl.class);
        ISchoolYearRepository schoolYearRepoDouble = mock(SchoolYearRepositoryMem.class);
        IDepartmentRepository departmentRepoDouble = mock(DepartmentRepositoryImpl.class);
        IProgrammeRepository programmeDDDRepository = mock(ProgrammeRepositoryImpl.class);

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller =
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(PEERepoDouble, schoolYearRepoDouble, departmentRepoDouble, programmeDDDRepository);

        // Act
        List<SchoolYearID> doubleSchoolYears = controller.getAllSchoolYearsIDs();

        // Assert
        assertTrue(doubleSchoolYears.isEmpty());
    }

    @Test
    void testGetAllDepartments_NotNull() {
        IProgrammeEditionEnrolmentRepository PEERepoDouble = mock(ProgrammeEditionEnrolmentRepositoryImpl.class);
        ISchoolYearRepository schoolYearRepoDouble = mock(SchoolYearRepositoryMem.class);
        IDepartmentRepository departmentRepoDouble = mock(DepartmentRepositoryImpl.class);
        IProgrammeRepository programmeDDDRepository = mock(ProgrammeRepositoryImpl.class);

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller =
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(PEERepoDouble, schoolYearRepoDouble, departmentRepoDouble, programmeDDDRepository);

        // Act
        Set<DepartmentID> doubleDepartment = controller.getAllDepartmentID();

        // Assert
        assertNotNull(doubleDepartment);
    }

    @Test
    void testGetAllDepartments_EmptyList() {
        IProgrammeEditionEnrolmentRepository PEERepoDouble = mock(ProgrammeEditionEnrolmentRepositoryImpl.class);
        ISchoolYearRepository schoolYearRepoDouble = mock(SchoolYearRepositoryMem.class);
        IDepartmentRepository departmentRepoDouble = mock(DepartmentRepositoryImpl.class);
        IProgrammeRepository programmeDDDRepository = mock(ProgrammeRepositoryImpl.class);

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller =
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(PEERepoDouble, schoolYearRepoDouble, departmentRepoDouble, programmeDDDRepository);

        // Act
        Set<DepartmentID> doubleDepartment = controller.getAllDepartmentID();

        // Assert
        assertTrue(doubleDepartment.isEmpty());
    }

    @Test
    void testGetAllDepartments_IsNotEmptyList() {
        IProgrammeEditionEnrolmentRepository PEERepoDouble = mock(ProgrammeEditionEnrolmentRepositoryImpl.class);
        ISchoolYearRepository schoolYearRepoDouble = mock(SchoolYearRepositoryMem.class);
        IDepartmentRepository departmentRepoDouble = mock(DepartmentRepositoryImpl.class);
        IProgrammeRepository programmeDDDRepository = mock(ProgrammeRepositoryImpl.class);
        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller =
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(PEERepoDouble, schoolYearRepoDouble, departmentRepoDouble, programmeDDDRepository);

        DepartmentID dp1 = mock(DepartmentID.class);
        DepartmentID dp2 = mock(DepartmentID.class);
        when(departmentRepoDouble.getDepartmentIDs()).thenReturn(Set.of(dp1, dp2));

        // Act
        Set<DepartmentID> doubleDepartment = controller.getAllDepartmentID();

        // Assert
        assertTrue(doubleDepartment.size() == 2);
    }
}

    /*
    //Integration Tests
     */
    //test that ensures that the method returns a positive int when there are students enrolled in Programmes from specified department and school year
    /*@Test
    void shouldReturnCorrectCountWhenStudentsAreEnrolledInDepartmentAndSchoolYear() throws Exception {
        // arrange
        Department department = new Department(new DepartmentAcronym("CSE"),new Name("Computer Science Engineer"),new TeacherID(new TeacherAcronym("PP")))
        Date date = new Date("25-12-2024");
        TeacherCategoryID tcID = new TeacherCategoryID();
        WorkingPercentage wp = new WorkingPercentage(100);
        TeacherID teacherID = new TeacherFactoryImpl().createTeacher(new TeacherAcronym("pp"),n)
        TeacherCareerProgressionFactoryImpl teacherCareerProgressionFactoryImpl = new TeacherCareerProgressionFactoryImpl();
        ITeacherCareerProgressionListFactory teacherCareerProgressionListFactoryImpl = new TeacherCareerProgressionListFactoryImpl();
        IAddressFactory addressFactory = new AddressFactoryImpl();
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015",
                "Porto", "Portugal", addressFactory, date, tcID, wp, teacherID, department,
                teacherCareerProgressionFactoryImpl, teacherCareerProgressionListFactoryImpl);

        Description description1 = new Description("School Year 25/26");
        Description description2 = new Description("School Year 22/23");
        Date startDate1 = new Date("01-09-2025");
        Date endDate1 = new Date("31-07-2026");
        Date startDate2 = new Date("01-09-2022");
        Date endDate2 = new Date("31-07-2026");
        SchoolYear schoolYear1 = new SchoolYear(description1, startDate1, endDate1);
        SchoolYear schoolYear2 = new SchoolYear(description2, startDate2, endDate2);

        DegreeType master = new DegreeType("Master", 240);
        Programme programme1 = new Programme("Licenciatura Engenharia Informática", "LEI", 25, 6, master, department, teacher, new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(),
                new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(), new CourseFactoryImpl());
        Programme programme2 = new Programme("Licenciatura Engenharia de Computação", "LEC", 30, 6, master, department, teacher, new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(),
                new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(), new CourseFactoryImpl());

        ProgrammeEdition edition1 = new ProgrammeEdition(programme1, schoolYear1);
        ProgrammeEdition edition2 = new ProgrammeEdition(programme2, schoolYear1);
        ProgrammeEdition edition3 = new ProgrammeEdition(programme2, schoolYear2);

        Address address1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Address address2 = new Address("Rua do Lumiar", "4554-566", "Porto", "Portugal");
        Address address3 = new Address("Rua da Pedra", "4556-575", "Porto", "Portugal");

        StudentID studentID1 = new StudentID(1234567);
        StudentID studentID2 = new StudentID(1345678);
        StudentID studentID3 = new StudentID(1456789);

        Name name1 = new Name("João Silva");
        Name name2 = new Name("Rita Mendes");
        Name name3 = new Name("Ana Luisa");

        String countryName = "Portugal";
        Country country = new Country(countryName);

        NIF nif1 = new NIF("123456789", country);
        NIF nif2 = new NIF("123455649", country);
        NIF nif3 = new NIF("123456439", country);

        PhoneNumber phone1 = new PhoneNumber("+351", "221234567");
        PhoneNumber phone2 = new PhoneNumber("+351", "221234567");
        PhoneNumber phone3 = new PhoneNumber("+351", "221234569");

        Email email1 = new Email("joao123@gmail.com");
        Email email2 = new Email("rita123@gmail.com");
        Email email3 = new Email("ana123@gmail.com");

        StudentAcademicEmail academicEmail1 = new StudentAcademicEmail(studentID1);
        StudentAcademicEmail academicEmail2 = new StudentAcademicEmail(studentID2);
        StudentAcademicEmail academicEmail3 = new StudentAcademicEmail(studentID3);

        Student student1 = new Student(studentID1, name1, nif1, phone1, email1, address1, academicEmail1);
        Student student2 = new Student(studentID2, name2, nif2, phone2, email2, address2, academicEmail2);
        Student student3 = new Student(studentID3, name3, nif3, phone3, email3, address3, academicEmail3);

        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentRepository pEERepo = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);

        pEERepo.enrolStudentInProgrammeEdition(student1, edition1);
        pEERepo.enrolStudentInProgrammeEdition(student2, edition2);
        pEERepo.enrolStudentInProgrammeEdition(student3, edition3);

        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepositoryMem schoolYearRepository = new SchoolYearRepositoryMem(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        schoolYearRepository.addSchoolYear(description1, startDate1, endDate1);
        schoolYearRepository.addSchoolYear(description2, startDate2, endDate2);

        DepartmentFactoryImpl departmentFactoryImpl = new DepartmentFactoryImpl();
        DepartmentListFactoryImpl departmentListFactoryImpl = new DepartmentListFactoryImpl();
        DepartmentRepository departmentRepository = new DepartmentRepository(departmentFactoryImpl, departmentListFactoryImpl);
        departmentRepository.registerDepartment("CSE", "Computer Science Engineer");

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller = new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(pEERepo, schoolYearRepository, departmentRepository);
        // act
        int result = controller.countStudentsInProgrammesFromDepartmentInSchoolYear(department, schoolYear1);

        // assert
        assertEquals(2, result);
    }

    //test that ensures that the method throws an exception when School Year is not present in the School Year Repository
    @Test
    void shouldThrowExceptionWhenSchoolYearDoesntExist() throws Exception {
        // arrange
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        Description description = new Description("School Year 24/25");
        Date startDate1 = new Date("01-09-2024");
        Date endDate1 = new Date("31-07-2025");
        Date startDate2 = new Date("01-09-2022");
        Date endDate2 = new Date("31-07-2023");

        SchoolYear schoolYear1 = new SchoolYear(description, startDate1, endDate1);
        SchoolYear schoolYear2 = new SchoolYear(description, startDate2, endDate2);

        Date date = new Date("25-12-2024");
        TeacherCategoryID tcID = new TeacherCategoryID();
        WorkingPercentage wp = new WorkingPercentage(100);
        TeacherID teacherID = TeacherID.createNew();
        IAddressFactory addressFactory = new AddressFactoryImpl();
        TeacherCareerProgressionFactoryImpl teacherCareerProgressionFactoryImpl = new TeacherCareerProgressionFactoryImpl();
        ITeacherCareerProgressionListFactory teacherCareerProgressionListFactory = new TeacherCareerProgressionListFactoryImpl();
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015",
                "Porto", "Portugal", addressFactory, date, tcID, wp, teacherID, department1,
                teacherCareerProgressionFactoryImpl, teacherCareerProgressionListFactory);

        DegreeType master = new DegreeType("Master", 240);
        Programme programme1 = new Programme("Licenciatura Engenharia Informática", "LEI", 25, 6, master, department1, teacher1, new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(),
                new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(), new CourseFactoryImpl());
        Programme programme2 = new Programme("Licenciatura Engenharia de Computação", "LEC", 30, 6, master, department1, teacher1, new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(),
                new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(), new CourseFactoryImpl());

        ProgrammeEdition edition1 = new ProgrammeEdition(programme1, schoolYear1);
        ProgrammeEdition edition2 = new ProgrammeEdition(programme2, schoolYear1);
        ProgrammeEdition edition3 = new ProgrammeEdition(programme2, schoolYear2);

        Address address1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Address address2 = new Address("Rua do Lumiar", "4554-566", "Porto", "Portugal");
        Address address3 = new Address("Rua da Pedra", "4556-575", "Porto", "Portugal");

        StudentID studentID1 = new StudentID(1234567);
        StudentID studentID2 = new StudentID(1345678);
        StudentID studentID3 = new StudentID(1456789);

        Name name1 = new Name("João Silva");
        Name name2 = new Name("Rita Mendes");
        Name name3 = new Name("Ana Luisa");
        String countryName = "Portugal";
        Country country = new Country(countryName);

        NIF nif1 = new NIF("123456789", country);
        NIF nif2 = new NIF("123455649", country);
        NIF nif3 = new NIF("123456439", country);

        PhoneNumber phone1 = new PhoneNumber("+351", "221234567");
        PhoneNumber phone2 = new PhoneNumber("+351", "221234567");
        PhoneNumber phone3 = new PhoneNumber("+351", "221234569");

        Email email1 = new Email("joao123@gmail.com");
        Email email2 = new Email("rita123@gmail.com");
        Email email3 = new Email("ana123@gmail.com");

        StudentAcademicEmail academicEmail1 = new StudentAcademicEmail(studentID1);
        StudentAcademicEmail academicEmail2 = new StudentAcademicEmail(studentID2);
        StudentAcademicEmail academicEmail3 = new StudentAcademicEmail(studentID3);

        Student student1 = new Student(studentID1, name1, nif1, phone1, email1, address1, academicEmail1);
        Student student2 = new Student(studentID2, name2, nif2, phone2, email2, address2, academicEmail2);
        Student student3 = new Student(studentID3, name3, nif3, phone3, email3, address3, academicEmail3);


        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentRepository pEERepo = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);

        pEERepo.enrolStudentInProgrammeEdition(student1, edition1);
        pEERepo.enrolStudentInProgrammeEdition(student2, edition2);
        pEERepo.enrolStudentInProgrammeEdition(student3, edition3);

        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepositoryMem schoolYearRepository = new SchoolYearRepositoryMem(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        schoolYearRepository.addSchoolYear(description, startDate2, endDate2);

        DepartmentFactoryImpl departmentFactoryImpl = new DepartmentFactoryImpl();
        DepartmentListFactoryImpl departmentListFactoryImpl = new DepartmentListFactoryImpl();
        DepartmentRepository departmentRepository = new DepartmentRepository(departmentFactoryImpl, departmentListFactoryImpl);
        departmentRepository.registerDepartment("DEI", "Departamento Engenharia Informática");

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller = new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(pEERepo, schoolYearRepository, departmentRepository);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {
            controller.countStudentsInProgrammesFromDepartmentInSchoolYear(department1, schoolYear1);
        });
        // Assert
        assertEquals("SchoolYear does not exist.", exception.getMessage());
    }

    //test that ensures that the method throws an exception when Department is not present in the Department Repository
    @Test
    void shouldThrowExceptionWhenDepartmentDoesNotExist() throws Exception {
        // arrange
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");

        Description description = new Description("School Year 24/25");
        Date startDate1 = new Date("01-09-2024");
        Date endDate1 = new Date("31-07-2025");
        Date startDate2 = new Date("01-09-2022");
        Date endDate2 = new Date("31-07-2023");
        SchoolYear schoolYear1 = new SchoolYear(description, startDate1, endDate1);
        SchoolYear schoolYear2 = new SchoolYear(description, startDate2, endDate2);

        Date date = new Date("25-12-2024");
        TeacherCategoryID tcID = new TeacherCategoryID();
        WorkingPercentage wp = new WorkingPercentage(100);
        TeacherID teacherID = TeacherID.createNew();
        IAddressFactory addressFactory = new AddressFactoryImpl();
        TeacherCareerProgressionFactoryImpl teacherCareerProgressionFactoryImpl = new TeacherCareerProgressionFactoryImpl();
        ITeacherCareerProgressionListFactory teacherCareerProgressionListFactory = new TeacherCareerProgressionListFactoryImpl();
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015",
                "Porto", "Portugal", addressFactory, date, tcID, wp, teacherID, department1,
                teacherCareerProgressionFactoryImpl, teacherCareerProgressionListFactory);

        DegreeType master = new DegreeType("Master", 240);
        Programme programme1 = new Programme("Licenciatura Engenharia Informática", "LEI", 25, 6, master, department1, teacher1, new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(),
                new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(), new CourseFactoryImpl());
        Programme programme2 = new Programme("Licenciatura Engenharia de Computação", "LEC", 30, 6, master, department1, teacher1, new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(),
                new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(), new CourseFactoryImpl());

        ProgrammeEdition edition1 = new ProgrammeEdition(programme1, schoolYear1);
        ProgrammeEdition edition2 = new ProgrammeEdition(programme2, schoolYear1);
        ProgrammeEdition edition3 = new ProgrammeEdition(programme2, schoolYear2);

        Address address1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Address address2 = new Address("Rua do Lumiar", "4554-566", "Porto", "Portugal");
        Address address3 = new Address("Rua da Pedra", "4556-575", "Porto", "Portugal");

        StudentID studentID1 = new StudentID(1234567);
        StudentID studentID2 = new StudentID(1345678);
        StudentID studentID3 = new StudentID(1456789);

        Name name1 = new Name("João Silva");
        Name name2 = new Name("Rita Mendes");
        Name name3 = new Name("Ana Luisa");

        String countryName = "Portugal";
        Country country = new Country(countryName);

        NIF nif1 = new NIF("123456789", country);
        NIF nif2 = new NIF("123455649", country);
        NIF nif3 = new NIF("123456439", country);

        PhoneNumber phone1 = new PhoneNumber("+351", "221234567");
        PhoneNumber phone2 = new PhoneNumber("+351", "221234567");
        PhoneNumber phone3 = new PhoneNumber("+351", "221234569");

        Email email1 = new Email("joao123@gmail.com");
        Email email2 = new Email("rita123@gmail.com");
        Email email3 = new Email("ana123@gmail.com");

        StudentAcademicEmail academicEmail1 = new StudentAcademicEmail(studentID1);
        StudentAcademicEmail academicEmail2 = new StudentAcademicEmail(studentID2);
        StudentAcademicEmail academicEmail3 = new StudentAcademicEmail(studentID3);

        Student student1 = new Student(studentID1, name1, nif1, phone1, email1, address1, academicEmail1);
        Student student2 = new Student(studentID2, name2, nif2, phone2, email2, address2, academicEmail2);
        Student student3 = new Student(studentID3, name3, nif3, phone3, email3, address3, academicEmail3);


        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentRepository pEERepo = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);

        pEERepo.enrolStudentInProgrammeEdition(student1, edition1);
        pEERepo.enrolStudentInProgrammeEdition(student2, edition2);
        pEERepo.enrolStudentInProgrammeEdition(student3, edition3);

        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepositoryMem schoolYearRepository = new SchoolYearRepositoryMem(schoolYearFactoryImpl, schoolYearListFactoryImpl);

        schoolYearRepository.addSchoolYear(description, startDate1, endDate1);
        schoolYearRepository.addSchoolYear(description, startDate2, endDate2);

        DepartmentFactoryImpl departmentFactoryImpl = new DepartmentFactoryImpl();
        DepartmentListFactoryImpl departmentListFactoryImpl = new DepartmentListFactoryImpl();
        DepartmentRepository departmentRepository = new DepartmentRepository(departmentFactoryImpl, departmentListFactoryImpl);
        departmentRepository.registerDepartment("DEI", "Departamento Engenharia Informática");

        Department department2 = new Department("MAT", "Mat");
        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller = new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(pEERepo, schoolYearRepository, departmentRepository);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {
            controller.countStudentsInProgrammesFromDepartmentInSchoolYear(department2, schoolYear1);
        });

        // Assert
        assertEquals("Department does not exist.", exception.getMessage());
    }

    //test that ensures that the method throws an exception when Department is null
    @Test
    void shouldThrowExceptionWhenDepartmentIs_Null() throws Exception {
        // arrange
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");

        Description description = new Description("School Year 24/25");
        Date startDate1 = new Date("01-09-2024");
        Date endDate1 = new Date("31-07-2025");
        Date startDate2 = new Date("01-09-2022");
        Date endDate2 = new Date("31-07-2023");
        SchoolYear schoolYear1 = new SchoolYear(description, startDate1, endDate1);
        SchoolYear schoolYear2 = new SchoolYear(description, startDate2, endDate2);

        Date date = new Date("25-12-2024");
        TeacherCategoryID tcID = new TeacherCategoryID();
        WorkingPercentage wp = new WorkingPercentage(100);
        TeacherID teacherID = TeacherID.createNew();
        IAddressFactory addressFactory = new AddressFactoryImpl();
        TeacherCareerProgressionFactoryImpl teacherCareerProgressionFactoryImpl = new TeacherCareerProgressionFactoryImpl();
        ITeacherCareerProgressionListFactory teacherCareerProgressionListFactory = new TeacherCareerProgressionListFactoryImpl();
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015",
                "Porto", "Portugal", addressFactory, date, tcID, wp, teacherID, department1,
                teacherCareerProgressionFactoryImpl, teacherCareerProgressionListFactory);

        DegreeType master = new DegreeType("Master", 240);
        Programme programme1 = new Programme("Licenciatura Engenharia Informática", "LEI", 25, 6, master, department1, teacher1, new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(),
                new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(), new CourseFactoryImpl());
        Programme programme2 = new Programme("Licenciatura Engenharia de Computação", "LEC", 30, 6, master, department1, teacher1, new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(),
                new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(), new CourseFactoryImpl());

        ProgrammeEdition edition1 = new ProgrammeEdition(programme1, schoolYear1);
        ProgrammeEdition edition2 = new ProgrammeEdition(programme2, schoolYear1);
        ProgrammeEdition edition3 = new ProgrammeEdition(programme2, schoolYear2);

        Address address1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Address address2 = new Address("Rua do Lumiar", "4554-566", "Porto", "Portugal");
        Address address3 = new Address("Rua da Pedra", "4556-575", "Porto", "Portugal");

        StudentID studentID1 = new StudentID(1234567);
        StudentID studentID2 = new StudentID(1345678);
        StudentID studentID3 = new StudentID(1456789);

        Name name1 = new Name("João Silva");
        Name name2 = new Name("Rita Mendes");
        Name name3 = new Name("Ana Luisa");

        String countryName = "Portugal";
        Country country = new Country(countryName);

        NIF nif1 = new NIF("123456789", country);
        NIF nif2 = new NIF("123455649", country);
        NIF nif3 = new NIF("123456439", country);

        PhoneNumber phone1 = new PhoneNumber("+351", "221234567");
        PhoneNumber phone2 = new PhoneNumber("+351", "221234567");
        PhoneNumber phone3 = new PhoneNumber("+351", "221234569");

        Email email1 = new Email("joao123@gmail.com");
        Email email2 = new Email("rita123@gmail.com");
        Email email3 = new Email("ana123@gmail.com");

        StudentAcademicEmail academicEmail1 = new StudentAcademicEmail(studentID1);
        StudentAcademicEmail academicEmail2 = new StudentAcademicEmail(studentID2);
        StudentAcademicEmail academicEmail3 = new StudentAcademicEmail(studentID3);

        Student student1 = new Student(studentID1, name1, nif1, phone1, email1, address1, academicEmail1);
        Student student2 = new Student(studentID2, name2, nif2, phone2, email2, address2, academicEmail2);
        Student student3 = new Student(studentID3, name3, nif3, phone3, email3, address3, academicEmail3);

        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentRepository pEERepo = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);

        pEERepo.enrolStudentInProgrammeEdition(student1, edition1);
        pEERepo.enrolStudentInProgrammeEdition(student2, edition2);
        pEERepo.enrolStudentInProgrammeEdition(student3, edition3);

        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepositoryMem schoolYearRepository = new SchoolYearRepositoryMem(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        schoolYearRepository.addSchoolYear(description, startDate1, endDate1);
        schoolYearRepository.addSchoolYear(description, startDate2, endDate2);

        DepartmentFactoryImpl departmentFactoryImpl = new DepartmentFactoryImpl();
        DepartmentListFactoryImpl departmentListFactoryImpl = new DepartmentListFactoryImpl();
        DepartmentRepository departmentRepository = new DepartmentRepository(departmentFactoryImpl, departmentListFactoryImpl);
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
    void shouldThrowExceptionWhenSchoolYearIs_Null() throws Exception {
        // arrange
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");

        Description description = new Description("School Year 24/25");
        Date startDate1 = new Date("01-09-2024");
        Date endDate1 = new Date("31-07-2025");
        Date startDate2 = new Date("01-09-2022");
        Date endDate2 = new Date("31-07-2023");
        SchoolYear schoolYear1 = new SchoolYear(description, startDate1, endDate1);
        SchoolYear schoolYear2 = new SchoolYear(description, startDate2, endDate2);

        Date date = new Date("25-12-2024");
        TeacherCategoryID tcID = new TeacherCategoryID();
        WorkingPercentage wp = new WorkingPercentage(100);
        TeacherID teacherID = TeacherID.createNew();
        IAddressFactory addressFactory = new AddressFactoryImpl();
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        TeacherCareerProgressionFactoryImpl teacherCareerProgressionFactoryImpl = new TeacherCareerProgressionFactoryImpl();
        ITeacherCareerProgressionListFactory teacherCareerProgressionListFactory = new TeacherCareerProgressionListFactoryImpl();
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015",
                "Porto", "Portugal", addressFactory, date, tcID, wp, teacherID, department1,
                teacherCareerProgressionFactoryImpl, teacherCareerProgressionListFactory);

        DegreeType master = new DegreeType("Master", 240);
        Programme programme1 = new Programme("Licenciatura Engenharia Informática", "LEI", 25, 6, master, department1, teacher1, new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(),
                new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(), new CourseFactoryImpl());
        Programme programme2 = new Programme("Licenciatura Engenharia de Computação", "LEC", 30, 6, master, department1, teacher1, new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(),
                new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(), new CourseFactoryImpl());

        ProgrammeEdition edition1 = new ProgrammeEdition(programme1, schoolYear1);
        ProgrammeEdition edition2 = new ProgrammeEdition(programme2, schoolYear1);
        ProgrammeEdition edition3 = new ProgrammeEdition(programme2, schoolYear2);

        Address address1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Address address2 = new Address("Rua do Lumiar", "4554-566", "Porto", "Portugal");
        Address address3 = new Address("Rua da Pedra", "4556-575", "Porto", "Portugal");

        StudentID studentID1 = new StudentID(1234567);
        StudentID studentID2 = new StudentID(1345678);
        StudentID studentID3 = new StudentID(1456789);

        Name name1 = new Name("João Silva");
        Name name2 = new Name("Rita Mendes");
        Name name3 = new Name("Ana Luisa");

        String countryName = "Portugal";
        Country country = new Country(countryName);

        NIF nif1 = new NIF("123456789", country);
        NIF nif2 = new NIF("123455649", country);
        NIF nif3 = new NIF("123456439", country);

        PhoneNumber phone1 = new PhoneNumber("+351", "221234567");
        PhoneNumber phone2 = new PhoneNumber("+351", "221234567");
        PhoneNumber phone3 = new PhoneNumber("+351", "221234569");

        Email email1 = new Email("joao123@gmail.com");
        Email email2 = new Email("rita123@gmail.com");
        Email email3 = new Email("ana123@gmail.com");

        StudentAcademicEmail academicEmail1 = new StudentAcademicEmail(studentID1);
        StudentAcademicEmail academicEmail2 = new StudentAcademicEmail(studentID2);
        StudentAcademicEmail academicEmail3 = new StudentAcademicEmail(studentID3);

        Student student1 = new Student(studentID1, name1, nif1, phone1, email1, address1, academicEmail1);
        Student student2 = new Student(studentID2, name2, nif2, phone2, email2, address2, academicEmail2);
        Student student3 = new Student(studentID3, name3, nif3, phone3, email3, address3, academicEmail3);

        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentRepository pEERepo = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);

        pEERepo.enrolStudentInProgrammeEdition(student1, edition1);
        pEERepo.enrolStudentInProgrammeEdition(student2, edition2);
        pEERepo.enrolStudentInProgrammeEdition(student3, edition3);

        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepositoryMem schoolYearRepository = new SchoolYearRepositoryMem(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        schoolYearRepository.addSchoolYear(description, startDate1, endDate1);
        schoolYearRepository.addSchoolYear(description, startDate2, endDate2);

        DepartmentFactoryImpl departmentFactoryImpl = new DepartmentFactoryImpl();
        DepartmentListFactoryImpl departmentListFactoryImpl = new DepartmentListFactoryImpl();
        DepartmentRepository departmentRepository = new DepartmentRepository(departmentFactoryImpl, departmentListFactoryImpl);
        departmentRepository.registerDepartment("DEI", "Departamento Engenharia Informática");

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller = new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(pEERepo, schoolYearRepository, departmentRepository);
        // Act
        Exception exception = assertThrows(Exception.class, () -> {
            controller.countStudentsInProgrammesFromDepartmentInSchoolYear(department1, null);
        });

        // Assert
        assertEquals("Department or SchoolYear cannot be null", exception.getMessage());
    }
    private createDepartment(){
        return new Department();
    }
    private createDepartment
} */