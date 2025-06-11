package PAI.service.student;

import PAI.VOs.*;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.domain.courseEditionEnrolment.ICourseEditionEnrolmentFactory;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.programmeEditionEnrolment.IProgrammeEditionEnrolmentFactory;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolment;
import PAI.domain.repositoryInterfaces.courseEdition.ICourseEditionRepository;
import PAI.domain.repositoryInterfaces.courseEditionEnrolment.ICourseEditionEnrolmentRepository;
import PAI.domain.repositoryInterfaces.programmeEditionEnrolment.IProgrammeEditionEnrolmentRepository;
import PAI.service.programmeEnrolment.IAvailableCoursesService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeAndCoursesEnrolmentServiceImplTest {
    @Test
    void shouldReturnConstructor() {

        //arrange
        IProgrammeEditionEnrolmentFactory enrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentRepository enrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentFactory courseEditionEnrolmentFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);

        ProgrammeAndCoursesEnrolmentServiceImpl service = new ProgrammeAndCoursesEnrolmentServiceImpl(enrolmentFactory, enrolmentRepository, courseEditionEnrolmentFactory,
                courseEditionEnrolmentRepository, availableCoursesService, courseEditionRepository);

        //assert
        assertNotNull(service);

    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionEnrolmentIsNull() {

        //arrange
        IProgrammeEditionEnrolmentFactory enrolmentFactory = null;
        IProgrammeEditionEnrolmentRepository enrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentFactory courseEditionEnrolmentFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);

        //assert
        assertThrows(IllegalArgumentException.class,() -> new ProgrammeAndCoursesEnrolmentServiceImpl(enrolmentFactory, enrolmentRepository, courseEditionEnrolmentFactory,
                courseEditionEnrolmentRepository, availableCoursesService, courseEditionRepository));
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionEnrolmentRepositoryIsNull() {

        //arrange
        IProgrammeEditionEnrolmentFactory enrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentRepository enrolmentRepository = null;
        ICourseEditionEnrolmentFactory courseEditionEnrolmentFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);

        //assert
        assertThrows(IllegalArgumentException.class,() -> new ProgrammeAndCoursesEnrolmentServiceImpl(enrolmentFactory, enrolmentRepository, courseEditionEnrolmentFactory,
                courseEditionEnrolmentRepository, availableCoursesService, courseEditionRepository));
    }

    @Test
    void shouldThrowExceptionWhenCourseEditionEnrolmentFactoryIsNull() {

        //arrange
        IProgrammeEditionEnrolmentFactory enrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentRepository enrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentFactory courseEditionEnrolmentFactory = null;
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);

        //assert
        assertThrows(IllegalArgumentException.class,() -> new ProgrammeAndCoursesEnrolmentServiceImpl(enrolmentFactory, enrolmentRepository, courseEditionEnrolmentFactory,
                courseEditionEnrolmentRepository, availableCoursesService, courseEditionRepository));
    }

    @Test
    void shouldThrowExceptionWhenCourseEditionEnrolmentRepositoryIsNull() {

        //arrange
        IProgrammeEditionEnrolmentFactory enrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentRepository enrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentFactory courseEditionEnrolmentFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = null;
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);

        //assert
        assertThrows(IllegalArgumentException.class,() -> new ProgrammeAndCoursesEnrolmentServiceImpl(enrolmentFactory, enrolmentRepository, courseEditionEnrolmentFactory,
                courseEditionEnrolmentRepository, availableCoursesService, courseEditionRepository));
    }

    @Test
    void shouldThrowExceptionWhenAvailableServiceIsNull() {

        //arrange
        IProgrammeEditionEnrolmentFactory enrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentRepository enrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentFactory courseEditionEnrolmentFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        IAvailableCoursesService availableCoursesService = null;
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);

        //assert
        assertThrows(IllegalArgumentException.class,() -> new ProgrammeAndCoursesEnrolmentServiceImpl(enrolmentFactory, enrolmentRepository, courseEditionEnrolmentFactory,
                courseEditionEnrolmentRepository, availableCoursesService, courseEditionRepository));
    }

    @Test
    void shouldThrowExceptionWhenCourseEditionRepositoryIsNull() {

        //arrange
        IProgrammeEditionEnrolmentFactory enrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentRepository enrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentFactory courseEditionEnrolmentFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);
        ICourseEditionRepository courseEditionRepository = null;

        //assert
        assertThrows(IllegalArgumentException.class,() -> new ProgrammeAndCoursesEnrolmentServiceImpl(enrolmentFactory, enrolmentRepository, courseEditionEnrolmentFactory,
                courseEditionEnrolmentRepository, availableCoursesService, courseEditionRepository));
    }

    @Test
    void shouldReturnProgrammeEditionEnrolment() {

        //arrange
        IProgrammeEditionEnrolmentFactory enrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentRepository enrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentFactory courseEditionEnrolmentFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);

        ProgrammeAndCoursesEnrolmentServiceImpl service = new ProgrammeAndCoursesEnrolmentServiceImpl(enrolmentFactory, enrolmentRepository, courseEditionEnrolmentFactory,
                courseEditionEnrolmentRepository, availableCoursesService, courseEditionRepository);

        StudentID studentID = mock(StudentID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);

        ProgrammeEditionEnrolment programmeEditionEnrolment = mock(ProgrammeEditionEnrolment.class);

        when(enrolmentFactory.newProgrammeEditionEnrolment(studentID,programmeEditionID)).thenReturn(programmeEditionEnrolment);

        //act

        ProgrammeEditionEnrolment result = service.createProgrammeEditionEnrolment(studentID,programmeEditionID);

        //assert
        assertNotNull(result);

    }

    @Test
    void shouldEnrollStudentInProgrammeAndCoursesSuccessfully() throws Exception {
        // arrange
        IProgrammeEditionEnrolmentFactory enrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentRepository enrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentFactory courseEditionEnrolmentFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);

        ProgrammeAndCoursesEnrolmentServiceImpl service = new ProgrammeAndCoursesEnrolmentServiceImpl(
                enrolmentFactory, enrolmentRepository, courseEditionEnrolmentFactory,
                courseEditionEnrolmentRepository, availableCoursesService, courseEditionRepository);

        StudentID studentID = mock(StudentID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        CourseID courseID = mock(CourseID.class);
        CourseInStudyPlan csp = mock(CourseInStudyPlan.class);
        CourseInStudyPlanID cspID = mock(CourseInStudyPlanID.class);
        CourseEditionID ceID = mock(CourseEditionID.class);
        CourseEditionEnrolment cee = mock(CourseEditionEnrolment.class);
        ProgrammeEditionEnrolment pee = mock(ProgrammeEditionEnrolment.class);

        when(csp.getCourseID()).thenReturn(courseID);
        when(csp.identity()).thenReturn(cspID);

        when(availableCoursesService.allCourseEditionIdsFromProgrammeEdition(programmeEditionID)).thenReturn(List.of(ceID));
        when(availableCoursesService.allCoursesInStudyFromProgrammeEdition(List.of(ceID))).thenReturn(List.of(cspID));
        when(availableCoursesService.getByIdentity(List.of(cspID))).thenReturn(List.of(csp));
        when(courseEditionRepository.findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID(programmeEditionID, cspID)).thenReturn(List.of(ceID));

        when(enrolmentFactory.newProgrammeEditionEnrolment(studentID, programmeEditionID)).thenReturn(pee);
        when(enrolmentRepository.save(pee)).thenReturn(pee);
        when(courseEditionEnrolmentFactory.createCourseEditionEnrolment(studentID, ceID)).thenReturn(cee);
        when(courseEditionEnrolmentRepository.save(cee)).thenReturn(cee);

        // act
        US34Response result = service.enrollStudentInProgrammeAndCourses(studentID, programmeEditionID, List.of(courseID));

        // assert
        assertNotNull(result);
        assertEquals(pee, result.progEditionEnrolment());
        assertEquals(1, result.listCourseEditionEnrolment().size());
        assertEquals(cee, result.listCourseEditionEnrolment().get(0));
    }


    @Test
    void shouldReturnEmptyCourseEditionListWhenCourseEditionIDsIsNull() throws Exception {
        // arrange
        IProgrammeEditionEnrolmentFactory enrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentRepository enrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentFactory courseEditionEnrolmentFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);

        ProgrammeAndCoursesEnrolmentServiceImpl service = new ProgrammeAndCoursesEnrolmentServiceImpl(
                enrolmentFactory, enrolmentRepository, courseEditionEnrolmentFactory,
                courseEditionEnrolmentRepository, availableCoursesService, courseEditionRepository);

        StudentID studentID = mock(StudentID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolment pee = mock(ProgrammeEditionEnrolment.class);
        CourseID courseID = mock(CourseID.class);

        // Mocks return empty path
        when(availableCoursesService.allCourseEditionIdsFromProgrammeEdition(programmeEditionID)).thenReturn(List.of());
        when(availableCoursesService.allCoursesInStudyFromProgrammeEdition(List.of())).thenReturn(List.of());
        when(availableCoursesService.getByIdentity(List.of())).thenReturn(List.of());
        when(enrolmentFactory.newProgrammeEditionEnrolment(studentID, programmeEditionID)).thenReturn(pee);
        when(enrolmentRepository.save(pee)).thenReturn(pee);

        // act
        US34Response result = service.enrollStudentInProgrammeAndCourses(
                studentID, programmeEditionID, List.of(courseID));

        // assert
        assertNotNull(result);
        assertEquals(pee, result.progEditionEnrolment());
        assertNotNull(result.listCourseEditionEnrolment());
        assertTrue(result.listCourseEditionEnrolment().isEmpty());
    }

    @Test
    void shouldAddCourseInStudyPlanWhenCourseIDMatches() throws Exception {
        // arrange
        IProgrammeEditionEnrolmentFactory enrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentRepository enrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentFactory courseEditionEnrolmentFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);

        ProgrammeAndCoursesEnrolmentServiceImpl service = new ProgrammeAndCoursesEnrolmentServiceImpl(
                enrolmentFactory, enrolmentRepository, courseEditionEnrolmentFactory,
                courseEditionEnrolmentRepository, availableCoursesService, courseEditionRepository);

        StudentID studentID = mock(StudentID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        CourseID courseID = mock(CourseID.class);
        CourseInStudyPlan csp = mock(CourseInStudyPlan.class);
        CourseInStudyPlanID cspID = mock(CourseInStudyPlanID.class);
        CourseEditionID ceID = mock(CourseEditionID.class);
        CourseEditionEnrolment cee = mock(CourseEditionEnrolment.class);
        ProgrammeEditionEnrolment pee = mock(ProgrammeEditionEnrolment.class);

        when(csp.getCourseID()).thenReturn(courseID);
        when(csp.identity()).thenReturn(cspID);

        when(availableCoursesService.allCourseEditionIdsFromProgrammeEdition(programmeEditionID)).thenReturn(List.of(ceID));
        when(availableCoursesService.allCoursesInStudyFromProgrammeEdition(List.of(ceID))).thenReturn(List.of(cspID));
        when(availableCoursesService.getByIdentity(List.of(cspID))).thenReturn(List.of(csp));
        when(courseEditionRepository.findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID(programmeEditionID, cspID)).thenReturn(List.of(ceID));
        when(enrolmentFactory.newProgrammeEditionEnrolment(studentID, programmeEditionID)).thenReturn(pee);
        when(enrolmentRepository.save(pee)).thenReturn(pee);
        when(courseEditionEnrolmentFactory.createCourseEditionEnrolment(studentID, ceID)).thenReturn(cee);
        when(courseEditionEnrolmentRepository.save(cee)).thenReturn(cee);

        // act
        US34Response result = service.enrollStudentInProgrammeAndCourses(studentID, programmeEditionID, List.of(courseID));

        // assert
        assertEquals(pee, result.progEditionEnrolment());
        assertEquals(1, result.listCourseEditionEnrolment().size());
        assertEquals(cee, result.listCourseEditionEnrolment().get(0));
    }

    @Test
    void shouldNotAddCourseInStudyPlanWhenCourseIDDoesNotMatch() throws Exception {
        // arrange
        IProgrammeEditionEnrolmentFactory enrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentRepository enrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentFactory courseEditionEnrolmentFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);

        ProgrammeAndCoursesEnrolmentServiceImpl service = new ProgrammeAndCoursesEnrolmentServiceImpl(
                enrolmentFactory, enrolmentRepository, courseEditionEnrolmentFactory,
                courseEditionEnrolmentRepository, availableCoursesService, courseEditionRepository);

        StudentID studentID = mock(StudentID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        CourseID requestedCourseID = mock(CourseID.class);
        CourseID differentCspCourseID = mock(CourseID.class);

        CourseInStudyPlan csp = mock(CourseInStudyPlan.class);
        when(csp.getCourseID()).thenReturn(differentCspCourseID);

        ProgrammeEditionEnrolment pee = mock(ProgrammeEditionEnrolment.class);
        when(enrolmentFactory.newProgrammeEditionEnrolment(studentID, programmeEditionID)).thenReturn(pee);
        when(enrolmentRepository.save(pee)).thenReturn(pee);

        when(availableCoursesService.allCourseEditionIdsFromProgrammeEdition(programmeEditionID)).thenReturn(List.of());
        when(availableCoursesService.allCoursesInStudyFromProgrammeEdition(List.of())).thenReturn(List.of());
        when(availableCoursesService.getByIdentity(List.of())).thenReturn(List.of(csp));

        // act
        US34Response result = service.enrollStudentInProgrammeAndCourses(
                studentID, programmeEditionID, List.of(requestedCourseID));

        // assert
        assertEquals(pee, result.progEditionEnrolment());
        assertTrue(result.listCourseEditionEnrolment().isEmpty());
    }
}