package PAI.controller;

import PAI.VOs.*;
import PAI.domain.SchoolYear;
import PAI.domain.programme.IProgrammeFactory;
import PAI.domain.programme.Programme;
import PAI.domain.programme.ProgrammeFactoryImpl;
import PAI.domain.programmeEdition.IProgrammeEditionFactory;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.domain.programmeEdition.ProgrammeEditionFactoryImpl;
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

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

class US18_CreateProgrammeEditionForCurrentSchoolYearControllerTest {

    //Constructor Tests
    @Test
    void shouldCreateController() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionRepositoryListFactory = new ProgrammeEditionDDDListFactoryImpl();
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepositoryDDD programmeEditionRepository = new ProgrammeEditionRepositoryDDDImpl(programmeEditionRepositoryListFactory, programmeEditionFactory);

        IProgrammeDDDRepositoryListFactory programmeRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeDDDRepository programmeRepository = new ProgrammeDDDRepositoryImpl(programmeFactory, programmeRepositoryListFactory);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactory, schoolYearRepositoryListFactory);

        // Act
        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionRepository, schoolYearRepository, programmeRepository);

        // Assert
        assertNotNull(controller);
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionRepositoryIsNull() {
        // Arrange
        IProgrammeEditionRepositoryDDD ProgrammeEditionRepository = null;

        IProgrammeDDDRepositoryListFactory programmeRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
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
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepositoryDDD ProgrammeEditionRepository = new ProgrammeEditionRepositoryDDDImpl(programmeEditionRepositoryListFactory, programmeEditionFactory);

        IProgrammeDDDRepositoryListFactory programmeRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
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
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepositoryDDD programmeEditionRepository = new ProgrammeEditionRepositoryDDDImpl(programmeEditionRepositoryListFactory, programmeEditionFactory);

        IProgrammeDDDRepository programmeRepository = null;

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactory, schoolYearRepositoryListFactory);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionRepository, schoolYearRepository, programmeRepository);});

        // Assert
        assertEquals("Programme Repository cannot be null", exception.getMessage());
    }

    @Test
    void shouldReturnListOfNamesOfAllExistingProgrammes() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionRepositoryListFactory = new ProgrammeEditionDDDListFactoryImpl();
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepositoryDDD programmeEditionRepository = new ProgrammeEditionRepositoryDDDImpl(programmeEditionRepositoryListFactory, programmeEditionFactory);

        IProgrammeDDDRepositoryListFactory programmeRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeDDDRepository programmeRepository = new ProgrammeDDDRepositoryImpl(programmeFactory, programmeRepositoryListFactory);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactory, schoolYearRepositoryListFactory);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionRepository, schoolYearRepository, programmeRepository);

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
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepositoryDDD programmeEditionRepository = new ProgrammeEditionRepositoryDDDImpl(programmeEditionRepositoryListFactory, programmeEditionFactory);

        IProgrammeDDDRepositoryListFactory programmeRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeDDDRepository programmeRepository = new ProgrammeDDDRepositoryImpl(programmeFactory, programmeRepositoryListFactory);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactory, schoolYearRepositoryListFactory);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionRepository, schoolYearRepository, programmeRepository);

        // Act
        List<NameWithNumbersAndSpecialChars> listToTest = controller.getAllProgrammeNames();

        // Assert
        assertNotNull(listToTest);
        assertEquals(0, listToTest.size());
    }

    @Test
    void shouldReturnFalseIfThereIsNoCurrentSchoolYearInTheSystem() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionRepositoryListFactory = new ProgrammeEditionDDDListFactoryImpl();
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepositoryDDD programmeEditionRepository = new ProgrammeEditionRepositoryDDDImpl(programmeEditionRepositoryListFactory, programmeEditionFactory);

        IProgrammeDDDRepositoryListFactory programmeRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeDDDRepository programmeRepository = new ProgrammeDDDRepositoryImpl(programmeFactory, programmeRepositoryListFactory);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactory, schoolYearRepositoryListFactory);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionRepository, schoolYearRepository, programmeRepository);

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

        Description description1 = new Description("2023/2024");
        Date startDate1 = new Date("01-09-2023");
        Date endDate1 = new Date("31-08-2024");
        Description description3 = new Description("2025/2026");
        Date startDate3 = new Date("01-09-2025");
        Date endDate3 = new Date("31-08-2026");
        schoolYearRepository.addSchoolYear(description1, startDate1, endDate1);
        schoolYearRepository.addSchoolYear(description3, startDate3, endDate3);

        // Act
        boolean result = controller.createAProgrammeEditionForTheCurrentSchoolYear(programmeName2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfThereIsNoProgrammeInTheSystemWithTheNameGiven() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionRepositoryListFactory = new ProgrammeEditionDDDListFactoryImpl();
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepositoryDDD programmeEditionRepository = new ProgrammeEditionRepositoryDDDImpl(programmeEditionRepositoryListFactory, programmeEditionFactory);

        IProgrammeDDDRepositoryListFactory programmeRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeDDDRepository programmeRepository = new ProgrammeDDDRepositoryImpl(programmeFactory, programmeRepositoryListFactory);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactory, schoolYearRepositoryListFactory);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionRepository, schoolYearRepository, programmeRepository);

        NameWithNumbersAndSpecialChars programmeName1 = new NameWithNumbersAndSpecialChars("Licenciatura em Engenharia Informatica");
        Acronym programmeAcronym1 = new Acronym("LEI");
        NameWithNumbersAndSpecialChars programmeName2 = new NameWithNumbersAndSpecialChars("Licenciatura em Engenharia Espacial");
        Acronym programmeAcronym2 = new Acronym("LEE");
        NameWithNumbersAndSpecialChars programmeName3 = new NameWithNumbersAndSpecialChars("Licenciatura em Engenharia Agricula");
        Acronym programmeAcronym3 = new Acronym("LEA");
        NameWithNumbersAndSpecialChars programmeName4 = new NameWithNumbersAndSpecialChars("Licenciatura em Artes Do Pensamento Inexistente");
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

        Description description1 = new Description("2023/2024");
        Date startDate1 = new Date("01-09-2023");
        Date endDate1 = new Date("31-08-2024");
        Description description2 = new Description("2024/2025");
        Date startDate2 = new Date("01-09-2024");
        Date endDate2 = new Date("31-08-2025");
        Description description3 = new Description("2025/2026");
        Date startDate3 = new Date("01-09-2025");
        Date endDate3 = new Date("31-08-2026");
        schoolYearRepository.addSchoolYear(description1, startDate1, endDate1);
        schoolYearRepository.addSchoolYear(description2, startDate2, endDate2);
        schoolYearRepository.addSchoolYear(description3, startDate3, endDate3);

        // Act
        boolean result = controller.createAProgrammeEditionForTheCurrentSchoolYear(programmeName4);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfAlreadyExistAProgrammeEditionInTheCurrentSchoolYearWithProgrammeNameGiven() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionRepositoryListFactory = new ProgrammeEditionDDDListFactoryImpl();
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepositoryDDD programmeEditionRepository = new ProgrammeEditionRepositoryDDDImpl(programmeEditionRepositoryListFactory, programmeEditionFactory);

        IProgrammeDDDRepositoryListFactory programmeRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeDDDRepository programmeRepository = new ProgrammeDDDRepositoryImpl(programmeFactory, programmeRepositoryListFactory);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactory, schoolYearRepositoryListFactory);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionRepository, schoolYearRepository, programmeRepository);

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

        Description description1 = new Description("2023/2024");
        Date startDate1 = new Date("01-09-2023");
        Date endDate1 = new Date("31-08-2024");
        Description description2 = new Description("2024/2025");
        Date startDate2 = new Date("01-09-2024");
        Date endDate2 = new Date("31-08-2025");
        Description description3 = new Description("2025/2026");
        Date startDate3 = new Date("01-09-2025");
        Date endDate3 = new Date("31-08-2026");
        schoolYearRepository.addSchoolYear(description1, startDate1, endDate1);
        schoolYearRepository.addSchoolYear(description2, startDate2, endDate2);
        schoolYearRepository.addSchoolYear(description3, startDate3, endDate3);

        Optional<Programme> programmeOpt1= programmeRepository.getProgrammeByName(programmeName1);
        Optional<Programme> programmeOpt2= programmeRepository.getProgrammeByName(programmeName2);
        Optional<Programme> programmeOpt3= programmeRepository.getProgrammeByName(programmeName3);


        Programme programme1 = programmeOpt1.orElse(null);
        ProgrammeID pID1 = programme1.identity();

        Programme programme2 = programmeOpt2.orElse(null);
        ProgrammeID pID2 = programme2.identity();

        Programme programme3 = programmeOpt3.orElse(null);
        ProgrammeID pID3 = programme3.identity();


        SchoolYear currentSY = schoolYearRepository.getCurrentSchoolYear();
        SchoolYearID sYID = currentSY.identity();

        programmeEditionRepository.createProgrammeEdition(pID1, sYID);
        programmeEditionRepository.createProgrammeEdition(pID2, sYID);
        programmeEditionRepository.createProgrammeEdition(pID3, sYID);

        // Act
        boolean result = controller.createAProgrammeEditionForTheCurrentSchoolYear(programmeName3);

        // Assert
        List<ProgrammeEdition> list = StreamSupport
                .stream(programmeEditionRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        assertEquals(new HashSet<>(list), programmeEditionRepository.findAll());
        assertEquals(3, list.size());
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfTheProgrammeEditionIsCreatedInTheCurrentSchoolYearAndRegisteredInTheSystem() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionRepositoryListFactory = new ProgrammeEditionDDDListFactoryImpl();
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepositoryDDD programmeEditionRepository = new ProgrammeEditionRepositoryDDDImpl(programmeEditionRepositoryListFactory, programmeEditionFactory);

        IProgrammeDDDRepositoryListFactory programmeRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeDDDRepository programmeRepository = new ProgrammeDDDRepositoryImpl(programmeFactory, programmeRepositoryListFactory);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactory, schoolYearRepositoryListFactory);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionRepository, schoolYearRepository, programmeRepository);

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

        Description description1 = new Description("2023/2024");
        Date startDate1 = new Date("01-09-2023");
        Date endDate1 = new Date("31-08-2024");
        Description description2 = new Description("2024/2025");
        Date startDate2 = new Date("01-09-2024");
        Date endDate2 = new Date("31-08-2025");
        Description description3 = new Description("2025/2026");
        Date startDate3 = new Date("01-09-2025");
        Date endDate3 = new Date("31-08-2026");
        schoolYearRepository.addSchoolYear(description1, startDate1, endDate1);
        schoolYearRepository.addSchoolYear(description2, startDate2, endDate2);
        schoolYearRepository.addSchoolYear(description3, startDate3, endDate3);

        Optional<Programme> programmeOpt1= programmeRepository.getProgrammeByName(programmeName1);
        Optional<Programme> programmeOpt2= programmeRepository.getProgrammeByName(programmeName2);
        Optional<Programme> programmeOpt3= programmeRepository.getProgrammeByName(programmeName3);


        Programme programme1 = programmeOpt1.orElse(null);
        ProgrammeID pID1 = programme1.identity();

        Programme programme2 = programmeOpt2.orElse(null);
        ProgrammeID pID2 = programme2.identity();

        Programme programme3 = programmeOpt3.orElse(null);
        ProgrammeID pID3 = programme3.identity();


        SchoolYear currentSY = schoolYearRepository.getCurrentSchoolYear();
        SchoolYearID sYID = currentSY.identity();

        programmeEditionRepository.createProgrammeEdition(pID1, sYID);
        programmeEditionRepository.createProgrammeEdition(pID2, sYID);

        // Act
        boolean result = controller.createAProgrammeEditionForTheCurrentSchoolYear(programmeName3);

        // Assert
        List<ProgrammeEdition> list = StreamSupport
                .stream(programmeEditionRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        assertEquals(new HashSet<>(list), programmeEditionRepository.findAll());
        assertEquals(3, list.size());
        assertTrue(result);
    }
}