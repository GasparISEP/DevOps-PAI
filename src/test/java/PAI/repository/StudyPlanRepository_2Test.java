package PAI.repository;

import PAI.VOs.Date;
import PAI.VOs.DurationInYears;
import PAI.VOs.ProgrammeID;
import PAI.VOs.StudyPlanID;
import PAI.domain.StudyPlan_2;
import PAI.factory.IStudyPlanFactory_2;
import PAI.factory.IStudyPlanListFactory_2;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudyPlanRepository_2Test {

    @Test
    void testCreateStudyPlan_NewPlan() throws Exception {
        // Arrange: criar mocks para as dependências
        IStudyPlanFactory_2 factory = mock(IStudyPlanFactory_2.class);
        IStudyPlanListFactory_2 listFactory = mock(IStudyPlanListFactory_2.class);
        List<StudyPlan_2> studyPlanList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(studyPlanList);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);

        // Simular a criação de um novo StudyPlan_2
        when(factory.newStudyPlan_2(programmeID, implementationDate, durationInYears))
                .thenAnswer(invocation -> new StudyPlan_2(programmeID, implementationDate, durationInYears));

        StudyPlanRepository_2 repository = new StudyPlanRepository_2(factory, listFactory);

        // Act: criar um novo plano de estudos
        boolean created = repository.createStudyPlan_2(programmeID, implementationDate, durationInYears);

        // Assert
        assertTrue(created, "Deve criar um novo plano de estudos.");
        assertEquals(1, repository.getAllStudyPlans_2().size(), "A lista deve conter um único plano de estudos.");
    }

    @Test
    void testCreateStudyPlan_DuplicatePlan() throws Exception {
        // Arrange: criar mocks para as dependências
        IStudyPlanFactory_2 factory = mock(IStudyPlanFactory_2.class);
        IStudyPlanListFactory_2 listFactory = mock(IStudyPlanListFactory_2.class);
        List<StudyPlan_2> studyPlanList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(studyPlanList);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);

        StudyPlanRepository_2 repository = new StudyPlanRepository_2(factory, listFactory);

        // Act: criar o plano de estudos duas vezes com os mesmos parâmetros
        boolean createdFirstStudyPlan = repository.createStudyPlan_2(programmeID, implementationDate, durationInYears);
        boolean createdSecondStudyPlan = repository.createStudyPlan_2(programmeID, implementationDate, durationInYears);

        // Assert
        assertTrue(createdFirstStudyPlan, "Deve criar o plano de estudos na primeira chamada.");
        assertFalse(createdSecondStudyPlan, "Não deve criar um plano duplicado.");
        assertEquals(1, repository.getAllStudyPlans_2().size(), "A lista deve conter apenas um plano de estudos.");
    }

    @Test
    void testGetAllStudyPlans_ReturnsMultiplePlans() throws Exception {
        // Arrange: criar mocks para as dependências
        IStudyPlanFactory_2 factory = mock(IStudyPlanFactory_2.class);
        IStudyPlanListFactory_2 listFactory = mock(IStudyPlanListFactory_2.class);
        List<StudyPlan_2> studyPlanList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(studyPlanList);

        // Utilizar diferentes ProgrammeID para criar planos distintos
        ProgrammeID programmeID1 = mock(ProgrammeID.class);
        ProgrammeID programmeID2 = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);

        when(factory.newStudyPlan_2(programmeID1, implementationDate, durationInYears))
                .thenAnswer(invocation -> new StudyPlan_2(programmeID1, implementationDate, durationInYears));
        when(factory.newStudyPlan_2(programmeID2, implementationDate, durationInYears))
                .thenAnswer(invocation -> new StudyPlan_2(programmeID2, implementationDate, durationInYears));

        StudyPlanRepository_2 repository = new StudyPlanRepository_2(factory, listFactory);

        // Act: criar dois planos de estudos com ProgrammeID diferentes
        boolean created1 = repository.createStudyPlan_2(programmeID1, implementationDate, durationInYears);
        boolean created2 = repository.createStudyPlan_2(programmeID2, implementationDate, durationInYears);
        List<StudyPlan_2> allPlans = repository.getAllStudyPlans_2();

        // Assert
        assertTrue(created1, "Deve criar o primeiro plano de estudos.");
        assertTrue(created2, "Deve criar o segundo plano de estudos.");
        assertEquals(2, allPlans.size(), "A lista deve conter dois planos de estudos.");
    }

    @Test
    void testFindByStudyPlanID_Found() throws Exception {
        // Arrange: criar mocks para as dependências
        IStudyPlanFactory_2 factory = mock(IStudyPlanFactory_2.class);
        IStudyPlanListFactory_2 listFactory = mock(IStudyPlanListFactory_2.class);
        List<StudyPlan_2> studyPlanList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(studyPlanList);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);

        // Simular a criação de um novo StudyPlan_2
        when(factory.newStudyPlan_2(programmeID, implementationDate, durationInYears))
                .thenAnswer(invocation -> new StudyPlan_2(programmeID, implementationDate, durationInYears));

        StudyPlanRepository_2 repository = new StudyPlanRepository_2(factory, listFactory);

        // Act: criar um novo plano de estudos
        repository.createStudyPlan_2(programmeID, implementationDate, durationInYears);
        // Obter o plano criado e o seu StudyPlanID
        StudyPlan_2 createdPlan = repository.getAllStudyPlans_2().get(0);
        StudyPlanID studyPlanID = createdPlan.getStudyPlanID();

        // Act: procurar o plano de estudos pelo seu ID
        Optional<StudyPlan_2> foundPlanOpt = repository.findByStudyPlanID(studyPlanID);

        // Assert: deve encontrar o plano e ser igual ao criado
        assertTrue(foundPlanOpt.isPresent(), "Deve encontrar o plano de estudos com o ID fornecido.");
        assertEquals(createdPlan, foundPlanOpt.get(), "O plano encontrado deve ser igual ao plano criado.");
    }

    @Test
    void testFindByStudyPlanID_NotFound() throws Exception {
        // Arrange: criar mocks para as dependências
        IStudyPlanFactory_2 factory = mock(IStudyPlanFactory_2.class);
        IStudyPlanListFactory_2 listFactory = mock(IStudyPlanListFactory_2.class);
        List<StudyPlan_2> studyPlanList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(studyPlanList);

        StudyPlanRepository_2 repository = new StudyPlanRepository_2(factory, listFactory);

        // Act: criar um StudyPlanID que não exista na lista
        StudyPlanID nonExistentID = new StudyPlanID();
        Optional<StudyPlan_2> foundPlanOpt = repository.findByStudyPlanID(nonExistentID);

        // Assert: não deve encontrar nenhum plano de estudos para esse ID
        assertFalse(foundPlanOpt.isPresent(), "Não deve encontrar um plano de estudos para um ID inexistente.");
    }
}