package PAI.controller;

import PAI.VOs.*;
import PAI.assembler.programme.IProgrammeAssembler;
import PAI.assembler.programme.ProgrammeAssembler;
import PAI.assembler.programmeEdition.IProgrammeEditionAssembler;
import PAI.assembler.programmeEdition.ProgrammeEditionAssemblerImpl;
import PAI.domain.degreeType.DegreeType;
import PAI.domain.department.Department;
import PAI.domain.repositoryInterfaces.degreeType.IDegreeTypeRepository;
import PAI.domain.repositoryInterfaces.department.IDepartmentRepository;
import PAI.domain.repositoryInterfaces.programmeEditionEnrolment.IProgrammeEditionEnrolmentRepository;
import PAI.domain.repositoryInterfaces.teacher.ITeacherRepository;
import PAI.domain.schoolYear.ISchoolYearFactory;
import PAI.domain.schoolYear.SchoolYear;
import PAI.domain.programme.Programme;
import PAI.domain.programmeEdition.IProgrammeEditionFactory;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.domain.programmeEdition.ProgrammeEditionFactoryImpl;
import PAI.domain.schoolYear.SchoolYearFactoryImpl;
import PAI.domain.programme.IProgrammeFactory;
import PAI.domain.programme.ProgrammeFactoryImpl;
import PAI.assembler.schoolYear.ISchoolYearAssembler;
import PAI.assembler.schoolYear.SchoolYearAssembler;
import PAI.domain.teacher.Teacher;
import PAI.dto.Programme.ProgrammeVOsDTO;
import PAI.persistence.mem.degreeType.DegreeTypeListFactoryImpl;
import PAI.persistence.mem.degreeType.DegreeTypeRepositoryImpl;
import PAI.persistence.mem.degreeType.IDegreeTypeListFactory;
import PAI.persistence.mem.department.DepartmentListFactoryImpl;
import PAI.persistence.mem.department.DepartmentRepositoryImpl;
import PAI.persistence.mem.department.IDepartmentListFactory;
import PAI.persistence.mem.programmeEditionEnrolment.IProgrammeEditionEnrolmentListFactory;
import PAI.persistence.mem.programmeEditionEnrolment.ProgrammeEditionEnrolmentListFactoryImpl;
import PAI.persistence.mem.programmeEditionEnrolment.ProgrammeEditionEnrolmentRepositoryImpl;
import PAI.persistence.mem.schoolYear.ISchoolYearListFactory;
import PAI.persistence.mem.schoolYear.SchoolYearListFactoryImpl;
import PAI.persistence.mem.schoolYear.SchoolYearRepositoryImpl;
import PAI.persistence.mem.programme.IProgrammeRepositoryListFactory;
import PAI.persistence.mem.programme.ProgrammeRepositoryImpl;
import PAI.persistence.mem.programme.ProgrammeRepositoryListFactoryImpl;
import PAI.persistence.mem.programmeEdition.IProgrammeEditionListFactory;
import PAI.persistence.mem.programmeEdition.ProgrammeEditionListFactoryImpl;
import PAI.persistence.mem.programmeEdition.ProgrammeEditionRepositoryImpl;
import PAI.domain.repositoryInterfaces.schoolYear.ISchoolYearRepository;
import PAI.domain.repositoryInterfaces.programmeEdition.IProgrammeEditionRepository;
import PAI.domain.repositoryInterfaces.programme.IProgrammeRepository;
import PAI.persistence.mem.teacher.ITeacherListFactory;
import PAI.persistence.mem.teacher.TeacherListFactoryImpl;
import PAI.persistence.mem.teacher.TeacherRepositoryImpl;
import PAI.service.programme.IProgrammeService;
import PAI.service.programme.ProgrammeServiceImpl;
import PAI.service.programmeEdition.IProgrammeEditionService;
import PAI.service.programmeEdition.ProgrammeEditionService;
import PAI.service.schoolYear.ISchoolYearService;
import PAI.service.schoolYear.SchoolYearServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US18_CreateProgrammeEditionForCurrentSchoolYearControllerTest {

    //Unit Tests
    //Constructor Tests
    @Test
    void shouldCreateControllerUnitTest() throws Exception {
        //Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);

        //Act
        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, programmeService, schoolYearService);

        //Assert
        assertNotNull(controller);
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionServiceIsNullUnitTest() {
        //Arrange
        IProgrammeService programmeService = mock(IProgrammeService.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {new US18_CreateProgrammeEditionForCurrentSchoolYearController(null, programmeService, schoolYearService);});

        // Assert
        assertEquals("Programme Edition Service cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenProgrammeServiceIsNullUnitTest() {
        //Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, null, schoolYearService);});

        // Assert
        assertEquals("Programme Service cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenSchoolYearServiceIsNullUnitTest() throws Exception {
        //Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, programmeService, null);});

        // Assert
        assertEquals("School Year Service cannot be null", exception.getMessage());
    }

    //getAllProgrammes Tests
    @Test
    void shouldReturnAllProgrammesUnitTest() throws Exception {
        //Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, programmeService, schoolYearService);

        Iterable<Programme> expectedProgrammes = mock(Iterable.class);
        when(programmeService.findAll()).thenReturn(expectedProgrammes);

        //Act
        Iterable<Programme> actualProgrammes = controller.getAllProgrammes();

        //Assert
        assertEquals(expectedProgrammes, actualProgrammes);
    }

    @Test
    void shouldReturnEmptyListOfNamesIfProgrammeRepositoryIsEmptyUnitTest() throws Exception {
        // Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, programmeService, schoolYearService);

        when(programmeService.findAll()).thenReturn(new ArrayList<>());

        // Act
        Iterable<Programme> actualProgrammes = controller.getAllProgrammes();

        // Assert
        assertNotNull(actualProgrammes);
        assertFalse(actualProgrammes.iterator().hasNext());
    }

    //getCurrentSchoolYear Tests
    @Test
    void shouldReturnCurrentSchoolYearUnitTest() throws Exception {
        // Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, programmeService, schoolYearService);

        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        when(schoolYearService.getCurrentSchoolYearID()).thenReturn(Optional.of(schoolYearID));

        //Act
        SchoolYearID currentSchoolYearID = controller.getCurrentSchoolYear();

        //Assert
        assertEquals(schoolYearID, currentSchoolYearID);
    }

    @Test
    void shouldReturnNoSchoolYearUnitTest() throws Exception {
        // Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, programmeService, schoolYearService);

        when(schoolYearService.getCurrentSchoolYearID()).thenReturn(Optional.empty());

        //Act
        SchoolYearID currentSchoolYearID = controller.getCurrentSchoolYear();

        //Assert
        assertNull(currentSchoolYearID);
    }

    //CreateProgrammeEdition Tests
    @Test
    void shouldThrowExceptionWhenProgrammeIDIsNull() throws Exception {
        // Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller =
                new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, programmeService, schoolYearService);

        SchoolYearID schoolYearID = mock(SchoolYearID.class);

        // Act + Assert
        assertThrows(Exception.class, () -> {controller.createAProgrammeEditionForTheCurrentSchoolYear(null,schoolYearID);});
    }

    @Test
    void shouldThrowExceptionWhenSchoolYearIDIsNull() throws Exception {
        // Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller =
                new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, programmeService, schoolYearService);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);

        // Act + Assert
        assertThrows(Exception.class, () -> {controller.createAProgrammeEditionForTheCurrentSchoolYear(programmeID,null);});
    }

    @Test
    void shouldReturnTrueWhenProgrammeEditionIsSuccessfullyCreatedAndSavedUnitTest() throws Exception {
        // Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller =
                new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, programmeService, schoolYearService);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        when(programmeEditionService.createProgrammeEdition(programmeID, schoolYearID)).thenReturn(programmeEdition);
        when(programmeEditionService.saveProgrammeEdition(programmeEdition)).thenReturn(Optional.of(programmeEdition));

        //Act
        boolean result = controller.createAProgrammeEditionForTheCurrentSchoolYear(programmeID, schoolYearID);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenCreateProgrammeEditionFailsUnitTest() throws Exception {
        // Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller =
                new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, programmeService, schoolYearService);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        when(programmeEditionService.createProgrammeEdition(programmeID, schoolYearID)).thenReturn(null);

        // Act
        boolean result = controller.createAProgrammeEditionForTheCurrentSchoolYear(programmeID, schoolYearID);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenSavingProgrammeEditionFailsUnitTest() throws Exception {
        // Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller =
                new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, programmeService, schoolYearService);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);

        when(programmeEditionService.createProgrammeEdition(programmeID, schoolYearID)).thenReturn(programmeEdition);
        when(programmeEditionService.saveProgrammeEdition(programmeEdition)).thenReturn(Optional.empty());
        // Act
        boolean result = controller.createAProgrammeEditionForTheCurrentSchoolYear(programmeID, schoolYearID);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenCreatingProgrammeEditionThrowsExceptionUnitTest() throws Exception {
        // Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller =
                new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, programmeService, schoolYearService);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);

        when(programmeEditionService.createProgrammeEdition(programmeID, schoolYearID)).thenThrow(IllegalArgumentException.class);
        // Act
        boolean result = controller.createAProgrammeEditionForTheCurrentSchoolYear(programmeID, schoolYearID);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenSavingProgrammeEditionThrowsExceptionUnitTest() throws Exception {
        // Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller =
                new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, programmeService, schoolYearService);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);

        when(programmeEditionService.createProgrammeEdition(programmeID, schoolYearID)).thenReturn(programmeEdition);
        when(programmeEditionService.saveProgrammeEdition(programmeEdition)).thenThrow(IllegalArgumentException.class);
        // Act
        boolean result = controller.createAProgrammeEditionForTheCurrentSchoolYear(programmeID, schoolYearID);

        // Assert
        assertFalse(result);
    }


    //Integration Tests
    //Constructor Tests
    @Test
    void shouldCreateController() throws Exception {
        // Arrange
        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);

        IProgrammeRepositoryListFactory programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeRepositoryListFactory);
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();
        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository, programmeAssembler);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactory);
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearRepositoryListFactory);

        IProgrammeEditionEnrolmentListFactory programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactory);
        IProgrammeEditionAssembler programmeEditionAssembler = new ProgrammeEditionAssemblerImpl();
        IProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository,programmeRepository,schoolYearRepository,programmeEditionEnrolmentRepository,programmeEditionAssembler);

        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory,schoolYearMapperDTO);

        // Act
        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, programmeService, schoolYearService);

        // Assert
        assertNotNull(controller);
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionServiceIsNull() {
        // Arrange
        IProgrammeRepositoryListFactory programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeRepositoryListFactory);
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();
        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository, programmeAssembler);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactory);
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearRepositoryListFactory);

        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory,schoolYearMapperDTO);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {new US18_CreateProgrammeEditionForCurrentSchoolYearController(null, programmeService, schoolYearService);});

        // Assert
        assertEquals("Programme Edition Service cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenProgrammeServiceIsNull() throws Exception {
        // Arrange
        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearRepositoryListFactory);
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactory);

        IProgrammeRepositoryListFactory programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeRepositoryListFactory);
        IProgrammeEditionEnrolmentListFactory programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactory);
        IProgrammeEditionAssembler programmeEditionAssembler = new ProgrammeEditionAssemblerImpl();
        IProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository,programmeRepository,schoolYearRepository,programmeEditionEnrolmentRepository,programmeEditionAssembler);

        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory, schoolYearMapperDTO);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, null, schoolYearService);});

        // Assert
        assertEquals("Programme Service cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenSchoolYearServiceIsNull() throws Exception {
        // Arrange
        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);

        IProgrammeRepositoryListFactory programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeRepositoryListFactory);
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();
        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository, programmeAssembler);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearRepositoryListFactory);
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactory);

        IProgrammeEditionEnrolmentListFactory programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactory);
        IProgrammeEditionAssembler programmeEditionAssembler = new ProgrammeEditionAssemblerImpl();
        IProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository,programmeRepository,schoolYearRepository,programmeEditionEnrolmentRepository,programmeEditionAssembler);

        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory,schoolYearMapperDTO);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, programmeService, null);});

        // Assert
        assertEquals("School Year Service cannot be null", exception.getMessage());
    }

    @Test
    void shouldReturnListOfNamesOfAllExistingProgrammes() throws Exception {
        // Arrange
        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);

        IProgrammeRepositoryListFactory programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeRepositoryListFactory);
        IDegreeTypeListFactory degreeTypeListFactory = new DegreeTypeListFactoryImpl();
        IDegreeTypeRepository degreeTypeRepository = new DegreeTypeRepositoryImpl(degreeTypeListFactory);
        IDepartmentListFactory departmentListFactory = new DepartmentListFactoryImpl();
        IDepartmentRepository departmentRepository = new DepartmentRepositoryImpl(departmentListFactory);
        ITeacherListFactory teacherListFactory = new TeacherListFactoryImpl();
        ITeacherRepository teacherRepository = new TeacherRepositoryImpl(teacherListFactory);
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();
        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository, programmeAssembler);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearRepositoryListFactory);
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactory);

        IProgrammeEditionEnrolmentListFactory programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactory);
        IProgrammeEditionAssembler programmeEditionAssembler = new ProgrammeEditionAssemblerImpl();
        IProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository,programmeRepository,schoolYearRepository,programmeEditionEnrolmentRepository,programmeEditionAssembler);

        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory,schoolYearMapperDTO);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, programmeService, schoolYearService);

        NameWithNumbersAndSpecialChars programmeName1 = new NameWithNumbersAndSpecialChars("Licenciatura em Engenharia Informatica");
        Acronym programmeAcronym1 = new Acronym("LEI");
        NameWithNumbersAndSpecialChars programmeName2 = new NameWithNumbersAndSpecialChars("Licenciatura em Engenharia Espacial");
        Acronym programmeAcronym2 = new Acronym("LEE");
        NameWithNumbersAndSpecialChars programmeName3 = new NameWithNumbersAndSpecialChars("Licenciatura em Engenharia Agricula");
        Acronym programmeAcronym3 = new Acronym("LEA");
        MaxEcts maxEcts = new MaxEcts(30);
        QuantSemesters quantSemesters = new QuantSemesters(4);
        DegreeTypeID degreeTypeID = new DegreeTypeID("Licenciatura");
        DepartmentAcronym departmentAcronym = new DepartmentAcronym("DEI");
        DepartmentID departmentID = new DepartmentID(departmentAcronym);
        TeacherAcronym teacherAcronym = new TeacherAcronym("JFC");
        TeacherID teacherID = new TeacherID(teacherAcronym);

        Name dtname = new Name("Master");
        DegreeType degreeType = new DegreeType(degreeTypeID, dtname, maxEcts);
        Name departmentName = new Name("Astronomy");
        DepartmentAcronym dAcronym = new DepartmentAcronym("DEI");
        Department department = new Department(dAcronym, departmentName);
        Name teacherName = new Name("AAA");
        Email email = new Email ("aaa@gmail.com");
        Country country = new Country("Portugal");
        NIF nif = new NIF("123456789", country);
        PhoneNumber phoneNumber = new PhoneNumber("+351", "912345678");
        AcademicBackground academicBackground = new AcademicBackground("Mestrado ISEP 2024");
        Street street = new Street("Rua das Flores");
        PostalCode postalCode = new PostalCode("4450-789");
        Location location = new Location("Coimbra");
        Address address = new Address(street, postalCode, location, country);
        Teacher teacher = new Teacher(teacherID, teacherName, email, nif, phoneNumber, academicBackground, address, departmentID);

        degreeTypeRepository.save(degreeType);
        departmentRepository.save(department);
        teacherRepository.save(teacher);

        ProgrammeVOsDTO programmeVOsDTO1 = new ProgrammeVOsDTO(programmeName1, programmeAcronym1, maxEcts, quantSemesters, degreeTypeID, departmentID, teacherID);
        ProgrammeVOsDTO programmeVOsDTO2 = new ProgrammeVOsDTO(programmeName2, programmeAcronym2, maxEcts, quantSemesters, degreeTypeID, departmentID, teacherID);
        ProgrammeVOsDTO programmeVOsDTO3 = new ProgrammeVOsDTO(programmeName3, programmeAcronym3, maxEcts, quantSemesters, degreeTypeID, departmentID, teacherID);

        programmeService.registerProgramme(programmeVOsDTO1);
        programmeService.registerProgramme(programmeVOsDTO2);
        programmeService.registerProgramme(programmeVOsDTO3);

        // Act
        Iterable<Programme> listToTest = controller.getAllProgrammes();

        // Assert
        assertNotNull(listToTest);
        long size = StreamSupport.stream(listToTest.spliterator(), false).count();
        assertEquals(3, size);
    }

    @Test
    void shouldReturnEmptyListOfNamesIfProgrammeRepositoryIsEmpty() throws Exception {
        // Arrange
        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);

        IProgrammeRepositoryListFactory programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeRepositoryListFactory);
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();
        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository, programmeAssembler);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearRepositoryListFactory);
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactory);

        IProgrammeEditionEnrolmentListFactory programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactory);
        IProgrammeEditionAssembler programmeEditionAssembler = new ProgrammeEditionAssemblerImpl();
        IProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository,programmeRepository,schoolYearRepository,programmeEditionEnrolmentRepository,programmeEditionAssembler);

        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory,schoolYearMapperDTO);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, programmeService, schoolYearService);

        // Act
        Iterable<Programme> listToTest = controller.getAllProgrammes();

        // Assert
        assertNotNull(listToTest);
        long size = StreamSupport.stream(listToTest.spliterator(), false).count();
        assertEquals(0, size);
    }

    @Test
    void shouldReturnCurrentSchoolYearID() throws Exception {
        // Arrange
        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);

        IProgrammeRepositoryListFactory programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeRepositoryListFactory);
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();
        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository, programmeAssembler);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactory);
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearRepositoryListFactory);
        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory,schoolYearMapperDTO);

        IProgrammeEditionEnrolmentListFactory programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactory);
        IProgrammeEditionAssembler programmeEditionAssembler = new ProgrammeEditionAssemblerImpl();
        IProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository,programmeRepository,schoolYearRepository,programmeEditionEnrolmentRepository,programmeEditionAssembler);

        // Create and save multiple school years
        Description description1 = new Description("2022/2023");
        Date startDate1 = new Date("01-09-2022");
        Date endDate1 = new Date("31-08-2023");
        SchoolYear schoolYear1 = schoolYearFactory.createSchoolYear(description1, startDate1, endDate1);
        schoolYearRepository.save(schoolYear1);

        Description description2 = new Description("2023/2024");
        Date startDate2 = new Date("01-09-2023");
        Date endDate2 = new Date("31-08-2024");
        SchoolYear schoolYear2 = schoolYearFactory.createSchoolYear(description2, startDate2, endDate2);
        schoolYearRepository.save(schoolYear2);

        Description description3 = new Description("2024/2025");
        Date startDate3 = new Date("01-09-2024");
        Date endDate3 = new Date("31-08-2025");
        SchoolYear schoolYear3 = schoolYearFactory.createSchoolYear(description3, startDate3, endDate3);
        schoolYearRepository.save(schoolYear3);

        SchoolYearID expectedID = schoolYear3.identity();

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller =
                new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, programmeService, schoolYearService);

        // Act
        SchoolYearID result = controller.getCurrentSchoolYear();

        // Assert
        assertNotNull(result);
        assertEquals(expectedID, result);
    }

    @Test
    void shouldReturnNullIfSchoolYearIDIsEmpty() throws Exception {
        // Arrange
        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);

        IProgrammeRepositoryListFactory programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeRepositoryListFactory);
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();
        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository, programmeAssembler);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearRepositoryListFactory);
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactory);
        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory,schoolYearMapperDTO);

        IProgrammeEditionEnrolmentListFactory programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactory);
        IProgrammeEditionAssembler programmeEditionAssembler = new ProgrammeEditionAssemblerImpl();
        IProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository,programmeRepository,schoolYearRepository,programmeEditionEnrolmentRepository,programmeEditionAssembler);


        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, programmeService, schoolYearService);

        // Act
        SchoolYearID result = controller.getCurrentSchoolYear();

        // Assert
        assertNull(result);
    }

    @Test
    void shouldThrowExceptionIfParameterProgrammeIDNull() throws Exception {
        // Arrange
        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);

        IProgrammeRepositoryListFactory programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeRepositoryListFactory);
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();
        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository, programmeAssembler);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearRepositoryListFactory);
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactory);

        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory,schoolYearMapperDTO);

        IProgrammeEditionEnrolmentListFactory programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactory);
        IProgrammeEditionAssembler programmeEditionAssembler = new ProgrammeEditionAssemblerImpl();
        IProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository,programmeRepository,schoolYearRepository,programmeEditionEnrolmentRepository,programmeEditionAssembler);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, programmeService, schoolYearService);

        Description description1 = new Description("2023/2024");
        Date startDate1 = new Date("01-09-2023");
        Date endDate1 = new Date("31-08-2024");
        Description description3 = new Description("2025/2026");
        Date startDate3 = new Date("01-09-2025");
        Date endDate3 = new Date("31-08-2026");
        SchoolYear schoolYear1=schoolYearFactory.createSchoolYear(description1, startDate1, endDate1);
        schoolYearRepository.save(schoolYear1);
        SchoolYear schoolYear2=schoolYearFactory.createSchoolYear(description3, startDate3, endDate3);
        schoolYearRepository.save(schoolYear2);
        Iterable<SchoolYear> schoolYears = schoolYearRepository.findAll();
        Iterator<SchoolYear> schoolYearIterator = schoolYears.iterator();
        SchoolYear schoolYear = schoolYearIterator.next();
        SchoolYearID schoolYearID = schoolYear.identity();

        // Act + Assert
        assertThrows(Exception.class, () -> {controller.createAProgrammeEditionForTheCurrentSchoolYear(null,schoolYearID);});
    }

    @Test
    void shouldThrowExceptionIfParameterSchoolYearIDNull() throws Exception {
        // Arrange
        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);

        IProgrammeRepositoryListFactory programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeRepositoryListFactory);
        IDegreeTypeListFactory degreeTypeListFactory = new DegreeTypeListFactoryImpl();
        IDegreeTypeRepository degreeTypeRepository = new DegreeTypeRepositoryImpl(degreeTypeListFactory);
        IDepartmentListFactory departmentListFactory = new DepartmentListFactoryImpl();
        IDepartmentRepository departmentRepository = new DepartmentRepositoryImpl(departmentListFactory);
        ITeacherListFactory teacherListFactory = new TeacherListFactoryImpl();
        ITeacherRepository teacherRepository = new TeacherRepositoryImpl(teacherListFactory);
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();
        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository, programmeAssembler);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearRepositoryListFactory);
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactory);

        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory,schoolYearMapperDTO);

        IProgrammeEditionEnrolmentListFactory programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactory);
        IProgrammeEditionAssembler programmeEditionAssembler = new ProgrammeEditionAssemblerImpl();
        IProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository,programmeRepository,schoolYearRepository,programmeEditionEnrolmentRepository,programmeEditionAssembler);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, programmeService, schoolYearService);

        NameWithNumbersAndSpecialChars programmeName1 = new NameWithNumbersAndSpecialChars("Licenciatura em Engenharia Informatica");
        Acronym programmeAcronym1 = new Acronym("LEI");
        NameWithNumbersAndSpecialChars programmeName2 = new NameWithNumbersAndSpecialChars("Licenciatura em Engenharia Espacial");
        Acronym programmeAcronym2 = new Acronym("LEE");
        NameWithNumbersAndSpecialChars programmeName3 = new NameWithNumbersAndSpecialChars("Licenciatura em Engenharia Agricula");
        Acronym programmeAcronym3 = new Acronym("LEA");
        MaxEcts maxEcts = new MaxEcts(30);
        QuantSemesters quantSemesters = new QuantSemesters(4);
        DegreeTypeID degreeTypeID = new DegreeTypeID("Licenciatura");
        DepartmentAcronym departmentAcronym = new DepartmentAcronym("DEI");
        DepartmentID departmentID = new DepartmentID(departmentAcronym);
        TeacherAcronym teacherAcronym = new TeacherAcronym("JFC");
        TeacherID teacherID = new TeacherID(teacherAcronym);

        Name dtname = new Name("Master");
        DegreeType degreeType = new DegreeType(degreeTypeID, dtname, maxEcts);
        Name departmentName = new Name("Astronomy");
        DepartmentAcronym dAcronym = new DepartmentAcronym("DEI");
        Department department = new Department(dAcronym, departmentName);
        Name teacherName = new Name("AAA");
        Email email = new Email ("aaa@gmail.com");
        Country country = new Country("Portugal");
        NIF nif = new NIF("123456789", country);
        PhoneNumber phoneNumber = new PhoneNumber("+351", "912345678");
        AcademicBackground academicBackground = new AcademicBackground("Mestrado ISEP 2024");
        Street street = new Street("Rua das Flores");
        PostalCode postalCode = new PostalCode("4450-789");
        Location location = new Location("Coimbra");
        Address address = new Address(street, postalCode, location, country);
        Teacher teacher = new Teacher(teacherID, teacherName, email, nif, phoneNumber, academicBackground, address, departmentID);

        degreeTypeRepository.save(degreeType);
        departmentRepository.save(department);
        teacherRepository.save(teacher);

        ProgrammeVOsDTO programmeVOsDTO1 = new ProgrammeVOsDTO(programmeName1, programmeAcronym1, maxEcts, quantSemesters, degreeTypeID, departmentID, teacherID);
        ProgrammeVOsDTO programmeVOsDTO2 = new ProgrammeVOsDTO(programmeName2, programmeAcronym2, maxEcts, quantSemesters, degreeTypeID, departmentID, teacherID);
        ProgrammeVOsDTO programmeVOsDTO3 = new ProgrammeVOsDTO(programmeName3, programmeAcronym3, maxEcts, quantSemesters, degreeTypeID, departmentID, teacherID);

        programmeService.registerProgramme(programmeVOsDTO1);
        programmeService.registerProgramme(programmeVOsDTO2);
        programmeService.registerProgramme(programmeVOsDTO3);
        Iterable<Programme> programmes = programmeRepository.findAll();
        Iterator<Programme> iterator = programmes.iterator();
        Programme programme = iterator.next();
        ProgrammeID programmeID = programme.identity();

        Description description1 = new Description("2023/2024");
        Date startDate1 = new Date("01-09-2023");
        Date endDate1 = new Date("31-08-2024");
        Description description3 = new Description("2025/2026");
        Date startDate3 = new Date("01-09-2025");
        Date endDate3 = new Date("31-08-2026");
        SchoolYear schoolYear1=schoolYearFactory.createSchoolYear(description1, startDate1, endDate1);
        schoolYearRepository.save(schoolYear1);
        SchoolYear schoolYear2=schoolYearFactory.createSchoolYear(description3, startDate3, endDate3);
        schoolYearRepository.save(schoolYear2);
        Iterable<SchoolYear> schoolYears = schoolYearRepository.findAll();
        Iterator<SchoolYear> schoolYearIterator = schoolYears.iterator();
        SchoolYear schoolYear = schoolYearIterator.next();
        SchoolYearID schoolYearID = schoolYear.identity();
        SchoolYearID schoolYearID1 = mock(SchoolYearID.class);

        // Act + Assert
        assertThrows(Exception.class, () -> {controller.createAProgrammeEditionForTheCurrentSchoolYear(programmeID,null);});
    }

    @Test
    void shouldReturnTrueIfCreationIsSuccessful() throws Exception {
        // Arrange
        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);

        IProgrammeRepositoryListFactory programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeRepositoryListFactory);
        IDegreeTypeListFactory degreeTypeListFactory = new DegreeTypeListFactoryImpl();
        IDegreeTypeRepository degreeTypeRepository = new DegreeTypeRepositoryImpl(degreeTypeListFactory);
        IDepartmentListFactory departmentListFactory = new DepartmentListFactoryImpl();
        IDepartmentRepository departmentRepository = new DepartmentRepositoryImpl(departmentListFactory);
        ITeacherListFactory teacherListFactory = new TeacherListFactoryImpl();
        ITeacherRepository teacherRepository = new TeacherRepositoryImpl(teacherListFactory);
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();
        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository, programmeAssembler);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearRepositoryListFactory);
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactory);

        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory,schoolYearMapperDTO);

        IProgrammeEditionEnrolmentListFactory programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactory);
        IProgrammeEditionAssembler programmeEditionAssembler = new ProgrammeEditionAssemblerImpl();
        IProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository,programmeRepository,schoolYearRepository,programmeEditionEnrolmentRepository,programmeEditionAssembler);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, programmeService, schoolYearService);

        NameWithNumbersAndSpecialChars programmeName1 = new NameWithNumbersAndSpecialChars("Licenciatura em Engenharia Informatica");
        Acronym programmeAcronym1 = new Acronym("LEI");
        NameWithNumbersAndSpecialChars programmeName2 = new NameWithNumbersAndSpecialChars("Licenciatura em Engenharia Espacial");
        Acronym programmeAcronym2 = new Acronym("LEE");
        NameWithNumbersAndSpecialChars programmeName3 = new NameWithNumbersAndSpecialChars("Licenciatura em Engenharia Agricula");
        Acronym programmeAcronym3 = new Acronym("LEA");
        MaxEcts maxEcts = new MaxEcts(30);
        QuantSemesters quantSemesters = new QuantSemesters(4);
        DegreeTypeID degreeTypeID = new DegreeTypeID("Licenciatura");
        DepartmentAcronym departmentAcronym = new DepartmentAcronym("DEI");
        DepartmentID departmentID = new DepartmentID(departmentAcronym);
        TeacherAcronym teacherAcronym = new TeacherAcronym("JFC");
        TeacherID teacherID = new TeacherID(teacherAcronym);

        Name dtname = new Name("Master");
        DegreeType degreeType = new DegreeType(degreeTypeID, dtname, maxEcts);
        Name departmentName = new Name("Astronomy");
        DepartmentAcronym dAcronym = new DepartmentAcronym("DEI");
        Department department = new Department(dAcronym, departmentName);
        Name teacherName = new Name("AAA");
        Email email = new Email ("aaa@gmail.com");
        Country country = new Country("Portugal");
        NIF nif = new NIF("123456789", country);
        PhoneNumber phoneNumber = new PhoneNumber("+351", "912345678");
        AcademicBackground academicBackground = new AcademicBackground("Mestrado ISEP 2024");
        Street street = new Street("Rua das Flores");
        PostalCode postalCode = new PostalCode("4450-789");
        Location location = new Location("Coimbra");
        Address address = new Address(street, postalCode, location, country);
        Teacher teacher = new Teacher(teacherID, teacherName, email, nif, phoneNumber, academicBackground, address, departmentID);

        degreeTypeRepository.save(degreeType);
        departmentRepository.save(department);
        teacherRepository.save(teacher);

        ProgrammeVOsDTO programmeVOsDTO1 = new ProgrammeVOsDTO(programmeName1, programmeAcronym1, maxEcts, quantSemesters, degreeTypeID, departmentID, teacherID);
        ProgrammeVOsDTO programmeVOsDTO2 = new ProgrammeVOsDTO(programmeName2, programmeAcronym2, maxEcts, quantSemesters, degreeTypeID, departmentID, teacherID);
        ProgrammeVOsDTO programmeVOsDTO3 = new ProgrammeVOsDTO(programmeName3, programmeAcronym3, maxEcts, quantSemesters, degreeTypeID, departmentID, teacherID);

        programmeService.registerProgramme(programmeVOsDTO1);
        programmeService.registerProgramme(programmeVOsDTO2);
        programmeService.registerProgramme(programmeVOsDTO3);
        Iterable<Programme> programmes = programmeRepository.findAll();
        Iterator<Programme> iterator = programmes.iterator();
        Programme programme = iterator.next();
        ProgrammeID programmeID = programme.identity();

        Description description1 = new Description("2024/2025");
        Date startDate1 = new Date("01-09-2024");
        Date endDate1 = new Date("31-08-2025");
        Description description3 = new Description("2025/2026");
        Date startDate3 = new Date("01-09-2025");
        Date endDate3 = new Date("31-08-2026");
        SchoolYear schoolYear1=schoolYearFactory.createSchoolYear(description1, startDate1, endDate1);
        schoolYearRepository.save(schoolYear1);
        SchoolYear schoolYear2=schoolYearFactory.createSchoolYear(description3, startDate3, endDate3);
        schoolYearRepository.save(schoolYear2);

        SchoolYearID currentSchoolYearID = controller.getCurrentSchoolYear();

        // Act
        boolean result = controller.createAProgrammeEditionForTheCurrentSchoolYear(programmeID, currentSchoolYearID);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfAlreadyExistTheSameProgrammeEditionInTheCurrentSchoolYear() throws Exception {
        // Arrange
        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);

        IProgrammeRepositoryListFactory programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeRepositoryListFactory);
        IDegreeTypeListFactory degreeTypeListFactory = new DegreeTypeListFactoryImpl();
        IDegreeTypeRepository degreeTypeRepository = new DegreeTypeRepositoryImpl(degreeTypeListFactory);
        IDepartmentListFactory departmentListFactory = new DepartmentListFactoryImpl();
        IDepartmentRepository departmentRepository = new DepartmentRepositoryImpl(departmentListFactory);
        ITeacherListFactory teacherListFactory = new TeacherListFactoryImpl();
        ITeacherRepository teacherRepository = new TeacherRepositoryImpl(teacherListFactory);
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();
        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository, programmeAssembler);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactory);
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearRepositoryListFactory);
        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory,schoolYearMapperDTO);

        IProgrammeEditionEnrolmentListFactory programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactory);
        IProgrammeEditionAssembler programmeEditionAssembler = new ProgrammeEditionAssemblerImpl();
        IProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository,programmeRepository,schoolYearRepository,programmeEditionEnrolmentRepository,programmeEditionAssembler);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(
                programmeEditionService, programmeService, schoolYearService);

        // Create a programme
        NameWithNumbersAndSpecialChars programmeName = new NameWithNumbersAndSpecialChars("Test Programme");
        Acronym programmeAcronym = new Acronym("TP");
        MaxEcts maxEcts = new MaxEcts(30);
        QuantSemesters quantSemesters = new QuantSemesters(4);
        DegreeTypeID degreeTypeID = new DegreeTypeID("Licenciatura");
        DepartmentAcronym departmentAcronym = new DepartmentAcronym("DEI");
        DepartmentID departmentID = new DepartmentID(departmentAcronym);
        TeacherAcronym teacherAcronym = new TeacherAcronym("JFC");
        TeacherID teacherID = new TeacherID(teacherAcronym);

        Name dtname = new Name("Master");
        DegreeType degreeType = new DegreeType(degreeTypeID, dtname, maxEcts);
        Name departmentName = new Name("Astronomy");
        DepartmentAcronym dAcronym = new DepartmentAcronym("DEI");
        Department department = new Department(dAcronym, departmentName);
        Name teacherName = new Name("AAA");
        Email email = new Email ("aaa@gmail.com");
        Country country = new Country("Portugal");
        NIF nif = new NIF("123456789", country);
        PhoneNumber phoneNumber = new PhoneNumber("+351", "912345678");
        AcademicBackground academicBackground = new AcademicBackground("Mestrado ISEP 2024");
        Street street = new Street("Rua das Flores");
        PostalCode postalCode = new PostalCode("4450-789");
        Location location = new Location("Coimbra");
        Address address = new Address(street, postalCode, location, country);
        Teacher teacher = new Teacher(teacherID, teacherName, email, nif, phoneNumber, academicBackground, address, departmentID);

        degreeTypeRepository.save(degreeType);
        departmentRepository.save(department);
        teacherRepository.save(teacher);

        ProgrammeVOsDTO programmeVOsDTO1 = new ProgrammeVOsDTO(programmeName, programmeAcronym, maxEcts, quantSemesters, degreeTypeID, departmentID, teacherID);

        programmeService.registerProgramme(programmeVOsDTO1);

        Iterable<Programme> programmes = programmeRepository.findAll();
        Iterator<Programme> iterator = programmes.iterator();
        Programme programme = iterator.next();
        ProgrammeID programmeID = programme.identity();

        // Create a school year
        Description description = new Description("2024/2025");
        Date startDate = new Date("01-09-2024");
        Date endDate = new Date("31-08-2025");

        SchoolYear schoolYear1=schoolYearFactory.createSchoolYear(description, startDate, endDate);
        schoolYearRepository.save(schoolYear1);

        SchoolYearID currentSchoolYearID = controller.getCurrentSchoolYear();

        // Create first programme edition
        controller.createAProgrammeEditionForTheCurrentSchoolYear(programmeID, currentSchoolYearID);

        // Act - Create duplicate programme edition
        boolean result = controller.createAProgrammeEditionForTheCurrentSchoolYear(programmeID, currentSchoolYearID);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldThrowExceptionIfProgrammeEditionIsCreatedForNonCurrentYear() throws Exception {
        // Arrange
        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);

        IProgrammeRepositoryListFactory programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeRepositoryListFactory);
        IDegreeTypeListFactory degreeTypeListFactory = new DegreeTypeListFactoryImpl();
        IDegreeTypeRepository degreeTypeRepository = new DegreeTypeRepositoryImpl(degreeTypeListFactory);
        IDepartmentListFactory departmentListFactory = new DepartmentListFactoryImpl();
        IDepartmentRepository departmentRepository = new DepartmentRepositoryImpl(departmentListFactory);
        ITeacherListFactory teacherListFactory = new TeacherListFactoryImpl();
        ITeacherRepository teacherRepository = new TeacherRepositoryImpl(teacherListFactory);
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();
        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository, programmeAssembler);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactory);
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearRepositoryListFactory);
        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory,schoolYearMapperDTO);

        IProgrammeEditionEnrolmentListFactory programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactory);
        IProgrammeEditionAssembler programmeEditionAssembler = new ProgrammeEditionAssemblerImpl();
        IProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository,programmeRepository,schoolYearRepository,programmeEditionEnrolmentRepository,programmeEditionAssembler);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(
                programmeEditionService, programmeService, schoolYearService);

        // Create a programme
        NameWithNumbersAndSpecialChars programmeName = new NameWithNumbersAndSpecialChars("Test Programme");
        Acronym programmeAcronym = new Acronym("TP");
        MaxEcts maxEcts = new MaxEcts(30);
        QuantSemesters quantSemesters = new QuantSemesters(4);
        DegreeTypeID degreeTypeID = new DegreeTypeID("Licenciatura");
        DepartmentAcronym departmentAcronym = new DepartmentAcronym("DEI");
        DepartmentID departmentID = new DepartmentID(departmentAcronym);
        TeacherAcronym teacherAcronym = new TeacherAcronym("JFC");
        TeacherID teacherID = new TeacherID(teacherAcronym);

        Name dtname = new Name("Master");
        DegreeType degreeType = new DegreeType(degreeTypeID, dtname, maxEcts);
        Name departmentName = new Name("Astronomy");
        DepartmentAcronym dAcronym = new DepartmentAcronym("DEI");
        Department department = new Department(dAcronym, departmentName);
        Name teacherName = new Name("AAA");
        Email email = new Email ("aaa@gmail.com");
        Country country = new Country("Portugal");
        NIF nif = new NIF("123456789", country);
        PhoneNumber phoneNumber = new PhoneNumber("+351", "912345678");
        AcademicBackground academicBackground = new AcademicBackground("Mestrado ISEP 2024");
        Street street = new Street("Rua das Flores");
        PostalCode postalCode = new PostalCode("4450-789");
        Location location = new Location("Coimbra");
        Address address = new Address(street, postalCode, location, country);
        Teacher teacher = new Teacher(teacherID, teacherName, email, nif, phoneNumber, academicBackground, address, departmentID);

        degreeTypeRepository.save(degreeType);
        departmentRepository.save(department);
        teacherRepository.save(teacher);

        ProgrammeVOsDTO programmeVOsDTO1 = new ProgrammeVOsDTO(programmeName, programmeAcronym, maxEcts, quantSemesters, degreeTypeID, departmentID, teacherID);

        programmeService.registerProgramme(programmeVOsDTO1);

        Iterable<Programme> programmes = programmeRepository.findAll();
        Iterator<Programme> iterator = programmes.iterator();
        Programme programme = iterator.next();
        ProgrammeID programmeID = programme.identity();

        // Create a school year
        Description description = new Description("2023/2024");
        Date startDate = new Date("01-09-2023");
        Date endDate = new Date("31-08-2024");

        SchoolYear schoolYear1=schoolYearFactory.createSchoolYear(description, startDate, endDate);
        schoolYearRepository.save(schoolYear1);

        SchoolYearID currentSchoolYearID = controller.getCurrentSchoolYear();

        // Act + Assert
        assertThrows(Exception.class, () -> {controller.createAProgrammeEditionForTheCurrentSchoolYear(programmeID,currentSchoolYearID);});
    }

    @Test
    void shouldReturnFalseWhenCreatingProgrammeEditionThrowsException() throws Exception {
        // Arrange
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);

        IProgrammeRepositoryListFactory programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeRepositoryListFactory);
        IDegreeTypeListFactory degreeTypeListFactory = new DegreeTypeListFactoryImpl();
        IDegreeTypeRepository degreeTypeRepository = new DegreeTypeRepositoryImpl(degreeTypeListFactory);
        IDepartmentListFactory departmentListFactory = new DepartmentListFactoryImpl();
        IDepartmentRepository departmentRepository = new DepartmentRepositoryImpl(departmentListFactory);
        ITeacherListFactory teacherListFactory = new TeacherListFactoryImpl();
        ITeacherRepository teacherRepository = new TeacherRepositoryImpl(teacherListFactory);
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();
        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository, programmeAssembler);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearRepositoryListFactory);
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactory);
        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory,schoolYearMapperDTO);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(
                programmeEditionService, programmeService, schoolYearService);

        // Create a programme
        NameWithNumbersAndSpecialChars programmeName = new NameWithNumbersAndSpecialChars("Test Programme");
        Acronym programmeAcronym = new Acronym("TP");
        MaxEcts maxEcts = new MaxEcts(30);
        QuantSemesters quantSemesters = new QuantSemesters(4);
        DegreeTypeID degreeTypeID = new DegreeTypeID("Licenciatura");
        DepartmentID departmentID = new DepartmentID(new DepartmentAcronym("DEI"));
        TeacherID teacherID = new TeacherID(new TeacherAcronym("JFC"));

        Name dtname = new Name("Master");
        DegreeType degreeType = new DegreeType(degreeTypeID, dtname, maxEcts);
        Name departmentName = new Name("Astronomy");
        DepartmentAcronym dAcronym = new DepartmentAcronym("DEI");
        Department department = new Department(dAcronym, departmentName);
        Name teacherName = new Name("AAA");
        Email email = new Email ("aaa@gmail.com");
        Country country = new Country("Portugal");
        NIF nif = new NIF("123456789", country);
        PhoneNumber phoneNumber = new PhoneNumber("+351", "912345678");
        AcademicBackground academicBackground = new AcademicBackground("Mestrado ISEP 2024");
        Street street = new Street("Rua das Flores");
        PostalCode postalCode = new PostalCode("4450-789");
        Location location = new Location("Coimbra");
        Address address = new Address(street, postalCode, location, country);
        Teacher teacher = new Teacher(teacherID, teacherName, email, nif, phoneNumber, academicBackground, address, departmentID);

        degreeTypeRepository.save(degreeType);
        departmentRepository.save(department);
        teacherRepository.save(teacher);

        ProgrammeVOsDTO programmeVOsDTO1 = new ProgrammeVOsDTO(programmeName, programmeAcronym, maxEcts, quantSemesters, degreeTypeID, departmentID, teacherID);

        programmeService.registerProgramme(programmeVOsDTO1);

        Iterable<Programme> programmes = programmeRepository.findAll();
        Iterator<Programme> iterator = programmes.iterator();
        Programme programme = iterator.next();
        ProgrammeID programmeID = programme.identity();


        SchoolYearID schoolYearID = mock(SchoolYearID.class);

        // Mock the service to throw an exception
        when(programmeEditionService.createProgrammeEdition(any(), any()))
                .thenThrow(new RuntimeException("Simulated error"));

        // Act
        boolean result = controller.createAProgrammeEditionForTheCurrentSchoolYear(programmeID, schoolYearID);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenSavingProgrammeEditionThrowsException() throws Exception {
        // Arrange
        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);

        IProgrammeRepositoryListFactory programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeRepositoryListFactory);
        IDegreeTypeListFactory degreeTypeListFactory = new DegreeTypeListFactoryImpl();
        IDegreeTypeRepository degreeTypeRepository = new DegreeTypeRepositoryImpl(degreeTypeListFactory);
        IDepartmentListFactory departmentListFactory = new DepartmentListFactoryImpl();
        IDepartmentRepository departmentRepository = new DepartmentRepositoryImpl(departmentListFactory);
        ITeacherListFactory teacherListFactory = new TeacherListFactoryImpl();
        ITeacherRepository teacherRepository = new TeacherRepositoryImpl(teacherListFactory);
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();
        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository, programmeAssembler);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearRepositoryListFactory);
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactory);
        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory,schoolYearMapperDTO);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(
                programmeEditionService, programmeService, schoolYearService);

        // Create a programme
        NameWithNumbersAndSpecialChars programmeName = new NameWithNumbersAndSpecialChars("Test Programme");
        Acronym programmeAcronym = new Acronym("TP");
        MaxEcts maxEcts = new MaxEcts(30);
        QuantSemesters quantSemesters = new QuantSemesters(4);
        DegreeTypeID degreeTypeID = new DegreeTypeID("Licenciatura");
        DepartmentID departmentID = new DepartmentID(new DepartmentAcronym("DEI"));
        TeacherID teacherID = new TeacherID(new TeacherAcronym("JFC"));

        Name dtname = new Name("Master");
        DegreeType degreeType = new DegreeType(degreeTypeID, dtname, maxEcts);
        Name departmentName = new Name("Astronomy");
        DepartmentAcronym dAcronym = new DepartmentAcronym("DEI");
        Department department = new Department(dAcronym, departmentName);
        Name teacherName = new Name("AAA");
        Email email = new Email ("aaa@gmail.com");
        Country country = new Country("Portugal");
        NIF nif = new NIF("123456789", country);
        PhoneNumber phoneNumber = new PhoneNumber("+351", "912345678");
        AcademicBackground academicBackground = new AcademicBackground("Mestrado ISEP 2024");
        Street street = new Street("Rua das Flores");
        PostalCode postalCode = new PostalCode("4450-789");
        Location location = new Location("Coimbra");
        Address address = new Address(street, postalCode, location, country);
        Teacher teacher = new Teacher(teacherID, teacherName, email, nif, phoneNumber, academicBackground, address, departmentID);

        degreeTypeRepository.save(degreeType);
        departmentRepository.save(department);
        teacherRepository.save(teacher);

        ProgrammeVOsDTO programmeVOsDTO1 = new ProgrammeVOsDTO(programmeName, programmeAcronym, maxEcts, quantSemesters, degreeTypeID, departmentID, teacherID);

        programmeService.registerProgramme(programmeVOsDTO1);

        Iterable<Programme> programmes = programmeRepository.findAll();
        Iterator<Programme> iterator = programmes.iterator();
        Programme programme = iterator.next();
        ProgrammeID programmeID = programme.identity();

        SchoolYearID schoolYearID = mock(SchoolYearID.class);

        when(programmeEditionService.saveProgrammeEdition(any()))
                .thenThrow(new RuntimeException("Simulated error"));

        // Act
        boolean result = controller.createAProgrammeEditionForTheCurrentSchoolYear(programmeID, schoolYearID);

        // Assert
        assertFalse(result);
    }
}