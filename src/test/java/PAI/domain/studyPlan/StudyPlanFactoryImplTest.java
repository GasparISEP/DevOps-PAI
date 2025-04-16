package PAI.domain.studyPlan;

import PAI.VOs.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StudyPlanFactoryImplTest {

    @Test
    void shouldCreateFactoryConstructor() throws Exception {

        //arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        MaxEcts quantityOfEcts = mock(MaxEcts.class);

        //act
        IStudyPlanFactory studyPlanFactory_2 = new StudyPlanFactoryImpl();
        StudyPlan studyPlan_DDD = studyPlanFactory_2.newStudyPlan_2(programmeID, implementationDate, durationInYears, quantityOfEcts);

        //assert
        assertNotNull(studyPlan_DDD);
    }
}