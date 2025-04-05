package PAI.domain.studyPlan;

import PAI.VOs.Date;
import PAI.VOs.DurationInYears;
import PAI.VOs.ProgrammeID;
import PAI.VOs.QuantEcts;
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
        QuantEcts quantityOfEcts = mock(QuantEcts.class);

        //act
        IStudyPlanFactory studyPlanFactory_2 = new StudyPlanFactoryImpl();
        StudyPlan studyPlan_DDD = studyPlanFactory_2.newStudyPlan_2(programmeID, implementationDate, durationInYears, quantityOfEcts);

        //assert
        assertNotNull(studyPlan_DDD);
    }
}