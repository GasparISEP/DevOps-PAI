package PAI.service.courseInStudyPlan;

import PAI.VOs.*;
import PAI.assembler.courseInStudyPlan.ICourseInStudyPlanBusinessAssembler;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.courseInStudyPlan.ICourseInStudyPlanFactory;
import PAI.domain.repositoryInterfaces.courseInStudyPlan.ICourseInStudyPlanRepository;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanCommand;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanServiceDTO;
import PAI.exception.BusinessRuleViolationException;
import PAI.service.studyPlan.IStudyPlanService;
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
    private ICourseInStudyPlanBusinessAssembler businessAssembler;
    private AddCourseToAProgrammeServiceImpl service;

    @BeforeEach
    void setUp() {
        studyPlanService = mock(IStudyPlanService.class);
        repository = mock(ICourseInStudyPlanRepository.class);
        factory = mock(ICourseInStudyPlanFactory.class);
        businessAssembler = mock(ICourseInStudyPlanBusinessAssembler.class);
        service = new AddCourseToAProgrammeServiceImpl(studyPlanService, repository, factory, businessAssembler);
    }

    @Test
    void shouldAddCourseToAProgrammeSuccessfully() throws Exception {
        CourseInStudyPlanCommand command = mock(CourseInStudyPlanCommand.class);
        when(command.programmeName()).thenReturn(new NameWithNumbersAndSpecialChars("Programme"));
        when(command.programmeAcronym()).thenReturn(new Acronym("PRG"));
        when(command.semester()).thenReturn(new Semester(1));
        when(command.curricularYear()).thenReturn(new CurricularYear(1));
        when(command.courseAcronym()).thenReturn(new Acronym("CSE"));
        when(command.courseName()).thenReturn(new Name("Course"));
        when(command.credits()).thenReturn(new CourseQuantityCreditsEcts(6.0));

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

        CourseInStudyPlanServiceDTO dto = mock(CourseInStudyPlanServiceDTO.class);
        when(businessAssembler.toDTO(courseInStudyPlan)).thenReturn(dto);

        CourseInStudyPlanServiceDTO result = service.addCourseToAProgramme(command);

        assertNotNull(result);
        assertEquals(dto, result);
    }

    @Test
    void shouldThrowExceptionIfCommandIsNull() {
        assertThrows(IllegalArgumentException.class, () -> service.addCourseToAProgramme(null));
    }

    @Test
    void shouldThrowExceptionIfNoStudyPlanFound() throws Exception {
        CourseInStudyPlanCommand command = mock(CourseInStudyPlanCommand.class);
        when(command.programmeName()).thenReturn(new NameWithNumbersAndSpecialChars("Programme"));
        when(command.programmeAcronym()).thenReturn(new Acronym("PRG"));
        when(command.semester()).thenReturn(new Semester(1));
        when(command.curricularYear()).thenReturn(new CurricularYear(1));
        when(command.courseAcronym()).thenReturn(new Acronym("CSE"));
        when(command.courseName()).thenReturn(new Name("Course"));
        when(command.credits()).thenReturn(new CourseQuantityCreditsEcts(6.0));

        ProgrammeID programmeID = mock(ProgrammeID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        when(studyPlanID.getProgrammeID()).thenReturn(programmeID);
        when(studyPlanService.getLatestStudyPlanIDByProgrammeID(programmeID)).thenReturn(null);

        assertThrows(BusinessRuleViolationException.class, () -> service.addCourseToAProgramme(command));
    }

    @Test
    void shouldThrowExceptionIfCourseIsAlreadyInProgramme() throws Exception {
        CourseInStudyPlanCommand command = mock(CourseInStudyPlanCommand.class);
        when(command.programmeName()).thenReturn(new NameWithNumbersAndSpecialChars("Programme"));
        when(command.programmeAcronym()).thenReturn(new Acronym("PRG"));
        when(command.semester()).thenReturn(new Semester(1));
        when(command.curricularYear()).thenReturn(new CurricularYear(1));
        when(command.courseAcronym()).thenReturn(new Acronym("CSE"));
        when(command.courseName()).thenReturn(new Name("Course"));
        when(command.credits()).thenReturn(new CourseQuantityCreditsEcts(6.0));

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
        when(command.programmeName()).thenReturn(new NameWithNumbersAndSpecialChars("Programme"));
        when(command.programmeAcronym()).thenReturn(new Acronym("PRG"));
        when(command.semester()).thenReturn(new Semester(1));
        when(command.curricularYear()).thenReturn(new CurricularYear(1));
        when(command.courseAcronym()).thenReturn(new Acronym("CSE"));
        when(command.courseName()).thenReturn(new Name("Course"));
        when(command.credits()).thenReturn(new CourseQuantityCreditsEcts(10.0));

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

    @Test
    void should_Not_CreateCourseInStudyPlan_WhenTotalCreditsExceedsLimits() throws Exception {

        // arrange
        CourseInStudyPlan candidate = mock(CourseInStudyPlan.class);

        Semester semester = new Semester(1);
        CurricularYear curricularYear = new CurricularYear(1);
        Acronym courseAcronym = new Acronym("CS101");
        Name courseName = new Name("Computer Science");
        CourseID courseID = new CourseID(courseAcronym, courseName);
        NameWithNumbersAndSpecialChars programmeName = new NameWithNumbersAndSpecialChars("Computer Science Programme");
        Acronym programmeAcronym = new Acronym("CS");
        ProgrammeID programmeID = new ProgrammeID(programmeName, programmeAcronym);
        PAI.VOs.Date studyPlanDate = new PAI.VOs.Date("01-01-2023");
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, studyPlanDate);
        DurationCourseInCurricularYear durationOfCourse = new DurationCourseInCurricularYear(1);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = new CourseQuantityCreditsEcts(5.0);

        CourseInStudyPlanCommand command = new CourseInStudyPlanCommand(
                semester,
                curricularYear,
                courseAcronym,
                courseName,
                programmeAcronym,
                programmeName,
                studyPlanDate,
                durationOfCourse,
                quantityOfCreditsEcts
        );

        when(repository.getTotalCreditsEctsInStudyPlanSoFar(studyPlanID, semester, curricularYear, durationOfCourse))
                .thenReturn(29.0);

        when(factory.newCourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, durationOfCourse, quantityOfCreditsEcts))
                .thenReturn(candidate);

        // act + assert
        assertThrows(BusinessRuleViolationException.class, () -> {
            service.addCourseToAProgramme(command);
        });
    }

    @Test
    void should_CreateCourseInStudyPlan_WhenTotalCreditsDoesNotExceedLimits() throws Exception {
        // arrange
        CourseInStudyPlan candidate = mock(CourseInStudyPlan.class);

        Semester semester = new Semester(1);
        CurricularYear curricularYear = new CurricularYear(1);
        Acronym courseAcronym = new Acronym("CS101");
        Name courseName = new Name("Computer Science");
        CourseID courseID = new CourseID(courseAcronym, courseName);
        NameWithNumbersAndSpecialChars programmeName = new NameWithNumbersAndSpecialChars("Computer Science Programme");
        Acronym programmeAcronym = new Acronym("CS");
        ProgrammeID programmeID = new ProgrammeID(programmeName, programmeAcronym);
        PAI.VOs.Date studyPlanDate = new PAI.VOs.Date("01-01-2023");
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, studyPlanDate);
        DurationCourseInCurricularYear durationOfCourse = new DurationCourseInCurricularYear(1);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = new CourseQuantityCreditsEcts(5.0);

        CourseInStudyPlanCommand command = new CourseInStudyPlanCommand(
                semester,
                curricularYear,
                courseAcronym,
                courseName,
                programmeAcronym,
                programmeName,
                studyPlanDate,
                durationOfCourse,
                quantityOfCreditsEcts
        );

        when(studyPlanService.getLatestStudyPlanIDByProgrammeID(programmeID))
                .thenReturn(studyPlanID);

        when(repository.getTotalCreditsEctsInStudyPlanSoFar(studyPlanID, semester, curricularYear, durationOfCourse))
                .thenReturn(25.0);

        when(factory.newCourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, durationOfCourse, quantityOfCreditsEcts))
                .thenReturn(candidate);

        when(repository.save(candidate)).thenReturn(candidate);

        CourseInStudyPlanServiceDTO dto = mock(CourseInStudyPlanServiceDTO.class);
        when(businessAssembler.toDTO(candidate)).thenReturn(dto);

        // act
        CourseInStudyPlanServiceDTO result = service.addCourseToAProgramme(command);

        // assert
        assertNotNull(result);
        assertEquals(dto, result);
    }

    @Test
    void should_CreateCourseInStudyPlan_WhenTotalCreditsDoesNotReachLimit() throws Exception {
        // arrange
        CourseInStudyPlan candidate = mock(CourseInStudyPlan.class);

        Semester semester = new Semester(1);
        CurricularYear curricularYear = new CurricularYear(1);
        Acronym courseAcronym = new Acronym("CS101");
        Name courseName = new Name("Computer Science");
        CourseID courseID = new CourseID(courseAcronym, courseName);
        NameWithNumbersAndSpecialChars programmeName = new NameWithNumbersAndSpecialChars("Computer Science Programme");
        Acronym programmeAcronym = new Acronym("CS");
        ProgrammeID programmeID = new ProgrammeID(programmeName, programmeAcronym);
        PAI.VOs.Date studyPlanDate = new PAI.VOs.Date("01-01-2023");
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, studyPlanDate);
        DurationCourseInCurricularYear durationOfCourse = new DurationCourseInCurricularYear(1);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = new CourseQuantityCreditsEcts(3.0);

        CourseInStudyPlanCommand command = new CourseInStudyPlanCommand(
                semester,
                curricularYear,
                courseAcronym,
                courseName,
                programmeAcronym,
                programmeName,
                studyPlanDate,
                durationOfCourse,
                quantityOfCreditsEcts
        );

        when(studyPlanService.getLatestStudyPlanIDByProgrammeID(programmeID))
                .thenReturn(studyPlanID);

        when(repository.getTotalCreditsEctsInStudyPlanSoFar(studyPlanID, semester, curricularYear, durationOfCourse))
                .thenReturn(25.0);

        when(factory.newCourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, durationOfCourse, quantityOfCreditsEcts))
                .thenReturn(candidate);

        when(repository.save(candidate)).thenReturn(candidate);

        CourseInStudyPlanServiceDTO dto = mock(CourseInStudyPlanServiceDTO.class);
        when(businessAssembler.toDTO(candidate)).thenReturn(dto);

        // act
        CourseInStudyPlanServiceDTO result = service.addCourseToAProgramme(command);

        // assert
        assertNotNull(result);
        assertEquals(dto, result);
    }

    @Test
    void should_CreateCourseInStudyPlan_ifCourseDoesntExist() throws Exception {
        // arrange
        CourseInStudyPlan candidate = mock(CourseInStudyPlan.class);

        Semester semester = new Semester(1);
        CurricularYear curricularYear = new CurricularYear(1);
        Acronym courseAcronym = new Acronym("CS101");
        Name courseName = new Name("Computer Science");
        CourseID courseID = new CourseID(courseAcronym, courseName);
        NameWithNumbersAndSpecialChars programmeName = new NameWithNumbersAndSpecialChars("Computer Science Programme");
        Acronym programmeAcronym = new Acronym("CS");
        ProgrammeID programmeID = new ProgrammeID(programmeName, programmeAcronym);
        PAI.VOs.Date studyPlanDate = new PAI.VOs.Date("01-01-2023");
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, studyPlanDate);
        DurationCourseInCurricularYear durationOfCourse = new DurationCourseInCurricularYear(1);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = new CourseQuantityCreditsEcts(3.0);

        CourseInStudyPlanCommand command = new CourseInStudyPlanCommand(
                semester,
                curricularYear,
                courseAcronym,
                courseName,
                programmeAcronym,
                programmeName,
                studyPlanDate,
                durationOfCourse,
                quantityOfCreditsEcts
        );

        when(studyPlanService.getLatestStudyPlanIDByProgrammeID(programmeID))
                .thenReturn(studyPlanID);

        when(repository.containsOfIdentity(any()))
                .thenReturn(false);

        when(repository.getTotalCreditsEctsInStudyPlanSoFar(studyPlanID, semester, curricularYear, durationOfCourse))
                .thenReturn(25.0);

        when(factory.newCourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, durationOfCourse, quantityOfCreditsEcts))
                .thenReturn(candidate);

        when(repository.save(candidate)).thenReturn(candidate);

        CourseInStudyPlanServiceDTO dto = mock(CourseInStudyPlanServiceDTO.class);
        when(businessAssembler.toDTO(candidate)).thenReturn(dto);

        // act
        CourseInStudyPlanServiceDTO result = service.addCourseToAProgramme(command);

        // assert
        assertNotNull(result);
        assertEquals(dto, result);
    }

