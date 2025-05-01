package PAI.controller;


import PAI.VOs.*;
import PAI.domain.courseEditionEnrolment.*;
import PAI.domain.programmeEdition.IProgrammeEditionFactory;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.domain.programmeEdition.ProgrammeEditionFactoryImpl;
import PAI.factory.*;
import PAI.persistence.mem.CourseEditionEnrolmentRepositoryImpl;
import PAI.persistence.mem.SchoolYearRepositoryImpl;
import PAI.persistence.mem.programme.IProgrammeRepositoryListFactory;
import PAI.persistence.mem.programme.ProgrammeRepositoryImpl;
import PAI.persistence.mem.programme.ProgrammeRepositoryListFactoryImpl;
import PAI.persistence.mem.programmeEdition.IProgrammeEditionListFactory;
import PAI.persistence.mem.programmeEdition.ProgrammeEditionListFactoryImpl;
import PAI.persistence.mem.programmeEdition.ProgrammeEditionRepositoryImpl;
import PAI.repository.*;
import PAI.repository.programmeEditionRepository.IProgrammeEditionRepository;
import PAI.repository.programmeRepository.IProgrammeRepository;
import PAI.service.*;
import PAI.service.courseEdition.CourseEditionServiceImpl;
import PAI.service.courseEdition.ICourseEditionService;
import PAI.service.programme.IProgrammeService;
import PAI.service.programme.ProgrammeServiceImpl;
import PAI.service.programmeEdition.IProgrammeEditionService;
import PAI.service.programmeEdition.ProgrammeEditionService;
import PAI.service.schoolYear.ISchoolYearService;
import PAI.service.schoolYear.SchoolYearServiceImpl;
import org.junit.jupiter.api.Test;

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

