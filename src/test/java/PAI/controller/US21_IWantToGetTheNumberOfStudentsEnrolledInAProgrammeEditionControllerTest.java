package PAI.controller;

import PAI.VOs.*;

import PAI.domain.courseEditionEnrolment.CourseEditionEnrolmentListFactoryImpl;
import PAI.domain.courseEditionEnrolment.ICourseEditionEnrolmentListFactory;
import PAI.domain.courseEditionEnrolment.ICourseEditionEnrolmentRepository;
import PAI.factory.*;
import PAI.persistence.mem.CourseEditionEnrolmentRepositoryImpl;
import PAI.persistence.mem.SchoolYearRepositoryImpl;
import PAI.persistence.mem.programme.IProgrammeRepositoryListFactory;
import PAI.persistence.mem.programme.ProgrammeRepositoryImpl;
import PAI.persistence.mem.programme.ProgrammeRepositoryListFactoryImpl;
import PAI.repository.*;

import PAI.persistence.mem.programmeEdition.IProgrammeEditionListFactory;
import PAI.repository.programmeEditionRepository.IProgrammeEditionRepository;
import PAI.persistence.mem.programmeEdition.ProgrammeEditionListFactoryImpl;
import PAI.persistence.mem.programmeEdition.ProgrammeEditionRepositoryImpl;
import PAI.repository.programmeRepository.IProgrammeRepository;
import PAI.service.IProgrammeEditionEnrolmentService;
import PAI.service.ProgrammeEditionEnrolmentServiceImpl;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.factory.IProgrammeEditionEnrolmentFactory;
import PAI.factory.IProgrammeEditionEnrolmentListFactory;
import PAI.factory.ProgrammeEditionEnrolmentFactoryImpl;
import PAI.factory.ProgrammeEditionEnrolmentListFactoryImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionControllerTest {

    @Test
    void shouldCreateControllerWhenRepositoryIsValid_Isolation_Test() {
        //SUT Controller
        //Arrange
        IProgrammeEditionEnrolmentService iProgrammeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeEditionRepository iProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        //Act
        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController controller =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(iProgrammeEditionEnrolmentService, iProgrammeEditionRepository);
        //Assert
        assertNotNull(controller);
    }

    @Test
    void shouldThrowExceptionWhenServiceIsNull() {
        //SUT Controller
        IProgrammeEditionRepository iProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        //Arrange
        //Act + Assert
        assertThrows(Exception.class, () -> new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(null, iProgrammeEditionRepository));
    }

    @Test
    void shouldThrowExceptionWhenRepositoryIsNull() {
        //SUT Controller
        IProgrammeEditionEnrolmentService iProgrammeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        //Arrange
        //Act + Assert
        assertThrows(Exception.class, () -> new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(iProgrammeEditionEnrolmentService, null));
    }

    @Test
    void shouldGetTheTotalNumberOfStudentsEnrolledInAProgrammeEdition() throws Exception {
        // arrange
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);

        IProgrammeEditionEnrolmentService iProgrammeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeEditionRepository iProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController controller =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(iProgrammeEditionEnrolmentService, iProgrammeEditionRepository);
        when(iProgrammeEditionEnrolmentService.totalStudentsInProgrammeEdition(programmeEditionID)).thenReturn(1);
        // act
        int result = controller.getTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEditionID);
        // assert
        assertEquals(1, result);
    }

    @Test
    void shouldReturnIllegalArgumentExceptionIfProgrammeEditionIdNull() throws Exception {
        // arrange
        IProgrammeEditionEnrolmentService iProgrammeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeEditionRepository iProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController controller =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(iProgrammeEditionEnrolmentService, iProgrammeEditionRepository);
        // act && assert
        assertThrows(IllegalArgumentException.class, () -> controller.getTheNumberOfStudentsEnrolledInAProgrammeEdition(null));
    }

    @Test
    void shouldGetTheNumberOfStudentsEnrolledInAProgrammeEdition() throws Exception {
        // Arrange
        StudentID studentID = mock(StudentID.class);
        StudentID studentID2 = mock(StudentID.class);
        StudentID studentID3 = mock(StudentID.class);
        StudentID studentID4 = mock(StudentID.class);

        IProgrammeEditionEnrolmentListFactory programmeEditionEnrolmentListFactoryImpl = new ProgrammeEditionEnrolmentListFactoryImpl();

        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactoryImpl);


        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrolmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(IProgrammeRepositoryListFactory);
        ICourseEditionFactory courseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory courseEditionListFactory = new CourseEditionListFactoryImpl();
        ICourseEditionRepository courseEditionRepository = new CourseEditionRepositoryImpl(courseEditionFactory, courseEditionListFactory);
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentListFactory);
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentFactory, programmeEnrolmentList);

        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = new ProgrammeEditionEnrolmentServiceImpl(
                programmeEditionEnrolmentRepository,
                programmeEditionRepository,
                courseEditionEnrolmentRepositoryImpl,
                courseEditionRepository,
                schoolYearRepository,
                programmeEnrolmentRepository,
                programmeRepository,
                programmeEditionEnrolmentFactory);

        NameWithNumbersAndSpecialChars programmeName1 = new NameWithNumbersAndSpecialChars("Licenciatura em Engenharia Informatica");
        Acronym programmeAcronym1 = new Acronym("LEI");
        ProgrammeID programmeID = new ProgrammeID(programmeName1, programmeAcronym1);

        SchoolYearID schoolYearID1 = new SchoolYearID();
        SchoolYearID schoolYearID2 = new SchoolYearID();

        ProgrammeEditionID programmeEditionID1 = new ProgrammeEditionID(programmeID, schoolYearID1);
        ProgrammeEditionID programmeEditionID2 = new ProgrammeEditionID(programmeID, schoolYearID2);

        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController controller1 =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionEnrolmentService, programmeEditionRepository);

        programmeEditionEnrolmentService.enrolStudentInProgrammeEdition(studentID, programmeEditionID1);
        programmeEditionEnrolmentService.enrolStudentInProgrammeEdition(studentID2, programmeEditionID1);
        programmeEditionEnrolmentService.enrolStudentInProgrammeEdition(studentID3, programmeEditionID1);
        programmeEditionEnrolmentService.enrolStudentInProgrammeEdition(studentID4, programmeEditionID2);

        // Act
        int result = controller1.getTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEditionID1);

        // Assert
        assertEquals(3, result);
    }

    @Test
    void shouldReturnZeroIfProgrammeEditionHasZeroStudentsEnrolled() throws Exception {
        //Arrange

        IProgrammeEditionEnrolmentListFactory programmeEditionEnrolmentListFactoryImpl = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactoryImpl);

        NameWithNumbersAndSpecialChars programmeName1 = new NameWithNumbersAndSpecialChars("Licenciatura em Engenharia Informatica");
        Acronym programmeAcronym1 = new Acronym("LEI");
        ProgrammeID programmeID = new ProgrammeID(programmeName1, programmeAcronym1);

        SchoolYearID schoolYearID1 = new SchoolYearID();

        ProgrammeEditionID programmeEditionID1 = new ProgrammeEditionID(programmeID, schoolYearID1);
        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrolmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(IProgrammeRepositoryListFactory);
        ICourseEditionFactory courseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory courseEditionListFactory = new CourseEditionListFactoryImpl();
        ICourseEditionRepository courseEditionRepository = new CourseEditionRepositoryImpl(courseEditionFactory, courseEditionListFactory);
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentListFactory);
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentFactory, programmeEnrolmentList);

        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = new ProgrammeEditionEnrolmentServiceImpl(
                programmeEditionEnrolmentRepository,
                programmeEditionRepository,
                courseEditionEnrolmentRepositoryImpl,
                courseEditionRepository,
                schoolYearRepository,
                programmeEnrolmentRepository,
                programmeRepository,
                programmeEditionEnrolmentFactory);
        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController controller1 =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionEnrolmentService, programmeEditionRepository);

        // Act
        int result = controller1.getTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEditionID1);

        // Assert
        assertEquals(0, result);
    }
//


    @Test
    void shouldGetAllProgrammeEdition() {
        // arrange
        IProgrammeEditionEnrolmentService iProgrammeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeEditionRepository iProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController controller =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(iProgrammeEditionEnrolmentService, iProgrammeEditionRepository);
        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        when(iProgrammeEditionRepository.findAll()).thenReturn(List.of(programmeEdition));
        // act
        Iterable<ProgrammeEdition> result = controller.getAllProgrammeEdition();
        // assert
        assertEquals(List.of(programmeEdition), result);
    }
}



