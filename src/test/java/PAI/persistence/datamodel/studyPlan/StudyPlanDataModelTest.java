package PAI.persistence.datamodel.studyPlan;

import PAI.VOs.DurationInYears;
import PAI.VOs.MaxEcts;
import PAI.VOs.ProgrammeID;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

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
        UUID uuid = mock(UUID.class);

        //act
        StudyPlanDataModel studyPlanDataModel = new StudyPlanDataModel(studyPlanIDDataModel, uuid, maxEcts, durationInYears);

        //assert
        assertNotNull(studyPlanDataModel);
    }

    @Test
    void shouldNotCreateStudyPlanDataModelWithNullDataModelID() {
        //arrange
        MaxEcts maxEcts = mock(MaxEcts.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        UUID uuid = mock(UUID.class);

        //act + assert
        assertThrows(NullPointerException.class, () -> {
            new StudyPlanDataModel(null, uuid, maxEcts, durationInYears);
        });
    }

    @Test
    void shouldNotCreateStudyPlanDataModelWithNullUUID() {
        //arrange
        StudyPlanIDDataModel studyPlanIDDataModel = mock(StudyPlanIDDataModel.class);
        MaxEcts maxEcts = mock(MaxEcts.class);
        DurationInYears durationInYears = mock(DurationInYears.class);

        //act + assert
        assertThrows(NullPointerException.class, () -> {
            new StudyPlanDataModel(studyPlanIDDataModel, null, maxEcts, durationInYears);
        });
    }

    @Test
    void shouldNotCreateStudyPlanDataModelWithNullEcts() {
        //arrange
        StudyPlanIDDataModel studyPlanIDDataModel = mock(StudyPlanIDDataModel.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        UUID uuid = mock(UUID.class);

        //act + assert
        assertThrows(NullPointerException.class, () -> {
            new StudyPlanDataModel(studyPlanIDDataModel, uuid, null, durationInYears);
        });
    }

    @Test
    void shouldNotCreateStudyPlanDataModelWithNullDuration() {
        //arrange
        StudyPlanIDDataModel studyPlanIDDataModel = mock(StudyPlanIDDataModel.class);
        MaxEcts maxEcts = mock(MaxEcts.class);
        UUID uuid = mock(UUID.class);

        //act + assert
        assertThrows(NullPointerException.class, () -> {
            new StudyPlanDataModel(studyPlanIDDataModel, uuid, maxEcts, null);
        });
    }

    @Test
    void testEqualsSameReference() throws Exception {
        //arrange
        StudyPlanIDDataModel id = mock(StudyPlanIDDataModel.class);
        MaxEcts ects = new MaxEcts(180);
        DurationInYears duration = new DurationInYears(3);
        UUID uuid = mock(UUID.class);

        StudyPlanDataModel model = new StudyPlanDataModel(id, uuid, ects, duration);
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
        UUID uuid1 = mock(UUID.class);

        StudyPlanDataModel model1 = new StudyPlanDataModel(id1, uuid1, ects1, duration1);

        StudyPlanIDDataModel id2 = mock(StudyPlanIDDataModel.class);
        MaxEcts ects2 = new MaxEcts(180);
        DurationInYears duration2 = new DurationInYears(3);
        UUID uuid2 = mock(UUID.class);

        StudyPlanDataModel model2 = new StudyPlanDataModel(id2, uuid2, ects2, duration2);
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
        UUID uuid = mock(UUID.class);

        StudyPlanDataModel model = new StudyPlanDataModel(id, uuid, ects, duration);
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
        UUID uuid1 = mock(UUID.class);
        StudyPlanDataModel model1 = new StudyPlanDataModel(id, uuid1, ects1, duration1);

        MaxEcts ects2 = new MaxEcts(180);
        DurationInYears duration2 = new DurationInYears(3);
        UUID uuid2 = mock(UUID.class);

        StudyPlanDataModel model2 = new StudyPlanDataModel(id, uuid2, ects2, duration2);
        //act
        boolean result = model1.equals(model2);
        //assert
        assertTrue(result);
    }

    @Test
    void testHashCodeConsistency() throws Exception {
        //arrange
        ProgrammeIDDataModel programmeIDDataModel = new ProgrammeIDDataModel("TRO");
        LocalDate date = LocalDate.of(2000, 3, 21);

        StudyPlanIDDataModel id = new StudyPlanIDDataModel(programmeIDDataModel, date);

        MaxEcts ects1 = new MaxEcts(180);
        MaxEcts ects2 = new MaxEcts(200);
        DurationInYears duration1 = new DurationInYears(3);
        DurationInYears duration2 = new DurationInYears(4);
        UUID uuid1 = mock(UUID.class);
        UUID uuid2 = mock(UUID.class);

        StudyPlanDataModel model1 = new StudyPlanDataModel(id, uuid1, ects1, duration1);
        StudyPlanDataModel model2 = new StudyPlanDataModel(id, uuid2, ects2, duration2);
        //act + assert
        assertEquals(model1.hashCode(), model2.hashCode());
    }

    @Test
    void GettersTest() {
        //arrange
        StudyPlanIDDataModel studyPlanIDDataModel = mock(StudyPlanIDDataModel.class);
        MaxEcts maxEcts = mock(MaxEcts.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        UUID uuid = mock(UUID.class);

        //act
        StudyPlanDataModel studyPlanDataModel = new StudyPlanDataModel(studyPlanIDDataModel, uuid, maxEcts, durationInYears);
        //assert
        assertEquals(studyPlanIDDataModel, studyPlanDataModel.getStudyPlanIDDataModel());
        assertEquals(maxEcts.getMaxEcts(), studyPlanDataModel.getMaxECTS());
        assertEquals(durationInYears.getDurationInYears(), studyPlanDataModel.getDurationInYears());

    }

    @Test
    void testDifferentHashCode() throws Exception {
        //arrange
        ProgrammeIDDataModel programmeIDDataModel1 = new ProgrammeIDDataModel("TRO");
        LocalDate date1 = LocalDate.of(2000, 3, 21);
        StudyPlanIDDataModel id1 = new StudyPlanIDDataModel(programmeIDDataModel1, date1);

        ProgrammeIDDataModel programmeIDDataModel2 = new ProgrammeIDDataModel("AJU");
        LocalDate date2 = LocalDate.of(1991, 3, 21);
        StudyPlanIDDataModel id2 = new StudyPlanIDDataModel(programmeIDDataModel2, date2);

        MaxEcts ects1 = new MaxEcts(180);
        MaxEcts ects2 = new MaxEcts(200);

        DurationInYears duration1 = new DurationInYears(3);
        DurationInYears duration2 = new DurationInYears(4);

        UUID uuid1 = mock(UUID.class);
        UUID uuid2 = mock(UUID.class);

        StudyPlanDataModel model1 = new StudyPlanDataModel(id1, uuid1, ects1, duration1);
        StudyPlanDataModel model2 = new StudyPlanDataModel(id2, uuid2, ects2, duration2);

        //act + assert
        assertNotEquals(model1.hashCode(), model2.hashCode());
    }

    @Test
    void getUUIDShouldReturnUUID () {
        // Arrange
        StudyPlanIDDataModel studyPlanIDDataModel = mock(StudyPlanIDDataModel.class);
        MaxEcts maxEcts = mock(MaxEcts.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        UUID uuid = mock(UUID.class);

        StudyPlanDataModel studyPlanDataModel = new StudyPlanDataModel(studyPlanIDDataModel, uuid, maxEcts, durationInYears);

        // Act
        UUID result = studyPlanDataModel.getUUID();

        // Assert
        assertEquals(result, uuid);
    }
}