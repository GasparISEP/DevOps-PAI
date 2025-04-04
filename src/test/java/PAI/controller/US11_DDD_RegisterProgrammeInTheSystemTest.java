package PAI.controller;

import PAI.VOs.*;
import PAI.domain.Department;
import PAI.domain.programme.IProgrammeDDDFactory;
import PAI.domain.programme.ProgrammeDDDFactoryImpl;
import PAI.domain.studyPlan.IStudyPlanDDDFactory;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class US11_DDD_RegisterProgrammeInTheSystemTest {

    @Test
    void registerProgrammeInTheSystemControllerFailureWithNullProgrammeRepo() throws Exception {
        //arrange
        ProgrammeDDDRepositoryImpl programmeDDDRepositoryImpl = null;

        //act + assert
        Exception exception = assertThrows(Exception.class, () ->
                new US11_DDD_RegisterProgrammeInTheSystem(programmeDDDRepositoryImpl));

        assertEquals("Programme Repository cannot be null.", exception.getMessage());

    }

    @Test
    void registerProgrammeInTheSystemControllerFailureWithNullStudyPlanRepo() throws Exception {
        //arrange
        IProgrammeDDDFactory iProgrammeDDDFactory = mock(IProgrammeDDDFactory.class);
        IProgrammeDDDRepositoryListFactory iProgrammeDDDRepoListFactory = mock(IProgrammeDDDRepositoryListFactory.class);
        ProgrammeDDDRepositoryImpl programmeDDDRepositoryImpl = new ProgrammeDDDRepositoryImpl(iProgrammeDDDFactory, iProgrammeDDDRepoListFactory);

        //act
        US11_DDD_RegisterProgrammeInTheSystem controller = new US11_DDD_RegisterProgrammeInTheSystem(programmeDDDRepositoryImpl);

        //assert
        assertNotNull(controller);
    }

    @Test
    void registerProgrammeInTheSystemWithSuccess() throws Exception {
        //arrange
        ProgrammeDDDRepositoryImpl programmeRepo = mock(ProgrammeDDDRepositoryImpl.class);

        US11_DDD_RegisterProgrammeInTheSystem controller =
                new US11_DDD_RegisterProgrammeInTheSystem(programmeRepo);
        controller._programmeDDDList = programmeRepo;

        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);

        //act
        boolean result = controller.registerAProgrammeDDDInTheSystem(name, acronym, qtyEcts,qtySemesters,degreeTypeID,department1, programmeDirectorID);

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
        IProgrammeDDDRepository iProgrammeDDDRepository = new ProgrammeDDDRepositoryImpl(iProgrammeDDDFactory,iProgrammeDDDRepositoryListFactory);

        US11_DDD_RegisterProgrammeInTheSystem controller = new US11_DDD_RegisterProgrammeInTheSystem(iProgrammeDDDRepository);

        //act
        boolean result = controller.registerAProgrammeDDDInTheSystem(nameWithNumbersAndSpecialChars,acronym,quantEcts,quantSemesters,degreeTypeID,departmentID,teacherID);

        //assert
        assertTrue(result);
    }
}