//    @Test
//    void should_NotCreateCourseInStudyPlan_ifSemesterIsNotValid() throws Exception {
//        // arrange
//        Semester semester = new Semester(3); // Valor inválido
//        CurricularYear curricularYear = new CurricularYear(1);
//        Acronym courseAcronym = new Acronym("CS101");
//        Name courseName = new Name("Computer Science");
//        NameWithNumbersAndSpecialChars programmeName = new NameWithNumbersAndSpecialChars("Computer Science Programme");
//        Acronym programmeAcronym = new Acronym("CS");
//        PAI.VOs.Date studyPlanImplementationDate = new PAI.VOs.Date("01-01-2023");
//        DurationCourseInCurricularYear duration = new DurationCourseInCurricularYear(1);
//        CourseQuantityCreditsEcts credits = new CourseQuantityCreditsEcts(3.0);
//
//        CourseInStudyPlanCommand command = new CourseInStudyPlanCommand(
//                semester,
//                curricularYear,
//                courseAcronym,
//                courseName,
//                programmeAcronym,
//                programmeName,
//                studyPlanImplementationDate,
//                duration,
//                credits
//        );
//
//        // act + assert
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
//            service.addCourseToAProgramme(command);
//        });
//    }

//    @Test
//    void should_NotCreateCourseInStudyPlan_ifCurricularYearIsInvalid() throws Exception {
//
//        // arrange
//        Semester semester = new Semester(1);
//        CurricularYear curricularYear = new CurricularYear(0); // Invalid value
//        Acronym courseAcronym = new Acronym("CS101");
//        Name courseName = new Name("Computer Science");
//        NameWithNumbersAndSpecialChars programmeName = new NameWithNumbersAndSpecialChars("Computer Science Programme");
//        Acronym programmeAcronym = new Acronym("CS");
//        PAI.VOs.Date studyPlanImplementationDate = new PAI.VOs.Date("01-01-2023");
//        DurationCourseInCurricularYear duration = new DurationCourseInCurricularYear(1);
//        CourseQuantityCreditsEcts credits = new CourseQuantityCreditsEcts(3.0);
//
//        CourseInStudyPlanCommand command = new CourseInStudyPlanCommand(
//                semester,
//                curricularYear,
//                courseAcronym,
//                courseName,
//                programmeAcronym,
//                programmeName,
//                studyPlanImplementationDate,
//                duration,
//                credits
//        );
//
//        // act + assert
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
//            service.addCourseToAProgramme(command);
//        });
//    }

    @Test
    void should_NotCreateCourseInStudyPlan_ifCourseIsNull() throws Exception {
        // arrange
        Semester semester = new Semester(1);
        CurricularYear curricularYear = new CurricularYear(1);
        Acronym courseAcronym = null;
        Name courseName = new Name("Computer Science");
        NameWithNumbersAndSpecialChars programmeName = new NameWithNumbersAndSpecialChars("Computer Science Programme");
        Acronym programmeAcronym = new Acronym("CS");
        PAI.VOs.Date studyPlanDate = new PAI.VOs.Date("01-01-2023");
        DurationCourseInCurricularYear duration = new DurationCourseInCurricularYear(1);
        CourseQuantityCreditsEcts credits = new CourseQuantityCreditsEcts(3.0);

        CourseInStudyPlanCommand command = new CourseInStudyPlanCommand(
                semester,
                curricularYear,
                courseAcronym,
                courseName,
                programmeAcronym,
                programmeName,
                studyPlanDate,
                duration,
                credits
        );

        // act + assert
        assertThrows(BusinessRuleViolationException.class, () -> {
            service.addCourseToAProgramme(command);
        });
    }

    @Test
    void should_NotCreateCourseInStudyPlan_ifStudyPlanIDIsNull() throws Exception {

        // arrange
        Semester semester = new Semester(1);
        CurricularYear curricularYear = new CurricularYear(1);
        Acronym courseAcronym = new Acronym("CS101");
        Name courseName = new Name("Computer Science");
        NameWithNumbersAndSpecialChars programmeName = new NameWithNumbersAndSpecialChars("Computer Science Programme");
        Acronym programmeAcronym = new Acronym("CS");
        PAI.VOs.Date studyPlanDate = null;
        DurationCourseInCurricularYear duration = new DurationCourseInCurricularYear(1);
        CourseQuantityCreditsEcts credits = new CourseQuantityCreditsEcts(3.0);

        CourseInStudyPlanCommand command = new CourseInStudyPlanCommand(
                semester,
                curricularYear,
                courseAcronym,
                courseName,
                programmeAcronym,
                programmeName,
                studyPlanDate,
                duration,
                credits
        );

        // act + assert
        assertThrows(BusinessRuleViolationException.class, () -> {
            service.addCourseToAProgramme(command);
        });
    }

