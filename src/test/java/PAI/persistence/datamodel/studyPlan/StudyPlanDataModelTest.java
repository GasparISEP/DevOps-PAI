package PAI.persistence.datamodel.studyPlan;

import PAI.VOs.DurationInYears;
import PAI.VOs.MaxEcts;
import PAI.VOs.ProgrammeID;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
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

    @Test
    void shouldNotCreateStudyPlanDataModelWithNullDataModelID() {
        //arrange
        MaxEcts maxEcts = mock(MaxEcts.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        //act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new StudyPlanDataModel(null, maxEcts, durationInYears);
        });
    }

    @Test
    void shouldNotCreateStudyPlanDataModelWithNullEcts() {
        //arrange
        StudyPlanIDDataModel studyPlanIDDataModel = mock(StudyPlanIDDataModel.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        //act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new StudyPlanDataModel(studyPlanIDDataModel, null, durationInYears);
        });
    }

    @Test
    void shouldNotCreateStudyPlanDataModelWithNullDuration() {
        //arrange
        StudyPlanIDDataModel studyPlanIDDataModel = mock(StudyPlanIDDataModel.class);
        MaxEcts maxEcts = mock(MaxEcts.class);
        //act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new StudyPlanDataModel(studyPlanIDDataModel, maxEcts, null);
        });
    }

    @Test
    void testEqualsSameReference() throws Exception {
        //arrange
        StudyPlanIDDataModel id = mock(StudyPlanIDDataModel.class);
        MaxEcts ects = new MaxEcts(180);
        DurationInYears duration = new DurationInYears(3);
        StudyPlanDataModel model = new StudyPlanDataModel(id, ects, duration);
        //act
        boolean result = model.equals(model);
        //assert
        assertTrue(result);
    }

    @Test
    void testEqualsDifferentReference() throws Exception {
        //arrange
        StudyPlanIDDataModel id1 = mock(StudyPlanIDDataModel.class);
        MaxEcts ects1 = new MaxEcts(180);
        DurationInYears duration1 = new DurationInYears(3);
        StudyPlanDataModel model1 = new StudyPlanDataModel(id1, ects1, duration1);

        StudyPlanIDDataModel id2 = mock(StudyPlanIDDataModel.class);
        MaxEcts ects2 = new MaxEcts(180);
        DurationInYears duration2 = new DurationInYears(3);
        StudyPlanDataModel model2 = new StudyPlanDataModel(id2, ects2, duration2);
        //act
        boolean result = model1.equals(model2);
        //assert
        assertFalse(result);
    }

    @Test
    void testEqualsDifferentType() throws Exception {
        //arrange
        StudyPlanIDDataModel id = mock(StudyPlanIDDataModel.class);
        MaxEcts ects = new MaxEcts(180);
        DurationInYears duration = new DurationInYears(3);
        StudyPlanDataModel model = new StudyPlanDataModel(id, ects, duration);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        //act
        boolean result = model.equals(programmeID);
        //assert
        assertFalse(result);
    }

    @Test
    void testEqualsDifferentSPButSameID() throws Exception {
        //arrange
        StudyPlanIDDataModel id = mock(StudyPlanIDDataModel.class);
        MaxEcts ects1 = new MaxEcts(180);
        DurationInYears duration1 = new DurationInYears(3);
        StudyPlanDataModel model1 = new StudyPlanDataModel(id, ects1, duration1);

        MaxEcts ects2 = new MaxEcts(180);
        DurationInYears duration2 = new DurationInYears(3);
        StudyPlanDataModel model2 = new StudyPlanDataModel(id, ects2, duration2);
        //act
        boolean result = model1.equals(model2);
        //assert
        assertTrue(result);
    }

    @Test
    void testHashCodeConsistency() throws Exception {
        //arrange
        ProgrammeIDDataModel programmeIDDataModel = new ProgrammeIDDataModel("TROLHA", "TRO");
        LocalDate date = LocalDate.of(2000, 3, 21);

        StudyPlanIDDataModel id = new StudyPlanIDDataModel(programmeIDDataModel, date);

        MaxEcts ects1 = new MaxEcts(180);
        MaxEcts ects2 = new MaxEcts(200);
        DurationInYears duration1 = new DurationInYears(3);
        DurationInYears duration2 = new DurationInYears(4);

        StudyPlanDataModel model1 = new StudyPlanDataModel(id, ects1, duration1);
        StudyPlanDataModel model2 = new StudyPlanDataModel(id, ects2, duration2);
        //act + assert
        assertEquals(model1.hashCode(), model2.hashCode());
    }

    @Test
    void GettersTest() {
        //arrange
        StudyPlanIDDataModel studyPlanIDDataModel = mock(StudyPlanIDDataModel.class);
        MaxEcts maxEcts = mock(MaxEcts.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        //act
        StudyPlanDataModel studyPlanDataModel = new StudyPlanDataModel(studyPlanIDDataModel, maxEcts, durationInYears);
        //assert
        assertEquals(studyPlanIDDataModel, studyPlanDataModel.getStudyPlanIDDataModel());
        assertEquals(maxEcts.getMaxEcts(), studyPlanDataModel.getMaxECTS());
        assertEquals(durationInYears.getDurationInYears(), studyPlanDataModel.getDurationInYears());

    }

}