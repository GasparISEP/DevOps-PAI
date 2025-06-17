package PAI.controller;

import PAI.VOs.*;
import PAI.assembler.courseInStudyPlan.CourseInStudyPlanAssemblerImpl;
import PAI.assembler.courseInStudyPlan.CourseInStudyPlanBusinessAssemblerImpl;
import PAI.assembler.courseInStudyPlan.CourseInStudyPlanServiceAssemblerImpl;
import PAI.domain.courseEditionEnrolment.*;
import PAI.domain.courseInStudyPlan.CourseInStudyPlanFactoryImpl;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolmentFactoryImpl;
import PAI.persistence.mem.programmeEditionEnrolment.ProgrammeEditionEnrolmentListFactoryImpl;
import PAI.persistence.mem.programmeEnrolment.IProgrammeEnrolmentListFactory;
import PAI.persistence.mem.programmeEnrolment.ProgrammeEnrolmentListFactoryImpl;
import PAI.domain.courseEdition.CourseEditionFactoryImpl;
import PAI.persistence.mem.courseEdition.CourseEditionListFactoryImpl;
import PAI.persistence.mem.courseEditionEnrolment.CourseEditionEnrolmentListFactoryImpl;
import PAI.persistence.mem.courseEditionEnrolment.CourseEditionEnrolmentRepositoryImpl;
import PAI.persistence.mem.courseEditionEnrolment.ICourseEditionEnrolmentListFactory;
import PAI.persistence.mem.programmeEditionEnrolment.ProgrammeEditionEnrolmentRepositoryImpl;
import PAI.persistence.mem.schoolYear.SchoolYearListFactoryImpl;
import PAI.persistence.mem.schoolYear.SchoolYearRepositoryImpl;
import PAI.persistence.mem.courseEdition.CourseEditionRepositoryImpl;
import PAI.persistence.mem.courseInStudyPlan.CourseInStudyPlanListFactoryImpl;
import PAI.persistence.mem.courseInStudyPlan.CourseInStudyPlanRepositoryImpl;
import PAI.persistence.mem.programme.IProgrammeRepositoryListFactory;
import PAI.persistence.mem.programme.ProgrammeRepositoryImpl;
import PAI.persistence.mem.programme.ProgrammeRepositoryListFactoryImpl;
import PAI.persistence.mem.programmeEdition.IProgrammeEditionListFactory;
import PAI.persistence.mem.programmeEdition.ProgrammeEditionListFactoryImpl;
import PAI.persistence.mem.programmeEdition.ProgrammeEditionRepositoryImpl;
import PAI.domain.repositoryInterfaces.courseEditionEnrolment.ICourseEditionEnrolmentRepository;
import PAI.domain.repositoryInterfaces.programmeEdition.IProgrammeEditionRepository;
import PAI.domain.repositoryInterfaces.programmeEnrolment.IProgrammeEnrolmentRepository;
import PAI.persistence.mem.programmeEnrolment.ProgrammeEnrolmentRepositoryImpl;
import PAI.domain.repositoryInterfaces.programme.IProgrammeRepository;
import PAI.domain.repositoryInterfaces.schoolYear.ISchoolYearRepository;
import PAI.service.courseEditionEnrolment.CourseEditionEnrolmentServiceImpl;
import PAI.service.courseEditionEnrolment.ICourseEditionEnrolmentService;
import PAI.service.programmeEditionEnrolment.IProgrammeEditionEnrolmentService;
import PAI.service.programmeEditionEnrolment.ProgrammeEditionEnrolmentServiceImpl;
import PAI.service.courseInStudyPlan.CourseInStudyPlanServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US16_EnrolAStudentInACourseEditionControllerTest {

    private CourseEditionEnrolmentGeneratedID _ceeGeneratedIDDouble;
    private StudentID _studentIDDouble;
    private CourseEditionID _ceIDDouble;
    private Date _enrolmentDateDouble;
    private EnrolmentStatus _statusDouble;

    void createDoubles() {
        _ceeGeneratedIDDouble = mock(CourseEditionEnrolmentGeneratedID.class);
        _studentIDDouble = mock(StudentID.class);
        _ceIDDouble = mock(CourseEditionID.class);
        _enrolmentDateDouble = mock(Date.class);
        _statusDouble = mock(EnrolmentStatus.class);
    }

    //testing constructor of US16 controller

    @Test
    void shouldReturnExceptionIfCourseEditionEnrollmentRepositoryIsNull() {
        //arrange

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new US16_EnrolAStudentInACourseEditionController(null);
        });

        //assert
        assertEquals("Course edition enrolment service interface cannot be null!", exception.getMessage());
    }

    //testing find Programme Editions that Student is Enrolled Method

    @Test
    void shouldReturnEmptyListWhenStudentIsNull() {
        //arrange
        ICourseEditionEnrolmentService doubleCeeServiceInterface = mock(ICourseEditionEnrolmentService.class);

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(
                doubleCeeServiceInterface);

        //act
        List<ProgrammeEditionID> result = controller.findProgrammeEditionIDsThatStudentIsEnrolled(null);

        //assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnAListOfProgrammeEditionsThatStudentIsEnrolled() {
        //arrange
        ICourseEditionEnrolmentService doubleCeeServiceInterface = mock(ICourseEditionEnrolmentService.class);

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(
                doubleCeeServiceInterface);

        StudentID doubleStudentId = mock(StudentID.class);
        ProgrammeEditionID doublePee1Id = mock(ProgrammeEditionID.class);
        ProgrammeEditionID doublePee2Id = mock(ProgrammeEditionID.class);
        ProgrammeEditionID doublePee3Id = mock(ProgrammeEditionID.class);


        when(doubleCeeServiceInterface.findProgrammeEditionIDsThatStudentIsEnrolled(doubleStudentId)).
                thenReturn(List.of(doublePee1Id, doublePee2Id, doublePee3Id));

        //act
        List<ProgrammeEditionID> result = controller.findProgrammeEditionIDsThatStudentIsEnrolled(doubleStudentId);

        //assert
        assertEquals(3, result.size());
    }

    @Test
    void shouldReturnAnEmptyListWhenStudentIsNotEnrolledInAnyProgrammeEdition() {
        //arrange
        ICourseEditionEnrolmentService doubleCeeServiceInterface = mock(ICourseEditionEnrolmentService.class);

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(
                doubleCeeServiceInterface);

        StudentID doubleStudentId = mock(StudentID.class);

        when(doubleCeeServiceInterface.findProgrammeEditionIDsThatStudentIsEnrolled(doubleStudentId)).
                thenReturn(List.of());

        //act
        List<ProgrammeEditionID> result = controller.findProgrammeEditionIDsThatStudentIsEnrolled(doubleStudentId);

        //assert
        assertEquals(0, result.size());
    }

    //testing find Course Editions by Programme Edition Method

    @Test
    void shouldReturnAListOfCourseEditionsThatBelongsToAProgrammeEdition() {
        //arrange
        ICourseEditionEnrolmentService doubleCeeServiceInterface = mock(ICourseEditionEnrolmentService.class);

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(
                doubleCeeServiceInterface);

        ProgrammeEditionID doubleProgrammeEditionID = mock(ProgrammeEditionID.class);
        CourseEditionID doubleCourseEdition1 = mock(CourseEditionID.class);
        CourseEditionID doubleCourseEdition2 = mock(CourseEditionID.class);

        when(doubleCeeServiceInterface.findCourseEditionIDsByProgrammeEdition(doubleProgrammeEditionID)).thenReturn(List.of(doubleCourseEdition1, doubleCourseEdition2));

        //act
        List<CourseEditionID> result = controller.findCourseEditionIDsByProgrammeEdition(doubleProgrammeEditionID);

        //assert
        assertEquals(2, result.size());
    }

    //testing enroll a student in a course edition method

    @Test
    void shouldReturnTrueIfIsAValidCourseEditionEnrollment() {
        //arrange
        ICourseEditionEnrolmentService doubleCeeServiceInterface = mock(ICourseEditionEnrolmentService.class);

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(
                doubleCeeServiceInterface);

        when(doubleCeeServiceInterface.enrolStudentInACourseEditionFromPersistence(_ceeGeneratedIDDouble, _studentIDDouble, _ceIDDouble, _enrolmentDateDouble, _statusDouble)).thenReturn(true);

        //act
        boolean result = controller.enrolStudentInCourseEdition(_ceeGeneratedIDDouble, _studentIDDouble, _ceIDDouble, _enrolmentDateDouble, _statusDouble);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueWhenDifferentStudentsEnrollInSameCourseEdition() {
        //arrange
        ICourseEditionEnrolmentService doubleCeeServiceInterface = mock(ICourseEditionEnrolmentService.class);

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(
                doubleCeeServiceInterface);

        StudentID doubleStudentID1 = mock(StudentID.class);
        CourseEditionID doubleCEID = mock(CourseEditionID.class);
        StudentID doubleStudentID2 = mock(StudentID.class);

        when(doubleCeeServiceInterface.enrolStudentInACourseEditionFromPersistence(_ceeGeneratedIDDouble, doubleStudentID1, doubleCEID, _enrolmentDateDouble, _statusDouble)).thenReturn(true);

        controller.enrolStudentInCourseEdition(_ceeGeneratedIDDouble, doubleStudentID1, doubleCEID, _enrolmentDateDouble, _statusDouble);

        when(doubleCeeServiceInterface.enrolStudentInACourseEditionFromPersistence(_ceeGeneratedIDDouble, doubleStudentID2, doubleCEID, _enrolmentDateDouble, _statusDouble)).thenReturn(true);

        //act
        boolean result = controller.enrolStudentInCourseEdition(_ceeGeneratedIDDouble, doubleStudentID2, doubleCEID, _enrolmentDateDouble, _statusDouble);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueWhenDifferentStudentsEnrollInDifferentCourseEditions() {
        //arrange
        ICourseEditionEnrolmentService doubleCeeServiceInterface = mock(ICourseEditionEnrolmentService.class);

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(
                doubleCeeServiceInterface);

        StudentID doubleStudentID1 = mock(StudentID.class);
        CourseEditionID doubleCEID1 = mock(CourseEditionID.class);

        StudentID doubleStudentID2 = mock(StudentID.class);
        CourseEditionID doubleCEID2 = mock(CourseEditionID.class);

        when(doubleCeeServiceInterface.enrolStudentInACourseEditionFromPersistence(_ceeGeneratedIDDouble, doubleStudentID1, doubleCEID1, _enrolmentDateDouble, _statusDouble)).thenReturn(true);

        controller.enrolStudentInCourseEdition(_ceeGeneratedIDDouble, doubleStudentID1, doubleCEID1, _enrolmentDateDouble, _statusDouble);

        when(doubleCeeServiceInterface.enrolStudentInACourseEditionFromPersistence(_ceeGeneratedIDDouble, doubleStudentID2, doubleCEID2, _enrolmentDateDouble, _statusDouble)).thenReturn(true);

        //act
        boolean result = controller.enrolStudentInCourseEdition(_ceeGeneratedIDDouble, doubleStudentID2, doubleCEID2, _enrolmentDateDouble, _statusDouble);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueWhenSameStudentEnrollInDifferentCourseEditions() {
        //arrange
        ICourseEditionEnrolmentService doubleCeeServiceInterface = mock(ICourseEditionEnrolmentService.class);

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(
                doubleCeeServiceInterface);

        StudentID doubleStID1 = mock(StudentID.class);
        CourseEditionID doubleCeID1 = mock(CourseEditionID.class);

        CourseEditionID doubleCeID2 = mock(CourseEditionID.class);

        when(doubleCeeServiceInterface.enrolStudentInACourseEditionFromPersistence(_ceeGeneratedIDDouble, doubleStID1, doubleCeID1, _enrolmentDateDouble, _statusDouble)).thenReturn(true);

        controller.enrolStudentInCourseEdition(_ceeGeneratedIDDouble, doubleStID1, doubleCeID1, _enrolmentDateDouble, _statusDouble);

        when(doubleCeeServiceInterface.enrolStudentInACourseEditionFromPersistence(_ceeGeneratedIDDouble, doubleStID1, doubleCeID2, _enrolmentDateDouble, _statusDouble)).thenReturn(true);

        //act
        boolean result = controller.enrolStudentInCourseEdition(_ceeGeneratedIDDouble, doubleStID1, doubleCeID2, _enrolmentDateDouble, _statusDouble);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenStudentIsAlreadyEnrolledInCourseEdition() {
        //arrange
        ICourseEditionEnrolmentService doubleCeeServiceInterface = mock(ICourseEditionEnrolmentService.class);

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(
                doubleCeeServiceInterface);

        StudentID doubleStudentID = mock(StudentID.class);
        CourseEditionID doubleCEID = mock(CourseEditionID.class);

        when(doubleCeeServiceInterface.enrolStudentInACourseEditionFromPersistence(_ceeGeneratedIDDouble, doubleStudentID, doubleCEID, _enrolmentDateDouble, _statusDouble)).thenReturn(false);

        //act
        boolean result = controller.enrolStudentInCourseEdition(_ceeGeneratedIDDouble, doubleStudentID, doubleCEID, _enrolmentDateDouble, _statusDouble);

        //assert
        assertFalse(result);
    }

    //---------------------integration tests--------------------//

    @Test
    void shouldReturnOptionalEmptyWhenStudentIsNull_IntegrationTest() {
        // arrange
        ProgrammeEditionEnrolmentListFactoryImpl peeListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepositoryImpl peeRepository = new ProgrammeEditionEnrolmentRepositoryImpl(peeListFactory);

        CourseEditionEnrolmentFactoryImpl ceeFactory = new CourseEditionEnrolmentFactoryImpl();
        CourseEditionEnrolmentListFactoryImpl ceeListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl ceeRepository = new CourseEditionEnrolmentRepositoryImpl(ceeListFactory);

        CourseEditionFactoryImpl ceFactory = new CourseEditionFactoryImpl();
        CourseEditionListFactoryImpl ceListFactory = new CourseEditionListFactoryImpl();
        CourseEditionRepositoryImpl courseEditionRepository = new CourseEditionRepositoryImpl(ceFactory, ceListFactory);

        CourseEditionEnrolmentServiceImpl ceeService = new CourseEditionEnrolmentServiceImpl(
                ceeFactory, ceeRepository, peeRepository, courseEditionRepository
        );

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(ceeService);

        //act
        List<ProgrammeEditionID> result = controller.findProgrammeEditionIDsThatStudentIsEnrolled(null);

        //assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnAListOfProgrammeEditionsThatStudentIsEnrolled_IntegrationTest() throws Exception {
        //arrange
        ProgrammeEditionEnrolmentListFactoryImpl peeListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepositoryImpl peeRepository = new ProgrammeEditionEnrolmentRepositoryImpl(peeListFactory);

        CourseEditionEnrolmentFactoryImpl ceeFactory = new CourseEditionEnrolmentFactoryImpl();
        CourseEditionEnrolmentListFactoryImpl ceeListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl ceeRepository = new CourseEditionEnrolmentRepositoryImpl(ceeListFactory);

        CourseEditionFactoryImpl ceFactory = new CourseEditionFactoryImpl();
        CourseEditionListFactoryImpl ceListFactory = new CourseEditionListFactoryImpl();
        CourseEditionRepositoryImpl courseEditionRepository = new CourseEditionRepositoryImpl(ceFactory, ceListFactory);

        CourseEditionEnrolmentServiceImpl ceeService = new CourseEditionEnrolmentServiceImpl(
                ceeFactory, ceeRepository, peeRepository, courseEditionRepository
        );

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(ceeService);

        StudentID studentID = mock(StudentID.class);

        SchoolYearID schoolYearID2 = new SchoolYearID();

        Acronym acronym1 = new Acronym("P1");
        ProgrammeID programmeID1 = new ProgrammeID(acronym1);
        ProgrammeEditionID programmeEditionID1 = new ProgrammeEditionID(programmeID1, schoolYearID2);

        Acronym acronym2 = new Acronym("P2");
        ProgrammeID programmeID2 = new ProgrammeID(acronym2);
        ProgrammeEditionID programmeEditionID2 = new ProgrammeEditionID(programmeID2, schoolYearID2);

        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionListFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrolmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        IProgrammeRepositoryListFactory programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeRepositoryListFactory);
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentListFactory);
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearListFactoryImpl);
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentList);

        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = new ProgrammeEditionEnrolmentServiceImpl(
                peeRepository,
                programmeEditionRepository,
                courseEditionEnrolmentRepositoryImpl,
                courseEditionRepository,
                schoolYearRepository,
                programmeEnrolmentRepository,
                programmeRepository,
                programmeEditionEnrolmentFactory);

        programmeEditionEnrolmentService.enrolStudentInProgrammeEdition(studentID, programmeEditionID1);
        programmeEditionEnrolmentService.enrolStudentInProgrammeEdition(studentID, programmeEditionID2);

        //act
        List<ProgrammeEditionID> result = controller.findProgrammeEditionIDsThatStudentIsEnrolled(studentID);

        //assert
        assertEquals(2, result.size());
    }

    //testing find Course Editions by Programme Edition Method

    @Test
    void shouldReturnAListOfCourseEditionsThatBelongsToAProgrammeEdition_IntegrationTest() throws Exception {
        //arrange
        ProgrammeEditionEnrolmentListFactoryImpl peeListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepositoryImpl peeRepository = new ProgrammeEditionEnrolmentRepositoryImpl(peeListFactory);

        CourseEditionEnrolmentFactoryImpl ceeFactory = new CourseEditionEnrolmentFactoryImpl();
        CourseEditionEnrolmentListFactoryImpl ceeListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl ceeRepository = new CourseEditionEnrolmentRepositoryImpl(ceeListFactory);

        CourseEditionFactoryImpl ceFactory = new CourseEditionFactoryImpl();
        CourseEditionListFactoryImpl ceListFactory = new CourseEditionListFactoryImpl();
        CourseEditionRepositoryImpl courseEditionRepository = new CourseEditionRepositoryImpl(ceFactory, ceListFactory);

        CourseEditionEnrolmentServiceImpl ceeService = new CourseEditionEnrolmentServiceImpl(
                ceeFactory, ceeRepository, peeRepository, courseEditionRepository
        );

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(ceeService);

        Acronym acronym1 = new Acronym("P1");
        ProgrammeID programmeID1 = new ProgrammeID(acronym1);
        SchoolYearID schoolYearID1 = new SchoolYearID();
        ProgrammeEditionID programmeEditionID1 = new ProgrammeEditionID(programmeID1, schoolYearID1);

        Date date1 = new Date ("12-12-2023");
        Date date2 = new Date ("12-12-2024");
        StudyPlanID studyPlanID1 = new StudyPlanID(programmeID1,date1);
        StudyPlanID studyPlanID2 = new StudyPlanID(programmeID1,date2);

        Acronym acronym2 = new Acronym("DSOFT");
        Name name2 = new Name("Software Development");
        CourseID courseID2 = new CourseID(acronym2, name2);
        Acronym acronym3 = new Acronym("LABPROJ");
        Name name3 = new Name("Project Lab");
        CourseID courseID1 = new CourseID(acronym3, name3);

        CourseInStudyPlanID courseInStudyPlanID1 = new CourseInStudyPlanID (courseID1,studyPlanID1);
        CourseInStudyPlanID courseInStudyPlanID2 = new CourseInStudyPlanID (courseID2,studyPlanID2);

        CourseInStudyPlanFactoryImpl factory = new CourseInStudyPlanFactoryImpl();
        CourseInStudyPlanListFactoryImpl listFactory = new CourseInStudyPlanListFactoryImpl();
        CourseInStudyPlanRepositoryImpl repo = new CourseInStudyPlanRepositoryImpl(listFactory);
        CourseInStudyPlanServiceAssemblerImpl assembler = new CourseInStudyPlanServiceAssemblerImpl();
        CourseInStudyPlanServiceImpl courseInStudyPlanServiceInterface = new CourseInStudyPlanServiceImpl(repo, factory, assembler);

        Semester semester = new Semester(1);
        CurricularYear curricularYear = new CurricularYear(1);
        DurationCourseInCurricularYear durationOfCourse = new DurationCourseInCurricularYear(1);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = new CourseQuantityCreditsEcts(1);

        courseInStudyPlanServiceInterface.createCourseInStudyPlan(semester,curricularYear,courseID1,studyPlanID1, durationOfCourse, quantityOfCreditsEcts);
        courseInStudyPlanServiceInterface.createCourseInStudyPlan(semester,curricularYear,courseID2,studyPlanID1, durationOfCourse, quantityOfCreditsEcts);

        courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanID1,programmeEditionID1);
        courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanID2,programmeEditionID1);

        //act
        List<CourseEditionID> result = controller.findCourseEditionIDsByProgrammeEdition(programmeEditionID1);

        //assert
        assertEquals (2, result.size());
    }

    //testing enroll a student in a course edition

    @Test
    void shouldReturnTrueWhenDifferentStudentsEnrollInSameCourseEdition_IntegrationTest () throws Exception {
        //arrange
        ProgrammeEditionEnrolmentListFactoryImpl peeListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepositoryImpl peeRepository = new ProgrammeEditionEnrolmentRepositoryImpl(peeListFactory);

        CourseEditionEnrolmentFactoryImpl ceeFactory = new CourseEditionEnrolmentFactoryImpl();
        CourseEditionEnrolmentListFactoryImpl ceeListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl ceeRepository = new CourseEditionEnrolmentRepositoryImpl(ceeListFactory);

        CourseEditionFactoryImpl ceFactory = new CourseEditionFactoryImpl();
        CourseEditionListFactoryImpl ceListFactory = new CourseEditionListFactoryImpl();
        CourseEditionRepositoryImpl courseEditionRepository = new CourseEditionRepositoryImpl(ceFactory, ceListFactory);

        CourseEditionEnrolmentServiceImpl ceeService = new CourseEditionEnrolmentServiceImpl(
                ceeFactory, ceeRepository, peeRepository, courseEditionRepository
        );

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(ceeService);

        CourseEditionEnrolmentGeneratedID ceeGeneratedID = new CourseEditionEnrolmentGeneratedID();
        StudentID studentID = mock(StudentID.class);
        StudentID studentID2 = mock(StudentID.class);
        Date enrolmentDate = new Date("15-07-2016");
        EnrolmentStatus status = new EnrolmentStatus(true);

        Acronym acronym1 = new Acronym("P1");
        ProgrammeID programmeID1 = new ProgrammeID(acronym1);
        SchoolYearID schoolYearID1 = new SchoolYearID();
        ProgrammeEditionID programmeEditionID1 = new ProgrammeEditionID(programmeID1, schoolYearID1);

        Date date1 = new Date ("12-12-2023");
        StudyPlanID studyPlanID1 = new StudyPlanID(programmeID1,date1);

        Acronym acronym = new Acronym("DSOFT");
        Name name = new Name("Software Development");
        CourseID courseID1 = new CourseID(acronym, name);

        CourseInStudyPlanID courseInStudyPlanID1 = new CourseInStudyPlanID (courseID1,studyPlanID1);

        CourseInStudyPlanFactoryImpl factory = new CourseInStudyPlanFactoryImpl();
        CourseInStudyPlanListFactoryImpl listFactory = new CourseInStudyPlanListFactoryImpl();
        CourseInStudyPlanRepositoryImpl repo = new CourseInStudyPlanRepositoryImpl(listFactory);
        CourseInStudyPlanServiceAssemblerImpl assembler = new CourseInStudyPlanServiceAssemblerImpl();
        CourseInStudyPlanServiceImpl courseInStudyPlanServiceInterface = new CourseInStudyPlanServiceImpl(repo, factory, assembler);

        Semester semester = new Semester(1);
        CurricularYear curricularYear = new CurricularYear(1);
        DurationCourseInCurricularYear durationOfCourse = new DurationCourseInCurricularYear(1);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = new CourseQuantityCreditsEcts(1);

        courseInStudyPlanServiceInterface.createCourseInStudyPlan(semester,curricularYear,courseID1,studyPlanID1, durationOfCourse, quantityOfCreditsEcts);

        CourseEditionID ceID1 = new CourseEditionID(programmeEditionID1, courseInStudyPlanID1);

        courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanID1,programmeEditionID1);

        CourseEditionEnrolment cee = new CourseEditionEnrolment(studentID,ceID1);

        ceeRepository.save(cee);

        //act
        boolean result = controller.enrolStudentInCourseEdition(ceeGeneratedID, studentID2,ceID1, enrolmentDate, status);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueWhenSameStudentEnrollInDifferentCourseEditions_IntegrationTest () throws Exception {
        //arrange
        ProgrammeEditionEnrolmentListFactoryImpl peeListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepositoryImpl peeRepository = new ProgrammeEditionEnrolmentRepositoryImpl(peeListFactory);

        CourseEditionEnrolmentFactoryImpl ceeFactory = new CourseEditionEnrolmentFactoryImpl();
        CourseEditionEnrolmentListFactoryImpl ceeListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl ceeRepository = new CourseEditionEnrolmentRepositoryImpl(ceeListFactory);

        CourseEditionFactoryImpl ceFactory = new CourseEditionFactoryImpl();
        CourseEditionListFactoryImpl ceListFactory = new CourseEditionListFactoryImpl();
        CourseEditionRepositoryImpl courseEditionRepository = new CourseEditionRepositoryImpl(ceFactory, ceListFactory);

        CourseEditionEnrolmentServiceImpl ceeService = new CourseEditionEnrolmentServiceImpl(
                ceeFactory, ceeRepository, peeRepository, courseEditionRepository
        );

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(ceeService);

        CourseEditionEnrolmentGeneratedID ceeGeneratedID = new CourseEditionEnrolmentGeneratedID();
        StudentID studentID = mock(StudentID.class);
        Date enrolmentDate = new Date("15-07-2016");
        EnrolmentStatus status = new EnrolmentStatus(true);

        Acronym acronym1 = new Acronym("P1");
        ProgrammeID programmeID1 = new ProgrammeID(acronym1);
        SchoolYearID schoolYearID1 = new SchoolYearID();
        ProgrammeEditionID programmeEditionID1 = new ProgrammeEditionID(programmeID1, schoolYearID1);

        Date date1 = new Date ("12-12-2023");
        StudyPlanID studyPlanID1 = new StudyPlanID(programmeID1,date1);
        Acronym acronym = new Acronym("DSOFT");
        Name name = new Name("Software Development");
        CourseID courseID1 = new CourseID(acronym, name);
        CourseInStudyPlanID courseInStudyPlanID1 = new CourseInStudyPlanID (courseID1,studyPlanID1);

        CourseEditionID ceID1 = new CourseEditionID(programmeEditionID1, courseInStudyPlanID1);

        Date date2 = new Date ("12-12-2024");
        StudyPlanID studyPlanID2 = new StudyPlanID(programmeID1,date2);
        Acronym acronym2 = new Acronym("LABPROJ");
        Name name2 = new Name("Project Lab");
        CourseID courseID2 = new CourseID(acronym2, name2);
        CourseInStudyPlanID courseInStudyPlanID2 = new CourseInStudyPlanID (courseID2,studyPlanID2);

        CourseInStudyPlanFactoryImpl factory = new CourseInStudyPlanFactoryImpl();
        CourseInStudyPlanListFactoryImpl listFactory = new CourseInStudyPlanListFactoryImpl();
        CourseInStudyPlanRepositoryImpl repo = new CourseInStudyPlanRepositoryImpl(listFactory);
        CourseInStudyPlanServiceAssemblerImpl assembler = new CourseInStudyPlanServiceAssemblerImpl();
        CourseInStudyPlanServiceImpl courseInStudyPlanServiceInterface = new CourseInStudyPlanServiceImpl(repo, factory, assembler);

        Semester semester = new Semester(1);
        CurricularYear curricularYear = new CurricularYear(1);
        DurationCourseInCurricularYear durationOfCourse = new DurationCourseInCurricularYear(1);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = new CourseQuantityCreditsEcts(1);

        courseInStudyPlanServiceInterface.createCourseInStudyPlan(semester,curricularYear,courseID1,studyPlanID1,
                durationOfCourse, quantityOfCreditsEcts);

        CourseEditionID ceID2 = new CourseEditionID(programmeEditionID1, courseInStudyPlanID2);

        courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanID1,programmeEditionID1);

        CourseEditionEnrolment cee = new CourseEditionEnrolment(studentID,ceID1);

        ceeRepository.save(cee);

        //act
        boolean result = controller.enrolStudentInCourseEdition(ceeGeneratedID, studentID,ceID2, enrolmentDate, status);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueWhenDifferentStudentsEnrollInDifferentCourseEditions_IntegrationTest () throws Exception {
        //arrange
        ProgrammeEditionEnrolmentListFactoryImpl peeListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepositoryImpl peeRepository = new ProgrammeEditionEnrolmentRepositoryImpl(peeListFactory);

        CourseEditionEnrolmentFactoryImpl ceeFactory = new CourseEditionEnrolmentFactoryImpl();
        CourseEditionEnrolmentListFactoryImpl ceeListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl ceeRepository = new CourseEditionEnrolmentRepositoryImpl(ceeListFactory);

        CourseEditionFactoryImpl ceFactory = new CourseEditionFactoryImpl();
        CourseEditionListFactoryImpl ceListFactory = new CourseEditionListFactoryImpl();
        CourseEditionRepositoryImpl courseEditionRepository = new CourseEditionRepositoryImpl(ceFactory, ceListFactory);

        CourseEditionEnrolmentServiceImpl ceeService = new CourseEditionEnrolmentServiceImpl(
                ceeFactory, ceeRepository, peeRepository, courseEditionRepository
        );

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(ceeService);

        CourseEditionEnrolmentGeneratedID ceeGeneratedID = new CourseEditionEnrolmentGeneratedID();
        StudentID studentID = mock(StudentID.class);
        StudentID studentID2 = mock(StudentID.class);
        Date enrolmentDate = new Date("15-07-2016");
        EnrolmentStatus status = new EnrolmentStatus(true);

        Acronym acronym1 = new Acronym("P1");
        ProgrammeID programmeID1 = new ProgrammeID(acronym1);
        SchoolYearID schoolYearID1 = new SchoolYearID();
        ProgrammeEditionID programmeEditionID1 = new ProgrammeEditionID(programmeID1, schoolYearID1);

        Date date1 = new Date ("12-12-2023");
        StudyPlanID studyPlanID1 = new StudyPlanID(programmeID1,date1);
        Acronym acronym3 = new Acronym("LABPROJ");
        Name name3 = new Name("Project Lab");
        CourseID courseID1 = new CourseID(acronym3, name3);
        CourseInStudyPlanID courseInStudyPlanID1 = new CourseInStudyPlanID (courseID1,studyPlanID1);

        CourseEditionID ceID1 = new CourseEditionID(programmeEditionID1, courseInStudyPlanID1);

        Date date2 = new Date ("12-12-2024");
        StudyPlanID studyPlanID2 = new StudyPlanID(programmeID1,date2);
        Acronym acronym2 = new Acronym("DSOFT");
        Name name2 = new Name("Software Development");
        CourseID courseID2 = new CourseID(acronym2, name2);

        CourseInStudyPlanID courseInStudyPlanID2 = new CourseInStudyPlanID (courseID2,studyPlanID2);

        CourseInStudyPlanFactoryImpl factory = new CourseInStudyPlanFactoryImpl();
        CourseInStudyPlanListFactoryImpl listFactory = new CourseInStudyPlanListFactoryImpl();
        CourseInStudyPlanRepositoryImpl repo = new CourseInStudyPlanRepositoryImpl(listFactory);
        CourseInStudyPlanServiceAssemblerImpl assembler = new CourseInStudyPlanServiceAssemblerImpl();
        CourseInStudyPlanServiceImpl courseInStudyPlanServiceInterface = new CourseInStudyPlanServiceImpl(repo, factory, assembler);

        Semester semester = new Semester(1);
        CurricularYear curricularYear = new CurricularYear(1);
        DurationCourseInCurricularYear durationOfCourse = new DurationCourseInCurricularYear(1);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = new CourseQuantityCreditsEcts(1);

        courseInStudyPlanServiceInterface.createCourseInStudyPlan(semester,curricularYear,courseID1,studyPlanID1, durationOfCourse, quantityOfCreditsEcts);

        CourseEditionID ceID2 = new CourseEditionID(programmeEditionID1, courseInStudyPlanID2);

        courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanID1,programmeEditionID1);

        CourseEditionEnrolment cee = new CourseEditionEnrolment(studentID,ceID1);

        ceeRepository.save(cee);

        //act
        boolean result = controller.enrolStudentInCourseEdition(ceeGeneratedID, studentID2,ceID2, enrolmentDate, status);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfStudentIsAlreadyEnrolledInCourseEdition_IntegrationTest () throws Exception {
        //arrange
        ProgrammeEditionEnrolmentListFactoryImpl peeListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepositoryImpl peeRepository = new ProgrammeEditionEnrolmentRepositoryImpl(peeListFactory);

        CourseEditionEnrolmentFactoryImpl ceeFactory = new CourseEditionEnrolmentFactoryImpl();
        CourseEditionEnrolmentListFactoryImpl ceeListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl ceeRepository = new CourseEditionEnrolmentRepositoryImpl(ceeListFactory);

        CourseEditionFactoryImpl ceFactory = new CourseEditionFactoryImpl();
        CourseEditionListFactoryImpl ceListFactory = new CourseEditionListFactoryImpl();
        CourseEditionRepositoryImpl courseEditionRepository = new CourseEditionRepositoryImpl(ceFactory, ceListFactory);

        CourseEditionEnrolmentServiceImpl ceeService = new CourseEditionEnrolmentServiceImpl(
                ceeFactory, ceeRepository, peeRepository, courseEditionRepository
        );

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(ceeService);

        CourseEditionEnrolmentGeneratedID ceeGeneratedID = new CourseEditionEnrolmentGeneratedID();
        StudentID studentID = mock(StudentID.class);
        Date enrolmentDate = new Date("15-07-2016");
        EnrolmentStatus status = new EnrolmentStatus(true);

        Acronym acronym1 = new Acronym("P1");
        ProgrammeID programmeID1 = new ProgrammeID(acronym1);
        SchoolYearID schoolYearID1 = new SchoolYearID();
        ProgrammeEditionID programmeEditionID1 = new ProgrammeEditionID(programmeID1, schoolYearID1);

        Date date1 = new Date ("12-12-2023");
        StudyPlanID studyPlanID1 = new StudyPlanID(programmeID1,date1);

        Acronym acronym = new Acronym("DSOFT");
        Name name = new Name("Software Development");
        CourseID courseID1 = new CourseID(acronym, name);

        CourseInStudyPlanID courseInStudyPlanID1 = new CourseInStudyPlanID (courseID1,studyPlanID1);
        CourseEditionID ceID1 = new CourseEditionID(programmeEditionID1, courseInStudyPlanID1);

        CourseInStudyPlanFactoryImpl factory = new CourseInStudyPlanFactoryImpl();
        CourseInStudyPlanListFactoryImpl listFactory = new CourseInStudyPlanListFactoryImpl();
        CourseInStudyPlanRepositoryImpl repo = new CourseInStudyPlanRepositoryImpl(listFactory);
        CourseInStudyPlanServiceAssemblerImpl assembler = new CourseInStudyPlanServiceAssemblerImpl();
        CourseInStudyPlanServiceImpl courseInStudyPlanServiceInterface = new CourseInStudyPlanServiceImpl(repo, factory, assembler);

        Semester semester = new Semester(1);
        CurricularYear curricularYear = new CurricularYear(1);
        DurationCourseInCurricularYear durationOfCourse = new DurationCourseInCurricularYear(1);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = new CourseQuantityCreditsEcts(1);

        courseInStudyPlanServiceInterface.createCourseInStudyPlan(semester,curricularYear,courseID1,studyPlanID1, durationOfCourse, quantityOfCreditsEcts);

        courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanID1,programmeEditionID1);

        CourseEditionEnrolment cee = new CourseEditionEnrolment(studentID,ceID1);

        ceeRepository.save(cee);

        //act
        boolean result = controller.enrolStudentInCourseEdition(ceeGeneratedID, studentID,ceID1, enrolmentDate, status);

        //assert
        assertFalse(result);
    }
}