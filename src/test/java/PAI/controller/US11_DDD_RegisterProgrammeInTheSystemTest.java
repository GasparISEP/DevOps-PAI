package PAI.controller;

import PAI.VOs.*;
import PAI.domain.Department;
import PAI.domain.programme.IProgrammeDDDFactory;
import PAI.repository.programmeRepo.IProgrammeDDDRepositoryListFactory;
import PAI.repository.programmeRepo.ProgrammeDDDRepositoryImpl;
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

}