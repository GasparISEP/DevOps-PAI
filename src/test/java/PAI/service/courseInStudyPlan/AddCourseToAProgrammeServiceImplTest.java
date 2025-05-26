package PAI.service.courseInStudyPlan;

import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.StudyPlanID;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.courseInStudyPlan.ICourseInStudyPlanFactory;
import PAI.domain.repositoryInterfaces.courseInStudyPlan.ICourseInStudyPlanRepository;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanCommand;
import PAI.exception.BusinessRuleViolationException;
import PAI.service.studyPlan.IStudyPlanService;
import PAI.service.studyPlan.StudyPlanServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AddCourseToAProgrammeServiceImplTest {

    private IStudyPlanService studyPlanService;
    private ICourseInStudyPlanRepository repository;
    private ICourseInStudyPlanFactory factory;
    private AddCourseToAProgrammeServiceImpl service;

    @BeforeEach
    void setUp() {
        studyPlanService = mock(IStudyPlanService.class);
        repository = mock(ICourseInStudyPlanRepository.class);
        factory = mock(ICourseInStudyPlanFactory.class);
        service = new AddCourseToAProgrammeServiceImpl(studyPlanService, repository, factory);
    }

    @Test
    void shouldAddCourseToAProgrammeSuccessfully() throws Exception {
        CourseInStudyPlanCommand command = mock(CourseInStudyPlanCommand.class);
        when(command.programmeName()).thenReturn("Programme");
        when(command.programmeAcronym()).thenReturn("PRG");
        when(command.semester()).thenReturn(1);
        when(command.curricularYear()).thenReturn(1);
        when(command.courseAcronym()).thenReturn("CSE");
        when(command.courseName()).thenReturn("Course");
        when(command.credits()).thenReturn(6.0);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        when(studyPlanService.getLatestStudyPlanIDByProgrammeID(any())).thenReturn(studyPlanID);
        when(studyPlanID.getProgrammeID()).thenReturn(programmeID);
        when(studyPlanService.getLatestStudyPlanIDByProgrammeID(programmeID)).thenReturn(studyPlanID);

        CourseInStudyPlan courseInStudyPlan = mock(CourseInStudyPlan.class);
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        when(factory.newCourseInStudyPlan(any(), any(), any(), any(), any(), any())).thenReturn(courseInStudyPlan);
        when(courseInStudyPlan.identity()).thenReturn(courseInStudyPlanID);
        when(repository.containsOfIdentity(courseInStudyPlanID)).thenReturn(false);
        when(repository.getTotalCreditsEctsInStudyPlanSoFar(any(), any(), any(), any())).thenReturn(24.0);
        when(repository.save(courseInStudyPlan)).thenReturn(courseInStudyPlan);

        CourseInStudyPlan result = service.addCourseToAProgramme(command);

        assertNotNull(result);
        assertEquals(courseInStudyPlan, result);
    }

    @Test
    void shouldThrowExceptionIfCommandIsNull() {
        assertThrows(IllegalArgumentException.class, () -> service.addCourseToAProgramme(null));
    }

    @Test
    void shouldThrowExceptionIfNoStudyPlanFound() {
        CourseInStudyPlanCommand command = mock(CourseInStudyPlanCommand.class);
        when(command.programmeName()).thenReturn("Programme");
        when(command.programmeAcronym()).thenReturn("PRG");
        when(command.semester()).thenReturn(1);
        when(command.curricularYear()).thenReturn(1);
        when(command.courseAcronym()).thenReturn("CSE");
        when(command.courseName()).thenReturn("Course");
        when(command.credits()).thenReturn(6.0);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        when(studyPlanService.getLatestStudyPlanIDByProgrammeID(any())).thenReturn(studyPlanID);
        when(studyPlanID.getProgrammeID()).thenReturn(programmeID);
        when(studyPlanService.getLatestStudyPlanIDByProgrammeID(programmeID)).thenReturn(null);

        assertThrows(BusinessRuleViolationException.class, () -> service.addCourseToAProgramme(command));
    }

    @Test
    void shouldThrowExceptionIfCourseIsAlreadyInProgramme() throws Exception {
        CourseInStudyPlanCommand command = mock(CourseInStudyPlanCommand.class);
        when(command.programmeName()).thenReturn("Programme");
        when(command.programmeAcronym()).thenReturn("PRG");
        when(command.semester()).thenReturn(1);
        when(command.curricularYear()).thenReturn(1);
        when(command.courseAcronym()).thenReturn("CSE");
        when(command.courseName()).thenReturn("Course");
        when(command.credits()).thenReturn(6.0);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        when(studyPlanService.getLatestStudyPlanIDByProgrammeID(any())).thenReturn(studyPlanID);
        when(studyPlanID.getProgrammeID()).thenReturn(programmeID);
        when(studyPlanService.getLatestStudyPlanIDByProgrammeID(programmeID)).thenReturn(studyPlanID);

        CourseInStudyPlan courseInStudyPlan = mock(CourseInStudyPlan.class);
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        when(factory.newCourseInStudyPlan(any(), any(), any(), any(), any(), any())).thenReturn(courseInStudyPlan);
        when(courseInStudyPlan.identity()).thenReturn(courseInStudyPlanID);
        when(repository.containsOfIdentity(courseInStudyPlanID)).thenReturn(true);

        assertThrows(BusinessRuleViolationException.class, () -> service.addCourseToAProgramme(command));
    }

    @Test
    void shouldThrowExceptionIfCreditsExceedLimit() throws Exception {
        CourseInStudyPlanCommand command = mock(CourseInStudyPlanCommand.class);
        when(command.programmeName()).thenReturn("Programme");
        when(command.programmeAcronym()).thenReturn("PRG");
        when(command.semester()).thenReturn(1);
        when(command.curricularYear()).thenReturn(1);
        when(command.courseAcronym()).thenReturn("CSE");
        when(command.courseName()).thenReturn("Course");
        when(command.credits()).thenReturn(10.0);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        when(studyPlanService.getLatestStudyPlanIDByProgrammeID(any())).thenReturn(studyPlanID);
        when(studyPlanID.getProgrammeID()).thenReturn(programmeID);
        when(studyPlanService.getLatestStudyPlanIDByProgrammeID(programmeID)).thenReturn(studyPlanID);

        CourseInStudyPlan courseInStudyPlan = mock(CourseInStudyPlan.class);
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        when(factory.newCourseInStudyPlan(any(), any(), any(), any(), any(), any())).thenReturn(courseInStudyPlan);
        when(courseInStudyPlan.identity()).thenReturn(courseInStudyPlanID);
        when(repository.containsOfIdentity(courseInStudyPlanID)).thenReturn(false);
        when(repository.getTotalCreditsEctsInStudyPlanSoFar(any(), any(), any(), any())).thenReturn(25.0);

        assertThrows(BusinessRuleViolationException.class, () -> service.addCourseToAProgramme(command));
    }
}