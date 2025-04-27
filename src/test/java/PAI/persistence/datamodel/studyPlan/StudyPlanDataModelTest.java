package PAI.persistence.datamodel.studyPlan;

import PAI.VOs.DurationInYears;
import PAI.VOs.MaxEcts;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StudyPlanDataModelTest {

    @Test
    void shouldCreateStudyPlanDataModelWithoutParameters() {
        //arrange + act
        StudyPlanDataModel studyPlanDataModel = new StudyPlanDataModel();
        //assert
        assertNotNull(studyPlanDataModel);
    }

    @Test
    void shouldCreateStudyPlanDataModelWithParameters() {
        //arrange
        StudyPlanIDDataModel studyPlanIDDataModel = mock(StudyPlanIDDataModel.class);
        MaxEcts maxEcts = mock(MaxEcts.class);
        DurationInYears durationInYears = mock(DurationInYears.class);

        //act
        StudyPlanDataModel studyPlanDataModel = new StudyPlanDataModel(studyPlanIDDataModel, maxEcts, durationInYears);
        //assert
        assertNotNull(studyPlanDataModel);
    }
}