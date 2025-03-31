package PAI.domain.studyPlan;

import PAI.VOs.Date;
import PAI.VOs.DurationInYears;
import PAI.VOs.ProgrammeID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StudyPlanFactoryDDDImplTest {

    @Test
    void shouldCreateFactoryConstructor() throws Exception {

        //arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);

        //act
        IStudyPlanDDDFactory studyPlanFactory_2 = new StudyPlanDDDFactoryImpl();
        StudyPlanDDD studyPlan_DDD = studyPlanFactory_2.newStudyPlan_2(programmeID, implementationDate, durationInYears);

        //assert
        assertNotNull(studyPlan_DDD);
    }
}