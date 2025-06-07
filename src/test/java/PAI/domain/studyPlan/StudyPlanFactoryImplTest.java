package PAI.domain.studyPlan;

import PAI.VOs.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudyPlanFactoryImplTest {

    @Test
    void shouldCreateStudyPlanWhenCreateStudyPlanIsCalled() throws Exception {

        //arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        MaxEcts maxEcts = mock(MaxEcts.class);
        QuantSemesters quantSemesters = mock(QuantSemesters.class);
        int numberOfSemesters = 2;
        IStudyPlanFactory studyPlanFactory_2 = new StudyPlanFactoryImpl();

        when(quantSemesters.getQuantityOfSemesters()).thenReturn(numberOfSemesters);

        try (MockedConstruction<StudyPlan> constructorStudyPlanDouble = mockConstruction(StudyPlan.class);
        MockedConstruction<StudyPlanID> constructorStudyPlanIDDouble = mockConstruction(StudyPlanID.class);
        MockedConstruction<DurationInYears> constructorDurationInYears = mockConstruction(DurationInYears.class)){
            //act
            StudyPlan result = studyPlanFactory_2.createStudyPlan(programmeID, implementationDate, quantSemesters, maxEcts);

            //assert
            assertEquals(1, constructorStudyPlanDouble.constructed().size());
            assertSame(result, constructorStudyPlanDouble.constructed().get(0));
        }
    }

    @Test
    void shouldCreateStudyPlanWhenCreateStudyPlanFromDataModelIsCalled() throws Exception {

        //arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        StudyPlanID studyPlanId = mock(StudyPlanID.class);
        Date startDate = mock(Date.class);
        MaxEcts maxEcts = mock(MaxEcts.class);
        DurationInYears quantSemesters = mock(DurationInYears.class);
        StudyPlanGeneratedID generatedID = mock(StudyPlanGeneratedID.class);
        IStudyPlanFactory studyPlanFactory_2 = new StudyPlanFactoryImpl();

        try (MockedConstruction<StudyPlan> constructorStudyPlanDouble = mockConstruction(StudyPlan.class)){
            //act
            StudyPlan result = studyPlanFactory_2.createStudyPlanFromDataModel(programmeID, startDate, quantSemesters, maxEcts, studyPlanId, generatedID);

            //assert
            assertEquals(1, constructorStudyPlanDouble.constructed().size());
            assertSame(result, constructorStudyPlanDouble.constructed().get(0));
        }
    }
}