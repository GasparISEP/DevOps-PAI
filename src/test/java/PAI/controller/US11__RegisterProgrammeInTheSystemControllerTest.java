package PAI.controller;

import PAI.VOs.*;
import PAI.factory.IProgrammeFactory;
import PAI.factory.ProgrammeFactoryImpl;
import PAI.repository.programmeRepository.IProgrammeRepository;
import PAI.persistence.mem.programmeEdition.IProgrammeRepositoryListFactory;
import PAI.persistence.mem.programmeEdition.ProgrammeRepositoryImpl;
import PAI.persistence.mem.programmeEdition.ProgrammeRepositoryListFactoryImpl;
import PAI.service.IProgrammeService;
import PAI.service.ProgrammeService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US11__RegisterProgrammeInTheSystemControllerTest {

    @Test
    void registerProgrammeInTheSystemControllerFailureWithNullProgrammeRepo() throws Exception {
        //arrange
        IProgrammeService programmeService = null;

        //act + assert
        Exception exception = assertThrows(Exception.class, () ->
                new US11_RegisterProgrammeInTheSystemController(programmeService));

        assertEquals("Programme Service cannot be null.", exception.getMessage());


    }

    @Test
    void registerProgrammeInTheSystemWithSuccess() throws Exception {
        //arrange
        IProgrammeService programmeService = mock(IProgrammeService.class);

        US11_RegisterProgrammeInTheSystemController controller =
                new US11_RegisterProgrammeInTheSystemController(programmeService);

        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID departmentID = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);

        // dizer ao mock o que deve devolver
        when(programmeService.registerProgramme(name, acronym, qtyEcts, qtySemesters, degreeTypeID, departmentID, programmeDirectorID))
                .thenReturn(true);

        // act
        boolean result = controller.registerProgramme(name, acronym, qtyEcts, qtySemesters, degreeTypeID, departmentID, programmeDirectorID);

        // assert
        assertTrue(result);
    }

    @Test
    void registerProgrammeInTheSystemSuccessIntegrationTest() throws Exception {
        // arrange
        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("ABC");
        Acronym acronym = new Acronym("ABC");
        QuantEcts quantEcts = new QuantEcts(12);
        QuantSemesters quantSemesters = new QuantSemesters(2);
        DegreeTypeID degreeTypeID = new DegreeTypeID("123456789");
        DepartmentAcronym departmentAcronym = new DepartmentAcronym("ALG");
        DepartmentID departmentID = new DepartmentID(departmentAcronym);
        TeacherAcronym teacherAcronym = new TeacherAcronym("ALP");
        TeacherID teacherID = new TeacherID(teacherAcronym);

        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepositoryListFactory programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeFactory, programmeRepositoryListFactory);


        IProgrammeService programmeService = new ProgrammeService(programmeFactory, programmeRepository, null);


        US11_RegisterProgrammeInTheSystemController controller = new US11_RegisterProgrammeInTheSystemController(programmeService);

        // act
        boolean result = controller.registerProgramme(nameWithNumbersAndSpecialChars, acronym, quantEcts, quantSemesters, degreeTypeID, departmentID, teacherID);

        // assert
        assertTrue(result);
    }

}