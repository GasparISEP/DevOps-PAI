package PAI.controller;

import PAI.VOs.*;
import PAI.domain.programme.IProgrammeFactory;
import PAI.domain.programme.Programme;
import PAI.domain.programme.ProgrammeFactoryImpl;
import PAI.domain.studyPlan.IStudyPlanFactory;
import PAI.domain.studyPlan.StudyPlanFactoryImpl;
import PAI.repository.programmeRepository.IProgrammeDDDRepository;
import PAI.repository.programmeRepository.IProgrammeDDDRepositoryListFactory;
import PAI.repository.programmeRepository.ProgrammeDDDRepositoryImpl;
import PAI.repository.programmeRepository.ProgrammeDDDRepositoryListFactoryImpl;
import PAI.repository.studyPlanRepository.IStudyPlanDDDListFactory;
import PAI.repository.studyPlanRepository.IStudyPlanDDDRepository;
import PAI.repository.studyPlanRepository.StudyPlanDDDListFactoryImpl;
import PAI.repository.studyPlanRepository.StudyPlanDDDRepositoryImpl;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US27__RegisterAProgrammeInTheSystemIncludingTheStudyPlanControllerTest {

    @Test
    void registerProgrammeInTheSystemControllerFailureWithNullProgrammeRepo() throws Exception {
        //arrange
        ProgrammeDDDRepositoryImpl programmeDDDRepositoryImpl = null;
        IStudyPlanFactory factory = mock(IStudyPlanFactory.class);
        IStudyPlanDDDListFactory listFactory = mock(IStudyPlanDDDListFactory.class);
        StudyPlanDDDRepositoryImpl studyPlanDDDRepo = new StudyPlanDDDRepositoryImpl(factory, listFactory);

        //act + assert
        Exception exception = assertThrows(Exception.class, () ->
                new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(programmeDDDRepositoryImpl, studyPlanDDDRepo));

        assertEquals("Programme Repository cannot be null.", exception.getMessage());

    }

    @Test
    void registerProgrammeInTheSystemControllerFailureWithNullStudyPlanRepo() throws Exception {
        //arrange
        IProgrammeFactory iProgrammeFactory = mock(IProgrammeFactory.class);
        IProgrammeDDDRepositoryListFactory iProgrammeDDDRepoListFactory = mock(IProgrammeDDDRepositoryListFactory.class);
        ProgrammeDDDRepositoryImpl programmeDDDRepositoryImpl = new ProgrammeDDDRepositoryImpl(iProgrammeFactory, iProgrammeDDDRepoListFactory);
        StudyPlanDDDRepositoryImpl studyPlanDDDRepo = null;

        //act + assert
        Exception exception = assertThrows(Exception.class, () ->
                new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(programmeDDDRepositoryImpl, studyPlanDDDRepo));

        assertEquals("Study Plan Repository cannot be null.", exception.getMessage());


    }

    @Test
    void registerProgrammeInTheSystemControllerCorrectly() throws Exception {
        //arrange
        IProgrammeFactory iProgrammeFactory = mock(IProgrammeFactory.class);
        IProgrammeDDDRepositoryListFactory iProgrammeDDDRepoListFactory = mock(IProgrammeDDDRepositoryListFactory.class);
        ProgrammeDDDRepositoryImpl programmeDDDRepositoryImpl = new ProgrammeDDDRepositoryImpl(iProgrammeFactory, iProgrammeDDDRepoListFactory);
        IStudyPlanFactory factory = mock(IStudyPlanFactory.class);
        IStudyPlanDDDListFactory listFactory = mock(IStudyPlanDDDListFactory.class);
        StudyPlanDDDRepositoryImpl studyPlanDDDRepo = new StudyPlanDDDRepositoryImpl(factory, listFactory);

        //act
        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController controller = new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(programmeDDDRepositoryImpl, studyPlanDDDRepo);

        //assert
        assertNotNull(controller);
    }

    @Test
    void createStudyPlanDDDWithSuccess() throws Exception {
        // Arrange
        ProgrammeDDDRepositoryImpl programmeRepo = mock(ProgrammeDDDRepositoryImpl.class);
        StudyPlanDDDRepositoryImpl studyPlanRepo = mock(StudyPlanDDDRepositoryImpl.class);
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
        ProgrammeDDDRepositoryImpl programmeRepo = mock(ProgrammeDDDRepositoryImpl.class);
        StudyPlanDDDRepositoryImpl studyPlanRepo = mock(StudyPlanDDDRepositoryImpl.class);
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
        ProgrammeDDDRepositoryImpl programmeRepo = mock(ProgrammeDDDRepositoryImpl.class);
        StudyPlanDDDRepositoryImpl studyPlanRepo = mock(StudyPlanDDDRepositoryImpl.class);

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
        IProgrammeDDDRepositoryListFactory iProgrammeDDDRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDRepository iProgrammeDDDRepository = new ProgrammeDDDRepositoryImpl(iProgrammeFactory, iProgrammeDDDRepositoryListFactory);

        IStudyPlanFactory iStudyPlanFactory = new StudyPlanFactoryImpl();
        IStudyPlanDDDListFactory iStudyPlanDDDListFactory = new StudyPlanDDDListFactoryImpl();
        IStudyPlanDDDRepository iStudyPlanDDDRepository = new StudyPlanDDDRepositoryImpl(iStudyPlanFactory, iStudyPlanDDDListFactory);

        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController controller = new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(iProgrammeDDDRepository, iStudyPlanDDDRepository);

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
        IProgrammeDDDRepositoryListFactory iProgrammeDDDRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDRepository iProgrammeDDDRepository = new ProgrammeDDDRepositoryImpl(iProgrammeFactory, iProgrammeDDDRepositoryListFactory);

        IStudyPlanFactory iStudyPlanFactory = new StudyPlanFactoryImpl();
        IStudyPlanDDDListFactory iStudyPlanDDDListFactory = new StudyPlanDDDListFactoryImpl();
        IStudyPlanDDDRepository iStudyPlanDDDRepository = new StudyPlanDDDRepositoryImpl(iStudyPlanFactory, iStudyPlanDDDListFactory);

        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController controller = new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(iProgrammeDDDRepository, iStudyPlanDDDRepository);
        controller.registerAProgrammeDDDInTheSystem(nameWithNumbersAndSpecialChars, acronym, quantEcts, quantSemesters, degreeTypeID, departmentID, teacherID);

        //act
        boolean result = controller.createStudyPlanDDD(programmeID, implemtationDate);
        //assert
        assertTrue(result);
    }
}