//    @Test
//    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_Success_IntegrationTest() throws Exception {
//        // Arrange
//
//        // Programme Edition
//        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
//        ProgrammeEditionRepositoryImpl doubleProgrammeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);
//
//        // Programme Edition Enrolment
//        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
//        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
//        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactory);
//
//        // Programme
//        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(IProgrammeRepositoryListFactory);
//
//        // Course Edition
//        ICourseEditionFactory ICourseEditionFactory_2 = new CourseEditionFactoryImpl();
//        ICourseEditionListFactory ICourseEditionListFactory_2 = new CourseEditionListFactoryImpl();
//        ICourseEditionRepository courseEditionRepositoryImpl = new CourseEditionRepositoryImpl(ICourseEditionFactory_2, ICourseEditionListFactory_2);
//
//        // Course Edition Enrolment
//        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
//        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentListFactory);
//
//        // School Year
//        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
//        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
//        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearFactoryImpl, schoolYearListFactoryImpl);
//
//        // Programme Enrolment
//        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
//        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
//        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentFactory, programmeEnrolmentList);
//
//        // Services
//        ProgrammeEditionEnrolmentServiceImpl peeService = new ProgrammeEditionEnrolmentServiceImpl(
//                programmeEditionEnrolmentRepository,
//                doubleProgrammeEditionRepository,
//                courseEditionEnrolmentRepositoryImpl,
//                courseEditionRepositoryImpl,
//                schoolYearRepository,
//                programmeEnrolmentRepository,
//                programmeRepository,
//                programmeEditionEnrollmentFactory
//        );
//
//        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(
//                programmeFactory,
//                programmeRepository);
//
//        SchoolYearServiceImpl schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactoryImpl);
//
//        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
//                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeService, schoolYearService, peeService);
//
//        Date date = new Date("20-12-2010");
//
//        Description description = new Description("School Year 24/25");
//        Date startDate = new Date("23-11-2024");
//        Date endDate = new Date("09-12-2025");
//        schoolYearRepository.addSchoolYear(description, startDate, endDate);
//
//        SchoolYearID schoolYearId = new SchoolYearID();
//
//        AccessMethodID amId = new AccessMethodID();
//        StudentID studentID = mock(StudentID.class);
//
//        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("Computer Engineering");
//        Acronym pAcronym = new Acronym("CEN");
//        ProgrammeID programmeId = new ProgrammeID(nameWithNumbersAndSpecialChars, pAcronym);
//
//        if (!programmeEnrolmentRepository.isStudentEnrolled(studentID, programmeId)) {
//            programmeEnrolmentRepository.enrolStudents(studentID, amId, programmeId, date);
//        }
//        Date date1 = new Date("01-04-2023");
//        Date date2 = new Date("01-04-2024");
//        StudyPlanID studyPlanID1 = new StudyPlanID(programmeId, date1);
//        StudyPlanID studyPlanID2 = new StudyPlanID(programmeId, date2);
//        doubleProgrammeEditionRepository.createProgrammeEdition(programmeId, schoolYearId);
//        Optional<ProgrammeEditionID> pe1Opt = doubleProgrammeEditionRepository.findProgrammeEditionIDByProgrammeIDAndSchoolYearID(programmeId, schoolYearId);
//        ProgrammeEditionID pe1 = pe1Opt.get();
//
//        Acronym acronym2 = new Acronym("DSOFT");
//        Name name2 = new Name("Software Development");
//        CourseID courseId2 = new CourseID(acronym2, name2);
//        Acronym acronym3 = new Acronym("LABPROJ");
//        Name name3 = new Name("Project Lab");
//        CourseID courseId1 = new CourseID(acronym3, name3);
//        ProgrammeEditionID programmeEditionId = new ProgrammeEditionID(programmeId, schoolYearId);
//        CourseInStudyPlanID courseInStudyPlanID1 = new CourseInStudyPlanID(courseId1, studyPlanID1);
//        CourseInStudyPlanID courseInStudyPlanID2 = new CourseInStudyPlanID(courseId2, studyPlanID2);
//        CourseEditionID courseEditionId1 = new CourseEditionID(programmeEditionId, courseInStudyPlanID1);
//        CourseEditionID courseEditionId2 = new CourseEditionID(programmeEditionId, courseInStudyPlanID2);
//
//        courseEditionRepositoryImpl.createAndSaveCourseEdition(courseInStudyPlanID1, pe1);
//        courseEditionRepositoryImpl.createAndSaveCourseEdition(courseInStudyPlanID2, pe1);
//
//        // Act
//        boolean result = controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(studentID, programmeId, schoolYearId);
//        boolean result2 = courseEditionEnrolmentRepositoryImpl.isStudentEnrolledInCourseEdition(studentID, courseEditionId1);
//        boolean result3 = courseEditionEnrolmentRepositoryImpl.isStudentEnrolledInCourseEdition(studentID, courseEditionId2);
//
//        // Assert
//        assertTrue(result, "The student is enrolled in the ProgrammeEdition.");
//        assertTrue(result2, "The Student is enrolled in the CourseEdition.");
//        assertTrue(result3, "The Student is enrolled in the CourseEdition.");
//    }
//
//    @Test
//    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_StudentNotEnrolledInProgramme_IntegrationTest() throws Exception {
//        // Arrange
//
//        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
//        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
//        IProgrammeEditionRepository doubleProgrammeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionListFactory);
//        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
//        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
//        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
//        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(IProgrammeRepositoryListFactory);
//        ICourseEditionFactory ICourseEditionFactory_2 = new CourseEditionFactoryImpl();
//        ICourseEditionListFactory ICourseEditionListFactory_2 = new CourseEditionListFactoryImpl();
//        ICourseEditionRepository courseEditionRepositoryImpl = new CourseEditionRepositoryImpl(ICourseEditionFactory_2, ICourseEditionListFactory_2);
//        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
//        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentListFactory);
//        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
//        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
//        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearFactoryImpl, schoolYearListFactoryImpl);
//        IProgrammeEnrolmentRepository peRepository = mock(IProgrammeEnrolmentRepository.class);
//        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl(peRepository);
//        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
//        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentFactory, programmeEnrolmentList);
//        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionListFactory);
//        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = new ProgrammeEditionEnrolmentServiceImpl(programmeEditionEnrolmentRepository,programmeEditionRepository,courseEditionEnrolmentRepositoryImpl,
//                courseEditionRepositoryImpl,schoolYearRepository,programmeEnrolmentRepository,programmeRepository,programmeEditionEnrollmentFactory);
//
//        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
//                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
//                        programmeEditionEnrolmentRepository,
//                        doubleProgrammeEditionRepository,
//                        programmeRepository,
//                        courseEditionEnrolmentRepositoryImpl,
//                        courseEditionRepositoryImpl,
//                        schoolYearRepository,
//                        programmeEnrolmentRepository,
//                        programmeEditionEnrolmentService);
//
//        SchoolYearID schoolYearId = new SchoolYearID();
//
//        StudentID studentID = mock(StudentID.class);
//
//        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("Computer Engineering");
//        Acronym pAcronym = new Acronym("CEN");
//        ProgrammeID programmeId = new ProgrammeID(nameWithNumbersAndSpecialChars, pAcronym);
//
//        programmeEditionFactory.createProgrammeEdition(programmeId, schoolYearId);
//
//        // Act
//        boolean result = controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(studentID, programmeId, schoolYearId);
//
//        //Assert
//        assertFalse(result);
//    }
//
//    @Test
//    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_ProgrammeEditionNotFound_IntegrationTests() throws Exception {
//        // Arrange
//        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
//        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
//        IProgrammeEditionRepository doubleProgrammeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionListFactory);
//        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
//        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
//        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
//        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(IProgrammeRepositoryListFactory);
//        ICourseEditionFactory ICourseEditionFactory_2 = new CourseEditionFactoryImpl();
//        ICourseEditionListFactory ICourseEditionListFactory_2 = new CourseEditionListFactoryImpl();
//        ICourseEditionRepository courseEditionRepositoryImpl = new CourseEditionRepositoryImpl(ICourseEditionFactory_2, ICourseEditionListFactory_2);
//        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
//        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentListFactory);
//        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
//        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
//        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearFactoryImpl, schoolYearListFactoryImpl);
//        IProgrammeEnrolmentRepository peRepository = mock(IProgrammeEnrolmentRepository.class);
//        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl(peRepository);
//        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
//        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentFactory, programmeEnrolmentList);
//
//        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionListFactory);
//        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = new ProgrammeEditionEnrolmentServiceImpl(programmeEditionEnrolmentRepository,programmeEditionRepository,courseEditionEnrolmentRepositoryImpl,
//                courseEditionRepositoryImpl,schoolYearRepository,programmeEnrolmentRepository,programmeRepository,programmeEditionEnrollmentFactory);
//
//        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
//                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
//                        programmeEditionEnrolmentRepository,
//                        doubleProgrammeEditionRepository,
//                        programmeRepository,
//                        courseEditionEnrolmentRepositoryImpl,
//                        courseEditionRepositoryImpl,
//                        schoolYearRepository,
//                        programmeEnrolmentRepository,
//                        programmeEditionEnrolmentService);
//
//        Date date = new Date("20-12-2010");
//
//        SchoolYearID schoolYearId = new SchoolYearID();
//
//        AccessMethodID amId = new AccessMethodID();
//        StudentID studentID = mock(StudentID.class);
//
//        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("Computer Engineering");
//        Acronym pAcronym = new Acronym("CEN");
//        ProgrammeID programmeId = new ProgrammeID(nameWithNumbersAndSpecialChars, pAcronym);
//
//        if (!programmeEnrolmentRepository.isStudentEnrolled(studentID, programmeId)) {
//            programmeEnrolmentRepository.enrolStudents(studentID, amId, programmeId, date);
//        }
//        // Act
//        boolean result = controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(studentID, programmeId, schoolYearId);
//
//        assertFalse(result);
//    }
//
//
//    @Test
//    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_StudentAlreadyEnrolledInProgrammeEdition_IntegrationTest() throws Exception {
//        // Arrange
//        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
//        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
//        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionListFactory);
//        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
//        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
//        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
//        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        ProgrammeRepositoryImpl programmeRepository = new ProgrammeRepositoryImpl(IProgrammeRepositoryListFactory);
//        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
//        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
//        ICourseEditionRepository courseEditionRepository = new CourseEditionRepositoryImpl(ICourseEditionFactory, ICourseEditionListFactory);
//        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
//        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentListFactory);
//        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
//        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
//        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearFactoryImpl, schoolYearListFactoryImpl);
//        IProgrammeEnrolmentRepository peRepository = mock(IProgrammeEnrolmentRepository.class);
//        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl(peRepository);
//        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
//        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentFactory, programmeEnrolmentList);
//        IProgrammeEditionEnrolmentFactory programmeEditionEnrolmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
//        ICourseEditionEnrolmentListFactory courseEditionEnrolmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
//        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrolmentListFactory);
//
//
//        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = new ProgrammeEditionEnrolmentServiceImpl(
//                programmeEditionEnrolmentRepository,
//                programmeEditionRepository,
//                courseEditionEnrolmentRepository,
//                courseEditionRepository,
//                schoolYearRepository,
//                programmeEnrolmentRepository,
//                programmeRepository,
//                programmeEditionEnrolmentFactory);
//
//        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
//                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
//                        programmeEditionEnrolmentRepository,
//                        programmeEditionRepository,
//                        programmeRepository,
//                        courseEditionEnrolmentRepositoryImpl,
//                        courseEditionRepository,
//                        schoolYearRepository,
//                        programmeEnrolmentRepository,
//                        programmeEditionEnrolmentService);
//
//        Date date = new Date("20-12-2010");
//
//        SchoolYearID schoolYearId = new SchoolYearID();
//
//        AccessMethodID amId = new AccessMethodID();
//        StudentID studentID = mock(StudentID.class);
//
//        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("Computer Engineering");
//        Acronym pAcronym = new Acronym("CEN");
//        ProgrammeID programmeId = new ProgrammeID(nameWithNumbersAndSpecialChars, pAcronym);
//
//        if (!programmeEnrolmentRepository.isStudentEnrolled(studentID, programmeId)) {
//            programmeEnrolmentRepository.enrolStudents(studentID, amId, programmeId, date);
//        }
//
//        ProgrammeEdition programmeEdition = programmeEditionFactory.createProgrammeEdition(programmeId, schoolYearId);
//        programmeEditionRepository.save(programmeEdition);
//        Optional<ProgrammeEditionID> peOptional = programmeEditionRepository.findProgrammeEditionIDByProgrammeIDAndSchoolYearID(programmeId, schoolYearId);
//        ProgrammeEditionID programmeEditionId = peOptional.get();
//        programmeEditionEnrolmentService.enrolStudentInProgrammeEdition(studentID, programmeEditionId);
//
//        // Act
//        boolean result = controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(studentID, programmeId, schoolYearId);
//
//        //assert
//        assertFalse(result);
//    }
//
//
//    @Test
//    void testEnrollStudentInCourseEditionAndSetOfCoursesEditions_StudentAlreadyEnrolledInCourseEdition_IntegrationTest() throws Exception {
//        // Arrange
//        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
//        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);
//        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrolmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
//        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
//        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentFactory, programmeEditionEnrolmentListFactory);
//        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeRepositoryListFactory);
//        ICourseEditionFactory courseEditionFactory = new CourseEditionFactoryImpl();
//        ICourseEditionListFactory courseEditionListFactory = new CourseEditionListFactoryImpl();
//        ICourseEditionRepository courseEditionRepository = new CourseEditionRepositoryImpl(courseEditionFactory, courseEditionListFactory);
//        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
//        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentListFactory);
//        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
//        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
//        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearFactoryImpl, schoolYearListFactoryImpl);
//        IProgrammeEnrolmentRepository peRepo = mock(IProgrammeEnrolmentRepository.class);
//        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl(peRepo);
//        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
//        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentFactory, programmeEnrolmentList);
//
//        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = new ProgrammeEditionEnrolmentServiceImpl(
//                programmeEditionEnrolmentRepository,
//                programmeEditionRepository,
//                courseEditionEnrolmentRepositoryImpl,
//                courseEditionRepository,
//                schoolYearRepository,
//                programmeEnrolmentRepository,
//                programmeRepository,
//                programmeEditionEnrolmentFactory);
//
//        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
//                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
//                        programmeEditionEnrolmentRepository,
//                        programmeEditionRepository,
//                        programmeRepository,
//                        courseEditionEnrolmentRepositoryImpl,
//                        courseEditionRepository,
//                        schoolYearRepository,
//                        programmeEnrolmentRepository,
//                        programmeEditionEnrolmentService);
//
//        Date date = new Date("20-12-2010");
//        SchoolYearID schoolYearId = new SchoolYearID();
//        AccessMethodID amId = new AccessMethodID();
//        StudentID studentID = mock(StudentID.class);
//
//        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("Computer Engineering");
//        Acronym pAcronym = new Acronym("CEN");
//        ProgrammeID programmeId = new ProgrammeID(nameWithNumbersAndSpecialChars, pAcronym);
//
//        if (!programmeEnrolmentRepository.isStudentEnrolled(studentID, programmeId)) {
//            programmeEnrolmentRepository.enrolStudents(studentID, amId, programmeId, date);
//        }
//        Date date1 = new Date("01-04-2023");
//        Date date2 = new Date("01-04-2024");
//        StudyPlanID studyPlanID1 = new StudyPlanID(programmeId, date1);
//        StudyPlanID studyPlanID2 = new StudyPlanID(programmeId, date2);
//        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
//
//
//        IProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository);
//        ProgrammeEdition createdEdition = programmeEditionService.createProgrammeEdition(programmeId, schoolYearId);
//        programmeEditionService.saveProgrammeEdition(createdEdition);
//        Optional<ProgrammeEditionID> pe1Opt = programmeEditionRepository.findProgrammeEditionIDByProgrammeIDAndSchoolYearID(programmeId, schoolYearId);
//        ProgrammeEditionID pe1 = pe1Opt.get();
//
//        Acronym acronym2 = new Acronym("DSOFT");
//        Name name2 = new Name("Software Development");
//        CourseID courseId2 = new CourseID(acronym2, name2);
//        Acronym acronym3 = new Acronym("LABPROJ");
//        Name name3 = new Name("Project Lab");
//        CourseID courseId1 = new CourseID(acronym3, name3);
//        ProgrammeEditionID programmeEditionId = new ProgrammeEditionID(programmeId, schoolYearId);
//        CourseInStudyPlanID courseInStudyPlanID1 = new CourseInStudyPlanID(courseId1, studyPlanID1);
//        CourseInStudyPlanID courseInStudyPlanID2 = new CourseInStudyPlanID(courseId2, studyPlanID2);
//        CourseEditionID courseEditionId1 = new CourseEditionID(programmeEditionId, courseInStudyPlanID1);
//        CourseEditionID courseEditionId2 = new CourseEditionID(programmeEditionId, courseInStudyPlanID2);
//
//        ICourseEditionService courseEditionService = new CourseEditionServiceImpl(courseEditionFactory, courseEditionRepository);
//        courseEditionService.createAndSaveCourseEdition(courseInStudyPlanID1, pe1);
//        courseEditionService.createAndSaveCourseEdition(courseInStudyPlanID2, pe1);
//
//        courseEditionService.createAndSaveCourseEdition(courseInStudyPlanID1, pe1);
//        courseEditionService.createAndSaveCourseEdition(courseInStudyPlanID2, pe1);
//
//        ICourseEditionEnrolmentFactory courseEditionEnrolmentFactory = new CourseEditionEnrolmentFactoryImpl();
//        ICourseEditionEnrolmentListFactory courseEditionEnrolmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
//        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrolmentListFactory);
//        ICourseEditionEnrolmentService courseEditionEnrolmentService = new CourseEditionEnrolmentServiceImpl(courseEditionEnrolmentFactory, courseEditionEnrolmentRepository,programmeEditionEnrolmentRepository, courseEditionRepository);
//        courseEditionEnrolmentService.enrolStudentInACourseEdition(studentID, courseEditionId1);
//        courseEditionEnrolmentService.enrolStudentInACourseEdition(studentID, courseEditionId2);
//
//        // Act & Assert
//        assertThrows(IllegalStateException.class, () -> {
//            controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(studentID, programmeId, schoolYearId);
//        });
//    }

