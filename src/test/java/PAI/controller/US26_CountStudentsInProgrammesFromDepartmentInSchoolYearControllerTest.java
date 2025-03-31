package PAI.controller;
import PAI.VOs.*;
import PAI.domain.*;
import PAI.factory.*;
import PAI.repository.DepartmentRepository;
import PAI.repository.ProgrammeEditionEnrolmentRepository;
import PAI.repository.SchoolYearRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US26_CountStudentsInProgrammesFromDepartmentInSchoolYearControllerTest {

    //testing the constructor
    //valid constructor
    @Test
    void shouldCreateControllerWhenRepositoriesAreValid() {
        // arrange
        ProgrammeEditionEnrolmentRepository PEERepoDouble = mock(ProgrammeEditionEnrolmentRepository.class);
        SchoolYearRepository schoolYearRepoDouble= mock(SchoolYearRepository.class);
        DepartmentRepository departmentRepoDouble= mock(DepartmentRepository.class);

        // Act & Assert
        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller =
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(PEERepoDouble, schoolYearRepoDouble, departmentRepoDouble);
    }

    //test when ProgramEditionEnrollmentRepo is null
    @Test
    void shouldThrowExceptionWhenPEERepoIsNull(){
        // arrange
        SchoolYearRepository schoolYearRepoDouble= mock(SchoolYearRepository.class);
        DepartmentRepository departmentRepoDouble= mock(DepartmentRepository.class);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(null, schoolYearRepoDouble, departmentRepoDouble)
        );
        assertEquals("Repositories cannot be null.", exception.getMessage());
    }

    //test when SchoolYearRepo is null
    @Test
    void shouldThrowExceptionWhenSchoolYearRepoIsNull(){
        // arrange
        ProgrammeEditionEnrolmentRepository PEERepoDouble = mock(ProgrammeEditionEnrolmentRepository.class);
        DepartmentRepository departmentRepoDouble= mock(DepartmentRepository.class);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(PEERepoDouble, null, departmentRepoDouble)
        );
        assertEquals("Repositories cannot be null.", exception.getMessage());
    }

    //test when DepartmentRepo is null
    @Test
    void shouldThrowExceptionWhenDepartmentRepoNull(){
        // arrange
        ProgrammeEditionEnrolmentRepository PEERepoDouble = mock(ProgrammeEditionEnrolmentRepository.class);
        SchoolYearRepository schoolYearRepoDouble= mock(SchoolYearRepository.class);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(PEERepoDouble, schoolYearRepoDouble, null)
        );
        assertEquals("Repositories cannot be null.", exception.getMessage());
    }

    //test that ensures that the method returns a positive int when there are students enrolled in Programmes from specified department and school year
    @Test
    void shouldReturnCorrectCountWhenStudentsAreEnrolledInDepartmentAndInSchoolYear() {
        // Arrange
        Department departmentDouble = mock(Department.class);
        SchoolYear schoolYearDouble = mock(SchoolYear.class);
        ProgrammeEditionEnrolmentRepository PEERepoDouble = mock(ProgrammeEditionEnrolmentRepository.class);
        SchoolYearRepository schoolYearRepoDouble = mock(SchoolYearRepository.class);
        DepartmentRepository departmentRepoDouble = mock(DepartmentRepository.class);

        when(schoolYearRepoDouble.schoolYearExists(schoolYearDouble)).thenReturn(true);
        when(departmentRepoDouble.departmentExists(departmentDouble)).thenReturn(true);

        when(PEERepoDouble.countStudentsInProgrammesFromDepartmentInSchoolYear(departmentDouble, schoolYearDouble)).thenReturn(3);

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller =
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(PEERepoDouble, schoolYearRepoDouble, departmentRepoDouble);

        // Act
        int result = controller.countStudentsInProgrammesFromDepartmentInSchoolYear(departmentDouble, schoolYearDouble);

        // Assert
        assertEquals(3, result);
    }

    //test that ensures that the method throws an exception when Department is null
    @Test
    void shouldThrowExceptionWhenDepartmentIsNull() {
        // arrange
        ProgrammeEditionEnrolmentRepository pEERepoDouble = mock(ProgrammeEditionEnrolmentRepository.class);
        SchoolYearRepository schoolYearRepoDouble = mock(SchoolYearRepository.class);
        DepartmentRepository departmentRepoDouble = mock(DepartmentRepository.class);

        SchoolYear schoolYearDouble= mock(SchoolYear.class);

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller =
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(pEERepoDouble, schoolYearRepoDouble, departmentRepoDouble);

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
        ProgrammeEditionEnrolmentRepository pEERepoDouble = mock(ProgrammeEditionEnrolmentRepository.class);
        SchoolYearRepository schoolYearRepoDouble = mock(SchoolYearRepository.class);
        DepartmentRepository departmentRepoDouble = mock(DepartmentRepository.class);

       Department departmentDouble= mock(Department.class);

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller =
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(pEERepoDouble, schoolYearRepoDouble, departmentRepoDouble);
        // Act
        Exception exception = assertThrows(Exception.class, () -> {
            controller.countStudentsInProgrammesFromDepartmentInSchoolYear(departmentDouble, null);
        });

        // Assert
        assertEquals("Department or SchoolYear cannot be null", exception.getMessage());
    }

    //Integration Tests
    //test that ensures that the method returns a positive int when there are students enrolled in Programmes from specified department and school year
    @Test
    void shouldReturnCorrectCountWhenStudentsAreEnrolledInDepartmentAndSchoolYear() throws Exception {
        // arrange
        Department department = new Department("CSE", "Computer Science Engineer");

        Date date = new Date("25-12-2024");
        TeacherCategoryID tcID = new TeacherCategoryID();
        WorkingPercentage wp = new WorkingPercentage(100);
        TeacherID teacherID = TeacherID.createNew();
        TeacherCareerProgressionFactoryImpl teacherCareerProgressionFactoryImpl = new TeacherCareerProgressionFactoryImpl();
        ITeacherCareerProgressionListFactory teacherCareerProgressionListFactoryImpl = new TeacherCareerProgressionListFactoryImpl();
        IAddressFactory addressFactory = new AddressFactoryImpl();
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015",
                "Porto", "Portugal", addressFactory, date, tcID, wp, teacherID, department,
                teacherCareerProgressionFactoryImpl, teacherCareerProgressionListFactoryImpl);

        Description description1 = new Description("School Year 25/26");
        Description description2 = new Description("School Year 22/23");
        Date startDate1 = new Date ("01-09-2025");
        Date endDate1 = new Date ("31-07-2026");
        Date startDate2 = new Date ("01-09-2022");
        Date endDate2 = new Date ("31-07-2026");
        SchoolYear schoolYear1 = new SchoolYear(description1,startDate1, endDate1);
        SchoolYear schoolYear2 = new SchoolYear(description2,startDate2, endDate2);

        DegreeType master = new DegreeType("Master", 240);
        Programme programme1 = new Programme("Licenciatura Engenharia Informática", "LEI", 25, 6, master, department, teacher,  new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(),
                new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(), new CourseFactoryImpl());
        Programme programme2 = new Programme("Licenciatura Engenharia de Computação", "LEC", 30, 6, master, department, teacher,  new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(),
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

        NIF nif1 = new NIF("123456789");
        NIF nif2 = new NIF("123455649");
        NIF nif3 = new NIF("123456439");

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

        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory= new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory= new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentRepository pEERepo = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);

        pEERepo.enrolStudentInProgrammeEdition(student1, edition1);
        pEERepo.enrolStudentInProgrammeEdition(student2, edition2);
        pEERepo.enrolStudentInProgrammeEdition(student3, edition3);

        SchoolYearFactoryImpl schoolYearFactoryImpl= new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl= new SchoolYearListFactoryImpl();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl,schoolYearListFactoryImpl);
        schoolYearRepository.addSchoolYear(description1,startDate1, endDate1);
        schoolYearRepository.addSchoolYear(description2,startDate2, endDate2);

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
        Date startDate1 = new Date ("01-09-2024");
        Date endDate1 = new Date ("31-07-2025");
        Date startDate2 = new Date ("01-09-2022");
        Date endDate2 = new Date ("31-07-2023");

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

        NIF nif1 = new NIF("123456789");
        NIF nif2 = new NIF("123455649");
        NIF nif3 = new NIF("123456439");

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


        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory= new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory= new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentRepository pEERepo = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);

        pEERepo.enrolStudentInProgrammeEdition(student1, edition1);
        pEERepo.enrolStudentInProgrammeEdition(student2, edition2);
        pEERepo.enrolStudentInProgrammeEdition(student3, edition3);

        SchoolYearFactoryImpl schoolYearFactoryImpl= new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl= new SchoolYearListFactoryImpl();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl,schoolYearListFactoryImpl);
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
        Date startDate1 = new Date ("01-09-2024");
        Date endDate1 = new Date ("31-07-2025");
        Date startDate2 = new Date ("01-09-2022");
        Date endDate2 = new Date ("31-07-2023");
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

        NIF nif1 = new NIF("123456789");
        NIF nif2 = new NIF("123455649");
        NIF nif3 = new NIF("123456439");

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


        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory= new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory= new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentRepository pEERepo = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);

        pEERepo.enrolStudentInProgrammeEdition(student1, edition1);
        pEERepo.enrolStudentInProgrammeEdition(student2, edition2);
        pEERepo.enrolStudentInProgrammeEdition(student3, edition3);

        SchoolYearFactoryImpl schoolYearFactoryImpl= new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl= new SchoolYearListFactoryImpl();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl,schoolYearListFactoryImpl);

        schoolYearRepository.addSchoolYear(description,startDate1, endDate1);
        schoolYearRepository.addSchoolYear(description,startDate2, endDate2);

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
        Date startDate1 = new Date ("01-09-2024");
        Date endDate1 = new Date ("31-07-2025");
        Date startDate2 = new Date ("01-09-2022");
        Date endDate2 = new Date ("31-07-2023");
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

        NIF nif1 = new NIF("123456789");
        NIF nif2 = new NIF("123455649");
        NIF nif3 = new NIF("123456439");

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

        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory= new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory= new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentRepository pEERepo = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);

        pEERepo.enrolStudentInProgrammeEdition(student1, edition1);
        pEERepo.enrolStudentInProgrammeEdition(student2, edition2);
        pEERepo.enrolStudentInProgrammeEdition(student3, edition3);

        SchoolYearFactoryImpl schoolYearFactoryImpl= new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl= new SchoolYearListFactoryImpl();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl,schoolYearListFactoryImpl);
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
        Date startDate1 = new Date ("01-09-2024");
        Date endDate1 = new Date ("31-07-2025");
        Date startDate2 = new Date ("01-09-2022");
        Date endDate2 = new Date ("31-07-2023");
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

        NIF nif1 = new NIF("123456789");
        NIF nif2 = new NIF("123455649");
        NIF nif3 = new NIF("123456439");

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

        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory= new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory= new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentRepository pEERepo = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);

        pEERepo.enrolStudentInProgrammeEdition(student1, edition1);
        pEERepo.enrolStudentInProgrammeEdition(student2, edition2);
        pEERepo.enrolStudentInProgrammeEdition(student3, edition3);

        SchoolYearFactoryImpl schoolYearFactoryImpl= new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl= new SchoolYearListFactoryImpl();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl,schoolYearListFactoryImpl);
        schoolYearRepository.addSchoolYear(description, startDate1,endDate1);
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
}