package PAI.controller;

import PAI.VOs.*;
import PAI.domain.degreeType.DegreeType;
import PAI.factory.IProgrammeFactory;
import PAI.domain.programme.Programme;
import PAI.factory.ProgrammeFactoryImpl;
import PAI.domain.studyPlan.IStudyPlanFactory;
import PAI.domain.studyPlan.StudyPlanFactoryImpl;
import PAI.factory.DegreeTypeFactory.DegreeTypeFactoryImpl;
import PAI.factory.DegreeTypeFactory.DegreeTypeListFactoryImpl;
import PAI.factory.DegreeTypeFactory.IDegreeTypeFactory;
import PAI.factory.DegreeTypeFactory.IDegreeTypeListFactory;
import PAI.repository.degreeTypeRepository.DegreeTypeRepositoryImpl;
import PAI.repository.programmeRepository.IProgrammeRepository;
import PAI.persistence.mem.programmeEdition.IProgrammeRepositoryListFactory;
import PAI.persistence.mem.programmeEdition.ProgrammeRepositoryImpl;
import PAI.persistence.mem.programmeEdition.ProgrammeRepositoryListFactoryImpl;
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

        IDegreeTypeFactory factoryDegreeType = mock(IDegreeTypeFactory.class);
        IDegreeTypeListFactory listFactoryDegreeType= mock(IDegreeTypeListFactory.class);
        DegreeTypeRepositoryImpl degreeTypeRepository = new DegreeTypeRepositoryImpl(factoryDegreeType, listFactoryDegreeType);

        //act + assert
        Exception exception = assertThrows(Exception.class, () ->
                new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(programmeDDDRepositoryImpl, studyPlanDDDRepo, degreeTypeRepository));

        assertEquals("Programme Repository cannot be null.", exception.getMessage());

    }

    @Test
    void registerProgrammeInTheSystemControllerFailureWithNullStudyPlanRepo() throws Exception {
        //arrange
        IProgrammeFactory iProgrammeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory iProgrammeDDDRepoListFactory = mock(IProgrammeRepositoryListFactory.class);
        ProgrammeRepositoryImpl programmeDDDRepositoryImpl = new ProgrammeRepositoryImpl(iProgrammeFactory, iProgrammeDDDRepoListFactory);

        StudyPlanRepositoryImpl studyPlanDDDRepo = null;

        IDegreeTypeFactory factoryDegreeType = mock(IDegreeTypeFactory.class);
        IDegreeTypeListFactory listFactoryDegreeType= mock(IDegreeTypeListFactory.class);
        DegreeTypeRepositoryImpl degreeTypeRepository = new DegreeTypeRepositoryImpl(factoryDegreeType, listFactoryDegreeType);


        //act + assert
        Exception exception = assertThrows(Exception.class, () ->
                new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(programmeDDDRepositoryImpl, studyPlanDDDRepo, degreeTypeRepository));

        assertEquals("Study Plan Repository cannot be null.", exception.getMessage());

    }

    @Test
    void registerProgrammeInTheSystemControllerFailureWithNullDegreeTypeRepo() throws Exception {
        //arrange
        IProgrammeFactory iProgrammeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory iProgrammeDDDRepoListFactory = mock(IProgrammeRepositoryListFactory.class);
        ProgrammeRepositoryImpl programmeDDDRepositoryImpl = new ProgrammeRepositoryImpl(iProgrammeFactory, iProgrammeDDDRepoListFactory);

        IStudyPlanFactory factory = mock(IStudyPlanFactory.class);
        IStudyPlanListFactory listFactory = mock(IStudyPlanListFactory.class);
        StudyPlanRepositoryImpl studyPlanDDDRepo = new StudyPlanRepositoryImpl(factory, listFactory);

        DegreeTypeRepositoryImpl degreeTypeRepository = null;

        //act + assert
        Exception exception = assertThrows(Exception.class, () ->
                new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(programmeDDDRepositoryImpl, studyPlanDDDRepo, degreeTypeRepository));

        assertEquals("Degree Type Repository cannot be null.", exception.getMessage());

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

        IDegreeTypeFactory factoryDegreeType = mock(IDegreeTypeFactory.class);
        IDegreeTypeListFactory listFactoryDegreeType= mock(IDegreeTypeListFactory.class);
        DegreeTypeRepositoryImpl degreeTypeRepository = new DegreeTypeRepositoryImpl(factoryDegreeType, listFactoryDegreeType);

        //act
        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController controller = new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(programmeDDDRepositoryImpl, studyPlanDDDRepo, degreeTypeRepository);

        //assert
        assertNotNull(controller);
    }

    @Test
    void createStudyPlanDDDWithSuccess() throws Exception {
        // Arrange
        ProgrammeRepositoryImpl programmeRepo = mock(ProgrammeRepositoryImpl.class);
        StudyPlanRepositoryImpl studyPlanRepo = mock(StudyPlanRepositoryImpl.class);
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        DegreeTypeRepositoryImpl degreeTypeRepository = mock(DegreeTypeRepositoryImpl.class);
        Acronym acronym = mock(Acronym.class);

        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController controller =
                new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(programmeRepo, studyPlanRepo, degreeTypeRepository);
        controller._studyPlanDDDRepo = studyPlanRepo;

        Programme programme = mock(Programme.class);
        DegreeType degreeType = mock(DegreeType.class);
        QuantSemesters quantSemesters = mock(QuantSemesters.class);

        when(degreeType.getMaxEcts()).thenReturn(180);

        when(programme.getQuantSemesters()).thenReturn(quantSemesters);
        when(quantSemesters.getQuantityOfSemesters()).thenReturn(6);

        String dgtID = "Licenciatura";
        DegreeTypeID degreeTypeID = new DegreeTypeID(dgtID);

        ProgrammeID programmeID = new ProgrammeID(name, acronym);
        Date implementationDate = new Date("21-03-2000");

        when(programme.getDegreeTypeID()).thenReturn(degreeTypeID);
        when(programmeRepo.ofIdentity(programmeID)).thenReturn(Optional.of(programme));
        when(degreeTypeRepository.ofIdentity(degreeTypeID)).thenReturn(Optional.of(degreeType));

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
        DegreeTypeRepositoryImpl degreeTypeRepository = mock(DegreeTypeRepositoryImpl.class);
        Acronym acronym = mock(Acronym.class);

        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController controller =
                new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(programmeRepo, studyPlanRepo, degreeTypeRepository);
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
    void createStudyPlanDegreeTypeNotFound() throws Exception {
        // Arrange
        ProgrammeRepositoryImpl programmeRepo = mock(ProgrammeRepositoryImpl.class);
        StudyPlanRepositoryImpl studyPlanRepo = mock(StudyPlanRepositoryImpl.class);
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        DegreeTypeRepositoryImpl degreeTypeRepository = mock(DegreeTypeRepositoryImpl.class);
        Acronym acronym = mock(Acronym.class);

        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController controller =
                new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(programmeRepo, studyPlanRepo, degreeTypeRepository);
        controller._studyPlanDDDRepo = studyPlanRepo;

        ProgrammeID programmeID = new ProgrammeID(name, acronym);
        Date implementationDate = new Date("21-03-2000");

        Programme programme =mock(Programme.class);

        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);

        when(programmeRepo.ofIdentity(programmeID)).thenReturn(Optional.of(programme));
        when(degreeTypeRepository.ofIdentity(degreeTypeID)).thenReturn(Optional.empty());

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
        DegreeTypeRepositoryImpl degreeTypeRepository = mock(DegreeTypeRepositoryImpl.class);

        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController controller =
                new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(programmeRepo, studyPlanRepo, degreeTypeRepository);
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

        IDegreeTypeFactory factoryDegreeType = new DegreeTypeFactoryImpl();
        IDegreeTypeListFactory listFactoryDegreeType = new DegreeTypeListFactoryImpl();
        DegreeTypeRepositoryImpl degreeTypeRepository = new DegreeTypeRepositoryImpl(factoryDegreeType, listFactoryDegreeType);

        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController controller = new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(iProgrammeRepository, iStudyPlanRepository, degreeTypeRepository);

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
        MaxEcts maxEcts = new MaxEcts(180);
        Name name = new Name("Licenciatura");
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

        IDegreeTypeFactory factoryDegreeType = new DegreeTypeFactoryImpl();
        IDegreeTypeListFactory listFactoryDegreeType = new DegreeTypeListFactoryImpl();
        DegreeTypeRepositoryImpl degreeTypeRepository = new DegreeTypeRepositoryImpl(factoryDegreeType, listFactoryDegreeType);

        degreeTypeRepository.registerDegreeType(degreeTypeID, name, maxEcts);

        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController controller = new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(iProgrammeRepository, iStudyPlanRepository, degreeTypeRepository);
        controller.registerAProgrammeDDDInTheSystem(nameWithNumbersAndSpecialChars, acronym, quantEcts, quantSemesters, degreeTypeID, departmentID, teacherID);

        //act
        boolean result = controller.createStudyPlanDDD(programmeID, implemtationDate);

        //assert
        assertTrue(result);
    }
}