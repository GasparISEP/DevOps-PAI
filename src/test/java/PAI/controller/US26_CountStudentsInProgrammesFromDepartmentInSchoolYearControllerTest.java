package PAI.controller;

import PAI.VOs.*;
import PAI.assembler.programme.IProgrammeAssembler;
import PAI.assembler.programme.ProgrammeAssembler;
import PAI.domain.degreeType.DegreeTypeFactoryImpl;
import PAI.domain.degreeType.IDegreeTypeFactory;
import PAI.domain.department.Department;
import PAI.domain.department.IDepartmentFactory;
import PAI.domain.programmeEditionEnrolment.IProgrammeEditionEnrolmentFactory;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolment;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolmentFactoryImpl;
import PAI.domain.repositoryInterfaces.degreeType.IDegreeTypeRepository;
import PAI.domain.repositoryInterfaces.teacher.ITeacherRepository;
import PAI.domain.schoolYear.ISchoolYearFactory;
import PAI.assembler.schoolYear.ISchoolYearAssembler;
import PAI.assembler.schoolYear.SchoolYearAssembler;
import PAI.domain.teacher.ITeacherFactory;
import PAI.domain.teacher.TeacherFactoryImpl;
import PAI.persistence.mem.degreeType.DegreeTypeListFactoryImpl;
import PAI.persistence.mem.degreeType.DegreeTypeRepositoryImpl;
import PAI.persistence.mem.degreeType.IDegreeTypeListFactory;
import PAI.persistence.mem.programmeEditionEnrolment.ProgrammeEditionEnrolmentListFactoryImpl;
import PAI.persistence.mem.programmeEnrolment.IProgrammeEnrolmentListFactory;
import PAI.persistence.mem.programmeEnrolment.ProgrammeEnrolmentListFactoryImpl;
import PAI.domain.schoolYear.SchoolYear;
import PAI.domain.student.Student;
import PAI.persistence.mem.courseEditionEnrolment.CourseEditionEnrolmentListFactoryImpl;
import PAI.domain.department.DepartmentFactoryImpl;
import PAI.persistence.mem.department.DepartmentListFactoryImpl;
import PAI.domain.programme.Programme;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.domain.schoolYear.SchoolYearFactoryImpl;
import PAI.domain.courseEdition.CourseEditionFactoryImpl;
import PAI.persistence.mem.courseEdition.CourseEditionListFactoryImpl;
import PAI.domain.courseEdition.ICourseEditionFactory;
import PAI.persistence.mem.courseEdition.ICourseEditionListFactory;
import PAI.domain.programme.IProgrammeFactory;
import PAI.domain.programme.ProgrammeFactoryImpl;
import PAI.persistence.mem.courseEditionEnrolment.CourseEditionEnrolmentRepositoryImpl;
import PAI.persistence.mem.schoolYear.SchoolYearListFactoryImpl;
import PAI.persistence.mem.schoolYear.SchoolYearRepositoryImpl;
import PAI.persistence.mem.department.DepartmentRepositoryImpl;
import PAI.domain.repositoryInterfaces.department.IDepartmentRepository;
import PAI.persistence.mem.programme.IProgrammeRepositoryListFactory;
import PAI.persistence.mem.programme.ProgrammeRepositoryImpl;
import PAI.persistence.mem.programme.ProgrammeRepositoryListFactoryImpl;
import PAI.persistence.mem.programmeEdition.IProgrammeEditionListFactory;
import PAI.persistence.mem.programmeEdition.ProgrammeEditionListFactoryImpl;
import PAI.persistence.mem.programmeEdition.ProgrammeEditionRepositoryImpl;
import PAI.persistence.mem.courseEdition.CourseEditionRepositoryImpl;
import PAI.domain.repositoryInterfaces.programmeEnrolment.IProgrammeEnrolmentRepository;
import PAI.persistence.mem.programmeEditionEnrolment.ProgrammeEditionEnrolmentRepositoryImpl;
import PAI.persistence.mem.programmeEnrolment.ProgrammeEnrolmentRepositoryImpl;
import PAI.domain.repositoryInterfaces.programmeEdition.IProgrammeEditionRepository;
import PAI.domain.repositoryInterfaces.programme.IProgrammeRepository;
import PAI.persistence.springdata.department.DepartmentRepositorySpringDataImpl;
import PAI.persistence.springdata.teacher.TeacherRepositorySpringDataImpl;
import PAI.service.degreeType.DegreeTypeRegistrationServiceImpl;
import PAI.service.degreeType.DegreeTypeService;
import PAI.service.degreeType.IDegreeTypeRegistrationService;
import PAI.service.degreeType.IDegreeTypeService;
import PAI.service.programmeEditionEnrolment.IProgrammeEditionEnrolmentService;
import PAI.service.programmeEditionEnrolment.ProgrammeEditionEnrolmentServiceImpl;
import PAI.service.department.DepartmentServiceImpl;
import PAI.service.department.IDepartmentService;
import PAI.service.programme.IProgrammeService;
import PAI.service.programme.ProgrammeServiceImpl;
import PAI.service.schoolYear.ISchoolYearService;
import PAI.service.schoolYear.SchoolYearServiceImpl;
import PAI.service.teacher.ITeacherService;
import PAI.service.teacher.TeacherServiceImpl;
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

        IProgrammeEditionEnrolmentService pEEServiceDouble = mock(ProgrammeEditionEnrolmentServiceImpl.class);
        ISchoolYearService schoolYearServiceDouble = mock(SchoolYearServiceImpl.class);
        IDepartmentService departmentServiceDouble = mock(DepartmentServiceImpl.class);
        IProgrammeService programmeService = mock(ProgrammeServiceImpl.class);

        // Act & Assert
        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller =
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(pEEServiceDouble, schoolYearServiceDouble, departmentServiceDouble, programmeService);
    }

    //test when PProgrammeService is null
    @Test
    void shouldThrowExceptionWhenProgrammeServiceIsNull() {
        // arrange
        IProgrammeEditionEnrolmentService pEEServiceDouble = mock(ProgrammeEditionEnrolmentServiceImpl.class);
        ISchoolYearService schoolYearServiceDouble = mock(SchoolYearServiceImpl.class);
        IDepartmentService departmentServiceDouble = mock(DepartmentServiceImpl.class);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(pEEServiceDouble, schoolYearServiceDouble, departmentServiceDouble, null)
        );
        assertEquals("Services cannot be null.", exception.getMessage());
    }
    //test when ProgramEditionEnrollmentService is null

    @Test
    void shouldThrowExceptionWhenPEEServiceIsNull() {
        // arrange
        IProgrammeEditionEnrolmentService pEEServiceDouble = null;
        ISchoolYearService schoolYearServiceDouble = mock(SchoolYearServiceImpl.class);
        IDepartmentService departmentServiceDouble = mock(DepartmentServiceImpl.class);
        IProgrammeService programmeService = mock(ProgrammeServiceImpl.class);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(pEEServiceDouble, schoolYearServiceDouble, departmentServiceDouble, programmeService)
        );
        assertEquals("Services cannot be null.", exception.getMessage());
    }

    //test when SchoolYearService is null
    @Test
    void shouldThrowExceptionWhenSchoolYearServiceIsNull() {
        // arrange
        IProgrammeEditionEnrolmentService pEEServiceDouble = mock(ProgrammeEditionEnrolmentServiceImpl.class);
        ISchoolYearService schoolYearServiceDouble = null;
        IDepartmentService departmentServiceDouble = mock(DepartmentServiceImpl.class);
        IProgrammeService programmeService = mock(ProgrammeServiceImpl.class);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(pEEServiceDouble, schoolYearServiceDouble, departmentServiceDouble, programmeService)
        );
        assertEquals("Services cannot be null.", exception.getMessage());
    }

    //test when DepartmentService is null
    @Test
    void shouldThrowExceptionWhenDepartmentServiceIsNull() {
        // arrange
        IProgrammeEditionEnrolmentService pEEServiceDouble = mock(ProgrammeEditionEnrolmentServiceImpl.class);
        ISchoolYearService schoolYearServiceDouble = mock(SchoolYearServiceImpl.class);
        IDepartmentService departmentServiceDouble = null;
        IProgrammeService programmeService = mock(ProgrammeServiceImpl.class);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(pEEServiceDouble, schoolYearServiceDouble, departmentServiceDouble, programmeService)
        );
        assertEquals("Services cannot be null.", exception.getMessage());
    }

    /*//test that confirms that the method returns a positive int when there are students enrolled in Programmes from specified department and school year
    @Test
    void shouldReturnCorrectCountWhenStudentsAreEnrolledInDepartmentAndInSchoolYear() {
        // Arrange
        IProgrammeEditionEnrolmentService pEEServiceDouble = mock(ProgrammeEditionEnrolmentServiceImpl.class);
        ISchoolYearService schoolYearServiceDouble = mock(SchoolYearServiceImpl.class);
        IDepartmentService departmentServiceDouble = mock(DepartmentServiceImpl.class);
        IProgrammeService programmeService = mock(ProgrammeServiceImpl.class);
        DepartmentID departmentDouble = mock(DepartmentID.class);
        SchoolYearID schoolYearDouble = mock(SchoolYearID.class);

        List<ProgrammeID> programmeIDs = List.of(mock(ProgrammeID.class));

        when(schoolYearServiceDouble.schoolYearExistsById(schoolYearDouble)).thenReturn(true);
        when(departmentServiceDouble.departmentExists(departmentDouble)).thenReturn(true);
        when(programmeService.findProgrammeByDepartment(departmentDouble)).thenReturn(programmeIDs);

        when(pEEServiceDouble.countStudentsInProgrammesFromDepartmentInSchoolYear(schoolYearDouble, programmeIDs)).thenReturn(3);

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller =
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(pEEServiceDouble, schoolYearServiceDouble, departmentServiceDouble, programmeService);
        // Act
        int result = controller.countStudentsInProgrammesFromDepartmentInSchoolYear(departmentDouble, schoolYearDouble);

        // Assert
        assertEquals(3, result);
    }

    //test that confirms that the method throws an exception when DepartmentID is null
    @Test
    void shouldThrowExceptionWhenDepartmentIDIsNull() {
        // arrange
        SchoolYearID schoolYearDouble = mock(SchoolYearID.class);
        IProgrammeEditionEnrolmentService pEEServiceDouble = mock(ProgrammeEditionEnrolmentServiceImpl.class);
        ISchoolYearService schoolYearServiceDouble = mock(SchoolYearServiceImpl.class);
        IDepartmentService departmentServiceDouble = mock(DepartmentServiceImpl.class);
        IProgrammeService programmeService = mock(ProgrammeServiceImpl.class);

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller =
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(pEEServiceDouble, schoolYearServiceDouble, departmentServiceDouble, programmeService);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {
            controller.countStudentsInProgrammesFromDepartmentInSchoolYear(null, schoolYearDouble);
        });
        // Assert
        assertEquals("Department or SchoolYear cannot be null", exception.getMessage());
    }

    //test that confirms that the method throws an exception when School Year ID is null
    @Test
    void shouldThrowExceptionWhenSchoolYearIDIsNull() {
        // arrange
        DepartmentID departmentID = mock(DepartmentID.class);
        IProgrammeEditionEnrolmentService pEEServiceDouble = mock(ProgrammeEditionEnrolmentServiceImpl.class);
        ISchoolYearService schoolYearServiceDouble = mock(SchoolYearServiceImpl.class);
        IDepartmentService departmentServiceDouble = mock(DepartmentServiceImpl.class);
        IProgrammeService programmeService = mock(ProgrammeServiceImpl.class);

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller =
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(pEEServiceDouble, schoolYearServiceDouble, departmentServiceDouble, programmeService);

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
        IProgrammeEditionEnrolmentService pEEServiceDouble = mock(ProgrammeEditionEnrolmentServiceImpl.class);
        ISchoolYearService schoolYearServiceDouble = mock(SchoolYearServiceImpl.class);
        IDepartmentService departmentServiceDouble = mock(DepartmentServiceImpl.class);
        IProgrammeService programmeService = mock(ProgrammeServiceImpl.class);
        List<ProgrammeID> programmeIDs = List.of(mock(ProgrammeID.class));

        when(schoolYearServiceDouble.schoolYearExistsById(schoolYearDouble)).thenReturn(false);
        when(departmentServiceDouble.departmentExists(departmentDouble)).thenReturn(true);
        when(programmeService.findProgrammeByDepartment(departmentDouble)).thenReturn(programmeIDs);

        when(pEEServiceDouble.countStudentsInProgrammesFromDepartmentInSchoolYear(schoolYearDouble, programmeIDs)).thenReturn(3);

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller =
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(pEEServiceDouble, schoolYearServiceDouble, departmentServiceDouble, programmeService);

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
        IProgrammeEditionEnrolmentService pEEServiceDouble = mock(ProgrammeEditionEnrolmentServiceImpl.class);
        ISchoolYearService schoolYearServiceDouble = mock(SchoolYearServiceImpl.class);
        IDepartmentService departmentServiceDouble = mock(DepartmentServiceImpl.class);
        IProgrammeService programmeService = mock(ProgrammeServiceImpl.class);
        List<ProgrammeID> programmeIDs = List.of(mock(ProgrammeID.class));

        when(schoolYearServiceDouble.schoolYearExistsById(schoolYearDouble)).thenReturn(true);
        when(departmentServiceDouble.departmentExists(departmentDouble)).thenReturn(false);
        when(programmeService.findProgrammeByDepartment(departmentDouble)).thenReturn(programmeIDs);

        when(pEEServiceDouble.countStudentsInProgrammesFromDepartmentInSchoolYear(schoolYearDouble, programmeIDs)).thenReturn(3);

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller =
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(pEEServiceDouble, schoolYearServiceDouble, departmentServiceDouble, programmeService);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {
            controller.countStudentsInProgrammesFromDepartmentInSchoolYear(departmentDouble, schoolYearDouble);
        });
        // Assert
        assertEquals("Department does not exist.", exception.getMessage());
    }

    @Test
    void shouldReturnNotNullSchoolYearIfAllParametersAreValid() {
        IProgrammeEditionEnrolmentService pEEServiceDouble = mock(ProgrammeEditionEnrolmentServiceImpl.class);
        ISchoolYearService schoolYearServiceDouble = mock(SchoolYearServiceImpl.class);
        IDepartmentService departmentServiceDouble = mock(DepartmentServiceImpl.class);
        IProgrammeService programmeService = mock(ProgrammeServiceImpl.class);

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller =
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(pEEServiceDouble, schoolYearServiceDouble, departmentServiceDouble, programmeService);

        // Act
        List<SchoolYearID> doubleSchoolYears = controller.getAllSchoolYearsIDs();

        // Assert
        assertNotNull(doubleSchoolYears);
    }

    @Test
    void shouldReturnListOfSchoolYearIDsIfSchoolYearsAreInRepo() {
        IProgrammeEditionEnrolmentService pEEServiceDouble = mock(ProgrammeEditionEnrolmentServiceImpl.class);
        ISchoolYearService schoolYearServiceDouble = mock(SchoolYearServiceImpl.class);
        IDepartmentService departmentServiceDouble = mock(DepartmentServiceImpl.class);
        IProgrammeService programmeService = mock(ProgrammeServiceImpl.class);

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller =
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(pEEServiceDouble, schoolYearServiceDouble, departmentServiceDouble, programmeService);

        SchoolYearID doubleSchoolYear1 = mock(SchoolYearID.class);
        SchoolYearID doubleSchoolYear2 = mock(SchoolYearID.class);
        when(schoolYearServiceDouble.getAllSchoolYearsIDs()).thenReturn(List.of(doubleSchoolYear1, doubleSchoolYear2));
        // Act
        List<SchoolYearID> doubleSchoolYears = controller.getAllSchoolYearsIDs();

        // Assert
        assertTrue(doubleSchoolYears.size() == 2);
    }

    @Test
    void shouldReturnEmptyListOfSchoolYearIDsIfNoSchoolYearsAreInRepo() {
        IProgrammeEditionEnrolmentService pEEServiceDouble = mock(ProgrammeEditionEnrolmentServiceImpl.class);
        ISchoolYearService schoolYearServiceDouble = mock(SchoolYearServiceImpl.class);
        IDepartmentService departmentServiceDouble = mock(DepartmentServiceImpl.class);
        IProgrammeService programmeService = mock(ProgrammeServiceImpl.class);

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller =
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(pEEServiceDouble, schoolYearServiceDouble, departmentServiceDouble, programmeService);

        // Act
        List<SchoolYearID> doubleSchoolYears = controller.getAllSchoolYearsIDs();

        // Assert
        assertTrue(doubleSchoolYears.isEmpty());
    }

    @Test
    void shouldReturnListOfDepartmentIDsIfDepartmentsAreInRepo() {
        IProgrammeEditionEnrolmentService pEEServiceDouble = mock(ProgrammeEditionEnrolmentServiceImpl.class);
        ISchoolYearService schoolYearServiceDouble = mock(SchoolYearServiceImpl.class);
        IDepartmentService departmentServiceDouble = mock(DepartmentServiceImpl.class);
        IProgrammeService programmeService = mock(ProgrammeServiceImpl.class);

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller =
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(pEEServiceDouble, schoolYearServiceDouble, departmentServiceDouble, programmeService);

        // Act
        Set<DepartmentID> doubleDepartment = controller.getAllDepartmentID();

        // Assert
        assertNotNull(doubleDepartment);
    }

    @Test
    void shouldReturnEmptyListOfDepartmentIDsIfNoDepartmentsAreInRepo() {
        IProgrammeEditionEnrolmentService pEEServiceDouble = mock(ProgrammeEditionEnrolmentServiceImpl.class);
        ISchoolYearService schoolYearServiceDouble = mock(SchoolYearServiceImpl.class);
        IDepartmentService departmentServiceDouble = mock(DepartmentServiceImpl.class);
        IProgrammeService programmeService = mock(ProgrammeServiceImpl.class);

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller =
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(pEEServiceDouble, schoolYearServiceDouble, departmentServiceDouble, programmeService);

        // Act
        Set<DepartmentID> doubleDepartments = controller.getAllDepartmentID();

        // Assert
        assertTrue(doubleDepartments.isEmpty());
    }

    @Test
    void shouldReturnListOf2DepartmentIDsIf2DepartmentsAreInRepo() {
        IProgrammeEditionEnrolmentService pEEServiceDouble = mock(ProgrammeEditionEnrolmentServiceImpl.class);
        ISchoolYearService schoolYearServiceDouble = mock(SchoolYearServiceImpl.class);
        IDepartmentService departmentServiceDouble = mock(DepartmentServiceImpl.class);
        IProgrammeService programmeService = mock(ProgrammeServiceImpl.class);
        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller =
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(pEEServiceDouble, schoolYearServiceDouble, departmentServiceDouble, programmeService);

        DepartmentID dp1 = mock(DepartmentID.class);
        DepartmentID dp2 = mock(DepartmentID.class);
        when(departmentServiceDouble.getDepartmentIDs()).thenReturn(Set.of(dp1, dp2));

        // Act
        Set<DepartmentID> doubleDepartments = controller.getAllDepartmentID();

        // Assert
        assertTrue(doubleDepartments.size() == 2);
    }


    //Integration Tests

    //test that confirms that the method returns a positive int when there are students enrolled in Programmes from specified department and school year
    @Test
    void shouldReturnCorrectCountWhenStudentsAreEnrolledInDepartmentAndSchoolYear() throws Exception {
        // arrange
        Department department = new Department(new DepartmentAcronym("CSE"), new Name("Computer Science Engineer"), new TeacherID(new TeacherAcronym("PPP")));

        TeacherAcronym teacherAcronym = new TeacherAcronym("AAA");
        TeacherID teacherID = new TeacherID(teacherAcronym);
        DepartmentID departmentID = department.identity();

        Description description1 = new Description("School Year 25/26");
        Description description2 = new Description("School Year 22/23");
        Date startDate1 = new Date("01-09-2025");
        Date endDate1 = new Date("31-07-2026");
        Date startDate2 = new Date("01-09-2022");
        Date endDate2 = new Date("31-07-2026");
        SchoolYear schoolYear1 = new SchoolYear(description1, startDate1, endDate1);
        SchoolYear schoolYear2 = new SchoolYear(description2, startDate2, endDate2);

        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        Programme programme1 = programmeFactory.registerProgramme(new NameWithNumbersAndSpecialChars("PP"),
                new Acronym("APP"), new MaxEcts(30), new QuantSemesters(30), new DegreeTypeID(),
                departmentID, teacherID);
        Programme programme2 = programmeFactory.registerProgramme(new NameWithNumbersAndSpecialChars("PP2"),
                new Acronym("APP2"), new MaxEcts(30), new QuantSemesters(30), new DegreeTypeID(),
                departmentID, teacherID);

        ProgrammeEdition edition1 = new ProgrammeEdition(new ProgrammeEditionID(programme1.identity(), schoolYear1.identity()), programme1.identity(), schoolYear1.identity(), new ProgrammeEditionGeneratedID());
        ProgrammeEdition edition2 = new ProgrammeEdition(new ProgrammeEditionID(programme2.identity(), schoolYear1.identity()), programme2.identity(), schoolYear1.identity(), new ProgrammeEditionGeneratedID());
        ProgrammeEdition edition3 = new ProgrammeEdition(new ProgrammeEditionID(programme2.identity(), schoolYear2.identity()), programme2.identity(), schoolYear2.identity(), new ProgrammeEditionGeneratedID());

        Address address1 = new Address(new Street("Street1"), new PostalCode("4444-441"), new Location("Porto"), new Country("PT"));
        Address address2 = new Address(new Street("Street2"), new PostalCode("4444-442"), new Location("Braga"), new Country("PT"));
        Address address3 = new Address(new Street("Street3"), new PostalCode("4444-443"), new Location("Gaia"), new Country("PT"));

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


        StudentAcademicEmail academicEmail1 = new StudentAcademicEmail(studentID1.getUniqueNumber());
        StudentAcademicEmail academicEmail2 = new StudentAcademicEmail(studentID2.getUniqueNumber());
        StudentAcademicEmail academicEmail3 = new StudentAcademicEmail(studentID3.getUniqueNumber());

        Student student1 = new Student(studentID1, name1, nif1, phone1, email1, address1, academicEmail1);
        Student student2 = new Student(studentID2, name2, nif2, phone2, email2, address2, academicEmail2);
        Student student3 = new Student(studentID3, name3, nif3, phone3, email3, address3, academicEmail3);

        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepositoryImpl programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactory);
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student1.identity(), edition1.identity(), programmeEditionEnrolmentGeneratedID));
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student1.identity(), edition1.identity(), programmeEditionEnrolmentGeneratedID));
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student2.identity(), edition2.identity(), programmeEditionEnrolmentGeneratedID));
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student3.identity(), edition3.identity(), programmeEditionEnrolmentGeneratedID));

        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepositoryImpl schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearListFactoryImpl);
        schoolYearRepository.save(schoolYear1);
        schoolYearRepository.save(schoolYear2);

        CourseEditionEnrolmentListFactoryImpl courseEditionEnrolmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrolmentListFactory);

        ICourseEditionFactory courseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory courseEditionListFactory = new CourseEditionListFactoryImpl();

        CourseEditionRepositoryImpl courseEditionRepository = new CourseEditionRepositoryImpl(courseEditionFactory, courseEditionListFactory);

        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeRepositoryListFactory programmeLisListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);
        programmeEditionRepository.save(edition1);
        programmeEditionRepository.save(edition2);
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeLisListFactory);
        programmeRepository.save(programme1);
        programmeRepository.save(programme2);
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentList);

        DepartmentFactoryImpl departmentFactoryImpl = new DepartmentFactoryImpl();
        DepartmentListFactoryImpl departmentListFactoryImpl = new DepartmentListFactoryImpl();

        DepartmentRepositoryImpl departmentRepository = new DepartmentRepositoryImpl(departmentListFactoryImpl);
        departmentRepository.save(department);


        IProgrammeEditionEnrolmentFactory programmeEditionEnrolmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();

        ProgrammeEditionEnrolmentServiceImpl programmeEditionEnrolmentService =
                new ProgrammeEditionEnrolmentServiceImpl(programmeEditionEnrolmentRepository,
                        programmeEditionRepository,
                        courseEditionEnrolmentRepository,
                        courseEditionRepository,
                        schoolYearRepository,
                        programmeEnrolmentRepository,
                        programmeRepository,
                        programmeEditionEnrolmentFactory);

        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactory);
        SchoolYearServiceImpl schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactoryImpl,schoolYearMapperDTO);
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(departmentFactoryImpl,departmentRepository);
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();

        IDegreeTypeListFactory degreeTypeListFactory = new DegreeTypeListFactoryImpl();
        IDegreeTypeFactory degreeTypeFactory = new DegreeTypeFactoryImpl();
        IDegreeTypeRepository degreeTypeRepository = new DegreeTypeRepositoryImpl(degreeTypeListFactory);
        IDegreeTypeRegistrationService degreeTypeRegistrationService = new DegreeTypeRegistrationServiceImpl(degreeTypeFactory, degreeTypeRepository);

        ITeacherFactory teacherFactory = mock(TeacherFactoryImpl.class);
        ITeacherRepository teacherRepository = mock(TeacherRepositorySpringDataImpl.class);
        ITeacherService teacherService = new TeacherServiceImpl(teacherFactory, teacherRepository);

        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository, programmeAssembler, degreeTypeRegistrationService, departmentService, teacherService);


        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller = new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(programmeEditionEnrolmentService, schoolYearService, departmentService, programmeService);
        // act
        int result = controller.countStudentsInProgrammesFromDepartmentInSchoolYear(department.identity(), schoolYear1.identity());

        // assert
        assertEquals(2, result);
    }

    //test that confirms that the method throws an exception when School Year Service is null
    @Test
    void shouldThrowExceptionWhenSchoolYearServiceIsNull_Integration() throws Exception {
        // arrange
        Department department = new Department(new DepartmentAcronym("CSE"), new Name("Computer Science Engineer"), new TeacherID(new TeacherAcronym("PPP")));

        TeacherAcronym teacherAcronym = new TeacherAcronym("AAA");
        TeacherID teacherID = new TeacherID(teacherAcronym);
        DepartmentID departmentID = department.identity();

        Description description1 = new Description("School Year 25/26");
        Description description2 = new Description("School Year 22/23");
        Date startDate1 = new Date("01-09-2025");
        Date endDate1 = new Date("31-07-2026");
        Date startDate2 = new Date("01-09-2022");
        Date endDate2 = new Date("31-07-2026");
        SchoolYear schoolYear1 = new SchoolYear(description1, startDate1, endDate1);
        SchoolYear schoolYear2 = new SchoolYear(description2, startDate2, endDate2);

        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        Programme programme1 = programmeFactory.registerProgramme(new NameWithNumbersAndSpecialChars("PP"),
                new Acronym("APP"), new MaxEcts(30), new QuantSemesters(30), new DegreeTypeID(),
                departmentID, teacherID);
        Programme programme2 = programmeFactory.registerProgramme(new NameWithNumbersAndSpecialChars("PP2"),
                new Acronym("APP2"), new MaxEcts(30), new QuantSemesters(30), new DegreeTypeID(),
                departmentID, teacherID);

        ProgrammeEdition edition1 = new ProgrammeEdition(new ProgrammeEditionID(programme1.identity(), schoolYear1.identity()), programme1.identity(), schoolYear1.identity(), new ProgrammeEditionGeneratedID());
        ProgrammeEdition edition2 = new ProgrammeEdition(new ProgrammeEditionID(programme2.identity(), schoolYear1.identity()), programme2.identity(), schoolYear1.identity(), new ProgrammeEditionGeneratedID());
        ProgrammeEdition edition3 = new ProgrammeEdition(new ProgrammeEditionID(programme2.identity(), schoolYear2.identity()), programme2.identity(), schoolYear2.identity(), new ProgrammeEditionGeneratedID());

        Address address1 = new Address(new Street("Street1"), new PostalCode("4444-441"), new Location("Porto"), new Country("PT"));
        Address address2 = new Address(new Street("Street2"), new PostalCode("4444-442"), new Location("Braga"), new Country("PT"));
        Address address3 = new Address(new Street("Street3"), new PostalCode("4444-443"), new Location("Gaia"), new Country("PT"));

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


        StudentAcademicEmail academicEmail1 = new StudentAcademicEmail(studentID1.getUniqueNumber());
        StudentAcademicEmail academicEmail2 = new StudentAcademicEmail(studentID2.getUniqueNumber());
        StudentAcademicEmail academicEmail3 = new StudentAcademicEmail(studentID3.getUniqueNumber());

        Student student1 = new Student(studentID1, name1, nif1, phone1, email1, address1, academicEmail1);
        Student student2 = new Student(studentID2, name2, nif2, phone2, email2, address2, academicEmail2);
        Student student3 = new Student(studentID3, name3, nif3, phone3, email3, address3, academicEmail3);

        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepositoryImpl programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactory);
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student1.identity(), edition1.identity(), programmeEditionEnrolmentGeneratedID));
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student1.identity(), edition1.identity(), programmeEditionEnrolmentGeneratedID));
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student2.identity(), edition2.identity(), programmeEditionEnrolmentGeneratedID));
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student3.identity(), edition3.identity(), programmeEditionEnrolmentGeneratedID));

        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepositoryImpl schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearListFactoryImpl);
        schoolYearRepository.save(schoolYear1);
        schoolYearRepository.save(schoolYear2);

        CourseEditionEnrolmentListFactoryImpl courseEditionEnrolmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrolmentListFactory);

        ICourseEditionFactory courseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory courseEditionListFactory = new CourseEditionListFactoryImpl();

        CourseEditionRepositoryImpl courseEditionRepository = new CourseEditionRepositoryImpl(courseEditionFactory, courseEditionListFactory);

        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeRepositoryListFactory programmeLisListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);
        programmeEditionRepository.save(edition1);
        programmeEditionRepository.save(edition2);
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeLisListFactory);
        programmeRepository.save(programme1);
        programmeRepository.save(programme2);

        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();

        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentList);

        DepartmentFactoryImpl departmentFactoryImpl = new DepartmentFactoryImpl();
        DepartmentListFactoryImpl departmentListFactoryImpl = new DepartmentListFactoryImpl();

        IDepartmentRepository departmentRepository = new DepartmentRepositoryImpl(departmentListFactoryImpl);
        departmentRepository.save(department);


        IProgrammeEditionEnrolmentFactory programmeEditionEnrolmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();

        ProgrammeEditionEnrolmentServiceImpl programmeEditionEnrolmentService =
                new ProgrammeEditionEnrolmentServiceImpl(programmeEditionEnrolmentRepository,
                        programmeEditionRepository,
                        courseEditionEnrolmentRepository,
                        courseEditionRepository,
                        schoolYearRepository,
                        programmeEnrolmentRepository,
                        programmeRepository,
                        programmeEditionEnrolmentFactory);

        SchoolYearServiceImpl schoolYearService = null;
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(departmentFactoryImpl,departmentRepository);
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();

        IDegreeTypeListFactory degreeTypeListFactory = new DegreeTypeListFactoryImpl();
        IDegreeTypeFactory degreeTypeFactory = new DegreeTypeFactoryImpl();
        IDegreeTypeRepository degreeTypeRepository = new DegreeTypeRepositoryImpl(degreeTypeListFactory);
        IDegreeTypeRegistrationService degreeTypeRegistrationService = new DegreeTypeRegistrationServiceImpl(degreeTypeFactory, degreeTypeRepository);

        ITeacherFactory teacherFactory = mock(TeacherFactoryImpl.class);
        ITeacherRepository teacherRepository = mock(TeacherRepositorySpringDataImpl.class);
        ITeacherService teacherService = new TeacherServiceImpl(teacherFactory, teacherRepository);

        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository, programmeAssembler, degreeTypeRegistrationService, departmentService, teacherService);

        // act
        Exception exception = assertThrows(Exception.class, () -> {
            US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller = new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(programmeEditionEnrolmentService, schoolYearService, departmentService, programmeService);
            controller.countStudentsInProgrammesFromDepartmentInSchoolYear(department.identity(), schoolYear1.identity());
        });
        // Assert
        assertEquals("Services cannot be null.", exception.getMessage());
    }

    //test that confirms that the method throws an exception when DepartmentService is null
    @Test
    void shouldThrowExceptionWhenDepartmentServiceDoesNotExist_Integration() throws Exception {
        // arrange
        Department department = new Department(new DepartmentAcronym("CSE"), new Name("Computer Science Engineer"), new TeacherID(new TeacherAcronym("PPP")));

        TeacherAcronym teacherAcronym = new TeacherAcronym("AAA");
        TeacherID teacherID = new TeacherID(teacherAcronym);
        DepartmentID departmentID = department.identity();

        Description description1 = new Description("School Year 25/26");
        Description description2 = new Description("School Year 22/23");
        Date startDate1 = new Date("01-09-2025");
        Date endDate1 = new Date("31-07-2026");
        Date startDate2 = new Date("01-09-2022");
        Date endDate2 = new Date("31-07-2026");
        SchoolYear schoolYear1 = new SchoolYear(description1, startDate1, endDate1);
        SchoolYear schoolYear2 = new SchoolYear(description2, startDate2, endDate2);

        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        Programme programme1 = programmeFactory.registerProgramme(new NameWithNumbersAndSpecialChars("PP"),
                new Acronym("APP"), new MaxEcts(30), new QuantSemesters(30), new DegreeTypeID(),
                departmentID, teacherID);
        Programme programme2 = programmeFactory.registerProgramme(new NameWithNumbersAndSpecialChars("PP2"),
                new Acronym("APP2"), new MaxEcts(30), new QuantSemesters(30), new DegreeTypeID(),
                departmentID, teacherID);

        ProgrammeEdition edition1 = new ProgrammeEdition(new ProgrammeEditionID(programme1.identity(), schoolYear1.identity()), programme1.identity(), schoolYear1.identity(), new ProgrammeEditionGeneratedID());
        ProgrammeEdition edition2 = new ProgrammeEdition(new ProgrammeEditionID(programme2.identity(), schoolYear1.identity()), programme2.identity(), schoolYear1.identity(), new ProgrammeEditionGeneratedID());
        ProgrammeEdition edition3 = new ProgrammeEdition(new ProgrammeEditionID(programme2.identity(), schoolYear2.identity()), programme2.identity(), schoolYear2.identity(), new ProgrammeEditionGeneratedID());

        Address address1 = new Address(new Street("Street1"), new PostalCode("4444-441"), new Location("Porto"), new Country("PT"));
        Address address2 = new Address(new Street("Street2"), new PostalCode("4444-442"), new Location("Braga"), new Country("PT"));
        Address address3 = new Address(new Street("Street3"), new PostalCode("4444-443"), new Location("Gaia"), new Country("PT"));

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


        StudentAcademicEmail academicEmail1 = new StudentAcademicEmail(studentID1.getUniqueNumber());
        StudentAcademicEmail academicEmail2 = new StudentAcademicEmail(studentID2.getUniqueNumber());
        StudentAcademicEmail academicEmail3 = new StudentAcademicEmail(studentID3.getUniqueNumber());

        Student student1 = new Student(studentID1, name1, nif1, phone1, email1, address1, academicEmail1);
        Student student2 = new Student(studentID2, name2, nif2, phone2, email2, address2, academicEmail2);
        Student student3 = new Student(studentID3, name3, nif3, phone3, email3, address3, academicEmail3);

        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepositoryImpl programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactory);
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student1.identity(), edition1.identity(), programmeEditionEnrolmentGeneratedID));
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student1.identity(), edition1.identity(), programmeEditionEnrolmentGeneratedID));
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student2.identity(), edition2.identity(), programmeEditionEnrolmentGeneratedID));
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student3.identity(), edition3.identity(), programmeEditionEnrolmentGeneratedID));

        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepositoryImpl schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearListFactoryImpl);
        schoolYearRepository.save(schoolYear1);
        schoolYearRepository.save(schoolYear2);

        CourseEditionEnrolmentListFactoryImpl courseEditionEnrolmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrolmentListFactory);

        ICourseEditionFactory courseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory courseEditionListFactory = new CourseEditionListFactoryImpl();

        CourseEditionRepositoryImpl courseEditionRepository = new CourseEditionRepositoryImpl(courseEditionFactory, courseEditionListFactory);

        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeRepositoryListFactory programmeLisListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);
        programmeEditionRepository.save(edition1);
        programmeEditionRepository.save(edition2);
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeLisListFactory);
        programmeRepository.save(programme1);
        programmeRepository.save(programme2);
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentList);

        DepartmentListFactoryImpl departmentListFactoryImpl = new DepartmentListFactoryImpl();

        DepartmentRepositoryImpl departmentRepository = new DepartmentRepositoryImpl(departmentListFactoryImpl);
        departmentRepository.save(department);


        IProgrammeEditionEnrolmentFactory programmeEditionEnrolmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();

        ProgrammeEditionEnrolmentServiceImpl programmeEditionEnrolmentService =
                new ProgrammeEditionEnrolmentServiceImpl(programmeEditionEnrolmentRepository,
                        programmeEditionRepository,
                        courseEditionEnrolmentRepository,
                        courseEditionRepository,
                        schoolYearRepository,
                        programmeEnrolmentRepository,
                        programmeRepository,
                        programmeEditionEnrolmentFactory);

        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactory);
        SchoolYearServiceImpl schoolYearService = new SchoolYearServiceImpl(schoolYearRepository,schoolYearFactoryImpl, schoolYearMapperDTO);
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();

        IDegreeTypeListFactory degreeTypeListFactory = new DegreeTypeListFactoryImpl();
        IDegreeTypeFactory degreeTypeFactory = new DegreeTypeFactoryImpl();
        IDegreeTypeRepository degreeTypeRepository = new DegreeTypeRepositoryImpl(degreeTypeListFactory);
        IDegreeTypeRegistrationService degreeTypeRegistrationService = new DegreeTypeRegistrationServiceImpl(degreeTypeFactory, degreeTypeRepository);

        IDepartmentService departmentService = mock(IDepartmentService.class);
        ITeacherService teacherService = mock(TeacherServiceImpl.class);

        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository, programmeAssembler, degreeTypeRegistrationService, departmentService, teacherService);

        // act
        Exception exception = assertThrows(Exception.class, () -> {
            US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller = new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(programmeEditionEnrolmentService, schoolYearService, null, programmeService);
            controller.countStudentsInProgrammesFromDepartmentInSchoolYear(department.identity(), schoolYear1.identity());
        });
        // Assert
        assertEquals("Services cannot be null.", exception.getMessage());
    }

    //test that confirms that the method throws an exception when ProgrammeEditionEnrollmentService is null
    @Test
    void shouldThrowExceptionWhenProgrammeEditionEnrollmentServiceIsNull() throws Exception {
        // arrange
        Department department = new Department(new DepartmentAcronym("CSE"), new Name("Computer Science Engineer"), new TeacherID(new TeacherAcronym("PPP")));

        TeacherAcronym teacherAcronym = new TeacherAcronym("AAA");
        TeacherID teacherID = new TeacherID(teacherAcronym);
        DepartmentID departmentID = department.identity();

        Description description1 = new Description("School Year 25/26");
        Description description2 = new Description("School Year 22/23");
        Date startDate1 = new Date("01-09-2025");
        Date endDate1 = new Date("31-07-2026");
        Date startDate2 = new Date("01-09-2022");
        Date endDate2 = new Date("31-07-2026");
        SchoolYear schoolYear1 = new SchoolYear(description1, startDate1, endDate1);
        SchoolYear schoolYear2 = new SchoolYear(description2, startDate2, endDate2);

        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        Programme programme1 = programmeFactory.registerProgramme(new NameWithNumbersAndSpecialChars("PP"),
                new Acronym("APP"), new MaxEcts(30), new QuantSemesters(30), new DegreeTypeID(),
                departmentID, teacherID);
        Programme programme2 = programmeFactory.registerProgramme(new NameWithNumbersAndSpecialChars("PP2"),
                new Acronym("APP2"), new MaxEcts(30), new QuantSemesters(30), new DegreeTypeID(),
                departmentID, teacherID);

        ProgrammeEdition edition1 = new ProgrammeEdition(new ProgrammeEditionID(programme1.identity(), schoolYear1.identity()), programme1.identity(), schoolYear1.identity(), new ProgrammeEditionGeneratedID());
        ProgrammeEdition edition2 = new ProgrammeEdition(new ProgrammeEditionID(programme2.identity(), schoolYear1.identity()), programme2.identity(), schoolYear1.identity(), new ProgrammeEditionGeneratedID());
        ProgrammeEdition edition3 = new ProgrammeEdition(new ProgrammeEditionID(programme2.identity(), schoolYear2.identity()), programme2.identity(), schoolYear2.identity(), new ProgrammeEditionGeneratedID());

        Address address1 = new Address(new Street("Street1"), new PostalCode("4444-441"), new Location("Porto"), new Country("PT"));
        Address address2 = new Address(new Street("Street2"), new PostalCode("4444-442"), new Location("Braga"), new Country("PT"));
        Address address3 = new Address(new Street("Street3"), new PostalCode("4444-443"), new Location("Gaia"), new Country("PT"));

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


        StudentAcademicEmail academicEmail1 = new StudentAcademicEmail(studentID1.getUniqueNumber());
        StudentAcademicEmail academicEmail2 = new StudentAcademicEmail(studentID2.getUniqueNumber());
        StudentAcademicEmail academicEmail3 = new StudentAcademicEmail(studentID3.getUniqueNumber());

        Student student1 = new Student(studentID1, name1, nif1, phone1, email1, address1, academicEmail1);
        Student student2 = new Student(studentID2, name2, nif2, phone2, email2, address2, academicEmail2);
        Student student3 = new Student(studentID3, name3, nif3, phone3, email3, address3, academicEmail3);

        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepositoryImpl programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactory);
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student1.identity(), edition1.identity(), programmeEditionEnrolmentGeneratedID));
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student1.identity(), edition1.identity(), programmeEditionEnrolmentGeneratedID));
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student2.identity(), edition2.identity(), programmeEditionEnrolmentGeneratedID));
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student3.identity(), edition3.identity(), programmeEditionEnrolmentGeneratedID));

        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepositoryImpl schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearListFactoryImpl);
        schoolYearRepository.save(schoolYear1);
        schoolYearRepository.save(schoolYear2);

        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeRepositoryListFactory programmeLisListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);
        programmeEditionRepository.save(edition1);
        programmeEditionRepository.save(edition2);
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeLisListFactory);
        programmeRepository.save(programme1);
        programmeRepository.save(programme2);

        DepartmentFactoryImpl departmentFactoryImpl = new DepartmentFactoryImpl();
        DepartmentListFactoryImpl departmentListFactoryImpl = new DepartmentListFactoryImpl();

        DepartmentRepositoryImpl departmentRepository = new DepartmentRepositoryImpl(departmentListFactoryImpl);
        departmentRepository.save(department);

        ProgrammeEditionEnrolmentServiceImpl programmeEditionEnrolmentService =null;

        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactory);
        SchoolYearServiceImpl schoolYearService = new SchoolYearServiceImpl(schoolYearRepository,schoolYearFactoryImpl,schoolYearMapperDTO);
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(departmentFactoryImpl,departmentRepository);
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();

        IDegreeTypeListFactory degreeTypeListFactory = new DegreeTypeListFactoryImpl();
        IDegreeTypeFactory degreeTypeFactory = new DegreeTypeFactoryImpl();
        IDegreeTypeRepository degreeTypeRepository = new DegreeTypeRepositoryImpl(degreeTypeListFactory);
        IDegreeTypeRegistrationService degreeTypeRegistrationService = new DegreeTypeRegistrationServiceImpl(degreeTypeFactory, degreeTypeRepository);

        ITeacherFactory teacherFactory = mock(TeacherFactoryImpl.class);
        ITeacherRepository teacherRepository = mock(TeacherRepositorySpringDataImpl.class);
        ITeacherService teacherService = new TeacherServiceImpl(teacherFactory, teacherRepository);

        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository, programmeAssembler, degreeTypeRegistrationService, departmentService, teacherService);

        // act
        Exception exception = assertThrows(Exception.class, () -> {
            US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller = new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(programmeEditionEnrolmentService, schoolYearService, departmentService, programmeService);
            controller.countStudentsInProgrammesFromDepartmentInSchoolYear(department.identity(), schoolYear1.identity());
        });
        // Assert
        assertEquals("Services cannot be null.", exception.getMessage());
    }

    //test that confirms that the method throws an exception when ProgrammeService is null
    @Test
    void shouldThrowExceptionWhenProgrammeServiceIsNull_Integration() throws Exception {
        // arrange
        Department department = new Department(new DepartmentAcronym("CSE"), new Name("Computer Science Engineer"), new TeacherID(new TeacherAcronym("PPP")));

        TeacherAcronym teacherAcronym = new TeacherAcronym("AAA");
        TeacherID teacherID = new TeacherID(teacherAcronym);
        DepartmentID departmentID = department.identity();

        Description description1 = new Description("School Year 25/26");
        Description description2 = new Description("School Year 22/23");
        Date startDate1 = new Date("01-09-2025");
        Date endDate1 = new Date("31-07-2026");
        Date startDate2 = new Date("01-09-2022");
        Date endDate2 = new Date("31-07-2026");
        SchoolYear schoolYear1 = new SchoolYear(description1, startDate1, endDate1);
        SchoolYear schoolYear2 = new SchoolYear(description2, startDate2, endDate2);

        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        Programme programme1 = programmeFactory.registerProgramme(new NameWithNumbersAndSpecialChars("PP"),
                new Acronym("APP"), new MaxEcts(30), new QuantSemesters(30), new DegreeTypeID(),
                departmentID, teacherID);
        Programme programme2 = programmeFactory.registerProgramme(new NameWithNumbersAndSpecialChars("PP2"),
                new Acronym("APP2"), new MaxEcts(30), new QuantSemesters(30), new DegreeTypeID(),
                departmentID, teacherID);

        ProgrammeEdition edition1 = new ProgrammeEdition(new ProgrammeEditionID(programme1.identity(), schoolYear1.identity()), programme1.identity(), schoolYear1.identity(), new ProgrammeEditionGeneratedID());
        ProgrammeEdition edition2 = new ProgrammeEdition(new ProgrammeEditionID(programme2.identity(), schoolYear1.identity()), programme2.identity(), schoolYear1.identity(), new ProgrammeEditionGeneratedID());
        ProgrammeEdition edition3 = new ProgrammeEdition(new ProgrammeEditionID(programme2.identity(), schoolYear2.identity()), programme2.identity(), schoolYear2.identity(), new ProgrammeEditionGeneratedID());

        Address address1 = new Address(new Street("Street1"), new PostalCode("4444-441"), new Location("Porto"), new Country("PT"));
        Address address2 = new Address(new Street("Street2"), new PostalCode("4444-442"), new Location("Braga"), new Country("PT"));
        Address address3 = new Address(new Street("Street3"), new PostalCode("4444-443"), new Location("Gaia"), new Country("PT"));

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


        StudentAcademicEmail academicEmail1 = new StudentAcademicEmail(studentID1.getUniqueNumber());
        StudentAcademicEmail academicEmail2 = new StudentAcademicEmail(studentID2.getUniqueNumber());
        StudentAcademicEmail academicEmail3 = new StudentAcademicEmail(studentID3.getUniqueNumber());

        Student student1 = new Student(studentID1, name1, nif1, phone1, email1, address1, academicEmail1);
        Student student2 = new Student(studentID2, name2, nif2, phone2, email2, address2, academicEmail2);
        Student student3 = new Student(studentID3, name3, nif3, phone3, email3, address3, academicEmail3);

        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepositoryImpl programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactory);
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student1.identity(), edition1.identity(), programmeEditionEnrolmentGeneratedID));
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student1.identity(), edition1.identity(), programmeEditionEnrolmentGeneratedID));
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student2.identity(), edition2.identity(), programmeEditionEnrolmentGeneratedID));
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student3.identity(), edition3.identity(), programmeEditionEnrolmentGeneratedID));

        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepositoryImpl schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearListFactoryImpl);
        schoolYearRepository.save(schoolYear1);
        schoolYearRepository.save(schoolYear2);

        CourseEditionEnrolmentListFactoryImpl courseEditionEnrolmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrolmentListFactory);

        ICourseEditionFactory courseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory courseEditionListFactory = new CourseEditionListFactoryImpl();

        CourseEditionRepositoryImpl courseEditionRepository = new CourseEditionRepositoryImpl(courseEditionFactory, courseEditionListFactory);

        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeRepositoryListFactory programmeLisListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);
        programmeEditionRepository.save(edition1);
        programmeEditionRepository.save(edition2);
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeLisListFactory);
        programmeRepository.save(programme1);
        programmeRepository.save(programme2);
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentList);

        DepartmentFactoryImpl departmentFactoryImpl = new DepartmentFactoryImpl();
        DepartmentListFactoryImpl departmentListFactoryImpl = new DepartmentListFactoryImpl();

        DepartmentRepositoryImpl departmentRepository = new DepartmentRepositoryImpl(departmentListFactoryImpl);
        departmentRepository.save(department);


        IProgrammeEditionEnrolmentFactory programmeEditionEnrolmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();

        ProgrammeEditionEnrolmentServiceImpl programmeEditionEnrolmentService =
                new ProgrammeEditionEnrolmentServiceImpl(programmeEditionEnrolmentRepository,
                        programmeEditionRepository,
                        courseEditionEnrolmentRepository,
                        courseEditionRepository,
                        schoolYearRepository,
                        programmeEnrolmentRepository,
                        programmeRepository,
                        programmeEditionEnrolmentFactory);

        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactory);
        SchoolYearServiceImpl schoolYearService = new SchoolYearServiceImpl(schoolYearRepository,schoolYearFactoryImpl,schoolYearMapperDTO);
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(departmentFactoryImpl,departmentRepository);
        ProgrammeServiceImpl programmeService = null;

        // act
        Exception exception = assertThrows(Exception.class, () -> {
            US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller = new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(programmeEditionEnrolmentService, schoolYearService, departmentService, programmeService);
            controller.countStudentsInProgrammesFromDepartmentInSchoolYear(department.identity(), schoolYear1.identity());
        });
        // Assert
        assertEquals("Services cannot be null.", exception.getMessage());
    }

//Test that confirms that an exception is thrown when departmentID is null
    @Test
    void shouldThrowExceptionWhenDepartmentIDIsNull_Integration() throws Exception {
        // arrange
        Department department = new Department(new DepartmentAcronym("CSE"), new Name("Computer Science Engineer"), new TeacherID(new TeacherAcronym("PPP")));

        TeacherAcronym teacherAcronym = new TeacherAcronym("AAA");
        TeacherID teacherID = new TeacherID(teacherAcronym);
        DepartmentID departmentID = department.identity();

        Description description1 = new Description("School Year 25/26");
        Description description2 = new Description("School Year 22/23");
        Date startDate1 = new Date("01-09-2025");
        Date endDate1 = new Date("31-07-2026");
        Date startDate2 = new Date("01-09-2022");
        Date endDate2 = new Date("31-07-2026");
        SchoolYear schoolYear1 = new SchoolYear(description1, startDate1, endDate1);
        SchoolYear schoolYear2 = new SchoolYear(description2, startDate2, endDate2);

        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        Programme programme1 = programmeFactory.registerProgramme(new NameWithNumbersAndSpecialChars("PP"),
                new Acronym("APP"), new MaxEcts(30), new QuantSemesters(30), new DegreeTypeID(),
                departmentID, teacherID);
        Programme programme2 = programmeFactory.registerProgramme(new NameWithNumbersAndSpecialChars("PP2"),
                new Acronym("APP2"), new MaxEcts(30), new QuantSemesters(30), new DegreeTypeID(),
                departmentID, teacherID);

        ProgrammeEdition edition1 = new ProgrammeEdition(new ProgrammeEditionID(programme1.identity(), schoolYear1.identity()), programme1.identity(), schoolYear1.identity(), new ProgrammeEditionGeneratedID());
        ProgrammeEdition edition2 = new ProgrammeEdition(new ProgrammeEditionID(programme2.identity(), schoolYear1.identity()), programme2.identity(), schoolYear1.identity(), new ProgrammeEditionGeneratedID());
        ProgrammeEdition edition3 = new ProgrammeEdition(new ProgrammeEditionID(programme2.identity(), schoolYear2.identity()), programme2.identity(), schoolYear2.identity(), new ProgrammeEditionGeneratedID());

        Address address1 = new Address(new Street("Street1"), new PostalCode("4444-441"), new Location("Porto"), new Country("PT"));
        Address address2 = new Address(new Street("Street2"), new PostalCode("4444-442"), new Location("Braga"), new Country("PT"));
        Address address3 = new Address(new Street("Street3"), new PostalCode("4444-443"), new Location("Gaia"), new Country("PT"));

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


        StudentAcademicEmail academicEmail1 = new StudentAcademicEmail(studentID1.getUniqueNumber());
        StudentAcademicEmail academicEmail2 = new StudentAcademicEmail(studentID2.getUniqueNumber());
        StudentAcademicEmail academicEmail3 = new StudentAcademicEmail(studentID3.getUniqueNumber());

        Student student1 = new Student(studentID1, name1, nif1, phone1, email1, address1, academicEmail1);
        Student student2 = new Student(studentID2, name2, nif2, phone2, email2, address2, academicEmail2);
        Student student3 = new Student(studentID3, name3, nif3, phone3, email3, address3, academicEmail3);

        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepositoryImpl programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactory);
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student1.identity(), edition1.identity(), programmeEditionEnrolmentGeneratedID));
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student1.identity(), edition1.identity(), programmeEditionEnrolmentGeneratedID));
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student2.identity(), edition2.identity(), programmeEditionEnrolmentGeneratedID));
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student3.identity(), edition3.identity(), programmeEditionEnrolmentGeneratedID));

        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepositoryImpl schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearListFactoryImpl);
        schoolYearRepository.save(schoolYear1);
        schoolYearRepository.save(schoolYear2);

        CourseEditionEnrolmentListFactoryImpl courseEditionEnrolmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrolmentListFactory);

        ICourseEditionFactory courseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory courseEditionListFactory = new CourseEditionListFactoryImpl();

        CourseEditionRepositoryImpl courseEditionRepository = new CourseEditionRepositoryImpl(courseEditionFactory, courseEditionListFactory);

        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeRepositoryListFactory programmeLisListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);
        programmeEditionRepository.save(edition1);
        programmeEditionRepository.save(edition2);
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeLisListFactory);
        programmeRepository.save(programme1);
        programmeRepository.save(programme2);
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentList);

        DepartmentFactoryImpl departmentFactoryImpl = new DepartmentFactoryImpl();
        DepartmentListFactoryImpl departmentListFactoryImpl = new DepartmentListFactoryImpl();

        DepartmentRepositoryImpl departmentRepository = new DepartmentRepositoryImpl(departmentListFactoryImpl);
        departmentRepository.save(department);


        IProgrammeEditionEnrolmentFactory programmeEditionEnrolmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();

        ProgrammeEditionEnrolmentServiceImpl programmeEditionEnrolmentService =
                new ProgrammeEditionEnrolmentServiceImpl(programmeEditionEnrolmentRepository,
                        programmeEditionRepository,
                        courseEditionEnrolmentRepository,
                        courseEditionRepository,
                        schoolYearRepository,
                        programmeEnrolmentRepository,
                        programmeRepository,
                        programmeEditionEnrolmentFactory);

        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactory);
        SchoolYearServiceImpl schoolYearService = new SchoolYearServiceImpl(schoolYearRepository,schoolYearFactoryImpl,schoolYearMapperDTO);
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(departmentFactoryImpl,departmentRepository);
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();

        IDegreeTypeListFactory degreeTypeListFactory = new DegreeTypeListFactoryImpl();
        IDegreeTypeFactory degreeTypeFactory = new DegreeTypeFactoryImpl();
        IDegreeTypeRepository degreeTypeRepository = new DegreeTypeRepositoryImpl(degreeTypeListFactory);
        IDegreeTypeRegistrationService degreeTypeRegistrationService = new DegreeTypeRegistrationServiceImpl(degreeTypeFactory, degreeTypeRepository);

        ITeacherFactory teacherFactory = mock(TeacherFactoryImpl.class);
        ITeacherRepository teacherRepository = mock(TeacherRepositorySpringDataImpl.class);
        ITeacherService teacherService = new TeacherServiceImpl(teacherFactory, teacherRepository);

        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository, programmeAssembler, degreeTypeRegistrationService, departmentService, teacherService);

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller = new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(programmeEditionEnrolmentService, schoolYearService, departmentService, programmeService);

        // act
        Exception exception = assertThrows(Exception.class, () -> {
            controller.countStudentsInProgrammesFromDepartmentInSchoolYear(null, schoolYear1.identity());
        });
        // Assert
        assertEquals("Department or SchoolYear cannot be null", exception.getMessage());
    }


    //Test that confirms that an exception is thrown when schoolYearID is null
    @Test
    void shouldThrowExceptionWhenSchoolYearIDIsNull_Integration() throws Exception {
        // arrange
        Department department = new Department(new DepartmentAcronym("CSE"), new Name("Computer Science Engineer"), new TeacherID(new TeacherAcronym("PPP")));

        TeacherAcronym teacherAcronym = new TeacherAcronym("AAA");
        TeacherID teacherID = new TeacherID(teacherAcronym);
        DepartmentID departmentID = department.identity();

        Description description1 = new Description("School Year 25/26");
        Description description2 = new Description("School Year 22/23");
        Date startDate1 = new Date("01-09-2025");
        Date endDate1 = new Date("31-07-2026");
        Date startDate2 = new Date("01-09-2022");
        Date endDate2 = new Date("31-07-2026");
        SchoolYear schoolYear1 = new SchoolYear(description1, startDate1, endDate1);
        SchoolYear schoolYear2 = new SchoolYear(description2, startDate2, endDate2);

        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        Programme programme1 = programmeFactory.registerProgramme(new NameWithNumbersAndSpecialChars("PP"),
                new Acronym("APP"), new MaxEcts(30), new QuantSemesters(30), new DegreeTypeID(),
                departmentID, teacherID);
        Programme programme2 = programmeFactory.registerProgramme(new NameWithNumbersAndSpecialChars("PP2"),
                new Acronym("APP2"), new MaxEcts(30), new QuantSemesters(30), new DegreeTypeID(),
                departmentID, teacherID);

        ProgrammeEdition edition1 = new ProgrammeEdition(new ProgrammeEditionID(programme1.identity(), schoolYear1.identity()), programme1.identity(), schoolYear1.identity(), new ProgrammeEditionGeneratedID());
        ProgrammeEdition edition2 = new ProgrammeEdition(new ProgrammeEditionID(programme2.identity(), schoolYear1.identity()), programme2.identity(), schoolYear1.identity(), new ProgrammeEditionGeneratedID());
        ProgrammeEdition edition3 = new ProgrammeEdition(new ProgrammeEditionID(programme2.identity(), schoolYear2.identity()), programme2.identity(), schoolYear2.identity(), new ProgrammeEditionGeneratedID());

        Address address1 = new Address(new Street("Street1"), new PostalCode("4444-441"), new Location("Porto"), new Country("PT"));
        Address address2 = new Address(new Street("Street2"), new PostalCode("4444-442"), new Location("Braga"), new Country("PT"));
        Address address3 = new Address(new Street("Street3"), new PostalCode("4444-443"), new Location("Gaia"), new Country("PT"));

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


        StudentAcademicEmail academicEmail1 = new StudentAcademicEmail(studentID1.getUniqueNumber());
        StudentAcademicEmail academicEmail2 = new StudentAcademicEmail(studentID2.getUniqueNumber());
        StudentAcademicEmail academicEmail3 = new StudentAcademicEmail(studentID3.getUniqueNumber());

        Student student1 = new Student(studentID1, name1, nif1, phone1, email1, address1, academicEmail1);
        Student student2 = new Student(studentID2, name2, nif2, phone2, email2, address2, academicEmail2);
        Student student3 = new Student(studentID3, name3, nif3, phone3, email3, address3, academicEmail3);

        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepositoryImpl programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactory);
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student1.identity(), edition1.identity(), programmeEditionEnrolmentGeneratedID));
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student1.identity(), edition1.identity(), programmeEditionEnrolmentGeneratedID));
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student2.identity(), edition2.identity(), programmeEditionEnrolmentGeneratedID));
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student3.identity(), edition3.identity(), programmeEditionEnrolmentGeneratedID));

        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepositoryImpl schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearListFactoryImpl);
        schoolYearRepository.save(schoolYear1);
        schoolYearRepository.save(schoolYear2);

        CourseEditionEnrolmentListFactoryImpl courseEditionEnrolmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrolmentListFactory);

        ICourseEditionFactory courseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory courseEditionListFactory = new CourseEditionListFactoryImpl();

        CourseEditionRepositoryImpl courseEditionRepository = new CourseEditionRepositoryImpl(courseEditionFactory, courseEditionListFactory);

        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeRepositoryListFactory programmeLisListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);
        programmeEditionRepository.save(edition1);
        programmeEditionRepository.save(edition2);
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeLisListFactory);
        programmeRepository.save(programme1);
        programmeRepository.save(programme2);
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentList);

        DepartmentFactoryImpl departmentFactoryImpl = new DepartmentFactoryImpl();
        DepartmentListFactoryImpl departmentListFactoryImpl = new DepartmentListFactoryImpl();

        DepartmentRepositoryImpl departmentRepository = new DepartmentRepositoryImpl(departmentListFactoryImpl);
        departmentRepository.save(department);


        IProgrammeEditionEnrolmentFactory programmeEditionEnrolmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();

        ProgrammeEditionEnrolmentServiceImpl programmeEditionEnrolmentService =
                new ProgrammeEditionEnrolmentServiceImpl(programmeEditionEnrolmentRepository,
                        programmeEditionRepository,
                        courseEditionEnrolmentRepository,
                        courseEditionRepository,
                        schoolYearRepository,
                        programmeEnrolmentRepository,
                        programmeRepository,
                        programmeEditionEnrolmentFactory);

        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactory);
        SchoolYearServiceImpl schoolYearService = new SchoolYearServiceImpl(schoolYearRepository,schoolYearFactoryImpl,schoolYearMapperDTO);
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(departmentFactoryImpl,departmentRepository);
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();

        IDegreeTypeListFactory degreeTypeListFactory = new DegreeTypeListFactoryImpl();
        IDegreeTypeFactory degreeTypeFactory = new DegreeTypeFactoryImpl();
        IDegreeTypeRepository degreeTypeRepository = new DegreeTypeRepositoryImpl(degreeTypeListFactory);
        IDegreeTypeRegistrationService degreeTypeRegistrationService = new DegreeTypeRegistrationServiceImpl(degreeTypeFactory, degreeTypeRepository);

        ITeacherFactory teacherFactory = mock(TeacherFactoryImpl.class);
        ITeacherRepository teacherRepository = mock(TeacherRepositorySpringDataImpl.class);
        ITeacherService teacherService = new TeacherServiceImpl(teacherFactory, teacherRepository);

        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository, programmeAssembler, degreeTypeRegistrationService, departmentService, teacherService);

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller = new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(programmeEditionEnrolmentService, schoolYearService, departmentService, programmeService);

        // act
        Exception exception = assertThrows(Exception.class, () -> {
            controller.countStudentsInProgrammesFromDepartmentInSchoolYear(department.identity(), null);
        });
        // Assert
        assertEquals("Department or SchoolYear cannot be null", exception.getMessage());
    }

    //Test that confirms that an exception is thrown if schoolYearID is not found in Repository
    @Test
    void shouldThrowExceptionWhenSchoolYearIDIsNotFound_Integration() throws Exception {
        // arrange
        Department department = new Department(new DepartmentAcronym("CSE"), new Name("Computer Science Engineer"), new TeacherID(new TeacherAcronym("PPP")));

        TeacherAcronym teacherAcronym = new TeacherAcronym("AAA");
        TeacherID teacherID = new TeacherID(teacherAcronym);
        DepartmentID departmentID = department.identity();

        Description description1 = new Description("School Year 25/26");
        Description description2 = new Description("School Year 22/23");
        Date startDate1 = new Date("01-09-2025");
        Date endDate1 = new Date("31-07-2026");
        Date startDate2 = new Date("01-09-2022");
        Date endDate2 = new Date("31-07-2026");
        SchoolYear schoolYear1 = new SchoolYear(description1, startDate1, endDate1);
        SchoolYear schoolYear2 = new SchoolYear(description2, startDate2, endDate2);

        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        Programme programme1 = programmeFactory.registerProgramme(new NameWithNumbersAndSpecialChars("PP"),
                new Acronym("APP"), new MaxEcts(30), new QuantSemesters(30), new DegreeTypeID(),
                departmentID, teacherID);
        Programme programme2 = programmeFactory.registerProgramme(new NameWithNumbersAndSpecialChars("PP2"),
                new Acronym("APP2"), new MaxEcts(30), new QuantSemesters(30), new DegreeTypeID(),
                departmentID, teacherID);

        ProgrammeEdition edition1 = new ProgrammeEdition(new ProgrammeEditionID(programme1.identity(), schoolYear1.identity()), programme1.identity(), schoolYear1.identity(), new ProgrammeEditionGeneratedID());
        ProgrammeEdition edition2 = new ProgrammeEdition(new ProgrammeEditionID(programme2.identity(), schoolYear1.identity()), programme2.identity(), schoolYear1.identity(), new ProgrammeEditionGeneratedID());
        ProgrammeEdition edition3 = new ProgrammeEdition(new ProgrammeEditionID(programme2.identity(), schoolYear2.identity()), programme2.identity(), schoolYear2.identity(), new ProgrammeEditionGeneratedID());

        Address address1 = new Address(new Street("Street1"), new PostalCode("4444-441"), new Location("Porto"), new Country("PT"));
        Address address2 = new Address(new Street("Street2"), new PostalCode("4444-442"), new Location("Braga"), new Country("PT"));
        Address address3 = new Address(new Street("Street3"), new PostalCode("4444-443"), new Location("Gaia"), new Country("PT"));

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


        StudentAcademicEmail academicEmail1 = new StudentAcademicEmail(studentID1.getUniqueNumber());
        StudentAcademicEmail academicEmail2 = new StudentAcademicEmail(studentID2.getUniqueNumber());
        StudentAcademicEmail academicEmail3 = new StudentAcademicEmail(studentID3.getUniqueNumber());

        Student student1 = new Student(studentID1, name1, nif1, phone1, email1, address1, academicEmail1);
        Student student2 = new Student(studentID2, name2, nif2, phone2, email2, address2, academicEmail2);
        Student student3 = new Student(studentID3, name3, nif3, phone3, email3, address3, academicEmail3);

        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepositoryImpl programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactory);
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student1.identity(), edition1.identity(), programmeEditionEnrolmentGeneratedID));
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student1.identity(), edition1.identity(), programmeEditionEnrolmentGeneratedID));
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student2.identity(), edition2.identity(), programmeEditionEnrolmentGeneratedID));
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student3.identity(), edition3.identity(), programmeEditionEnrolmentGeneratedID));

        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepositoryImpl schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearListFactoryImpl);
        schoolYearRepository.save(schoolYear1);


        CourseEditionEnrolmentListFactoryImpl courseEditionEnrolmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrolmentListFactory);

        ICourseEditionFactory courseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory courseEditionListFactory = new CourseEditionListFactoryImpl();

        CourseEditionRepositoryImpl courseEditionRepository = new CourseEditionRepositoryImpl(courseEditionFactory, courseEditionListFactory);

        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeRepositoryListFactory programmeLisListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);
        programmeEditionRepository.save(edition1);
        programmeEditionRepository.save(edition2);
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeLisListFactory);
        programmeRepository.save(programme1);
        programmeRepository.save(programme2);
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentList);

        DepartmentFactoryImpl departmentFactoryImpl = new DepartmentFactoryImpl();
        DepartmentListFactoryImpl departmentListFactoryImpl = new DepartmentListFactoryImpl();

        DepartmentRepositoryImpl departmentRepository = new DepartmentRepositoryImpl(departmentListFactoryImpl);
        departmentRepository.save(department);


        IProgrammeEditionEnrolmentFactory programmeEditionEnrolmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();

        ProgrammeEditionEnrolmentServiceImpl programmeEditionEnrolmentService =
                new ProgrammeEditionEnrolmentServiceImpl(programmeEditionEnrolmentRepository,
                        programmeEditionRepository,
                        courseEditionEnrolmentRepository,
                        courseEditionRepository,
                        schoolYearRepository,
                        programmeEnrolmentRepository,
                        programmeRepository,
                        programmeEditionEnrolmentFactory);

        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactory);
        SchoolYearServiceImpl schoolYearService = new SchoolYearServiceImpl(schoolYearRepository,schoolYearFactoryImpl,schoolYearMapperDTO);
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(departmentFactoryImpl,departmentRepository);
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();

        IDegreeTypeListFactory degreeTypeListFactory = new DegreeTypeListFactoryImpl();
        IDegreeTypeFactory degreeTypeFactory = new DegreeTypeFactoryImpl();
        IDegreeTypeRepository degreeTypeRepository = new DegreeTypeRepositoryImpl(degreeTypeListFactory);
        IDegreeTypeRegistrationService degreeTypeRegistrationService = new DegreeTypeRegistrationServiceImpl(degreeTypeFactory, degreeTypeRepository);

        ITeacherFactory teacherFactory = mock(TeacherFactoryImpl.class);
        ITeacherRepository teacherRepository = mock(TeacherRepositorySpringDataImpl.class);
        ITeacherService teacherService = new TeacherServiceImpl(teacherFactory, teacherRepository);

        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository, programmeAssembler, degreeTypeRegistrationService, departmentService, teacherService);

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller = new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(programmeEditionEnrolmentService, schoolYearService, departmentService, programmeService);

        // act
        Exception exception = assertThrows(Exception.class, () -> {
            controller.countStudentsInProgrammesFromDepartmentInSchoolYear(department.identity(), schoolYear2.identity());
        });
        // Assert
        assertEquals("SchoolYear does not exist.", exception.getMessage());
    }


    //Test that confirms that an exception is thrown if departmentID is not found in Repository
    @Test
    void shouldThrowExceptionWhenDepartmentIDIsNotFound_Integration() throws Exception {
        // arrange
        Department department = new Department(new DepartmentAcronym("CSE"), new Name("Computer Science Engineer"), new TeacherID(new TeacherAcronym("PPP")));
        Department department2 = new Department(new DepartmentAcronym("ABE"), new Name("Computer Science neer"), new TeacherID(new TeacherAcronym("PPP")));

        TeacherAcronym teacherAcronym = new TeacherAcronym("AAA");
        TeacherID teacherID = new TeacherID(teacherAcronym);
        DepartmentID departmentID = department.identity();

        Description description1 = new Description("School Year 25/26");
        Description description2 = new Description("School Year 22/23");
        Date startDate1 = new Date("01-09-2025");
        Date endDate1 = new Date("31-07-2026");
        Date startDate2 = new Date("01-09-2022");
        Date endDate2 = new Date("31-07-2026");
        SchoolYear schoolYear1 = new SchoolYear(description1, startDate1, endDate1);
        SchoolYear schoolYear2 = new SchoolYear(description2, startDate2, endDate2);

        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        Programme programme1 = programmeFactory.registerProgramme(new NameWithNumbersAndSpecialChars("PP"),
                new Acronym("APP"), new MaxEcts(30), new QuantSemesters(30), new DegreeTypeID(),
                departmentID, teacherID);
        Programme programme2 = programmeFactory.registerProgramme(new NameWithNumbersAndSpecialChars("PP2"),
                new Acronym("APP2"), new MaxEcts(30), new QuantSemesters(30), new DegreeTypeID(),
                departmentID, teacherID);

        ProgrammeEdition edition1 = new ProgrammeEdition(new ProgrammeEditionID(programme1.identity(), schoolYear1.identity()), programme1.identity(), schoolYear1.identity(), new ProgrammeEditionGeneratedID());
        ProgrammeEdition edition2 = new ProgrammeEdition(new ProgrammeEditionID(programme2.identity(), schoolYear1.identity()), programme2.identity(), schoolYear1.identity(), new ProgrammeEditionGeneratedID());
        ProgrammeEdition edition3 = new ProgrammeEdition(new ProgrammeEditionID(programme2.identity(), schoolYear2.identity()), programme2.identity(), schoolYear2.identity(), new ProgrammeEditionGeneratedID());

        Address address1 = new Address(new Street("Street1"), new PostalCode("4444-441"), new Location("Porto"), new Country("PT"));
        Address address2 = new Address(new Street("Street2"), new PostalCode("4444-442"), new Location("Braga"), new Country("PT"));
        Address address3 = new Address(new Street("Street3"), new PostalCode("4444-443"), new Location("Gaia"), new Country("PT"));

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


        StudentAcademicEmail academicEmail1 = new StudentAcademicEmail(studentID1.getUniqueNumber());
        StudentAcademicEmail academicEmail2 = new StudentAcademicEmail(studentID2.getUniqueNumber());
        StudentAcademicEmail academicEmail3 = new StudentAcademicEmail(studentID3.getUniqueNumber());

        Student student1 = new Student(studentID1, name1, nif1, phone1, email1, address1, academicEmail1);
        Student student2 = new Student(studentID2, name2, nif2, phone2, email2, address2, academicEmail2);
        Student student3 = new Student(studentID3, name3, nif3, phone3, email3, address3, academicEmail3);

        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepositoryImpl programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactory);
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student1.identity(), edition1.identity(), programmeEditionEnrolmentGeneratedID));
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student1.identity(), edition1.identity(), programmeEditionEnrolmentGeneratedID));
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student2.identity(), edition2.identity(), programmeEditionEnrolmentGeneratedID));
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student3.identity(), edition3.identity(), programmeEditionEnrolmentGeneratedID));

        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepositoryImpl schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearListFactoryImpl);
        schoolYearRepository.save(schoolYear1);


        CourseEditionEnrolmentListFactoryImpl courseEditionEnrolmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrolmentListFactory);

        ICourseEditionFactory courseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory courseEditionListFactory = new CourseEditionListFactoryImpl();

        CourseEditionRepositoryImpl courseEditionRepository = new CourseEditionRepositoryImpl(courseEditionFactory, courseEditionListFactory);

        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeRepositoryListFactory programmeLisListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);
        programmeEditionRepository.save(edition1);
        programmeEditionRepository.save(edition2);
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeLisListFactory);
        programmeRepository.save(programme1);
        programmeRepository.save(programme2);
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentList);

        DepartmentFactoryImpl departmentFactoryImpl = new DepartmentFactoryImpl();
        DepartmentListFactoryImpl departmentListFactoryImpl = new DepartmentListFactoryImpl();

        DepartmentRepositoryImpl departmentRepository = new DepartmentRepositoryImpl(departmentListFactoryImpl);
        departmentRepository.save(department);


        IProgrammeEditionEnrolmentFactory programmeEditionEnrolmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();

        ProgrammeEditionEnrolmentServiceImpl programmeEditionEnrolmentService =
                new ProgrammeEditionEnrolmentServiceImpl(programmeEditionEnrolmentRepository,
                        programmeEditionRepository,
                        courseEditionEnrolmentRepository,
                        courseEditionRepository,
                        schoolYearRepository,
                        programmeEnrolmentRepository,
                        programmeRepository,
                        programmeEditionEnrolmentFactory);

        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactory);
        SchoolYearServiceImpl schoolYearService = new SchoolYearServiceImpl(schoolYearRepository,schoolYearFactoryImpl,schoolYearMapperDTO);
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(departmentFactoryImpl,departmentRepository);
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();

        IDegreeTypeListFactory degreeTypeListFactory = new DegreeTypeListFactoryImpl();
        IDegreeTypeFactory degreeTypeFactory = new DegreeTypeFactoryImpl();
        IDegreeTypeRepository degreeTypeRepository = new DegreeTypeRepositoryImpl(degreeTypeListFactory);
        IDegreeTypeRegistrationService degreeTypeRegistrationService = new DegreeTypeRegistrationServiceImpl(degreeTypeFactory, degreeTypeRepository);

        ITeacherFactory teacherFactory = mock(TeacherFactoryImpl.class);
        ITeacherRepository teacherRepository = mock(TeacherRepositorySpringDataImpl.class);
        ITeacherService teacherService = new TeacherServiceImpl(teacherFactory, teacherRepository);

        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository, programmeAssembler, degreeTypeRegistrationService, departmentService, teacherService);;

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller = new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(programmeEditionEnrolmentService, schoolYearService, departmentService, programmeService);

        // act
        Exception exception = assertThrows(Exception.class, () -> {
            controller.countStudentsInProgrammesFromDepartmentInSchoolYear(department2.identity(), schoolYear1.identity());
        });
        // Assert
        assertEquals("Department does not exist.", exception.getMessage());
    }


    //Test that confirms no students are found if there are no Programmes for given Department
    @Test
    void shouldReturn0StudentsIfNoProgrammesAreAssociatedToDepartment_Integration() throws Exception {
        // arrange
        Department department = new Department(new DepartmentAcronym("CSE"), new Name("Computer Science Engineer"), new TeacherID(new TeacherAcronym("PPP")));
        Department department2 = new Department(new DepartmentAcronym("ABE"), new Name("Computer Science neer"), new TeacherID(new TeacherAcronym("PPP")));

        TeacherAcronym teacherAcronym = new TeacherAcronym("AAA");
        TeacherID teacherID = new TeacherID(teacherAcronym);
        DepartmentID departmentID = department.identity();

        Description description1 = new Description("School Year 25/26");
        Description description2 = new Description("School Year 22/23");
        Date startDate1 = new Date("01-09-2025");
        Date endDate1 = new Date("31-07-2026");
        Date startDate2 = new Date("01-09-2022");
        Date endDate2 = new Date("31-07-2026");
        SchoolYear schoolYear1 = new SchoolYear(description1, startDate1, endDate1);
        SchoolYear schoolYear2 = new SchoolYear(description2, startDate2, endDate2);

        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        Programme programme1 = programmeFactory.registerProgramme(new NameWithNumbersAndSpecialChars("PP"),
                new Acronym("APP"), new MaxEcts(30), new QuantSemesters(30), new DegreeTypeID(),
                departmentID, teacherID);
        Programme programme2 = programmeFactory.registerProgramme(new NameWithNumbersAndSpecialChars("PP2"),
                new Acronym("APP2"), new MaxEcts(30), new QuantSemesters(30), new DegreeTypeID(),
                departmentID, teacherID);

        ProgrammeEdition edition1 = new ProgrammeEdition(new ProgrammeEditionID(programme1.identity(), schoolYear1.identity()), programme1.identity(), schoolYear1.identity(), new ProgrammeEditionGeneratedID());
        ProgrammeEdition edition2 = new ProgrammeEdition(new ProgrammeEditionID(programme2.identity(), schoolYear1.identity()), programme2.identity(), schoolYear1.identity(), new ProgrammeEditionGeneratedID());
        ProgrammeEdition edition3 = new ProgrammeEdition(new ProgrammeEditionID(programme2.identity(), schoolYear2.identity()), programme2.identity(), schoolYear2.identity(), new ProgrammeEditionGeneratedID());

        Address address1 = new Address(new Street("Street1"), new PostalCode("4444-441"), new Location("Porto"), new Country("PT"));
        Address address2 = new Address(new Street("Street2"), new PostalCode("4444-442"), new Location("Braga"), new Country("PT"));
        Address address3 = new Address(new Street("Street3"), new PostalCode("4444-443"), new Location("Gaia"), new Country("PT"));

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


        StudentAcademicEmail academicEmail1 = new StudentAcademicEmail(studentID1.getUniqueNumber());
        StudentAcademicEmail academicEmail2 = new StudentAcademicEmail(studentID2.getUniqueNumber());
        StudentAcademicEmail academicEmail3 = new StudentAcademicEmail(studentID3.getUniqueNumber());

        Student student1 = new Student(studentID1, name1, nif1, phone1, email1, address1, academicEmail1);
        Student student2 = new Student(studentID2, name2, nif2, phone2, email2, address2, academicEmail2);
        Student student3 = new Student(studentID3, name3, nif3, phone3, email3, address3, academicEmail3);

        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepositoryImpl programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactory);
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student1.identity(), edition1.identity(), programmeEditionEnrolmentGeneratedID));
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student1.identity(), edition1.identity(), programmeEditionEnrolmentGeneratedID));
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student2.identity(), edition2.identity(), programmeEditionEnrolmentGeneratedID));
        programmeEditionEnrolmentRepository.save(new ProgrammeEditionEnrolment(student3.identity(), edition3.identity(), programmeEditionEnrolmentGeneratedID));

        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepositoryImpl schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearListFactoryImpl);
        schoolYearRepository.save(schoolYear1);


        CourseEditionEnrolmentListFactoryImpl courseEditionEnrolmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrolmentListFactory);

        ICourseEditionFactory courseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory courseEditionListFactory = new CourseEditionListFactoryImpl();

        CourseEditionRepositoryImpl courseEditionRepository = new CourseEditionRepositoryImpl(courseEditionFactory, courseEditionListFactory);

        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeRepositoryListFactory programmeLisListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);
        programmeEditionRepository.save(edition1);
        programmeEditionRepository.save(edition2);
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeLisListFactory);
        programmeRepository.save(programme1);
        programmeRepository.save(programme2);
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentList);

        DepartmentFactoryImpl departmentFactoryImpl = new DepartmentFactoryImpl();
        DepartmentListFactoryImpl departmentListFactoryImpl = new DepartmentListFactoryImpl();

        DepartmentRepositoryImpl departmentRepository = new DepartmentRepositoryImpl(departmentListFactoryImpl);
        departmentRepository.save(department);
        departmentRepository.save(department2);


        IProgrammeEditionEnrolmentFactory programmeEditionEnrolmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();

        ProgrammeEditionEnrolmentServiceImpl programmeEditionEnrolmentService =
                new ProgrammeEditionEnrolmentServiceImpl(programmeEditionEnrolmentRepository,
                        programmeEditionRepository,
                        courseEditionEnrolmentRepository,
                        courseEditionRepository,
                        schoolYearRepository,
                        programmeEnrolmentRepository,
                        programmeRepository,
                        programmeEditionEnrolmentFactory);

        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactory);
        SchoolYearServiceImpl schoolYearService = new SchoolYearServiceImpl(schoolYearRepository,schoolYearFactoryImpl,schoolYearMapperDTO);
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(departmentFactoryImpl,departmentRepository);
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();

        IDegreeTypeListFactory degreeTypeListFactory = new DegreeTypeListFactoryImpl();
        IDegreeTypeFactory degreeTypeFactory = new DegreeTypeFactoryImpl();
        IDegreeTypeRepository degreeTypeRepository = new DegreeTypeRepositoryImpl(degreeTypeListFactory);
        IDegreeTypeRegistrationService degreeTypeRegistrationService = new DegreeTypeRegistrationServiceImpl(degreeTypeFactory, degreeTypeRepository);

        ITeacherFactory teacherFactory = mock(TeacherFactoryImpl.class);
        ITeacherRepository teacherRepository = mock(TeacherRepositorySpringDataImpl.class);
        ITeacherService teacherService = new TeacherServiceImpl(teacherFactory, teacherRepository);

        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository, programmeAssembler, degreeTypeRegistrationService, departmentService, teacherService);

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller = new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(programmeEditionEnrolmentService, schoolYearService, departmentService, programmeService);

        // act
      int result= controller.countStudentsInProgrammesFromDepartmentInSchoolYear(department2.identity(), schoolYear1.identity());
        // Assert
        assertEquals(0, result);
    }
    */
}
