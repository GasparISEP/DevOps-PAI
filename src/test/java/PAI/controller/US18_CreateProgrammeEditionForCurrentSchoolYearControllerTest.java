package PAI.controller;

import PAI.VOs.*;
import PAI.domain.programme.IProgrammeDDDFactory;
import PAI.domain.programme.ProgrammeDDDFactoryImpl;
import PAI.domain.programmeEdition.IProgrammeEditionDDDFactory;
import PAI.domain.programmeEdition.ProgrammeEditionDDDFactoryImpl;
import PAI.factory.ISchoolYearFactory;
import PAI.factory.ISchoolYearListFactory;
import PAI.factory.SchoolYearFactoryImpl;
import PAI.factory.SchoolYearListFactoryImpl;
import PAI.repository.ISchoolYearRepository;
import PAI.repository.SchoolYearRepository;
import PAI.repository.programmeEditionRepository.IProgrammeEditionDDDListFactory;
import PAI.repository.programmeEditionRepository.IProgrammeEditionRepositoryDDD;
import PAI.repository.programmeEditionRepository.ProgrammeEditionDDDListFactoryImpl;
import PAI.repository.programmeEditionRepository.ProgrammeEditionRepositoryDDDImpl;
import PAI.repository.programmeRepo.IProgrammeDDDRepository;
import PAI.repository.programmeRepo.IProgrammeDDDRepositoryListFactory;
import PAI.repository.programmeRepo.ProgrammeDDDRepositoryImpl;
import PAI.repository.programmeRepo.ProgrammeDDDRepositoryListFactoryImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class US18_CreateProgrammeEditionForCurrentSchoolYearControllerTest {

    //Constructor Tests
    @Test
    void shouldCreateController() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionRepositoryListFactory = new ProgrammeEditionDDDListFactoryImpl();
        IProgrammeEditionDDDFactory programmeEditionFactory = new ProgrammeEditionDDDFactoryImpl();
        IProgrammeEditionRepositoryDDD ProgrammeEditionRepository = new ProgrammeEditionRepositoryDDDImpl(programmeEditionRepositoryListFactory, programmeEditionFactory);

        IProgrammeDDDRepositoryListFactory programmeRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDFactory programmeFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepository programmeRepository = new ProgrammeDDDRepositoryImpl(programmeFactory, programmeRepositoryListFactory);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactory, schoolYearRepositoryListFactory);

        // Act
        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(ProgrammeEditionRepository, schoolYearRepository, programmeRepository);

        // Assert
        assertNotNull(controller);
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionRepositoryIsNull() {
        // Arrange
        IProgrammeEditionRepositoryDDD ProgrammeEditionRepository = null;

        IProgrammeDDDRepositoryListFactory programmeRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDFactory programmeFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepository programmeRepository = new ProgrammeDDDRepositoryImpl(programmeFactory, programmeRepositoryListFactory);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactory, schoolYearRepositoryListFactory);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {new US18_CreateProgrammeEditionForCurrentSchoolYearController(ProgrammeEditionRepository, schoolYearRepository, programmeRepository);});

        // Assert
        assertEquals("Programme Edition Repository cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenSchoolYearIsNull() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionRepositoryListFactory = new ProgrammeEditionDDDListFactoryImpl();
        IProgrammeEditionDDDFactory programmeEditionFactory = new ProgrammeEditionDDDFactoryImpl();
        IProgrammeEditionRepositoryDDD ProgrammeEditionRepository = new ProgrammeEditionRepositoryDDDImpl(programmeEditionRepositoryListFactory, programmeEditionFactory);

        IProgrammeDDDRepositoryListFactory programmeRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDFactory programmeFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepository programmeRepository = new ProgrammeDDDRepositoryImpl(programmeFactory, programmeRepositoryListFactory);

        ISchoolYearRepository schoolYearRepository = null;

        // Act
        Exception exception = assertThrows(Exception.class, () -> {new US18_CreateProgrammeEditionForCurrentSchoolYearController(ProgrammeEditionRepository, schoolYearRepository, programmeRepository);});

        // Assert
        assertEquals("School Year Repository cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenProgrammeRepositoryIsNull() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionRepositoryListFactory = new ProgrammeEditionDDDListFactoryImpl();
        IProgrammeEditionDDDFactory programmeEditionFactory = new ProgrammeEditionDDDFactoryImpl();
        IProgrammeEditionRepositoryDDD ProgrammeEditionRepository = new ProgrammeEditionRepositoryDDDImpl(programmeEditionRepositoryListFactory, programmeEditionFactory);

        IProgrammeDDDRepository programmeRepository = null;

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactory, schoolYearRepositoryListFactory);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {new US18_CreateProgrammeEditionForCurrentSchoolYearController(ProgrammeEditionRepository, schoolYearRepository, programmeRepository);});

        // Assert
        assertEquals("Programme Repository cannot be null", exception.getMessage());
    }

    @Test
    void shouldReturnListOfNamesOfAllExistingProgrammes() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionRepositoryListFactory = new ProgrammeEditionDDDListFactoryImpl();
        IProgrammeEditionDDDFactory programmeEditionFactory = new ProgrammeEditionDDDFactoryImpl();
        IProgrammeEditionRepositoryDDD ProgrammeEditionRepository = new ProgrammeEditionRepositoryDDDImpl(programmeEditionRepositoryListFactory, programmeEditionFactory);

        IProgrammeDDDRepositoryListFactory programmeRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDFactory programmeFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepository programmeRepository = new ProgrammeDDDRepositoryImpl(programmeFactory, programmeRepositoryListFactory);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactory, schoolYearRepositoryListFactory);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(ProgrammeEditionRepository, schoolYearRepository, programmeRepository);

        NameWithNumbersAndSpecialChars programmeName1 = new NameWithNumbersAndSpecialChars("Licenciatura em Engenharia Informatica");
        Acronym programmeAcronym1 = new Acronym("LEI");
        NameWithNumbersAndSpecialChars programmeName2 = new NameWithNumbersAndSpecialChars("Licenciatura em Engenharia Espacial");
        Acronym programmeAcronym2 = new Acronym("LEE");
        NameWithNumbersAndSpecialChars programmeName3 = new NameWithNumbersAndSpecialChars("Licenciatura em Engenharia Agricula");
        Acronym programmeAcronym3 = new Acronym("LEA");
        QuantEcts quantEcts = new QuantEcts(30);
        QuantSemesters quantSemesters = new QuantSemesters(4);
        DegreeTypeID degreeTypeID = new DegreeTypeID("Licenciatura");
        DepartmentAcronym departmentAcronym = new DepartmentAcronym("DEI");
        DepartmentID departmentID = new DepartmentID(departmentAcronym);
        TeacherAcronym teacherAcronym = new TeacherAcronym("JFC");
        TeacherID teacherID = new TeacherID(teacherAcronym);

        programmeRepository.registerProgramme(programmeName1, programmeAcronym1, quantEcts, quantSemesters, degreeTypeID, departmentID, teacherID);
        programmeRepository.registerProgramme(programmeName2, programmeAcronym2, quantEcts, quantSemesters, degreeTypeID, departmentID, teacherID);
        programmeRepository.registerProgramme(programmeName3, programmeAcronym3, quantEcts, quantSemesters, degreeTypeID, departmentID, teacherID);

        // Act
        List<NameWithNumbersAndSpecialChars> listToTest = controller.getAllProgrammeNames();

        // Assert
        assertNotNull(listToTest);
        assertEquals(3, listToTest.size());
        assertEquals(programmeName1, listToTest.get(0));
        assertEquals(programmeName2, listToTest.get(1));
        assertEquals(programmeName3, listToTest.get(2));
    }

    @Test
    void shouldReturnEmptyListOfNamesIfProgrammeRepositoryIsEmpty() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionRepositoryListFactory = new ProgrammeEditionDDDListFactoryImpl();
        IProgrammeEditionDDDFactory programmeEditionFactory = new ProgrammeEditionDDDFactoryImpl();
        IProgrammeEditionRepositoryDDD ProgrammeEditionRepository = new ProgrammeEditionRepositoryDDDImpl(programmeEditionRepositoryListFactory, programmeEditionFactory);

        IProgrammeDDDRepositoryListFactory programmeRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDFactory programmeFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepository programmeRepository = new ProgrammeDDDRepositoryImpl(programmeFactory, programmeRepositoryListFactory);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactory, schoolYearRepositoryListFactory);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(ProgrammeEditionRepository, schoolYearRepository, programmeRepository);

        // Act
        List<NameWithNumbersAndSpecialChars> listToTest = controller.getAllProgrammeNames();

        // Assert
        assertNotNull(listToTest);
        assertEquals(0, listToTest.size());
    }

    @Test
    void shouldNotCreateProgrammeEditionIfThereIsNoCurrentSchoolYearInTheSystem() {

    }

    @Test
    void shouldReturnAListOfAllProgrammeNamesFromRProgrammeList () {

    }

    @Test
    void shouldReturnAnEmptyListOfProgrammeNamesFromRepositoryIfProgrammeListIsNull() {
    }
}