package PAI.controller.US27_DDD;

import PAI.VOs.*;
import PAI.domain.Department;
import PAI.domain.programme.IProgrammeDDDFactory;
import PAI.domain.programme.ProgrammeDDD;
import PAI.domain.studyPlan.IStudyPlanDDDFactory;
import PAI.repository.programmeRepo.IProgrammeDDDRepositoryListFactory;
import PAI.repository.programmeRepo.ProgrammeDDDRepository;
import PAI.repository.studyPlanRepo.IStudyPlanDDDListFactory;
import PAI.repository.studyPlanRepo.StudyPlanDDDRepository;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US27_DDD_RegisterAProgrammeInTheSystemIncludingTheStudyPlanTest {

    @Test
    void registerProgrammeInTheSystemControllerFailureWithNullProgrammeRepo() throws Exception {
        //arrange
        ProgrammeDDDRepository programmeDDDRepository = null;
        IStudyPlanDDDFactory factory = mock(IStudyPlanDDDFactory.class);
        IStudyPlanDDDListFactory listFactory = mock(IStudyPlanDDDListFactory.class);
        StudyPlanDDDRepository studyPlanDDDRepo = new StudyPlanDDDRepository(factory, listFactory);

        //act + assert
        Exception exception = assertThrows(Exception.class, () ->
                new US27_DDD_RegisterAProgrammeInTheSystemIncludingTheStudyPlan(programmeDDDRepository, studyPlanDDDRepo));

        assertEquals("Programme Repository cannot be null.", exception.getMessage());

    }

    @Test
    void registerProgrammeInTheSystemControllerFailureWithNullStudyPlanRepo() throws Exception {
        //arrange
        IProgrammeDDDFactory iProgrammeDDDFactory = mock(IProgrammeDDDFactory.class);
        IProgrammeDDDRepositoryListFactory iProgrammeDDDRepoListFactory = mock(IProgrammeDDDRepositoryListFactory.class);
        ProgrammeDDDRepository programmeDDDRepository = new ProgrammeDDDRepository(iProgrammeDDDFactory, iProgrammeDDDRepoListFactory);
        StudyPlanDDDRepository studyPlanDDDRepo = null;

        //act + assert
        Exception exception = assertThrows(Exception.class, () ->
                new US27_DDD_RegisterAProgrammeInTheSystemIncludingTheStudyPlan(programmeDDDRepository, studyPlanDDDRepo));

        assertEquals("Study Plan Repository cannot be null.", exception.getMessage());


    }

    @Test
    void registerProgrammeInTheSystemControllerCorrectly() throws Exception {
        //arrange
        IProgrammeDDDFactory iProgrammeDDDFactory = mock(IProgrammeDDDFactory.class);
        IProgrammeDDDRepositoryListFactory iProgrammeDDDRepoListFactory = mock(IProgrammeDDDRepositoryListFactory.class);
        ProgrammeDDDRepository programmeDDDRepository = new ProgrammeDDDRepository(iProgrammeDDDFactory, iProgrammeDDDRepoListFactory);
        IStudyPlanDDDFactory factory = mock(IStudyPlanDDDFactory.class);
        IStudyPlanDDDListFactory listFactory = mock(IStudyPlanDDDListFactory.class);
        StudyPlanDDDRepository studyPlanDDDRepo = new StudyPlanDDDRepository(factory, listFactory);

        //act
        US27_DDD_RegisterAProgrammeInTheSystemIncludingTheStudyPlan controller = new US27_DDD_RegisterAProgrammeInTheSystemIncludingTheStudyPlan(programmeDDDRepository, studyPlanDDDRepo);

        //assert
        assertNotNull(controller);
    }

    @Test
    void createStudyPlanDDDWithSuccess() throws Exception {
        // Arrange
        ProgrammeDDDRepository programmeRepo = mock(ProgrammeDDDRepository.class);
        StudyPlanDDDRepository studyPlanRepo = mock(StudyPlanDDDRepository.class);
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);

        US27_DDD_RegisterAProgrammeInTheSystemIncludingTheStudyPlan controller =
                new US27_DDD_RegisterAProgrammeInTheSystemIncludingTheStudyPlan(programmeRepo, studyPlanRepo);
        controller._studyPlanDDDRepo = studyPlanRepo;

        ProgrammeDDD programmeDDD = mock(ProgrammeDDD.class);
        QuantSemesters quantSemesters = mock(QuantSemesters.class);
        QuantEcts quantEcts = mock(QuantEcts.class);
        when(programmeDDD.getQuantEcts()).thenReturn(quantEcts);
        when(quantEcts.getQuantEcts()).thenReturn(30);
        when(programmeDDD.getQuantSemesters()).thenReturn(quantSemesters);
        when(quantSemesters.getQuantityOfSemesters()).thenReturn(6);

        ProgrammeID programmeID = new ProgrammeID(name, acronym);
        Date implementationDate = new Date("21-03-2000");
        when(programmeRepo.findProgrammeByID(programmeID)).thenReturn(Optional.of(programmeDDD));

        // Act
        boolean result = controller.createStudyPlanDDD(programmeID, implementationDate);

        // Assert
        assertTrue(result);
    }

    @Test
    void createStudyPlanDDDProgrammeNotFound() throws Exception {
        // Arrange
        ProgrammeDDDRepository programmeRepo = mock(ProgrammeDDDRepository.class);
        StudyPlanDDDRepository studyPlanRepo = mock(StudyPlanDDDRepository.class);
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);

        US27_DDD_RegisterAProgrammeInTheSystemIncludingTheStudyPlan controller =
                new US27_DDD_RegisterAProgrammeInTheSystemIncludingTheStudyPlan(programmeRepo, studyPlanRepo);
        controller._studyPlanDDDRepo = studyPlanRepo;

        ProgrammeID programmeID = new ProgrammeID(name, acronym);
        Date implementationDate = new Date("21-03-2000");

        when(programmeRepo.findProgrammeByID(programmeID)).thenReturn(Optional.empty());

        // Act
        boolean result = controller.createStudyPlanDDD(programmeID, implementationDate);

        // Assert
        assertFalse(result);
    }

    @Test
    void registerProgrammeInTheSystemWithSuccess() throws Exception {
        //arrange
        ProgrammeDDDRepository programmeRepo = mock(ProgrammeDDDRepository.class);
        StudyPlanDDDRepository studyPlanRepo = mock(StudyPlanDDDRepository.class);

        US27_DDD_RegisterAProgrammeInTheSystemIncludingTheStudyPlan controller =
                new US27_DDD_RegisterAProgrammeInTheSystemIncludingTheStudyPlan(programmeRepo, studyPlanRepo);
        controller._programmeDDDList = programmeRepo;

        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        Department department1 = mock(Department.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);

        //act
        boolean result = controller.registerAProgrammeDDDInTheSystem(name, acronym, qtyEcts,qtySemesters,degreeTypeID,department1, programmeDirectorID);

        //assert
        assertTrue(result);
    }
}