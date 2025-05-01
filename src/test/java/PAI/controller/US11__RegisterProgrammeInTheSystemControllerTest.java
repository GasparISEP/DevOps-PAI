package PAI.controller;

import PAI.VOs.*;
import PAI.factory.IProgrammeFactory;
import PAI.factory.ProgrammeFactoryImpl;
import PAI.persistence.mem.programme.IProgrammeRepositoryListFactory;
import PAI.persistence.mem.programme.ProgrammeRepositoryImpl;
import PAI.persistence.mem.programme.ProgrammeRepositoryListFactoryImpl;
import PAI.repository.programmeRepository.IProgrammeRepository;
import PAI.service.programme.IProgrammeService;
import PAI.service.programme.ProgrammeServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class US11__RegisterProgrammeInTheSystemControllerTest {

    @Test
    void registerProgrammeInTheSystemControllerFailureWithNullProgrammeService() throws Exception {
        // arrange
        IProgrammeService programmeService = null;

        // act + assert
        Exception exception = assertThrows(Exception.class, () ->
                new US11_RegisterProgrammeInTheSystemController(programmeService));

        assertEquals("Programme Service cannot be null.", exception.getMessage());
    }

    @Test
    void registerProgrammeInTheSystemSuccessIntegrationTest() throws Exception {
        // arrange
        String name = "ABC";
        String acronym = "ABC";
        int quantityOfEcts = 12;
        int quantityOfSemesters = 2;
        DegreeTypeID degreeTypeID = new DegreeTypeID("123456789");
        DepartmentID departmentID = new DepartmentID(new DepartmentAcronym("ALG"));
        TeacherID teacherID = new TeacherID(new TeacherAcronym("ALP"));

        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepositoryListFactory programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeRepositoryListFactory);
        IProgrammeService programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository);

        US11_RegisterProgrammeInTheSystemController controller = new US11_RegisterProgrammeInTheSystemController(programmeService);

        // act
        boolean result = controller.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters,
                degreeTypeID, departmentID, teacherID);

        // assert
        assertTrue(result);
    }
}