package PAI.persistence.datamodel.courseInStudyPlan;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CourseInStudyPlanGeneratedIDDataModelTest {

    @Test
    void constructor_shouldSetId() {
        UUID uuid = UUID.randomUUID();
        CourseInStudyPlanGeneratedIDDataModel dataModel = new CourseInStudyPlanGeneratedIDDataModel(uuid);
        assertEquals(uuid, dataModel.getId());
    }

    @Test
    void constructor_shouldThrowIllegalArgumentException_whenIdIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new CourseInStudyPlanGeneratedIDDataModel(null);
        });
        assertEquals("Generated ID cannot be null", exception.getMessage());
    }

    @Test
    void equalsAndHashCode_shouldWorkProperly() {
        UUID uuid = UUID.randomUUID();
        CourseInStudyPlanGeneratedIDDataModel dm1 = new CourseInStudyPlanGeneratedIDDataModel(uuid);
        CourseInStudyPlanGeneratedIDDataModel dm2 = new CourseInStudyPlanGeneratedIDDataModel(uuid);
        CourseInStudyPlanGeneratedIDDataModel dm3 = new CourseInStudyPlanGeneratedIDDataModel(UUID.randomUUID());

        assertEquals(dm1, dm2);
        assertEquals(dm1.hashCode(), dm2.hashCode());
        assertNotEquals(dm1, dm3);
        assertNotEquals(dm1.hashCode(), dm3.hashCode());
    }

    @Test
    void getterAndSetter_shouldWork() {
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        CourseInStudyPlanGeneratedIDDataModel dataModel = new CourseInStudyPlanGeneratedIDDataModel(uuid1);
        assertEquals(uuid1, dataModel.getId());

        dataModel.setId(uuid2);
        assertEquals(uuid2, dataModel.getId());
    }
}