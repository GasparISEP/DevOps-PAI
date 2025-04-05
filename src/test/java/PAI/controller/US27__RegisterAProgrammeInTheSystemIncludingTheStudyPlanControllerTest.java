package PAI.controller;

import PAI.VOs.*;
import PAI.domain.programme.IProgrammeFactory;
import PAI.domain.programme.Programme;
import PAI.domain.programme.ProgrammeFactoryImpl;
import PAI.domain.studyPlan.IStudyPlanFactory;
import PAI.domain.studyPlan.StudyPlanFactoryImpl;
import PAI.repository.programmeRepository.IProgrammeRepository;
import PAI.repository.programmeRepository.IProgrammeRepositoryListFactory;
import PAI.repository.programmeRepository.ProgrammeRepositoryImpl;
import PAI.repository.programmeRepository.ProgrammeRepositoryListFactoryImpl;
import PAI.repository.studyPlanRepository.IStudyPlanListFactory;
import PAI.repository.studyPlanRepository.IStudyPlanRepository;
import PAI.repository.studyPlanRepository.StudyPlanListFactoryImpl;
import PAI.repository.studyPlanRepository.StudyPlanRepositoryImpl;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US27__RegisterAProgrammeInTheSystemIncludingTheStudyPlanControllerTest {

    @Test
    void registerProgrammeInTheSystemControllerFailureWithNullProgrammeRepo() throws Exception {
        //arrange
        ProgrammeRepositoryImpl programmeDDDRepositoryImpl = null;
        IStudyPlanFactory factory = mock(IStudyPlanFactory.class);
        IStudyPlanListFactory listFactory = mock(IStudyPlanListFactory.class);
        StudyPlanRepositoryImpl studyPlanDDDRepo = new StudyPlanRepositoryImpl(factory, listFactory);

        //act + assert
        Exception exception = assertThrows(Exception.class, () ->
                new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(programmeDDDRepositoryImpl, studyPlanDDDRepo));

        assertEquals("Programme Repository cannot be null.", exception.getMessage());

    }

    @Test
    void registerProgrammeInTheSystemControllerFailureWithNullStudyPlanRepo() throws Exception {
        //arrange
        IProgrammeFactory iProgrammeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory iProgrammeDDDRepoListFactory = mock(IProgrammeRepositoryListFactory.class);
        ProgrammeRepositoryImpl programmeDDDRepositoryImpl = new ProgrammeRepositoryImpl(iProgrammeFactory, iProgrammeDDDRepoListFactory);
        StudyPlanRepositoryImpl studyPlanDDDRepo = null;

        //act + assert
        Exception exception = assertThrows(Exception.class, () ->
                new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(programmeDDDRepositoryImpl, studyPlanDDDRepo));

        assertEquals("Study Plan Repository cannot be null.", exception.getMessage());


    }

    @Test
    void registerProgrammeInTheSystemControllerCorrectly() throws Exception {
        //arrange
        IProgrammeFactory iProgrammeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory iProgrammeDDDRepoListFactory = mock(IProgrammeRepositoryListFactory.class);
        ProgrammeRepositoryImpl programmeDDDRepositoryImpl = new ProgrammeRepositoryImpl(iProgrammeFactory, iProgrammeDDDRepoListFactory);
        IStudyPlanFactory factory = mock(IStudyPlanFactory.class);
        IStudyPlanListFactory listFactory = mock(IStudyPlanListFactory.class);
        StudyPlanRepositoryImpl studyPlanDDDRepo = new StudyPlanRepositoryImpl(factory, listFactory);

        //act
        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController controller = new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(programmeDDDRepositoryImpl, studyPlanDDDRepo);

        //assert
        assertNotNull(controller);
    }

    @Test
    void createStudyPlanDDDWithSuccess() throws Exception {
        // Arrange
        ProgrammeRepositoryImpl programmeRepo = mock(ProgrammeRepositoryImpl.class);
        StudyPlanRepositoryImpl studyPlanRepo = mock(StudyPlanRepositoryImpl.class);
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);

        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController controller =
                new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(programmeRepo, studyPlanRepo);
        controller._studyPlanDDDRepo = studyPlanRepo;

        Programme programme = mock(Programme.class);
        QuantSemesters quantSemesters = mock(QuantSemesters.class);
        QuantEcts quantEcts = mock(QuantEcts.class);
        when(programme.getQuantEcts()).thenReturn(quantEcts);
        when(quantEcts.getQuantEcts()).thenReturn(30);
        when(programme.getQuantSemesters()).thenReturn(quantSemesters);
        when(quantSemesters.getQuantityOfSemesters()).thenReturn(6);

        ProgrammeID programmeID = new ProgrammeID(name, acronym);
        Date implementationDate = new Date("21-03-2000");
        when(programmeRepo.ofIdentity(programmeID)).thenReturn(Optional.of(programme));

        // Act
        boolean result = controller.createStudyPlanDDD(programmeID, implementationDate);

        // Assert
        assertTrue(result);
    }

    @Test
    void createStudyPlanDDDProgrammeNotFound() throws Exception {
        // Arrange
        ProgrammeRepositoryImpl programmeRepo = mock(ProgrammeRepositoryImpl.class);
        StudyPlanRepositoryImpl studyPlanRepo = mock(StudyPlanRepositoryImpl.class);
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);

        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController controller =
                new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(programmeRepo, studyPlanRepo);
        controller._studyPlanDDDRepo = studyPlanRepo;

        ProgrammeID programmeID = new ProgrammeID(name, acronym);
        Date implementationDate = new Date("21-03-2000");

        when(programmeRepo.ofIdentity(programmeID)).thenReturn(Optional.empty());

        // Act
        boolean result = controller.createStudyPlanDDD(programmeID, implementationDate);

        // Assert
        assertFalse(result);
    }

    @Test
    void registerProgrammeInTheSystemWithSuccess() throws Exception {
        //arrange
        ProgrammeRepositoryImpl programmeRepo = mock(ProgrammeRepositoryImpl.class);
        StudyPlanRepositoryImpl studyPlanRepo = mock(StudyPlanRepositoryImpl.class);

        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController controller =
                new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(programmeRepo, studyPlanRepo);
        controller._programmeDDDList = programmeRepo;

        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);

        //act
        boolean result = controller.registerAProgrammeDDDInTheSystem(name, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID);

        //assert
        assertTrue(result);
    }

    @Test
    void registerProgrammeInTheSystemSuccessIntegrationTest() throws Exception {
        //arrange
        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("ABC");
        Acronym acronym = new Acronym("ABC");
        QuantEcts quantEcts = new QuantEcts(12);
        QuantSemesters quantSemesters = new QuantSemesters(2);
        DegreeTypeID degreeTypeID = new DegreeTypeID("123456789");
        DepartmentAcronym departmentAcronym = new DepartmentAcronym("ALG");
        DepartmentID departmentID = new DepartmentID(departmentAcronym);
        TeacherAcronym teacherAcronym = new TeacherAcronym("ALP");
        TeacherID teacherID = new TeacherID(teacherAcronym);

        IProgrammeFactory iProgrammeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepositoryListFactory iProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeRepository iProgrammeRepository = new ProgrammeRepositoryImpl(iProgrammeFactory, iProgrammeRepositoryListFactory);

        IStudyPlanFactory iStudyPlanFactory = new StudyPlanFactoryImpl();
        IStudyPlanListFactory iStudyPlanListFactory = new StudyPlanListFactoryImpl();
        IStudyPlanRepository iStudyPlanRepository = new StudyPlanRepositoryImpl(iStudyPlanFactory, iStudyPlanListFactory);

        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController controller = new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(iProgrammeRepository, iStudyPlanRepository);

        //act
        boolean result = controller.registerAProgrammeDDDInTheSystem(nameWithNumbersAndSpecialChars, acronym, quantEcts, quantSemesters, degreeTypeID, departmentID, teacherID);

        //assert
        assertTrue(result);
    }

    @Test
    void createStudyPlanIntegrationTest() throws Exception {
        //arrange
        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("ABC");
        Acronym acronym = new Acronym("ABC");
        QuantEcts quantEcts = new QuantEcts(12);
        QuantSemesters quantSemesters = new QuantSemesters(2);
        DegreeTypeID degreeTypeID = new DegreeTypeID("123456789");
        DepartmentAcronym departmentAcronym = new DepartmentAcronym("ALG");
        DepartmentID departmentID = new DepartmentID(departmentAcronym);
        TeacherAcronym teacherAcronym = new TeacherAcronym("ALP");
        TeacherID teacherID = new TeacherID(teacherAcronym);
        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronym);
        Date implemtationDate = new Date("21-03-2025");

        IProgrammeFactory iProgrammeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepositoryListFactory iProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeRepository iProgrammeRepository = new ProgrammeRepositoryImpl(iProgrammeFactory, iProgrammeRepositoryListFactory);

        IStudyPlanFactory iStudyPlanFactory = new StudyPlanFactoryImpl();
        IStudyPlanListFactory iStudyPlanListFactory = new StudyPlanListFactoryImpl();
        IStudyPlanRepository iStudyPlanRepository = new StudyPlanRepositoryImpl(iStudyPlanFactory, iStudyPlanListFactory);

        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController controller = new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(iProgrammeRepository, iStudyPlanRepository);
        controller.registerAProgrammeDDDInTheSystem(nameWithNumbersAndSpecialChars, acronym, quantEcts, quantSemesters, degreeTypeID, departmentID, teacherID);

        //act
        boolean result = controller.createStudyPlanDDD(programmeID, implemtationDate);
        //assert
        assertTrue(result);
    }
}