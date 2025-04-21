package PAI.persistence.datamodel.studyPlan;

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
        LocalDate implementationDate = mock(LocalDate.class);
        //act
        StudyPlanDataModel studyPlanDataModel = new StudyPlanDataModel(studyPlanIDDataModel, implementationDate);
        //assert
        assertNotNull(studyPlanDataModel);
    }
}