package PAI.factory;

import PAI.VOs.Date;
import PAI.VOs.DurationInYears;
import PAI.VOs.ProgrammeID;
import PAI.domain.StudyPlan_2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StudyPlanFactoryImpl_2Test {

    @Test
    void shouldCreateFactoryConstructor() throws Exception {

        //arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);

        //act
        IStudyPlanFactory_2 studyPlanFactory_2 = new StudyPlanFactoryImpl_2();
        StudyPlan_2 studyPlan_2 = studyPlanFactory_2.newStudyPlan_2(programmeID, implementationDate, durationInYears);

        //assert
        assertNotNull(studyPlan_2);
    }
}