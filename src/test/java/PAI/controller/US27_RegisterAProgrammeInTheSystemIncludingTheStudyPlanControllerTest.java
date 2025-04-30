package PAI.controller;

import PAI.VOs.*;
import PAI.domain.Department;
import PAI.domain.Teacher;
import PAI.domain.degreeType.DegreeType;
import PAI.domain.programme.Programme;

import PAI.service.DegreeType.DegreeTypeService;
import PAI.service.studyPlan.IStudyPlanService;
import PAI.service.programme.IProgrammeService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanControllerTest {

    @Test
    void registerProgrammeInTheSystemControllerFailureWithNullProgrammeRepo() throws Exception {
        //arrange
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        DegreeTypeService degreeTypeService = mock(DegreeTypeService.class);

        //act + assert
        Exception exception = assertThrows(Exception.class, () ->
                new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(null, studyPlanService, degreeTypeService));

        assertEquals("Programme Service cannot be null.", exception.getMessage());
    }

    @Test
    void registerProgrammeInTheSystemControllerFailureWithNullStudyPlanRepo() throws Exception {
        //arrange
        IProgrammeService programmeService = mock(IProgrammeService.class);
        DegreeTypeService degreeTypeService = mock(DegreeTypeService.class);

        //act + assert
        Exception exception = assertThrows(Exception.class, () ->
                new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(programmeService, null, degreeTypeService));

        assertEquals("Study Plan Service cannot be null.", exception.getMessage());

    }

    @Test
    void registerProgrammeInTheSystemControllerFailureWithNullDegreeTypeRepo() throws Exception {
        //arrange
        IProgrammeService programmeService = mock(IProgrammeService.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);

        //act + assert
        Exception exception = assertThrows(Exception.class, () ->
                new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(programmeService, studyPlanService, null));

        assertEquals("Degree Type Repository cannot be null.", exception.getMessage());

    }

    @Test
    void registerProgrammeInTheSystemControllerCorrectly() throws Exception {
        //arrange
        IProgrammeService programmeService = mock(IProgrammeService.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        DegreeTypeService degreeTypeService = mock(DegreeTypeService.class);

        //act
        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController controller = new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(programmeService, studyPlanService, degreeTypeService);

        //assert
        assertNotNull(controller);
    }

   @Test
    void createStudyPlanWithSuccess() throws Exception {
        // Arrange
       IProgrammeService programmeService = mock(IProgrammeService.class);
       IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
       DegreeTypeService degreeTypeService = mock(DegreeTypeService.class);

       US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController controller =
               new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(programmeService, studyPlanService, degreeTypeService);

       Programme programme = mock(Programme.class);
       DegreeType degreeType = mock(DegreeType.class);
       QuantSemesters quantSemesters = mock(QuantSemesters.class);

       DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);

       ProgrammeID programmeID = mock(ProgrammeID.class);
       LocalDate implementationDate = mock(LocalDate.class);

       when(programmeService.getProgrammeByID(programmeID)).thenReturn(Optional.of(programme));
       when(programme.getDegreeTypeID()).thenReturn(degreeTypeID);
       when(degreeTypeService.getDegreeTypeById(degreeTypeID)).thenReturn(Optional.of(degreeType));

       when(degreeType.getMaxEcts()).thenReturn(180);

       when(programme.getQuantSemesters()).thenReturn(quantSemesters);
       when(quantSemesters.getQuantityOfSemesters()).thenReturn(6);

       // Act
       boolean result = controller.createStudyPlan(programmeID, implementationDate);

       // Assert
       assertTrue(result);
    }

    @Test
    void createStudyPlanProgrammeNotFound() throws Exception {
        // Arrange
        IProgrammeService programmeService = mock(IProgrammeService.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        DegreeTypeService degreeTypeService = mock(DegreeTypeService.class);

        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);

        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController controller =
                new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(programmeService, studyPlanService, degreeTypeService);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        LocalDate implementationDate = mock(LocalDate.class);


        when(programmeService.getProgrammeByID(programmeID)).thenReturn(Optional.empty());

        // Act
        boolean result = controller.createStudyPlan(programmeID, implementationDate);

        // Assert
        assertFalse(result);
    }

    @Test
    void createStudyPlanDegreeTypeNotFound() throws Exception {
        // Arrange
        IProgrammeService programmeService = mock(IProgrammeService.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        DegreeTypeService degreeTypeService = mock(DegreeTypeService.class);


        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController controller =
                new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(programmeService, studyPlanService, degreeTypeService);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        LocalDate implementationDate = mock(LocalDate.class);

        Programme programme =mock(Programme.class);

        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);

        when(programmeService.getProgrammeByID(programmeID)).thenReturn(Optional.of(programme));
        when(programme.getDegreeTypeID()).thenReturn(degreeTypeID);
        when(degreeTypeService.getDegreeTypeById(degreeTypeID)).thenReturn(Optional.empty());

        // Act
        boolean result = controller.createStudyPlan(programmeID, implementationDate);

        // Assert
        assertFalse(result);
    }


    @Test
    void registerProgrammeInTheSystemWithSuccess() throws Exception {
        //arrange
        IProgrammeService programmeService = mock(IProgrammeService.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        DegreeTypeService degreeTypeService = mock(DegreeTypeService.class);

        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController controller =
                new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(programmeService, studyPlanService, degreeTypeService);

        String name = "Programme";
        String acronym = "PRG";
        int qtyEcts = 30;
        int qtySemesters = 6;
        DegreeType degreeType = mock(DegreeType.class);
        Department department = mock(Department.class);
        Teacher programmeDirector = mock(Teacher.class);

        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID departmentID = mock(DepartmentID.class);
        TeacherID teacherID = mock(TeacherID.class);

        when(degreeType.identity()).thenReturn(degreeTypeID);
        when(department.identity()).thenReturn(departmentID);
        when(programmeDirector.identity()).thenReturn(teacherID);

        when(programmeService.registerProgramme(
                any(NameWithNumbersAndSpecialChars.class),
                any(Acronym.class),
                any(QuantEcts.class),
                any(QuantSemesters.class),
                eq(degreeTypeID),
                eq(departmentID),
                eq(teacherID)
        )).thenReturn(true);

        //act
        boolean result = controller.registerProgramme(name, acronym, qtyEcts, qtySemesters, degreeType, department, programmeDirector);

        //assert
        assertTrue(result);
    }

    /*@Test
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
    }*/

    /*@Test
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
    }*/
}