package PAI.controller;

import PAI.VOs.*;
import PAI.domain.programme.IProgrammeFactory;
import PAI.domain.programme.ProgrammeFactoryImpl;
import PAI.repository.programmeRepository.IProgrammeRepository;
import PAI.repository.programmeRepository.IProgrammeRepositoryListFactory;
import PAI.repository.programmeRepository.ProgrammeRepositoryImpl;
import PAI.repository.programmeRepository.ProgrammeRepositoryListFactoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class US11__RegisterProgrammeInTheSystemControllerTest {

    @Test
    void registerProgrammeInTheSystemControllerFailureWithNullProgrammeRepo() throws Exception {
        //arrange
        ProgrammeRepositoryImpl programmeDDDRepositoryImpl = null;

        //act + assert
        Exception exception = assertThrows(Exception.class, () ->
                new US11_RegisterProgrammeInTheSystemController(programmeDDDRepositoryImpl));

        assertEquals("Programme Repository cannot be null.", exception.getMessage());

    }

    @Test
    void registerProgrammeInTheSystemControllerFailureWithNullStudyPlanRepo() throws Exception {
        //arrange
        IProgrammeFactory iProgrammeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory iProgrammeDDDRepoListFactory = mock(IProgrammeRepositoryListFactory.class);
        ProgrammeRepositoryImpl programmeDDDRepositoryImpl = new ProgrammeRepositoryImpl(iProgrammeFactory, iProgrammeDDDRepoListFactory);

        //act
        US11_RegisterProgrammeInTheSystemController controller = new US11_RegisterProgrammeInTheSystemController(programmeDDDRepositoryImpl);

        //assert
        assertNotNull(controller);
    }

    @Test
    void registerProgrammeInTheSystemWithSuccess() throws Exception {
        //arrange
        ProgrammeRepositoryImpl programmeRepo = mock(ProgrammeRepositoryImpl.class);

        US11_RegisterProgrammeInTheSystemController controller =
                new US11_RegisterProgrammeInTheSystemController(programmeRepo);
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

        IProgrammeFactory iProgrammeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepositoryListFactory iProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeRepository iProgrammeRepository = new ProgrammeRepositoryImpl(iProgrammeFactory, iProgrammeRepositoryListFactory);

        US11_RegisterProgrammeInTheSystemController controller = new US11_RegisterProgrammeInTheSystemController(iProgrammeRepository);

        //act
        boolean result = controller.registerAProgrammeDDDInTheSystem(nameWithNumbersAndSpecialChars,acronym,quantEcts,quantSemesters,degreeTypeID,departmentID,teacherID);

        //assert
        assertTrue(result);
    }
}