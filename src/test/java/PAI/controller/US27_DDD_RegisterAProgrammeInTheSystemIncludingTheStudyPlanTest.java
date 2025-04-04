package PAI.controller;

import PAI.VOs.*;
import PAI.domain.Department;
import PAI.domain.programme.IProgrammeDDDFactory;
import PAI.domain.programme.ProgrammeDDD;
import PAI.domain.programme.ProgrammeDDDFactoryImpl;
import PAI.domain.studyPlan.IStudyPlanDDDFactory;
import PAI.domain.studyPlan.StudyPlanDDD;
import PAI.domain.studyPlan.StudyPlanDDDFactoryImpl;
import PAI.repository.programmeRepo.IProgrammeDDDRepository;
import PAI.repository.programmeRepo.IProgrammeDDDRepositoryListFactory;
import PAI.repository.programmeRepo.ProgrammeDDDRepositoryImpl;
import PAI.repository.programmeRepo.ProgrammeDDDRepositoryListFactoryImpl;
import PAI.repository.studyPlanRepo.IStudyPlanDDDListFactory;
import PAI.repository.studyPlanRepo.IStudyPlanDDDRepository;
import PAI.repository.studyPlanRepo.StudyPlanDDDListFactoryImpl;
import PAI.repository.studyPlanRepo.StudyPlanDDDRepositoryImpl;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US27_DDD_RegisterAProgrammeInTheSystemIncludingTheStudyPlanTest {

    @Test
    void registerProgrammeInTheSystemControllerFailureWithNullProgrammeRepo() throws Exception {
        //arrange
        ProgrammeDDDRepositoryImpl programmeDDDRepositoryImpl = null;
        IStudyPlanDDDFactory factory = mock(IStudyPlanDDDFactory.class);
        IStudyPlanDDDListFactory listFactory = mock(IStudyPlanDDDListFactory.class);
        StudyPlanDDDRepositoryImpl studyPlanDDDRepo = new StudyPlanDDDRepositoryImpl(factory, listFactory);

        //act + assert
        Exception exception = assertThrows(Exception.class, () ->
                new US27_DDD_RegisterAProgrammeInTheSystemIncludingTheStudyPlan(programmeDDDRepositoryImpl, studyPlanDDDRepo));

        assertEquals("Programme Repository cannot be null.", exception.getMessage());

    }

    @Test
    void registerProgrammeInTheSystemControllerFailureWithNullStudyPlanRepo() throws Exception {
        //arrange
        IProgrammeDDDFactory iProgrammeDDDFactory = mock(IProgrammeDDDFactory.class);
        IProgrammeDDDRepositoryListFactory iProgrammeDDDRepoListFactory = mock(IProgrammeDDDRepositoryListFactory.class);
        ProgrammeDDDRepositoryImpl programmeDDDRepositoryImpl = new ProgrammeDDDRepositoryImpl(iProgrammeDDDFactory, iProgrammeDDDRepoListFactory);
        StudyPlanDDDRepositoryImpl studyPlanDDDRepo = null;

        //act + assert
        Exception exception = assertThrows(Exception.class, () ->
                new US27_DDD_RegisterAProgrammeInTheSystemIncludingTheStudyPlan(programmeDDDRepositoryImpl, studyPlanDDDRepo));

        assertEquals("Study Plan Repository cannot be null.", exception.getMessage());


    }

    @Test
    void registerProgrammeInTheSystemControllerCorrectly() throws Exception {
        //arrange
        IProgrammeDDDFactory iProgrammeDDDFactory = mock(IProgrammeDDDFactory.class);
        IProgrammeDDDRepositoryListFactory iProgrammeDDDRepoListFactory = mock(IProgrammeDDDRepositoryListFactory.class);
        ProgrammeDDDRepositoryImpl programmeDDDRepositoryImpl = new ProgrammeDDDRepositoryImpl(iProgrammeDDDFactory, iProgrammeDDDRepoListFactory);
        IStudyPlanDDDFactory factory = mock(IStudyPlanDDDFactory.class);
        IStudyPlanDDDListFactory listFactory = mock(IStudyPlanDDDListFactory.class);
        StudyPlanDDDRepositoryImpl studyPlanDDDRepo = new StudyPlanDDDRepositoryImpl(factory, listFactory);

        //act
        US27_DDD_RegisterAProgrammeInTheSystemIncludingTheStudyPlan controller = new US27_DDD_RegisterAProgrammeInTheSystemIncludingTheStudyPlan(programmeDDDRepositoryImpl, studyPlanDDDRepo);

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

        US27_DDD_RegisterAProgrammeInTheSystemIncludingTheStudyPlan controller =
                new US27_DDD_RegisterAProgrammeInTheSystemIncludingTheStudyPlan(programmeRepo, studyPlanRepo);
        controller._studyPlanDDDRepo = studyPlanRepo;

        ProgrammeDDD programmeDDD = mock(ProgrammeDDD.class);
        QuantSemesters quantSemesters = mock(QuantSemesters.class);
        QuantEcts quantEcts = mock(QuantEcts.class);
        when(programmeDDD.getQuantEcts()).thenReturn(quantEcts);
        when(quantEcts.getQuantEcts()).thenReturn(30);
        when(programmeDDD.getQuantSemesters()).thenReturn(quantSemesters);
        when(quantSemesters.getQuantityOfSemesters()).thenReturn(6);

        ProgrammeID programmeID = new ProgrammeID(name, acronym);
        Date implementationDate = new Date("21-03-2000");
        when(programmeRepo.ofIdentity(programmeID)).thenReturn(Optional.of(programmeDDD));

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

        US27_DDD_RegisterAProgrammeInTheSystemIncludingTheStudyPlan controller =
                new US27_DDD_RegisterAProgrammeInTheSystemIncludingTheStudyPlan(programmeRepo, studyPlanRepo);
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

        US27_DDD_RegisterAProgrammeInTheSystemIncludingTheStudyPlan controller =
                new US27_DDD_RegisterAProgrammeInTheSystemIncludingTheStudyPlan(programmeRepo, studyPlanRepo);
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

        IProgrammeDDDFactory iProgrammeDDDFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepositoryListFactory iProgrammeDDDRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDRepository iProgrammeDDDRepository = new ProgrammeDDDRepositoryImpl(iProgrammeDDDFactory, iProgrammeDDDRepositoryListFactory);

        IStudyPlanDDDFactory iStudyPlanDDDFactory = new StudyPlanDDDFactoryImpl();
        IStudyPlanDDDListFactory iStudyPlanDDDListFactory = new StudyPlanDDDListFactoryImpl();
        IStudyPlanDDDRepository iStudyPlanDDDRepository = new StudyPlanDDDRepositoryImpl(iStudyPlanDDDFactory, iStudyPlanDDDListFactory);

        US27_DDD_RegisterAProgrammeInTheSystemIncludingTheStudyPlan controller = new US27_DDD_RegisterAProgrammeInTheSystemIncludingTheStudyPlan(iProgrammeDDDRepository, iStudyPlanDDDRepository);

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

        IProgrammeDDDFactory iProgrammeDDDFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepositoryListFactory iProgrammeDDDRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDRepository iProgrammeDDDRepository = new ProgrammeDDDRepositoryImpl(iProgrammeDDDFactory, iProgrammeDDDRepositoryListFactory);

        IStudyPlanDDDFactory iStudyPlanDDDFactory = new StudyPlanDDDFactoryImpl();
        IStudyPlanDDDListFactory iStudyPlanDDDListFactory = new StudyPlanDDDListFactoryImpl();
        IStudyPlanDDDRepository iStudyPlanDDDRepository = new StudyPlanDDDRepositoryImpl(iStudyPlanDDDFactory, iStudyPlanDDDListFactory);

        US27_DDD_RegisterAProgrammeInTheSystemIncludingTheStudyPlan controller = new US27_DDD_RegisterAProgrammeInTheSystemIncludingTheStudyPlan(iProgrammeDDDRepository, iStudyPlanDDDRepository);
        controller.registerAProgrammeDDDInTheSystem(nameWithNumbersAndSpecialChars, acronym, quantEcts, quantSemesters, degreeTypeID, departmentID, teacherID);

        //act
        boolean result = controller.createStudyPlanDDD(programmeID, implemtationDate);
        //assert
        assertTrue(result);
    }
}