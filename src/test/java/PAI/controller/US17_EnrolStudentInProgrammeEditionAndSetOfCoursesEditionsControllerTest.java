package PAI.controller;


import PAI.VOs.*;
import PAI.assembler.programme.IProgrammeAssembler;
import PAI.assembler.programme.ProgrammeAssembler;
import PAI.assembler.programmeEdition.IProgrammeEditionAssembler;
import PAI.assembler.programmeEdition.ProgrammeEditionAssemblerImpl;
import PAI.domain.courseEdition.CourseEdition;
import PAI.domain.degreeType.DegreeType;
import PAI.domain.degreeType.DegreeTypeFactoryImpl;
import PAI.domain.degreeType.IDegreeTypeFactory;
import PAI.domain.department.Department;
import PAI.domain.programmeEnrolment.*;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolmentFactoryImpl;
import PAI.assembler.schoolYear.ISchoolYearAssembler;
import PAI.assembler.schoolYear.SchoolYearAssembler;
import PAI.domain.repositoryInterfaces.degreeType.IDegreeTypeRepository;
import PAI.domain.repositoryInterfaces.department.IDepartmentRepository;
import PAI.domain.repositoryInterfaces.teacher.ITeacherRepository;
import PAI.domain.teacher.Teacher;
import PAI.dto.Programme.ProgrammeVOsDTO;
import PAI.persistence.mem.degreeType.DegreeTypeListFactoryImpl;
import PAI.persistence.mem.degreeType.DegreeTypeRepositoryImpl;
import PAI.persistence.mem.degreeType.IDegreeTypeListFactory;
import PAI.persistence.mem.department.DepartmentListFactoryImpl;
import PAI.persistence.mem.department.DepartmentRepositoryImpl;
import PAI.persistence.mem.department.IDepartmentListFactory;
import PAI.persistence.mem.programmeEditionEnrolment.ProgrammeEditionEnrolmentListFactoryImpl;
import PAI.domain.schoolYear.SchoolYear;
import PAI.domain.courseEditionEnrolment.*;
import PAI.domain.programmeEdition.IProgrammeEditionFactory;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.domain.programmeEdition.ProgrammeEditionFactoryImpl;
import PAI.domain.schoolYear.SchoolYearFactoryImpl;
import PAI.domain.courseEdition.CourseEditionFactoryImpl;
import PAI.persistence.mem.courseEdition.CourseEditionListFactoryImpl;
import PAI.domain.courseEdition.ICourseEditionFactory;
import PAI.persistence.mem.courseEdition.ICourseEditionListFactory;
import PAI.domain.programme.IProgrammeFactory;
import PAI.domain.programme.ProgrammeFactoryImpl;
import PAI.mapper.schoolYear.SchoolYearMapperImpl;
import PAI.persistence.mem.courseEditionEnrolment.CourseEditionEnrolmentListFactoryImpl;
import PAI.persistence.mem.courseEditionEnrolment.CourseEditionEnrolmentRepositoryImpl;
import PAI.persistence.mem.courseEditionEnrolment.ICourseEditionEnrolmentListFactory;
import PAI.persistence.mem.programmeEditionEnrolment.ProgrammeEditionEnrolmentRepositoryImpl;
import PAI.persistence.mem.programmeEnrolment.IProgrammeEnrolmentListFactory;
import PAI.persistence.mem.programmeEnrolment.ProgrammeEnrolmentListFactoryImpl;
import PAI.persistence.mem.schoolYear.SchoolYearListFactoryImpl;
import PAI.persistence.mem.schoolYear.SchoolYearRepositoryImpl;
import PAI.persistence.mem.courseEdition.CourseEditionRepositoryImpl;
import PAI.persistence.mem.programme.IProgrammeRepositoryListFactory;
import PAI.persistence.mem.programme.ProgrammeRepositoryImpl;
import PAI.persistence.mem.programme.ProgrammeRepositoryListFactoryImpl;
import PAI.persistence.mem.programmeEdition.IProgrammeEditionListFactory;
import PAI.persistence.mem.programmeEdition.ProgrammeEditionListFactoryImpl;
import PAI.persistence.mem.programmeEdition.ProgrammeEditionRepositoryImpl;
import PAI.domain.repositoryInterfaces.courseEditionEnrolment.ICourseEditionEnrolmentRepository;
import PAI.domain.repositoryInterfaces.courseEdition.ICourseEditionRepository;
import PAI.domain.repositoryInterfaces.programmeEditionEnrolment.IProgrammeEditionEnrolmentRepository;
import PAI.domain.repositoryInterfaces.programmeEdition.IProgrammeEditionRepository;
import PAI.domain.repositoryInterfaces.programmeEnrolment.IProgrammeEnrolmentRepository;
import PAI.persistence.mem.programmeEnrolment.ProgrammeEnrolmentRepositoryImpl;
import PAI.domain.repositoryInterfaces.programme.IProgrammeRepository;
import PAI.domain.repositoryInterfaces.schoolYear.ISchoolYearRepository;
import PAI.persistence.mem.teacher.ITeacherListFactory;
import PAI.persistence.mem.teacher.TeacherListFactoryImpl;
import PAI.persistence.mem.teacher.TeacherRepositoryImpl;
import PAI.service.courseEdition.CourseEditionServiceImpl;
import PAI.service.courseEdition.ICourseEditionService;
import PAI.service.courseEditionEnrolment.CourseEditionEnrolmentServiceImpl;
import PAI.service.courseEditionEnrolment.ICourseEditionEnrolmentService;
import PAI.service.degreeType.DegreeTypeRegistrationServiceImpl;
import PAI.service.degreeType.IDegreeTypeRegistrationService;
import PAI.service.programme.IProgrammeService;
import PAI.service.programme.ProgrammeServiceImpl;
import PAI.service.programmeEdition.IProgrammeEditionService;
import PAI.service.programmeEdition.ProgrammeEditionService;
import PAI.service.programmeEditionEnrolment.IProgrammeEditionEnrolmentService;
import PAI.service.programmeEditionEnrolment.ProgrammeEditionEnrolmentServiceImpl;
import PAI.service.programmeEnrolment.IProgrammeEnrolmentService;
import PAI.service.programmeEnrolment.ProgrammeEnrolmentServiceImpl;
import PAI.service.schoolYear.ISchoolYearService;
import PAI.service.schoolYear.SchoolYearServiceImpl;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsControllerTest {

    @Test
    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_Success() throws Exception {
        // Arrange
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);

        StudentID studentID = mock(StudentID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);

        when(programmeEditionEnrolmentService.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(
                studentID, programmeID, schoolYearID)).thenReturn(true);

        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeService,
                        schoolYearService,
                        programmeEditionEnrolmentService
                );

        // Act
        boolean result = controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(
                studentID, programmeID, schoolYearID
        );

        // Assert
        assertTrue(result, "Student should be enrolled in ProgrammeEdition and Courses.");

    }


    @Test
    void testGetAllProgrammes() {
        // Arrange
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);

        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeService,
                        schoolYearService,
                        programmeEditionEnrolmentService
                );

        ProgrammeID doubleProgramme1 = mock(ProgrammeID.class);
        ProgrammeID doubleProgramme2 = mock(ProgrammeID.class);

        when(programmeService.getAllProgrammeIDs()).thenReturn(List.of(doubleProgramme1, doubleProgramme2));

        // Act
        List<ProgrammeID> doubleProgrammes = controller.getAllProgrammesIDs();

        // Assert
        assertNotNull(doubleProgrammes, "The list of programmes should not be null.");
    }

    @Test
    void testGetAllProgrammes_SizeEqualsTwo()  {
        // Arrange
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);

        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeService,
                        schoolYearService,
                        programmeEditionEnrolmentService
                );

        ProgrammeID doubleProgramme1 = mock(ProgrammeID.class);
        ProgrammeID doubleProgramme2 = mock(ProgrammeID.class);

        when(programmeService.getAllProgrammeIDs()).thenReturn(List.of(doubleProgramme1, doubleProgramme2));

        // Act
        List<ProgrammeID> doubleProgrammes = controller.getAllProgrammesIDs();

        // Assert
        assertEquals(2, doubleProgrammes.size(), "The list of programmes should contain exactly 2 programmes.");
    }

    @Test
    void testGetAllProgrammes_ContainsAllProgrammes() {
        // Arrange
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);

        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeService,
                        schoolYearService,
                        programmeEditionEnrolmentService
                );

        ProgrammeID doubleProgramme1 = mock(ProgrammeID.class);
        ProgrammeID doubleProgramme2 = mock(ProgrammeID.class);

        when(programmeService.getAllProgrammeIDs()).thenReturn(List.of(doubleProgramme1, doubleProgramme2));

        // Act
        List<ProgrammeID> doubleProgrammes = controller.getAllProgrammesIDs();

        // Assert
        assertTrue(doubleProgrammes.contains(doubleProgramme1));
        assertTrue(doubleProgrammes.contains(doubleProgramme2));

    }

    @Test
    void testGetAllSchoolYears_NotNull() {
        // Arrange
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);

        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeService,
                        schoolYearService,
                        programmeEditionEnrolmentService
                );

        SchoolYearID doubleSchoolYear1 = mock(SchoolYearID.class);
        SchoolYearID doubleSchoolYear2 = mock(SchoolYearID.class);

        when(schoolYearService.getAllSchoolYearsIDs()).thenReturn(List.of(doubleSchoolYear1, doubleSchoolYear2));

        // Act
        List<SchoolYearID> doubleSchoolYears = controller.getAllSchoolYearsIDs();

        // Assert
        assertNotNull(doubleSchoolYears, "The list of school years should not be null.");
    }

    @Test
    void testGetAllSchoolYears_SizeEqualsTwo()  {
        // Arrange
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);

        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeService,
                        schoolYearService,
                        programmeEditionEnrolmentService
                );

        SchoolYearID doubleSchoolYear1 = mock(SchoolYearID.class);
        SchoolYearID doubleSchoolYear2 = mock(SchoolYearID.class);

        when(schoolYearService.getAllSchoolYearsIDs()).thenReturn(List.of(doubleSchoolYear1, doubleSchoolYear2));

        // Act
        List<SchoolYearID> doubleSchoolYears = controller.getAllSchoolYearsIDs();

        // Assert
        assertEquals(2, doubleSchoolYears.size(), "The list of school years should contain exactly 2 years.");
    }

    @Test
    void testGetAllSchoolYears_ContainsAllSchoolYears()  {
        // Arrange
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);

        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeService,
                        schoolYearService,
                        programmeEditionEnrolmentService
                );

        SchoolYearID doubleSchoolYear1 = mock(SchoolYearID.class);
        SchoolYearID doubleSchoolYear2 = mock(SchoolYearID.class);

        when(schoolYearService.getAllSchoolYearsIDs()).thenReturn(List.of(doubleSchoolYear1, doubleSchoolYear2));

        // Act
        List<SchoolYearID> doubleSchoolYears = controller.getAllSchoolYearsIDs();

        // Assert
        assertTrue(doubleSchoolYears.contains(doubleSchoolYear1), "The list should contain doubleSchoolYear1.");
        assertTrue(doubleSchoolYears.contains(doubleSchoolYear2), "The list should contain doubleSchoolYear2.");
    }

    @Test
    void shouldReturnExceptionIfProgrammeServiceIsNull() {
        // arrange
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);

        // act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(null, schoolYearService, programmeEditionEnrolmentService);
        });

        // assert
        assertEquals("Programme service cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfSchoolYearServiceIsNull() {
        // arrange
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);

        // act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController( programmeService,null, programmeEditionEnrolmentService);
        });

        // assert
        assertEquals("School year service cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfProgrammeEditionEnrolmentServiceIsNull() {
        // arrange
        IProgrammeService programmeService = mock(IProgrammeService.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);

        // act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController( programmeService, schoolYearService, null);
        });

        // assert
        assertEquals("Programme edition enrolment service cannot be null.", exception.getMessage());
    }


    //----------------------INTEGRATION TESTS------------------------------


    @Test
    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_Success_IntegrationTest() throws Exception {
        // Arrange

        // Programme Edition
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        ProgrammeEditionRepositoryImpl doubleProgrammeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);

        // Programme Edition Enrolment
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactory);

        // Programme
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(IProgrammeRepositoryListFactory);
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();

        // Course Edition
        ICourseEditionFactory courseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory courseEditionListFactory = new CourseEditionListFactoryImpl();
        ICourseEditionRepository courseEditionRepositoryImpl = new CourseEditionRepositoryImpl(courseEditionFactory, courseEditionListFactory);

        // Course Edition Enrolment
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentListFactory);

        // School Year
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearListFactoryImpl);
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactoryImpl);

        // Programme Enrolment
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentList);

        // Services
        ProgrammeEditionEnrolmentServiceImpl peeService = new ProgrammeEditionEnrolmentServiceImpl(
                programmeEditionEnrolmentRepository,
                doubleProgrammeEditionRepository,
                courseEditionEnrolmentRepositoryImpl,
                courseEditionRepositoryImpl,
                schoolYearRepository,
                programmeEnrolmentRepository,
                programmeRepository,
                programmeEditionEnrollmentFactory
        );

        IDegreeTypeListFactory degreeTypeListFactory = new DegreeTypeListFactoryImpl();
        IDegreeTypeFactory degreeTypeFactory = new DegreeTypeFactoryImpl();
        IDegreeTypeRepository degreeTypeRepository = new DegreeTypeRepositoryImpl(degreeTypeListFactory);
        IDegreeTypeRegistrationService degreeTypeRegistrationService = new DegreeTypeRegistrationServiceImpl(degreeTypeFactory, degreeTypeRepository);


        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(
                programmeFactory,
                programmeRepository,
                programmeAssembler, degreeTypeRegistrationService);

        SchoolYearServiceImpl schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactoryImpl, schoolYearMapperDTO);

        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeService, schoolYearService, peeService);

        Date date = new Date("20-12-2010");

        Description description = new Description("School Year 24/25");
        Date startDate = new Date("23-11-2024");
        Date endDate = new Date("09-12-2025");

        SchoolYear sy = schoolYearFactoryImpl.createSchoolYear(description, startDate, endDate);
        schoolYearRepository.save(sy);
        SchoolYearID schoolYearId = new SchoolYearID();

        int number = 1234567;
        StudentID studentID = new StudentID(number);

        AccessMethodID amId = new AccessMethodID();

        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("Computer Engineering");
        Acronym pAcronym = new Acronym("CEN");
        ProgrammeID programmeId = new ProgrammeID(nameWithNumbersAndSpecialChars, pAcronym);

        if (!programmeEnrolmentRepository.isStudentEnrolled(studentID, programmeId)) {
            ProgrammeEnrolment programmeEnrolment = programmeEnrolmentFactory.createProgrammeEnrolment(studentID, amId, programmeId, date);
            programmeEnrolmentRepository.save(programmeEnrolment);
        }

        ProgrammeEdition pe = programmeEditionFactory.createProgrammeEdition(programmeId, schoolYearId);
        doubleProgrammeEditionRepository.save(pe);
        Optional<ProgrammeEditionID> pe1Opt = doubleProgrammeEditionRepository.findProgrammeEditionIDByProgrammeIDAndSchoolYearID(programmeId, schoolYearId);
        ProgrammeEditionID pe1 = pe1Opt.get();

        Date date1 = new Date("01-04-2023");
        Date date2 = new Date("01-04-2024");
        StudyPlanID studyPlanID1 = new StudyPlanID(programmeId, date1);
        StudyPlanID studyPlanID2 = new StudyPlanID(programmeId, date2);
        Acronym acronym2 = new Acronym("DSOFT");
        Name name2 = new Name("Software Development");
        CourseID courseId2 = new CourseID(acronym2, name2);
        Acronym acronym3 = new Acronym("LABPROJ");
        Name name3 = new Name("Project Lab");
        CourseID courseId1 = new CourseID(acronym3, name3);
        CourseInStudyPlanID courseInStudyPlanID1 = new CourseInStudyPlanID(courseId1, studyPlanID1);
        CourseInStudyPlanID courseInStudyPlanID2 = new CourseInStudyPlanID(courseId2, studyPlanID2);
        CourseEditionID courseEditionId1 = new CourseEditionID(pe1, courseInStudyPlanID1);
        CourseEditionID courseEditionId2 = new CourseEditionID(pe1, courseInStudyPlanID2);

        CourseEdition ce1 = courseEditionFactory.createCourseEditionToDomain(courseInStudyPlanID1, pe1);
        CourseEdition ce2 = courseEditionFactory.createCourseEditionToDomain(courseInStudyPlanID2, pe1);
        courseEditionRepositoryImpl.save(ce1);
        courseEditionRepositoryImpl.save(ce2);

        List<CourseEditionID> courseEditionIds = new ArrayList<>();
        courseEditionIds.add(courseEditionId1);
        courseEditionIds.add(courseEditionId2);

        // Act
        boolean result = controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(studentID, programmeId, schoolYearId);
        boolean result2 = courseEditionEnrolmentRepositoryImpl.isStudentEnrolledInCourseEdition(studentID, courseEditionId1);
        boolean result3 = courseEditionEnrolmentRepositoryImpl.isStudentEnrolledInCourseEdition(studentID, courseEditionId2);

        // Assert
        assertTrue(result, "The student is enrolled in the ProgrammeEdition.");
        assertTrue(result2, "The Student is enrolled in the CourseEdition.");
        assertTrue(result3, "The Student is enrolled in the CourseEdition.");
    }


    @Test
    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_StudentNotEnrolledInProgramme_IntegrationTest() throws Exception {
        // Arrange

        // Programme Edition
        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionListFactory);

        // Programme
        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(IProgrammeRepositoryListFactory);
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();

        IDegreeTypeListFactory degreeTypeListFactory = new DegreeTypeListFactoryImpl();
        IDegreeTypeFactory degreeTypeFactory = new DegreeTypeFactoryImpl();
        IDegreeTypeRepository degreeTypeRepository = new DegreeTypeRepositoryImpl(degreeTypeListFactory);
        IDegreeTypeRegistrationService degreeTypeRegistrationService = new DegreeTypeRegistrationServiceImpl(degreeTypeFactory, degreeTypeRepository);

        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(IProgrammeFactory, programmeRepository, programmeAssembler, degreeTypeRegistrationService);

        //Programme Enrolment
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentList);

        // Course Edition
        ICourseEditionFactory ICourseEditionFactory_2 = new CourseEditionFactoryImpl();
        ICourseEditionListFactory ICourseEditionListFactory_2 = new CourseEditionListFactoryImpl();
        ICourseEditionRepository courseEditionRepositoryImpl = new CourseEditionRepositoryImpl(ICourseEditionFactory_2, ICourseEditionListFactory_2);

        // Course Edition Enrolment
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentListFactory);

        //School Year
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactoryImpl);
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearListFactoryImpl);
        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactoryImpl, schoolYearMapperDTO);



        // Programme Edition Enrolment
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactory);
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService =
                new ProgrammeEditionEnrolmentServiceImpl(programmeEditionEnrolmentRepository,
                        programmeEditionRepository,
                        courseEditionEnrolmentRepositoryImpl,
                        courseEditionRepositoryImpl,
                        schoolYearRepository,
                        programmeEnrolmentRepository,
                        programmeRepository,
                        programmeEditionEnrollmentFactory);

        // Controller
        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeService,
                        schoolYearService,
                        programmeEditionEnrolmentService);

        SchoolYearID schoolYearId = new SchoolYearID();

        StudentID studentID = mock(StudentID.class);

        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("Computer Engineering");
        Acronym pAcronym = new Acronym("CEN");
        ProgrammeID programmeId = new ProgrammeID(nameWithNumbersAndSpecialChars, pAcronym);

        programmeEditionFactory.createProgrammeEdition(programmeId, schoolYearId);

        // Act
        boolean result = controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(studentID, programmeId, schoolYearId);

        //Assert
        assertFalse(result);
    }


    @Test
    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_ProgrammeEditionNotFound_IntegrationTests() throws Exception {
        // Arrange

        // Programme Edition
        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionListFactory);

        // Programme
        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(IProgrammeRepositoryListFactory);
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();

        IDegreeTypeListFactory degreeTypeListFactory = new DegreeTypeListFactoryImpl();
        IDegreeTypeFactory degreeTypeFactory = new DegreeTypeFactoryImpl();
        IDegreeTypeRepository degreeTypeRepository = new DegreeTypeRepositoryImpl(degreeTypeListFactory);
        IDegreeTypeRegistrationService degreeTypeRegistrationService = new DegreeTypeRegistrationServiceImpl(degreeTypeFactory, degreeTypeRepository);

        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(IProgrammeFactory, programmeRepository, programmeAssembler, degreeTypeRegistrationService);


        //Programme Enrolment
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentList);
        IProgrammeEnrolmentService programmeEnrolmentService = new ProgrammeEnrolmentServiceImpl(programmeEnrolmentFactory, programmeEnrolmentRepository);

        // Course Edition
        ICourseEditionFactory ICourseEditionFactory_2 = new CourseEditionFactoryImpl();
        ICourseEditionListFactory ICourseEditionListFactory_2 = new CourseEditionListFactoryImpl();
        ICourseEditionRepository courseEditionRepositoryImpl = new CourseEditionRepositoryImpl(ICourseEditionFactory_2, ICourseEditionListFactory_2);

        // Course Edition Enrolment
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentListFactory);

        //School Year
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactoryImpl);
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearListFactoryImpl);
        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactoryImpl, schoolYearMapperDTO);

        // Programme Edition Enrolment
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactory);
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService =
                new ProgrammeEditionEnrolmentServiceImpl(programmeEditionEnrolmentRepository,
                        programmeEditionRepository,
                        courseEditionEnrolmentRepositoryImpl,
                        courseEditionRepositoryImpl,
                        schoolYearRepository,
                        programmeEnrolmentRepository,
                        programmeRepository,
                        programmeEditionEnrollmentFactory);

        // Controller
        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeService,
                        schoolYearService,
                        programmeEditionEnrolmentService);

        Date date = new Date("20-12-2010");

        SchoolYearID schoolYearId = new SchoolYearID();

        AccessMethodID amId = new AccessMethodID();
        StudentID studentID = mock(StudentID.class);

        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("Computer Engineering");
        Acronym pAcronym = new Acronym("CEN");
        ProgrammeID programmeId = new ProgrammeID(nameWithNumbersAndSpecialChars, pAcronym);

        if (!programmeEnrolmentRepository.isStudentEnrolled(studentID, programmeId)) {
            programmeEnrolmentService.enrolStudentInProgramme(studentID, amId, programmeId, date);
        }
        // Act
        boolean result = controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(studentID, programmeId, schoolYearId);

        assertFalse(result);
    }


    @Test
    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_StudentAlreadyEnrolledInProgrammeEdition_IntegrationTest() throws Exception {
        // Arrange

        // Programme Edition
        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionListFactory);

        // Programme
        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(IProgrammeRepositoryListFactory);
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();

        IDegreeTypeListFactory degreeTypeListFactory = new DegreeTypeListFactoryImpl();
        IDegreeTypeFactory degreeTypeFactory = new DegreeTypeFactoryImpl();
        IDegreeTypeRepository degreeTypeRepository = new DegreeTypeRepositoryImpl(degreeTypeListFactory);
        IDegreeTypeRegistrationService degreeTypeRegistrationService = new DegreeTypeRegistrationServiceImpl(degreeTypeFactory, degreeTypeRepository);


        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(IProgrammeFactory, programmeRepository, programmeAssembler, degreeTypeRegistrationService);


        //Programme Enrolment
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentList);
        IProgrammeEnrolmentService programmeEnrolmentService = new ProgrammeEnrolmentServiceImpl(programmeEnrolmentFactory, programmeEnrolmentRepository);

        // Course Edition
        ICourseEditionFactory ICourseEditionFactory_2 = new CourseEditionFactoryImpl();
        ICourseEditionListFactory ICourseEditionListFactory_2 = new CourseEditionListFactoryImpl();
        ICourseEditionRepository courseEditionRepositoryImpl = new CourseEditionRepositoryImpl(ICourseEditionFactory_2, ICourseEditionListFactory_2);

        // Course Edition Enrolment
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentListFactory);

        //School Year
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactoryImpl);
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearListFactoryImpl);
        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactoryImpl, schoolYearMapperDTO);

        // Programme Edition Enrolment
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactory);
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService =
                new ProgrammeEditionEnrolmentServiceImpl(programmeEditionEnrolmentRepository,
                        programmeEditionRepository,
                        courseEditionEnrolmentRepositoryImpl,
                        courseEditionRepositoryImpl,
                        schoolYearRepository,
                        programmeEnrolmentRepository,
                        programmeRepository,
                        programmeEditionEnrollmentFactory);

        // Controller
        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeService,
                        schoolYearService,
                        programmeEditionEnrolmentService);

        Date date = new Date("20-12-2010");

        SchoolYearID schoolYearId = new SchoolYearID();

        AccessMethodID amId = new AccessMethodID();
        StudentID studentID = mock(StudentID.class);

        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("Computer Engineering");
        Acronym pAcronym = new Acronym("CEN");
        ProgrammeID programmeId = new ProgrammeID(nameWithNumbersAndSpecialChars, pAcronym);

        if (!programmeEnrolmentRepository.isStudentEnrolled(studentID, programmeId)) {
            programmeEnrolmentService.enrolStudentInProgramme(studentID, amId, programmeId, date);
        }

        ProgrammeEdition programmeEdition = programmeEditionFactory.createProgrammeEdition(programmeId, schoolYearId);
        programmeEditionRepository.save(programmeEdition);
        Optional<ProgrammeEditionID> peOptional = programmeEditionRepository.findProgrammeEditionIDByProgrammeIDAndSchoolYearID(programmeId, schoolYearId);
        ProgrammeEditionID programmeEditionId = peOptional.get();
        programmeEditionEnrolmentService.enrolStudentInProgrammeEdition(studentID, programmeEditionId);

        // Act
        boolean result = controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(studentID, programmeId, schoolYearId);

        //assert
        assertFalse(result);
    }


    @Test
    void testEnrollStudentInCourseEditionAndSetOfCoursesEditions_StudentAlreadyEnrolledInCourseEdition_IntegrationTest() throws Exception {
        // Arrange

        // Programme Edition
        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionListFactory);
        IProgrammeEditionAssembler programmeEditionAssembler = new ProgrammeEditionAssemblerImpl();

        // Programme
        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(IProgrammeRepositoryListFactory);
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();

        IDegreeTypeListFactory degreeTypeListFactory = new DegreeTypeListFactoryImpl();
        IDegreeTypeFactory degreeTypeFactory = new DegreeTypeFactoryImpl();
        IDegreeTypeRepository degreeTypeRepository = new DegreeTypeRepositoryImpl(degreeTypeListFactory);
        IDegreeTypeRegistrationService degreeTypeRegistrationService = new DegreeTypeRegistrationServiceImpl(degreeTypeFactory, degreeTypeRepository);

        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(IProgrammeFactory, programmeRepository, programmeAssembler, degreeTypeRegistrationService);


        //Programme Enrolment
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentList);
        IProgrammeEnrolmentService programmeEnrolmentService = new ProgrammeEnrolmentServiceImpl(programmeEnrolmentFactory, programmeEnrolmentRepository);

        // Course Edition
        ICourseEditionFactory courseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory courseEditionListFactory = new CourseEditionListFactoryImpl();
        ICourseEditionRepository courseEditionRepositoryImpl = new CourseEditionRepositoryImpl(courseEditionFactory, courseEditionListFactory);

        // Course Edition Enrolment
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentListFactory);

        //School Year
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactoryImpl);
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearListFactoryImpl);
        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactoryImpl, schoolYearMapperDTO);

        // Programme Edition Enrolment
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactory);
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService =
                new ProgrammeEditionEnrolmentServiceImpl(programmeEditionEnrolmentRepository,
                        programmeEditionRepository,
                        courseEditionEnrolmentRepositoryImpl,
                        courseEditionRepositoryImpl,
                        schoolYearRepository,
                        programmeEnrolmentRepository,
                        programmeRepository,
                        programmeEditionEnrollmentFactory);

        // Controller
        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeService,
                        schoolYearService,
                        programmeEditionEnrolmentService);

        Date date = new Date("20-12-2010");
        SchoolYearID schoolYearId = new SchoolYearID();
        AccessMethodID amId = new AccessMethodID();
        StudentID studentID = mock(StudentID.class);

        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("Computer Engineering");
        Acronym pAcronym = new Acronym("CEN");
        ProgrammeID programmeId = new ProgrammeID(nameWithNumbersAndSpecialChars, pAcronym);

        if (!programmeEnrolmentRepository.isStudentEnrolled(studentID, programmeId)) {
            programmeEnrolmentService.enrolStudentInProgramme(studentID, amId, programmeId, date);
        }
        Date date1 = new Date("01-04-2023");
        Date date2 = new Date("01-04-2024");
        StudyPlanID studyPlanID1 = new StudyPlanID(programmeId, date1);
        StudyPlanID studyPlanID2 = new StudyPlanID(programmeId, date2);


        IProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository,programmeRepository,schoolYearRepository,programmeEditionEnrolmentRepository,programmeEditionAssembler);
        ProgrammeEdition createdEdition = programmeEditionService.createProgrammeEdition(programmeId, schoolYearId);
        programmeEditionService.saveProgrammeEdition(createdEdition);
        Optional<ProgrammeEditionID> pe1Opt = programmeEditionRepository.findProgrammeEditionIDByProgrammeIDAndSchoolYearID(programmeId, schoolYearId);
        ProgrammeEditionID pe1 = pe1Opt.get();

        Acronym acronym2 = new Acronym("DSOFT");
        Name name2 = new Name("Software Development");
        CourseID courseId2 = new CourseID(acronym2, name2);
        Acronym acronym3 = new Acronym("LABPROJ");
        Name name3 = new Name("Project Lab");
        CourseID courseId1 = new CourseID(acronym3, name3);
        ProgrammeEditionID programmeEditionId = new ProgrammeEditionID(programmeId, schoolYearId);
        CourseInStudyPlanID courseInStudyPlanID1 = new CourseInStudyPlanID(courseId1, studyPlanID1);
        CourseInStudyPlanID courseInStudyPlanID2 = new CourseInStudyPlanID(courseId2, studyPlanID2);
        CourseEditionID courseEditionId1 = new CourseEditionID(programmeEditionId, courseInStudyPlanID1);
        CourseEditionID courseEditionId2 = new CourseEditionID(programmeEditionId, courseInStudyPlanID2);

        ICourseEditionService courseEditionService = new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepositoryImpl);
        courseEditionService.createAndSaveCourseEdition(courseInStudyPlanID1, pe1);
        courseEditionService.createAndSaveCourseEdition(courseInStudyPlanID2, pe1);

        courseEditionService.createAndSaveCourseEdition(courseInStudyPlanID1, pe1);
        courseEditionService.createAndSaveCourseEdition(courseInStudyPlanID2, pe1);

        ICourseEditionEnrolmentFactory courseEditionEnrolmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrolmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrolmentListFactory);

        ICourseEditionEnrolmentService courseEditionEnrolmentService = new CourseEditionEnrolmentServiceImpl(courseEditionEnrolmentFactory,
                courseEditionEnrolmentRepository,programmeEditionEnrolmentRepository, courseEditionRepositoryImpl);
        courseEditionEnrolmentService.enrolStudentInACourseEdition(studentID, courseEditionId1);
        courseEditionEnrolmentService.enrolStudentInACourseEdition(studentID, courseEditionId2);

        programmeEditionEnrolmentService.enrolStudentInProgrammeEdition(studentID, pe1);

        // Act
        boolean result = controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(studentID, programmeId, schoolYearId);

        // Assert
        assertFalse(result, "Expected enrolment to fail because student is already enrolled in course editions.");
    }


    @Test
    void testGetAllProgrammes_NotNull_IntegrationTest() throws Exception {
        // Arrange

        // Programme Edition
        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionListFactory);

        // Programme
        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(IProgrammeRepositoryListFactory);
        IDegreeTypeListFactory degreeTypeListFactory = new DegreeTypeListFactoryImpl();
        IDegreeTypeRepository degreeTypeRepository = new DegreeTypeRepositoryImpl(degreeTypeListFactory);
        IDepartmentListFactory departmentListFactory = new DepartmentListFactoryImpl();
        IDepartmentRepository departmentRepository = new DepartmentRepositoryImpl(departmentListFactory);
        ITeacherListFactory teacherListFactory = new TeacherListFactoryImpl();
        ITeacherRepository teacherRepository = new TeacherRepositoryImpl(teacherListFactory);
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();

        IDegreeTypeFactory degreeTypeFactory = new DegreeTypeFactoryImpl();
        IDegreeTypeRegistrationService degreeTypeRegistrationService = new DegreeTypeRegistrationServiceImpl(degreeTypeFactory, degreeTypeRepository);

        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(IProgrammeFactory, programmeRepository, programmeAssembler, degreeTypeRegistrationService);

        //Programme Enrolment
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentList);

        // Course Edition
        ICourseEditionFactory courseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory courseEditionListFactory = new CourseEditionListFactoryImpl();
        ICourseEditionRepository courseEditionRepositoryImpl = new CourseEditionRepositoryImpl(courseEditionFactory, courseEditionListFactory);

        // Course Edition Enrolment
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentListFactory);

        //School Year
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactoryImpl);
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearListFactoryImpl);
        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactoryImpl, schoolYearMapperDTO);

        // Programme Edition Enrolment
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactory);
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService =
                new ProgrammeEditionEnrolmentServiceImpl(programmeEditionEnrolmentRepository,
                        programmeEditionRepository,
                        courseEditionEnrolmentRepositoryImpl,
                        courseEditionRepositoryImpl,
                        schoolYearRepository,
                        programmeEnrolmentRepository,
                        programmeRepository,
                        programmeEditionEnrollmentFactory);

        // Controller
        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeService,
                        schoolYearService,
                        programmeEditionEnrolmentService);

        Acronym acronym1 = new Acronym("CSE");
        Acronym acronym2 = new Acronym("CVE");
        NameWithNumbersAndSpecialChars name1 = new NameWithNumbersAndSpecialChars("Computer Engineering");
        NameWithNumbersAndSpecialChars name2 = new NameWithNumbersAndSpecialChars("Civil Engineering");
        MaxEcts maxEcts = new MaxEcts(25);
        QuantSemesters quantSemesters = new QuantSemesters(4);
        DepartmentAcronym dAcronym = new DepartmentAcronym("DEI");
        DepartmentID departmentID = new DepartmentID(dAcronym);
        DegreeTypeID degreeTypeID = new DegreeTypeID("Master");
        Name name = new Name("Master");
        Name departmentName = new Name("Astronomy");
        Name teacherName = new Name("AAA");
        Email email = new Email ("aaa@gmail.com");
        Country country = new Country("Portugal");
        NIF nif = new NIF("123456789", country);
        TeacherAcronym tAcronym = new TeacherAcronym("AAA");
        PhoneNumber phoneNumber = new PhoneNumber("+351", "912345678");
        AcademicBackground academicBackground = new AcademicBackground("Mestrado ISEP 2024");
        Street street = new Street("Rua das Flores");
        PostalCode postalCode = new PostalCode("4450-789");
        Location location = new Location("Coimbra");
        Address address = new Address(street, postalCode, location, country);
        TeacherID teacherID1 = new TeacherID(tAcronym);
        DegreeType degreeType = new DegreeType(degreeTypeID, name, maxEcts);
        Department department = new Department(dAcronym, departmentName);
        Teacher teacher = new Teacher(teacherID1, teacherName, email, nif, phoneNumber, academicBackground, address, departmentID);

        degreeTypeRepository.save(degreeType);
        departmentRepository.save(department);
        teacherRepository.save(teacher);

        ProgrammeVOsDTO programmeVOsDTO1 = new ProgrammeVOsDTO(name1, acronym1, maxEcts, quantSemesters, degreeTypeID, departmentID, teacherID1);
        ProgrammeVOsDTO programmeVOsDTO2 = new ProgrammeVOsDTO(name2, acronym2, maxEcts, quantSemesters, degreeTypeID, departmentID, teacherID1);

        programmeService.registerProgramme(programmeVOsDTO1);
        programmeService.registerProgramme(programmeVOsDTO2);

        // Act
        List<ProgrammeID> programmes = controller.getAllProgrammesIDs();

        // Assert
        assertNotNull(programmes, "The list of programmes should not be null.");
    }


    @Test
    void testGetAllProgrammes_ListSize_IntegrationTest() throws Exception {
        // Arrange

        // Programme Edition
        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionListFactory);

        // Programme
        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(IProgrammeRepositoryListFactory);
        IDegreeTypeListFactory degreeTypeListFactory = new DegreeTypeListFactoryImpl();
        IDegreeTypeRepository degreeTypeRepository = new DegreeTypeRepositoryImpl(degreeTypeListFactory);
        IDepartmentListFactory departmentListFactory = new DepartmentListFactoryImpl();
        IDepartmentRepository departmentRepository = new DepartmentRepositoryImpl(departmentListFactory);
        ITeacherListFactory teacherListFactory = new TeacherListFactoryImpl();
        ITeacherRepository teacherRepository = new TeacherRepositoryImpl(teacherListFactory);
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();

        IDegreeTypeFactory degreeTypeFactory = new DegreeTypeFactoryImpl();
        IDegreeTypeRegistrationService degreeTypeRegistrationService = new DegreeTypeRegistrationServiceImpl(degreeTypeFactory, degreeTypeRepository);

        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(IProgrammeFactory, programmeRepository, programmeAssembler, degreeTypeRegistrationService);

        //Programme Enrolment
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentList);

        // Course Edition
        ICourseEditionFactory courseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory courseEditionListFactory = new CourseEditionListFactoryImpl();
        ICourseEditionRepository courseEditionRepositoryImpl = new CourseEditionRepositoryImpl(courseEditionFactory, courseEditionListFactory);

        // Course Edition Enrolment
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentListFactory);

        //School Year
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactoryImpl);
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearListFactoryImpl);
        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactoryImpl, schoolYearMapperDTO);

        // Programme Edition Enrolment
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactory);
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService =
                new ProgrammeEditionEnrolmentServiceImpl(programmeEditionEnrolmentRepository,
                        programmeEditionRepository,
                        courseEditionEnrolmentRepositoryImpl,
                        courseEditionRepositoryImpl,
                        schoolYearRepository,
                        programmeEnrolmentRepository,
                        programmeRepository,
                        programmeEditionEnrollmentFactory);

        // Controller
        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeService,
                        schoolYearService,
                        programmeEditionEnrolmentService);

        Acronym acronym1 = new Acronym("CSE");
        Acronym acronym2 = new Acronym("CVE");
        NameWithNumbersAndSpecialChars name1 = new NameWithNumbersAndSpecialChars("Computer Engineering");
        NameWithNumbersAndSpecialChars name2 = new NameWithNumbersAndSpecialChars("Civil Engineering");
        MaxEcts maxEcts = new MaxEcts(25);
        QuantSemesters quantSemesters = new QuantSemesters(4);
        DepartmentAcronym dAcronym = new DepartmentAcronym("DEI");
        DepartmentID departmentID = new DepartmentID(dAcronym);
        DegreeTypeID degreeTypeID = new DegreeTypeID("Master");
        TeacherAcronym tAcronym = new TeacherAcronym("AAA");
        TeacherID teacherID1 = new TeacherID(tAcronym);
        Name name = new Name("Master");
        Name departmentName = new Name("Astronomy");
        Name teacherName = new Name("AAA");
        Email email = new Email ("aaa@gmail.com");
        Country country = new Country("Portugal");
        NIF nif = new NIF("123456789", country);
        PhoneNumber phoneNumber = new PhoneNumber("+351", "912345678");
        AcademicBackground academicBackground = new AcademicBackground("Mestrado ISEP 2024");
        Street street = new Street("Rua das Flores");
        PostalCode postalCode = new PostalCode("4450-789");
        Location location = new Location("Coimbra");
        Address address = new Address(street, postalCode, location, country);
        DegreeType degreeType = new DegreeType(degreeTypeID, name, maxEcts);
        Department department = new Department(dAcronym, departmentName);
        Teacher teacher = new Teacher(teacherID1, teacherName, email, nif, phoneNumber, academicBackground, address, departmentID);

        degreeTypeRepository.save(degreeType);
        departmentRepository.save(department);
        teacherRepository.save(teacher);

        ProgrammeVOsDTO programmeVOsDTO1 = new ProgrammeVOsDTO(name1, acronym1, maxEcts, quantSemesters, degreeTypeID, departmentID, teacherID1);
        ProgrammeVOsDTO programmeVOsDTO2 = new ProgrammeVOsDTO(name2, acronym2, maxEcts, quantSemesters, degreeTypeID, departmentID, teacherID1);

        programmeService.registerProgramme(programmeVOsDTO1);
        programmeService.registerProgramme(programmeVOsDTO2);

        // Act
        List<ProgrammeID> programmes = controller.getAllProgrammesIDs();

        // Assert
        assertEquals(2, programmes.size(), "The list of programmes should contain exactly 2 programmes.");

    }


    @Test
    void testGetAllSchoolYears_NotNullList_IntegrationTest() throws Exception {
        // Arrange

        // Programme Edition
        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionListFactory);

        // Programme
        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(IProgrammeRepositoryListFactory);
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();

        IDegreeTypeListFactory degreeTypeListFactory = new DegreeTypeListFactoryImpl();
        IDegreeTypeFactory degreeTypeFactory = new DegreeTypeFactoryImpl();
        IDegreeTypeRepository degreeTypeRepository = new DegreeTypeRepositoryImpl(degreeTypeListFactory);
        IDegreeTypeRegistrationService degreeTypeRegistrationService = new DegreeTypeRegistrationServiceImpl(degreeTypeFactory, degreeTypeRepository);

        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(IProgrammeFactory, programmeRepository, programmeAssembler, degreeTypeRegistrationService);


        //Programme Enrolment
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentList);

        // Course Edition
        ICourseEditionFactory courseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory courseEditionListFactory = new CourseEditionListFactoryImpl();
        ICourseEditionRepository courseEditionRepositoryImpl = new CourseEditionRepositoryImpl(courseEditionFactory, courseEditionListFactory);

        // Course Edition Enrolment
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentListFactory);

        //School Year
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactoryImpl);
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearListFactoryImpl);
        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactoryImpl,schoolYearMapperDTO);

        // Programme Edition Enrolment
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactory);
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService =
                new ProgrammeEditionEnrolmentServiceImpl(programmeEditionEnrolmentRepository,
                        programmeEditionRepository,
                        courseEditionEnrolmentRepositoryImpl,
                        courseEditionRepositoryImpl,
                        schoolYearRepository,
                        programmeEnrolmentRepository,
                        programmeRepository,
                        programmeEditionEnrollmentFactory);

        // Controller
        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeService,
                        schoolYearService,
                        programmeEditionEnrolmentService);

        Description descriptionInfo = new Description("ola");
        Date startDateInfo = mock(Date.class);
        Date endDateInfo = mock(Date.class);
        Description descriptionInfo1 = new Description("olas");
        Date startDateInfo1 = mock(Date.class);
        Date endDateInfo1 = mock(Date.class);

        schoolYearService.addSchoolYear(descriptionInfo, startDateInfo, endDateInfo);
        schoolYearService.addSchoolYear(descriptionInfo1, startDateInfo1, endDateInfo1);

        // Act
        List<SchoolYearID> schoolYears = controller.getAllSchoolYearsIDs();

        // Assert
        assertNotNull(schoolYears, "The list of school years should not be null.");

    }

    @Test
    void testGetAllSchoolYears_ListSize_IntegrationTest() throws Exception {
// Arrange

        // Programme Edition
        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionListFactory);

        // Programme
        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(IProgrammeRepositoryListFactory);
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();

        IDegreeTypeListFactory degreeTypeListFactory = new DegreeTypeListFactoryImpl();
        IDegreeTypeFactory degreeTypeFactory = new DegreeTypeFactoryImpl();
        IDegreeTypeRepository degreeTypeRepository = new DegreeTypeRepositoryImpl(degreeTypeListFactory);
        IDegreeTypeRegistrationService degreeTypeRegistrationService = new DegreeTypeRegistrationServiceImpl(degreeTypeFactory, degreeTypeRepository);

        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(IProgrammeFactory, programmeRepository, programmeAssembler, degreeTypeRegistrationService);


        //Programme Enrolment
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentList);

        // Course Edition
        ICourseEditionFactory courseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory courseEditionListFactory = new CourseEditionListFactoryImpl();
        ICourseEditionRepository courseEditionRepositoryImpl = new CourseEditionRepositoryImpl(courseEditionFactory, courseEditionListFactory);

        // Course Edition Enrolment
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentListFactory);

        //School Year
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactoryImpl);
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearListFactoryImpl);
        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactoryImpl, schoolYearMapperDTO);

        // Programme Edition Enrolment
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactory);
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService =
                new ProgrammeEditionEnrolmentServiceImpl(programmeEditionEnrolmentRepository,
                        programmeEditionRepository,
                        courseEditionEnrolmentRepositoryImpl,
                        courseEditionRepositoryImpl,
                        schoolYearRepository,
                        programmeEnrolmentRepository,
                        programmeRepository,
                        programmeEditionEnrollmentFactory);

        // Controller
        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeService,
                        schoolYearService,
                        programmeEditionEnrolmentService);

        Description descriptionInfo = new Description("ola");
        Description descriptionInfos = new Description("olas");
        Date startDateInfo = new Date(LocalDate.of(2025,2,2));
        Date startDateInfos = new Date(LocalDate.of(2026,2,2));
        Date endDateInfo = new Date(LocalDate.of(2026,2,2));
        Date endDateInfos = new Date(LocalDate.of(2027,2,2));
        schoolYearService.addSchoolYear(descriptionInfo, startDateInfo, endDateInfo);
        schoolYearService.addSchoolYear(descriptionInfos, startDateInfos, endDateInfos);

        // Act
        List<SchoolYearID> schoolYears = controller.getAllSchoolYearsIDs();

        // Assert
        assertEquals(2, schoolYears.size(), "The list of school years should contain exactly 2 years.");

    }

}
