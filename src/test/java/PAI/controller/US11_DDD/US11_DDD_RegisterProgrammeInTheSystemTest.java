package PAI.controller.US11_DDD;

import PAI.VOs.*;
import PAI.domain.Department;
import PAI.domain.programme.IProgrammeDDDFactory;
import PAI.repository.programmeRepo.IProgrammeDDDRepositoryListFactory;
import PAI.repository.programmeRepo.ProgrammeDDDRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class US11_DDD_RegisterProgrammeInTheSystemTest {

    @Test
    void registerProgrammeInTheSystemControllerFailureWithNullProgrammeRepo() throws Exception {
        //arrange
        ProgrammeDDDRepository programmeDDDRepository = null;

        //act + assert
        Exception exception = assertThrows(Exception.class, () ->
                new US11_DDD_RegisterProgrammeInTheSystem(programmeDDDRepository));

        assertEquals("Programme Repository cannot be null.", exception.getMessage());

    }

    @Test
    void registerProgrammeInTheSystemControllerFailureWithNullStudyPlanRepo() throws Exception {
        //arrange
        IProgrammeDDDFactory iProgrammeDDDFactory = mock(IProgrammeDDDFactory.class);
        IProgrammeDDDRepositoryListFactory iProgrammeDDDRepoListFactory = mock(IProgrammeDDDRepositoryListFactory.class);
        ProgrammeDDDRepository programmeDDDRepository = new ProgrammeDDDRepository(iProgrammeDDDFactory, iProgrammeDDDRepoListFactory);

        //act
        US11_DDD_RegisterProgrammeInTheSystem controller = new US11_DDD_RegisterProgrammeInTheSystem(programmeDDDRepository);

        //assert
        assertNotNull(controller);
    }

    @Test
    void registerProgrammeInTheSystemWithSuccess() throws Exception {
        //arrange
        ProgrammeDDDRepository programmeRepo = mock(ProgrammeDDDRepository.class);

        US11_DDD_RegisterProgrammeInTheSystem controller =
                new US11_DDD_RegisterProgrammeInTheSystem(programmeRepo);
        controller._programmeDDDList = programmeRepo;

        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeType_ID degreeTypeID = mock(DegreeType_ID.class);
        Department department1 = mock(Department.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);

        //act
        boolean result = controller.registerAProgrammeDDDInTheSystem(name, acronym, qtyEcts,qtySemesters,degreeTypeID,department1, programmeDirectorID);

        //assert
        assertTrue(result);
    }

}