//    @Test
//    void testGetAllProgrammes_NotNull_IntegrationTest() throws Exception {
//        // Arrange
//        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
//        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);
//        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrolmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
//        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
//        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentFactory, programmeEditionEnrolmentListFactory);
//        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeFactory, IProgrammeRepositoryListFactory);
//        ICourseEditionFactory courseEditionFactory = new CourseEditionFactoryImpl();
//        ICourseEditionListFactory courseEditionListFactory = new CourseEditionListFactoryImpl();
//        ICourseEditionRepository courseEditionRepository = new CourseEditionRepositoryImpl(courseEditionFactory, courseEditionListFactory);
//        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
//        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentListFactory);
//        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
//        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
//        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearFactoryImpl, schoolYearListFactoryImpl);
//        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
//        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
//        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentFactory, programmeEnrolmentList);
//
//        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = new ProgrammeEditionEnrolmentServiceImpl(
//                programmeEditionEnrolmentRepository,
//                programmeEditionRepository,
//                courseEditionEnrolmentRepositoryImpl,
//                courseEditionRepository,
//                schoolYearRepository,
//                programmeEnrolmentRepository,
//                programmeRepository,
//                programmeEditionEnrolmentFactory);
//
//        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
//                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
//                        programmeEditionEnrolmentRepository,
//                        programmeEditionRepository,
//                        programmeRepository,
//                        courseEditionEnrolmentRepositoryImpl,
//                        courseEditionRepository,
//                        schoolYearRepository,
//                        programmeEnrolmentRepository,
//                        programmeEditionEnrolmentService);
//
//        Acronym acronym1 = new Acronym("CSE");
//        Acronym acronym2 = new Acronym("CVE");
//        NameWithNumbersAndSpecialChars name1 = new NameWithNumbersAndSpecialChars("Computer Engineering");
//        NameWithNumbersAndSpecialChars name2 = new NameWithNumbersAndSpecialChars("Civil Engineering");
//        QuantEcts quantEcts = new QuantEcts(25);
//        QuantSemesters quantSemesters = new QuantSemesters(6);
//        DepartmentAcronym dAcronym = new DepartmentAcronym("DEI");
//        DepartmentID departmentID = new DepartmentID(dAcronym);
//        DegreeTypeID degreeTypeID = new DegreeTypeID("Master");
//        TeacherAcronym tAcronym = new TeacherAcronym("AAA");
//        TeacherID teacherID1 = new TeacherID(tAcronym);
//
//        IProgrammeService programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository);
//        programmeService.registerProgramme(name1, acronym1, quantEcts, quantSemesters, degreeTypeID, departmentID, teacherID1);
//        programmeService.registerProgramme(name2, acronym2, quantEcts, quantSemesters, degreeTypeID, departmentID, teacherID1);
//
//        // Act
//        List<ProgrammeID> programmes = controller.getAllProgrammesIDs();
//
//        // Assert
//        assertNotNull(programmes, "The list of programmes should not be null.");
//    }
//
//    @Test
//    void testGetAllProgrammes_ListSize_IntegrationTest() throws Exception {
//        // Arrange
//        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
//        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);
//        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrolmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
//        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
//        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentFactory, programmeEditionEnrolmentListFactory);
//        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeFactory, IProgrammeRepositoryListFactory);
//        ICourseEditionFactory courseEditionFactory = new CourseEditionFactoryImpl();
//        ICourseEditionListFactory courseEditionListFactory = new CourseEditionListFactoryImpl();
//        ICourseEditionRepository courseEditionRepository = new CourseEditionRepositoryImpl(courseEditionFactory, courseEditionListFactory);
//        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
//        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentListFactory);
//        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
//        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
//        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearFactoryImpl, schoolYearListFactoryImpl);
//        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
//        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
//        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentFactory, programmeEnrolmentList);
//
//        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = new ProgrammeEditionEnrolmentServiceImpl(
//                programmeEditionEnrolmentRepository,
//                programmeEditionRepository,
//                courseEditionEnrolmentRepositoryImpl,
//                courseEditionRepository,
//                schoolYearRepository,
//                programmeEnrolmentRepository,
//                programmeRepository,
//                programmeEditionEnrolmentFactory);
//
//        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
//                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
//                        programmeEditionEnrolmentRepository,
//                        programmeEditionRepository,
//                        programmeRepository,
//                        courseEditionEnrolmentRepositoryImpl,
//                        courseEditionRepository,
//                        schoolYearRepository,
//                        programmeEnrolmentRepository,
//                        programmeEditionEnrolmentService);
//
//        Acronym acronym1 = new Acronym("CSE");
//        Acronym acronym2 = new Acronym("CVE");
//        NameWithNumbersAndSpecialChars name1 = new NameWithNumbersAndSpecialChars("Computer Engineering");
//        NameWithNumbersAndSpecialChars name2 = new NameWithNumbersAndSpecialChars("Civil Engineering");
//        QuantEcts quantEcts = new QuantEcts(25);
//        QuantSemesters quantSemesters = new QuantSemesters(6);
//        DepartmentAcronym dAcronym = new DepartmentAcronym("DEI");
//        DepartmentID departmentID = new DepartmentID(dAcronym);
//        DegreeTypeID degreeTypeID = new DegreeTypeID("Master");
//        TeacherAcronym tAcronym = new TeacherAcronym("AAA");
//        TeacherID teacherID1 = new TeacherID(tAcronym);
//
//
//        IProgrammeService programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository);
//        programmeService.registerProgramme(name1, acronym1, quantEcts, quantSemesters, degreeTypeID, departmentID, teacherID1);
//        programmeService.registerProgramme(name2, acronym2, quantEcts, quantSemesters, degreeTypeID, departmentID, teacherID1);
//
//        // Act
//        List<ProgrammeID> programmes = controller.getAllProgrammesIDs();
//
//        // Assert
//        assertEquals(2, programmes.size(), "The list of programmes should contain exactly 2 programmes.");
//
//    }