//    @Test
//    void should_NotCreateCourseInStudyPlan_ifDurationOfCourseIsInvalid() throws Exception {
//
//        // arrange
//        Semester semester = new Semester(1);
//        CurricularYear curricularYear = new CurricularYear(1);
//        Acronym courseAcronym = new Acronym("CS101");
//        Name courseName = new Name("Computer Science");
//        NameWithNumbersAndSpecialChars programmeName = new NameWithNumbersAndSpecialChars("Computer Science Programme");
//        Acronym programmeAcronym = new Acronym("CS");
//        PAI.VOs.Date studyPlanImplementationDate = new PAI.VOs.Date("01-01-2023");
//        DurationCourseInCurricularYear duration = new DurationCourseInCurricularYear(10);
//        CourseQuantityCreditsEcts credits = new CourseQuantityCreditsEcts(3.0);
//
//        CourseInStudyPlanCommand command = new CourseInStudyPlanCommand(
//                semester,
//                curricularYear,
//                courseAcronym,
//                courseName,
//                programmeAcronym,
//                programmeName,
//                studyPlanImplementationDate,
//                duration,
//                credits
//        );
//
//        // act + assert
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
//            service.addCourseToAProgramme(command);
//        });
//
//        assertEquals("The duration of the current year is invalid.", exception.getMessage());
//    }
//
//    @Test
//    void should_NotCreateCourseInStudyPlan_ifQuantityOfCreditsECTSIsInvalid() throws Exception {
//
//        // arrange
//        Semester semester = new Semester(1);
//        CurricularYear curricularYear = new CurricularYear(1);
//        Acronym courseAcronym = new Acronym("CS101");
//        Name courseName = new Name("Computer Science");
//        NameWithNumbersAndSpecialChars programmeName = new NameWithNumbersAndSpecialChars("Computer Science Programme");
//        Acronym programmeAcronym = new Acronym("CS");
//        PAI.VOs.Date studyPlanImplementationDate = new PAI.VOs.Date("01-01-2023");
//        DurationCourseInCurricularYear duration = new DurationCourseInCurricularYear(1);
//        CourseQuantityCreditsEcts credits = new CourseQuantityCreditsEcts(0.0);
//
//        CourseInStudyPlanCommand command = new CourseInStudyPlanCommand(
//                semester,
//                curricularYear,
//                courseAcronym,
//                courseName,
//                programmeAcronym,
//                programmeName,
//                studyPlanImplementationDate,
//                duration,
//                credits
//        );
//
//        // act + assert
//        assertThrows(IllegalArgumentException.class, () -> {
//            service.addCourseToAProgramme(command);
//        });
//    }
}