//    @Test
//    void testGetAllSchoolYears_NotNullList_IntegrationTest() throws Exception {
//        // Arrange
//        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
//        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);
//        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrolmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
//        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
//        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentFactory, programmeEditionEnrolmentListFactory);
//        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeRepositoryListFactory);
//        ICourseEditionFactory courseEditionFactory = new CourseEditionFactoryImpl();
//        ICourseEditionListFactory courseEditionListFactory = new CourseEditionListFactoryImpl();
//        ICourseEditionRepository courseEditionRepository = new CourseEditionRepositoryImpl(courseEditionFactory, courseEditionListFactory);
//        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
//        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentListFactory);
//        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
//        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
//        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearFactoryImpl, schoolYearListFactoryImpl);
//        IProgrammeEnrolmentRepository peRepository = mock(IProgrammeEnrolmentRepository.class);
//        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl(peRepository);
//        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
//        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentFactory, programmeEnrolmentList);
//
//        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = new ProgrammeEditionEnrolmentServiceImpl(
//                programmeEditionEnrolmentRepository,
//                programmeEditionRepository,
//                courseEditionEnrolmentRepositoryImpl,
//                courseEditionRepository,
//                schoolYearRepository,
//                programmeEnrolmentRepository,
//                programmeRepository,
//                programmeEditionEnrolmentFactory);
//
//        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
//                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
//                        programmeEditionEnrolmentRepository,
//                        programmeEditionRepository,
//                        programmeRepository,
//                        courseEditionEnrolmentRepositoryImpl,
//                        courseEditionRepository,
//                        schoolYearRepository,
//                        programmeEnrolmentRepository,
//                        programmeEditionEnrolmentService);
//
//        Description description1 = new Description("School Year 24/25");
//        Description description2 = new Description("School Year 25/26");
//        Date startDate1 = new Date("23-11-2024");
//        Date endDate1 = new Date("09-12-2025");
//        Date startDate2 = new Date("10-11-2025");
//        Date endDate2 = new Date("09-12-2026");
//        schoolYearRepository.addSchoolYear(description1, startDate1, endDate1);
//        schoolYearRepository.addSchoolYear(description2, startDate2, endDate2);
//
//        // Act
//        List<SchoolYearID> schoolYears = controller.getAllSchoolYearsIDs();
//
//        // Assert
//        assertNotNull(schoolYears, "The list of school years should not be null.");
//
//    }
//
//    @Test
//    void testGetAllSchoolYears_ListSize_IntegrationTest() throws Exception {
//
//        // Arrange
//        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
//        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionListFactory);
//        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrolmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
//        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
//        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentFactory, programmeEditionEnrolmentListFactory);
//        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeRepositoryListFactory);
//        ICourseEditionFactory courseEditionFactory = new CourseEditionFactoryImpl();
//        ICourseEditionListFactory courseEditionListFactory = new CourseEditionListFactoryImpl();
//        ICourseEditionRepository courseEditionRepository = new CourseEditionRepositoryImpl(courseEditionFactory, courseEditionListFactory);
//        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
//        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentListFactory);
//        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
//        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
//        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearFactoryImpl, schoolYearListFactoryImpl);
//        IProgrammeEnrolmentRepository peRepository = mock(IProgrammeEnrolmentRepository.class);
//        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl(peRepository);
//        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
//        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentFactory, programmeEnrolmentList);
//
//        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = new ProgrammeEditionEnrolmentServiceImpl(
//                programmeEditionEnrolmentRepository,
//                programmeEditionRepository,
//                courseEditionEnrolmentRepositoryImpl,
//                courseEditionRepository,
//                schoolYearRepository,
//                programmeEnrolmentRepository,
//                programmeRepository,
//                programmeEditionEnrolmentFactory);
//
//        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
//                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
//                        programmeEditionEnrolmentRepository,
//                        programmeEditionRepository,
//                        programmeRepository,
//                        courseEditionEnrolmentRepositoryImpl,
//                        courseEditionRepository,
//                        schoolYearRepository,
//                        programmeEnrolmentRepository,
//                        programmeEditionEnrolmentService
//                );
//        Description description1 = new Description("School Year 24/25");
//        Description description2 = new Description("School Year 25/26");
//        Date startDate1 = new Date("23-11-2024");
//        Date endDate1 = new Date("09-12-2025");
//        Date startDate2 = new Date("10-11-2025");
//        Date endDate2 = new Date("09-12-2026");
//        schoolYearRepository.addSchoolYear(description1, startDate1, endDate1);
//        schoolYearRepository.addSchoolYear(description2, startDate2, endDate2);
//
//        // Act
//        List<SchoolYearID> schoolYears = controller.getAllSchoolYearsIDs();
//
//        // Assert
//        assertEquals(2, schoolYears.size(), "The list of school years should contain exactly 2 years.");
//
//    }

}
